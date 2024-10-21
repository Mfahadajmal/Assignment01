package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import androidx.core.view.PointerIconCompat$Api24Impl;
import androidx.lifecycle.Lifecycle;
import androidx.room.util.TableInfo;
import com.google.android.accessibility.selecttospeak.popup.S2SPopupParsedIntentKt$parseIntent$text$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.GeneratorSequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReportFragment extends Fragment {
    public static final PointerIconCompat$Api24Impl Companion$ar$class_merging$868eb497_0 = new PointerIconCompat$Api24Impl();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        public static final Companion Companion = new Companion();

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Companion {
            public static final boolean defaultValueEqualsCommon(String str, String str2) {
                if (Intrinsics.areEqual(str, str2)) {
                    return true;
                }
                if (str.length() != 0) {
                    int i = 0;
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        if (i < str.length()) {
                            char charAt = str.charAt(i);
                            int i4 = i3 + 1;
                            if (i3 == 0) {
                                if (charAt != '(') {
                                    break;
                                }
                                i3 = 0;
                                charAt = '(';
                            }
                            if (charAt == '(') {
                                i2++;
                            } else if (charAt == ')' && i2 - 1 == 0 && i3 != str.length() - 1) {
                                break;
                            }
                            i++;
                            i3 = i4;
                        } else if (i2 == 0) {
                            String substring = str.substring(1, str.length() - 1);
                            substring.getClass();
                            return Intrinsics.areEqual(OnDeviceStainRemovalLogEvent.trim(substring).toString(), str2);
                        }
                    }
                }
                return false;
            }

            public static final boolean equalsCommon(TableInfo tableInfo, Object obj) {
                Set set;
                if (tableInfo == obj) {
                    return true;
                }
                if (!(obj instanceof TableInfo)) {
                    return false;
                }
                TableInfo tableInfo2 = (TableInfo) obj;
                if (!Intrinsics.areEqual(tableInfo.name, tableInfo2.name) || !Intrinsics.areEqual(tableInfo.columns, tableInfo2.columns) || !Intrinsics.areEqual(tableInfo.foreignKeys, tableInfo2.foreignKeys)) {
                    return false;
                }
                Set set2 = tableInfo.indices;
                if (set2 == null || (set = tableInfo2.indices) == null) {
                    return true;
                }
                return Intrinsics.areEqual(set2, set);
            }

            public static final String formatString(Collection collection) {
                String joinToString$default$ar$ds$9e9f5bd_0;
                if (!collection.isEmpty()) {
                    joinToString$default$ar$ds$9e9f5bd_0 = OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence(OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(collection, ",\n", "\n", "\n", null, 56)), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
                    return joinToString$default$ar$ds$9e9f5bd_0.concat("},");
                }
                return " }";
            }

            public static final void joinToStringEndWithIndent(Collection collection) {
                OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence(OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(collection, ",", null, null, null, 62)), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
                OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence(" }"), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
            }

            public static final void joinToStringMiddleWithIndent(Collection collection) {
                OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence(OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(collection, ",", null, null, null, 62)), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
                OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence("},"), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
            }

            public static final void registerIn$ar$ds(Activity activity) {
                activity.getClass();
                activity.registerActivityLifecycleCallbacks(new LifecycleCallbacks());
            }
        }

        public static final void registerIn(Activity activity) {
            Companion.registerIn$ar$ds(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activity.getClass();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            activity.getClass();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            activity.getClass();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            activity.getClass();
            PointerIconCompat$Api24Impl pointerIconCompat$Api24Impl = ReportFragment.Companion$ar$class_merging$868eb497_0;
            PointerIconCompat$Api24Impl.dispatch$lifecycle_runtime_release$ar$ds(activity, Lifecycle.Event.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            activity.getClass();
            PointerIconCompat$Api24Impl pointerIconCompat$Api24Impl = ReportFragment.Companion$ar$class_merging$868eb497_0;
            PointerIconCompat$Api24Impl.dispatch$lifecycle_runtime_release$ar$ds(activity, Lifecycle.Event.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            activity.getClass();
            PointerIconCompat$Api24Impl pointerIconCompat$Api24Impl = ReportFragment.Companion$ar$class_merging$868eb497_0;
            PointerIconCompat$Api24Impl.dispatch$lifecycle_runtime_release$ar$ds(activity, Lifecycle.Event.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            activity.getClass();
            PointerIconCompat$Api24Impl pointerIconCompat$Api24Impl = ReportFragment.Companion$ar$class_merging$868eb497_0;
            PointerIconCompat$Api24Impl.dispatch$lifecycle_runtime_release$ar$ds(activity, Lifecycle.Event.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            activity.getClass();
            PointerIconCompat$Api24Impl pointerIconCompat$Api24Impl = ReportFragment.Companion$ar$class_merging$868eb497_0;
            PointerIconCompat$Api24Impl.dispatch$lifecycle_runtime_release$ar$ds(activity, Lifecycle.Event.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            activity.getClass();
            PointerIconCompat$Api24Impl pointerIconCompat$Api24Impl = ReportFragment.Companion$ar$class_merging$868eb497_0;
            PointerIconCompat$Api24Impl.dispatch$lifecycle_runtime_release$ar$ds(activity, Lifecycle.Event.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            activity.getClass();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            activity.getClass();
            bundle.getClass();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            activity.getClass();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            activity.getClass();
        }
    }

    private final void dispatch(Lifecycle.Event event) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            activity.getClass();
            PointerIconCompat$Api24Impl.dispatch$lifecycle_runtime_release$ar$ds(activity, event);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        dispatch(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        dispatch(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        dispatch(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        dispatch(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        dispatch(Lifecycle.Event.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        dispatch(Lifecycle.Event.ON_STOP);
    }
}
