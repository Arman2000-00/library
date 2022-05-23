package am.tt.library.response;

import am.tt.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

    private String name;

    private String surname;

    private String email;

    private List<Book> books;

}
