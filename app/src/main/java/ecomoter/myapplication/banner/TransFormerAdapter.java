package ecomoter.myapplication.banner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import ecomoter.myapplication.R;

/**
 * Created by lwl on 2017/6/27.
 * Describe:
 */

public class TransFormerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mDatas=new ArrayList<String>();
    private OnCLickListener1 mOnL;
    public TransFormerAdapter(List datas,OnCLickListener1 onL){
        this.mDatas=datas;
        this.mOnL=onL;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transform_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        holder1.mTv.setText(mDatas.get(position));
        holder1.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnL.onClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTv= (TextView) itemView.findViewById(R.id.tv_trans);
        }
    }

    interface OnCLickListener1{
        void onClick(int postion);
    }


}
