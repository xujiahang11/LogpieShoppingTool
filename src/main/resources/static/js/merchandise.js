$(document).ready(function(){
	/* add shortcut key event 
	keyEvent("popup-create-merchandise","close-create-merchandise");

	/* add onclick event to main tab 
	var tabs = document.getElementById("main-tab").getElementsByTagName("span");
	for(var i=0; i<tabs.length; i++){
		tabs[i].onclick = function(){
			tabOnClickEvent(this);
		};
	}
	/* add onclick event to buttons 
	document.getElementById("btn-create-merchandise").onclick = function(){
		popUpEvent(document.getElementById("popup-create-merchandise"), document.getElementById("close-create-merchandise"));
	};
	*/

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
			}
		}
		
	}
	
	

	var elements = document.getElementsByTagName("div");
	var details_btns = new Array();
	for(var i=0; i<elements.length; i++){
		if(elements[i].childNodes[0]!=undefined && elements[i].childNodes[0].nodeValue=="二级类别详情")
			details_btns.push(elements[i]);
	}
	for(var i=0; i<details_btns.length; i++){
		details_btns[i].onclick = function(){
			if(hasClass(document.getElementById("1"), "hide")){
				document.getElementById("1").className = "show";
				
				var img_updown = this.childNodes[1];
				img_updown.setAttribute("src", "../img/array_up.svg");

				var package_row = this.parentNode.parentNode;
				while(package_row.nodeType!=1 || !package_row.getAttribute("class") || !hasClass(package_row, "first-line")){
					package_row = package_row.previousSibling;
				}
				addClass(package_row, "unfold");
			}else{
				document.getElementById("1").className = "hide";

				var img_updown = this.childNodes[1];
				img_updown.setAttribute("src", "../img/array_down.svg");

				var package_row = this.parentNode.parentNode;
				while(package_row.nodeType!=1 || !package_row.getAttribute("class") || !hasClass(package_row, "first-line")){
					package_row = package_row.previousSibling;
				}
				removeClass(package_row, "unfold");
			}
		}
	}
});