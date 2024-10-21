package android.support.v4.app;

import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class FragmentTransaction {
    boolean mAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList mCommitRunnables;
    public int mEnterAnim;
    public int mExitAnim;
    String mName;
    public int mPopEnterAnim;
    public int mPopExitAnim;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;
    ArrayList mOps = new ArrayList();
    boolean mAllowAddToBackStack = true;
    boolean mReorderingAllowed = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Op {
        int mCmd;
        Lifecycle.State mCurrentMaxState;
        int mEnterAnim;
        int mExitAnim;
        Fragment mFragment;
        boolean mFromExpandedOp;
        Lifecycle.State mOldMaxState;
        int mPopEnterAnim;
        int mPopExitAnim;

        public Op() {
        }

        public Op(int i, Fragment fragment) {
            this.mCmd = i;
            this.mFragment = fragment;
            this.mFromExpandedOp = false;
            this.mOldMaxState = Lifecycle.State.RESUMED;
            this.mCurrentMaxState = Lifecycle.State.RESUMED;
        }

        public Op(int i, Fragment fragment, byte[] bArr) {
            this.mCmd = i;
            this.mFragment = fragment;
            this.mFromExpandedOp = true;
            this.mOldMaxState = Lifecycle.State.RESUMED;
            this.mCurrentMaxState = Lifecycle.State.RESUMED;
        }

        public Op(Op op) {
            this.mCmd = op.mCmd;
            this.mFragment = op.mFragment;
            this.mFromExpandedOp = op.mFromExpandedOp;
            this.mEnterAnim = op.mEnterAnim;
            this.mExitAnim = op.mExitAnim;
            this.mPopEnterAnim = op.mPopEnterAnim;
            this.mPopExitAnim = op.mPopExitAnim;
            this.mOldMaxState = op.mOldMaxState;
            this.mCurrentMaxState = op.mCurrentMaxState;
        }
    }

    public final void add$ar$ds(int i, Fragment fragment) {
        doAddOp(i, fragment, null, 1);
    }

    public final void add$ar$ds$4410556b_0(Fragment fragment, String str) {
        doAddOp(0, fragment, str, 1);
    }

    public final void add$ar$ds$510d2aac_0(int i, Fragment fragment, String str) {
        doAddOp(i, fragment, str, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addOp(Op op) {
        this.mOps.add(op);
        op.mEnterAnim = this.mEnterAnim;
        op.mExitAnim = this.mExitAnim;
        op.mPopEnterAnim = this.mPopEnterAnim;
        op.mPopExitAnim = this.mPopExitAnim;
    }

    public final void addToBackStack$ar$ds(String str) {
        if (this.mAllowAddToBackStack) {
            this.mAddToBackStack = true;
            this.mName = str;
            return;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    public abstract int commit();

    public abstract void commitAllowingStateLoss$ar$ds();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    public final void disallowAddToBackStack$ar$ds() {
        if (!this.mAddToBackStack) {
            this.mAllowAddToBackStack = false;
            return;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public void doAddOp(int i, Fragment fragment, String str, int i2) {
        throw null;
    }

    public FragmentTransaction remove(Fragment fragment) {
        throw null;
    }

    public final FragmentTransaction replace(int i, Fragment fragment) {
        replace$ar$ds(i, fragment, null);
        return this;
    }

    public final void replace$ar$ds(int i, Fragment fragment, String str) {
        if (i != 0) {
            doAddOp(i, fragment, str, 2);
            return;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment) {
        throw null;
    }

    public final void setReorderingAllowed$ar$ds() {
        this.mReorderingAllowed = true;
    }
}
