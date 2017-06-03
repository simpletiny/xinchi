<%@ page language="java" pageEncoding="UTF-8"%>
 <%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String key = request.getParameter("key");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>欣驰国际</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>static/vendor/datetimepicker/jquery.datetimepicker.css"/>
    <style>
    	.delete {
    	position:relative;
    	display:block;
	width: 12px;
	height: 12px;
	background:url(../../static/img/mc-icon-cancel.png) no-repeat;
    background-size:cover;
    margin-right:25px;
   	padding-top:2px;
	z-index: 999;
	float:right;
	cursor: pointer;
}
    </style>
</head>
<body>
<div class="main-body">
<jsp:include page="../layout.jsp" />
    <div class="subtitle">
        <h2>支付<a  href="javascript:void(0)" onclick="javascript:history.go(-1);return false;" class="cancel-create"><i class="ic-cancel"></i>取消</a></h2>
    </div>

    <div class="main-container">
        <div class="main-box">
        	<div class="form-box info-form">
					<h3>支付信息</h3>
					<div class="input-row clearfloat">

						<div class="col-md-6">
							<label class="l">支付单号</label>
							<div class="ip">
								<p class="ip-default" data-bind="text: wfp().pay_number"></p>
							</div>
						</div>
						<div class="col-md-6">
							<label class="l">项目</label>
							<div class="ip">
								<p class="ip-default" data-bind="text: itemMapping[wfp().item]"></p>
							</div>
						</div>
						<div class="col-md-6">
							<label class="l">收款方</label>
							<div class="ip">
								<p class="ip-default" data-bind="text: wfp().receiver"></p>
							</div>
						</div>
						<div class="col-md-6">
							<label class="l">总金额</label>
							<div class="ip">
								<p class="ip-default" data-bind="text: wfp().money"></p>
							</div>
						</div>
					</div>
			</div>
            <form class="form-box info-form">
             <input type="hidden" id="wfp_pk" value="<%=key%>" />
             <div>
                <div class="input-row clearfloat">

                    <div class="col-md-6 required">
                        <label class="l">支出账户</label>
                        <div class="ip"><select class="form-control" name="account1" data-bind="options: accounts, optionsCaption: '-- 请选择 --'" required="required"></select></div>
                    </div>
                     <div class="col-md-6 required">
                        <label class="l">支出时间</label>
                        <div class="ip"><input type="text" name="time1" class="ip- datetime-picker" name="xx" placeholder="支出时间" required="required"/></div>
                    </div>
                </div>
                 <div class="input-row clearfloat">
            	
                    <div class="col-md-6 required">
                        <label class="l">收款方账户名</label>
                        <div class="ip"><input type="text" name="receiver1" class="ip-"  placeholder="收款方账户名" required="required"/></div>
                    </div>
                     <div class="col-md-6 required">
                        <label class="l">支付金额</label>
                        <div class="ip"><input type="number" data-bind="value: defaultMoney()" name="money1" class="ip-"  placeholder="支付金额"  required="required"/></div>
                    </div>
                </div>
                <hr/>
               </div>
                 <div align="right" id="div_add"><a type="submit" class="btn btn-green btn-r" data-bind="click: add">添加</a></div>
            </form>
			
            <div align="right"><a type="submit" class="btn btn-green btn-r" data-bind="click: finish">支付完成</a></div>
        </div>
    </div>
  </div>
  
  <div id="div_mod" style="display:none">
  		<div>
  				<div class="delete" title="删除" onclick="remove(this)"></div>
                	<div class="input-row clearfloat">
                	
                    <div class="col-md-6 required">
                        <label class="l">支出账户</label>
                        <div class="ip"><select class="form-control" name="account"  data-bind="options: accounts, optionsCaption: '-- 请选择 --'" required="required"></select></div>
                    </div>
                     <div class="col-md-6 required">
                        <label class="l">支出时间</label>
                        <div class="ip"><input type="text" name="time" class="ip- datetime-picker" placeholder="支出时间" required="required"/></div>
                    </div>
                </div>
                 <div class="input-row clearfloat">
            
                    <div class="col-md-6 required">
                        <label class="l">收款方账户名</label>
                        <div class="ip"><input type="text" name="receiver" class="ip-"  placeholder="收款方账户名" required="required"/></div>
                    </div>
                     <div class="col-md-6 required">
                        <label class="l">支付金额</label>
                        <div class="ip"><input type="number" name="money" class="ip-"  placeholder="支付金额"  required="required"/></div>
                    </div>
                </div>
                	<hr/>
                	</div>
                	</div>
      <script src="<%=basePath %>static/vendor/datetimepicker/jquery.datetimepicker.js"></script>
  <script>
    $(".finance").addClass("current").children("ol").css("display", "block");
  </script>
    <script type="text/javascript" src="<%=basePath %>static/vendor/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>static/vendor/messages_zh.min.js"></script>
    <script src="<%=basePath %>static/js/validation.js"></script>
    <script src="<%=basePath %>static/js/datepicker.js"></script>
  <script src="<%=basePath %>static/js/accounting/paid.js"></script>
</body>
</html>