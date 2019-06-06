package com.motor.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.greendao.dbUtils.GreateTaskUtils;
import com.greendao.manager.DataTFJ;
import com.greendao.manager.MotorData;
import com.jaeger.library.StatusBarUtil;
import com.ldoublem.loadingviewlib.view.LVPlayBall;
import com.motor.Adapter.TestPagerAdapter;
import com.motor.Listener.ISensorInf;
import com.motor.Tools.MyFunction;
import com.motor.Tools.SerialControl;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.administrator.DATAbase.greendao.TaskResEnity;
import com.motor.app.MyApp;
import com.motor.test.fragment.DataFragment;
import com.motor.test.fragment.ParameterFragment;
import com.motor.test.fragment.TestFragment;
import com.motor.test.fragment.childfragment.TestDatafragment;
import com.sensor.SensorData;
import com.sensor.SensorInf;
import com.sensor.view.SensorView;
import com.xzkydz.bean.ComBean;
import com.xzkydz.helper.ComControl;
import com.xzkydz.helper.SerialHelper;
import com.xzkydz.util.DataType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static com.xzkydz.function.ftp.Globals.getContext;


public class TestActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tl)
    com.flyco.tablayout.SlidingTabLayout tl;
    @BindView(R.id.loadding)
    LVPlayBall loadding;

    public TestDatafragment testDatafragment;
    public SensorView sensorView;
    public MyApp myApp;
    public DataTFJ mdata;
    public MotorData mDataMotor;

    private SerialControl ComA;
    ParameterFragment parameterFragment;//参数fragment
    DataFragment dataFragment;//数据fragment
    TestFragment testFragment;//测试fragment
    DecimalFormat df1 = new DecimalFormat("0.0");
    DecimalFormat df2 = new DecimalFormat("0.00");
    DecimalFormat df3 = new DecimalFormat("0.000");
    public double electricquantity;//电量
    private int IsConfiged = 0;
    private boolean FirstConfiged = true;

    public boolean isShuaXin() {
        return shuaXin;
    }

    public void setShuaXin(boolean shuaXin) {
        this.shuaXin = shuaXin;
    }

    private boolean shuaXin;

    private long pretime = 0;
    private TaskEntity mtask;

    int msingal, mpower;

    public static volatile long[] IsTx = new long[21]; // 测试线程修改参数
    public static volatile long CaijiTime = 0;
    public static int TxDelay = 5;

    //<editor-fold desc="Description">
    private boolean IsStart;
    //</editor-fold>
    private Handler handler;


    public synchronized void SetFiveOne(int index) {
        IsTx[index] = System.currentTimeMillis();
    }

    public synchronized void SetCaiji() {
        CaijiTime = System.currentTimeMillis();
    }

    private long TimeBetween(Long mTime) {
        return System.currentTimeMillis() - mTime;
    }


    @Override
    public void onBackPressed() {
        Dialog dialog = new AlertDialog.Builder(TestActivity.this).setTitle("选择此任务状态")
                .setIcon(R.drawable.complete)
                .setMessage("测试任务是否完成？")
                .setPositiveButton("已完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mtask.set_IsCompleteTask(true);
                        GreateTaskUtils.update(mtask);
                        dialog.dismiss();
                        finish();
                    }
                }).setNegativeButton("未完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.tittleBar), 0);
        setBackArrowStyle();
        myApp = MyApp.getInstance();
        handler = new Handler();
        showLoading();
        mtask = MyApp.getTaskEnity();
        initData();//初始化数据
        shuaXin = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initViewPager();
                        hideLoading();
                    }
                });
            }
        }).start();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // DataType.DATA_OK_PARSE : 返回整的串口数据包
        // DataType.DATA_NO_PARSE : 返回不进行校验的数据，不按完整数据包返回。
        ComA = new SerialControl(TestActivity.this, DataType.DATA_OK_PARSE);
        ComA.setiDelay(20);  // 设置读取串口的时间间隔
        ComControl.OpenComPort(ComA); // 打开串口
        SendforData();// 发送命令获取功率数据
        ComA.setOnReceivedSensorListener(new SerialControl.OnReceivedSensorData() {
            @Override
            public void getData(final ISensorInf sensorInf) {
                switch (sensorInf.getSensorType()) {
                    case 1://功率箱
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //sensorData 用于设置传感器信息（电量、信号、状态、图片）
                                SensorData sensorData = new SensorData(sensorInf.getPower(), sensorInf.getSignal(),
                                        SensorInf.NORMAL, System.currentTimeMillis());
                                testFragment.setSensor_(sensorData);
                            }
                        });
                        break;
                }
            }
        });
        handler.postDelayed(runnable, 1000);
    }

    public void initData() {//onCreate方法中执行,只执行一次
        mdata = new DataTFJ();
        mDataMotor = new MotorData();
        try {
            mdata.setMotordata(mDataMotor);
            mdata.SetHisTask(myApp.getTaskEnity());
            ininmotorData();//初始化电机数据
            mdata.setMotordata(mDataMotor);
            mdata.Refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBackArrowStyle() {
        setSupportActionBar(toolbar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showLoading() {
        loadding.setViewColor(R.color.green);
        loadding.setBallColor(R.color.red);
        loadding.startAnim(300);
    }

    private void hideLoading() {
        loadding.setVisibility(View.GONE);
        loadding.stopAnim();
    }

    /*
     * description:初始化Fragment  和 ViewPager
     */
    private void initViewPager() {
        parameterFragment = new ParameterFragment();//参数fragment
        testFragment = new TestFragment();//测试fragment
        dataFragment = new DataFragment();//数据fragment
        dataFragment.setDataManage(false);//


        //构造适配器
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(parameterFragment);
        fragments.add(testFragment);
        fragments.add(dataFragment);
        TestPagerAdapter mViewPagerAdapter = new TestPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setCurrentItem(1);
        String[] tittle = {"参  数", "测  试", "数  据"};
        tl.setViewPager(viewPager, tittle);
        initEvent();

    }


    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tl.setCurrentTab(0, true);
                        break;
                    case 1:
                        tl.setCurrentTab(1, true);
                        break;
                    case 2:
                        tl.setCurrentTab(2, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        dataFragment.GetData();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean getshuaxin() {
        return shuaXin;
    }

    private void ininmotorData() {

        try {
            mDataMotor.setMethods(mtask.getCsff());

            mDataMotor.setEddl(Double.parseDouble(df2.format(mtask.getDjeddl1())));//额定电流
            mDataMotor.setEddy(Double.parseDouble(df2.format(mtask.getDjeddy1())));//额定电压
            mDataMotor.setEdgl(Double.parseDouble(df2.format(mtask.getDjedgl1())));//额定功率
            //   mData.setEdglys(Double.parseDouble(mtask.getEdglys()));

            mDataMotor.setEdxl(Double.parseDouble(df2.format(mtask.getDjedxl1())));//额定效率
            mDataMotor.setKzdl(Double.parseDouble(df2.format(mtask.getDjkzdl1())));//空载电流
            mDataMotor.setKzgl(Double.parseDouble(df2.format(mtask.getDjkzgl1())));//空载功率
            mDataMotor.setJs(Integer.parseInt(mtask.getDjjs1()));//级数
            mDataMotor.setWgjjdl(Double.parseDouble(df2.format(mtask.getDjwgjjdl1())));//无功经济当量
//            mDataMotor.setMbglys(0.95d);//目标功率因数
            mDataMotor.setMbglys(Double.parseDouble(df3.format(mtask.getDjmbglys())));//目标功率因数
            mDataMotor.setMethods(mtask.getCsff());//测试方法
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------刷新显示线程
//    private class DispQueueThread extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            while (!isInterrupted()) {
//                final ComBean ComData;
//                try {
//                    while ((ComData = ComA.QueueList.poll()) != null) {
//                        try {
//                            Log.i("ggh", "DispQueueThread ");
//                            getData(ComData);//更新电压电流和谐波数据
//                            mDataMotor.Calculate();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Thread.sleep(20);//显示性能高的话，可以把此数值调小。
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


    private double preyggl = 0d;
    private double prewggl = 0d;
    private String strsxbphd;
    private int showerror = 5;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!MyApp.isConnected) {//如果传感器断开了,并且此时允许刷新
                if (shuaXin) {
                    Log.i("ggh", "runnable----------------------------------------------------------------------------------------------中断");
                    testFragment.refreshdisconnect();
                }
            } else {//如果传感器没断
                getData();//更新电压电流和谐波数据
                mDataMotor.Calculate();//mData=====motorData
                if (shuaXin) {//如果此时允许刷新
                    mDataMotor.setYgdn(mDataMotor.getYgdn() + (preyggl + mDataMotor.getYggl()) / 2 / 3600);//有功电能
                    mDataMotor.setWgdn(mDataMotor.getWgdn() + (prewggl + mDataMotor.getWggl()) / 2 / 3600);//无功电能
                    preyggl = mDataMotor.getYggl();
                    prewggl = mDataMotor.getWggl();
                }
                mdata.setMotordata(mDataMotor);//mdata=====DataTFJ
                testFragment.refresh();//给各项赋值
            }
            handler.postDelayed(this, 1000);
        }
    };

    public void clearelec() {
        mDataMotor.setYgdn(0);//有功电能
        mDataMotor.setWgdn(0);//无功电能
    }


    public void refresh() {

    }

    public boolean SaveData() {
        boolean isSave = false;
        TaskResEnity mres = new TaskResEnity();
        mdata.CopyRes(mres);
        try {
            double a = mres.getGlys();
            if (a == 0d) {
                mres.setGlys(1d);
            }
            mres.setSaveTime(getSysTime());
            MyApp.getDaoInstant().getTaskResEnityDao().insert(mres);
            isSave = true;
            SetCaiji();
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }
        return isSave;
    }

    // -------------------------------------------------——-------------------获取系统时间
    String getSysTime() {
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

    private void getData() {
//        int mspan;
        int Leixing = Math.abs((int) MyApp.comBeanMotor.recData[9]);
        if (Leixing == 128 && MyApp.comBeanMotor.recData.length == 36) {//普通数据包，则重新发送命令使其变为电机模式
            Log.i("dde", "发送命令获取功率数据前: ");
            sendPortData(ComA, "4B590B8001000101FF8001000101F4"); // 发送命令获取功率数据
            Log.i("dde", "发送命令获取功率数据后: ");
        }
        if (MyApp.comBeanUI != null) {
            getUIData(MyApp.comBeanUI);
        }
        if (MyApp.comBeanHAM != null) {
            getHamData(MyApp.comBeanHAM);
        }
    }

    //谐波
    private void getHamData(ComBean comData) {
        double[] mham = new double[32];

        for (int i = 0; i < 32; i++) {
            mham[i] = MyFunction.byte2float(comData.recData, 14 + 2 * i);
            if (mham[i] < 0.01d) {
                mham[i] = 0.0d;
            }
        }

        mpower = MyFunction.twoBytesToInt(comData.recData, 78);
        msingal = comData.recData[80] < 0 ? 256 + comData.recData[80] : comData.recData[80];
//        testFragment.setSensor(mpower,msingal);
        switch (comData.recData[13]) {
            case 3:
                mDataMotor.setHarmUA(mham);
                break;
            case 4:
                mDataMotor.setHarmIA(mham);
                break;
            case 5:
                mDataMotor.setHarmUB(mham);
                break;
            case 6:
                mDataMotor.setHarmIB(mham);
                break;
            case 7:
                mDataMotor.setHarmUC(mham);
                break;
            case 8:
                mDataMotor.setHarmIC(mham);
                break;
        }
    }

    //电压电流
    private void getUIData(ComBean comData) {
        double QblcU = 500d;
        double QblcI = 500d;
        double Dybb = 1;
        double Dlbb = 1;

        try {
            if (mtask.getDjqblc1().equals("500A")) {

                QblcI = 500;
            } else if (mtask.getDjqblc1().equals("20A")) {
                QblcI = 100;
            }
            Dybb = Double.parseDouble(mtask.getDybb1()) / Double.parseDouble(mtask.getDybb2());
            Dlbb = Double.parseDouble(mtask.getDlbb1()) / Double.parseDouble(mtask.getDlbb2());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        double exU = QblcU * Dybb;
        double exI = QblcI * Dlbb;
        if (shuaXin) {
            int i = 14;
//            mDataMotor.setSxbphd(((((float) MyFunction.twoBytesToInt(comData.recData, i)) / 10000) * 100));
            mDataMotor.setSxbphd((float) MyFunction.twoBytesToInt(comData.recData, i) / 10000);
            strsxbphd = comData.recData[14] + " " + comData.recData[15];
            i += 2;
            mDataMotor.setUA((float) ((MyFunction.twoByte2int(comData.recData, i) * exU / 10000)));
            i += 2;
            mDataMotor.setUB((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000)));
            i += 2;
            mDataMotor.setUC((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000)));
            i += 2;
            if (mDataMotor.getMethod() == 0) {//单瓦法
                mDataMotor.setUAB(Float.parseFloat(df2.format((float) (Math.sqrt(3) * (MyFunction.twoByte2int(comData.recData, i) * exU / 10000)))));
            } else {
                mDataMotor.setUAB(Float.parseFloat(df2.format((float) (MyFunction.twoByte2int(comData.recData, i) * exU / 10000))));
            }
            i += 2;
            mDataMotor.setUBC(Float.parseFloat(df2.format((float) (MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000))));
            i += 2;
            mDataMotor.setUCA(Float.parseFloat(df2.format((float) (MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000))));
            i += 2;
            mDataMotor.setIA(Float.parseFloat(df2.format((float) (MyFunction.twoByte2int(comData.recData, i) * exI / 10000))));
            i += 2;
            mDataMotor.setIB(Float.parseFloat(df2.format((float) (MyFunction.twoBytesToInt(comData.recData, i) * exI / 10000))));
            i += 2;
            mDataMotor.setIC(Float.parseFloat(df2.format((float) (MyFunction.twoBytesToInt(comData.recData, i) * exI / 10000))));
            i += 2;
            mDataMotor.setLxdl((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exI / 10000)));
            i += 2;
            mDataMotor.setYggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI * 3 / 10000)));
            i += 2;
            mDataMotor.setWggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI * 3 / 10000)));
            i += 2;
            mDataMotor.setSzgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI * 3 / 10000)));
            i += 2;
            mDataMotor.setGlys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));//功率因数
            i += 2;
            mDataMotor.setDwpl((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 100)));
            i += 2;
            mDataMotor.setAyggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setByggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setCyggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setAwggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setBwggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setCwggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setAszgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setBszgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setCszgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mDataMotor.setAglys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));
            i += 2;
            mDataMotor.setBglys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));
            i += 2;
            mDataMotor.setCglys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));//C项功率因数
            i += 2;
            mDataMotor.setKUA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//A项电压K值
            i += 2;
            mDataMotor.setKUB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//B项电压K值
            i += 2;
            mDataMotor.setKUC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//C项电压K值
            i += 2;
            mDataMotor.setKIA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//A项电流K值
            i += 2;
            mDataMotor.setKIB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//B项电流K值
            i += 2;
            mDataMotor.setKIC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//C项电流K值
            i += 2;
//            mData.setHCUA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000) * 100));//A项电压谐波分量
            mDataMotor.setHCUA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//A项电压谐波分量
            i += 2;
            mDataMotor.setHCUB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//B项电压谐波分量
            i += 2;
            mDataMotor.setHCUC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//C项电压谐波分量
            i += 2;
            mDataMotor.setHCIA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//A项电流谐波分量
            i += 2;
            mDataMotor.setHCIB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//B项电流谐波分量
            i += 2;
            mDataMotor.setHCIC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));//C项电流谐波分量
            i += 2;
            //AB项电压相位角
            double phUAB = (double) (MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100f * 180f / Math.PI);
//            Log.i("sss", "UABfloat:  " + UABfloat);
            mDataMotor.setPhUAB(phUAB);
            i += 1;
            //BC项电压相位角
            double phUBC = (float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100f * 180f / Math.PI;
//            Log.i("sss", "phUBC: " + phUBC);
            mDataMotor.setPhUBC(phUBC);
            i += 1;
            //CA项电压相位角
            double phUCA = (float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100f * 180f / Math.PI;
//            Log.i("sss", "phUCA: " + phUCA);
            mDataMotor.setPhUCA(phUCA);
            i += 1;
            //A项电压电流相位角
            double phUIA = (float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100f * 180f / Math.PI;
//            Log.i("sss", "phUIA: " + phUIA);
            mDataMotor.setPhUIA(phUIA);
            i += 1;
            //B项电压电流相位角
            double phUIB = (float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100f * 180f / Math.PI;
//            Log.i("sss", "phUIB: " + phUIB);
            mDataMotor.setPhUIB(phUIB);
            i += 1;
            //C项电压电流相位角
            double phUIC = (float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100f * 180f / Math.PI;
//            Log.i("sss", "phUIC: " + phUIC);
            mDataMotor.setPhUIC(phUIC);
//            Log.i("sss", "原生数据====phUIA: " + phUIA + " phUIB: " + phUIB + " phUIC " + phUIC + " phUAB " + phUAB + " phUBC " + phUBC+" phUCA "+phUCA);

//            mData.setPhUIC((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
//                    comData.recData, i, i + 1)) / 100 );


            //640-800
            //  electricquantity = ((MyFunction.twoBytesToInt(comData.recData, i) / 100));
            mpower = MyFunction.twoBytesToInt(comData.recData, 100);
            msingal = comData.recData[102] < 0 ? 256 + comData.recData[102] : comData.recData[102];

//        if(energyTime!=null)
//        {
//          energy=energy+  TimeBetween(energyTime)/1000*mData.getYggl();
//        }
//        else{
//            energyTime=System.currentTimeMillis();
//        }
        }
    }

    // 发送命令获取功率数据
    private void SendforData() {
        //4B590B8001000101FF8001000101F4
        //4B590B8001000101FF8001000100F5
        new Handler().postDelayed(new Runnable() {
            public void run() {
                sendPortData(ComA, "4B590B8001000101FF8001000101F4"); // 发送命令获取功率数据
            }
        }, 100);
        Toasty.info(this, "开始配置传感器！").show();
    }

    // ----------------------------------------------------串口发送
    private void sendPortData(SerialHelper ComPort, String sOut) {
        if (ComPort != null && ComPort.isOpen()) {
            ComPort.sendHex(sOut);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ComA.close();
        ComControl.CloseComPort(ComA);
        MyApp.isConnected = false;
        MyApp.isFuYongCanShu = false;
        if (handler != null) {
            handler.removeCallbacks(runnable);
            handler = null;
        }
    }

}
