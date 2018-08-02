package com.jingnuo.quanmb.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.R;


public class SideBar extends View {
	
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    public static String[] A_Z = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z", "#" };
    private int choose = -1;
    private Paint paint = new Paint();
  
    private TextView mTextDialog;
  
    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;  
    }  
  
  
    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
    }  
  
    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  
  
    public SideBar(Context context) {
        super(context);  
    }  
  
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);  
        int height = getHeight();// ��ȡ��Ӧ�߶�
        int width = getWidth(); // ��ȡ��Ӧ���  
        int singleHeight = height / A_Z.length-2;// ��ȡÿһ����ĸ�ĸ߶�  (����-2������Ϊ�˺ÿ�����)
  
        for (int i = 0; i < A_Z.length; i++) {  
            paint.setColor(getResources().getColor(R.color.daohangzimu));  //
            paint.setTypeface(Typeface.DEFAULT_BOLD);  //��������
            paint.setAntiAlias(true);  //���ÿ����
            paint.setTextSize(25);  //������ĸ�����С
            // ѡ�е�״̬  
            if (i == choose) {  
                paint.setColor(getResources().getColor(R.color.yellow_background));  //ѡ�е���ĸ�ı���ɫ
                paint.setFakeBoldText(true);  //��������Ϊ����
            }  
            // x��������м�-�ַ�����ȵ�һ��.  
            float xPos = width / 2 - paint.measureText(A_Z[i]) / 2;  
            float yPos = singleHeight * i + singleHeight;  
            canvas.drawText(A_Z[i], xPos, yPos, paint);  //�������е���ĸ
            paint.reset();// ���û���  
        }  
  
    }  
  
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();  
        final float y = event.getY();// ���y����  
        final int oldChoose = choose;  
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;  
        final int c = (int) (y / getHeight() * A_Z.length);// ���y������ռ�ܸ߶ȵı���*b����ĳ��Ⱦ͵��ڵ��b�еĸ���.  
  
        switch (action) {  
        case MotionEvent.ACTION_UP:
            setBackgroundDrawable(new ColorDrawable(0x00000000));
            choose = -1;//

            invalidate();  
            if (mTextDialog != null) {  
                mTextDialog.setVisibility(View.INVISIBLE);
            }
            break;  
  
        default:
            setBackgroundResource(R.color.daohangzimu_background); //设置导航字母的背景颜色
            getBackground().setAlpha(100);
            if (oldChoose != c) {  //�ж�ѡ����ĸ�Ƿ����ı�
                if (c >= 0 && c < A_Z.length) {  
                    if (listener != null) {  
                        listener.onTouchingLetterChanged(A_Z[c]);  
                    }  
                    if (mTextDialog != null) {  
                        mTextDialog.setText(A_Z[c]);  
                        mTextDialog.setVisibility(View.VISIBLE);
                    }  
                    
                    choose = c;  
                    invalidate();  
                }  
            }  
  
            break;  
        }  
        return true;  
    }  
  

    public void setOnTouchingLetterChangedListener(  
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {  
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;  
    }  

    public interface OnTouchingLetterChangedListener {  
        public void onTouchingLetterChanged(String s);
    }

}
