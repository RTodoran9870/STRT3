package com.example.rtodo.strt3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slide_images = {
      R.drawable.g1,
            R.drawable.g2,
            R.drawable.g3
    };

    public String[] slide_headings = {
            "EAT",
            "SLEEP",
            "CODE"
    };

    public String[] slide_descriptions = {
            "Razvan era un baiat cuminte si fericit pana a descoperit ca sa fii un baiat cuminte si fericit nu te duce nicaieri asa ca a fevenitun baiat rau.",
            "Razvan era un baiat cuminte si fericit pana a descoperit ca sa fii un baiat cuminte si fericit nu te duce nicaieri asa ca a fevenitun baiat rau.",
            "Razvan era un baiat cuminte si fericit pana a descoperit ca sa fii un baiat cuminte si fericit nu te duce nicaieri asa ca a fevenitun baiat rau."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide, container, false);

        ImageView slideImageView = view.findViewById(R.id.slideimageView);
        TextView slideHeading = view.findViewById(R.id.slidetextView);
        TextView slideDescription = view.findViewById(R.id.slidetextView2);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
