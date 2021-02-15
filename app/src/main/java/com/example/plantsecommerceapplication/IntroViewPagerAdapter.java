package com.example.plantsecommerceapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantsecommerceapplication.IntroScreenItem;
import com.example.plantsecommerceapplication.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import java.util.List;



public class IntroViewPagerAdapter extends PagerAdapter {

    Context mContext ;
    List<ScreenItem> mListScreen;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);
        title.setText(mListScreen.get(position).getTitle());
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(), "fonts/With Rose TTF.ttf");
        title.setTypeface(fontType);
        description.setText(mListScreen.get(position).getDescription());
//        Typeface fontType2 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Landsdowne.ttf");
//        description.setTypeface(fontType2);
         imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;





    }
    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }
    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }}