package com.reminder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Build;
import android.os.StrictMode;

public class Post {
	// uriPath: ��ݺ��}
	private static String uriPath = "http://140.116.245.154/getTrend.php";
	//private static String data;
	
	static{ 
        if( Build.VERSION.SDK_INT > 8 ){  
        	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.permitAll()
				.build());
        } 
    } 
	
	public Post(){
		//uriPath = uri_path;
	}
	
	public Post(String uri_path){
		uriPath = uri_path;
	}
	
	public void Set(String uri_path){
		uriPath = uri_path;
	}
	
	public String Get_data(){
		return uriPath;
	}
	
	/**
	 * �ǰe��Ʀ^Server 
	 * data: ���
	 */
	public String SendHttpPost(String data) {
	   
		HttpPost httpRequest = new HttpPost(uriPath);
		
	    // Post�B�@�ǰe�ܼƥ�����NameValuePair[]�}�C�x�s
	    List<NameValuePair> params = new ArrayList<NameValuePair>();

	    params.add(new BasicNameValuePair("data", data));

	    try {
	    	//�o�XHTTP request  
	        httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
	        //���oHTTP response
	        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
	        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
	        HttpResponse httpResponse = defaultHttpClient.execute(httpRequest);  
	         
	        //�Y���A�X��200 ok   
	        if(httpResponse.getStatusLine().getStatusCode() == 200) {  
	        	//���X�^���r�� 
	        	String strResult = EntityUtils.toString(httpResponse.getEntity());  
	        	return strResult;  
	        }
	        else   
	        	return "Error Response" ;//+ httpResponse.getStatusLine().toString();  
	        
	    } catch(ClientProtocolException e) {  
	    	e.printStackTrace(); 
	    	return e.getMessage().toString(); 
	    } catch (UnsupportedEncodingException e) {  
	        e.printStackTrace();
	        return e.getMessage().toString(); 
	    } catch (IOException e) {  
	        e.printStackTrace();
	        return e.getMessage().toString();
	    } 
	}
}
