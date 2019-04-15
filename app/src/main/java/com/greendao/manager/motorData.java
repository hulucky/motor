package com.greendao.manager;

import com.motor.administrator.DATAbase.greendao.TaskEntity;

import java.text.DecimalFormat;

import static java.lang.Math.acos;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

/**
 * Created by lish on 2017/10/17.
 */

public class motorData {


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
    private String method = "";//测试方法 0=单瓦，1=双瓦，2=三瓦
    public double getPjxdy() {
        return pjxdy;
    }

    public void setPjxdy(double pjxdy) {
        this.pjxdy = pjxdy;
    }

    private double xl = 0.0d;//效率

    public double getDybb() {
        return dybb;
    }

    public void setDybb(double dybb) {
        this.dybb = dybb;
    }

    public double getDlbb() {
        return dlbb;
    }

    public void setDlbb(double dlbb) {
        this.dlbb = dlbb;
    }

    public int getMethod() {
        int res=0;
        switch (method) {
            case "单瓦法":

                res=0;
            break;
            case "双瓦法":

                res= 1;
            break;
            case "三瓦法":

                res= 2;
            break;
        }
        return res;
    }

    public void setMethod(int intmethod) {

        switch (intmethod) {
            case 0:

           method ="单瓦法" ;
            break;
            case 1:

                method ="双瓦法";
            break;
            case 2:method="三瓦法";

            break;
        }

    }

    public double getfzxs() {
        return fzxs;
    }

    public void setfzxs(double fzxs) {
        this.fzxs = fzxs;
    }

    public double getXl() {
        return xl;
    }

    public void setXl(double xl) {
        this.xl = xl;
    }

    public void Calculate() {
        DecimalFormat df2 = new DecimalFormat("#.00");
        DecimalFormat df3 = new DecimalFormat("#.000");
        DecimalFormat df4 = new DecimalFormat("#.0000");
        DecimalFormat df5 = new DecimalFormat("#.00000");
        DecimalFormat df6 = new DecimalFormat("#.000000");


        //
        if (method .equals( "单瓦法") ){
            pjdy = Math.max(UA, Math.max(UB, UC));
            pjdl = Math.max(IA, Math.max(IB, IC));
            pjxdy = Math.max(UAB, Math.max(UBC, UCA));
            szgl=Float.parseFloat(df2.format(pjdy*pjdl*3));
            yggl=Float.parseFloat(df2.format(szgl*glys));
            wggl=Float.parseFloat(df2.format(Math.sqrt(Math.pow(szgl,2)-Math.pow(yggl,2))));
        } else if (method .equals( "双瓦法")) {
            pjdy = (UA + UB + UC) / 3;
            pjdl = (IA + IC) / 2;
            pjxdy = (UAB + UBC + UCA) / 3;
            szgl=Float.parseFloat(df2.format((UA*IA+UC*IC)*1.5));
            yggl=Float.parseFloat(df2.format(szgl*glys));
            wggl=Float.parseFloat(df2.format(Math.sqrt(Math.pow(szgl,2)-Math.pow(yggl,2))));
        } else if (method .equals( "三瓦法")) {
            pjdy = (UA + UB + UC) / 3;
            pjdl = (IA + IB + IC) / 3;

            pjxdy = (UAB + UBC + UCA) / 3;
            szgl=Float.parseFloat(df2.format(UA*IA+UB*IB+UC*IC));
            yggl=Float.parseFloat(df2.format(szgl*glys));
            wggl=Float.parseFloat(df2.format(Math.sqrt(Math.pow(szgl,2)-Math.pow(yggl,2))));
        }
        szgl=szgl/1000;
        yggl=yggl/1000;
        wggl=wggl/1000;


        double tmpA = Double.parseDouble(df2.format((1 / (edxl / 100) - 1) * edgl - kzgl));
        double tmpB = Double.parseDouble(df2.format(tmpA * (yggl - kzgl)));
        double tmpC = Double.parseDouble(df2.format(pow(edgl, 2) / 4 + tmpB));
        //负载率
        if (edxl == 0 || tmpA == 0 || tmpC < 0) {
            fzxs = 0;
        } else {
            fzxs = Double.parseDouble(df6.format((sqrt(tmpC) - edgl / 2) / tmpA));
        }
        //输出功率
        scgl = Double.parseDouble(df4.format(fzxs * edgl));
        //有功损耗
        if (edxl == 0) {
            ygglsh = 0.00f;
        } else {
            ygglsh = Double.parseDouble(df2.format(kzgl + fzxs * fzxs * tmpA));
        }
        //效率
        if (yggl == 0) {
            xl = 0.00f;
        } else {
            xl = Double.parseDouble(df4.format(scgl / (yggl)));
        }
        xl = xl * 100;

        //  Q1空载无功功率
        // if ((3 * ArrData[5] * ArrData[5] * kzdl * kzdl / 1000000 - kzgl * kzgl) > 0)
        //{
        // kzwggl = Math.round(Math.sqrt((3 * ArrData[5] * ArrData[5] * kzdl * kzdl / 1000000 - kzgl * kzgl)), 2);

        //}
        if ((3 * eddy * eddy * kzdl * kzdl / 1000000 - kzgl * kzgl) > 0) {
            kzwggl = Double.parseDouble(df2.format(sqrt((3 * eddy * eddy * kzdl * kzdl / 1000000 - kzgl * kzgl))));
        } else {
            kzwggl = 0.00f;
        }
        // Q2额定负载无功功率
        if (edxl == 0) {
            //|| edglys == 0 || (1 - pow(edglys, 2)) < 0) {
            edfzwggl = 0.00f;
        } else {
            //edfzwggl = Math.round(edgl / (edxl / 100) * (Math.sqrt(1 - edglys * edglys) / edglys), 2);
            edfzwggl = Double.parseDouble(df6.format(edgl / (edxl / 100000) / sqrt(3) / eddl / eddy));
            edfzwggl = Double.parseDouble(df4.format(acos(edfzwggl)));
            edfzwggl = Double.parseDouble(df2.format(edgl / (edxl / 100) * (tan(edfzwggl))));
        }
        //综合功率损耗
        zhglsh = Double.parseDouble(df2.format(kzgl + fzxs * fzxs * tmpA + wgjjdl * (kzwggl + (fzxs * fzxs) * (edfzwggl - kzwggl))));
        //额定综合功率损耗
        edzhglsh = (1 / (edxl / 100) - 1) * edgl + edfzwggl * wgjjdl;

        //综合效率
        if ((zhglsh + scgl) == 0) {
            zhxl = 0.00f;
        } else {
            zhxl = Double.parseDouble(df5.format(scgl / (zhglsh + scgl)));
        }

        zhxl = zhxl * 100;
        if (xl > 100 || xl < 0) {
            xl = 0.00f;
        }
        if (zhxl > 100 || zhxl < 0) {
            zhxl = 0.00f;
        }

        ////            综合消耗功率 =负载率*额定功率+综合功率损耗
        zhxhgl = Double.parseDouble(df2.format(fzxs * edgl + zhglsh));
        ////额定综合消耗功率=额定功率+（1/额定效率-1）*额定功率+额定负载时的无功功率*无功经济当量
        edzhxhgl = Double.parseDouble(df2.format(edgl + ((1 / (edxl / 100)) - 1) * edgl + edfzwggl * wgjjdl));
        ////额定综合效率=（额定功率/(额定功率+（1/额定效率-1）*额定功率+额定负载时的无功功率*无功经济当量）*100%)
        edzhxl = Double.parseDouble(df2.format((edgl / edzhxhgl) * 100));
//        fzxs = fzxs * 100;


        //无功补偿
        //            无功补偿容量=视在功率*(SQRT(1/功率因数/功率因数-1)-SQRT(1/目标功率因数/目标功率因数-1))
        //无功补偿电容量=无功补偿容量*1000000000/2/PI/电网频率/额定电压/额定电压
        if (glys != 0 && mbglys != 0) {
            wgbcrl = Math.round(szgl / 1000 * (Math.sqrt(1 / glys / glys - 1) - Math.sqrt(1 / mbglys / mbglys - 1)));
        } else {
            wgbcrl = 0.00f;
        }
        if (dwpl != 0) {
            wgbcdrl = Math.round(wgbcrl * 1000000000 / 2 / Math.PI / dwpl / eddy / eddy);
        } else {
            wgbcdrl = 0.00f;
        }
    }


    private double[] harmUA = new double[32];//A相电压谐波
    private double[] harmUB = new double[32];//B相电压谐波
    private double[] harmUC = new double[32];//C相电压谐波
    private double[] harmIA = new double[32];//A相电流谐波
    private double[] harmIB = new double[32];//B相电流谐波

    public double getPjdy() {
        return pjdy;
    }

    public void setPjdy(double pjdy) {
        this.pjdy = pjdy;
    }

    public double getPjdl() {
        return pjdl;
    }

    public void setPjdl(double pjdl) {
        this.pjdl = pjdl;
    }

    public double getYggl() {
        return yggl;
    }

    public void setYggl(double yggl) {
        this.yggl = yggl;
    }

    public double getWggl() {
        return wggl;
    }

    public void setWggl(double wggl) {
        this.wggl = wggl;
    }

    public double getScgl() {
        return scgl;
    }

    public void setScgl(double scgl) {
        this.scgl = scgl;
    }

    public double getGlys() {
        return glys;
    }

    public void setGlys(double glys) {
        this.glys = glys;
    }

    public double getYgdn() {
        return ygdn;
    }

    public void setYgdn(double ygdn) {
        this.ygdn = ygdn;
    }

    public double getWgdn() {
        return wgdn;
    }

    public void setWgdn(double wgdn) {
        this.wgdn = wgdn;
    }

    public double getDwpl() {
        return dwpl;
    }

    public void setDwpl(double dwpl) {
        this.dwpl = dwpl;
    }

    public double getSzgl() {
        return szgl;
    }

    public void setSzgl(double szgl) {
        this.szgl = szgl;
    }

    public double getFzxs() {
        return fzxs;
    }

    public void setFzxs(double fzxs) {
        this.fzxs = fzxs;
    }

    public double getZhxl() {
        return zhxl;
    }

    public void setZhxl(double zhxl) {
        this.zhxl = zhxl;
    }

    public double getUAB() {
        return UAB;
    }

    public void setUAB(double UAB) {
        this.UAB = UAB;
    }

    public double getUBC() {
        return UBC;
    }

    public void setUBC(double UBC) {
        this.UBC = UBC;
    }

    public double getUCA() {
        return UCA;
    }

    public void setUCA(double UCA) {
        this.UCA = UCA;
    }

    public double getIA() {
        return IA;
    }

    public void setIA(double IA) {
        this.IA = IA;
    }

    public double getIB() {
        return IB;
    }

    public void setIB(double IB) {
        this.IB = IB;
    }

    public double getIC() {
        return IC;
    }

    public void setIC(double IC) {
        this.IC = IC;
    }

    public double getAyggl() {
        return Ayggl;
    }

    public void setAyggl(double ayggl) {
        Ayggl = ayggl;
    }

    public double getByggl() {
        return Byggl;
    }

    public void setByggl(double byggl) {
        Byggl = byggl;
    }

    public double getCyggl() {
        return Cyggl;
    }

    public void setCyggl(double cyggl) {
        Cyggl = cyggl;
    }

    public double getAwggl() {
        return Awggl;
    }

    public void setAwggl(double awggl) {
        Awggl = awggl;
    }

    public double getBwggl() {
        return Bwggl;
    }

    public void setBwggl(double bwggl) {
        Bwggl = bwggl;
    }

    public double getCwggl() {
        return Cwggl;
    }

    public void setCwggl(double cwggl) {
        Cwggl = cwggl;
    }

    public double getAszgl() {
        return Aszgl;
    }

    public void setAszgl(double aszgl) {
        Aszgl = aszgl;
    }

    public double getBszgl() {
        return Bszgl;
    }

    public void setBszgl(double bszgl) {
        Bszgl = bszgl;
    }

    public double getCszgl() {
        return Cszgl;
    }

    public void setCszgl(double cszgl) {
        Cszgl = cszgl;
    }

    public double getAglys() {
        return Aglys;
    }

    public void setAglys(double aglys) {
        Aglys = aglys;
    }

    public double getBglys() {
        return Bglys;
    }

    public void setBglys(double bglys) {
        Bglys = bglys;
    }

    public double getCglys() {
        return Cglys;
    }

    public void setCglys(double cglys) {
        Cglys = cglys;
    }

    public double getPhUAB() {
        return phUAB;
    }

    public void setPhUAB(double phUAB) {
        this.phUAB = phUAB;
    }

    public double getPhUBC() {
        return phUBC;
    }

    public void setPhUBC(double phUBC) {
        this.phUBC = phUBC;
    }

    public double getPhUCA() {
        return phUCA;
    }

    public void setPhUCA(double phUCA) {
        this.phUCA = phUCA;
    }

    public double getPhUIA() {
        return phUIA;
    }

    public void setPhUIA(double phUIA) {
        this.phUIA = phUIA;
    }

    public double getPhUIB() {
        return phUIB;
    }

    public void setPhUIB(double phUIB) {
        this.phUIB = phUIB;
    }

    public double getPhUIC() {
        return phUIC;
    }

    public void setPhUIC(double phUIC) {
        this.phUIC = phUIC;
    }

    public double getZhglsh() {
        return zhglsh;
    }

    public void setZhglsh(double zhglsh) {
        this.zhglsh = zhglsh;
    }

    public double getKzwggl() {
        return kzwggl;
    }

    public void setKzwggl(double kzwggl) {
        this.kzwggl = kzwggl;
    }

    public double getEdfzwggl() {
        return edfzwggl;
    }

    public void setEdfzwggl(double edfzwggl) {
        this.edfzwggl = edfzwggl;
    }

    public double getYgglsh() {
        return ygglsh;
    }

    public void setYgglsh(double ygglsh) {
        this.ygglsh = ygglsh;
    }

    public double getEdfzglsh() {
        return edfzglsh;
    }

    public void setEdfzglsh(double edfzglsh) {
        this.edfzglsh = edfzglsh;
    }

    public double getZhxhgl() {
        return zhxhgl;
    }

    public void setZhxhgl(double zhxhgl) {
        this.zhxhgl = zhxhgl;
    }

    public double getEdzhxhgl() {
        return edzhxhgl;
    }

    public void setEdzhxhgl(double edzhxhgl) {
        this.edzhxhgl = edzhxhgl;
    }

    public double getEdzhxl() {
        return edzhxl;
    }

    public void setEdzhxl(double edzhxl) {
        this.edzhxl = edzhxl;
    }

    public double getWgbcrl() {
        return wgbcrl;
    }

    public void setWgbcrl(double wgbcrl) {
        this.wgbcrl = wgbcrl;
    }

    public double getWgbcdrl() {
        return wgbcdrl;
    }

    public void setWgbcdrl(double wgbcdrl) {
        this.wgbcdrl = wgbcdrl;
    }

    public double getEddy() {
        return eddy;
    }

    public void setEddy(double eddy) {
        this.eddy = eddy;
    }

    public double getEddl() {
        return eddl;
    }

    public void setEddl(double eddl) {
        this.eddl = eddl;
    }

    public double getEdxl() {
        return edxl;
    }

    public void setEdxl(double edxl) {
        this.edxl = edxl;
    }

    public double getEdgl() {
        return edgl;
    }

    public void setEdgl(double edgl) {
        this.edgl = edgl;
    }

    public double getKzgl() {
        return kzgl;
    }

    public void setKzgl(double kzgl) {
        this.kzgl = kzgl;
    }

    public double getKzdl() {
        return kzdl;
    }

    public double getEdzhglsh() {
        return edzhglsh;
    }

    public void setEdzhglsh(double edzhglsh) {
        this.edzhglsh = edzhglsh;
    }

    public void setKzdl(double kzdl) {
        this.kzdl = kzdl;
    }

    public double getEdglys() {
        return edglys;
    }

    public void setEdglys(double edglys) {
        this.edglys = edglys;
    }

    public double getWgjjdl() {
        return wgjjdl;
    }

    public void setWgjjdl(double wgjjdl) {
        this.wgjjdl = wgjjdl;
    }

    public double getDjyxzt() {
        return djyxzt;
    }

    public String getstrDjyxzt() {
        String strres = "";
        if (zhxl > edzhxl) {
            strres = "经济运行";
        } else if (zhxl > (edzhxl * 0.6)) {
            strres = "允许运行";

        } else {
            strres = "非经济运行";
        }
        return strres;
    }

    public void setDjyxzt(double djyxzt) {
        this.djyxzt = djyxzt;
    }

    public double getDypc() {
        return dypc;
    }

    public void setDypc(double dypc) {
        this.dypc = dypc;
    }

    public double getSxbphd() {
        return sxbphd;
    }

    public void setSxbphd(double sxbphd) {
        this.sxbphd = sxbphd;
    }

    public double getMbglys() {
        return mbglys;
    }

    public void setMbglys(double mbglys) {
        this.mbglys = mbglys;
    }

    public double getWd() {
        return wd;
    }

    public void setWd(double wd) {
        this.wd = wd;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public double getDqy() {
        return dqy;
    }

    public void setDqy(double dqy) {
        this.dqy = dqy;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public double getSavedate() {
        return savedate;
    }

    public void setSavedate(double savedate) {
        this.savedate = savedate;
    }

    public double getBy1() {
        return by1;
    }

    public void setBy1(double by1) {
        this.by1 = by1;
    }

    public double getBy2() {
        return by2;
    }

    public void setBy2(double by2) {
        this.by2 = by2;
    }

    public double getBy3() {
        return by3;
    }

    public void setBy3(double by3) {
        this.by3 = by3;
    }

    public double getBy4() {
        return by4;
    }

    public void setBy4(double by4) {
        this.by4 = by4;
    }

    public double getBy5() {
        return by5;
    }

    public void setBy5(double by5) {
        this.by5 = by5;
    }

    public double getBy6() {
        return by6;
    }

    public void setBy6(double by6) {
        this.by6 = by6;
    }

    public double getBy7() {
        return by7;
    }

    public void setBy7(double by7) {
        this.by7 = by7;
    }

    public double getBy8() {
        return by8;
    }

    public void setBy8(double by8) {
        this.by8 = by8;
    }

    public double getBy9() {
        return by9;
    }

    public void setBy9(double by9) {
        this.by9 = by9;
    }

    public double getBy10() {
        return by10;
    }

    public void setBy10(double by10) {
        this.by10 = by10;
    }

    public double[] getHarmUA() {
        return harmUA;
    }

    public void setHarmUA(double[] harmUA) {
        this.harmUA = harmUA;
    }

    public double[] getHarmUB() {
        return harmUB;
    }

    public void setHarmUB(double[] harmUB) {
        this.harmUB = harmUB;
    }

    public double[] getHarmUC() {
        return harmUC;
    }

    public void setHarmUC(double[] harmUC) {
        this.harmUC = harmUC;
    }

    public double[] getHarmIA() {
        return harmIA;
    }

    public void setHarmIA(double[] harmIA) {
        this.harmIA = harmIA;
    }

    public double[] getHarmIB() {
        return harmIB;
    }

    public void setHarmIB(double[] harmIB) {
        this.harmIB = harmIB;
    }

    public double[] getHarmIC() {
        return harmIC;
    }

    public void setHarmIC(double[] harmIC) {
        this.harmIC = harmIC;
    }

    private double[] harmIC = new double[32];//C相电流谐波

    public double getUA() {
        return UA;
    }

    public void setUA(double UA) {
        this.UA = UA;
    }

    public double getUB() {
        return UB;
    }

    public void setUB(double UB) {
        this.UB = UB;
    }

    public double getUC() {
        return UC;
    }

    public void setUC(double UC) {
        this.UC = UC;
    }

    public int getJs() {
        return js;
    }

    public void setJs(int js) {
        this.js = js;
    }

    public double getLxdl() {
        return lxdl;
    }

    public void setLxdl(double lxdl) {
        this.lxdl = lxdl;
    }

    public double getKUA() {
        return KUA;
    }

    public void setKUA(double KUA) {
        this.KUA = KUA;
    }

    public double getKUB() {
        return KUB;
    }

    public void setKUB(double KUB) {
        this.KUB = KUB;
    }

    public double getKUC() {
        return KUC;
    }

    public void setKUC(double KUC) {
        this.KUC = KUC;
    }

    public double getKIA() {
        return KIA;
    }

    public void setKIA(double KIA) {
        this.KIA = KIA;
    }

    public double getKIB() {
        return KIB;
    }

    public void setKIB(double KIB) {
        this.KIB = KIB;
    }

    public double getKIC() {
        return KIC;
    }

    public void setKIC(double KIC) {
        this.KIC = KIC;
    }

    public double getHCUA() {
        return HCUA;
    }

    public void setHCUA(double HCUA) {
        this.HCUA = HCUA;
    }

    public double getHCUB() {
        return HCUB;
    }

    public void setHCUB(double HCUB) {
        this.HCUB = HCUB;
    }

    public double getHCUC() {
        return HCUC;
    }

    public void setHCUC(double HCUC) {
        this.HCUC = HCUC;
    }

    public double getHCIA() {
        return HCIA;
    }

    public void setHCIA(double HCIA) {
        this.HCIA = HCIA;
    }

    public double getHCIB() {
        return HCIB;
    }

    public void setHCIB(double HCIB) {
        this.HCIB = HCIB;
    }

    public double getHCIC() {
        return HCIC;
    }

    public void setHCIC(double HCIC) {
        this.HCIC = HCIC;
    }


    public void setMethods(String csff) {
        this.method = csff;
    }
}
