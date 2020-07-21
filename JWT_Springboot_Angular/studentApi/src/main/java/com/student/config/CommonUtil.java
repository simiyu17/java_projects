package com.student.config;

import com.student.dao.user.UserDaoImpl;
import com.student.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class CommonUtil {

    @Autowired
    private UserDaoImpl userdao;

    public UserInfo getCurrentLoggedUser() {
        UserInfo u = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
 
        Object user = authentication.getPrincipal();


        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println("USER currentUserName============================>"+currentUserName);

            u = userdao.findByUsername(currentUserName);
        }
        return u;
    }

    public UserInfo getCurrentLoggedUser2() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("USER2 principal============================>"+principal);

        String username = null;
        UserInfo u = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            System.out.println("USER2 username============================>"+username);

        } else {
            username = principal.toString();
            System.out.println("USER23 username============================>"+username);

        }

        if (username != null) {
            u = userdao.findByUsername(username);
        }
        return u;
    }

}