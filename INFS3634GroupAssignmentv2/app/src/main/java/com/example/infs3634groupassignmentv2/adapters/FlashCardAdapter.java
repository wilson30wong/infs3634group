package com.example.infs3634groupassignmentv2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.activities.FlashCardActivity;
import com.example.infs3634groupassignmentv2.activities.PokemonActivity;
import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.model.FlashCard;

import java.util.ArrayList;

public class FlashCardAdapter extends RecyclerView.Adapter<FlashCardAdapter.FlashCardViewHolder> {

    private ArrayList<FlashCard> flashCardArrayList;

    public FlashCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flash_card, parent,
                false);
        FlashCardViewHolder flashCardViewHolder = new FlashCardViewHolder(view);
        return flashCardViewHolder;
    }

    public void onBindViewHolder(@NonNull FlashCardViewHolder holder, int position) {
        final FlashCard flashCardObject = flashCardArrayList.get(position);
        final Context context = holder.view.getContext();

        if(position == 0){
            holder.flashCardTitle.setText("Make A New Flashcard");
            holder.flashCardBody.setText("Press Here to Make A New Flashcard");
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FlashCardActivity.class);
                    intent.putExtra("id", MainActivity.profile.getFlashCardArrayList().size());
                    context.startActivity(intent);
                }
            });
        } else{
            holder.flashCardTitle.setText(flashCardObject.getTitle());
            holder.flashCardBody.setText(flashCardObject.getBody());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FlashCardActivity.class);
                    intent.putExtra("id", flashCardObject.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public int getItemCount() {
        return flashCardArrayList.size();
    }

    public void setData(ArrayList<FlashCard> data) {
        this.flashCardArrayList = data;
    }

    public static class FlashCardViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView flashCardTitle;
        public TextView flashCardBody;

        public FlashCardViewHolder(View v) {
            super(v);
            view = v;
            flashCardTitle = v.findViewById(R.id.flashCardTitle);
            flashCardBody = v.findViewById(R.id.flashCardBody);
        }
    }
}

