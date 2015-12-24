package com.towenqi.qianlv.memory.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.towenqi.qianlv.memory.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomePageFace extends Fragment {

    public WelcomePageFace() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_welcome_page_face, container, false);

        return root;
    }


}
