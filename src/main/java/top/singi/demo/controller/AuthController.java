package top.singi.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.singi.demo.entity.User;
import top.singi.demo.util.BCryptUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    BCryptUtil bCryptUtil;

    @PostMapping("/login")
    SaResult login(@RequestBody User requestUser) {
        User user = new LambdaQueryChainWrapper<>(User.class).eq(User::getEmail, requestUser.getEmail()).one();
        if (user == null) {
            return SaResult.error("用户不存在");
        }
        if (!bCryptUtil.check(requestUser.getPassword(), user.getPassword())) {
            return SaResult.error("密码错误");
        }

        StpUtil.login(user.getId());

        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(StpUtil.getTokenTimeout());

        return SaResult.data(Map.of(
                "token", StpUtil.getTokenValue(),
                "expiredAtTimestampMillis", localDateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(localDateTime)).toEpochMilli(),
                "expiredAtTimestamp", localDateTime.toEpochSecond(ZoneId.systemDefault().getRules().getOffset(localDateTime)),
                "expiredAt", localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        ));
    }

    @GetMapping("/me")
    SaResult me() {
        User user = new LambdaQueryChainWrapper<>(User.class).eq(User::getId, StpUtil.getLoginId()).one();
        user.setPassword(null);
        return SaResult.data(user);
    }
}