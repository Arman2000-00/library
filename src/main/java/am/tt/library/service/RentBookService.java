package am.tt.library.service;

import am.tt.library.model.RentBook;

public interface RentBookService {

  void book(int bookId, String email);

  void returnBook(int id);

  boolean existsByBookIdAndEmail(int bookId, String email);

}
