package com.android.settingslib.collapsingtoolbar;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.work.impl.utils.NetworkApi24;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.CollapsingTextHelper;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CollapsingToolbarBaseActivity extends FragmentActivity {
    private ClientSettings.Builder mToolbardelegate$ar$class_merging;

    private final ClientSettings.Builder getToolbarDelegate$ar$class_merging() {
        if (this.mToolbardelegate$ar$class_merging == null) {
            this.mToolbardelegate$ar$class_merging = new ClientSettings.Builder(new FloatingActionButton.ShadowDelegateImpl(this));
        }
        return this.mToolbardelegate$ar$class_merging;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finishAfterTransition();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ClientSettings.Builder toolbarDelegate$ar$class_merging = getToolbarDelegate$ar$class_merging();
        View inflate = getLayoutInflater().inflate(R.layout.collapsing_toolbar_base_layout, (ViewGroup) null, false);
        if (inflate instanceof CoordinatorLayout) {
        }
        toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$realClientPackageName = (CollapsingToolbarLayout) inflate.findViewById(R.id.collapsing_toolbar);
        toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$realClientClassName = (AppBarLayout) inflate.findViewById(R.id.app_bar);
        Object obj = toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$realClientPackageName;
        if (obj != null) {
            ((CollapsingToolbarLayout) obj).collapsingTextHelper.lineSpacingMultiplier = 1.1f;
            if (Build.VERSION.SDK_INT >= 33) {
                CollapsingTextHelper collapsingTextHelper = ((CollapsingToolbarLayout) toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$realClientPackageName).collapsingTextHelper;
                collapsingTextHelper.hyphenationFrequency = 3;
                NetworkApi24 networkApi24 = new NetworkApi24();
                if (collapsingTextHelper.staticLayoutBuilderConfigurer$ar$class_merging$ar$class_merging != networkApi24) {
                    collapsingTextHelper.staticLayoutBuilderConfigurer$ar$class_merging$ar$class_merging = networkApi24;
                    collapsingTextHelper.recalculate(true);
                }
            }
        }
        Object obj2 = toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$realClientClassName;
        if (obj2 != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) ((AppBarLayout) obj2).getLayoutParams();
            AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
            behavior.onDragCallback$ar$class_merging$ar$class_merging$ar$class_merging = new FileUtils(null, null, null);
            layoutParams.setBehavior(behavior);
        }
        toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$account = (FrameLayout) inflate.findViewById(R.id.content_frame);
        toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$requiredScopes = (Toolbar) inflate.findViewById(R.id.action_bar);
        Object obj3 = toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$signInOptions;
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = (FloatingActionButton.ShadowDelegateImpl) obj3;
        super.setActionBar((Toolbar) toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$requiredScopes);
        ActionBar actionBar = super.getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
        super.setContentView(inflate);
    }

    @Override // android.app.Activity
    public boolean onNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finishAfterTransition();
            return true;
        }
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(int i) {
        ClientSettings.Builder builder = this.mToolbardelegate$ar$class_merging;
        Object obj = builder == null ? (ViewGroup) findViewById(R.id.content_frame) : builder.ClientSettings$Builder$ar$account;
        if (obj != null) {
            ((ViewGroup) obj).removeAllViews();
        }
        LayoutInflater.from(this).inflate(i, (ViewGroup) obj);
    }

    @Override // android.app.Activity
    public final void setTitle(int i) {
        setTitle(getText(i));
    }

    @Override // android.app.Activity
    public final void setTitle(CharSequence charSequence) {
        ClientSettings.Builder toolbarDelegate$ar$class_merging = getToolbarDelegate$ar$class_merging();
        Object obj = toolbarDelegate$ar$class_merging.ClientSettings$Builder$ar$realClientPackageName;
        if (obj != null) {
            ((CollapsingToolbarLayout) obj).setTitle(charSequence);
        }
        super.setTitle(charSequence);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view) {
        ClientSettings.Builder builder = this.mToolbardelegate$ar$class_merging;
        Object obj = builder == null ? (ViewGroup) findViewById(R.id.content_frame) : builder.ClientSettings$Builder$ar$account;
        if (obj != null) {
            ((ViewGroup) obj).addView(view);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ClientSettings.Builder builder = this.mToolbardelegate$ar$class_merging;
        Object obj = builder == null ? (ViewGroup) findViewById(R.id.content_frame) : builder.ClientSettings$Builder$ar$account;
        if (obj != null) {
            ((ViewGroup) obj).addView(view, layoutParams);
        }
    }
}
