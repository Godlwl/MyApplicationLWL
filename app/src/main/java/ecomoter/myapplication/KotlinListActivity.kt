package ecomoter.myapplication

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_kotlin_list.*
import org.jetbrains.anko.toast

class KotlinListActivity : AppCompatActivity() {
        lateinit var mAdapter:KotlinAdapter
        var data= ArrayList<String>()
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_list)
        loadData()
        mAdapter= KotlinAdapter(data,{pot:Int->toast("$pot")})
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=mAdapter
//        mAdapter.setData(data)

//        mAdapter.setListen(object : KotlinAdapter.OnClickListenBack{
//            override fun onClick() {
//
//            }
//        })
        mAdapter.notifyDataSetChanged()
    }

    fun loadData(){
        data.add("Kotlin1")
        data.add("Kotlin2")
        data.add("Kotlin3")
        data.add("Kotlin4")
        data.add("Kotlin5")
        data.add("Kotlin6")
        data.add("Kotlin7")
        data.add("Kotlin8")
        data.add("Kotlin9")
    }
}
