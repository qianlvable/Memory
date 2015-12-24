package com.towenqi.qianlv.memory.fragment;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.towenqi.qianlv.memory.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeCardFragment extends Fragment {


    public WelcomeCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_welcome_card, container, false);


        return root;
    }


}
