package com.motor.app;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ViewUtils;
import android.text.format.Time;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greendao.manager.DaoMaster;
import com.greendao.manager.DaoSession;
import com.greendao.manager.MotorData;
import com.greendao.manager.MotorTxtToDb;
import com.motor.administrator.DATAbase.greendao.MotorBean;
import com.motor.bean.MotorrBean;
import com.nrs.utils.tools.CrashHandler;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.tencent.bugly.Bugly;
import com.xzkydz.bean.ComBean;
import com.xzkydz.function.app.KyApp;
import com.xzkydz.function.style.AppStyle;
import com.xzkydz.function.utils.SharedPreferencesUtils;

public class MyApp extends KyApp {
    private static MyApp mInstance;
    private static DaoSession daoSession;
    private static TaskEntity taskEntity = new TaskEntity(); //测试任务
    private static Boolean isSetMotor1;
    public static ComBean comBeanUI;//电压电流实体类
    public static ComBean comBeanHAM;//谐波实体类
    public static ComBean comBeanMotor;//电机实体类
    public static MotorrBean motorBean;//电机实体类

    public static ComBean comBeanUI_Sensor;//电压电流实体类
    public static ComBean comBeanHAM_Sensor;//谐波实体类
    public static ComBean comBeanMotor_Sensor;//电机实体类
    public static MotorrBean motorBean_Sensor;//电机实体类

    public static boolean isConnected;//传感器是否连接
    public static boolean isConnected_Sensor;//传感器是否连接

    public static boolean isFuYongCanShu;//是否是复用参数，在ParamSetActivity中开始测试时判断

    public static String getSnapName() {
        return snapName;
    }

    public static void setSnapName() {
        MyApp.snapName = getSysTime();
    }

    static String getSysTime() {
// shijain
        String datesString;
        String monthString;
        String houString;
        String minString;
        String secondString;
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month + 1;
        int date = t.monthDay;
        int hour = t.hour; // 0-23
        int minute = t.minute;
        int second = t.second;
        if (date < 10) {
            datesString = "0" + date;
        } else {
            datesString = date + "";
        }
        if (month < 10) {
            monthString = "0" + month;
        } else {
            monthString = month + "";
        }
        if (hour < 10) {
            houString = "0" + hour;
        } else {
            houString = hour + "";
        }
        if (minute < 10) {
            minString = "0" + minute;
        } else {
            minString = minute + "";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = second + "";
        }
        String dString = year + "-" + monthString + "-" + datesString + "\n\t"
                + houString + ":" + minString + ":" + secondString;
        return dString;
    }

    private static String snapName;

    public static Boolean getIsSetMotor1() {
        return isSetMotor1;
    }

    public static void setIsSetMotor1(Boolean isSetMotor1) {
        MyApp.isSetMotor1 = isSetMotor1;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setupDatabase();
        initAllData();
        insertTxtMotor();
        mInstance = MyApp.this;
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
        Bugly.init(getApplicationContext(), "d6df5275c0", false);
    }

    @Override
    public void setAppStyleColor() {
        super.setAppStyleColor();
        //设置APP的名称
        AppStyle.appNameId = R.string.app_name;
        //设置APP的主题色（Toolbar的颜色）
        AppStyle.appToolbarColor = R.color.colorPrimary;
        //实例化SharedPreferenceUtils工具类方便调用
        SharedPreferencesUtils.init(this);
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //gasepump.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "database", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    /*
     * description：将Txt格式的电机数据库转换为sqlite
     */
    private void insertTxtMotor() {
        MotorTxtToDb motorTxtToDb = new MotorTxtToDb();
        motorTxtToDb.getMotorDb(getApplicationContext());
    }

    /**
     * Description: 初始化全局变量，确保在对一次任务进行测试、增删改查的时候，所用的变量只需一个地方获取，全局通用。
     */
    private void initAllData() {
        taskEntity = null; // 正在操作的测试任务

    }

    //用于返回Application实例
    public static MyApp getInstance() {
        return mInstance;
    }

    public static TaskEntity getTaskEnity() {
        if (taskEntity == null) {
            taskEntity = new TaskEntity();
        }
        return taskEntity;
    }


    public static void setTaskEnity(TaskEntity taskEnity) {
        MyApp.taskEntity = taskEnity;
    }

    public static double[] recalculate(double power, double elec, int jishu) {
        double[] res = new double[2];

        double jishugonglv;
        double jishudianliu;
        //获取级数

        //获取换算参数
        switch (jishu) {
            case 1:
            case 2:
                jishugonglv = 0.035;
                jishudianliu = 0.16;
                break;
            case 3:
            case 4:
                jishugonglv = 0.033;
                jishudianliu = 0.25;
                break;
            case 5:
            case 6:
                jishugonglv = 0.032;
                jishudianliu = 0.33;
                break;
            case 7:
            default:
                jishugonglv = 0.03;
                jishudianliu = 0.4;
                break;
        }

        res[0] = (double) power * jishugonglv;
        res[1] = (double) elec * jishudianliu;
        return res;

    }


}

