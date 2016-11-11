package apptest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class SomeTest {
	public static String source = "GT9RXPJIUHF8EQ34YLNV6MB1WS052OCDAZK7";

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(MD5("12312"));
	}

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
}
