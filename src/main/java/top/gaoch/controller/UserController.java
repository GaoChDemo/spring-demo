package top.gaoch.controller;

import lombok.Data;
import top.gaoch.annotation.MyAutowired;
import top.gaoch.service.UserService;

@Data
public class UserController {


  private UserService userService;

  @MyAutowired
  private UserService userService2;
}
