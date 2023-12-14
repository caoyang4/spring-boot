package com.caoyang.springboot3.controller;

import com.caoyang.springboot3.dao.UserDO;
import com.caoyang.springboot3.service.UserService;
import com.caoyang.springboot3.transfer.UserTransfer;
import com.caoyang.springboot3.vo.UserVO;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author caoyang
 * @create 2023-12-14 10:11
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/boot3")
public class UserController {

    @PostConstruct
    public void init() {
        log.info("welcome to UserController");
    }

    @Autowired
    private UserService userService;

    @GetMapping("/user/{name}")
    public UserVO getUser(@PathVariable String name) {
        return UserTransfer.INSTANCE.toVO(userService.getUser(name));
    }

    @GetMapping("/user")
    public List<UserVO> getUsers() {
        List<UserDO> userList = userService.getUserList();
        return UserTransfer.INSTANCE.toVOList(userList);
    }

    @PostMapping("/user")
    public Boolean addUsers(@Valid @RequestBody UserVO userVO){
        return userService.addUser(UserTransfer.INSTANCE.toDO(userVO));
    }
}
