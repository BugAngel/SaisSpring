<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="icon" href="/static/common/images/icon.ico" type="images/x-ico" />
    <meta charset="utf-8">

    <title>WFT后台</title>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" >
    <link rel="stylesheet" type="text/css" href="/webjars/animate.css/3.5.2/animate.min.css" >
    <link rel="stylesheet" type="text/css" href="/static/admin/css/admin-style.css" >
    <link rel="stylesheet" type="text/css" href="/static/admin/css/module.css"/>

    <script src="/webjars/jquery/2.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="/static/common/layer/layer.js"></script>
    <script type="text/javascript" src="/static/admin/js/admin.js"></script>
</head>

<body>
<div id="wrapper">
    <#include "left.ftl" >
    <@block name="body" >父模板的 body</@block>

</div>
</body>
</html>