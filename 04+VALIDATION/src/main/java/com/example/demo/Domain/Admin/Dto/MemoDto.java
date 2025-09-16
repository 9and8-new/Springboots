package com.example.demo.Domain.Admin.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor//모든인자 생성자
@AllArgsConstructor//toString
@Data//Getter and Setter
public class MemoDto {
    //@NotNull : null이 아니어야함. 모든 타입(Ob,St,In등), null이면 예외
    //@NotBlank : '문자열'이 null이 아니고, 공백이아니어야함. 문자열(St)전용, null,""," "(공백)이면 예외
    @Min(value = 10 , message="ID는 10자 이상 적어라.") //최소값
    @Max(value = 65535 , message="적으란다고 많이적네?ㅋ 65535밑으로 적어라") //최대값
    @NotNull(message="ID는 필수 ☆항목☆ 이다.") // 값 필수
    private Long id;
    @NotBlank(message="TEXT는 ☆필수☆다.") // 값 필수
    private String text;
    @NotBlank(message="작성자 입력안하냐?") // 값 필수
    @Email(message="부족하냐? 이메일 형식으로 입력해라.")
    private String writer;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull(message="날짜 입력할줄 모르냐?") // 값 필수
    @Future(message = "총맞았냐? 현재기준으로 예약해라") // 값 필수
    private LocalDateTime createAt;

    private LocalDate data_test;
}
