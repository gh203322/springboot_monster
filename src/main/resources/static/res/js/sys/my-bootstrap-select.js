			//定义下拉框对象
			var BtSelect =  new Object();
    
      //请求参数
      BtSelect.queryParam = {
											url: '',
											type: 'post',
											dataType: 'json',
											contentType: 'application/json; charset=utf-8',
											data: {},
											success: function(data){
												var html = "";
												if(BtSelect.baseParam.showNoChoice){
													html = "<option value=''>请选择</option>";
												}
												if(data.state){
													var content = data.data;
													for (var i=0; i<content.length; i++){
																html += "<option value='"+content[i][BtSelect.baseParam.sltkey]+"'>"+content[i][BtSelect.baseParam.sltvalue]+"</option>";
																BtSelect.select.html(html);
													}
												}else{
													BtSelect.select.html(html);
												}
												BtSelect.select.selectpicker('refresh');
												//判断是否默认选中
												if(BtSelect.baseParam.doomedChoice
													 &&BtSelect.select.attr("doomed")){
													 BtSelect.select.selectpicker('val', BtSelect.select.attr("doomed"));
												}
											}
			}

			//基本参数
			BtSelect.baseParam = {
      	   sltkey: "key",
				   sltvalue: "value",
				   showNoChoice: false,  //是否默认显示一个空行
				   doomedChoice: false, //通过在select中添加属性doomed，该属性值就是select的默认选中值，true默认选中，false忽略
      	   queryParam: BtSelect.queryParam
			}
    
	    //初始化表格方法
			BtSelect.init = function(obj, param){
	    	   if(obj && param){
						   $(obj).selectpicker();
							 //表对象缓存下来
							 BtSelect.select = $(obj);
	    		       
		    		   for (var key in param) {
								 BtSelect.baseParam[key] = param[key];
		    		   }
		    		   if (BtSelect.baseParam.url){
								  BtSelect.queryParam.url = BtSelect.baseParam.url;
							 }

						   BtSelect.query();
	    	   }
	    }

			//请求参数
			BtSelect.query = function(param){
				if(param){
					for (var key in param) {
						BtSelect.queryParam[key] = param[key];
					}
				}
				if(BtSelect.queryParam.url){
					$.ajax(
					   BtSelect.queryParam
					);
				}
			}