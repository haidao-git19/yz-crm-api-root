/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 31, 2015-11:14:46 AM
* @version 1.0
*/
package com.yao.yz.crm.web.v3.controller;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yao.yz.crm.service.util.SysPropertyReader;
import com.yao.yz.crm.web.util.HttpCall;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.drug.DrugBean;
import com.yao.yz.model.v3.biz.drug.DrugReq;
import com.yao.yz.model.v3.biz.drug.PageBean;

/**
 * 公司名: 壹药网
 * 类名称：DrugController
 * 类描述：
 * @author wangyulong
 * @date Dec 31, 2015-11:14:46 AM
 */
@Controller
@RequestMapping("/drug")
public class DrugController extends AbstractController{

	private static final Logger logger = Logger.getLogger(DrugController.class);
			
	@ResponseBody
	@RequestMapping(value = "/searchDrug",method = {RequestMethod.POST,RequestMethod.GET})
	public Object searchDrug(HttpServletRequest request,DrugReq drugReq){
		logger.info("药品信息搜索开始");
		logger.info("传参: "+JSON.toJSONString(drugReq));
		ResModelProxy resModel = new ResModelProxy();
		try {
			// token认证
			if (!authric(DrugController.TOKEN_AHTHRIC, drugReq, resModel, request)) {
				return resModel;
			}
			
			// 参数检查
			if (!drugReq.validate(resModel)){
				return resModel;
			}
			
			String pageSize="5";
			String pageIndex="1";
			if(drugReq.getPage_index()!=null&&drugReq.getPage_index()!=""){
				pageIndex=drugReq.getPage_index();
			}
			if(drugReq.getPage_size()!=null&&drugReq.getPage_size()!=""){
				pageSize=drugReq.getPage_size();
			}
			String keyWord=drugReq.getKeyWord();
			logger.info("keyWord: "+keyWord);
			keyWord=URLEncoder.encode(keyWord, "GBK");
			String url=SysPropertyReader.getValue("DRUG_URL");
			url=url+"&pagesize="+pageSize+"&pageindex="+pageIndex+"&keyword="+keyWord;
			//logger.info("药网url: "+url);
			String result=HttpCall.getResultContent(url);
			logger.info("药网返回: "+result);
			JSONObject jsonObj = JSON.parseObject(result);

			PageBean pb=new PageBean();
			pb.setPageSize(pageSize);
			pb.setPageIndex(pageIndex+"");
			pb.setTotalCount(jsonObj.getString("recordcount"));
			ArrayList<DrugBean> list=new ArrayList<DrugBean>();
			
			if(!jsonObj.getString("recordcount").equals("0")){
				JSONObject j1=(JSONObject) jsonObj.get("item_list_resp_info");
				JSONArray j2=(JSONArray) j1.get("hits");
				DrugBean db;
				for(int i=0;i<j2.size();i++){
					JSONObject j3=(JSONObject) j2.get(i);
					JSONObject j4=(JSONObject) j3.get("product");
					db=new DrugBean();
					db.setId(j4.get("itemId").toString());
					String name=j4.get("name").toString();
					if(name.lastIndexOf(" ")==-1){
						db.setName(name);
					}else{
						db.setName(name.substring(0, name.lastIndexOf(" ")));
					}
					if(j4.get("gift")!=null){
						db.setDesc(j4.get("gift").toString());
					}
					if(j4.get("img")!=null){
						db.setImg(j4.get("img").toString());
					}
					if(j4.get("brand")!=null){
						db.setBrand(j4.get("brand").toString());
					}
					if(j4.get("prescription")!=null){
						db.setPrescription(j4.get("prescription").toString());
					}
					list.add(db);
				}
			}
			
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			resModel.setPrivateData("pageInfo", pb);
			resModel.setPrivateData("drugList", list);
			logger.info("药品信息搜索结束");
		} catch (Exception e) {
			logger.info("药品信息搜索异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, "药品信息搜索异常");
		}
		return resModel;
	}
	
}
