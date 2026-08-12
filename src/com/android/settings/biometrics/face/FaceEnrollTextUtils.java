/*
 * Copyright (C) 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.biometrics.face;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;

import java.util.Locale;

final class FaceEnrollTextUtils {
    private FaceEnrollTextUtils() {}

    static CharSequence getText(Context context, int resId) {
        final CharSequence localized = context.getText(resId);
        if (!TextUtils.isEmpty(localized)) {
            return localized;
        }
        final Configuration configuration = new Configuration(
                context.getResources().getConfiguration());
        configuration.setLocale(Locale.ENGLISH);
        return context.createConfigurationContext(configuration).getText(resId);
    }
}
