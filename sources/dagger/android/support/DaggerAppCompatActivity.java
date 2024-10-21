package dagger.android.support;

import android.content.ComponentCallbacks2;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import dagger.android.HasAndroidInjector;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DaggerAppCompatActivity extends AppCompatActivity implements HasAndroidInjector {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ComponentCallbacks2 application = getApplication();
        if (!(application instanceof HasAndroidInjector)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), HasAndroidInjector.class.getCanonicalName()));
        }
        HasAndroidInjector hasAndroidInjector = (HasAndroidInjector) application;
        hasAndroidInjector.androidInjector$ar$class_merging$ar$ds();
        hasAndroidInjector.getClass();
        throw null;
    }

    @Override // dagger.android.HasAndroidInjector
    public final void androidInjector$ar$class_merging$ar$ds() {
    }
}
