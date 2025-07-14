package com.scm.controllers;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private ContactService contactService;
    
        
    
        //user dashboard page
        @RequestMapping(value =  "/dashboard")
        public String userDashboard(Model model, Authentication authentication) {
            System.out.println("User dashboard");

            String username = Helper.getEmailOfLoggedInUser(authentication);

            User user = userService.getUserByEmail(username);

            if(user == null) return "redirect:/login?error";

            model.addAttribute("username", user);

            List<Contact> recentContacts = contactService.getRecentContactsForUser(user.getUserId());
        model.addAttribute("recentContacts", recentContacts);
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {
        return "user/profile";
    }
    

    //user add contacts page

    //user view contacts page

    //user edit contacts 

    //user delete contacts

}
