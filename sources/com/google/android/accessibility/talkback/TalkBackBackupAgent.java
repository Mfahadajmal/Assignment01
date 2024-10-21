package com.google.android.accessibility.talkback;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;
import androidx.core.content.ContextCompat$Api24Impl;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackBackupAgent extends BackupAgentHelper {
    @Override // android.app.backup.BackupAgent
    public final void onCreate() {
        addHelper("prefsBackup", new SharedPreferencesBackupHelper(ContextCompat$Api24Impl.createDeviceProtectedStorageContext(this), String.valueOf(getPackageName()).concat("_preferences"), "braille_keyboard", "braille_display", "braille_analytics_log_cache"));
    }

    @Override // android.app.backup.BackupAgent
    public final void onRestoreFinished() {
        super.onRestoreFinished();
        SpannableUtils$IdentifierSpan.putBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences(this), getResources(), R.string.pref_has_performed_restore_key, true);
    }
}
