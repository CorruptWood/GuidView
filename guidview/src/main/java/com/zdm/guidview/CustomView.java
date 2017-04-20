package com.zdm.guidview;

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

public interface CustomView {

    int LEFT = 0;
    int RIGHT = 1;

    enum ShowMode{
        LEFT(0),RIGHT(1),CENTER(2);

        private int value;

        ShowMode(int value) {
            this.value = value;
        }

        public int intValue(){
            return this.value;
        }
    }

    interface OnScrollLisntener {
        void OnScrollLisntener(int position, int move);
    }

    interface OnPagerChangerListener {

        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }


    interface OnPageSelectedListener {
        void onPageSelected(int position);
    }

    interface OnViewPagerItemClickListener{
        void OnClick(int position);
    }
}
