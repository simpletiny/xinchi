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
</head>
<body>
<div class="main-body">
<jsp:include page="../layout.jsp" />
    <div class="subtitle">
        <h2>公司编辑<a href="<%=basePath %>/templates/supplier/supplier.jsp" class="cancel-create"><i class="ic-cancel"></i>取消</a></h2>
    </div>

    <div class="main-container">
        <div class="main-box">
            <form class="form-box info-form">
             <input type="hidden" id="supplier_key" name = "supplier.pk" value="<%=key%>">
                <div class="input-row clearfloat">
                    <div class="col-md-6 required">
                        <label class="l">公司名称</label>
                        <div class="ip"><input type="text" id="name" class="ip-default" data-bind="value: supplier().supplier_name" placeholder="公司名称" name="supplier.supplier_name" required="required"/></div>
                    </div>
                    <div class="col-md-6 required">
                        <label class="l">公司简称</label>
                        <div class="ip"><input type="text" id="name" class="ip-default" data-bind="value: supplier().supplier_short_name" placeholder="公司简称" name="supplier.supplier_short_name" required="required"/></div>
                    </div>
                </div>
                <div class="input-row clearfloat">
                    <div class="col-md-6">
                        <label class="l">电话</label>
                        <div class="ip"><input type="text" class="ip-" data-bind="value: supplier().telephone" placeholder="电话" name="supplier.telephone"/></div>
                    </div>
                    <div class="col-md-6 ">
                        <label class="l">传真</label>
                        <div class="ip"><input type="number" min="0" class="ip-" data-bind="value: supplier().fax" placeholder="传真" name="supplier.fax" /></div>
                    </div>
                </div>
                <div class="input-row clearfloat">
                     <div class="col-md-6 required">
                        <label class="l">地区</label>
                        <div class="ip"><select class="form-control" data-bind="options: supplierArea, optionsCaption: '-- 请选择 --', value: supplier().supplier_area" name="supplier.supplier_area" required="required"></select></div>
                    </div>
                    <div class="col-md-6 required">
                        <label class="l">公司类型</label>
                        <div class="ip"><select class="form-control" data-bind="options: supplierType, optionsCaption: '-- 请选择 --', value: supplier().supplier_type" name="supplier.supplier_type" required="required"></select></div>
                    </div>
                </div>
                 <div class="input-row clearfloat">
                    <div class="col-md-12">
                        <label class="l">地址</label>
                        <div class="ip"><input type="text" class="ip-" data-bind="value: supplier().address" placeholder="地址" name="supplier.address"/></textarea></div>
                    </div>
                </div>
                <hr noshade color="#0066cc">
                 <h3>财务主体</h3>
                <div class="input-row clearfloat">
                    <div class="col-md-6 required">
                        <label class="l">姓名</label>
                        <div class="ip"><input type="text" class="ip- date-picker" data-bind="value: supplier().body_name" placeholder="财务主体姓名" name="supplier.body_name" required="required"/></div>
                    </div>
                    <div class="col-md-6 required">
                        <label class="l">性别</label>
                        <div class="ip"><select class="form-control" data-bind="options: genders,value: supplier().body_sex" name="supplier.body_sex" required="required"></select></div>
                    </div>
                </div>
                <div class="input-row clearfloat">
                    <div class="col-md-6">
                        <label class="l">身份证号</label>
                        <div class="ip"><input type="text" class="ip-" data-bind="value: supplier().body_id" placeholder="身份证号" name="supplier.body_id"/></div>
                    </div>
                    <div class="col-md-6">
                        <label class="l">微信</label>
                        <div class="ip"><input type="text" class="ip-" data-bind="value: supplier().body_wechat" placeholder="财务主体微信" name="supplier.body_wechat"/></div>
                    </div>
                </div>
                <div class="input-row clearfloat">
                    <div class="col-md-6 required">
                        <label class="l">手机号</label>
                        <div class="ip"><input type="text" class="ip-" data-bind="value: supplier().body_cellphone" placeholder="财务主体手机号" name="supplier.body_cellphone" required="required"/></div>
                    </div>
                    <div class="col-md-6">
                        <label class="l">出生年</label>
                        <div class="ip"><input type="text" class="ip-" data-bind="value: supplier().body_birth_year" placeholder="出生年" name="supplier.body_birth_year" /></div>
                    </div>
                </div>
                <div class="input-row clearfloat">
                    <div class="col-md-12">
                        <label class="l">备注</label>
                        <div class="ip"><textarea type="text" class="ip-default" rows="15" data-bind="value: supplier().comment" name ="supplier.comment" placeholder="需要备注说明的信息"></textarea></div>
                    </div>
                </div>
            </form>

            <div align="right"><a type="submit" class="btn btn-green btn-r" data-bind="click: saveCompany">保存</a></div>
        </div>
    </div>
  </div>
  <script>
    $(".supplier").addClass("current").children("ol").css("display", "block");
  </script>
    <script type="text/javascript" src="<%=basePath %>static/vendor/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>static/vendor/messages_zh.min.js"></script>
    <script src="<%=basePath %>static/js/validation.js"></script>
  <script src="<%=basePath %>static/js/supplier/supplier-edit.js"></script>
</body>
</html>