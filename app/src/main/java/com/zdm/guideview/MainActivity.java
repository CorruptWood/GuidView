package com.zdm.guideview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AutoLayoutActivity {


    @InjectView(R.id.text_view1)
    TextView textView1;
    @InjectView(R.id.text_view2)
    TextView textView2;
    @InjectView(R.id.text_view3)
    TextView textView3;
    @InjectView(R.id.text_view4)
    TextView textView4;
    @InjectView(R.id.text_view5)
    TextView textView5;
    @InjectView(R.id.text_view6)
    TextView textView6;
    @InjectView(R.id.text_view7)
    TextView textView7;

    @InjectView(R.id.text_view8)
    TextView textView8;
    @InjectView(R.id.text_view9)
    TextView textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.text_view1, R.id.text_view2, R.id.text_view3, R.id.text_view4, R.id.text_view5
            , R.id.text_view6, R.id.text_view7})
    public void onViewClicked(View view) {
        Intent intent = new Intent(MainActivity.this, GuidActivity.class);
        switch (view.getId()) {
            case R.id.text_view1:
                intent.putExtra(Constants.TYPE, 1);
                break;
            case R.id.text_view2:
                intent.putExtra(Constants.TYPE, 2);
                break;
            case R.id.text_view3:
                intent.putExtra(Constants.TYPE, 3);
                break;
            case R.id.text_view4:
                intent.putExtra(Constants.TYPE, 4);
                break;
            case R.id.text_view5:
                intent.putExtra(Constants.TYPE, 5);
                break;
            case R.id.text_view6:
                intent.putExtra(Constants.TYPE, 6);
                break;
            case R.id.text_view7:
                intent.putExtra(Constants.TYPE, 7);
                break;
        }
        startActivity(intent);
    }

    @OnClick({R.id.text_view8, R.id.text_view9})
    public void onView(View view) {
        switch (view.getId()) {
            case R.id.text_view8:
                Intent intent = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view9:
                break;
        }
    }
}
