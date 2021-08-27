package com.llschool.servicebase.exceptionhandler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //生成有参构造
@NoArgsConstructor  //生成无参构造
public class LlschoolException extends RuntimeException{
    private Integer code;
    private String msg;

}
