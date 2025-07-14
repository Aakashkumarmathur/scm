package com.scm.helpers;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){

        
        if(authentication instanceof OAuth2AuthenticationToken){

            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            var clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = "";

            if(clientId.equalsIgnoreCase("google")){
                // google se login kiya hai
                System.out.println("getting email from google");
                username=oauth2User.getAttribute("email").toString();
            }
            
            else if(clientId.equalsIgnoreCase("github")){
                //github se login kiya hai
                System.out.println("getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
            return username;
        }
        //agar email and password se login kiya hai toh hum email kaiise nikalenge
        else{
            System.out.println("getting email from local database");
            return authentication.getName();
        }

        

       
    }

    public static String getLinkForEmailVerification(String emailToken){

        String link = "http://localhost:8085/auth/verify-email?token=" + emailToken;

        return link;
    }
}
