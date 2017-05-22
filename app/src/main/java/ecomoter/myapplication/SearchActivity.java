package ecomoter.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Method;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private String[] mStr={"abc","bbbbb","ccccc","ddddd","abc","abcd","eeeee","abcdef","abc","fffff","abcde","abcdef"};

    private Toolbar mTb;
    private SearchView mSv;

    private SearchView.SearchAutoComplete mSearchAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mRv= (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
//        SimpleCursorAdapter adapter=new SimpleCursorAdapter();
        Cursor cursor=query("a");
        while (cursor.moveToNext()){
            Log.d("fromCursor",cursor.getString(cursor.getColumnIndex("name")));
        }

        mTb= (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(mTb);

        //Toolbar返回按钮的点击事件
        mTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchAutoComplete.isShown()) {
                    try {
                        //如果搜索框中有文字，则会先清空文字，但网易云音乐是在点击返回键时直接关闭搜索框
                        mSearchAutoComplete.setText("");
                        Method method = mSv.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSv);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        MenuItem menuItem=menu.findItem(R.id.menu_search);
        mSv= (SearchView) MenuItemCompat.getActionView(menuItem);
        //通过id得到搜索框控件
        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSv.findViewById(R.id.search_src_text);

        //设置输入框提示文字样式
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.background_light));
        mSearchAutoComplete.setTextSize(14);

        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        mSv.onActionViewExpanded();
        mSv.setIconified(true);

        mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Cursor cursor = TextUtils.isEmpty(s) ? null : query(s);
                // 不要频繁创建适配器，如果适配器已经存在，则只需要更新适配器中的cursor对象即可。
                if (mSv.getSuggestionsAdapter() == null) {
                    mSv.setSuggestionsAdapter(new SimpleCursorAdapter(SearchActivity.this, R.layout.search_item, cursor, new String[]{"name"}, new int[]{R.id.tv_search}));
                } else {
                    mSv.getSuggestionsAdapter().changeCursor(cursor);
                }

                return false;
            }
        });
        //设置触发查询的最少字符数（默认2个字符才会触发查询）
        mSearchAutoComplete.setThreshold(1);
        return super.onCreateOptionsMenu(menu);
    }
    // 让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private Cursor query(String s){
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(getFilesDir()+"test.db",null);
        Cursor cursor=null;
        try{
            String querySql = "select * from tb_music where name like '%" + s + "%'";
            cursor = db.rawQuery(querySql, null);
        }catch (Exception e){
            String createSql = "create table tb_music (_id integer primary key autoincrement,name varchar(100))";
            db.execSQL(createSql);

            String insertSql = "insert into tb_music values (null,?)";
            for (int i = 0; i < mStr.length; i++) {
                db.execSQL(insertSql, new String[]{mStr[i]});
            }

            String querySql = "select * from tb_music where name like '%" + s + "%'";
            cursor = db.rawQuery(querySql, null);
        }
        return cursor;
    }
}
