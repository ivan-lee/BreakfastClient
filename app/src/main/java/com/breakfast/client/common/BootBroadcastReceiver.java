package com.breakfast.client.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.breakfast.client.home.activity.SplashActivity;

/**
 * Created by Steven on 2017/6/9.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //MainActivity就是开机显示的界面
        Intent mBootIntent = new Intent(context, SplashActivity.class);
        //下面这句话必须加上才能开机自动运行app的界面
        mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mBootIntent);

    }
}
