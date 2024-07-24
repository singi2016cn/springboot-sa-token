package top.singi.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.singi.demo.entity.Book;
import top.singi.demo.mapper.BookMapper;

@Service
public class BookService extends ServiceImpl<BookMapper, Book> implements IService<Book> {
}
