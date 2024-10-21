package androidx.core.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1;
import androidx.core.view.ViewKt$allViews$1;
import androidx.lifecycle.ViewModelStore;
import com.google.android.marvin.talkback.R;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DrawableCompat$Api23Impl {
    public static final void callPoolingContainerOnRelease(View view) {
        Iterator it = new ViewGroupKt$special$$inlined$Sequence$1(new ViewKt$allViews$1(view, null), 4).iterator();
        while (it.hasNext()) {
            getPoolingContainerListenerHolder$ar$class_merging$ar$class_merging((View) it.next()).onRelease();
        }
    }

    public static int getLayoutDirection(Drawable drawable) {
        return drawable.getLayoutDirection();
    }

    public static final ViewModelStore getPoolingContainerListenerHolder$ar$class_merging$ar$class_merging(View view) {
        ViewModelStore viewModelStore = (ViewModelStore) view.getTag(R.id.pooling_container_listener_holder_tag);
        if (viewModelStore == null) {
            ViewModelStore viewModelStore2 = new ViewModelStore((char[]) null);
            view.setTag(R.id.pooling_container_listener_holder_tag, viewModelStore2);
            return viewModelStore2;
        }
        return viewModelStore;
    }

    public static boolean setLayoutDirection(Drawable drawable, int i) {
        return drawable.setLayoutDirection(i);
    }
}
