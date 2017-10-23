var sourceLayer;
var PassengerContext = function() {
	var self = this;
	self.apiurl = $("#hidden_apiurl").val();
	self.chosenPassengers = ko.observableArray([]);

	self.passengers = ko.observable({
		total : 0,
		items : []
	});
	self.allRoles = [ 'ADMIN', 'MANAGER', 'SALES', 'PRODUCT', 'FINANCE', 'TICKET' ];

	self.roleMapping = {
		'MANAGER' : '经理',
		'ADMIN' : '管理员',
		'SALES' : '销售人员',
		'PRODUCT' : '产品',
		'FINANCE' : '财务',
		'TICKET' : '票务'
	};
	self.existsSources = ko.observableArray([]);
	self.operate = function() {
		if (self.chosenPassengers().length < 1) {
			fail_msg("请选择乘客！");
			return;
		} else {
			var sources = $(".txt-ticket-source");
			self.existsSources.removeAll();
			// 先填写票源
			if (sources.length > 0) {
				for ( var i = 0; i < sources.length; i++) {
					var ss = $(sources[i]).val().trim();
					$(sources[i]).val(ss);
					if (ss == "") {
						fail_msg("请先填写第" + (i + 1) + "个票源");
						return;
					}
					var obj = new Object();
					obj.name = ss;
					obj.index = i;
					self.existsSources.push(obj);
				}
				sourceLayer = $.layer({
					type : 1,
					title : [ '选择票源', '' ],
					maxmin : false,
					closeBtn : [ 1, true ],
					shadeClose : false,
					area : [ '400px', '150px' ],
					offset : [ '', '' ],
					scrollbar : true,
					page : {
						dom : '#source-pick'
					},
					end : function() {
					}
				});
			} else {
				if ($("#div-table").hasClass("already")) {
					self.addTicketSource();
				} else {
					$("#div-table").animate({
						width : '70%'
					}, "slow", function() {
						$(this).css("float", "left");
						$(this).addClass("already");
						$(".right-div").css("display", "block");
						self.addTicketSource();
					});
				}
			}
		}
	};
	// 选择票源
	self.pickTicketSource = function() {
		var source = $("#select-ticket-source").val();
		if (source == "") {
			self.addTicketSource();
		} else {
			var sources = $(".name-box");
			var target = sources[source];

			for ( var i = 0; i < self.chosenPassengers().length; i++) {
				var data = self.chosenPassengers()[i].split(":");
				var padiv = $('<em class="small-box"></em>');
				var label = $('<a href="#" style="margin-left:5px;cursor:pointer" class="passenger-name"></a>');
				var hiddenPk = $('<input type="hidden" class="passenger-pk"></input>');
				$(hiddenPk).val(data[0]);
				$(label).text(data[1]);
				$(label).click(function() {
					self.addDeletePassenger(this);
				});
				$(padiv).append(label);
				$(padiv).append(hiddenPk);
				$(target).append(padiv);
			}
			self.chosenPassengers.removeAll();
		}
		layer.close(sourceLayer);
	};
	// 添加票源
	self.addTicketSource = function() {
		var sourceDiv = $('<div style="border: solid 1px black;width:100%;margin-top:10px" class="source-div"></div>');
		var deleteDiv = $('<div class="deleteDiv"></div>');
		$(deleteDiv).click(function() {
			self.deleteSource(this);
		});
		var passengerDiv = $('<div class="input-row clearfloat" style="padding: 20px 10px 0 0px"></div>');
		var passengerBox = $('<div class="col-md-12"></div>');
		var passengerBoxChild = $('<div class="ip" style="width: 80%"></div>');
		var passengerBoxGrandson = $('<div style="padding-top: 4px;" class="name-box"></div>');

		$(passengerBox).append('<label class="l" style="width: 20%">名单</label>');

		for ( var i = 0; i < self.chosenPassengers().length; i++) {
			var data = self.chosenPassengers()[i].split(":");
			var padiv = $('<em class="small-box"></em>');
			var label = $('<a href="#" style="margin-left:5px;cursor:pointer" class="passenger-name"></a>');
			var hiddenPk = $('<input type="hidden" class="passenger-pk"></input>');
			$(hiddenPk).val(data[0]);
			$(label).text(data[1]);
			$(label).click(function() {
				self.addDeletePassenger(this);
			});
			$(padiv).append(label);
			$(padiv).append(hiddenPk);
			$(passengerBoxGrandson).append(padiv);
		}
		$(passengerBoxChild).append(passengerBoxGrandson);
		$(passengerBox).append(passengerBoxChild);
		$(passengerDiv).append(passengerBox);
		$(sourceDiv).append(deleteDiv);
		$(sourceDiv).append(
				'<div class="input-row clearfloat" style="padding: 20px 10px 0 0px">' + '<div class="col-md-12">' + '<label class="l" style="width: 20%">票源</label>'
						+ '<div class="ip" style="width: 80%">'
						+ '<input  st="supplier-name" type="text" onclick="choseSupplierEmployee(event)"  class="ip- txt-ticket-source" placeholder="票源" maxlength="20"'
						+ ' /><input type="text" st="supplier-pk" style="display: none" />' + '</div>' + '</div>');

		$(sourceDiv).append(passengerDiv);
		$(".right-div").append(sourceDiv);
		self.chosenPassengers.removeAll();
	};
	self.deleteSource = function(div) {
		$(div).parent().remove();
	};

	self.addDeletePassenger = function(label) {
		$('.deletePassenger').remove();
		var X = $(label).offset().top;
		var Y = $(label).offset().left;
		var div = $('<div></div>');
		var a_name = $('<a href="#" style="cursor:pointer">删除</a>');
		$(div).append(a_name);

		$(div).addClass("deletePassenger");
		$(div).css({
			'top' : X + 20,
			'left' : Y
		});
		$('body').append(div);
		$(a_name).click({
			label : label
		}, function(event) {
			self.deleteRightPassenger(event.data.label);
		});
	};

	self.deleteRightPassenger = function(label) {
		$(label).parent().remove();
	};
	self.refresh = function() {
		var param = $("form").serialize();
		param += "&page.start=" + self.startIndex() + "&page.count=" + self.perPage;
		$.getJSON(self.apiurl + 'ticket/searchAirTicketNameListByPage', param, function(data) {
			self.passengers(data.airTicketNameList);

			self.totalCount(Math.ceil(data.page.total / self.perPage));
			self.setPageNums(self.currentPage());
		});
	};

	// 完成选择票源
	self.finishChosen = function() {
		var sources = $(".txt-ticket-source");
		if (sources.length > 0) {
			for ( var i = 0; i < sources.length; i++) {
				var ss = $(sources[i]).val().trim();
				$(sources[i]).val(ss);
				if (ss == "") {
					fail_msg("请先填写第" + (i + 1) + "个票源");
					return;
				}
			}
			var json = '[';
			var all = $('.source-div');
			for ( var i = 0; i < all.length; i++) {
				var current = all[i];
				var sourceName = $(current).find("input.txt-ticket-source").val();
				var sourcePk = $(current).find("[st='supplier-pk']").val();
				var pks = $(current).find("input.passenger-pk");
				var passengerPks = "";
				for ( var j = 0; j < pks.length; j++) {
					passengerPks += $(pks[j]).val() + ",";
				}
				passengerPks = passengerPks.substr(0, passengerPks.length - 1);
				json += '{"sourceName":"' + sourceName + '","sourcePk":"' + sourcePk + '","passengerPks":"' + passengerPks + '"},';
			}
			json = json.substr(0, json.length - 1);
			json += ']';
			$("#json-data").val(json);

			$("#data-form").submit();
		} else {
			fail_msg("什么也没有！");
			retrurn;
		}

	};

	self.search = function() {

	};

	self.resetPage = function() {

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

	// 供应商选择
	self.supplierEmployees = ko.observable({});
	self.refreshSupplier = function() {
		var param = "employee.type=A&employee.name=" + $("#supplier_name").val();
		param += "&page.start=" + self.startIndex1() + "&page.count=" + self.perPage1;
		$.getJSON(self.apiurl + 'supplier/searchEmployeeByPage', param, function(data) {
			self.supplierEmployees(data.employees);

			self.totalCount1(Math.ceil(data.page.total / self.perPage1));
			self.setPageNums1(self.currentPage1());
		});
	};

	self.searchSupplierEmployee = function() {
		self.refreshSupplier();
	};
	self.pickSupplierEmployee = function(name, pk) {
		$(currentSupplier).val(name);
		$(currentSupplier).next().val(pk);
		layer.close(supplierEmployeeLayer);
	};

	// start pagination
	self.currentPage1 = ko.observable(1);
	self.perPage1 = 10;
	self.pageNums1 = ko.observableArray();
	self.totalCount1 = ko.observable(1);
	self.startIndex1 = ko.computed(function() {
		return (self.currentPage1() - 1) * self.perPage1;
	});

	self.resetPage1 = function() {
		self.currentPage1(1);
	};

	self.previousPage1 = function() {
		if (self.currentPage1() > 1) {
			self.currentPage1(self.currentPage1() - 1);
			self.refreshPage1();
		}
	};

	self.nextPage1 = function() {
		if (self.currentPage1() < self.pageNums1().length) {
			self.currentPage1(self.currentPage1() + 1);
			self.refreshPage1();
		}
	};

	self.turnPage1 = function(pageIndex) {
		self.currentPage1(pageIndex);
		self.refreshPage1();
	};

	self.setPageNums1 = function(curPage) {
		var startPage1 = curPage - 4 > 0 ? curPage - 4 : 1;
		var endPage1 = curPage + 4 <= self.totalCount1() ? curPage + 4 : self.totalCount1();
		var pageNums1 = [];
		for ( var i = startPage1; i <= endPage1; i++) {
			pageNums1.push(i);
		}
		self.pageNums1(pageNums1);
	};

	self.refreshPage1 = function() {
		self.searchSupplierEmployee();
	};
	// end pagination
};

var ctx = new PassengerContext();

$(document).ready(function() {
	ko.applyBindings(ctx);
	ctx.refresh();
	$('body').click(function(event) {
		var target = event.target;
		if (!$(target).hasClass("passenger-name")) {
			$('.deletePassenger').remove();
		}
	});
});
/**
 * 
 * @param tr
 */
function showDetail(tr) {
	console.log(tr);
	var style = $("<tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>");
	$(tr).after(style);

}

var currentSupplier;
var supplierEmployeeLayer;
function choseSupplierEmployee(event) {
	supplierEmployeeLayer = $.layer({
		type : 1,
		title : [ '选择供应商操作', '' ],
		maxmin : false,
		closeBtn : [ 1, true ],
		shadeClose : false,
		area : [ '600px', '650px' ],
		offset : [ '50px', '' ],
		scrollbar : true,
		page : {
			dom : '#supplier-pick'
		},
		end : function() {
			console.log("Done");
		}
	});

	currentSupplier = event.toElement;
	$(currentSupplier).blur();
}
