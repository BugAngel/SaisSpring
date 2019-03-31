<@override name="body">
<div id="page-wrapper" class="gray-bg dashbard-1">
    <#include "../common/nav-header.ftl" >
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>用户管理</h5>
            </div>
            <div class="ibox-content">
                <form id="form" name = "form" class="form-horizontal m-t " method="post" action="/admin/user_list/editAction">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">账号：</label>
                        <div class="col-sm-3">
                            <input  type="text" id="account" class="form-control" name="account"  value=${user.account} >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否禁言：</label>
                        <input type="radio"  value="1"  name="comment"  <#if user.comment==1>checked </#if>/>
                        否
                        <input type="radio"  value="0"  name="comment"  <#if user.comment==0>checked </#if> />
                        是
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-3">
                            <button class="btn btn-primary" type="submit">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <#include "../common/footer.ftl" >
</div>

<script>
    $('form').submit(function(){
        var account = $('#account').val();
        if(account === ''){
            layer.msg('请填写账号',{time:1000});
            return false;
        }
        var url  = $(this).attr('action');
        var formData = $("#form").serialize();
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            data: formData,
            success: function (res) {
                if (res.status) {
                    layer.msg(res.message, {time: 1000}, function () {
                        window.location.href = "/admin/user_list/lists";
                    });
                } else {
                    layer.msg(res.message);
                }
            }
        });
        return false;
    });
</script>
</@override>
<@extends name="../common/common.ftl"/>