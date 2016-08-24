package com.ofweek.live.core.modules.sys.entity;

import com.ofweek.live.core.common.persistence.DataEntity;
import com.ofweek.live.core.common.utils.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author tangqian
 */
public class SysEmail extends DataEntity<SysEmail> {

	//待发送
	public static final int TO_SEND = 0;
	//发送成功
	public static final int SEND_SUCCESS = 1;
	//发送失败
	public static final int SEND_FAIL = 2;

	private static final long serialVersionUID = 1L;
	
	private String subject;
	
	private String content;
	
	private String receiver;
	
	private String cc;
	
	private String bcc;
	
	private Integer status;
	
	private String subjectId;
	
	private Integer subjectType;
	
	private java.util.Date sendTime;

	public void setSubject(String value) {
		this.subject = value;
	}

	@NotBlank(message = "邮件主题不能为空")
	public String getSubject() {
		return this.subject;
	}
	
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setReceiver(String value) {
		this.receiver = value;
	}

	@NotBlank(message = "接收者邮箱不能为空")
	public String getReceiver() {
		return this.receiver;
	}
	
	public void setCc(String value) {
		this.cc = value;
	}
	
	public String getCc() {
		return this.cc;
	}
	
	public void setBcc(String value) {
		this.bcc = value;
	}
	
	public String getBcc() {
		return this.bcc;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setSubjectId(String value) {
		this.subjectId = value;
	}
	
	public String getSubjectId() {
		return this.subjectId;
	}
	
	public void setSubjectType(Integer value) {
		this.subjectType = value;
	}
	
	public Integer getSubjectType() {
		return this.subjectType;
	}
	
	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}
	
	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	/**
	 * 该邮件是否已成功发送
	 * @return
	 */
	public boolean isSendSuccess(){
		return status != null && status == SEND_SUCCESS;
	}

	@Override
	public void preInsert() {
		super.preInsert();
		status = TO_SEND;//状态0表示待发送， 1：发送成功，2：发送失败
		content = StringUtils.defaultString(content);
		subjectId = StringUtils.defaultString(subjectId, NULL_REFERENCE_ID);

		if(subjectType == null)
			subjectType = 0;
	}

	/**
	 * 邮箱服务器账户信息
	 */
	public static class EmailAccount {
		private String smtpServer;

		private String user;

		private String password;

		private String accountName;

		private String account;

		public String getSmtpServer() {
			return smtpServer;
		}

		public void setSmtpServer(String smtpServer) {
			this.smtpServer = smtpServer;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}
	}
}
