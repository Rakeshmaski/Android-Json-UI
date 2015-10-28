package com.qurater.pivotal.ui;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Warn user that he/she is getting out of the app
 * @author mishras
 *
 */
public class ExitWarning {
    public static boolean warn(final Activity activity) {
        ActivityManager mngr = (ActivityManager) activity.getSystemService( Activity.ACTIVITY_SERVICE );
        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if(taskList.get(0).numActivities == 1 &&
           taskList.get(0).topActivity.getClassName().equals(activity.getClass().getName())) {
            new AlertDialog.Builder(activity)
            .setTitle("Exit")
            .setMessage("Do you really want to exit?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    activity.finish();
                }})
             .setNegativeButton(android.R.string.no, null).show();
            return true;
        } else {
            return false;
        }
    }
}