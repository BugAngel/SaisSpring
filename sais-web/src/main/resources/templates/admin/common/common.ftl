<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="icon" href="/static/common/images/icon.ico" type="images/x-ico" />
    <meta charset="utf-8">

    <title>WFT后台</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/layer/3.0.1/skin/default/layer.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >
    <link href="//cdn.bootcss.com/animate.css/3.5.2/animate.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/static/admin/css/admin-style.css" >
    <link rel="stylesheet" type="text/css" href="/static/admin/css/module.css"/>

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/layer/3.0.1/layer.min.js"></script>
    <script type="text/javascript" src="/static/admin/js/admin.js"></script>
</head>

<body>
<div id="wrapper">
    <#include "left.ftl" >
    <@block name="body" >父模板的 body</@block>

</div>
</body>
</html>