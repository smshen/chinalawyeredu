<#escape x as (x)!"">
<div  class="popbox" id="idmessage">
<div class="box-orange">
	<h4>系统提示</h4>
	<div class="box-content">
		<p>${message}</p>
	</div>
	<div class="popboxbtn">
    	<#if redirectURL?length gt 0>
    	<input type="hidden" id="redirecturl" value="${redirectURL}" />
    	<button onclick="window.location.href='${redirectURL}'" class="submitBtn">确定</button>
    	<#else>
    	<button onclick="$.unblockUI();" class="cancerBtn">关闭</button>
    	</#if>
	</div>
</div>
</div>
</#escape>