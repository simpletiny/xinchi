package com.xinchi.bean;

import java.io.Serializable;
import com.xinchi.common.SupperBO;

public class ProductOrderOperationBean extends SupperBO implements Serializable {

	private static final long serialVersionUID = -8159159160110864379L;

	private String team_number;

	private Integer operate_index;

	private Integer supplier_count;

	private String supplier_employee_pk;
	private String supplier_employee_name;

	private java.math.BigDecimal supplier_cost;

	private String supplier_product_name;

	private Integer people_count;

	private String pick_date;

	private String send_date;
	private String pick_type;

	private String picker_cellphone;

	private String send_type;

	private String create_user;

	private String update_user;

	private String pk;

	private String status;

	private Integer land_day;

	private String picker;

	private Integer off_day;

	public String getTeam_number() {
		return team_number;
	}

	public void setTeam_number(String team_number) {
		this.team_number = team_number;
	}

	public Integer getOperate_index() {
		return operate_index;
	}

	public void setOperate_index(Integer operate_index) {
		this.operate_index = operate_index;
	}

	public Integer getSupplier_count() {
		return supplier_count;
	}

	public void setSupplier_count(Integer supplier_count) {
		this.supplier_count = supplier_count;
	}

	public String getSupplier_employee_pk() {
		return supplier_employee_pk;
	}

	public void setSupplier_employee_pk(String supplier_employee_pk) {
		this.supplier_employee_pk = supplier_employee_pk;
	}

	public java.math.BigDecimal getSupplier_cost() {
		return supplier_cost;
	}

	public void setSupplier_cost(java.math.BigDecimal supplier_cost) {
		this.supplier_cost = supplier_cost;
	}

	public String getSupplier_product_name() {
		return supplier_product_name;
	}

	public void setSupplier_product_name(String supplier_product_name) {
		this.supplier_product_name = supplier_product_name;
	}

	public Integer getPeople_count() {
		return people_count;
	}

	public void setPeople_count(Integer people_count) {
		this.people_count = people_count;
	}

	public String getPick_date() {
		return pick_date;
	}

	public void setPick_date(String pick_date) {
		this.pick_date = pick_date;
	}

	public String getPick_type() {
		return pick_type;
	}

	public void setPick_type(String pick_type) {
		this.pick_type = pick_type;
	}

	public String getPicker_cellphone() {
		return picker_cellphone;
	}

	public void setPicker_cellphone(String picker_cellphone) {
		this.picker_cellphone = picker_cellphone;
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String send_type) {
		this.send_type = send_type;
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

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getSupplier_employee_name() {
		return supplier_employee_name;
	}

	public void setSupplier_employee_name(String supplier_employee_name) {
		this.supplier_employee_name = supplier_employee_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLand_day() {
		return land_day;
	}

	public void setLand_day(Integer land_day) {
		this.land_day = land_day;
	}

	public String getPicker() {
		return picker;
	}

	public void setPicker(String picker) {
		this.picker = picker;
	}

	public Integer getOff_day() {
		return off_day;
	}

	public void setOff_day(Integer off_day) {
		this.off_day = off_day;
	}

}
