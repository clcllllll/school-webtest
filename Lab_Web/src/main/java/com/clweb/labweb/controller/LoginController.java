package com.clweb.labweb.controller;

import com.clweb.labweb.entities.User;
import com.clweb.labweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
//        List<Map<String, Object>> equipList = jdbcTemplate.queryForList("select * from user where username=?", username);
//        if(equipList.get(0).get("password").equals(password)){
//            return "redirect:/main.html";
//        }
//        else{
//            map.put("msg","用户名与密码不匹配！");
//            return "/login";
//        }
        session.setAttribute("loginname",username);
        String testpassword = userMapper.testLogin(username);
        if (testpassword.equals(password)){
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名与密码不匹配！");
            return "/login";
        }
    }

    @GetMapping("/register")
    public String goRegister(){
        return "/User/Register";
    }


}
