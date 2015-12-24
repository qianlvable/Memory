package com.towenqi.qianlv.memory;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isFabOpen;
    private FloatingActionButton fab;
    private FloatingActionButton fabCall;
    private FloatingActionButton fab2;
    private Animation fab_open,fab_close;
    private View fabCallTag,fab2Tag;
    private View mRevealView;

    private boolean enableWelcomePage;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setBackgroundDrawable(null);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Memory");
        ImageView imageView = (ImageView) findViewById(R.id.toolbar_cover);
        imageView.setImageResource(R.drawable.cover_front);


        RecyclerView gridView  = (RecyclerView) findViewById(R.id.memory_grid);
        gridView.setHasFixedSize(true);
        gridView.addItemDecoration(new GridAdapter.SpacesItemDecoration(20));
        mLayoutManager = new GridLayoutManager(this,2);
        gridView.setLayoutManager(mLayoutManager);
        ArrayList<PageData> data = new ArrayList<>();
        loadCardData(data);
        gridView.setAdapter(new GridAdapter(this,data));


        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });
        fabCall = (FloatingActionButton) findViewById(R.id.fab_call);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fabCallTag =  findViewById(R.id.fab1_tag);
        fab2Tag =  findViewById(R.id.fab2_tag);
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:18519664115"));
                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                sharedPreferences.edit().putBoolean("welcome_setting",true).apply();
                // TODO: 2015/12/23 need to modify to snackbar
                Toast.makeText(MainActivity.this,"引导页开启，下次再打开应用生效"
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        mRevealView = findViewById(R.id.reveal_view);
        mRevealView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });


        sharedPreferences = getPreferences(MODE_APPEND);
        enableWelcomePage = sharedPreferences.getBoolean("welcome_setting",true);
        if (enableWelcomePage){
            sharedPreferences.edit().putBoolean("welcome_setting",false).apply();
            Intent intent = new Intent(this,WelcomeActivity.class);
            startActivity(intent);
        }
    }

    public void animateFAB(){
        if(isFabOpen){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            int cx = fab.getLeft() + fab.getWidth()/2;
            int cy = fab.getTop() + fab.getWidth()/2;
            int initialRadius = mRevealView.getWidth();
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, initialRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mRevealView.setVisibility(View.INVISIBLE);
                }
            });
            anim.start();

            fabCall.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab2Tag.startAnimation(fab_close);
            fabCallTag.startAnimation(fab_close);
            fabCall.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x80000000);

            int cx = fab.getLeft() + fab.getWidth()/2;
            int cy = fab.getTop() + fab.getWidth()/2;
            int finalRadius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, finalRadius);
            mRevealView.setVisibility(View.VISIBLE);
            anim.start();

            fabCall.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab2Tag.startAnimation(fab_open);
            fabCallTag.startAnimation(fab_open);
            fabCall.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;

        }
    }

    private void loadCardData(ArrayList<PageData> data) {
        data.add(new PageData(R.drawable.first_meet,"抓到一群自拍的！","差点就和那个唱“时间吹不过白马”的女孩错过"));
        data.add(new PageData(R.drawable.mi_band,"米boy & 米girl","掌阅首席miboy和migirl"));
        data.add(new PageData(R.drawable.healthy_food,"淡淡的沙拉","一排大腿约你出来吃饭，结果吃了沙拉"));
        data.add(new PageData(R.drawable.healty_bunger,"Burger != Bug","那天聊得太开心，我已经不知道这顿饭啥味道了"));
        data.add(new PageData(R.drawable.lao_shu_cong,"外貌党","哈哈，找了半天差点被这破旧的书店外装修下走。"));
        data.add(new PageData(R.drawable.meizu_bear,"Meizu小熊","上海带回来的纪念品"));
        data.add(new PageData(R.drawable.xi_bei_u,"西贝莜面的大馒头","整个店全是I love U~充满爱"));
        data.add(new PageData(R.drawable.movie_ticket,"小黄人"," Mocha, fa les duo ma laki, boji. Mocha, fa ta qui ba dada. YMCA！"));
        data.add(new PageData(R.drawable.photogragher,"摄影师Pro","解锁吃马卡龙成就"));
        data.add(new PageData(R.drawable.indian,"3 idiots","以后看到阿三们会不会想起我"));
        data.add(new PageData(R.drawable.jia_quan,"搬家风云","那天你和我胃口不好，会不会吸甲醛得白血病了，我觉得又好笑又心疼"));
        data.add(new PageData(R.drawable.helping_meizi,"翻译小能手","折腾了一大晚上Excel搞不定，回去写了一个程序才挽救了你的辛苦劳作，第一次自己觉得写的程序萌萌哒"));
        data.add(new PageData(R.drawable.tou_nao,"头脑特工队","终于一起看了期待很久的电影。就像电影里的一样，所有的memory才组成了那个独一无二的你。或欢喜，或悲伤，或愤怒都是一种成长"));
        data.add(new PageData(R.drawable.black_people,"小黑人","好不容易合照一张，我们俩俨然成了“非洲土著”"));
        data.add(new PageData(R.drawable.ear_phone_pair,"Begin again","特别喜欢《Begin again》里男主女主，带着耳机听着对方的音乐约会的情节，傻乎乎的我也买了一个同款转换器和你听歌，哈哈"));
    }


    @Override
    public void onBackPressed() {
        if (isFabOpen) {
            animateFAB();
            return;
        }
        super.onBackPressed();
    }
}
