function change(img){
	
	img.src="getCode?"+new Date().getTime();
}

var flag=[true,true,true,true];	//标记位

function FocusItem(obj){
	
	//清空警告的内容
	if($(obj).attr('name')=='veryCode'){
		$(obj).next().next().html('').removeClass('error');
	}
	else{
		$(obj).next('span').html('').removeClass('error');
	}
}

function CheckItem(obj){
	var msgBox=$(obj).next('span');
	
	switch($(obj).attr('name')){
	case 'ID':
		if(obj.value==""){
			msgBox.html('用户名不能为空');
			msgBox.addClass('error');
			flag[0]=false;
		}else{
			var url="usernamecheck?name="+encodeURI($(obj).val())+"&"+new Date().getTime();
			//结果为真，表示该用户名可以使用；假则不行
			$.ajaxSettings.async = false;
			$.get(url,function(data){
				if(data=="false"){
					msgBox.html('该用户名已被注册');
					msgBox.addClass('error');
					flag[0]=false;
				}
				else{
					msgBox.html().removeClass('error');
					flag[0]=true;
				}
				
			});
		}
		
		break;
	case 'PASSWORD':
		if(obj.value==""){
			msgBox.html('密码不能为空');
			msgBox.addClass('error');
			flag[1]=false;
		}
		else{
			msgBox.html().removeClass('error');
			flag[1]=true;
		}
		break;
	case 'REPASSWORD':
		if(obj.value==""){
			msgBox.html('确认密码不能为空');
			msgBox.addClass('error');
			falg[2]=false;
		}
		else if($(obj).val()!=$('input[name="PASSWORD"]').val()){
			msgBox.html('两次输入的密码不一致');
			msgBox.addClass('error');
			flag[2]=false;
		}
		else{
			msgBox.html().removeClass('error');
			flag[2]=true;
		}
		break;
	case 'veryCode':
		var numshow=$(obj).next().next();
		
		if(obj.value==""){
			numshow.html('验证码不能为空');
			numshow.addClass('error');
			flag[3]=false;
		}
		else{
			var url="checkusernum?num="+encodeURI($(obj).val())+"&"+new Date().getTime();

			$.get(url,function(data){
				if(data=="false"){
					numshow.html('验证码错误');
					numshow.addClass('error');
					flag[3]=false;
				}
				else{
					numshow.html().removeClass('error');
					flag[3]=true;
				}
			});
		}
		break;
	}	
}

function checkForm(frm){
	//获取输入的整个表单，即用户注册时输入的所有信息
	var els=frm.getElementsByTagName('input');
	
	//在用户输入的信息中，只有带有onblur属性的数据才需要进行验证
	
	for(var i=0;i<els.length;i++){
		if(els[i]!=null)
			if(els[i].getAttribute("onblur")){
				CheckItem(els[i]);
			}
	}
	
	var n=flag[0]&&flat[1]&&flag[2]&&flag[3];
	
	return n;
}