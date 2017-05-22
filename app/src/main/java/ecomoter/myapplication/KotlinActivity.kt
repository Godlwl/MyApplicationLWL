package ecomoter.myapplication

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class KotlinActivity : AppCompatActivity() {
    var mTv: TextView? = null
    var mBtn: Button? = null

    var map= HashMap<String,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        mTv = findViewById(R.id.tv) as TextView?
        mBtn = findViewById(R.id.btn_show) as Button
        mTv!!.setText(test())
        map.put("zs","100")
        map.put("ls","99")
        map.put("ww","15")
        map.put("lw","120")

        mBtn?.setOnClickListener {

            for ((k, v) in map) {
                Log.d("result","$k -> $v")
            }
            toast("哈哈哈哈")
    }



}


    fun test(): String {
        for (i in 1..2){

        }
        return "hello world"
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

//    fun niceToast(message: String,
//                  tag: String = javaClass<KotlinActivity>().getSimpleName (),
//                  length: Int = Toast.LENGTH_SHORT) {
//        Toast.makeText(this, "[$className] $message", length).show()
//    }

}
