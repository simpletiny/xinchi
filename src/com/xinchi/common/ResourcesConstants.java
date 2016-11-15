package com.xinchi.common;

import java.util.ArrayList;
import java.util.List;

import com.xinchi.bean.TaskBean;

public class ResourcesConstants {

	// 用户注册状态
	// 申请中
	public static String USER_STATUS_APPLY = "A";
	// 已通过正常使用
	public static String USER_STATUS_NORMAL = "N";
	// 拒绝
	public static String USER_STATUS_REJECT = "R";

	public static String LOGIN_SESSION_KEY = "user";

	// 定时任务列表
	public static List<TaskBean> ARRAY_TASK = new ArrayList<TaskBean>();
	// 执行一次
	public static String TASK_ONETIME = "1";
	// 每天执行
	public static String TASK_EVERYDAY = "2";
	// 每个星期执行
	public static String TASK_EVERYWEEK = "3";
	// 每个月执行
	public static String TASK_EVERYMONTH = "4";
	// 每年执行
	public static String TASK_EVERYYEAR = "5";
	// never used每个世纪
	public static String TASK_EVERYCENTURY = "6";

	/* 用户角色 */
	public static String USER_ROLE_ADMIN = "ADMIN";
	public static String USER_ROLE_MANAGER = "MANAGER";
	public static String USER_ROLE_SALES = "SALES";
	public static String USER_ROLE_PRODUCT = "PRODUCT";
	public static String USER_ROLE_FINANCE = "FINANCE";
	
	//团队状态
	public static String TEAM_STATUS_BEFORE="未出团";
	public static String TEAM_STATUS_AFTER="已出团";
	public static String TEAM_STATUS_RETURN="已回团";
	public static String TEAM_STATUS_NOT_RETURN="未回团";
	
}
