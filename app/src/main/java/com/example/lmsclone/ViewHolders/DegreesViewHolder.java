package com.example.lmsclone.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsclone.Interface.ItemClickListener;
import com.example.lmsclone.R;

public class DegreesViewHolder extends RecyclerView.ViewHolder implements ItemClickListener {
    public TextView dname;
    public ItemClickListener listener;


    public DegreesViewHolder(@NonNull View itemView) {
        super(itemView);
        dname = (TextView) itemView.findViewById(R.id.degreeName);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {

    }
}
