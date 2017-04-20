package com.zdm.guideview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
 * Created by zdm on 2017/4/20/0020.
 * <p>
 * 描述:
 */
public class BannerActivity extends AutoLayoutActivity {

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private BannerAdapter bannerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.inject(this);

        List<List<Integer>> lists = new ArrayList<>();
        for (int x = 0; x < 30; x++) {
            List<Integer> list = new ArrayList<>();
            list.add(R.mipmap.test1);
            list.add(R.mipmap.test2);
            list.add(R.mipmap.test3);
            list.add(R.mipmap.test4);
            list.add(R.mipmap.test5);
            lists.add(list);
        }

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        bannerAdapter = new BannerAdapter(this, lists);
        recyclerView.setAdapter(bannerAdapter);


        //        guidView.bindIntengerData(list);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                for (int x = 0; x < bannerAdapter.getItemCount(); x++) {
                    View childAt = layoutManager.getChildAt(x);
                    if(childAt!=null){
                    BannerAdapter.ViewHolder holder = (BannerAdapter.ViewHolder) recyclerView.getChildViewHolder(childAt);
                    if(x>=firstCompletelyVisibleItemPosition&&x<=lastCompletelyVisibleItemPosition){
                        holder.guidView.startBanner(1000);
                    }else {
                        holder.guidView.stopBanner();
                    }}
                }
            }
        });
    }
}
