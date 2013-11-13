package net.nifoo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class Main {

	public static void main(String[] args) {

		try {
			testRUL2(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testRUL(String[] args) throws IOException {
		URL nistServer = new URL("http://time.nist.gov:13");
		//URL nistServer = new URL("http://www.baidu.com");

		InputStream is = nistServer.openStream();
		byte[] buff = new byte[1024];
		int size = is.read(buff);
		String info = new String(buff, 0, size);
		is.close();

		System.out.println(info);
	}

	public static void testRUL2(String[] args) throws IOException, URISyntaxException {
		HttpClient client = new HttpClient();
		//  设置代理服务器地址和端口         

		//client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
		HttpMethod method = new GetMethod("http://time.nist.gov:13");
		//使用POST方法
		//HttpMethod method = new PostMethod("http://www.baidu.com");
		client.executeMethod(method);
		

		//打印服务器返回的状态
		System.out.println(method.getStatusLine());
		//打印返回的信息
		System.out.println(method.getResponseBodyAsString());
		//释放连接
		method.releaseConnection();
	}
}
