package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private OnDbOpListener dbOpListener;

    public HomeFragment() {}

    public interface OnDbOpListener
    {
        void dBOpPerformed(int method);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button btnADD = view.findViewById(R.id.btn_add_contact);
        btnADD.setOnClickListener(this);
        Button btnView = view.findViewById(R.id.btn_view_contact);
        btnView.setOnClickListener(this);
        Button btnUpdate = view.findViewById(R.id.btn_update_contact);
        btnUpdate.setOnClickListener(this);
        Button btnDelete = view.findViewById(R.id.btn_delete_contact);
        btnDelete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_add_contact:
                dbOpListener.dBOpPerformed(0);
                break;
            case R.id.btn_view_contact:
                dbOpListener.dBOpPerformed(1);
                break;
            case R.id.btn_update_contact:
                dbOpListener.dBOpPerformed(2);
                break;
            case R.id.btn_delete_contact:
                dbOpListener.dBOpPerformed(3);
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            dbOpListener = (OnDbOpListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+"must implement the interface method...");
        }
    }
}
