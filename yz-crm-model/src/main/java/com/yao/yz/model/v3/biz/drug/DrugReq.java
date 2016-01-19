/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 31, 2015-11:25:54 AM
* @version 1.0
*/
package com.yao.yz.model.v3.biz.drug;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;

/**
 * 公司名: 壹药网
 * 类名称：DrugReq
 * 类描述：
 * @author wangyulong
 * @date Dec 31, 2015-11:25:54 AM
 */
public class DrugReq extends AbstractReqModel{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= -8005156240418534410L;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}
	
	//@BindValidation(_blackable = false, _nullable = false, description = "page_index")
	private String page_index;
	
	private String page_size;
	
	@BindValidation(_blackable = false, _nullable = false, description = "keyWord")
	private String keyWord;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getPage_index() {
		return page_index;
	}

	public void setPage_index(String page_index) {
		this.page_index = page_index;
	}

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	

}
