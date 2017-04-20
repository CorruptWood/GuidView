package com.zdm.guideview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zdm.guidview.CustomView;
import com.zdm.guidview.GuidView;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * <p>
 * ..................佛祖开光 ,永无BUG................
 * <p>
 * <p>
 * <p>
 * Created by zdm on 2017/4/19/0019.
 * <p>
 * 描述:
 */

public class GuidActivity extends AutoLayoutActivity {

    @InjectView(R.id.guid_view)
    GuidView guidView;

    TextView skip;
    private int extra;
//    private List<Object> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extra = getIntent().getIntExtra(Constants.TYPE, 0);
        if (extra == 1) {
            setContentView(R.layout.activity_guid1);
        } else if (extra == 2) {
            setContentView(R.layout.activity_guid2);
        } else if (extra == 3) {
            setContentView(R.layout.activity_guid3);
        } else if (extra == 4) {
            setContentView(R.layout.activity_guid4);
        } else if (extra == 5) {
            setContentView(R.layout.activity_guid5);
        } else if (extra == 6) {
            setContentView(R.layout.activity_guid5);
        } else if (extra == 7) {
            setContentView(R.layout.activity_guid5);
        }

        ButterKnife.inject(this);

        init();
    }

    private void init() {
        if (extra == 1) {
            initNetWorkData();
        } else {
            initLocalData();
        }
    }

    private void initLocalData() {
        final List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.test1);
        list.add(R.mipmap.test2);
        list.add(R.mipmap.test3);
        list.add(R.mipmap.test4);
        list.add(R.mipmap.test5);

        guidView.bindIntengerData(list);

        if (extra == 3 || extra == 4 || extra == 5) {
            guidView.setDotLayoutBackgroundColor(Color.parseColor("#40000000"));
        }

        if(extra==6 || extra==7){
            skip= (TextView) findViewById(R.id.skip);
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(GuidActivity.this,MainActivity.class));
                    finish();
                }
            });
        }

        //添加滑动跳转效果
        if(extra==6){
            guidView.setOnScrollLisntener(new CustomView.OnScrollLisntener() {
                @Override
                public void OnScrollLisntener(int position, int move) {
                    //最后一张且往左滑
                    if(position==list.size()-1 && move==CustomView.LEFT){
                        startActivity(new Intent(GuidActivity.this,MainActivity.class));
                        finish();
                    }else if(position==0 && move==CustomView.RIGHT){//第一张 右滑
                        CustomToast.showToast(GuidActivity.this,"已经是第一张了");
                    }
                }
            });
        }

        if(extra==7){
            guidView.setOnPagerChangerListener(new CustomView.OnPagerChangerListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position==list.size()-1){
                        skip.setVisibility(View.VISIBLE);
                    }else {
                        skip.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            guidView.getDotLayout().setBackgroundColor(Color.parseColor("#ff6633"));

            guidView.setOnViewItemClickListener(new CustomView.OnViewPagerItemClickListener() {
                @Override
                public void OnClick(int position) {
                    CustomToast.showToast(GuidActivity.this,"当前点击的索引："+position);
                }
            });
        }

    }

    /**
     * 加载网络图片，使用Picasso加载
     */
    private void initNetWorkData() {
        List<String> list=new ArrayList<>();
        list.add("http://img04.tooopen.com/images/20130701/tooopen_20083555.jpg");
        list.add("http://img06.tooopen.com/images/20170321/tooopen_sy_202673188311.jpg");
        list.add("http://img02.tooopen.com/images/20141231/sy_78327074576.jpg");
        list.add("http://img04.tooopen.com/images/20130701/tooopen_20083555.jpg");
        list.add("http://img06.tooopen.com/images/20170321/tooopen_sy_202706818574.jpg");

        guidView.bindStringData(list);
    }
}
