package com.google.android.libraries.mdi.download.debug.dagger;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.core.app.ActivityCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.DialogFragmentNavigator;
import androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener;
import com.google.android.marvin.talkback.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;
import dagger.android.support.DaggerAppCompatActivity;
import java.lang.ref.WeakReference;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugActivity extends DaggerAppCompatActivity {
    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Rect rect = new Rect();
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (currentFocus instanceof EditText) {
                rect.setEmpty();
                currentFocus.getGlobalVisibleRect(rect);
                if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                    currentFocus.clearFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) currentFocus.getContext().getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // dagger.android.support.DaggerAppCompatActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        Object findViewById;
        super.onCreate(bundle);
        setContentView(R.layout.mdd_debug_activity);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        int[] iArr = {R.id.navigation_filegroups, R.id.navigation_debuginfo};
        HashSet hashSet = new HashSet();
        for (int i = 0; i < 2; i++) {
            hashSet.add(Integer.valueOf(iArr[i]));
        }
        ViewModelStore viewModelStore = new ViewModelStore(hashSet);
        if (Build.VERSION.SDK_INT >= 28) {
            findViewById = ActivityCompat.Api28Impl.requireViewById(this, R.id.nav_host);
        } else {
            findViewById = findViewById(R.id.nav_host);
            if (findViewById == null) {
                throw new IllegalArgumentException("ID does not reference a View inside this Activity");
            }
        }
        findViewById.getClass();
        final NavController findViewNavController$ar$ds = Navigation.findViewNavController$ar$ds((View) findViewById);
        if (findViewNavController$ar$ds != null) {
            findViewNavController$ar$ds.setGraph(R.navigation.debug_navigation);
            findViewNavController$ar$ds.addOnDestinationChangedListener(new AbstractAppBarOnDestinationChangedListener((AppCompatActivity) this, viewModelStore));
            bottomNavigationView.getClass();
            bottomNavigationView.selectedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(findViewNavController$ar$ds, null);
            final WeakReference weakReference = new WeakReference(bottomNavigationView);
            findViewNavController$ar$ds.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() { // from class: androidx.navigation.ui.NavigationUI$setupWithNavController$12
                @Override // androidx.navigation.NavController.OnDestinationChangedListener
                public final void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle2) {
                    navDestination.getClass();
                    NavigationBarView navigationBarView = (NavigationBarView) weakReference.get();
                    if (navigationBarView == null) {
                        findViewNavController$ar$ds.removeOnDestinationChangedListener(this);
                        return;
                    }
                    if (!(navDestination instanceof DialogFragmentNavigator.Destination)) {
                        NavigationBarMenu navigationBarMenu = navigationBarView.menu;
                        navigationBarMenu.getClass();
                        int size = navigationBarMenu.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            MenuItem item = navigationBarMenu.getItem(i2);
                            item.getClass();
                            if (AccessibilityNodeInfoCompat.Api34Impl.matchDestination$navigation_ui_release(navDestination, item.getItemId())) {
                                item.setChecked(true);
                            }
                        }
                    }
                }
            });
            return;
        }
        throw new IllegalStateException("Activity " + this + " does not have a NavController set on 2131427928");
    }
}
