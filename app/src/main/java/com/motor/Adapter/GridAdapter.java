package com.motor.Adapter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.greendao.dbUtils.GreateTaskUtils;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.bean.GridBean;
import com.motor.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


/**
 * 测试 --> 参数  --->  listView 适配器
 */

public class GridAdapter extends BaseAdapter {
    private TestActivity mContext;
    private List<GridBean> list;
    private int type;
    private TaskEntity taskParInf;
    private Callback mCallback;
    private String functionStr;

    //	private final TaskEnityDao taskDaoUtils;
    public interface Callback {
        public void click();
    }

    public void setListViewType() {
        switch (type) {
            case 0://任务信息
                List<GridBean> taskInfList = new ArrayList<>();
                taskInfList.add(getGridItem("受检单位", taskParInf.getUnitName(), "电机编号", taskParInf.getNumber()));
                taskInfList.add(getGridItem("测试人员", taskParInf.getPeopleName(), "测试方法", taskParInf.getTestFunction()));
                taskInfList.add(getGridItem("创建日期", taskParInf.getGreateTaskTime(), "", ""));
//                taskInfList.add(getGridItem("电压变比", taskParInf.getDybb1() + ":" + taskParInf.getDybb2(), "电流变比", taskParInf.getDlbb1() + ":" + taskParInf.getDlbb2()));
                this.list = taskInfList;
                break;

            case 1: // 电机参数
                List<GridBean> djInfList = new ArrayList<>();
                djInfList.add(getGridItem("电机型号", taskParInf.getDjk1(), "", ""));
                djInfList.add(getGridItem("钳表量程", taskParInf.getDjqblc1(), "测试方法", taskParInf.getDjcsff1()));

                this.list = djInfList;
                break;

            case 3: // 输入的参数
                List<GridBean> inputInfList = new ArrayList<>();
//                String fsStr = taskParInf.getSdfs() ? taskParInf.getFs() : "--";
//                String wdStr = taskParInf.getSdwd() ? taskParInf.getWd() : "--";
//                String jyStr = taskParInf.getSdjy() ? taskParInf.getJy() : "--";
//                String sdStr = taskParInf.getSdsd() ? taskParInf.getSd() : "--";
//                String cyStr = taskParInf.getSdcy() ? taskParInf.getCy() : "--";
//                String dqyStr = taskParInf.getSddqy() ? taskParInf.getDqy() : "--";
//                String dj1glStr = taskParInf.getSddj1gl() ? taskParInf.getDj1gl() : "--";
//                String dj2glStr = taskParInf.getSddj2gl() ? taskParInf.getDj2gl() : "--";
//                String dj1xlStr = taskParInf.getSddj1xl() ? taskParInf.getDj1xl() : "--";
//                String dj2xlStr = taskParInf.getSddj2xl() ? taskParInf.getDj2xl() : "--";

                inputInfList.add(getGridItem("电压变比", taskParInf.getDybb1() + ":" + taskParInf.getDybb2(), "电流变比", taskParInf.getDlbb1() + ":" + taskParInf.getDlbb2()));
                inputInfList.add(getGridItem("额定电压", taskParInf.getDjeddy1() + " V", "额定电流", taskParInf.getDjeddl1() + " A"));
                inputInfList.add(getGridItem("额定功率", taskParInf.getDjedgl1() + " kW", "额定效率", taskParInf.getDjedxl1() + " %"));
                inputInfList.add(getGridItem("空载功率", taskParInf.getDjkzgl1() + " kW", "空载电流", taskParInf.getDjkzdl1() + " A"));
                inputInfList.add(getGridItem("级       数", taskParInf.getDjjs1() + " ", "无功\n经济当量", taskParInf.getDjwgjjdl1() + " "));
                inputInfList.add(getGridItem("目标\n功率因数", taskParInf.getBy1() + " ", "", ""));
                this.list = inputInfList;
                break;

        }
    }


    public GridAdapter(TestActivity mContext, TaskEntity taskParInf, int type, Callback callback) {
        super();
        this.mContext = mContext;
        this.type = type;
        this.taskParInf = taskParInf;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_parameter, parent, false);
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

        //任务信息
        if (type == 0) {
            if (position == 0 || position == 1 || position == 2) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.color_radiobutton_text));
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.color_radiobutton_text));
                viewHolder.textView1.setClickable(false);
                viewHolder.textView3.setClickable(false);
            }
        }


        //设置电机型号不可点击
        if (type == 1) {
            if (position == 0) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                viewHolder.textView1.setClickable(false);
            }
        }


        //输入
        if (type == 3) {
            viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.input));
            viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.input));
            viewHolder.textView1.setEnabled(true);
            viewHolder.textView3.setEnabled(true);

        }

        // 设置每个Item的第postion == 1 的元素的点击事件
        viewHolder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = -1;
                switch (type) {
                    case 1: //
                        switch (position) {
                            case 1:// 钳表量程
                                showSingleChoiceDialog(taskParInf.getDjqblc1(), 103);
                                break;
                        }
                        break;

                    case 3: // 输入
                        switch (position) {
                            case 0: //电压变比
                                dialogTwoEt("电压变比", taskParInf.getDybb1(), taskParInf.getDybb2(), " ", 031);
                                break;
                            case 1: //额定电压
                                dialog("额定电压", taskParInf.getDjeddy1(), " V", 311);
                                break;
                            case 2: //额定功率
                                dialog("额定功率", taskParInf.getDjedgl1(), " kW", 321);
                                break;
                            case 3: //空载功率
                                dialog("空载功率", taskParInf.getDjkzgl1(), " kW", 331);
                                break;
                            case 4: //级数
                                dialog("级      数", taskParInf.getDjjs1(), " ", 341);
                                break;
                            case 5: //目标功率因数
                                dialog("目标功率因数", taskParInf.getBy1(), " ", 351);
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
                choice = -1;
                switch (type) {
                    case 0:
                        switch (position) {
                            case 3:
                                break;

                        }
                        break;
                    case 1: //电机参数
                        switch (position) {
                            case 0:
                                break;
                            case 1: //测试方法
                                showSingleChoiceDialog(taskParInf.getDjcsff1(), 111);
                                break;

                        }
                        break;

                    case 3: //输入参数
                        switch (position) {
                            case 0://电流变比
                                dialogTwoEt("电流变比", taskParInf.getDlbb1(), taskParInf.getDlbb2(), " ", 033);
                                break;
                            case 1://额定电流
                                dialog("额定电流", taskParInf.getDjeddl1(), " A", 313);
                                break;
                            case 2://额定效率
                                dialog("额定效率", taskParInf.getDjedxl1(), " %", 323);
                                break;
                            case 3://空载电流
                                dialog("空载电流", taskParInf.getDjkzdl1(), " A", 333);
                                break;
                            case 4://无功经济当量
                                dialog("无功经济当量", taskParInf.getDjwgjjdl1(), " ", 343);
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
//                            case 301: //风速
//                                if (notNull && inputData >= 0 && inputData <= 50) {
//                                    taskParInf.setSdfs(true);
//                                    taskParInf.setFs(inputStr + "");
//                                } else {
//                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 50 m/s", 5).show();
//                                }
//                                break;
//                            case 303: //温度
//                                if (notNull && inputData > 0 && inputData >= -40 && inputData <= 100) {
//                                    taskParInf.setSdwd(true);
//                                    taskParInf.setWd(inputStr + "");
//                                } else {
//                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "-40 ～ 100 ℃", 5).show();
//                                }
//                                break;
                            case 311: //额定电压
                                if (notNull && inputData >= 0 && inputData <= 10000) {

                                    taskParInf.setDjeddy1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 10000 V", 5).show();
                                }
                                break;
                            case 313: //额定电流
                                if (notNull && inputData >= 0 && inputData <= 1000) {

                                    taskParInf.setDjeddl1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 1000 A", 5).show();
                                }
                                break;
                            case 321: //额定功率
                                if (notNull && inputData >= 0 && inputData <= 4000) {

                                    taskParInf.setDjedgl1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 4000 kW", 5).show();
                                }
                                break;
                            case 323: //额定效率
                                if (notNull && inputData >= 0 && inputData <= 100) {

                                    taskParInf.setDjedxl1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 100 %", 5).show();
                                }
                                break;
                            case 331: //空载功率
                                if (notNull && inputData >= 0 && inputData <= 100) {

                                    taskParInf.setDjkzgl1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 100 kW", 5).show();
                                }
                                break;
                            case 333: //空载电流
                                if (notNull && inputData >= 0 && inputData <= 400) {

                                    taskParInf.setDjkzdl1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 400 A", 5).show();
                                }
                                break;
                            case 341: //级数
                                if (notNull && inputData >= 0 && inputData <= 20) {

                                    taskParInf.setDjjs1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 20", 5).show();
                                }
                                break;
                            case 343: //无功经济当量
                                if (notNull && inputData >= 0.02 && inputData <= 0.1) {

                                    taskParInf.setDjwgjjdl1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0.02 ～ 0.1", 5).show();
                                }
                                break;
                            case 351: //目标功率因数
                                if (notNull && inputData >= 0 && inputData <= 1) {

                                    taskParInf.setBy1(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 1 ", 5).show();
                                }
                                break;


                        }
                        GreateTaskUtils.update(taskParInf);
                        mCallback.click();
                        setListViewType();
                        Toasty.success(mContext, "参数已修改", 5).show();
                        notifyDataSetChanged();
                    }
                });
        customizeDialog.show();
    }


    private void dialogTwoEt(String parNameStr, String parOneStr, String parTwoStr, String unitStr, final int parType) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(mContext);
        final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_two_edittext, null);
        TextView nameTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_name);
        final EditText inputOneEdittext = (EditText) dialogView.findViewById(R.id.et_dialog_input_one);
        final EditText inputTwoEdittext = (EditText) dialogView.findViewById(R.id.et_dialog_input_two);
        TextView unitTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_unit);

        nameTextView.setText(parNameStr);
        inputOneEdittext.setText(parOneStr);
        inputTwoEdittext.setText(parTwoStr);
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
                        switch (parType) {
                            case 031:  //  电压变比
                                if (!TextUtils.isEmpty(inputOneEdittext.getText())) {
                                    taskParInf.setDybb1(inputOneEdittext.getText().toString().trim());
                                } else {
                                    taskParInf.setDybb1("1");
                                }

                                if (!TextUtils.isEmpty(inputTwoEdittext.getText())) {
                                    if (Float.parseFloat(inputTwoEdittext.getText().toString()) == 0) {
                                        Toasty.error(mContext, "输入了错误的参数" + "\n" + "变比分母不可为0！", 5).show();
                                    } else {
                                        taskParInf.setDybb2(inputTwoEdittext.getText().toString().trim());
                                    }
                                } else {
                                    taskParInf.setDybb2("1");
                                }

                                break;

                            case 033: // 电流变比

                                if (!TextUtils.isEmpty(inputOneEdittext.getText())) {
                                    taskParInf.setDlbb1(inputOneEdittext.getText().toString().trim());
                                } else {
                                    taskParInf.setDlbb1("1");
                                }

                                if (!TextUtils.isEmpty(inputTwoEdittext.getText())) {
                                    if (Float.parseFloat(inputTwoEdittext.getText().toString()) == 0) {
                                        Toasty.error(mContext, "输入了错误的参数" + "\n" + "变比分母不可为0！", 5).show();
                                    } else {
                                        taskParInf.setDlbb2(inputTwoEdittext.getText().toString());
                                    }
                                } else {
                                    taskParInf.setDlbb2("1");
                                }
                                break;

                        }

                        GreateTaskUtils.update(taskParInf);//更新数据
                        mCallback.click();
                        setListViewType();
                        Toasty.success(mContext, "参数已修改", 5).show();
                        notifyDataSetChanged();
                    }
                });
        customizeDialog.show();
    }


    int choice = -1;
    String[] items = new String[]{};

    private void showSingleChoiceDialog(String itemStr, final int typePar) {
        final String[] cshffItems = {"单瓦法", "双瓦法", "三瓦法"};
        final String[] qblchItems = {"500 A", "20 A"};
        final String[] chdfshItems = {"1", "0.99", "0.98", "0.97", "0.96", "0.95", "0.94", "0.93", "0.92", "0.91", "0.90", "0.89", "0.88", "0.87"};
        switch (typePar) {
            case 203: //钳表量程
                items = qblchItems;
                break;
            case 103:
                items = qblchItems;
                break;

            case 211://电机测试方法
                items = cshffItems;
                break;
            case 111:
                items = cshffItems;
                break;

            case 213://传动方式
                items = chdfshItems;
                break;
            case 113:
                items = chdfshItems;
                break;
        }

        AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(mContext);
        singleChoiceDialog.setTitle("选择参数");
        // 第二个参数是默认选项，此处设置为0
        int indexPostion = 0;
        for (int i = 0; i < items.length; i++) {
            if (itemStr.equals(items[i])) {
                indexPostion = i;
            }
        }

        singleChoiceDialog.setSingleChoiceItems(items, indexPostion,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choice = which;
                    }
                });

        singleChoiceDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choice != -1) {
                            String itemStr = items[choice];
                            switch (typePar) {
                                case 103: //钳表量程
                                    taskParInf.setDjqblc1(itemStr);
                                    break;
                                case 111://电机测试方法
                                    taskParInf.setDjcsff1(itemStr);
                                    taskParInf.setCsff(itemStr);
                                    break;


                            }
                        }
                        GreateTaskUtils.update(taskParInf);
                        mCallback.click();
                        setListViewType();
                        Toast.makeText(mContext, "参数已修改", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
        singleChoiceDialog.show();
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
    private GridBean getGridItem(String item0, String item1, String item2, String item3) {
        GridBean gridBean = new GridBean();
        gridBean.setStr0(item0);
        gridBean.setStr1(item1);
        gridBean.setStr2(item2);
        gridBean.setStr3(item3);
        return gridBean;
    }
}
