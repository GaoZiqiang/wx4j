package cn.zr.wx4j.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyUtils {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * ����get����
	 */
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {

			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// ����ʵ�ʵ�����
			conn.connect();
			// ��ȡ������Ӧͷ�ֶ�
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ����post����
	 */
	public static String sendPost(String url, String param) {
		System.out.println("url:" + url);
		System.out.println("param:" + param);
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Accept-Charset", "UTF-8");

			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����POST��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ���ַ���md5����
	 *
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
		try {
			// ����һ��MD5���ܼ���ժҪ
			MessageDigest md = MessageDigest.getInstance("MD5");
			// ����md5����
			md.update(str.getBytes());
			// digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
			// BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			return "error";
		}
	}

	/**
	 * ����post����for json
	 */
	public static String sendBytes(String url, byte[] data) {
		System.out.println("url:" + url);
		System.out.println("data:" + new String(data));
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);

			URLConnection conn = realUrl.openConnection();

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");

			conn.setDoOutput(true);
			conn.setDoInput(true);
			// out = new PrintWriter(conn.getOutputStream());
			// out.print(data);

			conn.getOutputStream().write(data);
			// out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����POST��������쳣��" + e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * stringתunicode
	 */
	static String string2Unicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);

				out.append(str);
				out.append(str1);
				out.append(" ");
			}
			return out.toString().toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String china2Unicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	/**
	 * unicodeתstring
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			int data = Integer.parseInt(hex[i], 16);

			string.append((char) data);
		}

		return string.toString();
	}

	/**
	 * asciiתnative
	 */
	public static String ascii2native(String ascii) {

		List<String> ascii_s = new ArrayList<String>();
		String zhengz = "\\\\u[0-9,a-f,A-F]{4}";
		Pattern p = Pattern.compile(zhengz);
		Matcher m = p.matcher(ascii);
		while (m.find()) {
			ascii_s.add(m.group());
		}
		for (int i = 0, j = 2; i < ascii_s.size(); i++) {
			String code = ascii_s.get(i).substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			ascii = ascii.replace(ascii_s.get(i), String.valueOf(ch));
		}
		return ascii;
	}

	/**
	 * 
	 * ���뺬��html�ַ���string������滻
	 */
	public static String htmlEscape(String content) {
		if (content == null)
			return "";
		String html = content;

		html = html.replaceAll("&", "&amp;");
		html = html.replaceAll("\"", "&quot;"); // "
		html = html.replaceAll("\t", "&nbsp;&nbsp;");
		html = html.replaceAll(" ", "&nbsp;");
		html = html.replaceAll("<", "&lt;");

		html = html.replaceAll(">", "&gt;");

		return html;
	}

	/**
	 * xml��תmap
	 * 
	 */
	public static Map<String, String> xmlInputStream2Map(InputStream is) {
		SAXReader sr = new SAXReader();
		Document doc = null;
		try {
			doc = sr.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();

		List<Element> elements = root.elements();
		Map<String, String> map = new HashMap<String, String>();
		for (Element element : elements) {
			map.put(element.getName(), element.getText());
		}
		return map;
	}

	public static String xmlInputStream2String(InputStream is) {
		SAXReader sr = new SAXReader();
		Document doc = null;
		try {
			doc = sr.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc.getRootElement().asXML();

	}

	public static Map<String, String> xmlString2Map(String xml) {

		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element root = doc.getRootElement();

		List<Element> elements = root.elements();
		Map<String, String> map = new HashMap<String, String>();
		for (Element element : elements) {
			map.put(element.getName(), element.getText());
		}
		return map;
	}

	/**
	 * �ַ������
	 * 
	 */
	public static boolean strIsEmpty(String str) {

		if (str.trim().equals("") || str == null)
			return true;
		else
			return false;

	}

	/**
	 * jsonת����
	 * 
	 */
	public static <T> T json2Obj(String json, Class<T> c) {
		Gson g = new Gson();

		return g.fromJson(json, c);
	}

	/**
	 * ����תjson�ַ���
	 * 
	 * @param json
	 * @return
	 */
	public static String obj2Json(Object o) {

		Gson g = new GsonBuilder().disableHtmlEscaping().create();
		return g.toJson(o);
	}

	/***
	 * 
	 * 
	 * ͨ��httpclient����post
	 */
	public static String sendPostWithFilesAndDatas(String url, Map<String, String> files, Map<String, String> datas) {

		HttpClient httpClient = HttpClients.createDefault();
		String result = "";
		try {
			HttpPost httpPost = new HttpPost(url);
			MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
			if (files != null) {

				for (String key : files.keySet()) {
					entityBuilder.addPart(key, new FileBody(new File(files.get(key))));
				}
			}
			if (datas != null) {
				for (String key : datas.keySet()) {
					entityBuilder.addTextBody(key, datas.get(key));
				}
			}

			HttpEntity entity = entityBuilder.build();
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == HttpStatus.SC_OK) {
				System.out.println("response is ok!");
				HttpEntity resEntity = response.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(resEntity.getContent()));

				String line = null;
				while ((line = reader.readLine()) != null) {
					result += line;
				}
				EntityUtils.consume(resEntity);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				// httpClient.getConnectionManager().shutdown();
			} catch (Exception ignore) {

			}
		}
		return result;

	}

	public static String sendPostWithFiles(String url, Map<String, String> files) {

		return sendPostWithFilesAndDatas(url, files, null);
	}

	public static String sendPostWithDatas(String url, Map<String, String> datas) {

		return sendPostWithFilesAndDatas(url, null, datas);
	}

	public static String sendPostWithJson(String url, String json) {
		String result = "";
		HttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(json);
			entity.setContentType("application/json");

			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == HttpStatus.SC_OK) {
				System.out.println("response is ok!");
				HttpEntity resEntity = response.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(resEntity.getContent()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					result += line;
				}
				EntityUtils.consume(resEntity);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				// httpClient.getConnectionManager().shutdown();
			} catch (Exception ignore) {

			}
		}
		return result;

	}

}
