var href = window.location.href;

var clientValidationRules = {
	rules: {
		name: {
			required: true
		},
		wechatName: {
			required: true
		},
		phone: {
			required: true,
			digits: true
		}
	},
	messages: {
		name: {
			required: "请输入姓名"
		},
		wechatName: {
			required: "请输入微信名"
		},
		phone: {
			required: "请输入手机号",
			digits: "请输入正确的手机号"
		}
	}
}

$(document).ready(function(){
	/* add onclick event to main tab */
	$(".main-tab").find("span").click(function(){
		tabOnClickEvent($(this));
	});

	/* add onclick event to buttons */
	$("#btn_create_client").click(function(){
		popUpEvent($("#popup_create_client"), $("#dismiss_create_client"));
		
		// set form validation rules
		var validator = $("#form_create_client").validate(clientValidationRules);
		// reset the validator when click the button every time
		validator.resetForm();
		
		$("#submit_btn_create_client").click(function(){
			// check the form is valid
			if($("#form_create_client").valid){
				$("#form_create_client").submit();
			}
		});
	});
	
	$(".btn:contains(修改)").click(function(){
		popUpEvent($("#popup_edit_client"), $("#dismiss_edit_client"));
		
		var id = $(this).parent().parent().children(".id");
		var url = href.substring(0, href.lastIndexOf("/")) + "/id/" + id.html();
 			
		/* get properties of this client from server side*/
 		try{
 			$.get(url, function(result){
				$("#edit_id").val(id.html());
				$("#edit_name").val(result["name"]);
				$("#edit_wechat_name").val(result["wechatName"]);
				$("#edit_wechat_id").val(result["wechatId"]);
				$("#edit_phone").val(result["phone"]);
				$("#edit_note").val(result["note"]);
			}, "json");
 		}catch(error){
 			console.log("cannot fetch any info of this client");
 		}
 		
 		// set form validation rules
		var validator = $("#form_edit_client").validate(clientValidationRules);
		// reset the validator when click the button every time
		validator.resetForm();
		
		$("#submit_btn_edit_client").click(function(){
			// check the form is valid
			if($("#form_edit_client").valid){
				$("#form_edit_client").submit();
			}
		});
	});
	
	$(".dropdown").click(function(){
			var currentRow = $(this).parent().parent();
			// check if dropdown is unfolded
			currentRow.find(".id").attr("rowspan")==undefined ? showDropdown(currentRow) : dismissDropdown(currentRow);
	});
});

function showDropdown(row){
	var id = row.find(".id");
	var url = href.substring(0, href.lastIndexOf("/")) + "/id/" + id.html() + "/address";
	
	try{
		/* get address info of this client from server side and append it */
		$.get(url, function(result){
			var size = result["length"];
			if(size==0){
				var span = 2;
			}else{
				var span = size + 2;
			}			 				
			id.prop("rowspan", span);
			row.find(".edit").prop("rowspan", span);
			appendDropdownList(row, result, size);
		}, "json");
	}catch(error){
		console.log("cannot fetch any addresses of this client")
	}
}

function dismissDropdown(row){
	var id = row.find(".id");
	var btn = row.find(".edit");
	
	for(var i=0; i<id.attr("rowspan")-1; i++){
		row.next().remove();
	}
	
	id.removeAttr("rowspan");
	btn.removeAttr("rowspan");
}

function appendDropdownList(row, result, size){
	var pointer = row;
	var dropdownRowClass = "text-left font-small border-bottom bg-dark-grey grey";
	var btnClass = "font-extra-small blue underline pointer";
	
	for(var i=0; i<size; i++){
		var addrContent = $("<span>收件人: " + result[i]["recipent"] + "</span><span>联系电话: " 
						  + result[i]["phone"] + "</span><span>地址: " + result[i]["address"] 
						  + " " + result[i]["zip"] + "</span>");
		var addrIdDiv = $("<div class='address-id hide'></div>").append(result[i]["id"]);
		var addrDiv = $("<div class='address text-omit' style='float:left; width:95%'></div>")
					  .append(addrIdDiv)
					  .append(addrContent);
		
		var editBtn = $("<div class='" + btnClass + "'>修改</div>").click(function(){
			popUpEvent($("#popup_edit_address"), $("#dismiss_edit_address"));
			
			var addrId = $(this).parent().find(".address-id").html();
			var url = href.substring(0, href.lastIndexOf("/")) + "/id/" + row.find(".id").html() + "/address/" + addrId;
	 			
			/* get properties of this address */
	 		try{
	 			$.get(url, function(result){
					$("#edit_address_id").val(addrId);
					$("#edit_recipent_name").val(result["recipent"]);
					$("#edit_recipent_phone").val(result["phone"]);
					$("#edit_address").val(result["address"]);
					$("#edit_zip").val(result["zip"]);
				}, "json");
	 		}catch(error){
	 			console.log("cannot fetch this address");
	 		}
	 		
			$("#btn_edit_address").click(function(){
				requestToEditAddress(row, addrId);
			});
		});
		
		var dropdownRow = $("<tr class='" + dropdownRowClass + "'></tr>");
		var dropdownCell = $("<td colspan='6' style='padding-left:20px'></td>").append(addrDiv).append(editBtn);
		dropdownRow.append(dropdownCell).insertAfter(pointer);
		pointer = dropdownRow;
	}
	
	var createBtn = $("<div class='" + btnClass + "'>添加新地址</div>").click(function(){
		popUpEvent($("#popup_create_address"), $("#dismiss_create_address"));
		$("#btn_create_address").click(function(){
			requestToCreateAddress(row);
		});
	});
	
	var dropdownCell = $("<td colspan='6' style='padding-left:20px'></td>").append(createBtn);
	$("<tr class='" + dropdownRowClass + "'></tr>").append(dropdownCell).insertAfter(pointer);
}

function requestToCreateAddress(row){
	var data = {
		"recipent": $("#recipent_name").val(),
		"phone": $("#recipent_phone").val(),
		"address": $("#address").val(),
		"zip": $("#zip").val()
	};
	
	var url = href.substring(0, href.lastIndexOf("/")) + "/id/" + row.find(".id").html() + "/address/create";
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify(data),
		dataType : 'text',
		success : function(data) {
			hide($("#popup_create_address"));
			dismissDropdown(row);
			showDropdown(row);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function requestToEditAddress(row, addrId){
	var data = {
		"id": addrId,
		"recipent": $("#edit_recipent_name").val(),
		"phone": $("#edit_recipent_phone").val(),
		"address": $("#edit_address").val(),
		"zip": $("#edit_zip").val()
	};
	
	var url = href.substring(0, href.lastIndexOf("/")) + "/id/" + row.find(".id").html() + "/address/edit";
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify(data),
		dataType : 'text',
		success : function(data) {
			hide($("#popup_edit_address"));
			dismissDropdown(row);
			showDropdown(row);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}