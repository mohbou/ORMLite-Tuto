package com.quizappexam.app.ormlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "test3.db";

    private static final int DATABASE_VERSION = 1;

    private Dao<Test,Integer> testDao =  null;
    private RuntimeExceptionDao<Test,Integer> testRuntimeDao=null;

    private Dao<TestCollection,Integer> testCollectionDao =  null;
    private RuntimeExceptionDao<TestCollection,Integer> testCollectionRuntimeDao=null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Test.class);
            TableUtils.createTableIfNotExists(connectionSource, TestCollection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Test.class,true);
            TableUtils.dropTable(connectionSource,TestCollection.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Test,Integer> getTestDao() throws SQLException {
        if(testDao==null) {
            testDao = getDao(Test.class);
        }
        return testDao;
    }
    public RuntimeExceptionDao<Test,Integer> getTestRuntimeDao() throws SQLException {
        if(testRuntimeDao==null) {
            testRuntimeDao = getRuntimeExceptionDao(Test.class);
        }
        return testRuntimeDao;
    }


    public Dao<TestCollection,Integer> getTestCollectionDao() throws SQLException {
        if(testCollectionDao==null) {
            testCollectionDao = getDao(TestCollection.class);
        }
        return testCollectionDao;
    }

    public RuntimeExceptionDao<TestCollection,Integer> getTestRuntimeCollectionDao() throws SQLException {
        if(testCollectionRuntimeDao==null) {
            Log.d("test","it gets here");
            testCollectionRuntimeDao = getRuntimeExceptionDao(TestCollection.class);
        }
        return testCollectionRuntimeDao;
    }

}
