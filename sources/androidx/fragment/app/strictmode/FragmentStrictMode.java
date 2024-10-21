package androidx.fragment.app.strictmode;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.EmptyMap;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentStrictMode {
    public static final FragmentStrictMode INSTANCE = new FragmentStrictMode();
    private static final Policy defaultPolicy = Policy.LAX;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Flag {
        PENALTY_LOG,
        PENALTY_DEATH,
        DETECT_FRAGMENT_REUSE,
        DETECT_FRAGMENT_TAG_USAGE,
        DETECT_WRONG_NESTED_HIERARCHY,
        DETECT_RETAIN_INSTANCE_USAGE,
        DETECT_SET_USER_VISIBLE_HINT,
        DETECT_TARGET_FRAGMENT_USAGE,
        DETECT_WRONG_FRAGMENT_CONTAINER
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Policy {
        public static final Policy LAX = new Policy(EmptySet.INSTANCE, EmptyMap.INSTANCE);
        public final Set flags;
        public final Map mAllowedViolations;

        public Policy(Set set, Map map) {
            this.flags = set;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put((String) entry.getKey(), (Set) entry.getValue());
            }
            this.mAllowedViolations = linkedHashMap;
        }
    }

    private FragmentStrictMode() {
    }

    public static final Policy getNearestPolicy$ar$ds(Fragment fragment) {
        while (fragment != null) {
            if (fragment.isAdded()) {
                fragment.getParentFragmentManager();
            }
            fragment = fragment.getParentFragment();
        }
        return defaultPolicy;
    }

    public static final void handlePolicyViolation$ar$ds(Policy policy, Violation violation) {
        Fragment fragment = violation.fragment;
        String name = fragment.getClass().getName();
        policy.flags.contains(Flag.PENALTY_LOG);
        if (policy.flags.contains(Flag.PENALTY_DEATH)) {
            DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3 defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3 = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(name, violation, 18);
            if (fragment.isAdded()) {
                Handler handler = fragment.getParentFragmentManager().mHost.handler;
                if (Intrinsics.areEqual(handler.getLooper(), Looper.myLooper())) {
                    defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3.run();
                    return;
                } else {
                    handler.post(defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3);
                    return;
                }
            }
            defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3.run();
        }
    }

    public static final void logIfDebuggingEnabled$ar$ds(Violation violation) {
        if (FragmentManager.isLoggingEnabled(3)) {
            violation.fragment.getClass().getName();
        }
    }

    public static final void onFragmentReuse(Fragment fragment, String str) {
        fragment.getClass();
        FragmentReuseViolation fragmentReuseViolation = new FragmentReuseViolation(fragment, str);
        logIfDebuggingEnabled$ar$ds(fragmentReuseViolation);
        Policy nearestPolicy$ar$ds = getNearestPolicy$ar$ds(fragment);
        if (nearestPolicy$ar$ds.flags.contains(Flag.DETECT_FRAGMENT_REUSE) && shouldHandlePolicyViolation$ar$ds(nearestPolicy$ar$ds, fragment.getClass(), fragmentReuseViolation.getClass())) {
            handlePolicyViolation$ar$ds(nearestPolicy$ar$ds, fragmentReuseViolation);
        }
    }

    public static final boolean shouldHandlePolicyViolation$ar$ds(Policy policy, Class cls, Class cls2) {
        Set set = (Set) policy.mAllowedViolations.get(cls.getName());
        if (set == null) {
            return true;
        }
        if ((Intrinsics.areEqual(cls2.getSuperclass(), Violation.class) || !OnDeviceLanguageIdentificationLogEvent.contains(set, cls2.getSuperclass())) && !set.contains(cls2)) {
            return true;
        }
        return false;
    }
}
