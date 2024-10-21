package androidx.preference;

import _COROUTINE._BOUNDARY;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceManager;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PreferenceFragmentCompat extends Fragment implements PreferenceManager.OnPreferenceTreeClickListener, PreferenceManager.OnDisplayPreferenceDialogListener, PreferenceManager.OnNavigateToScreenListener, DialogPreference.TargetFragment {
    public static final String ARG_PREFERENCE_ROOT = "androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT";
    private static final String DIALOG_FRAGMENT_TAG = "androidx.preference.PreferenceFragment.DIALOG";
    private static final int MSG_BIND_PREFERENCES = 1;
    private static final String PREFERENCES_TAG = "android:preferences";
    private static final String TAG = "PreferenceFragment";
    private boolean mHavePrefs;
    private boolean mInitDone;
    public RecyclerView mList;
    private PreferenceManager mPreferenceManager;
    private Runnable mSelectPreferenceRunnable;
    private final DividerDecoration mDividerDecoration = new DividerDecoration();
    private int mLayoutResId = R.layout.preference_list_fragment;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: androidx.preference.PreferenceFragmentCompat.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PreferenceFragmentCompat.this.bindPreferences();
        }
    };
    private final Runnable mRequestFocus = new SearchView$SearchAutoComplete.AnonymousClass1(this, 15);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DividerDecoration extends AppCompatReceiveContentHelper$OnDropApi24Impl {
        public boolean mAllowDividerAfterLastItem = true;
        public Drawable mDivider;
        public int mDividerHeight;

        public DividerDecoration() {
        }

        private final boolean shouldDrawDividerBelow(View view, RecyclerView recyclerView) {
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
            if (!(childViewHolder instanceof PreferenceViewHolder) || !((PreferenceViewHolder) childViewHolder).mDividerAllowedBelow) {
                return false;
            }
            boolean z = this.mAllowDividerAfterLastItem;
            int indexOfChild = recyclerView.indexOfChild(view);
            if (indexOfChild < recyclerView.getChildCount() - 1) {
                z = true;
                RecyclerView.ViewHolder childViewHolder2 = recyclerView.getChildViewHolder(recyclerView.getChildAt(indexOfChild + 1));
                if (!(childViewHolder2 instanceof PreferenceViewHolder) || !((PreferenceViewHolder) childViewHolder2).mDividerAllowedAbove) {
                    return false;
                }
            }
            return z;
        }

        @Override // android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl
        public final void getItemOffsets$ar$ds(Rect rect, View view, RecyclerView recyclerView) {
            if (shouldDrawDividerBelow(view, recyclerView)) {
                rect.bottom = this.mDividerHeight;
            }
        }

        @Override // android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl
        public final void onDrawOver$ar$ds(Canvas canvas, RecyclerView recyclerView) {
            if (this.mDivider != null) {
                int childCount = recyclerView.getChildCount();
                int width = recyclerView.getWidth();
                for (int i = 0; i < childCount; i++) {
                    View childAt = recyclerView.getChildAt(i);
                    if (shouldDrawDividerBelow(childAt, recyclerView)) {
                        int y = ((int) childAt.getY()) + childAt.getHeight();
                        this.mDivider.setBounds(0, y, width, this.mDividerHeight + y);
                        this.mDivider.draw(canvas);
                    }
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnPreferenceDisplayDialogCallback {
        boolean onPreferenceDisplayDialog$ar$ds();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnPreferenceStartScreenCallback {
        boolean onPreferenceStartScreen$ar$ds();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ScrollToPreferenceObserver extends WindowCallbackWrapper.Api24Impl {
        private final RecyclerView.Adapter mAdapter;
        private final String mKey;
        private final RecyclerView mList;
        private final Preference mPreference;

        public ScrollToPreferenceObserver(RecyclerView.Adapter adapter, RecyclerView recyclerView, Preference preference, String str) {
            this.mAdapter = adapter;
            this.mList = recyclerView;
            this.mPreference = preference;
            this.mKey = str;
        }

        private final void scrollToPreference() {
            int preferenceAdapterPosition;
            this.mAdapter.unregisterAdapterDataObserver$ar$class_merging(this);
            Preference preference = this.mPreference;
            if (preference != null) {
                preferenceAdapterPosition = ((PreferenceGroupAdapter) this.mAdapter).getPreferenceAdapterPosition(preference);
            } else {
                preferenceAdapterPosition = ((PreferenceGroupAdapter) this.mAdapter).getPreferenceAdapterPosition(this.mKey);
            }
            if (preferenceAdapterPosition != -1) {
                this.mList.scrollToPosition(preferenceAdapterPosition);
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onChanged() {
            scrollToPreference();
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeChanged(int i, int i2, Object obj) {
            scrollToPreference();
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeInserted(int i, int i2) {
            scrollToPreference();
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeMoved$ar$ds(int i, int i2) {
            scrollToPreference();
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeRemoved(int i, int i2) {
            scrollToPreference();
        }
    }

    private void postBindPreferences() {
        if (this.mHandler.hasMessages(1)) {
            return;
        }
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    private void requirePreferenceManager() {
        if (this.mPreferenceManager != null) {
        } else {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
    }

    private void scrollToPreferenceInternal(Preference preference, String str) {
        DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0 defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0 = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(this, preference, str, 3);
        if (this.mList == null) {
            this.mSelectPreferenceRunnable = defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
        } else {
            defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0.run();
        }
    }

    private void unbindPreferences() {
        getListView().setAdapter(null);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.onDetached();
        }
        onUnbindPreferences();
    }

    public void addPreferencesFromResource(int i) {
        requirePreferenceManager();
        setPreferenceScreen(this.mPreferenceManager.inflateFromResource(requireContext(), i, getPreferenceScreen()));
    }

    public void bindPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            getListView().setAdapter(onCreateAdapter(preferenceScreen));
            preferenceScreen.onAttached();
        }
        onBindPreferences();
    }

    @Override // androidx.preference.DialogPreference.TargetFragment
    public <T extends Preference> T findPreference(CharSequence charSequence) {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager == null) {
            return null;
        }
        return (T) preferenceManager.findPreference(charSequence);
    }

    public Fragment getCallbackFragment() {
        return null;
    }

    public final RecyclerView getListView() {
        return this.mList;
    }

    public PreferenceManager getPreferenceManager() {
        return this.mPreferenceManager;
    }

    public PreferenceScreen getPreferenceScreen() {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager == null) {
            return null;
        }
        return preferenceManager.mPreferenceScreen;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        requireContext().getTheme().resolveAttribute(R.attr.preferenceTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = R.style.PreferenceThemeOverlay;
        }
        requireContext().getTheme().applyStyle(i, false);
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        this.mPreferenceManager = preferenceManager;
        preferenceManager.mOnNavigateToScreenListener = this;
        if (getArguments() != null) {
            str = getArguments().getString(ARG_PREFERENCE_ROOT);
        } else {
            str = null;
        }
        onCreatePreferences(bundle, str);
    }

    protected RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new PreferenceGroupAdapter(preferenceScreen);
    }

    public RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(requireContext());
    }

    public abstract void onCreatePreferences(Bundle bundle, String str);

    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        if (requireContext().getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(R.layout.preference_recyclerview, viewGroup, false);
        recyclerView2.setLayoutManager(onCreateLayoutManager());
        recyclerView2.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(recyclerView2));
        return recyclerView2;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TypedArray obtainStyledAttributes = requireContext().obtainStyledAttributes(null, R$styleable.PreferenceFragmentCompat, R.attr.preferenceFragmentCompatStyle, 0);
        this.mLayoutResId = obtainStyledAttributes.getResourceId(0, this.mLayoutResId);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(2, -1);
        boolean z = obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.recycle();
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(requireContext());
        View inflate = cloneInContext.inflate(this.mLayoutResId, viewGroup, false);
        View findViewById = inflate.findViewById(android.R.id.list_container);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup2 = (ViewGroup) findViewById;
            RecyclerView onCreateRecyclerView = onCreateRecyclerView(cloneInContext, viewGroup2, bundle);
            if (onCreateRecyclerView != null) {
                this.mList = onCreateRecyclerView;
                onCreateRecyclerView.addItemDecoration$ar$class_merging(this.mDividerDecoration);
                setDivider(drawable);
                if (dimensionPixelSize != -1) {
                    setDividerHeight(dimensionPixelSize);
                }
                this.mDividerDecoration.mAllowDividerAfterLastItem = z;
                if (this.mList.getParent() == null) {
                    viewGroup2.addView(this.mList);
                }
                this.mHandler.post(this.mRequestFocus);
                return inflate;
            }
            throw new RuntimeException("Could not create RecyclerView");
        }
        throw new IllegalStateException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mHandler.removeMessages(1);
        if (this.mHavePrefs) {
            unbindPreferences();
        }
        this.mList = null;
        super.onDestroyView();
    }

    @Override // androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(Preference preference) {
        boolean z;
        DialogFragment multiSelectListPreferenceDialogFragmentCompat;
        if (getCallbackFragment() instanceof OnPreferenceDisplayDialogCallback) {
            z = ((OnPreferenceDisplayDialogCallback) getCallbackFragment()).onPreferenceDisplayDialog$ar$ds();
        } else {
            z = false;
        }
        for (Fragment fragment = this; !z && fragment != null; fragment = fragment.getParentFragment()) {
            if (fragment instanceof OnPreferenceDisplayDialogCallback) {
                z = ((OnPreferenceDisplayDialogCallback) fragment).onPreferenceDisplayDialog$ar$ds();
            }
        }
        if (!z && (getContext() instanceof OnPreferenceDisplayDialogCallback)) {
            z = ((OnPreferenceDisplayDialogCallback) getContext()).onPreferenceDisplayDialog$ar$ds();
        }
        if (!z) {
            if ((!(getActivity() instanceof OnPreferenceDisplayDialogCallback) || !((OnPreferenceDisplayDialogCallback) getActivity()).onPreferenceDisplayDialog$ar$ds()) && getParentFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG) == null) {
                if (preference instanceof EditTextPreference) {
                    String key = preference.getKey();
                    multiSelectListPreferenceDialogFragmentCompat = new EditTextPreferenceDialogFragmentCompat();
                    Bundle bundle = new Bundle(1);
                    bundle.putString("key", key);
                    multiSelectListPreferenceDialogFragmentCompat.setArguments(bundle);
                } else if (preference instanceof ListPreference) {
                    multiSelectListPreferenceDialogFragmentCompat = ListPreferenceDialogFragmentCompat.newInstance(preference.getKey());
                } else if (preference instanceof MultiSelectListPreference) {
                    String key2 = preference.getKey();
                    multiSelectListPreferenceDialogFragmentCompat = new MultiSelectListPreferenceDialogFragmentCompat();
                    Bundle bundle2 = new Bundle(1);
                    bundle2.putString("key", key2);
                    multiSelectListPreferenceDialogFragmentCompat.setArguments(bundle2);
                } else {
                    throw new IllegalArgumentException("Cannot display dialog for an unknown Preference type: " + preference.getClass().getSimpleName() + ". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
                }
                multiSelectListPreferenceDialogFragmentCompat.setTargetFragment(this, 0);
                multiSelectListPreferenceDialogFragmentCompat.show(getParentFragmentManager(), DIALOG_FRAGMENT_TAG);
            }
        }
    }

    @Override // androidx.preference.PreferenceManager.OnNavigateToScreenListener
    public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        boolean z;
        if (getCallbackFragment() instanceof OnPreferenceStartScreenCallback) {
            z = ((OnPreferenceStartScreenCallback) getCallbackFragment()).onPreferenceStartScreen$ar$ds();
        } else {
            z = false;
        }
        for (Fragment fragment = this; !z && fragment != null; fragment = fragment.getParentFragment()) {
            if (fragment instanceof OnPreferenceStartScreenCallback) {
                z = ((OnPreferenceStartScreenCallback) fragment).onPreferenceStartScreen$ar$ds();
            }
        }
        if (!z && (getContext() instanceof OnPreferenceStartScreenCallback)) {
            z = ((OnPreferenceStartScreenCallback) getContext()).onPreferenceStartScreen$ar$ds();
        }
        if (!z && (getActivity() instanceof OnPreferenceStartScreenCallback)) {
            ((OnPreferenceStartScreenCallback) getActivity()).onPreferenceStartScreen$ar$ds();
        }
    }

    @Override // androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getFragment() == null) {
            return false;
        }
        getCallbackFragment();
        for (Fragment fragment = this; fragment != null; fragment = fragment.getParentFragment()) {
        }
        if (!(getContext() instanceof TalkBackPreferencesActivity) || !((TalkBackPreferencesActivity) getContext()).onPreferenceStartFragment$ar$ds(preference)) {
            if (!(getActivity() instanceof TalkBackPreferencesActivity) || !((TalkBackPreferencesActivity) getActivity()).onPreferenceStartFragment$ar$ds(preference)) {
                Log.w(TAG, "onPreferenceStartFragment is not implemented in the parent activity - attempting to use a fallback implementation. You should implement this method so that you can configure the new fragment that will be displayed, and set a transition between the fragments.");
                FragmentManager parentFragmentManager = getParentFragmentManager();
                Bundle extras = preference.getExtras();
                FragmentFactory fragmentFactory = parentFragmentManager.getFragmentFactory();
                requireActivity().getClassLoader();
                Fragment instantiate$ar$ds = fragmentFactory.instantiate$ar$ds(preference.getFragment());
                instantiate$ar$ds.setArguments(extras);
                instantiate$ar$ds.setTargetFragment(this, 0);
                FragmentTransaction replace = new BackStackRecord(parentFragmentManager).replace(((View) requireView().getParent()).getId(), instantiate$ar$ds);
                replace.addToBackStack$ar$ds(null);
                replace.commit();
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.saveHierarchyState(bundle2);
            bundle.putBundle(PREFERENCES_TAG, bundle2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        PreferenceManager preferenceManager = this.mPreferenceManager;
        preferenceManager.mOnPreferenceTreeClickListener = this;
        preferenceManager.mOnDisplayPreferenceDialogListener = this;
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        PreferenceManager preferenceManager = this.mPreferenceManager;
        preferenceManager.mOnPreferenceTreeClickListener = null;
        preferenceManager.mOnDisplayPreferenceDialogListener = null;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen preferenceScreen;
        if (bundle != null && (bundle2 = bundle.getBundle(PREFERENCES_TAG)) != null && (preferenceScreen = getPreferenceScreen()) != null) {
            preferenceScreen.restoreHierarchyState(bundle2);
        }
        if (this.mHavePrefs) {
            bindPreferences();
            Runnable runnable = this.mSelectPreferenceRunnable;
            if (runnable != null) {
                runnable.run();
                this.mSelectPreferenceRunnable = null;
            }
        }
        this.mInitDone = true;
    }

    public void scrollToPreference(Preference preference) {
        scrollToPreferenceInternal(preference, null);
    }

    public void setDivider(Drawable drawable) {
        DividerDecoration dividerDecoration = this.mDividerDecoration;
        if (drawable != null) {
            dividerDecoration.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            dividerDecoration.mDividerHeight = 0;
        }
        dividerDecoration.mDivider = drawable;
        PreferenceFragmentCompat.this.mList.invalidateItemDecorations();
    }

    public void setDividerHeight(int i) {
        DividerDecoration dividerDecoration = this.mDividerDecoration;
        dividerDecoration.mDividerHeight = i;
        PreferenceFragmentCompat.this.mList.invalidateItemDecorations();
    }

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        PreferenceManager preferenceManager;
        PreferenceScreen preferenceScreen2;
        if (preferenceScreen != null && preferenceScreen != (preferenceScreen2 = (preferenceManager = this.mPreferenceManager).mPreferenceScreen)) {
            if (preferenceScreen2 != null) {
                preferenceScreen2.onDetached();
            }
            preferenceManager.mPreferenceScreen = preferenceScreen;
            onUnbindPreferences();
            this.mHavePrefs = true;
            if (this.mInitDone) {
                postBindPreferences();
            }
        }
    }

    public void setPreferencesFromResource(int i, String str) {
        requirePreferenceManager();
        PreferenceScreen inflateFromResource = this.mPreferenceManager.inflateFromResource(requireContext(), i, null);
        PreferenceScreen preferenceScreen = inflateFromResource;
        if (str != null) {
            Preference findPreference = inflateFromResource.findPreference(str);
            boolean z = findPreference instanceof PreferenceScreen;
            preferenceScreen = findPreference;
            if (!z) {
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Preference object with key ", " is not a PreferenceScreen"));
            }
        }
        setPreferenceScreen(preferenceScreen);
    }

    public void scrollToPreference(String str) {
        scrollToPreferenceInternal(null, str);
    }

    protected void onBindPreferences() {
    }

    protected void onUnbindPreferences() {
    }
}
