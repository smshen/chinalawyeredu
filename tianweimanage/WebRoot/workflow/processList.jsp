<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--
function getSearch(){
     document.form1.action = "searchUser.action";
     document.form1.submit();
}
function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=document.form1.selectAll.checked;
               }
          }else{
               document.form1.check.checked=document.form1.selectAll.checked;
          }
     }
}
function getDelete(){
     if(noChecked()){
          alert("请选择记录，删除需要选择记录！");
          return false;
     }
     if (confirm("您确定要进行删除?")) {
          document.form1.action="processsDelete.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function fanye(str){
  document.from1.pageNo.value=str;
  document.from1.submit()
  return true;
}
-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">流程管理&gt;&gt;流程列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="processList" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline" align="center">
                        <TD>选择</TD>
                      <TD align="center">流程名称</TD>
                      <TD align="center">支撑业务</TD>
                      <TD align="center">创建时间</TD>
                      <TD align="center">状态</TD>
                      <TD align="center">创建者</TD>
                      <TD align="center">配置流程</TD>
                      <TD align="center">修改</TD>
                        <TD align="center">删除</TD>
                      </TR>
<s:iterator value="page.items" status="status">
                      <TR class=listline>
                        <TD align="center">
                        <INPUT type="checkbox" value='${processid}' name="check">
                        </TD>
                        <TD>${processname}</TD>
                        <TD align="left">${twflBusiness.businessname}</TD>
                        <TD><s:date name="createtime" format="yyyy-MM-dd"/></TD>
                        <TD>${statusid==1?"启用":"关闭"}</TD>
                        <TD><s:property value="@com.sxit.system.util.CommonDatas@usernameMap[authorid]"/></TD>
                        <TD>
<a href="processPicView.action?processid=${processid}">图形</a> 
<a href="nodeList.action?processid=${processid}">配置</a>
                        </TD>
                        <TD><a href="processEdit!input.action?processid=${processid}">修改</a></TD>
                         <TD><a href="processDelete.action?processid=${processid}">【删除】</a></TD>
                  </TR>
</s:iterator>
<s:if test="processlist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>                    <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colSpan=12 align="center">

  <div align="right" style="bgcolor:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
<div align="center">
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;
</div>
                       </TD>
                    </TR>
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>

<s:form name="createForm" action="processCreate!input.action" method="POST">
</s:form>
