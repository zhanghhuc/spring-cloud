package com.example.shiro;

import com.example.shiro.entity.Permission;
import com.example.shiro.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {

	@Autowired
	public PermissionService permissionService;

	@Test
	public void contextLoads() {
		Map<String, Object> all = permissionService.selectAll();

		System.out.println("total  = "+all.get("total"));
		List<Permission> list= (List<Permission>) all.get("rows");
		for (Permission p : list) {
			System.out.println(p.toString());
		}
	}

}
