package am.tt.library.service.impl;

import am.tt.library.exception.UserNotFoundException;
import am.tt.library.mapper.UserMapper;
import am.tt.library.model.User;
import am.tt.library.repository.UserRepository;
import am.tt.library.request.UserRequest;
import am.tt.library.response.UserResponse;
import am.tt.library.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  @Override
  public void save(UserRequest userRequest) {
    userMapper.toEntity(userRequest);
    userRepository.save(userMapper.toEntity(userRequest));
  }

  @Override
  public void deleteUser(int id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserResponse findById(int id) {
    Optional<User> userById = userRepository.findById(id);
    return userById.map(userMapper::toResponse).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public UserResponse updateUser(int id, UserRequest userRequest) {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    modelMapper.map(userRequest, user);
    return userMapper.toResponse(user);
  }

  @Override
  public List<UserResponse> findAllUsers() {
    return userRepository.findAll()
        .stream()
        .map(userMapper::toResponse)
        .collect(Collectors.toList());
  }
}
