package am.tt.library.service;

import am.tt.library.request.BookRequest;
import am.tt.library.response.BookResponse;
import java.util.List;

public interface BookService {

  void save(BookRequest bookRequest);

  void deleteBook(int id);

  BookResponse findById(int id);

  BookResponse updateBook(int id, BookRequest bookRequest);

  List<BookResponse> findAllBooks();

}
