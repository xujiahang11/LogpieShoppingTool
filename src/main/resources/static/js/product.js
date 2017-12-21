var href = window.location.href;
var searchTimeRef = null;

var productValidationRules = {
	rules: {
		name: {
			required: true
		},
		price: {
			required: true
		}
	},
	messages: {
		name: {
			required: "请输入名称"
		},
		price: {
			required: "请输入价格"
		}
	}
}

$(document).ready(function(){
	/* append product list to main page */
	loadProductList();
	
	/* add click event to main tab */
	$(".main-tab").find("span").click(function(){
		tabOnClickEvent($(this));
	});

	/* add click event to buttons */
	$("#btn_create_product").click(function(){
		showPopup(null, null);
	});
	
});

function loadProductList() {
	var url = href + "/list"
	$("#list").load(url, function(){
		$("#table_products").find("select").change(function(){
			var thisRow = $(this).parent().parent();
			var configId = $(this).find("option:selected").val();
			var url = href.substring(0, href.lastIndexOf("/")) + "/config/" + configId;
			$.get(url, function(result){
				console.log(result);
				thisRow.find(".item-number").text(result["itemNumber"]);
				thisRow.find(".weight").text(result["weight"].toFixed(1));
				thisRow.find(".price").text(result["price"].toFixed(1));
			}, "json");
		});
	});
}

function showPopup(selectedBrand, selectedCategory) {
	
	var url = href.substring(0, href.lastIndexOf("/")) + "/creationForm";
	$("#popups").load(url, function(){
		
		/* show pop-up */
		popUpEvent($("#popup_create_product"), $("#dismiss_create_product"));
		
		var form = $("#form_create_product").click(function(event){
			$("#search_result").empty().hide();
			event.stopPropagation();
		});
		
		/* reset all options' selected as false */
		form.find("option").prop("selectd", false);
		
		/* set brand and sub-category options selected followed by rules: 
		 * 
		 * 1. make the one selected if it was just created
		 * 2. make the one selected if it was selected before
		 * 3. reset the default option of sub-category selected if a new category was created
		 * 
		 * */
		if(selectedBrand) {
			form.find("tr.brand option:contains('" + selectedBrand + "')").prop("selected", true);
		}else {
			form.find("tr.brand option:first").prop("selected", true);
		}
		if(selectedCategory) {
			form.find("tr.category option:contains('" + selectedCategory + "')").prop("selected", true);
		}else {
			form.find("tr.category option:first").prop("selected", true);
		}
		
		/* show append-div of creating a brand */
		$("#create_brand_btn_of_popup").click(function(){
			showBrandDiv();
		});
		
		/* show append-div of creating a category */
		$("#create_category_btn_of_popup").click(function(){
			showCategoryDiv();
		});
		
		/* show append-div of creating a sub-category */
		$("#create_subcategory_btn_of_popup").click(function(){
			showSubcategoryDiv();
		});
		
		$("#create_config_btn_of_popup").click(function(){
			showConfigDiv();
		})
		
		/*  set form validation rules
		 *  var validator = form.validate(productValidationRules);
		 *  
		 *  reset the validator when click the button every time
		 *  validator.resetForm();
		 *  
		 *  check the form is valid
		 *	if(form.valid){
		 *  
		 *  }
		 **/
		
		$("#submit_btn_create_product").click(function(){
			form.submit();
		});
	});
}

function showBrandDiv() {
	$("#create_brand_btn_of_popup").css("display","none");
	
	var brand_row = $("#form_create_product").find("table tr.brand");
	brand_row.find("select").prop("disabled", true);
	
	var input = $("<input id='new_brand' type='text' required>");
	
	var confirm_btn = $("<div class='btn create-btn'>确定创建</div>")
		.click(function(){
			/* validate input value */
			if($("#new_brand").val()=="") {
				$("#new_brand").attr("placeholder","品牌名不能为空");
				return false;
			}
			
			var url = href.substring(0, href.lastIndexOf("/")) + "/brand/create";
			var data = {"name": $("#new_brand").val()};
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data : JSON.stringify(data),
				dataType : 'text',
				success : function(brand) {
					showPopup(brand, form.find("tr.category option:selected").text());
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
		});
	
	var cancel_btn = $("<div class='btn cancel-btn'>取消创建</div>").click(function(){
		$("#form_create_product").find(".brand + .append-div").remove();
		$("#create_brand_btn_of_popup").css("display", "block");
		$("#form_create_product").find("tr.brand select").prop("disabled", false);
	});

	$("<tr class='append-div'></tr>")
		.append($("<td class='red'>品牌名称</td>"))
		.append($("<td></td>").append(input))
		.append($("<td></td>").append(confirm_btn))
		.append($("<td></td>").append(cancel_btn))
		.insertAfter(brand_row);
}

function showCategoryDiv(){
	hideCategoryBtns();
	
	var category_row = $("#form_create_product").find("table tr.category");
	category_row.find("select").prop("disabled", true);
				
	var input = $("<input id='new_category' type='text'>");
	
	var confirm_btn = $("<div class='btn create-btn'>确定创建</div>").click(function(){
		/* validate input value */
		if($("#new_category").val()=="") {
			$("#new_category").attr("placeholder","类别名不能为空");
			return false;
		}
		
		var url = href.substring(0, href.lastIndexOf("/")) + "/category/create";
		var data = {"name": $("#new_category").val()};
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : url,
			data : JSON.stringify(data),
			dataType : 'text',
			success : function(category) {
				showPopup($("#form_create_product").find("tr.brand option:selected").text(), null);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	});
	
	var cancel_btn = $("<div class='btn cancel-btn'>取消创建</div>").click(function(){
		restoreCategoryDiv();
	});;
	
	$("<tr class='append-div'></tr>")
		.append($("<td class='red'>类别名称</td>"))
		.append($("<td></td>").append(input))
		.append($("<td></td>").append(confirm_btn))
		.append($("<td></td>").append(cancel_btn))
		.insertAfter(category_row);
}

function showSubcategoryDiv(){
	hideCategoryBtns();
	
	var subcategory_row = $("#form_create_product").find("table tr.category");
	subcategory_row.find("select").prop("disabled", true);
				
	var url = href.substring(0, href.lastIndexOf("/")) + "/category";
	$.get(url, function(result){
		var select = $("<select></select>");
		$.each(result, function(i, obj){
			select.append($("<option></option>").val(obj.id).text(obj.name));
		});
		
		var input = $("<input id='new_subcategory' type='text'>");
		
		var confirm_btn = $("<div class='btn create-btn'>确定创建</div>").click(function(){
			/* validate select value */
			var categoryId = $(this).parent().parent().find("select option:selected").val();
			if(categoryId==undefined || categoryId=="") {
				return false;
			}
			
			/* validate input value */
			if($("#new_subcategory").val()=="") {
				$("#new_subcategory").attr("placeholder","子类别名不能为空");
				return false;
			}
			
			var url = href.substring(0, href.lastIndexOf("/")) + "/category/" + categoryId + "/subcategory/create";
			var data = {"name": $("#new_subcategory").val()};
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data : JSON.stringify(data),
				dataType : 'text',
				success : function(subcategory) {
					showPopup($("#form_create_product").find("tr.brand option:selected").text(), subcategory);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
		});
		
		var cancel_btn = $("<div class='btn cancel-btn'>取消创建</div>").click(function(){
			restoreCategoryDiv();
		});;
		
		$("<tr class='append-div'></tr>")
			.append($("<td class='red'>子类别名称</td>"))
			.append($("<td></td>").append(select).append(input))
			.append($("<td></td>").append(confirm_btn))
			.append($("<td></td>").append(cancel_btn))
			.insertAfter(subcategory_row);
	}, "json");	
}

function hideCategoryBtns() {
	$("#create_subcategory_btn_of_popup").css("display","none");
	$("#create_category_btn_of_popup").css("display","none");
}

function restoreCategoryDiv() {
	$("#form_create_product").find(".category ~ .append-div").remove();
	$("#create_category_btn_of_popup").css("display", "block");
	$("#create_subcategory_btn_of_popup").css("display", "block");
	$("#form_create_product").find("tr.category select").prop("disabled", false);
}

function searchOnRealTime(event) {
	/* 如果ref存在，则清空（即若上一次调用还未来得及触发，则清空） */
	if(searchTimeRef) {
		clearTimeout(searchTimeRef);
	}
	searchTimeRef = setTimeout(sendSearchRequest(), 500);
}

function sendSearchRequest() {
	var href = window.location.href;
	var url = href.substring(0, href.lastIndexOf("/")) + "/search";
	
	$.ajax({
		type : "POST",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		url : url,
		data : {"name": event.target.value},
		dataType : 'json',
		success : function(result) {
			$("#search_result").empty().click(function(event){
				event.stopPropagation();
			});
			
			if(result.length>0) {
				$("#search_result").show();
			}else {
				$("#search_result").hide();
			}
			
			$.each(result, function(i, obj){
				$("#search_result")
					.append($('<div></div>').append(obj.name));
			});
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function showConfigDiv() {
	
}