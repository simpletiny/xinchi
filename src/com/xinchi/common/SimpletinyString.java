package com.xinchi.common;

import java.math.BigDecimal;
import java.security.MessageDigest;

/**
 * 字符串工具类
 * 
 * @author simpletiny
 * 
 */
public class SimpletinyString {
	/**
	 * 字符串MD5加密100次
	 * 
	 * @param strSource
	 * @return 加密后的密文
	 */
	public static String MD5(String strSource) {
		try {
			for (int x = 0; x < 100; x++) {
				strSource = MD5OneTime(strSource);
			}
			return strSource;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * md5执行一次
	 * 
	 * @param strSource
	 * @return
	 */
	public static String MD5OneTime(String strSource) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {

			byte[] strTemp = strSource.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			strSource = String.valueOf(str);

			return strSource;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isEmpty(String str) {

		return null == str || str.equals("");
	}

	public static int str2Int(String str) {
		return (null == str || str.trim().equals("")) ? 0 : Integer.valueOf(str
				.trim());
	}

	public static BigDecimal str2Decimal(String str) {
		return (null == str || str.trim().equals("")) ? BigDecimal.ZERO
				: new BigDecimal((str.trim()));
	}

	public static String addSingleQuote(String str) {
		if (isEmpty(str))
			return "";
		return "'" + str.replaceAll(",", "','") + "'";
	}
}
