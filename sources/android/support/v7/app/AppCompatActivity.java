package android.support.v7.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager$$ExternalSyntheticLambda0;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.widget.AppCompatTextHelper;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.core.app.ActivityCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceDataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements AppCompatCallback {
    private AppCompatDelegate mDelegate;

    public AppCompatActivity() {
        getSavedStateRegistry().registerSavedStateProvider("androidx:appcompat", new FragmentManager$$ExternalSyntheticLambda0(this, 2));
        addOnContextAvailableListener(new OnContextAvailableListener() { // from class: android.support.v7.app.AppCompatActivity.2
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public final void onContextAvailable(Context context) {
                AppCompatDelegate delegate = AppCompatActivity.this.getDelegate();
                delegate.installViewFactory();
                AppCompatActivity.this.getSavedStateRegistry().consumeRestoredStateForKey("androidx:appcompat");
                delegate.onCreate$ar$ds();
            }
        });
    }

    private final void initViewTreeOwners() {
        ViewCompat.Api24Impl.set(getWindow().getDecorView(), this);
        ViewCompat.Api26Impl.set(getWindow().getDecorView(), this);
        PreferenceDataStore.set(getWindow().getDecorView(), this);
        AppCompatTextHelper.Api24Impl.set(getWindow().getDecorView(), this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners();
        getDelegate().addContentView(view, layoutParams);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a9, code lost:
    
        if (r4 != null) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ab, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b9, code lost:
    
        if (r4 == null) goto L46;
     */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0113  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void attachBaseContext(android.content.Context r12) {
        /*
            Method dump skipped, instructions count: 725
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatActivity.attachBaseContext(android.content.Context):void");
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // android.support.v4.app.SupportActivity, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        getSupportActionBar();
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public final View findViewById(int i) {
        return getDelegate().findViewById(i);
    }

    public final AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            int i = AppCompatDelegate.sDefaultNightMode;
            this.mDelegate = new AppCompatDelegateImpl(this, null, this, this);
        }
        return this.mDelegate;
    }

    public final FloatingActionButton.ShadowDelegateImpl getDrawerToggleDelegate$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return new FloatingActionButton.ShadowDelegateImpl(getDelegate());
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        Context context;
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        if (appCompatDelegateImpl.mMenuInflater == null) {
            appCompatDelegateImpl.initWindowDecorActionBar();
            ActionBar actionBar = appCompatDelegateImpl.mActionBar;
            if (actionBar != null) {
                context = actionBar.getThemedContext();
            } else {
                context = appCompatDelegateImpl.mContext;
            }
            appCompatDelegateImpl.mMenuInflater = new SupportMenuInflater(context);
        }
        return appCompatDelegateImpl.mMenuInflater;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        return super.getResources();
    }

    public final ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public final Intent getSupportParentActivityIntent() {
        return ActivityCompat.Api28Impl.getParentActivityIntent(this);
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getDelegate().onConfigurationChanged$ar$ds();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intent parentActivityIntent;
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() == 16908332 && supportActionBar != null && (((WindowDecorActionBar) supportActionBar).mDecorToolbar$ar$class_merging.getDisplayOptions() & 4) != 0 && (parentActivityIntent = ActivityCompat.Api28Impl.getParentActivityIntent(this)) != null) {
            if (shouldUpRecreateTask(parentActivityIntent)) {
                TaskStackBuilder taskStackBuilder = new TaskStackBuilder(this);
                Intent supportParentActivityIntent = getSupportParentActivityIntent();
                if (supportParentActivityIntent == null) {
                    supportParentActivityIntent = ActivityCompat.Api28Impl.getParentActivityIntent(this);
                }
                if (supportParentActivityIntent != null) {
                    ComponentName component = supportParentActivityIntent.getComponent();
                    if (component == null) {
                        component = supportParentActivityIntent.resolveActivity(taskStackBuilder.mSourceContext.getPackageManager());
                    }
                    taskStackBuilder.addParentStack$ar$ds(component);
                    taskStackBuilder.addNextIntent$ar$ds(supportParentActivityIntent);
                }
                taskStackBuilder.startActivities();
                try {
                    finishAffinity();
                    return true;
                } catch (IllegalStateException unused) {
                    finish();
                    return true;
                }
            }
            navigateUpTo(parentActivityIntent);
            return true;
        }
        return false;
    }

    @Override // android.app.Activity
    protected final void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((AppCompatDelegateImpl) getDelegate()).ensureSubDecor();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onPostResume() {
        super.onPostResume();
        ActionBar supportActionBar = ((AppCompatDelegateImpl) getDelegate()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        ((AppCompatDelegateImpl) getDelegate()).applyApplicationSpecificConfig(true, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override // android.app.Activity
    protected final void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        getDelegate().setTitle(charSequence);
    }

    @Override // android.app.Activity
    public final void openOptionsMenu() {
        getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(int i) {
        initViewTreeOwners();
        getDelegate().setContentView(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        super.setTheme(i);
        ((AppCompatDelegateImpl) getDelegate()).mThemeResId = i;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view) {
        initViewTreeOwners();
        getDelegate().setContentView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners();
        getDelegate().setContentView(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }
}
