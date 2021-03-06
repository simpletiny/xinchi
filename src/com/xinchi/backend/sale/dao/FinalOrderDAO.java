package com.xinchi.backend.sale.dao;

import java.util.List;

import com.xinchi.bean.FinalOrderBean;
import com.xinchi.bean.FinalOrderSupplierBean;
import com.xinchi.tools.Page;

public interface FinalOrderDAO {

	public void insert(FinalOrderBean order);

	public void saveOrderSupplier(List<FinalOrderSupplierBean> arrSupplier);

	public List<FinalOrderBean> selectAllByParam(FinalOrderBean order);

	public FinalOrderBean searchFinalOrderByPk(String order_pk);

	public List<FinalOrderSupplierBean> searchFinalSupplier(String team_number);

	public List<FinalOrderBean> selectAllByPage(Page<FinalOrderBean> page);

	public FinalOrderBean selectByTeamNumber(String team_number);

	public List<FinalOrderSupplierBean> searchFinalSupplierByParam(
			FinalOrderSupplierBean bo);

	public void deleteByTeamNumber(String team_number);

	public void deleteFinalOrderByPk(String order_pk);

}
