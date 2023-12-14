package com.caoyang.springboot3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caoyang
 * @create 2023-12-14 15:20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/boot3")
public class AdminController {

    @GetMapping("")
    public String welcome() {
        return "welcome";
    }

}
