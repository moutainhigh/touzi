package com.river.core.result;

import com.river.core.utils.FastJsonUtils;

public class ResultInfo {
	/**
	 * 执行成功
	 * @return
	 * @author yinovo
	 * 2017年9月30日 下午11:40:15
	 */
	public synchronized static String success() {
        return success(null, null);
    }
	
	/**
	 * 执行成功
	 * @return
	 * @author yinovo
	 * 2017年9月30日 下午11:40:15
	 */
	public synchronized static String success(Object obj) {
        return success(obj, null);
    }
	/**
	 * 转化执行成功, 并把成功的结果以JSON对象传给前端
	 * @param obj				转化的对象
	 * @param filterPropNames	需要过滤的字段
	 * @author yinovo
	 * 2017年9月30日 下午11:40:34
	 */
    public synchronized static String success(Object obj, String[] filterPropNames) {
        return success(obj, true, filterPropNames);
    }
    
    public synchronized static String success(Object obj, boolean isRefDetect, String[] filterPropNames) {
        ResponseBodyInfo<Object>body = new ResponseBodyInfo<>(0, "", obj);
        return FastJsonUtils.toJson(body, isRefDetect, filterPropNames);
    }

    
    /**
     * 返回错误对象
     * @param code		错误代码
     * @param message	错误信息
     * @author yinovo
     * 2017年9月30日 下午11:36:32
     */
    public synchronized static String error(int code, String message) {
        return error(code, message, null);
    }
    
    public synchronized static String error(int code, String message, Object obj) {
    	ResponseBodyInfo<Object>body = new ResponseBodyInfo<>(code, message, obj);
        return FastJsonUtils.entityToJsonSimple(body);
    }
}