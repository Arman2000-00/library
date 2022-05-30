package am.tt.library.service.impl;

import am.tt.library.exception.BookNotFoundException;
import am.tt.library.mapper.BookMapper;
import am.tt.library.model.Book;
import am.tt.library.model.RentBook;
import am.tt.library.repository.BookRepository;
import am.tt.library.request.BookRequest;
import am.tt.library.response.BookResponse;
import am.tt.library.security.CurrentUser;
import am.tt.library.service.BookService;
import am.tt.library.service.RentBookService;
import am.tt.library.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookMapper bookMapper;

  private final BookRepository bookRepository;

  private final ModelMapper modelMapper;

  private final UserService userService;

  private final RentBookService rentBookService;

  @Override
  public void save(BookRequest bookRequest, CurrentUser currentUser) {
    bookRequest.setUserId(currentUser.getUser().getId());
    bookRepository.save(bookMapper.toEntity(bookRequest));

  }

  @Override
  public void deleteBook(int id, CurrentUser currentUser) {
    Optional<Book> byId = bookRepository.findById(id);
    if (currentUser.getUser().getId() == byId.get().getUserId()) {
      bookRepository.deleteById(id);
    }
  }

  @Override
  public BookResponse findById(int id) {
    Optional<Book> bookById = bookRepository.findById(id);
    return bookById.map(bookMapper::toResponse).orElseThrow(BookNotFoundException::new);
  }

  @Override
  public BookResponse updateBook(int id, BookRequest bookRequest) {
    Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    modelMapper.map(bookRequest, book);
    return bookMapper.toResponse(book);
  }

  @Override
  public List<BookResponse> findAllBooks() {
    return bookRepository.findAll()
        .stream()
        .map(bookMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public boolean findByIdAndUserEmail(int id, String email) {
    Optional<Book> bookById = bookRepository.findById(id);
    if (bookById.isPresent()) {
      Book book = bookById.get();
      if (!rentBookService.existsByBookIdAndEmail(book.getId(), email)) {
        book.setCount(book.getCount() - 1);
        rentBookService.book(book.getId(), email);
      } else {
        book.setCount(book.getCount() + 1);
        rentBookService.returnBook(book.getId());
      }
      return true;
    }
    return false;
  }
}



