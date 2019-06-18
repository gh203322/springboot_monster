/*
* 扩展时间格式化方法
* */
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

/*
* 把表单序列化为json数据
* */
jQuery.fn.serializeForm = function () {
    var formData = {};
    var formArray = this.serializeArray();
    for (var i = 0, n = formArray.length; i < n; ++i) {
        formData[formArray[i].name] = formArray[i].value;
    }
    return formData;
};

/*
* 封装localStorae set get 方法
* */
//从localstorage保存键值
function lget(key){
       return window.localStorage.getItem(key);
};
//从localstorage获取键值
function lset(key, value) {
       window.localStorage.setItem(key, value);
};
//从localstorage保存json
function getJson(flag, key){
       var disk = lget(flag);
       if (disk){
       	     disk = JSON.parse(disk);
       	     if (disk){
       	     	     return disk[key];
						 }
			 }
};
//从localstorage获取json
function setJson(flag, key, value) {
				var disk = lget(flag);
				if (disk){
					  disk = JSON.parse(disk);
				}else{
					  disk = {};
				}
				if (disk){
						disk[key] = value;
						lset(flag,  JSON.stringify(disk));
				}
};

/*
* 封装常用的 layer 提示方法
* */
//成功提示
function lyrok(content){
				layer.alert(content, {
					  title:'提示',
						icon: 6,
						skin: 'layer-ext-moon'
				})
}
//失败提示
function lyrfail(content){
				layer.alert(content, {
					title:'提示',
					icon: 5,
					skin: 'layer-ext-moon'
				})
}
//警告提示
function lyrwarn(content){
				layer.alert(content, {
					title:'提示',
					icon: 7,
					skin: 'layer-ext-moon'
				})
}