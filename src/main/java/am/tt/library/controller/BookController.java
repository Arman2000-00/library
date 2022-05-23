package am.tt.library.controller;

import am.tt.library.request.BookRequest;
import am.tt.library.response.BookResponse;
import am.tt.library.security.CurrentUser;
import am.tt.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/add")
    public String addBook(){
        return "main";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute BookRequest bookRequest, @AuthenticationPrincipal CurrentUser currentUser) {
        bookService.save(bookRequest, currentUser);
        return "redirect:/api/v1/books/add";
    }

    @GetMapping("/all")
    public String allBooks(ModelMap modelMap) {
        List<BookResponse> allBooks = bookService.findAllBooks();
        modelMap.addAttribute("books", allBooks);
        return "books";
    }

}
