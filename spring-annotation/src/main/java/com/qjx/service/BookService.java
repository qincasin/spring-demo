package com.qjx.service;

import com.qjx.dao.BookDao;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Created by qincasin on 2020/3/26.
 */
@Service
@ToString
public class BookService {
//    @Qualifier("bookDao")
//    @Autowired
//    @Resource
    @Inject
    private BookDao bookDao;

    public void print() {
        System.out.println("bookService");
    }

    public void lable(){
        System.out.println("bookDao:"+bookDao);
    }

}
