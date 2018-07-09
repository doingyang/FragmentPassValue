package com.project.ydy.fragmentpassvalue.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.project.ydy.fragmentpassvalue.MainActivity;
import com.project.ydy.fragmentpassvalue.R;
import com.project.ydy.fragmentpassvalue.adapter.GoodsAdapter;
import com.project.ydy.fragmentpassvalue.base.BaseFragment;
import com.project.ydy.fragmentpassvalue.bean.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************
 * 文件名称 : FragmentGoods
 * 作    者 : Created by ydy
 * 创建时间 : 2018/7/7 11:39
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/7/7 1.00 初始版本
 * **************************************************
 */
public class FragmentGoods extends BaseFragment {

    private static final String TAG = "TAG";

    private ListView mLv;
    private List<Goods> dataList = new ArrayList<>();
    private GoodsAdapter adapter;

    private List<Goods> selectList = new ArrayList<>();

    public static FragmentGoods newInstance() {
        return new FragmentGoods();
    }

    @Override
    public void fragmentVisible() {
        Log.i(TAG, "待检fragmentVisible: ");
        initAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "待检onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initAdapter() {
        selectList = ((MainActivity) getActivity()).getSelectedData();
        Log.i(TAG, "待检initAdapter: " + selectList.toString());
        adapter = new GoodsAdapter(getActivity(), dataList);
        adapter.setSelectedData(selectList);
        mLv.setAdapter(adapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsAdapter.ViewHolder holder = (GoodsAdapter.ViewHolder) view.getTag();
                Goods goods = dataList.get(position);
                if (goods.isSelected()) {
                    goods.setSelected(false);
                } else {
                    goods.setSelected(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        Goods goods = new Goods();
        goods.setName("食用调和油");
        goods.setSpecification("1*2");
        goods.setUnit("个");
        dataList.add(goods);

        Goods goods1 = new Goods();
        goods1.setName("筷子");
        goods1.setSpecification("90双/包");
        goods1.setUnit("包");
        dataList.add(goods1);

        Goods goods2 = new Goods();
        goods2.setName("大米");
        goods2.setSpecification("25Kg/袋");
        goods2.setUnit("袋");
        dataList.add(goods2);

        Goods goods3 = new Goods();
        goods3.setName("啤酒");
        goods3.setSpecification("500ml/瓶");
        goods3.setUnit("瓶");
        dataList.add(goods3);

        Goods goods4 = new Goods();
        goods4.setName("可乐");
        goods4.setSpecification("330ml/听");
        goods4.setUnit("听");
        dataList.add(goods4);

        Goods goods5 = new Goods();
        goods5.setName("烤鸭");
        goods5.setSpecification("68元/kg");
        goods5.setUnit("只");
        dataList.add(goods5);

        Goods goods6 = new Goods();
        goods6.setName("纸碗");
        goods6.setSpecification("100个/份");
        goods6.setUnit("份");
        dataList.add(goods6);

        Goods goods7 = new Goods();
        goods7.setName("法克");
        goods7.setSpecification("呵呵哒");
        goods7.setUnit("shit");
        dataList.add(goods7);
    }

    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv_goods);
    }

    public List<Goods> getSelectList() {
        selectList.clear();
        for (Goods goods: dataList) {
            if (goods.isSelected()) {
                selectList.add(goods);
            }
        }
        return selectList;
    }
}
