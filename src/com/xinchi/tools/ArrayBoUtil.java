package com.xinchi.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.xinchi.common.SupperBO;


/**
 * @author wjx
 * @date 2015年1月9日 下午3:31:38
 * @note 对大数据的字典的处理
 */
public class ArrayBoUtil {

	/**
	 * 根据数组，字段名，code值，获取该字典中code对应的值
	 * 
	 * @param dicts
	 *            字典数组
	 * @param field_name
	 *            该对象中code的字段名称
	 * @param code
	 * @param field_value_name
	 *            该对象中value的字段名称
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static <T extends SupperBO> String getValueByCode(List<T> dicts, String field_name, String code,
			String field_value_name) {
		String result = "";
		field_name = Character.toUpperCase(field_name.charAt(0)) + field_name.substring(1);
		field_value_name = Character.toUpperCase(field_value_name.charAt(0)) + field_value_name.substring(1);
		for (int i = 0; i < dicts.size(); i++) {
			T obj = dicts.get(i);
			Class entity = obj.getClass();
			String methodName = "get" + field_name;
			String methodValueName = "get" + field_value_name;
			Method[] methods = entity.getDeclaredMethods();
			String code_temp = "";
			try {
				for (Method method : methods) {
					// 如果方法同名则执行该方法（不能用于BO中有重载方法的情况）
					// System.out.println("method:" + method.getName());
					if (methodName.equals(method.getName())) {
						code_temp = String.valueOf(method.invoke(obj));// 执行get方法返回一个Object
						break;
					}
				}
				if (code.equals(code_temp)) {
					for (Method method : methods) {
						// 如果方法同名则执行该方法（不能用于BO中有重载方法的情况）
						if (methodValueName.equals(method.getName())) {
							result = String.valueOf(method.invoke(obj));// 执行get方法返回一个Object
							return result;
						}
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return result;
	}


	/**
	 * 根据所有的编码（字符串：以逗号分割），获取所有的对应值（字符串：以逗号分割）
	 * 
	 * @param dicts
	 *            字典数组
	 * @param field_name
	 *            字典对象中code的字段名称
	 * @param code
	 *            所有的编码字符串
	 * @param field_value_name
	 *            字典对象中value的字段名称
	 * @return
	 */
	public static <T extends SupperBO> String getValueByCodes(List<T> dicts, String field_name, String code,
			String field_value_name) {
		String result = "";
		if (code != null) {
			String[] arr = code.split(",");
			for (String element : arr) {
				result += "," + getValueByCode(dicts, field_name, element, field_value_name);
			}
			if (result.startsWith(",")) {
				result = result.substring(1);
			}
		}
		return result;
	}

	/**
	 * 根据对象修改数组
	 * 
	 * @param dicts
	 *            数组
	 * @param field_name
	 *            字段名称
	 * @param field_value
	 *            字段值
	 * @return
	 */
	public static <T extends SupperBO> int updateByObject(List<T> dicts,
	// String field_name, String field_value
			T bo) {
		int result = -1;
		String field_value = bo.getPk();
		String field_name = "Id";
		for (int i = 0; i < dicts.size(); i++) {
			T obj = dicts.get(i);
			Class entity = SupperBO.class;
			String methodName = "get" + field_name;
			Method[] methods = entity.getDeclaredMethods();
			String code_temp = "";
			try {
				for (Method method : methods) {
					// 如果方法同名则执行该方法（不能用于BO中有重载方法的情况）
					// System.out.println("method:" + method.getName());
					if (methodName.equals(method.getName())) {
						code_temp = String.valueOf(method.invoke(obj));// 执行get方法返回一个Object
						break;
					}
				}
				if (field_value.equals(code_temp)) {
					dicts.set(i, bo);
					return i;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
