package com.yao.yz.crm.web.v3.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yao.yz.crm.service.interf.archive.ArchiveService;
import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveListReq;
//import com.yao.yz.crm.service.interf.archive.query.ArchiveListService;
/*
import com.yao.yz.crm.service.interf.archive.query.ArchiveQueryAdd;
import com.yao.yz.crm.service.interf.archive.query.ArchiveQueryBack;
import com.yao.yz.crm.service.interf.archive.query.ArchiveQueryEdit;
import com.yao.yz.crm.service.interf.archive.query.ArchiveQueryInquery;
import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveAdd;
import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveBack;
import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveEdit;
import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveInquery;
import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveNew;
*/
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.vo.BasicResVo;
/*
import com.yao.yz.crm.service.vo.req.archive.ArchiveInfoReq;
import com.yao.yz.crm.service.vo.req.archive.ArchiveSaveReq;
*/
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveListReq;
//import com.yao.yz.crm.web.controller.BaseController;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.ResModel;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.archive.ArchiveListReq;
import com.yao.yz.model.v3.biz.archive.ArchiveQueryReq;
import com.yao.yz.model.v3.biz.archive.ArchiveUpdateReq;

/**
 *	描述：健康档案控制器
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 3:33:36 PM
 *	@Versin 1.0
 *	modeified by wuwenjun 2015.12.09 移除detail、save接口
 */
@Controller
@RequestMapping("/archive")
public class ArchiveController extends AbstractController{

	private static final Logger logger = Logger.getLogger(ArchiveController.class);

	@Autowired
	private ArchiveService archiveService;
	
	@ResponseBody
	@RequestMapping(value = "/info",method = {RequestMethod.POST})
	public ResModel getArchiveInfo(HttpServletRequest request, ArchiveQueryReq archiveInfoReq,ResModelProxy resModelProxy) {
		
		logger.info("【健康档案详情查询接口】服务端接收到健康档案详情查询请求..." + archiveInfoReq.toString());
		
		try {
			// token验证
			if (!authric(TOKEN_AHTHRIC, archiveInfoReq, resModelProxy, request)) {
				return resModelProxy;
			}
			
			// 返回健康档案详情
			archiveService.getAchiveDetail(archiveInfoReq, resModelProxy);
		} catch (Exception e) {
			logger.error("【健康档案详情查询接口】服务端查询健康详情出现异常",e);
			resModelProxy.setPublicData(ModelConstants.RET_CODE_ERROR, 10004);
		}
		logger.info("【健康档案详情查询接口】服务端处理健康档案详情查询完毕" + resModelProxy.toString());
		return resModelProxy;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update",method = {RequestMethod.POST})
	public ResModel updateArchive(HttpServletRequest request,ArchiveUpdateReq archiveUpdateReq,ResModelProxy resModelProxy) {
		
		logger.info("【健康档案更新接口】服务端接收到健康档案更新请求..." + archiveUpdateReq.toString());
		
		try {
			// token 验证
			if (!authric(TOKEN_AHTHRIC, archiveUpdateReq, resModelProxy, request)) {
				return resModelProxy;
			}
			
			// 更新健康档案
			archiveService.updateArchive(archiveUpdateReq, resModelProxy);
		} catch (Exception e) {
			logger.error("【健康档案更新接口】服务端处理健康档案更新请求异常", e);
			resModelProxy.setPublicData(ModelConstants.RET_CODE_ERROR, 10004);
		}
		logger.info("【健康档案更新接口】服务端处理健康档案更新请求完毕" + resModelProxy.toString());
		return resModelProxy;
	}
	
	
	/**
	 * 查询用户健康档案列表
	 * @param archiveListReq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list",method = {RequestMethod.POST})
	public ResModel getArchiveList(ArchiveListReq archiveListReq,HttpServletRequest request,ResModelProxy resModelProxy) {
		logger.info("【健康档案列表查询请求】服务端接收到健康列表查询请求,准备处理...");
		try {
			// token验证
			if (!authric(TOKEN_AHTHRIC, archiveListReq, resModelProxy, request)) {
				return resModelProxy;
			}
			
			// 用户健康档案列表
			archiveService.getArchiveList(archiveListReq, resModelProxy);
		} catch (Exception e) {
			logger.error("【健康档案列表查询请求】接口异常", e);
			resModelProxy.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
		}
		
		logger.info("【健康档案列表查询请求】服务端处理健康档案列表查询请求完毕...." + resModelProxy.toString());
		
		return resModelProxy;
	}
//
// * 
// * v2.0版本移除
// * 2015.12.09
// * 伍文君
// * 
// * 
//	@Autowired
//	private ArchiveQueryAdd archiveQueryAdd;
//	
//	@Autowired
//	private ArchiveQueryBack archiveQueryBack;
//	
//	@Autowired
//	private ArchiveQueryEdit archiveQueryEdit;
//	
//	@Autowired
//	private ArchiveQueryInquery archiveQueryInquery;
//	
//	@Autowired
//	private ArchiveSaveInquery archiveSaveInquery;
//	
//	@Autowired
//	private ArchiveSaveAdd archiveSaveAdd;
//	
//	@Autowired
//	private ArchiveSaveBack archiveSaveBack;
//	
//	@Autowired
//	private ArchiveSaveEdit archiveSaveEdit;
//	
//	@Autowired
//	private ArchiveSaveNew archiveSaveNew;
//	
//	
//	@Autowired
//	private ArchiveListService archiveListQueryImpl;
//	
	
/*	
 * 
 * v2.0版本移除
 * 2015.12.09
 * 伍文君
 * 
 * 
	*//**
	 * 查询健康档案详情
	 * @param archiveInfoReq
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/detail",method = {RequestMethod.POST})
	public Object getArchiveInfo(ArchiveInfoReq archiveInfoReq,HttpServletRequest request){
		logger.info("【健康档案信息查询接口】服务端接收到健康档案详情查询请求，准备处理...");
		try {
			// token验证
			BasicResVo basicResVo = beforeExecute(archiveInfoReq, request);
			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
				return basicResVo;
			}
			
			// 判断请求类型执行不同的请求处理
			if (ServiceContant.ARCHIVE_DETAIL_A.equals(archiveInfoReq.getReq_type())) {
				return archiveQueryAdd.getArchiveInfo(archiveInfoReq);
			} else if (ServiceContant.ARCHIVE_DETAIL_E.equals(archiveInfoReq.getReq_type())) {
				return archiveQueryEdit.getArchiveInfo(archiveInfoReq);
			} else if (ServiceContant.ARCHIVE_DETAIL_R.equals(archiveInfoReq.getReq_type())) {
				return archiveQueryBack.getArchiveInfo(archiveInfoReq);
			} else if (ServiceContant.ARCHIVE_DETAIL_H.equals(archiveInfoReq.getReq_type())){
				return archiveQueryInquery.getArchiveInfo(archiveInfoReq);
			} else {
				return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "请求类型非法", null);
			}
		} catch (Exception e) {
			logger.error("【健康档案信息查询接口】接口异常", e);
			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
		}
	}
	
/*	
 * 
 * v2.0版本移除
 * 2015.12.09
 * 伍文君
 * 
 * 
 * 
	*//**
	 * 保存新增健康档案
	 * @param archiveAddSaveReq
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/save",method = {RequestMethod.POST})
	public Object saveArchiveAdd(ArchiveSaveReq archiveSaveReq,HttpServletRequest request){
		logger.info("【健康档案保存接口】服务端接收到健康档案保存请求，准备处理...");
		try {
			// token验证
			BasicResVo basicResVo = beforeExecute(archiveSaveReq, request);
			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
				return basicResVo;
			}
			
			// 判断请求类型执行不同的请求
			if (ServiceContant.ARCHIVE_SAVE_A.equals(archiveSaveReq.getReq_type())) {
				logger.info("【新建问诊保存】调用新建健康档案保存...");
				return archiveSaveAdd.save(archiveSaveReq);
			} else if (ServiceContant.ARCHIVE_SAVE_E.equals(archiveSaveReq.getReq_type())) {
				logger.info("【编辑问诊保存】调用编辑健康档案保存...");
				return archiveSaveEdit.save(archiveSaveReq);
			} else if (ServiceContant.ARCHIVE_SAVE_H.equals(archiveSaveReq.getReq_type())) {
				logger.info("【来电清单处理保存】调用来电清单处理保存...");
				return archiveSaveInquery.save(archiveSaveReq);
			} else if (ServiceContant.ARCHIVE_SAVE_N.equals(archiveSaveReq.getReq_type())) {
				logger.info("【全新问诊保存】调用全新问诊保存...");
				return archiveSaveNew.save(archiveSaveReq);
			} else if (ServiceContant.ARCHIVE_SAVE_R.equals(archiveSaveReq.getReq_type())) {
				logger.info("【回访问诊保存】调用回访问诊保存...");
				return archiveSaveBack.save(archiveSaveReq);
			} else {
				return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "请求类型非法", null);
			}
		} catch (Exception e) {
			logger.error("【健康档案保存接口】接口异常", e);
			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
		}
	}
	
*/
}
