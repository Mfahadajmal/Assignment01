package androidx.navigation.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.ViewParentCompat$Api21Impl;
import androidx.core.view.WindowCompat$Api30Impl;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavType;
import androidx.navigation.fragment.DialogFragmentNavigator;
import com.google.android.marvin.talkback.R;
import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AbstractAppBarOnDestinationChangedListener implements NavController.OnDestinationChangedListener {
    private final AppCompatActivity activity;
    private ValueAnimator animator;
    private DrawerArrowDrawable arrowDrawable;
    private final ViewModelStore configuration$ar$class_merging;
    private final Context context;
    private final WeakReference openableLayoutWeakReference;

    public AbstractAppBarOnDestinationChangedListener(Context context, ViewModelStore viewModelStore) {
        this.context = context;
        this.configuration$ar$class_merging = viewModelStore;
        this.openableLayoutWeakReference = null;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.Set, java.lang.Object] */
    @Override // androidx.navigation.NavController.OnDestinationChangedListener
    public final void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle) {
        CharSequence stringBuffer;
        NavType navType;
        NavArgument navArgument;
        Pair pair;
        navDestination.getClass();
        if (navDestination instanceof DialogFragmentNavigator.Destination) {
            return;
        }
        Context context = this.context;
        CharSequence charSequence = navDestination.label;
        if (charSequence == null) {
            stringBuffer = null;
        } else {
            Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(charSequence);
            StringBuffer stringBuffer2 = new StringBuffer();
            while (matcher.find()) {
                String group = matcher.group(1);
                if (bundle != null && bundle.containsKey(group)) {
                    matcher.appendReplacement(stringBuffer2, "");
                    if (group != null && (navArgument = (NavArgument) navDestination._arguments.get(group)) != null) {
                        navType = navArgument.type;
                    } else {
                        navType = null;
                    }
                    if (Intrinsics.areEqual(navType, NavType.ReferenceType)) {
                        String string = context.getString(bundle.getInt(group));
                        string.getClass();
                        stringBuffer2.append(string);
                    } else {
                        stringBuffer2.append(String.valueOf(bundle.get(group)));
                    }
                } else {
                    throw new IllegalArgumentException("Could not find \"" + group + "\" in " + bundle + " to fill label \"" + ((Object) charSequence) + '\"');
                }
            }
            matcher.appendTail(stringBuffer2);
            stringBuffer = stringBuffer2.toString();
        }
        if (stringBuffer != null) {
            setTitle(stringBuffer);
        }
        ViewModelStore viewModelStore = this.configuration$ar$class_merging;
        for (NavDestination navDestination2 : ViewParentCompat$Api21Impl.getHierarchy$ar$ds(navDestination)) {
            if (viewModelStore.ViewModelStore$ar$map.contains(Integer.valueOf(navDestination2.id))) {
                if (navDestination2 instanceof NavGraph) {
                    int i = navDestination.id;
                    WindowCompat$Api30Impl windowCompat$Api30Impl = NavGraph.Companion$ar$class_merging$ad045ee8_0;
                    if (i == WindowCompat$Api30Impl.findStartDestination$ar$ds((NavGraph) navDestination2).id) {
                    }
                }
                setNavigationIcon(null, 0);
                return;
            }
        }
        DrawerArrowDrawable drawerArrowDrawable = this.arrowDrawable;
        if (drawerArrowDrawable != null) {
            pair = new Pair(drawerArrowDrawable, true);
        } else {
            DrawerArrowDrawable drawerArrowDrawable2 = new DrawerArrowDrawable(this.context);
            this.arrowDrawable = drawerArrowDrawable2;
            pair = new Pair(drawerArrowDrawable2, false);
        }
        Object obj = pair.second;
        DrawerArrowDrawable drawerArrowDrawable3 = (DrawerArrowDrawable) pair.first;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        setNavigationIcon(drawerArrowDrawable3, R.string.nav_app_bar_navigate_up_description);
        if (booleanValue) {
            float f = drawerArrowDrawable3.mProgress;
            ValueAnimator valueAnimator = this.animator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(drawerArrowDrawable3, "progress", f, 1.0f);
            this.animator = ofFloat;
            ofFloat.getClass();
            ofFloat.start();
            return;
        }
        drawerArrowDrawable3.setProgress(1.0f);
    }

    protected final void setNavigationIcon(Drawable drawable, int i) {
        boolean z;
        ActionBar supportActionBar = this.activity.getSupportActionBar();
        if (supportActionBar != null) {
            if (drawable != null) {
                z = true;
            } else {
                z = false;
            }
            supportActionBar.setDisplayHomeAsUpEnabled(z);
            ActionBar supportActionBar2 = ((AppCompatDelegateImpl) this.activity.getDrawerToggleDelegate$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0).getSupportActionBar();
            if (supportActionBar2 != null) {
                WindowDecorActionBar windowDecorActionBar = (WindowDecorActionBar) supportActionBar2;
                windowDecorActionBar.mDecorToolbar$ar$class_merging.setNavigationIcon(drawable);
                windowDecorActionBar.mDecorToolbar$ar$class_merging.setNavigationContentDescription(i);
                return;
            }
            return;
        }
        throw new IllegalStateException("Activity " + this.activity + " does not have an ActionBar set via setSupportActionBar()");
    }

    protected final void setTitle(CharSequence charSequence) {
        ActionBar supportActionBar = this.activity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(charSequence);
            return;
        }
        throw new IllegalStateException("Activity " + this.activity + " does not have an ActionBar set via setSupportActionBar()");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AbstractAppBarOnDestinationChangedListener(android.support.v7.app.AppCompatActivity r2, androidx.lifecycle.ViewModelStore r3) {
        /*
            r1 = this;
            com.google.android.material.floatingactionbutton.FloatingActionButton$ShadowDelegateImpl r0 = r2.getDrawerToggleDelegate$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging()
            java.lang.Object r0 = r0.FloatingActionButton$ShadowDelegateImpl$ar$this$0
            android.support.v7.app.AppCompatDelegateImpl r0 = (android.support.v7.app.AppCompatDelegateImpl) r0
            android.content.Context r0 = r0.getActionBarThemedContext()
            r0.getClass()
            r1.<init>(r0, r3)
            r1.activity = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener.<init>(android.support.v7.app.AppCompatActivity, androidx.lifecycle.ViewModelStore):void");
    }
}
