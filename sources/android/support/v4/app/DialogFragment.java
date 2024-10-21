package android.support.v4.app;

import _COROUTINE._BOUNDARY;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.ComponentDialog;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.preference.PreferenceDataStore;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_INTERNAL_DIALOG_SHOWING = "android:dialogShowing";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    private int mBackStackId;
    private boolean mCancelable;
    private boolean mCreatingDialog;
    private Dialog mDialog;
    private boolean mDialogCreated;
    private Runnable mDismissRunnable;
    private boolean mDismissed;
    private Handler mHandler;
    private Observer<LifecycleOwner> mObserver;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private boolean mShownByMe;
    private boolean mShowsDialog;
    private int mStyle;
    private int mTheme;
    private boolean mViewDestroyed;

    public DialogFragment() {
        this.mDismissRunnable = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 2, null);
        this.mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: android.support.v4.app.DialogFragment.2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                if (DialogFragment.this.mDialog != null) {
                    DialogFragment dialogFragment = DialogFragment.this;
                    dialogFragment.onCancel(dialogFragment.mDialog);
                }
            }
        };
        this.mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: android.support.v4.app.DialogFragment.3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                if (DialogFragment.this.mDialog != null) {
                    DialogFragment dialogFragment = DialogFragment.this;
                    dialogFragment.onDismiss(dialogFragment.mDialog);
                }
            }
        };
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
        this.mObserver = new Observer() { // from class: android.support.v4.app.DialogFragment.4
            @Override // androidx.lifecycle.Observer
            public final /* bridge */ /* synthetic */ void onChanged(Object obj) {
                if (((LifecycleOwner) obj) != null && DialogFragment.this.mShowsDialog) {
                    View requireView = DialogFragment.this.requireView();
                    if (requireView.getParent() == null) {
                        if (DialogFragment.this.mDialog != null) {
                            if (FragmentManager.isLoggingEnabled(3)) {
                                toString();
                                Objects.toString(DialogFragment.this.mDialog);
                            }
                            DialogFragment.this.mDialog.setContentView(requireView);
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
            }
        };
        this.mDialogCreated = false;
    }

    private void dismissInternal(boolean z, boolean z2, boolean z3) {
        if (this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.mHandler.getLooper()) {
                    onDismiss(this.mDialog);
                } else {
                    this.mHandler.post(this.mDismissRunnable);
                }
            }
        }
        this.mViewDestroyed = true;
        if (this.mBackStackId >= 0) {
            if (z3) {
                FragmentManager parentFragmentManager = getParentFragmentManager();
                int i = this.mBackStackId;
                if (i >= 0) {
                    parentFragmentManager.popBackStackImmediate$ar$ds(i, 1);
                } else {
                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Bad id: "));
                }
            } else {
                FragmentManager parentFragmentManager2 = getParentFragmentManager();
                int i2 = this.mBackStackId;
                if (i2 >= 0) {
                    parentFragmentManager2.enqueueAction(new FragmentManager.PopBackStackState(null, i2), z);
                } else {
                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i2, "Bad id: "));
                }
            }
            this.mBackStackId = -1;
            return;
        }
        BackStackRecord backStackRecord = new BackStackRecord(getParentFragmentManager());
        backStackRecord.setReorderingAllowed$ar$ds();
        backStackRecord.remove(this);
        if (z3) {
            backStackRecord.commitNow();
        } else if (z) {
            backStackRecord.commitAllowingStateLoss$ar$ds();
        } else {
            backStackRecord.commit();
        }
    }

    private void prepareDialog(Bundle bundle) {
        if (this.mShowsDialog && !this.mDialogCreated) {
            try {
                this.mCreatingDialog = true;
                Dialog onCreateDialog = onCreateDialog(bundle);
                this.mDialog = onCreateDialog;
                if (this.mShowsDialog) {
                    setupDialog(onCreateDialog, this.mStyle);
                    Context context = getContext();
                    if (context instanceof Activity) {
                        this.mDialog.setOwnerActivity((Activity) context);
                    }
                    this.mDialog.setCancelable(this.mCancelable);
                    this.mDialog.setOnCancelListener(this.mOnCancelListener);
                    this.mDialog.setOnDismissListener(this.mOnDismissListener);
                    this.mDialogCreated = true;
                } else {
                    this.mDialog = null;
                }
            } finally {
                this.mCreatingDialog = false;
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public FragmentContainer createFragmentContainer() {
        final FragmentContainer createFragmentContainer = super.createFragmentContainer();
        return new FragmentContainer() { // from class: android.support.v4.app.DialogFragment.5
            @Override // android.support.v4.app.FragmentContainer
            public final View onFindViewById(int i) {
                FragmentContainer fragmentContainer = createFragmentContainer;
                if (fragmentContainer.onHasView()) {
                    return fragmentContainer.onFindViewById(i);
                }
                return DialogFragment.this.onFindViewById(i);
            }

            @Override // android.support.v4.app.FragmentContainer
            public final boolean onHasView() {
                if (!createFragmentContainer.onHasView() && !DialogFragment.this.onHasView()) {
                    return false;
                }
                return true;
            }
        };
    }

    public void dismiss() {
        dismissInternal(false, false, false);
    }

    public void dismissAllowingStateLoss() {
        dismissInternal(true, false, false);
    }

    public void dismissNow() {
        dismissInternal(false, false, true);
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // android.support.v4.app.Fragment
    @Deprecated
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LiveData<LifecycleOwner> viewLifecycleOwnerLiveData = getViewLifecycleOwnerLiveData();
        Observer<LifecycleOwner> observer = this.mObserver;
        LiveData.assertMainThread("observeForever");
        LiveData.AlwaysActiveObserver alwaysActiveObserver = new LiveData.AlwaysActiveObserver(viewLifecycleOwnerLiveData, observer);
        LiveData.ObserverWrapper observerWrapper = (LiveData.ObserverWrapper) viewLifecycleOwnerLiveData.mObservers.putIfAbsent(observer, alwaysActiveObserver);
        if (!(observerWrapper instanceof LiveData.LifecycleBoundObserver)) {
            if (observerWrapper == null) {
                alwaysActiveObserver.activeStateChanged(true);
            }
            if (!this.mShownByMe) {
                this.mDismissed = false;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        this.mHandler = new Handler();
        if (this.mContainerId == 0) {
            z = true;
        } else {
            z = false;
        }
        this.mShowsDialog = z;
        if (bundle != null) {
            this.mStyle = bundle.getInt(SAVED_STYLE, 0);
            this.mTheme = bundle.getInt(SAVED_THEME, 0);
            this.mCancelable = bundle.getBoolean(SAVED_CANCELABLE, true);
            this.mShowsDialog = bundle.getBoolean(SAVED_SHOWS_DIALOG, this.mShowsDialog);
            this.mBackStackId = bundle.getInt(SAVED_BACK_STACK_ID, -1);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (FragmentManager.isLoggingEnabled(3)) {
            toString();
        }
        return new ComponentDialog(requireContext(), getTheme());
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = true;
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!this.mDismissed) {
                onDismiss(this.mDialog);
            }
            this.mDialog = null;
            this.mDialogCreated = false;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
        getViewLifecycleOwnerLiveData().removeObserver(this.mObserver);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.mViewDestroyed) {
            if (FragmentManager.isLoggingEnabled(3)) {
                toString();
            }
            dismissInternal(true, true, false);
        }
    }

    public View onFindViewById(int i) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            return dialog.findViewById(i);
        }
        return null;
    }

    @Override // android.support.v4.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        LayoutInflater onGetLayoutInflater = super.onGetLayoutInflater(bundle);
        if (this.mShowsDialog && !this.mCreatingDialog) {
            prepareDialog(bundle);
            if (FragmentManager.isLoggingEnabled(2)) {
                toString();
            }
            Dialog dialog = this.mDialog;
            if (dialog != null) {
                return onGetLayoutInflater.cloneInContext(dialog.getContext());
            }
        } else if (FragmentManager.isLoggingEnabled(2)) {
            toString();
        }
        return onGetLayoutInflater;
    }

    public boolean onHasView() {
        return this.mDialogCreated;
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Bundle onSaveInstanceState = dialog.onSaveInstanceState();
            onSaveInstanceState.putBoolean(SAVED_INTERNAL_DIALOG_SHOWING, false);
            bundle.putBundle(SAVED_DIALOG_STATE_TAG, onSaveInstanceState);
        }
        int i = this.mStyle;
        if (i != 0) {
            bundle.putInt(SAVED_STYLE, i);
        }
        int i2 = this.mTheme;
        if (i2 != 0) {
            bundle.putInt(SAVED_THEME, i2);
        }
        if (!this.mCancelable) {
            bundle.putBoolean(SAVED_CANCELABLE, false);
        }
        if (!this.mShowsDialog) {
            bundle.putBoolean(SAVED_SHOWS_DIALOG, false);
        }
        int i3 = this.mBackStackId;
        if (i3 != -1) {
            bundle.putInt(SAVED_BACK_STACK_ID, i3);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = false;
            dialog.show();
            View decorView = this.mDialog.getWindow().getDecorView();
            ViewCompat.Api24Impl.set(decorView, this);
            ViewCompat.Api26Impl.set(decorView, this);
            PreferenceDataStore.set(decorView, this);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        Bundle bundle2;
        super.onViewStateRestored(bundle);
        if (this.mDialog != null && bundle != null && (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) != null) {
            this.mDialog.onRestoreInstanceState(bundle2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.performCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView == null && this.mDialog != null && bundle != null && (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) != null) {
            this.mDialog.onRestoreInstanceState(bundle2);
        }
    }

    public final ComponentDialog requireComponentDialog() {
        Dialog requireDialog = requireDialog();
        if (requireDialog instanceof ComponentDialog) {
            return (ComponentDialog) requireDialog;
        }
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(requireDialog, this, "DialogFragment ", " did not return a ComponentDialog instance from requireDialog(). The actual Dialog is "));
    }

    public final Dialog requireDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(this, "DialogFragment ", " does not have a Dialog."));
    }

    public void setCancelable(boolean z) {
        this.mCancelable = z;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCancelable(z);
        }
    }

    public void setShowsDialog(boolean z) {
        this.mShowsDialog = z;
    }

    public void setStyle(int i, int i2) {
        if (FragmentManager.isLoggingEnabled(2)) {
            toString();
        }
        this.mStyle = i;
        if (i == 2 || i == 3) {
            this.mTheme = R.style.Theme.Panel;
        }
        if (i2 != 0) {
            this.mTheme = i2;
        }
    }

    public void setupDialog(Dialog dialog, int i) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void show(FragmentManager fragmentManager, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        backStackRecord.setReorderingAllowed$ar$ds();
        backStackRecord.add$ar$ds$4410556b_0(this, str);
        backStackRecord.commit();
    }

    public void showNow(FragmentManager fragmentManager, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        backStackRecord.setReorderingAllowed$ar$ds();
        backStackRecord.add$ar$ds$4410556b_0(this, str);
        backStackRecord.commitNow();
    }

    public DialogFragment(int i) {
        super(i);
        this.mDismissRunnable = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 2, null);
        this.mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: android.support.v4.app.DialogFragment.2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                if (DialogFragment.this.mDialog != null) {
                    DialogFragment dialogFragment = DialogFragment.this;
                    dialogFragment.onCancel(dialogFragment.mDialog);
                }
            }
        };
        this.mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: android.support.v4.app.DialogFragment.3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                if (DialogFragment.this.mDialog != null) {
                    DialogFragment dialogFragment = DialogFragment.this;
                    dialogFragment.onDismiss(dialogFragment.mDialog);
                }
            }
        };
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
        this.mObserver = new Observer() { // from class: android.support.v4.app.DialogFragment.4
            @Override // androidx.lifecycle.Observer
            public final /* bridge */ /* synthetic */ void onChanged(Object obj) {
                if (((LifecycleOwner) obj) != null && DialogFragment.this.mShowsDialog) {
                    View requireView = DialogFragment.this.requireView();
                    if (requireView.getParent() == null) {
                        if (DialogFragment.this.mDialog != null) {
                            if (FragmentManager.isLoggingEnabled(3)) {
                                toString();
                                Objects.toString(DialogFragment.this.mDialog);
                            }
                            DialogFragment.this.mDialog.setContentView(requireView);
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
            }
        };
        this.mDialogCreated = false;
    }

    public int show(FragmentTransaction fragmentTransaction, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        fragmentTransaction.add$ar$ds$4410556b_0(this, str);
        this.mViewDestroyed = false;
        int commit = fragmentTransaction.commit();
        this.mBackStackId = commit;
        return commit;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }
}
