package com.tang.controller;

import com.tang.service.OrderService;
import com.tang.service.UserService;

public class UserController {

    private UserService userService;

    private OrderService orderService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    //public UserController(UserService userService) {
    //    this.userService = userService;
    //}

    public void insert() {
        userService.insert();
    }
}
