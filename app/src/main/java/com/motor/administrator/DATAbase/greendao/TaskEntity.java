package com.motor.administrator.DATAbase.greendao;

import com.xzkydz.function.search.module.ITaskRoot;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TaskEntity implements ITaskRoot {

    @Id(autoincrement = true)
    private Long id;
    //受检单位名称
    private String unitName;
    //编号
    private String gasePumpNumber;
    //测试员
    private String peopleName;
    //测试任务的状态
    private boolean _IsCompleteTask;
    //测试任务已经测得测试数据条数
    private int taskHaveGetData;
    //测试任务的创建时间
    private String greateTaskTime;

    private String csff ; // 测试方法

    private String dlbb1 ; // 电流变比1
    private String dlbb2 ; // 电流变比2
    private String dybb1 ; // 电压变比1
    private String dybb2 ; // 电压变比2
    private String djk1 ; // 电机库1
    private boolean djksfxz1 ; // 电机库是否选择1
    private String djqblc1 ; // 电机钳表量程1
    private String djcsff1 ; // 电机测试方法1
    private String djcdxl1 ; // 电机传动效率1
    private String djcdfs1 ; // 电机传动方式1
    private String djeddy1 ; // 电机额定电压1
    private String djeddl1 ; // 电机额定电流1
    private String djedgl1 ; // 电机额定功率1
    private String djedxl1 ; // 电机额定效率1
    private String djkzdl1 ; // 电机空载电流1
    private String djkzgl1 ; // 电机空载功率1
    private String djjs1 ; // 电机级数1
    private String djwgjjdl1 ; // 电机无功经济当量1



    private String by1 ; // 备用1
    private String by2 ; // 备用2
    private String by3 ; // 备用3
    private String by4 ; // 备用4
    private String by5 ; // 备用5
    private String by6 ; // 备用6
    private String by7 ; // 备用7
    private String by8 ; // 备用8
    private String by9 ; // 备用9
    private String by10 ; // 备用10
    private String by11 ; // 备用11
    private String by12 ; // 备用12
    @Generated(hash = 1655079103)
    public TaskEntity(Long id, String unitName, String gasePumpNumber,
            String peopleName, boolean _IsCompleteTask, int taskHaveGetData,
            String greateTaskTime, String csff, String dlbb1, String dlbb2,
            String dybb1, String dybb2, String djk1, boolean djksfxz1,
            String djqblc1, String djcsff1, String djcdxl1, String djcdfs1,
            String djeddy1, String djeddl1, String djedgl1, String djedxl1,
            String djkzdl1, String djkzgl1, String djjs1, String djwgjjdl1,
            String by1, String by2, String by3, String by4, String by5, String by6,
            String by7, String by8, String by9, String by10, String by11,
            String by12) {
        this.id = id;
        this.unitName = unitName;
        this.gasePumpNumber = gasePumpNumber;
        this.peopleName = peopleName;
        this._IsCompleteTask = _IsCompleteTask;
        this.taskHaveGetData = taskHaveGetData;
        this.greateTaskTime = greateTaskTime;
        this.csff = csff;
        this.dlbb1 = dlbb1;
        this.dlbb2 = dlbb2;
        this.dybb1 = dybb1;
        this.dybb2 = dybb2;
        this.djk1 = djk1;
        this.djksfxz1 = djksfxz1;
        this.djqblc1 = djqblc1;
        this.djcsff1 = djcsff1;
        this.djcdxl1 = djcdxl1;
        this.djcdfs1 = djcdfs1;
        this.djeddy1 = djeddy1;
        this.djeddl1 = djeddl1;
        this.djedgl1 = djedgl1;
        this.djedxl1 = djedxl1;
        this.djkzdl1 = djkzdl1;
        this.djkzgl1 = djkzgl1;
        this.djjs1 = djjs1;
        this.djwgjjdl1 = djwgjjdl1;
        this.by1 = by1;
        this.by2 = by2;
        this.by3 = by3;
        this.by4 = by4;
        this.by5 = by5;
        this.by6 = by6;
        this.by7 = by7;
        this.by8 = by8;
        this.by9 = by9;
        this.by10 = by10;
        this.by11 = by11;
        this.by12 = by12;
    }
    @Generated(hash = 397975341)
    public TaskEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getGasePumpNumber() {
        return this.gasePumpNumber;
    }
    public void setGasePumpNumber(String gasePumpNumber) {
        this.gasePumpNumber = gasePumpNumber;
    }
    public String getPeopleName() {
        return this.peopleName;
    }
    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
    public boolean get_IsCompleteTask() {
        return this._IsCompleteTask;
    }
    public void set_IsCompleteTask(boolean _IsCompleteTask) {
        this._IsCompleteTask = _IsCompleteTask;
    }
    public int getTaskHaveGetData() {
        return this.taskHaveGetData;
    }
    public void setTaskHaveGetData(int taskHaveGetData) {
        this.taskHaveGetData = taskHaveGetData;
    }
    public String getGreateTaskTime() {
        return this.greateTaskTime;
    }
    public void setGreateTaskTime(String greateTaskTime) {
        this.greateTaskTime = greateTaskTime;
    }
    public String getCsff() {
        return this.csff;
    }
    public void setCsff(String csff) {
        this.csff = csff;
    }
    public String getDlbb1() {
        return this.dlbb1;
    }
    public void setDlbb1(String dlbb1) {
        this.dlbb1 = dlbb1;
    }
    public String getDlbb2() {
        return this.dlbb2;
    }
    public void setDlbb2(String dlbb2) {
        this.dlbb2 = dlbb2;
    }
    public String getDybb1() {
        return this.dybb1;
    }
    public void setDybb1(String dybb1) {
        this.dybb1 = dybb1;
    }
    public String getDybb2() {
        return this.dybb2;
    }
    public void setDybb2(String dybb2) {
        this.dybb2 = dybb2;
    }
    public String getDjk1() {
        return this.djk1;
    }
    public void setDjk1(String djk1) {
        this.djk1 = djk1;
    }
    public boolean getDjksfxz1() {
        return this.djksfxz1;
    }
    public void setDjksfxz1(boolean djksfxz1) {
        this.djksfxz1 = djksfxz1;
    }
    public String getDjqblc1() {
        return this.djqblc1;
    }
    public void setDjqblc1(String djqblc1) {
        this.djqblc1 = djqblc1;
    }
    public String getDjcsff1() {
        return this.djcsff1;
    }
    public void setDjcsff1(String djcsff1) {
        this.djcsff1 = djcsff1;
    }
    public String getDjcdxl1() {
        return this.djcdxl1;
    }
    public void setDjcdxl1(String djcdxl1) {
        this.djcdxl1 = djcdxl1;
    }
    public String getDjcdfs1() {
        return this.djcdfs1;
    }
    public void setDjcdfs1(String djcdfs1) {
        this.djcdfs1 = djcdfs1;
    }
    public String getDjeddy1() {
        return this.djeddy1;
    }
    public void setDjeddy1(String djeddy1) {
        this.djeddy1 = djeddy1;
    }
    public String getDjeddl1() {
        return this.djeddl1;
    }
    public void setDjeddl1(String djeddl1) {
        this.djeddl1 = djeddl1;
    }
    public String getDjedgl1() {
        return this.djedgl1;
    }
    public void setDjedgl1(String djedgl1) {
        this.djedgl1 = djedgl1;
    }
    public String getDjedxl1() {
        return this.djedxl1;
    }
    public void setDjedxl1(String djedxl1) {
        this.djedxl1 = djedxl1;
    }
    public String getDjkzdl1() {
        return this.djkzdl1;
    }
    public void setDjkzdl1(String djkzdl1) {
        this.djkzdl1 = djkzdl1;
    }
    public String getDjkzgl1() {
        return this.djkzgl1;
    }
    public void setDjkzgl1(String djkzgl1) {
        this.djkzgl1 = djkzgl1;
    }
    public String getDjjs1() {
        return this.djjs1;
    }
    public void setDjjs1(String djjs1) {
        this.djjs1 = djjs1;
    }
    public String getDjwgjjdl1() {
        return this.djwgjjdl1;
    }
    public void setDjwgjjdl1(String djwgjjdl1) {
        this.djwgjjdl1 = djwgjjdl1;
    }
    public String getBy1() {
        return this.by1;
    }
    public void setBy1(String by1) {
        this.by1 = by1;
    }
    public String getBy2() {
        return this.by2;
    }
    public void setBy2(String by2) {
        this.by2 = by2;
    }
    public String getBy3() {
        return this.by3;
    }
    public void setBy3(String by3) {
        this.by3 = by3;
    }
    public String getBy4() {
        return this.by4;
    }
    public void setBy4(String by4) {
        this.by4 = by4;
    }
    public String getBy5() {
        return this.by5;
    }
    public void setBy5(String by5) {
        this.by5 = by5;
    }
    public String getBy6() {
        return this.by6;
    }
    public void setBy6(String by6) {
        this.by6 = by6;
    }
    public String getBy7() {
        return this.by7;
    }
    public void setBy7(String by7) {
        this.by7 = by7;
    }
    public String getBy8() {
        return this.by8;
    }
    public void setBy8(String by8) {
        this.by8 = by8;
    }
    public String getBy9() {
        return this.by9;
    }
    public void setBy9(String by9) {
        this.by9 = by9;
    }
    public String getBy10() {
        return this.by10;
    }
    public void setBy10(String by10) {
        this.by10 = by10;
    }
    public String getBy11() {
        return this.by11;
    }
    public void setBy11(String by11) {
        this.by11 = by11;
    }
    public String getBy12() {
        return this.by12;
    }
    public void setBy12(String by12) {
        this.by12 = by12;
    }

    public Long getTaskId() {
        return id;
    }

    @Override
    public String getTestFunction() {
        return csff;
    }
    @Override
    public String getNumber() {
        return gasePumpNumber;
    }
    @Override
    public boolean getTaskStatue() {
        return _IsCompleteTask;
    }
}
