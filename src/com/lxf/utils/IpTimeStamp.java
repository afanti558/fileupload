package com.lxf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 获取上传文件名：ip+时间�?三位随机�?
 * @description
 * @author xflin
 * @time 2014-8-28上午8:21:50
 * Note：这里需要外面传入变量，用static等全�?��量和方法有些不妥
 */
public class IpTimeStamp {
	
	private String ip = null;
	
	private SimpleDateFormat sdf = null;
	
	public String getIp (){
		return this.ip;
	}

	public IpTimeStamp(String ip){
		this.ip = ip;
	}
	
	/**
	 * 取得拼凑的文件名
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-28上午8:32:56
	 * @param ip
	 * @return
	 */
	public String getIpTimeRand(){
		StringBuffer buf = new StringBuffer();
		if(ip != null){
			String ips[] = ip.split("\\.");
			for(int i = 0;i<ips.length;i++){
				buf.append(this.addZero(ips[i],3));
			}
		}
		buf.append(this.getTimeStamp());
		Random r = new Random();
		for(int i = 0;i<3;i++){
			buf.append(r.nextInt(10));
		}
		return buf.toString();
	}
	
	/**
	 * 在String前面�?，满足长度要�?
	 * @return String
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-28上午8:44:39
	 * @param str
	 * @param len
	 * @return
	 */
	public String addZero(String str,int len){
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(str);
		while(sbuf.length()<len){
			sbuf.insert(0, 0);
		}
		return sbuf.toString();
	}
	
	/**
	 * 获取当前时间的时间戳
	 * @return String
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-28上午8:56:13
	 * @return
	 */
	public String getTimeStamp(){
		this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return this.sdf.format(new Date());
	}
	
	/**
	 * 获取文件的后�?
	 * @return String
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-28下午5:09:01
	 * @param filename
	 * @return
	 */
	public String getsuf(String filename){
		int n = filename.lastIndexOf(".");
		System.out.println(n);
		String s = filename.substring(n+1, filename.length()-n);
		return s;
	}
	
}





