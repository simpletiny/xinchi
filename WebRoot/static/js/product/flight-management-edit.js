var FlightContext = function() {
	var self = this;
	self.apiurl = $("#hidden_apiurl").val();

	// 产品基础信息
	self.productPk = $("#product-pk").val();
	self.product = ko.observable({});
	self.flight = ko.observable({});
	
	$.getJSON(self.apiurl + 'product/searchProductByPk', {
		product_pk : self.productPk
	}, function(data) {
		self.product(data.product);
	});
	$.getJSON(self.apiurl + 'product/searchFlightByProductPk', {
		product_pk : self.productPk
	}, function(data) {
		self.flight(data.flight);
	});
	
	

	self.updateProductFlight = function() {
		if (!$("form").valid()) {
			return;
		}

		startLoadingIndicator("更新中！");

		var data = $("form").serialize();

		var json = '[';
		var tbody = $("#table-ticket tbody");
		var allTrs = tbody.children();
		for (var i = 0; i < allTrs.length; i++) {
			var current = allTrs[i];

			var flight_index = i + 1;

			var flight_leg = $(current).find("[st='flight-leg']").val();
			var start_day = $(current).find("[st='start-day']").val();
			var start_city = $(current).find("[st='start-city']").val();
			var end_day = $(current).find("[st='end-day']").val();
			var end_city = $(current).find("[st='end-city']").val();
			var flight_number = $(current).find("[st='flight-number']").val();

			json += '{"flight_index":"' + flight_index + '","flight_leg":"'
					+ flight_leg + '","start_day":"' + start_day
					+ '","start_city":"' + start_city + '","end_day":"'
					+ end_day + '","end_city":"' + end_city
					+ '","flight_number":"' + flight_number + '"';

			if (i == allTrs.length - 1) {
				json += '}';
			} else {
				json += '},';
			}
		}

		json += ']';
		data += "&json=" + json;
		$.ajax({
			type : "POST",
			url : self.apiurl + 'product/updateProductFlight',
			data : data
		}).success(
				function(str) {
					if (str == "success") {
						window.location.href = self.apiurl
								+ "templates/product/product-upkeep.jsp";
					}
				});
	}
	
};

var ctx = new FlightContext();

$(document).ready(function() {
	ko.applyBindings(ctx);
});

function addRow() {
	var tbody = $("#table-ticket tbody");
	var index = tbody.children().length;
	var tr = $('<tr><input type="hidden" st="flight-index" value="1" /><td ><input st="flight-leg" type="text" maxlength="10"/></td><td><input st="start-day" maxlength="2" type="number" min="1"/></td><td><input st="start-city" type="text" maxlength="10"/></td><td><input st="end-day"  maxlength="2" type="number" min="1"/></td><td><input st="end-city" type="text" maxlength="10"/></td><td><input st="flight-number" type="text" maxlength="10" /></td></tr>');
	$(tr).find("input[st='flight-leg']").val(CHARACTER_ARRAY[index]);

	$(tr).find("input[st='flight-index']").val(index + 1);

	tbody.append(tr);
}

function deleteRow() {
	var tbody = $("#table-ticket tbody");
	var index = tbody.children().length - 1;
	if (index > 0) {
		$(tbody.children()[index]).remove();
	}
}