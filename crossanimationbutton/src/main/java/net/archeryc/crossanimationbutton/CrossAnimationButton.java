package net.archeryc.crossanimationbutton;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * 一句话功能简述
 * 功能详细描述
 *
 * @author 杨晨 on 2017/7/4 15:09
 * @e-mail 247067345@qq.com
 * @see [相关类/方法](可选)
 */

public class CrossAnimationButton extends View {
    private Context mContext;

    private int mCrossColor;
    private int mCrossWidth;
    private int mCrossMargin;
    private int mCrossCorner;

    private boolean mExpanded = false;

    private static final float COLLAPSED_PLUS_ROTATION = 0f;
    private static final float EXPANDED_PLUS_ROTATION = 90f + 45f;

    private AnimatorSet mExpandAnimation = new AnimatorSet().setDuration(600);
    private AnimatorSet mCollapseAnimation = new AnimatorSet().setDuration(600);
    private OnCrossButtonStateChangedListener mOnCrossButtonStateChangedListener;

    public CrossAnimationButton(Context context) {
        this(context, null);
    }

    public CrossAnimationButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrossAnimationButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CrossAnimationButton, 0, 0);
        mCrossColor = typedArray.getColor(R.styleable.CrossAnimationButton_cross_color, Color.BLACK);
        mCrossWidth = typedArray.getDimensionPixelSize(R.styleable.CrossAnimationButton_cross_width, getResources().getDimensionPixelSize(R.dimen.cross_button_width));
        mCrossMargin = typedArray.getDimensionPixelSize(R.styleable.CrossAnimationButton_cross_margin, getResources().getDimensionPixelSize(R.dimen.cross_button_margin));
        mCrossCorner = typedArray.getDimensionPixelSize(R.styleable.CrossAnimationButton_cross_corner,0);
        updateBackground();
        initAnimation();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mExpanded) {
                    collapse();
                } else {
                    expand();
                }
            }
        });
    }

    private void initAnimation() {
        final OvershootInterpolator interpolator = new OvershootInterpolator();

        ObjectAnimator expandAnimator = ObjectAnimator.ofFloat(this, "rotation", COLLAPSED_PLUS_ROTATION, EXPANDED_PLUS_ROTATION);
        ObjectAnimator collapseAnimator = ObjectAnimator.ofFloat(this, "rotation", EXPANDED_PLUS_ROTATION, COLLAPSED_PLUS_ROTATION);

        expandAnimator.setInterpolator(interpolator);
        collapseAnimator.setInterpolator(interpolator);

        mExpandAnimation.play(expandAnimator);
        mCollapseAnimation.play(collapseAnimator);
    }

    /**
     * 展开
     */
    private void expand() {
        if (!mExpanded) {
            mExpanded = true;
            mCollapseAnimation.cancel();
            mExpandAnimation.start();
            if (mOnCrossButtonStateChangedListener != null) {
                mOnCrossButtonStateChangedListener.onExpanded();
            }
        }
    }

    /**
     * 收起
     */
    private void collapse() {
        if (mExpanded) {
            mExpanded = false;
            mExpandAnimation.cancel();
            mCollapseAnimation.start();
            if (mOnCrossButtonStateChangedListener != null) {
                mOnCrossButtonStateChangedListener.onCollapsed();
            }
        }
    }

    private void updateBackground() {
        Shape shape = new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                RectF rectHorizental=new RectF(mCrossMargin, getMeasuredHeight() / 2 - mCrossWidth / 2,
                        getMeasuredWidth() - mCrossMargin,
                        getMeasuredHeight() / 2 + mCrossWidth / 2);
                RectF rectVertical = new RectF(getMeasuredWidth() / 2 - mCrossWidth / 2, mCrossMargin,
                        getMeasuredWidth() / 2 + mCrossWidth / 2, getMeasuredHeight() - mCrossMargin);
                canvas.drawRoundRect(rectHorizental,mCrossCorner,mCrossCorner,paint);
                canvas.drawRoundRect(rectVertical,mCrossCorner,mCrossCorner,paint);
            }
        };
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(mCrossColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        setBackgroundCompat(shapeDrawable);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private void setBackgroundCompat(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public interface OnCrossButtonStateChangedListener {

        void onExpanded();

        void onCollapsed();
    }

    public void setCrossButtonStateChangeListener(OnCrossButtonStateChangedListener listener) {
        this.mOnCrossButtonStateChangedListener = listener;
    }
}
