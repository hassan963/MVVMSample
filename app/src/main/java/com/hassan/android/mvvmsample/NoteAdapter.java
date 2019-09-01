package com.hassan.android.mvvmsample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> noteList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note note = noteList.get(i);
        noteHolder.titleTV.setText(note.getTitle());
        noteHolder.priorityTV.setText(String.valueOf(note.getPriority()));
        noteHolder.descriptionTV.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return noteList.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView titleTV, priorityTV, descriptionTV;

        public NoteHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            priorityTV = itemView.findViewById(R.id.priorityTV);
            descriptionTV = itemView.findViewById(R.id.descriptionTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(noteList.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
