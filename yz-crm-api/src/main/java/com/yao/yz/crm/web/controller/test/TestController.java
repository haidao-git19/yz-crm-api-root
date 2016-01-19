//package com.yao.yz.crm.web.controller.test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yao.yz.crm.service.util.HttpUtils;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * Desc: 测试控制器
// * Author: wuwenjun
// * Date: 2015/10/24 12:13
// */
//@Controller
//@RequestMapping("/test")
//public class TestController {
//	
//	private static final Logger logger = Logger.getLogger(TestController.class);
//	
//	@ResponseBody
//	@RequestMapping(value = "/httptest",method = {RequestMethod.POST,RequestMethod.GET})
//	public Object httpTest() {
//		logger.info("服务访问成功，准备调用httpclient接口");
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("a", "12");
//		String reString = HttpUtils.httpPostPubMethod("http://localhost:8080/crm/test/httpservice", paramMap);
//		return reString;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/httpservice",method = {RequestMethod.POST,RequestMethod.GET})
//	public Object httpService(HashMap<String, Object> paraHashMap) {
//		logger.info("调用http成功");
//		return "httpservice";
//	}
//
//    @ResponseBody
//    @RequestMapping(value = "/request",method = {RequestMethod.GET})
//    public Object test(){
//        System.out.println("访问接口成功...");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("a","张三");
//        return jsonObject;
//    }
//
//    @ResponseBody
//    @RequestMapping(value ="/jsores",method = {RequestMethod.GET})
//    public Object test1() {
//        A a = new A();
//        a.setName("张三");
//        
//        C c = new C();
//        
//        List<B> map = new ArrayList<TestController.B>(); 
//        B b1 = new B();
//        b1.setKey("key1");
//        b1.setValue("value1");
//        map.add(b1);
//        
//        B b2 = new B();
//        b2.setKey("key2");
//        b2.setValue("value2");
//        map.add(b2);
//        
//        c.setMaps(map);
//        
//        a.setData(c);
//        
//        return a;
//    }
//
//    class A {
//        private String name;
//
//        private Object data;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//		public Object getData() {
//			return data;
//		}
//
//		public void setData(Object data) {
//			this.data = data;
//		}
//        
//    }
//    
//    class C {
//    	
//    	private List<B> maps;
//
//		public List<B> getMaps() {
//			return maps;
//		}
//
//		public void setMaps(List<B> maps) {
//			this.maps = maps;
//		}
//    	
//    }
//    
//    class B {
//    	private String key;
//    	
//    	private String value;
//
//		public String getKey() {
//			return key;
//		}
//
//		public void setKey(String key) {
//			this.key = key;
//		}
//
//		public String getValue() {
//			return value;
//		}
//
//		public void setValue(String value) {
//			this.value = value;
//		}
//    	
//    	
//    }
//
//}
