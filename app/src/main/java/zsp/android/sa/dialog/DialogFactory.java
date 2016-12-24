package zsp.android.sa.dialog;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import reqlib.hmi.request.AuthenticateRequest;
import reqlib.hmi.request.CreateRequest;
import reqlib.hmi.request.FindRequest;
import reqlib.hmi.request.SignUpRequest;
import reqlib.hmi.request.UpdateRequest;
import reqlib.hmi.response.ScheduleJson;
import reqlib.hmi.response.SubjectsJson;
import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.adapter.SubjectsViewAdapter;
import zsp.android.sa.callback.AuthenticateCallbackHandler;
import zsp.android.sa.callback.CreateCallbackHandler;
import zsp.android.sa.callback.FindCallbackHandler;
import zsp.android.sa.callback.SignUpCallbackHandler;
import zsp.android.sa.callback.UpdateCallbackHandler;
import zsp.android.sa.data.AppData;
import zsp.android.sa.fragment.DaySubjectsFragment;
import zsp.android.sa.fragment.NoScheduleFragment;
import zsp.android.sa.fragment.SubjectsFragment;
import zsp.android.sa.util.StateController;

public class DialogFactory {

    public static DialogPlus createLoginDialog(final BaseActivity activity) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_login))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final EditText etLogin = (EditText) dialog.findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) dialog.findViewById(R.id.etPassword);
        Button btAuth = (Button) dialog.findViewById(R.id.btAuth);

        btAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etLogin.getText().toString().isEmpty()) {
                    etLogin.setError("Введите почтовый адрес.");
                    return;
                }

                if (!etLogin.getText().toString().contains("@") ||
                        etLogin.getText().toString().indexOf("@") == etLogin.getText().toString().length() - 1) {

                    etLogin.setError("Неверный почтовый адрес.");
                    return;
                }

                if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Введите пароль.");
                    return;
                }

                AppData.savedLogin = etLogin.getText().toString();
                AppData.savedPassword = etPassword.getText().toString();

                AuthenticateRequest authenticateRequest = new AuthenticateRequest(
                        etLogin.getText().toString(),
                        etPassword.getText().toString()
                );

                activity.showDialogPlus(DialogFactory.createProgressDialog(
                        activity, "Выполнение авторизации..."));

                AppData.clientInterface.authenticate(authenticateRequest,
                        new AuthenticateCallbackHandler(activity));
            }
        });

        return dialog;
    }

    public static DialogPlus createRegisterDialog(final BaseActivity activity) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_register))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final EditText etLogin = (EditText) dialog.findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) dialog.findViewById(R.id.etPassword);
        final EditText etRepeatPassword = (EditText) dialog.findViewById(R.id.etRepeatPassword);
        Button btRegister = (Button) dialog.findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etLogin.getText().toString().isEmpty()) {
                    etLogin.setError("Введите почтовый адрес.");
                    return;
                }

                if (!etLogin.getText().toString().contains("@") ||
                        etLogin.getText().toString().indexOf("@") == etLogin.getText().toString().length() - 1) {

                    etLogin.setError("Неверный почтовый адрес.");
                    return;
                }

                if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Введите пароль.");
                    return;
                }

                if (!etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
                    etRepeatPassword.setError("Пароли не совпадают.");
                    return;
                }

                AppData.savedLogin = etLogin.getText().toString();
                AppData.savedPassword = etPassword.getText().toString();

                SignUpRequest signUpRequest = new SignUpRequest(
                        etLogin.getText().toString(),
                        etPassword.getText().toString()
                );

                activity.showDialogPlus(DialogFactory.createProgressDialog(
                        activity, "Выполнение регистрации..."));

                AppData.clientInterface.signUp(signUpRequest,
                        new SignUpCallbackHandler(activity));
            }
        });

        return dialog;
    }

    public static DialogPlus createSubjectDialog(final BaseActivity activity,
                                                 final SubjectsJson subject, final int day,
                                                 final RecyclerView rc) {

        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_subject))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final TextView tvSubject = (TextView) dialog.findViewById(R.id.tvSubject);
        tvSubject.setText("Предмет:");
        final TextView tvTime = (TextView) dialog.findViewById(R.id.tvTime);
        tvTime.setText("Время:");
        final TextView tvProfessor = (TextView) dialog.findViewById(R.id.tvProfessor);
        tvProfessor.setText("Преподаватель:");
        final TextView tvCabinet = (TextView) dialog.findViewById(R.id.tvCabinet);
        tvCabinet.setText("Кабинет:");
        final TextView tvInfo = (TextView) dialog.findViewById(R.id.tvInfo);
        tvInfo.setText("Описание:");
        final EditText etSubject = (EditText) dialog.findViewById(R.id.etSubject);
        etSubject.setText(subject.getName());
        final EditText etTime = (EditText) dialog.findViewById(R.id.etTime);
        etTime.setText(subject.getShortTime());
        final EditText etProfessor = (EditText) dialog.findViewById(R.id.etProfessor);
        etProfessor.setText(subject.getProfessor());
        final EditText etCabinet = (EditText) dialog.findViewById(R.id.etCabinet);
        etCabinet.setText(subject.getPlace());
        final EditText etInfo = (EditText) dialog.findViewById(R.id.etInfo);
        etInfo.setText(subject.getDescription());

        Button btClose = (Button) dialog.findViewById(R.id.btClose);
        Button btSave = (Button) dialog.findViewById(R.id.btSave);

        if (!AppData.editing) {
            etSubject.setEnabled(false);
            etTime.setEnabled(false);
            etProfessor.setEnabled(false);
            etCabinet.setEnabled(false);
            etInfo.setEnabled(false);
            btSave.setVisibility(View.GONE);
            btClose.setText("Закрыть");
        }

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dismissDialogPlus();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSubject.getText().toString().isEmpty()) {
                    etSubject.setError("Введите имя предмета.");
                    return;
                }

                if (etTime.getText().toString().length() < 5) {
                    etTime.setError("Неверный формат времени.");
                    return;
                }

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                    formatter.parse(etTime.getText().toString());
                } catch (ParseException e) {
                    etTime.setError("Неверный формат времени.");
                    return;
                }

                if (etProfessor.getText().toString().isEmpty()) {
                    etProfessor.setError("Введите имя преподавателя.");
                    return;
                }

                if (etCabinet.getText().toString().isEmpty()) {
                    etCabinet.setError("Введите номер кабинета.");
                    return;
                }

                if (!subject.getName().equals(etSubject.getText().toString())) {
                    subject.setName(etSubject.getText().toString());
                    AppData.changes = true;
                }

                if (!subject.getShortTime().equals(etTime.getText().toString())) {
                    subject.setShortTime(etTime.getText().toString());
                    AppData.changes = true;
                }

                if (!subject.getProfessor().equals(etProfessor.getText().toString())) {
                    subject.setProfessor(etProfessor.getText().toString());
                    AppData.changes = true;
                }

                if (!subject.getPlace().equals(etCabinet.getText().toString())) {
                    subject.setPlace(etCabinet.getText().toString());
                    AppData.changes = true;
                }

                if (!subject.getDescription().equals(etInfo.getText().toString())) {
                    subject.setDescription(etInfo.getText().toString());
                    AppData.changes = true;
                }

                rc.setAdapter(new SubjectsViewAdapter(activity, day));
                rc.invalidate();
                activity.dismissDialogPlus();
                StateController.update(activity);
            }
        });

        return dialog;
    }

    public static DialogPlus createAddSubjectDialog(final BaseActivity activity, final int day, final RecyclerView rc) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_add_subject))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final TextView tvSubject = (TextView) dialog.findViewById(R.id.tvSubject);
        tvSubject.setText("Предмет:");
        final TextView tvTime = (TextView) dialog.findViewById(R.id.tvTime);
        tvTime.setText("Время:");
        final TextView tvProfessor = (TextView) dialog.findViewById(R.id.tvProfessor);
        tvProfessor.setText("Преподаватель:");
        final TextView tvCabinet = (TextView) dialog.findViewById(R.id.tvCabinet);
        tvCabinet.setText("Кабинет:");
        final TextView tvInfo = (TextView) dialog.findViewById(R.id.tvInfo);
        tvInfo.setText("Описание:");
        final EditText etSubject = (EditText) dialog.findViewById(R.id.etSubject);
        final EditText etTime = (EditText) dialog.findViewById(R.id.etTime);
        final EditText etProfessor = (EditText) dialog.findViewById(R.id.etProfessor);
        final EditText etCabinet = (EditText) dialog.findViewById(R.id.etCabinet);
        final EditText etInfo = (EditText) dialog.findViewById(R.id.etInfo);

        Button btClose = (Button) dialog.findViewById(R.id.btClose);
        Button btSave = (Button) dialog.findViewById(R.id.btSave);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dismissDialogPlus();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSubject.getText().toString().isEmpty()) {
                    etSubject.setError("Введите имя предмета.");
                    return;
                }

                if (etTime.getText().toString().length() < 5) {
                    etTime.setError("Неверный формат времени.");
                    return;
                }

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                    formatter.parse(etTime.getText().toString());
                } catch (ParseException e) {
                    etTime.setError("Неверный формат времени.");
                    return;
                }

                if (etProfessor.getText().toString().isEmpty()) {
                    etProfessor.setError("Введите имя преподавателя.");
                    return;
                }

                if (etCabinet.getText().toString().isEmpty()) {
                    etCabinet.setError("Введите номер кабинета.");
                    return;
                }

                SubjectsJson subjectJson = new SubjectsJson(etSubject.getText().toString(),
                        etProfessor.getText().toString(), 0, etTime.getText().toString(),
                        day, etCabinet.getText().toString(), etInfo.getText().toString());

                AppData.editingSchedule.getSubjects().add(subjectJson);
                rc.setAdapter(new SubjectsViewAdapter(activity, day));
                rc.invalidate();
                AppData.changes = true;
                activity.dismissDialogPlus();
                StateController.update(activity);
            }
        });

        return dialog;
    }

    public static DialogPlus createAddScheduleDialog(final BaseActivity activity) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_add_schedule))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final EditText etCountry = (EditText) dialog.findViewById(R.id.etCountry);
        final EditText etUniversity = (EditText) dialog.findViewById(R.id.etUniversity);
        final EditText etCourse = (EditText) dialog.findViewById(R.id.etCourse);
        final EditText etGroup = (EditText) dialog.findViewById(R.id.etGroup);

        Button btClose = (Button) dialog.findViewById(R.id.btClose);
        Button btSave = (Button) dialog.findViewById(R.id.btSave);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dismissDialogPlus();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCountry.getText().toString().isEmpty()) {
                    etCountry.setError("Введите страну.");
                    return;
                }

                if (etUniversity.getText().toString().isEmpty()) {
                    etUniversity.setError("Введите университет.");
                    return;
                }

                if (etCourse.getText().toString().isEmpty()) {
                    etCourse.setError("Введите курс.");
                    return;
                }

                if (etGroup.getText().toString().isEmpty()) {
                    etGroup.setError("Введите номер группы.");
                    return;
                }

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf2= new SimpleDateFormat("HH:mm:ss");
                String date = sdf1.format(new Date()) + "T" + sdf2.format(new Date()) + ".000Z";

                CreateRequest createRequest = new CreateRequest(date, date, true, etUniversity.getText().toString(),
                        etCountry.getText().toString(), etCourse.getText().toString(), etGroup.getText().toString());

                activity.showDialogPlus(DialogFactory.createProgressDialog(
                        activity, "Создание расписания..."));

                AppData.clientInterface.create(createRequest,
                        new CreateCallbackHandler(activity));
            }
        });

        return dialog;
    }

    public static DialogPlus createProgressDialog(BaseActivity activity, String text) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_progress))
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        ((TextView) dialog.findViewById(R.id.body)).setText(text);
        return dialog;
    }

    public static DialogPlus createErrorDialog(final BaseActivity activity, String text) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_error))
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        ((TextView) dialog.findViewById(R.id.body)).setText(text);
        Button btClose = (Button) dialog.findViewById(R.id.btClose);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dismissDialogPlus();
            }
        });

        return dialog;
    }

    public static DialogPlus createWarningDialog(final BaseActivity activity) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_warning))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        Button btSave = (Button) dialog.findViewById(R.id.btSave);
        Button btDontSave = (Button) dialog.findViewById(R.id.btDontSave);
        Button btCancel = (Button) dialog.findViewById(R.id.btCancel);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dismissDialogPlus();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppData.home) {
                    AppData.pop = true;
                }

                UpdateRequest updateRequest = new UpdateRequest(AppData.editingSchedule);
                activity.showDialogPlus(DialogFactory.createProgressDialog(activity, "Сохранение расписания..."));
                AppData.clientInterface.update(updateRequest, new UpdateCallbackHandler(activity));
            }
        });

        btDontSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppData.changes = false;
                AppData.editing = false;
                AppData.editingSchedule = AppData.currentSchedule.getCopy();
                activity.dismissDialogPlus();

                if (AppData.logout) {
                    List<Fragment> fragmentList =((SubjectsFragment) AppData.currentFragment).getAdapter().getmFragmentList();

                    for (int i = 0; i < 7; ++i) {
                        DaySubjectsFragment dsf = (DaySubjectsFragment) fragmentList.get(i);

                        if (dsf.getRc() != null) {
                            dsf.getRc().setAdapter(new SubjectsViewAdapter(activity, i + 1));
                            dsf.getRc().invalidate();
                        }
                    }

                    activity.setUserName(null);
                } else if (AppData.startSearch) {
                    FindRequest findRequest = new FindRequest(AppData.search);
                    activity.showDialogPlus(DialogFactory.createProgressDialog(activity, "Поиск расписаний..."));
                    AppData.clientInterface.find(findRequest, new FindCallbackHandler(activity));
                } else if (AppData.home) {
                    activity.replaceFragment(R.id.fragment_container, new NoScheduleFragment(), false, true);
                    AppData.home = false;
                } else {
                    if (AppData.currentFragment instanceof NoScheduleFragment) {
                        List<Fragment> fragmentList = ((SubjectsFragment) AppData.currentFragment).getAdapter().getmFragmentList();

                        for (int i = 0; i < 7; ++i) {
                            DaySubjectsFragment dsf = (DaySubjectsFragment) fragmentList.get(i);

                            if (dsf.getRc() != null) {
                                dsf.getRc().setAdapter(new SubjectsViewAdapter(activity, i + 1));
                                dsf.getRc().invalidate();
                            }
                        }
                    }

                    activity.getSupportFragmentManager().popBackStack();
                }

                StateController.update(activity);
            }
        });

        return dialog;
    }
}
