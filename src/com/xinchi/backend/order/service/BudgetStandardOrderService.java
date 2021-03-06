package com.xinchi.backend.order.service;

import java.util.List;

import com.xinchi.bean.BudgetStandardOrderBean;
import com.xinchi.common.BaseService;

public interface BudgetStandardOrderService extends BaseService {

	/**
	 * 新增
	 * 
	 * @param bean
	 */
	public String createOrder(BudgetStandardOrderBean bean, String json);

	/**
	 * 修改
	 * 
	 * @param bean
	 */
	public String update(BudgetStandardOrderBean bean,String json);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public String delete(String id);

	/**
	 * 根据主键查找
	 * 
	 * @param id
	 */
	public BudgetStandardOrderBean selectByPrimaryKey(String id);

	/**
	 * 根据条件查找
	 * 
	 * @param bean
	 */
	public List<BudgetStandardOrderBean> selectByParam(BudgetStandardOrderBean bean);

	public String updateComment(BudgetStandardOrderBean bean);

	public BudgetStandardOrderBean selectByTeamNumber(String team_number);

	public String updateConfirmedStandardOrder(BudgetStandardOrderBean bsOrder,String json);

	public String rollBackCOrder(String order_pk);
}