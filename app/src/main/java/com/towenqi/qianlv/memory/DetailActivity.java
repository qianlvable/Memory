package com.towenqi.qianlv.memory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG = "DetailActvity";
    ImageView detailCoverImg;
    TextView title;
    TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailCoverImg = (ImageView) findViewById(R.id.detail_img);
        title = (TextView) findViewById(R.id.detail_title);
        description = (TextView) findViewById(R.id.detail_description);

        View backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null){
            Bundle bundle = intent.getExtras();
            final PageData pageData = bundle.getParcelable(TAG);
            title.setText(pageData.mTitleStrId);
            description.setText(pageData.mDescriStrId);

            Bitmap bm = BitmapFactory.decodeResource(getResources(), pageData.mImgResId);
            detailCoverImg.setImageBitmap(bm);
            Palette.generateAsync(bm, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(palette.getDarkMutedColor(0xff000000));
                    window.setNavigationBarColor(palette.getLightMutedColor(0xff000000));
                }
            });
        }
    }


}
