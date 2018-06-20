package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SecondFragment extends Fragment{
    public SecondFragment(){

    }
    View MyView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.second_layout,container,false);
        Intent intent = new Intent(getActivity(),CourseViewList.class);
        startActivity(intent);
        return MyView;
    }
}
