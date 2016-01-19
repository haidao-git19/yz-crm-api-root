package com.yao.yz.crm.service.util;

import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yao.yz.util.constants.ExceptionConstants;
import com.yao.yz.util.exception.YzRuntimeException;

public class HttpClientProxy {

	private static final Logger logger = Logger.getLogger(HttpClientProxy.class);
	
	private static HttpClient httpClient;
	
	private static String ENCODING = "UTF-8";
	
	static {
		httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt(SysPropertyReader.getValue("CONN_TIMEOUT")));
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(Integer.parseInt(SysPropertyReader.getValue("RES_TIMEOUT")));
		logger.info("Get httpclientProxy success");
	}
	
	public static String httpPostPubMethod(String url,Map<String, Object> params) {
		
		PostMethod method = new PostMethod(url);
		try {
			// 调用
			if (StringUtils.isBlank(url)) {
				throw new YzRuntimeException(0, "推送地址错误[url=" + url + "]");
			}
			if (null == params || params.size() == 0) {
				throw new YzRuntimeException(0, "无传递参数");
			}
			method.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
			method.setRequestBody(buildNameValuePairs(params));
			HttpMethodParams param = method.getParams();
			param.setContentCharset(ENCODING);
			
			// 调用并判断返回码
			int httpCode = httpClient.executeMethod(method);
			logger.info("服务端返回码：" + httpCode);

			logger.info("服务端返回信息:" + method.getResponseBodyAsString());
			Header[] headers = method.getResponseHeaders();
			if (headers != null && headers.length > 0) {
				for (Header header : headers) {
					logger.info(header.getName());
					for (HeaderElement headerElement : header.getElements()) {
						logger.info(headerElement.getName());
						logger.info(header.getValue());
					}
				}
			}
			
			if (HttpStatus.SC_OK == httpCode) {
				return method.getResponseBodyAsString();
			} else {
				logger.error("服务端返回状态：" + httpCode);
				throw new YzRuntimeException(0, "服务端返回错误：" + httpCode);
			}
		} catch (Exception e) {
			logger.error("调用订单消耗接口异常", e);
			throw new YzRuntimeException(
					ExceptionConstants.HTTP_POST_SEND_ERROR,
					"post send error>>>" + e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
	}
	
	/**
	 * 组装参数,这里用Map,一键一值比较通用,可以当做共用方法
	 * 
	 * @param params
	 * @return NameValuePair[]
	 */
	private static NameValuePair[] buildNameValuePairs(Map<String, Object> params) {
		Object[] keys = params.keySet().toArray();
		NameValuePair[] pairs = new NameValuePair[keys.length];

		for (int i = 0; i < keys.length; i++) {
			String key = (String) keys[i];
			pairs[i] = new NameValuePair(key, (String)params.get(key));
		}
		return pairs;
	}
}
