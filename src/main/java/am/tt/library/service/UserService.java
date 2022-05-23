package am.tt.library.service;

import am.tt.library.request.UserRequest;
import am.tt.library.response.UserResponse;

import java.util.List;

public interface UserService {

    void save(UserRequest userRequest);

    void deleteUser(int id);

    UserResponse findById(int id);

    UserResponse updateUser(int id, UserRequest userRequest);

    List<UserResponse> findAllUsers();

    UserResponse findByEmail(String email);
}
