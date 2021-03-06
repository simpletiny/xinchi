var clientEmployeeLayer;
var ClientContext = function() {
	var self = this;
	self.apiurl = $("#hidden_apiurl").val();
	self.visit = ko.observable({});
	self.clientEmployees = ko.observable({});
	self.createVisit = function() {
		window.location.href = self.apiurl + "templates/client/visit-creation.jsp";

	};
	var key = $("#client_key").val();
	var arr = key.split(";");
	var client_employee_pk = arr[0];
	var client_employee_name = arr[1];
	self.visit().client_employee_pk = ko.observable(client_employee_pk);
	self.visit().client_employee_name = ko.observable(client_employee_name);
	self.target = [ '开拓客户', '关系破局', '升级关系', '产品宣推', '催讨尾款', '登门善后' ];
	self.chosenSubTargets = ko.observableArray([]);
	
//	self.targetMapping = {
//		'1' : '开拓客户',
//		'2' : '关系破局',
//		'3' : '升级关系',
//		'4' : '产品宣推',
//		'5' : '催讨尾款',
//		'6' : '登门善后',
//	};

	var x = new Date();
	self.visit().date = ko.observable();
	self.visit().date(x.Format("yyyy-MM-dd"));
	
	var minDate= x.addDate(-2).Format("yyyy/MM/dd");
	$('.date-picker').datetimepicker({
		maxDate:0,
		minDate:minDate
	});
	
	self.saveVisit = function() {
		console.log($("form").serialize() + "&visit.type=VISIT&visit.sub_target="+self.chosenSubTargets())
		if (!$("form").valid()) {
			return;
		}
		// if (self.visit().summary.length < 21) {
		// fail_msg("总结不得少于20个字");
		// return;
		// }

		$.ajax({
			type : "POST",
			url : self.apiurl + 'client/createVisit',
			data : $("form").serialize() + "&visit.type=VISIT&visit.sub_target="+self.chosenSubTargets()
		}).success(function(str) {
			if (str == "OK") {
				window.location.href = self.apiurl + "templates/client/client-relation.jsp";
			}
		});

	};
	// self.calTime = function() {
	// var from_time = $("#from_time").val();
	// var end_time = $("#end_time").val();
	// $("#from_time").val(from_time.trim());
	// $("#end_time").val(end_time.trim());
	// if (from_time.trim() == "" || end_time.trim() == "" ||
	// from_time.indexOf(":") < 0 || end_time.indexOf(":") < 0 ||
	// end_time.trim().length != 5 || from_time.trim().length != 5) {
	// $("#sum_time").text("0分钟");
	// return;
	// }
	//
	// var from = from_time.split(":");
	// var end = end_time.split(":");
	//
	// var time = (end[0] - from[0]) * 60 + (end[1] - from[1]);
	// $("#sum_time").text(time + "分钟");
	// };
	// self.refreshClient = function() {
	// var param = "employee.name=" + $("#client_name").val();
	// param += "&page.start=" + self.startIndex() + "&page.count=" +
	// self.perPage;
	// $.getJSON(self.apiurl + 'client/searchEmployeeByPage', param,
	// function(data) {
	// self.clientEmployees(data.employees);
	//
	// self.totalCount(Math.ceil(data.page.total / self.perPage));
	// self.setPageNums(self.currentPage());
	// });
	// };
	//
	// self.searchClientEmployee = function() {
	// self.refreshClient();
	// };
	//
	// self.pickClientEmployee = function(name, pk) {
	// $("#txt-client-employee-name").val(name);
	// $("#txt-client-employee-pk").val(pk);
	// layer.close(clientEmployeeLayer);
	// };
	//
	// self.choseClientEmployee = function() {
	// clientEmployeeLayer = $.layer({
	// type : 1,
	// title : [ '选择客户操作', '' ],
	// maxmin : false,
	// closeBtn : [ 1, true ],
	// shadeClose : false,
	// area : [ '600px', '650px' ],
	// offset : [ '50px', '' ],
	// scrollbar : true,
	// page : {
	// dom : '#client-pick'
	// },
	// end : function() {
	// console.log("Done");
	// }
	// });
	// };
	//
	// // start pagination
	// self.currentPage = ko.observable(1);
	// self.perPage = 10;
	// self.pageNums = ko.observableArray();
	// self.totalCount = ko.observable(1);
	// self.startIndex = ko.computed(function() {
	// return (self.currentPage() - 1) * self.perPage;
	// });
	//
	// self.resetPage = function() {
	// self.currentPage(1);
	// };
	//
	// self.previousPage = function() {
	// if (self.currentPage() > 1) {
	// self.currentPage(self.currentPage() - 1);
	// self.refreshPage();
	// }
	// };
	//
	// self.nextPage = function() {
	// if (self.currentPage() < self.pageNums().length) {
	// self.currentPage(self.currentPage() + 1);
	// self.refreshPage();
	// }
	// };
	//
	// self.turnPage = function(pageIndex) {
	// self.currentPage(pageIndex);
	// self.refreshPage();
	// };
	//
	// self.setPageNums = function(curPage) {
	// var startPage = curPage - 4 > 0 ? curPage - 4 : 1;
	// var endPage = curPage + 4 <= self.totalCount() ? curPage + 4 :
	// self.totalCount();
	// var pageNums = [];
	// for ( var i = startPage; i <= endPage; i++) {
	// pageNums.push(i);
	// }
	// self.pageNums(pageNums);
	// };
	//
	// self.refreshPage = function() {
	// self.searchClientEmployee();
	// };
	// // end pagination
};

var ctx = new ClientContext();

$(document).ready(function() {
	ko.applyBindings(ctx);
});