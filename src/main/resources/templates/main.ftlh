<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <form method="post">
            <input type="number" name="sumCd" placeholder="sumCd" value="${sumCd}">
            <input type="text" name="prcCd" placeholder="prcCd" value="${prcCd}">
            <input type="number" name="period" placeholder="period" value="${period}">
            <button type="submit"> add </button>
        </form>
    </div>
    <#if plan?has_content>
        <#list plan as plan>
            <div>
                <b>${plan.planData}</b>
                <span>${plan.percentSum}</span>
                <i>${plan.odSum}</i>
            </div>
            <#else>
            No plan
        </#list>
    </#if>
</@c.page>