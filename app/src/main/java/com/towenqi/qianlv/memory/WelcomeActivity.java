package com.towenqi.qianlv.memory;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.towenqi.qianlv.memory.UI.RealHorizontalScrollView;
import com.towenqi.qianlv.memory.UI.RealViewPager;

public class WelcomeActivity extends AppCompatActivity {
    RealViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        RealHorizontalScrollView realHorizontalScrollView = (RealHorizontalScrollView) findViewById(R.id.scrollable_background);
        viewPager = (RealViewPager) findViewById(R.id.welcome_pager);
        viewPager.configureWithMyListener(realHorizontalScrollView);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewPager.manageScrollWithMyListeners(positionOffsetPixels, position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
    }
}
