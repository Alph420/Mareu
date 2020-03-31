package com.openclassrooms.mareu.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import butterknife.BindView;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Alph4 le 31/03/2020.
 * Projet: Mareu
 **/
public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingListRecyclerViewAdapter.ViewHolder> {

    private List<Meeting> mMeetingList;

    public MeetingListRecyclerViewAdapter(List<Meeting> mMeetingList){
        this.mMeetingList = mMeetingList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = mMeetingList.get(position);
        holder.mMeetingColor.setBackgroundColor(meeting.getColor());
        holder.mMeetingInfo.setText(meeting.getInfo());
    }

    @Override
    public int getItemCount() {
        return this.mMeetingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_color)
        public ImageView mMeetingColor;
        @BindView(R.id.item_list_info)
        public TextView mMeetingInfo;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        public ViewHolder(View view ) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
