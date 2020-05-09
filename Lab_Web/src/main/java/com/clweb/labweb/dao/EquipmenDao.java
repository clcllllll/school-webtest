package com.clweb.labweb.dao;

import com.clweb.labweb.entities.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EquipmenDao {
    private static Map<Integer, Equipment> equipments = null;



    static{
        equipments = new HashMap<Integer, Equipment>();

    }

    private static Integer initId = 1006;



    public Collection<Equipment> getAll(){
        return equipments.values();
    }

    public Equipment get(Integer id){
        return equipments.get(id);
    }

    public void delete(Integer id){
        equipments.remove(id);
    }
}
