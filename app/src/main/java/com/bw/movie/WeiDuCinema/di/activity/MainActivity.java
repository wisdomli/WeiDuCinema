package com.bw.movie.WeiDuCinema.di.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bw.movie.WeiDuCinema.R;
import com.bw.movie.WeiDuCinema.di.fragment.CinemaFragment;
import com.bw.movie.WeiDuCinema.di.fragment.FilmFragment;
import com.bw.movie.WeiDuCinema.di.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页主布局切换
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout main_frame;
    private ImageView main_img_01, main_img_02, main_img_03;
    private FilmFragment filmFragment;
    private MyFragment myFragment;
    private CinemaFragment cinemaFragment;
    private List<ImageView> list = new ArrayList<>();
    private FragmentManager manager;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_frame = findViewById(R.id.main_frame);
        main_img_01 = findViewById(R.id.main_img_01);
        main_img_02 = findViewById(R.id.main_img_02);
        main_img_03 = findViewById(R.id.main_img_03);
        filmFragment = new FilmFragment();
        myFragment = new MyFragment();
        cinemaFragment = new CinemaFragment();
        addList();
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        for (int i = 0; i < list.size(); i++) {
            setSizeMin(list.get(i));
        }
        setSizeMax(main_img_01);
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.main_frame, myFragment).commit();
        manager.beginTransaction().add(R.id.main_frame, cinemaFragment).commit();
        manager.beginTransaction().add(R.id.main_frame, filmFragment).commit();
        main_img_01.setOnClickListener(this);
        main_img_02.setOnClickListener(this);
        main_img_03.setOnClickListener(this);
    }

    //设置图片变大时的宽高
    public void setSizeMax(ImageView imageView) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = 70 * (width / 2) / 160;
        params.width = 70 * (width / 2) / 160;
        imageView.setLayoutParams(params);
    }

    //添加数据
    public void addList() {
        list.add(main_img_01);
        list.add(main_img_02);
        list.add(main_img_03);
    }

    //设置图片变小时的宽高
    public void setSizeMin(ImageView imageView) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        //1dp*像素密度/160 = 实际像素数   dp转换px公式
        params.height = 55 * (width / 2) / 160;
        params.width = 55 * (width / 2) / 160;
        imageView.setLayoutParams(params);
    }

    //点击图片切换页面
    @Override
    public void onClick(View view) {
        for (int i = 0; i < list.size(); i++) {
            setSizeMin(list.get(i));
        }
        switch (view.getId()) {
            case R.id.main_img_01:
                setSizeMax(main_img_01);
                main_img_01.setImageResource(R.drawable.com_icon_film_selected_hdpi);
                main_img_02.setImageResource(R.drawable.com_icon_cinema_default_hdpi);
                main_img_03.setImageResource(R.drawable.com_icon_my_default_hdip);
                manager.beginTransaction().show(filmFragment).commit();
                manager.beginTransaction().hide(myFragment).commit();
                manager.beginTransaction().hide(cinemaFragment).commit();
                break;
            case R.id.main_img_02:
                setSizeMax(main_img_02);
                main_img_01.setImageResource(R.drawable.com_icon_film_fault_hdpi);
                main_img_02.setImageResource(R.drawable.com_icon_cinema_selected_hdpi);
                main_img_03.setImageResource(R.drawable.com_icon_my_default_hdip);
                manager.beginTransaction().show(cinemaFragment).commit();
                manager.beginTransaction().hide(myFragment).commit();
                manager.beginTransaction().hide(filmFragment).commit();
                break;
            case R.id.main_img_03:
                setSizeMax(main_img_03);
                main_img_01.setImageResource(R.drawable.com_icon_film_fault_hdpi);
                main_img_02.setImageResource(R.drawable.com_icon_cinema_default_hdpi);
                main_img_03.setImageResource(R.drawable.com_icon_my_selected_hdpi);
                manager.beginTransaction().show(myFragment).commit();
                manager.beginTransaction().hide(cinemaFragment).commit();
                manager.beginTransaction().hide(filmFragment).commit();
                break;
        }
    }
}
