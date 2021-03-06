package com.xinchi.backend.ticket.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.xinchi.backend.ticket.dao.AirTicketOrderDAO;
import com.xinchi.bean.AirTicketOrderBean;
import com.xinchi.common.DaoUtil;
import com.xinchi.tools.Page;

@Repository
public class AirTicketOrderDAOImpl extends SqlSessionDaoSupport implements AirTicketOrderDAO {

	private SqlSession sqlSession;
	private DaoUtil daoUtil;

	public void initDao() {
		if (daoUtil == null) {
			sqlSession = sqlSession == null ? getSqlSession() : sqlSession;
			daoUtil = new DaoUtil(sqlSession);
		}
	}

	@Override
	public String insert(AirTicketOrderBean bean) {
		return daoUtil.insertBO("com.xinchi.bean.mapper.AirTicketOrderMapper.insert", bean);
	}

	@Override
	public void update(AirTicketOrderBean bean) {
		daoUtil.updateByPK("com.xinchi.bean.mapper.AirTicketOrderMapper.updateByPrimaryKey", bean);
	}

	@Override
	public void delete(String id) {
		daoUtil.deleteByPK("com.xinchi.bean.mapper.AirTicketOrderMapper.deleteByPrimaryKey", id);
	}

	@Override
	public AirTicketOrderBean selectByPrimaryKey(String id) {
		return (AirTicketOrderBean) daoUtil.selectByPK("com.xinchi.bean.mapper.AirTicketOrderMapper.selectByPrimaryKey",
				id);
	}

	@Override
	public List<AirTicketOrderBean> selectByParam(AirTicketOrderBean bean) {
		List<AirTicketOrderBean> list = daoUtil
				.selectByParam("com.xinchi.bean.mapper.AirTicketOrderMapper.selectByParam", bean);
		return list;
	}

	@Override
	public List<AirTicketOrderBean> selectByPage(Page<AirTicketOrderBean> page) {
		return daoUtil.selectByParam("com.xinchi.bean.mapper.AirTicketOrderMapper.selectByPage", page);
	}

	@Override
	public List<AirTicketOrderBean> selectByPks(List<String> airTicketOrderPks) {
		return daoUtil.selectByParam("com.xinchi.bean.mapper.AirTicketOrderMapper.selectByPks", airTicketOrderPks);
	}

	@Override
	public AirTicketOrderBean selectBySaleOrderPk(String pk) {

		return daoUtil.selectOneValueByParam("com.xinchi.bean.mapper.AirTicketOrderMapper.selectBySaleOrderPk", pk);
	}

}