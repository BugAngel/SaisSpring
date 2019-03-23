<@override name="body">
<div id="page-wrapper" class="gray-bg dashbard-1">
    <#include "../common/nav-header.ftl" >
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>幻灯片管理</h5>
            </div>
            <div class="ibox-content">
                <form id="form" name = "form" class="form-horizontal m-t " method="post" enctype="multipart/form-data" action="/admin/slide_list/editAction">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">校英文名：</label>
                        <div class="col-sm-3">
                            <input  type="text" id="college_e_name" class="form-control" name="college_e_name" value="${slide.college_e_name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">学校简介：</label>
                        <div class="col-sm-3">
                            <textarea name="introduce" id="introduce" class="form-control" rows="7" >${slide.introduce}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">图片：</label>
                        <div class="col-sm-9">
                            <img width="160px" height="60px" src="/college/images/uploads/${slide.picture}" alt="图片">
                        </div>
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
        var college_e_name = $('#college_e_name').val();
        var introduce = $('#introduce').val();

        if(college_e_name === ''){
            layer.msg('请填写校英文名',{time:1000});
            return false;
        }
        if(introduce === ''){
            layer.msg('请填写学校简介',{time:1000});
            return false;
        }
    });
</script>
</@override>
<@extends name="../common/common.ftl"/>