/*
 * Copyright (c) 2016 Onegini B.V.
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

package com.onegini.mobile.sdk.cordova.mobileAuthentication;

import com.onegini.mobile.sdk.android.handlers.request.callback.OneginiPinCallback;
import com.onegini.mobile.sdk.android.model.entity.AuthenticationAttemptCounter;
import com.onegini.mobile.sdk.android.model.entity.OneginiMobileAuthenticationRequest;

public class PinCallback extends Callback {
  private final OneginiPinCallback pinCallback;
  private final AuthenticationAttemptCounter authenticationAttemptCounter;

  public PinCallback(final OneginiMobileAuthenticationRequest mobileAuthenticationRequest,
                     final OneginiPinCallback pinCallback,
                     final AuthenticationAttemptCounter authenticationAttemptCounter) {
    super(mobileAuthenticationRequest, Callback.Method.PIN);
    this.authenticationAttemptCounter = authenticationAttemptCounter;
    this.pinCallback = pinCallback;
  }

  public AuthenticationAttemptCounter getAuthenticationAttemptCounter() {
    return authenticationAttemptCounter;
  }

  public OneginiPinCallback getPinCallback() {
    return pinCallback;
  }
}
