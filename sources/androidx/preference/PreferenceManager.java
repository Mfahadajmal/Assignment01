package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import androidx.core.content.ContextCompat$Api24Impl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PreferenceManager {
    private final Context mContext;
    private SharedPreferences.Editor mEditor;
    public boolean mNoCommit;
    public OnDisplayPreferenceDialogListener mOnDisplayPreferenceDialogListener;
    public OnNavigateToScreenListener mOnNavigateToScreenListener;
    public OnPreferenceTreeClickListener mOnPreferenceTreeClickListener;
    public PreferenceScreen mPreferenceScreen;
    private SharedPreferences mSharedPreferences;
    private String mSharedPreferencesName;
    private long mNextId = 0;
    private int mStorage = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnDisplayPreferenceDialogListener {
        void onDisplayPreferenceDialog(Preference preference);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnNavigateToScreenListener {
        void onNavigateToScreen(PreferenceScreen preferenceScreen);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnPreferenceTreeClickListener {
        boolean onPreferenceTreeClick(Preference preference);
    }

    public PreferenceManager(Context context) {
        this.mContext = context;
        setSharedPreferencesName(getDefaultSharedPreferencesName(context));
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(getDefaultSharedPreferencesName(context), 0);
    }

    private static String getDefaultSharedPreferencesName(Context context) {
        return String.valueOf(context.getPackageName()).concat("_preferences");
    }

    private final void setNoCommit(boolean z) {
        SharedPreferences.Editor editor;
        if (!z && (editor = this.mEditor) != null) {
            editor.apply();
        }
        this.mNoCommit = z;
    }

    public final Preference findPreference(CharSequence charSequence) {
        PreferenceScreen preferenceScreen = this.mPreferenceScreen;
        if (preferenceScreen == null) {
            return null;
        }
        return preferenceScreen.findPreference(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SharedPreferences.Editor getEditor() {
        if (this.mNoCommit) {
            if (this.mEditor == null) {
                this.mEditor = getSharedPreferences().edit();
            }
            return this.mEditor;
        }
        return getSharedPreferences().edit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long getNextId() {
        long j;
        synchronized (this) {
            j = this.mNextId;
            this.mNextId = 1 + j;
        }
        return j;
    }

    public final SharedPreferences getSharedPreferences() {
        Context createDeviceProtectedStorageContext;
        if (this.mSharedPreferences == null) {
            if (this.mStorage != 1) {
                createDeviceProtectedStorageContext = this.mContext;
            } else {
                createDeviceProtectedStorageContext = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(this.mContext);
            }
            this.mSharedPreferences = createDeviceProtectedStorageContext.getSharedPreferences(this.mSharedPreferencesName, 0);
        }
        return this.mSharedPreferences;
    }

    public final PreferenceScreen inflateFromResource(Context context, int i, PreferenceScreen preferenceScreen) {
        setNoCommit(true);
        PreferenceInflater preferenceInflater = new PreferenceInflater(context, this);
        XmlResourceParser xml = preferenceInflater.mContext.getResources().getXml(i);
        try {
            Preference inflate = preferenceInflater.inflate(xml, preferenceScreen);
            xml.close();
            PreferenceScreen preferenceScreen2 = (PreferenceScreen) inflate;
            preferenceScreen2.onAttachedToHierarchy(this);
            setNoCommit(false);
            return preferenceScreen2;
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
    }

    public final void setSharedPreferencesName(String str) {
        this.mSharedPreferencesName = str;
        this.mSharedPreferences = null;
    }

    public final void setStorageDeviceProtected() {
        this.mStorage = 1;
        this.mSharedPreferences = null;
    }
}
