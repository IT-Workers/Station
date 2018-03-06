
layui.use('jquery', function(){

    var $ = layui.jquery;

    //添加导航菜单
    $('#add-nav-menu').on('click', function(){
        layer.prompt(function(value, index, elem){
           // alert(value); //得到value
            if(value && value.length > 0){
                $.ajax({
                    type: 'POST',
                    url: '/admin/add/head-nav-menu',
                    data: {name:value},
                    dataType: "JSON",
                    success:function(data){
                        if(data.success){
                            location.reload();
                        }else{
                            layer.alert('添加失败！', {icon: 5});
                        }
                    }
                });
            }else{
                layer.msg('请输入导航菜单名称！');
            }
            layer.close(index);
        });
    });

    //导航编辑框
    $('.head-menus').on('click', function(){

        var id = $(this).attr('id');
        var name = $(this).attr('name');

        var html = '<input id="head-menu-name" type="text" value="'+name+'" placeholder="请输入导航名称" style="height: 30px; width: 85%">';

        layer.open({
            title:'编辑导航菜单'
            ,content: html
            ,btn: ['修改', '删除']
            ,yes: function(index, layero){
                var name = $('#head-menu-name').val();
                if(name && name.length > 0){
                    $.ajax({
                        type: 'POST',
                        url: '/admin/edit/head-nav-menu',
                        data: {id:id,name:name},
                        dataType: "JSON",
                        success:function(data){
                            if(data.success){
                                location.reload();
                            }else{
                                layer.alert('修改失败！', {icon: 5});
                            }
                        }
                    });
                }else{
                    layer.msg('请输入导航菜单名称！');
                }
            },btn2: function(index, layero){

                layer.confirm('您确认删除?', {icon: 2, title:'提示'}, function(index){
                    $.ajax({
                        type: 'POST',
                        url: '/admin/del/head-nav-menu',
                        data: {id:id,name:name},
                        dataType: "JSON",
                        success:function(data){
                            if(data.success){
                                location.reload();
                            }else{
                                layer.alert('删除失败！', {icon: 5});
                            }
                        }
                    });
                    layer.close(index);
                });
            }
        });
    });
});



