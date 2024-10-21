package androidx.loader.content;

import android.content.Context;
import androidx.loader.app.LoaderManagerImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Loader {
    public final Context mContext;
    public int mId;
    public LoaderManagerImpl.LoaderInfo mListener$ar$class_merging$a2c4ce4e_0;
    public boolean mStarted = false;
    public boolean mAbandoned = false;
    public boolean mReset = true;
    public boolean mContentChanged = false;

    public Loader(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static final String dataToString$ar$ds(Object obj) {
        StringBuilder sb = new StringBuilder(64);
        if (obj == null) {
            sb.append("null");
        } else {
            sb.append(obj.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(obj)));
            sb.append("}");
        }
        return sb.toString();
    }

    public void onCancelLoad$ar$ds() {
        throw null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" id=");
        sb.append(this.mId);
        sb.append("}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onForceLoad() {
    }

    public void onStartLoading() {
    }

    public void onStopLoading() {
    }
}
