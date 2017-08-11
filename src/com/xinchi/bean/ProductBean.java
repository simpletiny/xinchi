package com.xinchi.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.xinchi.common.SupperBO;

public class ProductBean extends SupperBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String product_number;

	private String location;

	private List<String> locations;

	private Integer days;

	private BigDecimal profit_space;

	private BigDecimal max_profit_substract;

	private BigDecimal business_price;
	private String sale_flg;

	private String product_manager;

	private String comment;

	private String pk;

	private String create_user;

	private String update_user;
	private String air_ticket_charge;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct_number() {
		return product_number;
	}

	public void setProduct_number(String product_number) {
		this.product_number = product_number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public BigDecimal getProfit_space() {
		return profit_space;
	}

	public void setProfit_space(BigDecimal profit_space) {
		this.profit_space = profit_space;
	}

	public BigDecimal getMax_profit_substract() {
		return max_profit_substract;
	}

	public void setMax_profit_substract(BigDecimal max_profit_substract) {
		this.max_profit_substract = max_profit_substract;
	}

	public String getProduct_manager() {
		return product_manager;
	}

	public void setProduct_manager(String product_manager) {
		this.product_manager = product_manager;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
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

	public BigDecimal getBusiness_price() {
		return business_price;
	}

	public void setBusiness_price(BigDecimal business_price) {
		this.business_price = business_price;
	}

	public String getSale_flg() {
		return sale_flg;
	}

	public void setSale_flg(String sale_flg) {
		this.sale_flg = sale_flg;
	}

	public String getAir_ticket_charge() {
		return air_ticket_charge;
	}

	public void setAir_ticket_charge(String air_ticket_charge) {
		this.air_ticket_charge = air_ticket_charge;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}


}
