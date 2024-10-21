package com.google.android.accessibility.talkback.labeling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Looper;
import android.support.v7.widget.GapWorker;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.labeling.LabelsFetchRequest;
import com.google.android.accessibility.talkback.labeling.RevertImportedLabelsRequest;
import com.google.android.accessibility.talkback.labeling.StoragelessLabelManager;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.labeling.LabelManager;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CustomLabelManager extends TalkBackLabelManager {
    public final ExecutionList.RunnableExecutorPair client$ar$class_merging$ae701839_0;
    public final Context context;
    private HapticPatternParser$$ExternalSyntheticLambda1 dataConsistencyCheckCallback$ar$class_merging$ar$class_merging$ar$class_merging;
    public final NavigableSet labelCache;
    public final HapticPatternParser$$ExternalSyntheticLambda1 labelChangeListener$ar$class_merging$ar$class_merging;
    private HapticPatternParser$$ExternalSyntheticLambda1 labelsInPackageChangeListener$ar$class_merging$ar$class_merging$ar$class_merging;
    private final LocaleChangedReceiver localeChangedReceiver;
    private final PackageManager packageManager;
    private final PackageRemovalReceiver packageRemovalReceiver;
    private final CacheRefreshReceiver refreshReceiver;
    public int runningTasks;
    private boolean shouldShutdownClient;
    private final LabelManager.State stateReader;
    public final HapticPatternParser$$ExternalSyntheticLambda1 taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final Pattern RESOURCE_NAME_SPLIT_PATTERN = Pattern.compile(":id/");
    private static final IntentFilter REFRESH_INTENT_FILTER = new IntentFilter("com.google.android.marvin.talkback.labeling.REFRESH_LABEL_CACHE");

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.labeling.CustomLabelManager$6 */
    /* loaded from: classes.dex */
    final class AnonymousClass6 implements RevertImportedLabelsRequest.OnImportLabelsRevertedListener {
        final /* synthetic */ CustomLabelManager this$0;
        final /* synthetic */ RevertImportedLabelsRequest.OnImportLabelsRevertedListener val$listener;

        public AnonymousClass6(CustomLabelManager customLabelManager, RevertImportedLabelsRequest.OnImportLabelsRevertedListener onImportLabelsRevertedListener) {
            r2 = onImportLabelsRevertedListener;
            r1 = customLabelManager;
        }

        @Override // com.google.android.accessibility.talkback.labeling.RevertImportedLabelsRequest.OnImportLabelsRevertedListener
        public final void onImportLabelsReverted() {
            r1.sendCacheRefreshIntent(new String[0]);
            r2.onImportLabelsReverted();
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.labeling.CustomLabelManager$7 */
    /* loaded from: classes.dex */
    public final class AnonymousClass7 implements LabelsFetchRequest.OnLabelsFetchedListener {
        final /* synthetic */ Object CustomLabelManager$7$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass7(Object obj, int i) {
            this.switching_field = i;
            this.CustomLabelManager$7$ar$this$0 = obj;
        }

        @Override // com.google.android.accessibility.talkback.labeling.LabelsFetchRequest.OnLabelsFetchedListener
        public final void onLabelsFetched(List list) {
            boolean z;
            if (this.switching_field != 0) {
                if (list != null && !list.isEmpty()) {
                    z = true;
                } else {
                    z = false;
                }
                new TalkBackAnalyticsLoggerWithClearcut.SendLogsToClearcutAndClearCacheTask((TalkBackAnalyticsLoggerWithClearcut) this.CustomLabelManager$7$ar$this$0).execute(Boolean.valueOf(z));
                return;
            }
            ((CustomLabelManager) this.CustomLabelManager$7$ar$this$0).labelCache.clear();
            String defaultLocale = SpannableUtils$IdentifierSpan.getDefaultLocale();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Label label = (Label) it.next();
                String str = label.mLocale;
                if (str != null && str.startsWith(defaultLocale)) {
                    ((CustomLabelManager) this.CustomLabelManager$7$ar$this$0).labelCache.add(label);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CacheRefreshReceiver extends BroadcastReceiver {
        public CacheRefreshReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            CustomLabelManager.this.refreshCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LocaleChangedReceiver extends BroadcastReceiver {
        public LocaleChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            CustomLabelManager.this.refreshCache();
        }
    }

    public CustomLabelManager(Context context) {
        this(context, null);
    }

    public static final void checkUiThread$ar$ds() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
        } else {
            throw new IllegalStateException("run not on UI thread");
        }
    }

    public static Pair splitResourceName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = RESOURCE_NAME_SPLIT_PATTERN.split(str, 2);
        if (split.length == 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
            return new Pair(split[0], split[1]);
        }
        LogUtils.w("CustomLabelManager", "Failed to parse resource: %s", str);
        return null;
    }

    public final void addLabel(String str, String str2) {
        String str3;
        if (!isInitialized()) {
            return;
        }
        if (str2 != null) {
            String trim = str2.trim();
            if (!TextUtils.isEmpty(trim)) {
                Pair splitResourceName = splitResourceName(str);
                if (splitResourceName == null) {
                    LogUtils.w("CustomLabelManager", "Attempted to add a label with an invalid or poorly formed view ID.", new Object[0]);
                    return;
                }
                try {
                    PackageInfo packageInfo = this.packageManager.getPackageInfo((String) splitResourceName.first, 64);
                    String defaultLocale = SpannableUtils$IdentifierSpan.getDefaultLocale();
                    int i = packageInfo.versionCode;
                    long currentTimeMillis = System.currentTimeMillis();
                    Signature[] signatureArr = packageInfo.signatures;
                    try {
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                        for (Signature signature : signatureArr) {
                            messageDigest.update(signature.toByteArray());
                        }
                        str3 = StringBuilderUtils.bytesToHexString(messageDigest.digest());
                    } catch (NoSuchAlgorithmException unused) {
                        LogUtils.w("CustomLabelManager", "Unable to create SHA-1 MessageDigest", new Object[0]);
                        str3 = "";
                    }
                    new LabelTask(new LabelAddRequest(this.client$ar$class_merging$ae701839_0, new Label((String) splitResourceName.first, str3, (String) splitResourceName.second, trim, defaultLocale, i, "", currentTimeMillis), this.labelsInPackageChangeListener$ar$class_merging$ar$class_merging$ar$class_merging), this.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
                    return;
                } catch (PackageManager.NameNotFoundException unused2) {
                    LogUtils.w("CustomLabelManager", "Attempted to add a label for an unknown package.", new Object[0]);
                    return;
                }
            }
            throw new IllegalArgumentException("Attempted to add a label with an empty userLabel value");
        }
        throw new IllegalArgumentException("Attempted to add a label with a null userLabel value");
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final boolean canAddLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String viewIdResourceName;
        Pair splitResourceName;
        if (accessibilityNodeInfoCompat != null && (viewIdResourceName = accessibilityNodeInfoCompat.getViewIdResourceName()) != null && (splitResourceName = splitResourceName(viewIdResourceName)) != null && splitResourceName.first != null) {
            try {
                this.context.getPackageManager().getPackageInfo((String) splitResourceName.first, 0);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager, com.google.android.accessibility.utils.labeling.LabelManager
    public final Label getLabelForViewIdFromCache(String str) {
        Pair splitResourceName;
        String str2;
        String str3;
        if (!isInitialized() || (splitResourceName = splitResourceName(str)) == null) {
            return null;
        }
        Label label = new Label((String) splitResourceName.first, null, (String) splitResourceName.second, null, null, 0, null, 0L);
        Label label2 = (Label) this.labelCache.ceiling(label);
        if (label2 == null || (str2 = label.mViewName) == null || !str2.equals(label2.mViewName) || (str3 = label.mPackageName) == null || !str3.equals(label2.mPackageName)) {
            return null;
        }
        return label2;
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final void getLabelsFromDatabase(LabelsFetchRequest.OnLabelsFetchedListener onLabelsFetchedListener) {
        if (!isInitialized()) {
            return;
        }
        new LabelTask(new LabelsFetchRequest(this.client$ar$class_merging$ae701839_0, onLabelsFetchedListener), this.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final boolean isInitialized() {
        checkUiThread$ar$ds();
        return this.client$ar$class_merging$ae701839_0.isInitialized();
    }

    public final void maybeShutdownClient() {
        checkUiThread$ar$ds();
        if (this.runningTasks == 0 && this.shouldShutdownClient) {
            LogUtils.v("CustomLabelManager", "All tasks completed and shutdown requested.  Releasing database.", new Object[0]);
            this.client$ar$class_merging$ae701839_0.shutdown();
        }
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final void onResume(Context context) {
        EditorInfoCompat.registerReceiver$ar$ds(context, this.packageRemovalReceiver, PackageRemovalReceiver.INTENT_FILTER, 2);
        if (!isInitialized()) {
            return;
        }
        new LabelTask(new DataConsistencyCheckRequest(this.client$ar$class_merging$ae701839_0, this.context, this.dataConsistencyCheckCallback$ar$class_merging$ar$class_merging$ar$class_merging), this.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final void onSuspend(Context context) {
        context.unregisterReceiver(this.packageRemovalReceiver);
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final void onUnlockedBoot() {
        if (this.labelCache.isEmpty()) {
            refreshCache();
        }
    }

    public final void refreshCache() {
        getLabelsFromDatabase(new AnonymousClass7(this, 0));
    }

    public final void removeLabel(Label... labelArr) {
        if (!isInitialized()) {
            return;
        }
        new LabelTask(new LabelRemoveRequest(this.client$ar$class_merging$ae701839_0, labelArr[0], this.labelsInPackageChangeListener$ar$class_merging$ar$class_merging$ar$class_merging), this.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
    }

    public final void sendCacheRefreshIntent(String... strArr) {
        Intent intent = new Intent("com.google.android.marvin.talkback.labeling.REFRESH_LABEL_CACHE");
        intent.putExtra("EXTRA_STRING_ARRAY_PACKAGES", strArr);
        this.context.sendBroadcast(intent);
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final boolean setLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str) {
        String viewIdResourceName;
        if (accessibilityNodeInfoCompat == null || (viewIdResourceName = accessibilityNodeInfoCompat.getViewIdResourceName()) == null) {
            return false;
        }
        Label labelForViewIdFromCache = getLabelForViewIdFromCache(viewIdResourceName);
        if (labelForViewIdFromCache == null) {
            addLabel(viewIdResourceName, str);
        } else if (str == null) {
            removeLabel(labelForViewIdFromCache);
        } else {
            labelForViewIdFromCache.mText = str;
            labelForViewIdFromCache.mTimestampMillis = System.currentTimeMillis();
            updateLabel(labelForViewIdFromCache);
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final void shutdown() {
        LogUtils.v("CustomLabelManager", "Shutdown requested.", new Object[0]);
        this.context.unregisterReceiver(this.refreshReceiver);
        this.context.unregisterReceiver(this.localeChangedReceiver);
        this.shouldShutdownClient = true;
        maybeShutdownClient();
    }

    @Override // com.google.android.accessibility.utils.labeling.LabelManager
    public final LabelManager.State stateReader() {
        return this.stateReader;
    }

    public final void updateLabel(Label... labelArr) {
        if (isInitialized()) {
            Label label = labelArr[0];
            if (label != null) {
                if (!TextUtils.isEmpty(label.mText)) {
                    new LabelTask(new LabelUpdateRequest(this.client$ar$class_merging$ae701839_0, label, this.labelsInPackageChangeListener$ar$class_merging$ar$class_merging$ar$class_merging), this.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
                    return;
                }
                throw new IllegalArgumentException("Attempted to update a label with an empty text value");
            }
            throw new IllegalArgumentException("Attempted to update a null label.");
        }
    }

    public CustomLabelManager(Context context, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.stateReader = new StoragelessLabelManager.State(this, 1);
        this.labelCache = new TreeSet(new GapWorker.AnonymousClass1(8));
        CacheRefreshReceiver cacheRefreshReceiver = new CacheRefreshReceiver();
        this.refreshReceiver = cacheRefreshReceiver;
        LocaleChangedReceiver localeChangedReceiver = new LocaleChangedReceiver();
        this.localeChangedReceiver = localeChangedReceiver;
        this.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.dataConsistencyCheckCallback$ar$class_merging$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.labelsInPackageChangeListener$ar$class_merging$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.context = context;
        this.labelChangeListener$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.packageManager = context.getPackageManager();
        this.client$ar$class_merging$ae701839_0 = new ExecutionList.RunnableExecutorPair(context);
        EditorInfoCompat.registerReceiver$ar$ds(context, cacheRefreshReceiver, REFRESH_INTENT_FILTER, 2);
        EditorInfoCompat.registerReceiver$ar$ds(context, localeChangedReceiver, new IntentFilter("android.intent.action.LOCALE_CHANGED"), 2);
        this.packageRemovalReceiver = new PackageRemovalReceiver();
        refreshCache();
    }
}
