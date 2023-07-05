package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "/users/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        String rsl;
        try {
            userService.create(user);
            rsl = "redirect:/posts/index";
        } catch (Exception exception) {
            model.addAttribute("message", "Пользователь с login " + user.getLogin() + " уже существует");
            rsl = "errors/404";
        }
        return rsl;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "users/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        var userOptional = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Логин или пароль введены неверно");
            return "users/login";
        }
        var session = request.getSession();
        session.setAttribute("user", userOptional.get());
        return  "redirect:/posts/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }
}
