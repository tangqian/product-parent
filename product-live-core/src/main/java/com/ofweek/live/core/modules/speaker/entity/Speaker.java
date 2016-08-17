package com.ofweek.live.core.modules.speaker.entity;

import com.ofweek.live.core.common.persistence.DataEntity;

/**
 * 
 * @author tangqian
 */
public class Speaker extends DataEntity<Speaker> {

	private static final long serialVersionUID = 1L;
	
	private String company;
	
	private String address;
	
	private String industry;
	
	private String name;
	
	private String job;
	
	private Integer logoId;
	
	private Integer sex;
	
	private String department;
	
	private String mobilePhone;
	
	private String telephone;
	
	private String fax;

	public void setCompany(String value) {
		this.company = value;
	}
	
	public String getCompany() {
		return this.company;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setIndustry(String value) {
		this.industry = value;
	}
	
	public String getIndustry() {
		return this.industry;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setJob(String value) {
		this.job = value;
	}
	
	public String getJob() {
		return this.job;
	}
	
	public void setLogoId(Integer value) {
		this.logoId = value;
	}
	
	public Integer getLogoId() {
		return this.logoId;
	}
	
	public void setSex(Integer value) {
		this.sex = value;
	}
	
	public Integer getSex() {
		return this.sex;
	}
	
	public void setDepartment(String value) {
		this.department = value;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
	public void setMobilePhone(String value) {
		this.mobilePhone = value;
	}
	
	public String getMobilePhone() {
		return this.mobilePhone;
	}
	
	public void setTelephone(String value) {
		this.telephone = value;
	}
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setFax(String value) {
		this.fax = value;
	}
	
	public String getFax() {
		return this.fax;
	}
	
}
