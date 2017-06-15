package ecomoter.myapplication.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import ecomoter.myapplication.MyApplication;
import ecomoter.myapplication.greendao.DaoMaster;
import ecomoter.myapplication.greendao.TeacherDao;

/**
 * Created by lwl on 2017/5/31.
 * Describe:
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
        // 第二个参数为要升级的Dao文件.
        MigrationHelper.getInstance().migrate(db, TeacherDao.class);
        MyApplication.getContext().getDaoMaster().createAllTables(db,true);
//        String sql="ALTER TABLE USER ADD COLUMN test INTEGER";
//        db.execSQL(sql);

    }


}
