package ecomoter.myapplication.wxdatabase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ecomoter.myapplication.R;

/**
 * Created by lwl on 2017/8/30.
 * Describe:
 */

public class WxContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ContactEntity> mDatas=new ArrayList();

    public WxContactAdapter(List<ContactEntity> datas){
        this.mDatas=datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_contact_item,parent,false);
        ContactViewHolder holder=new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ContactViewHolder)holder).mTvUserName.setText(mDatas.get(position).getUserName());
        ((ContactViewHolder)holder).mTvNickName.setText(mDatas.get(position).getNickName());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    class ContactViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvUserName;
        private TextView mTvNickName;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mTvUserName= (TextView) itemView.findViewById(R.id.tv_username);
            mTvNickName=(TextView) itemView.findViewById(R.id.tv_nickname);
        }
    }
}
