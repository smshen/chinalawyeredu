<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<title>新版中国律师培训网-在线课程中心</title>	
	<link href="../css/css_new.css" rel="stylesheet" type="text/css" />

<script language="javascript" id="clientEventHandlersJS">
<!--
var number=8;

function LMYC() {
var lbmc;
//var treePic;
    for (i=1;i<=number;i++) {
        lbmc = eval('LM' + i);
        //treePic = eval('treePic'+i);
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
}
 
function ShowFLT(i) {
    lbmc = eval('LM' + i);
    //treePic = eval('treePic' + i)
    if (lbmc.style.display == 'none') {
        LMYC();
        //treePic.src = 'images/nofile.gif';
        lbmc.style.display = '';
    }
    else {
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
}
//-->
      </script>
</head>

<body >
<div class="header">
  <div class="logo left"><a href="index.html"><img src="../images/logo.gif" width="234" height="51" /></a></div>
  <div class="denglu right"> ${lawyer.lawyername}，您好，欢迎您登录培训平台！&nbsp;&nbsp;<a href="#">首页</a>&nbsp;&nbsp;<a href="#">注销</a>&nbsp;&nbsp;<a href="#">退出</a></div>
</div>
<div class="blank15px"></div>
<div class="nav2"><ul>
  <li><a href="lessoncenter.html" class="current">选课中心</a></li>
  <li><a href="course.html">我选购的课程</a></li>
  <li><a href="notice.html">培训通知</a></li>
  <li><a href="jifen.html">我的学分</a></li>
  <li><a href="favorites.html">收藏夹</a></li>
  <li><a href="shopping.html">购物车</a></li>
  <li><a href="order.html">我的订单</a></li>
  <li><a href="news.html">我的消息</a></li>
  <li><a href="application.html">转所申请</a></li>
  <li><a href="informtion.html">个人信息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="index.html">首页</a>----<a href="lessoncenter.html"><strong>选课中心</strong></a></div>
<div class="con">
  <div class="con_left2 left">
     <div class="con_left2_title">选课中心</div>   
     
     <TABLE cellSpacing=0 cellPadding=0 width="88%" border=0>
        <TBODY>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(1) 
                  href="javascript:void(null)">民商事法律业务（180个）</A> </TD>
          </TR>
          <TR id=LM1 style="DISPLAY: none">
          <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=文件夹 
                        href="http://www.cnzz.cn/" 
                        target=_parent>民事诉讼</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=资料册 
                        href="http://www.cnzz.cn/" 
                        target=_parent>合同担保法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=档案盒 
                        href="http://www.cnzz.cn/" 
                        target=_parent>侵权法</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=文件盒 
                        href="http://www.cnzz.cn/" 
                        target=_parent>物权法</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=文件柜 
                        href="http://www.cnzz.cn/" 
                        target=_parent>公司证券法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>知识产权法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>建筑房地产法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>破产法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>国有资产法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>金融保险法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>涉外法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>劳动关系法律业务</A> </TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=公文包 
                        href="http://www.cnzz.cn/" 
                        target=_parent>其他（未分类）</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                </TBODY>
            </TABLE></TD>
          </TR>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(2) 
                  href="javascript:void(null)">刑事法律业务（120个）</A> </TD>
          </TR>
          <TR id=LM2 style="DISPLAY: none">
            <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=削笔机 
                        href="http://www.cnzz.cn/" 
                        target=_parent>削笔机</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=订书机 
                        href="http://www.cnzz.cn/" 
                        target=_parent>订书机</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                </TBODY>
            </TABLE></TD>
          </TR>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(3) 
                  href="javascript:void(null)">行政法律业务（69个）</A> </TD>
          </TR>
          <TR id=LM3 style="DISPLAY: none">
            <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=碎纸机 
                        href="http://www.cnzz.cn/" 
                        target=_parent>碎纸机</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=切纸刀 
                        href="http://www.cnzz.cn/" 
                        target=_parent>切纸刀</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                </TBODY>
            </TABLE></TD>
          </TR>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(4) 
                  href="javascript:void(null)">执行法律业务（36个）</A> </TD>
          </TR>
          <TR id=LM4>
            <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=中性笔 
                        href="http://www.cnzz.cn/" 
                        target=_parent>中性笔</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=圆珠笔 
                        href="http://www.cnzz.cn/" 
                        target=_parent>圆珠笔</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=记号笔 
                        href="http://www.cnzz.cn/" 
                        target=_parent>记号笔</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=白板笔 
                        href="http://www.cnzz.cn/" 
                        target=_parent>白板笔</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                </TBODY>
            </TABLE></TD>
          </TR>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(5) 
                  href="javascript:void(null)">律师事务所管理（28个）</A> </TD>
          </TR>
          <TR id=LM5 style="DISPLAY: none">
            <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=传真纸 
                        href="http://www.cnzz.cn/" 
                        target=_parent>传真纸</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=复印纸 
                        href="http://www.cnzz.cn/" 
                        target=_parent>复印纸</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=复写纸 
                        href="http://www.cnzz.cn/" 
                        target=_parent>复写纸</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=便条纸 
                        href="http://www.cnzz.cn/" 
                        target=_parent>便条纸</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=百事贴 
                        href="http://www.cnzz.cn/" 
                        target=_parent>百事贴</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=皮面笔记本 
                        href="http://www.cnzz.cn/" 
                        target=_parent>皮面笔记本</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                </TBODY>
            </TABLE></TD>
          </TR>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(6) 
                  href="javascript:void(null)">推广及品牌宣传（35个）</A> </TD>
          </TR>
          <TR id=LM6 style="DISPLAY: none">
            <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=计算器 
                        href="http://www.cnzz.cn/" 
                        target=_parent>计算器</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                </TBODY>
            </TABLE></TD>
          </TR>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(7) 
                  href="javascript:void(null)">思想政治教育（12个）</A> </TD>
          </TR>
          <TR id=LM7 style="DISPLAY: none">
            <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=阅读架 
                        href="http://www.cnzz.cn/" 
                        target=_parent>阅读架</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=CD保护 
                        href="http://www.cnzz.cn/" 
                        target=_parent>CD保护</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=光盘盒 
                        href="http://www.cnzz.cn/" 
                        target=_parent>光盘盒</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                </TBODY>
            </TABLE></TD>
          </TR>
          <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> <A onclick=javascript:ShowFLT(8) 
                  href="javascript:void(null)">其他（36个）</A> </TD>
          </TR>
          <TR id=LM8 style="DISPLAY: none">
            <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=清洁桶 
                        href="http://www.cnzz.cn/" 
                        target=_parent>清洁桶</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=白板 
                        href="http://www.cnzz.cn/" 
                        target=_parent>白板</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=网状文具 
                        href="http://www.cnzz.cn/" 
                        target=_parent>网状文具</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> <A title=封箱器 
                        href="http://www.cnzz.cn/" 
                        target=_parent>封箱器</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                  
                  
                </TBODY>
            </TABLE></TD>
          </TR>
        </TBODY>
    </TABLE>
     
   
  </div>
  <div class="con_right2 left">
    <div class="con_right2_title"><h2>分类：<a href="#">全部</a></h2><span><a href="#">详细显示</a>&nbsp;&nbsp;<a href="#">列表显示</a></span></div>
    <div class="star">
	    <div class="startitle">
排序：<a href="#">按热门</a>&nbsp;&nbsp;<a href="#">按年份</a>&nbsp;&nbsp;<a href="#">按评分</a>&nbsp;&nbsp;&nbsp;&nbsp;显示：<a href="#">全部</a>&nbsp; <a href="#">必修</a>&nbsp; <a href="#">选修</a>&nbsp; <a href="#">免费</a>&nbsp; <a href="#">收费</a></div>
	<div class="t_n">
	<a href="#"><img src="../images/ico2.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a>&nbsp;<a href="#">选购（￥50）</a></div></div>
    </div>
    <div class="t_n">
	<a href="#"><img src="../images/ico3.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a></div></div>
    </div>
    <div class="t_n">
	<a href="#"><img src="../images/ico4.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a></div></div>
    </div>
    <div class="t_n">
	<a href="#"><img src="../images/ico5.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a>&nbsp;<a href="#">选购（￥50）</a></div></div>
    </div>
    <div class="t_n">
	<a href="#"><img src="../images/ico6.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a href="#" >收藏</a>&nbsp;<a href="#">选购（￥50）</a></div></div>
    </div>
    <div class="t_n">
	<a href="#"><img src="../images/ico7.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a></div></div>
    </div>
    <div class="t_n">
	<a href="#"><img src="../images/ico8.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a></div></div>
    </div>
    <div class="t_n">
	<a href="#"><img src="../images/ico9.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>合同法</strong></a></h3>
           <p class="num_con"><span><strong>8</strong>.0</span>( 58人评)</p>
				<li>主讲人：<span>张三丰</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <div class="gk"></div>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a>&nbsp;<a href="#">选购（￥50）</a></div></div>
    </div>
    
    <div class="page_01"><form action="" method="get">
		  <span><a href="#">首页</a></span>  <span><a href="#">上一页</a></span>  <span><a href="#">下一页</a></span>  <span><a href="#">尾页</a></span>  <span><a href="#">共6页</a></span>
		</form></div>
    </div>
    
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="#">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>
</body>
</html>

