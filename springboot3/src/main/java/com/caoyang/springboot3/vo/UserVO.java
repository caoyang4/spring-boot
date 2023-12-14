package com.caoyang.springboot3.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author caoyang
 * @create 2023-12-14 10:12
 */
@Data
public class UserVO {

    private String creditId;

    private String name;

    private String loginName;

    private  Integer age;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

}
