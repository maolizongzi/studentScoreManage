
function toAdd(){
	window.location.href = "../user/toUserDetail.do";
}
function toEdit(id){
	window.location.href = "../user/toUserDetail.do?userId="+id;
}



function toDelete(id){
	var flag = window.confirm("确认删除吗?");
	if (flag){
		var url="../user/ajaxDelete.do?userId="+id;
		$.ajax({
			url:url,
			type:'post',
			success:function(res){
				var result=JSON.parse(res);
				if(result.flag){
					alert("删除成功");
					$("#form1").submit() ;
				}else{
					alert("删除失败");
				}
			},
			error:function(error){
				alert("删除失败");
			}
		});
	}
}

function login(){
	 $("#form1").submit() ;
}


