<@override name="body">
<link rel="stylesheet" type="text/css" href="../../../static/college/css/college.css">
<style>
    .schools-banner{ width: 960px; height: 400px; background: url(/upload/${banner})  center center no-repeat; background-size: 100% 100%; margin:0px auto 0; position: relative;}
</style>

<div class="container" style="margin-bottom: 20px">
    <div id="bg" class="row clearfix bg">
        <div class="col-md-12 column">
            <div class="schools-banner">
                <div class="school-ab">
                    <i><img width="90px" height="90px" src="../../../static/college/images/colleges/${college_detail.icon}"/></i>
                    <b>${college_detail.college_name}</b>
                    <span>${college_detail.college_e_name}</span>
                    <div class="list">
                        <em>QS世界排名：${college_detail.qs_rank}</em>
                        <em>${college_detail.local_rank_name}：${college_detail.local_rank}</em>
                        <em>录取率： <#if college_detail.rate!=0> ${college_detail.rate} </#if></em>
                        <em>热门专业：${college_detail.hot_major}</em>
                        <em>国家：${college_detail.country}</em>
                        <em>地区：${college_detail.area}</em>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul id="myTab" class="nav nav-tabs" style="margin-bottom: 20px">
                <li class="active"><a href="#introduce" data-toggle="tab">学校介绍</a></li>
                <li><a href="#apply" data-toggle="tab">申请信息</a></li>
                <li><a href="#profession" data-toggle="tab">专业设置</a></li>
                <li><a href="#fee" data-toggle="tab">费用说明</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="introduce">
                    <p style="font-size: 20px;font-family: 楷体">${college_detail.introduce}</p>
                    <br>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>总学生数</th>
                            <th>本科学生数</th>
                            <th>研究生学生数</th>
                            <th>师生比例</th>
                            <th>国际生比例（本科）</th>
                            <th>国际生比例（研究生）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td>${college_detail.sum}</td>
                            <td>${college_detail.undergraduate}</td>
                            <td>${college_detail.graduate}</td>
                            <td>1:${college_detail.student_staff_ratio}</td>
                            <td><#if college_detail.undergraduate_international_proportion != 0>${college_detail.undergraduate_international_proportion} </#if> </td>
                            <td><#if college_detail.graduate_international_proportion!= 0>${college_detail.graduate_international_proportion} </#if> </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="apply">
                    <h3 style="font-family: 楷体;color: darkorange" align="center">本科</h3>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>平均GPA分数</th>
                            <th>SAT分数</th>
                            <th><#if college_detail.country =="美国"> TOEFL录取最低分： <#else> IELTS录取最低分： </#if></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td><#if college_detail.undergraduate_gpa != 6>${college_detail.undergraduate_gpa}</#if></td>
                            <td><#if college_detail.sat != 9999>${college_detail.sat}</#if></td>
                            <td>${college_detail.undergraduate_language}</td>
                        </tr>
                        </tbody>
                    </table>
                    <h4 style="font-family: 楷体;color: red" align="left">申请材料</h4>
                    <div class="two-column" style="font-family: 楷体;font-size: 20px ">
                        <ol>
                            <#list college_detail.undergraduate_document as value>
                            <li>${value}</li>
                            </#list>
                        </ol>
                    </div>
                    <br>
                    <h3 style="font-family: 楷体;color: darkorange" align="center">研究生</h3>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>平均GPA分数</th>
                            <th><#if college_detail.country =="美国"> TOEFL录取最低分： <#else> IELTS录取最低分： </#if></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td><#if college_detail.graduate_gpa != 6>${college_detail.graduate_gpa}</#if></td>
                            <td>${college_detail.graduate_language}</td>
                        </tr>
                        </tbody>
                    </table>
                    <h4 style="font-family: 楷体;color: red" align="left">申请材料</h4>
                    <div class="two-column" style="font-family: 楷体;font-size: 20px;">
                        <ol>
                            <#list college_detail.graduate_document as value>
                            <li>${value}</li>
                            </#list>
                        </ol>
                    </div>
                    <br>
                    <h3 style="font-family: 楷体;color: darkorange; " align="center">申请截止时间</h3>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>EA申请截止日期</th>
                            <th>RD申请截止日期</th>
                            <th>转学申请截止日期</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td>${college_detail.ea}</td>
                            <td>${college_detail.rd }</td>
                            <td>${college_detail.transfer}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="profession">
                    <div class="four-column" style="font-family: 楷体;font-size: 20px;">
                        <ol>
                            <#list college_detail.profession as value>
                            <li>${value}</li>
                            </#list>
                        </ol>
                    </div>
                </div>
                <div class="tab-pane fade" id="fee">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>学费</th>
                            <th>生活费</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td>${college_detail.tuition_fee}</td>
                            <td>${college_detail.living_fee}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="../common/common.ftl"/>