package com.project.ydy.fragmentpassvalue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.ydy.fragmentpassvalue.R;
import com.project.ydy.fragmentpassvalue.bean.Goods;

import java.util.List;

/**
 * **************************************************
 * 文件名称 : GoodsInspectionAdapter
 * 作    者 : Created by ydy
 * 创建时间 : 2018/7/7 13:54
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/7/7 1.00 初始版本
 * **************************************************
 */
public class GoodsInspectionAdapter extends BaseAdapter {

    private Context context;
    private List<Goods> data;
    private LayoutInflater inflater;

    public GoodsInspectionAdapter(Context context, List<Goods> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_goods_inspection, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Goods goods = data.get(position);
        String name = goods.getName();
        String specification = goods.getSpecification();
        String unit = goods.getUnit();

        holder.tvName.setText(name);
        holder.tvSpecification.setText(specification);
        holder.tvUnit.setText(unit);
        if (position % 2 == 0) {
            holder.llRoot.setBackgroundColor(Color.parseColor("#EFF9F0"));
        } else {
            holder.llRoot.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        holder.position = position;

        return convertView;
    }

    class ViewHolder implements View.OnClickListener {

        private int position;
        private LinearLayout llRoot;
        private TextView tvName;
        private TextView tvSpecification;
        private TextView tvUnit;
        private ImageView ivDelete;

        public ViewHolder(View view) {
            view.setTag(this);
            llRoot = (LinearLayout) view.findViewById(R.id.ll_root);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvSpecification = (TextView) view.findViewById(R.id.tv_specification);
            tvUnit = (TextView) view.findViewById(R.id.tv_unit);
            ivDelete = (ImageView) view.findViewById(R.id.iv_delete);
            ivDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            data.remove(position);
            notifyDataSetChanged();
        }
    }
}
