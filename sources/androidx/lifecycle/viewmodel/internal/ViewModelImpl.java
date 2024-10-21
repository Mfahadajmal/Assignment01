package androidx.lifecycle.viewmodel.internal;

import androidx.core.view.ViewCompat;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewModelImpl {
    public volatile boolean isCleared;
    public final ViewCompat.Api29Impl lock$ar$class_merging$2cccb82d_0 = new ViewCompat.Api29Impl();
    public final Map keyToCloseables = new LinkedHashMap();
    public final Set closeables = new LinkedHashSet();

    public static final void closeWithRuntimeException$ar$ds(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
