var GroupContext = function() {
	var self = this;
	self.apiurl = $("#hidden_apiurl").val();

	self.logs = ko.observable({
		total : 0,
		items : []
	});
	var x = new Date();
	self.today = ko.observable();
	self.today(x.Format("yyyy-MM-dd"));
	self.users = ko.observableArray([]);
	self.user_names = ko.observableArray([]);
	$.getJSON(self.apiurl + 'user/searchAllUsers', {}, function(data) {
		self.users(data.users);
		$(self.users()).each(function(idx, data) {
			if(data.user_number!="N00000"){
				self.user_names.push(data.user_name);
			}
		});
	});

	self.refresh = function() {
		var param = $("form").serialize();
		param += "&page.start=" + self.startIndex() + "&page.count=" + self.perPage;

		$.getJSON(self.apiurl + 'user/searchUserLogs', param, function(data) {
			self.logs(data.logs);
			console.log(data);
			self.totalCount(Math.ceil(data.page.total / self.perPage));
			self.setPageNums(self.currentPage());
		});
	};

	// start pagination
	self.currentPage = ko.observable(1);
	self.perPage = 20;
	self.pageNums = ko.observableArray();
	self.totalCount = ko.observable(1);
	self.startIndex = ko.computed(function() {
		return (self.currentPage() - 1) * self.perPage;
	});

	self.resetPage = function() {
		self.currentPage(1);
	};

	self.previousPage = function() {
		if (self.currentPage() > 1) {
			self.currentPage(self.currentPage() - 1);
			self.refreshPage();
		}
	};

	self.nextPage = function() {
		if (self.currentPage() < self.pageNums().length) {
			self.currentPage(self.currentPage() + 1);
			self.refreshPage();
		}
	};

	self.turnPage = function(pageIndex) {
		self.currentPage(pageIndex);
		self.refreshPage();
	};

	self.setPageNums = function(curPage) {
		var startPage = curPage - 4 > 0 ? curPage - 4 : 1;
		var endPage = curPage + 4 <= self.totalCount() ? curPage + 4 : self.totalCount();
		var pageNums = [];
		for ( var i = startPage; i <= endPage; i++) {
			pageNums.push(i);
		}
		self.pageNums(pageNums);
	};

	self.refreshPage = function() {
		self.refresh();
	};
	// end pagination
};

var ctx = new GroupContext();
$(document).ready(function() {
	ko.applyBindings(ctx);
	ctx.refresh();
});
