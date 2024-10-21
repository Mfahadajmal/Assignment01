package androidx.preference;

import android.R;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PreferenceGroupAdapter extends RecyclerView.Adapter implements Preference.OnPreferenceChangeInternalListener {
    private final PreferenceGroup mPreferenceGroup;
    private final List mPreferenceResourceDescriptors;
    private List mPreferences;
    private List mVisiblePreferences;
    private final Runnable mSyncRunnable = new SearchView$SearchAutoComplete.AnonymousClass1(this, 17);
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PreferenceResourceDescriptor {
        final String mClassName;
        final int mLayoutResId;
        final int mWidgetLayoutResId;

        public PreferenceResourceDescriptor(Preference preference) {
            this.mClassName = preference.getClass().getName();
            this.mLayoutResId = preference.getLayoutResource();
            this.mWidgetLayoutResId = preference.getWidgetLayoutResource();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof PreferenceResourceDescriptor)) {
                return false;
            }
            PreferenceResourceDescriptor preferenceResourceDescriptor = (PreferenceResourceDescriptor) obj;
            if (this.mLayoutResId != preferenceResourceDescriptor.mLayoutResId || this.mWidgetLayoutResId != preferenceResourceDescriptor.mWidgetLayoutResId || !TextUtils.equals(this.mClassName, preferenceResourceDescriptor.mClassName)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return ((((this.mLayoutResId + 527) * 31) + this.mWidgetLayoutResId) * 31) + this.mClassName.hashCode();
        }
    }

    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
        this.mPreferenceGroup = preferenceGroup;
        preferenceGroup.setOnPreferenceChangeInternalListener(this);
        this.mPreferences = new ArrayList();
        this.mVisiblePreferences = new ArrayList();
        this.mPreferenceResourceDescriptors = new ArrayList();
        if (preferenceGroup instanceof PreferenceScreen) {
            setHasStableIds(((PreferenceScreen) preferenceGroup).mShouldUseGeneratedIds);
        } else {
            setHasStableIds(true);
        }
        updatePreferences();
    }

    private final List createVisiblePreferencesList(final PreferenceGroup preferenceGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        int i = 0;
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = preferenceGroup.getPreference(i2);
            if (preference.isVisible()) {
                if (isGroupExpandable$ar$ds(preferenceGroup) && i >= preferenceGroup.mInitialExpandedChildrenCount) {
                    arrayList2.add(preference);
                } else {
                    arrayList.add(preference);
                }
                if (!(preference instanceof PreferenceGroup)) {
                    i++;
                } else {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                    if (!preferenceGroup2.isOnSameScreenAsChildren()) {
                        continue;
                    } else {
                        if (isGroupExpandable$ar$ds(preferenceGroup) && isGroupExpandable$ar$ds(preferenceGroup2)) {
                            throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                        }
                        for (Preference preference2 : createVisiblePreferencesList(preferenceGroup2)) {
                            if (isGroupExpandable$ar$ds(preferenceGroup) && i >= preferenceGroup.mInitialExpandedChildrenCount) {
                                arrayList2.add(preference2);
                            } else {
                                arrayList.add(preference2);
                            }
                            i++;
                        }
                    }
                }
            }
        }
        if (isGroupExpandable$ar$ds(preferenceGroup) && i > preferenceGroup.mInitialExpandedChildrenCount) {
            ExpandButton expandButton = new ExpandButton(preferenceGroup.getContext(), arrayList2, preferenceGroup.getId());
            expandButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: androidx.preference.PreferenceGroupAdapter.3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference3) {
                    preferenceGroup.setInitialExpandedChildrenCount(Preference.DEFAULT_ORDER);
                    PreferenceGroupAdapter.this.onPreferenceHierarchyChange$ar$ds();
                    return true;
                }
            });
            arrayList.add(expandButton);
        }
        return arrayList;
    }

    private final void flattenPreferenceGroup(List list, PreferenceGroup preferenceGroup) {
        synchronized (preferenceGroup) {
            Collections.sort(preferenceGroup.mPreferences);
        }
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            list.add(preference);
            PreferenceResourceDescriptor preferenceResourceDescriptor = new PreferenceResourceDescriptor(preference);
            if (!this.mPreferenceResourceDescriptors.contains(preferenceResourceDescriptor)) {
                this.mPreferenceResourceDescriptors.add(preferenceResourceDescriptor);
            }
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                if (preferenceGroup2.isOnSameScreenAsChildren()) {
                    flattenPreferenceGroup(list, preferenceGroup2);
                }
            }
            preference.setOnPreferenceChangeInternalListener(this);
        }
    }

    private static final boolean isGroupExpandable$ar$ds(PreferenceGroup preferenceGroup) {
        if (preferenceGroup.mInitialExpandedChildrenCount != Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }

    public final Preference getItem(int i) {
        if (i >= 0 && i < getItemCount()) {
            return (Preference) this.mVisiblePreferences.get(i);
        }
        return null;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.mVisiblePreferences.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        if (!this.mHasStableIds) {
            return -1L;
        }
        return getItem(i).getId();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        PreferenceResourceDescriptor preferenceResourceDescriptor = new PreferenceResourceDescriptor(getItem(i));
        int indexOf = this.mPreferenceResourceDescriptors.indexOf(preferenceResourceDescriptor);
        if (indexOf != -1) {
            return indexOf;
        }
        List list = this.mPreferenceResourceDescriptors;
        int size = list.size();
        list.add(preferenceResourceDescriptor);
        return size;
    }

    public final int getPreferenceAdapterPosition(Preference preference) {
        int size = this.mVisiblePreferences.size();
        for (int i = 0; i < size; i++) {
            Preference preference2 = (Preference) this.mVisiblePreferences.get(i);
            if (preference2 != null && preference2.equals(preference)) {
                return i;
            }
        }
        return -1;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        PreferenceViewHolder preferenceViewHolder = (PreferenceViewHolder) viewHolder;
        View view = preferenceViewHolder.itemView;
        Preference item = getItem(i);
        Drawable background = view.getBackground();
        Drawable drawable = preferenceViewHolder.mBackground;
        if (background != drawable) {
            preferenceViewHolder.itemView.setBackground(drawable);
        }
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.title);
        if (textView != null && preferenceViewHolder.mTitleTextColors != null && !textView.getTextColors().equals(preferenceViewHolder.mTitleTextColors)) {
            textView.setTextColor(preferenceViewHolder.mTitleTextColors);
        }
        item.onBindViewHolder(preferenceViewHolder);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        PreferenceResourceDescriptor preferenceResourceDescriptor = (PreferenceResourceDescriptor) this.mPreferenceResourceDescriptors.get(i);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.BackgroundStyle);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable == null) {
            drawable = AppCompatDelegate.Api33Impl.getDrawable(viewGroup.getContext(), R.drawable.list_selector_background);
        }
        obtainStyledAttributes.recycle();
        View inflate = from.inflate(preferenceResourceDescriptor.mLayoutResId, viewGroup, false);
        if (inflate.getBackground() == null) {
            inflate.setBackground(drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.id.widget_frame);
        if (viewGroup2 != null) {
            int i2 = preferenceResourceDescriptor.mWidgetLayoutResId;
            if (i2 != 0) {
                from.inflate(i2, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new PreferenceViewHolder(inflate);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public final void onPreferenceChange(Preference preference) {
        int indexOf = this.mVisiblePreferences.indexOf(preference);
        if (indexOf != -1) {
            this.mObservable.notifyItemRangeChanged(indexOf, 1, preference);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public final void onPreferenceHierarchyChange$ar$ds() {
        this.mHandler.removeCallbacks(this.mSyncRunnable);
        this.mHandler.post(this.mSyncRunnable);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public final void onPreferenceVisibilityChange(Preference preference) {
        onPreferenceHierarchyChange$ar$ds();
    }

    public final void updatePreferences() {
        Iterator it = this.mPreferences.iterator();
        while (it.hasNext()) {
            ((Preference) it.next()).setOnPreferenceChangeInternalListener(null);
        }
        ArrayList arrayList = new ArrayList(this.mPreferences.size());
        this.mPreferences = arrayList;
        flattenPreferenceGroup(arrayList, this.mPreferenceGroup);
        this.mVisiblePreferences = createVisiblePreferencesList(this.mPreferenceGroup);
        this.mPreferenceGroup.getPreferenceManager();
        notifyDataSetChanged();
        Iterator it2 = this.mPreferences.iterator();
        while (it2.hasNext()) {
            ((Preference) it2.next()).clearWasDetached();
        }
    }

    public final int getPreferenceAdapterPosition(String str) {
        int size = this.mVisiblePreferences.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(str, ((Preference) this.mVisiblePreferences.get(i)).getKey())) {
                return i;
            }
        }
        return -1;
    }
}
