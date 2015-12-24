package com.towenqi.qianlv.memory.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by qianlv on 2015/9/18.
 */
public class MoonImageView extends ImageView {
    Paint paint;

    PointF topLeft;
    PointF middle;
    PointF rightTop;
    PointF rightBottom;
    PointF leftBottom;

    public MoonImageView(Context context) {
        super(context);

        init();

    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
    }

    public MoonImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoonImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        topLeft = new PointF(0,height*0.8f);
        middle = new PointF(width*0.5f,height);
        rightTop = new PointF(width,height*0.8f);
        rightBottom = new PointF(width,height);
        leftBottom = new PointF(0,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path moonShape = new Path();
        moonShape.moveTo(topLeft.x,topLeft.y);
        moonShape.quadTo(middle.x, middle.y, rightTop.x, rightTop.y);
        moonShape.lineTo(rightBottom.x, rightBottom.y);
        moonShape.lineTo(leftBottom.x, leftBottom.y);
        moonShape.close();

        canvas.drawPath(moonShape,paint);

    }
}
