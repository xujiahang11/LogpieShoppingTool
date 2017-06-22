var href = window.location.href;

$(document).ready(function(){
	/* add onclick event to main tab */
	$(".main-tab").find("span").click(function(){
		tabOnClickEvent($(this));
	});
	
	var url = href + "/list";
	$("#list").load(url);
	
	
	
});