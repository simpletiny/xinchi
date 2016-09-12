package com.xinchi.common;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 默认Action
 * 
 * @author niushixing 2014-12-25 下午7:12:20
 * 
 */
@Controller
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DefaultAction extends ActionSupport {
	private static final long serialVersionUID = -631024752203403L;

	public String execute() {

		return "NOTFOUND";
	}
}
