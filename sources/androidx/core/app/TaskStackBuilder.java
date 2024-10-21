package androidx.core.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskStackBuilder implements Iterable {
    private final ArrayList mIntents = new ArrayList();
    public final Context mSourceContext;

    public TaskStackBuilder(Context context) {
        this.mSourceContext = context;
    }

    public final void addNextIntent$ar$ds(Intent intent) {
        this.mIntents.add(intent);
    }

    public final void addParentStack$ar$ds(ComponentName componentName) {
        int size = this.mIntents.size();
        try {
            Intent parentActivityIntent = ActivityCompat.Api28Impl.getParentActivityIntent(this.mSourceContext, componentName);
            while (parentActivityIntent != null) {
                this.mIntents.add(size, parentActivityIntent);
                parentActivityIntent = ActivityCompat.Api28Impl.getParentActivityIntent(this.mSourceContext, parentActivityIntent.getComponent());
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    @Override // java.lang.Iterable
    @Deprecated
    public final Iterator iterator() {
        return this.mIntents.iterator();
    }

    public final void startActivities() {
        if (!this.mIntents.isEmpty()) {
            Intent[] intentArr = (Intent[]) this.mIntents.toArray(new Intent[0]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            this.mSourceContext.startActivities(intentArr, null);
            return;
        }
        throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
}
