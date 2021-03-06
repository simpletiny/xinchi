package com.xinchi.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 生成静态HTML通用类
 * 
 * @author niushixing 2014-11-27 上午11:10:38
 * 
 */
public class FreemakerUtil {
	private static Logger logger = Logger.getLogger(FreemakerUtil.class);

	/**
	 * 生成HTML
	 * 
	 * @param context
	 * @param data
	 *            数据
	 * @param templetPath
	 *            模板名称
	 * @param htmlPath
	 *            生成文件路径
	 */
	public static void createHTML(ServletContext context, Map<String, Object> data, String templetPath, String htmlPath) {
		createHTML(context, data, "UTF-8", templetPath, "UTF-8", htmlPath);
	}

	public static void createHTML(ServletContext context, Map<String, Object> data, String templetEncode,
			String templetPath, String htmlEncode, String htmlPath) {
		Configuration freemarkerCfg = new Configuration();
		Writer writer = null;
		try {
			freemarkerCfg.setServletContextForTemplateLoading(context, "/");
			// 设置对象包装器
			freemarkerCfg.setObjectWrapper(new DefaultObjectWrapper());

			// 设置异常处理器
			freemarkerCfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			// 指定模版路径
			Template template = freemarkerCfg.getTemplate(templetPath, templetEncode);
			template.setEncoding(templetEncode);
			// 静态页面路径
			File htmlFile = new File(htmlPath);
			if (!htmlFile.getParentFile().exists()) {
				htmlFile.getParentFile().mkdirs();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), htmlEncode));
			// 处理模版
			template.process(data, writer);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			logger.error(e1);
		} catch (TemplateException e2) {
			logger.error(e2);
		}
	}
}
