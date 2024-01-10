package com.office.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.library.book.BookVo;

@Controller
@RequestMapping("/book/user")
public class BookController {
	
	@Autowired
	BookService bookService;
	
    @GetMapping("/searchBookConfirm")
    public String searchBookConfirm(BookVo bookVo, Model model) {
        System.out.println("[UserBookController] searchBookConfirm()");
        String nextPage = "user/book/search_book";

        List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);

        model.addAttribute("bookVos", bookVos);

        return nextPage;
    }
}