
function commonPage(pageInfo,current){

    var pageStr = "";
    $(".pagination").empty();

    pageStr = pageStr+ "<li><a href='#'>&laquo;</a></li>";

    $(pageInfo.navigatepageNums).each(function (i,item) {
        if (item == current){
            pageStr = pageStr+ "<li class=\"active\"><a  onclick='page("+item+",10)' href=\"#\">"+item+"</a></li>";
        } else{
            pageStr = pageStr+ "<li><a  onclick='page("+item+",10)' href=\"#\">"+item+"</a></li>";
        }
    })
    pageStr = pageStr+ "<li><a href='#'>&raquo;</a></li>";


    $(".pagination").append(pageStr);



}