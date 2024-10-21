package com.google.android.play.core.splitinstall.testing;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback;
import com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Provider;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitCompatInterceptorProvider;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallInfoProvider;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import dagger.Lazy;
import j$.util.DesugarCollections;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FakeSplitInstallManager implements SplitInstallManager {
    public static final long DOWNLOAD_SEGMENT_DURATION_MS = TimeUnit.SECONDS.toMillis(1);
    private final Set additionalLanguages;
    public final Executor backgroundExecutor;
    public final Context context;
    private final SplitInstallModule currentLocaleProvider$ar$class_merging;
    public final SplitInstallModule frameworkListenerRegistry$ar$class_merging$ar$class_merging;
    private final Set installedModuleNames;
    public final DownloadedStateInterceptor$Provider interceptorProvider;
    public final SplitInstallModule listenerRegistry$ar$class_merging$ar$class_merging;
    private final Lazy localTestingConfig;
    private final Handler mainThreadHandler;
    public final AtomicBoolean shouldNetworkError;
    public final NativeLibraryPathListMutex sleeper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final SplitInstallInfoProvider splitInstallInfoProvider;
    private final AtomicReference splitInstallSessionState;
    private final File splitsDir;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface StateTransform {
        SplitInstallSessionState apply(SplitInstallSessionState splitInstallSessionState);
    }

    public FakeSplitInstallManager(Context context, File file, SplitInstallInfoProvider splitInstallInfoProvider, Lazy lazy) {
        Executor executor = NativeLibraryPathListMutex.get();
        SplitInstallModule splitInstallModule = new SplitInstallModule(context);
        NativeLibraryPathListMutex nativeLibraryPathListMutex = new NativeLibraryPathListMutex();
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.splitInstallSessionState = new AtomicReference();
        this.installedModuleNames = DesugarCollections.synchronizedSet(new HashSet());
        this.additionalLanguages = DesugarCollections.synchronizedSet(new HashSet());
        this.shouldNetworkError = new AtomicBoolean(false);
        this.context = context;
        this.splitsDir = file;
        this.splitInstallInfoProvider = splitInstallInfoProvider;
        this.localTestingConfig = lazy;
        this.backgroundExecutor = executor;
        this.currentLocaleProvider$ar$class_merging = splitInstallModule;
        this.sleeper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = nativeLibraryPathListMutex;
        this.listenerRegistry$ar$class_merging$ar$class_merging = new SplitInstallModule((byte[]) null);
        this.frameworkListenerRegistry$ar$class_merging$ar$class_merging = new SplitInstallModule((byte[]) null);
        this.interceptorProvider = SplitCompatInterceptorProvider.INSTANCE;
    }

    private final SplitInstallModule getLanguageSplitMapping$ar$class_merging() {
        try {
            SplitInstallModule languageSplitMapping$ar$class_merging$bfee7109_0 = this.splitInstallInfoProvider.getLanguageSplitMapping$ar$class_merging$bfee7109_0(this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE).applicationInfo.metaData);
            if (languageSplitMapping$ar$class_merging$bfee7109_0 != null) {
                return languageSplitMapping$ar$class_merging$bfee7109_0;
            }
            throw new IllegalStateException("Language information could not be found. Make sure you are using the target application context, not the tests context, and the app is built as a bundle.");
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("App is not found in PackageManager", e);
        }
    }

    public static String getModuleName(String str) {
        return str.split("\\.config\\.", 2)[0];
    }

    private final synchronized SplitInstallSessionState updateStatus(StateTransform stateTransform) {
        SplitInstallSessionState internalSessionState;
        SplitInstallSessionState apply;
        internalSessionState = getInternalSessionState();
        apply = stateTransform.apply(internalSessionState);
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(this.splitInstallSessionState, internalSessionState, apply)) {
            return apply;
        }
        return null;
    }

    private final Task updateStatusAndGetTaskForError(final int i) {
        updateStatus(new StateTransform() { // from class: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager$$ExternalSyntheticLambda8
            @Override // com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.StateTransform
            public final SplitInstallSessionState apply(SplitInstallSessionState splitInstallSessionState) {
                long j = FakeSplitInstallManager.DOWNLOAD_SEGMENT_DURATION_MS;
                if (splitInstallSessionState == null) {
                    return null;
                }
                return new SplitInstallSessionState(splitInstallSessionState.sessionId(), 6, i, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages(), null, null);
            }
        });
        return SpannableUtils$NonCopyableTextSpan.forException(new SplitInstallException(i));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task deferredUninstall(List list) {
        return SpannableUtils$NonCopyableTextSpan.forException(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set getInstalledModules() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.splitInstallInfoProvider.getInstalledAndEmulatedModules());
        hashSet.addAll(this.installedModuleNames);
        return hashSet;
    }

    public final SplitInstallSessionState getInternalSessionState() {
        return (SplitInstallSessionState) this.splitInstallSessionState.get();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task getSessionStates() {
        List emptyList;
        SplitInstallSessionState internalSessionState = getInternalSessionState();
        if (internalSessionState != null) {
            emptyList = Collections.singletonList(internalSessionState);
        } else {
            emptyList = Collections.emptyList();
        }
        return SpannableUtils$NonCopyableTextSpan.forResult(emptyList);
    }

    public final void ingest(final List list, final List list2, final List list3, final long j, final boolean z) {
        this.interceptorProvider.getInterceptor$ar$class_merging$ar$class_merging().intercept(list, new DownloadedStateInterceptor$Callback(this) { // from class: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.1
            final /* synthetic */ FakeSplitInstallManager this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback
            public final void whenEmulated() {
                this.this$0.setModulesInstalledAndNotify(list2, list3, j);
            }

            @Override // com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback
            public final void whenFailed(int i) {
                this.this$0.notifyStatus$ar$ds(i);
            }

            @Override // com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback
            public final void whenVerified() {
                if (!z) {
                    this.this$0.ingest(list, list2, list3, j, true);
                }
            }
        });
    }

    public final boolean notifyStatus(final int i, final int i2, final Long l, final Long l2, final List list, final Integer num, final List list2) {
        SplitInstallSessionState updateStatus = updateStatus(new StateTransform() { // from class: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager$$ExternalSyntheticLambda6
            @Override // com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.StateTransform
            public final SplitInstallSessionState apply(SplitInstallSessionState splitInstallSessionState) {
                int intValue;
                long longValue;
                long longValue2;
                List list3;
                long j = FakeSplitInstallManager.DOWNLOAD_SEGMENT_DURATION_MS;
                if (splitInstallSessionState == null) {
                    splitInstallSessionState = new SplitInstallSessionState(0, 0, 0, 0L, 0L, new ArrayList(), new ArrayList(), null, null);
                }
                Integer num2 = num;
                if (num2 == null) {
                    intValue = splitInstallSessionState.sessionId();
                } else {
                    intValue = num2.intValue();
                }
                int i3 = intValue;
                Long l3 = l;
                if (l3 == null) {
                    longValue = splitInstallSessionState.bytesDownloaded();
                } else {
                    longValue = l3.longValue();
                }
                long j2 = longValue;
                Long l4 = l2;
                if (l4 == null) {
                    longValue2 = splitInstallSessionState.totalBytesToDownload();
                } else {
                    longValue2 = l4.longValue();
                }
                long j3 = longValue2;
                List list4 = list;
                if (list4 == null) {
                    list4 = splitInstallSessionState.moduleNames();
                }
                List list5 = list4;
                List list6 = list2;
                if (list6 == null) {
                    list3 = splitInstallSessionState.languages();
                } else {
                    list3 = list6;
                }
                return new SplitInstallSessionState(i3, i, i2, j2, j3, list5, list3, null, null);
            }
        });
        if (updateStatus != null) {
            this.mainThreadHandler.post(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, updateStatus, 16, (byte[]) null));
            return true;
        }
        return false;
    }

    public final void notifyStatus$ar$ds(int i) {
        notifyStatus(6, i, null, null, null, null, null);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.listenerRegistry$ar$class_merging$ar$class_merging.registerListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
    }

    public final void setModulesInstalledAndNotify(List list, List list2, long j) {
        this.installedModuleNames.addAll(list);
        this.additionalLanguages.addAll(list2);
        Long valueOf = Long.valueOf(j);
        notifyStatus(5, 0, valueOf, valueOf, null, null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x013e, code lost:
    
        if (r5.contains(r15) == false) goto L45;
     */
    /* JADX WARN: Incorrect condition in loop: B:20:0x008e */
    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.tasks.Task startInstall(final com.google.android.play.core.splitinstall.SplitInstallRequest r20) {
        /*
            Method dump skipped, instructions count: 645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.gms.tasks.Task");
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.listenerRegistry$ar$class_merging$ar$class_merging.unregisterListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
    }
}
