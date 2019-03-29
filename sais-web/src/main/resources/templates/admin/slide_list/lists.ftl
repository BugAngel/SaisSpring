<@override name="body">
<div id="page-wrapper" class="gray-bg dashbard-1">
    <#include "../common/nav-header.ftl" >
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>幻灯片管理</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="input-button">
                                    <a href="/admin/slide_list/add">
                                        <button class="btn btn-primary add" type="button"><i class="fa fa-plus"></i>&nbsp;新增</button>
                                    </a>
                                    <button class="btn btn-warning delete-all" type="button" url="/admin/slide_list/delAll"><i
                                                class="fa fa-minus "></i>&nbsp;删除
                                    </button>
                                </div>
                            </div>
                            <!--搜索开始-->
                            <form method="post" action="/admin/slide_list/lists">
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入学校信息" class="input-sm form-control"
                                               name="keyword" value="${keyword}">
                                        <span class="input-group-btn">
                                                <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                                            </span>
                                    </div>
                                </div>
                            </form>
                            <!--搜索结束-->
                        </div>
                        <!--表格开始    -->
                        <form action="/admin/slide_list/lists" method="post" name="updateSort" id="updateSort">
                            <div class="table-responsive">
                                <table class="table  table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="width: 35px;">
                                            <input type="checkbox" id="checkAll" class="check-all">
                                            <label for="checkAll"></label>
                                        </th>
                                        <th>图片</th>
                                        <th>ID</th>
                                        <th>校英文名</th>
                                        <th>学校简介</th>
                                        <th style="width: 100px">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list datalists as vo>
                                    <tr>
                                        <td>
                                            <input class="ids regular-checkbox" type="checkbox" value="${vo.id}"
                                                   name="ids[]">
                                        </td>
                                        <td><img height="80px" width="160px" src="/upload/college/images/${vo.picture}" /></td>
                                        <td> ${vo.id}</td>
                                        <td> ${vo.college_e_name}</td>
                                        <td> ${vo.introduce}</td>
                                        <td>
                                            <a href="/admin/slide_list/edit?id=${vo.id}">编辑</a>
                                            <a class="confirm" href="/admin/slide_list/delete?id=${vo.id}">删除</a>
                                        </td>
                                    </tr>
                                    </#list>
                                    </tbody>
                                </table>
                                <!--分页开始-->
                                <#--{$datalists|raw}-->
                                <!--分页结束-->
                            </div>
                        </form>
                        <!--表格结束-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "../common/footer.ftl" >
</div>
</@override>
<@extends name="../common/common.ftl"/>