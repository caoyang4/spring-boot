package com.caoyang.springboot3.service;

import com.caoyang.springboot3.dao.UserDO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caoyang
 * @create 2023-12-14 10:07
 */
@Slf4j(topic = "UserServiceTest")
class UserServiceTest {


    @Test
    void test1() {
        var i = 4;
        var name = "young";
        log.info("i={}, name={}", i, name);
    }

    @Test
    void test2() {
        var age = 70;
        var type = switch (age) {
            case 10 -> "束发";
            case 20 -> "弱冠";
            case 30 -> "而立";
            case 40 -> "不惑";
            case 50 -> "知天命";
            case 60 -> "花甲";
            case 70 -> "古稀";
            default -> "老贼";
        };
        log.info("age={}, type={}", age, type);
    }

    @Test
    void test3() {
        String text = """
                        占位符 ${}中的变量名必须是有效的 Java 标识符。
                        占位符 ${}中的变量值可以是任意类型，编译器会自动进行类型转换。
                        如果变量值为 null，占位符 ${}会被替换为字符串"null"。
                      """;
        log.info(text, 1,2,3);
    }

    @Test
    void test4() {
         Thread.ofVirtual().start(() -> log.info("{}", "测试1"));
         Thread.ofVirtual().start(() -> log.info("{}", "测试2"));
    }

    @Test
    void test5() {
        var taskSize = 10;
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < taskSize; i++) {
            String msg =  "任务" + (i+1);
            executor.submit(() -> log.info("执行{}", msg));
        }
    }

    @Test
    void test6() {
        var list = List.of("apple", "banana", "orange");
        log.info("{}", list);
    }

    @Test
    void test7() {
        val userDO = UserDO.builder().age(1).name("young").build();
        UserService userService = userDO instanceof UserService ? ((UserService) userDO) : null;
        System.out.println(userService);
    }

    @Test
    void test8() {

    }
}