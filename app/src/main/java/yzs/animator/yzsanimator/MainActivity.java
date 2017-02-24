package yzs.animator.yzsanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_obj_animator,R.id.btn_value_animator,R.id.btn_animator_set,
            R.id.btn_xml_animator,R.id.btn_layout_animator,R.id.btn_view_animator})
    void onClick(View v){
        switch (v.getId()){
            case R.id.btn_obj_animator:
                ObjectAnimatorActivity.startObjActivity(this);
                break;
            case R.id.btn_value_animator:
                ValuesAnimatorActivity.startValuesAnimator(this);
                break;
            case R.id.btn_animator_set:
                AnimatorSetActivity.startAnimatorSet(this);
                break;
            case R.id.btn_xml_animator:
                XmlAnimatorActivity.startXmlAnimator(this);
                break;
            case R.id.btn_layout_animator:
                LayoutAnimatorActivity.startLayoutAnimator(this);
                break;
            case R.id.btn_view_animator:
                ViewAnimatorActivity.startViewAnimator(this);
                break;
            default:
                break;
        }
    }

}
