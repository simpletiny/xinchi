package com.xinchi.struts.typeconvert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class StringToTimestamp extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (toType == Timestamp.class) { // 当字符串向Timestamp类型转换时
				String[] params = (String[]) value;
				return new Timestamp(sdf.parse(params[0]).getTime());
			} else if (toType == String.class) { // 当Timestamp转换成字符串时
				Timestamp date = (Timestamp) value;
				return sdf.format(date);
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
