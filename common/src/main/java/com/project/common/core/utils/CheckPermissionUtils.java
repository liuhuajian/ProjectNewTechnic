package com.project.common.core.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */

public class CheckPermissionUtils extends Activity {
    public static final int REQUEST_CODE_LOCATION = 1;
    public static final int REQUEST_CODE_WRITE_STORAGE = 2;
    public static final int REQUEST_CODE_READ_STORAGE = 3;
    public static final int REQUEST_CODE_CARMER = 4;
    public static final int REQUEST_CODE_CALL_PHONE = 5;

    private static volatile CheckPermissionUtils checkPermissionUtils = null;

    private CheckPermissionUtils() {
    }

    public static CheckPermissionUtils getInstance() {
        if (checkPermissionUtils == null) {
            synchronized (CheckPermissionUtils.class) {
                if (checkPermissionUtils == null) {
                    checkPermissionUtils = new CheckPermissionUtils();
                }
            }
        }
        return checkPermissionUtils;
    }

    public interface RequestPermissInterface {
        void access();
    }

    //需要申请的权限
    private static String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,     //REQUEST_CODE_LOCATION
            Manifest.permission.WRITE_EXTERNAL_STORAGE,     //REQUEST_CODE_WRITE_STORAGE
            Manifest.permission.READ_EXTERNAL_STORAGE,      //REQUEST_CODE_READ_STORAGE
            Manifest.permission.CAMERA,                     //REQUEST_CODE_CARMER
            Manifest.permission.CALL_PHONE                  //REQUEST_CODE_CALL_PHONE
    };

    /**
     * 检测所有需要权限
     *
     * @param context
     * @return
     */
    public String[] checkPermission(Context context) {
        List<String> data = new ArrayList<>();//存储未申请的权限
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {//未申请
                MyLogger.i("permission-->" + permission);
                data.add(permission);
            }
        }
        return data.toArray(new String[data.size()]);
    }

    /**
     * 检查指定权限
     *
     * @param permission
     * @param context
     * @param requestPermissInterface
     */
    public void checkPermission(String permission, Context context, RequestPermissInterface requestPermissInterface) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
        boolean carmerPermission = checkSelfPermission == PackageManager.PERMISSION_GRANTED;
        if (!carmerPermission) {
            int index = Arrays.asList(permissions).indexOf(permission); //该索引需要与REQUEST和permissions相对应
            if (index == -1) {
                MyLogger.i("不存在该权限-->" + permission);
                return;
            }
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, index + 1);
        } else {
            requestPermissInterface.access();
        }
    }

    public void checkPermissions(String[] permissions, Context context,int requestCode, RequestPermissInterface requestPermissInterface) {
        List<String> grantedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            boolean carmerPermission = checkSelfPermission == PackageManager.PERMISSION_GRANTED;
            if (!carmerPermission)
                grantedPermissions.add(permission);
        }
        if (grantedPermissions.size() == 0) {
            requestPermissInterface.access();
        } else {
            ActivityCompat.requestPermissions((Activity) context, grantedPermissions.toArray(new String[grantedPermissions.size()]), requestCode);
        }
    }

    public boolean checkPermissionLocation(Context context) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        boolean locationPermission = checkSelfPermission == PackageManager.PERMISSION_GRANTED;
        boolean locationEnable = isLocationEnabled(context);
        MyLogger.i("locationPermission-->" + locationPermission + "--locationEnable-->" + locationEnable);
        return locationEnable && locationPermission;
    }

    /**
     * 判断定位服务是否开启
     *
     * @param context
     * @return
     */
    private boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }
}
