package com.example.smilekarina.agree.application;

import com.example.smilekarina.agree.domain.Agree;
import com.example.smilekarina.agree.domain.AgreeAdvertise;
import com.example.smilekarina.agree.dto.AgreeAdvertiseDto;
import com.example.smilekarina.agree.infrastructure.AgreeAdvertiseRepository;
import com.example.smilekarina.agree.infrastructure.AgreeRepository;
import com.example.smilekarina.agree.vo.AgreeAdvertiseOut;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreeServiceImpl implements AgreeService{
    private final UserService userService;
    private final AgreeRepository agreeRepository;
    private final AgreeAdvertiseRepository agreeAdvertiseRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createAgreeAdvertise(String token, AgreeAdvertiseDto agreeAdvertiseDto) {
        Long userId = userService.getUserIdFromToken(token);
        // agreeAdvertise save
        Long agreeAdvertiseId = createAgreeAdvertise(agreeAdvertiseDto);
        // agree save
        Agree agree = Agree.builder()
                .userId(userId)
                .agreeAdvertiseId(agreeAdvertiseId)
                .build();
        agreeRepository.save(agree);
    }

    @Override
    public AgreeAdvertiseOut getAgreeAdvertise(String token) {
        Long userId = userService.getUserIdFromToken(token);
        Long agreeAdvertiseId = agreeRepository.findByUserId(userId).getAgreeAdvertiseId();
        Optional<AgreeAdvertise> agreeAdvertise = agreeAdvertiseRepository.findById(agreeAdvertiseId);
        return agreeAdvertise.map(advertise -> modelMapper.map(advertise, AgreeAdvertiseOut.class)).orElse(null);
    }

    private Long createAgreeAdvertise(AgreeAdvertiseDto agreeAdvertiseDto) {
        AgreeAdvertise agreeAdvertise = AgreeAdvertise.builder()
                .optionOne(agreeAdvertiseDto.getOptionOne())
                .optionTwo(agreeAdvertiseDto.getOptionTwo())
                .agreeEmail(agreeAdvertiseDto.getAgreeEmail())
                .letter(agreeAdvertiseDto.getLetter())
                .DM(agreeAdvertiseDto.getDM())
                .TM(agreeAdvertiseDto.getTM())
                .build();
        AgreeAdvertise savedAgreeAdvertise = agreeAdvertiseRepository.save(agreeAdvertise);
        return savedAgreeAdvertise.getId();
    }
}
