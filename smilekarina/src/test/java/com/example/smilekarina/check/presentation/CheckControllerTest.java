package com.example.smilekarina.check.presentation;

import com.example.smilekarina.check.application.CheckService;
import com.example.smilekarina.check.vo.CreateCheckIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CheckControllerTest {
    @InjectMocks // 가짜 객체주입을 위해 사용
    private CheckController checkController;

    @Mock // 가짜 객체 생성을 위해 사용
    private CheckService checkService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(checkController).build();
    }
    @SneakyThrows
    @DisplayName("check 성공")
    @Test
    void createCheck() {
        // given
        String token = "someToken";
        CreateCheckIn createCheckIn = new CreateCheckIn();
        String content = new ObjectMapper().writeValueAsString(createCheckIn);

        doNothing().when(checkService).createCheck(token, createCheckIn.getTime());
        // when
        ResultActions result = mockMvc.perform(
            put("/benefits/pntPlus/attend")
                    .header("Authorization", token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
        );
        // then

    }
    @DisplayName("check get 가져옴")
    @Test
    void getCheck() {
        // given
        // when
        // then
    }
}