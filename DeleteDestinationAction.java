package com.internousdev.georgia.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.georgia.dao.DestinationInfoDAO;
import com.internousdev.georgia.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteDestinationAction extends ActionSupport implements SessionAware{

	private String id;
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

		//ログインしていれば宛先を削除

		String result = ERROR;
		String userId = session.get("userId").toString();

		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();

		if (!destinationInfoDAO.isExistDestinationInfo(userId, id)) {
			return result;
		}

		int count = destinationInfoDAO.deleteDestination(id);
		if(count > 0) {
			//ユーザーにひもづく
			destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(session.get("userId").toString());
		}
		return SUCCESS;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
