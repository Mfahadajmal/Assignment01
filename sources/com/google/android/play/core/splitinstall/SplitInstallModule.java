package com.google.android.play.core.splitinstall;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.play.core.common.model.EventRecord;
import com.google.android.play.core.splitcompat.FileStorage;
import j$.util.DesugarCollections;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallModule {
    public final Object SplitInstallModule$ar$context;

    public SplitInstallModule(Object obj) {
        this.SplitInstallModule$ar$context = obj;
    }

    public static final int addResourcePathsForFile$ar$ds(AssetManager assetManager, File file) {
        return ((Integer) NativeLibraryPathListMutex.invokeMethod(assetManager, "addAssetPath", Integer.class, String.class, file.getPath())).intValue();
    }

    public static String getLanguage(Locale locale) {
        String concat;
        String language = locale.getLanguage();
        if (locale.getCountry().isEmpty()) {
            concat = "";
        } else {
            concat = "_".concat(String.valueOf(locale.getCountry()));
        }
        return String.valueOf(language).concat(concat);
    }

    private static String messageFor(String str, String str2, Object... objArr) {
        if (objArr.length > 0) {
            try {
                str2 = String.format(Locale.US, str2, objArr);
            } catch (IllegalFormatException e) {
                Log.e("PlayCore", "Unable to format ".concat(String.valueOf(str2)), e);
                str2 = str2 + " [" + TextUtils.join(", ", objArr) + "]";
            }
        }
        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str2, str, " : ");
    }

    public final synchronized void addResourcePathsForFiles(Context context, Set set) {
        AssetManager assets = context.getAssets();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            addResourcePathsForFile$ar$ds(assets, (File) it.next());
        }
    }

    public final synchronized boolean addResourcePathsForSplitIdsDisablingStrictMode(Context context, Set set) {
        StrictMode.ThreadPolicy threadPolicy;
        boolean z;
        try {
            threadPolicy = StrictMode.getThreadPolicy();
            try {
                StrictMode.allowThreadDiskReads();
                StrictMode.allowThreadDiskWrites();
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            threadPolicy = null;
        }
        try {
            try {
                HashSet hashSet = new HashSet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    hashSet.add(((FileStorage) this.SplitInstallModule$ar$context).fileForVerifiedSplit((String) it.next()));
                }
                addResourcePathsForFiles(context, hashSet);
                z = true;
            } catch (Exception e) {
                Log.e("SplitCompat", "Error installing additional splits", e);
                z = false;
            }
            if (threadPolicy != null) {
                StrictMode.setThreadPolicy(threadPolicy);
            }
        } catch (Throwable th) {
            if (threadPolicy != null) {
                StrictMode.setThreadPolicy(threadPolicy);
            }
            throw th;
        }
        return z;
    }

    public final void e$ar$ds$cdf76eb7_0(String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 6)) {
            Log.e("PlayCore", messageFor((String) this.SplitInstallModule$ar$context, str, objArr));
        }
    }

    public final void e$ar$ds$fb17e3b8_0(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 6)) {
            Log.e("PlayCore", messageFor((String) this.SplitInstallModule$ar$context, str, objArr), th);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.Map, java.lang.Object] */
    public final Map getLanguageToSplits(Collection collection) {
        Set unmodifiableSet;
        HashMap hashMap = new HashMap();
        for (String str : this.SplitInstallModule$ar$context.keySet()) {
            if (!this.SplitInstallModule$ar$context.containsKey(str)) {
                unmodifiableSet = Collections.emptySet();
            } else {
                HashSet hashSet = new HashSet();
                for (Map.Entry entry : ((Map) this.SplitInstallModule$ar$context.get(str)).entrySet()) {
                    if (collection.contains(entry.getKey())) {
                        hashSet.add((String) entry.getValue());
                    }
                }
                unmodifiableSet = DesugarCollections.unmodifiableSet(hashSet);
            }
            hashMap.put(str, unmodifiableSet);
        }
        return hashMap;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List, java.lang.Object] */
    public final void recordEvent(int i) {
        this.SplitInstallModule$ar$context.add(new EventRecord(i, System.currentTimeMillis()));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Set, java.lang.Object] */
    public final synchronized void registerListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.SplitInstallModule$ar$context.add(hapticPatternParser$$ExternalSyntheticLambda1);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Set, java.lang.Object] */
    public final synchronized void unregisterListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.SplitInstallModule$ar$context.remove(hapticPatternParser$$ExternalSyntheticLambda1);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Set, java.lang.Object] */
    public final synchronized void updateListeners(Object obj) {
        Iterator it = this.SplitInstallModule$ar$context.iterator();
        while (it.hasNext()) {
            ((HapticPatternParser$$ExternalSyntheticLambda1) it.next()).onStateUpdate(obj);
        }
    }

    public final void w$ar$ds(String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 5)) {
            Log.w("PlayCore", messageFor((String) this.SplitInstallModule$ar$context, str, objArr));
        }
    }

    public SplitInstallModule(byte[] bArr, byte[] bArr2) {
        this.SplitInstallModule$ar$context = new ArrayList();
    }

    public SplitInstallModule(byte[] bArr) {
        this.SplitInstallModule$ar$context = new HashSet();
    }

    public SplitInstallModule(String str) {
        this.SplitInstallModule$ar$context = ("UID: [" + Process.myUid() + "]  PID: [" + Process.myPid() + "] ").concat(String.valueOf(str));
    }

    public SplitInstallModule() {
        this.SplitInstallModule$ar$context = new HashMap();
    }
}
