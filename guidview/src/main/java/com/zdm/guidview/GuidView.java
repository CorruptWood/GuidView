package com.zdm.guidview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

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

public class GuidView extends AutoFrameLayout implements GestureDetector.OnGestureListener {

    private boolean isShowDot;
    //    private boolean isShowCenter;
    private int dotNormal;
    private int dotSelect;
    private int dotMaginBottom;
    private int dotMaginLeft;
    private ViewPager viewPager;
    private LinearLayout dotContianr;
    private PagerAdapter mPagerAdapter;
    private boolean flag = true;
    private int dotSize;
    private List<? extends Object> list;
    private CustomView.OnPagerChangerListener onPagerChangerListener;
    private CustomView.OnPageSelectedListener onPageSelectedListener;
    private CustomView.OnViewPagerItemClickListener itemClickListener;
    private GestureDetector detector = new GestureDetector(this);
    ;
    private CustomView.OnScrollLisntener onScrollLisntener;
    private int dotLayoutShowMode;
    private int dotLayoutWidth;
    private int dotLayoutHight;
    private boolean isBanner;
    private Handler handler = new Handler();
    private boolean toRight;

    /**
     * @param dotLayoutShowMode LEFT(0),RIGHT(1),CENTER(2);
     *                          ShowMode.LEFT
     *                          ShowMode.RIGHT
     *                          ShowMode.CENTER
     */
    public void setDotLayoutShowMode(int dotLayoutShowMode) {
        this.dotLayoutShowMode = dotLayoutShowMode;
    }

    public void setOnViewItemClickListener(CustomView.OnViewPagerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnPagerChangerListener(CustomView.OnPagerChangerListener onPagerChangerListener) {
        this.onPagerChangerListener = onPagerChangerListener;
    }

    public void setOnScrollLisntener(CustomView.OnScrollLisntener onScrollLisntener) {
        this.onScrollLisntener = onScrollLisntener;
    }

    public void setOnPageSelectedListener(CustomView.OnPageSelectedListener onPageSelectedListener) {
        this.onPageSelectedListener = onPageSelectedListener;
    }

    public GuidView(Context context) {
        this(context, null);
    }

    public GuidView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GuidView);

        isShowDot = typedArray.getBoolean(R.styleable.GuidView_showDot, false);

        dotSize = (int) typedArray.getDimension(R.styleable.GuidView_dotSize, 15f);

        //        isShowCenter = typedArray.getBoolean(R.styleable.GuidView_showCenter, false);

        dotLayoutShowMode = typedArray.getInt(R.styleable.GuidView_dotLayoutShowMode, 2);

        dotLayoutWidth = typedArray.getInt(R.styleable.GuidView_dotLayoutWidth, 1);

        dotLayoutHight = (int) typedArray.getDimension(R.styleable.GuidView_dotLayoutHight, 0);

        dotMaginBottom = (int) typedArray.getDimension(R.styleable.GuidView_dotMaginBottom, 80f);

        dotMaginLeft = (int) typedArray.getDimension(R.styleable.GuidView_dotMaginLeft, 0f);

        dotNormal = typedArray.getResourceId(R.styleable.GuidView_dotNormal, R.drawable.dot_normal_shape);

        dotSelect = typedArray.getResourceId(R.styleable.GuidView_dotSelect, R.drawable.dot_select_shape);

        isBanner = typedArray.getBoolean(R.styleable.GuidView_isBanner, false);

        typedArray.recycle();

        //添加viewpager
        addViewPager();

        //是否添加小圆点布局
        if (isShowDot) {
            addViewDot();
        }

        AutoUtils.auto(this);
    }

    /**
     * 长度为MATCH_PARENT时gravity=BOTTOM
     * 长度为WRAP_CONTENT时gravity=BOTTOM|(设置的位置)
     */
    private void addViewDot() {
        dotContianr = new LinearLayout(getContext());
        FrameLayout.LayoutParams params;
        //是否居中显示控制圆点布局长度
        params = new LayoutParams((dotLayoutWidth == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT),
                (dotLayoutHight == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : dotLayoutHight));
        params.gravity = (dotLayoutWidth == 0 ? Gravity.BOTTOM : (Gravity.BOTTOM | (dotLayoutShowMode == 0 ? Gravity.LEFT :
                (dotLayoutShowMode == 1 ? Gravity.RIGHT : Gravity.CENTER))));
        params.setMargins(0, 0, 0, dotMaginBottom);
        dotContianr.setLayoutParams(params);
        AutoUtils.auto(dotContianr);
        addView(dotContianr);
    }

    private void addViewPager() {
        viewPager = new ViewPager(getContext());
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(params);
        addView(viewPager);
    }


    private void initViewData() {
        //是否添加小圆点
        if (isShowDot) {
            initDot();
        }
        if (mPagerAdapter == null) {
            mPagerAdapter = new GuidViewAdapter(getContext(), isBanner);
            ((GuidViewAdapter) mPagerAdapter).setList(list, flag);
        }
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(mPagerChangeListener);

        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 只是 要求 父控件不拦截，部分父控件会重写此方法（requestDisallowInterceptTouchEvent），会依然拦截
                        // 此方法只在一组触摸事件中生效，下一组触摸事件会被重置
                        //                    v.getParent().requestDisallowInterceptTouchEvent(true);
                        //                    isAutoRollWhenDown = autoRoll;
                        //                    if (isAutoRollWhenDown) {
                        //                        setAutoRoll(false);
                        //                    }

                        isTouching = true;
                        break;
                    // 当子控件原来在消费触摸事件时，后续的触摸事件被父控件拦截了。子控件就得不到后续的触摸事件了
                    // 但是父控件会制造一个 ACTION_CANCEL 的触摸事件，分发给孩子，
                    // 那这样孩子就知道没有后续的触摸事件了

                    //  ACTION_CANCEL 约等于  ACTION_UP 代表了一组触摸事件的结束
                    case MotionEvent.ACTION_CANCEL:
                        //                    Log.e("onTouch","ACTION_CANCEL");
                    case MotionEvent.ACTION_UP:
                        //                    if (isAutoRollWhenDown) {
                        //                        setAutoRoll(true);
                        //                    }
                        isTouching = false;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return detector.onTouchEvent(ev);
    }

    //初始化小圆点

    /**
     * 长度为MATCH_PARENT时gravity=用户设置的位置
     * 长度为WRAP_CONTENT时 不处理
     */
    private void initDot() {
        dotContianr.removeAllViews();
        dotContianr.setGravity(dotLayoutShowMode == 0 ? Gravity.LEFT :
                (dotLayoutShowMode == 1 ? Gravity.RIGHT : Gravity.CENTER_HORIZONTAL));

        //        int percentWidthSize = AutoUtils.getPercentWidthSize(dotSize);
        //        int percentHeightSize = AutoUtils.getPercentHeightSize(dotSize);
        //        MyLogger.jLog().e(percentWidthSize+"=======:==="+AutoUtils.getPercentHeightSize(30));
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(getContext());

            AutoLinearLayout.LayoutParams layoutParams = new AutoLinearLayout.LayoutParams(dotSize, dotSize);
            //                    percentWidthSize,percentHeightSize);
            layoutParams.setMargins(dotMaginLeft / 2, 0, dotMaginLeft / 2, 0);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            imageView.setLayoutParams(layoutParams);

            AutoUtils.auto(imageView);
            imageView.setImageResource(dotNormal);
            //            Picasso.with(getContext()).load(dotNormal).into(imageView);
            dotContianr.addView(imageView);
        }
        ((ImageView) dotContianr.getChildAt(0)).setImageResource(dotSelect);
    }


    //按下
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    //短按
    @Override
    public void onShowPress(MotionEvent e) {
    }

    //单击
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (itemClickListener != null) {
            itemClickListener.OnClick(getCurrentItem());
            this.performClick();
        }
        return false;
    }

    //滑动
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    //长按
    @Override
    public void onLongPress(MotionEvent e) {
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int currentItem = viewPager.getCurrentItem();
        if (onScrollLisntener != null) {
            if (e1.getX() - e2.getX() > 80) {//左滑  日期加1
                onScrollLisntener.OnScrollLisntener(currentItem, CustomView.LEFT);
            } else if (e1.getX() - e2.getX() < -80) {
                onScrollLisntener.OnScrollLisntener(currentItem, CustomView.RIGHT);
            }
        }
        return false;
    }

    //页面监听
    ViewPager.OnPageChangeListener mPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (onPagerChangerListener != null) {
                onPagerChangerListener.onPageScrolled(position % list.size(), positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            //            int newPosition = position % dotContianr.getChildCount();
            if (isShowDot) {
                for (int i = 0; i < dotContianr.getChildCount(); i++) {
                    if (i == position % list.size()) {
                        ((ImageView) dotContianr.getChildAt(i)).setImageResource(dotSelect);
                    } else {
                        ((ImageView) dotContianr.getChildAt(i)).setImageResource(dotNormal);
                    }
                }


            }

            //回调
            if (onPagerChangerListener != null) {
                onPagerChangerListener.onPageSelected(position % list.size());
            }

            if (onPageSelectedListener != null) {
                onPageSelectedListener.onPageSelected(position % list.size());
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (onPagerChangerListener != null) {
                onPagerChangerListener.onPageScrollStateChanged(state);
            }
        }
    };

    /**
     * 设置圆点显示背景
     *
     * @param color
     */
    public void setDotLayoutBackgroundColor(@ColorInt int color) {
        if (dotContianr != null) {
            dotContianr.setBackgroundColor(color);
        }
    }

    /**
     * 获取圆点布局对象
     *
     * @return
     */
    public LinearLayout getDotLayout() {
        return dotContianr;
    }


    public void bindIntengerData(@NonNull List<Integer> list) {
        this.bindData(list, flag, null);
    }


    public void bindIntengerData(@NonNull List<Integer> list, PagerAdapter adapter) {
        this.bindData(list, flag, adapter);
    }

    public void bindStringData(@NonNull List<String> list) {
        this.bindData(list, !flag, null);
    }

    public void bindStringData(@NonNull List<String> list, PagerAdapter adapter) {
        this.bindData(list, !flag, adapter);
    }

    /**
     * 绑定本地资源数据
     * <p>
     * 内置有adapter ,可以自定义
     *
     * @param list
     * @param flag    list的类型  true为Intenger false为String
     * @param adapter
     */
    private void bindData(@NonNull List<? extends Object> list, @NonNull boolean flag, PagerAdapter adapter) {
        this.list = list;
        this.flag = flag;
        this.mPagerAdapter = adapter;
        if (list.isEmpty()) {
            throw (new NullPointerException("请设置数据源！！！！"));
        }
        initViewData();
    }

    public void startBanner() {
        //        this.isStart=true;
        this.startBanner(3000);
    }

    long delayMillis;

    public void startBanner(long delayMillis) {
        //        this.isStart = true;
        this.delayMillis = delayMillis;
        handler.postDelayed(bannerRunnable, delayMillis);
    }

    public void stopBanner() {
        //        this.isStart=false;
        handler.removeCallbacks(bannerRunnable);
    }

    private boolean isTouching;
//    boolean isShowNext=true;
    Runnable bannerRunnable = new Runnable() {
        @Override
        public void run() {

            // 只保证有一个任务在执行，防止重复执行，
            //            removeCallbacks 只能取消掉未执行的任务， 可以取消掉所有 == 参数 的任务
            handler.removeCallbacks(this);

            if (!isTouching) {

//                if(isShowNext){
                    showNextPage();
//                }else {
//                    showBackPage();
//                }

            }
            handler.postDelayed(this, delayMillis);
        }
    };

    private void showNextPage() {
        // 只有一张页面的时候，setCurrentItem 设置了错误的值，但Viewpager已经处理了，我们要知道
        if (mPagerAdapter.getCount() == 1) {
            return;
        }

        viewPager.setCurrentItem(getCurrentItem() + 1);
    }

//    private void showBackPage() {
//        // 只有一张页面的时候，setCurrentItem 设置了错误的值，但Viewpager已经处理了，我们要知道
//        if (mPagerAdapter.getCount() == 1) {
//            return;
//        }
//
//        int currentIndex = viewPager.getCurrentItem();
//        if (currentIndex == 0) {
//            toRight = true;
//        } else if (currentIndex == mPagerAdapter.getCount() - 1) {
//            toRight = false;
//        }
//
//        int targetIndex;
//        if (toRight) {
//            targetIndex = currentIndex + 1;
//        } else {
//            targetIndex = currentIndex - 1;
//        }
//
//        viewPager.setCurrentItem(targetIndex);
//        //        Log.e("ARL", "showNextPage " + items.get(targetIndex).getTitle());
//
//    }

    public int getCurrentItem() {
        return viewPager.getCurrentItem();
    }

//    //轮播图循环模式 默认true无限右滑 false 到末尾后返回循环
//    public void setBannerMode(boolean b){
//        this.isShowNext=b;
//    }
}
