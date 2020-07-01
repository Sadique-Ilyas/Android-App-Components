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


public class UpdateContactsFragment extends Fragment {
    private EditText Update_id, Update_name, Update_email;

    public UpdateContactsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_contacts, container, false);
        Update_id = view.findViewById(R.id.txt_update_id);
        Update_name = view.findViewById(R.id.txt_update_name);
        Update_email = view.findViewById(R.id.txt_update_email);

        Button update_btn = view.findViewById(R.id.btn_update_contact);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });
        return view;
    }

    private void updateContact()
    {
        int id = Integer.parseInt(Update_id.getText().toString());
        String name = Update_name.getText().toString();
        String email = Update_email.getText().toString();

        ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
        SQLiteDatabase database = contactDBHelper.getWritableDatabase();

        contactDBHelper.updateContacts(id, name, email, database);
        contactDBHelper.close();

        Toast.makeText(getActivity(),"Contact Updated...",Toast.LENGTH_SHORT).show();

        Update_id.setText("");
        Update_name.setText("");
        Update_email.setText("");
    }
}
