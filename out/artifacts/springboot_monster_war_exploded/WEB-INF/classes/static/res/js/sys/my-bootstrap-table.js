        //初始化表格
        var BtTable =  new Object();
    
      //初始化表格参数
	    BtTable.baseParam = {
									    url: "",                                                           // 获取数据的url
									    method: 'post',                                              //请求方式
									    toolbar: '#toolbar-Farmer',                             //工具按钮容器
									    contentType : "application/x-www-form-urlencoded",
									    dataType: 'json',                                            //数据类型
									    singleSelect: false,                                          //启用单选
									    cache: false,                                                  //启用缓存，默认true
									    striped: true,                                                 //表格显示条纹，默认为false
									    pagination: true,                                            //是否显示分页组件，默认false
									    pageSize: 10,                                                 //默认页面数据条数
									    pageList: [10, 25, 50, 100],                              //设置分页可选的条数
									    pageNumber: 1,                                            //默认当前页
									    pagination: true,                                           //是否启用分页
									    sidePagination : "server",                                //设置为服务器端分页，客户端为 client 
									    smartDisplay: true,                                         //快速渲染页面
									    showRefresh: true,                                         //显示刷新按钮
									    showFullscreen:  true,                                    //显示全屏按钮
									    showToggle : true,                                        //显示视图切换按钮
									    showColumns: true,                                       //显示所有的列的按钮
									    showExport: true,                                          //显示默认的导出按钮（需要js支持，见index.html）
									    exportTypes: ['doc', 'excel', 'xlsx', 'pdf'],           //默认导出的文件类型列表
									    exportDataType: "basic",                                //导出当前页还是所有数据 all 为所有
									    buttonsClass: 'primary',                                  //所有按钮样式
									    toolbar: '#toolbar',                                        //自定义工具栏
							            striped: true,                                                 //是否显示行间隔色
							            sortable:true,                                                //是否显示页面排序
							            sortOrder: "asc",                                            //排序方式
							            clickToSelect: true,                                         //是否启用点击选中行
							            uniqueId: "ID",                                               //每一行的唯一标识，一般为主键列
							            columns: [],                                                   //表格字段配置
									    queryParams: function (params) {                   // 请求服务器之前的回调函数，返回false则终止请求
									        return {
									            //在请求中添加分页参数
									            size: params.limit,                                   // 每页要显示的数据条数
									            page: (params.offset/params.limit),          // 当前页(对应数据库之后当前页应该少1)
									            sortOrder: params.order,                        //排序方式
                                                sortName: params.sort                           //排序字段
									        }
									    },
									    responseHandler:function(res){                        //得到服务器响应后的回调函数
									        //这里对应数据返回格式中的data部分(使用Page分页之后，数据被封装了一层，data上层为content)
									    	return {total:res.data.totalElements,rows:res.data.content};
									    },
									    onLoadError: function(){                                 //加载失败时的回调函数
									          console.info("数据加载失败");
									    }
							}
    
	    //初始化表格方法
	    BtTable.init = function(obj, param){
	    	   if(obj && param){
	    		       //表对象缓存下来
	    		       BtTable.table = $(obj);
	    		       
		    		   for (var key in param) {
				    	    BtTable.baseParam[key] = param[key];
					   }
		    		   BtTable.table.bootstrapTable(BtTable.baseParam);                         // obj为table对象
	    	   }
	    }
	
	    //初始化导出参数
	    BtTable.exportParam = {
	    		type:'xls',                          //导出文件格式 XLS XLSX DOC PDF CSV XML JSON PNG SQL TSV TXT
                fileName:'页面导出文件',    //导出文件名称
                ignoreColumn: []              //忽略导出的列,可以用字段名称，也可以用数组下标
		  }
	    
		//自定义导出，详细参考github https://github.com/hhurz/tableExport.jquery.plugin
		BtTable.exportFile = function(obj, param){
			   
	    	   if(obj){
		    		   for (var key in param) {
				    	    BtTable.exportParam[key] = param[key];
					   }
		    		   $(obj).tableExport(BtTable.exportParam);                             // obj为table对象
	    	   }
		}
	    
	    //获取所有选中行
		BtTable.getSelecttions = function(){
			
			   var ary = BtTable.table.bootstrapTable('getAllSelections');
				if(!ary ||  ary.length < 1){
					layer.msg('请至少选择一行');
					return;
				}
			   return ary;
		}
		
		//获取所有选中行id
		BtTable.getSelecttionIds = function(){
			
			   var ary = BtTable.table.bootstrapTable('getAllSelections');
			   if(!ary ||  ary.length < 1){
					 layer.msg('请至少选择一行');
				   return;
			   }
			   var idsAry = [];
			   for(var i = 0; i < ary.length; i++){
				   idsAry.push(ary[i].id);
			   }
			   
			   return idsAry;
		}
		
		//获取单个选中行
		BtTable.getSingleSelecttion = function(){
			
			   var ary = BtTable.table.bootstrapTable('getAllSelections');
			   if(!ary || ary.length < 1){
					 layer.msg('请至少选择一行');
				   return;
			   }
			   if(ary&&ary.length>1){
					 layer.msg('只能选择一行');
				   return;
			   }
			   return ary[0];
		}
		
		//获取单个选中行id
		BtTable.getSingleSelecttionId = function(){
			
			   var ary = BtTable.table.bootstrapTable('getAllSelections');
			   if(!ary || ary.length < 1){
					 layer.msg('请至少选择一行');
				   return;
			   }
			   if(ary&&ary.length>1){
					 layer.msg('只能选择一行');
				   return;
			   }
			   return ary[0].id;
		}
		
		//刷新表格
		BtTable.refresh = function(params){
			     BtTable.table.bootstrapTable("refresh", {
			        silent: true 
			      });
		}
		
		//带查询条件刷新表格
		BtTable.queryfresh = function(params){
			     if(!params){
			    	 params = {};
			     }
			     params.silent = true;  //静态刷新
			     BtTable.table.bootstrapTable("refresh", params);
		}