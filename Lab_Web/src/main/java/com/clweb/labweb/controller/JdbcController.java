package com.clweb.labweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/readEquip")
    public Object map(){
        String username="lky";
        List<Map<String, Object>> equipList = jdbcTemplate.queryForList("select * from user where username=?", username);
        return equipList.get(0).get("password");
    }

}
