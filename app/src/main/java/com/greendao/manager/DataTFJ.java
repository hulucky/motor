package com.greendao.manager;


import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.administrator.DATAbase.greendao.TaskResEnity;
import com.motor.app.MyApp;

import org.greenrobot.greendao.annotation.Unique;

import java.text.DecimalFormat;
import java.util.List;

public class DataTFJ {
    private TaskEntity mHisTask;
    private TaskResEnity mRes;

    public motorData getMotordata() {
        return mmdata;
    }

    public void setMotordata(motorData mmdata) {
        this.mmdata = mmdata;
    }

    private motorData mmdata;
    private float fdjgl1;
    private float fdjgl2;
    private float fdjxl1;
    private float fdjxl2;
    private float mBhzqy;
    private float mWd;
    private float mKqmd;
    private float mDqy;
    private double mSd;

    private float mCfddy;
    private double mPjfs;
    private double mFl;
    private double mCfmj;
    private float mDy;
    private double mPtgxs;
    private float mCymjd;
    private float mCymjx;
    private float mJyc;
    private float mZgl1;
    private float mDjgl;
    private float mDjxl;
    private float mCdxl;
    private float mZgl2;
    private float mDjgl2;
    private float mDjxl2;
    private float mCdxl2;
    private float mJygl;
    private double mFjjy;
    private float mQygl;
    private double mFjqy;
    private float mJyxl;
    private float mQyxl;
    private float mKsckmj;
    private float mJy;
    private float mKsckdy;
    private float mFjyxxl;
    private float mGxnh;
    private float mcKqmdzhxs;
    private int mSczs;
    private float mcZszhxs;
    private int mEdzs;
    private double mgFl;
    private float mgZgl1;
    private float mgZgl2;
    private float mgFjjy;
    private float mgJygl;
    private float mgFjqy;
    private float mgQygl;
    private float mgJyxl;
    private float mgQyxl;

    // 电参数
    @Unique
    private float Uab;
    private float Ia;
    private float Ubc;
    private float Ib;
    private float Uca;
    private float Ic;
    private float pjU;
    private float pjI;
    private float djgl;


    private float Ua;
    private float Ub;
    private float Uc;


    private float zgl;
    private float scgl;
    private float djxl;
    private float zhxl;
    private float glys;
    private float fzxs;
    private String yxzt = "未连接";
    private String dj1qblc;
    private String dj1csff;


    private float dj1cdxl;
    // 2
    @Unique
    private float Ua2;
    private float Ub2;
    private float Uc2;
    private float Uab2;
    private float Ia2;
    private float Ubc2;
    private float Ib2;
    private float Uca2;
    private float Ic2;
    private float pjU2;
    private float pjI2;
    private float djgl2;
    private float zgl2;
    private float scgl2;
    private float djxl2;
    private float zhxl2;
    private float glys2;
    private float fzxs2;
    private String yxzt2 = "未连接";
    private String dj2qblc;
    private String dj2csff;
    private float dj2cdxl;

    private String mff;


    private Boolean mventi;
    private Long mId;

    DecimalFormat df1 = new DecimalFormat("#.0");
    DecimalFormat df2 = new DecimalFormat("#.00");
    DecimalFormat df3 = new DecimalFormat("#.000");

    public void SetHisTask(TaskEntity mtask) {
        this.mHisTask = mtask;
        mff = mHisTask.getCsff();

        dj1csff = mHisTask.getDjcsff1();



        dj1qblc = mHisTask.getDjqblc1();
        mmdata.setMethods(mtask.getCsff());
        mmdata.setEddl(Double.parseDouble(mtask.getDjeddl1()));
        mmdata.setEddy(Double.parseDouble(mtask.getDjeddy1()));
        mmdata.setEdgl(Double.parseDouble(mtask.getDjedgl1())*1000);
        //   mmdata.setEdglys(Double.parseDouble(mtask.getEdglys()));

        mmdata.setEdxl(Double.parseDouble(mtask.getDjedxl1()));
        mmdata.setKzdl(Double.parseDouble(mtask.getDjkzdl1()));
        mmdata.setKzgl(Double.parseDouble(mtask.getDjkzgl1())*1000);
        mmdata.setJs(Integer.parseInt(mtask.getDjjs1()));
        mmdata.setWgjjdl(Double.parseDouble(mtask.getDjwgjjdl1()));
        mmdata.setMbglys(0.95d);

        mmdata.setMethods(mtask.getCsff());

    }

    public TaskEntity GetHisTask() {
//		mHisTask.setFluid(mSczs);
//		mHisTask.setPsgd((float) mCfmj);
//
//		if (mff == 3) {
//			mHisTask.setJkyl((float) mPtgxs);
//		} else if (mff == 4) {
//			mHisTask.setJkyl(mCymjx);
//			mHisTask.setBwc(mCymjd);
//		}
        if (mHisTask != null) {
            return this.mHisTask;
        } else if (mRes != null) {
            return MyApp.getDaoInstant().getTaskEntityDao().queryBuilder().where(TaskEntityDao.Properties.Id.eq(mRes.getTaskId())).list().get(0);
        } else {
            return new TaskEntity();
        }
    }

    public void SetResOnly(TaskResEnity mres) {
        this.mRes = mres;
        List<TaskEntity> mlist = MyApp.getDaoInstant().getTaskEntityDao().queryBuilder().where(TaskEntityDao.Properties.Id.eq(mres.getTaskId())).list();
        if (mlist.size() > 0) {
            this.mHisTask = mlist.get(0);
            mff = mHisTask.getCsff();
            dj1csff = mHisTask.getDjcsff1();
            dj1qblc = mHisTask.getDjqblc1();
        }
        mId = mres.getId();
        if (mff == null) {
            mff = mres.getCsff();
        }
        if(mmdata==null)
        {
            mmdata=new motorData();
        }
        mmdata.setPjdy(mres.getPjdy());
        mmdata.setPjdl(mres.getPjdl());
        mmdata.setYggl(mres.getYggl());
        mmdata.setWggl(mres.getWggl());
        mmdata.setScgl(mres.getScgl());
        mmdata.setGlys(mres.getGlys());
        mmdata.setYgdn(mres.getYgdn());
        mmdata.setWgdn(mres.getWgdn());
        mmdata.setDwpl(mres.getDwpl());
        mmdata.setSzgl(mres.getSzgl());
        mmdata.setFzxs(mres.getFzxs());
        mmdata.setZhxl(mres.getZhxl());
        mmdata.setUAB(mres.getUAB());
        mmdata.setUBC(mres.getUBC());
        mmdata.setUCA(mres.getUCA());
        mmdata.setPjxdy(mres.getPjxdy());
        mmdata.setUA(mres.getUA());
        mmdata.setUB(mres.getUB());
        mmdata.setUC(mres.getUC());
        mmdata.setIA(mres.getIA());
        mmdata.setIB(mres.getIB());
        mmdata.setIC(mres.getIC());
        mmdata.setAyggl(mres.getAyggl());
        mmdata.setByggl(mres.getByggl());
        mmdata.setCyggl(mres.getCyggl());
        mmdata.setAwggl(mres.getAwggl());
        mmdata.setBwggl(mres.getBwggl());
        mmdata.setCwggl(mres.getCwggl());
        mmdata.setAszgl(mres.getAszgl());
        mmdata.setBszgl(mres.getBszgl());
        mmdata.setCszgl(mres.getCszgl());
        mmdata.setAglys(mres.getAglys());
        mmdata.setBglys(mres.getBglys());
        mmdata.setCglys(mres.getCglys());
        mmdata.setPhUAB(mres.getPhUAB());
        mmdata.setPhUBC(mres.getPhUBC());
        mmdata.setPhUCA(mres.getPhUCA());
        mmdata.setPhUIA(mres.getPhUIA());
        mmdata.setPhUIB(mres.getPhUIB());
        mmdata.setPhUIC(mres.getPhUIC());
        mmdata.setZhglsh(mres.getZhglsh());
        mmdata.setKzwggl(mres.getKzwggl());
        mmdata.setEdfzwggl(mres.getEdfzwggl());
        mmdata.setYgglsh(mres.getYgglsh());
        mmdata.setEdfzglsh(mres.getEdfzglsh());
        mmdata.setZhxhgl(mres.getZhxhgl());
        mmdata.setEdzhxhgl(mres.getEdzhxhgl());
        mmdata.setEdzhglsh(mres.getEdzhglsh());
        mmdata.setEdzhxl(mres.getEdzhxl());
        mmdata.setWgbcrl(mres.getWgbcrl());
        mmdata.setWgbcdrl(mres.getWgbcdrl());
        mmdata.setEddy(mres.getEddy());
        mmdata.setEddl(mres.getEddl());
        mmdata.setEdxl(mres.getEdxl());
        mmdata.setEdgl(mres.getEdgl());
        mmdata.setKzgl(mres.getKzgl());
        mmdata.setKzdl(mres.getKzdl());
        mmdata.setEdglys(mres.getEdglys());
        mmdata.setWgjjdl(mres.getWgjjdl());
        mmdata.setDjyxzt(mres.getDjyxzt());
        mmdata.setDypc(mres.getDypc());
        mmdata.setSxbphd(mres.getSxbphd());
        mmdata.setMbglys(mres.getMbglys());
        mmdata.setWd(mres.getWd());
        mmdata.setSd(mres.getSd());
        mmdata.setDqy(mres.getDqy());
        mmdata.setDate(mres.getDate());
        mmdata.setSavedate(mres.getSavedate());
        mmdata.setJs(mres.getJs());
        mmdata.setLxdl(mres.getLxdl());
        mmdata.setKUA(mres.getKUA());
        mmdata.setKUB(mres.getKUB());
        mmdata.setKUC(mres.getKUC());
        mmdata.setKIA(mres.getKIA());
        mmdata.setKIB(mres.getKIB());
        mmdata.setKIC(mres.getKIC());
        mmdata.setHCUA(mres.getHCUA());
        mmdata.setHCUB(mres.getHCUB());
        mmdata.setHCUC(mres.getHCUC());
        mmdata.setHCIA(mres.getHCIA());
        mmdata.setHCIB(mres.getHCIB());
        mmdata.setHCIC(mres.getHCIC());
        mmdata.setBy1(mres.getBy1());
        mmdata.setBy2(mres.getBy2());
        mmdata.setBy3(mres.getBy3());
        mmdata.setBy4(mres.getBy4());
        mmdata.setBy5(mres.getBy5());
        mmdata.setBy6(mres.getBy6());
        mmdata.setBy7(mres.getBy7());
        mmdata.setBy8(mres.getBy8());
        mmdata.setBy9(mres.getBy9());
        mmdata.setBy10(mres.getBy10());
        mmdata.setDybb(mres.getDybb());
        mmdata.setDlbb(mres.getDlbb());
        mmdata.setMethod(mres.getMethod());
        mmdata.setXl(mres.getXl());



//        mSczs = mres.getMSczs();
//        mEdzs = mres.getMEdzs();
//        mKsckmj = mres.getMKsckmj();
//        mDjxl = mres.getMDjxl();
//        mCdxl = mres.getMCdxl();
//        mCfmj = mres.getMCfmj();
//        if (mff.equals("静压全压法")) {
//            mPtgxs = mres.getMPtgxs();
//        } else if (mff.equals("静压差法")) {
//            mCymjx = mres.getMCymjx();
//            mCymjd = mres.getMCymjd();
//        }
//        mDjxl2 = mres.getMDjxl2();
//        mCdxl2 = mres.getMCdxl2();
//        dj1csff = mres.getDj1csff();
//        dj2csff = mres.getDj2csff();
//        dj1cdxl = mres.getDj1cdxl();
//        dj2cdxl = mres.getDj2cdxl();
//        dj1qblc = mres.getDj1qblc();
//        dj2qblc = mres.getDj2qblc();
//        mPjfs = mres.getMPjfs();
//        mDqy = mres.getMDqy();
//        mJy = mres.getMJy();
//        mDy = mres.getMDy();
//        mJyc = mres.getMJyc();
//        mWd = mres.getMWd();
//        mSd = mres.getMSd();
//        mDjgl = mres.getMDjgl();
//        mDjgl2 = mres.getMDjgl2();
//        mZgl1 = mres.getMZgl1();
//        mZgl2 = mres.getMZgl2();
//        mKqmd = mres.getMKqmd();
//        mFl = mres.getMFl();
//        mBhzqy = mres.getMBhzqy();
//        mCfddy = mres.getMCfddy();
//        mKsckdy = mres.getMKsckdy();
//        mFjjy = mres.getMFjjy();
//        mFjqy = mres.getMFjqy();
//        mJygl = mres.getMJygl();
//        mJyxl = mres.getMJyxl();
//        mQygl = mres.getMQygl();
//        mQyxl = mres.getMQyxl();
//        mFjyxxl = mres.getMFjyxxl();
//        mGxnh = mres.getMGxnh();
//        mcKqmdzhxs = mres.getMcKqmdzhxs();
//        mcZszhxs = mres.getMcZszhxs();
//        mgFl = mres.getMgFl();
//        mgZgl1 = mres.getMgZgl1();
//        mgZgl2 = mres.getMgZgl2();
//        mgFjjy = mres.getMgFjjy();
//        mgJygl = mres.getMgJygl();
//        mgJyxl = mres.getMgJyxl();
//        mgFjqy = mres.getMgFjqy();
//        mgQygl = mres.getMgQygl();
//        mgQyxl = mres.getMgQyxl();
//
//        Ua = mres.getUa();
//        Ub = mres.getUb();
//        Uc = mres.getUc();
//        Ua2 = mres.getUa2();
//        Ub2 = mres.getUb2();
//        Uc2 = mres.getUc2();
//        Uab = mres.getUab();
//        Ia = mres.getIa();
//        Ubc = mres.getUbc();
//        Ib = mres.getIb();
//        Uca = mres.getUca();
//        Ic = mres.getIc();
//        pjU = mres.getPjU();
//        pjI = mres.getPjI();
//        djgl = mres.getDjgl();
//        zgl = mres.getZgl();
//        scgl = mres.getScgl();
//        djxl = mres.getDjxl();
//        zhxl = mres.getZhxl();
//        glys = mres.getGlys();
//        fzxs = mres.getFzxs();
//        yxzt = mres.getStryxzt();
//
//        Uab2 = mres.getUab2();
//        Ia2 = mres.getIa2();
//        Ubc2 = mres.getUbc2();
//        Ib2 = mres.getIb2();
//        Uca2 = mres.getUca2();
//        Ic2 = mres.getIc2();
//        pjU2 = mres.getPjU2();
//        pjI2 = mres.getPjI2();
//        djgl2 = mres.getDjgl2();
//        zgl2 = mres.getZgl2();
//        scgl2 = mres.getScgl2();
//        djxl2 = mres.getDjxl2();
//        zhxl2 = mres.getZhxl2();
//        glys2 = mres.getGlys2();
//        fzxs2 = mres.getFzxs2();
//        yxzt2 = mres.getStryxzt2();
//        dj1csff = mres.getDj1csff();
//        dj2csff = mres.getDj2csff();
//        dj1cdxl = mres.getDj1cdxl();
//        dj2cdxl = mres.getDj2cdxl();
//        dj1qblc = mres.getDj1qblc();
//        dj2qblc = mres.getDj2qblc();
    }

    public void SetRes(TaskResEnity mres) {
        this.mRes = mres;
        mId = mres.getId();
//        mPjfs = mres.getMPjfs();
//        mDqy = mres.getMDqy();
//        mJy = mres.getMJy();
//        mDy = mres.getMDy();
//        mJyc = mres.getMJyc();
//        mWd = mres.getMWd();
//        mSd = mres.getMSd();
//        mDjgl = mres.getMDjgl();
//        mDjgl2 = mres.getMDjgl2();
//        mDjxl = mres.getMDjxl();
//        mDjxl2 = mres.getMDjxl2();
//        mZgl1 = mres.getMZgl1();
//        mZgl2 = mres.getMZgl2();
//        mKqmd = mres.getMKqmd();
//
//        mFl = mres.getMFl();
//        mBhzqy = mres.getMBhzqy();
//        mCfddy = mres.getMCfddy();
//        mKsckdy = mres.getMKsckdy();
//        mFjjy = mres.getMFjjy();
//        mFjqy = mres.getMFjqy();
//        mJygl = mres.getMJygl();
//        mJyxl = mres.getMJyxl();
//        mQygl = mres.getMQygl();
//        mQyxl = mres.getMQyxl();
//        mFjyxxl = mres.getMFjyxxl();
//        mGxnh = mres.getMGxnh();
//        mcKqmdzhxs = mres.getMcKqmdzhxs();
//        mcZszhxs = mres.getMcZszhxs();
//        mgFl = mres.getMgFl();
//        mgZgl1 = mres.getMgZgl1();
//        mgZgl2 = mres.getMgZgl2();
//        mgFjjy = mres.getMgFjjy();
//        mgJygl = mres.getMgJygl();
//        mgJyxl = mres.getMgJyxl();
//        mgFjqy = mres.getMgFjqy();
//        mgQygl = mres.getMgQygl();
//        mgQyxl = mres.getMgQyxl();
//
//        Ua = mres.getUa();
//        Ub = mres.getUb();
//        Uc = mres.getUc();
//        Ua2 = mres.getUa2();
//        Ub2 = mres.getUb2();
//        Uc2 = mres.getUc2();
//        Uab = mres.getUab();
//        Ia = mres.getIa();
//        Ubc = mres.getUbc();
//        Ib = mres.getIb();
//        Uca = mres.getUca();
//        Ic = mres.getIc();
//        pjU = mres.getPjU();
//        pjI = mres.getPjI();
//        djgl = mres.getDjgl();
//        zgl = mres.getZgl();
//        scgl = mres.getScgl();
//        djxl = mres.getDjxl();
//        zhxl = mres.getZhxl();
//        glys = mres.getGlys();
//        fzxs = mres.getFzxs();
////        yxzt = mres.getYxzt();
//
//        Uab2 = mres.getUab2();
//        Ia2 = mres.getIa2();
//        Ubc2 = mres.getUbc2();
//        Ib2 = mres.getIb2();
//        Uca2 = mres.getUca2();
//        Ic2 = mres.getIc2();
//        pjU2 = mres.getPjU2();
//        pjI2 = mres.getPjI2();
//        djgl2 = mres.getDjgl2();
//        zgl2 = mres.getZgl2();
//        scgl2 = mres.getScgl2();
//        djxl2 = mres.getDjxl2();
//        zhxl2 = mres.getZhxl2();
//        glys2 = mres.getGlys2();
//        fzxs2 = mres.getFzxs2();
////        yxzt2 = mres.getYxzt2();
//        dj1csff = mres.getDj1csff();
//        dj2csff = mres.getDj2csff();
//        dj1cdxl = mres.getDj1cdxl();
//        dj2cdxl = mres.getDj2cdxl();
//        dj1qblc = mres.getDj1qblc();
//        dj2qblc = mres.getDj2qblc();

    }

    public void CopyRes(TaskResEnity mRes) {
        mRes.setTaskId(mHisTask.getId());

        mRes.setCsff(mff);
        saveMotor(mRes);
    }


    private void saveMotor(TaskResEnity mRes)
    {

        mRes.setPjdy(mmdata.getPjdy());
        mRes.setPjdl(mmdata.getPjdl());
        mRes.setYggl(mmdata.getYggl());
        mRes.setWggl(mmdata.getWggl());
        mRes.setScgl(mmdata.getScgl());
        mRes.setGlys(mmdata.getGlys());
        mRes.setYgdn(mmdata.getYgdn());
        mRes.setWgdn(mmdata.getWgdn());
        mRes.setDwpl(mmdata.getDwpl());
        mRes.setSzgl(mmdata.getSzgl());
        mRes.setFzxs(mmdata.getFzxs());
        mRes.setZhxl(mmdata.getZhxl());
        mRes.setUAB(mmdata.getUAB());
        mRes.setUBC(mmdata.getUBC());
        mRes.setUCA(mmdata.getUCA());
        mRes.setPjxdy(mmdata.getPjxdy());
        mRes.setUA(mmdata.getUA());
        mRes.setUB(mmdata.getUB());
        mRes.setUC(mmdata.getUC());
        mRes.setIA(mmdata.getIA());
        mRes.setIB(mmdata.getIB());
        mRes.setIC(mmdata.getIC());
        mRes.setAyggl(mmdata.getAyggl());
        mRes.setByggl(mmdata.getByggl());
        mRes.setCyggl(mmdata.getCyggl());
        mRes.setAwggl(mmdata.getAwggl());
        mRes.setBwggl(mmdata.getBwggl());
        mRes.setCwggl(mmdata.getCwggl());
        mRes.setAszgl(mmdata.getAszgl());
        mRes.setBszgl(mmdata.getBszgl());
        mRes.setCszgl(mmdata.getCszgl());
        mRes.setAglys(mmdata.getAglys());
        mRes.setBglys(mmdata.getBglys());
        mRes.setCglys(mmdata.getCglys());
        mRes.setPhUAB(mmdata.getPhUAB());
        mRes.setPhUBC(mmdata.getPhUBC());
        mRes.setPhUCA(mmdata.getPhUCA());
        mRes.setPhUIA(mmdata.getPhUIA());
        mRes.setPhUIB(mmdata.getPhUIB());
        mRes.setPhUIC(mmdata.getPhUIC());
        mRes.setZhglsh(mmdata.getZhglsh());
        mRes.setKzwggl(mmdata.getKzwggl());
        mRes.setEdfzwggl(mmdata.getEdfzwggl());
        mRes.setYgglsh(mmdata.getYgglsh());
        mRes.setEdfzglsh(mmdata.getEdfzglsh());
        mRes.setZhxhgl(mmdata.getZhxhgl());
        mRes.setEdzhxhgl(mmdata.getEdzhxhgl());
        mRes.setEdzhglsh(mmdata.getEdzhglsh());
        mRes.setEdzhxl(mmdata.getEdzhxl());
        mRes.setWgbcrl(mmdata.getWgbcrl());
        mRes.setWgbcdrl(mmdata.getWgbcdrl());
        mRes.setEddy(mmdata.getEddy());
        mRes.setEddl(mmdata.getEddl());
        mRes.setEdxl(mmdata.getEdxl());
        mRes.setEdgl(mmdata.getEdgl());
        mRes.setKzgl(mmdata.getKzgl());
        mRes.setKzdl(mmdata.getKzdl());
        mRes.setEdglys(mmdata.getEdglys());
        mRes.setWgjjdl(mmdata.getWgjjdl());
        mRes.setDjyxzt(mmdata.getDjyxzt());
        mRes.setDypc(mmdata.getDypc());
        mRes.setSxbphd(mmdata.getSxbphd());
        mRes.setMbglys(mmdata.getMbglys());
        mRes.setWd(mmdata.getWd());
        mRes.setSd(mmdata.getSd());
        mRes.setDqy(mmdata.getDqy());
        mRes.setDate(mmdata.getDate());
        mRes.setSavedate(mmdata.getSavedate());
        mRes.setJs(mmdata.getJs());
        mRes.setLxdl(mmdata.getLxdl());
        mRes.setKUA(mmdata.getKUA());
        mRes.setKUB(mmdata.getKUB());
        mRes.setKUC(mmdata.getKUC());
        mRes.setKIA(mmdata.getKIA());
        mRes.setKIB(mmdata.getKIB());
        mRes.setKIC(mmdata.getKIC());
        mRes.setHCUA(mmdata.getHCUA());
        mRes.setHCUB(mmdata.getHCUB());
        mRes.setHCUC(mmdata.getHCUC());
        mRes.setHCIA(mmdata.getHCIA());
        mRes.setHCIB(mmdata.getHCIB());
        mRes.setHCIC(mmdata.getHCIC());
        mRes.setBy1(mmdata.getBy1());
        mRes.setBy2(mmdata.getBy2());
        mRes.setBy3(mmdata.getBy3());
        mRes.setBy4(mmdata.getBy4());
        mRes.setBy5(mmdata.getBy5());
        mRes.setBy6(mmdata.getBy6());
        mRes.setBy7(mmdata.getBy7());
        mRes.setBy8(mmdata.getBy8());
        mRes.setBy9(mmdata.getBy9());
        mRes.setBy10(mmdata.getBy10());
        mRes.setDybb(mmdata.getDybb());
        mRes.setDlbb(mmdata.getDlbb());
        mRes.setMethod(mmdata.getMethod());
        mRes.setXl(mmdata.getXl());



    }

    public TaskResEnity GetRes() {
        TaskResEnity mmRes = new TaskResEnity();
        mmRes.setId(mId);
        if (mHisTask != null) {
            mmRes.setTaskId(mHisTask.getId());
        } else {
            mmRes.setTaskId(mRes.getTaskId());
        }
        saveMotor(mmRes);
        mmRes.setSaveTime(mRes.getSaveTime());

        return mmRes;
    }

    public boolean Refresh() {
        try {


            // ��������ѹ��lnP=12.062-4039.558/(�¶�+235.379)
            // P=e^(12.062-4039.558/(�¶�+235.379)) MPa(MPa=1000hPa=100000Pa)
            mBhzqy =Float.parseFloat(df1.format( (float) Math.exp(12.062 - 4039.558 / (mWd + 235.379)) * 100*1000));
            // //0.00000001455208*t^4-0.00000151077638*t^3+0.00011599438358*t^2+0.00098088830675*t-0.17248452012436
            // mBhzqy= (float)
            // ((float)0.00000001455208*mWd*mWd*mWd*mWd-0.00000151077638*mWd*mWd*mWd+0.00011599438358*mWd*mWd+0.00098088830675*mWd-0.17248452012436);
            // //7.07406-��1657.46/(T+227.02)��
            // mBhzqy=(float) Math.exp(7.07406-(1657.46/(mWd+227.02)));
            // �����ܶ�=0.003484����������ѹ������-0.3779�����ʪ�ȡ���������ѹ(Pa)���£�273������¶ȣ� ǧ�� /������
            mKqmd =Float.parseFloat(df2.format( (float) (0.003484 * (mDqy * 100 - 0.3779 * mSd / 100
                    * mBhzqy ) / (273 + mWd))));

            switch (mff) {
                // �籭����
                // ����=ƽ�����١������� ������/��
                // ���㶯ѹ=0.5*�����ܶ�*ƽ������*ƽ������
                case "风杯法":// ���㷨

                    mCfddy = Float.parseFloat(df1.format((float) (0.5 * mKqmd * mPjfs * mPjfs)));
                    mFl =Float.parseFloat(df1.format( mPjfs * mCfmj));


                    break;
                case "静压全压法":// ��ѹȫѹ��
                    // ƽ������ =��(2*���㶯ѹ/�����ܶ�)*Ƥ�й�ϵ��
                    // ����=ƽ�����١������� ������/�루������=��ѹ�Ĳ�ѹ�����
                    mCfddy = mDy;
                    if (mKqmd != 0) {
                        mPjfs =Float.parseFloat(df1.format( (float) (Math.sqrt(2 * mCfddy / mKqmd) * mPtgxs)));
                        mFl =Float.parseFloat(df1.format( mPjfs * mCfmj));
                    }
                    break;
                case "静压差法":// ��ѹ�
                    // ����=0.99*��ѹ���1*��ѹ���2*��((2*��ѹ��)/(�����ܶ�*������ѹ���2��^2-����ѹ���1��^2��))
                    // ���㶯ѹ=0.5*�����ܶ�*ƽ������*ƽ������
                    // ƽ������=����/������
                    if (mKqmd != 0 && mCymjd != mCymjx) {
                        mFl = Float.parseFloat(df1.format((float) (0.99 * mCymjx * mCymjd * Math.sqrt(2 * mJyc
                                / (mKqmd * (mCymjd * mCymjd - mCymjx * mCymjx))))));
                        mPjfs = Float.parseFloat(df1.format(mFl / mCfmj));
                        mCfddy = Float.parseFloat(df1.format( (float) (0.5 * mKqmd * mPjfs * mPjfs)));
                    }
                    break;
                default:
                    break;

            }
            // �Ṧ��=������ʡ����Ч�ʡ�����Ч�� ǧ��

            mZgl1 = Float.parseFloat(df1.format( fdjgl1 * fdjxl1 / 100 * dj1cdxl));

            mZgl2 = Float.parseFloat(df1.format( fdjgl2 * fdjxl2 / 100 * dj2cdxl));
            // ��ɢ�����ڵĶ�ѹ=0.5*�����ܶ�*������/��ɢ���������*������/��ɢ���������
            if (mKsckmj != 0) {
                mKsckdy = Float.parseFloat(df1.format( (float) (0.5 * mKqmd * (mFl / mKsckmj) * (mFl / mKsckmj))));
            }
            // ͨ�緽ʽ
            if (mventi) {
                // ���ʽͨ�����
                // �����ѹ=��Ծ�ѹ-���㶯ѹ.
                // ���ȫѹ=�����ѹ+��ɢ�����ڵĶ�ѹ. ��
                // �������Ч��=��ѹЧ��
                mFjjy = mJy - mCfddy;
                mFjqy = mFjjy + mKsckdy;
                // ��ѹ����=�����ѹ��������1000 ǧ��
                mJygl =  Float.parseFloat(df1.format((float) (mFjjy * mFl / 1000)));
                // ȫѹ����=���ȫѹ��������1000 ǧ��
                mQygl = Float.parseFloat(df1.format( (float) (mFjqy * mFl / 1000)));

                if ((mZgl1 + mZgl2) != 0) {
                    // ��ѹЧ��=��ѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mJyxl = Float.parseFloat(df1.format( mJygl / (mZgl1 + mZgl2) * 100));

                    // ȫѹЧ��=ȫѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mQyxl = Float.parseFloat(df1.format( mQygl / (mZgl1 + mZgl2) * 100));
                }

                mFjyxxl = mJyxl;
            } else {
                // ѹ��ʽͨ�����
                // �����ѹ=��Ծ�ѹ ��
                // ���ȫѹ=�����ѹ�����㶯ѹ
                // �������Ч��=ȫѹЧ��
                mFjjy = mJy;
                mFjqy = mFjjy + mCfddy;
                // ��ѹ����=�����ѹ��������1000 ǧ��
                mJygl = Float.parseFloat(df1.format( (float) (mFjjy * mFl / 1000)));
                // ȫѹ����=���ȫѹ��������1000 ǧ��
                mQygl = Float.parseFloat(df1.format( (float) (mFjqy * mFl / 1000)));

                if ((mZgl1 + mZgl2) != 0) {
                    // ��ѹЧ��=��ѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mJyxl = Float.parseFloat(df1.format( mJygl / (mZgl1 + mZgl2) * 100));

                    // ȫѹЧ��=ȫѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mQyxl = Float.parseFloat(df1.format( mQygl / (mZgl1 + mZgl2) * 100));
                }

                mFjyxxl = mQyxl;
            }

            // �����ܺ�=1�£�3.6���������Ч�ʣ� ǧ��ʱ/����������
            if (mFjyxxl != 0) {
                mGxnh = Float.parseFloat(df1.format( (float) (1 / (3.6 * mFjyxxl / 100))));
            }
            // ת��ϵ��
            if (mKqmd != 0) {
                mcKqmdzhxs = Float.parseFloat(df1.format( (float) (1.2 / mKqmd)));
            }
            //
            if (mSczs != 0) {
                mcZszhxs = Float.parseFloat(df1.format( (float) mEdzs / mSczs));
            }
            // ת�������
            // ����
            mgFl = Float.parseFloat(df1.format( (float) (mcZszhxs * mFl)));
            //
            mgZgl1 = Float.parseFloat(df1.format( mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mZgl1));
            mgZgl2 = Float.parseFloat(df1.format( mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mZgl2));
            mgFjjy = Float.parseFloat(df1.format( (float) (mcKqmdzhxs * mcZszhxs * mcZszhxs * mFjjy)));
            mgJygl = Float.parseFloat(df1.format( mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mJygl));

            mgFjqy = Float.parseFloat(df1.format( (float) (mcKqmdzhxs * mcZszhxs * mcZszhxs * mFjqy)));
            mgQygl = Float.parseFloat(df1.format( mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mQygl));
            if ((mgZgl1 + mgZgl2) != 0) {
                mgJyxl = Float.parseFloat(df1.format( mgJygl / (mgZgl1 + mgZgl2) * 100));
                mgQyxl = Float.parseFloat(df1.format( mgQygl / (mgZgl1 + mgZgl2) * 100));
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public float getmBhzqy() {
        return mBhzqy;
    }

    public void setmBhzqy(float mBhzqy) {
        this.mBhzqy = mBhzqy;
    }

    public float getmWd() {
        return mWd;
    }

    public void setmWd(float mWd) {
        this.mWd = mWd;
    }

    public float getmKqmd() {
        return mKqmd;
    }

    public void setmKqmd(float mKqmd) {
        this.mKqmd = mKqmd;
    }

    public float getmDqy() {
        return mDqy;
    }

    public void setmDqy(float mDqy) {
        this.mDqy = mDqy;
    }

    public double getmSd() {
        return mSd;
    }

    public void setmSd(double mSd) {
        this.mSd = mSd;
    }

    public float getmCfddy() {
        return mCfddy;
    }

    public void setmCfddy(float mCfddy) {
        this.mCfddy = mCfddy;
    }

    public double getmPjfs() {
        return mPjfs;
    }

    public void setmPjfs(double mPjfs) {
        this.mPjfs = mPjfs;
    }

    public double getmFl() {
        return mFl;
    }

    public void setmFl(double mFl) {
        this.mFl = mFl;
    }

    public double getmCfmj() {
        return mCfmj;
    }

    public void setmCfmj(double mCfmj) {
        this.mCfmj = mCfmj;
    }

    public float getmDy() {
        return mDy;
    }

    public void setmDy(float mDy) {
        this.mDy = mDy;
    }

    public double getmPtgxs() {
        return mPtgxs;
    }

    public void setmPtgxs(double mPtgxs) {
        this.mPtgxs = mPtgxs;
    }

    public float getmCymjd() {
        return mCymjd;
    }

    public void setmCymjd(float mCymjd) {
        this.mCymjd = mCymjd;
    }

    public float getmCymjx() {
        return mCymjx;
    }

    public void setmCymjx(float mCymjx) {
        this.mCymjx = mCymjx;
    }

    public float getmJyc() {
        return mJyc;
    }

    public void setmJyc(float mJyc) {
        this.mJyc = mJyc;
    }

    public float getmZgl1() {
        return mZgl1;
    }

    public void setmZgl1(float mZgl1) {
        this.mZgl1 = mZgl1;
    }

    public float getmDjgl() {
        return mDjgl;
    }

    public void setmDjgl(float mDjgl) {
        this.mDjgl = mDjgl;
    }

    public float getmDjxl() {
        return mDjxl;
    }

    public void setmDjxl(float mDjxl) {
        this.mDjxl = mDjxl;
    }

    public float getmCdxl() {
        return mCdxl;
    }

    public void setmCdxl(float mCdxl) {
        this.mCdxl = mCdxl;
    }

    public float getmZgl2() {
        return mZgl2;
    }

    public void setmZgl2(float mZgl2) {
        this.mZgl2 = mZgl2;
    }

    public float getmDjgl2() {
        return mDjgl2;
    }

    public void setmDjgl2(float mDjgl2) {
        this.mDjgl2 = mDjgl2;
    }

    public float getmDjxl2() {
        return mDjxl2;
    }

    public void setmDjxl2(float mDjxl2) {
        this.mDjxl2 = mDjxl2;
    }

    public float getmCdxl2() {
        return mCdxl2;
    }

    public void setmCdxl2(float mCdxl2) {
        this.mCdxl2 = mCdxl2;
    }

    public float getmJygl() {
        return mJygl;
    }

    public void setmJygl(float mJygl) {
        this.mJygl = mJygl;
    }

    public double getmFjjy() {
        return mFjjy;
    }

    public void setmFjjy(double mFjjy) {
        this.mFjjy = mFjjy;
    }

    public float getmQygl() {
        return mQygl;
    }

    public void setmQygl(float mQygl) {
        this.mQygl = mQygl;
    }

    public double getmFjqy() {
        return mFjqy;
    }

    public void setmFjqy(double mFjqy) {
        this.mFjqy = mFjqy;
    }

    public float getmJyxl() {
        return mJyxl;
    }

    public void setmJyxl(float mJyxl) {
        this.mJyxl = mJyxl;
    }

    public float getmQyxl() {
        return mQyxl;
    }

    public void setmQyxl(float mQyxl) {
        this.mQyxl = mQyxl;
    }

    public float getmKsckmj() {
        return mKsckmj;
    }

    public void setmKsckmj(float mKsckmj) {
        this.mKsckmj = mKsckmj;
    }

    public float getmJy() {
        return mJy;
    }

    public void setmJy(float mJy) {
        this.mJy = mJy;
    }

    public float getmKsckdy() {
        return mKsckdy;
    }

    public void setmKsckdy(float mKsckdy) {
        this.mKsckdy = mKsckdy;
    }

    public float getmFjyxxl() {
        return mFjyxxl;
    }

    public void setmFjyxxl(float mFjyxxl) {
        this.mFjyxxl = mFjyxxl;
    }

    public float getmGxnh() {
        return mGxnh;
    }

    public void setmGxnh(float mGxnh) {
        this.mGxnh = mGxnh;
    }

    public float getMcKqmdzhxs() {
        return mcKqmdzhxs;
    }

    public void setMcKqmdzhxs(float mcKqmdzhxs) {
        this.mcKqmdzhxs = mcKqmdzhxs;
    }

    public int getmSczs() {
        return mSczs;
    }

    public void setmSczs(int mSczs) {
        this.mSczs = mSczs;
    }

    public float getMcZszhxs() {
        return mcZszhxs;
    }

    public void setMcZszhxs(float mcZszhxs) {
        this.mcZszhxs = mcZszhxs;
    }

    public int getmEdzs() {
        return mEdzs;
    }

    public void setmEdzs(int mEdzs) {
        this.mEdzs = mEdzs;
    }

    public double getMgFl() {
        return mgFl;
    }

    public void setMgFl(double mgFl) {
        this.mgFl = mgFl;
    }

    public float getMgZgl1() {
        return mgZgl1;
    }

    public void setMgZgl1(float mgZgl1) {
        this.mgZgl1 = mgZgl1;
    }

    public float getMgZgl2() {
        return mgZgl2;
    }

    public void setMgZgl2(float mgZgl2) {
        this.mgZgl2 = mgZgl2;
    }

    public float getMgFjjy() {
        return mgFjjy;
    }

    public void setMgFjjy(float mgFjjy) {
        this.mgFjjy = mgFjjy;
    }

    public float getMgJygl() {
        return mgJygl;
    }

    public void setMgJygl(float mgJygl) {
        this.mgJygl = mgJygl;
    }

    public float getMgFjqy() {
        return mgFjqy;
    }

    public void setMgFjqy(float mgFjqy) {
        this.mgFjqy = mgFjqy;
    }

    public float getMgQygl() {
        return mgQygl;
    }

    public void setMgQygl(float mgQygl) {
        this.mgQygl = mgQygl;
    }

    public float getMgJyxl() {
        return mgJyxl;
    }

    public void setMgJyxl(float mgJyxl) {
        this.mgJyxl = mgJyxl;
    }

    public float getMgQyxl() {
        return mgQyxl;
    }

    public void setMgQyxl(float mgQyxl) {
        this.mgQyxl = mgQyxl;
    }

    public float getUab() {
        return Uab;
    }

    public void setUab(float uab) {
        Uab = uab;
    }

    public float getIa() {
        return Ia;
    }

    public void setIa(float ia) {
        Ia = ia;
    }

    public float getUbc() {
        return Ubc;
    }

    public void setUbc(float ubc) {
        Ubc = ubc;
    }

    public float getIb() {
        return Ib;
    }

    public void setIb(float ib) {
        Ib = ib;
    }

    public float getUca() {
        return Uca;
    }

    public void setUca(float uca) {
        Uca = uca;
    }

    public float getIc() {
        return Ic;
    }

    public void setIc(float ic) {
        Ic = ic;
    }

    public float getPjU() {
        return pjU;
    }

    public void setPjU(float pjU) {
        this.pjU = pjU;
    }

    public float getPjI() {
        return pjI;
    }

    public void setPjI(float pjI) {
        this.pjI = pjI;
    }

    public void setDjgl(float djgl) {
        this.djgl = djgl;
    }

    public float getZgl() {
        return zgl;
    }

    public void setZgl(float zgl) {
        this.zgl = zgl;
    }

    public float getScgl() {
        return scgl;
    }

    public void setScgl(float scgl) {
        this.scgl = scgl;
    }

    public void setDjxl(float djxl) {
        this.djxl = djxl;
    }

    public float getZhxl() {
        return zhxl;
    }

    public void setZhxl(float zhxl) {
        this.zhxl = zhxl;
    }

    public float getGlys() {
        return glys;
    }

    public void setGlys(float glys) {
        this.glys = glys;
    }

    public float getFzxs() {
        return fzxs;
    }

    public void setFzxs(float fzxs) {
        this.fzxs = fzxs;
    }

    public String getYxzt() {
        return yxzt;
    }

    public void setYxzt(String yxzt) {
        this.yxzt = yxzt;
    }

    public float getUab2() {
        return Uab2;
    }

    public void setUab2(float uab2) {
        Uab2 = uab2;
    }

    public float getIa2() {
        return Ia2;
    }

    public void setIa2(float ia2) {
        Ia2 = ia2;
    }

    public float getUbc2() {
        return Ubc2;
    }

    public void setUbc2(float ubc2) {
        Ubc2 = ubc2;
    }

    public float getIb2() {
        return Ib2;
    }

    public void setIb2(float ib2) {
        Ib2 = ib2;
    }

    public float getUca2() {
        return Uca2;
    }

    public void setUca2(float uca2) {
        Uca2 = uca2;
    }

    public float getIc2() {
        return Ic2;
    }

    public void setIc2(float ic2) {
        Ic2 = ic2;
    }

    public float getPjU2() {
        return pjU2;
    }

    public void setPjU2(float pjU2) {
        this.pjU2 = pjU2;
    }

    public float getPjI2() {
        return pjI2;
    }

    public void setPjI2(float pjI2) {
        this.pjI2 = pjI2;
    }

    public void setDjgl2(float djgl2) {
        this.djgl2 = djgl2;
    }

    public void setZgl2(float zgl2) {
        this.zgl2 = zgl2;
    }

    public float getScgl2() {
        return scgl2;
    }

    public void setScgl2(float scgl2) {
        this.scgl2 = scgl2;
    }

    public void setDjxl2(float djxl2) {
        this.djxl2 = djxl2;
    }

    public float getZhxl2() {
        return zhxl2;
    }

    public void setZhxl2(float zhxl2) {
        this.zhxl2 = zhxl2;
    }

    public float getGlys2() {
        return glys2;
    }

    public void setGlys2(float glys2) {
        this.glys2 = glys2;
    }

    public float getFzxs2() {
        return fzxs2;
    }

    public void setFzxs2(float fzxs2) {
        this.fzxs2 = fzxs2;
    }

    public String getYxzt2() {
        return yxzt2;
    }

    public void setYxzt2(String yxzt2) {
        this.yxzt2 = yxzt2;
    }

    public String getMff() {
        return mff;
    }

    public void setMff(String mff) {
        this.mff = mff;
    }


    public float getDjgl() {
        return djgl;
    }

    public float getDjxl() {
        return djxl;
    }

    public String getDj1qblc() {
        return dj1qblc;
    }

    public void setDj1qblc(String dj1qblc) {
        this.dj1qblc = dj1qblc;
    }

    public String getDj1csff() {
        return dj1csff;
    }

    public void setDj1csff(String dj1csff) {
        this.dj1csff = dj1csff;
    }

    public float getDj1cdxl() {
        return dj1cdxl;
    }

    public void setDj1cdxl(float dj1cdxl) {
        this.dj1cdxl = dj1cdxl;
    }

    public float getDjgl2() {
        return djgl2;
    }

    public float getZgl2() {
        return zgl2;
    }

    public Boolean getMventi() {
        return mventi;
    }

    public void setMventi(Boolean mventi) {
        this.mventi = mventi;
    }

    public float getDjxl2() {
        return djxl2;
    }

    public String getDj2qblc() {
        return dj2qblc;
    }

    public void setDj2qblc(String dj2qblc) {
        this.dj2qblc = dj2qblc;
    }

    public String getDj2csff() {
        return dj2csff;
    }

    public void setDj2csff(String dj2csff) {
        this.dj2csff = dj2csff;
    }

    public float getDj2cdxl() {
        return dj2cdxl;
    }

    public void setDj2cdxl(float dj2cdxl) {
        this.dj2cdxl = dj2cdxl;
    }

    public float getUa() {
        return Ua;
    }

    public void setUa(float ua) {
        Ua = ua;
    }

    public float getUb() {
        return Ub;
    }

    public void setUb(float ub) {
        Ub = ub;
    }

    public float getUc() {
        return Uc;
    }

    public void setUc(float uc) {
        Uc = uc;
    }

    public float getUa2() {
        return Ua2;
    }

    public void setUa2(float ua2) {
        Ua2 = ua2;
    }

    public float getUb2() {
        return Ub2;
    }

    public void setUb2(float ub2) {
        Ub2 = ub2;
    }

    public float getUc2() {
        return Uc2;
    }

    public void setUc2(float uc2) {
        Uc2 = uc2;
    }


}
