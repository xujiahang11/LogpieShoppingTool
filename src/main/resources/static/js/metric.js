$(window).on('load', function(){
    var url = window.location.href + "/metric";
    var data = $("#metricDiv").text();
    $.ajax({
        type : "POST",
        contentType : "text/html",
        url : url,
        data : data,
        dataType : 'text',
        success : function(data) {
            console.log("success");
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
});