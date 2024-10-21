package androidx.activity.result;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.room.TriggerBasedInvalidationTracker$onAllowRefresh$1;
import androidx.work.impl.model.WorkName;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.GeneratorSequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActivityResultRegistry {
    public final transient Map keyToCallback;
    public final Map keyToLifecycleContainers;
    public final Map keyToRc;
    public final List launchedKeys;
    public final Map parsedPendingResults;
    public final Bundle pendingResults;
    public final Map rcToKey;
    final /* synthetic */ ComponentActivity this$0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActivityResultRegistry(ComponentActivity componentActivity) {
        this();
        this.this$0 = componentActivity;
    }

    public final void bindRcKey(int i, String str) {
        Map map = this.rcToKey;
        Integer valueOf = Integer.valueOf(i);
        map.put(valueOf, str);
        this.keyToRc.put(str, valueOf);
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object, androidx.activity.result.ActivityResultCallback] */
    public final boolean dispatchResult(int i, int i2, Intent intent) {
        Object obj;
        String str = (String) this.rcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        NodeBrailler nodeBrailler = (NodeBrailler) this.keyToCallback.get(str);
        if (nodeBrailler != null) {
            obj = nodeBrailler.NodeBrailler$ar$context;
        } else {
            obj = null;
        }
        if (obj != null && this.launchedKeys.contains(str)) {
            nodeBrailler.NodeBrailler$ar$context.onActivityResult(((ActivityResultContract) nodeBrailler.NodeBrailler$ar$rules).parseResult(i2, intent));
            this.launchedKeys.remove(str);
            return true;
        }
        this.parsedPendingResults.remove(str);
        this.pendingResults.putParcelable(str, new ActivityResult(i2, intent));
        return true;
    }

    public final void onLaunch$ar$ds(int i, ActivityResultContract activityResultContract, Object obj) {
        Bundle bundle;
        ComponentActivity componentActivity = this.this$0;
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat synchronousResult$ar$class_merging$ar$class_merging = activityResultContract.getSynchronousResult$ar$class_merging$ar$class_merging(componentActivity, obj);
        int i2 = 0;
        if (synchronousResult$ar$class_merging$ar$class_merging != null) {
            new Handler(Looper.getMainLooper()).post(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(this, i, synchronousResult$ar$class_merging$ar$class_merging, i2));
            return;
        }
        Intent createIntent$ar$ds = activityResultContract.createIntent$ar$ds(obj);
        if (createIntent$ar$ds.getExtras() != null) {
            Bundle extras = createIntent$ar$ds.getExtras();
            extras.getClass();
            if (extras.getClassLoader() == null) {
                createIntent$ar$ds.setExtrasClassLoader(componentActivity.getClassLoader());
            }
        }
        if (createIntent$ar$ds.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
            Bundle bundleExtra = createIntent$ar$ds.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            createIntent$ar$ds.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            bundle = bundleExtra;
        } else {
            bundle = null;
        }
        if (Intrinsics.areEqual("androidx.activity.result.contract.action.REQUEST_PERMISSIONS", createIntent$ar$ds.getAction())) {
            String[] stringArrayExtra = createIntent$ar$ds.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
            if (stringArrayExtra == null) {
                stringArrayExtra = new String[0];
            }
            ActivityCompat.requestPermissions(componentActivity, stringArrayExtra, i);
            return;
        }
        if (Intrinsics.areEqual("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST", createIntent$ar$ds.getAction())) {
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) createIntent$ar$ds.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                intentSenderRequest.getClass();
                componentActivity.startIntentSenderForResult(intentSenderRequest.intentSender, i, intentSenderRequest.fillInIntent, intentSenderRequest.flagsMask, intentSenderRequest.flagsValues, 0, bundle);
                return;
            } catch (IntentSender.SendIntentException e) {
                new Handler(Looper.getMainLooper()).post(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(this, i, e, 2));
                return;
            }
        }
        componentActivity.startActivityForResult(createIntent$ar$ds, i, bundle);
    }

    public final ActivityResultLauncher register(final String str, final ActivityResultContract activityResultContract, ActivityResultCallback activityResultCallback) {
        registerKey(str);
        this.keyToCallback.put(str, new NodeBrailler(activityResultCallback, activityResultContract));
        if (this.parsedPendingResults.containsKey(str)) {
            Object obj = this.parsedPendingResults.get(str);
            this.parsedPendingResults.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) AppCompatDelegate.Api24Impl.getParcelable(this.pendingResults, str, ActivityResult.class);
        if (activityResult != null) {
            this.pendingResults.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.resultCode, activityResult.data));
        }
        return new ActivityResultLauncher() { // from class: androidx.activity.result.ActivityResultRegistry$register$3
            @Override // androidx.activity.result.ActivityResultLauncher
            public final void launch$ar$ds(Object obj2) {
                Object obj3 = ActivityResultRegistry.this.keyToRc.get(str);
                if (obj3 != null) {
                    int intValue = ((Number) obj3).intValue();
                    ActivityResultRegistry.this.launchedKeys.add(str);
                    try {
                        ActivityResultRegistry.this.onLaunch$ar$ds(intValue, activityResultContract, obj2);
                        return;
                    } catch (Exception e) {
                        ActivityResultRegistry.this.launchedKeys.remove(str);
                        throw e;
                    }
                }
                throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj2 + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public final void unregister() {
                ActivityResultRegistry.this.unregister$activity_release(str);
            }
        };
    }

    public final void registerKey(String str) {
        if (((Integer) this.keyToRc.get(str)) == null) {
            final TriggerBasedInvalidationTracker$onAllowRefresh$1 triggerBasedInvalidationTracker$onAllowRefresh$1 = TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging;
            for (Number number : OnDeviceSmartReplyLogEvent.constrainOnce(new GeneratorSequence(triggerBasedInvalidationTracker$onAllowRefresh$1, new Function1() { // from class: kotlin.sequences.SequencesKt__SequencesKt$generateSequence$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    obj.getClass();
                    return Function0.this.invoke();
                }
            }, 0))) {
                if (!this.rcToKey.containsKey(Integer.valueOf(number.intValue()))) {
                    bindRcKey(number.intValue(), str);
                    return;
                }
            }
            throw new NoSuchElementException("Sequence contains no element matching the predicate.");
        }
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.lang.Iterable] */
    public final void unregister$activity_release(String str) {
        Integer num;
        if (!this.launchedKeys.contains(str) && (num = (Integer) this.keyToRc.remove(str)) != null) {
            this.rcToKey.remove(num);
        }
        this.keyToCallback.remove(str);
        if (this.parsedPendingResults.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.parsedPendingResults.get(str));
            this.parsedPendingResults.remove(str);
        }
        if (this.pendingResults.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + ((ActivityResult) AppCompatDelegate.Api24Impl.getParcelable(this.pendingResults, str, ActivityResult.class)));
            this.pendingResults.remove(str);
        }
        WorkName workName = (WorkName) this.keyToLifecycleContainers.get(str);
        if (workName != null) {
            Iterator it = workName.WorkName$ar$workSpecId.iterator();
            while (it.hasNext()) {
                ((Lifecycle) workName.WorkName$ar$name).removeObserver((LifecycleEventObserver) it.next());
            }
            workName.WorkName$ar$workSpecId.clear();
            this.keyToLifecycleContainers.remove(str);
        }
    }

    public ActivityResultRegistry() {
        this.rcToKey = new LinkedHashMap();
        this.keyToRc = new LinkedHashMap();
        this.keyToLifecycleContainers = new LinkedHashMap();
        this.launchedKeys = new ArrayList();
        this.keyToCallback = new LinkedHashMap();
        this.parsedPendingResults = new LinkedHashMap();
        this.pendingResults = new Bundle();
    }
}
