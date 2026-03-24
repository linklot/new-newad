
$(document).ready(function(){
	var form = $("form:first");
	form.submit(function(event){
		if(!checkForm())
			event.preventDefault();
	});
	
});

function checkForm() {
	
	var username = $('#username');
	var s_username = $.trim(username.val());
	
	if(s_username.length < 3) {
		alert('用户名不能少于3个字符。');
		return false;
	}
	username.val(s_username);
	
	var title = $('#title');
	var s_title = $.trim(title.val());
	
	if(s_title.length < 3) {
		alert('标题不能太短。');
		return false;
	}
	title.val(s_title);
	
	var content = $('#content');
	var s_content = $.trim(content.val());
	
	if(s_content.length < 10) {
		alert('请尽量详细描述您的问题。');
		return false;
	}
	
	return true;
}