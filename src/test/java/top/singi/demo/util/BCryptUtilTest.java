package top.singi.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BCryptUtilTest {

    @Autowired
    BCryptUtil bCryptUtil;

    @Test
    void check() {
        String password = "12345678";
        String encrypt = bCryptUtil.encrypt(password);
        log.info("加密前的密码：{}", password);
        log.info("加密后的密码：{}", encrypt);
        assertEquals(true, bCryptUtil.check(password, encrypt));
    }
}