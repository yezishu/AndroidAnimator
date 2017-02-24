package yzs.animator.yzsanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Des：ValuesAnimator使用
 * creat by Zishu.Ye on 2017/2/24  13:42
 */
public class ValuesAnimatorActivity extends AppCompatActivity {
    public static final String TAG="ValuesAnimatorActivity";
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_animator);
        ButterKnife.bind(this);
    }

    public static void startValuesAnimator(Context context){
        context.startActivity(new Intent(context,ValuesAnimatorActivity.class));
    }

    @OnClick({R.id.btn_down,R.id.btn_PWX})
    void onClick(View v){
        switch (v.getId()){
            case R.id.btn_down:
                verticalDown(iv);
                break;
            case R.id.btn_PWX:
                paowuxian();
                break;
        }
    }


    /**
     * 下落
     * @param iv v
     */
    private void verticalDown(final ImageView iv) {
        ValueAnimator animator=ValueAnimator
                .ofFloat(0f,(ScreenUtils.getScreenSize(this)[0]-iv.getHeight()));
        animator.setTarget(iv);
        animator.setDuration(1000)
                .start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                iv.setTranslationY((Float)valueAnimator.getAnimatedValue());
            }
        });
    }

    /**
     * 使用自定义 value animator 做抛物线动画
     */
    private void paowuxian() {
        ValueAnimator animator=new ValueAnimator();
        animator.setDuration(3000);
//        animator.setInterpolator(new LinearInterpolator());
        animator.setObjectValues(new PointF(0,0));
        animator.setEvaluator(new TypeEvaluator<PointF>() {

            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF pointF, PointF t1) {
                PointF point=new PointF();
                point.y=200*fraction*3;
                point.x = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF point=(PointF)valueAnimator.getAnimatedValue();
                iv.setTranslationY(point.y);
                iv.setTranslationX(point.x);
            }
        });
        animator.start();
    }

    /**
     * 使用动画监听
     */
    private void animatorListener(){
        ValueAnimator animator=ValueAnimator
                .ofFloat(0f,(ScreenUtils.getScreenSize(this)[0]-iv.getHeight()));
        animator.setTarget(iv);
        animator.setDuration(1000)
                .start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.e(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.e(TAG, "onAnimationEnd");
                ViewGroup parent = (ViewGroup) iv.getParent();
                if (parent != null)
                    parent.removeView(iv);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.e(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.e(TAG, "onAnimationRepeat");
            }
        });

        ///or  AnimatorListenerAdapter
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.e(TAG, "onAnimationEnd");
                ViewGroup parent = (ViewGroup) iv.getParent();
                if (parent != null)
                    parent.removeView(iv);
            }
        });
    }

}
