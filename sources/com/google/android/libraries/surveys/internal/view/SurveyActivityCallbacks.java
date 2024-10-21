package com.google.android.libraries.surveys.internal.view;

import android.support.v4.app.FragmentManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SurveyActivityCallbacks {
    void autoCloseActivityWithDelay();

    FragmentManager getSupportFragmentManager();

    void hideCloseButton();

    boolean isRunningEmbeddedFlow();

    boolean isSurveyControlRequired();
}
