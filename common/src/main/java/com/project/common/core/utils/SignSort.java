package com.project.common.core.utils;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignSort {
	// 加密公钥
	private final static String PUBLIC_KEY = "07c940a6d6f525714aab0a39ac09be6b";
	private final static String input_charset = "utf-8";

	/**
	 *
	 * sortUp:(当多个参数时字符串升序排列签名)
	 *
	 * @param @param
	 *            strs 可变参数
	 * @param @return
	 *            设定文件
	 * @return String DOM对象 返回字符串对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public static String sortUp(String... strs) {
		// 生成的sign
		String upString = "";
		Arrays.sort(strs, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// 升序排列
				return o1.compareTo(o2);
			}
		});
		for (int i = 0; i < strs.length; i++) {
			if (i == strs.length - 1) {
				upString += strs[i] + PUBLIC_KEY;
			} else {
				upString += strs[i] + "&";
			}
		}
		return upString;
	}


	/**
	 * 根据规则生成sign参数
	 *
	 * @param params
	 * @return
	 */
	public static String generateSign(Map<String, Object> params) {
		String sign = "";
		// 按照key排序 升序
		List<Map.Entry<String, Object>> keys = new ArrayList<>(params.entrySet());
		// 排序
		Collections.sort(keys, new Comparator<Map.Entry<String, Object>>() {
			public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		StringBuffer accum = new StringBuffer();
		for (Map.Entry<String, Object> entry : keys) {
			accum.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}

		if (accum.length() > 1) {
			accum.deleteCharAt(accum.length() - 1);
		}
		// 加密key
		accum.append(PUBLIC_KEY);
		sign = sign(accum.toString(), "UTF-8");
		return sign;
	}

	/**
	 * 签名字符串
	 *
	 * @param text
	 *            需要签名的字符串
	 * @param
	 *
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String input_charset) {
		// text = text + key;
		return MD5Util.MD5Encode(text,input_charset);
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	/**
	 * HashMap转换成JavaBean
	 *
	 * @param map
	 * @param cls
	 * @return
	 * @author julyzeng
	 * @time 下午13:57:16
	 */
	public static <T> T hashMapToJavaBean(HashMap<?, ?> map, Class<T> cls) {
		T obj = null;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取出bean里的所有方法
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			// 取方法名
			String method = methods[i].getName();
			// 取出方法的类型
			Class<?>[] cc = methods[i].getParameterTypes();
			if (cc.length != 1)
				continue;

			// 如果方法名没有以set开头的则退出本次for
			if (!method.startsWith("set"))
				continue;
			// 类型
			String type = cc[0].getSimpleName();

			try {
				//
				Object value = method.substring(3, 4).toLowerCase().concat(method.substring(4));
				// 如果map里有该key
				if (map.containsKey(value)) {
					// 调用其底层方法
					setValue(type, map.get(value), i, methods, obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	private static void setValue(String type, Object value, int i, Method[] method,
                                 Object bean) throws Exception {
		if (value != null && !value.equals("")) {
			try {
				if (type.equals("String")) {
					// 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
					method[i].invoke(bean, new Object[]{value});
				} else if (type.equals("int") || type.equals("Integer")) {
					method[i].invoke(bean, new Object[]{new Integer(""
							+ value)});
				} else if (type.equals("BigDecimal")) {
					method[i].invoke(bean, new Object[]{new BigDecimal((String) value)});
				} else if (type.equals("long") || type.equals("Long")) {
					method[i].invoke(bean,
							new Object[]{new Long("" + value)});
				} else if (type.equals("boolean") || type.equals("Boolean")) {
					method[i].invoke(bean, new Object[]{Boolean.valueOf(""
							+ value)});
				} else if (type.equals("Date")) {
					Date date = null;
					if (value.getClass().getName().equals("java.util.Date")) {
						date = (Date) value;
					} else {
						//根据文件内的格式不同修改，时间格式太多在此不做通用格式处理。
						if (value.toString().length() > 10) {
							String format = "yyyy-MM-dd HHmmss";
							date = parseDateTime("" + value, format);
						} else if (value.toString().length() == 10) {
							String format = "yyyy-MM-dd";
							date = parseDateTime("" + value, format);
						} else if (value.toString().length() == 8) {
							String format = "yyyyMMdd";
							date = parseDateTime("" + value, format);
						} else if (value.toString().length() == 14) {
							String format = "yyyyMMddHHmmss";
							date = parseDateTime("" + value, format);
						} else if (value.toString().length() == 6) {
							String format = "HHmmss";
							date = parseDateTime("" + value, format);
						}
					}
					if (date != null) {
						method[i].invoke(bean, new Object[]{date});
					}
				} else if (type.equals("byte[]")) {
					method[i].invoke(bean,
							new Object[]{new String(value + "").getBytes()});
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}


	/**
	 * 日期格式转换
	 *
	 * @param dateValue
	 * @param format
	 * @return
	 * @author
	 * @time 下午14:02:59
	 */
	private static Date parseDateTime(String dateValue, String format) {
		SimpleDateFormat obj = new SimpleDateFormat(format);
		try {
			return obj.parse(dateValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
