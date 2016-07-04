package me.codego.view;

import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * 拦截快速点击事件
 * Created by mengxn on 16/6/8.
 */
public abstract class OnEffectiveClickListener implements View.OnClickListener{

    private int effectiveTime = DEFAULT_EFFECTIVE_TIME;
    private long lastClickTime;
    private int clickCount;

    private final int WHAT_CLICK = 1;

    private static final int DEFAULT_EFFECTIVE_TIME = 300;//default 300ms

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_CLICK:
                    callbackEffectiveClick((View) msg.obj);
                    lastClickTime = System.currentTimeMillis();
                    break;
            }
        }
    };

    public OnEffectiveClickListener() {
    }

    public OnEffectiveClickListener(int effectiveTime) {
        //must > 300ms
        this.effectiveTime = Math.max(DEFAULT_EFFECTIVE_TIME, effectiveTime);
    }

    @Override
    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime > effectiveTime) {
            callbackEffectiveClick(view);
        } else {
            clickCount ++;
            handler.removeMessages(WHAT_CLICK);
            handler.sendMessageDelayed(handler.obtainMessage(WHAT_CLICK, view), effectiveTime);
        }
        lastClickTime = currentTimeMillis;
    }

    private void callbackEffectiveClick(View view) {
        onEffectiveClick(view);
        onEffectiveCountClick(view, clickCount);
        clickCount = 0;
    }

    public abstract void onEffectiveClick(View view);

    public void onEffectiveCountClick(View view, int count) {

    }
}
