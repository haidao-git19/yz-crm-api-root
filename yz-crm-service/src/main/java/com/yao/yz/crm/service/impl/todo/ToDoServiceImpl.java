package com.yao.yz.crm.service.impl.todo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzToBeDoneDao;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzToBeDone;
import com.yao.yz.crm.service.interf.todo.TodoService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.util.ServiceUtil;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.todo.ToDoReq;
import com.yao.yz.crm.service.vo.req.todo.TodoFinishReq;
import com.yao.yz.crm.service.vo.res.todo.ToDoList;
import com.yao.yz.crm.service.vo.res.todo.Todo;
import com.yao.yz.util.cache.merberCache.MemcachedUtils;
import com.yao.yz.util.common.MemcacheKeyPropsReader;

/**
 *	描述：待办事项服务接口实现
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 10:09:18 AM
 *	@Versin 1.0
 */
@Service
public class ToDoServiceImpl implements TodoService{

	private static final Logger logger = Logger.getLogger(ToDoServiceImpl.class);
	
	@Autowired
	CrmYzToBeDoneDao yzToBeDoneDao;

	@Override
	public BasicResVo todoFinish(TodoFinishReq todoFinishReq) {
		BasicResVo basicResVo = new BasicResVo();
		
		// 参数校验
		todoFinishReq.checkParameter(basicResVo);
		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
			return basicResVo;
		}
		
		// 更新待办事项状态
		int flag = yzToBeDoneDao.todayFinish(Integer.parseInt(todoFinishReq.getArchive_id()));
		
		if (flag == 1) {
			String key = MemcacheKeyPropsReader.getProperties("YZ_USER_ARCHIVE_KEY") + todoFinishReq.getArchive_id();
			try {
				if (MemcachedUtils.delete(key)) {
					logger.info("【MemberCache缓存更新】从缓存中删除用户健康档案列表成功,key=[" + key + "]");
				} else {
					logger.warn("【MemberCache缓存更新】从缓存中删除用户健康档案列表失败,key=[" + key + "]");
				}
			} catch (Exception e) {
				logger.error("【MemberCache缓存更新】更新缓存中的用户健康档案列表异常,key=[" + key + "]",e);
			}
			logger.info("【待办事项完成接口】更新健康档案[archiveId=" + todoFinishReq.getArchive_id() + "]完成状态成功..");
			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", null);
		} else {
			logger.error("【待办事项完成接口】更新健康档案[archiveId=" + todoFinishReq.getArchive_id() + "]完成状态失败");
			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案不存在", null);
		}
	}

	@Override
	public BasicResVo getToList(ToDoReq toDoReq) {
		BasicResVo basicResVo = new BasicResVo();
		
		// 参数校验
		toDoReq.checkParameter(basicResVo);
		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
			return basicResVo;
		}
		
		ToDoList toDoList = new ToDoList();
		try {
			List<CrmYzToBeDone> toBeDones = null;
			// 判断是今日还是明日待办事项
			if (ServiceContant.REQ_TYPE_F.equals(toDoReq.getTodo_type())) {
				toBeDones = yzToBeDoneDao.getTomorrowList();
			} else if (ServiceContant.REQ_TYPE_N.equals(toDoReq.getTodo_type())){
				toBeDones = yzToBeDoneDao.getTodayList();
			}
			
			if (toBeDones != null) {
				for (CrmYzToBeDone toBeDone:toBeDones) {
					Todo todo = new Todo();
					
					// id
					todo.setArchive_id(toBeDone.getId());
					
					// bookStart bookEnd
					try {
						todo.setBook_start(ServiceUtil.intToTime(toBeDone.getBookStart()));
						todo.setBook_end(ServiceUtil.intToTime(toBeDone.getBookEnd()));
					} catch (Exception e) {
						logger.error("【待办事项查询接口】转换预约时间异常[archiveId=" + toBeDone.getId() + "]", e);
						return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "查询失败", null);
					}
					// userCall
					if (ServiceContant.SEX_MAN.equals(toBeDone.getUser().getSex())) {
						todo.setUser_call((toBeDone.getUser().getUserSecondName()==null?"":(toBeDone.getUser().getUserSecondName()) + ServiceContant.CALL_MAN));
					} else if (ServiceContant.SEX_WOMAN.equals(toBeDone.getUser().getSex())) {
						todo.setUser_call((toBeDone.getUser().getUserSecondName()==null?"":(toBeDone.getUser().getUserSecondName()) + ServiceContant.CALL_WOMAN));
					} else {
						logger.warn("【待办事项查询接口】用户[uid=" + toBeDone.getUid() + "未填写性别，请注意...");
					}
					
					// bookDesc
					todo.setBook_desc(toBeDone.getBookDesc());
					
					// bookDoctor
					if (toBeDone.getBookDoctorId() != null) {
						if (toBeDone.getBookDoctorId() != 0) {
							todo.setBook_doctor(toBeDone.getDoctor().getSecondName()+toBeDone.getDoctor().getFirstName());
						} else {
							todo.setBook_doctor("私人健康顾问");
						}
					}
					
					// uid
					todo.setUid(toBeDone.getUid());
					
					toDoList.getTodo_list().add(todo);
				}
			} else {
				logger.warn("【待办事项查询接口】无待办事项，请注意...");
			}
			logger.info("【待办事项查询接口】查询待办事项[reqType=" + toDoReq.getTodo_type() + "]成功...");
			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", toDoList);
		} catch (Exception e) {
			logger.error("【待办事项查询接口】查询待办事项异常", e);
			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "查询数据库异常", null);
		}
	}

	
}
