package org.example.expert.domain.comment.controller;

import org.example.expert.domain.comment.service.CommentAdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CommentAdminController.class)
@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class CommentAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentAdminService commentAdminService;

    @Test
    public void 어드민_댓글_삭제_로그출력(CapturedOutput output) throws Exception {
        // given
        long commentId = 1L;
        long userId = 2L;

        // when
        var resultActions = mockMvc.perform(delete("/admin/comments/{commentId}", commentId).requestAttr("userId", userId));


        // then
//        resultActions.andExpect(status().isOk());
        // 로그에 특정 string이 포함되어 있는지 확인
        assertThat(output.getAll()).contains("adminUserId =", "requestTime =", "requestURL =");
    }

}