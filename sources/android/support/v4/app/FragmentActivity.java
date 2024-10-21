package android.support.v4.app;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.ComponentActivity$$ExternalSyntheticLambda4;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.PrintWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity {
    boolean mCreated;
    boolean mResumed;
    public final AccessibilityNodeInfoCompat.CollectionItemInfoCompat mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(new HostCallbacks());
    final LifecycleRegistry mFragmentLifecycleRegistry = new LifecycleRegistry(this);
    boolean mStopped = true;

    public FragmentActivity() {
        int i = 1;
        getSavedStateRegistry().registerSavedStateProvider("android:support:lifecycle", new FragmentManager$$ExternalSyntheticLambda0(this, i));
        addOnConfigurationChangedListener(new FragmentActivity$$ExternalSyntheticLambda2(this, i));
        this.onNewIntentListeners.add(new FragmentActivity$$ExternalSyntheticLambda2(this, 0));
        addOnContextAvailableListener(new ComponentActivity$$ExternalSyntheticLambda4(this, i));
    }

    private static boolean markState(FragmentManager fragmentManager, Lifecycle.State state) {
        boolean z = false;
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null) {
                if (fragment.getHost() != null) {
                    z |= markState(fragment.getChildFragmentManager(), state);
                }
                FragmentViewLifecycleOwner fragmentViewLifecycleOwner = fragment.mViewLifecycleOwner;
                if (fragmentViewLifecycleOwner != null && fragmentViewLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    fragment.mViewLifecycleOwner.mLifecycleRegistry.setCurrentState(state);
                    z = true;
                }
                if (fragment.mLifecycleRegistry.state.isAtLeast(Lifecycle.State.STARTED)) {
                    fragment.mLifecycleRegistry.setCurrentState(state);
                    z = true;
                }
            }
        }
        return z;
    }

    final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.mLayoutInflaterFactory.onCreateView(view, str, context, attributeSet);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0032, code lost:
    
        if (r0.equals("--list-dumpables") != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 33) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003b, code lost:
    
        if (r0.equals("--dump-dumpable") == false) goto L32;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0010. Please report as an issue. */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dump(java.lang.String r3, java.io.FileDescriptor r4, java.io.PrintWriter r5, java.lang.String[] r6) {
        /*
            r2 = this;
            super.dump(r3, r4, r5, r6)
            if (r6 == 0) goto L54
            int r0 = r6.length
            if (r0 != 0) goto L9
            goto L54
        L9:
            r0 = 0
            r0 = r6[r0]
            int r1 = r0.hashCode()
            switch(r1) {
                case -645125871: goto L45;
                case 100470631: goto L35;
                case 472614934: goto L2c;
                case 1159329357: goto L1d;
                case 1455016274: goto L14;
                default: goto L13;
            }
        L13:
            goto L54
        L14:
            java.lang.String r1 = "--autofill"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L53
            goto L54
        L1d:
            java.lang.String r1 = "--contentcapture"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L54
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 < r1) goto L54
            goto L53
        L2c:
            java.lang.String r1 = "--list-dumpables"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L54
            goto L3e
        L35:
            java.lang.String r1 = "--dump-dumpable"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3e
            goto L54
        L3e:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            if (r0 < r1) goto L54
            goto L53
        L45:
            java.lang.String r1 = "--translation"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L54
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 31
            if (r0 < r1) goto L54
        L53:
            return
        L54:
            r5.print(r3)
            java.lang.String r0 = "Local FragmentActivity "
            r5.print(r0)
            int r0 = java.lang.System.identityHashCode(r2)
            java.lang.String r0 = java.lang.Integer.toHexString(r0)
            r5.print(r0)
            java.lang.String r0 = " State:"
            r5.println(r0)
            java.lang.String r0 = java.lang.String.valueOf(r3)
            java.lang.String r1 = "  "
            java.lang.String r0 = r0.concat(r1)
            r5.print(r0)
            java.lang.String r1 = "mCreated="
            r5.print(r1)
            boolean r1 = r2.mCreated
            r5.print(r1)
            java.lang.String r1 = " mResumed="
            r5.print(r1)
            boolean r1 = r2.mResumed
            r5.print(r1)
            java.lang.String r1 = " mStopped="
            r5.print(r1)
            boolean r1 = r2.mStopped
            r5.print(r1)
            android.app.Application r1 = r2.getApplication()
            if (r1 == 0) goto La4
            androidx.loader.app.LoaderManager r1 = androidx.loader.app.LoaderManager.getInstance(r2)
            r1.dump$ar$ds(r0, r5)
        La4:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r0 = r2.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
            android.support.v4.app.FragmentManager r0 = r0.getSupportFragmentManager()
            r0.dump(r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentActivity.dump(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }

    public final FragmentManager getSupportFragmentManager() {
        return this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getSupportFragmentManager();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void markFragmentsCreated() {
        do {
        } while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.noteStateNotSaved();
        super.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchCreate();
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchDestroy();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 6) {
            return ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchContextItemSelected(menuItem);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchPause();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchResume();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.noteStateNotSaved();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.noteStateNotSaved();
        super.onResume();
        this.mResumed = true;
        this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.execPendingActions$ar$ds();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStart() {
        this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.noteStateNotSaved();
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchActivityCreated();
        }
        this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.execPendingActions$ar$ds();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchStart();
    }

    @Override // android.app.Activity
    public final void onStateNotSaved() {
        this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.noteStateNotSaved();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        ((FragmentHostCallback) this.mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo).fragmentManager.dispatchStop();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class HostCallbacks extends FragmentHostCallback implements OnConfigurationChangedProvider, OnTrimMemoryProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, ViewModelStoreOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, SavedStateRegistryOwner, FragmentOnAttachListener, MenuHost {
        public HostCallbacks() {
            super(FragmentActivity.this, FragmentActivity.this, new Handler());
        }

        @Override // androidx.core.view.MenuHost
        public final void addMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
            FragmentActivity.this.addMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(shadowDelegateImpl);
        }

        @Override // androidx.core.content.OnConfigurationChangedProvider
        public final void addOnConfigurationChangedListener(Consumer consumer) {
            FragmentActivity.this.addOnConfigurationChangedListener(consumer);
        }

        @Override // android.support.v4.app.OnMultiWindowModeChangedProvider
        public final void addOnMultiWindowModeChangedListener(Consumer consumer) {
            FragmentActivity.this.addOnMultiWindowModeChangedListener(consumer);
        }

        @Override // android.support.v4.app.OnPictureInPictureModeChangedProvider
        public final void addOnPictureInPictureModeChangedListener(Consumer consumer) {
            FragmentActivity.this.addOnPictureInPictureModeChangedListener(consumer);
        }

        @Override // androidx.core.content.OnTrimMemoryProvider
        public final void addOnTrimMemoryListener(Consumer consumer) {
            FragmentActivity.this.addOnTrimMemoryListener(consumer);
        }

        @Override // androidx.activity.result.ActivityResultRegistryOwner
        public final ActivityResultRegistry getActivityResultRegistry() {
            return FragmentActivity.this.activityResultRegistry;
        }

        @Override // androidx.lifecycle.LifecycleOwner
        public final Lifecycle getLifecycle() {
            return FragmentActivity.this.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.OnBackPressedDispatcherOwner
        public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return FragmentActivity.this.getOnBackPressedDispatcher();
        }

        @Override // androidx.savedstate.SavedStateRegistryOwner
        public final SavedStateRegistry getSavedStateRegistry() {
            return FragmentActivity.this.getSavedStateRegistry();
        }

        @Override // androidx.lifecycle.ViewModelStoreOwner
        public final ViewModelStore getViewModelStore() {
            return FragmentActivity.this.getViewModelStore();
        }

        @Override // android.support.v4.app.FragmentHostCallback
        public final void onDump$ar$ds(PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump("  ", null, printWriter, strArr);
        }

        @Override // android.support.v4.app.FragmentHostCallback, android.support.v4.app.FragmentContainer
        public final View onFindViewById(int i) {
            return FragmentActivity.this.findViewById(i);
        }

        @Override // android.support.v4.app.FragmentHostCallback
        public final /* synthetic */ Object onGetHost() {
            return FragmentActivity.this;
        }

        @Override // android.support.v4.app.FragmentHostCallback
        public final LayoutInflater onGetLayoutInflater() {
            FragmentActivity fragmentActivity = FragmentActivity.this;
            return fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
        }

        @Override // android.support.v4.app.FragmentHostCallback, android.support.v4.app.FragmentContainer
        public final boolean onHasView() {
            Window window = FragmentActivity.this.getWindow();
            if (window != null && window.peekDecorView() != null) {
                return true;
            }
            return false;
        }

        @Override // android.support.v4.app.FragmentHostCallback
        public final boolean onShouldShowRequestPermissionRationale(String str) {
            if (Build.VERSION.SDK_INT < 33 && TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
                return false;
            }
            FragmentActivity fragmentActivity = FragmentActivity.this;
            if (Build.VERSION.SDK_INT >= 32) {
                return ActivityCompat.Api32Impl.shouldShowRequestPermissionRationale(fragmentActivity, str);
            }
            if (Build.VERSION.SDK_INT == 31) {
                return ActivityCompat.Api31Impl.shouldShowRequestPermissionRationale(fragmentActivity, str);
            }
            return ActivityCompat.Api23Impl.shouldShowRequestPermissionRationale(fragmentActivity, str);
        }

        @Override // android.support.v4.app.FragmentHostCallback
        public final void onSupportInvalidateOptionsMenu() {
            FragmentActivity.this.invalidateOptionsMenu();
        }

        @Override // androidx.core.view.MenuHost
        public final void removeMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
            FragmentActivity.this.removeMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(shadowDelegateImpl);
        }

        @Override // androidx.core.content.OnConfigurationChangedProvider
        public final void removeOnConfigurationChangedListener(Consumer consumer) {
            FragmentActivity.this.removeOnConfigurationChangedListener(consumer);
        }

        @Override // android.support.v4.app.OnMultiWindowModeChangedProvider
        public final void removeOnMultiWindowModeChangedListener(Consumer consumer) {
            FragmentActivity.this.removeOnMultiWindowModeChangedListener(consumer);
        }

        @Override // android.support.v4.app.OnPictureInPictureModeChangedProvider
        public final void removeOnPictureInPictureModeChangedListener(Consumer consumer) {
            FragmentActivity.this.removeOnPictureInPictureModeChangedListener(consumer);
        }

        @Override // androidx.core.content.OnTrimMemoryProvider
        public final void removeOnTrimMemoryListener(Consumer consumer) {
            FragmentActivity.this.removeOnTrimMemoryListener(consumer);
        }

        @Override // android.support.v4.app.FragmentOnAttachListener
        public final void onAttachFragment$ar$ds(Fragment fragment) {
        }
    }
}
