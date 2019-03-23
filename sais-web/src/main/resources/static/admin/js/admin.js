/**
 * Created by andy on 2016/6/6.
 */

//dom 加载完成后执行的js
$(function(){
    //单选删除
    $('td .confirm').click(function(){
        var url = $(this).attr('href');
        layer.confirm('确认要执行删除操作吗？',function(){
            $.ajax({
                type: "GET",
                url: url,
                success: function(message){
                    layer.msg(message,{time:2000},function(){
                        window.location.reload();
                    });
                }
            });
        });
        return false;
    });

    //点击选择全部
    $(".check-all").click(function(){
        $(".ids").prop("checked", this.checked);
    });
    //选择多个
    $(".ids").click(function(){
        var option = $(".ids");
        option.each(function(i){
            if(!this.checked){
                $(".check-all").prop("checked", false);
                return false;
            }else{
                $(".check-all").prop("checked", true);
            }
        });
    });

    //全选删除
    $('.delete-all').click(function(){
        var ids = $('.ids:checked');
        if(ids.length == 0){
            layer.msg('请选择要删除的选项',{time:1000});
            return false;
        }
        var url = $(this).attr('url');
        layer.confirm('确认要执行删除操作吗？',function(){
            var query = ids.serialize();
            $.ajax({
                type: "POST",
                url: url,
                data: query,
                success: function(message){
                    layer.msg(message,{time:1000},function(){
                        window.location.reload();
                    });
                }
            });
        });
        return false;
    });





});
