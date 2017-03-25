package cn.zr.wx4j.util;

import java.util.HashMap;
import java.util.Map;

import cn.zr.wx4j.exception.IllegalFileNameException;
import cn.zr.wx4j.model.material.NewsMaterials;
import cn.zr.wx4j.model.material.VideoDescription;

public class MaterialUtils {
	// /***
	// *
	// * ��ȡ���������ز�
	// *
	// */
	// public static String getMaterialList() {
	//
	// return null;
	//
	// }

	/***
	 * 
	 * ���������زģ�image��voice��thumb
	 * 
	 * @param type
	 * @param filePath
	 * @throws IllegalFileNameException
	 */
	public static String addPerpetualOtherMaterial(String type, String filePath) throws IllegalFileNameException {
		if (!checkIsIllegalMaterial(filePath))
			throw new IllegalFileNameException("��������ȷ���ļ�·�����ļ�������voice/bmp/png/jpeg/jpg/gif/mp3/wma/wav/amr/mp4�ȸ�ʽ");

		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()).replace("TYPE", type);
		Map<String, String> files = new HashMap<String, String>();
		files.put("media", filePath);
		return MyUtils.sendPostWithFiles(url, files);

	}

	/***
	 * 
	 * ����video�زģ�video��thumb
	 * 
	 * @return ����mediaId
	 * @param type
	 * @param filePath
	 * @throws IllegalFileNameException
	 */
	public static String addPerpetualVideoMaterial(String filePath, VideoDescription description)
			throws IllegalFileNameException {
		if (!checkVideoIsMp4(filePath))
			throw new IllegalFileNameException("��Ƶֻ����mp4��ʽ");

		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()).replace("TYPE", "video");
		Map<String, String> files = new HashMap<String, String>();
		Map<String, String> datas = new HashMap<String, String>();
		files.put("media", filePath);
		datas.put("description", MyUtils.obj2Json(description));

		return MyUtils.sendPostWithFilesAndDatas(url, files, datas);

	}

	/**
	 * 
	 * �����ʱ�ز�
	 * 
	 * @param filePath
	 *            ֻ����image voice video��thumb
	 * @param type
	 * @return jsonStr
	 */
	public static String addTempMaterial(String type, String filePath) {
		String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken()).replace("TYPE", type);
		Map<String, String> files = new HashMap<String, String>();
		files.put("media", filePath);
		return MyUtils.sendPostWithFiles(url, files);

	}

	/***
	 * 
	 * ���������زģ�image��voice��thumb bmp/png/jpeg/jpg/gif��ʽ
	 * ������voice����2M�����ų��Ȳ�����60s��mp3/wma/wav/amr��ʽ ��Ƶ��video����10MB��֧��MP4��ʽ
	 * ����ͼ��thumb����64KB��֧��JPG��ʽ
	 * 
	 * @param type
	 * @param filePath
	 */
	public static String addPerpetualNewsMaterials(NewsMaterials newsMaterials) {
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
		String json = MyUtils.obj2Json(newsMaterials);
		return  MyUtils.sendPostWithJson(url, json);

	}

	/***
	 * 
	 * 
	 * @param filePath
	 * @return ����ͼƬurl��ֻ��������Ѷϵ����ʹ��
	 * @throws IllegalFileNameException
	 */
	public static String uploadImageMaterial(String filePath) throws IllegalFileNameException {
		if (!checkImgIsPngOrJpg(filePath))
			throw new IllegalFileNameException("ͼƬֻ����png/jpg��ʽ");
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
		Map<String, String> files = new HashMap<String, String>();
		files.put("media", filePath);
		return MyUtils.sendPostWithFilesAndDatas(url, files, null);

	}

	/***
	 * �Ƴ������ز�
	 * 
	 * @param mediaId
	 */
	public String removePerpetualMaterial(String mediaId) {
		// https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN
		mediaId = "{\"media_id\":\"" + mediaId + "\"}";
		return MyUtils
				.sendPostWithJson("https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN"
						.replace("ACCESS_TOKEN", WxUtils.getAccessToken()), mediaId);

	}

	// public void getTempMaterial(OutputStream os) {
	// //"https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"
	// }
	/**
	 * 
	 * ���ͼƬ��ʽ�Ƿ���ȷ
	 * 
	 * @param filePath
	 * @return
	 */
	private static boolean checkImgIsPngOrJpg(String filePath) {
		filePath = filePath.toLowerCase();
		if (filePath.endsWith(".jpg") || filePath.endsWith("png"))
			return true;
		return false;
	}

	/**
	 * 
	 * �����Ƶ��ʽ�Ƿ���ȷ
	 * 
	 * @param filePath
	 * @return
	 */
	private static boolean checkVideoIsMp4(String filePath) {
		filePath = filePath.toLowerCase();
		if (filePath.toLowerCase().endsWith(".mp4"))
			return true;
		return false;
	}

	/**
	 * 
	 * ��������زĸ�ʽ�Ƿ���ȷ
	 * 
	 * @param filePath
	 * @return
	 */
	private static boolean checkIsIllegalMaterial(String filePath) {
		filePath = filePath.toLowerCase();
		if (filePath.endsWith(".bmp") || filePath.endsWith(".png") || filePath.endsWith(".jpg")
				|| filePath.endsWith(".gif") || filePath.endsWith(".mp3") || filePath.endsWith("wma")
				|| filePath.endsWith("wav") || filePath.endsWith("amr") || filePath.endsWith("mp4")) {
			return true;
		}
		return false;
	}

	/*
	 * private static boolean checkVideoSize(String filePath) { filePath =
	 * filePath.toLowerCase(); if (filePath.endsWith(".bmp") ||
	 * filePath.endsWith(".png") || filePath.endsWith(".jpg") ||
	 * filePath.endsWith(".gif") || filePath.endsWith(".mp3") ||
	 * filePath.endsWith("wma") || filePath.endsWith("wav") ||
	 * filePath.endsWith("amr") || filePath.endsWith("mp4")) { return true; }
	 * return false; }
	 * 
	 * private static boolean checkImageSize(String filePath) { filePath =
	 * filePath.toLowerCase(); if (filePath.endsWith(".bmp") ||
	 * filePath.endsWith(".png") || filePath.endsWith(".jpg") ||
	 * filePath.endsWith(".gif") || filePath.endsWith(".mp3") ||
	 * filePath.endsWith("wma") || filePath.endsWith("wav") ||
	 * filePath.endsWith("amr") || filePath.endsWith("mp4")) { return true; }
	 * return false; }
	 */
}
