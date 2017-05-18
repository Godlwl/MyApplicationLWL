package ecomoter.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by lwl on 2017/4/15.
 * Describe:
 */

public class StickyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<StickyModel> datas = new ArrayList<>();

    public StickyAdapter(List<StickyModel> datas) {
        this.datas = datas;
    }

//    @Override
//    public long getHeaderId(int position) {
//        return datas.get(position).getMsg().charAt(5);
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticky_head_layout,parent,false);
//        HeadHolder holder=new HeadHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ((HeadHolder)holder).tvHead.setText("我是头部");
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType==0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticky_head_layout,parent,false);
             holder=new HeadHolder(view);
        }else if (viewType==1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
             holder=new ItemHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadHolder){
            ((HeadHolder)holder).tvHead.setText("我是头部");
        }else {
            ((ItemHolder)holder).tvItem.setText(datas.get(position).getMsg());
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;

        public ItemHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        private TextView tvHead;

        public HeadHolder(View itemView) {
            super(itemView);
            tvHead = (TextView) itemView.findViewById(R.id.tv_head);
        }
    }
}
