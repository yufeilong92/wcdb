package com.example.myapplication;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnOne;
    private Button mBtnTwo;
    private SQLiteDatabase writableDatabase;
    private Button mBtnThre;
    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initdB();


    }

    private void initdB() {
        OpeN opeN = new OpeN(this);
        writableDatabase = opeN.getWritableDatabase();


    }

    private void add(SQLiteDatabase writableDatabase) {
        for (int i = 0; i < 10; i++) {
            ContentValues values = new ContentValues();
            values.put("id", i);
            values.put("name", "名字" + i);
            values.put("age", "年龄" + i);
            writableDatabase.insert(OpeN.TABLE_NAME, null, values);
        }
    }

    private void initView() {
        mBtnOne = (Button) findViewById(R.id.btn_one);
        mBtnTwo = (Button) findViewById(R.id.btn_two);

        mBtnOne.setOnClickListener(this);
        mBtnTwo.setOnClickListener(this);
        mBtnThre = (Button) findViewById(R.id.btn_thre);
        mBtnThre.setOnClickListener(this);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mTvShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                add(writableDatabase);
                break;
            case R.id.btn_two:
                updata();
                break;
            case R.id.btn_thre:
                ArrayList<UserInfom> infom = getInfom();
                if (infom == null || infom.isEmpty()) {
                    mTvShow.setText("暂无数据");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < infom.size(); i++) {
                    UserInfom userInfom = infom.get(i);
                    buffer.append("id=" + userInfom.getId());
                    buffer.append("age=" + userInfom.getAge());
                    buffer.append("name=" + userInfom.getName());
                    buffer.append("====");
                }
                mTvShow.setText(buffer.toString());
                break;
        }
    }

    private void updata() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("age", "222222222");
        writableDatabase.update(OpeN.TABLE_NAME, contentValues, "id=?",
                new String[]{"0"});
    }

    private ArrayList<UserInfom> getInfom() {
        Cursor query = writableDatabase.query(OpeN.TABLE_NAME, null, null, null, null, null, null);
        DbQueryUtil queryUtil = DbQueryUtil.get_Instance();
        queryUtil.initCursor(query);
        ArrayList<UserInfom> list = new ArrayList<>();
        while (query.moveToNext()) {
            UserInfom infom = getUserInfom(queryUtil);
            list.add(infom);
        }
        query.close();
        return list;


    }

    private UserInfom getUserInfom(DbQueryUtil dbQueryUtil) {
        UserInfom vo = new UserInfom();
        int id = dbQueryUtil.queryInt("id");
        String name = dbQueryUtil.queryString("name");
        String age = dbQueryUtil.queryString("age");
        vo.setId(id);
        vo.setName(name);
        vo.setAge(age);
        return vo;

    }

    private class UserInfom {
        int id;
        String name;
        String age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
