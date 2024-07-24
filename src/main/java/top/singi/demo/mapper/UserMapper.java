package top.singi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.singi.demo.entity.User;

@Component
public interface UserMapper extends BaseMapper<User> {
}
