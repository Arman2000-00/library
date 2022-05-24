package am.tt.library.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRequest {

  private String name;

  private String authorName;

  private long count;

  private double price;

  private int userId;
}
