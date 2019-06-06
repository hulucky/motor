package com.motor.Tools;

import android.content.Context;
import android.support.annotation.Nullable;

import com.motor.Listener.ISensorInf;
import com.motor.app.MyApp;
import com.motor.bean.MotorrBean;
import com.motor.test.TestActivity;
import com.xzkydz.bean.ComBean;
import com.xzkydz.helper.SerialHelper;

import java.util.LinkedList;
import java.util.Queue;

import static com.motor.app.MyApp.motorBean_Sensor;


public class SerialControlMy extends SerialHelper {
//    public Queue<ComBean> QueueList = new LinkedList<ComBean>();

    public SerialControlMy(Context context, int mDataType) {
        super(context, mDataType);
    }

    public SerialControlMy(Context context, String sPort, String sBaudRate, int mDataType) {
        super(context, sPort, sBaudRate, mDataType);
    }

    @Override
    protected void onDataReceived(ComBean comBean) {
        switch (comBean.recDataType) {
            case -128://功率箱
                MyApp.isConnected_Sensor = true;
                MyApp.comBeanMotor_Sensor = comBean;
                if (comBean.recData.length == 104) {//电压电流 1.6秒发一次
                    MyApp.comBeanUI_Sensor = comBean;
                    //解析传感器串口数据包，并更新motorBean的属性
                    if (motorBean_Sensor == null) {
                        motorBean_Sensor = new MotorrBean(comBean.recData);
                    } else {
                        motorBean_Sensor.caculate(comBean.recData);
                    }
                }
                if (comBean.recData.length == 82) {//谐波 时刻在发送
                    MyApp.comBeanHAM_Sensor = comBean;
                }

                //设置回调接口
                if (receivedSensorData != null && motorBean_Sensor != null) {
                    receivedSensorData.getData(motorBean_Sensor);
                }
                break;
        }

        // 处理接收到的数据（转换、计算）
//        AddQueue(comBean);
    }

    private OnReceivedSensorData receivedSensorData;

    public void setOnReceivedSensorListener(@Nullable OnReceivedSensorData l) {
        this.receivedSensorData = l;
    }

    public interface OnReceivedSensorData {
        void getData(ISensorInf sensorInf);
    }

//    public synchronized void AddQueue(ComBean ComData) {
//        QueueList.add(ComData);
//    }
}
