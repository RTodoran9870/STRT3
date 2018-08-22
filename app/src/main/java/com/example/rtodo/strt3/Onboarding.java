package com.example.rtodo.strt3;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Onboarding extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private SliderAdapter sliderAdapter;
    private LinearLayout linearLayout;
    private TextView[] mDots;
    private int k;
    private Button mNextButton;
    private Button mPrevButton;
    private int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mSlideViewPager = findViewById(R.id.slideViewPager);
        linearLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mNextButton = findViewById(R.id.nextBT);
        mPrevButton = findViewById(R.id.prevBT);

        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(Integer position){

        mDots = new TextView[3];
        linearLayout.removeAllViews();

        for (k = 0; k < mDots.length; k++){
            mDots[k] = new TextView(this);
            mDots[k].setText(Html.fromHtml("&#8226;"));
            mDots[k].setTextSize(35);
            mDots[k].setTextColor(getResources().getColor(R.color.transparentwhite));

            linearLayout.addView(mDots[k]);

        }

        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            mCurrentPage = position;

            if (position == 0){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(false);
                mPrevButton.setVisibility(View.INVISIBLE);

                mNextButton.setText("NEXT");
                mPrevButton.setText("");
            }else if(position == mDots.length - 1){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("FINISH");
                mPrevButton.setText("BACK");

                mNextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openAlarmManager();
                        finish();
                    }
                });
            }else {
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("NEXT");
                mPrevButton.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void openAlarmManager(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
