package yzs.animator.yzsanimator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Des： obj animator 使用
 * 1）translationX 和 translationY：这两个属性控制了View所处的位置，它们的值是由layout容器设置的，是相对于坐标原点（0，0左上角）的一个偏移量。
 * 2）rotation, rotationX 和 rotationY：控制View绕着轴点（pivotX和pivotY）旋转。
 * 3）scaleX 和 scaleY：控制View基于pivotX和pivotY的缩放。
 * 4）pivotX 和 pivotY：旋转的轴点和缩放的基准点，默认是View的中心点。
 * 5）x 和 y：描述了view在其父容器中的最终位置，是左上角左标和偏移量（translationX，translationY）的和。
 * 6）aplha：透明度，1是完全不透明，0是完全透明。
 * creat by Zishu.Ye on 2017/2/24  10:34
 */
public class ObjectAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obj);
        ButterKnife.bind(this);
    }

    public static void startObjActivity(Context context){
        context.startActivity(new Intent(context,ObjectAnimatorActivity.class));
    }

    @OnClick({R.id.iv,R.id.btn_insert_listener,R.id.btn_value_holder})
    void onClick(View v){
        switch (v.getId()){
            case R.id.iv:
                rotateYAnimator(v);
                break;
            case R.id.btn_insert_listener:
                rotateInsListenerAni(iv);
                break;
            case R.id.btn_value_holder:
                rotateValueHolderAni(iv);
                break;
            default:
                break;
        }
    }

    private void rotateYAnimator(View view){
        ObjectAnimator.ofFloat(view,"rotationX",0.0f,360.0f)
        .setDuration(3000)
        .start();
    }

    /**
     * 使用更新监听做 动画
     * @param view  v
     */
    private void rotateInsListenerAni(final View view){
        ObjectAnimator obj=ObjectAnimator.ofFloat(view,"yzs",1.0f,0.0f)
                .setDuration(1000);
        obj.start();
        obj.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float cVal=(Float)valueAnimator.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
            }
        });
    }

    /**
     * 使用 property value holder
     * @param v v
     */
    private void rotateValueHolderAni(View v) {
        PropertyValuesHolder pvh=PropertyValuesHolder.ofFloat("alpha",1f,0.0f,1f);
        PropertyValuesHolder pvhX=PropertyValuesHolder.ofFloat("scaleX",0.5f,0,1f,0.2f);
        PropertyValuesHolder pvhY=PropertyValuesHolder.ofFloat("scaleY",0.2f,0.7f,1f,0.2f);
        ObjectAnimator.ofPropertyValuesHolder(v, pvh, pvhX,pvhY).setDuration(1000).start();
    }
}
