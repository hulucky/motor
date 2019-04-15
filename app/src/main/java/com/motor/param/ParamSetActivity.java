package com.motor.param;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.greendao.dbUtils.GreateTaskUtils;
import com.jaeger.library.StatusBarUtil;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.app.MyApp;
import com.motor.test.TestActivity;
import com.xzkydz.function.motor.module.ConstantData;
import com.xzkydz.function.utils.DateUtil;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static android.text.TextUtils.isEmpty;
import static com.motor.Tools.Method.IsEmpty;

public class ParamSetActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_task_name)
    EditText etTaskName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_people_name)
    EditText etPeopleName;
    //    @BindView(R.id.task_inf_ll)
//    LinearLayout taskInfLl;
//    @BindView(R.id.tv_param_djxl1)
//    TextView tvParamDjxl1;
//    @BindView(R.id.tv_param_qblc1)
//    TextView tvParamQblc1;
//    @BindView(R.id.sp_input_param_qblc1)
//    Spinner spInputParamQblc1;
//    @BindView(R.id.tv_param_motor_csff1)
//    TextView tvParamMotorCsff1;
//    @BindView(R.id.sp_input_param_motor_csff1)
//    Spinner spInputParamMotorCsff1;
//    @BindView(R.id.et_param_dybb1)
//    EditText etParamDybb1;
//    @BindView(R.id.et_param_dybb2)
//    EditText etParamDybb2;
//    @BindView(R.id.tv_param_dybb)
//    TextView tvParamDybb;
//    @BindView(R.id.et_param_dlbb1)
//    EditText etParamDlbb1;
//    @BindView(R.id.et_param_dlbb2)
//    EditText etParamDlbb2;
//    @BindView(R.id.tv_param_dlbb)
//    TextView tvParamDlbb;
    @BindView(R.id.imageView3)
    ImageView imageView3;

    @BindView(R.id.et_param_set_eddy)
    EditText etParamSetEddy;
    @BindView(R.id.et_param_set_eddl)
    EditText etParamSetEddl;
    @BindView(R.id.et_param_set_edgl)
    EditText etParamSetEdgl;
    @BindView(R.id.et_param_set_edxl)
    EditText etParamSetEdxl;
    @BindView(R.id.et_param_set_kzgl)
    EditText etParamSetKzgl;
    @BindView(R.id.et_param_set_kzdl)
    EditText etParamSetKzdl;
    @BindView(R.id.et_param_set_js)
    EditText etParamSetJs;
    @BindView(R.id.et_param_set_wgjjdl)
    EditText etParamSetWgjjdl;
    @BindView(R.id.ly_param_set)
    LinearLayout lyParamSet;
    //    @BindView(R.id.nestedScrollView)
//    NestedScrollView nestedScrollView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.btn_go_test)
    Button btnGoTest;
    private MyApp myApp;
    private int openlimit = 0; //任务信息 展开、关闭设位置
    TaskEntity mTask;
    private boolean ParamIsSet = false;//设定参数是否展开
    private boolean canSaveedxl = false;
    private boolean canSavedjxl2 = false;
    private boolean canSavesd = false;
    private boolean newTask = true;


    //    @BindView(R.id.toolbar_layout)
//    CollapsingToolbarLayout toolbarLayout;
//
//
    @BindView(R.id.task_inf_ll)
    LinearLayout llTittle;
    //
//
    @BindView(R.id.sp_input_param_motor_csff1)
    Spinner spmcsff1;
    @BindView(R.id.sp_input_param_qblc1)
    Spinner spqblc1;
    //
//
//    @BindView(R.id.ly_param_set)
//    LinearLayout lyset;
    @BindView(R.id.tv_param_dlbb)
    TextView tvdlbb;
    @BindView(R.id.tv_param_dybb)
    TextView tvdybb;

    @BindView(R.id.et_param_dlbb1)
    EditText etdlbb1;
    @BindView(R.id.et_param_dybb1)
    EditText etdybb1;
    @BindView(R.id.et_param_dlbb2)
    EditText etdlbb2;
    @BindView(R.id.et_param_dybb2)
    EditText etdybb2;
    //
//
//
//
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedscrollview;
    //    @BindView(R.id.imageview)
//    ImageView imageView;
//
    @BindView(R.id.tv_param_djxl1)
    TextView tvdjxl1;

    private boolean canSaveptg;
    private Animation mShakeAnim;
    private String errstr = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);
        ButterKnife.bind(this);
        myApp = MyApp.getInstance();
        mTask = new TaskEntity();
        mShakeAnim = AnimationUtils.loadAnimation(ParamSetActivity.this, R.anim.shake_x);
        setSupportActionBar(toolbar);

        StatusBarUtil.setColor(ParamSetActivity.this, getResources().getColor(R.color.tittleBar), 0);
        setDamp();
        toolbarLayout.setTitle(getResources().getString(R.string.title_activity_create_task));
        initView();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        spmcsff1.setSelection(2);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


    }

    private void initView() {

        TextChangeDLBB textChangedl = new TextChangeDLBB();
        TextChangeDYBB textChangedy = new TextChangeDYBB();
        etdybb1.addTextChangedListener(textChangedy);
        etdybb2.addTextChangedListener(textChangedy);
        etdlbb1.addTextChangedListener(textChangedl);
        etdlbb2.addTextChangedListener(textChangedl);
        etdybb2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etdybb2.getText().equals(0)) {
                    etdybb2.startAnimation(mShakeAnim);
                }
            }
        });
        etdlbb2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etdlbb2.getText().equals(0)) {
                    etdlbb2.startAnimation(mShakeAnim);
                }
            }
        });
        etParamSetEdxl.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                canSaveedxl = false;
                double medxl = 0.0;
                try {
                    medxl = Double.parseDouble(etParamSetEdxl.getText().toString());
                    if (0.0d <= medxl || medxl <= 100.0d) {
                        canSaveedxl = true;
                        etParamSetEdxl.setTextColor(Color.BLACK);


                    } else {
                        canSaveedxl = false;
                        etParamSetEdxl.setTextColor(Color.RED);
                    }
                } catch (
                        NumberFormatException e)

                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        TextChange textChange = new TextChange();
        etParamSetEdgl.addTextChangedListener(textChange);
        etParamSetEddl.addTextChangedListener(textChange);
        etParamSetJs.addTextChangedListener(textChange);


    }
//

    private Boolean CanSave() {
        boolean res = true;
        errstr = "";
        try {


            if (isEmpty(mTask.getDjk1())) {
                res = res & false;
                tvdjxl1.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "未选择电机1";
            }

            if (isEmpty(tvdybb.getText().toString())) {
                res = res & false;
                tvdybb.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "电压变比设置不正确";
            }
            if (isEmpty(tvdlbb.getText().toString())) {
                res = res & false;
                tvdlbb.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "电流变比设置不正确";
            }
            if (IsEmpty(etParamSetEddy)) {
                res = res & false;
                etParamSetEddy.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入额定电压";
            } else if (100 > Float.parseFloat(etParamSetEddy.getText().toString()) || Float.parseFloat(etParamSetEddy.getText().toString()) > 10000) {
                etParamSetEddy.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "额定电压：100～10000V";
            }
            if (IsEmpty(etParamSetEddl)) {
                res = res & false;
                etParamSetEddl.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入额定电流";
            } else if (0 > Float.parseFloat(etParamSetEddl.getText().toString()) || Float.parseFloat(etParamSetEddl.getText().toString()) > 1000) {
                etParamSetEddl.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "额定电流：0～1000A";
            }
            if (IsEmpty(etParamSetEdgl)) {
                res = res & false;
                etParamSetEdgl.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入额定功率";
            } else if (0 > Float.parseFloat(etParamSetEdgl.getText().toString()) || Float.parseFloat(etParamSetEdgl.getText().toString()) > 4000) {
                etParamSetEdgl.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "额定功率：0～4000kW";
            }
            if (IsEmpty(etParamSetEdxl)) {
                res = res & false;
                etParamSetEdxl.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入额定效率";
            } else if (0 > Float.parseFloat(etParamSetEdxl.getText().toString()) || Float.parseFloat(etParamSetEdxl.getText().toString()) > 100) {
                etParamSetEdxl.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "额定效率：0～100%";
            }
            if (IsEmpty(etParamSetKzgl)) {
                res = res & false;
                etParamSetKzgl.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入空载功率";
            } else if (0 > Float.parseFloat(etParamSetKzgl.getText().toString()) || Float.parseFloat(etParamSetKzgl.getText().toString()) > 100) {
                etParamSetKzgl.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "空载功率：0～100kW";
            }
            if (IsEmpty(etParamSetKzdl)) {
                res = res & false;
                etParamSetKzdl.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入空载电流";
            } else if (0 > Float.parseFloat(etParamSetKzdl.getText().toString()) || Float.parseFloat(etParamSetKzdl.getText().toString()) > 400) {
                etParamSetKzdl.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "空载电流：0～400A";
            }
            if (IsEmpty(etParamSetJs)) {
                res = res & false;
                etParamSetJs.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入级数";
            } else if (2 > Float.parseFloat(etParamSetJs.getText().toString()) || Float.parseFloat(etParamSetJs.getText().toString()) > 20) {
                etParamSetJs.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "级数：2～20";
            }
            if (IsEmpty(etParamSetWgjjdl)) {
                res = res & false;
                etParamSetWgjjdl.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入无功经济当量";
            } else if (0.02 > Float.parseFloat(etParamSetWgjjdl.getText().toString()) || Float.parseFloat(etParamSetWgjjdl.getText().toString()) > 0.1) {
                etParamSetWgjjdl.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "无功经济当量：0.02～0.1";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void setDamp() {
        nestedscrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == KeyEvent.ACTION_UP) {
                    appBar.setExpanded((openlimit > -170 ? true : false), true);
                    if (openlimit > -170 ? true : false) {
                        llTittle.setAlpha(1);
                        toolbarLayout.setTitle("");
                    } else {
                        toolbarLayout.setTitle(getResources().getString(R.string.title_activity_create_task));
                    }
                    return true;
                }

                return false;
            }
        });

        //缩放中心点坐标
        final float height = 0;    // 根据布局文件中Linearlayout的宽度获得
        final float width = 720;     // 我屏幕宽度
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                openlimit = verticalOffset;
                float vel = 1f - (float) Math.abs(verticalOffset) / 262f;
                llTittle.setAlpha(vel);
                llTittle.setScaleX(vel);
                llTittle.setScaleY(vel);
                llTittle.setPivotX(width / 2);
                llTittle.setPivotY(height);
            }
        });
    }

    @OnClick({R.id.fab, R.id.btn_go_test, R.id.tv_param_djxl1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intent = new Intent();
                intent.setClass(ParamSetActivity.this, HistoryTaskSearchActivity.class);
                startActivityForResult(intent, ConstantData.HistoryTask_resultCode);
                break;
            case R.id.btn_go_test:
                // 参数合理性检测
                if (CanSave()) {
                    SaveTask();
                    Intent intent1 = new Intent();
                    myApp.getInstance().setTaskEnity(mTask);
                    Toasty.success(ParamSetActivity.this, "开始测试！").show();
                    intent1.setClass(ParamSetActivity.this, TestActivity.class);
                    startActivity(intent1);
                } else {
                    Toasty.error(ParamSetActivity.this, "参数输入不正确！" + errstr).show();
                }
                break;

            case R.id.tv_param_djxl1:
                myApp.getInstance().setIsSetMotor1(true);
                intent = new Intent();
                intent.setClass(ParamSetActivity.this, MotorSelectorActivity.class);
                startActivityForResult(intent, ConstantData.Motor_requestCode);
                break;


        }
    }

    private void SaveTask() {

        try {
            if (!etTaskName.getText().toString().equals("")) {
                mTask.setUnitName(etTaskName.getText().toString());
            } else {
                mTask.setUnitName("未设定单位");
            }
            if (!etNumber.getText().toString().equals("")) {
                mTask.setGasePumpNumber(etNumber.getText().toString());
            } else {
                mTask.setGasePumpNumber("未设定编号");
            }
            if (!etPeopleName.getText().toString().equals("")) {
                mTask.setPeopleName(etPeopleName.getText().toString());
            } else {
                mTask.setPeopleName("未设定人员");
            }


            if (!isEmpty(etdybb1.getText())) {
                mTask.setDybb1(etdybb1.getText().toString());
            }
            if (!isEmpty(etdybb2.getText())) {
                mTask.setDybb2(etdybb2.getText().toString());
            }
            if (!isEmpty(etdlbb1.getText())) {
                mTask.setDlbb1(etdlbb1.getText().toString());
            }
            if (!isEmpty(etdlbb2.getText())) {
                mTask.setDlbb2(etdlbb2.getText().toString());
            }

            mTask.setDjqblc1(spqblc1.getSelectedItem().toString());

            mTask.setDjcsff1(spmcsff1.getSelectedItem().toString());
            mTask.setCsff(spmcsff1.getSelectedItem().toString());
            mTask.setDjeddy1(etParamSetEddy.getText().toString());
            mTask.setDjeddl1(etParamSetEddl.getText().toString());
            mTask.setDjedgl1(etParamSetEdgl.getText().toString());
            mTask.setDjedxl1(etParamSetEdxl.getText().toString());
            mTask.setDjkzgl1(etParamSetKzgl.getText().toString());
            mTask.setDjkzdl1(etParamSetKzdl.getText().toString());
            mTask.setDjjs1(etParamSetJs.getText().toString());
            mTask.setDjwgjjdl1(etParamSetWgjjdl.getText().toString());


            if (!mTask.get_IsCompleteTask()) {

                mTask.setGreateTaskTime(DateUtil.getGreatedTaskTime());

            }
            mTask.setBy1("0.95");
            new GreateTaskUtils().insert(mTask);
            MyApp.getInstance().setTaskEnity(mTask);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //EditText的监听器
    class TextChangeDYBB implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {

                Animation mShakeAnim = AnimationUtils.loadAnimation(ParamSetActivity.this, R.anim.shake_x);
                DecimalFormat df4 = new DecimalFormat("0.0");
                double mDybbone, mDybbtwo;
                double dybb;
                mDybbone = Double.parseDouble(etdybb1.getText().toString());
                mDybbtwo = Double.parseDouble(etdybb2.getText().toString());
                if (mDybbtwo == 0.0f) {
                    etdybb2.startAnimation(mShakeAnim);
                    tvdybb.setText((""));

                } else {
                    if (mDybbtwo > mDybbone) {
                        dybb = mDybbtwo / mDybbone;
                        etdybb1.setTextColor(Color.argb(160, 255, 140, 0));

                        etdybb2.setTextColor(Color.argb(160, 255, 140, 0));
                    } else {
                        dybb = mDybbone / mDybbtwo;
                        etdybb1.setTextColor(Color.BLACK);

                        etdybb2.setTextColor(Color.BLACK);
                    }
                    tvdybb.setText(df4.format(dybb));
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }//EditText的监听器

    class TextChangeDLBB implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {


                DecimalFormat df4 = new DecimalFormat("0.0");
                double mDlbbone, mDlbbtwo;
                double dlbb;
                mDlbbone = Double.parseDouble(etdlbb1.getText().toString());
                mDlbbtwo = Double.parseDouble(etdlbb2.getText().toString());
                if (mDlbbtwo == 0.0f) {
                    etdlbb2.startAnimation(mShakeAnim);
                    tvdlbb.setText((""));
                } else {
                    if (mDlbbtwo > mDlbbone) {
                        dlbb = mDlbbtwo / mDlbbone;
                        etdlbb1.setTextColor(Color.argb(160, 255, 140, 0));

                        etdlbb2.setTextColor(Color.argb(160, 255, 140, 0));
                    } else {
                        dlbb = mDlbbone / mDlbbtwo;
                        etdlbb1.setTextColor(Color.BLACK);

                        etdlbb2.setTextColor(Color.BLACK);
                    }
                    tvdlbb.setText(df4.format(dlbb));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }

    //EditText的监听器
    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                DecimalFormat df4 = new DecimalFormat("00.00");
                double mCedgl, mCeddl;
                int mCjs;
                mCedgl = Double.parseDouble(etParamSetEdgl.getText().toString());
                mCeddl = Double.parseDouble(etParamSetEddl.getText().toString());
                mCjs = Integer.parseInt(etParamSetJs.getText().toString());
                double[] res = MyApp.recalculate(mCedgl, mCeddl, mCjs);
                etParamSetKzgl.setText(df4.format(res[0]));
                etParamSetKzdl.setText(df4.format(res[1]));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 根据值, 设置spinner默认选中:
     *
     * @param spinner
     * @param value
     */
    public static void setSpinnerItemSelectedByValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                break;
            }
        }
    }

    // -------------------------------------------------——-------------------获取系统时间
    String getSysTime() {
// shijain
        String datesString;
        String monthString;
        String houString;
        String minString;
        String secondString;
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month + 1;
        int date = t.monthDay;
        int hour = t.hour; // 0-23
        int minute = t.minute;
        int second = t.second;
        if (date < 10) {
            datesString = "0" + date;
        } else {
            datesString = date + "";
        }
        if (month < 10) {
            monthString = "0" + month;
        } else {
            monthString = month + "";
        }
        if (hour < 10) {
            houString = "0" + hour;
        } else {
            houString = hour + "";
        }
        if (minute < 10) {
            minString = "0" + minute;
        } else {
            minString = minute + "";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = second + "";
        }
        String dString = year + "-" + monthString + "-" + datesString + "\n\t"
                + houString + ":" + minString + ":" + secondString;
        return dString;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int motorResultCode = ConstantData.Motor_resultCode; //

        switch (resultCode) {
            case 22: // 电机库返回码 ConstantData.Motor_resultCode

                tvdjxl1.setText(data.getStringExtra(ConstantData.MotorName_resultCode));
                mTask.setDjk1(data.getStringExtra(ConstantData.MotorName_resultCode));
                mTask.setDjeddy1(data.getStringExtra(ConstantData.MotorEddy_resultCode));
                mTask.setDjeddl1(data.getStringExtra(ConstantData.MotorEddl_resultCode));
                mTask.setDjedgl1(data.getStringExtra(ConstantData.MotorEdgl_resultCode));
                mTask.setDjedxl1(data.getStringExtra(ConstantData.MotorEdxl_resultCode));
                mTask.setDjkzdl1(data.getStringExtra(ConstantData.MotorKzdl_resultCode));
                mTask.setDjkzgl1(data.getStringExtra(ConstantData.MotorKzgl_resultCode));

                mTask.setDjjs1(data.getStringExtra(ConstantData.MotorJs_resultCode));
                mTask.setDjwgjjdl1(data.getStringExtra(ConstantData.MotorWgjjdl_resultCode));

                etParamSetEddy.setText(data.getStringExtra(ConstantData.MotorEddy_resultCode));
                etParamSetEddl.setText(data.getStringExtra(ConstantData.MotorEddl_resultCode));
                etParamSetEdgl.setText(data.getStringExtra(ConstantData.MotorEdgl_resultCode));
                etParamSetEdxl.setText(data.getStringExtra(ConstantData.MotorEdxl_resultCode));
                etParamSetKzdl.setText(data.getStringExtra(ConstantData.MotorKzdl_resultCode));
                etParamSetKzgl.setText(data.getStringExtra(ConstantData.MotorKzgl_resultCode));
                etParamSetJs.setText(data.getStringExtra(ConstantData.MotorJs_resultCode));
                etParamSetWgjjdl.setText(data.getStringExtra(ConstantData.MotorWgjjdl_resultCode));

                break;

            case 55: // 历史任务返回码  ConstantData.HistoryTask_resultCode
                Long taskId = data.getLongExtra(ConstantData.HistoryTask_ID_resultCode, 1L);
                GreateTaskUtils greateTaskUtils = new GreateTaskUtils();
                // 这个只是复用参数的历史任务，参数有可能在此基础上更改，不一定就是测试任务。


                TaskEntity mmTask = greateTaskUtils.query(taskId);
                if (mmTask.get_IsCompleteTask() == true) {
                    mTask = new TaskEntity();
                }
                CopyTask(mTask, mmTask);
                this.setParForHistoryTask(mTask);

                break;
            default:
                break;
        }
    }

    private void CopyTask(TaskEntity mTask, TaskEntity mmTask) {

        mTask.setBy12(mmTask.getBy12());
        mTask.setBy11(mmTask.getBy11());
        mTask.setBy10(mmTask.getBy10());
        mTask.setBy9(mmTask.getBy9());
        mTask.setBy8(mmTask.getBy8());
        mTask.setBy7(mmTask.getBy7());
        mTask.setBy6(mmTask.getBy6());
        mTask.setBy5(mmTask.getBy5());
        mTask.setBy4(mmTask.getBy4());
        mTask.setBy3(mmTask.getBy3());
        mTask.setBy2(mmTask.getBy2());
        mTask.setBy1(mmTask.getBy1());

        mTask.setDjwgjjdl1(mmTask.getDjwgjjdl1());
        mTask.setDjjs1(mmTask.getDjjs1());
        mTask.setDjkzgl1(mmTask.getDjkzgl1());
        mTask.setDjkzdl1(mmTask.getDjkzdl1());
        mTask.setDjedxl1(mmTask.getDjedxl1());
        mTask.setDjedgl1(mmTask.getDjedgl1());
        mTask.setDjeddl1(mmTask.getDjeddl1());
        mTask.setDjeddy1(mmTask.getDjeddy1());
        mTask.setDjcdfs1(mmTask.getDjcdfs1());
        mTask.setDjcdxl1(mmTask.getDjcdxl1());
        mTask.setDjcsff1(mmTask.getDjcsff1());
        mTask.setDjqblc1(mmTask.getDjqblc1());
        mTask.setDjksfxz1(mmTask.getDjksfxz1());
        mTask.setDjk1(mmTask.getDjk1());
        mTask.setDybb2(mmTask.getDybb2());
        mTask.setDybb1(mmTask.getDybb1());
        mTask.setDlbb2(mmTask.getDlbb2());
        mTask.setDlbb1(mmTask.getDlbb1());

        mTask.setCsff(mmTask.getCsff());


//        mTask.setGreateTaskTime(mmTask.getGreateTaskTime());
//        mTask.setTaskHaveGetData(mmTask. getTaskHaveGetData());
//        mTask.set_IsCompleteTask(mmTask. get_IsCompleteTask());
        mTask.setPeopleName(mmTask.getPeopleName());
        mTask.setGasePumpNumber(mmTask.getGasePumpNumber());
        mTask.setUnitName(mmTask.getUnitName());

    }

    public void setParForHistoryTask(TaskEntity taskEnity) {


        try {
            etTaskName.setText(taskEnity.getUnitName());
            etNumber.setText(taskEnity.getGasePumpNumber());
            etPeopleName.setText(taskEnity.getPeopleName());

            etdybb1.setText(taskEnity.getDybb1());
            etdybb2.setText(taskEnity.getDybb2());
            etdlbb1.setText(taskEnity.getDlbb1());
            etdlbb2.setText(taskEnity.getDlbb2());

            etParamSetEddy.setText(taskEnity.getDjeddy1());
            etParamSetEddl.setText(taskEnity.getDjeddl1());
            etParamSetEdgl.setText(taskEnity.getDjedgl1());
            etParamSetEdxl.setText(taskEnity.getDjedxl1());
            etParamSetKzdl.setText(taskEnity.getDjkzdl1());
            etParamSetKzgl.setText(taskEnity.getDjkzgl1());
            etParamSetJs.setText(taskEnity.getDjjs1());
            etParamSetWgjjdl.setText(taskEnity.getDjwgjjdl1());


            setSpinnerItemSelectedByValue(spqblc1, taskEnity.getDjqblc1());

            setSpinnerItemSelectedByValue(spmcsff1, taskEnity.getDjcsff1());


            tvdjxl1.setText(taskEnity.getDjk1());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
