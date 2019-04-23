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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.greendao.dbUtils.GreateTaskUtils;
import com.greendao.manager.DataTFJ;
import com.greendao.manager.motorData;
import com.jaeger.library.StatusBarUtil;
import com.ldoublem.loadingviewlib.view.LVPlayBall;
import com.motor.Adapter.TestPagerAdapter;
import com.motor.Tools.MyFunction;
import com.motor.Tools.SerialControl;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.administrator.DATAbase.greendao.TaskResEnity;
import com.motor.app.MyApp;
import com.motor.test.fragment.DataFragment;
import com.motor.test.fragment.ParameterFragment;
import com.motor.test.fragment.TestFragment;
import com.xzkydz.bean.ComBean;
import com.xzkydz.helper.ComControl;
import com.xzkydz.helper.SerialHelper;
import com.xzkydz.util.DataType;

import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;


public class TestActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tl)
    com.flyco.tablayout.SlidingTabLayout tl;
    @BindView(R.id.loadding)
    LVPlayBall loadding;
    public MyApp myApp;
    public DataTFJ mdata;


    SerialControl ComA;
    ParameterFragment parameterFragment;
    DataFragment dataFragment;
    TestFragment testFragment;
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    DecimalFormat df1 = new DecimalFormat("####0.0");


    public double electricquantity;//电量
    private int IsConfiged = 0;
    private boolean FirstConfiged;

    public boolean isShuaXin() {
        return shuaXin;
    }

    public void setShuaXin(boolean shuaXin) {
        this.shuaXin = shuaXin;
    }

    private boolean shuaXin;

    private long pretime = 0;
    private TaskEntity mtask;
    public motorData mData;
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
        getData();

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

        SendforData();
    }


    public void getData() {
        mdata = new DataTFJ();
        mData = new motorData();


        try {
            mdata.setMotordata(mData);
            mdata.SetHisTask(myApp.getTaskEnity());
            ininmotorData();
            mdata.setMotordata(mData);

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
        parameterFragment = new ParameterFragment();
        dataFragment = new DataFragment();
        dataFragment.setDataManage(false);
        testFragment = new TestFragment();


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

    @Override
    protected void onStart() {
        super.onStart();
        // DataType.DATA_OK_PARSE : 返回整的串口数据包
        // DataType.DATA_NO_PARSE : 返回不进行校验的数据，不按完整数据包返回。
        ComA = new SerialControl(TestActivity.this, DataType.DATA_OK_PARSE);
        ComA.setiDelay(20);  // 设置读取串口的时间间隔
        ComControl.OpenComPort(ComA); // 打开串口
        // 定义刷新UI线程，从SerialControl 类中读取需要刷新的数据。
        DispQueueThread DispQueue = new DispQueueThread();
        DispQueue.start();
        if (IsStart == false) {
            handler.postDelayed(runnable, 1000);
            IsStart = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ComControl.CloseComPort(ComA);
        handler.removeCallbacksAndMessages(null);

    }

    public boolean getshuaxin() {
        return shuaXin;
    }

    private void ininmotorData() {

        try {
            mData.setMethods(mtask.getCsff());
            mData.setEddl(Double.parseDouble(mtask.getDjeddl1()));
            mData.setEddy(Double.parseDouble(mtask.getDjeddy1()));
            mData.setEdgl(Double.parseDouble(mtask.getDjedgl1()));
            //   mData.setEdglys(Double.parseDouble(mtask.getEdglys()));

            mData.setEdxl(Double.parseDouble(mtask.getDjedxl1()));
            mData.setKzdl(Double.parseDouble(mtask.getDjkzdl1()));
            mData.setKzgl(Double.parseDouble(mtask.getDjkzgl1()));
            mData.setJs(Integer.parseInt(mtask.getDjjs1()));
            mData.setWgjjdl(Double.parseDouble(mtask.getDjwgjjdl1()));
            mData.setMbglys(0.95d);

            mData.setMethods(mtask.getCsff());


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------刷新显示线程
    private class DispQueueThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                final ComBean ComData;

                try {
                    while ((ComData = ComA.QueueList.poll()) != null) {
//                        runOnUiThread(new Runnable() {
//                            public void run() {
//                                DispRecData(ComData);
//                            }
//                        });

                        try {
                            getData(ComData);

                            mData.Calculate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(100);//显示性能高的话，可以把此数值调小。
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private double preyggl = 0d;
    private double prewggl = 0d;
    private String strsxbphd;
    private int showerror = 5;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {


            // gonglv1
            if (TimeBetween(IsTx[3]) > TxDelay * 1000) {

                testFragment.refreshdisconnect();
            } else if ((TimeBetween(IsTx[3]) < TxDelay * 1000)) {
                if (TimeBetween(IsTx[3]) < 1000) {
                    testFragment.setSensor(mpower, msingal);
                }
                mData.Calculate();

                mData.setYgdn(mData.getYgdn() + (preyggl + mData.getYggl()) / 2 / 3600);
                mData.setWgdn(mData.getWgdn() + (prewggl + mData.getWggl()) / 2 / 3600);
                preyggl = mData.getYggl();
                prewggl = mData.getWggl();
                mdata.setMotordata(mData);
                testFragment.refresh();

            }


            if (showerror > 0) {
                showerror--;
            }
            if ( showerror == 0) {
                showerror = 5;
                if(IsConfiged == 2 ) {
                    Toasty.error(TestActivity.this, "传感器配置错误！", 1).show();
                }
//                try {
//                    Toasty.info(TestActivity.this, "三相不平衡度" + mData.getSxbphd() + "|" + strsxbphd,1).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
            if (FirstConfiged) {
                Toasty.success(TestActivity.this, "传感器配置成功！").show();
                FirstConfiged = false;
            }
            handler.postDelayed(this, 1000);
        }
    };

    public void clearelec() {
        mData.setYgdn(0);
        mData.setWgdn(0);
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

    private void getData(ComBean comData) {
        int mspan;
        int Leixing = Math.abs((int) comData.recData[9]);
        if (Leixing == 128 && comData.recData.length == 36) {
            IsConfiged = 2;
            FirstConfiged = false;
        } else {
            if (IsConfiged != 1) {
                FirstConfiged = true;
            }
            IsConfiged = 1;
        }
        switch (comData.recData[2]) {


            case 100://电压电流
                getUIData(comData);
                SetFiveOne(3);
                break;
            case 78://谐波
                getHamData(comData);
                SetFiveOne(3);
                break;
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
                mData.setHarmUA(mham);
                break;
            case 4:
                mData.setHarmIA(mham);
                break;
            case 5:
                mData.setHarmUB(mham);
                break;
            case 6:
                mData.setHarmIB(mham);
                break;
            case 7:
                mData.setHarmUC(mham);
                break;
            case 8:
                mData.setHarmIC(mham);
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
            mData.setSxbphd(((((float) MyFunction.twoBytesToInt(comData.recData, i)) / 10000) * 100));
            strsxbphd = comData.recData[14] + " " + comData.recData[15];
            i += 2;
            mData.setUA((float) ((MyFunction.twoByte2int(comData.recData, i) * exU / 10000)));
            i += 2;
            mData.setUB((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000)));
            i += 2;
            mData.setUC((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000)));
            i += 2;
            mData.setUAB((float) ((MyFunction.twoByte2int(comData.recData, i) * exU / 10000)));
            i += 2;
            mData.setUBC((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000)));
            i += 2;
            mData.setUCA((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exU / 10000)));
            i += 2;
            mData.setIA((float) ((MyFunction.twoByte2int(comData.recData, i) * exI / 10000)));
            i += 2;
            mData.setIB((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exI / 10000)));
            i += 2;
            mData.setIC((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exI / 10000)));
            i += 2;
            mData.setLxdl((float) ((MyFunction.twoBytesToInt(comData.recData, i) * exI / 10000)));
            i += 2;
            mData.setYggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI * 3 / 10000)));
            i += 2;
            mData.setWggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI * 3 / 10000)));
            i += 2;
            mData.setSzgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI * 3 / 10000)));
            i += 2;
            mData.setGlys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));
            i += 2;
            mData.setDwpl((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 100)));
            i += 2;
            mData.setAyggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setByggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setCyggl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setAwggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setBwggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setCwggl((float) (Math.abs(MyFunction.twoBytesToInt_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setAszgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setBszgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setCszgl((float) ((MyFunction.twoByte2double_(comData.recData, i) * exU * exI / 10000)));
            i += 2;
            mData.setAglys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));
            i += 2;
            mData.setBglys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));
            i += 2;
            mData.setCglys((float) ((MyFunction.twoByte2double_(comData.recData, i) / 10000)));
            i += 2;
            mData.setKUA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));
            i += 2;
            mData.setKUB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));
            i += 2;
            mData.setKUC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));
            i += 2;
            mData.setKIA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));
            i += 2;
            mData.setKIB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));
            i += 2;
            mData.setKIC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000)));
            i += 2;
            mData.setHCUA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000) * 100));
            i += 2;
            mData.setHCUB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000) * 100));
            i += 2;
            mData.setHCUC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000) * 100));
            i += 2;
            mData.setHCIA((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000) * 100));
            i += 2;
            mData.setHCIB((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000) * 100));
            i += 2;
            mData.setHCIC((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 100)));
            i += 2;
            mData.setPhUAB((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100);

            i += 1;
            mData.setPhUBC((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100);
            i += 1;
            mData.setPhUCA((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100);
            i += 1;
            mData.setPhUIA((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100);
            i += 1;
            mData.setPhUIB((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100);
            i += 1;
            mData.setPhUIC((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                    comData.recData, i, i + 1)) / 100);


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

}
