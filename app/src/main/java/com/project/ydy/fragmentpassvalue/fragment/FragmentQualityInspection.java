package com.project.ydy.fragmentpassvalue.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import com.project.ydy.fragmentpassvalue.MainActivity;
import com.project.ydy.fragmentpassvalue.R;
import com.project.ydy.fragmentpassvalue.adapter.GoodsInspectionAdapter;
import com.project.ydy.fragmentpassvalue.base.BaseFragment;
import com.project.ydy.fragmentpassvalue.bean.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************
 * 文件名称 : FragmentQualityInspection
 * 作    者 : Created by ydy
 * 创建时间 : 2018/7/7 11:39
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/7/7 1.00 初始版本
 * **************************************************
 */
public class FragmentQualityInspection extends BaseFragment {

    private static final String TAG = "TAG";

    private SearchView mSv;
    private ListView mLv;
    private List<Goods> dataList = new ArrayList<>();
    private GoodsInspectionAdapter adapter;

    public static FragmentQualityInspection newInstance() {
        return new FragmentQualityInspection();
    }

    @Override
    public void fragmentVisible() {
        Log.i(TAG, "质检fragmentVisible: ");
        initAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "质检onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_quality_inspection, container, false);
        initView(view);
        return view;
    }

    private void initAdapter() {
        dataList = ((MainActivity) getActivity()).getInspectionData();
        Log.i(TAG, "质检initData: " + dataList.toString());
        adapter = new GoodsInspectionAdapter(getActivity(), dataList);
        mLv.setAdapter(adapter);
    }

    private void initView(View view) {
        mSv = (SearchView) view.findViewById(R.id.sv);
        mLv = (ListView) view.findViewById(R.id.lv_inspection);
    }

    public List<Goods> getInspectionList() {
        return dataList;
    }
}
