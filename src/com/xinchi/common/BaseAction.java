package com.xinchi.common;

import com.opensymphony.xwork2.ActionSupport;
import com.xinchi.tools.Page;


/**
 * Action 基类
 * 
 * @author niushixing 2014-12-11 下午3:11:44
 * 
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -4887936515659054262L;
	protected String resultStr;
	protected final static String OK = "OK";
	protected Page page = new Page();

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
