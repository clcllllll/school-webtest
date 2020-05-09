package com.clweb.labweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//springboot启动时候扫描@WebServlet注解，并将该类实例化
public class LabwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabwebApplication.class, args);
    }

}
