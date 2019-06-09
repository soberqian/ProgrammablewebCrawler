package main;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import db.MYSQLControl;
import model.ApiBasicInfoModel;
import model.DetailInfoModel;
import util.HTTPUtils;

public class CrawlerDetailInfoMain {

	public static void main(String[] args) throws Exception {
		Boolean tu=true;
		int count = 1;
		while (tu) {
			/*count++;
			if(count%20==0){
				int m = (int)(Math.random()*20);
				Thread.sleep(20*1000+m*1000);
			}*/
			List<ApiBasicInfoModel> urllist = MYSQLControl.getListInfoBySQL("select api_name,url from basicinfo_cloud where tag=0 order by rand( ) limit 50",ApiBasicInfoModel.class);
			List<DetailInfoModel> datalist = new ArrayList<>();
			if (urllist.size() != 0) {
				for (int i = 0; i < urllist.size(); i++) {
					String urlpage = urllist.get(i).getUrl();
					String html = HTTPUtils.getRawHtml(urlpage); //»ñÈ¡html
					String tags = Jsoup.parse(html).select("div[class=tags]").text();
					String content = Jsoup.parse(html).select("div[class=intro]").text();
//					System.out.println(tags+"\t"+content);
					DetailInfoModel model = new DetailInfoModel();
					model.setApi_name(urllist.get(i).getApi_name());
					model.setTags(tags);
					model.setContent(content);
					datalist.add(model);
					if (datalist.size() == 20) {
						MYSQLControl.insertDetailInfo(datalist);
						datalist.clear();
					}
				}
				MYSQLControl.insertDetailInfo(datalist);
			}else {
				tu=false;
			}
		}
		
	}

}
