package am.tt.library.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

  private String name;

  private String surname;

  private String email;

  private String password;
}
