package org.example.expert.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.expert.domain.user.dto.request.UserRoleChangeRequest;
import org.example.expert.domain.user.service.UserAdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserAdminController.class)
@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class UserAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserAdminService userAdminService;

    @Test
    public void 어드민_권한_변경_로그출력(CapturedOutput output) throws Exception {
        // given
        long userId = 2L;
        UserRoleChangeRequest userRoleChangeRequest = new UserRoleChangeRequest("USER");

        // when
         var resultActions = mockMvc.perform(patch("/admin/users/{userId}", userId)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .requestAttr("userId", userId)
                 .content(objectMapper.writeValueAsString(userRoleChangeRequest)));


        // then
//        resultActions.andExpect(status().isOk());
        // 로그에 특정 string이 포함되어 있는지 확인
        assertThat(output.getAll()).contains("adminUserId =", "requestTime =", "requestURL =");
    }

}