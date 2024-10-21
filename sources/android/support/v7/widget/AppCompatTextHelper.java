package android.support.v7.widget;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.LocaleList;
import android.support.v7.appcompat.R$styleable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavArgument;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.Preference;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.utils.NetworkRequest28;
import androidx.work.impl.utils.NetworkRequestCompat;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AppCompatTextHelper {
    public boolean mAsyncFontPending;
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private NavArgument.Builder mDrawableBottomTint$ar$class_merging;
    private NavArgument.Builder mDrawableEndTint$ar$class_merging;
    private NavArgument.Builder mDrawableLeftTint$ar$class_merging;
    private NavArgument.Builder mDrawableRightTint$ar$class_merging;
    private NavArgument.Builder mDrawableStartTint$ar$class_merging;
    private NavArgument.Builder mDrawableTopTint$ar$class_merging;
    public Typeface mFontTypeface;
    private final TextView mView;
    public int mStyle = 0;
    private int mFontWeight = -1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        static LocaleList forLanguageTags(String str) {
            LocaleList forLanguageTags;
            forLanguageTags = LocaleList.forLanguageTags(str);
            return forLanguageTags;
        }

        public static final WorkGenerationalId generationalId(WorkSpec workSpec) {
            workSpec.getClass();
            return new WorkGenerationalId(workSpec.id, workSpec.generation);
        }

        public static final void set(View view, OnBackPressedDispatcherOwner onBackPressedDispatcherOwner) {
            view.getClass();
            view.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, onBackPressedDispatcherOwner);
        }

        static void setTextLocales(TextView textView, LocaleList localeList) {
            textView.setTextLocales(localeList);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api26Impl {
        public static final Set byteArrayToSetOfTriggers(byte[] bArr) {
            bArr.getClass();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (bArr.length == 0) {
                return linkedHashSet;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        int readInt = objectInputStream.readInt();
                        for (int i = 0; i < readInt; i++) {
                            Uri parse = Uri.parse(objectInputStream.readUTF());
                            boolean readBoolean = objectInputStream.readBoolean();
                            parse.getClass();
                            linkedHashSet.add(new Constraints.ContentUriTrigger(parse, readBoolean));
                        }
                        OnDeviceShadowRemovalLogEvent.closeFinally(objectInputStream, null);
                    } finally {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OnDeviceShadowRemovalLogEvent.closeFinally(byteArrayInputStream, null);
                return linkedHashSet;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    OnDeviceShadowRemovalLogEvent.closeFinally(byteArrayInputStream, th);
                    throw th2;
                }
            }
        }

        public static final void cancel(WorkManagerImpl workManagerImpl, String str) {
            WorkerWrapper cleanUpWorkerUnsafe;
            WorkDatabase workDatabase = workManagerImpl.mWorkDatabase;
            workDatabase.getClass();
            WorkSpecDao workSpecDao = workDatabase.workSpecDao();
            DependencyDao dependencyDao = workDatabase.dependencyDao();
            List mutableListOf = OnDeviceLanguageIdentificationLogEvent.mutableListOf(str);
            while (!mutableListOf.isEmpty()) {
                String str2 = (String) OnDeviceLanguageIdentificationLogEvent.removeLast(mutableListOf);
                int state$ar$edu$fd856834_0 = workSpecDao.getState$ar$edu$fd856834_0(str2);
                if (state$ar$edu$fd856834_0 != 3 && state$ar$edu$fd856834_0 != 4) {
                    WorkSpecDao_Impl workSpecDao_Impl = (WorkSpecDao_Impl) workSpecDao;
                    workSpecDao_Impl.__db.assertNotSuspendingTransaction();
                    FrameworkSQLiteStatement acquire$ar$class_merging = workSpecDao_Impl.__preparedStmtOfSetCancelledState.acquire$ar$class_merging();
                    acquire$ar$class_merging.bindString(1, str2);
                    try {
                        ((WorkSpecDao_Impl) workSpecDao).__db.beginTransaction();
                        try {
                            acquire$ar$class_merging.executeUpdateDelete();
                            ((WorkSpecDao_Impl) workSpecDao).__db.setTransactionSuccessful();
                        } finally {
                        }
                    } finally {
                        workSpecDao_Impl.__preparedStmtOfSetCancelledState.release$ar$class_merging(acquire$ar$class_merging);
                    }
                }
                mutableListOf.addAll(dependencyDao.getDependentWorkIds(str2));
            }
            Processor processor = workManagerImpl.mProcessor;
            processor.getClass();
            synchronized (processor.mLock) {
                Logger.get$ar$ds$16341a92_0();
                processor.mCancelledIds.add(str);
                cleanUpWorkerUnsafe = processor.cleanUpWorkerUnsafe(str);
            }
            Processor.interrupt$ar$ds(cleanUpWorkerUnsafe, 1);
            Iterator it = workManagerImpl.mSchedulers.iterator();
            while (it.hasNext()) {
                ((Scheduler) it.next()).cancel(str);
            }
        }

        static int getAutoSizeStepGranularity(TextView textView) {
            int autoSizeStepGranularity;
            autoSizeStepGranularity = textView.getAutoSizeStepGranularity();
            return autoSizeStepGranularity;
        }

        public static final int intToBackoffPolicy$ar$edu(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 1) {
                return 2;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Could not convert ", " to BackoffPolicy"));
        }

        public static final int intToNetworkType$ar$edu(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 1) {
                return 2;
            }
            if (i == 2) {
                return 3;
            }
            if (i == 3) {
                return 4;
            }
            if (i == 4) {
                return 5;
            }
            if (Build.VERSION.SDK_INT >= 30 && i == 5) {
                return 6;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Could not convert ", " to NetworkType"));
        }

        public static final int intToOutOfQuotaPolicy$ar$edu(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 1) {
                return 2;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Could not convert ", " to OutOfQuotaPolicy"));
        }

        public static final int intToState$ar$edu(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 1) {
                return 2;
            }
            if (i == 2) {
                return 3;
            }
            if (i == 3) {
                return 4;
            }
            if (i == 4) {
                return 5;
            }
            if (i == 5) {
                return 6;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Could not convert ", " to State"));
        }

        static void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int i, int i2, int i3, int i4) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        }

        static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, int[] iArr, int i) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        }

        static boolean setFontVariationSettings(TextView textView, String str) {
            boolean fontVariationSettings;
            fontVariationSettings = textView.setFontVariationSettings(str);
            return fontVariationSettings;
        }

        public static final int stateToInt$ar$edu(int i) {
            if (i != 0) {
                int i2 = i - 1;
                if (i2 != 0) {
                    int i3 = 1;
                    if (i2 != 1) {
                        i3 = 2;
                        if (i2 != 2) {
                            i3 = 3;
                            if (i2 != 3) {
                                i3 = 4;
                                if (i2 != 4) {
                                    return 5;
                                }
                            }
                        }
                    }
                    return i3;
                }
                return 0;
            }
            throw null;
        }

        public static final NetworkRequestCompat toNetworkRequest$work_runtime_release(byte[] bArr) {
            bArr.getClass();
            if (Build.VERSION.SDK_INT >= 28 && bArr.length != 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        int readInt = objectInputStream.readInt();
                        int[] iArr = new int[readInt];
                        for (int i = 0; i < readInt; i++) {
                            iArr[i] = objectInputStream.readInt();
                        }
                        int readInt2 = objectInputStream.readInt();
                        int[] iArr2 = new int[readInt2];
                        for (int i2 = 0; i2 < readInt2; i2++) {
                            iArr2[i2] = objectInputStream.readInt();
                        }
                        NetworkRequestCompat createNetworkRequestCompat$work_runtime_release = NetworkRequest28.INSTANCE.createNetworkRequestCompat$work_runtime_release(iArr2, iArr);
                        OnDeviceShadowRemovalLogEvent.closeFinally(objectInputStream, null);
                        OnDeviceShadowRemovalLogEvent.closeFinally(byteArrayInputStream, null);
                        return createNetworkRequestCompat$work_runtime_release;
                    } finally {
                    }
                } finally {
                }
            } else {
                return new NetworkRequestCompat((Object) null);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api28Impl {
        static Typeface create(Typeface typeface, int i, boolean z) {
            Typeface create;
            create = Typeface.create(typeface, i, z);
            return create;
        }

        public static final int nextId(WorkDatabase workDatabase, String str) {
            int i;
            Long longValue = workDatabase.preferenceDao().getLongValue(str);
            int i2 = 0;
            if (longValue != null) {
                i = (int) longValue.longValue();
            } else {
                i = 0;
            }
            if (i != Integer.MAX_VALUE) {
                i2 = i + 1;
            }
            updatePreference(workDatabase, str, i2);
            return i;
        }

        /* JADX WARN: Type inference failed for: r5v4, types: [java.util.Map, java.lang.Object] */
        public static final WorkSpec tryDelegateRemoteListenableWorker(WorkSpec workSpec) {
            boolean hasKeyWithValueOfType = workSpec.input.hasKeyWithValueOfType("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", String.class);
            boolean hasKeyWithValueOfType2 = workSpec.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME", String.class);
            boolean hasKeyWithValueOfType3 = workSpec.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME", String.class);
            if (hasKeyWithValueOfType || !hasKeyWithValueOfType2 || !hasKeyWithValueOfType3) {
                return workSpec;
            }
            String str = workSpec.workerClassName;
            ViewModelStore viewModelStore = new ViewModelStore((byte[]) null, (byte[]) null);
            Data data = workSpec.input;
            data.getClass();
            viewModelStore.putAll$ar$ds(data.values);
            viewModelStore.ViewModelStore$ar$map.put("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", str);
            Data build = viewModelStore.build();
            String str2 = workSpec.id;
            int i = workSpec.state$ar$edu;
            String str3 = workSpec.inputMergerClassName;
            Data data2 = workSpec.output;
            long j = workSpec.initialDelay;
            long j2 = workSpec.intervalDuration;
            long j3 = workSpec.flexDuration;
            Constraints constraints = workSpec.constraints;
            int i2 = workSpec.runAttemptCount;
            int i3 = workSpec.backoffPolicy$ar$edu;
            long j4 = workSpec.backoffDelayDuration;
            long j5 = workSpec.lastEnqueueTime;
            long j6 = workSpec.minimumRetentionDuration;
            long j7 = workSpec.scheduleRequestedAt;
            boolean z = workSpec.expedited;
            int i4 = workSpec.outOfQuotaPolicy$ar$edu;
            int i5 = workSpec.periodCount;
            int i6 = workSpec.generation;
            long j8 = workSpec.nextScheduleTimeOverride;
            int i7 = workSpec.nextScheduleTimeOverrideGeneration;
            int i8 = workSpec.stopReason;
            String str4 = workSpec.traceTag;
            if (i != 0) {
                str3.getClass();
                data2.getClass();
                constraints.getClass();
                if (i3 != 0 && i4 != 0) {
                    return new WorkSpec(str2, i, "androidx.work.multiprocess.RemoteListenableDelegatingWorker", str3, build, data2, j, j2, j3, constraints, i2, i3, j4, j5, j6, j7, z, i4, i5, i6, j8, i7, i8, str4);
                }
            }
            throw null;
        }

        public static final void updatePreference(WorkDatabase workDatabase, String str, int i) {
            workDatabase.preferenceDao().insertPreference(new Preference(str, Long.valueOf(i)));
        }
    }

    public AppCompatTextHelper(TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    private final void applyCompoundDrawableTint$ar$class_merging(Drawable drawable, NavArgument.Builder builder) {
        if (drawable != null && builder != null) {
            ResourceManagerInternal.tintDrawable$ar$class_merging(drawable, builder, this.mView.getDrawableState());
        }
    }

    private static NavArgument.Builder createTintInfo$ar$class_merging(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i);
        if (tintList != null) {
            NavArgument.Builder builder = new NavArgument.Builder();
            builder.defaultValuePresent = true;
            builder.NavArgument$Builder$ar$type = tintList;
            return builder;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void populateSurroundingTextIfNeeded$ar$ds(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        int i;
        int i2;
        int i3;
        CharSequence subSequence;
        if (Build.VERSION.SDK_INT < 30 && inputConnection != null) {
            CharSequence text = textView.getText();
            if (Build.VERSION.SDK_INT < 30) {
                text.getClass();
                if (Build.VERSION.SDK_INT < 30) {
                    if (editorInfo.initialSelStart > editorInfo.initialSelEnd) {
                        i = editorInfo.initialSelEnd;
                    } else {
                        i = editorInfo.initialSelStart;
                    }
                    if (editorInfo.initialSelStart > editorInfo.initialSelEnd) {
                        i2 = editorInfo.initialSelStart;
                    } else {
                        i2 = editorInfo.initialSelEnd;
                    }
                    int length = text.length();
                    if (i >= 0 && i2 <= length) {
                        int i4 = editorInfo.inputType & 4095;
                        if (i4 != 129 && i4 != 225 && i4 != 18) {
                            if (length > 2048) {
                                int i5 = i2 - i;
                                int length2 = text.length() - i2;
                                if (i5 > 1024) {
                                    i3 = 0;
                                } else {
                                    i3 = i5;
                                }
                                int i6 = 2048 - i3;
                                int min = Math.min(length2, i6 - Math.min(i, (int) (i6 * 0.8d)));
                                int min2 = Math.min(i, i6 - min);
                                int i7 = i - min2;
                                if (EditorInfoCompat.isCutOnSurrogate(text, i7, 0)) {
                                    i7++;
                                    min2--;
                                }
                                if (EditorInfoCompat.isCutOnSurrogate(text, (i2 + min) - 1, 1)) {
                                    min--;
                                }
                                int i8 = min2 + i3;
                                if (i3 != i5) {
                                    subSequence = TextUtils.concat(text.subSequence(i7, i7 + min2), text.subSequence(i2, min + i2));
                                } else {
                                    subSequence = text.subSequence(i7, min + i8 + i7);
                                }
                                EditorInfoCompat.setSurroundingText(editorInfo, subSequence, min2, i8);
                                return;
                            }
                            EditorInfoCompat.setSurroundingText(editorInfo, text, i, i2);
                            return;
                        }
                        EditorInfoCompat.setSurroundingText(editorInfo, null, 0, 0);
                        return;
                    }
                    EditorInfoCompat.setSurroundingText(editorInfo, null, 0, 0);
                    return;
                }
                editorInfo.setInitialSurroundingSubText(text, 0);
                return;
            }
            editorInfo.setInitialSurroundingSubText(text, 0);
        }
    }

    private final void updateTypefaceAndStyle$ar$class_merging$ar$class_merging$ar$class_merging(Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        String string;
        boolean z;
        boolean z2;
        int[] iArr = R$styleable.ActionBar;
        this.mStyle = runnableExecutorPair.getInt(2, this.mStyle);
        if (Build.VERSION.SDK_INT >= 28) {
            int i = runnableExecutorPair.getInt(11, -1);
            this.mFontWeight = i;
            if (i != -1) {
                this.mStyle &= 2;
            }
        }
        int i2 = 10;
        boolean z3 = false;
        if (!runnableExecutorPair.hasValue(10) && !runnableExecutorPair.hasValue(12)) {
            if (runnableExecutorPair.hasValue(1)) {
                this.mAsyncFontPending = false;
                int i3 = runnableExecutorPair.getInt(1, 1);
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 == 3) {
                            this.mFontTypeface = Typeface.MONOSPACE;
                            return;
                        }
                        return;
                    }
                    this.mFontTypeface = Typeface.SERIF;
                    return;
                }
                this.mFontTypeface = Typeface.SANS_SERIF;
                return;
            }
            return;
        }
        Typeface typeface = null;
        this.mFontTypeface = null;
        if (true == runnableExecutorPair.hasValue(12)) {
            i2 = 12;
        }
        final int i4 = this.mFontWeight;
        final int i5 = this.mStyle;
        if (!context.isRestricted()) {
            final WeakReference weakReference = new WeakReference(this.mView);
            ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback() { // from class: android.support.v7.widget.AppCompatTextHelper.1
                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                public final void onFontRetrieved(Typeface typeface2) {
                    int i6;
                    boolean z4;
                    if (Build.VERSION.SDK_INT >= 28 && (i6 = i4) != -1) {
                        if ((i5 & 2) != 0) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        typeface2 = Api28Impl.create(typeface2, i6, z4);
                    }
                    AppCompatTextHelper appCompatTextHelper = AppCompatTextHelper.this;
                    WeakReference weakReference2 = weakReference;
                    if (appCompatTextHelper.mAsyncFontPending) {
                        appCompatTextHelper.mFontTypeface = typeface2;
                        TextView textView = (TextView) weakReference2.get();
                        if (textView != null) {
                            if (textView.isAttachedToWindow()) {
                                textView.post(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(textView, typeface2, appCompatTextHelper.mStyle, 1));
                            } else {
                                textView.setTypeface(typeface2, appCompatTextHelper.mStyle);
                            }
                        }
                    }
                }

                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                public final void onFontRetrievalFailed(int i6) {
                }
            };
            try {
                int i6 = this.mStyle;
                int resourceId = ((TypedArray) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable).getResourceId(i2, 0);
                if (resourceId != 0) {
                    if (runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next == null) {
                        runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next = new TypedValue();
                    }
                    Object obj = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor;
                    Object obj2 = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next;
                    ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
                    if (!((Context) obj).isRestricted()) {
                        typeface = ResourcesCompat.loadFont$ar$ds((Context) obj, resourceId, (TypedValue) obj2, i6, fontCallback, true, false);
                    }
                }
                if (typeface != null) {
                    if (Build.VERSION.SDK_INT >= 28 && this.mFontWeight != -1) {
                        Typeface create = Typeface.create(typeface, 0);
                        int i7 = this.mFontWeight;
                        if ((this.mStyle & 2) != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        this.mFontTypeface = Api28Impl.create(create, i7, z2);
                    } else {
                        this.mFontTypeface = typeface;
                    }
                }
                if (this.mFontTypeface == null) {
                    z = true;
                } else {
                    z = false;
                }
                this.mAsyncFontPending = z;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.mFontTypeface == null && (string = runnableExecutorPair.getString(i2)) != null) {
            if (Build.VERSION.SDK_INT >= 28 && this.mFontWeight != -1) {
                Typeface create2 = Typeface.create(string, 0);
                int i8 = this.mFontWeight;
                if ((2 & this.mStyle) != 0) {
                    z3 = true;
                }
                this.mFontTypeface = Api28Impl.create(create2, i8, z3);
                return;
            }
            this.mFontTypeface = Typeface.create(string, this.mStyle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void applyCompoundDrawablesTints() {
        if (this.mDrawableLeftTint$ar$class_merging != null || this.mDrawableTopTint$ar$class_merging != null || this.mDrawableRightTint$ar$class_merging != null || this.mDrawableBottomTint$ar$class_merging != null) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint$ar$class_merging(compoundDrawables[0], this.mDrawableLeftTint$ar$class_merging);
            applyCompoundDrawableTint$ar$class_merging(compoundDrawables[1], this.mDrawableTopTint$ar$class_merging);
            applyCompoundDrawableTint$ar$class_merging(compoundDrawables[2], this.mDrawableRightTint$ar$class_merging);
            applyCompoundDrawableTint$ar$class_merging(compoundDrawables[3], this.mDrawableBottomTint$ar$class_merging);
        }
        if (this.mDrawableStartTint$ar$class_merging == null && this.mDrawableEndTint$ar$class_merging == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
        applyCompoundDrawableTint$ar$class_merging(compoundDrawablesRelative[0], this.mDrawableStartTint$ar$class_merging);
        applyCompoundDrawableTint$ar$class_merging(compoundDrawablesRelative[2], this.mDrawableEndTint$ar$class_merging);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.mAutoSizeTextSizesInPx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.mAutoSizeTextType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02cb, code lost:
    
        if (r7 != null) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0319, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0317, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0315, code lost:
    
        if (r7 != null) goto L179;
     */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:176:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0361  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void loadFromAttributes(android.util.AttributeSet r22, int r23) {
        /*
            Method dump skipped, instructions count: 960
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onLayout$ar$ds() {
        if (!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            autoSizeText();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onSetTextAppearance(Context context, int i) {
        String string;
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging(context, i, R$styleable.TextAppearance);
        if (obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(14)) {
            setAllCaps(obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getBoolean(14, false));
        }
        if (obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(0) && obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getDimensionPixelSize(0, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle$ar$class_merging$ar$class_merging$ar$class_merging(context, obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging);
        if (obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(13) && (string = obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getString(13)) != null) {
            Api26Impl.setFontVariationSettings(this.mView, string);
        }
        obtainStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.recycle();
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            this.mView.setTypeface(typeface, this.mStyle);
        }
    }

    final void setAllCaps(boolean z) {
        this.mView.setAllCaps(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        if (appCompatTextViewAutoSizeHelper.supportsAutoSizeText()) {
            DisplayMetrics displayMetrics = appCompatTextViewAutoSizeHelper.mContext.getResources().getDisplayMetrics();
            appCompatTextViewAutoSizeHelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (appCompatTextViewAutoSizeHelper.setupAutoSizeText()) {
                appCompatTextViewAutoSizeHelper.autoSizeText();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        if (appCompatTextViewAutoSizeHelper.supportsAutoSizeText()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = appCompatTextViewAutoSizeHelper.mContext.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx = AppCompatTextViewAutoSizeHelper.cleanupAutoSizePresetSizes$ar$ds(iArr2);
                if (!appCompatTextViewAutoSizeHelper.setupAutoSizeUniformPresetSizesConfiguration()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: ".concat(String.valueOf(Arrays.toString(iArr))));
                }
            } else {
                appCompatTextViewAutoSizeHelper.mHasPresetAutoSizeValues = false;
            }
            if (appCompatTextViewAutoSizeHelper.setupAutoSizeText()) {
                appCompatTextViewAutoSizeHelper.autoSizeText();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setAutoSizeTextTypeWithDefaults(int i) {
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        if (appCompatTextViewAutoSizeHelper.supportsAutoSizeText()) {
            if (i != 0) {
                if (i == 1) {
                    DisplayMetrics displayMetrics = appCompatTextViewAutoSizeHelper.mContext.getResources().getDisplayMetrics();
                    appCompatTextViewAutoSizeHelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                    if (appCompatTextViewAutoSizeHelper.setupAutoSizeText()) {
                        appCompatTextViewAutoSizeHelper.autoSizeText();
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unknown auto-size text type: "));
            }
            appCompatTextViewAutoSizeHelper.mAutoSizeTextType = 0;
            appCompatTextViewAutoSizeHelper.mAutoSizeMinTextSizeInPx = -1.0f;
            appCompatTextViewAutoSizeHelper.mAutoSizeMaxTextSizeInPx = -1.0f;
            appCompatTextViewAutoSizeHelper.mAutoSizeStepGranularityInPx = -1.0f;
            appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx = new int[0];
            appCompatTextViewAutoSizeHelper.mNeedsAutoSizeText = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setTextSize(int i, float f) {
        if (!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE && !isAutoSizeEnabled()) {
            this.mAutoSizeTextHelper.setTextSizeInternal(i, f);
        }
    }
}
