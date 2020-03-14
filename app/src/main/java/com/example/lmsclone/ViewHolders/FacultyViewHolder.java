package com.example.lmsclone.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsclone.Interface.ItemClickListener;
import com.example.lmsclone.R;

public class FacultyViewHolder extends RecyclerView.ViewHolder implements ItemClickListener {
    public TextView txtFacultyname, fid;
    public ItemClickListener listener;

    public FacultyViewHolder(@NonNull View itemView) {
        super(itemView);
        txtFacultyname = (TextView) itemView.findViewById(R.id.facultyName);

    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {

    }
}
