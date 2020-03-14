package com.example.lmsclone.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsclone.Interface.ItemClickListener;
import com.example.lmsclone.R;

public class SubjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView sname, filepath, pid;

    public ItemClickListener listener;

    public SubjectViewHolder(@NonNull View itemView)
    {
        super(itemView);
        sname = (TextView) itemView.findViewById(R.id.subjectName);
    }

    public void setItemClickListner(ItemClickListener listner)
    {
        this.listener = listner;
    }

    @Override
    public void onClick(View v)
    {

    }
}
