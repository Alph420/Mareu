package com.openclassrooms.mareu.ui;

import org.greenrobot.eventbus.EventBus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.events.DeleteMeetingEvent;
import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alph4 le 31/03/2020.
 * Projet: Mareu
 **/
public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingListRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetingList;

    MeetingListRecyclerViewAdapter(List<Meeting> MeetingList) {
        mMeetingList = MeetingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Meeting meeting = mMeetingList.get(position);
        holder.mMeetingColor.setColorFilter(meeting.getColor());
        holder.mMeetingInfo.setText(meeting.getInfo());
        holder.mMeetingParticipants.setText(meeting.getParticipantsList());

        holder.mDeleteButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteMeetingEvent(meeting)));
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_color)
        public ImageView mMeetingColor;
        @BindView(R.id.item_list_info)
        public TextView mMeetingInfo;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView(R.id.item_list_participants)
        public TextView mMeetingParticipants;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
