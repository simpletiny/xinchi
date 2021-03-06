package com.xinchi.backend.client.dao;

import java.util.List;

import com.xinchi.bean.ClientChangeSaleLogBean;


public interface ClientChangeSaleLogDAO{
	
	/**
	 * 新增
	 * @param bean
	 */
	public void insert(ClientChangeSaleLogBean bean);
	
	/**
	 * 修改
	 * @param bean
	 */
	public void update(ClientChangeSaleLogBean bean);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 根据主键查找
	 * @param id
	 */
	public ClientChangeSaleLogBean selectByPrimaryKey(String id);
	
	/**
	 * 根据条件查找
	 * @param bean
	 */
	public List<ClientChangeSaleLogBean> selectByParam(ClientChangeSaleLogBean bean);
}