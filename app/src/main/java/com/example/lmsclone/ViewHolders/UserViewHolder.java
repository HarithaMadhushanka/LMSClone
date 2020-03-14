package com.example.lmsclone.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsclone.Interface.ItemClickListener;
import com.example.lmsclone.R;

public class UserViewHolder extends RecyclerView.ViewHolder implements ItemClickListener {

    public TextView sId, sName, sDegree, sBatch, deleteUser;
    public ItemClickListener listener;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        sId = (TextView) itemView.findViewById(R.id.studentId);
        sName = (TextView) itemView.findViewById(R.id.studentName);
        sDegree = (TextView) itemView.findViewById(R.id.studentDegree);
        sBatch = (TextView) itemView.findViewById(R.id.studentBatch);
        deleteUser = (TextView) itemView.findViewById(R.id.deleteUser);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {

    }
}
