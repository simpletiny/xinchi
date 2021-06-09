<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String key = request.getParameter("key");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欣驰国际</title>
<style>
.title {
	margin-left: 20px;
}
</style>

</head>
<body>
	<div class="main-body">
		<input type="hidden" id="supplier_key" value="<%=key%>">
		<jsp:include page="../layout.jsp" />
		<div class="subtitle">
			<h2>财务汇总</h2>
		</div>

		<div class="main-container">
			<div class="main-box">
				<hr>
				<div class="form-box info-form">
					<h3 class="title">总计</h3>
					<div class="input-row clearfloat">

						<div class="col-md-6">
							<label class="l">应收款</label>
							<div class="ip">
								<p class="ip-default rmb" data-bind="text: summary().receivable"></p>
							</div>
						</div>
						<div class="col-md-6">
							<label class="l">应付款</label>
							<div class="ip">
								<p class="ip-default rmb" data-bind="text: summary().payable"></p>
							</div>
						</div>
					</div>
					<div class="input-row clearfloat">
						<div class="col-md-6">
							<label class="l">现金</label>
							<div class="ip">
								<p class="ip-default rmb" data-bind="text: summary().cash"></p>
							</div>
						</div>
					</div>
					<hr />
					<h3 class="title">应收明细</h3>
					<div class="col-md-12">
						<div data-bind="foreach: summary().areaReceivable" class="col-md-6">
							<div class="input-row clearfloat">
								<div class="col-md-6">
									<label class="l">地区</label>
									<div class="ip" style="width: 50%">
										<p class="ip- rmb" data-bind="text:$data.key_key "></p>
									</div>
								</div>
								<div class="col-md-6">
									<label class="l">应收款</label>
									<div class="ip" style="width: 50%">
										<p class="ip- rmb" data-bind="text:$data.value_value "></p>
									</div>
								</div>
							</div>
						</div>
						<div data-bind="foreach: summary().salesReceivable" class="col-md-6">
							<div class="input-row clearfloat">
								<div class="col-md-6">
									<label class="l">销售</label>
									<div class="ip" style="width: 50%">
										<p class="ip- rmb" data-bind="text:$data.key_key "></p>
									</div>
								</div>
								<div class="col-md-6">
									<label class="l">应收款</label>
									<div class="ip" style="width: 50%">
										<p class="ip- rmb" data-bind="text:$data.value_value "></p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<hr />
					<h3 class="title">应付明细（不含票务）</h3>
					<div data-bind="foreach: summary().areaPayable">

						<div class="input-row clearfloat">
							<div class="col-md-3">
								<label class="l">省份</label>
								<div class="ip" style="width: 50%">
									<p class="ip- rmb" data-bind="text:$data.key_key "></p>
								</div>
							</div>
							<div class="col-md-3">
								<label class="l">应付款</label>
								<div class="ip" style="width: 50%">
									<p class="ip- rmb" data-bind="text:$data.value_value "></p>
								</div>
							</div>
						</div>

					</div>
					<hr />
					<h3 class="title">现金明细</h3>
					<div class="input-row clearfloat">
						<div class="col-md-6">
							<label class="l">现金</label>
							<div class="ip">
								<p class="ip-" data-bind="text: summary().positive_cash"></p>
							</div>
						</div>
						<div class="col-md-6">
							<label class="l">透支</label>
							<div class="ip">
								<p class="ip-" data-bind="text:summary().negative_cash "></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".data").addClass("current").children("ol").css("display", "block");
	</script>
	<script src="<%=basePath%>static/js/data/finance-summary.js"></script>
</body>
</html>