package ecomoter.myapplication;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private MyAdapter mAdapter;
    private List<String> mData = new ArrayList<>();
    private RecyclerView.LayoutManager manager;
    private PtrFrameLayout mRefresh;
    private Handler mHandler;
    private View mLoadingMore;

    private ImageView mIvLoading;
    /**
     * 动画
     */
    private Animation rotateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Fresco.initialize(this);
        mLoadingMore=findViewById(R.id.ll_loadingmore);
        mIvLoading= (ImageView) findViewById(R.id.iv_loading);
        mRefresh = (PtrFrameLayout) findViewById(R.id.pfl_refresh);
        mRv = (RecyclerView) findViewById(R.id.rv_refresh);
        manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mAdapter = new MyAdapter((ArrayList<String>) mData);
        mRv.setAdapter(mAdapter);
        for (int i = 0; i < 20; i++) {
            mData.add("数据" + i);
        }
        mAdapter.notifyDataSetChanged();

//        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
//        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
        RefreshHead header = new RefreshHead(this);
        mRefresh.setHeaderView(header);
        mRefresh.addPtrUIHandler(header);
        mHandler = new Handler();

        mRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh.refreshComplete();
                    }
                }, 2000);
            }
        });

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!mRv.canScrollVertically(1)) {
                    Toast.makeText(SecondActivity.this, "已经到滚到底部", Toast.LENGTH_SHORT).show();
                    if (!mLoadingMore.isShown()) {
                        /**
                         * 防止加载中再次滚到底部造成多次加载
                         */
                        mLoadingMore.setVisibility(View.VISIBLE);
                        rotateAnimation.startNow();
                        loadMore();
                    }

                } else if (!mRv.canScrollVertically(-1)) {
                    Toast.makeText(SecondActivity.this, "已经到滚到顶部", Toast.LENGTH_SHORT).show();

                }
                String s=String.valueOf(mRv.findChildViewUnder(20,5).getContentDescription());
                Toast.makeText(SecondActivity.this, "当前第一个可见view"+s, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                int firstView=   ((LinearLayoutManager)manager).findFirstVisibleItemPosition();
//                if (firstView%3==0){
//                    mRv.getChildAt(firstView).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                }
//                Toast.makeText(SecondActivity.this, "当前第一个可见view"+firstView, Toast.LENGTH_SHORT).show();
            }
        });

        setAnim();
    }

    void loadMore(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++){
                    mData.add("新增数据"+i);
                }
                mLoadingMore.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
                rotateAnimation.cancel();
            }
        },2000);


    }


    private void setAnim() {
        rotateAnimation=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        mIvLoading.setAnimation(rotateAnimation);

    }


    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<String> datas;

        public MyAdapter(ArrayList<String> datas) {
            this.datas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder holder1 = (MyViewHolder) holder;
            holder1.mTv.setText(datas.get(position));


        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView mTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }
}
