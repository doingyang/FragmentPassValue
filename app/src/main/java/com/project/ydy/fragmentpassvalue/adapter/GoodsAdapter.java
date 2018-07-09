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
 * 文件名称 : GoodsAdapter
 * 作    者 : Created by ydy
 * 创建时间 : 2018/7/7 13:54
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/7/7 1.00 初始版本
 * **************************************************
 */
public class GoodsAdapter extends BaseAdapter {

    private Context context;
    private List<Goods> data;
    private LayoutInflater inflater;

    public GoodsAdapter(Context context, List<Goods> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    public void setSelectedData(List<Goods> selectedData) {
        for (Goods goods : data) {
            inner:
            if (null != selectedData && selectedData.size() > 0) {
                for (int i = 0; i < selectedData.size(); i++) {
                    if (goods.equals(selectedData.get(i))) {
                        goods.setSelected(true);
                        break inner;
                    } else {
                        goods.setSelected(false);
                    }
                }
            } else {
                goods.setSelected(false);
            }
        }
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
            convertView = inflater.inflate(R.layout.item_goods, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Goods goods = data.get(position);
        String name = goods.getName();
        String specification = goods.getSpecification();
        String unit = goods.getUnit();
        boolean selected = goods.isSelected();

        if (position % 2 == 0) {
            holder.llRoot.setBackgroundColor(Color.parseColor("#EFF9F0"));
        } else {
            holder.llRoot.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        holder.tvName.setText(name);
        holder.tvSpecification.setText(specification);
        holder.tvUnit.setText(unit);
        if (selected) {
            holder.ivSelect.setImageResource(R.drawable.icon_duoxuan_s);
        } else {
            holder.ivSelect.setImageResource(R.drawable.icon_duoxuan);
        }

        return convertView;
    }

    public class ViewHolder {

        private LinearLayout llRoot;
        private TextView tvName;
        private TextView tvSpecification;
        private TextView tvUnit;
        private ImageView ivSelect;

        public ViewHolder(View view) {
            view.setTag(this);
            llRoot = (LinearLayout) view.findViewById(R.id.ll_root);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvSpecification = (TextView) view.findViewById(R.id.tv_specification);
            tvUnit = (TextView) view.findViewById(R.id.tv_unit);
            ivSelect = (ImageView) view.findViewById(R.id.iv_select);
        }
    }
}
