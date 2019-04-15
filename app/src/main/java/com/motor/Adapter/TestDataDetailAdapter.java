package com.motor.Adapter;

import android.app.Activity;
import android.app.UiAutomation;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.greendao.dbUtils.GreateTaskUtils;
import com.greendao.manager.DataTFJ;
import com.greendao.manager.motorData;
import com.motor.Tools.MyFunction;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.app.MyApp;
import com.motor.bean.GridBean;
import com.motor.test.TestActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import es.dmoral.toasty.Toasty;


/**
 * 测试 --> 参数  --->  listView 适配器
 */

public class TestDataDetailAdapter extends BaseAdapter {
    private DataTFJ mdata;
    private Activity mContext;
    private List<GridBean> list;
    private Callback mCallback;
    private int type;

    DecimalFormat df0 = new DecimalFormat("####0");
    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");


    private String functionStr;
    //	private final TaskEnityDao taskDaoUtils;

    public interface Callback {
        public void click(DataTFJ mdata);
    }

    public void setListViewType() {

        switch (type) {
            case 0://电机1参数
                List<GridBean> dj1List = new ArrayList<>();
                if (mdata.GetHisTask() != null) {


                        dj1List.add(getTSGridItem("平均电压", df1.format(mdata.getPjU()) + " V", "平均电流", df1.format(mdata.getPjI()) + " A"));
                        dj1List.add(getTSGridItem("AB线电压", df1.format(mdata.getUab()) + " V", "A相电流", df1.format(mdata.getIa()) + " A"));
                        dj1List.add(getTSGridItem("BC线电压", df1.format(mdata.getUbc()) + " V", "B相电流", df1.format(mdata.getIb()) + " A"));
                        dj1List.add(getTSGridItem("CA线电压", df1.format(mdata.getUca()) + " V", "C相电流", df1.format(mdata.getIc()) + " A"));

                        dj1List.add(getTSGridItem("电机功率", df1.format(mdata.getDjgl()) + " kW", "电机效率", df1.format( mdata.getDjxl()) + " %"));
                        dj1List.add(getTSGridItem("负载系数", df3.format(mdata.getFzxs()) + " ", "输出功率", df1.format(mdata.getScgl()) + " kW"));
                        dj1List.add(getTSGridItem("综合效率", df1.format(mdata.getZhxl()) + " %", "功率因数", df3.format(mdata.getGlys()) + " "));
                        dj1List.add(getTSGridItem("运行状态", mdata.getYxzt() + " ", "", ""));

                }
                this.list = dj1List;
                break;



            case 2: // 工况测量值
                List<GridBean> gk1list = new ArrayList<>();
                gk1list.add(getTSGridItem("温度", df1.format(mdata.getmWd()) + " ℃", "平均风速", df1.format(mdata.getmPjfs()) + " m/s"));
                gk1list.add(getTSGridItem("湿度", df1.format(mdata.getmSd()) + " %RH", "静压", df1.format(mdata.getmJy()) + " Pa"));
                try {
                    if (mdata.getMff().equals("静压全压法")) {
                        gk1list.add(getTSGridItem("大气压", df1.format(mdata.getmDqy()) + " hPa", "动压", df1.format(mdata.getmDy()) + " Pa"));
                    } else if (mdata.getMff().equals("静压差法")) {
                        gk1list.add(getTSGridItem("大气压", df1.format(mdata.getmDqy()) + " hPa", "静压差", df1.format(mdata.getmJyc()) + " Pa"));
                    } else {
                        gk1list.add(getTSGridItem("大气压", df1.format(mdata.getmDqy()) + " hPa", "", ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.list = gk1list;
                break;
            case 3: // 计算值
                List<GridBean> gk2list = new ArrayList<>();
                gk2list.add(getTSGridItem("风量", df1.format(mdata.getmFl()) + " m³/s", "空气密度", df2.format(mdata.getmKqmd()) + " kg/m³"));
                gk2list.add(getTSGridItem("饱和蒸气压", df1.format(mdata.getmBhzqy()) + " Pa", "扩散器出口动压", df1.format(mdata.getmKsckdy()) + " Pa"));
                gk2list.add(getTSGridItem("轴功率1", df1.format(mdata.getmZgl1()) + " kW", "轴功率2", df1.format(mdata.getmZgl2()) + " kW"));
                gk2list.add(getTSGridItem("风机静压", df1.format(mdata.getmFjjy()) + " Pa", "风机全压", df1.format(mdata.getmFjqy()) + " Pa"));
                gk2list.add(getTSGridItem("静压功率", df1.format(mdata.getmJygl()) + " kW", "全压功率", df1.format(mdata.getmQygl()) + " kW"));
                gk2list.add(getTSGridItem("静压效率", df1.format(mdata.getmJyxl()) + " %", "全压效率", df1.format(mdata.getmQyxl()) + " %"));
                gk2list.add(getTSGridItem("风机运行效率", df1.format(mdata.getmFjyxxl()) + " %", "工序能耗", df1.format(mdata.getmGxnh()) + "\n kW·h/(m³·MPa)"));

                this.list = gk2list;
                break;
            case 4://换算系数
                List<GridBean> bkhs1list = new ArrayList<>();
                bkhs1list.add(getTSGridItem("标准空气密度", "1.29 kg/m³", "额定转速", df0.format(mdata.getmEdzs()) + " r/min"));
                bkhs1list.add(getTSGridItem("实测空气密度", df2.format(mdata.getmKqmd()) + " kg/m³", "实测转速", df0.format(mdata.getmSczs()) + " r/min"));
                bkhs1list.add(getTSGridItem("空气密度转换系数", df1.format(mdata.getMcKqmdzhxs()) + " ", "转速转换系数", df1.format(mdata.getMcZszhxs()) + " "));
                this.list = bkhs1list;
                break;
            case 5://换算后
                List<GridBean> bkhshlist = new ArrayList<>();
                bkhshlist.add(getTSGridItem("风量", df1.format(mdata.getMgFl()) + " m³/s", "", ""));
                bkhshlist.add(getTSGridItem("轴功率1", df1.format(mdata.getMgZgl1()) + " kW", "轴功率2", df1.format(mdata.getMgZgl2()) + " kW"));
                bkhshlist.add(getTSGridItem("风机静压", df1.format(mdata.getMgFjjy()) + " Pa", "风机全压", df1.format(mdata.getMgFjqy()) + " Pa"));
                bkhshlist.add(getTSGridItem("静压功率", df1.format(mdata.getMgJygl()) + " kW", "全压功率", df1.format(mdata.getMgQygl()) + " kW"));
                bkhshlist.add(getTSGridItem("静压效率", df1.format(mdata.getMgJyxl()) + " %", "全压效率", df1.format(mdata.getMgQyxl()) + " %"));
                this.list = bkhshlist;
                break;
            case 6://参数
                List<GridBean> paramlist = new ArrayList<>();
                paramlist.add(getTSGridItem("测风面积", df1.format(mdata.getmCfmj()) + " ㎡", "扩散器出口面积", df1.format(mdata.getmKsckmj()) + " ㎡"));

                paramlist.add(getTSGridItem("电机1功率", df1.format(mdata.getmDjgl()) + " kW", "电机2功率", df1.format(mdata.getmDjgl2()) + " kW"));
                paramlist.add(getTSGridItem("电机1效率", df1.format(mdata.getmDjxl()) + " %", "电机2效率", df1.format(mdata.getmDjxl2()) + " %"));
                if (mdata.getMff().equals("静压全压法")) {
                    paramlist.add(getTSGridItem("皮托管系数", df1.format(mdata.getmPtgxs()) + " ", "", ""));
                } else if (mdata.getMff().equals("静压差法")) {
                    paramlist.add(getTSGridItem("测压面积小", df1.format(mdata.getmCymjx()) + " ㎡", "测压面积大", df1.format(mdata.getmCymjd()) + " ㎡"));
                }
                this.list = paramlist;

        }
    }


    public TestDataDetailAdapter(Activity mContext, DataTFJ mdata, int type, Callback callback) {
        super();
        this.mContext = mContext;
        this.type = type;
        this.mdata = mdata;
        list = new ArrayList<>();
        mCallback = callback;
        setListViewType();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_testdetail, parent, false);
            viewHolder.textView0 = convertView.findViewById(R.id.tv_item_tab0);
            viewHolder.textView1 = convertView.findViewById(R.id.tv_item_tab1);
            viewHolder.textView2 = convertView.findViewById(R.id.tv_item_tab2);
            viewHolder.textView3 = convertView.findViewById(R.id.tv_item_tab3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GridBean gridBean = list.get(position);
        viewHolder.textView0.setText(gridBean.getStr0());
        viewHolder.textView1.setText(gridBean.getStr1());
        viewHolder.textView2.setText(gridBean.getStr2());
        viewHolder.textView3.setText(gridBean.getStr3());

        int mcolor = R.color.gray;
        if (type == 0) {
            if (position == 0) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView1.setClickable(true);
            }
        }
        if (type == 1) {
            if (position == 0) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView1.setClickable(true);
            }
        }

        if (type == 2) {
            if (position == 0) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView1.setClickable(true);
            }
        }
        if (type == 2) {
            if (position == 1) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView1.setClickable(true);
            }
        }
        if (type == 2) {
            if (position == 2) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView1.setClickable(true);
            }
        }
        if (type == 6) {
            if (position == 0) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView1.setClickable(true);
            }
        }
        if (type == 6) {
            if (position == 3) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView1.setClickable(true);
            }
        }


        if (type == 0) {
            if (position == 0) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }
        if (type == 1) {
            if (position == 0) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }
        if (type == 2) {
            if (position == 1) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }
        if (type == 2) {
            if (position == 2) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }
        if (type == 4) {
            if (position == 0) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }
        if (type == 4) {
            if (position == 1) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }
        if (type == 6) {
            if (position == 0) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }
        if (type == 6) {
            if (position == 3) {
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(mcolor));
                viewHolder.textView3.setClickable(true);
            }
        }



        viewHolder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (type) {
                    case 6:
                        switch (position) {


                            case 0://测风面积
                                dialog("测风面积", df1.format(mdata.getmCfmj()), " ㎡", 601);
                                break;

                            case 3:
                                if (mdata.getMff().equals("静压全压法")) {
                                    dialog("皮托管系数", df3.format(mdata.getmPtgxs()), " ", 6111);
                                } else if (mdata.getMff().equals("静压差法")) {
                                    dialog("测压面积小", df1.format(mdata.getmCymjx()), " ㎡", 6112);
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (position) {
                            case 0://温度
                                dialog("温度", df1.format(mdata.getmWd()), " ℃", 201);
                                break;
                            case 1://湿度
                                dialog("湿度", df1.format(mdata.getmSd()), " ", 211);
                                break;
                            case 2://大气压
                                dialog("大气压", df1.format(mdata.getmDqy()), " hPa", 221);
                                break;
                        }
                        break;
                    case 0:
                        switch (position) {
                            case 0://平均电压
                                dialog("平均电压", df1.format(mdata.getPjU()), " V", 001);
                                break;
                        }
                        break;
                    case 1:
                        switch (position) {
                            case 0://平均电压
                                dialog("平均电压", df1.format(mdata.getPjU2()), " V", 101);
                                break;
                        }
                        break;


                }
            }
        });


        // 设置每个Item的第postion == 3 的元素的点击事件

        viewHolder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (type) {
                    case 2:
                        switch (position) {
                            case 0: //风速
                                dialog("风速", df1.format(mdata.getmPjfs()), " m/s", 203);
                                break;
                            case 1: //静压
                                dialog("静压", df1.format(mdata.getmJy()), " Pa", 213);
                                break;
                            case 2: //差压
                                if (mdata.getMff().equals("静压全压法")) {
                                    dialog("动压", df3.format(mdata.getmDy()), " Pa", 2231);
                                } else if (mdata.getMff().equals("静压差法")) {
                                    dialog("静压差", df1.format(mdata.getmJyc()), " Pa", 2232);
                                }
                                break;
                        }
                        break;
                    case 4: //输入参数
                        switch (position) {
                            case 0://额定转速
                                dialog("额定转速", df0.format(mdata.getmEdzs()), " r/min", 403);
                                break;
                            case 1://实测转速
                                dialog("实测转速", df0.format(mdata.getmSczs()), " r/min", 413);
                                break;
                        }
                        break;
                    case 6://
                        switch (position) {
                            case 0://扩散出口面积
                                dialog("扩散器出口面积", df1.format(mdata.getmKsckdy()), " ㎡", 603);
                                break;


                            case 3://电机2效率
                                if (mdata.getMff().equals("静压差法")) {
                                    dialog("测压面积大", df1.format(mdata.getmCymjd()), " ㎡", 613);
                                }
                                break;
                        }
                        break;
                    case 0:
                        switch (position) {
                            case 0://平均电流
                                dialog("平均电流", df1.format(mdata.getPjI()), " A", 003);
                                break;
                        }
                        break;
                    case 1:
                        switch (position) {
                            case 0://平均电流
                                dialog("平均电流", df1.format(mdata.getPjI2()), " A", 103);
                                break;
                        }
                        break;

                }
            }
        });


        return convertView;
    }

    private class ViewHolder {
        TextView textView0;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }


    /**
     * 返回 GridBean
     *
     * @param item0
     * @param item1
     * @param item2
     * @param item3
     * @return
     */
    private GridBean getTSGridItem(String item0, String item1, String item2, String item3) {
        GridBean gridBean = new GridBean();
        gridBean.setStr0(item0);
        gridBean.setStr1(item1);
        gridBean.setStr2(item2);
        gridBean.setStr3(item3);
        return gridBean;
    }

    /**
     * @param parNameStr
     * @param parStr
     * @param unitStr
     * @param parType
     */
    private void dialog(String parNameStr, String parStr, String unitStr, final int parType) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(mContext);
        final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_one_edittext, null);
        TextView nameTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_name);
        final EditText inputEdittext = (EditText) dialogView.findViewById(R.id.et_dialog_input);
        TextView unitTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_unit);
        nameTextView.setText(parNameStr + ":");
        inputEdittext.setText(parStr);
        unitTextView.setText(unitStr);
        customizeDialog.setTitle("参数修改");
        customizeDialog.setView(dialogView);
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        String inputStr = inputEdittext.getText().toString().trim();
                        boolean notNull = inputStr.length() > 0 ? true : false;
                        float inputData = notNull ? Float.parseFloat(inputStr) : -100f;
                        switch (parType) {
                            case 001://电机1平均电压
                                if (notNull && inputData >= 0 && inputData <=6000 ) {
                                    float pre = mdata.getPjU();
                                    float now = Float.parseFloat(inputStr);
                                    ReCalMotor(0, now - pre);
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 6000 V", 5).show();
                                }
                                break;
                            case 003://电机1平均电流
                                if (notNull && inputData >= 0 && inputData <=6000 ) {
                                    float pre = mdata.getPjI();
                                    float now = Float.parseFloat(inputStr);
                                    ReCalMotor(now - pre, 0);
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 1000 A", 5).show();
                                }
                                break;
                        }
                        setListViewType();
                        notifyDataSetChanged();
                        mCallback.click(mdata);
                    }
                });
        customizeDialog.show();
    }



    private void ReCalMotor(float changei, float changeu) {
        changeu = (float) (changeu / (Math.sqrt(3)));
        float Ua = mdata.getUa() + changeu;
        float Ia = mdata.getIa() + changei;
        float Ub = mdata.getUb() + changeu;
        float Ib = mdata.getIb() + changei;
        float Uc = mdata.getUc() + changeu;
        float Ic = mdata.getIc() + changei;
        float Cos = mdata.getGlys();
        float aszgl = (Ua * Ia
                // / 1000
        );
        float ayggl = (aszgl * Cos);
        float awggl = (float) (Math.sqrt(Math.pow(aszgl, 2)
                - Math.pow(ayggl, 2)));

        float bszgl = (Ub * Ib
                // / 1000
        );
        float byggl = (bszgl * Cos);
        float bwggl = (float) (Math.sqrt(Math.pow(bszgl, 2)
                - Math.pow(byggl, 2)));

        float cszgl = (Uc * Ic
                // / 1000
        );
        float cyggl = (cszgl * Cos);
        float cwggl = (float) (Math.sqrt(Math.pow(cszgl, 2)
                - Math.pow(cyggl, 2)));


        float S = (aszgl + bszgl + cszgl) / 1000;
        float P = S * Cos;
        float Q = (float) Math.sqrt(Math.pow(S, 2) - Math.pow(P, 2));

        float cs = (float) Math.sqrt(3);
        float u0 = (Ua + Ub + Uc) / 3;
        float i0 = (Ia + Ib + Ic) / 3;

        mdata.setUab(cs * Ua);
        mdata.setIa(Ia);
        mdata.setUca(cs * Uc);
        mdata.setIc(Ic);
        mdata.setUbc(cs * Ub);
        mdata.setIb(Ib);
        mdata.setPjI((i0));
        mdata.setPjU(cs * u0);
        mdata.setGlys(Cos);
        mdata.setDjgl(P);
        mdata.setUa(Ua);
        mdata.setUb(Ub);
        mdata.setUc(Uc);


        TaskEntity mtask = mdata.GetHisTask();
        motorData mmotordata = new motorData();
        try {
            mmotordata.setEddl(Double.parseDouble(mtask.getDjeddl1()));
            mmotordata.setEddy(Double.parseDouble(mtask.getDjeddy1()));
            mmotordata.setEdgl(Double.parseDouble(mtask.getDjedgl1()));
            mmotordata.setEdxl(Double.parseDouble(mtask.getDjedxl1()));
            mmotordata.setJs(Integer.parseInt(mtask.getDjjs1()));
            mmotordata.setKzdl(Double.parseDouble(mtask.getDjkzdl1()));
            mmotordata.setKzgl(Double.parseDouble(mtask.getDjkzgl1()));
            mmotordata.setWgjjdl(Double.parseDouble(mtask.getDjwgjjdl1()));
            mmotordata.setUA(Ua);
            mmotordata.setUB(Ub);
            mmotordata.setUC(Uc);
            mmotordata.setUAB(cs * Ua);
            mmotordata.setUBC(cs * Ub);
            mmotordata.setUCA(cs * Uc);
            mmotordata.setIA(Ia);
            mmotordata.setIB(Ib);
            mmotordata.setIC(Ic);
            mmotordata.setYggl(P);
            mmotordata.setWggl(Q);
            mmotordata.setSzgl(S);


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mmotordata.Calculate();
        mdata.setDjxl((float) mmotordata.getXl());
        mdata.setFzxs((float) mmotordata.getFzxs());
        mdata.setScgl((float) mmotordata.getScgl());
        mdata.setZhxl((float) mmotordata.getZhxl());
        mdata.setYxzt(mmotordata.getstrDjyxzt());
    }
}
