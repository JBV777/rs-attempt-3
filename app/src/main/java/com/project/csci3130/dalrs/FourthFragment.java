package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;



import com.google.firebase.auth.FirebaseUser;

public class FourthFragment extends Fragment{

    View MyView;
    private Button changepassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.fourth_layout,container,false);
        FirebaseUser user = LoginInterfaceActivity.getUser();
        changepassword = MyView.findViewById(R.id.changepassword);

        changepassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });
        //ArrayAdapter<String> adapter = new ArrayAdapter(getView().getContext(),android.R.layout.simple_list_item_1,list);
       // getListView().setAdapter

        return MyView;
    }

}
