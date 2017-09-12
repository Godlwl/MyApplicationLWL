package ecomoter.myapplication.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by lwl on 2017/6/17.
 * Describe:
 */

/**
 * 123->31231
 * 首尾分别添加最后一张和第一张banner。
 * 默认从第二张banner开始，也就是源数据的第一张。
 * 当滑到最后一张时，跳到第二张。
 * 当滑到第一张时，跳到倒数第二张。
 */

public class BannerAdapter extends PagerAdapter {
    /**
     * 当前页面
     */
    private int mCurrentPotion;

    /**
     * 数据
     */
    private ArrayList<String> mDatas;

    /**
     * 点击监听回调
     * @param datas
     */
    private OnClickL mOnClickL;
    public BannerAdapter(ArrayList<String> datas,OnClickL onClickL){
        mDatas=new ArrayList<>();
//        /**
//         * 添加最后一张到第一张
//         */
//        datas.add(0,datas.get(datas.size()-1));
//        /**
//         * 添加第一张到最后一张
//         */
//        datas.add(datas.get(1));
//        this.mDatas=datas;
        this.mOnClickL=onClickL;

    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView=new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(container.getContext()).load(mDatas.get(position)).into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickL.onClick(position);

            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 解决调用notify不能自动刷新的问题
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void notifyDataChanged(ArrayList<String> datas){
        /**
         * 添加最后一张到第一张
         */
        datas.add(0,datas.get(datas.size()-1));
        /**
         * 添加第一张到最后一张
         */
        datas.add(datas.get(1));
        this.mDatas=datas;
        notifyDataSetChanged();
    }

    interface OnClickL{
        void onClick(int potision);
    }
}
