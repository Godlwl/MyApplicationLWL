package ecomoter.myapplication.md;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ecomoter.myapplication.R;
import ecomoter.myapplication.SecondActivity;

public class MdDemoActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private MyAdapter mAdapter;
    private ArrayList<String> mDatas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll2);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
//        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色

        mRv= (RecyclerView) findViewById(R.id.rv_md);
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mDatas.add("32434");
        mAdapter=new MyAdapter(mDatas);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);

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
