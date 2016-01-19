package com.yao.yz.crm.web.test;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 9:19:30 AM Jan 12, 2016
 * @Version: v1.0
 * 
 */

public class JsonToString {
	
	public static void main(String[] args) {
		// java to json
		User user = new User();
		user.setName("王武");
		user.setAge(5);
//		String jsonText = JSON.toJSONString(user);

		// JsonString to Java
		String s = "{\"age\":5,\"name\":\"王武\"}";
		User user2 = JSON.parseObject(s, User.class);
		System.out.println(user2);
		System.out.println(user2.getName());
		System.out.println(user2.getAge());
		
		// JsonString to List
		String a = "[{\"age\":5,\"name\":\"王武1\"},{\"age\":6,\"name\":\"王武2\"}]";
		ArrayList<User> users = JSON.parseObject(a,new TypeReference<ArrayList<User>>(){});
		
		System.out.println(users.toString());
		User user3 = users.get(0);
		System.out.println(user3.getName());
		User user4 = users.get(1);
		System.out.println(user4.getName());
	}
	
}


class User{
	
	private String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}