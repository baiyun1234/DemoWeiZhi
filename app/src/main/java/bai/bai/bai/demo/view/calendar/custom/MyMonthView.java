package bai.bai.bai.demo.view.calendar.custom;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import bai.bai.bai.demo.view.calendar.Calendar;
import bai.bai.bai.demo.view.calendar.MonthView;

public class MyMonthView extends MonthView {

    private Paint mTextPaint = new Paint();//普通文本设置

    private int mRadius;//圆的半径

    public MyMonthView(Context context) {
        super(context);

        mTextPaint.setTextSize(35);
        mTextPaint.setColor(0xff336666);
        mTextPaint.setAntiAlias(true);//防锯齿
        mTextPaint.setFakeBoldText(false);//设置为粗体
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        //兼容硬件加速无效的代码
        setLayerType(View.LAYER_TYPE_SOFTWARE, mSelectedPaint);
        //4.0以上硬件加速会导致无效
        mSelectedPaint.setMaskFilter(new BlurMaskFilter(25, BlurMaskFilter.Blur.SOLID));

    }

    /**
     * 开始绘制前的钩子，这里做一些初始化的操作，每次绘制只调用一次，性能高效
     * 没有需要可忽略不实现
     * 例如：
     * 1、需要绘制圆形标记事件背景，可以在这里计算半径
     * 2、绘制矩形选中效果，也可以在这里计算矩形宽和高
     */
    @Override
    protected void onPreviewHook() {
        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;
        mSchemePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 绘制选中的日子
     *
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param y         日历Card y起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return true 则绘制onDrawScheme，因为这里背景色不是是互斥的
     *
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSchemePaint);
        return false;
    }

    /**
     * 绘制标记的事件日子
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     * @param y        日历Card y起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSchemePaint);
    }

    /**
     * 绘制文本
     *
     * @param canvas     canvas
     * @param calendar   日历calendar
     * @param x          日历Card x起点坐标
     * @param y          日历Card y起点坐标
     * @param hasScheme  是否是标记的日期
     * @param isSelected 是否选中
     */
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {

        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;

        mCurDayTextPaint.setColor(0xff33FF00);//当天文本颜色
        mSchemeTextPaint.setColor(0xff0033ff);//标记文本颜色
        mSelectTextPaint.setColor(0xff00ffff);//选中文本颜色

        mSelectedPaint.setColor(0xeeff6600);//选中背景

        if (isSelected) {//如果是选中
            canvas.drawCircle(x + mItemWidth / 2, y + mItemHeight / 2, mRadius, mSelectedPaint);//绘制背景
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, mSelectTextPaint);//绘制文本
        } else if (hasScheme) {//如果是被标记
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, mSchemeTextPaint);
        } else {//普通
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, calendar.isCurrentDay() ? mCurDayTextPaint : mTextPaint);
        }
    }

    /**
     * dp转px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
