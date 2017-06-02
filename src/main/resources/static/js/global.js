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
	/* find original tab which is selected */
	var tabs = $("#main-tab").find("span");
	var selected_tab = $("#main-tab").find("span.blue");
	
	/* calculate new margin-left */
	var bar_margin_left = 20; // margin-left is 20px
	for(var i=0;i<tabs.index(tab);i++){
		bar_margin_left = $(tabs[i]).html().length*14 + 20 + bar_margin_left; // font-size is 14px
	}
	
	/* set new width and position for the selected-bar */
	var bar = $("#selected-bar");
	bar.width(tab.html().length*14 + "px");
	bar.css("margin-left", bar_margin_left + "px");
	
	/* exchange color for original tab and new tab */
	selected_tab.removeClass();
	tab.addClass("blue");
	
	/* set new subtitle for header navigator */
	$("#header-nav-subtitle").html("> "+ tab.html());
}

function popUpEvent(popup, btn_close){
	show(popup);
	btn_close.click(function(){
		hide(popup);
	});
}

function show(popup){
	$("#mask").css("display","block");
	popup.css("display","block");
}

function hide(popup){
	$("#mask").css("display","none");
	popup.css("display","none");
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