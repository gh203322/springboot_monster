//扩展时间格式化方法
Date.prototype.format = function(format){ 
	    var o =  { 
			    "M+" : this.getMonth()+1, //month 
			    "d+" : this.getDate(), //day 
			    "h+" : this.getHours(), //hour 
			    "m+" : this.getMinutes(), //minute 
			    "s+" : this.getSeconds(), //second 
			    "q+" : Math.floor((this.getMonth()+3)/3), //quarter 
			    "S" : this.getMilliseconds() //millisecond 
			 };
		    if(/(y+)/.test(format)){ 
		         format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		    }
		    for(var k in o)  { 
			     if(new RegExp("("+ k +")").test(format)){ 
			      format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
			} 
	    } 
	    return format; 
};

//把表单序列化为json数据
jQuery.fn.serializeForm = function () {
    var formData = {};
    var formArray = this.serializeArray();
    for (var i = 0, n = formArray.length; i < n; ++i) {
        formData[formArray[i].name] = formArray[i].value;
    }
    return formData;
};