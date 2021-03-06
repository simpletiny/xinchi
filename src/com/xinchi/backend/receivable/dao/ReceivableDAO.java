package com.xinchi.backend.receivable.dao;

import java.math.BigDecimal;
import java.util.List;

import com.xinchi.bean.ReceivableBean;
import com.xinchi.bean.ReceivableSummaryBean;
import com.xinchi.tools.Page;

public interface ReceivableDAO {

	public void insert(ReceivableBean receivable);

	public ReceivableSummaryBean selectReceivableSummary(String sales);

	public ReceivableBean selectReceivableByTeamNumber(String teamNumber);

	public void update(ReceivableBean receivable);

	public List<ReceivableBean> selectAllReceivablesWithFinancial();

	public List<ReceivableBean> selectByPage(Page<ReceivableBean> page);

	public void deleteByTeamNumber(String team_number);

	public BigDecimal fetchEmployeeBalance(String client_employee_pk);

}
