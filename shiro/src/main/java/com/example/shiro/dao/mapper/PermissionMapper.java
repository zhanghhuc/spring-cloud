package com.example.shiro.dao.mapper;

import com.example.shiro.entity.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    Permission selectByPrimaryKey(String id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

	List<Permission> selectByIds(List<String> pids);

	Permission selectByPermission(String permission);
}