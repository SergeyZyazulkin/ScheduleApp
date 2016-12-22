package zsp.android.sa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zsp.android.sa.R;

public class SchedulesViewAdapter extends RecyclerView.Adapter<SchedulesViewAdapter.ScheduleViewHolder> {

    private Context context;
    private List<ScheduleView> scheduleViews;

    public class ScheduleView {

        private String course;
        private String group;
        private String university;

        public ScheduleView(String course, String group, String university) {
            this.course = course;
            this.group = group;
            this.university = university;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView courseGroup;
        private TextView university;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            courseGroup = (TextView) itemView.findViewById(R.id.tvCourseGroup);
            university = (TextView) itemView.findViewById(R.id.tvUniversity);
        }
    }

    public SchedulesViewAdapter(Context context) {
        this.context = context;
        this.scheduleViews = new ArrayList<>();
        scheduleViews.add(new ScheduleView("4", "12", "Белорусский государственный университет"));
        scheduleViews.add(new ScheduleView("3", "10", "БГУИР"));
        scheduleViews.add(new ScheduleView("5", "1", "БНТУ"));
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_schedule, parent, false);

        return new ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ScheduleViewHolder holder, int position) {
        ScheduleView view = scheduleViews.get(position % 3);
        holder.courseGroup.setText(view.getCourse() + " курс " + view.getGroup() + " группа");
        holder.university.setText(view.getUniversity());
    }

    @Override
    public int getItemCount() {
        return scheduleViews.size() * 10;
    }
}
