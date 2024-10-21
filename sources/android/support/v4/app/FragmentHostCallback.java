package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.PrintWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class FragmentHostCallback<H> extends FragmentContainer {
    public final Activity activity;
    public final Context context;
    public final FragmentManager fragmentManager = new FragmentManager(null);
    public final Handler handler;

    public FragmentHostCallback(Activity activity, Context context, Handler handler) {
        this.activity = activity;
        this.context = context;
        this.handler = handler;
    }

    public void onDump$ar$ds(PrintWriter printWriter, String[] strArr) {
        throw null;
    }

    @Override // android.support.v4.app.FragmentContainer
    public View onFindViewById(int i) {
        throw null;
    }

    public abstract Object onGetHost();

    public LayoutInflater onGetLayoutInflater() {
        throw null;
    }

    @Override // android.support.v4.app.FragmentContainer
    public boolean onHasView() {
        throw null;
    }

    public boolean onShouldShowRequestPermissionRationale(String str) {
        throw null;
    }

    public final void onStartActivityFromFragment$ar$ds(Intent intent, int i, Bundle bundle) {
        intent.getClass();
        if (i == -1) {
            this.context.startActivity(intent, bundle);
            return;
        }
        throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }

    public void onSupportInvalidateOptionsMenu() {
    }
}
