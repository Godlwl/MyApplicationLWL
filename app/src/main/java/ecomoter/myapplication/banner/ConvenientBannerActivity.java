package ecomoter.myapplication.banner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.youth.banner.transformer.ABaseTransformer;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.CubeInTransformer;
import com.youth.banner.transformer.CubeOutTransformer;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.FlipHorizontalTransformer;
import com.youth.banner.transformer.FlipVerticalTransformer;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
import com.youth.banner.transformer.RotateDownTransformer;
import com.youth.banner.transformer.RotateUpTransformer;
import com.youth.banner.transformer.StackTransformer;
import com.youth.banner.transformer.ZoomInTransformer;
import com.youth.banner.transformer.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

import ecomoter.myapplication.R;

public class ConvenientBannerActivity extends AppCompatActivity {
    private ConvenientBanner mCb;
    private List<String> mDatas=new ArrayList<>();
    private List<String> mTransFormrs=new ArrayList<>();
    private RecyclerView mRv;
    private RecyclerView.Adapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenient_banner);
        initData();
        mCb=(ConvenientBanner)findViewById(R.id.cb);
//        setViewLayout(mCb,getResources().getDisplayMetrics().widthPixels,getResources().getDisplayMetrics().widthPixels);
        mRv= (RecyclerView) findViewById(R.id.rv_transform);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdpter=new TransFormerAdapter(mTransFormrs, new TransFormerAdapter.OnCLickListener1() {
            @Override
            public void onClick(int position) {
                String transforemerName = mTransFormrs.get(position);
                try {
                    Class cls = Class.forName("com.youth.banner.transformer." + transforemerName);
                    ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
                    mCb.getViewPager().setPageTransformer(true,transforemer);

                    //部分3D特效需要调整滑动速度
                    if(transforemerName.equals("StackTransformer")){
                        mCb.setScrollDuration(1200);
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        mRv.setAdapter(mAdpter);
        mCb.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolder();
            }
        },mDatas).setPageIndicator(new int[]{R.mipmap.index_page_control_u,
                R.mipmap.index_page_control_s}).
                setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).
                setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        mCb.setPageTransformer(new AccordionTransformer());
        mCb.startTurning(3000);

    }

    private void initData() {
        mTransFormrs.add(DefaultTransformer.class.getSimpleName());
        mTransFormrs.add(AccordionTransformer.class.getSimpleName());
        mTransFormrs.add(BackgroundToForegroundTransformer.class.getSimpleName());
        mTransFormrs.add(CubeInTransformer.class.getSimpleName());
        mTransFormrs.add(CubeOutTransformer.class.getSimpleName());
        mTransFormrs.add(DepthPageTransformer.class.getSimpleName());
        mTransFormrs.add(FlipHorizontalTransformer.class.getSimpleName());
        mTransFormrs.add(FlipVerticalTransformer.class.getSimpleName());
        mTransFormrs.add(ForegroundToBackgroundTransformer.class.getSimpleName());
        mTransFormrs.add(RotateDownTransformer.class.getSimpleName());
        mTransFormrs.add(RotateUpTransformer.class.getSimpleName());
        mTransFormrs.add(StackTransformer.class.getSimpleName());
        mTransFormrs.add(ZoomInTransformer.class.getSimpleName());
        mTransFormrs.add(ZoomOutTranformer.class.getSimpleName());

        mDatas.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/06/01/pic_1496319418956.jpg");
        mDatas.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/05/26/pic_1495779591458.jpg");
        mDatas.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/05/19/pic_1495179248615.jpg");
        mDatas.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/05/09/pic_1494310505350.jpg");
    }

    class ImageHolder implements Holder<String>{
        private ImageView mIv;



        @Override
        public View createView(Context context) {
            mIv=new ImageView(context);
            mIv.setScaleType(ImageView.ScaleType.FIT_XY);

//            View view= LayoutInflater.from(context).inflate(R.layout.lvitem_head_recommend,null);
//            mIv= (ImageView) view.findViewById(R.id.car_img_iv);
            return mIv;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(context).load(data).error(R.mipmap.default_big_img).into(mIv);

        }
    }

    //设置view的宽高
    private void setViewLayout(View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (height != 0) layoutParams.height = height;
        if (width != 0) layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }

}
