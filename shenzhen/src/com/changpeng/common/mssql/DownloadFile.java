/**
 * DownloadFile.java
 */

package com.changpeng.common.mssql;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 从指定的url下载一个图片下来
 * 
 * @author 华锋 2008-6-11 下午06:13:37
 * 
 */
public class DownloadFile {

	private static final Log LOG = LogFactory.getLog(DownloadFile.class);
	private static final String separator=System.getProperty("file.separator");

	
	
	public static String downloadPic(String picurl, String savepath) {
	
		
		int endfixindex = picurl.lastIndexOf(".");
		String endfix = picurl.substring(endfixindex);
		String savename = System.currentTimeMillis() + endfix;

		try {
			URL url = new URL(picurl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			String dest=savepath + separator + savename;
			FileOutputStream fos = new FileOutputStream(dest);
			int size = 0;
			byte[] buffer = new byte[1024];
			while ((size = bis.read(buffer)) != -1) {
				fos.write(buffer, 0, size);
			}
			fos.close();
			bis.close();
			connection.disconnect();
			LOG.info("从"+picurl+"下载相片到:"+dest+"成功...");
			return savename;
		}
		catch (Exception e) {
			LOG.error("下载相片出错:" + e);
			return "";
		}
	}
	
	public static void main(String[] args)throws Exception{
		
		String s=downloadPic("http://www.wangchao.net.cn/images/logo5.jpg","c:\\");
		System.out.println("================"+s);
	}
}