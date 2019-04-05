<div class="showPage">
    <div id=page>
        <a href="${url}?pageNum=${firstPage}">首页</a>
        <#if pageNum - 1 gt 0>
            <a href="${url}?pageNum=${pageNum-1}">&laquo;</a>
        </#if>
        <#if pageNum - 2 gt 0>
            <a href="${url}?pageNum=${pageNum-2}">${pageNum-2}</a>
        </#if>
        <#if pageNum - 1 gt 0>
            <a href="${url}?pageNum=${pageNum-1}">${pageNum-1}</a>
        </#if>

        <a href="${url}?pageNum=${pageNum}">${pageNum}</a>

        <#if pageNum + 1 lte lastPage>
            <a href="${url}?pageNum=${pageNum+1}">${pageNum+1}</a>
        </#if>
        <#if pageNum + 2 lte lastPage>
            <a href="${url}?pageNum=${pageNum+2}">${pageNum+2}</a>
        </#if>
        <#if pageNum + 1 lte lastPage>
            <a href="${url}?pageNum=${pageNum+1}">&raquo;</a>
        </#if>
        <a href="${url}?pageNum=${lastPage}">尾页</a>
        <p class='pageRemark'>共<b>${pages}</b>页<b>${total}</b>条数据</p>
    </div>
    <div class="cl"></div>
</div>
