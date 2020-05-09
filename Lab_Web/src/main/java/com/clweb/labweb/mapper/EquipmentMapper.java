package com.clweb.labweb.mapper;

import com.clweb.labweb.entities.Equipment;
import com.clweb.labweb.entities.ScrappedEquipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EquipmentMapper {

    public Equipment getEqById(String id);

    public List<Equipment> findAllEquipment();
    public List<Equipment> findAllRepairEquipment();
    public List<Equipment> findAllScrappedEquipment();
    public List<Equipment> findAllLackEquipment();

    public void insertEquipment(Equipment equipment);
    public void updateEquipment(Equipment equipment);
    public void MoveToRepair (String id);
    public void BackToEquipment(String id);
    public void DeleteToUnuse(String id);
    public void AppleSuccess(String id);

    public List<Equipment> findEquipmentId(String id);
    public List<Equipment> findEquipmentMode(String eqmode);
    public List<Equipment> findEquipmentName(String eqname);
    public List<Equipment> findEquipmentSize(String eqsize);



}
