package yzs.animator.yzsanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Des：AnimatorSet 动画集合使用
 * creat by Zishu.Ye on 2017/2/24  14:29
 */
public class AnimatorSetActivity extends AppCompatActivity {

    @BindView(R.id.id_ball)
    ImageView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        ButterKnife.bind(this);
    }

    public static void startAnimatorSet(Context context) {
        context.startActivity(new Intent(context, AnimatorSetActivity.class));
    }

    public void togetherRun(View view) {
        ObjectAnimator objScaleX = ObjectAnimator.ofFloat(ball, "scaleX", 1.0f, 2.0f);
        ObjectAnimator objScaleY = ObjectAnimator.ofFloat(ball, "scaleY", 1.0f, 2.5f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(objScaleX, objScaleY);
        animatorSet.start();
    }

    public void playWithAfter(View view) {
        float cx = ball.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(ball, "scaleX",
                1.0f, 0.2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ball, "scaleY",
                1.0f, 0.2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(ball,
                "x",  cx ,  0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(ball,
                "x", cx);

        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(2000);
        animSet.start();
    }
}
