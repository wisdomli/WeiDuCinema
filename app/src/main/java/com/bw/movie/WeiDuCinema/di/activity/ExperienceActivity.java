package com.bw.movie.WeiDuCinema.di.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.bw.movie.WeiDuCinema.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首次安装进入的页面
 */
public class ExperienceActivity extends AppCompatActivity {
    private ViewPager viewpager_experience;
    private Button button_experience;
    private List<Integer> list = new ArrayList<>();
    private SharedPreferences config;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        config = getSharedPreferences("config", MODE_PRIVATE);
        flag = config.getBoolean("flag", true);
        if (flag) {
            viewpager_experience = findViewById(R.id.viewpager_experience);
            button_experience = findViewById(R.id.button_experience);
            addList();
            viewpager_experience.setAdapter(new Adapter());
            viewpager_experience.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //判断按钮是否显示
                    if (viewpager_experience.getCurrentItem() == 3) {
                        button_experience.setVisibility(View.VISIBLE);
                    } else {
                        button_experience.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            button_experience.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor edit = config.edit();
                    edit.putBoolean("flag", false);
                    edit.commit();
                    startActivity(new Intent(ExperienceActivity.this, MainActivity.class));
                    finish();
                }
            });
        } else {
            startActivity(new Intent(ExperienceActivity.this, MainActivity.class));
            finish();
        }
    }

    //添加数据
    private void addList() {
        list.add(R.drawable.start_01);
        list.add(R.drawable.start_02);
        list.add(R.drawable.start_03);
        list.add(R.drawable.start_04);
    }

    //viewpager适配器
    class Adapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(ExperienceActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(list.get(position));
            container.addView(imageView);
            return imageView;
        }
    }
}