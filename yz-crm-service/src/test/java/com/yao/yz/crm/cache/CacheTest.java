package com.yao.yz.crm.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.crm.AbstractTest;
import com.yao.yz.util.cache.redis.CacheFacade;

public class CacheTest extends AbstractTest{

	@Autowired
	private CacheFacade cacheFacade;
	
	@Test
	public void testUserCache() {
		cacheFacade.deleteByFiledsNKey("yz:user", "yzfiled:uid_" + "992");
	}
}
