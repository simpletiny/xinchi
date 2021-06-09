package com.xinchi.backend.ticket.service;

import java.util.List;

import com.xinchi.bean.SeasonTicketBaseBean;
import com.xinchi.common.BaseService;
import com.xinchi.tools.Page;

public interface SeasonTicketService extends BaseService {

	public String createSeasonTicket(String json, SeasonTicketBaseBean base);

	public List<SeasonTicketBaseBean> selectByPage(Page<SeasonTicketBaseBean> page);

	public SeasonTicketBaseBean searchByBasePk(String base_pk);

	public String deleteSeasonTicket(String base_pk);

	public String updateSeasonTicket(String json, SeasonTicketBaseBean base);

}