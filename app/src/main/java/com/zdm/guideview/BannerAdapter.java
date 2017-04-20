package com.zdm.guideview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdm.guidview.CustomView;
import com.zdm.guidview.GuidView;
import com.zhy.autolayout.utils.AutoUtils;

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

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private Context mContext;
    private List<List<Integer>> list;

    public BannerAdapter(Context mContext,List<List<Integer>> list) {
        this.mContext = mContext;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.banner_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindViewData(position);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.guid_view)
        GuidView guidView;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.inject(this,itemView);
        }

        public void bindViewData(int position) {
            if(position>=0 && position<=5){
                guidView.setDotLayoutShowMode(CustomView.ShowMode.LEFT.intValue());
            }else if(position%2==0){
                guidView.setDotLayoutShowMode(CustomView.ShowMode.RIGHT.intValue());
            }else {
                guidView.setDotLayoutShowMode(CustomView.ShowMode.CENTER.intValue());
            }

            List<Integer> integers = list.get(position);

            guidView.bindIntengerData(integers);

            //不建议在此处开始轮播 不好处理轮播图的生命周期
//            guidView.startBanner(1000);
        }
    }
}
