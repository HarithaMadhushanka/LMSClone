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

import com.example.lmsclone.Model.Degrees;
import com.example.lmsclone.R;
import com.example.lmsclone.ViewHolders.DegreesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DegreesActivity extends AppCompatActivity {
    private DatabaseReference DegreesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String facultyId = "";
    TextView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degrees);

        DegreesRef = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Degrees");
        recyclerView = findViewById(R.id.recyclerMenuDegree);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        facultyId = getIntent().getStringExtra("id");
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

        FirebaseRecyclerOptions<Degrees> options =
                new  FirebaseRecyclerOptions.Builder<Degrees>()
                        .setQuery(DegreesRef.orderByChild("fid").equalTo(facultyId), Degrees.class)
                        .build();

        FirebaseRecyclerAdapter<Degrees, DegreesViewHolder> adapter =
                new FirebaseRecyclerAdapter<Degrees, DegreesViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull DegreesViewHolder holder, int position, @NonNull final Degrees model) {

                            holder.dname.setText(model.getName());
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(DegreesActivity.this, SubjectsActivity.class);
//                                    intent.putExtra("id" , facultyId);
                                    intent.putExtra("sid" , model.getSid());
                                    startActivity(intent);
                                }
                            });
                    }

                    @NonNull
                    @Override
                    public DegreesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.degrees_layout, parent, false);
                        DegreesViewHolder holder = new DegreesViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(DegreesActivity.this, 1);
        recyclerView.setLayoutManager(mGridLayoutManager);
    }
}
