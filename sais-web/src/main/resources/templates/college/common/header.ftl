<div class="container" style="margin-bottom: 20px">
    <div class="row clearfix">
        <div class="col-md-6 column">
            <a href="/college/index/index"><img src="/static/microblog/images/index_logo.png" /></a>
        </div>
        <div class="col-md-6 column">
            <form class="form-inline" role="form" id="search">
                <div class="form-group pull-right">
                    <input id="sousuo" class="form-control input-lg" placeholder="搜索你感兴趣的学校" style="margin-top: 18px;">
                    <button type="submit" class="btn btn-info btn-lg" style="width: 122px; margin-top: 18px; float:right">
                        <i class="fa fa-search" aria-hidden="true"></i>&nbsp&nbsp搜索</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $('#search').submit(function () {
        var sousuo=$("#sousuo").val();
        if(sousuo===""){
            layer.msg("请输入院校名信息");
            return false;
        }
        window.location.href="/college/college/search"+'?like='+sousuo;
        return false;
    });
</script>