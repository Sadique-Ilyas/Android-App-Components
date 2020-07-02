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


public class AddContactFragment extends Fragment {
    EditText Id, Name, Email;
    public AddContactFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        Button btnSave = view.findViewById(R.id.btn_save);
        Id = view.findViewById(R.id.txt_contact_id);
        Name = view.findViewById(R.id.txt_contact_name);
        Email = view.findViewById(R.id.txt_contact_email);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Id.getText().toString();
                String name = Name.getText().toString();
                String email = Email.getText().toString();

                ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
                SQLiteDatabase database = contactDBHelper.getWritableDatabase();

                contactDBHelper.addContact(Integer.parseInt(id),name,email,database);
                contactDBHelper.close();

                Id.setText("");
                Name.setText("");
                Email.setText("");
                Toast.makeText(getActivity(),"Contact Saved Successfully...",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public boolean isValidEmailAddress(String email)
    {
        return email.contains("@");
    }
}
