package main;

import java.util.List;

import db.MYSQLControl;
import model.ApiBasicInfoModel;
import parse.Parse;

public class CrawlerMain {

	public static void main(String[] args) throws Exception {
	/*	for (int i = 0; i < 715; i++) {
			System.out.println("��ǰ��ȡ���ǵ�"+i+"ҳ��");
			String urlpage = "https://www.programmableweb.com/category/all/apis?page="+i;
			List<ApiBasicInfoModel> datalist=Parse.parsehtml(urlpage);
			MYSQLControl.insertBasicInfo(datalist);
		}*/
		for (int i = 0; i < 33; i++) {
			System.out.println("��ǰ��ȡ���ǵ�"+i+"ҳ��");
			String urlpage = "https://www.programmableweb.com/category/cloud/apis?category=20103&page="+i;
			List<ApiBasicInfoModel> datalist = Parse.parsehtml(urlpage);
			MYSQLControl.insertBasicInfo(datalist);
		}
	}

}
