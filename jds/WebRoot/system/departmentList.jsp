<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
/**
 * <p>功能： 查看department列表</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-09-24</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<s:head theme="ajax" debug="true" />
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
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
          document.form1.action="departmentsDelete.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
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
              <td width="97%"><span class="sort-title">部门管理&gt;&gt;部门列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="departmentsDelete.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
                      <TR class="listheadline">
                        <TD>选择</TD>
                      <TD align="middle">部门名称</TD>
                      <TD align="middle">类型</TD>                      
                      <TD align="middle">上级部门</TD>
                      <TD align="middle">电话</TD>
                      <TD align="middle">联系人</TD>
                      <TD align="middle">时间</TD>
                        <TD align="middle">详细信息</TD>
                      </TR>
<s:iterator value="departmentlist" status="status">
                      <TR class=listline>
                        <TD align="middle">
                        <INPUT type="checkbox" value='${departmentid}' name="check">
                        </TD>
						<TD>${departmentname}</TD>
                        <TD>
                        ${type==0?"公司":""}
                        ${type==1?"一级部门":""}
                        ${type==2?"二级部门":""}
                        ${type==3?"三级部门":""}
						</TD>                        
                        <TD>${parent.departmentname}</TD>
                        <TD>${phone}</TD>
                        <TD>${linkman}</TD>
                        <TD><s:date name="createtime" format="yyyy-MM-dd"/></TD>
                        <TD><a href="departmentView.action?departmentid=${departmentid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="departmentlist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="middle"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>                    <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colSpan=12 align="middle">
<s:if test="recordsize>0">
  <div align="right" bgcolor="#FEF7E9">
  <span class="pt9-18">
   共<font color=red><b><s:property value="recordsize"/></b></font>记录
第<font color=red><b><s:property value="pagenumber+1"/></b></font>页/
共<font color=red><b><s:property value="pagesize"/></b></font>页
  </span>
  <s:if test="pagesize==1">
   <font color="#bbbbbb">
     首页 前页 后页 末页
   </font>
  </s:if>
  <s:else>
  <s:if test="pagenumber+1==1">
   <font color="#bbbbbb">
    首页 前页
   </font>
  </s:if>
  <s:else>
    <a href="javascript:page(0)">首页</a>
    <a href="javascript:page(<s:property value="pagenumber-1"/>)">前页</a>
  </s:else>
  <s:if test="pagenumber+1==pagesize">
   <font color="#bbbbbb">
    后页 末页
   </font>
  </s:if>
  <s:else>
    <a href="javascript:page(<s:property value="pagenumber+1"/>)">后页</a>
    <a href="javascript:page(<s:property value="pagesize-1"/>)">末页</a>
  </s:else>
</s:else>
</div>
</s:if>
                      </TD>
                    </TR>
                    <TR  class="pt9-18">
                      <TD colSpan=12 align="middle"><div align="center">
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;&nbsp;
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
<s:form name="pageForm" action="departmentList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="departmentCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
<s:form name="searchForm" action="departmentSearch.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
