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
public class MoonShapeFragment extends Fragment {
    Button startBtn;

    public MoonShapeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_moon_shape, container, false);
        // Inflate the layout for this fragment
        startBtn = (Button) root.findViewById(R.id.start_button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return root;
    }


}
