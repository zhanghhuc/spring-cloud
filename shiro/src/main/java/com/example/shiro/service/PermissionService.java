package com.example.shiro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.shiro.dao.mapper.PermissionMapper;
import com.example.shiro.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    public Permission selectByPrimaryKey(String id){
        return permissionMapper.selectByPrimaryKey(id);
    }

    public Permission selectByPermission(String permission){
        return permissionMapper.selectByPermission(permission);
    }

    public Map<String,Object> selectAll(){
        Map<String,Object> map = new HashMap<>();
        List<Permission> list = permissionMapper.selectAll();
        map.put("total", list.size());
        map.put("rows", list);
        return map;
    }

    public List<Permission> selectByIds(List<String> pids) {
        return permissionMapper.selectByIds(pids);
    }

}
