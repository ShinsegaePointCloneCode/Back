package com.example.smilekarina.agree.application;

import com.example.smilekarina.agree.domain.AgreeAdvertise;
import com.example.smilekarina.agree.domain.AgreeServiceManage;
import com.example.smilekarina.agree.domain.AgreeType;
import com.example.smilekarina.agree.domain.QAgreeServiceManage;
import com.example.smilekarina.agree.dto.AgreeAdvertiseDto;
import com.example.smilekarina.agree.dto.AgreeServiceManageDto;
import com.example.smilekarina.agree.infrastructure.AgreeAdvertiseRepository;
import com.example.smilekarina.agree.infrastructure.AgreeServiceManageRepository;
import com.example.smilekarina.agree.vo.AgreeAdvertiseOut;
import com.example.smilekarina.agree.vo.AgreeServiceManageOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true)
public class AgreeServiceImpl implements AgreeService{
    private final UserService userService;
    private final AgreeAdvertiseRepository agreeAdvertiseRepository;
    private final AgreeServiceManageRepository agreeServiceManageRepository;
    private final ModelMapper modelMapper;

    @Override
//    @Transactional(readOnly = false)
    public void createAgreeAdvertise(String token, AgreeAdvertiseDto agreeAdvertiseDto) {
        Long userId = userService.getUserIdFromToken(token);
        Optional<AgreeAdvertise> agreeAdvertise = agreeAdvertiseRepository.findByUserId(userId);
        if (agreeAdvertise.isPresent() && agreeAdvertise != null) {
            // agreeAdvertise edit
            log.info(" ==> edit"+ agreeAdvertise);
            editAdvertise(agreeAdvertise.get(), agreeAdvertiseDto);
        } else {
            // agreeAdvertise create
            log.info(" ==> create" + agreeAdvertiseDto);
            createAdvertise(userId, agreeAdvertiseDto);
        }
    }
    @Override
//    @Transactional(readOnly = false)
    public void createAgreeAdvertiseByUser(Long userId, AgreeAdvertiseDto agreeAdvertiseDto) {
        createAdvertise(userId, agreeAdvertiseDto);
    }
    @Override
    public AgreeAdvertiseOut getAgreeAdvertise(String token) {
        Long userId = userService.getUserIdFromToken(token);
        return modelMapper.map(agreeAdvertiseRepository.findByUserId(userId),AgreeAdvertiseOut.class);
    }

    @Override
//    @Transactional(readOnly = false)
    public void createAgreeServiceManage(String token, AgreeServiceManageDto agreeServiceManageDto) {
        Long userId = userService.getUserIdFromToken(token);
        Optional<AgreeServiceManage> agreeServiceManage = agreeServiceManageRepository.findByUserIdAndAgreeType(userId, agreeServiceManageDto.getAgreeType());
        if (agreeServiceManage.isPresent()) {
//            edit
            editService(agreeServiceManage.get(), agreeServiceManageDto);
        } else {
//            create
            createService(userId, agreeServiceManageDto);
        }
    }

    @Override
    public List<AgreeServiceManageOut> getAgreeServiceManage(String token) {
        Long userId = userService.getUserIdFromToken(token);
        List<AgreeServiceManage> results = agreeServiceManageRepository.findByUserId(userId);

        // 현재 results에 있는 AgreeType 목록 추출
        Set<AgreeType> existingTypes = results.stream()
                .map(AgreeServiceManage::getAgreeType)
                .collect(Collectors.toSet());
        // 모든 AgreeType 중에서 existingTypes에 없는 AgreeType만 찾아서 기본값으로 추가
        List<AgreeServiceManage> defaultEntities = Arrays.stream(AgreeType.values())
                .filter(type -> !existingTypes.contains(type))
                .map(type -> AgreeServiceManage.builder()
                        .agreeType(type)
                        .agreeCheck(false)
                        .build())
                .toList();
        log.info(defaultEntities.toString());
        results.addAll(defaultEntities);

        // AgreeServiceManage 리스트를 AgreeServiceManageOut 리스트로 변환
        return results.stream()
                .map(entity -> AgreeServiceManageOut.builder()
                        .agreeType(entity.getAgreeType())
                        .agreeCheck(entity.getAgreeCheck())
                        .time(Optional.ofNullable(entity.getUpdatedDate())
                                .orElse(entity.getCreatedDate()))
                        .build())
                .toList();
    }

    private void createService(Long userId, AgreeServiceManageDto dto) {
        AgreeServiceManage agreeServiceManage = AgreeServiceManage.builder()
                .agreeCheck(dto.getAgreeCheck())
                .userId(userId)
                .agreeType(dto.getAgreeType())
                .build();
        agreeServiceManageRepository.save(agreeServiceManage);
    }

    private void editService(AgreeServiceManage agreeServiceManage, AgreeServiceManageDto dto) {
        agreeServiceManage.setAgreeCheck(dto.getAgreeCheck());
        agreeServiceManageRepository.save(agreeServiceManage);
    }

    private void createAdvertise(Long userId, AgreeAdvertiseDto agreeAdvertiseDto) {
        AgreeAdvertise agreeAdvertise = AgreeAdvertise.builder()
                .optionOne(agreeAdvertiseDto.getOptionOne())
                .optionTwo(agreeAdvertiseDto.getOptionTwo())
                .agreeEmail(agreeAdvertiseDto.getAgreeEmail())
                .letter(agreeAdvertiseDto.getLetter())
                .DM(agreeAdvertiseDto.getDm())
                .TM(agreeAdvertiseDto.getTm())
                .userId(userId)
                .build();
        agreeAdvertiseRepository.save(agreeAdvertise);
    }
    private void editAdvertise(AgreeAdvertise agreeAdvertise, AgreeAdvertiseDto dto) {
        agreeAdvertise.setOptionOne(dto.getOptionOne());
        agreeAdvertise.setOptionTwo(dto.getOptionTwo());
        agreeAdvertise.setAgreeEmail(dto.getAgreeEmail());
        agreeAdvertise.setLetter(dto.getLetter());
        agreeAdvertise.setDM(dto.getDm());
        agreeAdvertise.setTM(dto.getTm());
        agreeAdvertiseRepository.save(agreeAdvertise);
    }
}
