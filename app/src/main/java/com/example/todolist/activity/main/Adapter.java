package com.example.todolist.activity.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.model.Note;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerViewAdapter> {
    private List<Note> notes;
    private Context context;
    private ItemClickListener itemClickListener;

    public Adapter(List<Note> notes, Context context, ItemClickListener itemClickListener) {
        this.notes = notes;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_notes, parent,false);
            return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
             Note note = notes.get(position);
             holder.title.setText(note.getTitle());
             holder.note.setText(note.getBody());
             holder.date.setText(note.getDate());
             holder.card.setCardBackgroundColor(note.getColor());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title,note,date;
        CardView card;
        ItemClickListener itemClickListener;

        public RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);


            title = itemView.findViewById(R.id.title);
            note = itemView.findViewById(R.id.note);
            date = itemView.findViewById(R.id.date);
            card = itemView.findViewById(R.id.card_item);
            this.itemClickListener = itemClickListener;
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            itemClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    public interface ItemClickListener{
        void onItemClick(View v, int pos);
    }
}
