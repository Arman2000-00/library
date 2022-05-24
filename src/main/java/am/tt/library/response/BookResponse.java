package am.tt.library.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookResponse {

  private int id;

  private String name;

  private String authorName;

  private long count;

  private double price;

  private int userId;

}
