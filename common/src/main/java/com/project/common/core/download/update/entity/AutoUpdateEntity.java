package com.project.common.core.download.update.entity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by julyzeng on 2016-08-19.
 */
@SuppressWarnings("ALL")
public class AutoUpdateEntity {
    private static String TAG ="AutoUpdateEntity";
    private String versionCode;
    private String versionName;
    private String updateMessage;
    private String apkUrl;
    private boolean isforce;

	public AutoUpdateEntity(){

	}

    /**
     *
     * @param jsonInfo 包含本类实体属性的json字符串
     */
    public AutoUpdateEntity(String jsonInfo){
        String info = jsonInfo.toLowerCase();
        Log.e("info", info);
        try {
            JSONObject object = new JSONObject(jsonInfo);
            setVersionCode(object.getString("versionCode"));
            setVersionName(object.getString("versionName"));
            setUpdateMessage(object.getString("updateMessage"));
            setApkUrl(object.getString("apkUrl"));
			setIsforce(object.getBoolean("isforce"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v(TAG,e.toString());
        }
    }

	public static String getTAG() {
		return TAG;
	}

	public static void setTAG(String TAG) {
		AutoUpdateEntity.TAG = TAG;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public boolean isforce() {
		return isforce;
	}

	public void setIsforce(boolean isforce) {
		this.isforce = isforce;
	}
}
