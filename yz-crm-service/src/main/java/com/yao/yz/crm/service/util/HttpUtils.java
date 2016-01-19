package com.yao.yz.crm.service.util;

import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yao.yz.util.constants.ExceptionConstants;
import com.yao.yz.util.exception.YzRuntimeException;

/**
 * <p>
 * Title: HttpUtils<／p>
 * <p>
 * Description:给Http使用的保证线程安全 由于在同一时间多个连接只能安全地用于单一线程和方法和有限的资源，我们就必须确保连接分配给正确的方法
 * HttpClient就在多线程中执行多个方法了。当我们再次调用httpClient.executeMethod()方法时， 就会去Connection
 * Manager中去请求HttpConneciton的实例，这样就避免了线程安全问题 <／p>
 * <p>
 * Company: 壹药网<／p>
 * 
 * @author wangtao9953
 * @date Sep 17, 2015
 */
public class HttpUtils {
	
	private static final Logger logger = Logger.getLogger(HttpUtils.class);
	
	/**
	 * 客户端总并发数
	 */
	private static final int MAX_HTTP_CONNECTION = 200;
	/**
	 * HOST并发数
	 */
	private static final int MAX_CONNECTION_PER_HOST = 50;
	/**
	 * 编码方式
	 */
	private static String ENCODING = "UTF-8";

	public static HttpConnectionManager connectionManager;

	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		
		// HttpClient 连接池属性设置，HOST并发数默认为50, 客户端总并发数为200, TimeOut时间为5s.
		connectionManager.getParams().setMaxTotalConnections(MAX_HTTP_CONNECTION);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(MAX_CONNECTION_PER_HOST);
		connectionManager.getParams().setSoTimeout(Integer.parseInt(SysPropertyReader.getValue("RES_TIMEOUT")));
		connectionManager.getParams().setConnectionTimeout(Integer.parseInt(SysPropertyReader.getValue("CONN_TIMEOUT")));
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

	/**
	 * 获取httpClient客户端
	 * <p>
	 * Title: getHttpClient<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param connectionManager
	 * @return
	 */
	private  static HttpClient getHttpClient(
			HttpConnectionManager connectionManager) {
		return new HttpClient(connectionManager);
	}

	/**
	 * 处理Post请求方法
	 * <p>
	 * Title: postPubMethod<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param url
	 * @param params Map
	 * @return 
	 */
	public static String httpPostPubMethod(String url,Map<String, Object> params) {
		PostMethod method = new PostMethod(url);
		try {
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
			
			int httpCode = getHttpClient(connectionManager).executeMethod(method);
			
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
			logger.error("httpclient调用异常",e);
			throw new YzRuntimeException(
					ExceptionConstants.HTTP_POST_SEND_ERROR,
					"post send error>>>" + e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
	}

	public static String httpGetPubMethod(String url) {
		GetMethod method = new GetMethod(url);
		try {
			if (StringUtils.isBlank(url)) {
				return "推送地址错误[url=" + url + "]";
			}
			getHttpClient(connectionManager).executeMethod(method);
			return method.getResponseBodyAsString();
		} catch (Exception e) {
			throw new YzRuntimeException(
					ExceptionConstants.HTTP_GET_SEND_ERROR,
					"get send error>>>" + e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
	}

	}
