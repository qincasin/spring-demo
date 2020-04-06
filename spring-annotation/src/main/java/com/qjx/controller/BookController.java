package com.qjx.controller;

import com.qjx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by qincasin on 2020/3/26.
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
