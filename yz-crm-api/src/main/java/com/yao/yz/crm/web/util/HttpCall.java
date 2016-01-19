/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 26, 2015-11:54:44 AM
* @version 1.0
*/
package com.yao.yz.crm.web.util;

import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.URLDecoder;
import java.net.URLEncoder;
//import java.nio.charset.Charset;
//import java.util.List;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;

/**
 * 公司名: 壹药网
 * 类名称：HttpCall
 * 类描述：
 * @author wangyulong
 * @date Nov 26, 2015-11:54:44 AM
 */
public class HttpCall {

	/**
	 * <p>Method: main<／p>
	 * <p>Description: <／p>
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		String keyWord="感康";
		keyWord=URLEncoder.encode(keyWord, "GBK");
		String str=getResultContent("http://192.168.89.18:19121/ApiControl?method=yizhen.products.search&pagesize=10&pageindex=1&keyword="+keyWord);
		System.out.println(str);
		
	}
	
	public static String getResultContent(String urlStr) throws Exception{
		try
        {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setRequestMethod("POST");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200)
            {
                return ConvertStream2String(httpConn.getInputStream());
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		return "";
	}
	
	
	private static String ConvertStream2String(InputStream inputStream) throws IOException
    {
		String result = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        
        return result;
    }
	
}

