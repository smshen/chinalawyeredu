<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-2/3G流量分析</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript" src="../js/swfobject.js"></script>
 <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
 <script type="text/javascript">
  $(document).ready(function(){
 $("#tableOrder").tablesorter();
 });
 
 
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
  document.form1.resultType.value="excel";
  document.form1.submit();
}
function queryit(){
  document.form1.resultType.value="list";
  document.form1.submit();
}
function imageit(){
 if(ishide){
  $("#imageopton").show();
  ishide=false;
  }else{
     $("#imageopton").hide();
      $("#imgreport").hide();
     ishide=true;
  }
}
var ishide=true;
$(document).ready(function(){
  $("#imageopton").hide();
  $("#imgreport").hide();
  ishide=true;
});
function confirmit(){
   $("#imgreport").show();
   var flashType=$("#flashType").val();
   var flashby=$("#flashby").val();
   var nettype=$("#nettype").val();
      var start=$("#start").val();
   var end=$("#end").val();
   var url="stream23g.action?start="+start+"%26end="+end+"%26resultType=flash%26flashby="+flashby+"%26flashType="+flashType+"%26nettype="+nettype;
   swfobject.embedSWF("../open-flash-chart.swf", "barchart", "500", "300", "9.0.0","",{"data-file":url,"loading":"正在载入数据..."} );
   //alert(url);

}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>统计分析</a>＞<em>业务全貌</em>＞<em>2/3G流量分析</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="stream23g" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                 <s:hidden name="pageNo"/>
                                  <s:hidden name="resultType"/>
							 <td>起始日期：<jscalendar:jscalendar name="start" cssClass="txt"/></td>
								 <td>结束日期：<jscalendar:jscalendar name="end" cssClass="txt"/></td>
									 <td><input type="button" class="btnSubmit" title="查询" value="查询" onclick="queryit()"/></td>
								 <td><input type="button" class="btnSubmit" title="图 形" value="图形"  onclick="imageit()"/></td>
								 <td id="imageopton">
								   <s:select name="flashType" id="flashType" list="#{'line':'曲线图','bar':'柱状图'}" label="类型"></s:select>
								   <s:select name="flashby" id="flashby" list="#{'total':'总流量','user':'总用户数','average':'平均流量'}" label="维度"></s:select>
								   <s:select name="nettype" id="nettype" list="#{'gsm':'GSM','td':'TD'}" label="网络类型"></s:select>
								   <input type="button" class="btnSubmit" value="确 认"  onclick="confirmit()" id="flashconfirm"/>
								 </td>
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>-->
				  <div  class="tablist" style="text-align:center" id="imgreport">
                    <div id="barchart"></div>
                    </div>
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="tableOrder">
                      <thead>
                        <tr>
                       
                          <th>日期</th>
                          <th>用户类型</th>
                          <th>总流量（M）</th>
                                 <th>上行流量（M）</th>
                            <th>下行流量（M）</th>
                          <th>总用户数</th>
                          <th>平均流量（K）</th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="streamlist" status="stat">
                        <tr>
                     <!--     <s:if test="#stat.odd">
                          <td rowspan="2">${date}</td>
                          </s:if>-->
                          <td>${date}</td>
                          <td>${nettype}</td>
                          <td>${totalStreamStr }</td>
                             <td>${upvolumeStr }</td>
                          <td>${downvolumeStr }</td>
                          <td>${totalUser}</td>
                          <td>${averageStreamStr}</td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			  
                    </table>
			  </div>
<!-- 				<div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>-->
				</div>
			</div>
		</div>
</s:form>
</body>

</html>

