package am.tt.library.service.impl;

import am.tt.library.model.RentBook;
import am.tt.library.repository.RentBookRepository;
import am.tt.library.service.RentBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentBookServiceImpl implements RentBookService {

  private final RentBookRepository rentBookRepository;

  @Override
  public void book(int bookId, String email) {
    rentBookRepository.save(RentBook.builder()
        .bookId(bookId)
        .email(email)
        .build());
  }

  @Override
  public void returnBook(int id) {
    rentBookRepository.deleteByBookId(id);
  }

  @Override
  public boolean existsByBookIdAndEmail(int bookId, String email) {
    return rentBookRepository.existsByBookIdAndEmail(bookId, email);
  }
}
