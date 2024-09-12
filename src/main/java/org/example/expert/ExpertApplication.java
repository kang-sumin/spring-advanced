package org.example.expert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;
@EnableAspectJAutoProxy // AOP 활성화, AspectJ의 자동 프록시 생성 기능 활성화
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class ExpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpertApplication.class, args);
    }

}
