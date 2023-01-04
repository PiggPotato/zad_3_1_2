package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UsersController {


    @GetMapping
    public String show(Model model) {
        return "index";
    }


    private String getUsersView(Model model) {
        model.addAttribute("message", "Список пользователей");
        model.addAttribute("url", "/users");
        return "index";
    }

    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
        addUsers();
    }

    private void addUsers() {
        userService.create(new User("User1", "lastName1", "1@mail.ru"));
        userService.create(new User("Юзер2", "ЛастНаме2", "2@mail.ru"));
        userService.create(new User("Юзверь3", "Фамили3", "3@mail.ru"));
        userService.create(new User("User4", "LasNane4", "4@mail.ru"));
    }


    @GetMapping("users/new")
    public String createForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("users")
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:users";
    }


    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "all";
    }


    @GetMapping("users/{id}/edit")
    public String editForm(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("users/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }


    @DeleteMapping("users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}

