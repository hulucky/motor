package com.motor.param;


import com.motor.administrator.DATAbase.greendao.MotorBean;
import com.motor.administrator.DATAbase.greendao.MotorEnity;
import com.motor.app.MyApp;
import com.xzkydz.function.motor.module.IMotorBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MotorSelectorActivity extends com.xzkydz.function.motor.view.MotorSelectorActivity {

    MyApp myApp = MyApp.getInstance();
    private List<IMotorBean> iMotorBeans;

    @Override
    public List<IMotorBean> getMotorList() {
        iMotorBeans = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MotorEnity> motorBeanList = myApp.getDaoInstant().getMotorEnityDao().loadAll();
                Collections.reverse(motorBeanList);
                iMotorBeans.addAll(motorBeanList);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        initListView(iMotorBeans);
                    }
                });
            }
        }).start();

        return iMotorBeans;
    }


    @Override
    public void insertNewMotor(final com.xzkydz.function.transform.MotorBean motorBean) {
        final MotorEnity motorBean1 = new MotorEnity();
        motorBean1.setDJ_LIB_NAME(motorBean.getDJ_LIB_NAME());
        motorBean1.setEDDY(motorBean.getEDDY());
        motorBean1.setEDDL(motorBean.getEDDL());
        motorBean1.setEDGL(motorBean.getEDGL());
        motorBean1.setEDXL(motorBean.getEDXL());
        motorBean1.setJS(motorBean.getJS());
        motorBean1.setKZDL(motorBean.getKZDL());
        motorBean1.setKZGL(motorBean.getKZGL());
        motorBean1.setEDGLYS(motorBean.getEDGLYS());
        motorBean1.setWGJJDL(motorBean.getWGJJDL());
        motorBean1.setIsAdd(true);
        motorBean1.setDJXL("新创建");
        iMotorBeans.add(0, motorBean1);
        setMotorAllList(iMotorBeans);
        initListView(iMotorBeans);

        new Thread(new Runnable() {
            @Override
            public void run() {
                myApp.getDaoInstant().getMotorEnityDao().insert(motorBean1);
            }
        }).start();
    }

    @Override
    public void deleteMotor(final IMotorBean iMotorBean) {
        iMotorBeans.remove(iMotorBean);
        setMotorAllList(iMotorBeans);
        initListView(iMotorBeans);
        new Thread(new Runnable() {
            @Override
            public void run() {
                myApp.getDaoInstant().getMotorEnityDao().deleteByKey(iMotorBean.getId());
            }
        }).start();
    }
}
