package net.nifoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

	public static void main(String[] args) {
		try {
			testRUL2(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testRUL(String[] args) throws IOException {
		URL nistServer = new URL("http://time.nist.gov:13");
		//URL nistServer = new URL("http://www.163.com");

		InputStream is = nistServer.openStream();
		byte[] buff = new byte[1024];
		int size = is.read(buff);
		String info = new String(buff, 0, size);
		is.close();

		System.out.println(info);
	}

	public static void testRUL2(String[] args) throws IOException, URISyntaxException {
		URL url = new URL("http://time.nist.gov:13");
		//		URI url = new URI("http://stdtime.gov.hk:13/");
		URLConnection connection = url.openConnection();
		//connection.setRequestProperty("User-Agent", "Mozilla/3.5.7 (compatible; MSIE 5.0; Windows NT; DigExt)");
		connection.connect();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		//		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		//		BufferedReader reader = new BufferedReader(new FileReader(file));

		StringBuilder buff = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			buff.append(line).append("\n");
		}

		reader.close();
		System.out.println(buff);
	}
}
