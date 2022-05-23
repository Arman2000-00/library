package am.tt.library.mapper;

import am.tt.library.mapper.config.BaseMapper;
import am.tt.library.model.User;
import am.tt.library.request.UserRequest;
import am.tt.library.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<User, UserRequest, UserResponse> {

  private final ModelMapper modelMapper;

  @Override
  public User toEntity(UserRequest userRequest) {
    return modelMapper.map(userRequest, User.class);
  }

  @Override
  public UserResponse toResponse(User user) {
    return modelMapper.map(user, UserResponse.class);
  }
}
