package android.support.v4.app;

import android.content.res.Resources;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.view.menu.CascadingMenuPopup;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.fragment.app.strictmode.WrongFragmentContainerViolation;
import androidx.fragment.app.strictmode.WrongNestedHierarchyViolation;
import androidx.work.impl.model.WorkName;
import com.google.android.marvin.talkback.R;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentStateManager {
    private final WorkName mDispatcher$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Fragment mFragment;
    private final FragmentStore mFragmentStore;
    private boolean mMovingToState = false;
    public int mFragmentManagerState = -1;

    public FragmentStateManager(WorkName workName, FragmentStore fragmentStore, Fragment fragment) {
        this.mDispatcher$ar$class_merging$ar$class_merging$ar$class_merging = workName;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addViewToContainer() {
        Fragment fragment;
        View view;
        View view2;
        View view3 = this.mFragment.mContainer;
        while (true) {
            fragment = null;
            if (view3 == null) {
                break;
            }
            Fragment viewFragment = FragmentManager.getViewFragment(view3);
            if (viewFragment != null) {
                fragment = viewFragment;
                break;
            }
            Object parent = view3.getParent();
            if (parent instanceof View) {
                view3 = (View) parent;
            } else {
                view3 = null;
            }
        }
        Fragment parentFragment = this.mFragment.getParentFragment();
        if (fragment != null && !fragment.equals(parentFragment)) {
            Fragment fragment2 = this.mFragment;
            int i = fragment2.mContainerId;
            FragmentStrictMode fragmentStrictMode = FragmentStrictMode.INSTANCE;
            fragment2.getClass();
            WrongNestedHierarchyViolation wrongNestedHierarchyViolation = new WrongNestedHierarchyViolation(fragment2, fragment, i);
            FragmentStrictMode.logIfDebuggingEnabled$ar$ds(wrongNestedHierarchyViolation);
            FragmentStrictMode.Policy nearestPolicy$ar$ds = FragmentStrictMode.getNearestPolicy$ar$ds(fragment2);
            if (nearestPolicy$ar$ds.flags.contains(FragmentStrictMode.Flag.DETECT_WRONG_NESTED_HIERARCHY) && FragmentStrictMode.shouldHandlePolicyViolation$ar$ds(nearestPolicy$ar$ds, fragment2.getClass(), wrongNestedHierarchyViolation.getClass())) {
                FragmentStrictMode.handlePolicyViolation$ar$ds(nearestPolicy$ar$ds, wrongNestedHierarchyViolation);
            }
        }
        FragmentStore fragmentStore = this.mFragmentStore;
        Fragment fragment3 = this.mFragment;
        ViewGroup viewGroup = fragment3.mContainer;
        int i2 = -1;
        if (viewGroup != null) {
            int indexOf = fragmentStore.mAdded.indexOf(fragment3);
            int i3 = indexOf - 1;
            while (true) {
                if (i3 < 0) {
                    while (true) {
                        indexOf++;
                        if (indexOf >= fragmentStore.mAdded.size()) {
                            break;
                        }
                        Fragment fragment4 = (Fragment) fragmentStore.mAdded.get(indexOf);
                        if (fragment4.mContainer == viewGroup && (view = fragment4.mView) != null) {
                            i2 = viewGroup.indexOfChild(view);
                            break;
                        }
                    }
                } else {
                    Fragment fragment5 = (Fragment) fragmentStore.mAdded.get(i3);
                    if (fragment5.mContainer == viewGroup && (view2 = fragment5.mView) != null) {
                        i2 = viewGroup.indexOfChild(view2) + 1;
                        break;
                    }
                    i3--;
                }
            }
        }
        Fragment fragment6 = this.mFragment;
        fragment6.mContainer.addView(fragment6.mView, i2);
    }

    final void createView() {
        Bundle bundle;
        String str;
        if (this.mFragment.mFromLayout) {
            return;
        }
        if (FragmentManager.isLoggingEnabled(3)) {
            Objects.toString(this.mFragment);
        }
        Bundle bundle2 = this.mFragment.mSavedFragmentState;
        ViewGroup viewGroup = null;
        if (bundle2 != null) {
            bundle = bundle2.getBundle("savedInstanceState");
        } else {
            bundle = null;
        }
        Fragment fragment = this.mFragment;
        LayoutInflater performGetLayoutInflater = fragment.performGetLayoutInflater(bundle);
        ViewGroup viewGroup2 = fragment.mContainer;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i = fragment.mContainerId;
            if (i != 0) {
                if (i != -1) {
                    viewGroup = (ViewGroup) fragment.mFragmentManager.mContainer.onFindViewById(i);
                    if (viewGroup == null) {
                        Fragment fragment2 = this.mFragment;
                        if (!fragment2.mRestored) {
                            try {
                                str = fragment2.getResources().getResourceName(this.mFragment.mContainerId);
                            } catch (Resources.NotFoundException unused) {
                                str = "unknown";
                            }
                            throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.mFragment.mContainerId) + " (" + str + ") for fragment " + this.mFragment);
                        }
                    } else if (!(viewGroup instanceof FragmentContainerView)) {
                        Fragment fragment3 = this.mFragment;
                        FragmentStrictMode fragmentStrictMode = FragmentStrictMode.INSTANCE;
                        fragment3.getClass();
                        WrongFragmentContainerViolation wrongFragmentContainerViolation = new WrongFragmentContainerViolation(fragment3, viewGroup);
                        FragmentStrictMode.logIfDebuggingEnabled$ar$ds(wrongFragmentContainerViolation);
                        FragmentStrictMode.Policy nearestPolicy$ar$ds = FragmentStrictMode.getNearestPolicy$ar$ds(fragment3);
                        if (nearestPolicy$ar$ds.flags.contains(FragmentStrictMode.Flag.DETECT_WRONG_FRAGMENT_CONTAINER) && FragmentStrictMode.shouldHandlePolicyViolation$ar$ds(nearestPolicy$ar$ds, fragment3.getClass(), wrongFragmentContainerViolation.getClass())) {
                            FragmentStrictMode.handlePolicyViolation$ar$ds(nearestPolicy$ar$ds, wrongFragmentContainerViolation);
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Cannot create fragment " + this.mFragment + " for a container view with no id");
                }
            }
        }
        Fragment fragment4 = this.mFragment;
        fragment4.mContainer = viewGroup;
        fragment4.performCreateView(performGetLayoutInflater, viewGroup, bundle);
        if (this.mFragment.mView != null) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Objects.toString(this.mFragment);
            }
            this.mFragment.mView.setSaveFromParentEnabled(false);
            Fragment fragment5 = this.mFragment;
            fragment5.mView.setTag(R.id.fragment_container_view_tag, fragment5);
            if (viewGroup != null) {
                addViewToContainer();
            }
            Fragment fragment6 = this.mFragment;
            if (fragment6.mHidden) {
                fragment6.mView.setVisibility(8);
            }
            if (this.mFragment.mView.isAttachedToWindow()) {
                ViewCompat.Api20Impl.requestApplyInsets(this.mFragment.mView);
            } else {
                View view = this.mFragment.mView;
                view.addOnAttachStateChangeListener(new CascadingMenuPopup.AnonymousClass2(view, 1));
            }
            this.mFragment.performViewCreated();
            WorkName workName = this.mDispatcher$ar$class_merging$ar$class_merging$ar$class_merging;
            Fragment fragment7 = this.mFragment;
            workName.dispatchOnFragmentViewCreated(fragment7, fragment7.mView, bundle, false);
            int visibility = this.mFragment.mView.getVisibility();
            this.mFragment.setPostOnViewCreatedAlpha(this.mFragment.mView.getAlpha());
            Fragment fragment8 = this.mFragment;
            if (fragment8.mContainer != null && visibility == 0) {
                View findFocus = fragment8.mView.findFocus();
                if (findFocus != null) {
                    this.mFragment.setFocusedView(findFocus);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Objects.toString(findFocus);
                        Objects.toString(this.mFragment);
                    }
                }
                this.mFragment.mView.setAlpha(0.0f);
            }
        }
        this.mFragment.mState = 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void ensureInflatedView() {
        Bundle bundle;
        Fragment fragment = this.mFragment;
        if (fragment.mFromLayout && fragment.mInLayout && !fragment.mPerformedCreateView) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Objects.toString(this.mFragment);
            }
            Bundle bundle2 = this.mFragment.mSavedFragmentState;
            if (bundle2 != null) {
                bundle = bundle2.getBundle("savedInstanceState");
            } else {
                bundle = null;
            }
            Fragment fragment2 = this.mFragment;
            fragment2.performCreateView(fragment2.performGetLayoutInflater(bundle), null, bundle);
            View view = this.mFragment.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.mFragment;
                fragment3.mView.setTag(R.id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.mFragment;
                if (fragment4.mHidden) {
                    fragment4.mView.setVisibility(8);
                }
                this.mFragment.performViewCreated();
                WorkName workName = this.mDispatcher$ar$class_merging$ar$class_merging$ar$class_merging;
                Fragment fragment5 = this.mFragment;
                workName.dispatchOnFragmentViewCreated(fragment5, fragment5.mView, bundle, false);
                this.mFragment.mState = 2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02d3, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x02ad, code lost:
    
        r3.moveToExpectedState();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void moveToExpectedState() {
        /*
            Method dump skipped, instructions count: 1430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentStateManager.moveToExpectedState():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void restoreState(ClassLoader classLoader) {
        Bundle bundle = this.mFragment.mSavedFragmentState;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            if (this.mFragment.mSavedFragmentState.getBundle("savedInstanceState") == null) {
                this.mFragment.mSavedFragmentState.putBundle("savedInstanceState", new Bundle());
            }
            try {
                Fragment fragment = this.mFragment;
                fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("viewState");
                Fragment fragment2 = this.mFragment;
                fragment2.mSavedViewRegistryState = fragment2.mSavedFragmentState.getBundle("viewRegistryState");
                FragmentState fragmentState = (FragmentState) this.mFragment.mSavedFragmentState.getParcelable("state");
                if (fragmentState != null) {
                    Fragment fragment3 = this.mFragment;
                    fragment3.mTargetWho = fragmentState.mTargetWho;
                    fragment3.mTargetRequestCode = fragmentState.mTargetRequestCode;
                    Boolean bool = fragment3.mSavedUserVisibleHint;
                    if (bool != null) {
                        fragment3.mUserVisibleHint = bool.booleanValue();
                        this.mFragment.mSavedUserVisibleHint = null;
                    } else {
                        fragment3.mUserVisibleHint = fragmentState.mUserVisibleHint;
                    }
                }
                Fragment fragment4 = this.mFragment;
                if (!fragment4.mUserVisibleHint) {
                    fragment4.mDeferStart = true;
                }
            } catch (BadParcelableException e) {
                Fragment fragment5 = this.mFragment;
                Objects.toString(fragment5);
                throw new IllegalStateException("Failed to restore view hierarchy state for fragment ".concat(String.valueOf(fragment5)), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle saveState() {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        Fragment fragment = this.mFragment;
        if (fragment.mState == -1 && (bundle = fragment.mSavedFragmentState) != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("state", new FragmentState(this.mFragment));
        if (this.mFragment.mState >= 0) {
            Bundle bundle3 = new Bundle();
            this.mFragment.performSaveInstanceState(bundle3);
            if (!bundle3.isEmpty()) {
                bundle2.putBundle("savedInstanceState", bundle3);
            }
            this.mDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentSaveInstanceState(this.mFragment, bundle3, false);
            Bundle bundle4 = new Bundle();
            this.mFragment.mSavedStateRegistryController.performSave(bundle4);
            if (!bundle4.isEmpty()) {
                bundle2.putBundle("registryState", bundle4);
            }
            Bundle saveAllStateInternal = this.mFragment.mChildFragmentManager.saveAllStateInternal();
            if (!saveAllStateInternal.isEmpty()) {
                bundle2.putBundle("childFragmentManager", saveAllStateInternal);
            }
            if (this.mFragment.mView != null) {
                saveViewState();
            }
            SparseArray<Parcelable> sparseArray = this.mFragment.mSavedViewState;
            if (sparseArray != null) {
                bundle2.putSparseParcelableArray("viewState", sparseArray);
            }
            Bundle bundle5 = this.mFragment.mSavedViewRegistryState;
            if (bundle5 != null) {
                bundle2.putBundle("viewRegistryState", bundle5);
            }
        }
        Bundle bundle6 = this.mFragment.mArguments;
        if (bundle6 != null) {
            bundle2.putBundle("arguments", bundle6);
        }
        return bundle2;
    }

    final void saveViewState() {
        if (this.mFragment.mView != null) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Objects.toString(this.mFragment);
                Objects.toString(this.mFragment.mView);
            }
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.mFragment.mView.saveHierarchyState(sparseArray);
            if (sparseArray.size() > 0) {
                this.mFragment.mSavedViewState = sparseArray;
            }
            Bundle bundle = new Bundle();
            this.mFragment.mViewLifecycleOwner.mSavedStateRegistryController.performSave(bundle);
            if (!bundle.isEmpty()) {
                this.mFragment.mSavedViewRegistryState = bundle;
            }
        }
    }

    public FragmentStateManager(WorkName workName, FragmentStore fragmentStore, Fragment fragment, Bundle bundle) {
        this.mDispatcher$ar$class_merging$ar$class_merging$ar$class_merging = workName;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        Fragment fragment2 = fragment.mTarget;
        fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
        fragment.mTarget = null;
        fragment.mSavedFragmentState = bundle;
        fragment.mArguments = bundle.getBundle("arguments");
    }

    public FragmentStateManager(WorkName workName, FragmentStore fragmentStore, ClassLoader classLoader, FragmentFactory fragmentFactory, Bundle bundle) {
        this.mDispatcher$ar$class_merging$ar$class_merging$ar$class_merging = workName;
        this.mFragmentStore = fragmentStore;
        Fragment instantiate = ((FragmentState) bundle.getParcelable("state")).instantiate(fragmentFactory, classLoader);
        this.mFragment = instantiate;
        instantiate.mSavedFragmentState = bundle;
        Bundle bundle2 = bundle.getBundle("arguments");
        if (bundle2 != null) {
            bundle2.setClassLoader(classLoader);
        }
        instantiate.setArguments(bundle2);
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(instantiate);
        }
    }
}
