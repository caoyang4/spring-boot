package com.caoyang.springboot3.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author caoyang
 * @create 2023-12-14 10:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "user")
public class UserDO implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String creditId;

    private String name;

    private String loginName;

    private  Integer age;

    private String email;

    private Date registerTime;

    private Date loginTime;

    private Date createTime;

    private Date updateTime;

}
