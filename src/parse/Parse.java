package parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.ApiBasicInfoModel;
import util.HTTPUtils;

public class Parse {
	public static List<ApiBasicInfoModel> parsehtml(String urlpage) throws Exception {
		String html=HTTPUtils.getRawHtml(urlpage); //ªÒ»°html
		List<ApiBasicInfoModel> datalist = new ArrayList<>();
		Elements elements = Jsoup.parse(html).select("div[class=view-content]").select("tbody").select("tr");
		for (Element ele:elements) {
			if (ele.select("td[class=views-field views-field-field-article-primary-category]").text().length()!=0) {
				String api_name = ele.select("td").get(0).text();
				String url = "https://www.programmableweb.com"+ele.select("td").get(0).select("a").attr("href");
				String category = ele.select("td[class=views-field views-field-field-article-primary-category]").text();
				ApiBasicInfoModel model = new ApiBasicInfoModel();
				model.setApi_name(api_name);
				model.setCategory(category);
				model.setUrl(url);
				datalist.add(model);
				System.out.println(api_name+"\t"+category);
			}
		}
		return datalist;
	}
}
