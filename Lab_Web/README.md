# 实验室设备管理网站（GDPU·朱定洲）

## 前期工作

### 1、导入依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--引入thymeleaf-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <!--引入jquery-->
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>jquery</artifactId>
        <version>3.3.1</version>
    </dependency>
    <!--引入bootstrap-->
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>bootstrap</artifactId>
        <version>4.4.1-1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

### 2、JDBC+MySQL

导入依赖

```xml
<!--jdbc-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

yml的配置

```yml
spring:
  datasource:
    username: root
    password: clcheese
    url: jdbc:mysql://localhost:3306/labwebdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
```

控制器的简单使用

```java
@Controller
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/readEquip")
    public Map<String, Object> map(){
        List<Map<String, java.lang.Object>> equipList = jdbcTemplate.queryForList("select * from equipment");
        return equipList.get(0);

    }
```

### 3、表格设计

类别string、设备名string、编号string（主键）、型号string、规格string、单价double、数量int、购置日期date、生产厂家string、保质期date和经办人string

status设置

0——可用

1——维修

2——报废

3——采购

## 页面设计

#### 1、使用bootstrap、导入thymeleafm

```htmlh
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

```html
<link  th:href="@{/webjars/bootstrap/4.4.1-1/css/bootstrap.css}" rel="stylesheet">
<script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.4.1-1/js/bootstrap.min.js"></script>
<script src="/webjars/bootstrap/4.4.1-1/js/bootstrap.bundle.js"></script>
```

#### 2、body设置

```css
<style type="text/css">
    body { padding-top: 50px; }
</style>
```

目的是为了放置固定导航条之后覆盖其他东西

#### 3、导航条固定

```html
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
<nav class="navbar  bg-dark navbar-dark position-fixed" >
```

#### 4、导航条内置下拉菜单

```html
<div class="dropdown-menu">
    <a class="dropdown-item" href="#">Link 1</a>
    <a class="dropdown-item" href="#">Link 2</a>
    <a class="dropdown-item" href="#">Link 3</a>
</div>
```

#### 5、几个常用的按钮

```html
btn-secondary//灰色 btn-primary//蓝色 btn-success//绿色 btn-danger//红色
```



## 后台设计

#### 1、建一个总过滤器

```java
@Controller
@Configuration
@EnableAutoConfiguration
public class ClLabMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/main.html").setViewName("Main");
    }
}
```

方便我们既用到了thyemleaf，也可以正确跳转到对应的html

#### 2、登录设计

登录的表单是post方法提交，建登录控制器类

核心代码：

```java
@PostMapping("/user/login")
public String login(@RequestParam("username") String username,
                    @RequestParam("password") String password,
                    Map<String,Object> map,
                    HttpSession session){
    if(password.equals("123456") ){
        session.setAttribute("loginUser",username);
        return "redirect:/main.html";
    }
    else{
        map.put("msg","用户名密码错误");
        return "/login";
    }
}
```

return跳转的要自己过滤器设置一下，不设置就是默认在templates文件夹下找html文件

#### 3、JDBC的数据库调用

新建仪器的控制类(controller)文件

```java
@Autowired
JdbcTemplate jdbcTemplate;

//查询所有设备，返回列表页面
@GetMapping("emps")//点击“所有设备”
public String list(Model model){
    List<Map<String, Object>> equipments = jdbcTemplate.queryForList("select * from equipment");
    //放在请求域中
    model.addAttribute("equips",equipments);
    //thymeleaf会默认拼串，找classpatj://templates/xxx.html下的文件
    return "Equip/ReadEquipment";
}
```

然后在Equipment.html用thyemleaf方法获得传递到前台的数据，并且以表格的形式显示出来

```html
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>编号</th> <th>设备名称</th> <th>设备单价</th> <th>维修次数</th> <th>没有故障？</th> <th>有无报废?</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="equipments:${equips}">
                    <td th:text="${equipments.id}"></td>
                    <td th:text="${equipments.eqname}"></td>
                    <td th:text="${equipments.eqprice}"></td>
                    <td th:text="${equipments.repaircount}"></td>
                    <td th:text="${equipments.good}"></td>
                    <td th:text="${equipments.live}"></td>
                </tr>
            </tbody>
        </table>
    </div>

</main>
```

#### 4、mybatis配置版使用

4.1、新建mapper

```java
package com.clweb.labweb.mapper;

import com.clweb.labweb.entities.Equipment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EquipmentMapper {

    public Equipment getEqById(int id);

    public void insertEquipment(Equipment equipment);
}
```

4.2、新建mybatis的设置文件mybatis_config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

</configuration>
```

4.3、新建和mapper类对应的设置类

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.clweb.labweb.mapper.EquipmentMapper">
    <select id="getEqById" resultType="com.clweb.labweb.entities.Equipment" >
        select * from equipment where id=#{id}
    </select>
    <insert id="insertEquipment">
        insert into equipment(eqname,eqprice,repaircount,good,live) values (#{eqname},#{eqprice},#{repaircount},#{good},#{live})
    </insert>
</mapper>
```

4.4、去application.yml配置好路径和数据库基本信息

```yml
spring:
  datasource:
    username: root
    password: clcheese
    url: jdbc:mysql://localhost:3306/labwebdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis:
  config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml
```

4.5、测试

在controller下写这个方法

```java
@GetMapping("/equ/{id}")
public Equipment getEq (@PathVariable("id") int id){
    return equipmentMapper.getEqById(id);
}
```

@PathVariable("id")//获取路径变量

![image-20200401131209632](C:\Users\17943\AppData\Roaming\Typora\typora-user-images\image-20200401131209632.png)

#### 5、mybatis访问实验器材表

5.1、mapper写上方法

```java
public List<Equipment> findAllEquipment();
```

5.2、对应的xml配置好sql语句

```xml
<select id="findAllEquipment" resultType="com.clweb.labweb.entities.Equipment">
    select * from equipment
</select>
```

5.3、controller拦截查找器材的命令并调用方法

```java
@GetMapping("emps")
public String eqlist(Model model){
    List<Equipment> equipments = equipmentMapper.findAllEquipment();
    model.addAttribute("equips",equipments);
    return "Equip/ReadEquipment";
}
```

#### 6、thymeleaf引用当前时间

```elm
th:text="${#dates.format(new java.util.Date().getTime(), 'yyyy-MM-dd ')}"
```

#### 7、通过修改status的值使得设备更换可用状态

设置跳转语句

```html
<a class="btn btn-sm btn-dark" th:href="@{/sendRepair/}+${equipments.id}">送去维修</a>
```

设置mapper

```java
public void BackToEquipment(String id);
```

mapper对应的xml添加SQL语句

```xml
<update id="MoveToRepair" >
    update equipment set status=1 where id=#{id}
</update>
```

controller写函数

```java
@GetMapping("/sendRepair/{id}")
public String sendToRepair(@PathVariable("id") String id,Model model){
    equipmentMapper.MoveToRepair(id);
    List<Equipment> equipments = equipmentMapper.findAllEquipment();
    model.addAttribute("equips",equipments);
    return "Equip/ReadEquipment";
}
```

#### 8、获得表单提交

HTML页面post方式提交，表格的name要和Equipment类的变量名字一一对应

```html
<form class="form-signin " th:action="@{/addEqu}" th:method="post">
```

mapper里写下添加设备的方法

```java
public void insertEquipment(Equipment equipment);
```

去对应的xml文件写SQL语句

```xml
<insert id="insertEquipment">
    insert into equipment(id,eqmode,eqname,eqsize,eqprice,eqcount,buydate,manufacturer,manager,status)
    values (#{id},#{eqmode},#{eqname},#{eqsize},#{eqprice},#{eqcount},#{CURDATE()},#{manufacturer},#{manager},3)
</insert>
```

```sql
CURDATE()//在mysql中获取当前日期
```

去controller实现post请求

```java
//SpringMVC自动将请求参数和入参对象的属性进行一一绑定，要求了请求参数的名字和javaBean的属性名要一致
@PostMapping("/addEqu")//添加设备表
public String addEquipment(Equipment equipment){
    equipmentMapper.insertEquipment(equipment);
    return "redirect:/lack";
}
```

9、设备信息的修改

首先把要修改的信息传到后台

```html
<a class="btn btn-sm btn-primary" th:href="@{/editRead/}+${equipments.id}">修改信息</a>
```

后台接受到id后跳转到修改信息表

```java
//1、先跳转到修改信息表
@GetMapping("/editRead/{id}")
public String readThisEquipment(@PathVariable("id") String id,Model model){
    Equipment equipment = equipmentMapper.getEqById(id);
    model.addAttribute("thiseq",equipment);
    return "Equip/EditEquipment";
}
```

```java
public Equipment getEqById(String id);
```

```xml
<select id="getEqById" resultType="com.clweb.labweb.entities.Equipment" >
    select * from equipment where id=#{id}
</select>
```

然后在修改信息表修改

![image-20200403163928688](C:\Users\17943\AppData\Roaming\Typora\typora-user-images\image-20200403163928688.png)

固定死修改编号不能修改，提交表单同上面第七点思路一样

9、把输入的日期转换成Date存入数据库

```java
@InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
```

