package com.yao.yz.crm.service.impl.archive;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzRecommendDrugsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzRecommendDrugs;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzArchiveExt;
import com.yao.yz.biz.diagnosis.srv.handler.inquery.InqueryAppPushHandler;

/**
 * 
 * @Description: 健康档案服务组件
 * @Autor: wuwenjun
 * @Date: 6:45:35 PM Jan 8, 2016
 * @Version: v1.0
 * 
 */
@Service
public class ArchiveServiceCom {

	private static final Logger logger = Logger.getLogger(ArchiveServiceCom.class);
	
	@Autowired
	private CrmYzArchiveDaoExt yzArchiveDao;
	
	@Autowired
	private CrmYzRecommendDrugsDaoExt yzRecommendDrugsDaoExt;
	
	@Autowired
	private InqueryAppPushHandler inqueryAppPushHandler;
	
	/**
	 * 查询健康档案
	 * @param archiveId 档案id
	 * @return
	 */
	public YzArchive getArchive(Integer archiveId) {
		return yzArchiveDao.getYzArchiveByKey(archiveId);
	}
	
	/**
	 * 查询健康档案的用药建议
	 * @param archiveId
	 * @return
	 */
	public List<YzRecommendDrugs> getDrugs(Integer archiveId) {
		return yzRecommendDrugsDaoExt.getByArchiveId(archiveId);
	}
	
	/**
	 * 更新健康档案并重新记录用药建议
	 */
	@Transactional
	public void updateArchive(YzArchive yzArchive,List<YzRecommendDrugs> drugs){
		// 更新健康档案
		yzArchiveDao.update(yzArchive);
		
		// 删除健康档案原有的用药建议
		yzRecommendDrugsDaoExt.deleteByArchiveId(yzArchive.getId());
		
		// 新增用药建议
		if (drugs.size() > 0) {
			yzRecommendDrugsDaoExt.addDrugList(drugs);
		}
	}
	
	/**
	 * 根据科室和用户编号查询健康档案
	 * @param uid 
	 * @param dep
	 * @return
	 */
	public List<CrmYzArchiveExt> getByUidAndDep(Integer uid,Integer dep) {
		if (0 == dep) {// 查询全部科室的健康档案列表
			return yzArchiveDao.archiveList(uid);
		} else {// 查询指定科室的健康档案列表
			return yzArchiveDao.selectArchiveExts(uid, dep);
		}
	}
	
	/**
	 * 推送app消息
	 */
	public void pushAppMsg(Integer uid) {
		try {
			inqueryAppPushHandler.pushApp(uid);
			logger.info(">>> 调用个推推送app消息成功...");
		} catch (Exception e) {
			logger.warn(">>> 调用个推同送app消息失败",e);
		}
	}
}
