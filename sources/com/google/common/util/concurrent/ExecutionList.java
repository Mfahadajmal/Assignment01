package com.google.common.util.concurrent;

import _COROUTINE._BOUNDARY;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import androidx.constraintlayout.core.SolverVariable;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ServiceLifecycleDispatcher$DispatchRunnable;
import com.google.android.accessibility.talkback.actor.TalkBackUIActor$Type;
import com.google.android.accessibility.talkback.quickmenu.QuickMenuOverlay;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.labeling.LabelManager;
import com.google.android.accessibility.utils.labeling.LabelsTable;
import com.google.android.gms.clearcut.internal.LogErrorQueue;
import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screenunderstanding.VisualAnnotationPipeline;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.firebase.encoders.proto.AtProtobuf;
import j$.util.DesugarCollections;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutionList {
    private static final LazyLogger log = new LazyLogger(ExecutionList.class);
    public boolean executed;
    public RunnableExecutorPair runnables;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RunnableExecutorPair {
        public final Object ExecutionList$RunnableExecutorPair$ar$executor;
        public Object ExecutionList$RunnableExecutorPair$ar$next;
        public final Object ExecutionList$RunnableExecutorPair$ar$runnable;

        public RunnableExecutorPair() {
            this.ExecutionList$RunnableExecutorPair$ar$executor = new AtProtobuf(null);
            this.ExecutionList$RunnableExecutorPair$ar$runnable = new AtProtobuf(null);
            this.ExecutionList$RunnableExecutorPair$ar$next = new SolverVariable[32];
        }

        public static ContentValues buildContentValuesForLabel(Label label) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("packageName", label.mPackageName);
            contentValues.put("packageSignature", label.mPackageSignature);
            contentValues.put("viewName", label.mViewName);
            contentValues.put("text", label.mText);
            contentValues.put("locale", label.mLocale);
            contentValues.put("packageVersion", Integer.valueOf(label.mPackageVersion));
            contentValues.put("screenshotPath", label.mScreenshotPath);
            contentValues.put("timestamp", Long.valueOf(label.mTimestampMillis));
            return contentValues;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v1, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v3, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v0, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v1, types: [java.util.Map, java.lang.Object] */
        private final void createOverlays(Context context) {
            this.ExecutionList$RunnableExecutorPair$ar$executor.clear();
            this.ExecutionList$RunnableExecutorPair$ar$executor.put(TalkBackUIActor$Type.SELECTOR_MENU_ITEM_OVERLAY_MULTI_FINGER, new QuickMenuOverlay(context, R.layout.quick_menu_item_overlay));
            this.ExecutionList$RunnableExecutorPair$ar$executor.put(TalkBackUIActor$Type.SELECTOR_MENU_ITEM_OVERLAY_SINGLE_FINGER, new QuickMenuOverlay(context, R.layout.quick_menu_item_overlay_without_multifinger_gesture));
            this.ExecutionList$RunnableExecutorPair$ar$executor.put(TalkBackUIActor$Type.SELECTOR_ITEM_ACTION_OVERLAY, new QuickMenuOverlay(context, R.layout.quick_menu_item_action_overlay));
            this.ExecutionList$RunnableExecutorPair$ar$executor.put(TalkBackUIActor$Type.GESTURE_ACTION_OVERLAY, new QuickMenuOverlay(context, R.layout.quick_menu_item_action_overlay));
        }

        public static final Label getLabelFromCursorAtCurrentPosition$ar$ds(Cursor cursor) {
            if (!cursor.isClosed() && !cursor.isAfterLast()) {
                return new Label(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getLong(8));
            }
            LogUtils.w("LabelProviderClient", "Failed to get label from cursor.", new Object[0]);
            return null;
        }

        private static final void logResult$ar$ds(Iterable iterable) {
            if (LogUtils.minLogLevel >= 2) {
                StringBuilder sb = new StringBuilder("Query result: [");
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    Label label = (Label) it.next();
                    sb.append("\n  ");
                    sb.append(label);
                }
                sb.append("].");
                LogUtils.v("LabelProviderClient", sb.toString(), new Object[0]);
            }
        }

        public static RunnableExecutorPair obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging(Context context, AttributeSet attributeSet, int[] iArr) {
            return new RunnableExecutorPair(context, context.obtainStyledAttributes(attributeSet, iArr));
        }

        public static RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging(Context context, int i, int[] iArr) {
            return new RunnableExecutorPair(context, context.obtainStyledAttributes(i, iArr));
        }

        public static RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
            return new RunnableExecutorPair(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
        public final void addChild$ar$class_merging(RunnableExecutorPair runnableExecutorPair) {
            this.ExecutionList$RunnableExecutorPair$ar$executor.add(runnableExecutorPair);
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
        public final void cancelExistingRequestIfNeeded() {
            ?? r0 = this.ExecutionList$RunnableExecutorPair$ar$next;
            if (r0 != 0 && !r0.isDone()) {
                this.ExecutionList$RunnableExecutorPair$ar$next.cancel(false);
                this.ExecutionList$RunnableExecutorPair$ar$next = null;
            }
        }

        public final boolean checkClient() {
            if (this.ExecutionList$RunnableExecutorPair$ar$next == null) {
                LogUtils.w("LabelProviderClient", "Aborting operation: the client failed to initialize or already shut down.", new Object[0]);
                return false;
            }
            return true;
        }

        public final void deleteLabel$ar$ds(String str, String str2, String str3, int i) {
            LogUtils.d("LabelProviderClient", "Deleting label: package name: %s, view name: %s, locale: %s, package version: %d, source type: %d", str, str2, str3, Integer.valueOf(i), 2);
            if (!checkClient()) {
                return;
            }
            try {
                ((ContentProviderClient) this.ExecutionList$RunnableExecutorPair$ar$next).delete((Uri) this.ExecutionList$RunnableExecutorPair$ar$runnable, "packageName = ? AND viewName = ? AND locale LIKE ? AND packageVersion <= ? AND sourceType = ?", new String[]{str, str2, str3 + "%", Integer.toString(i), Integer.toString(2)});
            } catch (RemoteException e) {
                LogUtils.e("LabelProviderClient", "RemoteException caught!", new Object[0]);
                LogUtils.d("LabelProviderClient", e.toString(), new Object[0]);
            }
        }

        public final boolean deleteLabels(int i) {
            LogUtils.d("LabelProviderClient", "Deleting backup labels", new Object[0]);
            if (!checkClient()) {
                return false;
            }
            try {
                String _BOUNDARY$ar$MethodOutlining$dc56d17a_3 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "sourceType = ");
                if (((ContentProviderClient) this.ExecutionList$RunnableExecutorPair$ar$next).delete((Uri) this.ExecutionList$RunnableExecutorPair$ar$runnable, _BOUNDARY$ar$MethodOutlining$dc56d17a_3, null) <= 0) {
                    return false;
                }
                return true;
            } catch (RemoteException e) {
                LogUtils.e("LabelProviderClient", "RemoteException caught!", new Object[0]);
                LogUtils.d("LabelProviderClient", e.toString(), new Object[0]);
                return false;
            }
        }

        public final boolean getBoolean(int i, boolean z) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getBoolean(i, z);
        }

        public final int getColor$ar$ds(int i) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getColor(i, 0);
        }

        public final ColorStateList getColorStateList(int i) {
            int resourceId;
            ColorStateList colorStateList;
            if (((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).hasValue(i) && (resourceId = ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getResourceId(i, 0)) != 0 && (colorStateList = EditorInfoCompat.getColorStateList((Context) this.ExecutionList$RunnableExecutorPair$ar$executor, resourceId)) != null) {
                return colorStateList;
            }
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getColorStateList(i);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
        public final List getCurrentLabels() {
            RemoteException e;
            Cursor cursor;
            List unmodifiableList;
            LogUtils.d("LabelProviderClient", "Querying all labels.", new Object[0]);
            ?? checkClient = checkClient();
            Cursor cursor2 = null;
            try {
                if (checkClient == 0) {
                    return null;
                }
                try {
                    Object obj = this.ExecutionList$RunnableExecutorPair$ar$next;
                    cursor = ((ContentProviderClient) obj).query((Uri) this.ExecutionList$RunnableExecutorPair$ar$runnable, LabelsTable.ALL_COLUMNS, "sourceType != 2", null, null);
                } catch (RemoteException e2) {
                    e = e2;
                    cursor = null;
                } catch (Throwable th) {
                    th = th;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
                try {
                    if (cursor == null) {
                        unmodifiableList = Collections.emptyList();
                    } else {
                        ArrayList arrayList = new ArrayList();
                        while (cursor.moveToNext()) {
                            Label labelFromCursorAtCurrentPosition$ar$ds = getLabelFromCursorAtCurrentPosition$ar$ds(cursor);
                            if (labelFromCursorAtCurrentPosition$ar$ds != null) {
                                arrayList.add(labelFromCursorAtCurrentPosition$ar$ds);
                            }
                        }
                        logResult$ar$ds(arrayList);
                        unmodifiableList = DesugarCollections.unmodifiableList(arrayList);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return unmodifiableList;
                } catch (RemoteException e3) {
                    e = e3;
                    LogUtils.e("LabelProviderClient", "RemoteException caught!", new Object[0]);
                    LogUtils.d("LabelProviderClient", e.toString(), new Object[0]);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor2 = checkClient;
            }
        }

        public final File getDataPartition() {
            Object obj;
            File dataDir;
            synchronized (this.ExecutionList$RunnableExecutorPair$ar$runnable) {
                if (this.ExecutionList$RunnableExecutorPair$ar$next == null) {
                    dataDir = ((Context) this.ExecutionList$RunnableExecutorPair$ar$executor).getDataDir();
                    this.ExecutionList$RunnableExecutorPair$ar$next = dataDir;
                }
                obj = this.ExecutionList$RunnableExecutorPair$ar$next;
            }
            return (File) obj;
        }

        public final float getDimension$ar$ds(int i) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getDimension(i, -1.0f);
        }

        public final int getDimensionPixelOffset(int i, int i2) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getDimensionPixelOffset(i, i2);
        }

        public final int getDimensionPixelSize(int i, int i2) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getDimensionPixelSize(i, i2);
        }

        public final Drawable getDrawable(int i) {
            int resourceId;
            if (((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).hasValue(i) && (resourceId = ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getResourceId(i, 0)) != 0) {
                return AppCompatDelegate.Api33Impl.getDrawable((Context) this.ExecutionList$RunnableExecutorPair$ar$executor, resourceId);
            }
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getDrawable(i);
        }

        public final Drawable getDrawableIfKnown(int i) {
            int resourceId;
            if (((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).hasValue(i) && (resourceId = ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getResourceId(i, 0)) != 0) {
                return AppCompatDrawableManager.get().getDrawable$ar$ds((Context) this.ExecutionList$RunnableExecutorPair$ar$executor, resourceId);
            }
            return null;
        }

        public final int getInt(int i, int i2) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getInt(i, i2);
        }

        public final int getInteger(int i, int i2) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getInteger(i, i2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:35:0x009f  */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
        /* JADX WARN: Type inference failed for: r1v4 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.Map getLabelsForPackage(java.lang.String r11, java.lang.String r12, int r13) {
            /*
                r10 = this;
                java.lang.Integer r0 = java.lang.Integer.valueOf(r13)
                r1 = 3
                java.lang.Object[] r1 = new java.lang.Object[r1]
                r2 = 0
                r1[r2] = r11
                r3 = 1
                r1[r3] = r12
                r3 = 2
                r1[r3] = r0
                java.lang.String r0 = "Querying labels for package: packageName=%s, locale=%s, maxPackageVersion=%s."
                java.lang.String r3 = "LabelProviderClient"
                com.google.android.libraries.accessibility.utils.log.LogUtils.d(r3, r0, r1)
                boolean r0 = r10.checkClient()
                r1 = 0
                if (r0 != 0) goto L1f
                return r1
            L1f:
                java.lang.String r12 = java.lang.String.valueOf(r12)
                java.lang.String r0 = "%"
                java.lang.String r12 = r12.concat(r0)
                java.lang.String r13 = java.lang.String.valueOf(r13)
                java.lang.String r0 = "2"
                java.lang.String[] r8 = new java.lang.String[]{r11, r12, r13, r0}
                java.lang.Object r11 = r10.ExecutionList$RunnableExecutorPair$ar$next     // Catch: java.lang.Throwable -> L7f android.os.RemoteException -> L82
                java.lang.Object r12 = r10.ExecutionList$RunnableExecutorPair$ar$runnable     // Catch: java.lang.Throwable -> L7f android.os.RemoteException -> L82
                java.lang.String[] r6 = com.google.android.accessibility.utils.labeling.LabelsTable.ALL_COLUMNS     // Catch: java.lang.Throwable -> L7f android.os.RemoteException -> L82
                java.lang.String r7 = "packageName = ? AND locale LIKE ? AND packageVersion <= ? AND sourceType != ? "
                r5 = r12
                android.net.Uri r5 = (android.net.Uri) r5     // Catch: java.lang.Throwable -> L7f android.os.RemoteException -> L82
                r4 = r11
                android.content.ContentProviderClient r4 = (android.content.ContentProviderClient) r4     // Catch: java.lang.Throwable -> L7f android.os.RemoteException -> L82
                r9 = 0
                android.database.Cursor r11 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L7f android.os.RemoteException -> L82
                if (r11 != 0) goto L4d
                java.util.Map r12 = java.util.Collections.emptyMap()     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                goto L77
            L4d:
                int r12 = r11.getCount()     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                int r12 = java.lang.Math.max(r12, r2)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                java.util.HashMap r13 = new java.util.HashMap     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                r13.<init>(r12)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
            L5a:
                boolean r12 = r11.moveToNext()     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                if (r12 == 0) goto L6c
                com.google.android.accessibility.utils.labeling.Label r12 = getLabelFromCursorAtCurrentPosition$ar$ds(r11)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                if (r12 == 0) goto L5a
                java.lang.String r0 = r12.mViewName     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                r13.put(r0, r12)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                goto L5a
            L6c:
                java.util.Collection r12 = r13.values()     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                logResult$ar$ds(r12)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
                java.util.Map r12 = j$.util.DesugarCollections.unmodifiableMap(r13)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L9b
            L77:
                if (r11 == 0) goto L7c
                r11.close()
            L7c:
                return r12
            L7d:
                r12 = move-exception
                goto L85
            L7f:
                r11 = move-exception
                r12 = r11
                goto L9d
            L82:
                r11 = move-exception
                r12 = r11
                r11 = r1
            L85:
                java.lang.String r13 = "RemoteException caught!"
                java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L9b
                com.google.android.libraries.accessibility.utils.log.LogUtils.e(r3, r13, r0)     // Catch: java.lang.Throwable -> L9b
                java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> L9b
                java.lang.Object[] r13 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L9b
                com.google.android.libraries.accessibility.utils.log.LogUtils.d(r3, r12, r13)     // Catch: java.lang.Throwable -> L9b
                if (r11 == 0) goto L9a
                r11.close()
            L9a:
                return r1
            L9b:
                r12 = move-exception
                r1 = r11
            L9d:
                if (r1 == 0) goto La2
                r1.close()
            La2:
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair.getLabelsForPackage(java.lang.String, java.lang.String, int):java.util.Map");
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.lang.Iterable] */
        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
        public final RunnableExecutorPair getLastNode$ar$class_merging() {
            RunnableExecutorPair runnableExecutorPair = this;
            while (!runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor.isEmpty()) {
                runnableExecutorPair = (RunnableExecutorPair) ContextDataProvider.getLast(runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor);
            }
            return runnableExecutorPair;
        }

        public final int getLayoutDimension(int i, int i2) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getLayoutDimension(i, i2);
        }

        public final int getResourceId(int i, int i2) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getResourceId(i, i2);
        }

        public final String getString(int i) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getString(i);
        }

        public final CharSequence getText(int i) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).getText(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v0, types: [com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair] */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object] */
        public final boolean hasDescendant$ar$class_merging(RunnableExecutorPair runnableExecutorPair) {
            HashSet hashSet = new HashSet();
            Object obj = this;
            while (obj != null) {
                RunnableExecutorPair runnableExecutorPair2 = (RunnableExecutorPair) obj;
                Object obj2 = runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$runnable;
                if (hashSet.contains(obj2)) {
                    LogUtils.w("WorkingTree", "Looped ancestors line", new Object[0]);
                    return false;
                }
                hashSet.add(obj2);
                obj = runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$next;
            }
            while (runnableExecutorPair != 0) {
                RunnableExecutorPair runnableExecutorPair3 = (RunnableExecutorPair) runnableExecutorPair;
                if (!((AccessibilityNodeInfoCompat) this.ExecutionList$RunnableExecutorPair$ar$runnable).equals(runnableExecutorPair3.ExecutionList$RunnableExecutorPair$ar$runnable)) {
                    runnableExecutorPair = runnableExecutorPair3.ExecutionList$RunnableExecutorPair$ar$next;
                } else {
                    return true;
                }
            }
            return false;
        }

        public final boolean hasValue(int i) {
            return ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).hasValue(i);
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final boolean hide(TalkBackUIActor$Type talkBackUIActor$Type) {
            QuickMenuOverlay quickMenuOverlay = (QuickMenuOverlay) this.ExecutionList$RunnableExecutorPair$ar$executor.get(talkBackUIActor$Type);
            if (quickMenuOverlay == null) {
                return false;
            }
            if (quickMenuOverlay.isShowing()) {
                quickMenuOverlay.hide();
                return true;
            }
            return true;
        }

        public final Label insertLabel(Label label, int i) {
            LogUtils.d("LabelProviderClient", "Inserting label: %s.", label);
            if (label != null) {
                long j = label.mId;
                if (j != -1) {
                    LogUtils.w("LabelProviderClient", "Cannot insert label with existing ID (id=%d).", Long.valueOf(j));
                    return null;
                }
                if (checkClient()) {
                    ContentValues buildContentValuesForLabel = buildContentValuesForLabel(label);
                    buildContentValuesForLabel.put("sourceType", Integer.valueOf(i));
                    try {
                        Uri insert = ((ContentProviderClient) this.ExecutionList$RunnableExecutorPair$ar$next).insert((Uri) this.ExecutionList$RunnableExecutorPair$ar$runnable, buildContentValuesForLabel);
                        if (insert == null) {
                            LogUtils.w("LabelProviderClient", "Failed to insert label.", new Object[0]);
                            return null;
                        }
                        return new Label(label, Long.parseLong(insert.getLastPathSegment()));
                    } catch (RemoteException e) {
                        LogUtils.e("LabelProviderClient", "RemoteException caught!", new Object[0]);
                        LogUtils.d("LabelProviderClient", e.toString(), new Object[0]);
                    }
                }
            }
            return null;
        }

        public final boolean isInitialized() {
            if (this.ExecutionList$RunnableExecutorPair$ar$next != null) {
                return true;
            }
            return false;
        }

        public final void onConfigurationChanged$ar$ds$53b703a9_0(Configuration configuration) {
            Object obj = this.ExecutionList$RunnableExecutorPair$ar$next;
            if (obj == null || ((Configuration) obj).densityDpi != configuration.densityDpi || ((Configuration) this.ExecutionList$RunnableExecutorPair$ar$next).getLayoutDirection() != configuration.getLayoutDirection() || (((Configuration) this.ExecutionList$RunnableExecutorPair$ar$next).uiMode & 48) != (configuration.uiMode & 48) || ((Configuration) this.ExecutionList$RunnableExecutorPair$ar$next).fontScale != configuration.fontScale || ((Configuration) this.ExecutionList$RunnableExecutorPair$ar$next).screenWidthDp != configuration.screenWidthDp || ((Configuration) this.ExecutionList$RunnableExecutorPair$ar$next).screenHeightDp != configuration.screenHeightDp || ((Configuration) this.ExecutionList$RunnableExecutorPair$ar$next).orientation != configuration.orientation) {
                createOverlays((Context) this.ExecutionList$RunnableExecutorPair$ar$runnable);
            }
            this.ExecutionList$RunnableExecutorPair$ar$next = new Configuration(configuration);
        }

        public final void postDispatchRunnable(final Lifecycle.Event event) {
            Object obj = this.ExecutionList$RunnableExecutorPair$ar$next;
            if (obj != null) {
                ((ServiceLifecycleDispatcher$DispatchRunnable) obj).run();
            }
            final LifecycleRegistry lifecycleRegistry = (LifecycleRegistry) this.ExecutionList$RunnableExecutorPair$ar$runnable;
            Runnable runnable = new Runnable(lifecycleRegistry, event) { // from class: androidx.lifecycle.ServiceLifecycleDispatcher$DispatchRunnable
                private final Lifecycle.Event event;
                private final LifecycleRegistry registry;
                private boolean wasExecuted;

                {
                    event.getClass();
                    this.registry = lifecycleRegistry;
                    this.event = event;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    if (!this.wasExecuted) {
                        this.registry.handleLifecycleEvent(this.event);
                        this.wasExecuted = true;
                    }
                }
            };
            this.ExecutionList$RunnableExecutorPair$ar$next = runnable;
            ((Handler) this.ExecutionList$RunnableExecutorPair$ar$executor).postAtFrontOfQueue(runnable);
        }

        public final void recycle() {
            ((TypedArray) this.ExecutionList$RunnableExecutorPair$ar$runnable).recycle();
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final boolean setSupported(TalkBackUIActor$Type talkBackUIActor$Type, boolean z) {
            QuickMenuOverlay quickMenuOverlay = (QuickMenuOverlay) this.ExecutionList$RunnableExecutorPair$ar$executor.get(talkBackUIActor$Type);
            if (quickMenuOverlay == null) {
                return false;
            }
            if (!z) {
                quickMenuOverlay.hide();
            }
            quickMenuOverlay.supported = z;
            return true;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
        public final boolean showQuickMenu(TalkBackUIActor$Type talkBackUIActor$Type, CharSequence charSequence, boolean z) {
            QuickMenuOverlay quickMenuOverlay = (QuickMenuOverlay) this.ExecutionList$RunnableExecutorPair$ar$executor.get(talkBackUIActor$Type);
            if (quickMenuOverlay == null) {
                return false;
            }
            for (QuickMenuOverlay quickMenuOverlay2 : this.ExecutionList$RunnableExecutorPair$ar$executor.values()) {
                if (quickMenuOverlay != quickMenuOverlay2) {
                    quickMenuOverlay2.hide();
                }
            }
            quickMenuOverlay.message = charSequence;
            quickMenuOverlay.show(z);
            return true;
        }

        public final void shutdown() {
            if (checkClient()) {
                ((ContentProviderClient) this.ExecutionList$RunnableExecutorPair$ar$next).release();
                this.ExecutionList$RunnableExecutorPair$ar$next = null;
            }
        }

        public final void updateSourceType$ar$ds(int i) {
            LogUtils.d("LabelProviderClient", "Updating source type", new Object[0]);
            if (!checkClient()) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("sourceType", (Integer) 0);
            try {
                String _BOUNDARY$ar$MethodOutlining$dc56d17a_3 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "sourceType=");
                ((ContentProviderClient) this.ExecutionList$RunnableExecutorPair$ar$next).update((Uri) this.ExecutionList$RunnableExecutorPair$ar$runnable, contentValues, _BOUNDARY$ar$MethodOutlining$dc56d17a_3, null);
            } catch (RemoteException e) {
                LogUtils.e("LabelProviderClient", "RemoteException caught!", new Object[0]);
                LogUtils.d("LabelProviderClient", e.toString(), new Object[0]);
            }
        }

        private RunnableExecutorPair(Context context, TypedArray typedArray) {
            this.ExecutionList$RunnableExecutorPair$ar$executor = context;
            this.ExecutionList$RunnableExecutorPair$ar$runnable = typedArray;
        }

        public RunnableExecutorPair(LabelManager labelManager, AppLifecycleMonitor appLifecycleMonitor) {
            this.ExecutionList$RunnableExecutorPair$ar$runnable = labelManager;
            this.ExecutionList$RunnableExecutorPair$ar$executor = appLifecycleMonitor;
        }

        public RunnableExecutorPair(Runnable runnable, Executor executor, RunnableExecutorPair runnableExecutorPair) {
            this.ExecutionList$RunnableExecutorPair$ar$runnable = runnable;
            this.ExecutionList$RunnableExecutorPair$ar$executor = executor;
            this.ExecutionList$RunnableExecutorPair$ar$next = runnableExecutorPair;
        }

        public RunnableExecutorPair(byte[] bArr) {
            this.ExecutionList$RunnableExecutorPair$ar$executor = new LogErrorQueue(null);
            this.ExecutionList$RunnableExecutorPair$ar$runnable = new LogErrorQueue(null);
            this.ExecutionList$RunnableExecutorPair$ar$next = new android.support.constraint.solver.SolverVariable[32];
        }

        public RunnableExecutorPair(Context context) {
            Uri build = new Uri.Builder().scheme("content").authority("com.google.android.marvin.talkback.providers.LabelProvider").path("labels").build();
            this.ExecutionList$RunnableExecutorPair$ar$runnable = build;
            this.ExecutionList$RunnableExecutorPair$ar$executor = new Uri.Builder().scheme("content").authority("com.google.android.marvin.talkback.providers.LabelProvider").path("packageSummary").build();
            try {
                Uri uri = build;
                this.ExecutionList$RunnableExecutorPair$ar$next = context.getContentResolver().acquireContentProviderClient(build);
            } catch (SecurityException e) {
                LogUtils.e("LabelProviderClient", "Failed to open LabelProvider: %s", e);
                this.ExecutionList$RunnableExecutorPair$ar$next = null;
            }
            if (this.ExecutionList$RunnableExecutorPair$ar$next == null) {
                LogUtils.w("LabelProviderClient", "Failed to acquire content provider client.", new Object[0]);
            }
        }

        public RunnableExecutorPair(LifecycleOwner lifecycleOwner) {
            this.ExecutionList$RunnableExecutorPair$ar$runnable = new LifecycleRegistry(lifecycleOwner);
            this.ExecutionList$RunnableExecutorPair$ar$executor = new Handler();
        }

        public RunnableExecutorPair(Context context, char[] cArr) {
            this.ExecutionList$RunnableExecutorPair$ar$executor = new EnumMap(TalkBackUIActor$Type.class);
            this.ExecutionList$RunnableExecutorPair$ar$runnable = context;
            createOverlays(context);
        }

        public RunnableExecutorPair(Context context, byte[] bArr, char[] cArr) {
            AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((short[]) null, (byte[]) null);
            String packageName = context.getPackageName();
            if (packageName != null) {
                ((Bundle) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).putString("consumerPkg", packageName);
            }
            InternalUsageReportingClient internalUsageReportingClient = new InternalUsageReportingClient(context, appLifecycleMonitor.build());
            this.ExecutionList$RunnableExecutorPair$ar$executor = internalUsageReportingClient;
            this.ExecutionList$RunnableExecutorPair$ar$runnable = internalUsageReportingClient.getSpatulaHeader();
        }

        public RunnableExecutorPair(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, RunnableExecutorPair runnableExecutorPair) {
            this.ExecutionList$RunnableExecutorPair$ar$executor = new ArrayList();
            this.ExecutionList$RunnableExecutorPair$ar$runnable = accessibilityNodeInfoCompat;
            this.ExecutionList$RunnableExecutorPair$ar$next = runnableExecutorPair;
        }

        public RunnableExecutorPair(Context context, byte[] bArr, byte[] bArr2) {
            this.ExecutionList$RunnableExecutorPair$ar$executor = new Handler(context.getMainLooper());
            this.ExecutionList$RunnableExecutorPair$ar$runnable = new VisualAnnotationPipeline();
        }

        public RunnableExecutorPair(Context context, byte[] bArr) {
            this.ExecutionList$RunnableExecutorPair$ar$runnable = new Object();
            this.ExecutionList$RunnableExecutorPair$ar$executor = context.getApplicationContext();
        }
    }

    public static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            log.get().logp(Level.SEVERE, "com.google.common.util.concurrent.ExecutionList", "executeListener", "RuntimeException while executing runnable " + runnable.toString() + " with executor " + executor.toString(), (Throwable) e);
        }
    }
}
