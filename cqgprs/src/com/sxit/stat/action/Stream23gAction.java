/**
 * 
 */
package com.sxit.stat.action;

import java.util.List;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.LineChart;

import com.sxit.stat.models.SgsnStatModel;
import com.sxit.stat.models.TotalStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * 
 * 2/3G流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class Stream23gAction extends StatAction {
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		if(startDate==null){
			startDate=super.getPrevCountDate(PREV_DAYS);
			this.start=df.format(startDate);
		}
		if(endDate==null){
			endDate=getPrevDate();
			this.end=df.format(endDate);
		}
		
		streamlist = statservice.getDaysTotalStream23g(startDate, endDate);
		if (resultType.equals("list"))
			return SUCCESS;

		else if (resultType.equals("excel"))
			return "excel";
		else if (resultType.equals("flash")) {

			if (flashType.equals("bar")) { // 产生柱状图
				this.flashChart = barChart();
			} else { // 产生线图
				this.flashChart = lineChart();
			}
			return "ofc";
		} else {
			this.message = "返回数据类型错误";
			return "message";
		}
//		return SUCCESS;
	}
	
	private String nettype="gsm";
	

	private List streamlist;

	public List getStreamlist() {
		return this.streamlist;
	}
	
	private Chart lineChart() {
		double min = 0d;
		double max = 0d;
		LineChart c2 = new LineChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = streamlist.size();
		for (int i = 0; i < len; i++) {
			TotalStatModel stat = (TotalStatModel) streamlist.get(i);

			if (stat.getNettype().equalsIgnoreCase(nettype)) {
				// 总流量
				float value = 0;
				// 总流量
				if (flashby.equals("total"))
					value =(float)stat.getTotalStream();
				else if (flashby.equals("average"))
					value = stat.getAverageStream();
				else if (flashby.equals("user"))
					value = stat.getTotalUser();
				if (value < min)
					min = value;
				if (value > max)
					max = value;
				c2.addValues(value);
				jofc2.model.axis.Label label = new Label();
				label.setText(stat.getDate());	
				label.setRotation(ration);
				xlables.addLabels(label);
			}
		}

		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表

		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setXAxisLabels(xlables); // 显示横坐标
		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(min - steps<=0?0:(min-steps)); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		flashChart.setYAxis(yaxis);
		Text title = new Text();
		title.setStyle("{font-size:14px}");
		if (nettype.equals("gsm")) {
			if (flashby.equals("total"))
				title.setText(start + "至" + end + "各SGSN总流量分析(GSM)");
			else if (flashby.equals("average")) {
				title.setText(start + "至" + end + "各SGSN平均流量分析(GSM)");
			} else if (flashby.equals("user")) {
				title.setText(start + "至" + end + "各SGSN总用户数分析(GSM)");
			}
		} else {
			if (flashby.equals("total"))
				title.setText(start + "至" + end + "各SGSN总流量分析(TD)");
			else if (flashby.equals("average")) {
				title.setText(start + "至" + end + "各SGSN平均流量分析(TD)");
			} else if (flashby.equals("user")) {
				title.setText(start + "至" + end + "各SGSN总用户数分析(TD)");
			}
		}
		flashChart.setTitle(title);

		return flashChart;
	}

	private Chart barChart() {
		double min = 0d;
		double max = 0d;
		BarChart c2 = new BarChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = streamlist.size();
		for (int i = 0; i < len; i++) {
			TotalStatModel stat = (TotalStatModel) streamlist.get(i);

			if (stat.getNettype().equalsIgnoreCase(nettype)) {
				// 总流量
				float value = 0;
				// 总流量
				if (flashby.equals("total"))
					value = (float)stat.getTotalStream();
				else if (flashby.equals("average"))
					value = stat.getAverageStream();
				else if (flashby.equals("user"))
					value = stat.getTotalUser();
				if (value < min)
					min = value;
				if (value > max)
					max = value;
				c2.addValues(value);
				jofc2.model.axis.Label label = new Label();
				label.setText(stat.getDate());	
				label.setRotation(ration);
				xlables.addLabels(label);
			}
		}

		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表

		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setXAxisLabels(xlables); // 显示横坐标
		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(min - steps<=0?0:(min-steps)); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		flashChart.setYAxis(yaxis);
		Text title = new Text();
		title.setStyle("{font-size:14px}");
		if (nettype.equals("gsm")) {			if (flashby.equals("total"))
				title.setText(start + "至" + end + "各SGSN总流量分析(GSM)");
			else if (flashby.equals("average")) {
				title.setText(start + "至" + end + "各SGSN平均流量分析(GSM)");
			} else if (flashby.equals("user")) {
				title.setText(start + "至" + end + "各SGSN总用户数分析(GSM)");
			}
		} else {
			if (flashby.equals("total"))
				title.setText(start + "至" + end + "各SGSN总流量分析(TD)");
			else if (flashby.equals("average")) {
				title.setText(start + "至" + end + "各SGSN平均流量分析(TD)");
			} else if (flashby.equals("user")) {
				title.setText(start + "至" + end + "各SGSN总用户数分析(TD)");
			}
		}
		flashChart.setTitle(title);
		return flashChart;
	}

	/**
	 * @return the nettype
	 */
	public String getNettype() {
		return nettype;
	}

	/**
	 * @param nettype the nettype to set
	 */
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}
}
