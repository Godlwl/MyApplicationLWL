package ecomoter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

public class StickyRecyCyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private List<StickyModel> mDatas=new ArrayList<>();
    private StickyAdapter mAdapter;
    private StickyRecyclerHeadersDecoration headersDecor;
    private View mHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_recy_cycler_view);
        mHead=findViewById(R.id.head);
        mRv= (RecyclerView) findViewById(R.id.rv_sticky);
        mAdapter=new StickyAdapter(mDatas);
        mRv.setAdapter(mAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        // 为RecyclerView添加Decorator装饰器
        // 为RecyclerView中的Item添加Header头部（自动获取头部ID，将相邻的ID相同的聚合到一起形成一个Header）
//        headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
//        mRv.addItemDecoration(headersDecor);

        loadData();
        mAdapter.notifyDataSetChanged();
//        // 为RecyclerView添加Item之间的分隔线
//        rv.addItemDecoration(new DividerDecoration(this));

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager= (LinearLayoutManager) mRv.getLayoutManager();
                int type=mAdapter.getItemViewType(manager.findFirstCompletelyVisibleItemPosition());
                int firstItem=manager.findFirstVisibleItemPosition();
//                if (mDatas.get(firstItem).getType()==0){
//                    mHead.setVisibility(View.VISIBLE);
//                }else {
//                    mHead.setVisibility(View.GONE);
//                }
                /**
                 * 显示与隐藏头部
                 */
                View stickyInfoView = recyclerView.findChildViewUnder(mHead.getMeasuredWidth() / 2, 5);
//                int viewType=recyclerView.getChildViewHolder(stickyInfoView).getItemViewType();
//                if (viewType==0&&stickyInfoView.getTop()<=0){
//                    mHead.setVisibility(View.VISIBLE);
//                    mHead.setTranslationY(0);
//                }
                View secondView=recyclerView.findChildViewUnder(mHead.getMeasuredWidth()/2,mHead.getMeasuredHeight());
//                int secondViewType=recyclerView.getChildViewHolder(secondView).getItemViewType();
//                if (secondViewType==0){
//                    mHead.setTranslationY(secondView.getTop()-mHead.getMeasuredHeight());
//                }

                /**
                 * 固定指定item到头部
                 */
                int topItemPosition=recyclerView.getChildViewHolder(stickyInfoView).getLayoutPosition();
                Log.d("type1type1111","顶部item的postion"+topItemPosition+"gettop值："+stickyInfoView.getTop());


                View headView=recyclerView.getChildAt(firstItem);
                View item=recyclerView.getChildAt(3);
                mRv.getChildAt(0).getId();
                Log.d("type1type1111",stickyInfoView.getId()+"");
                Log.d("type1type111",mRv.getChildAt(0).getId()+"");
                Log.d("type1type111","第一个可见item的下标："+firstItem);
//                Log.d("type1type111","第一个可见item top值："+headView.getTop());
                Log.d("type1type111","第4item top值："+stickyInfoView.getTop());
                Log.d("type1type111","第4个item："+item.getContentDescription());
                Log.d("测试测试","ddddd");
//                Toast.makeText(StickyRecyCyclerViewActivity.this, mAdapter.getItemViewType(manager.findFirstCompletelyVisibleItemPosition()),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loadData() {
        for (int i=0;i<30;i++){
            StickyModel model=new StickyModel();
            model.setType(i%5==0?0:1);
            model.setMsg("hello"+i);
            mDatas.add(model);
        }

    }
}
