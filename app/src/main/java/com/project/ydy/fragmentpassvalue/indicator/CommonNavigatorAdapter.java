package com.project.ydy.fragmentpassvalue.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.project.ydy.fragmentpassvalue.R;
import com.project.ydy.fragmentpassvalue.util.AppUtil;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;

public class CommonNavigatorAdapter extends net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter {

    private ViewPager mViewPager;
    private ArrayList<String> mTitles;

    private int typeValue = TypedValue.COMPLEX_UNIT_DIP;
    private float textSize = 16;//dp值
    private boolean isAutoFit = true;

    public CommonNavigatorAdapter(ViewPager mViewPager, ArrayList<String> mTitles) {
        this.mViewPager = mViewPager;
        this.mTitles = mTitles;
    }

    public CommonNavigatorAdapter(ViewPager mViewPager, String[] titles) {
        this.mViewPager = mViewPager;
        mTitles = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mTitles.add(titles[i]);
        }
    }

    /**
     * 设置切换到的tab的文字大小
     */
    public void setTextSize(int type, float size) {
        setTextSize(type, size, false);
    }

    public void setTextSize(int type, float size, boolean fit) {
        this.typeValue = type;
        this.textSize = size;
        this.isAutoFit = fit;
    }

    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        CommonPagerTitleView titleView = new CommonPagerTitleView(context);
        titleView.setText(mTitles.get(index));
        titleView.setTextSize(typeValue, textSize);
        titleView.setSizeToFit(isAutoFit);

        titleView.setNormalColor(context.getResources().getColor(R.color.colorDark));
        titleView.setSelectedColor(context.getResources().getColor(R.color.colorGreen));
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(index);
            }
        });

        return titleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        MyLinePagerIndicator indicator = new MyLinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineHeight(AppUtil.dip2px(context, 3));
        indicator.setRoundRadius(AppUtil.dip2px(context, 3));
        indicator.setLineWidth(AppUtil.dip2px(context, 30));

        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));

        return indicator;
    }
}
