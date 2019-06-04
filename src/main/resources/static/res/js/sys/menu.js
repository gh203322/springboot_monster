      /*
       * 创建菜单树
       * */
      function createMenu(dataAry){
          if(dataAry){
              var menuTreeHtml = "";
              for(var i=0; i < dataAry.length; i++){
                  menuTreeHtml += createMenuNode(dataAry[i]);
              }
              $(".sidebar-menu").html(menuTreeHtml);
          }
      }

      /*
       * 创建菜单节点
       * */
      function createMenuNode(data){
        //console.table(data);
        if(!data){
          return "";
        }
        //如果是隐藏节点
        if(data.isShow == 0){
          return "";
        }

          var tree_style ="treeview ";//默认为根节点
          var shell_class ="fa fa-th";//默认样式
          var shell_name ="无菜单名称";//默认菜单名称
          var child_num = 0;//默认子菜单数量
          var childs = data.childs;//子菜单
          var href = "#";
          var html = "";

          //是否是叶子节点
          if(data.isLeaf == 1){
             tree_style = "";
          }
          //是否展开
          if(data.isOpen == 1){
             tree_style += "active menu-open";
          }
          //图标样式
          if(data.css){
            shell_class = data.css;
          }else{
            //是否是根节点(根节点默认样式为：fa fa-th)
            if(data.isBoot == 1){
              shell_class = "fa fa-th";
            }else{
              shell_class = "fa fa-circle-o text-black";
            }
          }
          //菜单名称
          if(data.name){
            shell_name = data.name;
          }
          //菜单对应的htm页面
          if(data.href){
            href = data.href;
          }
          //子菜单个数
          if(data.childNum){
            child_num = data.childNum;
          }

           html += '<li class="'+tree_style+'">';
           html += '<a href="#" href-data="'+href+'"><i class="'+shell_class+'"></i>';
           html += '<span>'+shell_name+'</span>';
           html += '<span class="pull-right-container">';
           html += '<i class="fa fa-angle-left pull-right"></i>';
           html += '<small class="label pull-right bg-red">'+child_num+'</small>';
           html += '</span>';
           html += '</a>';
           html += '<ul class="treeview-menu">';

           if(childs){
               for(var i=0; i<childs.length; i++){
                 //如果是隐藏节点
                 if(data.isShow == 0){
                   continue;
                 }
                 if(childs[i].childs&&childs[i].childs.length > 0){
                   //可展开节点(递归)
                   html += createMenuNode(childs[i]);
                 }else{
                   //普通节点
                   //没有配置样式则按照默认样式
                   var leafCss = childs[i].css;
                   if(!leafCss){
                     if((i+1)%4 == 1) leafCss = "fa fa-leaf text-red";
                     else if((i+1)%4 == 2) leafCss = "fa fa-leaf text-yellow";
                     else if((i+1)%4 == 3) leafCss = "fa fa-leaf text-aqua";
                     else if((i+1)%4 == 0) leafCss = "fa fa-leaf text-green";
                   }
                   html += '<li><a href="javascript:openContent(\''+childs[i].href+'\');"><i class="'+leafCss+'"></i>';
                   html += '<span>'+childs[i].name+'</span></a></li>';
                 }
               }
           }

           html += '</ul>';
           html += '</li>';

         return  html;
      }

      /*
       * 加载菜单数据
       * */
      $(function(){
          $.ajax({
                url: '/auth/menu/getMenuTreeByQuery',
                type: 'post',
                data: {},
                dataType: 'json',
                success: function(data){
                    if(data.state){
                        createMenu(data.data);
                    }
                },
                fail: {
                
                }
          })
      });
      
      /*
       * 打开菜单对应的页面
       * */
      function openContent(href){
    	  if(href){
    		  $('.content').load("/auth/menu/openContent",{"href":href});
    	  }
      }
      
      
      