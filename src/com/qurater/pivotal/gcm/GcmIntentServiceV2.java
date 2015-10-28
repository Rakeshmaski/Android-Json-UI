/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qurater.pivotal.gcm;

import org.apache.commons.lang.StringEscapeUtils;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * This {@code IntentService} does the actual handling of the GCM message.
 * {@code GcmBroadcastReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class GcmIntentServiceV2 extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    public static final int NOTIFICATION_QUESTION_ID = 2;
    public static final int NOTIFICATION_ANSWER_ID = 3;
    public static final int NOTIFICATION_FOLLOW_QUESTION_ID = 4;
    public static final int NOTIFICATION_SHARE_ID = 5;
    @SuppressWarnings("unused")
	private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentServiceV2() {
        super("GcmIntentServiceV2");
    }
    public static final String TAG = "GCM Demo";

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (extras != null && !extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM will be
             * extended in the future with new message types, just ignore any message types you're
             * not interested in, or that you don't recognize.
             */
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                //sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                //sendNotification("Deleted messages on server: " + extras.toString());
            // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                /*
                for (int i = 0; i < 5; i++) {
                    Log.i(TAG, "Working... " + (i + 1)
                            + "/5 @ " + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }*/
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
                // Post notification of received message.
                sendNotification(extras);
                Log.i(TAG, "Received: " + extras.toString());
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    @SuppressWarnings("unused")
	private void sendNotification(Bundle extras) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        String msg = "";
        String title = "";
        String type = extras.getString("type");
        String id = extras.getString("id");

        String notificationType = "QUESTION";
        int notificationId = Integer.valueOf(id);
        if ("0".equals(type)) {
            String question = extras.getString("question");
            question = (question == null)? "": question;
            question = StringEscapeUtils.unescapeHtml(question);
            String from = extras.getString("who");
            msg = question;
            title = from + " needs your help.";
            notificationType = "QUESTION";
        }
        if ("1".equals(type)) {
            String answer = extras.getString("answer");
            answer = (answer == null)? "": answer;
            answer = StringEscapeUtils.unescapeHtml(answer);
            String from = extras.getString("who");
            msg = answer;
            title = from + "'s new answer to your request.";
            notificationType = "ANSWER";
        }
        if ("2".equals(type)) {
            String answer = extras.getString("answer");
            answer = (answer == null)? "": answer;
            answer = StringEscapeUtils.unescapeHtml(answer);
            String from = extras.getString("who");
            msg = answer;
            title = from + " answered a question you follow.";
            notificationType = "FOLLOW_QUESTION";
        }
        if ("3".equals(type)) {
            String question = extras.getString("question");
            question = (question == null)? "": question;
            question = StringEscapeUtils.unescapeHtml(question);
            String from = extras.getString("who");
            msg = question;
            title = from + " refered a question.";
            notificationType = "SHARE";
            notificationId = NOTIFICATION_SHARE_ID;
        }
        try {

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
