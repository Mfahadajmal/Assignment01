package androidx.core.content;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ContextCompat$Api24Impl {
    public static Context createDeviceProtectedStorageContext(Context context) {
        return context.createDeviceProtectedStorageContext();
    }

    static File getDataDir(Context context) {
        return context.getDataDir();
    }

    static boolean isDeviceProtectedStorage(Context context) {
        return context.isDeviceProtectedStorage();
    }

    public boolean hasSubMenu() {
        throw null;
    }

    public boolean isVisible() {
        throw null;
    }

    public View onCreateActionView(MenuItem menuItem) {
        throw null;
    }

    public boolean onPerformDefaultAction() {
        throw null;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
        throw null;
    }

    public boolean overridesItemVisibility() {
        throw null;
    }

    public void setVisibilityListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        throw null;
    }
}
