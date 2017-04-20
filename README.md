# GuidView

用于引导页和轮播图

支持自定义viewpager的adapter，支持自定义圆点显示样式

# 效果图

模拟器比较卡顿，见谅，电脑性能有些差

<img src="https://github.com/CorruptWood/GuidView/blob/master/guidView.gif"/>

# 注意

内置的adapter只加载本地资源id和网络图片,若界面样式多变，请自定义adapter,将adapter传入，也可一样使用库内封装好的接口回调

本库使用 鸿洋大神的AutoLayout，基于768x1280 做适配，一定使用px指定圆点的宽高、距下方的距离

鸿洋大神的适配库：https://github.com/hongyangAndroid/AndroidAutoLayout



# 引入

1.将其添加到存储库末尾的根build.gradle中：

	allprojects {
		repositories {
        	maven { url 'https://jitpack.io' }
		}
	  }
  
2.添加依赖关系

    dependencies {
	        compile 'com.github.CorruptWood:GuidView:1.0.0'
	  }

#  使用

1.本库有4个接口回调:

   滑动监听  move 对应 Custom.LEFT Custom.REGIHT
   仅仅只分辨出左滑还是右滑，在引导界面，可以设置滑动最后一页时，滑动跳转到主界面，不需要单独添加跳转按钮
   
   
       interface OnScrollLisntener {
    
         void OnScrollLisntener(int position, int move);
	
       }
    
   同ViewPager的OnPagerChangerListener，便于添加其他需要监听ViewPager变化的控件
    
    
        interface OnPagerChangerListener {

          void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

          void onPageSelected(int position);

          void onPageScrollStateChanged(int state);
        }

   简化版的OnPagerChangerListener，便于添加其他需要监听ViewPager变化的控件
    
    
        interface OnPageSelectedListener {
    
          void onPageSelected(int position);
        }

   设置viewPager页面点击事件 比如轮播图点击图片，跳转页面
    
    
        interface OnViewPagerItemClickListener{
    
          void OnClick(int position);
        }
	
 2.属性介绍
 
 	<declare-styleable name="GuidView">
        <!--是否显示小圆点-->
        <attr name="showDot" format="boolean"/>
        <!--圆点的大小-->
        <attr name="dotSize" format="dimension"/>
        <!--小圆点布局距离下方的距离  px-->
        <attr name="dotMaginBottom" format="dimension"/>
        <!--小圆点布局显示位置  默认居中-->
        <attr name="dotLayoutShowMode" format="enum">
            <enum name="left" value="0"/>
            <enum name="right" value="1"/>
            <enum name="center" value="2"/>
        </attr>
        <!--小圆点布局长度   默认wrap_content-->
        <attr name="dotLayoutWidth" format="enum">
            <enum name="match_parent" value="0"/>

            <enum name="wrap_content" value="1"/>
        </attr>
        <!--小圆点布局高度   默认wrap_content-->
        <attr name="dotLayoutHight" format="dimension"/>
        <!--小圆点之间的距离 距离左侧距离 -->
        <attr name="dotMaginLeft" format="dimension"/>
        <!--小圆点选中和未选中的资源Id-->
        <attr name="dotNormal" format="reference"/>
        <attr name="dotSelect" format="reference"/>
        <!--是否用于banner图  默认false-->
        <attr name="isBanner" format="boolean"/>
	
	
3.方法介绍

 ①：获取当前viewpager的索引
 
     public int getCurrentItem() {
        return viewPager.getCurrentItem();
     }
     
 ②： 绑定数据，以及自定义的adapter，数据仅支持资源id和图片路径
 	
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
    
    
 ③：使用banner，如果需要做banner,必须设置属性 isBanner=true
 
 	<com.zdm.guidview.GuidView
        android:id="@+id/guid_view"
        android:layout_width="match_parent"
        android:layout_height="360px"   
       	app:dotMaginBottom="30px"       
        app:dotSize="20px"             
        app:showDot="true"              
        app:dotLayoutHight="30px"       
        app:dotLayoutShowMode="center"  
        app:dotMaginLeft="10px"         
        app:isBanner="true"             
        app:dotLayoutWidth="match_parent"/>
	
   在你觉得合适的地方设置 banner切换的时间和开始轮播
   
   
       public void startBanner() {
          this.startBanner(3000);
   	 }

       public void startBanner(long delayMillis) {
       
        }

   在你觉得合适的地方停止轮播，管理其轮播周期，避免oom
   
       public void stopBanner() {
      
    	}
	
	
 ④：其他方法
 	
    获取小圆点布局的对象,改变背景等


         public void setDotLayoutBackgroundColor(@ColorInt int color) {
   
        }

    
        public LinearLayout getDotLayout() {
          
        }
	
    更多使用请查看示例。
    
    
	
