package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class DeleteContactFragment extends Fragment {
    private EditText delete_id;

    public DeleteContactFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_contact, container, false);
        delete_id = view.findViewById(R.id.txt_delete_id);
        Button delete_btn = view.findViewById(R.id.btn_delete_contact);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContacts();
            }
        });
        return view;
    }

    private void deleteContacts()
    {
        ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
        SQLiteDatabase database = contactDBHelper.getWritableDatabase();

        int id = Integer.parseInt(delete_id.getText().toString());

        contactDBHelper.deleteContacts(id,database);
        contactDBHelper.close();

        delete_id.setText("");
        Toast.makeText(getActivity(),"Contact Deleted Successfully...",Toast.LENGTH_SHORT).show();
    }
}
