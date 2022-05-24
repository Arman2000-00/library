package am.tt.library.controller;

import am.tt.library.model.UserType;
import am.tt.library.request.UserRequest;
import am.tt.library.security.CurrentUser;
import am.tt.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/accessDenied")
  public String acsses() {
    return "404";
  }

  @GetMapping("/register")
  public String register() {
    return "index";
  }

  @PostMapping("/sign-up")
  public String AddUser(@ModelAttribute UserRequest userRequest, BindingResult result) {
    if (result.hasErrors()) {
      return "sign-up";
    }
    userService.save(userRequest);
    return "redirect:/";
  }

  @GetMapping("/sign-in")
  public String loginSuccess() {
    return "login";
  }

  @GetMapping("/successLogin")
  public String loginPage(@AuthenticationPrincipal CurrentUser currentUser) {
    if (currentUser.getUser().getUserType().equals(UserType.USER)) {
      return "redirect:/";
    }
    return "redirect:/loggedEmployee";
  }
}
