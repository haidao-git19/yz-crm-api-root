package com.yao.yz.crm;

import com.yz.push.model.MessageModel;
import com.yz.push.srv.PushImpl;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 12:30:06 PM Jan 13, 2016
 * @Version: v1.0
 * 
 */

public class PushManager extends AbstractTest{

	@org.junit.Test
	public void Test(){
		PushImpl pushManager = new PushImpl();
		
		MessageModel messageModel = new MessageModel();
		messageModel.setClientId("793342edfda4b18acf8a55c64955aa36");
		messageModel.setContent("1111111");
		messageModel.setTitle("1zhenudan");
		messageModel.setOs("ios");
		
		pushManager.sendMessage(messageModel);
	}
	
}
