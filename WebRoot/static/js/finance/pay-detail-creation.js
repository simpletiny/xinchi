var DetailContext = function() {
	var self = this;
	self.apiurl = $("#hidden_apiurl").val();
	self.detail = ko.observable({});
	self.accounts = ko.observableArray([]);
	$.getJSON(self.apiurl + 'finance/searchAllAccounts', {}, function(data) {
		if (data.accounts) {
			self.accounts(data.accounts);
		} else {
			fail_msg("不存在账户，无法建立明细账！");
		}
	}).fail(function(reason) {
		fail_msg(reason.responseText);
	});

	self.createDetail = function() {
		if (!$("form").valid()) {
			return;
		}

		$.layer({
			area : [ 'auto', 'auto' ],
			dialog : {
				msg : '提交后无法修改，是否确认提交?',
				btns : 2,
				type : 4,
				btn : [ '确认', '取消' ],
				yes : function(index) {
					startLoadingSimpleIndicator("保存中");
					$.ajax({
						type : "POST",
						url : self.apiurl + 'finance/createDetail',
						data : $("form").serialize() + "&detail.type=支出"
					}).success(function(str) {
						if (str == "success") {
							window.location.href = self.apiurl + "templates/finance/detail.jsp";
						} else if (str == "time") {
							endLoadingIndicator();
							fail_msg("同一时间同一账户存在明细账");
						}

					});
					layer.close(index);
				}
			}
		});
	};
};

var ctx = new DetailContext();

$(document).ready(function() {
	ko.applyBindings(ctx);
});
