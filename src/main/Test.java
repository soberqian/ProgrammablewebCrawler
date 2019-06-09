package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.HTTPUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		String html=HTTPUtils.getRawHtml("https://s.taobao.com/search?q=python&s=1&ie=utf8"); //ªÒ»°html
		Pattern pattern = Pattern.compile("raw_title\":\"([^\"]+)");
		Matcher matcher = pattern.matcher(html);
		while(matcher.find()) { 
		     System.out.println(matcher.group()); 
		} 
		Pattern pattern1 = Pattern.compile("view_price\":\"([^\"]+)");
		Matcher matcher1 = pattern1.matcher(html);
		while(matcher1.find()) { 
		     System.out.println(matcher1.group()); 
		} 
		Pattern pattern2 = Pattern.compile("detail_url\":\"([^\"]+)");
		Matcher matcher2 = pattern2.matcher(html);
		while(matcher2.find()) { 
		     System.out.println(matcher2.group(1)); 
		} 
		Pattern pattern3 = Pattern.compile("comment_count\":\"([^\"]+)");
		Matcher matcher3 = pattern3.matcher(html);
		while(matcher3.find()) { 
		     System.out.println(matcher3.group()); 
		} 
		
	}

}
