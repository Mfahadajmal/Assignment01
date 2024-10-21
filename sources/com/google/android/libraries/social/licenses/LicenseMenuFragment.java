package com.google.android.libraries.social.licenses;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.collection.internal.ContainerHelpersKt;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManagerImpl;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.marvin.talkback.R;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LicenseMenuFragment extends Fragment {
    public LicenseMenuActivity licenseSelectionListener$ar$class_merging;
    public ArrayAdapter listAdapter;

    @Override // android.support.v4.app.Fragment
    public final void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof LicenseMenuActivity) {
            this.licenseSelectionListener$ar$class_merging = (LicenseMenuActivity) activity;
        }
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.libraries_social_licenses_license_menu_fragment, viewGroup, false);
    }

    @Override // android.support.v4.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        LoaderManager loaderManager = LoaderManager.getInstance(getActivity());
        LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl) loaderManager;
        if (!loaderManagerImpl.mLoaderViewModel.mCreatingLoader) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                if (LoaderManagerImpl.isLoggingEnabled(2)) {
                    Objects.toString(loaderManager);
                }
                LoaderManagerImpl.LoaderInfo loader$ar$ds = loaderManagerImpl.mLoaderViewModel.getLoader$ar$ds();
                if (loader$ar$ds != null) {
                    loader$ar$ds.destroy$ar$ds();
                    SparseArrayCompat sparseArrayCompat = loaderManagerImpl.mLoaderViewModel.mLoaders;
                    int binarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, 54321);
                    if (binarySearch >= 0) {
                        Object[] objArr = sparseArrayCompat.values;
                        Object obj = objArr[binarySearch];
                        Object obj2 = SparseArrayCompatKt.DELETED;
                        if (obj != obj2) {
                            objArr[binarySearch] = obj2;
                            sparseArrayCompat.garbage = true;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    @Override // android.support.v4.app.Fragment
    public final void onDetach() {
        super.onDetach();
        this.licenseSelectionListener$ar$class_merging = null;
    }

    @Override // android.support.v4.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        FragmentActivity activity = getActivity();
        this.listAdapter = new ArrayAdapter(activity, R.layout.libraries_social_licenses_license, R.id.license, new ArrayList());
        LoaderManager loaderManager = LoaderManager.getInstance(activity);
        LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl) loaderManager;
        if (!loaderManagerImpl.mLoaderViewModel.mCreatingLoader) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                LoaderManagerImpl.LoaderInfo loader$ar$ds = loaderManagerImpl.mLoaderViewModel.getLoader$ar$ds();
                if (LoaderManagerImpl.isLoggingEnabled(2)) {
                    Objects.toString(loaderManager);
                }
                byte[] bArr = null;
                int i = 3;
                if (loader$ar$ds == null) {
                    try {
                        ((LoaderManagerImpl) loaderManager).mLoaderViewModel.mCreatingLoader = true;
                        AsyncTaskLoader asyncTaskLoader = new AsyncTaskLoader(getActivity(), null);
                        if (asyncTaskLoader.getClass().isMemberClass() && !Modifier.isStatic(asyncTaskLoader.getClass().getModifiers())) {
                            throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + asyncTaskLoader);
                        }
                        LoaderManagerImpl.LoaderInfo loaderInfo = new LoaderManagerImpl.LoaderInfo(asyncTaskLoader);
                        if (LoaderManagerImpl.isLoggingEnabled(3)) {
                            loaderInfo.toString();
                        }
                        ((LoaderManagerImpl) loaderManager).mLoaderViewModel.mLoaders.put(54321, loaderInfo);
                        loaderManagerImpl.mLoaderViewModel.finishCreatingLoader();
                        loaderInfo.setCallback$ar$class_merging$ar$ds(loaderManagerImpl.mLifecycleOwner, this);
                    } catch (Throwable th) {
                        loaderManagerImpl.mLoaderViewModel.finishCreatingLoader();
                        throw th;
                    }
                } else {
                    if (LoaderManagerImpl.isLoggingEnabled(3)) {
                        Objects.toString(loader$ar$ds);
                    }
                    loader$ar$ds.setCallback$ar$class_merging$ar$ds(loaderManagerImpl.mLifecycleOwner, this);
                }
                ListView listView = (ListView) view.findViewById(R.id.license_list);
                listView.setAdapter((ListAdapter) this.listAdapter);
                listView.setOnItemClickListener(new ContextMenuDialog.AnonymousClass1(this, i, bArr));
                return;
            }
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }
}
