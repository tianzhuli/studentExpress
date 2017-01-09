/**
 * 
 */
package com.me.util;

import java.math.BigDecimal;

/**
 * @author 李天柱 通过经纬度计算距离
 */
public class CalculateLnglat {
	private static final double EARTH_RADIUS = 6378.137;

	public static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	public static double getDistance(double lat1,double lng1,double lat2,double lng2){
		double radLat1=rad(lat1);
		double radLat2=rad(lat2);
		double a=radLat1-radLat2;
		double b=rad(lng1)-rad(lng2);
		
		double s=2*Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2), 2)));
		s=s*EARTH_RADIUS;
		BigDecimal bigDecimal=new BigDecimal(s);
		//return (s*10000)/10000;
		return bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
