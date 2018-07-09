package com.project.ydy.fragmentpassvalue.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;

public class MyLinePagerIndicator extends CommonLinePagerIndicator {

    public MyLinePagerIndicator(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //渐变色设置
        int[] ints = {0xFF4BD355, 0xFF4BD355};
        LinearGradient lg = new LinearGradient(getLineRect().left, getLineRect().top, getLineRect().right, getLineRect().bottom, ints, null, LinearGradient.TileMode.CLAMP);
        getPaint().setShader(lg);
        canvas.drawRoundRect(getLineRect(), 0, 0, getPaint());
//        canvas.drawRoundRect(getLineRect(), getRoundRadius(), getRoundRadius(), getPaint());
    }
}
