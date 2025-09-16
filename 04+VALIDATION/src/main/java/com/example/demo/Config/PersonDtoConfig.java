package com.example.demo.Config;

import com.example.demo.Dto.PersonDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonDtoConfig {

    @Bean
    public PersonDto person03(){
        return PersonDto.builder()
                .name("김정은")
                .age(45)
                .addr("평양")
                .build();
    }
    @Bean(name = "personBean")
    public PersonDto person04(){
        return PersonDto.builder()
                .name("히틀러")
                .age(38)
                .addr("베를린")
                .build();
    }
}
