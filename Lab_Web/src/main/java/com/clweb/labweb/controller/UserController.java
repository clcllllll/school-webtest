package com.clweb.labweb.controller;

import com.clweb.labweb.entities.Equipment;
import com.clweb.labweb.entities.User;
import com.clweb.labweb.mapper.EquipmentMapper;
import com.clweb.labweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    EquipmentMapper equipmentMapper;

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username){
        return userMapper.getUserUsername(username);
    }

}
