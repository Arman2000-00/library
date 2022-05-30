package am.tt.library.repository;

import am.tt.library.model.RentBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentBookRepository extends JpaRepository<RentBook, Integer> {

  boolean existsByBookIdAndEmail(int bookId, String email);

  void deleteByBookId(int id);
}
