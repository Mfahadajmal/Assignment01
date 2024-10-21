package androidx.navigation;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavOptions {
    public final int enterAnim;
    public final int exitAnim;
    public final int popEnterAnim;
    public final int popExitAnim;
    public final int popUpToId;
    public final boolean popUpToInclusive;
    public final boolean popUpToSaveState;
    public final boolean restoreState;
    public final boolean singleTop;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private boolean popUpToInclusive;
        private boolean popUpToSaveState;
        public boolean restoreState;
        public boolean singleTop;
        private int popUpToId = -1;
        public int enterAnim = -1;
        public int exitAnim = -1;
        public int popEnterAnim = -1;
        public int popExitAnim = -1;

        public static /* synthetic */ Builder setPopUpTo$default$ar$ds(Builder builder, int i, boolean z) {
            builder.setPopUpTo$ar$ds(i, z, false);
            return builder;
        }

        public final NavOptions build() {
            return new NavOptions(this.singleTop, this.restoreState, this.popUpToId, this.popUpToInclusive, this.popUpToSaveState, this.enterAnim, this.exitAnim, this.popEnterAnim, this.popExitAnim);
        }

        public final void setPopUpTo$ar$ds(int i, boolean z, boolean z2) {
            this.popUpToId = i;
            this.popUpToInclusive = z;
            this.popUpToSaveState = z2;
        }
    }

    public NavOptions(boolean z, boolean z2, int i, boolean z3, boolean z4, int i2, int i3, int i4, int i5) {
        this.singleTop = z;
        this.restoreState = z2;
        this.popUpToId = i;
        this.popUpToInclusive = z3;
        this.popUpToSaveState = z4;
        this.enterAnim = i2;
        this.exitAnim = i3;
        this.popEnterAnim = i4;
        this.popExitAnim = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof NavOptions)) {
            NavOptions navOptions = (NavOptions) obj;
            if (this.singleTop == navOptions.singleTop && this.restoreState == navOptions.restoreState && this.popUpToId == navOptions.popUpToId && Intrinsics.areEqual(null, null) && this.popUpToInclusive == navOptions.popUpToInclusive && this.popUpToSaveState == navOptions.popUpToSaveState && this.enterAnim == navOptions.enterAnim && this.exitAnim == navOptions.exitAnim && this.popEnterAnim == navOptions.popEnterAnim && this.popExitAnim == navOptions.popExitAnim) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((((this.singleTop ? 1 : 0) * 31) + (this.restoreState ? 1 : 0)) * 31) + this.popUpToId) * 961) + (this.popUpToInclusive ? 1 : 0)) * 31) + (this.popUpToSaveState ? 1 : 0)) * 31) + this.enterAnim) * 31) + this.exitAnim) * 31) + this.popEnterAnim) * 31) + this.popExitAnim;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        if (this.singleTop) {
            sb.append("launchSingleTop ");
        }
        if (this.restoreState) {
            sb.append("restoreState ");
        }
        if (this.enterAnim != -1 || this.exitAnim != -1 || this.popEnterAnim != -1 || this.popExitAnim != -1) {
            sb.append("anim(enterAnim=0x");
            sb.append(Integer.toHexString(this.enterAnim));
            sb.append(" exitAnim=0x");
            sb.append(Integer.toHexString(this.exitAnim));
            sb.append(" popEnterAnim=0x");
            sb.append(Integer.toHexString(this.popEnterAnim));
            sb.append(" popExitAnim=0x");
            sb.append(Integer.toHexString(this.popExitAnim));
            sb.append(")");
        }
        return sb.toString();
    }
}
