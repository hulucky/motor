package com.motor.administrator.DATAbase.greendao;

import com.greendao.manager.motorData;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class TaskResEnity {

    //    @Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
    //    @Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
    //    @Property：可以自定义字段名，注意外键不能使用该属性
    //    @NotNull：属性不能为空
    //    @Transient：使用该注释的属性不会被存入数据库的字段中
    //    @Unique：该属性值必须在数据库中是唯一值
    //    @Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改

    //不能用int
    @Id(autoincrement = true)
    private Long id;

    private Long taskId;
    private String csff; // 测试方法

    private int saveIndex;
    private String SaveTime;
    private double pjdy = 0.0d;//平均电压
    private double pjdl = 0.0d;//平均电流
    private double yggl = 0.0d;//有功功率
    private double wggl = 0.0d;//无功功率
    private double scgl = 0.0d;//输出功率
    private double glys = 0.0d;//功率因数
    private double ygdn = 0.0d;//有功电能
    private double wgdn = 0.0d;//无功电能
    private double dwpl = 0.0d;//电网频率
    private double szgl = 0.0d;//视在功率
    private double fzxs = 0.0d;//负载系数

    private double zhxl = 0.0d;//综合效率
    private double UAB = 0.0d;//AB线电压
    private double UBC = 0.0d;//BC线电压
    private double UCA = 0.0d;//CA线电压


    private double pjxdy = 0.0d;//平均线电压
    private double UA = 0.0d;//A相相电压
    private double UB = 0.0d;//B相相电压
    private double UC = 0.0d;//C相相电压
    private double IA = 0.0d;//A相电流
    private double IB = 0.0d;//B相电流
    private double IC = 0.0d;//C相电流
    private double Ayggl = 0.0d;//A相有功功率
    private double Byggl = 0.0d;//B相有功功率
    private double Cyggl = 0.0d;//C相有功功率
    private double Awggl = 0.0d;//A相无功功率
    private double Bwggl = 0.0d;//B相无功功率
    private double Cwggl = 0.0d;//C相无功功率
    private double Aszgl = 0.0d;//A相视在功率
    private double Bszgl = 0.0d;//B相视在功率
    private double Cszgl = 0.0d;//C相视在功率
    private double Aglys = 0.0d;//A相功率因数
    private double Bglys = 0.0d;//B相功率因数
    private double Cglys = 0.0d;//C相功率因数
    private double phUAB = 0.0d;//AB相电压相位角
    private double phUBC = 0.0d;//BC相电压相位角
    private double phUCA = 0.0d;//CA相电压相位角
    private double phUIA = 0.0d;//A相电压电流相位角
    private double phUIB = 0.0d;//B相电压电流相位角
    private double phUIC = 0.0d;//C相电压电流相位角
    private double zhglsh = 0.0d;//综合功率损耗
    private double kzwggl = 0.0d;//空载无功功率
    private double edfzwggl = 0.0d;//额定负载无功功率
    private double ygglsh = 0.0d;//有功功率损耗
    private double edfzglsh = 0.0d;//额定负载功率损耗
    private double zhxhgl = 0.0d;//综合消耗功率
    private double edzhxhgl = 0.0d;//额定综合消耗功率


    private double edzhglsh = 0.0d;//额定综合功率损耗
    private double edzhxl = 0.0d;//额定综合效率
    private double wgbcrl = 0.0d;//无功补偿容量
    private double wgbcdrl = 0.0d;//无功补偿电容量
    private double eddy = 0.0d;//额定电压
    private double eddl = 0.0d;//额定电流
    private double edxl = 0.0d;//额定效率
    private double edgl = 0.0d;//额定功率
    private double kzgl = 0.0d;//空载功率
    private double kzdl = 0.0d;//空载电流
    private double edglys = 0.0d;//额定功率因数
    private double wgjjdl = 0.0d;//无功经济当量
    private double djyxzt = 0.0d;//电机运行状态
    private double dypc = 0.0d;//电源电压与额定电压偏差
    private double sxbphd = 0.0d;//三相电压不平衡度
    private double mbglys = 0.0d;//目标功率因数

    private double wd = 0.0d;//温度
    private double sd = 0.0d;//湿度
    private double dqy = 0.0d;//大气压
    private double date = 0.0d;//时间
    private double savedate = 0.0d;//保存时间
    private int js = 0;//级数


    private double lxdl = 0.0d;//零序电流
    private double KUA = 0.0d;
    private double KUB = 0.0d;
    private double KUC = 0.0d;
    private double KIA = 0.0d;
    private double KIB = 0.0d;
    private double KIC = 0.0d;


    private double HCUA = 0.0d;
    private double HCUB = 0.0d;
    private double HCUC = 0.0d;
    private double HCIA = 0.0d;
    private double HCIB = 0.0d;
    private double HCIC = 0.0d;


    private double by1 = 0.0d;//备用1
    private double by2 = 0.0d;//备用2
    private double by3 = 0.0d;//备用3
    private double by4 = 0.0d;//备用4
    private double by5 = 0.0d;//备用5
    private double by6 = 0.0d;//备用6
    private double by7 = 0.0d;//备用7
    private double by8 = 0.0d;//备用8
    private double by9 = 0.0d;//备用9
    private double by10 = 0.0d;//备用10

    private double dybb = 1d;//电压变比
    private double dlbb = 1d;//电流变比\
    private int method = 2;//测试方法 0=单瓦，1=双瓦，2=三瓦
    private double xl = 0.0d;//效率
    @Generated(hash = 260407414)
    public TaskResEnity(Long id, Long taskId, String csff, int saveIndex, String SaveTime,
            double pjdy, double pjdl, double yggl, double wggl, double scgl, double glys,
            double ygdn, double wgdn, double dwpl, double szgl, double fzxs, double zhxl,
            double UAB, double UBC, double UCA, double pjxdy, double UA, double UB, double UC,
            double IA, double IB, double IC, double Ayggl, double Byggl, double Cyggl,
            double Awggl, double Bwggl, double Cwggl, double Aszgl, double Bszgl,
            double Cszgl, double Aglys, double Bglys, double Cglys, double phUAB,
            double phUBC, double phUCA, double phUIA, double phUIB, double phUIC,
            double zhglsh, double kzwggl, double edfzwggl, double ygglsh, double edfzglsh,
            double zhxhgl, double edzhxhgl, double edzhglsh, double edzhxl, double wgbcrl,
            double wgbcdrl, double eddy, double eddl, double edxl, double edgl, double kzgl,
            double kzdl, double edglys, double wgjjdl, double djyxzt, double dypc,
            double sxbphd, double mbglys, double wd, double sd, double dqy, double date,
            double savedate, int js, double lxdl, double KUA, double KUB, double KUC,
            double KIA, double KIB, double KIC, double HCUA, double HCUB, double HCUC,
            double HCIA, double HCIB, double HCIC, double by1, double by2, double by3,
            double by4, double by5, double by6, double by7, double by8, double by9,
            double by10, double dybb, double dlbb, int method, double xl) {
        this.id = id;
        this.taskId = taskId;
        this.csff = csff;
        this.saveIndex = saveIndex;
        this.SaveTime = SaveTime;
        this.pjdy = pjdy;
        this.pjdl = pjdl;
        this.yggl = yggl;
        this.wggl = wggl;
        this.scgl = scgl;
        this.glys = glys;
        this.ygdn = ygdn;
        this.wgdn = wgdn;
        this.dwpl = dwpl;
        this.szgl = szgl;
        this.fzxs = fzxs;
        this.zhxl = zhxl;
        this.UAB = UAB;
        this.UBC = UBC;
        this.UCA = UCA;
        this.pjxdy = pjxdy;
        this.UA = UA;
        this.UB = UB;
        this.UC = UC;
        this.IA = IA;
        this.IB = IB;
        this.IC = IC;
        this.Ayggl = Ayggl;
        this.Byggl = Byggl;
        this.Cyggl = Cyggl;
        this.Awggl = Awggl;
        this.Bwggl = Bwggl;
        this.Cwggl = Cwggl;
        this.Aszgl = Aszgl;
        this.Bszgl = Bszgl;
        this.Cszgl = Cszgl;
        this.Aglys = Aglys;
        this.Bglys = Bglys;
        this.Cglys = Cglys;
        this.phUAB = phUAB;
        this.phUBC = phUBC;
        this.phUCA = phUCA;
        this.phUIA = phUIA;
        this.phUIB = phUIB;
        this.phUIC = phUIC;
        this.zhglsh = zhglsh;
        this.kzwggl = kzwggl;
        this.edfzwggl = edfzwggl;
        this.ygglsh = ygglsh;
        this.edfzglsh = edfzglsh;
        this.zhxhgl = zhxhgl;
        this.edzhxhgl = edzhxhgl;
        this.edzhglsh = edzhglsh;
        this.edzhxl = edzhxl;
        this.wgbcrl = wgbcrl;
        this.wgbcdrl = wgbcdrl;
        this.eddy = eddy;
        this.eddl = eddl;
        this.edxl = edxl;
        this.edgl = edgl;
        this.kzgl = kzgl;
        this.kzdl = kzdl;
        this.edglys = edglys;
        this.wgjjdl = wgjjdl;
        this.djyxzt = djyxzt;
        this.dypc = dypc;
        this.sxbphd = sxbphd;
        this.mbglys = mbglys;
        this.wd = wd;
        this.sd = sd;
        this.dqy = dqy;
        this.date = date;
        this.savedate = savedate;
        this.js = js;
        this.lxdl = lxdl;
        this.KUA = KUA;
        this.KUB = KUB;
        this.KUC = KUC;
        this.KIA = KIA;
        this.KIB = KIB;
        this.KIC = KIC;
        this.HCUA = HCUA;
        this.HCUB = HCUB;
        this.HCUC = HCUC;
        this.HCIA = HCIA;
        this.HCIB = HCIB;
        this.HCIC = HCIC;
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
        this.dybb = dybb;
        this.dlbb = dlbb;
        this.method = method;
        this.xl = xl;
    }
    @Generated(hash = 131024579)
    public TaskResEnity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTaskId() {
        return this.taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public String getCsff() {
        return this.csff;
    }
    public void setCsff(String csff) {
        this.csff = csff;
    }
    public int getSaveIndex() {
        return this.saveIndex;
    }
    public void setSaveIndex(int saveIndex) {
        this.saveIndex = saveIndex;
    }
    public String getSaveTime() {
        return this.SaveTime;
    }
    public void setSaveTime(String SaveTime) {
        this.SaveTime = SaveTime;
    }
    public double getPjdy() {
        return this.pjdy;
    }
    public void setPjdy(double pjdy) {
        this.pjdy = pjdy;
    }
    public double getPjdl() {
        return this.pjdl;
    }
    public void setPjdl(double pjdl) {
        this.pjdl = pjdl;
    }
    public double getYggl() {
        return this.yggl;
    }
    public void setYggl(double yggl) {
        this.yggl = yggl;
    }
    public double getWggl() {
        return this.wggl;
    }
    public void setWggl(double wggl) {
        this.wggl = wggl;
    }
    public double getScgl() {
        return this.scgl;
    }
    public void setScgl(double scgl) {
        this.scgl = scgl;
    }
    public double getGlys() {
        return this.glys;
    }
    public void setGlys(double glys) {
        this.glys = glys;
    }
    public double getYgdn() {
        return this.ygdn;
    }
    public void setYgdn(double ygdn) {
        this.ygdn = ygdn;
    }
    public double getWgdn() {
        return this.wgdn;
    }
    public void setWgdn(double wgdn) {
        this.wgdn = wgdn;
    }
    public double getDwpl() {
        return this.dwpl;
    }
    public void setDwpl(double dwpl) {
        this.dwpl = dwpl;
    }
    public double getSzgl() {
        return this.szgl;
    }
    public void setSzgl(double szgl) {
        this.szgl = szgl;
    }
    public double getFzxs() {
        return this.fzxs;
    }
    public void setFzxs(double fzxs) {
        this.fzxs = fzxs;
    }
    public double getZhxl() {
        return this.zhxl;
    }
    public void setZhxl(double zhxl) {
        this.zhxl = zhxl;
    }
    public double getUAB() {
        return this.UAB;
    }
    public void setUAB(double UAB) {
        this.UAB = UAB;
    }
    public double getUBC() {
        return this.UBC;
    }
    public void setUBC(double UBC) {
        this.UBC = UBC;
    }
    public double getUCA() {
        return this.UCA;
    }
    public void setUCA(double UCA) {
        this.UCA = UCA;
    }
    public double getPjxdy() {
        return this.pjxdy;
    }
    public void setPjxdy(double pjxdy) {
        this.pjxdy = pjxdy;
    }
    public double getUA() {
        return this.UA;
    }
    public void setUA(double UA) {
        this.UA = UA;
    }
    public double getUB() {
        return this.UB;
    }
    public void setUB(double UB) {
        this.UB = UB;
    }
    public double getUC() {
        return this.UC;
    }
    public void setUC(double UC) {
        this.UC = UC;
    }
    public double getIA() {
        return this.IA;
    }
    public void setIA(double IA) {
        this.IA = IA;
    }
    public double getIB() {
        return this.IB;
    }
    public void setIB(double IB) {
        this.IB = IB;
    }
    public double getIC() {
        return this.IC;
    }
    public void setIC(double IC) {
        this.IC = IC;
    }
    public double getAyggl() {
        return this.Ayggl;
    }
    public void setAyggl(double Ayggl) {
        this.Ayggl = Ayggl;
    }
    public double getByggl() {
        return this.Byggl;
    }
    public void setByggl(double Byggl) {
        this.Byggl = Byggl;
    }
    public double getCyggl() {
        return this.Cyggl;
    }
    public void setCyggl(double Cyggl) {
        this.Cyggl = Cyggl;
    }
    public double getAwggl() {
        return this.Awggl;
    }
    public void setAwggl(double Awggl) {
        this.Awggl = Awggl;
    }
    public double getBwggl() {
        return this.Bwggl;
    }
    public void setBwggl(double Bwggl) {
        this.Bwggl = Bwggl;
    }
    public double getCwggl() {
        return this.Cwggl;
    }
    public void setCwggl(double Cwggl) {
        this.Cwggl = Cwggl;
    }
    public double getAszgl() {
        return this.Aszgl;
    }
    public void setAszgl(double Aszgl) {
        this.Aszgl = Aszgl;
    }
    public double getBszgl() {
        return this.Bszgl;
    }
    public void setBszgl(double Bszgl) {
        this.Bszgl = Bszgl;
    }
    public double getCszgl() {
        return this.Cszgl;
    }
    public void setCszgl(double Cszgl) {
        this.Cszgl = Cszgl;
    }
    public double getAglys() {
        return this.Aglys;
    }
    public void setAglys(double Aglys) {
        this.Aglys = Aglys;
    }
    public double getBglys() {
        return this.Bglys;
    }
    public void setBglys(double Bglys) {
        this.Bglys = Bglys;
    }
    public double getCglys() {
        return this.Cglys;
    }
    public void setCglys(double Cglys) {
        this.Cglys = Cglys;
    }
    public double getPhUAB() {
        return this.phUAB;
    }
    public void setPhUAB(double phUAB) {
        this.phUAB = phUAB;
    }
    public double getPhUBC() {
        return this.phUBC;
    }
    public void setPhUBC(double phUBC) {
        this.phUBC = phUBC;
    }
    public double getPhUCA() {
        return this.phUCA;
    }
    public void setPhUCA(double phUCA) {
        this.phUCA = phUCA;
    }
    public double getPhUIA() {
        return this.phUIA;
    }
    public void setPhUIA(double phUIA) {
        this.phUIA = phUIA;
    }
    public double getPhUIB() {
        return this.phUIB;
    }
    public void setPhUIB(double phUIB) {
        this.phUIB = phUIB;
    }
    public double getPhUIC() {
        return this.phUIC;
    }
    public void setPhUIC(double phUIC) {
        this.phUIC = phUIC;
    }
    public double getZhglsh() {
        return this.zhglsh;
    }
    public void setZhglsh(double zhglsh) {
        this.zhglsh = zhglsh;
    }
    public double getKzwggl() {
        return this.kzwggl;
    }
    public void setKzwggl(double kzwggl) {
        this.kzwggl = kzwggl;
    }
    public double getEdfzwggl() {
        return this.edfzwggl;
    }
    public void setEdfzwggl(double edfzwggl) {
        this.edfzwggl = edfzwggl;
    }
    public double getYgglsh() {
        return this.ygglsh;
    }
    public void setYgglsh(double ygglsh) {
        this.ygglsh = ygglsh;
    }
    public double getEdfzglsh() {
        return this.edfzglsh;
    }
    public void setEdfzglsh(double edfzglsh) {
        this.edfzglsh = edfzglsh;
    }
    public double getZhxhgl() {
        return this.zhxhgl;
    }
    public void setZhxhgl(double zhxhgl) {
        this.zhxhgl = zhxhgl;
    }
    public double getEdzhxhgl() {
        return this.edzhxhgl;
    }
    public void setEdzhxhgl(double edzhxhgl) {
        this.edzhxhgl = edzhxhgl;
    }
    public double getEdzhglsh() {
        return this.edzhglsh;
    }
    public void setEdzhglsh(double edzhglsh) {
        this.edzhglsh = edzhglsh;
    }
    public double getEdzhxl() {
        return this.edzhxl;
    }
    public void setEdzhxl(double edzhxl) {
        this.edzhxl = edzhxl;
    }
    public double getWgbcrl() {
        return this.wgbcrl;
    }
    public void setWgbcrl(double wgbcrl) {
        this.wgbcrl = wgbcrl;
    }
    public double getWgbcdrl() {
        return this.wgbcdrl;
    }
    public void setWgbcdrl(double wgbcdrl) {
        this.wgbcdrl = wgbcdrl;
    }
    public double getEddy() {
        return this.eddy;
    }
    public void setEddy(double eddy) {
        this.eddy = eddy;
    }
    public double getEddl() {
        return this.eddl;
    }
    public void setEddl(double eddl) {
        this.eddl = eddl;
    }
    public double getEdxl() {
        return this.edxl;
    }
    public void setEdxl(double edxl) {
        this.edxl = edxl;
    }
    public double getEdgl() {
        return this.edgl;
    }
    public void setEdgl(double edgl) {
        this.edgl = edgl;
    }
    public double getKzgl() {
        return this.kzgl;
    }
    public void setKzgl(double kzgl) {
        this.kzgl = kzgl;
    }
    public double getKzdl() {
        return this.kzdl;
    }
    public void setKzdl(double kzdl) {
        this.kzdl = kzdl;
    }
    public double getEdglys() {
        return this.edglys;
    }
    public void setEdglys(double edglys) {
        this.edglys = edglys;
    }
    public double getWgjjdl() {
        return this.wgjjdl;
    }
    public void setWgjjdl(double wgjjdl) {
        this.wgjjdl = wgjjdl;
    }
    public double getDjyxzt() {
        return this.djyxzt;
    }
    public void setDjyxzt(double djyxzt) {
        this.djyxzt = djyxzt;
    }
    public double getDypc() {
        return this.dypc;
    }
    public void setDypc(double dypc) {
        this.dypc = dypc;
    }
    public double getSxbphd() {
        return this.sxbphd;
    }
    public void setSxbphd(double sxbphd) {
        this.sxbphd = sxbphd;
    }
    public double getMbglys() {
        return this.mbglys;
    }
    public void setMbglys(double mbglys) {
        this.mbglys = mbglys;
    }
    public double getWd() {
        return this.wd;
    }
    public void setWd(double wd) {
        this.wd = wd;
    }
    public double getSd() {
        return this.sd;
    }
    public void setSd(double sd) {
        this.sd = sd;
    }
    public double getDqy() {
        return this.dqy;
    }
    public void setDqy(double dqy) {
        this.dqy = dqy;
    }
    public double getDate() {
        return this.date;
    }
    public void setDate(double date) {
        this.date = date;
    }
    public double getSavedate() {
        return this.savedate;
    }
    public void setSavedate(double savedate) {
        this.savedate = savedate;
    }
    public int getJs() {
        return this.js;
    }
    public void setJs(int js) {
        this.js = js;
    }
    public double getLxdl() {
        return this.lxdl;
    }
    public void setLxdl(double lxdl) {
        this.lxdl = lxdl;
    }
    public double getKUA() {
        return this.KUA;
    }
    public void setKUA(double KUA) {
        this.KUA = KUA;
    }
    public double getKUB() {
        return this.KUB;
    }
    public void setKUB(double KUB) {
        this.KUB = KUB;
    }
    public double getKUC() {
        return this.KUC;
    }
    public void setKUC(double KUC) {
        this.KUC = KUC;
    }
    public double getKIA() {
        return this.KIA;
    }
    public void setKIA(double KIA) {
        this.KIA = KIA;
    }
    public double getKIB() {
        return this.KIB;
    }
    public void setKIB(double KIB) {
        this.KIB = KIB;
    }
    public double getKIC() {
        return this.KIC;
    }
    public void setKIC(double KIC) {
        this.KIC = KIC;
    }
    public double getHCUA() {
        return this.HCUA;
    }
    public void setHCUA(double HCUA) {
        this.HCUA = HCUA;
    }
    public double getHCUB() {
        return this.HCUB;
    }
    public void setHCUB(double HCUB) {
        this.HCUB = HCUB;
    }
    public double getHCUC() {
        return this.HCUC;
    }
    public void setHCUC(double HCUC) {
        this.HCUC = HCUC;
    }
    public double getHCIA() {
        return this.HCIA;
    }
    public void setHCIA(double HCIA) {
        this.HCIA = HCIA;
    }
    public double getHCIB() {
        return this.HCIB;
    }
    public void setHCIB(double HCIB) {
        this.HCIB = HCIB;
    }
    public double getHCIC() {
        return this.HCIC;
    }
    public void setHCIC(double HCIC) {
        this.HCIC = HCIC;
    }
    public double getBy1() {
        return this.by1;
    }
    public void setBy1(double by1) {
        this.by1 = by1;
    }
    public double getBy2() {
        return this.by2;
    }
    public void setBy2(double by2) {
        this.by2 = by2;
    }
    public double getBy3() {
        return this.by3;
    }
    public void setBy3(double by3) {
        this.by3 = by3;
    }
    public double getBy4() {
        return this.by4;
    }
    public void setBy4(double by4) {
        this.by4 = by4;
    }
    public double getBy5() {
        return this.by5;
    }
    public void setBy5(double by5) {
        this.by5 = by5;
    }
    public double getBy6() {
        return this.by6;
    }
    public void setBy6(double by6) {
        this.by6 = by6;
    }
    public double getBy7() {
        return this.by7;
    }
    public void setBy7(double by7) {
        this.by7 = by7;
    }
    public double getBy8() {
        return this.by8;
    }
    public void setBy8(double by8) {
        this.by8 = by8;
    }
    public double getBy9() {
        return this.by9;
    }
    public void setBy9(double by9) {
        this.by9 = by9;
    }
    public double getBy10() {
        return this.by10;
    }
    public void setBy10(double by10) {
        this.by10 = by10;
    }
    public double getDybb() {
        return this.dybb;
    }
    public void setDybb(double dybb) {
        this.dybb = dybb;
    }
    public double getDlbb() {
        return this.dlbb;
    }
    public void setDlbb(double dlbb) {
        this.dlbb = dlbb;
    }
    public int getMethod() {
        return this.method;
    }
    public void setMethod(int method) {
        this.method = method;
    }
    public double getXl() {
        return this.xl;
    }
    public void setXl(double xl) {
        this.xl = xl;
    }



 }
