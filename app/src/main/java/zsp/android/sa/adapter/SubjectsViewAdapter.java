package zsp.android.sa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.dialog.DialogFactory;

public class SubjectsViewAdapter extends RecyclerView.Adapter<SubjectsViewAdapter.SubjectViewHolder> {

    private Context context;
    private List<SubjectView> subjectViews;

    public class SubjectView {

        private String subject;
        private String time;
        private String professor;
        private String cabinet;

        public SubjectView(String subject, String time, String professor, String cabinet) {
            this.subject = subject;
            this.time = time;
            this.professor = professor;
            this.cabinet = cabinet;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getProfessor() {
            return professor;
        }

        public void setProfessor(String professor) {
            this.professor = professor;
        }

        public String getCabinet() {
            return cabinet;
        }

        public void setCabinet(String cabinet) {
            this.cabinet = cabinet;
        }
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSubject;
        private TextView tvTime;
        private TextView tvProfessor;
        private TextView tvCabinet;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            tvSubject = (TextView) itemView.findViewById(R.id.tvSubject);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvProfessor = (TextView) itemView.findViewById(R.id.tvProfessor);
            tvCabinet = (TextView) itemView.findViewById(R.id.tvCabinet);
        }
    }

    public SubjectsViewAdapter(Context context) {
        this.context = context;
        this.subjectViews = new ArrayList<>();
        subjectViews.add(new SubjectView("БИС", "13:00", "Зубович К.А.", "к. 666"));
        subjectViews.add(new SubjectView("ЧМИ (лекция)", "11:15", "Давидовская М.И.", "к. 508"));
        subjectViews.add(new SubjectView("ЧМИ (практика)", "09:45", "Давидовская М.И.", "к. 518"));

        Collections.sort(subjectViews, new Comparator<SubjectView>() {
            @Override
            public int compare(SubjectView subjectView, SubjectView t1) {
                return subjectView.time.compareTo(t1.time);
            }
        });
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_subject, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity) context).showDialogPlus(DialogFactory.createSubjectDialog((BaseActivity) context));
            }
        });

        return new SubjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int position) {
        SubjectView view = subjectViews.get(position % 3);
        holder.tvSubject.setText(view.subject);
        holder.tvTime.setText(view.time);
        holder.tvCabinet.setText(view.cabinet);
        holder.tvProfessor.setText(view.professor);
    }

    @Override
    public int getItemCount() {
        return subjectViews.size();
    }
}
