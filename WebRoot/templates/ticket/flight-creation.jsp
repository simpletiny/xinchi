<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欣驰国际</title>
<style>
#table-ticket th, #table-ticket td {
	text-align: center;
}

#table-ticket tr td input {
	width: 90%;
}

#table-ticket {
	border-collapse: separate;
	border-spacing: 0px 10px;
}

#air-ticket input[type="button"] {
	width: 30px;
	font-weight: bold;
	font-size: 20px;
}

.required th[class="r"]:after {
	content: " *";
	color: red;
	font-weight: bold;
}

tr td {
	text-overflow: ellipsis; /* for IE */
	-moz-text-overflow: ellipsis; /* for Firefox,mozilla */
	overflow: hidden;
	white-space: nowrap;
	text-align: left
}
</style>
</head>
<body>
	<div class="main-body">
		<jsp:include page="../layout.jsp" />
		<div class="subtitle">
			<h2>
				航班新建<a href="javascript:void(0)" onclick="javascript:history.go(-1);return false;" class="cancel-create"><i
					class="ic-cancel"></i>取消</a>
			</h2>
		</div>

		<div class="main-container">
			<div class="main-box">
				<form class="form-box info-form">

					<div class="input-row clearfloat">
						<div class="col-md-3 required">
							<label class="l" style="width: 25%">航班名称</label>
							<div class="ip" style="width: 50%">
								<input type="text" class="ip- date-picker" data-bind="value: flight().name" name="flight.name"
									required="required" />
							</div>
						</div>
						<div class="col-md-3 required">
							<label class="l" style="width: 25%">编号</label>
							<div class="ip" style="width: 50%">
								<input type="text" class="ip- date-picker" maxlength="10" data-bind="value: flight().number"
									name="flight.number" required="required" />
							</div>
						</div>
					</div>
					<div class="input-row clearfloat">
						<div class="col-md-3 required">
							<label class="l" style="width: 25%">成人票价</label>
							<div class="ip" style="width: 50%">
								<input type="number" class="ip- date-picker" data-bind="value: flight().adult_price" name="flight.adult_price"
									required="required" />
							</div>
						</div>
						<div class="col-md-3 required">
							<label class="l" style="width: 25%">儿童票价</label>
							<div class="ip" style="width: 50%">
								<input type="number" class="ip- date-picker" data-bind="value: flight().child_price" name="flight.child_price"
									required="required" />
							</div>
						</div>
					</div>
					<div class="input-row clearfloat" id="air-ticket">
						<div class="col-md-6">
							<table style="width: 100%" id="table-ticket">
								<thead>
									<tr class="required">
										<th class="r" style="width: 10%">航段</th>
										<th class="r" style="width: 10%">天次</th>
										<th class="r" style="width: 20%">起飞城市</th>
										<th class="r" style="width: 10%">天次</th>
										<th class="r" style="width: 20%">降落城市</th>
										<th style="width: 40%"><input type="checkbox" id="chk-designated" onclick="designated()" />指定航班</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<input type="hidden" st="flight-index" value="1" />
										<td>A<input type="hidden" st="flight-leg" value="A" /></td>
										<td><input st="start-day" type="text" maxlength="2" min="1" max="20" required="required" /></td>
										<td><input st="start-city" type="text" maxlength="10" required="required" /></td>
										<td><input st="end-day" type="text" maxlength="2" min="1" max="20" required="required" /></td>
										<td><input st="end-city" type="text" maxlength="10" required="required" /></td>
										<td><input st="flight-number" disabled="disabled" type="text" maxlength="10" /></td>
									</tr>
								</tbody>
							</table>
							<div style="margin-top: 20px; float: right">
								<input type="button" value="-" onclick="deleteRow()"></input> <input type="button" value="+" onclick="addRow()"></input>
							</div>
						</div>
					</div>
					<div class="input-row clearfloat">
						<div class="col-md-6">
							<label class="l">票务要求</label>
							<div class="ip">
								<textarea type="text" class="ip-default" rows="5" data-bind="value: flight().comment" name="flight.comment"
									placeholder="需要备注说明的信息"></textarea>
							</div>
						</div>
					</div>
					<div class="input-row clearfloat">
						<div class="col-md-6" style="text-align: right">
							<a type="submit" class="btn btn-green btn-r" data-bind="click: createFlight">保存</a>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

	<script>
		$(".ticket").addClass("current").children("ol").css("display", "block");
	</script>
	<script type="text/javascript" src="<%=basePath%>static/vendor/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/vendor/messages_zh.min.js"></script>
	<script src="<%=basePath%>static/js/validation.js"></script>
	<script src="<%=basePath%>static/js/ticket/flight-creation.js"></script>
</body>
</html>