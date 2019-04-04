<ul class="pagination pagination-lg">
    <li><a href="${url}?pageNum=${firstPage}">首页</a></li>
    <#if pageNum - 1 gt 0>
        <li><a href="${url}?pageNum=${pageNum-1}">&laquo;</a></li>
    </#if>
    <#if pageNum - 2 gt 0>
        <li><a href="${url}?pageNum=${pageNum-2}">${pageNum-2}</a></li>
    </#if>
    <#if pageNum - 1 gt 0>
        <li><a href="${url}?pageNum=${pageNum-1}">${pageNum-1}</a></li>
    </#if>

    <li><a href="${url}?pageNum=${pageNum}">${pageNum}</a></li>

    <#if pageNum + 1 lte lastPage>
        <li><a href="${url}?pageNum=${pageNum+1}">${pageNum+1}</a></li>
    </#if>
    <#if pageNum + 2 lte lastPage>
        <li><a href="${url}?pageNum=${pageNum+2}">${pageNum+2}</a></li>
    </#if>
    <#if pageNum + 1 lte lastPage>
        <li><a href="${url}?pageNum=${pageNum+1}">&raquo;</a></li>
    </#if>
    <li><a href="${url}?pageNum=${lastPage}">尾页</a></li>
    <li><a href="#">共${pages}页</a></li>
</ul>
