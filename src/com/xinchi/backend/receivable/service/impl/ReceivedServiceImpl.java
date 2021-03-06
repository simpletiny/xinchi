package com.xinchi.backend.receivable.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinchi.backend.accounting.dao.PayApprovalDAO;
import com.xinchi.backend.receivable.dao.ReceivedDAO;
import com.xinchi.backend.receivable.service.ReceivableService;
import com.xinchi.backend.receivable.service.ReceivedService;
import com.xinchi.bean.ClientReceivedDetailBean;
import com.xinchi.bean.PayApprovalBean;
import com.xinchi.common.ResourcesConstants;
import com.xinchi.tools.Page;

@Service
@Transactional
public class ReceivedServiceImpl implements ReceivedService {

	@Autowired
	private ReceivedDAO dao;

	@Autowired
	private ReceivableService receivableService;

	@Override
	public void insert(ClientReceivedDetailBean detail) {

		dao.insert(detail);
	}

	@Override
	public void insertWithPk(ClientReceivedDetailBean detail) {

		dao.insertWithPk(detail);
	}

	@Override
	public List<ClientReceivedDetailBean> getAllReceivedsByPage(Page page) {
		return dao.getAllByPage(page);
	}

	@Autowired
	private PayApprovalDAO payApprovalDao;

	@Override
	public String rollBackReceived(String received_pks) {
		String[] pks = received_pks.split(",");
		for (String pk : pks) {
			ClientReceivedDetailBean detail = dao.selectByPk(pk);
			if (detail.getType().equals(ResourcesConstants.RECEIVED_TYPE_SUM)
					|| detail.getType().equals(ResourcesConstants.RECEIVED_TYPE_STRIKE_OUT)
					|| detail.getType().equals(ResourcesConstants.RECEIVED_TYPE_STRIKE_IN)) {
				String related_pk = detail.getRelated_pk();
				List<ClientReceivedDetailBean> related_detail = dao.selectByRelatedPks(related_pk);
				if (related_detail != null && related_detail.size() > 0) {
					for (ClientReceivedDetailBean d : related_detail) {
						doRollBack(d);
					}
				}
			} else if (detail.getType().equals(ResourcesConstants.RECEIVED_TYPE_PAY)) {
				PayApprovalBean pa = payApprovalDao.selectByBackPk(detail.getRelated_pk());
				payApprovalDao.delete(pa.getPk());
				doRollBack(detail);
			} else if (detail.getType().equals(ResourcesConstants.RECEIVED_TYPE_FLY)) {
				PayApprovalBean pa = payApprovalDao.selectByBackPk(detail.getRelated_pk());
				payApprovalDao.delete(pa.getPk());
				dao.deleteByPk(detail.getPk());
			} else {
				doRollBack(detail);
			}
		}

		return "OK";
	}

	private void doRollBack(ClientReceivedDetailBean detail) {
		detail.setReceived(BigDecimal.ZERO.subtract(detail.getReceived()));
		receivableService.updateReceivableReceived(detail);
		dao.deleteByPk(detail.getPk());
	}

	@Override
	public List<ClientReceivedDetailBean> selectByRelatedPks(String related_pks) {
		return dao.selectByRelatedPks(related_pks);
	}

	@Override
	public void update(ClientReceivedDetailBean detail) {
		dao.update(detail);
	}

	@Override
	public ClientReceivedDetailBean selectByPk(String received_pk) {
		return dao.selectByPk(received_pk);
	}

	@Override
	public ClientReceivedDetailBean selectReceivedDetailByRelatedPk(String related_pk) {
		return dao.selectReceivedDetailByRelatedPk(related_pk);
	}

	@Override
	public List<ClientReceivedDetailBean> selectByParam(ClientReceivedDetailBean bean) {
		return dao.selectByParam(bean);
	}
}
