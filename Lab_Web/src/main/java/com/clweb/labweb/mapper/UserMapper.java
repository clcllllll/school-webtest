package com.clweb.labweb.mapper;

import com.clweb.labweb.entities.Equipment;
import com.clweb.labweb.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Mapper//指定这是一个操作数据库的mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    public User getUserUsername(String username);

    @Select("select password from user where username=#{username} ")
    public String testLogin(String username);

    @Select("select * from equipment where id =#{id}")
    public Equipment getEpById(int id);

    @Delete("delete from equipment where id=#{id}")
    public int deleteEqById(int id);

//    @Options(useGeneratedKeys = true ,keyProperty = "id")//添加时自动设置主键的id
//    @Insert("insert into equipment(equname) values(#{equname})")
//    public int insertEq(Equipment equipment);

//    @Update("update equipment set equname=#{equname} where id=#{id}")
//    public int updateEq(Equipment equipment);

}
