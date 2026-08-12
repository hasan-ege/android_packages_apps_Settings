/*
 * Copyright (C) 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.biometrics.face;

import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import android.content.res.Configuration;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.settings.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

@RunWith(AndroidJUnit4.class)
public class FaceEnrollTextUtilsTest {
    @Test
    public void emptyLocalizedGuidanceFallsBackWithoutChangingLocale() {
        for (Locale locale : new Locale[] {
                Locale.forLanguageTag("es"),
                Locale.forLanguageTag("ja"),
                Locale.forLanguageTag("ar")}) {
            final Configuration configuration = new Configuration(
                    ApplicationProvider.getApplicationContext().getResources().getConfiguration());
            configuration.setLocale(locale);
            final Context context = ApplicationProvider.getApplicationContext()
                    .createConfigurationContext(configuration);

            assertThat(FaceEnrollTextUtils.getText(context,
                    R.string.security_settings_face_enroll_introduction_info_looking).toString())
                    .isNotEmpty();
            assertThat(context.getResources().getConfiguration().getLocales().get(0))
                    .isEqualTo(locale);
        }
    }

    @Test
    public void parentalAndAccessibilityFallbacksAreNonEmpty() {
        final Context context = ApplicationProvider.getApplicationContext();
        assertThat(FaceEnrollTextUtils.getText(context,
                R.string.security_settings_face_enroll_introduction_info_consent_looking)
                .toString()).isNotEmpty();
        assertThat(FaceEnrollTextUtils.getText(context,
                R.string.security_settings_face_enroll_introduction_accessibility_expanded)
                .toString()).isNotEmpty();
    }
}
