package zsp.android.sa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.activity.MainActivity;
import zsp.android.sa.data.AppData;
import zsp.android.sa.fragment.SubjectsFragment;

public class SchedulesViewAdapter extends RecyclerView.Adapter<SchedulesViewAdapter.ScheduleViewHolder> {

    private Context context;
    private ScheduleJson[] schedules;

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView courseGroup;
        private TextView university;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            courseGroup = (TextView) itemView.findViewById(R.id.tvCourseGroup);
            university = (TextView) itemView.findViewById(R.id.tvUniversity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppData.currentSchedule = schedules[getAdapterPosition()];
                    AppData.editingSchedule = schedules[getAdapterPosition()].getCopy();
                    SubjectsFragment fragment = new SubjectsFragment();
                    fragment.setSchedule(schedules[getAdapterPosition()]);
                    ((MainActivity) context).replaceFragment(R.id.fragment_container, fragment, true, false);
                }
            });
        }
    }

    public SchedulesViewAdapter(Context context, ScheduleJson[] schedules) {
        this.context = context;
        this.schedules = schedules == null ? new ScheduleJson[0] : schedules;
        AppData.schedules = schedules;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_schedule, parent, false);

        return new ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ScheduleViewHolder holder, int position) {
        ScheduleJson view = schedules[position];
        holder.courseGroup.setText(view.getCourse() + " курс " + view.getGroup() + " группа");
        holder.university.setText(view.getUniversity());
    }

    @Override
    public int getItemCount() {
        return schedules.length;
    }
}
