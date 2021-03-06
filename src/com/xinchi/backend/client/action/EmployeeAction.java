package com.xinchi.backend.client.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xinchi.backend.client.service.EmployeeService;
import com.xinchi.bean.ClientEmployeeBean;
import com.xinchi.bean.RelationLevelDto;
import com.xinchi.common.BaseAction;
import com.xinchi.common.ResourcesConstants;
import com.xinchi.common.SimpletinyString;
import com.xinchi.common.UserSessionBean;
import com.xinchi.common.XinChiApplicationContext;

@Controller
@Scope("prototype")
public class EmployeeAction extends BaseAction {
	private static final long serialVersionUID = 599705102230406514L;
	private ClientEmployeeBean employee;
	@Autowired
	private EmployeeService employeeService;

	public String createEmployee() {
		UserSessionBean sessionBean = (UserSessionBean) XinChiApplicationContext
				.getSession(ResourcesConstants.LOGIN_SESSION_KEY);
		employee.setSales_name(sessionBean.getUser_name());
		employee.setSales(sessionBean.getPk());
		resultStr = employeeService.createEmployee(employee);
		return SUCCESS;
	}

	public String updateEmployee() {
		resultStr = employeeService.updateEmployee(employee);
		return SUCCESS;
	}

	public String updateEmployeeSimply() {
		resultStr = employeeService.update(employee);
		return SUCCESS;
	}

	public String swapDimission() {
		resultStr = employeeService.update(employee);
		return SUCCESS;
	}

	private String market_level;
	public String setClientEmployeeLevel() {
		resultStr  = employeeService.updateMarketLevel(employee_pks,market_level);
		return SUCCESS;
	}

	private List<ClientEmployeeBean> employees;

	private String employee_status;

	@SuppressWarnings("unchecked")
	public String searchEmployeeByPage() {
		UserSessionBean sessionBean = (UserSessionBean) XinChiApplicationContext
				.getSession(ResourcesConstants.LOGIN_SESSION_KEY);
		String roles = sessionBean.getUser_roles();
		Map<String, Object> params = new HashMap<String, Object>();

		if (!roles.contains(ResourcesConstants.USER_ROLE_ADMIN)) {
			employee.setSales(sessionBean.getPk());
		}

		params.put("bo", employee);
		page.setParams(params);
		employees = employeeService.getAllClientEmployeeByPage(page);
		return SUCCESS;
	}

	private RelationLevelDto rld;

	public String searchSumCntData() {
		UserSessionBean sessionBean = (UserSessionBean) XinChiApplicationContext
				.getSession(ResourcesConstants.LOGIN_SESSION_KEY);
		String roles = sessionBean.getUser_roles();

		if (roles.contains(ResourcesConstants.USER_ROLE_ADMIN)) {
			if (null == employee.getSales() || employee.getSales().equals("")) {
				rld = employeeService.selectRelationCntAdmin();
			} else {
				rld = employeeService.selectRelationCntBySales(employee.getSales());
			}
		} else {
			employee = new ClientEmployeeBean();
			employee.setSales(sessionBean.getPk());
			rld = employeeService.selectRelationCntBySales(employee.getSales());
		}

		if (null == rld) {
			rld = new RelationLevelDto();
		}
		return SUCCESS;
	}

	public String searchEmployee() {
		UserSessionBean sessionBean = (UserSessionBean) XinChiApplicationContext
				.getSession(ResourcesConstants.LOGIN_SESSION_KEY);
		String roles = sessionBean.getUser_roles();

		if (!roles.contains(ResourcesConstants.USER_ROLE_ADMIN)) {
			employee = new ClientEmployeeBean();
			employee.setSales(sessionBean.getPk());
		}

		employees = employeeService.getAllClientEmployeeByParam(employee);
		return SUCCESS;
	}

	private List<String> employee_pks;

	public String deleteClientEmployee() {
		resultStr = employeeService.deleteClientEmployee(employee_pks);
		return SUCCESS;
	}

	private List<String> sale_pks;

	public String changeEmployeeSales() {
		resultStr = employeeService.changeEmployeeSales(employee_pks, sale_pks);

		return SUCCESS;
	}

	/**
	 * 删除客户员工（物理删除）
	 * 
	 * @return
	 */
	public String deleteClientEmployeeReally() {
		resultStr = employeeService.deleteClientEmployeeReally(employee_pk);
		return SUCCESS;
	}

	/**
	 * 合并客户员工资料
	 * 
	 * @return
	 */
	public String combineClientEmployee() {
		resultStr = employeeService.combineClientEmployee(employee_pks);
		return SUCCESS;
	}

	/**
	 * 跳槽
	 * 
	 * @return
	 */
	public String jobHopping() {
		resultStr = employeeService.jobHopping(employee);
		return SUCCESS;
	}

	/**
	 * 审核客户员工
	 * 
	 * @return
	 */
	public String reviewEmployee() {
		employee.setReview_flg("Y");
		resultStr = employeeService.reviewEmployee(employee);
		return SUCCESS;
	}

	public String publicClientEmployee() {
		resultStr = employeeService.publicClientEmployee(employee_pks);
		return SUCCESS;
	}

	public String recoveryClientEmployee() {
		resultStr = employeeService.recoveryClientEmployee(employee_pks);
		return SUCCESS;
	}

	private String employee_pk;

	public String searchOneEmployee() {
		employee = employeeService.selectByPrimaryKey(employee_pk);
		return SUCCESS;
	}

	public String checkEmployeeCellphone() {
		employee.setCellphone1(employee.getCellphone());
		employees = employeeService.getAllClientEmployeeByParam(employee);

		if (employees != null && employees.size() > 0) {
			if (employee.getPk() != null) {
				resultStr = INPUT;
				for (ClientEmployeeBean e : employees) {
					if (!e.getPk().equals(employee.getPk())) {
						resultStr = SUCCESS;
					}
				}
			} else {
				resultStr = SUCCESS;
			}
		} else {
			resultStr = INPUT;
		}

		return SUCCESS;
	}

	public String checkEmployeeWechat() {
		employee.setWechat1(employee.getWechat());
		employees = employeeService.getAllClientEmployeeByParam(employee);

		if (employees != null && employees.size() > 0) {
			if (employee.getPk() != null) {
				resultStr = INPUT;
				for (ClientEmployeeBean e : employees) {
					if (!e.getPk().equals(employee.getPk())) {

						resultStr = SUCCESS;
					}
				}
			} else {
				resultStr = SUCCESS;
			}
		} else {
			resultStr = INPUT;
		}

		return SUCCESS;
	}

	public String checkTelInfo() {
		ClientEmployeeBean e = employeeService.selectByPrimaryKey(employee_pk);
		if (SimpletinyString.isEmpty(e.getCellphone1())) {
			e.setCellphone1(ResourcesConstants.PLACE_HOLDER);
		}
		if (SimpletinyString.isEmpty(e.getWechat1())) {
			e.setWechat1(ResourcesConstants.PLACE_HOLDER);
		}
		if (SimpletinyString.isEmpty(e.getWechat())) {
			e.setWechat(ResourcesConstants.PLACE_HOLDER);
		}
		List<ClientEmployeeBean> es = employeeService.selectSameTelEmployee(e);
		if (null != es && es.size() > 0) {
			resultStr = "exist";
			employee = es.get(0);
		} else {
			resultStr = SUCCESS;
		}
		return SUCCESS;
	}

	public ClientEmployeeBean getEmployee() {
		return employee;
	}

	public void setEmployee(ClientEmployeeBean employee) {
		this.employee = employee;
	}

	public List<ClientEmployeeBean> getEmployees() {
		return employees;
	}

	public void setEmployees(List<ClientEmployeeBean> employees) {
		this.employees = employees;
	}

	public String getEmployee_pk() {
		return employee_pk;
	}

	public void setEmployee_pk(String employee_pk) {
		this.employee_pk = employee_pk;
	}

	public List<String> getEmployee_pks() {
		return employee_pks;
	}

	public void setEmployee_pks(List<String> employee_pks) {
		this.employee_pks = employee_pks;
	}

	public String getEmployee_status() {
		return employee_status;
	}

	public void setEmployee_status(String employee_status) {
		this.employee_status = employee_status;
	}

	public RelationLevelDto getRld() {
		return rld;
	}

	public void setRld(RelationLevelDto rld) {
		this.rld = rld;
	}

	public List<String> getSale_pks() {
		return sale_pks;
	}

	public void setSale_pks(List<String> sale_pks) {
		this.sale_pks = sale_pks;
	}

	public String getMarket_level() {
		return market_level;
	}

	public void setMarket_level(String market_level) {
		this.market_level = market_level;
	}
}