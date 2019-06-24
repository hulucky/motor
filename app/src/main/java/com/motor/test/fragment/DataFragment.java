package com.motor.test.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greendao.manager.DataTFJ;
import com.greendao.manager.MotorData;
import com.greendao.manager.TaskEntityDao;
import com.greendao.manager.TaskResEnityDao;
import com.motor.Adapter.TestShowPagerAdapter;

import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.administrator.DATAbase.greendao.TaskResEnity;
import com.motor.app.MyApp;
import com.motor.bean.ListBean;
import com.motor.data.DataDetailActivity;
import com.motor.param.ParamSetActivity;
import com.motor.print.PrintActivity;
import com.motor.service.FileService;
import com.motor.test.TestActivity;
import com.motor.test.fragment.DataChildFragment.DataCurvefragment;
import com.motor.test.fragment.DataChildFragment.DataDetailfragment;
import com.motor.view.MyNoScrollViewPager;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//数据fragment
public class DataFragment extends Fragment {

    @BindView(R.id.fr_detailcurve)
    MyNoScrollViewPager flmain;
    @BindView(R.id.btn_testdetail_delete)
    Button btndelete;
    @BindView(R.id.btn_testdetail_curve)
    Button btncurve;
    @BindView(R.id.btn_testdetail_report)
    Button btnreport;
    @BindView(R.id.btn_testdetail_print)
    Button btnprint;
    @BindView(R.id.lv_testdata_list)
    ListView lv_list;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;


    List<ListBean> mbeanlist;
    List<TaskResEnity> mreslist;
    DataCurvefragment mDataCurvefragment;
    DataDetailfragment mDataDetailfragment;
    List<Fragment> list_fragments;
    Activity mActivity;
    TaskEntity mTask;
    MyApp myApp;
    Unbinder unbinder;
    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");
    DecimalFormat df3 = new DecimalFormat("####0.000");
    DecimalFormat df4 = new DecimalFormat("####00.00");

    private FileService fileService;
    long SystemTime = 0;
    List<Long> Res_id;

    private Map<Integer, Integer> selected;

    TestDataListAdapter mAdapter;
    private boolean IsShowCurve = false;
    private ArrayList<DataTFJ> mData;

    public boolean isDataManage() {
        return isDataManage;
    }

    public void setDataManage(boolean dataManage) {
        isDataManage = dataManage;
    }

    boolean isDataManage = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_fragments = new ArrayList<Fragment>();
        mDataCurvefragment = new DataCurvefragment();
        mDataDetailfragment = new DataDetailfragment();
        list_fragments.add(mDataDetailfragment);
        list_fragments.add(mDataCurvefragment);
        mbeanlist = new ArrayList<>();
        selected = new HashMap<Integer, Integer>();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_res_data, null);
        myApp = MyApp.getInstance();
        mTask = myApp.getTaskEnity();
        unbinder = ButterKnife.bind(this, view);
        try {
            mActivity = (TestActivity) getActivity();
        } catch (Exception e) {
            mActivity = (DataDetailActivity) getActivity();
            llBottom.setVisibility(View.GONE);
            e.printStackTrace();
        }

        flmain.setNoScroll(true);
        if (list_fragments != null) {
            TestShowPagerAdapter msgAdapter = new TestShowPagerAdapter(getChildFragmentManager(), list_fragments);
            flmain.setAdapter(msgAdapter);
            flmain.setCurrentItem(0);
        }
        GetData();


        return view;
    }

    public void RefreshList() {
        int IndexSel = lv_list.getSelectedItemPosition();
        InitList();
        lv_list.setSelection(IndexSel);
    }

    public void InitList() {
        mbeanlist = new ArrayList<>();
        Long taskId = mTask.getId();
        TaskResEnityDao mdao = MyApp.getDaoInstant().getTaskResEnityDao();
        mreslist = mdao.queryBuilder().where(TaskResEnityDao.Properties.TaskId.eq(taskId)).list();
        for (TaskResEnity mres : mreslist) {
            ListBean mbean = new ListBean();
            mbean.setId(mres.getId());
            mbean.setStr0(df2.format(mres.getPjxdy()));
            mbean.setStr1(df2.format(mres.getPjdl()));
            mbean.setStr2(df2.format(mres.getYggl()));
            mbean.setStr3(df2.format(mres.getXl()));
            mbean.setStr4("");
            mbean.setStr5(mres.getSaveTime().replace(" ", "\n"));
            mbeanlist.add(mbean);
        }
        mAdapter = new TestDataListAdapter(getActivity(), mbeanlist);

        lv_list.setAdapter(mAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
                mDataDetailfragment.RefreshByRes(mreslist.get(position));
                Log.d("dsas", "onItemClick: " + mreslist.get(position).toString());
                flmain.setCurrentItem(0);
                IsShowCurve = false;
            }
        });
    }

    public void GetData() {
        try {
            if (mTask == null) {
                mTask = MyApp.getInstance().getTaskEnity();
            }
            InitList();
            lv_list.setSelection(0);

            DataTFJ mdata = new DataTFJ();

            mdata.SetResOnly(mreslist.get(0));
            mDataDetailfragment.refresh(mdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        GetData();
        mAdapter = new TestDataListAdapter(getActivity(), mbeanlist);
        lv_list.setAdapter(mAdapter);
    }

    @OnClick({R.id.btn_testdetail_delete, R.id.btn_testdetail_print, R.id.btn_testdetail_curve, R.id.btn_testdetail_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_testdetail_curve:

                Res_id = new ArrayList<Long>();
                for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                    Res_id.add(mreslist.get(entry.getValue()).getId());
                }

                if (Res_id.size() == 0) {
                    Toasty.error(mActivity, "请先勾选生成曲线的数据！", Toast.LENGTH_SHORT, true).show();
                } else {
                    flmain.setCurrentItem(1);
                    mDataCurvefragment.setResIds(Res_id);
                    IsShowCurve = true;
                }
                break;
            case R.id.btn_testdetail_delete:
                if (mreslist != null) {
                    Res_id = new ArrayList<Long>();
                    for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                        Res_id.add(mreslist.get(entry.getValue()).getId());
                    }

                    if (Res_id.size() == 0) {
                        Toasty.error(mActivity, "请先勾选需要删除的数据！", Toast.LENGTH_SHORT, true).show();
                    } else {
                        new AlertDialog.Builder(mActivity)
                                .setTitle("系统提示")
                                // 设置对话框标题

                                .setMessage("确认要删除选中数据？")
                                // 设置显示的内容

                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {// 添加确定按钮

                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {// 确定按钮的响应事件

                                                // TODO Auto-generated method stub
                                                for (Long id : Res_id
                                                        ) {
                                                    MyApp.getDaoInstant().getTaskResEnityDao().deleteByKey(id);
                                                }
                                                InitList();
                                                if (mreslist.size() == 0) {
                                                    if (isDataManage) {
                                                        mActivity.finish();
                                                    }
                                                }

                                            }

                                        })
                                .setNegativeButton("取消",
                                        new DialogInterface.OnClickListener() {// 添加返回按钮

                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {// 响应事件

                                                // TODO Auto-generated method stub

                                            }

                                        }).show();// 在按键响应事件中显示此对话框

                    }
                }

                break;
            case R.id.btn_testdetail_print:

                Res_id = new ArrayList<Long>();
                for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                    Res_id.add(mreslist.get(entry.getValue()).getId());
                }

                if (Res_id.size() == 0) {
                    Toasty.error(mActivity, "请先勾选需要打印的数据！", Toast.LENGTH_SHORT, true).show();
                } else {
                    Intent intent1 = new Intent();
                    intent1.setClass(mActivity, PrintActivity.class);
                    intent1.putExtra("testResID", Res_id.get(0));

                    startActivity(intent1);
                }
                break;
            case R.id.btn_testdetail_report://生成报告
                Res_id = new ArrayList<Long>();
                for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                    Res_id.add(mreslist.get(entry.getValue()).getId());
                }
                if (Res_id.size() == 0) {
                    Toasty.error(mActivity, "请先勾选生成报告的数据！", Toast.LENGTH_SHORT, true).show();
                } else {
                    GetmData(Res_id);
                    updateExcel(mData, SystemTime);
                }
                break;

        }
    }

    public class TestDataListAdapter extends BaseAdapter {

        private Activity mContext;
        private List<ListBean> list;


        public TestDataListAdapter(Activity mContext, List<ListBean> mlist) {
            super();
            this.mContext = mContext;
            this.list = mlist;
            selected = new HashMap<Integer, Integer>();

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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_testdatalistl, parent, false);
                viewHolder.checkBox = convertView.findViewById(R.id.chb_testdetaillist_xh);
                viewHolder.textView0 = convertView.findViewById(R.id.tv_testdatalist_tab0);
                viewHolder.textView1 = convertView.findViewById(R.id.tv_testdatalist_tab1);
                viewHolder.textView2 = convertView.findViewById(R.id.tv_testdatalist_tab2);
                viewHolder.textView3 = convertView.findViewById(R.id.tv_testdatalist_tab3);
                viewHolder.textView4 = convertView.findViewById(R.id.tv_testdatalist_tab4);
                viewHolder.textView5 = convertView.findViewById(R.id.tv_testdatalist_tab5);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ListBean gridBean = list.get(position);
            viewHolder.checkBox.setText((position + 1) + "");
            viewHolder.checkBox.setTag(position);
            viewHolder.textView0.setText(gridBean.getStr0());
            viewHolder.textView1.setText(gridBean.getStr1());
            viewHolder.textView2.setText(gridBean.getStr2());
            viewHolder.textView3.setText(gridBean.getStr3());
            viewHolder.textView4.setText(gridBean.getStr4());
            viewHolder.textView5.setText(gridBean.getStr5());
            viewHolder.textView0.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView1.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView2.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView3.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView4.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView5.setBackgroundResource(R.drawable.listviewselector);
            if (selected.containsKey(position)) {
                viewHolder.checkBox.setChecked(true);
            } else {
                viewHolder.checkBox.setChecked(false);
            }
            viewHolder.checkBox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            // TODO Auto-generated method stub
                            if (isChecked) {
                                if (!selected.containsKey(buttonView.getTag()))
                                    selected.put((Integer) buttonView.getTag(),
                                            position);

                            } else {
                                selected.remove((Integer) buttonView.getTag());
                            }
                            if (selected.size() == 1) {
                                btnprint.setVisibility(View.VISIBLE);
                                btnreport.setVisibility(View.VISIBLE);

                            } else {
                                btnprint.setVisibility(View.GONE);
                                btnreport.setVisibility(View.GONE);
                            }
                            if (selected.size() > 3) {
//                                btncurve.setVisibility(View.VISIBLE);
                            } else {
                                btncurve.setVisibility(View.GONE);
                            }
                        }
                    });


            return convertView;
        }

        private class ViewHolder {
            CheckBox checkBox;
            TextView textView0;
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
            TextView textView5;
        }


        /**
         * 返回 GridBean
         *
         * @param item0
         * @param item1
         * @return
         */
        private ListBean getTSGridItem(String item0, String item1) {
            ListBean gridBean = new ListBean();
            gridBean.setStr0(item0);
            gridBean.setStr1(item1);
            gridBean.setStr2(item0);
            gridBean.setStr3(item1);
            gridBean.setStr4(item0);
            gridBean.setStr5(item1);

            return gridBean;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * jxl��ʱ���ṩ�޸��Ѿ����ڵ����ݱ�,����ͨ��һ��С�취���ﵽ���Ŀ��,���ʺϴ������ݸ���! ������ͨ������ԭ�ļ������µ�.
     */
    public void updateExcel(List<DataTFJ> mDataList, Long systimeLong) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
        String date = sDateFormat.format(new Date());
        try {

            // Workbook workbook = Workbook.getWorkbook(new
            // File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ͨ����ۺϲ�����/.SystemFiles/SystemFiles/flow(��ֹɾ��).ky"));
            Workbook workbook = Workbook.getWorkbook(new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/CDZ11W矿用电机无线多参数测试仪/.报告模板/电机报告.xls"));

            WritableWorkbook writableWorkbook = Workbook.createWorkbook(
                    new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath()
                            + "/CDZ11W矿用电机无线多参数测试仪/测试报告/"
                            + mTask.getUnitName()
                            + "_"
                            + mTask.getNumber()
                            + "_"
                            + date
                            + ".xls"), workbook);// copy
            WritableSheet ws4 = writableWorkbook.getSheet(4);
            MotorData mData = mDataList.get(0).getMotordata();


            WritableCell wc;
            Label l;


            wc = ws4.getWritableCell(5, 3);
            l = (Label) wc;
            l.setString(df2.format(mData.getEddy()));
            wc = ws4.getWritableCell(5, 4);
            l = (Label) wc;
            l.setString(df2.format(mData.getEddl()));
            wc = ws4.getWritableCell(5, 5);
            l = (Label) wc;
            l.setString(df2.format(mData.getEdgl()));
            wc = ws4.getWritableCell(5, 6);
            l = (Label) wc;
            l.setString(df2.format(mData.getEdxl()));
            wc = ws4.getWritableCell(5, 7);
            l = (Label) wc;
            l.setString(df2.format(mData.getKzdl()));
            wc = ws4.getWritableCell(5, 8);
            l = (Label) wc;
            l.setString(df2.format(mData.getKzgl()));
            wc = ws4.getWritableCell(5, 9);
            l = (Label) wc;
            l.setString(df2.format(mData.getWgjjdl()));
            wc = ws4.getWritableCell(5, 10);
            l = (Label) wc;
            l.setString(df2.format(mData.getEdzhxl()));
            wc = ws4.getWritableCell(5, 11);
            l = (Label) wc;
            l.setString(df2.format(mData.getEdzhxl() * 0.6));
            wc = ws4.getWritableCell(5, 12);
            l = (Label) wc;
            l.setString(df2.format(mData.getEdglys()));
            wc = ws4.getWritableCell(5, 13);
            l = (Label) wc;
            l.setString(df2.format(mData.getUA()));
            wc = ws4.getWritableCell(5, 14);
            l = (Label) wc;
            l.setString(df2.format(mData.getUB()));
            wc = ws4.getWritableCell(5, 15);
            l = (Label) wc;
            l.setString(df2.format(mData.getUC()));
            wc = ws4.getWritableCell(5, 16);
            l = (Label) wc;
            l.setString(df2.format(mData.getPjdy()));
            wc = ws4.getWritableCell(5, 17);
            l = (Label) wc;
            l.setString(df2.format(mData.getSxbphd()));
            wc = ws4.getWritableCell(5, 18);
            l = (Label) wc;
            l.setString(df2.format(mData.getIA()));
            wc = ws4.getWritableCell(5, 19);
            l = (Label) wc;
            l.setString(df2.format(mData.getIB()));
            wc = ws4.getWritableCell(5, 20);
            l = (Label) wc;
            l.setString(df2.format(mData.getIC()));
            wc = ws4.getWritableCell(5, 21);
            l = (Label) wc;
            l.setString(df2.format(mData.getPjdl()));
            wc = ws4.getWritableCell(5, 22);
            l = (Label) wc;
            l.setString(df2.format(mData.getAyggl() / 1000));
            wc = ws4.getWritableCell(5, 23);
            l = (Label) wc;
            l.setString(df2.format(mData.getByggl() / 1000));
            wc = ws4.getWritableCell(5, 24);
            l = (Label) wc;
            l.setString(df2.format(mData.getCyggl() / 1000));
            wc = ws4.getWritableCell(5, 25);
            l = (Label) wc;
            l.setString(df2.format(mData.getUAB()));
            wc = ws4.getWritableCell(5, 26);
            l = (Label) wc;
            l.setString(df2.format(mData.getUBC()));
            wc = ws4.getWritableCell(5, 27);
            l = (Label) wc;
            l.setString(df2.format(mData.getUCA()));
            wc = ws4.getWritableCell(5, 28);
            l = (Label) wc;
            l.setString(df2.format(mData.getYggl()));
            wc = ws4.getWritableCell(5, 29);
            l = (Label) wc;
            l.setString(df2.format(mData.getWggl()));
            wc = ws4.getWritableCell(5, 30);
            l = (Label) wc;
            l.setString(df2.format(mData.getSzgl()));
            wc = ws4.getWritableCell(5, 31);
            l = (Label) wc;
            l.setString(df2.format(mData.getScgl()));
            wc = ws4.getWritableCell(5, 32);
            l = (Label) wc;
            l.setString(df2.format(mData.getXl()));
            wc = ws4.getWritableCell(5, 33);
            l = (Label) wc;
            l.setString(df2.format(mData.getZhxl()));
            wc = ws4.getWritableCell(5, 34);
            l = (Label) wc;
            l.setString(df3.format(mData.getFzxs()));
            wc = ws4.getWritableCell(5, 35);
            l = (Label) wc;
            l.setString(df3.format(mData.getGlys()));


            // ���ͼƬ


            writableWorkbook.write();
            writableWorkbook.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Intent intent = getExcelFileIntent(new File(Environment
                    .getExternalStorageDirectory().getAbsolutePath()
                    + "/CDZ11W矿用电机无线多参数测试仪/测试报告/"
                    + mTask.getUnitName()
                    + "_"
                    + mTask.getNumber()
                    + "_"
                    + date
                    + ".xls"));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ��Word
    public static Intent getWordFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    //android��ȡһ�����ڴ�Excel�ļ���intent
    public static Intent getExcelFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    private void initSize(WebSettings settings) {

        settings.setBuiltInZoomControls(true);

        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            // ����Ĭ�����ŷ�ʽ�ߴ���far ?��MEDIUM�ɣ�
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        settings.setDefaultZoom(zoomDensity);
    }

    private void GetmData(List<Long> ResIdLists) {
        mData = new ArrayList<DataTFJ>();
        TaskResEnityDao mdao = MyApp.getDaoInstant().getTaskResEnityDao();
        TaskEntityDao mtaskdao = MyApp.getDaoInstant().getTaskEntityDao();
        TaskEntity mHisTask = null;
        for (int i = 0; i < ResIdLists.size(); i++) {

            long resId = ResIdLists.get(i);


            TaskResEnity mRes = mdao.queryBuilder()
                    .where(TaskResEnityDao.Properties.Id.eq(resId)).list().get(0);


            if (mHisTask == null) {
                mHisTask = mtaskdao.queryBuilder()
                        .where(TaskEntityDao.Properties.Id.eq(mRes.getTaskId())).list().get(0);
            }
            DataTFJ md = new DataTFJ();

            md.SetResOnly(mRes);
            md.Refresh();
            mData.add(md);
        }
    }
}
