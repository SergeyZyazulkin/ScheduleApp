package zsp.android.sa.dialog;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnDismissListener;

import java.lang.reflect.Field;

public class DialogPlusExtended {

    private DialogPlus dialogPlus;

    public DialogPlusExtended() {
    }

    public DialogPlusExtended(DialogPlus dialogPlus) {
        this.dialogPlus = dialogPlus;
    }

    public DialogPlus getDialogPlus() {
        return dialogPlus;
    }

    public void setDialogPlus(DialogPlus dialogPlus) {
        this.dialogPlus = dialogPlus;
    }

    public OnDismissListener getOnDismissListener() {
        try {
            Field f = dialogPlus.getClass().getDeclaredField("onDismissListener");
            f.setAccessible(true);
            return (OnDismissListener) f.get(dialogPlus);
        } catch (Exception ignored) {
            return null;
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        try {
            Field f = dialogPlus.getClass().getDeclaredField("onDismissListener");
            f.setAccessible(true);
            f.set(dialogPlus, onDismissListener);
        } catch (Exception ignored) {
        }
    }
}
