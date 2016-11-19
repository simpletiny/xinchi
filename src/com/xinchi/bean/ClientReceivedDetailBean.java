package com.xinchi.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.xinchi.common.SupperBO;

public class ClientReceivedDetailBean extends SupperBO implements Serializable {
	private static final long serialVersionUID = -8576728527390144279L;

	private String team_number;

	private BigDecimal received;

	private String received_time;

	private String type;

	private String confirm_time;

	private String status;

	private String comment;

	private BigDecimal sum_received;

	private String related_pk;

	private String card_pk;

	private String card_account;

	private String create_user;

	private String update_user;

	private String pk;

	public String getTeam_number() {
		return team_number;
	}

	public void setTeam_number(String team_number) {
		this.team_number = team_number;
	}

	public BigDecimal getReceived() {
		return received;
	}

	public void setReceived(BigDecimal received) {
		this.received = received;
	}

	public String getReceived_time() {
		return received_time;
	}

	public void setReceived_time(String received_time) {
		this.received_time = received_time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getSum_received() {
		return sum_received;
	}

	public void setSum_received(BigDecimal sum_received) {
		this.sum_received = sum_received;
	}

	public String getRelated_pk() {
		return related_pk;
	}

	public void setRelated_pk(String related_pk) {
		this.related_pk = related_pk;
	}

	public String getCard_pk() {
		return card_pk;
	}

	public void setCard_pk(String card_pk) {
		this.card_pk = card_pk;
	}

	public String getCard_account() {
		return card_account;
	}

	public void setCard_account(String card_account) {
		this.card_account = card_account;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

}