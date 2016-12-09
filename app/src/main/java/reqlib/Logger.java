package reqlib;

import android.util.Log;

import java.util.UUID;

import reqlib.ReqLib;

public class Logger {

    public static void logTaskRequest(UUID uuid, String msg) {
        logTaskRequest(uuid, msg, null);
    }

    public static void logTaskRequest(UUID uuid, String msg, Exception exception) {
        msg = "Task#" + (uuid == null ? "UNKNOWN" : uuid.toString()) + transformMsg(msg);

        if (exception == null) {
            Log.d(reqlib.ReqLib.name, msg);
        } else {
            Log.e(reqlib.ReqLib.name, msg, exception);
        }
    }

    public static void logDebugInfo(String msg) {
        Log.d(reqlib.ReqLib.name, msg == null ? "" : msg);
    }

    public static void logInternalError(Class clazz, String msg) {
        msg = (clazz == null ? "UNKNOWN CLASS" : clazz.getName()) + transformMsg(msg);
        Log.e(ReqLib.name, msg);
    }

    private static String transformMsg(String msg) {
        return msg == null ? "" : ": " + msg;
    }
}
