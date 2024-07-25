package com.bgcg.queryexecute.controller;


import com.bgcg.queryexecute.entity.Users;
import com.bgcg.queryexecute.entity.UsersType;
import com.bgcg.queryexecute.exception.UserAlreadyExistsException;
import com.bgcg.queryexecute.services.UsersService;
import com.bgcg.queryexecute.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAll", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model){
        try {
            usersService.addNew(users);
            return "dashboard";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAll", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
    }


}
