$(document).ready(function(){
	/* add shortcut key event */
	keyEvent("popup-create-client","close-create-client");
	/* add onclick event to main tab */
	var tabs = document.getElementById("main-tab").getElementsByTagName("span");
	for(var i=0; i<tabs.length; i++){
		tabs[i].onclick = function(){
			tabOnClickEvent(this);
		};
	}
	/* add onclick event to buttons */
	$("btn-create-client").click(function(){
		popUpEvent(document.getElementById("popup-create-client"), document.getElementById("close-create-client"));
	});
	
	var btns = getElementByAttribute(document.getElementsByTagName("div"), "class", "btn");
	if(btns){
		for(var i=0; i<btns.length; i++){
			var btn_text = btns[i].childNodes[0].nodeValue;
			if(btn_text=="修改"){
			 	btns[i].onclick = function(){
			 		var cells = this.parentNode.parentNode.childNodes;
			 		try{
			 			var client = {};
			 			var id = getElementByAttribute(cells, "class", "id")[0].childNodes[0].nodeValue;
			 			client["id"] = id;
			 			
			 			var currentUrl = window.location.href;
			 			var url = currentUrl.substring(0, currentUrl.lastIndexOf("/")) + "/id/" + id;
			 			
			 			$.ajax({
			 				  type: "POST",
			 				  contentType: 'application/json',
			 				  url: url,
			 				  data: JSON.stringify(client), // build 'client' to JSON object by using a built-in method
			 				  success: function(data){
				 					document.getElementById("edit_id").value = id;
				 					document.getElementById("edit_name").value = data["name"];
				 					document.getElementById("edit_wechat_name").value = data["wechatName"];
				 					document.getElementById("edit_wechat_id").value = data["wechatId"];
				 					document.getElementById("edit_taobao_name").value = data["taobaoName"];
				 					document.getElementById("edit_phone").value = data["phone"];
				 					document.getElementById("edit_note").value = data["note"];
				 				},
			 				  dataType: "json"
			 			});
			 			
			 		}catch(error){
			 			alert("cannot fetch any info of this client");
			 		}
			 		popUpEvent(document.getElementById("popup-modify-client"), document.getElementById("close-modify-client"));
			 	};
			}
		}
	}
	
	$(".dropdown").click(function(){
			var $currentRow = $(this).parent().parent();
			var $idCell = $currentRow.find(".id");
			var $btnCell = $currentRow.find(".edit");
			
			if($idCell.attr("rowspan")==undefined){
				try{
					var currentUrl = window.location.href;
					var url = currentUrl.substring(0, currentUrl.lastIndexOf("/")) + "/id/" + $idCell.html() + "/address";
					
					$.get(url, function(data){
									var size = data["length"];
									if(size==0){
										var span = 2;
									}else{
										var span = size + 1;
									}			 				
									$idCell.prop("rowspan", span);
									$btnCell.prop("rowspan", span);
									appendDropdownList($currentRow, data, size);
								}, "json");
				}catch(error){
					alert("cannot fetch any addresses of this client")
				}
			}else{
				var size = $idCell.attr("rowspan");
				$idCell.removeAttr("rowspan");
				$btnCell.removeAttr("rowspan");
				removeDropdownList($currentRow, size-1);
			}
	});
});

function appendDropdownList(row, data, size){
	var $pointer = row;
	var dropdownRowClass = "text-left font-small border-bottom bg-dark-grey grey";
	for(var k=0; k<size; k++){
		var $text = "常用地址" + (k+1) + "：" + data[k]["address"];
		var $dropdownRow = $("<tr class='" + dropdownRowClass + "'></tr>");
		var $dropdownCell = $("<td colspan='7' style='padding-left:10px'></td>").append($text);
		$dropdownRow.append($dropdownCell);
		// set cell padding to 0px
		$dropdownRow.insertAfter($pointer);
		$pointer = $dropdownRow;
	}
	if(size==0){
		var $dropdownRow = $("<tr class='" + dropdownRowClass + "'></tr>");
		var $dropdownCell = $("<td colspan='7' style='padding-left:10px'>暂无常用地址</td>");
		$dropdownRow.append($dropdownCell);
		// set cell padding to 0px
		$dropdownRow.insertAfter($pointer);
	}
}

function removeDropdownList(row, size){
	var $pointer = row;
	for(var k=0; k<size; k++){
		$pointer.next().remove();
	}
}