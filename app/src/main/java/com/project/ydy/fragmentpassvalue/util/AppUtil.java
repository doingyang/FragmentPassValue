package com.project.ydy.fragmentpassvalue.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * **************************************************
 * 文件名称 : AppUtil
 * 作    者 : Created by ydy
 * 创建时间 : 2018/5/26 10:58
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/5/26 1.00 初始版本
 * **************************************************
 */
public class AppUtil {

    /**
     * 获取版本号
     */
    public static int getAppVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi.versionCode;
    }

    /**
     * 获取版本名称
     */
    public static String getAppVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi.versionName;
    }

    /**
     * SIM卡运营商
     * 如中国移动，中国联通，中国电信
     */
    @SuppressLint("MissingPermission")
    public static String getOperators(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = null;
        String imsi = tm.getSubscriberId();
        if (imsi == null || "".equals(imsi)) {
            return null;
        }
        if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
            operator = "中国移动";
        } else if (imsi.startsWith("46001")) {
            operator = "中国联通";
        } else if (imsi.startsWith("46003")) {
            operator = "中国电信";
        }
        return operator;
    }


    /**
     * 网络类型
     */
    public static int getNetworkType(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkType();
    }

    /**
     * 获取IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return String.valueOf(telephonyManager.getDeviceId());
    }

    /**
     * 获取设备型号
     * 如谷歌nexus
     */
    public static String getBuildBrandModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备状态栏的高度（px）
     */
    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    /**
     * 获取导航栏高度
     * @param context 上下文
     * @return int 高度
     */
    public static int getNavigationHeight(Context context) {
        int result = 0;
        int resourceId = 0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * 获得设备的宽和高尺寸
     * float[0]存放设备屏幕的宽（px值）
     * float[1]存放设备屏幕的高（px值）
     */
    public static float[] getDeviceDisplaySize(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        float[] size = new float[2];
        size[0] = width;
        size[1] = height;

        return size;
    }

    /**
     * 检测网络是否可用
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     *
     */
    public static float getAutoFitTextSize(Context context, TextView textView, int type, int limit, int precision) {
        if (type == TypedValue.COMPLEX_UNIT_PX) {
            float textSize = textView.getTextSize();
            while (true) {
                //计算所有文本占有的屏幕宽度(pix)
                float textWidth = textView.getPaint().measureText(textView.getText().toString());
                //如果所有文本的宽度超过TextView自身限定的宽度，那么就尝试迭代的减小字体的textSize，直到不超过TextView的宽度为止。
                if (textWidth > limit) {
                    textSize = (int) textView.getTextSize();
                    textSize = textSize - precision;
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                } else {
                    break;
                }
            }
            return textSize;
        } else {
            return getAutoFitTextSize(context, textView, limit, precision);
        }
    }

    /**
     * 一个TextView限定宽度，然后寻找合适的自适应字体大小textSize
     */
    public static float getAutoFitTextSize(Context context, TextView mTextView, int dipLimitTextViewWidth, int precisionAccuracy) {
        float textSize = mTextView.getTextSize();
        //获取dipLimitTextViewWidth转换后的设备pix值。
        int mTextViewWidth = dip2px(context, dipLimitTextViewWidth);
        while (true) {
            //计算所有文本占有的屏幕宽度(pix)
            float textWidth = mTextView.getPaint().measureText(mTextView.getText().toString());
            //如果所有文本的宽度超过TextView自身限定的宽度，那么就尝试迭代的减小字体的textSize，直到不超过TextView的宽度为止。
            if (textWidth > mTextViewWidth) {
                textSize = (int) mTextView.getTextSize();
                textSize = textSize - precisionAccuracy;
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            } else {
                break;
            }
        }
        return textSize;
    }

    /**
     * px转dp
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转sp
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * dp转px
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
