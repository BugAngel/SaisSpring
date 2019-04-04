<@override name="body">
<div id="page-wrapper" class="gray-bg dashbard-1">
    <#include "../common/nav-header.ftl" >
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>用户管理</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="input-button">
                                    <button class="btn btn-warning delete-all" type="button" url="/admin/user_list/delAll"><i
                                                class="fa fa-minus "></i>&nbsp;删除
                                    </button>
                                </div>
                            </div>
                            <!--搜索开始-->
                            <form method="post" action="/admin/user_list/lists">
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入账号" class="input-sm form-control"
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
                        <form action="/admin/user_list/lists" method="post" name="updateSort" id="updateSort">
                            <div class="table-responsive">
                                <table class="table  table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="width: 35px;">
                                            <input type="checkbox" id="checkAll" class="check-all">
                                            <label for="checkAll"></label>
                                        </th>
                                        <th>ID</th>
                                        <th>账号</th>
                                        <th>昵称</th>
                                        <th>最近登录IP</th>
                                        <th>最近登录时间</th>
                                        <th>注册时间</th>
                                        <th>是否禁言</th>
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
                                            <td> ${vo.id}</td>
                                            <td> ${vo.account}</td>
                                            <td> ${vo.nickname}</td>
                                            <td> ${vo.loginip}</td>
                                            <td> ${vo.logintime}</td>
                                            <td> ${vo.addtime}</td>
                                            <td> <#if vo.comment == 1>否<#else>是</#if></td>
                                            <td>
                                                <a href="/admin/user_list/edit?id=${vo.id}">编辑</a>
                                                <a class="confirm" href="/admin/user_list/delete?id=${vo.id}">删除</a>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                                <!--分页开始-->
                                <#include "../common/page.ftl">
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