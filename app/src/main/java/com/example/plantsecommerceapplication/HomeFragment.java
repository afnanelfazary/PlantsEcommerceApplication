package com.example.plantsecommerceapplication;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class HomeFragment extends Fragment  {
  TextView title;
  Context context;
    ViewPager myViewFlipper;
    ToggleButton tab[]=new ToggleButton[3];
    com.example.plantsecommerceapplication.FragmentAdapterTabs FragmentAdapterTabs;
         ImageButton SideNav ;
 //   private LinearLayout mLinearLayout;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        return root;


    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        tab[0]=view.findViewById(R.id.tab1);
        tab[1]=view.findViewById(R.id.tab2);
        tab[2]=view.findViewById(R.id.tab3);

        myViewFlipper=view.findViewById(R.id.myViewFlipper);
        title=view.findViewById(R.id.TV_title);

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DEEPWOOD.ttf");
    title.setTypeface(font);



//        mFrgAct = getActivity();
//        mIntent = mFrgAct.getIntent(); //  Intent intent = new Intent(getActivity().getIntent());
        myViewFlipper.setCurrentItem(2);
        FragmentAdapterTabs =new FragmentAdapterTabs( getFragmentManager());
         //   FragmentAdapterTabs = new FragmentAdapterTabs(getSupportFragmentManager());
        myViewFlipper.setAdapter(FragmentAdapterTabs);
        // Make Recommended Tab the Defult
        myViewFlipper.setCurrentItem(0);
        tab[0].setTextColor(getResources().getColor(R.color.black));
        tab[0].setPaintFlags(tab[0].getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tab[0].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  myViewFlipper.setCurrentItem(0);
                tab[0].setTextColor(getResources().getColor(R.color.black));
                tab[0].setPaintFlags(tab[0].getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                  unCheck(v.getId());

              }
          });
        tab[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewFlipper.setCurrentItem(1);
                tab[1].setTextColor(getResources().getColor(R.color.black));
                tab[1].setPaintFlags(tab[1].getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                unCheck(v.getId());

            }
        });
        tab[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewFlipper.setCurrentItem(2);
                tab[2].setTextColor(getResources().getColor(R.color.black));
                tab[2].setPaintFlags(tab[2].getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                      unCheck(v.getId());

            }
        });
     }
    public void unCheck(int id) {
        for (ToggleButton tb : tab) {
            if (tb.getId() != id) {
                tb.setChecked(false);
                tb.setTextColor(getResources().getColor(R.color.grey));
                tb.setPaintFlags(0);

            } else
                tb.setChecked(true);


        }
    }}