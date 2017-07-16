var ProductContext = function() {
	var self = this;
	self.apiurl = $("#hidden_apiurl").val();
	self.product = ko.observable({});
	self.locations = [ "云南", "华东", "桂林", "张家界", "四川", "其他" ];
	self.createProduct = function() {
		if (!$("form").valid()) {
			return;
		}
		startLoadingIndicator("保存中");
		var data = $("form").serialize();
		$.ajax({
			type : "POST",
			url : self.apiurl + 'product/createProduct',
			data : data
		}).success(function(str) {
			endLoadingIndicator();
			if (str == "success") {
				window.location.href = self.apiurl + "templates/product/product.jsp";
			} else if (str == "exists") {
				fail_msg("产品库中存在同名产品！");
				endLoadingIndicator();
			}
		});

	};

};

var ctx = new ProductContext();
$(document).ready(function() {
	ko.applyBindings(ctx);
});
