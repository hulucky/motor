package com.motor.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.motor.administrator.DATAbase.R;


@SuppressLint("DrawAllocation")
public class BorderTextView extends AppCompatTextView {

    public BorderTextView(Context context) {
        super(context);
    }
    public BorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private int sroke_width = 1;
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        //  将边框设为黑色

//       paint.setAntiAlias(true);//使用抗锯齿功能
                paint.setColor(getResources().getColor(R.color.aliceblue));    //设置画笔的颜色为绿色
                  paint.setStyle(Paint.Style.STROKE);//设置画笔类型为STROKE类型（个人感觉是描边的意思）
        int span=2;
        //  画TextView的4个边
        canvas.drawLine(0+span, 0, this.getWidth() - sroke_width-span, 0, paint);
        canvas.drawLine(0, 0+span, 0, this.getHeight() - sroke_width-span, paint);
        canvas.drawLine(this.getWidth() - sroke_width, 0+span, this.getWidth() - sroke_width, this.getHeight() - sroke_width-span, paint);
        canvas.drawLine(0+span, this.getHeight() - sroke_width, this.getWidth() - sroke_width-span, this.getHeight() - sroke_width, paint);
//        canvas.drawA
        super.onDraw(canvas);
    }
}