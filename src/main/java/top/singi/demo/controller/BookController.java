package top.singi.demo.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.singi.demo.entity.Book;
import top.singi.demo.mapper.BookMapper;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookMapper bookMapper;

    @GetMapping("/{id}")
    Book show(@PathVariable("id") Integer id) {
        return new LambdaQueryChainWrapper<>(Book.class).eq(Book::getId, id).one();
    }

    @GetMapping("/one/{id}")
    Map<String, Object> one(@PathVariable("id") Integer id) {
        return bookMapper.selectBookById(id);
    }

    @GetMapping("/bookProvince")
    ArrayList<Map<String, Object>> bookProvince() {
        return bookMapper.selectBookAndProvince();
    }
}