package androidx.preference;

import android.view.View;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PreferenceDataStore {
    public static final void set(View view, SavedStateRegistryOwner savedStateRegistryOwner) {
        view.getClass();
        view.setTag(R.id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }
}
