package com.zssq.utils.excel.annotation;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.zssq.utils.excel.common.Constant;

import net.sf.cglib.beans.BeanMap;

@SuppressWarnings("all")
public class AnnotationUtil implements Constant {
    /**
     * 获取类模板中注解值 属性为key 注解值为value
     * 
     * @param className
     *            类名
     * @return 注解属性键值对
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws IllegalClassFormatException
     */
    public static Map<String, Object> getAnnotationTitleMap(Class className)
            throws IllegalArgumentException, IllegalAccessException {
        // 获取当前类中属性与注解值
        Field filed[] = className.getDeclaredFields();
        int lenght_ = filed.length;
        Map<String, Object> value_Map = new LinkedHashMap<String, Object>(
                lenght_);
        if (null != filed && lenght_ > NUMBER_ZERO) {
            for (int i = NUMBER_ZERO; i < lenght_; i++) {
                Field filed_ = filed[i];
                String filed_name = filed_.getName();
                ExcelAnnotation excleAn = filed_
                        .getAnnotation(ExcelAnnotation.class);
                if (null != excleAn) {
                	// 如果是隐藏列就放入 null中（只允许一行）
                	if(excleAn.hidden()){
                		value_Map.put(filed_name, filed_name+HIDDEN_FLAG);
                	}else{
                		value_Map.put(filed_name, excleAn.value());
                	}
                }
            }
        }
        return value_Map;
    }
 
    /**
     * 获取类模板中注解值 注解值为key 属性为value
     * 
     * @param className
     *            类名
     * @return 注解属性键值对
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws IllegalClassFormatException
     */
    public static Map<String, Object> getTitleValueMap(Class className)
            throws IllegalArgumentException, IllegalAccessException {
        // 获取当前类中属性与注解值
        Field filed[] = className.getDeclaredFields();
        int lenght_ = filed.length;
        Map<String, Object> value_Map = new LinkedHashMap<String, Object>(
                lenght_);
        if (null != filed && lenght_ > NUMBER_ZERO) {
            for (int i = NUMBER_ZERO; i < lenght_; i++) {
                Field filed_ = filed[i];
                String filed_name = filed_.getName();
                ExcelAnnotation excleAn = filed_
                        .getAnnotation(ExcelAnnotation.class);
                if (null != excleAn) {
                	// 如果是隐藏列就放入 null中（只允许一行）
                	if(excleAn.hidden()){
                		 value_Map.put(filed_name+HIDDEN_FLAG, filed_name);
                	}else{
                		 value_Map.put(excleAn.value(), filed_name);
                	}
                   
                }
            }
        }
        return value_Map;
    }
 
    /**
     * 获取类模板中注解值
     * 
     * @param className
     *            类名
     * @return 注解属性键值对
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws IllegalClassFormatException
     */
    public static Map<String, Object> getTitleType(Class className)
            throws IllegalArgumentException, IllegalAccessException {
        // 获取当前类中属性与注解值
        Field filed[] = className.getDeclaredFields();
        int lenght_ = filed.length;
        Map<String, Object> value_Map = new LinkedHashMap<String, Object>(
                lenght_);
        if (null != filed && lenght_ > NUMBER_ZERO) {
            for (int i = NUMBER_ZERO; i < lenght_; i++) {
                Field filed_ = filed[i];
                String filed_name = filed_.getName();
                ExcelAnnotation excleAn = filed_
                        .getAnnotation(ExcelAnnotation.class);
                if (null != excleAn) {
                    value_Map.put(filed_name, excleAn.value());
                }
            }
        }
        return value_Map;
    }
 
    /**
     * 获取类模板中注解字段
     * 
     * @param className
     *            类名
     * @return 注解属性键值对
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws IllegalClassFormatException
     */
    public static Object[] getAnnotationTitle(Class className)
            throws IllegalArgumentException, IllegalAccessException {
        // 获取当前类中属性与注解值
        Field filed[] = className.getDeclaredFields();
        Set<String> set = new HashSet<String>(50);
        int lenght_ = filed.length;
        if (null != filed && lenght_ > NUMBER_ZERO) {
            for (int i = NUMBER_ZERO; i < lenght_; i++) {
                Field filed_ = filed[i];
                String filed_name = filed_.getName();
                ExcelAnnotation excleAn = filed_
                        .getAnnotation(ExcelAnnotation.class);
                if (null != excleAn) {
                    set.add(filed_name);
                }
            }
        }
        return set.toArray();
    }
 
    /**
     * 获取注解类中的数据
     * 
     * @param className
     *            对象名
     * @param list
     *            包含多个待解析对象集合
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     */
    public static List<BeanMap> getAnnotationData(Class className,
            Collection<Object> bodyList) throws IllegalArgumentException,
            IllegalAccessException, InstantiationException {
 
        // 获取当前类中注解
        Object[] properties = getAnnotationTitle(className);
        List<BeanMap> bodyMap = new ArrayList<BeanMap>(bodyList.size());
 
        // 解析集合對象
        if (null == properties || properties.length == NUMBER_ZERO) {
            throw new IllegalArgumentException(className.getName()
                    + " Does not contain ExcelAnnotation!");
        }
 
        if (null != bodyList && NUMBER_ZERO < bodyList.size()) {
            for (Object value : bodyList) {
                // 目标对象映射
                BeanMap targetBeanMap = BeanMap.create(className.newInstance());
                // 源对象映射
                BeanMap srcBeanMap = BeanMap.create(value);
                srcBeanMap.setBean(value);
 
                // 获取源的属性键值对集合
                Set<Entry> propertySet = srcBeanMap.entrySet();
                Object property = null;
                // 迭代属性键值对
                for (Entry entry : propertySet) {
                    property = entry.getKey();
                    // 如果指定了属性拷贝，则根据属性名称进行过滤并且拷贝。
                    // 否则就对比两个对象，只要是相同的属性，就拷贝。
                    if (null != properties && properties.length > NUMBER_ZERO) {
                        for (Object propertyName : properties) {
                            if (property.equals((String) propertyName)
                                    && targetBeanMap.containsKey(property)) {
                                targetBeanMap.put(property, entry.getValue());
                                break;
                            }
                        }
                    }
                }
                bodyMap.add(targetBeanMap);
            }
        }
        return bodyMap;
    }
 
    /**
     * 拷贝bean的属性值
     * 
     * @param target
     *            拷贝目标对象
     * @param source
     *            拷贝源对象
     * @param properties
     *            要拷贝的属性名称
     */
    public static void copyProperties(Object target, Object source,
            String[] properties) {
        // 目标对象映射
        BeanMap targetBeanMap = BeanMap.create(target);
        // 源对象映射
        BeanMap srcBeanMap = BeanMap.create(source);
        // 获取源的属性键值对集合
        Set<Entry> propertySet = srcBeanMap.entrySet();
        Object property = null;
        // 迭代属性键值对
        for (Entry entry : propertySet) {
            property = entry.getKey();
            // 如果指定了属性拷贝，则根据属性名称进行过滤并且拷贝。
            // 否则就对比两个对象，只要是相同的属性，就拷贝。
            if (null != properties && properties.length > NUMBER_ZERO) {
                for (String propertyName : properties) {
                    if (property.equals(propertyName)
                            && targetBeanMap.containsKey(property)) {
                        targetBeanMap.put(property, entry.getValue());
                        break;
                    }
                }
            } else {
                if (targetBeanMap.containsKey(property)) {
                    targetBeanMap.put(property, entry.getValue());
                }
            }
        }
    }
 
    /**
     * 根据指定类型创建bean，并且根据属性和map对bean赋值；
     * 
     * @param <T>
     *            泛型类型
     * @param clazz
     *            类型
     * @param beanMap
     *            对象映射
     * @param tarProperties
     *            目标属性
     * @param srcProperties
     *            源属性
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T createBean(Class<T> clazz, Map<String, Object> srcMap,
            String[] tarProperties, String[] srcProperties)
            throws InstantiationException, IllegalAccessException {
        T t;
        t = (T) clazz.newInstance();
        BeanMap beanMap = BeanMap.create(t);
 
        // 如果传入了参数属性，则按照参数进行对象赋值
        if (null != tarProperties && tarProperties.length > 0
                && null != srcProperties && srcProperties.length > 0) {
            int index = 0;
            for (String tarProperty : tarProperties) {
                // 如果对象的属性包含.，则递归get访问，
                if (tarProperty.contains(".")) {
                    String[] _tarProperties = tarProperty.split("\\.");
                    int _index = 0;
                    Object _obj = t;
                    do {
                        _obj = BeanMap.create(_obj).get(
                                _tarProperties[_index++]);
                    } while (_index < (_tarProperties.length - 1));
 
                    BeanMap.create(_obj).put(_tarProperties[_index],
                            srcMap.get(srcProperties[index++]));
 
                } else {
                    Object val = srcMap.get(srcProperties[index++]);
                    beanMap.put(tarProperty, val);
                }
            }
        }
        return t;
    }
}