package com.util;

import com.alibaba.fastjson.JSONObject;
import com.ddSdk.base.Env;
import com.ddSdk.base.OApiException;
import com.ddSdk.utils.HttpHelper;

/**
 * Created by pwj on 2016/11/1.
 * 传入的参数
 */
public class AttendanceWork {
    public static String getSuiteToken(String userId, String workDateFrom,String workDateTo,String Access_token) {
        //这是请求接口的地址
        //public static final String OAPI_HOST = "https://oapi.dingtalk.com";
        String url = Env.OAPI_HOST + "/attendance/list?access_token=" + Access_token;
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("workDateFrom", workDateFrom);
        json.put("workDateTo", workDateTo);
        json.put("outtime", 10000);
        JSONObject reponseJson = null;
        String suiteAccessToken = null;
        try {
            reponseJson = HttpHelper.httpPost(url, json);
            //suiteAccessToken = reponseJson.getString("access_token");
        } catch (OApiException e) {
            e.printStackTrace();
        }
        return reponseJson.toJSONString();
    }

}
