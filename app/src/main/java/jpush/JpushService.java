package jpush;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;


import com.app.gaolonglong.fragmenttabhost.R;

import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * Created by hudong on 2016/11/22.
 */
public class JpushService {
    private static final String TAG = "JpushService";
    private Context context;
    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;
    private static JpushService instance=new JpushService();
    public static JpushService getInstance(){
        return instance;
    }
    public void init(Context context){
        this.context=context;
        JPushInterface.init(context);
        JPushInterface.setLatestNotificationNumber(context,1);
    }
    public void setDebugMode(boolean flg){
        JPushInterface.setDebugMode(flg);
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (ExampleUtil.isConnected(context.getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
        }

    };

    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (ExampleUtil.isConnected(context.getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
        }

    };
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(context.getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;

                case MSG_SET_TAGS:
                    Log.d(TAG, "Set tags in handler.");
                    JPushInterface.setAliasAndTags(context.getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
                    break;

                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
    public void setTag(String tag){
        // 检查 tag 的有效性
        if (TextUtils.isEmpty(tag)) {
            Log.i(TAG, context.getResources().getString(R.string.error_tag_empty));
            return;
        }

        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!ExampleUtil.isValidTagAndAlias(sTagItme)) {
                Log.i(TAG, context.getResources().getString(R.string.error_tag_gs_empty));
                return;
            }
            tagSet.add(sTagItme);
        }

        //调用JPush API设置Tag
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));

    }

    public void setAlias(String alias){
        if (TextUtils.isEmpty(alias)) {
            Log.i(TAG, context.getResources().getString(R.string.error_alias_empty));
            return;
        }
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            Log.i(TAG, context.getResources().getString(R.string.error_tag_gs_empty));
            return;
        }

        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }
    /*public final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    JpushReceiver mJpushReceiver;
    public void registerNotificationReceiver() {
        mJpushReceiver = new JpushReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        context.registerReceiver(mJpushReceiver, filter);
    }
    public void unRegisterNotificationReceiver() {
        context.unregisterReceiver(mJpushReceiver);
    }*/
}
