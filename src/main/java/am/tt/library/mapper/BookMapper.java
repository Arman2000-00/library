package am.tt.library.mapper;

import am.tt.library.mapper.config.BaseMapper;
import am.tt.library.model.Book;
import am.tt.library.request.BookRequest;
import am.tt.library.response.BookResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper implements BaseMapper<Book, BookRequest, BookResponse> {

  private final ModelMapper modelMapper;

  @Override
  public Book toEntity(BookRequest bookRequest) {
    return modelMapper.map(bookRequest, Book.class);
  }

  @Override
  public BookResponse toResponse(Book book) {
    return modelMapper.map(book, BookResponse.class);
  }
}
