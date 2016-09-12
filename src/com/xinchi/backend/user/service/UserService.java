package com.xinchi.backend.user.service;

import java.util.List;

import com.xinchi.bean.UserBaseBean;
import com.xinchi.bean.UserCommonBean;
import com.xinchi.bean.UserInfoBean;

public interface UserService {

	/**
	 * 新增
	 * 
	 * @param bo
	 */
	public void register(UserBaseBean bo, UserInfoBean uib);

	/**
	 * 修改
	 * 
	 * @param bo
	 */
	public void update(UserBaseBean bo);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 根据主键查找
	 * 
	 * @param id
	 */
	public com.xinchi.bean.UserBaseBean selectByPrimaryKey(String id);

	/**
	 * 根据主键查找
	 * 
	 * @param id
	 */
	public com.xinchi.bean.UserBaseBean selectByParam(UserBaseBean bo);

	/**
	 * 根据条件查找
	 * 
	 * @param bo
	 */
	public List<com.xinchi.bean.UserBaseBean> getAllByParam(UserBaseBean bo);

	public String login(UserBaseBean ubb);
	
	public List<UserCommonBean> getAllUserCommonByParam(UserCommonBean bo);
}