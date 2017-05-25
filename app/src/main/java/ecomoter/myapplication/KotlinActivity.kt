package ecomoter.myapplication

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.UiThread
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import ecomoter.myapplication.model.JsonModel
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlin.properties.Delegates

class KotlinActivity : AppCompatActivity() {
    var mTv: TextView? = null
    var mBtn: Button? = null

    var map= HashMap<String,String>()

    var handler:Handler by Delegates.notNull<Handler>();

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
//            toast("哈哈哈哈")

            var m:Message=Message.obtain();
            m.arg1=1;
            m.arg2=2;
            handler.sendMessage(m);

            async {
                var str=URL("http://sz.seasaw.cn:8085/carManager/carManager/personal!myBasic.action?user_type=1&user_id=13560788876&phone=13560788876").readText()
                var model=Gson().fromJson(str,JsonModel::class.java)
                uiThread {
                    Log.d("jsonjson",str)
                    toast(model.name)
                }
            }
    }
        handler=object: Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                var a= msg?.arg1;
//                toast("${msg?.arg1}")
            }
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
