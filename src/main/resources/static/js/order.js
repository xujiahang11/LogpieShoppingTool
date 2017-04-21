$(document).ready(function(){
	/* add shortcut key event */
	keyEvent("popup-create-order","close-create-order");
	/* add onclick event to main tab */
	var tabs = document.getElementById("main-tab").getElementsByTagName("span");
	for(var i=0; i<tabs.length; i++){
		tabs[i].onclick = function(){
			tabOnClickEvent(this);
		};
	}
	/* add onclick event to buttons */
	document.getElementById("btn-create-order").onclick = function(){
		popUpEvent(document.getElementById("popup-create-order"), document.getElementById("close-create-order"));
	};

	var btns = getElementByAttribute(document.getElementsByTagName("div"), "class", "btn");
	if(btns){
		for(var i=0; i<btns.length; i++){
			var btn_text = btns[i].childNodes[0].nodeValue;
			if(btn_text=="修改订单"){
			 	btns[i].onclick = function(){
					popUpEvent(document.getElementById("popup-modify-order"), document.getElementById("close-modify-order"));
				};
			}else if(btn_text=="装箱待寄"){
				btns[i].onclick = function(){
					popUpEvent(document.getElementById("popup-ship-order"), document.getElementById("close-ship-order"));
				};
			}else if(btn_text=="客服转寄" && true){
				btns[i].onclick = function(){
					popUpEvent(document.getElementById("popup-forward-order"), document.getElementById("close-forward-order"));
				};
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

	/* toggle for show and hide international shipping fee typed manually */
	var toggle_of_isf = document.getElementById("is-auto-calculated");
	var div_isf = document.getElementById("manually-calculate");
	var popup_ship_order = document.getElementById("popup-ship-order");
	toggle_of_isf.onclick = function(){
		if(toggle_of_isf.checked){
			div_isf.style.display = "none";
			popup_ship_order.style.height = "190px";
		}else{
			div_isf.style.display = "";
			popup_ship_order.style.height = "230px";
		}
	};

});