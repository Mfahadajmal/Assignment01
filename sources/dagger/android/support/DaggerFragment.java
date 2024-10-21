package dagger.android.support;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import dagger.android.HasAndroidInjector;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DaggerFragment extends Fragment implements HasAndroidInjector {
    public DaggerFragment() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public final void onAttach(Context context) {
        HasAndroidInjector hasAndroidInjector;
        Fragment fragment = this;
        while (true) {
            fragment = fragment.getParentFragment();
            if (fragment != 0) {
                if (fragment instanceof HasAndroidInjector) {
                    hasAndroidInjector = (HasAndroidInjector) fragment;
                    break;
                }
            } else {
                FragmentActivity activity = getActivity();
                if (!(activity instanceof HasAndroidInjector)) {
                    if (!(activity.getApplication() instanceof HasAndroidInjector)) {
                        throw new IllegalArgumentException(String.format("No injector was found for %s", getClass().getCanonicalName()));
                    }
                    hasAndroidInjector = (HasAndroidInjector) activity.getApplication();
                } else {
                    hasAndroidInjector = (HasAndroidInjector) activity;
                }
            }
        }
        hasAndroidInjector.androidInjector$ar$class_merging$ar$ds();
        hasAndroidInjector.getClass();
        throw null;
    }

    public DaggerFragment(int i) {
        super(i);
    }

    @Override // dagger.android.HasAndroidInjector
    public final void androidInjector$ar$class_merging$ar$ds() {
    }
}
