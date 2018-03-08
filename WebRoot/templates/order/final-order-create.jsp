<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String key = request.getParameter("key");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欣驰国际</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/vendor/datetimepicker/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/js/order/upload.css" />

<style>
#name-table tr th,td {
	text-align: center
}

.fix-width {
	width: 40% !important;
}
</style>
</head>
<body>
	<div class="main-body">
		<jsp:include page="../layout.jsp" />
		<div class="subtitle">
			<h2>
				订单决算</span><a href="javascript:void(0)" onclick="javascript:history.go(-1);return false;" class="cancel-create"><i class="ic-cancel"></i>取消</a>
			</h2>
		</div>

		<div class="main-container">
			<div class="main-box">
				<form class="form-box info-form" id="form_container">
					<div class="input-row clearfloat" data-bind="foreach: types">
						<em class="small-box "> <input type="radio" data-bind="attr:{'value':$data.value},checked:$root.chosenType" onclick="changeType(this)" name="order.final_type" /><label
							data-bind="text:$data.name"></label>
						</em>
					</div>
					<input type="hidden" id="key" value="<%=key%>" name="order.pk"></input>
					<div class="input-row clearfloat">
						<div class="col-md-4">
							<label class="l">客户</label>
							<div class="ip fix-width">
								<p class="ip-default" data-bind="text:order().client_employee_name"></p>
							</div>
						</div>
						<div class="col-md-4">
							<label class="l">财务主体</label>
							<div class="ip fix-width">
								<p class="ip-default" data-bind="text:employee().financial_body_name"></p>
							</div>
						</div>
						<div class="col-md-4">
							<label class="l">产品名称</label>
							<div class="ip fix-width">
								<p class="ip-default" data-bind="text:order().product_name"></p>
							</div>
						</div>
					</div>
					<div class="input-row clearfloat">
						<div class="col-md-4">
							<label class="l">出团日期</label>
							<div class="ip fix-width">
								<p class="ip-default" data-bind="text:order().departure_date"></p>
							</div>
						</div>
						<div class="col-md-4">
							<label class="l">预算团款</label>
							<div class="ip fix-width">
								<p class="ip-default" data-bind="text:order().receivable"></p>
							</div>
						</div>
						<div class="col-md-4">
							<label class="l">决算团款</label>
							<div class="ip fix-width">
								<p class="ip-default" data-bind="text:order().receivable" id="p-final-receivable"></p>
								<input type="hidden" name="order.receivable" data-bind="value:order().receivable" id="txt-final-receivable" />
							</div>
						</div>
					</div>
					<hr />
					<div style="display: none" id="div-2">
						<div class="input-row clearfloat">
							<div class="col-md-4 required">
								<label class="l">增加款项</label>
								<div class="ip">
									<input type="number" id="txt-raise-money" required="required" min=0 onkeyup="inputRaise(this)" name="order.raise_money" class="ip-" placeholder="增加款项" value="0" />
								</div>
							</div>
							<div class="col-md-4 required">
								<label class="l">减少款项</label>
								<div class="ip">
									<input type="number" id="txt-reduce-money" required="required" min=0 onkeyup="inputReduce(this)" name="order.reduce_money" class="ip-" placeholder="减少款项" value="0" />
								</div>
							</div>
							<div class="col-md-4 required type-3">
								<label class="l">投诉扣款</label>
								<div class="ip">
									<input type="number" required="required" id="txt-complain-money" min=0 onkeyup="inputComplain(this)" name="order.complain_money" class="ip-" placeholder="增加款项" value="0" />
								</div>
							</div>
						</div>
						<div class="input-row clearfloat">
							<div class="col-md-4" id="l-raise">
								<label class="l">增加款项说明</label>
								<div class="ip">
									<textarea id="txt-raise" type="text" class="ip-default" rows="10" maxlength="200" placeholder="增加款项说明" name="order.raise_comment"></textarea>
								</div>
							</div>
							<div class="col-md-4" id="l-reduce">
								<label class="l">减少款项说明</label>
								<div class="ip">
									<textarea id="txt-reduce" type="text" class="ip-default" rows="10" maxlength="200" name="order.reduce_comment" placeholder="减少款项说明"></textarea>
								</div>
							</div>
							<div class="col-md-4 type-3" id="l-complain-reason">
								<label class="l">投诉原因</label>
								<div class="ip">
									<textarea id="txt-complain-reason" type="text" class="ip-default" rows="10" maxlength="200" placeholder="投诉原因" name="order.complain_reason"></textarea>
								</div>
							</div>
						</div>
						<div class="input-row clearfloat type-3">
							<div class="col-md-4"></div>
							<div class="col-md-4"></div>
							<div class="col-md-4" id="l-complain-solution">
								<label class="l">解决方案</label>
								<div class="ip">
									<textarea id="txt-complain-solution" type="text" class="ip-default" rows="10" maxlength="200" name="order.complain_solution" placeholder="解决方案"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="input-row clearfloat">
						<div class="col-md-6">
							<a href="javascript:;" class="a-upload">上传决算单<input type="file" name="file" /></a> <input type="hidden" data-bind="value:order().confirm_file" name="order.confirm_file" />
						</div>
						<div class="col-md-6"></div>
					</div>
					<div class="input-row clearfloat"></div>
					<div class="input-row clearfloat">
						<div class="col-md-6">
							<a href="javascript:;" class="a-upload">上传凭证<input type="file" name="file" /></a> <input type="hidden" data-bind="value:order().voucher_file" name="order.voucher_file" />
						</div>
						<div class="col-md-6"></div>
					</div>
					<div class="input-row clearfloat"></div>
				</form>

				<div align="right">
					<a type="submit" class="btn btn-green btn-r" data-bind="click: finalOrder">申请决算</a>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".order-box").addClass("current").children("ol").css("display", "block");
	</script>
	<script type="text/javascript" src="<%=basePath%>static/vendor/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/vendor/messages_zh.min.js"></script>
	<script src="<%=basePath%>static/js/validation.js"></script>
	<script src="<%=basePath%>static/vendor/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=basePath%>static/js/datepicker.js"></script>
	<script src="<%=basePath%>static/js/order/confirm-upload.js"></script>
	<script src="<%=basePath%>static/js/order/final-order-create.js"></script>
</body>
</html>