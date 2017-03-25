package cn.zr.wx4j.util;

import java.io.UnsupportedEncodingException;

import cn.zr.wx4j.model.menu.Button;
import cn.zr.wx4j.model.menu.ConditionalMenu;
import cn.zr.wx4j.model.menu.Menu;
import cn.zr.wx4j.model.menu.ViewButton;

public class MenuUtils {
	/**
	 * �����Զ���˵�
	 * 
	 * @throws UnsupportedEncodingException
	 * 
	 */
	public static String createMenu(Menu m) {
		System.out.println("createMenu");
		String json = MyUtils.obj2Json(m);
		System.out.println("ԭ�˵�json:" + json);

		// byte[] bytes = null;
		// try {
		// bytes = json.getBytes("utf-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// }
		// String res =
		// MyUtils.sendBytes("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"
		// .replace("ACCESS_TOKEN", WxUtils.getToken()), bytes);
		String res = MyUtils.sendPostWithJson("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()), json);
		System.out.println("�����Զ���˵��������ݣ�" + res);
		return res;
	}

	/***
	 * 
	 * ɾ���˵�
	 * 
	 */
	public static String deleteMenu() {

		return MyUtils.sendGet("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()));

	}

	/***
	 * 
	 * ��ȡ�˵�
	 * 
	 */
	public static String getMenu() {
		return MyUtils.sendGet("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()));

	}

	public static String addConditionalMenu(ConditionalMenu menu) {
		// https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN

		String res = MyUtils
				.sendPostWithJson("https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN"
						.replace("ACCESS_TOKEN", WxUtils.getAccessToken()), MyUtils.obj2Json(menu));
		return res;
	}

	public static String removeConditionalMenu() {
		// https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN
		return MyUtils.sendGet("https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()));
	}

	// �����˿΢�ź� or OpenId
	public static String tryMatchConditionalMenu(String userId) {
		return MyUtils
				.sendPostWithJson(
						"https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN"
								.replace("ACCESS_TOKEN", WxUtils.getAccessToken()),
						"{\"user_id\":\"USER_ID\"}".replace("USER_ID", userId));

	}

	public static String getCurrentMenuInfo() {
		return MyUtils.sendGet("https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()));

	}

	public static Menu getSampleMenu() {

		Menu m = new Menu();

		Button b1 = new Button();
		b1.setName("�ͻ�����");
		ViewButton b11 = new ViewButton();
		b11.setName("����1");
		b11.setType(ButtonType.VIEW);
		b11.setUrl("http://www.zhour.net");
		ViewButton b12 = new ViewButton();
		b12.setName("����2");
		b12.setType(ButtonType.VIEW);
		b12.setUrl("http://www.zhour.net");
		ViewButton b13 = new ViewButton();
		b13.setName("test1");
		b13.setType(ButtonType.VIEW);
		b13.setUrl("http://www.zhour.net");
		ViewButton b14 = new ViewButton();
		b14.setName("test2");
		b14.setType(ButtonType.VIEW);

		b14.setUrl("http://www.zhour.net");
		b1.setSub_button(new Button[] { b11, b12, b13, b14 });

		Button b2 = new Button();
		b2.setName("infomation");
		ViewButton b21 = new ViewButton();
		b21.setName("����");
		b21.setType(ButtonType.VIEW);
		b21.setUrl("http://www.huapengfei.com");
		ViewButton b22 = new ViewButton();
		b22.setName("APP");
		b22.setType(ButtonType.VIEW);
		b22.setUrl("http://www.zhour.net");

		b2.setSub_button(new Button[] { b21, b22 });

		ViewButton b3 = new ViewButton();
		b3.setName("��ϵ����");
		b3.setType(ButtonType.VIEW);
		b3.setUrl("http://www.zhour.net");

		m.setButton(new Button[] { b1, b2, b3 });
		// return m;
		return m;
	}
}
