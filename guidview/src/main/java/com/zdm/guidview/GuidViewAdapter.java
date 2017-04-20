package com.zdm.guidview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

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

public class GuidViewAdapter extends PagerAdapter {

    private Context context;
    private List<? extends Object> list;
    private boolean flag;
    boolean isBanner;

    public GuidViewAdapter(Context context, boolean isBanner) {
        this.context = context;
        this.isBanner = isBanner;
    }

    public void setList(List<? extends Object> list, boolean flag) {
        this.list = list;
        this.flag = flag;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (isBanner) {
            return Integer.MAX_VALUE;
        } else
            return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        int newPosition = position % list.size();
        ImageView imageView = new ImageView(context);
        if (flag) {
            Picasso.with(context).load((Integer) list.get(newPosition)).centerCrop().fit().into(imageView);
        } else {
            Picasso.with(context).load((String) list.get(newPosition)).centerCrop().fit().into(imageView);
        }
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
