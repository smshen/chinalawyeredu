<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=com.changpeng.common.Constants.SYS_NAME%>
</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript" type="text/javascript">
 
var issubmit=false;
function submitform (){
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
  if(!issubmit){//如果没有提交,直接关闭窗口的话
  
    $.getJSON("../commonajax/ajaxlogout.pl", {"now":new Date().getTime()}, function(resp){
      }
    ); 
  }
}
</script>
</head>

<body leftmargin="0" topmargin="0" onunload="exitform()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <!-- <td  align="right" background="../imagesa/top-bg1.jpg">-->
     <td  align="right" background="${resourcePath }${topbarpic }">
     <form name="form1" method="post" action="../common/logout.pl" target="_top">
       <table width="28%" border="0" cellspacing="0" cellpadding="0" background="../imagesa/bgright.jpg">
        <tr> 
         	
          <td height="26"  align="right">
                 <a href="../index/workspace.pl" target="mainFrame">返回首页</a> ｜
                 <s:if test="lawyer.provinceunion!=22">
                 <a href="../common/passwdChange!input.pl" target="mainFrame">修改密码</a> ｜
                 </s:if>
                  <s:if test="lawyer.roleid!=null&&lawyer.roleid==1">
                 <a href="../shouce/lawyers.html" target="_blank"> 操作手册</a> ｜
                 </s:if>
                 <s:if test="isofficeadminer">
                 <a href="../shouce/offices.html" target="_blank"> 操作手册</a> ｜
                 </s:if>
                 <a href="#" onclick="submitform()">【退出】</a>         
          </td> 
       
        </tr>
        <tr>
          <td height="24" align="right">
               ${lawyer.lawyername} 您好,欢迎登录培训系统！  
          </td>
        </tr>
</table>
   </form>
    </td>
  </tr>
</table>
<table width="100%" height="2" border="0" cellpadding="0" cellspacing="0" bgcolor="eeeeee">
  <tr>
    <td> </td>
  </tr>
</table>
</body>
</html>