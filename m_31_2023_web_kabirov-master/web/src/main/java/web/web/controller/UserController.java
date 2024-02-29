package web.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserService;
import web.service.converter.UserRegFormToDto;
import web.service.dto.UserDto;
import web.web.form.RegForm;

import java.util.Optional;

@Controller
@RequestMapping("web")
@AllArgsConstructor
public class UserController extends AbstractController {
    private final UserService userService;
    private final UserRegFormToDto converter;

    @GetMapping(path = "/index")
    String indexGet(Model model) {
        Optional<UserDto> user = getUser();
        if (user.isEmpty()) {
            return "redirect:/web/login";
        }

        model.addAttribute("name", user.get().getFirstName());
        model.addAttribute("secondName", user.get().getSecondName());
        return "index";

    }

    @GetMapping(path = "/register")
    String registerGet(Model model) {
        model.addAttribute("reg_form", new RegForm());
        return "reg";
    }

    @PostMapping(path = "/register")
    String registerPost(@ModelAttribute("reg_form") @Valid RegForm regForm,
                        BindingResult result,
                        Model model) {
        if (!result.hasErrors()) {
            if (!userService.register(converter.convert(regForm), regForm.getPassword())) {
                result.addError(new FieldError("reg_form", "email", "This email already exists"));
                return "reg";
            }
        }
        UserDto userDto = userService.login(regForm.getEmail(), regForm.getPassword());

        model.addAttribute("name", regForm.getName());
        model.addAttribute("secondName", regForm.getSecondName());
        return "redirect:/web/index";
    }

    @GetMapping(path = "/login")
    String loginGet() {
        return "login";
    }

   /* @PostMapping(path = "/login")
    String loginPost(@ModelAttribute("login_form") @Valid LoginForm loginForm,
                     BindingResult result,
                     Model model, HttpServletRequest request) {
        UserDto login = null;
        if (!result.hasErrors()) {
            try {
                login = userService.login(loginForm.getEmail(), loginForm.getPassword());

            } catch (CustomException e) {
                result.addError(new FieldError("login_form", "password", e.getMessage()));
                return "login";
            }

        }


        model.addAttribute("name", login.getFirstName());
        model.addAttribute("secondName", login.getSecondName());
        request.getSession().setAttribute(USER_ID_ATTR, login.getId());

        return "redirect:/web/index";
    }*/

    @GetMapping(path = "/logout")
    String logoutGet(HttpServletRequest request) {
        request.getSession().setAttribute(USER_ID_ATTR, null);
        return "redirect:/web/login";
    }

    private Optional<UserDto> getUser() {
        return getUserId().flatMap(userService::getUserById);
    }

/*    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("12345678"));
    }*/
}
