package com.motor.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.greendao.manager.DataTFJ;
import com.greendao.manager.motorData;
import com.jaeger.library.StatusBarUtil;
import com.sensor.SensorData;
import com.sensor.view.SensorView;
import com.motor.Tools.MyFunction;
import com.motor.Tools.SerialControl;
import com.motor.administrator.DATAbase.R;
import com.motor.app.MyApp;
import com.motor.test.TestActivity;
import com.motor.test.fragment.TestFragment;
import com.xzkydz.bean.ComBean;
import com.xzkydz.helper.ComControl;
import com.xzkydz.helper.SerialHelper;
import com.xzkydz.util.DataType;

import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SensorActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
 
    @BindView(R.id.gl1_sensor)
    SensorView gl1Sensor;
    @BindView(R.id.UAB1_sensor)
    TextView UAB1Sensor;
    @BindView(R.id.UBC1_sensor)
    TextView UBC1Sensor;
    @BindView(R.id.UCA1_sensor)
    TextView UCA1Sensor;
    @BindView(R.id.IA1_sensor)
    TextView IA1Sensor;
    @BindView(R.id.IB1_sensor)
    TextView IB1Sensor;
    @BindView(R.id.IC1_sensor)
    TextView IC1Sensor;
    @BindView(R.id.P1_sensor)
    TextView P1Sensor;
    @BindView(R.id.Q1_sensor)
    TextView Q1Sensor;
    @BindView(R.id.cos1_sensor)
    TextView cos1Sensor;


    private boolean shuaXin;
    private float[] mFsList;
    private long pretime = 0;


    public static volatile long[] IsTx = new long[21]; // 测试线程修改参数
    public static volatile long CaijiTime = 0;
    public static int TxDelay = 5;
    private float JyZero = 0f;
    private float CyZero = 0f;
    private float Jy = 0f;
    private float Cy = 0f;
    public DataTFJ mdata;

    //<editor-fold desc="Description">
    private boolean IsStart;
    //</editor-fold>
    private Handler handler;
    SerialControl ComA;
    private motorData mData;
    private int mpower;
    private int msingal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //保持屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.setContentView(R.layout.activity_sensor);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //决定左上角的图标是否可以点击
        StatusBarUtil.setColor(this, getResources().getColor(R.color.tittleBar), 0);
        mdata = new DataTFJ();
        mData=new motorData();
        handler = new Handler();

        mFsList = new float[16];
        shuaXin = true;

     

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
        ComA = new SerialControl(SensorActivity.this, DataType.DATA_OK_PARSE);
        ComA.setiDelay(100);  // 设置读取串口的时间间隔
        ComControl.OpenComPort(ComA); // 打开串口
        // 定义刷新UI线程，从SerialControl 类中读取需要刷新的数据。
        DispQueueThread DispQueue = new DispQueueThread();
        DispQueue.start();
        if (IsStart == false) {
            handler.postDelayed(runnable, 1000);
            IsStart = true;
        }
        SendforData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ComControl.CloseComPort(ComA);
        handler.removeCallbacksAndMessages(null);
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
                        try {
                            getData(ComData);
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


    DecimalFormat df2 = new DecimalFormat("####0.00");
    DecimalFormat df3 = new DecimalFormat("####0.000");

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

          
            // gonglv1
            if (TimeBetween(IsTx[3]) > TxDelay * 1000) {

                UAB1Sensor.setText("-- V");
                UBC1Sensor.setText("-- V");
                UCA1Sensor.setText("-- V");
                IA1Sensor.setText("-- A");
                IB1Sensor.setText("-- A");
                IC1Sensor.setText("-- A");
                P1Sensor.setText("-- kW");
                Q1Sensor.setText("-- kVA");
                cos1Sensor.setText("--");

            }else{
                UAB1Sensor.setText(df2.format(mData.getUAB())+" V");
                UBC1Sensor.setText(df2.format(mData.getUBC())+" V");
                UCA1Sensor.setText(df2.format(mData.getUCA())+" V");
                IA1Sensor.setText(df2.format(mData.getIA())+" A");
                IB1Sensor.setText(df2.format(mData.getIB())+" A");
                IC1Sensor.setText(df2.format(mData.getIC())+" A");
                P1Sensor.setText(df2.format(mData.getYggl())+" kW");
                Q1Sensor.setText(df2.format(mData.getSzgl())+" kVA");
                cos1Sensor.setText(df3.format(mData.getGlys())+" ");
                if(TimeBetween(IsTx[3])<1000)
            {
                SetSensorState(gl1Sensor,mpower,msingal,1);
            }}
         

            handler.postDelayed(this, 1000);
        }
    };

    public synchronized void SetFiveOne(int index) {
        IsTx[index] = System.currentTimeMillis();
    }


    private long TimeBetween(Long mTime) {
        return System.currentTimeMillis() - mTime;
    }

    private void SetSensorState(SensorView msv, float mpower, float msignal, int minf) {
        SensorData svData = new SensorData();
        // 第二步：设置 SensorData 属性
        svData.setStatus(minf)
                .setPower(mpower)
                .setSignal(msignal);

        // 第三步：给SensorView 赋值
        msv.setData(svData);
    }

    private void getData(ComBean comData) {
        int mspan;

        switch (comData.recData[2]) {

            case 100:
                getUIData(comData);
                SetFiveOne(3);
                break;
            case 78:
                getHamData(comData);
                SetFiveOne(3);
                break;
        }
    }

    private void getHamData(ComBean comData) {
        double[] mham = new double[32];

        for (int i = 0; i < 32; i++) {
            mham[i] = MyFunction.byte2float(comData.recData, 14 + 2 * i);
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

    private void getUIData(ComBean comData) {
        double QblcU = 500d;
        double QblcI = 500d;
        double Dybb = 1;
        double Dlbb = 1;

        try {
          

            
          
            Dybb = 1;
            Dlbb =1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        double exU = QblcU * Dybb;
        double exI = QblcI * Dlbb;
        int i = 14;
        mData.setSxbphd((float) ((MyFunction.twoBytesToInt(comData.recData, i) / 10000) * 100));
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
        i += 1;

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

    private void SendforData() {
        //4B590B8001000101FF8001000101F4
        //4B590B8001000101FF8001000100F5


        new Handler().postDelayed(new Runnable() {
            public void run() {
                sendPortData(ComA, "4B590B8001000101FF8001000101F4"); // 发送命令获取功率数据
            }
        }, 100);

    }

    // ----------------------------------------------------串口发送
    private void sendPortData(SerialHelper ComPort, String sOut) {
        if (ComPort != null && ComPort.isOpen()) {
            ComPort.sendHex(sOut);
        }
    }
}

