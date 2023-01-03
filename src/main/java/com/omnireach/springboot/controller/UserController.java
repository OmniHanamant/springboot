package com.omnireach.springboot.controller;

import com.omnireach.springboot.customexception.EmployeeNotFoundException;
import com.omnireach.springboot.entity.UserEntity;
import com.omnireach.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    public UserController() {
        System.out.println("Created" + " " + this.getClass().getSimpleName());
    }

    @GetMapping
    public String view() {
        return "index";

    }

    @GetMapping("/user")
    public String showUserList(Model model) {
        List<UserEntity> userEntities = service.validateAndFindAll();
        System.out.println("userEntities" + " " + userEntities);
        model.addAttribute("users", userEntities);
        return "showUser";

    }

    @GetMapping("/user/new")
    public String addNewEmployee(Model model) {
        model.addAttribute("users", new UserEntity());
        model.addAttribute("pageTitle","Add New User");
        return "newEmployee";
    }

    @PostMapping("user/save")
    public String saveEmployee(UserEntity userEntity, RedirectAttributes attributes) {
        System.out.println("Running saveEmployee method in controller");
        UserEntity save = service.validateAndSave(userEntity);
        attributes.addFlashAttribute("message", "Added New Employee");
        System.out.println(save);
        return "showUser";
    }

    @GetMapping("user/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes) {
        try {
            UserEntity user = service.get(id);
            model.addAttribute("users", user);
            model.addAttribute("pageTitle","Edit User(ID:"+id+")");
            return "newEmployee";
        } catch (EmployeeNotFoundException e) {
            attributes.addFlashAttribute("message", "The Employee Details edited");
            return "showUser";
        }
    }
    @GetMapping("user/delete/{id}")
    public String deleteForm(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes) {
        try {
             service.delete(id);
        } catch (EmployeeNotFoundException e) {
            attributes.addFlashAttribute("message", "The Employee Details Delete");
        }
        return "showUser";
    }

}


