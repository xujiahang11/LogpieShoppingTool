function keyEvent(popup_div,close_btn){
	document.onkeydown = function() {
		var eventListener = window.event;
		/* use combination of TAB & ALT key to switch the subtitle */
		if(eventListener.altKey && eventListener.keyCode == 9){
			var tabs = document.getElementById("main-tab").getElementsByTagName("span");
			var selected_tab;
			var i=0;
			for(;i<tabs.length;i++){
				if(tabs[i].getAttribute("class")=="blue"){
					selected_tab = tabs[i];
					break;
				}
			}
			if(i==tabs.length-1)
				tabOnClickEvent(tabs[0]);
			else
				tabOnClickEvent(tabs[i+1]);
		};
		/* use combination of ALT key & C to popup the section for creating */
		if(eventListener.keyCode == 67 && eventListener.altKey){
			popUpEvent(document.getElementById(popup_div), document.getElementById(close_btn));
		};
		/* use right arrow key to choose next page */
		if(eventListener.keyCode == 39){
			alert("next page");
		};
		/* use left arrow key to choose previous page */
		if(eventListener.keyCode == 37)
			alert("previous page");
	}
}

function tabOnClickEvent(tab) {
	if(tab==null) return false;
	/* find original tab which is selected */
	var tabs = document.getElementById("main-tab").getElementsByTagName("span");
	var selected_tab;
	for(var i=0;i<tabs.length;i++){
		if(tabs[i].getAttribute("class")=="blue"){
			selected_tab = tabs[i];
			break;
		}
	}
	
	/* calculate new margin-left */
	var bar_margin_left = 20; // margin-left is 20px
	for(var i=0;i<tabs.length;i++){
		if(tabs[i]==tab) break;
		bar_margin_left = tabs[i].childNodes[0].nodeValue.length*14 + 20 + bar_margin_left; // font-size is 14px
	}
	/* set new width and position for the selected-bar */
	var bar = document.getElementById("selected-bar");
	bar.style.width = tab.childNodes[0].nodeValue.length*14 + "px";
	bar.style.marginLeft = bar_margin_left + "px";
	/* exchange color for original tab and new tab */
	selected_tab.setAttribute("class", "");
	tab.setAttribute("class", "blue");
	/* set new subtitle for header navigator */
	var subtitle = document.getElementById("header-nav-subtitle");
	subtitle.innerHTML = "> "+ tab.childNodes[0].nodeValue;
}

function popUpEvent(popup, btn_close){
	show(popup);
	btn_close.onclick = function(){
		hide(popup);
	};
}

function show(popup){
	document.getElementById("mask").style.display = "block";
	popup.style.display = "block";
}

function hide(popup){
	document.getElementById("mask").style.display = "none";
	popup.style.display = "none";
}

function hover(element, showinfo){
	if(element && showinfo){
		element.onmouseover = function(){
			showinfo.style.display = "block";
		};
		element.onmouseout = function() {
			showinfo.style.display = "none";
		};
	}
}

function getElementByAttribute(elements, attribute_name, attribute_value){
	if(elements){
		var results = new Array();
		for(var i=0; i<elements.length; i++){
			if(elements[i].nodeType==1 && elements[i].getAttribute(attribute_name)){
				if(elements[i].getAttribute(attribute_name).indexOf(attribute_value)!= -1){
					results.push(elements[i]);
				}
			}
		}
		if(results.length==0) return false; 
		return results;
	}
	return false;
}

function addClass(element, class_name){
	element.className = element.className+" "+class_name;
}

function removeClass(element, class_name){
    element.className = element.className.replace(class_name, '');
}

function hasClass(element, class_name){
	if(element.className.match(class_name)){
		return true;
	}else{
		return false;
	}
}