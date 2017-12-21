function tabOnClickEvent(tab) {
	/* find original tab which is selected */
	var tabs = $(".main-tab").find("span");
	var selected_tab = $(".main-tab").find("span.blue");
	
	/* calculate new margin-left */
	var bar_margin_left = 20; // margin-left is 20px
	for(var i=0;i<tabs.index(tab);i++){
		bar_margin_left = $(tabs[i]).html().length*14 + 20 + bar_margin_left; // font-size is 14px
	}
	
	/* set new width and position for the selected-bar */
	var bar = $("#main_selected_bar");
	bar.width(tab.html().length*14 + "px");
	bar.css("margin-left", bar_margin_left + "px");
	
	/* exchange color for original tab and new tab */
	selected_tab.removeClass();
	tab.addClass("blue");
	
	/* set new subtitle for header navigator */
	$("#header_nav_sub").html("> "+ tab.html());
}

function popUpEvent(popup, btn_close){
	show(popup);
	btn_close.click(function(){
		hide(popup);
	});
}

function show(popup){
	$("#mask").show();
	popup.css("display","block");
}

function hide(popup){
	$("#mask").hide();
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