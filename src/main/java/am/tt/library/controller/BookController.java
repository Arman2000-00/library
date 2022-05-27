package am.tt.library.controller;

import am.tt.library.request.BookRequest;
import am.tt.library.response.BookResponse;
import am.tt.library.security.CurrentUser;
import am.tt.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/add")
    public String addBook() {
        return "index";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute BookRequest bookRequest,
                          @AuthenticationPrincipal CurrentUser currentUser) {
        bookService.save(bookRequest, currentUser);
        return "redirect:/books/add";
    }

    @GetMapping("/all")
    public String allBooks(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<BookResponse> allBooks = bookService.findAllBooks();
        modelMap.addAttribute("books", allBooks);
        modelMap.addAttribute("user", currentUser.getUser());
        return "books";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        bookService.deleteBook(id, currentUser);
        return "redirect:/books/all";
    }

    @GetMapping("/count")
    public String findByIdAndUserEmail(@RequestParam("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        bookService.findByIdAndUserEmail(id, currentUser.getUser().getEmail());
        return "redirect:/books/all";
    }
}
