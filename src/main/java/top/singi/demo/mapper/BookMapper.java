package top.singi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Component;
import top.singi.demo.entity.Book;

import java.util.ArrayList;
import java.util.Map;

@Component
public interface BookMapper extends BaseMapper<Book> {

    Map<String, Object> selectBookById(int id);

    ArrayList<Map<String, Object>> selectBookAndProvince();
}
