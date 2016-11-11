package com.xinchi.common;


public class Utils {

	public static String fullFill(int source, String sign, int size) {
		String s = String.valueOf(source);
		if (s.length() >= size) {
			return s;
		} else {
			String before = "";
			for (int i = 0; i < size - s.length(); i++) {
				before += sign;
			}
			return before + s;
		}
	}

	public static String getFileExt(String fileName) {
		String ext = "";
		if (null == fileName || fileName.equals(""))
			return "";
		ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		return ext;
	}
}
