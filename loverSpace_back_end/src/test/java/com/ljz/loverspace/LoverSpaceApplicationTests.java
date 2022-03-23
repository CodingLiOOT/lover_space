package com.ljz.loverspace;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoverSpaceApplicationTests {

    @BeforeEach
    void setUp() {
    System.out.println("测试开始");
    }
    @AfterAll
    static void testDown() {
        System.out.println("测试结束");
    }

}
