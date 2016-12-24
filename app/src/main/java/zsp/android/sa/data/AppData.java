package zsp.android.sa.data;

import android.support.v4.app.Fragment;

import reqlib.hmi.ScheduleClientInterface;
import reqlib.hmi.response.ScheduleJson;

public class AppData {

    public static Boolean clear;
    public static String user;
    public static Fragment currentFragment;
    public static boolean changes = false;
    public static boolean editing = false;
    public static ScheduleClientInterface clientInterface;
    public static String savedLogin;
    public static String savedPassword;
    public static String token;
    public static ScheduleJson editingSchedule;
    public static ScheduleJson currentSchedule;
    public static ScheduleJson[] schedules;
    public static String search;
    public static boolean pop = false;
    public static boolean home = false;
    public static boolean animation = true;
    public static boolean startSearch = false;
    public static boolean logout = false;
    public static ScheduleJson savedSchedule;
}
