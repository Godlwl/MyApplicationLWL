package com.jl.framework.ui.widget.recyclerviewutils.common;

import android.content.Context;

import com.jl.framework.R;


/**
 * @author YanLu
 * @since 16/4/10
 */
public class DefaultLoadMoreFooterView extends BaseLoadMoreFooterView {


    public DefaultLoadMoreFooterView(Context context) {
        super(context);
    }

    @Override
    public int getLoadMoreLayoutResource() {
        return R.layout.default_load_more_view;
    }
}
