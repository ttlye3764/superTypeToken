package com.supertypetoken.controller;

import com.supertypetoken.dao.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MyController {

    @RequestMapping("/")
    public List<User> users() {
        return Arrays.asList(new User("A"), new User("B"), new User("C"));
    }


}
