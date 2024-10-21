package com.google.android.libraries.mdi.download.debug.dagger;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.libraries.mdi.download.debug.MddDebugListAdapter;
import com.google.android.libraries.mdi.download.debug.common.filegroups.FileGroupListViewModel;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProvider;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProviderImpl;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentHelper;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentUiProviderImpl;
import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.GoogleLogger;
import dagger.android.support.DaggerFragment;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.DesugarCollections;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugListFragment extends DaggerFragment {
    private MddDebugListFragmentHelper helper;

    public final MddDebugListFragmentHelper getHelper() {
        return this.helper;
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MddDebugListFragmentActionProviderImpl mddDebugListFragmentActionProviderImpl = new MddDebugListFragmentActionProviderImpl(this);
        MddDebugListFragmentUiProviderImpl mddDebugListFragmentUiProviderImpl = new MddDebugListFragmentUiProviderImpl(this, mddDebugListFragmentActionProviderImpl);
        MddDebugListFragmentHelper mddDebugListFragmentHelper = new MddDebugListFragmentHelper(mddDebugListFragmentUiProviderImpl, mddDebugListFragmentActionProviderImpl);
        this.helper = mddDebugListFragmentHelper;
        final MddDebugListFragmentActionProviderImpl mddDebugListFragmentActionProviderImpl2 = (MddDebugListFragmentActionProviderImpl) mddDebugListFragmentHelper.actionProvider;
        mddDebugListFragmentActionProviderImpl2.callbacks = mddDebugListFragmentUiProviderImpl;
        mddDebugListFragmentActionProviderImpl2.futureRegistry$ar$class_merging$ar$class_merging = OptionalMethod.forFragment$ar$class_merging$ar$class_merging(mddDebugListFragmentActionProviderImpl2.fragment);
        mddDebugListFragmentActionProviderImpl2.futureRegistry$ar$class_merging$ar$class_merging.registerCallback$ar$ds(R.id.list_fragment_action_callback, mddDebugListFragmentActionProviderImpl2.listFragmentActionCallback);
        mddDebugListFragmentActionProviderImpl2.listViewModel = (FileGroupListViewModel) new ViewModelProvider(mddDebugListFragmentActionProviderImpl2.fragment, new ViewModelProvider.NewInstanceFactory() { // from class: com.google.android.libraries.mdi.download.debug.common.filegroups.FileGroupListViewModel.1
            @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
            public final ViewModel create(Class cls) {
                return new FileGroupListViewModel();
            }
        }).get(FileGroupListViewModel.class);
        FileGroupListViewModel fileGroupListViewModel = mddDebugListFragmentActionProviderImpl2.listViewModel;
        AtomicReference atomicReference = fileGroupListViewModel.fileGroupList;
        int i = ImmutableList.ImmutableList$ar$NoOp;
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(atomicReference, new MutableLiveData(RegularImmutableList.EMPTY))) {
            MutableLiveData mutableLiveData = (MutableLiveData) fileGroupListViewModel.fileGroupList.get();
            mutableLiveData.getClass();
            mutableLiveData.observe(mddDebugListFragmentActionProviderImpl2.fragment, new Observer() { // from class: com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProviderImpl$$ExternalSyntheticLambda0
                /* JADX WARN: Type inference failed for: r5v2, types: [java.util.concurrent.Executor, java.lang.Object] */
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ImmutableList copyOf = ImmutableList.copyOf((Collection) obj);
                    MddDebugListFragmentActionProvider.UiCallbacks uiCallbacks = MddDebugListFragmentActionProviderImpl.this.callbacks;
                    if (uiCallbacks != null) {
                        MddDebugListFragmentUiProviderImpl mddDebugListFragmentUiProviderImpl2 = (MddDebugListFragmentUiProviderImpl) uiCallbacks;
                        AsyncListDiffer asyncListDiffer = mddDebugListFragmentUiProviderImpl2.listAdapter.mDiffer;
                        int i2 = asyncListDiffer.mMaxScheduledGeneration + 1;
                        asyncListDiffer.mMaxScheduledGeneration = i2;
                        List list = asyncListDiffer.mList;
                        if (copyOf != list) {
                            List list2 = asyncListDiffer.mReadOnlyList;
                            if (copyOf == null) {
                                int size = list.size();
                                asyncListDiffer.mList = null;
                                asyncListDiffer.mReadOnlyList = Collections.emptyList();
                                asyncListDiffer.mUpdateCallback.onRemoved(0, size);
                                asyncListDiffer.onCurrentListChanged$ar$ds(list2);
                            } else if (list == null) {
                                asyncListDiffer.mList = copyOf;
                                asyncListDiffer.mReadOnlyList = DesugarCollections.unmodifiableList(copyOf);
                                asyncListDiffer.mUpdateCallback.onInserted(0, copyOf.size());
                                asyncListDiffer.onCurrentListChanged$ar$ds(list2);
                            } else {
                                asyncListDiffer.mConfig$ar$class_merging$ar$class_merging$ar$class_merging.WorkName$ar$name.execute(new AsyncListDiffer.AnonymousClass1(list, copyOf, i2));
                            }
                        }
                        if (copyOf.isEmpty()) {
                            mddDebugListFragmentUiProviderImpl2.onShowFileGroupStatus("No Tracked File Groups\n(You may need to force refresh tracked file groups)");
                        } else {
                            mddDebugListFragmentUiProviderImpl2.fragment.requireActivity().runOnUiThread(new MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0(mddDebugListFragmentUiProviderImpl2, 0));
                        }
                    }
                }
            });
            return;
        }
        throw null;
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MddDebugListFragmentUiProviderImpl mddDebugListFragmentUiProviderImpl = this.helper.uiProvider;
        Fragment fragment = mddDebugListFragmentUiProviderImpl.fragment;
        View inflate = layoutInflater.inflate(R.layout.mdd_debug_list_fragment, viewGroup, false);
        Context context = fragment.getContext();
        mddDebugListFragmentUiProviderImpl.listAdapter = new MddDebugListAdapter(context);
        mddDebugListFragmentUiProviderImpl.listAdapter.selectListener$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(mddDebugListFragmentUiProviderImpl.actionProvider, null);
        mddDebugListFragmentUiProviderImpl.listStatusTextView = (TextView) inflate.findViewById(R.id.file_group_list_status_text);
        mddDebugListFragmentUiProviderImpl.swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.pull_to_refresh);
        SwipeRefreshLayout swipeRefreshLayout = mddDebugListFragmentUiProviderImpl.swipeRefreshLayout;
        swipeRefreshLayout.mListener = mddDebugListFragmentUiProviderImpl;
        swipeRefreshLayout.setEnabled(true);
        mddDebugListFragmentUiProviderImpl.recyclerView = (RecyclerView) inflate.findViewById(R.id.file_group_list);
        mddDebugListFragmentUiProviderImpl.recyclerView.setHasFixedSize$ar$ds();
        mddDebugListFragmentUiProviderImpl.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mddDebugListFragmentUiProviderImpl.recyclerView.setAdapter(mddDebugListFragmentUiProviderImpl.listAdapter);
        mddDebugListFragmentUiProviderImpl.filterField = (EditText) inflate.findViewById(R.id.filter);
        EditText editText = mddDebugListFragmentUiProviderImpl.filterField;
        MddDebugListFragmentActionProvider mddDebugListFragmentActionProvider = mddDebugListFragmentUiProviderImpl.actionProvider;
        final PrimesConfigurations$Builder$$ExternalSyntheticLambda0 primesConfigurations$Builder$$ExternalSyntheticLambda0 = new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(mddDebugListFragmentUiProviderImpl, 1);
        final MddDebugListFragmentActionProviderImpl mddDebugListFragmentActionProviderImpl = (MddDebugListFragmentActionProviderImpl) mddDebugListFragmentActionProvider;
        editText.addTextChangedListener(new TextWatcher() { // from class: com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProviderImpl.2
            final /* synthetic */ MddDebugListFragmentActionProviderImpl this$0;
            final /* synthetic */ Provider val$enabled;

            public AnonymousClass2(final MddDebugListFragmentActionProviderImpl mddDebugListFragmentActionProviderImpl2, final Provider primesConfigurations$Builder$$ExternalSyntheticLambda02) {
                r2 = primesConfigurations$Builder$$ExternalSyntheticLambda02;
                r1 = mddDebugListFragmentActionProviderImpl2;
            }

            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                if (!((Boolean) r2.get()).booleanValue()) {
                    return;
                }
                String trim = editable.toString().trim();
                ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugListFragmentActionProviderImpl.logger.atFine()).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugListFragmentActionProviderImpl$2", "afterTextChanged", 173, "MddDebugListFragmentActionProviderImpl.java")).log("Updating File Group Filter: %s", trim);
                r1.listViewModel.fileGroupFilter = trim;
                throw null;
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        return inflate;
    }
}
