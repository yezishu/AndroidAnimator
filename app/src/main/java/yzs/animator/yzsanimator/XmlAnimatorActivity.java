package yzs.animator.yzsanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Des：使用XML 定义动画
 * creat by Zishu.Ye on 2017/2/24  15:41
 */
public class XmlAnimatorActivity extends AppCompatActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_animator);
        ButterKnife.bind(this);
    }

    public static void startXmlAnimator(Context context){
        context.startActivity(new Intent(context,XmlAnimatorActivity.class));
    }

    public void scaleX(View view){
        Animator animator= AnimatorInflater.loadAnimator(this,R.animator.scale_obj_animator);
        iv.setPivotX(0);
        iv.setPivotY(0);
        iv.invalidate();
        animator.setTarget(iv);
        animator.start();
    }

    public void animatorSet(View view){
        Animator animator=AnimatorInflater.loadAnimator(this,R.animator.set_obj_animator);
        animator.setTarget(iv);
        animator.start();
    }
}
