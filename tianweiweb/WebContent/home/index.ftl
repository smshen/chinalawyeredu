﻿<#import "../common/homenoleft.ftl" as home>
<#escape x as (x)!"">
<@home.home>
	<div id="nr">
		<div id="nrl">
			<span class="hy">
			     本网站联合华侨城旅行社隆重<br />
推出南非世界杯足球赛旅游套餐优惠<br />&nbsp; 活动！另近期将推出特惠“台湾游”活动专题，<br />敬请留意！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<#if currentUserid=0>
	          <a href="../regist/regist!input.action">立即注册>></a>
	          </#if>&nbsp;</span>
			<span class="hd">
			<script language="javascript">
var swf_width=340;
var swf_height=217;
var ary_file=new Array();
var ary_link=new Array();
var ary_text=new Array();
 	<#assign ii=0 />
	<#list huodongs as huodong>
	ary_file[${ii}]="${huodong.filenumber}";
ary_link[${ii}]="../news/viewnews.action?newsid=${huodong.infoid}";
ary_text[${ii}]="${huodong.infoname}";
   <#assign ii=ii+1 />
	</#list>
//ary_file[1]="../images/szsc.jpg";
//ary_link[1]="http://www.google.com";
//ary_text[1]="好玩的活动";
var files=ary_file.join("|");
var links=ary_link.join("|");
var texts=ary_text.join("|");
document.write('<object class="indexflash" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cabversion=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
document.write('<param name="movie" value="../css/pic_show.swf"><param name="quality" value="high">');
document.write('<param name="menu" value="false"><param name="wmode" value="opaque"><param name="wmode" value="transparent" />');
//document.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&AutoPlayTime=5">');
document.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&AutoPlayTime=5">');
//document.write('<embed src="../css/pic_show.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&AutoPlayTime=5" menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer">');
document.write('<embed src="../css/pic_show.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&AutoPlayTime=5" menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer">');
document.write('</object>');
</script>

			<!--<a href="#"><img src="../images/hd1.jpg" width="350" height="225" /></a>--></span>
		</div>
		<div id="nrr">
			<div class="video">
				<div class="media"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="394" height="322">
          <param name="movie" value="../images/video.swf" />
          <param name="quality" value="high" />
          <param name="wmode" value="transparent" />
          <embed src="../images/video.swf" quality="high" wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="394" height="322"></embed>
		  </object></div>
				<div class="dh">
					<div class="navdl">
					<h3>节目导航</h3>
					<ul>
					<#list shichuangs as shichuang>
						<li>

						<a  href="${shichuang.url}" target="_blank">${shichuang.title}</a></li>

				<!--		<a  href="#" onclick="window.open('${shichuang.url}','newwindow', 'width=640,height=526, top=100, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')">${shichuang.title}</a></li>
				-->
					</#list>
					</ul>
					</div>
					<!-- <div class="nowday">
						<h3>今日更新</h3>
						<ul>
						<#list daohangs as daohang>
							<li><img width="7" height="7" align="absmiddle" alt="" src="../images/video_doc.gif"/>
							<a  href="#" onclick="window.open('${daohang.url}','newwindow', 'height=526,width=640, top=100, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')">${daohang.title}</a></li>
						</#list>
						</ul>
					</div> -->
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="ad">
		<span class="adl" id="left1"></span>
		<span class="adr" id="right1"></span>
	</div>
	<div id="friend">

	</div>
	<div class="frb"></div>
	<div class="clear"></div>

<script type="text/javascript" src="bubble/BubbleTooltips.js"></script>
<script type="text/javascript">
function openbubble(){
	window.onload=function(){enableTooltips("bubble")};
}
setTimeout("openbubble()",5000);
</script>
</@home.home>
</#escape>