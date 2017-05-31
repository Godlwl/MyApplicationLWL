package ecomoter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import ecomoter.myapplication.model.DaoMaster;
import ecomoter.myapplication.model.DaoSession;
import ecomoter.myapplication.model.Teacher;
import ecomoter.myapplication.model.User;
import ecomoter.myapplication.model.UserDao;
import ecomoter.myapplication.mvpbase.Student;

public class GreenDaoActivity extends AppCompatActivity {
    private Button mBtnInsert;
    private Button mBtnQuery;
    private DaoSession mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        mSession=MyApplication.getContext().getSession();
        mBtnInsert= (Button) findViewById(R.id.btn_insert);
        mBtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                User user=new User();
//                user.setName("ls");
//                user.setAge(12);
//                user.setCarType("奔驰");
//                user.setCarCount(5);
//                mSession.getUserDao().insert(user);

                Teacher teacher=new Teacher();
                teacher.setName("张老师");
                teacher.setCourse("语文");
//                teacher.setSex("男");
                mSession.getTeacherDao().insert(teacher);

                Student student=new Student();
                student.setName("小明");
                student.setAge(15);
                student.setNum("11212");
                mSession.getStudentDao().insert(student);
            }
        });

        mBtnQuery= (Button) findViewById(R.id.btn_query);
        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> userList = mSession.getUserDao().queryBuilder()
                        .where(UserDao.Properties.Id.notEq(999))
                        .orderAsc(UserDao.Properties.Id)
                        .limit(5)
                        .build().list();

                Toast.makeText(GreenDaoActivity.this,userList.get(0).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
