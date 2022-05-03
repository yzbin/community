function post(){
    var questionsId = $("#question_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("不能回复空内容")
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify(
            {
                "parentId":questionsId,
                "content":content,
                "type":1
            }
        ),
        success: function (response){
            if(response.code== 200){
                window.location.reload();
            }else {
                if(response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=e5079ec94222e979934e&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("colsable",true);
                    }
                }else {
                    alert(response.message);
                }
            }
            console.log(response)
        },
        dataType: "json"
    });
}

/**
 * 展开二级评论
 */
function collapseCommnt(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    var collapse = e.getAttribute("data-collapse");
    if(collapse){
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
    }else {
        comments.addClass("in")
        e.setAttribute("data-collapse","in");
    }
}