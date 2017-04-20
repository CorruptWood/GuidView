# GuidView

用于引导页和轮播图

支持自定义viewpager的adapter，支持自定义圆点显示样式

# 效果图

模拟器比较卡顿，见谅，电脑性能有些差

<img src="https://github.com/CorruptWood/GuidView/blob/master/guidView.gif" width=480 hight=800/>

# 注意

内置的adapter只支持加载本地资源id和网络图片

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

 定义的属性
 
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
	
