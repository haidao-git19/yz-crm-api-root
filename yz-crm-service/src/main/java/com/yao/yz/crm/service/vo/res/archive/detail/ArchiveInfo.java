package com.yao.yz.crm.service.vo.res.archive.detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：健康档案查询详情
 * 
 * @Author wuwenjun
 * @Date Oct 28, 2015 1:07:09 PM
 * @Versin 1.0
 */
public class ArchiveInfo implements Serializable{

	private static final long serialVersionUID = -7694424902178890423L;

	/**
	 * 健康档案编号
	 */
	private Integer archive_id;

	/**
	 * 患者自诉
	 */
	private String self_desc;

	/**
	 * 患处照片
	 */
	private List<String> photos;

	/**
	 * 处理方式：F-初诊，S-复诊，R-复诊
	 */
	private String quest_type;

	/**
	 * 处理科室编号
	 */
	private Integer guest_department;

	/**
	 * 处理医生编号
	 */
	private Integer doctor_id;

	/**
	 * 档案标题
	 */
	private String illness_key;

	/**
	 * 私人医生咨询记录
	 */
	private String consult;

	/**
	 * 预约科室编号
	 */
	private Integer book_department;

	/**
	 * 预约医生编号
	 */
	private Integer book_doctor_id;

	/**
	 * 预约日期
	 */
	private Long book_date;

	/**
	 * 预约开始时间：HH：mm
	 */
	private String book_start;

	/**
	 * 预约结束时间：HH：mm
	 */
	private String book_end;
	
	/**
	 * 事项描述
	 */
	private String book_desc;
	
	/**
	 * 创建时间，格式：yyyy-MM-dd-HH:mm:ss
	 */
	private String create_time;
	
	/**
	 * 源健康档案id
	 */
	private Integer source_id;
	
	/**
	 * 来单清单编号
	 */
	private Integer inquery_id;
	
	public Integer getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(Integer inquery_id) {
		this.inquery_id = inquery_id;
	}

	public ArchiveInfo(){
		photos = new ArrayList<String>();
	}

	public Integer getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(Integer archive_id) {
		this.archive_id = archive_id;
	}

	public String getSelf_desc() {
		return self_desc==null?"":self_desc;
	}

	public void setSelf_desc(String self_desc) {
		this.self_desc = self_desc;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public String getQuest_type() {
		return quest_type==null?"":quest_type;
	}

	public void setQuest_type(String quest_type) {
		this.quest_type = quest_type;
	}

	public Integer getGuest_department() {
		return guest_department == null?0:guest_department;
	}

	public void setGuest_department(Integer guest_department) {
		this.guest_department = guest_department;
	}

	public Integer getDoctor_id() {
		return doctor_id==null?0:doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getBook_department() {
		return book_department==null?0:book_department;
	}

	public void setBook_department(Integer book_department) {
		this.book_department = book_department;
	}

	public Integer getBook_doctor_id() {
		return book_doctor_id==null?0:book_doctor_id;
	}

	public void setBook_doctor_id(Integer book_doctor_id) {
		this.book_doctor_id = book_doctor_id;
	}

	public String getIllness_key() {
		return illness_key==null?"":illness_key;
	}

	public void setIllness_key(String illness_key) {
		this.illness_key = illness_key;
	}

	public String getConsult() {
		return consult==null?"":consult;
	}

	public void setConsult(String consult) {
		this.consult = consult;
	}

	public Long getBook_date() {
		return book_date;
	}

	public void setBook_date(Long book_date) {
		this.book_date = book_date;
	}

	public String getBook_start() {
		return book_start==null?"":book_start;
	}

	public void setBook_start(String book_start) {
		this.book_start = book_start;
	}

	public String getBook_end() {
		return book_end==null?"":book_end;
	}

	public void setBook_end(String book_end) {
		this.book_end = book_end;
	}

	public String getBook_desc() {
		return book_desc==null?"":book_desc;
	}

	public void setBook_desc(String book_desc) {
		this.book_desc = book_desc;
	}

	public String getCreate_time() {
		return create_time==null?"":create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getSource_id() {
		return source_id;
	}

	public void setSource_id(Integer source_id) {
		this.source_id = source_id;
	}
}
