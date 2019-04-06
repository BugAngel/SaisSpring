<@override name="body">
<link rel="stylesheet" type="text/css" href="../../../static/college/css/college.css">
<div class="container">
    <div class="row clearfix">
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
                    <span>录取率：<#if value.rate != 0> ${value.rate}  <#else> 暂无 </#if></span>
                    <span>热门专业：${value.hot_major} </span>
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
        </div>
    </div>
</div>
</@override>
<@extends name="../common/common.ftl"/>