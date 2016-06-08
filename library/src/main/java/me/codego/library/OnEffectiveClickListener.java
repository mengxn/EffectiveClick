package me.codego.library;

import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * 拦截快速点击事件
 * Created by mengxn on 16/6/8.
 */
public abstract class OnEffectiveClickListener implements View.OnClickListener{

    private int effectiveTime = 300;//default 300ms
    private long lastClickTime;

    private final int WHAT_CLICK = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_CLICK:
                    onEffectiveClick((View) msg.obj);
                    break;
            }
        }
    };

    public OnEffectiveClickListener() {
    }

    public OnEffectiveClickListener(int effectiveTime) {
        //must >300
        if (effectiveTime > 300) {
            this.effectiveTime = effectiveTime;
        }
    }

    @Override
    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime > effectiveTime) {
            onEffectiveClick(view);
        } else {
            handler.removeMessages(WHAT_CLICK);
            handler.sendMessageDelayed(handler.obtainMessage(WHAT_CLICK, view), effectiveTime);
        }
        lastClickTime = currentTimeMillis;
    }


    public abstract void onEffectiveClick(View view);
}
