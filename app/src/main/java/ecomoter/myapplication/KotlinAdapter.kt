package ecomoter.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.kotlin_list_item.view.*
import org.jetbrains.anko.onClick

/**
 * Created by lwl on 2017/5/20.
 * Describe:
 */
class KotlinAdapter(var data:List<String> = ArrayList(),var listen:(Int)->Unit): RecyclerView.Adapter<KotlinAdapter.ViewHolder>() {

    /**
     * 次构造函数
     */
    constructor(data:List<String> = ArrayList(),listen:(Int)->Unit,em:String):this(data,listen){

    }
    var listener:OnClickListenBack? =null

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.itemView.tv2.text=data.get(position)
        holder.itemView.onClick {
           listen(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kotlin_list_item,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

//    fun setListen(listen1:OnClickListenBack){
//        listener=listen1
//    }

    fun setData(data1:ArrayList<String>){
        data=data1;
    }

    interface OnClickListenBack{
        fun onClick()
    }

}