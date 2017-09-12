package ecomoter.myapplication.HorizonSelect;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import ecomoter.myapplication.R;

/**
 * Created by lwl on 2017/6/21.
 * Describe:
 */

public class MyScrollView extends HorizontalScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout ll=new LinearLayout(context);
                ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        for (int i=0;i<20;i++){
            TextView textView=new TextView(context);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(0,0,10,0);
            textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            textView.setText(i+"00");
            ll.addView(textView);
        }
        addView(ll);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
