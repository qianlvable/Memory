package com.towenqi.qianlv.memory.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.towenqi.qianlv.memory.R;
import com.towenqi.qianlv.memory.UI.IntroView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeMapFragment extends Fragment {


    public WelcomeMapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_welcome_map, container, false);
        IntroView introView = (IntroView) root.findViewById(R.id.intro);

        TextView slogan = (TextView) root.findViewById(R.id.map_slogan);
        slogan.animate().alpha(255).setDuration(1000).setStartDelay(3200)
                .setInterpolator(new AccelerateDecelerateInterpolator()).start();
        introView.setSvgResource(R.raw.china_test);
        return root;
    }


}
