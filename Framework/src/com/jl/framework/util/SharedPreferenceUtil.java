package com.jl.framework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * 
 * @author huang.b
 * @version 2013-7-9 下午3:28:55
 */
public class SharedPreferenceUtil {
	// 无效标记
	public static final int INVALID_CODE = -1;

	private static final String TAG = "SharedPreferenceUtil";
	private static final String SEPARATOR = "_";

	/**
	 * 根据KEY 查询字符串值
	 * 
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param key
	 *            key
	 * @return String
	 */
	public static String getStringValueByKey(Context mContext, String fileName,
			String key) {
		String value = "";
		if (!StringUtil.isNullOrEmpty(key)) {
			SharedPreferences sharedPreferences = mContext
					.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			value = sharedPreferences.getString(key, "");
		}
		return value;
	}

	/**
	 * 根据KEY 查询Boolean值
	 * 
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param key
	 *            key
	 * @return boolean
	 */
	public static boolean getBooleanValueByKey(Context mContext,
			String fileName, String key) {
		boolean value = false;
		if (!StringUtil.isNullOrEmpty(key)) {
			SharedPreferences sharedPreferences = mContext
					.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			value = sharedPreferences.getBoolean(key, false);
		}
		return value;
	}

	/**
	 * 根据KEY 查询Integer值
	 * 
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param key
	 *            key
	 * @return int
	 */
	public static int getIntegerValueByKey(Context mContext, String fileName,
			String key) {
		int value = INVALID_CODE;
		if (!StringUtil.isNullOrEmpty(key)) {
			SharedPreferences sharedPreferences = mContext
					.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			value = sharedPreferences.getInt(key, INVALID_CODE);
		}
		return value;
	}

	/**
	 * 根据KEY 查询float值
	 * 
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param key
	 *            key
	 * @return int
	 */
	public static float getFloatValueByKey(Context mContext, String fileName,
			String key) {
		float value = INVALID_CODE;
		if (!StringUtil.isNullOrEmpty(key)) {
			SharedPreferences sharedPreferences = mContext
					.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			value = sharedPreferences.getFloat(key, INVALID_CODE);
		}
		return value;
	}

	/**
	 * 避免多个地方同时修改所以加synchronized; 保存本地数据，在读取的时候就不需要加了；
	 * 
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public static synchronized void saveValue(Context mContext,
			String fileName, String key, Object value) {
		if (!StringUtil.isNullOrEmpty(key)) {
			SharedPreferences sharedPreferences = mContext
					.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			if (value != null) {
				if (value instanceof String) {
					editor.putString(key, String.valueOf(value));
				} else if (value instanceof Integer) {
					editor.putInt(key, (Integer) value);
				} else if (value instanceof Boolean) {
					editor.putBoolean(key, (Boolean) value);
				}
				editor.commit();
			}
		}
	}

	/**
	 * 避免多个地方同时修改所以加synchronized;
	 * 
	 * @Title: removeValue
	 * @Description: 移除保存的数据
	 * @param mContext
	 *            上下文对象
	 * @param fileName
	 *            fileName
	 * @param key
	 *            待移除数据的key
	 */
	public static synchronized void removeValue(Context mContext,
			String fileName, String key) {
		if (!StringUtil.isNullOrEmpty(key)) {
			SharedPreferences sharedPreferences = mContext
					.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.remove(key);
			editor.commit();
		}
	}

	/**
	 * 直接将对象保存，字段类型只能为String,Integer,Long,Boolean,Float,Double类型，如果有其它类型，
	 * 那么需要另做对象保存；
	 * 
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param obj
	 *            obj
	 */
	public static synchronized void saveObject(Context mContext,
			String fileName, Object obj) {
		saveObject(mContext, fileName, obj, null);
	}

	/**
	 * 如果同一个对象可以在SharePreference中保存多个实例，如WeiboInfoModel; 可以有Sina，QQ，RenRen
	 * 直接将对象保存，字段类型只能为String,Integer,Long,Boolean,Float,Double类型，如果有其它类型，
	 * 那么需要另做对象保存；
	 * 
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param obj
	 *            obj
	 * @param keyPrefixType
	 *            前缀类型；
	 * @throws
	 */
	public static synchronized void saveObject(Context mContext,
			String fileName, Object obj, String keyPrefixType) {
		String prefix = "";
		if (keyPrefixType != null && keyPrefixType.length() > 0) {
			prefix = keyPrefixType + SEPARATOR;
		}
		Class clazz = obj.getClass();
		EvtLog.d(TAG, "saveObject:" + clazz.getName() + "----" + obj.toString());
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
				fileName, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		// 鑾峰彇瀹炰綋绫荤殑鎵�湁灞炴�锛岃繑鍥濬ield鏁扮粍
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.getName() == null) {
					continue;
				}
				if (field.get(obj) == null) {
					editor.remove(prefix + field.getName());
					continue;
				}
				// 濡傛灉绫诲瀷鏄疭tring// 濡傛灉绫诲瀷鏄疘nteger// 濡傛灉绫诲瀷鏄疊oolean 鏄皝瑁呯被
				if (field.getType().toString().equals("class java.lang.String")) {
					editor.putString(prefix + field.getName(), field.get(obj)
							.toString());
				} else if (field.getType().toString()
						.equals("class java.lang.Integer")
						|| field.getType().toString().equals("int")) {
					editor.putInt(prefix + field.getName(),
							Integer.parseInt(field.get(obj).toString()));
				} else if (field.getType().toString()
						.equals("class java.lang.Boolean")
						|| field.getType().toString().equals("boolean")) {
					editor.putBoolean(prefix + field.getName(),
							Boolean.parseBoolean(field.get(obj).toString()));
				} else if (field.getType().toString()
						.equals("class java.lang.Long")
						|| field.getType().toString().equals("long")) {
					editor.putLong(prefix + field.getName(),
							Long.parseLong(field.get(obj).toString()));
				} else if (field.getType().toString()
						.equals("class java.lang.Double")
						|| field.getType().toString()
								.equals("class java.lang.Float")
						|| field.getType().toString().equals("double")
						|| field.getType().toString().equals("float")) {
					editor.putFloat(prefix + field.getName(),
							Float.parseFloat(field.get(obj).toString()));
				}
			} catch (NumberFormatException e) {
				EvtLog.w(TAG, " saveObject  error :" + e);
			} catch (IllegalArgumentException e) {
				EvtLog.w(TAG, " saveObject  error :" + e);
			} catch (IllegalAccessException e) {
				EvtLog.w(TAG, " saveObject  error :" + e);
			}
		}
		editor.commit();
	}

	/**
	 * 直接从SharePreference中获得需要的实体类；
	 * 
	 * @Method: getObject
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param clazz
	 *            需要返回的实体类变量；
	 * @return Object
	 */
	public static Object getObject(Context mContext, String fileName,
			Class clazz) {
		return getObject(mContext, fileName, clazz, null);
	}

	/**
	 * 
	 * @Method: getObject
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param clazz
	 *            clazz
	 * @param keyPrefixType
	 *            keyPrefixType
	 * @return Object
	 * @throws
	 */
	public static Object getObject(Context mContext, String fileName,
			Class clazz, String keyPrefixType) {
		Object object = null;
		try {
			// 寰楀埌鍓嶇紑瀛楃涓诧紱
			String prefix = "";
			if (keyPrefixType != null && keyPrefixType.length() > 0) {
				prefix = keyPrefixType + SEPARATOR;
			}

			object = clazz.newInstance();
			SharedPreferences sharedPreferences = mContext
					.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			// 鑾峰彇瀹炰綋绫荤殑鎵�湁灞炴�锛岃繑鍥濬ield鏁扮粍
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Class<?> typeClass = field.getType();
				Constructor<?> con;
				Object valueObj = null;
				// 濡傛灉绫诲瀷鏄疭tring// 濡傛灉绫诲瀷鏄疘nteger// 濡傛灉绫诲瀷鏄疊oolean 鏄皝瑁呯被
				if (typeClass.toString().equals("class java.lang.String")) {
					con = String.class.getConstructor(String.class);
					String value = sharedPreferences.getString(
							prefix + field.getName(), "");
					valueObj = con.newInstance(value);
				} else if (typeClass.toString().equals(
						"class java.lang.Integer")
						|| typeClass.toString().equals("int")) {
					con = Integer.class.getConstructor(String.class);
					Integer value = sharedPreferences.getInt(
							prefix + field.getName(), -1);
					valueObj = con.newInstance(value + "");
				} else if (typeClass.toString().equals(
						"class java.lang.Boolean")
						|| typeClass.toString().equals("boolean")) {
					con = Boolean.class.getConstructor(String.class);
					Boolean value = sharedPreferences.getBoolean(
							prefix + field.getName(), false);
					valueObj = con.newInstance(value + "");
				} else if (typeClass.toString().equals("class java.lang.Long")
						|| typeClass.toString().equals("long")) {
					con = Long.class.getConstructor(String.class);
					Long value = sharedPreferences.getLong(
							prefix + field.getName(), -1);
					valueObj = con.newInstance(value + "");
				} else if (typeClass.toString().equals("class java.lang.Float")
						|| typeClass.toString().equals("float")) {
					con = Float.class.getConstructor(String.class);
					Float value = sharedPreferences.getFloat(
							prefix + field.getName(), -1f);
					valueObj = con.newInstance(value + "");
				} else if (typeClass.toString()
						.equals("class java.lang.Double")
						|| typeClass.toString().equals("double")) {
					con = Double.class.getConstructor(String.class);
					Float value = sharedPreferences.getFloat(
							prefix + field.getName(), -1f);
					valueObj = con.newInstance(value + "");
				}
				if (valueObj != null) {
					if (Modifier.toString(field.getModifiers()) != null
							&& Modifier.toString(field.getModifiers())
									.endsWith("final")) {
						// 濡傛灉涓篺inal绫诲瀷锛岄偅涔堜笉鑳借祴鍊硷紝涓嶇劧浼氭姤閿欙紱
					} else {
						field.set(object, valueObj);
					}
				}
			}
			return object;
		} catch (Exception e) {
			EvtLog.w(TAG, " getObject  error :" + e);
		}
		return object;
	}

	/**
	 * 清除当前Model的本地存储；
	 * 
	 * @Method: removeObject
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @throws
	 */
	public static void clearObject(Context mContext, String fileName) {
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
				fileName, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 清除当前Model的本地存储以某些关键字为前缀的缓存；如同时删除会员卡信息UserReq.clearMemberCardInfo()
	 * 
	 * @Method: removeObject
	 * @param mContext
	 *            mContext
	 * @param fileName
	 *            fileName
	 * @param prefix
	 *            prefix
	 * @throws
	 */
	public static void clearAllByPrefix(Context mContext, String fileName,
			String prefix) {
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
				fileName, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();

		Map<String, String> map = (Map<String, String>) sharedPreferences
				.getAll();

		Set<Entry<String, String>> set = map.entrySet();
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
				.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			// 如果是以指定的prefix为前缀，则删除；
			if (entry.getKey().startsWith(prefix)) {
				editor.remove(entry.getKey());
			}
		}
		editor.commit();
	}
}
