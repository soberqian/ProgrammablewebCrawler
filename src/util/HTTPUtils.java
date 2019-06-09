package util;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import net.HttpRequest;
import net.HttpResponse;


public abstract class HTTPUtils {
	//获取网页的html文件
	public static String getRawHtml(String personalUrl) throws Exception {
		
		HttpRequest request = new HttpRequest(personalUrl);
		HttpResponse response = request.getResponse();
		String html = response.getHtmlByCharsetDetect();
		return html;
	}
	public static String gethtml(String redirectLocation) { 
		HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(redirectLocation);  
        // Create a response handler  
        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
        String responseBody = "";  
        try {  
            responseBody = httpClient.execute(httpget, responseHandler);  
        } catch (Exception e) {  
            e.printStackTrace();  
            responseBody = null;  
        } finally {  
            httpget.abort();  
            httpClient.getConnectionManager().shutdown();  
        }  
        return responseBody;  
    } 
	
}
