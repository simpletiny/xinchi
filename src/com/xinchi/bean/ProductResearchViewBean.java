package com.xinchi.bean;

import java.io.Serializable;

import com.xinchi.common.SupperBO;

public class ProductResearchViewBean extends SupperBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	private String content;

	private String update_user;

	private String pk;

	private String create_user;

	private String label;

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public String getPk() {
		return pk;
	}

	public String getCreate_user() {
		return create_user;
	}

	public String getLabel() {
		return label;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
