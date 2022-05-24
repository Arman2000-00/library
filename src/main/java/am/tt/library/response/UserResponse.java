package am.tt.library.response;

import am.tt.library.model.Book;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

  private int id;

  private String name;

  private String surname;

  private String email;

  private List<Book> books;

}
