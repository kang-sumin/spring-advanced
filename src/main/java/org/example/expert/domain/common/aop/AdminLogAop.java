package org.example.expert.domain.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Slf4j
@Aspect
public class AdminLogAop {

    /**
     * 포인트컷 : deleteComment(), chageUserRole()에 범위 지정
     */
    @Pointcut( "execution(* org.example.expert.domain.comment.controller.CommentAdminController.deleteComment(..)) || execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    private void AdminLogControllerLayer(){

    }

    @Around("AdminLogControllerLayer()")
    public void beforeAdminLogServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Long adminUserId = (Long) request.getAttribute("userId");
        LocalDateTime requestTime = LocalDateTime.now();
        String requestURL = request.getRequestURI();


        log.info("::: adminUserId = {}, requestTime = {}, requestURL = {} :::", adminUserId, requestTime, requestURL);

        try{
            joinPoint.proceed();
        }finally {
            log.info("::: 작동 끝 :::");
        }
    }



}
