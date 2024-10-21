package android.support.v4.app;

import android.support.v4.app.SpecialEffectsController;

/* compiled from: PG */
/* loaded from: classes.dex */
final class DefaultSpecialEffectsController$TransitionInfo extends DefaultSpecialEffectsController$SpecialEffectsInfo {
    public final boolean isOverlapAllowed;
    public final Object sharedElementTransition;
    public final Object transition;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController$TransitionInfo(SpecialEffectsController.Operation operation, boolean z, boolean z2) {
        super(operation);
        Object exitTransition;
        Object obj;
        operation.getClass();
        boolean z3 = false;
        boolean z4 = true;
        if (operation.finalState$ar$edu == 2) {
            if (z) {
                exitTransition = operation.fragment.getReenterTransition();
                z3 = true;
            } else {
                exitTransition = operation.fragment.getEnterTransition();
            }
        } else if (z) {
            exitTransition = operation.fragment.getReturnTransition();
            z3 = true;
        } else {
            exitTransition = operation.fragment.getExitTransition();
        }
        this.transition = exitTransition;
        if (operation.finalState$ar$edu == 2) {
            if (z3) {
                z4 = operation.fragment.getAllowReturnTransitionOverlap();
            } else {
                z4 = operation.fragment.getAllowEnterTransitionOverlap();
            }
        }
        this.isOverlapAllowed = z4;
        if (z2) {
            if (z3) {
                obj = operation.fragment.getSharedElementReturnTransition();
            } else {
                obj = operation.fragment.getSharedElementEnterTransition();
            }
        } else {
            obj = null;
        }
        this.sharedElementTransition = obj;
    }

    public final FragmentTransitionImpl getHandlingImpl() {
        Object obj = this.sharedElementTransition;
        FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
        FragmentTransitionImpl handlingImpl2 = getHandlingImpl(obj);
        if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
            return handlingImpl == null ? handlingImpl2 : handlingImpl;
        }
        throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.operation.fragment + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition);
    }

    public final boolean hasSharedElementTransition() {
        if (this.sharedElementTransition != null) {
            return true;
        }
        return false;
    }

    private final FragmentTransitionImpl getHandlingImpl(Object obj) {
        if (obj == null) {
            return null;
        }
        FragmentTransition fragmentTransition = FragmentTransition.INSTANCE;
        if (FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
            return FragmentTransition.PLATFORM_IMPL;
        }
        FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.SUPPORT_IMPL;
        if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
            return FragmentTransition.SUPPORT_IMPL;
        }
        throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.operation.fragment + " is not a valid framework Transition or AndroidX Transition");
    }
}
