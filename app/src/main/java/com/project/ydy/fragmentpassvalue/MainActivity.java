package com.project.ydy.fragmentpassvalue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.ydy.fragmentpassvalue.base.BaseFragment;
import com.project.ydy.fragmentpassvalue.bean.Goods;
import com.project.ydy.fragmentpassvalue.fragment.FragmentGoods;
import com.project.ydy.fragmentpassvalue.fragment.FragmentQualityInspection;
import com.project.ydy.fragmentpassvalue.indicator.CommonNavigatorAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    private String[] titles = new String[]{"质检商品", "待检商品"};
    private List<BaseFragment> fragments = new ArrayList<>();

    private MagicIndicator magicIndicator;
    private ViewPager viewPager;
    private FragmentQualityInspection fragmentQualityInspection;
    private FragmentGoods fragmentGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initTable();
    }

    /**
     * 获取被选择的数据集合，即质检商品界面当前的数据
     */
    public List<Goods> getSelectedData() {
        if (null != fragmentQualityInspection) {
            Log.i(TAG, "MainActivity:getSelectedData ");
            return fragmentQualityInspection.getInspectionList();
        } else {
            Log.i(TAG, "MainActivity:getSelectedData null");
            return new ArrayList<>();
        }
    }

    /**
     * 获取进行质检的数据，即待检商品界面选中的数据
     */
    public List<Goods> getInspectionData() {
        if (null != fragmentGoods) {
            Log.i(TAG, "MainActivity:getInspectionData ");
            return fragmentGoods.getSelectList();
        } else {
            Log.i(TAG, "MainActivity:getInspectionData null");
            return new ArrayList<>();
        }
    }

    private void initFragment() {
        fragmentQualityInspection = FragmentQualityInspection.newInstance();
        fragmentGoods = FragmentGoods.newInstance();
        fragments.add(fragmentQualityInspection);
        fragments.add(fragmentGoods);
    }

    private void initTable() {
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        CommonNavigator commonNavigator = new CommonNavigator(getApplicationContext());
        commonNavigator.setAdjustMode(true);
        CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter(viewPager, titles);
        commonNavigator.setAdapter(commonNavigatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        viewPager.setOffscreenPageLimit(titles.length);
    }

    private void initView() {
        magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
