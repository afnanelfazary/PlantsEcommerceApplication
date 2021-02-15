package com.example.plantsecommerceapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ToggleButton;

import com.example.plantsecommerceapplication.IndoorFragment;
import com.example.plantsecommerceapplication.OutdoorFragment;
import com.example.plantsecommerceapplication.RecommendedFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapterTabs extends FragmentPagerAdapter {
  //  ToggleButton tab1,tab2,tab3;

    public FragmentAdapterTabs(
                @NonNull FragmentManager fm)
        {
            super(fm);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            if (position == 0)
                fragment = new RecommendedFragment();
//                tab1.findViewById(R.id.tab1)
//                tab1.setTextColor(Color.BLACK);
//                tab1.setPaintFlags(tab1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//}
            else if (position == 1)
                fragment = new OutdoorFragment();
            else if (position == 2)
                fragment = new IndoorFragment();
//                tab1.setTextColor(Color.BLACK);
//                tab2.setPaintFlags(tab1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            return fragment;
        }
        @Override
        public int getCount()
        {

            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            String title = null;

            if (position == 0)
                title = "Recommended";

            else if (position == 1)
                title = "Outdoor";
            else if (position == 2)
                title = "Indoor";
            return title;
        }
    }