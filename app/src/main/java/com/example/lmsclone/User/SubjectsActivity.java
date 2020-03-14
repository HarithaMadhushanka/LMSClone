package com.example.lmsclone.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsclone.Model.Subjects;
import com.example.lmsclone.R;
import com.example.lmsclone.ViewHolders.SubjectViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SubjectsActivity extends AppCompatActivity {

    private DatabaseReference SubjectsRef;
    TextView backBtn;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String facultyId = "";
    private String degreeId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        SubjectsRef = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Subjects");
        recyclerView = findViewById(R.id.recyclerMenu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        facultyId = getIntent().getStringExtra("id");
        degreeId = getIntent().getStringExtra("sid");
        backBtn = (TextView) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Subjects> options =
                new  FirebaseRecyclerOptions.Builder<Subjects>()
                        .setQuery(SubjectsRef.orderByChild("sid").equalTo(degreeId), Subjects.class)
                        .build();

        FirebaseRecyclerAdapter<Subjects, SubjectViewHolder> adapter =
                new FirebaseRecyclerAdapter<Subjects, SubjectViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull SubjectViewHolder holder, int position, @NonNull final Subjects model)
                    {
                            holder.sname.setText(model.getName());
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SubjectsActivity.this,LessonsActivity.class);
//                                    intent.putExtra("id" , facultyId);
                                intent.putExtra("sid" , model.getSid());
                                intent.putExtra("subject_id" , model.getSubject_id());
                                startActivity(intent);

                            }

                        });
                    }

                    @NonNull
                    @Override
                    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects_layout, parent, false);
                        SubjectViewHolder holder = new SubjectViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(SubjectsActivity.this, 1);
        recyclerView.setLayoutManager(mGridLayoutManager);

    }


}
