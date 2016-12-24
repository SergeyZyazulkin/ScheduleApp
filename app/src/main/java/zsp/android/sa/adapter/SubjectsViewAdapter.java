package zsp.android.sa.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import reqlib.hmi.response.SubjectsJson;
import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;
import zsp.android.sa.fragment.DaySubjectsFragment;
import zsp.android.sa.fragment.SubjectsFragment;
import zsp.android.sa.util.StateController;

public class SubjectsViewAdapter extends RecyclerView.Adapter<SubjectsViewAdapter.SubjectViewHolder> {

    private Context context;
    private List<SubjectsJson> subjectViews;
    private int day;

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSubject;
        private TextView tvTime;
        private TextView tvProfessor;
        private TextView tvCabinet;
        private ImageView ivDelete;
        private Button btDelete;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            tvSubject = (TextView) itemView.findViewById(R.id.tvSubject);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvProfessor = (TextView) itemView.findViewById(R.id.tvProfessor);
            tvCabinet = (TextView) itemView.findViewById(R.id.tvCabinet);
            btDelete = (Button) itemView.findViewById(R.id.btDelete);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DaySubjectsFragment fragment = (DaySubjectsFragment) ((SubjectsFragment) AppData.currentFragment)
                            .getAdapter().getmFragmentList().get(day - 1);

                    RecyclerView rc = fragment.getRc();

                    ((BaseActivity) context).showDialogPlus(DialogFactory.createSubjectDialog(
                            (BaseActivity) context, subjectViews.get(getAdapterPosition()), day, rc));
                }
            });

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SubjectsJson removing = subjectViews.get(getAdapterPosition());
                    AppData.editingSchedule.getSubjects().remove(removing);
                    DaySubjectsFragment fragment = (DaySubjectsFragment) ((SubjectsFragment) AppData.currentFragment)
                            .getAdapter().getmFragmentList().get(day - 1);

                    RecyclerView rc = fragment.getRc();
                    rc.setAdapter(new SubjectsViewAdapter(context, day));
                    rc.invalidate();
                    AppData.changes = true;
                    StateController.update((BaseActivity) context);
                }
            });
        }
    }

    public SubjectsViewAdapter(Context context, int day) {
        this.context = context;
        this.subjectViews = new ArrayList<>();
        this.day = day;

        if (AppData.editingSchedule != null) {
            for (SubjectsJson subject : AppData.editingSchedule.getSubjects()) {
                if (subject.getDayOfWeek() == day) {
                    subjectViews.add(subject);
                }
            }
        }

        Collections.sort(subjectViews, new Comparator<SubjectsJson>() {
            @Override
            public int compare(SubjectsJson subjectsJson, SubjectsJson t1) {
                return subjectsJson.getTime().compareTo(t1.getTime());
            }
        });
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_subject, parent, false);

        return new SubjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int position) {
        SubjectsJson view = subjectViews.get(position);
        holder.tvSubject.setText(view.getName());
        holder.tvTime.setText(view.getShortTime());
        holder.tvCabinet.setText(view.getPlace());
        holder.tvProfessor.setText(view.getProfessor());

        if (AppData.editing) {
            holder.btDelete.setEnabled(true);
            holder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            holder.btDelete.setEnabled(false);
            holder.ivDelete.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return subjectViews.size();
    }
}
