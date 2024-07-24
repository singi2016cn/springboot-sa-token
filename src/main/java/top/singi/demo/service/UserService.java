package top.singi.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.singi.demo.entity.User;
import top.singi.demo.mapper.UserMapper;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {
}
