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
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/vendor/datetimepicker/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/vendor/datetimepicker/MonthPicker.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/jquery-ui.css" />
</head>
<body>
	<div class="main-body">
		<jsp:include page="../layout.jsp" />
		<div class="subtitle">
			<h2>
				费用报销申请<a href="javascript:void(0)" onclick="javascript:history.go(-1);return false;" class="cancel-create"><i
					class="ic-cancel"></i>取消</a>
			</h2>
		</div>

		<div class="main-container">
			<div class="main-box">
				<form class="form-box info-form">
					<div class="input-row clearfloat">

						<div class="col-md-6 required">
							<label class="l">项目</label>
							<div class="ip">
								<select class="form-control" data-bind=" options:itemMapping,optionsText: 'value',optionsValue:'key'"
									name="reimbursement.item" required="required"></select>
							</div>
						</div>
						<div class="col-md-6 required">
							<label class="l">归属月份</label>
							<div class="ip">
								<input type="text" class="form-control month-picker-st" placeholder="归属月份" data-bind="value:reibursement().month" name="reimbursement.month" required="required" />
							</div>
						</div>

					</div>
					<div class="input-row clearfloat">
						<div class="col-md-6 required">
							<label class="l">金额</label>
							<div class="ip">
								<input type="number" class="ip-default" placeholder="金额" name="reimbursement.money" required="required" />
							</div>
						</div>
						<div class="col-md-6 required">
							<label class="l">填报日期</label>
							<div class="ip">
								<input type="text" class="ip-default date-picker" data-bind="value:reibursement().date" placeholder="2013-10-19" name="reimbursement.date"
									required="required" />
							</div>
						</div>
					</div>
					<div class="input-row clearfloat">
						<div class="col-md-12">
							<label class="l">备注</label>
							<div class="ip">
								<textarea type="text" class="ip-default" rows="15" maxlength="200" name="reimbursement.comment" placeholder="备注"></textarea>
							</div>
						</div>
					</div>
				</form>
				<div align="right">
					<a type="submit" class="btn btn-green btn-r" id="btn_save" data-bind="click: save">提交</a>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".client").addClass("current").children("ol").css("display", "block");
	</script>
	<script type="text/javascript" src="<%=basePath%>static/vendor/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/vendor/messages_zh.min.js"></script>
	<script src="<%=basePath%>static/js/validation.js"></script>
		<script src="<%=basePath%>static/vendor/jquery-ui.min.js"></script>
	<script src="<%=basePath%>static/vendor/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=basePath%>static/vendor/datetimepicker/MonthPicker.min.js"></script>
	<script src="<%=basePath%>static/js/datepicker.js"></script>
	<script src="<%=basePath%>static/js/accounting/reimbursement-creation.js"></script>
</body>
</html>