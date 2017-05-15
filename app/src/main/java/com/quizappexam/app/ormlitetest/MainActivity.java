package com.quizappexam.app.ormlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.util.Iterator;
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
        RuntimeExceptionDao<TestCollection,Integer> testCollections = mDatabaseHelper.getTestRuntimeCollectionDao();
        Test test = new Test();
        test.setName("test 1");



        testDao.create(test);

        for (int i=0;i<20;i++) {
            TestCollection testCollection1 = new TestCollection();
            testCollection1.setName("collection "+i);
            testCollection1.setTest(test);
            testCollections.create(testCollection1);
        }

            Test testResult =testDao.queryForId(test.getId());

        ForeignCollection<TestCollection> collections = testResult.getTestCollections();

        Iterator<TestCollection> iterator = collections.iterator();

        while (iterator.hasNext()) {
            TestCollection next = iterator.next();
            Log.d("collection",next.getName());

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Closing","Closing connection......");
        OpenHelperManager.releaseHelper();

    }


}
