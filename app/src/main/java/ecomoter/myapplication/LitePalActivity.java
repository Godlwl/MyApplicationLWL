package ecomoter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import ecomoter.myapplication.model.Student;

public class LitePalActivity extends AppCompatActivity {
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
        mBtnSave= (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student();
                student.setName("张三");
                student.setNum("12222");
                student.setAge(1);
                if (student.save()){
                    Toast.makeText(LitePalActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LitePalActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }


//                List<Student> all = DataSupport.findAll(Student.class);

            }
        });
    }
}
