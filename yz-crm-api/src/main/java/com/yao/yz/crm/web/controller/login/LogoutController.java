/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 2, 2015-1:39:32 PM
* @version 1.0
*/
package com.yao.yz.crm.web.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yao.yz.crm.web.controller.BaseController;
import com.yao.yz.util.cache.merberCache.MemcachedUtils;

/**
 * 公司名: 壹药网
 * 类名称：LogoutController
 * 类描述：
 * @author wangyulong
 * @date Nov 2, 2015-1:39:32 PM
 */
@Controller
public class LogoutController extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String  logout(HttpServletRequest request){
		int ret=0;
		String msg=null;
		String url=null;
		if(MemcachedUtils.get("MAIN_URL")!=null){
			ret=1;
			msg="ok";
			url=(String)MemcachedUtils.get("MAIN_URL");
			
			MemcachedUtils.delete(request.getParameter("token"));
		}else{
			ret=0;
			msg="登出url在缓存中不存在!";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ret", ret);
		jsonObject.put("msg", msg);
        jsonObject.put("url", url);
        return jsonObject.toString();
	}
}
