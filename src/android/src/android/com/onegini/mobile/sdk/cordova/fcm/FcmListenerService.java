/*
 * Copyright (c) 2017 Onegini B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onegini.mobile.sdk.cordova.fcm;

import static com.onegini.mobile.sdk.cordova.OneginiCordovaPluginConstants.EXTRA_MOBILE_AUTHENTICATION;
import static com.onegini.mobile.sdk.cordova.OneginiCordovaPluginConstants.PUSH_MSG_CONTENT;
import static com.onegini.mobile.sdk.cordova.OneginiCordovaPluginConstants.PUSH_MSG_TRANSACTION_ID;
import static java.util.Collections.emptyMap;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

@SuppressLint("Registered")
public class FcmListenerService extends FirebaseMessagingService {

  @Override
  public void onMessageReceived(final RemoteMessage message) {
    if (message == null) {
      return;
    }

    if (isMobileAuthenticationRequest(message)) {
      startMainActivityWithIntentExtra(message);
    }
  }

  private void startMainActivityWithIntentExtra(final RemoteMessage message) {
    final String packageName = this.getPackageName();
    final Intent launchIntent = this.getPackageManager().getLaunchIntentForPackage(packageName);
    launchIntent.putExtra(EXTRA_MOBILE_AUTHENTICATION, message);
    startActivity(launchIntent);
  }

  private boolean isMobileAuthenticationRequest(final RemoteMessage message) {
    try {
      Map<String, String> data = getRemoteMessageData(message);
      final JSONObject messageContent = new JSONObject(data.get(PUSH_MSG_CONTENT));
      return messageContent.has(PUSH_MSG_TRANSACTION_ID);
    } catch (JSONException e) {
      return false;
    }
  }

  private Map<String, String> getRemoteMessageData(RemoteMessage message) {
    Map<String, String> data = message.getData();
    if (data == null) {
      return emptyMap();
    }

    return data;
  }
}