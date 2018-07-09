package com.project.ydy.fragmentpassvalue.base;

/**
 * @author ydy
 * @date 2018/1/15 14:33
 */
public class BaseFragment extends LazyLoadFragment {

    /**
     * fragment第一次可见
     */
    public void fragmentFirstVisible() {

    }

    /**
     * fragment可见
     */
    public void fragmentVisible() {

    }

    /**
     * fragment不可见
     */
    public void fragmentInvisible() {

    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        fragmentFirstVisible();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            fragmentVisible();
        } else {
            fragmentInvisible();
        }
    }

}
