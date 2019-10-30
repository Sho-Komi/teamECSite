package com.internousdev.georgia.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.georgia.dao.DestinationInfoDAO;
import com.internousdev.georgia.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware{

	private List<DestinationInfoDTO> destinationInfoDTOList;
	private Map<String, Object> session;

	public String execute() {
		//sessionがタイムアウトのチェック
		if(!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";
		}
		int logined = Integer.parseInt(String.valueOf(session.get("logined")));
		if(logined != 1) {
			return "sessionTimeout";
		}

		// ログインしている場合は、ユーザーIDを取得する
		String userId = session.get("userId").toString();
		// ユーザーに紐づく
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(userId);
		return SUCCESS;
	}

	public List<DestinationInfoDTO> getDestinationInfoDTOList() {
		return destinationInfoDTOList;
	}
	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
		this.destinationInfoDTOList = destinationInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
