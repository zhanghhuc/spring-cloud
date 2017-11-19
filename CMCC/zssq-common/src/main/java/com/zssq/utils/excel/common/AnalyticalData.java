package com.zssq.utils.excel.common;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.zssq.utils.excel.exception.ExcelException;
import com.zssq.utils.excel.tools.DateTools;

import net.sf.cglib.beans.BeanMap;

@SuppressWarnings("all")
public class AnalyticalData implements Constant {
	public static void copyProperties(Object target, Object source, String[] properties) {
		BeanMap targetBeanMap = BeanMap.create(target);
		BeanMap srcBeanMap = BeanMap.create(source);
		Set<Entry> propertySet1 = srcBeanMap.entrySet();
		Object property = null;
		for (Entry entry : propertySet1) {
			property = entry.getKey();

			if ((properties != null) && (properties.length > 0)) {
				for (String propertyName : properties) {
					if ((property.equals(propertyName)) && (targetBeanMap.containsKey(property))) {
						targetBeanMap.put(property, entry.getValue());
						break;
					}
				}
			} else if (targetBeanMap.containsKey(property))
				targetBeanMap.put(property, entry.getValue());
		}
	}

	public static <T> T createBean(Class<T> clazz, Map<String, Object> srcMap, String[] tarProperties,
			String[] srcProperties) throws InstantiationException, IllegalAccessException {
		T t;
		t = (T) clazz.newInstance();
		BeanMap beanMap = BeanMap.create(t);

		if ((tarProperties != null) && (tarProperties.length > 0) && (srcProperties != null)
				&& (srcProperties.length > 0)) {
			int index = 0;
			for (String tarProperty : tarProperties) {
				if (tarProperty.contains(".")) {
					String[] _tarProperties = tarProperty.split("\\.");
					int _index = 0;
					Object _obj = t;
					do
						_obj = BeanMap.create(_obj).get(_tarProperties[(_index++)]);
					while (_index < _tarProperties.length - 1);

					BeanMap.create(_obj).put(_tarProperties[_index], srcMap.get(srcProperties[(index++)]));
				} else {
					Object val = srcMap.get(srcProperties[(index++)]);
					beanMap.put(tarProperty, val);
				}
			}
		}
		return t;
	}

	public static String analyFieldType(Object title) {
		String val = "";
		if ((title instanceof Integer))
			val = ((Integer) title).toString();
		else if ((title instanceof Date))
			val = DateTools.format((Date) title);
		else if ((title instanceof Long))
			val = ((Long) title).toString();
		else if ((title instanceof Double))
			val = ((Double) title).toString();
		else if ((title instanceof Float))
			val = ((Float) title).toString();
		else if ((title instanceof String)) {
			val = title.toString();
		}

		return val;
	}

	public static Object getAttribute(Class className, String parameter, String value) {
		try {
			Field filed = className.getDeclaredField(parameter);

			if (filed != null) {
				String tyep = filed.getType().toString();
				if (("int".equals(tyep)) || ("class java.lang.Integer".equals(tyep)))
					return (value == null) || ("".equals(value)) ? null : Integer.valueOf(Integer.parseInt(value));
				if (("shot".equals(tyep)) || ("class java.lang.Short".equals(tyep)))
					return (value == null) || ("".equals(value)) ? null : Short.valueOf(Short.parseShort(value));
				if (("long".equals(tyep)) || ("class java.lang.Long".equals(tyep)))
					return (value == null) || ("".equals(value)) ? null : Long.valueOf(Long.parseLong(value));
				if (("float".equals(tyep)) || ("class java.lang.Float".equals(tyep)))
					return (value == null) || ("".equals(value)) ? null : Float.valueOf(Float.parseFloat(value));
				if (("double".equals(tyep)) || ("class java.lang.Double".equals(tyep)))
					return (value == null) || ("".equals(value)) ? null : Double.valueOf(Double.parseDouble(value));
				if ("class java.util.Date".equals(tyep))
					return (value == null) || ("".equals(value)) ? null : DateTools.format(value);
			}
		} catch (Exception e) {
			throw new ExcelException("getAttribute method Exception :" + e.getMessage());
		}
		return value;
	}
}