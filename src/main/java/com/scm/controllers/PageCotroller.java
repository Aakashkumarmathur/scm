package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;




@Controller
public class PageCotroller {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Page Handler");
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Aakash Coding");
        model.addAttribute("githubRepo", "https://github.com/Aakashkumarmathur");
        return "home";
    }

    //about route
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", true);
        System.out.println("About Page loading");
        return "about";
    }

    //services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Service Page loading");
        return "services";
    }
   
    //contact page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

   @GetMapping("/login")
   public String login(){
    return new String("login");
   }


    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        //default data bhi dal skte hain
        // userForm.setName("Aakash");
        // userForm.setAbout("This is about : Write something about Yourself");

        model.addAttribute("userForm", userForm);
        return "register";
    }
    
    //processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing");
        //we have to fetch the form data
        System.out.println(userForm);

        if(rBindingResult.hasErrors()) {
            return "register"; 
        }

        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://media.licdn.com/dms/image/v2/D5603AQGbG6_VjhbjJg/profile-displayphoto-shrink_800_800/B56ZPatpkyGsAc-/0/1734541221427?e=1743638400&v=beta&t=gElQ_CCrB--4qDcKifiKaai6LAzD0EG3Dg9JeMeAV_Q")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://media.licdn.com/dms/image/v2/D5603AQGbG6_VjhbjJg/profile-displayphoto-shrink_800_800/B56ZPatpkyGsAc-/0/1734541221427?e=1743638400&v=beta&t=gElQ_CCrB--4qDcKifiKaai6LAzD0EG3Dg9JeMeAV_Q");

        User savedUser = userService.saveUser(user);

        System.out.println("User Saved !");

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        return "redirect:/register";
    }
}
