package com.liu.controller;

import com.liu.domain.User;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
  @Autowired
  private UserService userService;
  @RequestMapping("/findAll")
    public List<User> findAll(){
    List<User> lists = userService.findAll();
    //Ures.forEach(user-> System.out.println(Ures));
    return lists;
  }
}
