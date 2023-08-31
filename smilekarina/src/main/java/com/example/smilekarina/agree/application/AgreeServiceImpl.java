package com.example.smilekarina.agree.application;

import com.example.smilekarina.agree.domain.AgreeAdvertise;
import com.example.smilekarina.agree.dto.AgreeAdvertiseDto;
import com.example.smilekarina.agree.infrastructure.AgreeAdvertiseRepository;
import com.example.smilekarina.agree.vo.AgreeAdvertiseOut;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true)
public class AgreeServiceImpl implements AgreeService{
    private final UserService userService;
    private final AgreeAdvertiseRepository agreeAdvertiseRepository;


    @Override
    public void createAgreeAdvertise(String token, AgreeAdvertiseDto agreeAdvertiseDto) {
        Long userId = userService.getUserIdFromToken(token);
        Optional<AgreeAdvertise> agreeAdvertise = agreeAdvertiseRepository.findByUserId(userId);
        if (agreeAdvertise.isPresent()) {
            // agreeAdvertise edit
            editAgreeAdvertise(agreeAdvertise.get() ,userId, agreeAdvertiseDto);
        } else {
            // agreeAdvertise save
            createAgreeAdvertise(userId, agreeAdvertiseDto);
        }
    }


    @Override
    public AgreeAdvertiseOut getAgreeAdvertise(String token) {
        Long userId = userService.getUserIdFromToken(token);

        return null;
    }

    private void createAgreeAdvertise(Long userId, AgreeAdvertiseDto agreeAdvertiseDto) {
        AgreeAdvertise agreeAdvertise = AgreeAdvertise.builder()
                .optionOne(agreeAdvertiseDto.getOptionOne())
                .optionTwo(agreeAdvertiseDto.getOptionTwo())
                .agreeEmail(agreeAdvertiseDto.getAgreeEmail())
                .letter(agreeAdvertiseDto.getLetter())
                .DM(agreeAdvertiseDto.getDM())
                .TM(agreeAdvertiseDto.getTM())
                .userId(userId)
                .build();
        AgreeAdvertise savedAgreeAdvertise = agreeAdvertiseRepository.save(agreeAdvertise);
    }
    private void editAgreeAdvertise(AgreeAdvertise agreeAdvertise, Long userId, AgreeAdvertiseDto agreeAdvertiseDto) {


    }
}
