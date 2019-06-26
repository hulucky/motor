package com.motor.print;

import android.content.Intent;
import android.icu.util.Measure;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.greendao.manager.TaskEntityDao;
import com.greendao.manager.TaskResEnityDao;
import com.jaeger.library.StatusBarUtil;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.administrator.DATAbase.greendao.TaskResEnity;
import com.motor.app.MyApp;
import com.xzkydz.bluetoothlib.BluetoolthPrintActivity;
import com.xzkydz.bluetoothlib.bean.Regulation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrintActivity extends BluetoolthPrintActivity {

    private long testResID;
    private TaskResEnity testRes;
    private TaskEntity taskParInf;
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.tittleBar), 0);
        Intent intent = getIntent();
        testResID = intent.getLongExtra("testResID", 0);
        try {
            testRes = MyApp.getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.Id.eq(testResID)).list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            taskParInf = MyApp.getDaoInstant().getTaskEntityDao().queryBuilder().where(TaskEntityDao.Properties.Id.eq(testRes.getTaskId())).list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Regulation> setRegulations() {
        // Regulation对象是对打印规则的封装，需要传入三个参数:【规定初始化时只有两种方案，否则会报错】
        // name 方案名称
        // start 需要打印的参数起始位置(包含)
        // end 需要打印的参数结束位置(包含)
        List<Regulation> regulations = new ArrayList<>();
        Regulation reg0 = new Regulation("打印全部", 0, 100);
        Regulation reg1 = new Regulation("不打印测试数据", 100, 100);//但受检单位等项目会打印
        regulations.add(reg0);
        return regulations;
    }


    @Override
    public List<String> setParametersName() {

        List<String> parS = new ArrayList<>();
        parS.add("平均电压");
        parS.add("平均电流");
        parS.add("有功功率");
        parS.add("无功功率");
        parS.add("输出功率");
        parS.add("功率因数");
        parS.add("有功电能");
        parS.add("无功电能");
        parS.add("电网频率");
        parS.add("视在功率");
        parS.add("负载系数");
        parS.add("综合效率");
        parS.add("AB线电压");
        parS.add("BC线电压");
        parS.add("CA线电压");
        parS.add("A相电流");
        parS.add("B相电流");
        parS.add("C相电流");
        parS.add("A相有功功率");
        parS.add("B相有功功率");
        parS.add("C相有功功率");
        parS.add("A相无功功率");
        parS.add("B相无功功率");
        parS.add("C相无功功率");
        parS.add("A相视在功率");
        parS.add("B相视在功率");
        parS.add("C相视在功率");
        parS.add("A相功率因数");
        parS.add("B相功率因数");
        parS.add("C相功率因数");
        parS.add("AB相电压相位角");
        parS.add("BC相电压相位角");
        parS.add("CA相电压相位角");
        parS.add("A相电压电流相位角");
        parS.add("B相电压电流相位角");
        parS.add("C相电压电流相位角");
        parS.add("综合功率损耗");
        parS.add("空载无功功率");
        parS.add("额定负载无功功率");
        parS.add("有功功率损耗");
//        parS.add("额定负载功率损耗");  //去掉
        parS.add("综合消耗功率");
        parS.add("额定综合消耗功率");
        parS.add("额定综合效率");
        parS.add("无功补偿容量");
        parS.add("无功补偿电容量");
        parS.add("运行状态");
        parS.add("电机效率");


        return parS;


    }

    @Override
    public List<String> setParametersData() {
        List<String> datas = new ArrayList<>();
//四个固定
        datas.add(taskParInf.getUnitName());
        datas.add(taskParInf.getNumber());
        datas.add(taskParInf.getPeopleName());
        datas.add(testRes.getSaveTime());

//        datas.add(df2.format(testRes.getPjdy()) + "");
        datas.add(df2.format(testRes.getPjxdy()) + "");
        datas.add(df2.format(testRes.getPjdl()) + "");
        datas.add(df2.format(testRes.getYggl()) + "");
        datas.add(df2.format(testRes.getWggl()) + "");
        datas.add(df2.format(testRes.getScgl()) + "");
        datas.add(df3.format(testRes.getGlys()) + "");
        datas.add(df2.format(testRes.getYgdn()) + "");
        datas.add(df2.format(testRes.getWgdn()) + "");
        datas.add(df2.format(testRes.getDwpl()) + "");
        datas.add(df2.format(testRes.getSzgl()) + "");
        datas.add(df3.format(testRes.getFzxs()) + "");
        datas.add(df2.format(testRes.getZhxl()) + "");
        datas.add(df2.format(testRes.getUAB()) + "");
        datas.add(df2.format(testRes.getUBC()) + "");
        datas.add(df2.format(testRes.getUCA()) + "");
        datas.add(df2.format(testRes.getIA()) + "");
        if(testRes.getCsff().equals("单瓦法")){
            datas.add("--");//B项电流
            datas.add("--");//C项电流
        }else if(testRes.getCsff().equals("双瓦法")){
            datas.add("--");//B项电流
            datas.add(df2.format(testRes.getIC()) + "");//C项电流
        }else {//三瓦法
            datas.add(df2.format(testRes.getIB()) + "");
            datas.add(df2.format(testRes.getIC()) + "");
        }
        datas.add(df2.format(testRes.getAyggl() / 1000) + "");
        datas.add(df2.format(testRes.getByggl() / 1000) + "");
        datas.add(df2.format(testRes.getCyggl() / 1000) + "");
        datas.add(df2.format(testRes.getAwggl() / 1000) + "");
        datas.add(df2.format(testRes.getBwggl() / 1000) + "");
        datas.add(df2.format(testRes.getCwggl() / 1000) + "");
        datas.add(df2.format(testRes.getAszgl() / 1000) + "");
        datas.add(df2.format(testRes.getBszgl() / 1000) + "");
        datas.add(df2.format(testRes.getCszgl() / 1000) + "");
        datas.add(df3.format(testRes.getAglys()) + "");
        datas.add(df3.format(testRes.getBglys()) + "");
        datas.add(df3.format(testRes.getCglys()) + "");
        datas.add(df2.format(testRes.getPhUAB()) + "");
        datas.add(df2.format(testRes.getPhUBC()) + "");
        datas.add(df2.format(testRes.getPhUCA()) + "");
        datas.add(df2.format(testRes.getPhUIA()) + "");
        datas.add(df2.format(testRes.getPhUIB()) + "");
        datas.add(df2.format(testRes.getPhUIC()) + "");
        datas.add(df2.format(testRes.getZhglsh()) + "");
        datas.add(df2.format(testRes.getKzwggl()) + "");
        datas.add(df2.format(testRes.getEdfzwggl()) + "");
//        parS.add("有功功率损耗");
//        parS.add("额定负载功率损耗");
//        parS.add("综合消耗功率");
//        parS.add("额定综合消耗功率");
//        parS.add("额定综合效率");
//        parS.add("无功补偿容量");
//        parS.add("无功补偿电容量");

        datas.add(df2.format(testRes.getYgglsh()) + "");
//        datas.add(df2.format(testRes.getEdfzglsh()) + "");//额定负载功率损耗，去掉
        datas.add(df2.format(testRes.getZhxhgl()) + "");
        datas.add(df2.format(testRes.getEdzhxhgl()) + "");
        datas.add(df2.format(testRes.getEdzhxl()) + "");
        datas.add(df2.format(testRes.getWgbcrl()) + "");
        datas.add(df2.format(testRes.getWgbcdrl()) + "");

        String strres = "";
        if (testRes.getZhxl() > testRes.getEdzhxl()) {
            strres = "经济运行";
        } else if (testRes.getZhxl() > testRes.getEdzhxl() * 0.6) {
            strres = "允许运行";

        } else {
            strres = "非经济运行";
        }
        datas.add(strres);
        Log.d("hhj", "setParametersData:xl== " + testRes.getXl());
        datas.add(df2.format(testRes.getXl()) + "");//电机效率

        return datas;


    }

    @Override
    public List<String> setParametersUnit() {
        List<String> units = new ArrayList<>();
        units.add("V");
        units.add("A");
        units.add("kW");
        units.add("kvar");
        units.add("kW");
        units.add(" ");//功率因数
        units.add("kW·h");//有功电能
        units.add("kVar·h");//无功电能
        units.add("Hz");
        units.add("kVA");
        units.add(" ");
        units.add("%");
        units.add("V");
        units.add("V");
        units.add("V");
        units.add("A");
        units.add("A");
        units.add("A");
        units.add("kW");
        units.add("kW");
        units.add("kW");
        units.add("kvar");
        units.add("kvar");
        units.add("kvar");
        units.add("KVA");
        units.add("KVA");
        units.add("KVA");
        units.add(" ");
        units.add(" ");
        units.add(" ");
        units.add("°");
        units.add("°");
        units.add("°");
        units.add("°");
        units.add("°");
        units.add("°");
        units.add("kW");
        units.add("kvar");
        units.add("kvar");
        units.add("kW");
//        units.add("kW"); //额定负载功率损耗，去掉
        units.add("kW");
        units.add("kW");
        units.add("%");
        units.add("kvar");
        units.add("uf");
        units.add(" ");
        units.add("%");


        return units;

    }

}
