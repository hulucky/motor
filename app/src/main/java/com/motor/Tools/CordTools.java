package com.motor.Tools;

import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CordTools {
	  /// <summary>
    /// ��������
    /// </summary>
    /// <param name="v1"></param>
    /// <param name="v2"></param>
    /// <returns></returns>
    public static double GetLength2Vectors(Point v1, Point v2)
    {
        double lth;
        lth = Math.sqrt(Math.pow((v1.x - v2.x), 2) + Math.pow((v1.y - v2.y), 2) );
        return lth;

    }
    /// <summary>
    /// ����ά������
    /// </summary>
    /// <param name="p1"></param>
    /// <param name="p2"></param>
    /// <returns></returns>
    public static double GetLength2Points(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }
    /// <summary>
    /// ��ü�ͷ��
    /// </summary>
    /// <param name="v1">����㣨��ͷβ�㣩</param>
    /// <param name="v2">��ͷ�˵�</param>
    /// <param name="ArrowLength">��ͷ����</param>
    /// <returns>��ͷ�㳤��4</returns>
    public static Point[] GetArrowBy2Vertex(Point v1, Point v2, double ArrowLength)
    {
        Point[] res = new Point[6];
        res[0] = v2;
        res[1] = new Point();
        res[2] = v2;
        res[3] = new Point();
        res[4] = v2;
        res[5] = v1;
        double mAngle = GetStartAngleBy2Vectors(v1, v2);
        res[1].x = (int) (res[0].x + ArrowLength * Math.cos((mAngle - 15) * Math.PI / 180));
        res[1].y =(int)(res[0].y + ArrowLength * Math.sin((mAngle - 15) * Math.PI / 180));
      
        res[3].x =(int)(res[0].x + ArrowLength * Math.cos((mAngle + 15) * Math.PI / 180));
        res[3].y =(int)(res[0].y + ArrowLength * Math.sin((mAngle + 15) * Math.PI / 180));
     
        return res;


    }
    /// <summary>
    /// ����N�����ظ������ɫ
    /// </summary>
    /// <param name="n"></param>
    /// <returns></returns>
    public static List<Integer> GetRandomColor(int n)
    {
        List<Integer> res = new ArrayList<Integer>();
        Random rdm = new Random();
        int R = rdm.nextInt(255);
        int G = rdm.nextInt(255);
        int B = rdm.nextInt(255);
        Integer A  ;
        for (int i = 0; i < n; i++)
        {
            double ar = (R + i * 97 + 256) % 256;
            //byte arr = Convert.ToByte(ar);
            double aG = (G + i * 137 + 256)% 256;
            //byte agg     = Convert.ToByte(aG); 
            double ab = (B + i * 179 + 256)% 256;
            // byte abb = Convert.ToByte(ab);
            A = Color.rgb((int)(ar),(int)(aG),(int)(ab));

            //A =Color.FromArgb( Convert.ToByte(Math.IEEERemainder(R + i * 97,256)),
            //Convert.ToByte(Math.IEEERemainder(G + i * 137,256)),
            //Convert.ToByte(Math.IEEERemainder(B + i * 179,256)));
            res.add(A);
        }
        return res;

    }
    /// <summary>
    /// ����������ǰ�������ߴ�ֱ 2d
    /// </summary>
    /// <param name="v1">��һ��</param>
    /// <param name="v2">�ڶ���</param>
    /// <param name="v3">������</param>
    /// <returns></returns>
    public static float[] Perpendicular3Vectors(Point a, Point b, Point c)
    {
        float k;//б��
        float kc;//����б��
        float d;//���߳���
        kc = 1;
        k = 1;
        d = 0;
        if ((a.x - b.x) != 0)
        {


            k = (a.y - b.y) / (a.x - b.x);
        }
        else if ((a.x - b.x) == 0)
        {
            kc = 0;

        }
        if (k != 0)
        {


            kc = -1 / k;
        }
        d = c.y - kc * c.x;
        float[] re = new float[2];
        re[0] = kc;
        re[1] = d;
        return re;

    }
    /// <summary>
    /// �����д���
    /// </summary>
    /// <param name="V1"></param>
    /// <param name="V2"></param>
    /// <returns></returns>
    public static float[] PB2Vectors(Point V1, Point V2)
    {
        Point vmid = new Point((int)((V1.x + V2.x) / 2),(int)((V1.y + V2.y) / 2));
        float[] re = Perpendicular3Vectors(V1, V2, vmid);
        return re;

    }
    /// <summary>
    /// ֱ�߽����
    /// </summary>
    /// <param name="k1"></param>
    /// <param name="b1"></param>
    /// <param name="k2"></param>
    /// <param name="b2"></param>
    /// <returns></returns>
    public static Point GetTwoLinesCross(float k1, float b1, float k2, float b2)
    {
        Point JD = new Point();
        JD.x =(int)((b1 - b2) / (k2 - k1));
        JD.y =(int)( k1 * (b1 - b2) / (k2 - k1) + b1);

        return JD;

    }
    /// <summary>
    /// ֱ�߽���㣨һ���ߴ�ֱ��
    /// </summary>
    /// <param name="k1">б��</param>
    /// <param name="b1">����</param>
    /// <param name="Coordx">��ֱ������</param>
    /// <returns></returns>
    public static Point GetTwoLinesCrossPerp(float k1, float b1, float Coordx)
    {
        Point JD = new Point();
        JD.x =(int)( Coordx);
        JD.y =(int)( k1 * Coordx + b1);

        return JD;

    }
    /// <summary>
    /// ֱ�߽���㣨һ����ˮƽ��
    /// </summary>
    /// <param name="k1">б��</param>
    /// <param name="b1">����</param>
    /// <param name="Coordy">ˮƽ������</param>
    /// <returns></returns>
    public static Point GetTwoLinesCrossHoriz(float k1, float b1, float Coordy)
    {
        Point JD = new Point();
        JD.y =(int)( Coordy);
        JD.x =(int)((Coordy - b1) / k1);
     
        return JD;
    }
    /// <summary>
    /// ͨ��б�ʻ����ʼ�Ƕ�
    /// </summary>
    /// <param name="k">б��</param>
    /// 
    /// <returns></returns>
    public static double GetStartAngleByK(double k)
    {
        double gsa;
        gsa = Math.atan(k) * 180 / Math.PI;
        return gsa;
    }
    /// <summary>
    /// ͨ������Բ�Ļ����ʼ�Ƕ�
    /// </summary>
    /// <param name="v1">���</param>
    /// <param name="v2">Բ��</param>
    /// <returns></returns>
    public static double GetStartAngleBy2Vectors(Point v1, Point v2)
    {
        double gsa;
        gsa = GetStartAngleByK(GetKBy2Vectors(v1, v2));
        if (v1.x < v2.x)
        {
           
            gsa = Math.IEEEremainder(gsa + 180, 360);

        }
        else if (v1.x == v2.x)
        {
            if (v1.y > v2.y)
            {
                gsa = 90d;
            }
            else if (v1.y < v2.y)
            {
                gsa = -90d;
            }
            
        }
        return gsa;
    }
    /// <summary>
    /// ͨ�������㼰Բ�Ļ�û��ƽǶ�
    /// </summary>
    /// <param name="V1">��һ����</param>
    /// <param name="V2">�ڶ�����</param>
    /// <param name="AC">Բ��</param>
    /// <returns></returns>
    public static double GetArcAngleBy2VectorsAndAc(Point V1, Point V2, Point AC)
    {
        Point VMid = new Point();///�е�
        VMid.x = (V1.x + V2.x) / 2;
        VMid.y = (V1.y + V2.y) / 2;
   
        double k = GetLength2Vectors(V1, VMid) / GetLength2Vectors(VMid, AC);
        double gda = 2 * Math.atan(k) * 180 / Math.PI;
        if (GetKBy2Vectors(V1, AC) > GetKBy2Vectors(V1, V2))
        {
            gda = 360 - gda;
        }
        return gda;
    }
    ///<summary>
    ///ͨ����������Բ��
    ///</summary>
    ///<param name="V1">���</param>
    ///<param name="V2">�е�</param>
    ///<param name="V3">�յ�</param>
    ///<returns></returns>
    public static Point GetArcCenterBy3Vectors(Point V1, Point V2, Point V3)
    {
        float[] Pline1 = PB2Vectors(V1, V2);
        float[] Pline2 = PB2Vectors(V2, V3);
        Point AcPoint = GetTwoLinesCross(Pline1[0], Pline1[1], Pline2[0], Pline2[1]);

        return AcPoint;
    }
    /// <summary>
    /// һ��ͨ����һ����ת������(2d)
    /// </summary>
    /// <param name="v1">��׼��</param>
    /// <param name="v2">��תǰ������</param>
    /// <param name="mAngle">��ת�Ƕ�(�ǻ���)</param>
    /// <returns>��ת�������</returns>
    public static Point GetPointRotate(Point v1, Point v2, double mAngle)
    {
        Point res = new Point();
        mAngle = GetStartAngleBy2Vectors(v2, v1) + mAngle;
        double mLength = GetLength2Vectors(v1, v2);
        res.y =(int)(mLength * Math.sin(mAngle * Math.PI / 180) + v1.y);
        res.x =(int)(mLength * Math.cos(mAngle * Math.PI / 180) + v1.x);
      
        return res;

    }
    /// <summary>
    /// ��ȡ�ı�����λ��(2D)
    /// </summary>
    /// <param name="mLocationType">�ο�������0�����ϣ�1���ϣ�2���£�3���ϣ�4���У�5����</param>
    /// <param name="mText">�ı�</param>
    /// <param name="mLocation">�ο���</param>
    /// <param name="mSize">�ֺ�</param>
    /// <param name="mSpace">�ַ����</param>
    /// <param name="mLineAngle">�нǶ�</param>
    /// <param name="mPy">�߿��</param>
    /// <returns></returns>
    public static Point GetTextLocation(int mLocationType, String mText, Point mLocation, int mSize, int mspace, double mLineAngle, float mPy)
    {
        Point res = new Point();


        int mLength = mText.length();
        double mwidth = mSize;
        double mheight = mwidth * mPy;
        mLineAngle = mLineAngle * Math.PI / 180;
        
       
         switch (mLocationType)
        {
            case 0:
                {
                    res.x = mLocation.x;
                    res.y =(int)(mLocation.y - mheight);
                    break;
                }
            case 1:
                {
                    res.x =(int)(mLocation.x - mLength * mwidth - mspace * (mLength - 1));
                    res.y =(int)(mLocation.y - mheight);
                    break;
                }
            case 2:
                {
                    res.x =(int)(mLocation.x - mLength * mwidth - mspace * (mLength - 1));
                    res.y = mLocation.y;
                    break;
                }
            case 3:
                {
                    res.x =(int)(mLocation.x - (mLength * mwidth + mspace * (mLength - 1)) / 2);
                    res.y =(int)(mLocation.y - mheight);
                    break;


                }
            case 4:
                {
                    res.x =(int)(mLocation.x - (mLength * mwidth + mspace * (mLength - 1)) / 2);
                    res.y =(int)(mLocation.y - mheight / 2);
                    break;

                }
            case 5:
                {
                    res.x =(int)(mLocation.x - (mLength * mwidth + mspace * (mLength - 1)) / 2);
                    res.y =(int)(mLocation.y);
                    break;

                }
        }
         res = GetPointRotate(mLocation, res, -mLineAngle);
        return res;
    }
    
    /// <summary>
    /// ͨ�������б��
    /// </summary>
    /// <param name="V1"></param>
    /// <param name="V2"></param>
    /// <returns></returns>
    public static float GetKBy2Vectors(Point V1, Point V2)
    {
        float k;
        if (V2.x != V1.x)
        {
            k = (V2.y - V1.y);
            k = k / (V2.x - V1.x);
            return k;
        }
        else
        {
            return k = (float)(Math.pow(2, 63) - 1);
        }

    }
    /// <summary>
    /// �������ֱ��б�ʼ�ƫ�Ƴ���y=kx+b
    /// </summary>
    /// <param name="V1"></param>
    /// <param name="V2"></param>
    /// <returns></returns>
    public static float[] GetLineByTwoPoint(Point V1, Point V2)
    {
        float[] f = new float[2];
        if (V2.x != V1.x)
        {
            //f[0] = (V2.y - V1.y) / (V2.x - V1.x);
            f[0] = V2.y - V1.y;
            f[0] = f[0] / (V2.x - V1.x);
            f[1] = (V2.x * V1.y - V1.x * V2.y);
            f[1] = f[1] / (V2.x - V1.x);

        }
        else
        {
            f[0] = (float)(Math.pow(2, 63) - 1);
            f[1] = 0;
        }

        return f;
    }
    /// <summary>
    /// �������ֱ��б�ʼ�ƫ�Ƴ���y=kx+b _3D
    /// </summary>
    /// <param name="V1"></param>
    /// <param name="V2"></param>
    /// <returns></returns>
    public static float[] GetLineByTwo3D(Point V1, Point V2)
    {
        float[] f = new float[2];
        if (V2.x != V1.x)
        {
            //f[0] = (V2.y - V1.y) / (V2.x - V1.x);
            f[0] = V2.y - V1.y;
            f[0] = f[0] / (V2.x - V1.x);
            f[1] = (V2.x * V1.y - V1.x * V2.y);
            f[1] = f[1] / (V2.x - V1.x);

        }
        else
        {
            f[0] = (float)(Math.pow(2, 63) - 1);
            f[1] = 0;
        }

        return f;
    }
    /// <summary>
    /// �����ֱ�߱�׼����
    /// </summary>
    /// <returns></returns>
    public static double[] GetStandardLineByTwo3D(Point V1, Point V2)
    {
        double[] res = new double[3];
        double x1 = (double)(V1.x);
        double y1 = (double)(V1.y);
        double x2 = (double)(V2.x);
        double y2 = (double)(V2.y);
        res[0] = y1 - y2;
        res[1] = x2 - x1;
        res[2] = x1 * y2 - x2 * y1;
        return res;
    }
    /// <summary>
    /// xyƽ���ڵ㵽ֱ�߾���
    /// </summary>
    /// <param name="mLine">ֱ�߱�׼����</param>
    /// <param name="mPoint">������</param>
    /// <returns></returns>
    public static float GetLengthPointToLine(double[] mLine, Point mPoint)
    {
        float res;
        double A = mLine[0];
        double B = mLine[1];
        double C = mLine[2];
        float x = mPoint.x;
        float y = mPoint.y;
        res = (float)(Math.abs(A * x + B * y + C) / Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2)));
        return res;

    }
    /// <summary>
    /// xyƽ�������㹲��
    /// </summary>
    /// <param name="Point1"></param>
    /// <param name="Point2"></param>
    /// <param name="Point3"></param>
    /// <returns></returns>
    public static Boolean OneLine3Point(Point Point1, Point Point2, Point Point3)
    {
        double[] mLine = GetStandardLineByTwo3D(Point1, Point2);
        double res = mLine[0] * Point3.x + mLine[1] * Point3.y + mLine[2];
        if (Math.abs(res) < (1 / Math.pow(10, 6)))
            return true;
        else
            return false;
    }
    /// <summary>
    /// ����û��ƽǶȣ���x��нǣ�
    /// </summary>
    /// <param name="BasePoint">����</param>
    /// <param name="x">ָ����</param>
    /// <returns></returns>
    public static double GetArcAngleBy2Vectors(Point BasePoint, Point x)
    {
        double Rec =0.0d;
        if (x.x != BasePoint.x)
        {
            Rec = Math.atan((x.y - BasePoint.y) / (x.x - BasePoint.x)) * 180 / Math.PI;
            if (x.x < BasePoint.x)
            {
                Rec = 180 + Rec;
            }
            else if (x.y < BasePoint.y && x.x > BasePoint.x)
            {
                Rec = 360 + Rec;
            }
        }
        else if (x.x == BasePoint.x)
        {
            if (x.y > BasePoint.y)
                Rec = 90;
            else if (x.y < BasePoint.y)
                Rec = 270;
        }




        return Rec;
    }
    /// <summary>
    /// ͨ��������˻�û��ƽǶ�
    /// </summary>
    /// <param name="x1">˳ʱ���յ㣬��ʱ���������x</param>
    /// <param name="y1">˳ʱ���յ㣬��ʱ���������y</param>
    /// <param name="x2">˳ʱ����㣬��ʱ���յ�����x</param>
    /// <param name="y2">˳ʱ����㣬��ʱ���յ�����y</param>
    /// <returns></returns>
    public static double GetArcAngleByx(double x1, double y1, double x2, double y2)
    {
         double epsilon = 1.0e-6;
         double nyPI = Math.PI;
        double dist, dot, degree, angle;

        // normalize
        dist = Math.sqrt(x1 * x1 + y1 * y1);

        x1 /= dist;
        y1 /= dist;
        dist = Math.sqrt(x2 * x2 + y2 * y2);
        x2 /= dist;
        y2 /= dist;
        // dot product
        dot = x1 * x2 + y1 * y2;
        if (Math.abs(dot - 1.0) <= epsilon)
            angle = 0.0;
        else if (Math.abs(dot + 1.0) <= epsilon)
            angle = nyPI;
        else
        {
            double cross;

            angle = Math.acos(dot);
            //cross product
            cross = x1 * y2 - x2 * y1;
            // vector p2 is clockwise from vector p1 
            // with respect to the origin (0.0)
            if (cross < 0)
            {
                angle = 2 * nyPI - angle;
            }
        }
        degree = angle * 180.0 / nyPI;
        return degree;
    }
    /// <summary>
    /// �Ƿ���ʱ��
    /// </summary>
    /// <param name="x1">��ʼ����x</param>
    /// <param name="x2">��ʼ����y</param>
    /// <param name="y1">��������x</param>
    /// <param name="y2">��������y</param>
    /// <returns></returns>
    public static Boolean IsAntiClock(double x1, double x2, double y1, double y2)
    {
        double cross;
        cross = x1 * y2 - x2 * y1;
        // vector p2 is clockwise from vector p1 
        // with respect to the origin (0.0)
        if (cross < 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    ///// <summary>
    ///// ����������һ����
    ///// </summary>
    ///// <param name="startPoint"></param>
    ///// <param name="endPoint"></param>
    ///// <param name="DividePara"></param>
    ///// <returns></returns>
    //public static GLLocationCollections DivideLineByPoint(Point startPoint, Point endPoint, int DividePara)
    //{
    //    GLLocationCollections result = new GLLocationCollections();

    //    float mx = new float();
    //    float my = new float();
    //    float mz = new float();
    //    if (DividePara > 0)
    //    {
    //        mx = (endPoint.x - startPoint.x) / (DividePara);
    //        my = (endPoint.y - startPoint.y) / (DividePara);
    //        mz = (endPoint.Z - startPoint.Z) / (DividePara);
    //    }

    //    result.Add(startPoint);
    //    for (int i = 1; i < DividePara; i++)
    //    {
    //        Point newPoint = new Point();
    //        newPoint.x = startPoint.x + mx *(int)i;
    //        newPoint.y = startPoint.y + my *(int)i;
    //        newPoint.Z = startPoint.Z + mz *(int)i;// +(int)10;
    //        result.Add(newPoint);
    //    }

    //    result.Add(endPoint);
    //    return result;

    //}
    ///// <summary>
    ///// ���������һ����
    ///// </summary>
    ///// <param name="startPoint"></param>
    ///// <param name="endPoint"></param>
    ///// <param name="mLength"></param>
    ///// <returns></returns>
    //public static GLLocationCollections DivideLineByLength(Point startPoint, Point endPoint, double divideLength)
    //{
    //    GLLocationCollections result = new GLLocationCollections();

    //    Point tempPoint = new Point();
    //    //float mx=new float();
    //    //float my=new float();
    //    //float mz=new float();
    //    double mlength = GetLength2Vectors(startPoint, endPoint);
    //    float mx =(int)(divideLength * (endPoint.x - startPoint.x) / mlength);
    //    float my =(int)(divideLength * (endPoint.y - startPoint.y) / mlength);
    //    float mz =(int)(divideLength * (endPoint.Z - startPoint.Z) / mlength);
    //    result.Add(startPoint);
    //    //tempPoint = startPoint;
    //    float Bx = new float();
    //    float By = new float();
    //    float BZ = new float();
    //    Bx = startPoint.x;
    //    By = startPoint.y;
    //    BZ = startPoint.Z;
    //    while (mlength > (2 * divideLength))
    //    {
    //        Point newPoint = new Point();
    //        mlength -= divideLength;
    //        Bx += mx;
    //        By += my;
    //        BZ += mz;

    //        newPoint.x = Bx;
    //        newPoint.y = By;
    //        newPoint.Z = BZ;
    //        result.Add(newPoint);

    //    }
    //    result.Add(endPoint);
    //    return result;
    //}
    /// <summary>
    /// �������ֱ�߼н� ��ά����
    /// </summary>
    /// <param name="V1">��һ��ֱ�߶˵�</param>
    /// <param name="V2">�ڶ���ֱ�߶˵�</param>
    /// <param name="AC">����</param>
    /// <returns></returns>
    public static double GetAngleTwoLine(Point V1, Point V2, Point AC)
    {
        float x1, y1, z1, x2, y2, z2;
        x1 = V1.x - AC.x;
        x2 = V2.x - AC.x;
        y1 = V1.y - AC.y;
        y2 = V2.y - AC.y;
    
        float InnerProduct = x1 * x2 + y1 * y2 ;
        float Module1 = (float) Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2) );
        float Module2 = (float) Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2) );

        double gda ;
        gda = (Math.acos(InnerProduct / (Module1 * Module2))) * 180 / Math.PI;

        return gda;
    }
    /// <summary>
    /// �Ƚ���ֱ�߼н���ָ��ֵ �н�С��ָ��ֵ����TRUE
    /// </summary>
    /// <param name="V1">ֱ��1�˵�</param>
    /// <param name="V2">ֱ��2�˵�</param>
    /// <param name="AC">����</param>
    /// <param name="mAngle">ָ���Ƕ�</param>
    /// <returns></returns>
    public static Boolean CompareAngle(Point V1, Point V2, Point AC, double mAngle)
    {
        Boolean res;
        double tempAngle = 180 - GetAngleTwoLine(V1, V2, AC);
        if (tempAngle > mAngle)
            res = false;
        else
            res = true;
        return res;

    }
    /// <summary>
    /// ��ȡ���ε��ĵ�����
    /// </summary>
    /// <param name="v1"></param>
    /// <param name="v2"></param>
    /// <param name="v3"></param>
    /// <returns></returns>
    public static Point GetLastVertex(Point v1, Point v2, Point v3)
    {
        Point res = new Point((int)(v1.x - (v2.x - v3.x)),
       (int)(v1.y - (v2.y - v3.y)));
        return res;
    }
    /// <summary>
    /// �Ƚ�����������ָ��ֵ��С��ָ��ֵ����TRUE
    /// </summary>
    /// <param name="V1">���</param>
    /// <param name="V2">�յ�</param>
    /// <param name="mLength">ָ������</param>
    /// <returns></returns>
    public static Boolean CompareLength(Point V1, Point V2, double mLength)
    {
        Boolean res;
        double templength = GetLength2Vectors(V1, V2);
        if (templength > mLength)
            res = false;
        else
            res = true;
        return res;
    }
    ///// <summary>
    ///// ������Բ�����㷵����Բһ��ʽԲ�ķ���
    ///// </summary>
    ///// <param name="centerpoint">Բ��</param>
    ///// <param name="startpoint">���</param>
    ///// <param name="endpoint">�յ�</param>
    ///// <param name="midpoint">�е�</param>
    ///// <returns>ABCf</returns>
    //public static double[] GetEllipseCenterFomuleByVertex(Point centerpoint, Point startpoint, Point endpoint, Point midpoint)
    //{
    //    double[] res = new double[4];
    //    double[,] A = new double[3, 4];
    //    A[0, 0] = Math.pow((startpoint.x - centerpoint.x), 2);
    //    A[0, 1] = 2 * (startpoint.x - centerpoint.x) * (startpoint.y - centerpoint.y);
    //    A[0, 2] = Math.pow((startpoint.y - centerpoint.y), 2);
    //    A[1, 0] = Math.pow((endpoint.x - centerpoint.x), 2);
    //    A[1, 1] = 2 * (endpoint.x - centerpoint.x) * (endpoint.y - centerpoint.y);
    //    A[1, 2] = Math.pow((endpoint.y - centerpoint.y), 2);
    //    A[2, 0] = Math.pow(midpoint.x - centerpoint.x, 2);
    //    A[2, 1] = 2 * (midpoint.x - centerpoint.x) * (midpoint.y - centerpoint.y);
    //    A[2, 2] = Math.pow(midpoint.y - centerpoint.y, 2);
    //    res = GetSolve3E(A);
    //    return res;
    //}
    /// <summary>
    /// ������Բ����һ��Բ��ʽ����Բ����һ��ʽ
    /// </summary>
    /// <param name="A">һ��Բ��ʽ����������4��double����</param>
    ///<param name="Center">Բ������</param>
    /// <returns>һ��ʽ���� ����5 double����</returns>
    public static double[] GetEllpseStandByCenter(double[] Ell, Point Center)
    {
        double[] res = new double[5];
        double A, B, C, D, E, F;
        double u = Center.x;
        double v = Center.y;
        A = Ell[0];
        B = Ell[1];
        C = Ell[2];
        D = -2 * A * u - B * v;
        E = -B * u - 2 * C * v;
        F = A * Math.pow(u, 2) + B * u * v + C * Math.pow(v, 2) + Ell[3];
        if (F != 0)
        {

            res[0] = A / F;
            res[1] = B / F;
            res[2] = C / F;
            res[3] = D / F;
            res[4] = E / F;

        }
        return res;

    }
    /// <summary>
    /// ��ȡ��Բ����
    /// </summary>
    /// <param name="Ell">��Բһ��ʽ����</param>
    /// <returns>����5��Բ��x,y,��ǣ����ᣬ����</returns>
    public static double[] GetEllipsePara(double[] Ell)
    {
        double[] res = new double[5];
        double Cx, Cy, Angle, LR, SR;
        double A, B, C, D, E;
        A = Ell[0];
        B = Ell[1];
        C = Ell[2];
        D = Ell[3];
        E = Ell[4];
        Cx = (B * E - 2 * C * D) / (4 * A * C - B * B);
        Cy = (B * D - 2 * A * E) / (4 * A * C - B * B);
        if (A == C)
        {
            Angle = Math.PI / 4;
        }
        else
        {
            Angle = (Math.atan(B / (A - C))) / 2;
        }
        double k = Math.sqrt((A - C) * (A - C) + B * B);
        //LR = Math.sqrt(2 * (A * Cx * Cx + C * Cy * Cy + B * Cx * Cy + 1)
        //    / (A + C + k/2));
        //SR =Math.sqrt( 2 * (A * Cx * Cx + C * Cy * Cy + B * Cx * Cy + 1)
        //    / (A + C - k/2));
        LR = Math.sqrt((1)
           / (A + C + k));
        SR = Math.sqrt((1)
            / (A + C - k));
        res[0] = Cx;
        res[1] = Cy;
        res[2] = Angle;
        res[3] = LR;
        res[4] = SR;
        return res;

    }
    ///// <summary>
    ///// ���ݿ��Ƶ��ȡ��Բ����
    ///// </summary>
    ///// <param name="CenterPoint"></param>
    ///// <param name="StartPoint"></param>
    ///// <param name="EndPoint"></param>
    ///// <param name="MidPoint"></param>
    //public static double[] GetEllipseParaByVertex(Point CenterPoint, Point StartPoint, Point EndPoint, Point MidPoint)
    //{
    //    double[] res = new double[5];
    //    res = GetEllipsePara(GetEllpseStandByCenter(GetEllipseCenterFomuleByVertex(CenterPoint, StartPoint, EndPoint, MidPoint), CenterPoint));
    //    return res;
    //}
    ///// <summary>
    ///// �������㷵��ƽ�淽��
    ///// </summary>
    ///// <param name="V1"></param>
    ///// <param name="V2"></param>
    ///// <param name="V3"></param>
    ///// <returns></returns>
    //public static double[] GetPlaneByThreePoint(Point V1, Point V2, Point V3)
    //{
    //    double[] res = new double[4];
    //    double[,] A = new double[3, 4];
    //    A[0, 0] = (double)(V1.x);
    //    A[0, 1] = (double)(V1.y);
    //    A[0, 2] = (double)(V1.Z);
    //    A[1, 0] = (double)(V2.x);
    //    A[1, 1] = (double)(V2.y);
    //    A[1, 2] = (double)(V2.Z);
    //    A[2, 0] = (double)(V3.x);
    //    A[2, 1] = (double)(V3.y);
    //    A[2, 2] = (double)(V3.Z);
    //    res = GetSolve3E(A);
    //    return res;
    //}
    ///// <summary>  
    ///// ��һ��ֱ����ƽ��Ľ���  
    ///// </summary>  
    ///// <param name="planeVector">ƽ��ķ�������������Ϊ3</param>  
    ///// <param name="planePoint">ƽ�澭����һ�����꣬����Ϊ3</param>  
    ///// <param name="lineVector">ֱ�ߵķ�������������Ϊ3</param>  
    ///// <param name="linePoint">ֱ�߾�����һ�����꣬����Ϊ3</param>  
    ///// <returns>���ؽ������꣬����Ϊ3</returns>  
    //public static float[] GetCrossLineAndPlane(float[] planeVector, float[] planePoint, float[] lineVector, float[] linePoint)
    //{
    //    float[] returnResult = new float[3];
    //    float vp1, vp2, vp3, n1, n2, n3, v1, v2, v3, m1, m2, m3, t, vpt;
    //    vp1 = planeVector[0];
    //    vp2 = planeVector[1];
    //    vp3 = planeVector[2];
    //    n1 = planePoint[0];
    //    n2 = planePoint[1];
    //    n3 = planePoint[2];
    //    v1 = lineVector[0];
    //    v2 = lineVector[1];
    //    v3 = lineVector[2];
    //    m1 = linePoint[0];
    //    m2 = linePoint[1];
    //    m3 = linePoint[2];
    //    vpt = v1 * vp1 + v2 * vp2 + v3 * vp3;
    //    //�����ж�ֱ���Ƿ���ƽ��ƽ��  
    //    if (vpt == 0)
    //    {
    //        returnResult = null;
    //    }
    //    else
    //    {
    //        t = ((n1 - m1) * vp1 + (n2 - m2) * vp2 + (n3 - m3) * vp3) / vpt;
    //        returnResult[0] = m1 + v1 * t;
    //        returnResult[1] = m2 + v2 * t;
    //        returnResult[2] = m3 + v3 * t;
    //    }
    //    return returnResult;
    //}
    ///// <summary>  
    ///// ��һ��ֱ����ƽ��Ľ���  (��������)
    ///// </summary>  
    ///// <param name="planeVector">ƽ��ķ��̣�����Ϊ4</param>  
    ///// <param name="planePoint">ƽ�澭����һ�����꣬����Ϊ3</param>  
    ///// <param name="lineVector">ֱ�ߵķ�������������Ϊ3</param>  
    ///// <param name="linePoint">ֱ�߾�����һ�����꣬����Ϊ3</param>  
    ///// <returns>���ؽ������꣬����Ϊ3</returns>  
    //public static Point GetCrossLineAndPlane(double[] planepara, Point planePoint, Point lineStartPoint, Point lineEndPoint)
    //{
    //    float[] returnResult = new float[3];
    //    float vp1, vp2, vp3, n1, n2, n3, v1, v2, v3, m1, m2, m3, t, vpt;
    //    vp1 = (float)(planepara[0]);
    //    vp2 = (float)(planepara[1]);
    //    vp3 = (float)(planepara[2]);
    //    n1 = planePoint.x;
    //    n2 = planePoint.y;
    //    n3 = planePoint.Z;

    //    //v1 = lineVector[0];
    //    //v2 = lineVector[1];
    //    //v3 = lineVector[2];
    //    v1 = lineStartPoint.x - lineEndPoint.x;
    //    v2 = lineStartPoint.y - lineEndPoint.y;
    //    v3 = lineStartPoint.Z - lineEndPoint.Z;

    //    m1 = lineEndPoint.x;
    //    m2 = lineEndPoint.y;
    //    m3 = lineEndPoint.Z;
    //    vpt = v1 * vp1 + v2 * vp2 + v3 * vp3;
    //    //�����ж�ֱ���Ƿ���ƽ��ƽ��  
    //    if (vpt == 0)
    //    {
    //        returnResult = null;
    //    }
    //    else
    //    {
    //        t = ((n1 - m1) * vp1 + (n2 - m2) * vp2 + (n3 - m3) * vp3) / vpt;
    //        returnResult[0] = m1 + v1 * t;
    //        returnResult[1] = m2 + v2 * t;
    //        returnResult[2] = m3 + v3 * t;
    //    }
    //    //return returnResult;
    //    Point res = new Point();


    //    if (returnResult != null)
    //    {
    //        res = new Point(returnResult[0], returnResult[1], returnResult[2]);
    //        return res;
    //    }
    //    else
    //        return null;
    //}
    ///// <summary>
    ///// ��ȡ��ָ��ƽ�洹ֱ��ƽ��
    ///// </summary>
    ///// <param name="ViewPlane"></param>
    ///// <param name="mStartPoint"></param>
    ///// <param name="mEndPoint"></param>
    ///// <returns></returns>
    //public static double[] GetPlanePerpByCrossLine(double[] ViewPlane, Point mStartPoint, Point mEndPoint)
    //{
    //    double[] res = new double[4];
    //    Point mVerPoint = new Point();
    //    mVerPoint.x = (float)(mStartPoint.x + ViewPlane[0]);
    //    mVerPoint.y = (float)(mStartPoint.x + ViewPlane[1]);
    //    mVerPoint.Z = (float)(mStartPoint.Z + ViewPlane[2]);
    //    res = GetPlaneByThreePoint(mStartPoint, mVerPoint, mEndPoint);
    //    return res;

    //}

    ///// <summary>
    ///// ��ƽ�淽��3��ʽ
    ///// </summary>
    ///// <param name="A"></param>
    ///// <returns></returns>
    //public static double[] GetSolve3E(double[,] A)
    //{
    //    double[] res = new double[4];
    //    res[0] = A[0, 1] * A[1, 2] - A[0, 1] * A[2, 2] - A[1, 1] * A[0, 2] + A[1, 1] * A[2, 2] + A[2, 1] * A[0, 2] - A[2, 1] * A[1, 2];
    //    //        a=         y1         z2    - y1        z3   -    y2      z1  +    y2     z3    +    y3      z1   -    y3       z2��
    //    res[1] = -A[0, 0] * A[1, 2] + A[0, 0] * A[2, 2] + A[1, 0] * A[0, 2] - A[1, 0] * A[2, 2] - A[2, 0] * A[0, 2] + A[2, 0] * A[1, 2];

    //    //       b=      -x1         z2   + x1         z3  +    x2      z1   -    x2        z3   -    x3     z1   +    x3      z2��
    //    res[2] = A[0, 0] * A[1, 1] - A[0, 0] * A[2, 1] - A[1, 0] * A[0, 1] + A[1, 0] * A[2, 1] + A[2, 0] * A[0, 1] - A[2, 0] * A[1, 1];
    //    //       c=       x1          y2   -    x1      y3  -     x2     y1  +    x2      y3   +   x3      y1    -    x3       y2��
    //    //d=-x1y2z3+x1y3z2+x2y1z3-x2y3z1-x3y1z2+x3y2z1
    //    res[3] = A[0, 0] * A[1, 1] * A[2, 2] - A[0, 0] * A[2, 1] * A[1, 2] - A[1, 0] * A[0, 1] * A[2, 2] + A[2, 0] * A[0, 1] * A[1, 2] + A[1, 0] * A[2, 1] * A[0, 2] - A[2, 0] * A[1, 1] * A[0, 2];
    //    //       d=      x1       y2          z3  -   x1       y3      z2   -   x2      y1       z3    +   x3        y1       z2   +   x2       y3      z1    -  x3         y2      z1
    //    return res;
    //}
    /// <summary>
    /// ����Ԫһ�η�����
    /// </summary>
    /// <param name="A">��������double[3,4]��ά����</param>
    /// <returns></returns>
    public static double[] GetSolveOfEquation31(double[][] A)
    {
        double d ;
        double[] x = new double[3];
        d = DETA(A); //��ϵ������ʽ��ֵ
        A = swap(0, A); //��x1��ϵ���볣�����
        x[0] = DETA(A) / d; //���x1
        A = swap(0, A); //�ָ�ԭ����ϵ���볣����
        A = swap(1, A); //��x2��ϵ���볣�����
        x[1] = DETA(A) / d; //���x2
        A = swap(1, A); //�ָ�ԭ����ϵ���볣����
        A = swap(2, A); //��x3��ϵ���볣�����
        x[2] = DETA(A) / d; //���x3
        A = swap(2, A); //�ָ�ԭ����ϵ���볣����
        return x;
    }
  
    protected static double DETA(double[][] A) //����ʽ����
    {
        return (
               A[0][ 0] * A[1][ 1] * A[2][ 2]
             + A[1][ 0] * A[2][ 1] * A[0][ 2]
             + A[2][ 0] * A[0][ 1] * A[1][ 2]
             - A[2][ 0] * A[1][ 1] * A[0][ 2]
             - A[0][ 0] * A[2][ 1] * A[1][ 2]
             - A[1][ 0] * A[0][ 1] * A[2][ 2]);
    }

    protected static double[][] swap(int k, double[][] A)//��������k��ϵ������
    {
        int i;
        double t;
        for (i = 0; i < 3; i++)
        {
            //t=B[i];
            //B[i]=A[i][k];
            //A[i][k]=t;
            t = A[i][ 4];
            A[i][ 4] = A[i][ k];
            A[i][ k] = t;
        }
        return A;
    }
   
}
