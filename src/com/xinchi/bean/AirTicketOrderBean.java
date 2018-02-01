package com.xinchi.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.xinchi.common.SupperBO;

public class AirTicketOrderBean extends SupperBO implements Serializable {
	private static final long serialVersionUID = -2803166589410802457L;

	private String client_number;

	private BigDecimal ticket_cost;

	private String first_ticket_date;

	private String first_start_city;

	private String first_end_city;

	private Integer people_count;

	private String team_number;

	private String tour_product_pk;

	private String sale_order_pk;

	private String status;

	private String lock_flg;
	private List<String> lock_flgs;
	private String sale_standard_flg;

	private String pk;

	private String create_user;

	private String update_user;

	private String first_from_to;
	private String client_name;

	private String passenger;
	public String getClient_number() {
		return client_number;
	}

	public void setClient_number(String client_number) {
		this.client_number = client_number;
	}

	public BigDecimal getTicket_cost() {
		return ticket_cost;
	}

	public void setTicket_cost(BigDecimal ticket_cost) {
		this.ticket_cost = ticket_cost;
	}

	public String getFirst_ticket_date() {
		return first_ticket_date;
	}

	public void setFirst_ticket_date(String first_ticket_date) {
		this.first_ticket_date = first_ticket_date;
	}

	public String getFirst_start_city() {
		return first_start_city;
	}

	public void setFirst_start_city(String first_start_city) {
		this.first_start_city = first_start_city;
	}

	public String getFirst_end_city() {
		return first_end_city;
	}

	public void setFirst_end_city(String first_end_city) {
		this.first_end_city = first_end_city;
	}

	public Integer getPeople_count() {
		return people_count;
	}

	public void setPeople_count(Integer people_count) {
		this.people_count = people_count;
	}

	public String getTeam_number() {
		return team_number;
	}

	public void setTeam_number(String team_number) {
		this.team_number = team_number;
	}

	public String getTour_product_pk() {
		return tour_product_pk;
	}

	public void setTour_product_pk(String tour_product_pk) {
		this.tour_product_pk = tour_product_pk;
	}

	public String getSale_order_pk() {
		return sale_order_pk;
	}

	public void setSale_order_pk(String sale_order_pk) {
		this.sale_order_pk = sale_order_pk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLock_flg() {
		return lock_flg;
	}

	public void setLock_flg(String lock_flg) {
		this.lock_flg = lock_flg;
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

	public String getFirst_from_to() {
		return first_from_to;
	}

	public void setFirst_from_to(String first_from_to) {
		this.first_from_to = first_from_to;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getSale_standard_flg() {
		return sale_standard_flg;
	}

	public void setSale_standard_flg(String sale_standard_flg) {
		this.sale_standard_flg = sale_standard_flg;
	}

	public List<String> getLock_flgs() {
		return lock_flgs;
	}

	public void setLock_flgs(List<String> lock_flgs) {
		this.lock_flgs = lock_flgs;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

}