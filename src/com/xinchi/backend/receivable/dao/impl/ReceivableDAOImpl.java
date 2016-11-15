package com.xinchi.backend.receivable.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.xinchi.backend.receivable.dao.ReceivableDAO;
import com.xinchi.bean.ReceivableBean;
import com.xinchi.bean.ReceivableSummaryBean;
import com.xinchi.common.DaoUtil;
import com.xinchi.common.ResourcesConstants;
import com.xinchi.common.SimpletinyString;
import com.xinchi.common.UserSessionBean;
import com.xinchi.common.XinChiApplicationContext;

@Repository
public class ReceivableDAOImpl extends SqlSessionDaoSupport implements
		ReceivableDAO {
	private SqlSession sqlSession;
	private DaoUtil daoUtil;

	public void initDao() {
		if (daoUtil == null) {
			sqlSession = sqlSession == null ? getSqlSession() : sqlSession;
			daoUtil = new DaoUtil(sqlSession);
		}
	}

	@Override
	public void insert(ReceivableBean receivable) {
		daoUtil.insertBO("com.xinchi.bean.mapper.ReceivableMapper.insert",
				receivable);
	}

	@Override
	public ReceivableSummaryBean selectReceivableSummary(String sales) {

		UserSessionBean sessionBean = (UserSessionBean) XinChiApplicationContext
				.getSession(ResourcesConstants.LOGIN_SESSION_KEY);
		String roles = sessionBean.getUser_roles();
		String user_number = sessionBean.getUser_number();
		if (!roles.contains(ResourcesConstants.USER_ROLE_ADMIN)) {
			return daoUtil
					.selectOneValueByParam(
							"com.xinchi.bean.mapper.ReceivableSummaryMapper.selectByUserNumber",
							user_number);
		} else {
			if (!SimpletinyString.isEmpty(sales)) {
				return daoUtil
						.selectOneValueByParam(
								"com.xinchi.bean.mapper.ReceivableSummaryMapper.selectByUserNumber",
								sales);
			} else {
				return daoUtil
						.selectOneValue("com.xinchi.bean.mapper.ReceivableSummaryMapper.selectReceivableSummary");
			}

		}
	}
}
