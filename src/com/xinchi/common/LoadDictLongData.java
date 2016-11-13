package com.xinchi.common;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import com.xinchi.bean.TaskBean;
import com.xinchi.solr.service.SimpletinySolr;
import com.xinchi.sys.xinchitask.dao.XinChiTaskDAO;

/**
 * @author wjx
 * @date 2015年1月9日 下午2:01:04
 * @note spring加载完成后，执行大数据字典的初始化
 */
@Repository
// 交给Spring管理，如果不是自动扫描加载bean的方式，则在xml里配一个即可
public class LoadDictLongData implements ApplicationListener {
	@Autowired
	private XinChiTaskDAO taskDao;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// 初始化专业信息
		// ResourcesConstants.ARRAY_MAJOR = majorDao.getAllByParam(new
		// HsMajorBO());
		// // 初始化职位
		// ResourcesConstants.ARRAY_JOB = jobDao.findAllJobsByParam(new
		// HsJobBO());
		// // 初始化行业
		// ResourcesConstants.ARRAY_TRADE = tradeDao.findAllTrade(new
		// HsTradeBO());
		// // 初始化城市
		// ResourcesConstants.ARRAY_CITY = cityDao.getAllByParam(new
		// HsCityBO());
		TaskBean task = new TaskBean();
		task.setIsdone("N");
		ResourcesConstants.ARRAY_TASK = taskDao.getAllByParam(task);
	}
}
