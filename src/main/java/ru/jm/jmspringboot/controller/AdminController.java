package ru.jm.jmspringboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.jm.jmspringboot.model.User;
import ru.jm.jmspringboot.service.UserService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin", produces = "application/json;charset=UTF-8")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping({"/index","/"})
    public String admin(ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.getUser(auth.getName());
        model.addAttribute("users", userService.getAllUsers() );
        model.addAttribute("userinfo",user.toString());
        return "admin/index";
    }

    @GetMapping("/userinfo")
    public String userinfo(ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.getUser(auth.getName());
        List<User> users = new LinkedList<>();
        users.add(user);
        model.addAttribute("userinfo",user);
        model.addAttribute("users", users );
        return "admin/userinfo";
    }

    @GetMapping("/admin/{id}")
//	@ResponseBody
    public User adminEditUser(@PathVariable("id") int id, ModelMap model){
        User user= userService.getUser(id);
        System.out.println(id);
        //user.emptyPassword();
        //model.addAttribute("user",  user );
        //return "updateuser";
        return user;
    }

    @GetMapping("/getUser/{id}")
    @ResponseBody
    public User adminEditUser(@PathVariable("id") int id) {
        User user= userService.getUser(id);
      //  System.out.println("adm:"+user.isRoleAdmin()+" :"+user.isRoleUser());
        user.emptyPassword();
        return user;
    }


    @PostMapping(value = "/update")
    public String updateUser(
            @ModelAttribute("user")
               User user) {

        System.out.println(user.toString());
        userService.updateUser(user.getId(), user);
        return "redirect:/admin/index";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {


        userService.removeUserById(id);
        return "redirect:/admin/index";
    }


    @GetMapping("/admin/createuser")
    public String adminCreateUser(ModelMap model){
        User user= new User();
        model.addAttribute("user",  user );
        return "createuser";
    }

    @PostMapping("/admin/create")
    public String adminCreateUSer( @ModelAttribute("user") User user
                                    ){
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/admin/index";
    }
/*
    @DeleteMapping(value = "/admin/{id}")
    public String deletUser(@PathVariable("id") int id) {

        userService.removeUserById(id);
        return "redirect:/admin";
    }
*/

}
