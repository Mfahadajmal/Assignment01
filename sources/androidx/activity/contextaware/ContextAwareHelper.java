package androidx.activity.contextaware;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextAwareHelper {
    public volatile Context context;
    public final Set listeners = new CopyOnWriteArraySet();
}
