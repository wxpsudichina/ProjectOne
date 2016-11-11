package com.sudichina.shipperclient.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by SudiChina-105 on 2016/8/30.
 */
public class MyDialogUtils {
    // 定义一个显示消息的对话框
    public static void showDialog(final Context ctx
            , String msg, boolean goHome) {
        // 创建一个AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setMessage(msg).setCancelable(false);
//        if(goHome)
//        {
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//            {
//                @Override
//                public void onClick(DialogInterface dialog, int which)
//                {
//                    Intent i = new Intent(ctx , VehicleCertificationActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    ctx.startActivity(i);
//                }
//            });
//        }
//        else
//        {
//            builder.setPositiveButton("确定", null);
//        }
        builder.create().show();
    }

    // 定义一个显示指定组件的对话框
    public static void showDialog(final Context ctx, View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setView(view).setCancelable(true)
                .create()
                .show();

    }

    /**
     * 点击item项，弹出框消失
     *
     * @param ctx
     * @param view
     */
    public static void dissDialog(final Context ctx, View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                builder.setItems(position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
