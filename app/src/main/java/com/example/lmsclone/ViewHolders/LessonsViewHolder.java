package com.example.lmsclone.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsclone.Interface.ItemClickListener;
import com.example.lmsclone.R;

public class LessonsViewHolder extends RecyclerView.ViewHolder implements ItemClickListener {
    public TextView lname, download, pid;
    public LessonsViewHolder(@NonNull View itemView) {
        super(itemView);
        lname = (TextView) itemView.findViewById(R.id.lessonName);
        download = (Button) itemView.findViewById(R.id.downloadBtn);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {

    }
}
