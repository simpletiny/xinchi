package com.xinchi.backend.accounting.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.xinchi.backend.accounting.dao.AccPaidDAO;
import com.xinchi.bean.WaitingForPaidBean;
import com.xinchi.common.DaoUtil;
import com.xinchi.tools.Page;

@Repository
public class AccPaidDAOImpl extends SqlSessionDaoSupport implements AccPaidDAO {
	private SqlSession sqlSession;
	private DaoUtil daoUtil;

	public void initDao() {
		if (daoUtil == null) {
			sqlSession = sqlSession == null ? getSqlSession() : sqlSession;
			daoUtil = new DaoUtil(sqlSession);
		}
	}

	@Override
	public String insert(WaitingForPaidBean paid) {
		return daoUtil.insertBO("com.xinchi.bean.mapper.WaitingForPaidMapper.insert", paid);
	}

	@Override
	public List<WaitingForPaidBean> selectByPage(Page page) {
		return daoUtil.selectByParam("com.xinchi.bean.mapper.WaitingForPaidMapper.selectByPage", page);
	}

	@Override
	public WaitingForPaidBean selectByPk(String pk) {

		return (WaitingForPaidBean) daoUtil.selectByPK("com.xinchi.bean.mapper.WaitingForPaidMapper.selectByPrimaryKey", pk);
	}

	@Override
	public WaitingForPaidBean selectByPayNumber(String pay_number) {

		return daoUtil.selectOneValueByParam("com.xinchi.bean.mapper.WaitingForPaidMapper.selectByPayNumber", pay_number);
	}

	@Override
	public void update(WaitingForPaidBean wfp) {
	daoUtil.updateByPK("com.xinchi.bean.mapper.WaitingForPaidMapper.updateByPrimaryKey", wfp);
	}
}
