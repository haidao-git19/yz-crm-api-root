//package com.yao.yz.crm.service.vo.req.archive;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.vo.BasicReqVo;
//import com.yao.yz.crm.service.vo.BasicResVo;
///**
// *	描述：健康档案详情请求
// *	@Author wuwenjun
// *	@Date Oct 28, 2015 12:43:47 PM
// *	@Versin 1.0
// */
//public class ArchiveInfoReq extends BasicReqVo{
//
//	private static final long serialVersionUID = 2733319005851691555L;
//	
//	private static final Logger logger = Logger.getLogger(ArchiveInfoReq.class);
//	
//	/**
//	 * 健康档案编号
//	 */
//	private String archive_id;
//	
//	/**
//	 * 详情请求类型
//	 */
//	private String req_type;
//	
//	/**
//	 * 来电清单编号
//	 */
//	private String inquery_id;
//
//	public String getArchive_id() {
//		return archive_id;
//	}
//
//	public void setArchive_id(String archive_id) {
//		this.archive_id = archive_id;
//	}
//
//	public String getReq_type() {
//		return req_type;
//	}
//
//	public void setReq_type(String req_type) {
//		this.req_type = req_type;
//	}
//
//	public String getInquery_id() {
//		return inquery_id;
//	}
//
//	public void setInquery_id(String inquery_id) {
//		this.inquery_id = inquery_id;
//	}
//
//	/**
//	 * 非空校验
//	 */
//	@Override
//	public void checkParameter(BasicResVo basicResVo) {
//		if (ServiceContant.ARCHIVE_DETAIL_H.equals(this.req_type)) {
//			if (StringUtils.isBlank(this.inquery_id)) {
//				logger.warn("【健康档案信息查询接口】来电清单编号为空，请注意...");
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "来电清单编号为空", null);
//				return;
//			} else if (!this.inquery_id.matches(ServiceContant.REG_NUMBER)) {
//				logger.warn("【健康档案信息查询接口】来电清单编号[inquiry_id=" + this.inquery_id + "]格式非法");
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "来电清单编号格式非法", null);
//				return;
//			}
//		} else if (ServiceContant.ARCHIVE_DETAIL_A.equals(this.req_type) 
//				|| ServiceContant.ARCHIVE_DETAIL_E.equals(this.req_type)
//				|| ServiceContant.ARCHIVE_DETAIL_R.equals(this.req_type)){
//			if (StringUtils.isBlank(this.archive_id)) {
//				logger.warn("【健康档案信息查询接口】健康档案编号为空，请注意...");
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案编号为空", null);
//				return;
//			}else if (!this.archive_id.matches(ServiceContant.REG_NUMBER)) {
//				logger.warn("【健康档案信息查询接口】来电清单编号[archive_id=" + this.archive_id + "]格式非法");
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案编号格式非法", null);
//				return;
//			}
//		} else {
//			logger.warn("【健康档案信息查询接口】请求类型[req_type=" + this.req_type + "]非法,请注意...");
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "请求类型非法", null);
//		}
//	}
//}
