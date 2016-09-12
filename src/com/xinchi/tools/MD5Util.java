package com.xinchi.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.xinchi.common.DictionaryEnum;


public class MD5Util {
	 	/** 
	     * MD5 加密 
	     */  
	    public static  String getMD5Str(String str) {  
	        MessageDigest messageDigest = null;  
	  
	        try {  
	            messageDigest = MessageDigest.getInstance("MD5");  
	  
	            messageDigest.reset();  
	  
	            messageDigest.update(str.getBytes("UTF-8"));  
	        } catch (NoSuchAlgorithmException e) {  
	            System.out.println("NoSuchAlgorithmException caught!");  
	            System.exit(-1);  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	  
	        byte[] byteArray = messageDigest.digest();  
	  
	        StringBuffer md5StrBuff = new StringBuffer();  
	  
	        for (int i = 0; i < byteArray.length; i++) {              
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
	            else  
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
	        }  
	  
	        return md5StrBuff.toString();  
	    }  
	    
	    public static void main(String[] args) {
	    	MD5Util md5=new MD5Util();
	    	long time=System.currentTimeMillis();
	    	System.out.println(new Date(time).toLocaleString());
	    	String str="273707977@qq.com&"+md5.getMD5Str("AE3SGJtkRLDsv1PRAAc2")+"&"+time;
	    	System.out.println(str);
	    	str=Base64.encodeBase64String(str.getBytes());
	    	System.out.println(str);
	    	String newStr = new String(Base64.decodeBase64(str));
			System.out.println(newStr);
	    	String[] strs = newStr.split("&");
	    	System.out.println(new Date(Long.valueOf(strs[2])).toLocaleString());	
	    	long a=(new Date().getTime()-Long.valueOf("1416359548296"))/(60*60*1000);
	    	System.out.println(a);
	    	System.out.println(DictionaryEnum.PERSON_TYPE.getStringValue());
		}
}

