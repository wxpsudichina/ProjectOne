package com.sudichina.shipperclient.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;

import com.sudichina.shipperclient.R;

/**
 * Created by mike on 2016/8/8.
 */
public class DialogUtils {
    public static void showDialog(Context context, int viewId, int style) {
        Dialog dialog = new Dialog(context, style);
        dialog.setContentView(viewId);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = CommonUtils.getScreenWidth(context);
        lp.height = 500;
        lp.gravity = Gravity.BOTTOM;
        System.out.println("width = " + lp.width);
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setWindowAnimations(R.style.bottomDialogAnim);
        dialog.show();
    }
}
