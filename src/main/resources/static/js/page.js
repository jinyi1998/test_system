
function commonPage(pageInfo,current){

    var pageStr = "";
    $(".pagination").empty();

    pageStr = pageStr+ "<li><a href='#'>&laquo;</a></li>";
    for(i=1;i<=pageInfo.totalPages;i++){
      if (i == current){
                pageStr = pageStr+ "<li class=\"active\"><a  onclick='page("+(i-1)+",10)' href=\"#\">"+i+"</a></li>";
            } else{
                pageStr = pageStr+ "<li><a  onclick='page("+(i-1)+",10)' href=\"#\">"+i+"</a></li>";
            }
    }
    pageStr = pageStr+ "<li><a href='#'>&raquo;</a></li>";


    $(".pagination").append(pageStr);



}