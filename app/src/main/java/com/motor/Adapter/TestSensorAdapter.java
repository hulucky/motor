package com.motor.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.greendao.manager.DataTFJ;
import com.motor.administrator.DATAbase.R;
import com.motor.bean.GridBean;
import com.motor.test.TestActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 测试 --> 参数  --->  listView 适配器
 */

public class TestSensorAdapter extends BaseAdapter {
    private DataTFJ mdata;
    private TestActivity mContext;
    private List<GridBean> list;
    private int type;

    DecimalFormat df0 = new DecimalFormat("####0");
    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");


    private String functionStr;
    //	private final TaskEnityDao taskDaoUtils;

    public void setListViewType() {


        List<GridBean> dj1List = new ArrayList<>();
        dj1List.add(getTSGridItem("AB线电压", df1.format(mdata.getUab())+ " V", "A相电流", df1.format(mdata.getIa())+ " A"));
        dj1List.add(getTSGridItem("BC线电压", mdata.getDj1csff().equals("单瓦法")?"--":df1.format(mdata.getUbc())+ " V", "B相电流", !mdata.getDj1csff().equals("三瓦法")?"--":df1.format(mdata.getIb())+ " A"));
        dj1List.add(getTSGridItem("CA线电压",  mdata.getDj1csff().equals("单瓦法")?"--":df1.format(mdata.getUca())+ " V", "C相电流",  mdata.getDj1csff().equals("单瓦法")?"--":df1.format(mdata.getIc())+ " A"));
        dj1List.add(getTSGridItem("平均电压", df1.format(mdata.getPjU())+ " V", "平均电流", df1.format(mdata.getPjI())+ " A"));
        dj1List.add(getTSGridItem("电机功率", df1.format(mdata.getDjgl())+ " kW", "电机效率", df1.format(mdata.getDjxl())+ " %"));
        dj1List.add(getTSGridItem("负载系数", df3.format(mdata.getFzxs())+ " ", "输出功率", df1.format(mdata.getScgl())+ " kW"));
        dj1List.add(getTSGridItem("综合效率", df1.format(mdata.getZhxl())+ " %", "功率因数", df3.format(mdata.getGlys())+ " "));
        dj1List.add(getTSGridItem("运行状态", mdata.getYxzt()+ " ","","" ));
        this.list = dj1List;


    }


    public TestSensorAdapter(TestActivity mContext, DataTFJ mdata, int type) {
        super();
        this.mContext = mContext;
        this.type = type;
        this.mdata = mdata;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_testshow, parent, false);
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
}
