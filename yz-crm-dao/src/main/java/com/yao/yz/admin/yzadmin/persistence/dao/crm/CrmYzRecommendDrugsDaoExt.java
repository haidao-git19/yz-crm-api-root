package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.dao.YzRecommendDrugsDao;
import com.yao.yz.admin.yzadmin.persistence.model.YzRecommendDrugs;

/**
 * 
 * @Description: 用药建议列表
 * @Autor: wuwenjun
 * @Date: 8:35:39 PM Jan 5, 2016
 * @Version: v1.0
 * 
 */

public interface CrmYzRecommendDrugsDaoExt extends YzRecommendDrugsDao{

	/**
	 * 查询诊单的用药建议
	 * @param inqueryId 诊单id
	 * @return
	 */
	List<YzRecommendDrugs> getByInqueryId(Integer inqueryId);
	
	/**
	 * 查询健康档案的用药建议
	 * @param archiveId
	 * @return
	 */
	List<YzRecommendDrugs> getByArchiveId(Integer archiveId);
	
	/**
	 * 根据健康档案id删除用药建议
	 * @param archiveId
	 */
	void deleteByArchiveId(Integer archiveId);

	/**
	 * 新增用药建议
	 * @param drugs
	 */
	void addDrugList(List<YzRecommendDrugs> drugs);
}
