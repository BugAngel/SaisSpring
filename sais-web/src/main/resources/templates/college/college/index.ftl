<@override name="body">
<link rel="stylesheet" type="text/css" href="../../../static/college/css/college.css">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column" style="margin-bottom: 20px">
            <form id="college_info" method="post" action="/college/college/index" autocomplete="off" role="form" class="form-inline">
                <span style="font-family:宋体;color:#B45F04;font-weight:bold;font-size:10.5000pt;">国家：</span>
                <div class="form-group" style="width: 150px">
                    <select id="select_country" class="form-control">
                        <option value="all">全部</option>
                        <option value="US">美国</option>
                        <option value="UK">英国</option>
                    </select>
                </div>

                <span style="font-family:宋体;color:#B45F04;font-weight:bold;font-size:10.5000pt;">&nbsp;QS世界排名：</span>
                <div class="form-group" style="width: 300px">
                    <select id="select_qs_low" class="form-control">
                        <#list 1..141 as i>
                            <option value=${i}>${i}</option>
                        </#list>
                    </select>
                    <span class="glyphicon glyphicon-minus"></span>
                    <select id="select_qs_high" class="form-control">
                        <#list 1..141 as i>
                            <option value=${i}>${i}</option>
                        </#list>
                    </select>
                    <span style="font-family:宋体;color:#000000;font-weight:bold;font-size:10.5000pt;">名</span>
                </div>

                <span style="font-family:宋体;color:#B45F04;font-weight:bold;font-size:10.5000pt;">本国排名：</span>
                <div class="form-group" style="width: 300px">
                    <select id="select_major_low" class="form-control">
                        <#list 1..141 as i>
                            <option value=${i}>${i}</option>
                        </#list>
                    </select>
                    <span class="glyphicon glyphicon-minus"></span>
                    <select id="select_major_high" class="form-control">
                        <#list 1..141 as i>
                            <option value=${i}>${i}</option>
                        </#list>
                    </select>
                    <span style="font-family:宋体;color:#000000;font-weight:bold;font-size:10.5000pt;">名</span>
                </div>

                <button type="submit" class="btn btn-primary" style="width: 122px; float:right">
                    <i class="fa fa-search" aria-hidden="true"></i>&nbsp&nbsp确定</button>
            </form>
        </div>
        <div style='font-family: 微软雅黑;color: #666;font-size: 12px;'>
            <table width="" border="0" cellpadding="0" cellspacing="0" class="yuanxiao-table">
                <#list datalists as value>
                <tr>
                    <td width="550">
                        <a href="/college/college/detail?id=${value.id}" class="school-img clearfix">
                            <i>
                                <img width="45px;" height="45px;" src="../../../static/college/images/colleges/${value.icon}" />
                            </i>
                            <div class="txt">
                                <b>${value.college_name}</b>
                                <span>${value.college_e_name}</span>
                            </div>
                        </a>
                    </td>
                    <td width="150">
                        <span>QS世界排名：${value.qs_rank}</span>
                        <span>${value.local_rank_name}：${value.local_rank}</span>
                    </td>
                    <td width="150">
                        <span>录取率：<#if value.rate != 0> ${value.rate}%  <#else> 暂无 </#if></span>
                        <span>热门专业：${value.hot_major}</span>
                    </td>
                    <td width="195">
                    </td>
                    <td width="150">
                        <span>国家：${value.country}</span>
                        <span class="region">地区：${value.area}</span>
                    </td>
                </tr>
                </#list>
            </table>
            <#include "../common/page.ftl">
        </div>
    </div>
</div>

<script>
    $('#college_info').submit(function () {
        var countryValue=$("#select_country").val();
        var qsLowValue=$("#select_qs_low").val();
        var qsHighValue=$("#select_qs_high").val();
        var majorLowValue=$("#select_major_low").val();
        var majorHighValue=$("#select_major_high").val();

        if(parseInt(qsLowValue)>parseInt(qsHighValue)) {
            layer.msg("请输入正确的QS世界排名顺序");
            return false;
        }
        if(parseInt(majorLowValue)>parseInt(majorHighValue)) {
            layer.msg("请输入正确的本国排名顺序");
            return false;
        }
        setCookie(countryValue,qsLowValue,qsHighValue,majorLowValue,majorHighValue);
        window.location.href="/college/college/index"+'?country='+countryValue+'&qsLow='+qsLowValue+'&qsHigh='+qsHighValue+'&majorLow='+majorLowValue+'&majorHigh='+majorHighValue;
        return false;
    });
</script>

<script>
    function setCookie(countryValue,qsLowValue,qsHighValue,majorLowValue,majorHighValue){
        $.cookie('country', countryValue);
        $.cookie('qsLow', qsLowValue);
        $.cookie('qsHigh', qsHighValue);
        $.cookie('majorLow', majorLowValue);
        $.cookie('majorHigh', majorHighValue);
    }

    function GetRequest() {
        var url = window.location.search; //获取url中"?"符后的字串
        if (url.indexOf("?") !== -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for ( var i = 0; i < strs.length; i++) {
                $.cookie(strs[i].split("=")[0], strs[i].split("=")[1]);
            }
        }
    }

    window.onload=function(){
        GetRequest();
        if($.cookie('country')!=null && $.cookie('country')!=='null'){
            $("#select_country").val($.cookie('country'));
        }else{
            $("#select_country").val("all");
        }
        if($.cookie('qsLow')!=null && $.cookie('qsLow')!=='null'){
            $("#select_qs_low").val($.cookie('qsLow'));
        }else{
            $("#select_qs_low").val("1");
        }
        if($.cookie('qsHigh')!=null && $.cookie('qsHigh')!=='null'){
            $("#select_qs_high").val($.cookie('qsHigh'));
        }else{
            $("#select_qs_high").val("140");
        }
        if($.cookie('majorLow')!=null && $.cookie('majorLow')!=='null'){
            $("#select_major_low").val($.cookie('majorLow'));
        }else{
            $("#select_major_low").val("1");
        }
        if($.cookie('majorHigh')!=null && $.cookie('majorHigh')!=='null'){
            $("#select_major_high").val($.cookie('majorHigh'));
        }else{
            $("#select_major_high").val("140");
        }
    };
</script>
</@override>
<@extends name="../common/common.ftl"/>