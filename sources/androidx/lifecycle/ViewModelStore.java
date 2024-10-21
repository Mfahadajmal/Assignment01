package androidx.lifecycle;

import android.content.ClipDescription;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DrawableUtils;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.widget.AbsSeekBar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import androidx.core.app.NotificationCompatBuilder$Api31Impl;
import androidx.core.os.UserManagerCompat$Api24Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.poolingcontainer.PoolingContainerListener;
import androidx.emoji2.text.EmojiCompat;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import androidx.room.driver.SupportSQLiteStatement;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.transition.ViewUtilsApi22;
import androidx.work.Data;
import androidx.work.Data_Kt;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.constraints.NetworkRequestConstraintController;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.constraints.trackers.BatteryNotLowTracker;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1;
import com.google.android.accessibility.braille.brailledisplay.controller.BdController;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__DistinctKt;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewModelStore {
    public final Object ViewModelStore$ar$map;

    public ViewModelStore(final TextView textView) {
        this.ViewModelStore$ar$map = new UserManagerCompat$Api24Impl(textView) { // from class: androidx.emoji2.viewsintegration.EmojiTextViewHelper$SkippingHelper19
            private final EmojiTextViewHelper$HelperInternal19 mHelperDelegate;

            {
                this.mHelperDelegate = new EmojiTextViewHelper$HelperInternal19(textView);
            }

            private static final boolean skipBecauseEmojiCompatNotInitialized$ar$ds() {
                EmojiCompat.isConfigured();
                return true;
            }

            @Override // androidx.core.os.UserManagerCompat$Api24Impl
            public final InputFilter[] getFilters(InputFilter[] inputFilterArr) {
                if (skipBecauseEmojiCompatNotInitialized$ar$ds()) {
                    return inputFilterArr;
                }
                return this.mHelperDelegate.getFilters(inputFilterArr);
            }

            @Override // androidx.core.os.UserManagerCompat$Api24Impl
            public final boolean isEnabled() {
                return this.mHelperDelegate.mEnabled;
            }

            @Override // androidx.core.os.UserManagerCompat$Api24Impl
            public final void setAllCaps(boolean z) {
                if (skipBecauseEmojiCompatNotInitialized$ar$ds()) {
                    return;
                }
                this.mHelperDelegate.setAllCaps(z);
            }

            @Override // androidx.core.os.UserManagerCompat$Api24Impl
            public final void setEnabled(boolean z) {
                if (skipBecauseEmojiCompatNotInitialized$ar$ds()) {
                    this.mHelperDelegate.mEnabled = z;
                } else {
                    this.mHelperDelegate.setEnabled(z);
                }
            }

            @Override // androidx.core.os.UserManagerCompat$Api24Impl
            public final TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod) {
                if (skipBecauseEmojiCompatNotInitialized$ar$ds()) {
                    return transformationMethod;
                }
                return this.mHelperDelegate.wrapTransformationMethod(transformationMethod);
            }
        };
    }

    private final CharSequence getNodeText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        CharSequence nodeText = AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat);
        if (!TextUtils.isEmpty(nodeText)) {
            return nodeText;
        }
        CharSequence hintText = AccessibilityNodeInfoUtils.getHintText(accessibilityNodeInfoCompat);
        if (!TextUtils.isEmpty(hintText)) {
            return hintText;
        }
        return ((FloatingActionButton.ShadowDelegateImpl) this.ViewModelStore$ar$map).getCustomLabelText(accessibilityNodeInfoCompat);
    }

    private static final boolean matchesAny$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Class... clsArr) {
        for (Class cls : clsArr) {
            if (AccessibilityNodeInfoUtils.nodeMatchesClassByType(accessibilityNodeInfoCompat, cls)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    public final void addMigration(Migration migration) {
        migration.getClass();
        int i = migration.startVersion;
        ?? r1 = this.ViewModelStore$ar$map;
        Integer valueOf = Integer.valueOf(i);
        Object obj = r1.get(valueOf);
        if (obj == null) {
            obj = new TreeMap();
            r1.put(valueOf, obj);
        }
        TreeMap treeMap = (TreeMap) obj;
        Integer valueOf2 = Integer.valueOf(migration.endVersion);
        if (treeMap.containsKey(valueOf2)) {
            Log.w("ROOM", "Overriding migration " + treeMap.get(valueOf2) + " with " + migration);
        }
        treeMap.put(valueOf2, migration);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.lang.Iterable] */
    public final boolean areAllConstraintsMet(WorkSpec workSpec) {
        workSpec.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.ViewModelStore$ar$map) {
            if (((ConstraintController) obj).isCurrentlyConstrained(workSpec)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            Logger.get$ar$ds$16341a92_0();
            long j = WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs;
            String str = workSpec.id;
            OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(arrayList, null, null, null, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$226cffef_0, 31);
        }
        return arrayList.isEmpty();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    public final Data build() {
        Data data = new Data((Map) this.ViewModelStore$ar$map);
        ViewUtilsApi22.Api29Impl.toByteArrayInternalV1$ar$ds(data);
        return data;
    }

    public final void cancel(Runnable runnable) {
        ((Handler) this.ViewModelStore$ar$map).removeCallbacks(runnable);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Map, java.lang.Object] */
    public final void clear() {
        Iterator it = this.ViewModelStore$ar$map.values().iterator();
        while (it.hasNext()) {
            ((ViewModel) it.next()).clear$lifecycle_viewmodel_release();
        }
        this.ViewModelStore$ar$map.clear();
    }

    public final CharSequence format(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String string;
        AccessibilityNodeInfoCompat labeledBy;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        CharSequence nodeText = getNodeText(accessibilityNodeInfoCompat);
        if (TextUtils.isEmpty(nodeText) && (labeledBy = accessibilityNodeInfoCompat.getLabeledBy()) != null) {
            nodeText = getNodeText(labeledBy);
        }
        if (TextUtils.isEmpty(nodeText) && ((TalkBackLabelManager) ((BdController) ((FloatingActionButton.ShadowDelegateImpl) this.ViewModelStore$ar$map).FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$producerIndex).stateReader().needsLabel(accessibilityNodeInfoCompat)) {
            if (matchesAny$ar$ds(accessibilityNodeInfoCompat, Button.class, ImageButton.class)) {
                nodeText = context.getString(R.string.type_unlabeled_button);
            } else if (matchesAny$ar$ds(accessibilityNodeInfoCompat, QuickContactBadge.class)) {
                nodeText = context.getString(R.string.type_unlabeled_quickcontact);
            } else if (matchesAny$ar$ds(accessibilityNodeInfoCompat, ImageView.class)) {
                nodeText = context.getString(R.string.type_unlabeled_image);
            } else if (matchesAny$ar$ds(accessibilityNodeInfoCompat, EditText.class)) {
                nodeText = context.getString(R.string.type_unlabeled_edittext);
            } else if (matchesAny$ar$ds(accessibilityNodeInfoCompat, AbsSeekBar.class)) {
                nodeText = context.getString(R.string.type_unlabeled_seekbar);
            } else {
                nodeText = context.getString(R.string.type_unlabeled);
            }
        }
        if (nodeText == null) {
            nodeText = "";
        }
        DrawableUtils.Api29Impl.appendWithSpaces$ar$ds(spannableStringBuilder, AppCompatDelegate.Api33Impl.filterNonPrintCharacter(nodeText));
        if (accessibilityNodeInfoCompat.isCheckable() || accessibilityNodeInfoCompat.isChecked()) {
            if (accessibilityNodeInfoCompat.isChecked()) {
                string = context.getString(R.string.checkmark_checked);
            } else {
                string = context.getString(R.string.checkmark_not_checked);
            }
            DrawableUtils.Api29Impl.appendWithSpaces$ar$ds(spannableStringBuilder, string);
        }
        return spannableStringBuilder;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    public final ViewModel get(String str) {
        str.getClass();
        return (ViewModel) this.ViewModelStore$ar$map.get(str);
    }

    public final Uri getContentUri() {
        Uri contentUri;
        contentUri = ViewModelStore$$ExternalSyntheticApiModelOutline0.m(this.ViewModelStore$ar$map).getContentUri();
        return contentUri;
    }

    public final ClipDescription getDescription() {
        ClipDescription description;
        description = ViewModelStore$$ExternalSyntheticApiModelOutline0.m(this.ViewModelStore$ar$map).getDescription();
        return description;
    }

    public final Object getInputContentInfo() {
        return this.ViewModelStore$ar$map;
    }

    public final Uri getLinkUri() {
        Uri linkUri;
        linkUri = ViewModelStore$$ExternalSyntheticApiModelOutline0.m(this.ViewModelStore$ar$map).getLinkUri();
        return linkUri;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    public final Set keys() {
        return new HashSet(this.ViewModelStore$ar$map.keySet());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
    public final void onRelease() {
        for (int lastIndex = OnDeviceLanguageIdentificationLogEvent.getLastIndex(this.ViewModelStore$ar$map); lastIndex >= 0; lastIndex--) {
            ((PoolingContainerListener) ((ArrayList) this.ViewModelStore$ar$map).get(lastIndex)).onRelease();
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [androidx.sqlite.db.SupportSQLiteDatabase, java.lang.Object] */
    /* renamed from: prepare, reason: merged with bridge method [inline-methods] */
    public final SupportSQLiteStatement m32prepare(String str) {
        String obj = OnDeviceStainRemovalLogEvent.trim(str).toString();
        int length = obj.length();
        ?? r2 = this.ViewModelStore$ar$map;
        if (length >= 3) {
            String substring = obj.substring(0, 3);
            substring.getClass();
            String upperCase = substring.toUpperCase(Locale.ROOT);
            upperCase.getClass();
            int hashCode = upperCase.hashCode();
            if (hashCode == 79487 ? upperCase.equals("PRA") : !(hashCode == 81978 ? !upperCase.equals("SEL") : !(hashCode == 85954 && upperCase.equals("WIT")))) {
                return new SupportSQLiteStatement.SupportAndroidSQLiteStatement(r2, str);
            }
        }
        return new SupportSQLiteStatement.SupportOtherAndroidSQLiteStatement(r2, str);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Map, java.lang.Object] */
    public final void putAll$ar$ds(Map map) {
        Object[] objArr;
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            str.getClass();
            ?? r2 = this.ViewModelStore$ar$map;
            if (value == null) {
                value = null;
            } else {
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(value.getClass());
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Byte.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Byte[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String[].class))) {
                    int i = 0;
                    if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(boolean[].class))) {
                        boolean[] zArr = (boolean[]) value;
                        int length = zArr.length;
                        String str2 = Data_Kt.TAG;
                        objArr = new Boolean[length];
                        while (i < length) {
                            objArr[i] = Boolean.valueOf(zArr[i]);
                            i++;
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(byte[].class))) {
                        byte[] bArr = (byte[]) value;
                        int length2 = bArr.length;
                        String str3 = Data_Kt.TAG;
                        objArr = new Byte[length2];
                        while (i < length2) {
                            objArr[i] = Byte.valueOf(bArr[i]);
                            i++;
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(int[].class))) {
                        int[] iArr = (int[]) value;
                        int length3 = iArr.length;
                        String str4 = Data_Kt.TAG;
                        objArr = new Integer[length3];
                        while (i < length3) {
                            objArr[i] = Integer.valueOf(iArr[i]);
                            i++;
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(long[].class))) {
                        long[] jArr = (long[]) value;
                        int length4 = jArr.length;
                        String str5 = Data_Kt.TAG;
                        objArr = new Long[length4];
                        while (i < length4) {
                            objArr[i] = Long.valueOf(jArr[i]);
                            i++;
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(float[].class))) {
                        float[] fArr = (float[]) value;
                        int length5 = fArr.length;
                        String str6 = Data_Kt.TAG;
                        objArr = new Float[length5];
                        while (i < length5) {
                            objArr[i] = Float.valueOf(fArr[i]);
                            i++;
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(double[].class))) {
                        double[] dArr = (double[]) value;
                        int length6 = dArr.length;
                        String str7 = Data_Kt.TAG;
                        objArr = new Double[length6];
                        while (i < length6) {
                            objArr[i] = Double.valueOf(dArr[i]);
                            i++;
                        }
                    } else {
                        throw new IllegalArgumentException("Key " + str + " has invalid type " + orCreateKotlinClass);
                    }
                    value = objArr;
                }
            }
            r2.put(str, value);
        }
    }

    public final void requestPermission() {
        ViewModelStore$$ExternalSyntheticApiModelOutline0.m(this.ViewModelStore$ar$map).requestPermission();
    }

    public final void scheduleWithDelay(long j, Runnable runnable) {
        ((Handler) this.ViewModelStore$ar$map).postDelayed(runnable, j);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.lang.Iterable] */
    public final Flow track(WorkSpec workSpec) {
        workSpec.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.ViewModelStore$ar$map) {
            if (((ConstraintController) obj).hasConstraint(workSpec)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ConstraintController) it.next()).track(workSpec.constraints));
        }
        return FlowKt__DistinctKt.distinctUntilChanged(new ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1((Flow[]) OnDeviceLanguageIdentificationLogEvent.toList(arrayList2).toArray(new Flow[0]), 1));
    }

    public ViewModelStore(Object obj) {
        this.ViewModelStore$ar$map = obj;
    }

    public ViewModelStore(Object obj, byte[] bArr) {
        this.ViewModelStore$ar$map = obj;
    }

    public ViewModelStore() {
        this.ViewModelStore$ar$map = new LinkedHashMap();
    }

    public ViewModelStore(char[] cArr) {
        this.ViewModelStore$ar$map = new ArrayList();
    }

    public ViewModelStore(byte[] bArr) {
        this.ViewModelStore$ar$map = new LinkedHashMap();
    }

    public ViewModelStore(SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        supportSQLiteOpenHelper.getClass();
        this.ViewModelStore$ar$map = supportSQLiteOpenHelper;
    }

    public ViewModelStore(byte[] bArr, byte[] bArr2) {
        this.ViewModelStore$ar$map = new LinkedHashMap();
    }

    public ViewModelStore(short[] sArr) {
        this.ViewModelStore$ar$map = NotificationCompatBuilder$Api31Impl.createAsync(Looper.getMainLooper());
    }

    public ViewModelStore(WorkQueue workQueue) {
        NetworkRequestConstraintController networkRequestConstraintController;
        ConstraintController[] constraintControllerArr = new ConstraintController[8];
        constraintControllerArr[0] = new BatteryChargingController((ConstraintTracker) workQueue.WorkQueue$ar$buffer);
        constraintControllerArr[1] = new BatteryNotLowController((BatteryNotLowTracker) workQueue.WorkQueue$ar$blockingTasksInBuffer);
        constraintControllerArr[2] = new StorageNotLowController((ConstraintTracker) workQueue.WorkQueue$ar$lastScheduledTask);
        ConstraintTracker constraintTracker = (ConstraintTracker) workQueue.WorkQueue$ar$consumerIndex;
        constraintControllerArr[3] = new NetworkConnectedController(constraintTracker);
        constraintControllerArr[4] = new NetworkUnmeteredController(constraintTracker);
        constraintControllerArr[5] = new NetworkNotRoamingController((ConstraintTracker) workQueue.WorkQueue$ar$consumerIndex);
        constraintControllerArr[6] = new NetworkMeteredController((ConstraintTracker) workQueue.WorkQueue$ar$consumerIndex);
        if (Build.VERSION.SDK_INT >= 28) {
            Object obj = workQueue.WorkQueue$ar$producerIndex;
            long j = WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs;
            Object systemService = ((Context) obj).getSystemService("connectivity");
            systemService.getClass();
            networkRequestConstraintController = new NetworkRequestConstraintController((ConnectivityManager) systemService, WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs);
        } else {
            networkRequestConstraintController = null;
        }
        constraintControllerArr[7] = networkRequestConstraintController;
        this.ViewModelStore$ar$map = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.filterNotNull(constraintControllerArr);
    }

    public ViewModelStore(WorkDatabase workDatabase, byte[] bArr) {
        workDatabase.getClass();
        this.ViewModelStore$ar$map = workDatabase;
    }
}
