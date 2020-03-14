package com.example.lmsclone.Admin;

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

import com.example.lmsclone.Model.Users;
import com.example.lmsclone.R;
import com.example.lmsclone.ViewHolders.UserViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminViewStudentsActivity extends AppCompatActivity {
    private DatabaseReference UserRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_students);

        UserRef = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView = findViewById(R.id.recyclerMenuViewStudents);
        backBtn = (TextView) findViewById(R.id.backBtn);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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

        FirebaseRecyclerOptions<Users> options =
                new  FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(UserRef, Users.class)
                        .build();

        FirebaseRecyclerAdapter<Users, UserViewHolder> adapter =
                new FirebaseRecyclerAdapter<Users, UserViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UserViewHolder holder, final int position, @NonNull final Users model) {

                        holder.sId.setText(model.getId());
                        holder.sName.setText(model.getName());
                        holder.sDegree.setText(model.getDegree());
                        holder.sBatch.setText(model.getBatch());
                        holder.deleteUser.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String uID = getRef(position).getKey();
                                RemoveOrder(uID);
                            }

                            private void RemoveOrder(String uID) {
                                UserRef.child(uID).removeValue();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_students_layout, parent, false);
                        UserViewHolder holder = new UserViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(AdminViewStudentsActivity.this, 1);
        recyclerView.setLayoutManager(mGridLayoutManager);
    }

}
