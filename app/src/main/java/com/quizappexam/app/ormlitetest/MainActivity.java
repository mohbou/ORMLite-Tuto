package com.quizappexam.app.ormlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            doTestStuff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doTestStuff() throws SQLException {
        mDatabaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        RuntimeExceptionDao<Test,Integer> testDao = mDatabaseHelper.getTestRuntimeDao();
        Test test = new Test();
        test.setName("test 1");
        Test test2 = new Test();
        test2.setName("test 2");
        Test test3 = new Test();
        test3.setName("test 3");


        testDao.create(test);
        testDao.create(test2);
        testDao.create(test3);


        List<Test> tests =testDao.queryForAll();
        Log.d("demo",tests.toString());

        List<Test> testsQuery = testDao.queryForEq("id",1);
        Log.d("demos",testsQuery.toString());



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OpenHelperManager.releaseHelper();
    }
}
