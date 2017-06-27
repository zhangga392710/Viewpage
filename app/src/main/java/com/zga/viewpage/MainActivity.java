package com.zga.viewpage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

  private ViewPagerAdapter vpAdapter;
  private List<View> views;
  private ViewPager mViewPage;
  private LinearLayout layout;

  //引导图片资源
  private static final int[] pics = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
  //底部小店图片
  private ImageView[] dots;
  //记录当前选中位置
  private int currentIndex;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mViewPage = (ViewPager) findViewById(R.id.viewpager);
    layout = (LinearLayout) findViewById(R.id.ll_point);
    mViewPagess();
  }

  private void mViewPagess() {
    views = new ArrayList<>();
    LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
      LinearLayout.LayoutParams.MATCH_PARENT);
    //初始化引导图片列表
    for (int pic : pics) {
      ImageView iv = new ImageView(this);
      iv.setLayoutParams(mParams);
      iv.setBackgroundResource(pic);
      views.add(iv);
    }
    //初始化Adapter
    vpAdapter = new ViewPagerAdapter(views);
    mViewPage.setAdapter(vpAdapter);
    //初始化底部小点
    initDots();
    //绑定回调
    vpAdapter.setpagerOnClickListener(new ViewPagerAdapter.pagerOnClickListener() {
      @Override
      public void pagerListener(View v) {
        Toast.makeText(MainActivity.this, "Hellow Back", Toast.LENGTH_LONG).show();
      }
    });
    mViewPage.setOnPageChangeListener(this);
  }

  private void initDots() {
    dots = new ImageView[pics.length];

    //循环取得小点图片
    for (int i = 0; i < pics.length; i++) {
      dots[i] = (ImageView) layout.getChildAt(i);
      dots[i].setEnabled(true);//都设为灰色
      dots[i].setOnClickListener(this);
      dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
    }

    currentIndex = 0;
    dots[currentIndex].setEnabled(false);//设置选中状态
  }


  /**
   * 设置当前的引导页
   */
  private void setCurView(int position) {
    if (position < 0 || position >= pics.length) {
      return;
    }
    mViewPage.setCurrentItem(position);
  }

  /**
   * 设置当前引导小点的选中
   */
  private void setCurDot(int positon) {
    if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
      return;
    }

    dots[positon].setEnabled(false);
    dots[currentIndex].setEnabled(true);
    currentIndex = positon;
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageSelected(int position) {
    //设置底部小点选中状态
    setCurDot(position);
  }


  @Override
  public void onPageScrollStateChanged(int state) {

  }

  @Override
  public void onClick(View v) {
    int position = (Integer) v.getTag();
    setCurView(position);
    setCurDot(position);
  }
}
