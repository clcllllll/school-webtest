package com.clweb.labweb.controller;

import com.clweb.labweb.dao.EquipmenDao;
import com.clweb.labweb.entities.Equipment;
import com.clweb.labweb.entities.ScrappedEquipment;
import com.clweb.labweb.mapper.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class EquipmentController {

    @Autowired
    EquipmenDao equipmenDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EquipmentMapper equipmentMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("emps")//可用设备表
    public String eqlist(Model model){
        List<Equipment> equipments = equipmentMapper.findAllEquipment();
        model.addAttribute("equips",equipments);
        return "Equip/ReadEquipment";//thymeleaf会默认拼串，找classpatj://templates/xxx.html下的文件
    }
    @GetMapping("test1")//测试
    public String eqtest(Model model){
        List<Equipment> scrappedEquipments = equipmentMapper.findAllScrappedEquipment();
        model.addAttribute("scequip",scrappedEquipments);
        return "test/bootstrap1";
    }

    @GetMapping("repair")//维修设备表
    public String rplist(Model model){
        List<Equipment> repairequipments = equipmentMapper.findAllRepairEquipment();
        model.addAttribute("repaireq",repairequipments);
        return "Equip/RepairEquipment.html";
    }

    @GetMapping("scrap")//报废设备表
    public String sclist(Model model){
        List<Equipment> scrappedEquipments = equipmentMapper.findAllScrappedEquipment();
        model.addAttribute("scequip",scrappedEquipments);
        return "Equip/ScrappedEquipment.html";
    }

    @GetMapping("lack")//缺少设备表
    public String lalist(Model model){
        List<Equipment> lackEquipments = equipmentMapper.findAllLackEquipment();
        model.addAttribute("laequip",lackEquipments);
        return "Equip/LackEquipment.html";
    }

    @GetMapping("/sendRepair/{id}")//改为正在维修状态
    public String sendToRepair(@PathVariable("id") String id,Model model){
        equipmentMapper.MoveToRepair(id);
        return "redirect:/emps";//重定向到一个地址
    }

    @GetMapping("/backGood/{id}")//维修好改为可以使用状态
    public String backToEquipment(@PathVariable("id") String id,Model model){
        equipmentMapper.BackToEquipment(id);
        return "redirect:/repair";
    }

    @GetMapping("/unUse/{id}")//改为报废状态
    public String deleteToUnuse(@PathVariable("id") String id,Model model){
        equipmentMapper.DeleteToUnuse(id);
        return "redirect:/emps";
    }

    @GetMapping("/applySuccess/{id}")//设备申请成功
    public String applyToEquipment(@PathVariable("id") String id,Model model){
        equipmentMapper.AppleSuccess(id);
        return "redirect:/lack";
    }

    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定，要求了请求参数的名字和javaBean的属性名要一致
    @PostMapping("/addEqu")//添加设备表
    public String addEquipment(Equipment equipment){
        equipmentMapper.insertEquipment(equipment);
        return "redirect:/lack";
    }

    //修改设备信息
    //1、先跳转到修改信息表
    @GetMapping("/editRead/{id}")
    public String readThisEquipment(@PathVariable("id") String id,Model model){
        Equipment equipment = equipmentMapper.getEqById(id);
        model.addAttribute("thiseq",equipment);
        return "Equip/EditEquipment";
    }
    //2、提交表单修改后跳转查看全部设备的表
    @PostMapping("/editEqu")
    public String editThisEquipment(Equipment equipment){
        equipmentMapper.updateEquipment(equipment);
        return "redirect:/emps";
    }

    /**
     * 搜索
     */
    //搜索板块
    @PostMapping("/search")
    public String searchEquipment(@RequestParam("sel1") String sel1,@RequestParam("searchtext") String searchtext,Model model){
        String id,eqname,eqmode,eqsize;
        if(sel1.equals("设备编号")){
            id=searchtext;
            List<Equipment> findEquipments = equipmentMapper.findEquipmentId(id);
            model.addAttribute("find",findEquipments);
        }else if(sel1.equals("设备型号")){
            eqmode=searchtext;
            List<Equipment> findEquipments = equipmentMapper.findEquipmentMode(eqmode);
            model.addAttribute("find",findEquipments);
        }else if(sel1.equals("设备名称")){
            eqname=searchtext;
            List<Equipment> findEquipments = equipmentMapper.findEquipmentName(eqname);
            model.addAttribute("find",findEquipments);
        }else{
            eqsize=searchtext;
            List<Equipment> findEquipments = equipmentMapper.findEquipmentSize(eqsize);
            model.addAttribute("find",findEquipments);
        }
        return "/Manage/Search";
    }
}
