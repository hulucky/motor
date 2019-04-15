package com.motor.Tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.view.View;


import com.greendao.manager.motorData;

import java.text.DecimalFormat;

/**
 * @author Administrator
 */

/**
 * @author Administrator
 */
public class DrawViewThi extends View {
    DecimalFormat df4 = new DecimalFormat("####0.00");
    DecimalFormat df2 = new DecimalFormat("####0");
    DecimalFormat df3 = new DecimalFormat("####0.0");

    Point mpoint = new Point();// 定位点（左上角）
    float UA = 220;

    float IA = 10;
    float UB = 220;
    float IB = 10;
    float UC = 220;
    float IC = 10;
    int TestMethod = 0;// 测试方法 0单瓦，1双瓦，2三瓦

    float phUAB = 120;
    float phUBC = 120;
    float phUCA = 120;
    float phUIA = 5;
    float phUIB = 5;
    float phUIC = 5;
    float CurveWidth = 460;// 绘制区域宽度
    float CurveHeight = 280;// 绘制区域高度
    // 各次电压电流谐波
    double[] hamUA = null;
    double[] hamUB = null;
    double[] hamUC = null;
    double[] hamIA = null;
    double[] hamIB = null;
    double[] hamIC = null;

    public int getHamNo() {
        return HamNo;
    }

    public void setHamNo(int hamNo) {
        HamNo = hamNo;
    }

    int HamNo = 0;//0 UA,1UB,2UC,3IA,4IB,5IC
    int AColor = Color.YELLOW;
    int BColor = Color.GREEN;
    int CColor = Color.RED;

    /**
     * 曲线类型，0为三相 1为单相 2为矢量3为谐波
     */
    int CurveType;

    /**
     * 谐波类型 0~5 0为A电压谐波，1为B电压谐波，以此类推
     */
    int hamType;

    int LabelOffSet = 10;// 坐标轴文本便宜量
    float XMax = 20;// X取值上限
    float XMin = 0;// X取值下限
    float LMax = 20;// 左侧坐标轴取值上限
    float LMin = 20;// 左侧坐标轴取值下限
    float RUMax = 30;// 右上坐标轴取值上限
    float RUMin = 30;// 右下坐标轴取值下限
    float RDMax = 20;// 右下坐标轴取值上限
    float RDMin = 20;// 右下坐标轴取值下限
    int XNum = 10;// X坐标段数
    int YNum = 10;// Y坐标段数
    int YUNum = 5;// 右上坐标段数
    int YDNum = 5;// 右下坐标段数

    float XDivision = 40;// x坐标分度
    float YDivision = 40;// y坐标分度
    float YUDivision = 30;// 右上Y坐标分度
    float YDDivision = 30;// 右下Y坐标分度
    float[] Xaxis = new float[(XNum + 1) * 4 + 4];// X坐标轴
    // 前部为标线，后部为轴线，4个数据一条线
    float[] Yaxis = new float[(YNum + 1) * 4 + 4];// Y坐标轴
    // 前部为标线，后部为轴线，4个数据一条线
    float[] YUaxis = new float[(YUNum + 1) * 4 + 4];// 右上坐标轴
    // 前部为标线，后部为轴线，4个数据一条线
    float[] YDaxis = new float[(YDNum + 1) * 4 + 4];// 右下坐标轴
    // 前部为标线，后部为轴线，4个数据一条线
    float XaxisLine = 5;// X坐标单位线长度
    float YaxisLine = -5;// Y坐标单位线长度，负值为向左
    float YUaxisLine = 5;// 右上坐标单位线长度
    float YDaxisLine = 5;// 右下坐标单位线长度
    int XColor = Color.BLACK;// X坐标颜色
    int YColor = Color.BLUE;// Y坐标颜色
    int YUColor = Color.GREEN;// 右上坐标颜色
    int YDColor = Color.RED;// 右下坐标颜色
    float[] SourceData1 = null;// Y原始数据
    float[] SourceData2 = null;// 右上原始数据
    float[] SourceData3 = null;// 右下原始数据
    float bca = 0.25f;// 第一控制点偏移量
    float bcb = 0.25f;// 第二控制点偏移量
    float XScale = 10f;// X坐标最大扩展系数 用法为最大值减最小值除此系数，然后最大值最小值按得到的扩展量扩展
    float YScale = 10f;// Y坐标最大扩展系数
    float YUScale = 10f;// YU坐标最大扩展系数
    float YDScale = 10f;// YD坐标最大扩展系数
    Boolean MaxOrMinIsSet = false;// 是否预设最大最小值

    public DrawViewThi(Context context) {
        super(context);
    }

    // private class Point {
    // int X;
    // int Y;
    // }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (CurveType) {
            case 0:
                Drawthi(canvas);
                break;
            case 1:
                Drawsin(canvas);
                break;
            case 2:
                Drawvec(canvas);
                break;
            case 3:
                Drawham(canvas);
                break;
            default:
                break;
        }
    }

    private void Drawthi(Canvas canvas) {
        Paint p = new Paint();
        // 基础点
        mpoint.x = 30;
        CurveHeight = 260;
        mpoint.y = 30;
        int mx = mpoint.x;
        int my = mpoint.y;

        // 电压坐标轴，电流坐标轴
        p.setColor(Color.WHITE);
        // Y轴，
        canvas.drawLine(mx, my, mx, my + CurveHeight, p);
        // X轴横线
        float Yd = CurveHeight / 12;
        canvas.drawLine(mx, my + 3 * Yd, mx + CurveWidth, my + 3 * Yd, p);
        canvas.drawLine(mx, my + 9 * Yd, mx + CurveWidth, my + 9 * Yd, p);
        // X辅助线
        p.setColor(Color.CYAN);
        canvas.drawLine(mx, my + 1 * Yd, mx + CurveWidth, my + 1 * Yd, p);
        canvas.drawLine(mx, my + 2 * Yd, mx + CurveWidth, my + 2 * Yd, p);
        canvas.drawLine(mx, my + 4 * Yd, mx + CurveWidth, my + 4 * Yd, p);
        canvas.drawLine(mx, my + 5 * Yd, mx + CurveWidth, my + 5 * Yd, p);
        canvas.drawLine(mx, my + 7 * Yd, mx + CurveWidth, my + 7 * Yd, p);
        canvas.drawLine(mx, my + 8 * Yd, mx + CurveWidth, my + 8 * Yd, p);
        canvas.drawLine(mx, my + 10 * Yd, mx + CurveWidth, my + 10 * Yd, p);
        canvas.drawLine(mx, my + 11 * Yd, mx + CurveWidth, my + 11 * Yd, p);
        // 坐标轴文本

        // 辅助文字
        canvas.drawText("电压波形", mx + CurveWidth / 6, my + CurveHeight / 2, p);
        canvas.drawText("电流波形", mx + CurveWidth / 6, my + CurveHeight, p);
        // 图例
        p.setColor(AColor);
        canvas.drawText("A相", mx + CurveWidth * 2 / 6, my + CurveHeight, p);
        if (TestMethod != 0) {
            if (TestMethod != 1) {
                p.setColor(BColor);
                canvas.drawText("B相", mx + CurveWidth * 3 / 6, my + CurveHeight, p);
            }
            p.setColor(CColor);
            canvas.drawText("C相", mx + CurveWidth * 4 / 6, my + CurveHeight, p);
        }
        // A电压 A电流
        float preyUA = 0.0f;
        float preyIA = 0.0f;
        float preyUB = 0.0f;
        float preyIB = 0.0f;
        float preyUC = 0.0f;
        float preyIC = 0.0f;
        float nowUA;
        float nowUB;
        float nowUC;
        float nowIA;
        float nowIB;
        float nowIC;
        for (int i = 1; i < CurveWidth; i++) {
            nowUA = (float) (2 * Yd * Math.sin(i * 2 * Math.PI / 180));

            nowUB = (float) (2 * Yd * Math.sin((i * 2 + phUAB) * Math.PI / 180));

            nowUC = (float) (2 * Yd * Math.sin((i * 2 + phUAB + phUBC)
                    * Math.PI / 180));

            nowIA = (float) (2 * Yd * Math.sin((i * 2 + phUIA) * Math.PI / 180));

            nowIB = (float) (2 * Yd * Math.sin((i * 2 + phUAB + phUIB)
                    * Math.PI / 180));

            nowIC = (float) (2 * Yd * Math.sin((i * 2 + phUAB + phUBC + phUIC)
                    * Math.PI / 180));

            p.setColor(AColor);
            canvas.drawLine(mx + i - 1, my + 3 * Yd - preyUA, mx + i, my + 3
                    * Yd - nowUA, p);
            canvas.drawLine(mx + i - 1, my + 9 * Yd - preyIA, mx + i, my + 9
                    * Yd - nowIA, p);
            if (TestMethod != 0) {
                if (TestMethod != 1) {
                    p.setColor(BColor);
                    canvas.drawLine(mx + i - 1, my + 3 * Yd - preyUB, mx + i, my + 3
                            * Yd - nowUB, p);
                    canvas.drawLine(mx + i - 1, my + 9 * Yd - preyIB, mx + i, my + 9
                            * Yd - nowIB, p);
                }
                p.setColor(CColor);
                canvas.drawLine(mx + i - 1, my + 3 * Yd - preyUC, mx + i, my + 3
                        * Yd - nowUC, p);
                canvas.drawLine(mx + i - 1, my + 9 * Yd - preyIC, mx + i, my + 9
                        * Yd - nowIC, p);
            }
            preyUA = nowUA;
            preyIA = nowIA;
            preyUB = nowUB;
            preyIB = nowIB;
            preyUC = nowUC;
            preyIC = nowIC;
        }

    }

    private void Drawsin(Canvas canvas) {
        Paint p = new Paint();
        // 基础点
        mpoint.x = 30;
        CurveHeight = 260;
        mpoint.y = 30;
        int mx = mpoint.x;
        int my = mpoint.y;
        float Yd = CurveHeight / 9;
        // 电压坐标轴，电流坐标轴
        p.setColor(Color.WHITE);
        // Y轴，
        canvas.drawLine(mx, my - Yd / 3, mx, my + CurveHeight, p);
        // X轴横线

        canvas.drawLine(mx, my + 1 * Yd, mx + CurveWidth, my + 1 * Yd, p);
        canvas.drawLine(mx, my + 4 * Yd, mx + CurveWidth, my + 4 * Yd, p);
        canvas.drawLine(mx, my + 7 * Yd, mx + CurveWidth, my + 7 * Yd, p);
        // 辅助文字

        canvas.drawText("A相电压：" + df3.format(UA) + "V,电流：" + df3.format(IA)
                        + "A,相位角：" + df3.format(phUIA) + "°", mx + CurveWidth / 6,
                (float) (my + 2.7 * Yd), p);
        if (TestMethod != 0) {
            if (TestMethod != 1) {
                canvas.drawText("B相电压：" + df3.format(UB) + "V,电流：" + df3.format(IB)
                                + "A,相位角：" + df3.format(phUIB) + "°", mx + CurveWidth / 6,
                        (float) (my + 5.7 * Yd), p);
            } else if (TestMethod == 1) {
                canvas.drawText("B相电压：" + df3.format(UB) + "V", mx + CurveWidth / 6,
                        (float) (my + 5.7 * Yd), p);
            }
            canvas.drawText("C相电压：" + df3.format(UC) + "V,电流：" + df3.format(IC)
                            + "A,相位角：" + df3.format(phUIC) + "°", mx + CurveWidth / 6,
                    (float) (my + 8.7 * Yd), p);
        }
        // X辅助线
        p.setColor(Color.CYAN);
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        p.setPathEffect(effects);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawLine(mx, my, mx + CurveWidth, my, p);
        canvas.drawLine(mx, my + 2 * Yd, mx + CurveWidth, my + 2 * Yd, p);
        canvas.drawLine(mx, my + 3 * Yd, mx + CurveWidth, my + 3 * Yd, p);
        canvas.drawLine(mx, my + 5 * Yd, mx + CurveWidth, my + 5 * Yd, p);
        canvas.drawLine(mx, my + 6 * Yd, mx + CurveWidth, my + 6 * Yd, p);
        canvas.drawLine(mx, my + 8 * Yd, mx + CurveWidth, my + 8 * Yd, p);

        // 坐标轴文本


        // // 图例
        // p.setColor(AColor);
        // canvas.drawText("A相", mx + CurveWidth * 2 / 6, my + CurveHeight, p);
        // p.setColor(BColor);
        // canvas.drawText("B相", mx + CurveWidth * 3 / 6, my + CurveHeight, p);
        // p.setColor(CColor);
        // canvas.drawText("C相", mx + CurveWidth * 4 / 6, my + CurveHeight, p);
        // A电压 A电流
        float preyUA = 0.0f;
        float preyIA = 0.0f;
        float preyUB = 0.0f;
        float preyIB = 0.0f;
        float preyUC = 0.0f;
        float preyIC = 0.0f;
        float nowUA;
        float nowUB;
        float nowUC;
        float nowIA;
        float nowIB;
        float nowIC;
        for (int i = 1; i < CurveWidth; i++) {
            nowUA = (float) (Yd * Math.sin(i * 2 * Math.PI / 180));

            nowUB = (float) (Yd * Math.sin((i * 2 + phUAB) * Math.PI / 180));

            nowUC = (float) (Yd * Math.sin((i * 2 + phUAB + phUBC) * Math.PI
                    / 180));

            nowIA = (float) (Yd / 2 * Math.sin((i * 2 + phUIA) * Math.PI / 180));

            nowIB = (float) (Yd / 2 * Math.sin((i * 2 + phUAB + phUIB)
                    * Math.PI / 180));

            nowIC = (float) (Yd / 2 * Math.sin((i * 2 + phUAB + phUBC + phUIC)
                    * Math.PI / 180));
            p.setColor(Color.WHITE);
            canvas.drawLine(mx + i - 1, my + Yd - preyUA, mx + i, my + Yd
                    - nowUA, p);
            canvas.drawLine(mx + i - 1, my + 4 * Yd - preyUB, mx + i, my + 4
                    * Yd - nowUB, p);
            canvas.drawLine(mx + i - 1, my + 7 * Yd - preyUC, mx + i, my + 7
                    * Yd - nowUC, p);
            p.setColor(AColor);

            canvas.drawLine(mx + i - 1, my + Yd - preyIA, mx + i, my + Yd
                    - nowIA, p);
            if (TestMethod != 0) {
                if (TestMethod != 1) {
                    p.setColor(BColor);

                    canvas.drawLine(mx + i - 1, my + 4 * Yd - preyIB, mx + i, my + 4
                            * Yd - nowIB, p);
                }
                p.setColor(CColor);

                canvas.drawLine(mx + i - 1, my + 7 * Yd - preyIC, mx + i, my + 7
                        * Yd - nowIC, p);
            }
            preyUA = nowUA;
            preyIA = nowIA;
            preyUB = nowUB;
            preyIB = nowIB;
            preyUC = nowUC;
            preyIC = nowIC;
        }

    }

    private void Drawvec(Canvas canvas) {
        Paint p = new Paint();
        // 基础点
CurveWidth=500;
        CurveHeight = 500;
        mpoint.x = (int) (CurveWidth / 2);

        mpoint.y = (int) (CurveHeight / 2);
        int mx = mpoint.x;
        int my = mpoint.y;
        float R = (float) (CurveHeight * 0.45);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.WHITE);
        canvas.drawCircle(mx, my, R, p);


        for (int i = 0; i < 12; i++) {

            canvas.drawLine(mx, my, (float) (mx + R * Math.sin(i * 2 * Math.PI / 12)), (float) (my + R * Math.cos(i * 2 * Math.PI / 12)), p);
        }


        FontMetrics a = p.getFontMetrics();
        float fsize = Math.abs(a.ascent) + Math.abs(a.descent) + Math.abs(a.leading);
        DrawArcAndText(canvas, p, mpoint, R, 0, "UA", AColor, (int) fsize);
        DrawArcAndText(canvas, p, mpoint, (float) (0.8 * R), phUIA, "IA", AColor, (int) fsize);
        if (TestMethod != 0) {
            if (TestMethod != 1) {

                DrawArcAndText(canvas, p, mpoint, (float) (0.8 * R), phUAB + phUIB, "IB", BColor, (int) fsize);
            }
            DrawArcAndText(canvas, p, mpoint, R, phUAB, "UB", BColor, (int) fsize);
            DrawArcAndText(canvas, p, mpoint, R, phUAB + phUBC, "UC", CColor, (int) fsize);
            DrawArcAndText(canvas, p, mpoint, (float) (0.8 * R), phUAB + phUBC + phUIC, "IC", CColor, (int) fsize);
        }
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        p.setPathEffect(effects);
        p.setColor(Color.CYAN);
        canvas.drawCircle(mx, my, (float) (R * 0.8), p);
        canvas.drawCircle(mx, my, (float) (R * 0.6), p);
        canvas.drawCircle(mx, my, (float) (R * 0.4), p);
        p.setColor(Color.WHITE);
        canvas.drawCircle(mx, my, R, p);
        //A电压
        //A电流

        //方向标志

        //说明

    }

    private void DrawArcAndText(Canvas canvas, Paint p, Point mp, float radius, float angle, String mtext, int mcolor, int mSize) {
        p.setColor(mcolor);
        Point[] mUAarr = CordTools.GetArrowBy2Vertex(mpoint, new Point((int) (mp.x + radius * Math.cos(angle * 2 * Math.PI / 180)), (int) (mp.y - radius * Math.sin(angle * 2 * Math.PI / 180))), radius * 0.1);//箭头数组
        Path ptUA = new Path();
        ptUA.moveTo(mUAarr[0].x, mUAarr[0].y);
        ptUA.lineTo(mUAarr[1].x, mUAarr[1].y);
        ptUA.lineTo(mUAarr[2].x, mUAarr[2].y);
        ptUA.lineTo(mUAarr[3].x, mUAarr[3].y);
        ptUA.lineTo(mUAarr[4].x, mUAarr[4].y);
        ptUA.lineTo(mUAarr[5].x, mUAarr[5].y);
        canvas.drawPath(ptUA, p);


        Point mUaText = CordTools.GetTextLocation(4, "UA", new Point((int) (mp.x + radius * 1.1 * Math.cos(angle * 2 * Math.PI / 180)), (int) (mp.y - radius * 1.1 * Math.sin(angle * 2 * Math.PI / 180))), mSize, 0, 0, 1);
        canvas.drawText(mtext, mUaText.x, mUaText.y, p);
    }

    private void Drawham(Canvas canvas) {
        CurveWidth=600;
        CurveHeight=330;
        Paint p = new Paint();
        double[] tmpHar = new double[32];
        FontMetrics a = p.getFontMetrics();
        float fsize = Math.abs(a.ascent) + Math.abs(a.descent) + Math.abs(a.leading);
        switch (HamNo) {
            case 0:
                tmpHar = hamUA;
                break;
            case 1:
                tmpHar = hamUB;
                break;
            case 2:
                tmpHar = hamUC;
                break;
            case 3:
                tmpHar = hamIA;
                break;
            case 4:
                tmpHar = hamIB;
                break;
            case 5:
                tmpHar = hamIC;
                break;

        }
        double TopHar = 0.0d;
        int TopI = 0;
        for (int i = 0; i < 32; i++) {

            if (TopHar < tmpHar[i]) {
                TopHar = tmpHar[i];
                TopI = i;
            }
        }
        p.setColor(AColor);
        for (int i = 0; i < 32; i++) {
            canvas.drawLine(CurveWidth / 10 + i * 7 * CurveWidth / 320,
                    (int) (CurveHeight / 2 - (2 * tmpHar[i] * CurveHeight / (5 * TopHar))),
                    CurveWidth / 10 + i * 7 * CurveWidth / 320,
                    CurveHeight / 2,
                    p);
            if (i == TopI) {
                Point tmpText = CordTools.GetTextLocation(2, df2.format(TopHar), new Point((int) (CurveWidth / 10 + 5), (int) (CurveHeight / 10 - 5)), (int) fsize, 0, 0, 1);

                canvas.drawText(df2.format(TopHar),  tmpText.x, tmpText.y,p);


            }
//            canvas.drawText(i + "次谐波：" + df3.format(tmpHar[i]), (float) (CurveWidth / 10 + i % 4 * (CurveWidth *8 / 40)),(float) (CurveHeight / 2 + 5 + (i / 4) * CurveHeight / 16),p);
        }
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        p.setPathEffect(effects);
        canvas.drawLine( CurveWidth / 10, CurveHeight / 10, 8 * CurveWidth / 10, CurveHeight / 10,p);
    }

    /*
     * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
     * drawLine 绘制直线 drawPoin 绘制点
     */
    // 创建画笔
    private void Drawtfj(Canvas canvas) {
        Paint p = new Paint();
        // p.setColor(Color.RED);
        // p.setStyle(Paint.Style.STROKE);
        // Point[] tmpa = new Point[5];
        // // for (int i = 0; i < 5; i++) {
        // // tmpa[i].x = 100 + i * 100;
        // // tmpa[i].y = 150 + i % 2 * 40 - i % 3 * (30);
        // // }
        // tmpa[0] = new Point(100, 120);
        //
        // tmpa[1] = new Point(200, 320);
        // tmpa[2] = new Point(300, 150);
        // tmpa[3] = new Point(400, 420);
        // tmpa[4] = new Point(500, 120);
        //
        // Point[] tmpC = getCtrlPoint(tmpa, 0.25f, 0.25f);
        // for (int i = 0; i < 4; i++) {
        // p.setColor(Color.RED);
        // p.setStyle(Paint.Style.STROKE);
        // Path pathtmp = new Path();
        // pathtmp.moveTo(tmpa[i].x, tmpa[i].y);
        // pathtmp.cubicTo(tmpC[i * 2].x, tmpC[i * 2].y, tmpC[i * 2 + 1].x,
        // tmpC[i * 2 + 1].y, tmpa[i + 1].x, tmpa[i + 1].y);
        // canvas.drawPath(pathtmp, p);
        // p.setColor(Color.BLUE);
        //
        // canvas.drawCircle(tmpa[i].x + 1, tmpa[i].y + 1, 10, p);
        //
        // }
        // p.setColor(Color.BLUE);
        // p.setStyle(Paint.Style.FILL);
        // canvas.drawCircle(tmpa[4].x + 1, tmpa[4].y + 1, 10, p);
        // p.setColor(Color.GREEN);
        // p.setStyle(Paint.Style.STROKE);
        // for (int i = 0; i < tmpC.length; i++) {
        // canvas.drawCircle(tmpC[i].x, tmpC[i].y, 10, p);
        // }

        // p.setColor(Color.RED);// 设置红色
        //
        // canvas.drawText("画圆：", 10, 20, p);// 画文本
        // canvas.drawCircle(60, 20, 10, p);// 小圆
        // p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        // canvas.drawCircle(120, 20, 20, p);// 大圆
        //
        // canvas.drawText("画线及弧线：", 10, 60, p);
        // p.setColor(Color.GREEN);// 设置绿色
        // canvas.drawLine(60, 40, 100, 40, p);// 画线
        // canvas.drawLine(110, 40, 190, 80, p);// 斜线
        // float[] aa = new float[] { 20, 400, 20, 410, 30, 500, 30, 510, 40,
        // 500,
        // 40, 510, 50, 500, 50, 510 };
        //
        // canvas.drawLines(aa, 0, aa.length, p);
        // // 画笑脸弧线
        // p.setStyle(Paint.Style.STROKE);// 设置空心
        // RectF oval1 = new RectF(150, 20, 180, 40);
        // canvas.drawArc(oval1, 180, 180, false, p);// 小弧形
        // oval1.set(190, 20, 220, 40);
        // canvas.drawArc(oval1, 180, 180, false, p);// 小弧形
        // oval1.set(160, 30, 210, 60);
        // canvas.drawArc(oval1, 0, 180, false, p);// 小弧形
        //
        // canvas.drawText("画矩形：", 10, 80, p);
        // p.setColor(Color.GRAY);// 设置灰色
        // p.setStyle(Paint.Style.FILL);// 设置填满
        // canvas.drawRect(60, 60, 80, 80, p);// 正方形
        // canvas.drawRect(60, 90, 160, 100, p);// 长方形
        //
        // canvas.drawText("画扇形和椭圆:", 10, 120, p);
        // /* 设置渐变色 这个正方形的颜色是改变的 */
        // Shader mShader = new LinearGradient(0, 0, 100, 100,
        // new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
        // Color.LTGRAY }, null, Shader.TileMode.REPEAT); //
        // 一个材质,打造出一个线性梯度沿著一条线。
        // p.setShader(mShader);
        // // p.setColor(Color.BLUE);
        // RectF oval2 = new RectF(60, 100, 200, 240);// 设置个新的长方形，扫描测量
        // canvas.drawArc(oval2, 200, 130, true, p);
        // // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        // // 画椭圆，把oval改一下
        // oval2.set(210, 100, 250, 130);
        // canvas.drawOval(oval2, p);
        //
        // canvas.drawText("画三角形：", 10, 200, p);
        // // 绘制这个三角形,你可以绘制任意多边形
        // Path path = new Path();
        // path.moveTo(80, 200);// 此点为多边形的起点
        // path.lineTo(120, 250);
        // path.lineTo(80, 250);
        // path.close(); // 使这些点构成封闭的多边形
        // canvas.drawPath(path, p);
        //
        // // 你可以绘制很多任意多边形，比如下面画六连形
        // p.reset();// 重置
        // p.setColor(Color.LTGRAY);
        // p.setStyle(Paint.Style.STROKE);// 设置空心
        // Path path1 = new Path();
        // path1.moveTo(180, 200);
        // path1.lineTo(200, 200);
        // path1.lineTo(210, 210);
        // path1.lineTo(200, 220);
        // path1.lineTo(180, 220);
        // path1.lineTo(170, 210);
        // path1.close();// 封闭
        // canvas.drawPath(path1, p);
        // /*
        // * Path类封装复合(多轮廓几何图形的路径
        // * 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
        // * (基于油漆的风格),或者可以用于剪断或画画的文本在路径。
        // */
        //
        // // 画圆角矩形
        // p.setStyle(Paint.Style.FILL);// 充满
        // p.setColor(Color.LTGRAY);
        // p.setAntiAlias(true);// 设置画笔的锯齿效果
        // canvas.drawText("画圆角矩形:", 10, 260, p);
        // RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形
        // canvas.drawRoundRect(oval3, 20, 15, p);// 第二个参数是x半径，第三个参数是y半径
        //
        // // 画贝塞尔曲线
        // canvas.drawText("画贝塞尔曲线:", 10, 310, p);
        // p.reset();
        // p.setStyle(Paint.Style.STROKE);
        // p.setColor(Color.GREEN);
        //
        // Path path2 = new Path();
        // path2.moveTo(100, 320);// 设置Path的起点
        // path2.quadTo(150, 310, 170, 400); // 设置贝塞尔曲线的控制点坐标和终点坐标
        // canvas.drawPath(path2, p);// 画出贝塞尔曲线
        //
        // // 画点
        // p.setColor(Color.RED);
        // p.setStyle(Paint.Style.FILL);
        // canvas.drawText("画点：", 10, 390, p);
        // canvas.drawPoint(100, 320, p);// 画一个点
        // canvas.drawPoints(new float[] { 150, 310, 170, 400 }, p);// 画多个点
        //
        // // 画图片，就是贴图
        // Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
        // R.drawable.ic_launcher);
        // canvas.drawBitmap(bitmap, 250, 360, p);

        // 获取数据后，判断最大最小值
        // 测试值
        mpoint.x = 50;
        mpoint.y = 50;
        SourceData1 = new float[]{5.0f, 7.0f, 15.0f, 25.0f, 35.0f, 55.0f,
                65.0f};
        SourceData2 = new float[]{5.0f, 20.0f, 15.0f, 30.0f, 25.0f, 32.0f,
                35.0f, 25.0f, 45.0f, 45.0f};
        SourceData3 = new float[]{5.0f, 20f, 15.0f, 50f, 25.0f, 70f, 35.0f,
                80f, 45.0f, 90f};
        Point[] SerialData1 = null;

        SerialData1 = new Point[SourceData1.length];

        Point[] SerialData2 = null;
        if (SourceData2.length % 2 == 0) {
            SerialData2 = new Point[SourceData2.length / 2];
        }
        Point[] SerialData3 = null;
        if (SourceData3.length % 2 == 0) {
            SerialData3 = new Point[SourceData3.length / 2];
        }
        // 判断最大最小
        for (int i = 0; i < SourceData1.length; i += 2) {
            // /min=(a<b)?a:b;
            if (i == 0 && !MaxOrMinIsSet)// 初始不设最大最小值
            {

                LMax = SourceData1[i];
                LMin = SourceData1[i];

            }

            LMax = (LMax < SourceData1[i]) ? SourceData1[i] : LMax;
            LMin = (LMin > SourceData1[i]) ? SourceData1[i] : LMin;

        }
        float dx = (XMax - XMin) / XScale;
        XMax = XMax + dx;
        XMin = XMin - dx;
        float dY = (LMax - LMin) / YScale;
        LMax = LMax + dY;
        LMin = LMin - dY;
        float dU = (RUMax - RUMin) / YUScale;
        RUMax = RUMax + dU;
        RUMin = RUMin - dU;
        float dD = (RDMax - RDMin) / YDScale;
        RDMax = RDMax + dD;
        RDMin = RDMin - dD;

        // 转换绘图点数据
        for (int i = 0; i < SourceData1.length; i++) {
            // 横坐标位置，三个数组的奇数位为X坐标
            // 基值+实际值/（最大-最小）*绘图区宽度
            float tmpx = (float) (mpoint.x + ((i * 0.25) - 0) / (10 - 0)
                    * CurveWidth);
            // 左侧纵坐标
            float tmpy = mpoint.y + CurveHeight - (SourceData1[i] - LMin)
                    / (LMax - LMin) * CurveHeight;
            SerialData1[i] = new Point((int) tmpx, (int) tmpy);
            // 右侧纵坐标
            // if (SpeedDrawOrNot) {
            // float tmpyu = mpoint.y + CurveHeight / 2 - YUDivision
            // - (SourceData2[i] - RUMin) / (RUMax - RUMin) * 5
            // * YUDivision;
            // SerialData2[i] = new Point((int) tmpx, (int) tmpyu);
            // }
            // // 右侧纵坐标
            // if (DisplacementDrawOrNot) {
            // float tmpyd = mpoint.y + CurveHeight - YDDivision
            // - (SourceData3[i] - RDMin) / (RDMax - RDMin) * 5
            // * YDDivision;
            // SerialData3[i] = new Point((int) tmpx, (int) tmpyd);
            // }

        }

        p.setColor(Color.DKGRAY);
        // p.setStyle(Style.STROKE);
        p.setTextAlign(Align.LEFT);

        for (int i = 0; i <= Math.max(XNum, YNum); i++) {
            if (i <= XNum) {
                p.setColor(XColor);
                p.setTextAlign(Align.CENTER);
                canvas.drawText(df3.format(i * (XMax - XMin) / XNum + XMin),
                        mpoint.x + i * XDivision, mpoint.y + CurveHeight
                                + LabelOffSet + LabelOffSet, p);
                Xaxis[i * 4] = mpoint.x + i * XDivision;
                Xaxis[i * 4 + 1] = mpoint.y + CurveHeight;
                Xaxis[i * 4 + 2] = mpoint.x + i * XDivision;
                Xaxis[i * 4 + 3] = mpoint.y + CurveHeight + XaxisLine;

            }
            if (i <= YNum) {
                p.setColor(YColor);
                p.setTextAlign(Align.CENTER);
                canvas.drawText(df3.format(i * (LMax - LMin) / YNum + LMin),
                        mpoint.x - 2 * LabelOffSet, mpoint.y + CurveHeight
                                - YDivision * i, p);
                Yaxis[i * 4] = mpoint.x;
                Yaxis[i * 4 + 1] = mpoint.y + CurveHeight - YDivision * i;
                Yaxis[i * 4 + 2] = mpoint.x + YaxisLine;
                Yaxis[i * 4 + 3] = mpoint.y + CurveHeight - YDivision * i;

            }
            if (i <= YUNum) {
                p.setColor(YUColor);
                p.setTextAlign(Align.LEFT);
                canvas.drawText(
                        df3.format(i * (RUMax - RUMin) / YUNum + RUMin),
                        mpoint.x + CurveWidth + LabelOffSet, mpoint.y
                                + CurveHeight / 2 - 3 * LabelOffSet
                                - YUDivision * i, p);
                YUaxis[i * 4] = mpoint.x + CurveWidth;
                YUaxis[i * 4 + 1] = mpoint.y + CurveHeight / 2 - YUDivision
                        * (i + 1);
                YUaxis[i * 4 + 2] = mpoint.x + CurveWidth + YUaxisLine;
                YUaxis[i * 4 + 3] = mpoint.y + CurveHeight / 2 - YUDivision
                        * (i + 1);

            }
            if (i <= YDNum) {
                p.setColor(YDColor);
                canvas.drawText(
                        df3.format(i * (RDMax - RDMin) / YDNum + RDMin),
                        mpoint.x + CurveWidth + LabelOffSet, mpoint.y
                                + CurveHeight - 3 * LabelOffSet - YDDivision
                                * i, p);
                YDaxis[i * 4] = mpoint.x + CurveWidth;
                YDaxis[i * 4 + 1] = mpoint.y + CurveHeight - YDDivision
                        * (i + 1);
                YDaxis[i * 4 + 2] = mpoint.x + CurveWidth + YDaxisLine;
                YDaxis[i * 4 + 3] = mpoint.y + CurveHeight - YDDivision
                        * (i + 1);

            }
        }
        p.setColor(YColor);
        canvas.drawText("h(Pa)", mpoint.x - LabelOffSet, mpoint.y - 2
                * LabelOffSet, p);
        // Y轴轴线
        Yaxis[(YNum + 1) * 4] = mpoint.x;
        Yaxis[(YNum + 1) * 4 + 1] = mpoint.y;
        Yaxis[(YNum + 1) * 4 + 2] = mpoint.x;
        Yaxis[(YNum + 1) * 4 + 3] = mpoint.y + CurveHeight;
        canvas.drawLines(Yaxis, p);
        for (int i = 0; i < SerialData1.length - 1; i++) {

            canvas.drawLine(SerialData1[i].x, SerialData1[i].y,
                    SerialData1[i + 1].x, SerialData1[i + 1].y, p);
        }

        p.setColor(YUColor);
        // canvas.drawText("p(kW)", mpoint.x + CurveWidth + 2 * LabelOffSet,
        // mpoint.y - LabelOffSet, p);
        // YUaxis[(YUNum + 1) * 4] = mpoint.x + CurveWidth;
        // YUaxis[(YUNum + 1) * 4 + 1] = mpoint.y + CurveHeight / 2 - (YUNum +
        // 1)
        // * YUDivision;
        // YUaxis[(YUNum + 1) * 4 + 2] = mpoint.x + CurveWidth;
        // YUaxis[(YUNum + 1) * 4 + 3] = mpoint.y + CurveHeight / 2 -
        // YUDivision;
        // canvas.drawLines(YUaxis, p);
        // Point[] tmpYU = getCtrlPoint(SerialData2, 0.25f, 0.25f);
        // for (int i = 0; i < SerialData2.length - 1; i++) {
        //
        // p.setStyle(Paint.Style.STROKE);
        // Path pathtmp = new Path();
        // pathtmp.moveTo(SerialData2[i].x, SerialData2[i].y);
        // pathtmp.cubicTo(tmpYU[i * 2].x, tmpYU[i * 2].y, tmpYU[i * 2 + 1].x,
        // tmpYU[i * 2 + 1].y, SerialData2[i + 1].x,
        // SerialData2[i + 1].y);
        // canvas.drawPath(pathtmp, p);
        //
        // }
        // p.setColor(YDColor);
        // canvas.drawText("η(%)", mpoint.x + CurveWidth + 2 * LabelOffSet,
        // mpoint.y + CurveHeight / 2 - LabelOffSet, p);
        // YDaxis[(YDNum + 1) * 4] = mpoint.x + CurveWidth;
        // YDaxis[(YDNum + 1) * 4 + 1] = mpoint.y + CurveHeight - (YDNum + 1)
        // * YDDivision;
        // YDaxis[(YDNum + 1) * 4 + 2] = mpoint.x + CurveWidth;
        // YDaxis[(YDNum + 1) * 4 + 3] = mpoint.y + CurveHeight - YDDivision;
        // canvas.drawLines(YDaxis, p);
        // Point[] tmpYD = getCtrlPoint(SerialData3, 0.25f, 0.25f);
        // for (int i = 0; i < SerialData2.length - 1; i++) {
        //
        // p.setStyle(Paint.Style.STROKE);
        // Path pathtmp = new Path();
        // pathtmp.moveTo(SerialData3[i].x, SerialData3[i].y);
        // pathtmp.cubicTo(tmpYD[i * 2].x, tmpYD[i * 2].y, tmpYD[i * 2 + 1].x,
        // tmpYD[i * 2 + 1].y, SerialData3[i + 1].x,
        // SerialData3[i + 1].y);
        // canvas.drawPath(pathtmp, p);
        //
        // }

        p.setColor(XColor);
        canvas.drawText("Q(m³/s)", mpoint.x + CurveWidth + LabelOffSet,
                mpoint.y + CurveHeight - LabelOffSet, p);
        Xaxis[(XNum + 1) * 4] = mpoint.x;
        Xaxis[(XNum + 1) * 4 + 1] = mpoint.y + CurveHeight;
        Xaxis[(XNum + 1) * 4 + 2] = mpoint.x + CurveWidth;
        Xaxis[(XNum + 1) * 4 + 3] = mpoint.y + CurveHeight;
        canvas.drawLines(Xaxis, p);
        // 曲线数据转换

    }

    private Point[] getCtrlPoint(Point[] ps, float OffSetA, float OffSetB) {
        Point[] mres = new Point[ps.length * 2 - 2];
        int PreIndex = 0;
        int AftIndex1 = 0;
        int AftIndex2 = 0;
        for (int i = 0; i < ps.length - 1; i++) {
            PreIndex = i - 1;
            AftIndex1 = i + 1;
            AftIndex2 = i + 2;
            if (PreIndex < 0) {
                PreIndex = 0;
            }
            if (AftIndex1 >= ps.length) {
                AftIndex1 = ps.length - 1;
            }
            if (AftIndex2 >= ps.length) {
                AftIndex2 = ps.length - 1;
            }

            mres[i * 2] = new Point((int) (ps[i].x + OffSetA
                    * (ps[AftIndex1].x - ps[PreIndex].x)),
                    (int) (ps[i].y + OffSetA
                            * (ps[AftIndex1].y - ps[PreIndex].y)));
            mres[i * 2 + 1] = new Point((int) (ps[AftIndex1].x - OffSetB
                    * (ps[AftIndex2].x - ps[i].x)),
                    (int) (ps[AftIndex1].y - OffSetB
                            * (ps[AftIndex2].y - ps[i].y)));
        }

        return mres;
    }

    public void SetPoint(int a, int b) {
        mpoint = new Point(a, b);
    }

    public void SetResourceDataY(float[] mr) {
        SourceData1 = new float[mr.length];
        System.arraycopy(mr, 0, SourceData1, 0, mr.length);
    }

    public void SetResourceDataU(float[] mr) {
        SourceData2 = new float[mr.length];
        System.arraycopy(mr, 0, SourceData2, 0, mr.length);
    }

    public void SetResourceDataD(float[] mr) {
        SourceData3 = new float[mr.length];
        System.arraycopy(mr, 0, SourceData3, 0, mr.length);
    }

    public float getPhUAB() {
        return phUAB;
    }

    public void setPhUAB(float phUAB) {
        this.phUAB = phUAB;
    }

    public float getPhUBC() {
        return phUBC;
    }

    public void setPhUBC(float phUBC) {
        this.phUBC = phUBC;
    }

    public float getPhUCA() {
        return phUCA;
    }

    public void setPhUCA(float phUCA) {
        this.phUCA = phUCA;
    }

    public float getPhUIA() {
        return phUIA;
    }

    public void setPhUIA(float phUIA) {
        this.phUIA = phUIA;
    }

    public float getPhUIB() {
        return phUIB;
    }

    public void setPhUIB(float phUIB) {
        this.phUIB = phUIB;
    }

    public float getPhUIC() {
        return phUIC;
    }

    public void setPhUIC(float phUIC) {
        this.phUIC = phUIC;
    }

    public float getCurveWidth() {
        return CurveWidth;
    }

    public void setCurveWidth(float curveWidth) {
        CurveWidth = curveWidth;
    }

    public float getCurveHeight() {
        return CurveHeight;
    }

    public void setCurveHeight(float curveHeight) {
        CurveHeight = curveHeight;
    }


    public int getAColor() {
        return AColor;
    }

    public void setAColor(int aColor) {
        AColor = aColor;
    }

    public int getBColor() {
        return BColor;
    }

    public void setBColor(int bColor) {
        BColor = bColor;
    }

    public int getCColor() {
        return CColor;
    }

    public void setCColor(int cColor) {
        CColor = cColor;
    }

    public int getCurveType() {
        return CurveType;
    }

    public void setCurveType(int curveType) {
        CurveType = curveType;
    }

    public int getHamType() {
        return hamType;
    }

    public void setHamType(int hamType) {
        this.hamType = hamType;
    }

    public float getUA() {
        return UA;
    }

    public void setUA(float uA) {
        UA = uA;
    }

    public float getIA() {
        return IA;
    }

    public void setIA(float iA) {
        IA = iA;
    }

    public float getUB() {
        return UB;
    }

    public void setUB(float uB) {
        UB = uB;
    }

    public float getIB() {
        return IB;
    }

    public void setIB(float iB) {
        IB = iB;
    }

    public float getUC() {
        return UC;
    }

    public void setUC(float uC) {
        UC = uC;
    }

    public float getIC() {
        return IC;
    }

    public void setIC(float iC) {
        IC = iC;
    }

    public int getTestMethod() {
        return TestMethod;
    }

    public void setTestMethod(int testMethod) {
        TestMethod = testMethod;
    }

    public void setData(motorData mdata) {
        UA = (float) mdata.getUA();
        UB = (float) mdata.getUB();
        UC = (float) mdata.getUC();
        IA = (float) mdata.getIA();
        IB = (float) mdata.getIB();
        IC = (float) mdata.getIC();
        phUAB = (float) mdata.getPhUAB();
        phUBC = (float) mdata.getPhUBC();
        phUCA = (float) mdata.getPhUCA();
        phUIA = (float) mdata.getPhUIA();
        phUIB = (float) mdata.getPhUIB();
        phUIC = (float) mdata.getPhUIC();
        TestMethod = mdata.getMethod();
        hamIA = mdata.getHarmIA();
        hamIB = mdata.getHarmIB();
        hamIC = mdata.getHarmIB();
        hamUA = mdata.getHarmUA();
        hamUB = mdata.getHarmUB();
        hamUC = mdata.getHarmUC();
    }
}
