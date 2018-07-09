package com.project.ydy.fragmentpassvalue.bean;

/**
 * **************************************************
 * 文件名称 : Goods
 * 作    者 : Created by ydy
 * 创建时间 : 2018/7/7 13:48
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/7/7 1.00 初始版本
 * **************************************************
 */
public class Goods {

    /**名称*/
    private String name;
    /**规格*/
    private String specification;
    /**单位*/
    private String unit;
    /**是否被选择*/
    private boolean isSelected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
