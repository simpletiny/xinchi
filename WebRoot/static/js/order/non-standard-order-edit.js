var clientEmployeeLayer;
var OrderContext = function() {
	var self = this;
	self.apiurl = $("#hidden_apiurl").val();
	self.order = ko.observable({});
	self.clientEmployees = ko.observable({});
	self.employee = ko.observable({});
	self.order_pk = $("#key").val();
	self.passengers = ko.observableArray([]);
	self.independent_msg = ko.observable();
	
	var d = new Date();
	var year_now = d.getFullYear();

	$.getJSON(self.apiurl + 'order/searchTbcBnsOrderByPk', {
		order_pk : self.order_pk
	}, function(data) {
		self.order(data.bnsOrder);
		
		$(data.passengers).each(function(idx, passenger) {
			passenger.age = ko.observable();
			var birthYear = passenger.id.substring(6, 10);
			passenger.age(year_now - birthYear);
		});
		self.passengers(data.passengers);
		
		if (self.order().name_list_lock == '1')
			$("#txt-name-list").disabled();

		if (self.order().independent_flg == 'Y') {
			self.independent_msg("（独立团）");
		}

		$.getJSON(self.apiurl + 'client/searchOneEmployee', {
			employee_pk : self.order().client_employee_pk
		}, function(data) {
			if (data.employee) {
				self.employee(data.employee);
			} else {
				fail_msg("员工不存在！");
			}
		}).fail(function(reason) {
			fail_msg(reason.responseText);
		});
		self.loadFiles();
	});
	self.loadFiles = function() {
		var stFileName = $("#txt-confirm-file");
		var fileName = $("#txt-confirm-file").val();
		if (!fileName.isEmpty()) {
			self.downFile(stFileName, fileName);
		}
	};

	self.downFile = function(stFileName, fileName) {
		var inputFile = $(stFileName).prev().find("input");
		var inputName = inputFile.attr("name");
		var imgContainer = $(inputFile).parent().parent().parent().next();
		var fileNameInput = stFileName;

		var formData = new FormData();
		formData.append("fileFileName", fileName);
		formData.append("fileType", "CLIENT_CONFIRM");
		formData.append("subFolder", self.order().create_user);

		var url = ctx.apiurl + 'file/getFileStream';
		var xhr = new XMLHttpRequest();
		xhr.open('POST', url, true);
		xhr.responseType = "blob";
		xhr.onload = function() {
			if (this.status == 200) {
				var blob = this.response;
				var deleteButton = $("<div class='delete'>删除</div>");

				var img = document.createElement("img");
				$("body").append(deleteButton);
				deleteButton.hide();
				deleteButton.click(function() {
					deleteImage(this, inputFile, img, fileNameInput, fileName);
				});
				deleteButton.mouseenter(function() {
					$(this).show();
				});
				img.onload = function(e) {
					window.URL.revokeObjectURL(img.src);
					if (img.width > initWidth) {
						img.height = initWidth * (img.height / img.width);
						img.width = initWidth;
					}

					$(img).mouseenter(function() {
						deleteButton.css("top", $(img).offset().top + img.height / 2 - 25);
						deleteButton.css("left", $(img).offset().left + img.width / 2 - 50);
						deleteButton.show();
					});
					$(img).mouseout(function() {
						deleteButton.hide();
					});
				};
				img.src = window.URL.createObjectURL(blob);

				if (inputName != "file6") {
					imgContainer.html(img);
				} else {
					imgContainer.append(img);
				}
			}
		};
		xhr.send(formData);
	};
	self.refreshClient = function() {
		var param = "employee.name=" + $("#client_name").val()+"&employee.review_flg=Y";
		param += "&page.start=" + self.startIndex() + "&page.count=" + self.perPage;
		$.getJSON(self.apiurl + 'client/searchEmployeeByPage', param, function(data) {
			self.clientEmployees(data.employees);

			self.totalCount(Math.ceil(data.page.total / self.perPage));
			self.setPageNums(self.currentPage());
		});
	};

	self.searchClientEmployee = function() {
		self.refreshClient();
	};

	self.choseClientEmployee = function() {
		$("#txt-client-employee-name").blur();
		clientEmployeeLayer = $.layer({
			type : 1,
			title : [ '选择客户操作', '' ],
			maxmin : false,
			closeBtn : [ 1, true ],
			shadeClose : false,
			area : [ '600px', '650px' ],
			offset : [ '50px', '' ],
			scrollbar : true,
			page : {
				dom : '#client-pick'
			},
			end : function() {
				console.log("Done");
			}
		});
	};

	self.pickClientEmployee = function(name, pk) {
		$("#txt-client-employee-name").val(name);
		$("#txt-client-employee-pk").val(pk);
		layer.close(clientEmployeeLayer);
	};

	self.updateOrder = function() {

		if (!$("form").valid()) {
			return;
		}
		var url = "";
		if ($("#team-number").val() == "") {
			url = self.apiurl + 'order/updateBudgetNonStandardOrder';
		} else {
			url = self.apiurl + 'order/updateConfirmedNonStandardOrder';
		}
		var data = $("form").serialize();
		
		// 名单json
		var tbody = $("#name-table").find("tbody");
		var trs = $(tbody).children();
		var json = '[';
		for (var i = 0; i < trs.length; i++) {
			if (i != 0)
				json += ',';
			var tr = trs[i];
			var teamChairman = $(tr).find("[name='team_chairman']").is(
					":checked") ? "Y" : "N";
			var index = i + 1;
			var name = $(tr).find("[st='name']").val();
			var sex = $(tr).find("[st='sex']").val();

			var cellphone_A = $(tr).find("[st='cellphone_A']").val();
			var cellphone_B = $(tr).find("[st='cellphone_B']").val();
			var id = $(tr).find("[st='id']").val();

			json += '{"chairman":"' + teamChairman + '","index":"' + index
					+ '","name":"' + name + '","sex":"' + sex
					+ '","cellphone_A":"' + cellphone_A + '","cellphone_B":"'
					+ cellphone_B + '","id":"' + id + '"}';
		}
		json += ']';
		data += "&json=" + json;

		startLoadingSimpleIndicator("保存中");
		$.ajax({
			type : "POST",
			url : url,
			data : data
		}).success(function(str) {
			if (str == "success") {
				window.history.go(-1);
			}
		});
	};

	self.addName = function() {
		var tbody = $("#name-table").find("tbody");
		var count = $(tbody).children().length;
		var html = '<tr>'
				+ '<td><input type="radio" name="team_chairman" /></td>'
				+ '<td st="name-index">'
				+ (count + 1)
				+ '</td>'
				+ '<td><input type="text" style="width: 90%" st="name" /></td>'
				+ '<td><select class="form-control" style="height: 34px" st="sex">'
				+ '<option value="">选择</option>'
				+ '<option value="M">男</option>'
				+ '<option value="F">女</option>'
				+ '</select></td>'
				+ '<td><input type="text" style="width: 90%" st="age" /></td>'
				+ '<td><input type="text" style="width: 90%" st="cellphone_A" /></td>'
				+ '<td><input type="text" style="width: 90%" st="cellphone_B" /></td>'
				+ '<td><input type="text" style="width: 90%" st="id" /></td>'
				+ '<td><input type="text" style="width: 90%" value="分房组" /></td>'
				+ '<td><a href="javascript:;" class="a-upload">上传身份证<input type="file" name="file" /></a> <input'
				+ 'type="hidden"/></td>'
				+ '<td><a href="javascript:;" class="a-upload">上传护照<input type="file" name="file" /></a> <input'
				+ 'type="hidden" /></td>'
				+ '<td><input type="button" style="width: 50px" onclick= "removeName(this)" alt="删除名单" value="-" /></td>'
				+ '</tr>';
		tbody.append(html);
	};
	// start pagination
	self.currentPage = ko.observable(1);
	self.perPage = 10;
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
		self.searchClientEmployee();
	};
	// end pagination
};

var ctx = new OrderContext();
$(document).ready(function() {
	ko.applyBindings(ctx);
	$(':file').change(function() {
		changeFile(this);
	});
});
function formatNameList() {
	nameList = $("#txt-name-list").val();
	if (nameList.trim() == "")
		return;
	var illegal = /[^\u4e00-\u9fa5,0-9,X,x]|[\n\r]/gm;

	nameList = nameList.replace(illegal, "");
	var idPattern = /[0-9,X,x]+/gm;
	var ids = nameList.match(idPattern);

	var namePattern = /[\u4e00-\u9fa5]+/gm;
	var names = nameList.match(namePattern);

	if (null == ids) {
		fail_msg("请填写正确的名单！");
		return;
	}

	for (var i = 0; i < ids.length; i++) {
		var id = ids[i];
		nameList = nameList.replace(id, ":" + id + ";");
	}

	var nameObjs = new Array();
	var names = nameList.split(";");
	var newNameList = "";

	for (var i = 0; i < names.length; i++) {
		if (names[i] == "")
			continue;

		if (i % 2 == 1) {
			newNameList += names[i] + ";\n";
		} else {
			newNameList += names[i] + ";";
		}
		var nameObj = new Object();
		var passenger = names[i].split(":");
		if (passenger.length < 2)
			continue;
		nameObj.name = passenger[0];
		nameObj.id = passenger[1];
		nameObjs.push(nameObj);
	}
	var d = new Date();
	var year_now = d.getFullYear();

	for (var i = 0; i < nameObjs.length; i++) {
		var nameObj = nameObjs[i];
		var birthYear = nameObj.id.substring(6, 10);

		var lastSecond = nameObj.id.charAt(16);

		if (isRepeatId(nameObj.id))
			continue;
		var tbody = $("#name-table").find("tbody");
		var trs = $(tbody).children();
		if (nameObjs.length > trs.length)
			ctx.addName();

		var tbody = $("#name-table").find("tbody");
		var trs = $(tbody).children();
		var tr = trs[i];
		var name = $(tr).find("[st='name']");
		var sex = $(tr).find("[st='sex']");
		var age = $(tr).find("[st='age']");
		var id = $(tr).find("[st='id']");

		$(name).val(nameObj.name);
		$(sex).val(lastSecond % 2 == 0 ? "F" : "M");
		$(age).val(year_now - birthYear);
		$(id).val(nameObj.id);
	}

	$("#txt-name-list").val(newNameList);
}
// 判断是否已经存在重复的id乘客
var isRepeatId = function(id) {
	var tbody = $("#name-table").find("tbody");

	var trs = $(tbody).children();

	for (var i = 0; i < trs.length; i++) {
		var tr = trs[i];
		var td_id = $(tr).find("[st='id']");
		var existId = $(td_id).val();
		if (id == existId)
			return true;
	}
	return false;
}
var removeName = function(btn) {
	var tbody = $("#name-table").find("tbody");
	var count = $(tbody).children().length;
	var target = $(btn).parent().parent();
	if (count > 1) {
		var td_radio = $(target).find("[name='team_chairman']");
		$(target).remove();
		refreshNameIndex();
	}

};
var refreshNameIndex = function() {
	var tbody = $("#name-table").find("tbody");

	var trs = $(tbody).children();

	for (var i = 0; i < trs.length; i++) {
		var tr = trs[i];
		var td_index = $(tr).find("[st='name-index']");
		$(td_index).html(i + 1);
	}
}