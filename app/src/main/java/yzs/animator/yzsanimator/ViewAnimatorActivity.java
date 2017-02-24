package yzs.animator.yzsanimator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Des：
 * creat by Zishu.Ye on 2017/2/24  16:20
 */
public class ViewAnimatorActivity extends AppCompatActivity{
    public static final String TAG="ViewAnimatorActivity";

    @BindView(R.id.id_ball)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animator);
        ButterKnife.bind(this);
    }

    public static void startViewAnimator(Context context){
        context.startActivity(new Intent(context,ViewAnimatorActivity.class));
    }

    public void viewAnim(View view){
        iv.animate()
                .setDuration(2000)
                .alpha(0)
                .y(ScreenUtils.getScreenSize(this)[0]/2)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"start");
                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "END");
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                iv.setY(0);
                                iv.setAlpha(1.0f);
                            }
                        });
                    }
                })
                .start();
    }
}
