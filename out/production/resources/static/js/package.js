$(document).ready(function(){
	/* add shortcut key event */
	keyEvent("popup-create-package","close-create-package");
	/* add onclick event to main tab */
	var tabs = document.getElementById("main-tab").getElementsByTagName("span");
	for(var i=0; i<tabs.length; i++){
		tabs[i].onclick = function(){
			tabOnClickEvent(this);
		};
	}
	/* add onclick event to buttons */
	document.getElementById("btn-create-package").onclick = function(){
		popUpEvent(document.getElementById("popup-create-package"), document.getElementById("close-create-package"));
	};

	var btns = getElementByAttribute(document.getElementsByTagName("div"), "class", "btn");
	if(btns){
		for(var i=0; i<btns.length; i++){
			var btn_text = btns[i].childNodes[0].nodeValue;
			if(btn_text=="国际发货"){
			 	btns[i].onclick = function(){
			 		if(false){
			 			// confirm the package details are not null
			 		}else{
			 			popUpEvent(document.getElementById("popup-cannot-ship-package"), document.getElementById("close-cannot-ship-package"));
			 		}
				};
			}else if(btn_text=="修改包裹"){
				btns[i].onclick = function(){
			 		popUpEvent(document.getElementById("popup-modify-package"), document.getElementById("close-modify-package"));
				};
			}else if(btn_text=="国内承运"){
				btns[i].onclick = function(){
			 		popUpEvent(document.getElementById("popup-forward-package"), document.getElementById("close-forward-package"));
				};
			}else if(btn_text=="确认收货"){
				btns[i].onclick = function(){
			 		popUpEvent(document.getElementById("popup-deliver-package"), document.getElementById("close-deliver-package"));
				};
			}else if(btn_text=="修改订单"){
			 	btns[i].onclick = function(){
					popUpEvent(document.getElementById("popup-modify-order"), document.getElementById("close-modify-order"));
				};
			}
		}
		
	}
	
	

	var elements = document.getElementsByTagName("div");
	var details_btns = new Array();
	for(var i=0; i<elements.length; i++){
		if(elements[i].childNodes[0]!=undefined && elements[i].childNodes[0].nodeValue=="包裹详情")
			details_btns.push(elements[i]);
	}
	for(var i=0; i<details_btns.length; i++){
		details_btns[i].onclick = function(){
			if(hasClass(document.getElementById("1"), "hide")){
				document.getElementById("1").className = "show";
				
				var img_updown = this.childNodes[1];
				img_updown.setAttribute("src", "../img/array_up.svg");

				var package_row = this.parentNode.parentNode;
				while(package_row.nodeType!=1 || !package_row.getAttribute("class") || !hasClass(package_row, "second-line")){
					package_row = package_row.previousSibling;
				}
				addClass(package_row, "unfold"); // 换用jQuery的方法
				while(package_row.nodeType!=1 || !package_row.getAttribute("class") || !hasClass(package_row, "first-line")){
					package_row = package_row.previousSibling;
				}
				addClass(package_row, "unfold"); // 换用jQuery的方法
			}else{
				document.getElementById("1").className = "hide";

				var img_updown = this.childNodes[1];
				img_updown.setAttribute("src", "../img/array_down.svg");

				var package_row = this.parentNode.parentNode;
				while(package_row.nodeType!=1 || !package_row.getAttribute("class") || !hasClass(package_row, "second-line")){
					package_row = package_row.previousSibling;
				}
				removeClass(package_row, "unfold");
				while(package_row.nodeType!=1 || !package_row.getAttribute("class") || !hasClass(package_row, "first-line")){
					package_row = package_row.previousSibling;
				}
				removeClass(package_row, "unfold");
			}
		}
	}


	/* add hover event*/
	var tags = getElementByAttribute(document.getElementsByTagName("img"), "class", "tag-info");
	for(var i=0; i<tags.length; i++){
		hover(tags[i], tags[i].nextSibling);
	}
	
	var shortcut_btns = getElementByAttribute(document.getElementsByTagName("div"), "class", "shortcut-btn");
	if(shortcut_btns){
		for(var i=0; i<shortcut_btns.length; i++){
			if(shortcut_btns[i].childNodes[0].nodeValue=="一键入账"){
				var price_node = shortcut_btns[i].parentNode.parentNode;
				while(price_node.nodeType!=1 || !price_node.getAttribute("name") || price_node.getAttribute("name")!="price"){
					price_node = price_node.previousSibling;
				}
				shortcut_btns[i].onclick = function(){
					this.parentNode.previousSibling.nodeValue = price_node.childNodes[0].nodeValue;
					this.style.display = "none";
				};
			}
		}
	}
});

function createPackageDetails(element){

}