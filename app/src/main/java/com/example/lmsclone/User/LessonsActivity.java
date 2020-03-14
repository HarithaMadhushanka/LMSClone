package com.example.lmsclone.User;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lmsclone.Model.uploadPdf;
import com.example.lmsclone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LessonsActivity extends AppCompatActivity {

    private String sId = "";
    private String subject_id = "";
    private ListView myPdfListView;
    TextView backBtn;
    Query databaseReference;
    List<uploadPdf> uploadPdfs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        sId = getIntent().getStringExtra("sid");
        subject_id = getIntent().getStringExtra("subject_id");
        myPdfListView = (ListView) findViewById(R.id.myListView);
        backBtn = (TextView) findViewById(R.id.backBtn);
        uploadPdfs = new ArrayList<>();

        viewAllFiles();

        myPdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                uploadPdf uploadPdf = uploadPdfs.get(position);

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.parse(uploadPdf.getUrl()), "application/pdf");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(intent);

                Uri webpage = Uri.parse(uploadPdf.getUrl());
                webpage = Uri.parse(uploadPdf.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    private void viewAllFiles()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Lessons");
        databaseReference.orderByChild("subject_id").equalTo(subject_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    uploadPdf uploadPdf = postSnapshot.getValue(com.example.lmsclone.Model.uploadPdf.class);
                    uploadPdfs.add(uploadPdf);
                }
                String[] uploads = new String[uploadPdfs.size()];

                for(int i=0; i<uploads.length; i++)
                {
                    uploads[i] = uploadPdfs.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView myText = (TextView) view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);
                        return super.getView(position, convertView, parent);
                    }
                };
                myPdfListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });


    }

}
