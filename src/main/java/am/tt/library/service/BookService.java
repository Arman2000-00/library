package am.tt.library.service;

import am.tt.library.request.BookRequest;
import am.tt.library.response.BookResponse;
import am.tt.library.security.CurrentUser;
import java.util.List;

public interface BookService {

  void save(BookRequest bookRequest, CurrentUser currentUser);

  void deleteBook(int id, CurrentUser currentUser);

  BookResponse findById(int id);

  BookResponse updateBook(int id, BookRequest bookRequest);

  List<BookResponse> findAllBooks();

}
