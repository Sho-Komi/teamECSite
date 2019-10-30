<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/button.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/horizonList.css">
<link rel="stylesheet" href="./css/message.css">
<link rel="stylesheet" href="./css/title.css">
<title>決済確認</title>

</head>
<body>
	<jsp:include page="header.jsp" />
	<script src="./js/settlementConfirm.js"></script>
	<div id="contents">
		<h1>決済確認画面</h1>
        <!-- 宛先情報がリストにあれば宛先を表示 -->
		<s:if
			test="destinationInfoDTOList!=null && destinationInfoDTOList.size()>0">

			<div class="message_blue"><p>宛先情報を選択してください。</p></div>
			<s:form id="settlementConfirmForm">
				<table class="horizon_list">
						<tr>
							<th><s:label value="#" /></th>
							<th><s:label value="姓" /></th>
							<th><s:label value="名" /></th>
							<th><s:label value="ふりがな" /></th>
							<th><s:label value="住所" /></th>
							<th><s:label value="電話番号" /></th>
							<th><s:label value="メールアドレス" /></th>
						</tr>
					<tbody>
					    <!-- 宛先リストの中身を表示 -->
						<s:iterator value="destinationInfoDTOList" status="st">
							<tr>
							    <!-- 表示される最初の宛先のボタンにチェックマーク -->
								<td><s:if test="#st.index == 0">
										<input type="radio" name="id" checked="checked"
											value="<s:property value='id'/>" />
									</s:if>
									<!-- 二つ目以降のボタンはチェックなし -->
									<s:else>
										<input type="radio" name="id" value="<s:property value='id'/>" />
									</s:else></td>
								<td><s:property value="familyName" /><br></td>
								<td><s:property value="firstName" /><br></td>

								<td><s:property value="familyNameKana" /><span> </span>
								<s:property value="firstNameKana" /><br></td>
								<td><s:property value="userAddress" /></td>
								<td><s:property value="telNumber" /></td>
								<td><s:property value="email" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>

				<div id="btn_position">
					<p><s:submit value="決済" class="submit_btn"
						onClick="goSettlementCompleteAction()" /></p>
				</div>

				<div id="btn_position">
					<p><s:submit value="削除" class="submit_btn"
						onClick="goDeleteDestinationAction()" /></p>
				</div>
			</s:form>
		</s:if>

        <!-- 宛先情報がリストにないとき -->
		<s:else>
			<div class="message_blue"><p>宛先情報がありません。</p></div>
		</s:else>

		<!-- 新規宛先登録ボタンは宛先情報の有無で表示切替はないが、
	決済ボタン/削除は宛先情報の有の場合のみ表示するため、コードの可読性を考慮してformを2つにする -->
		<div id="btn_position">
			<s:form action="CreateDestinationAction">
				<p><s:submit value="新規宛先登録" class="submit_btn" /></p>
			</s:form>
		</div>
	</div>
</body>
</html>