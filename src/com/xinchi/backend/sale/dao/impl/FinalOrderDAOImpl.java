package com.xinchi.backend.sale.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.xinchi.backend.sale.dao.FinalOrderDAO;
import com.xinchi.bean.FinalOrderBean;
import com.xinchi.bean.FinalOrderSupplierBean;
import com.xinchi.common.DaoUtil;
import com.xinchi.tools.Page;

@Repository
public class FinalOrderDAOImpl extends SqlSessionDaoSupport implements FinalOrderDAO {

	private SqlSession sqlSession;
	private DaoUtil daoUtil;

	public void initDao() {
		if (daoUtil == null) {
			sqlSession = sqlSession == null ? getSqlSession() : sqlSession;
			daoUtil = new DaoUtil(sqlSession);
		}
	}

	@Override
	public void insert(FinalOrderBean order) {
		daoUtil.insertBO("com.xinchi.bean.mapper.FinalOrderMapper.insert", order);

	}

	@Override
	public void saveOrderSupplier(List<FinalOrderSupplierBean> arrSupplier) {
		for (FinalOrderSupplierBean bean : arrSupplier) {
			daoUtil.insertBO("com.xinchi.bean.mapper.FinalOrderSupplierMapper.insert", bean);

		}
	}

	@Override
	public List<FinalOrderBean> selectAllByParam(FinalOrderBean order) {
		return daoUtil.selectByBOParamT("com.xinchi.bean.mapper.FinalOrderMapper.selectByParam", order);
	}

	@Override
	public FinalOrderBean searchFinalOrderByPk(String order_pk) {
		return (FinalOrderBean) daoUtil.selectByPK("com.xinchi.bean.mapper.FinalOrderMapper.selectByPrimaryKey", order_pk);
	}

	@Override
	public List<FinalOrderSupplierBean> searchFinalSupplier(String team_number) {
		return daoUtil.selectByParam("com.xinchi.bean.mapper.FinalOrderSupplierMapper.selectByTeamNumber", team_number);
	}

	@Override
	public List<FinalOrderBean> selectAllByPage(Page<FinalOrderBean> page) {
		return daoUtil.selectByParam("com.xinchi.bean.mapper.FinalOrderMapper.selectByPage", page);
	}

	@Override
	public FinalOrderBean selectByTeamNumber(String team_number) {
		return daoUtil.selectOneValueByParam("com.xinchi.bean.mapper.FinalOrderMapper.selectByTeamNumber", team_number);
	}

	@Override
	public List<FinalOrderSupplierBean> searchFinalSupplierByParam(FinalOrderSupplierBean bo) {
		return daoUtil.selectByParam("com.xinchi.bean.mapper.FinalOrderSupplierMapper.selectByParam", bo);
	}

	@Override
	public void deleteByTeamNumber(String team_number) {
		daoUtil.deleteByParam("com.xinchi.bean.mapper.FinalOrderSupplierMapper.deleteByTeamNumber", team_number);

	}

	@Override
	public void deleteFinalOrderByPk(String order_pk) {
		daoUtil.deleteByParam("com.xinchi.bean.mapper.FinalOrderMapper.deleteByPrimaryKey", order_pk);
	}

}
