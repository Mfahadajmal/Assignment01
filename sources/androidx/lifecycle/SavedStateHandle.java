package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager$$ExternalSyntheticLambda0;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.core.view.VelocityTrackerCompat;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SavedStateHandle {
    public final Map flows;
    public final Map liveDatas;
    public final Map regular;
    public final SavedStateRegistry.SavedStateProvider savedStateProvider;
    public final Map savedStateProviders;
    public static final VelocityTrackerCompat.Api34Impl Companion$ar$class_merging$2e5dec7f_0 = new VelocityTrackerCompat.Api34Impl();
    public static final Class[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavingStateLiveData extends MutableLiveData {
        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public final void setValue(Object obj) {
            throw null;
        }
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new FragmentManager$$ExternalSyntheticLambda0(this, 4);
    }

    public final boolean contains$ar$ds() {
        return this.regular.containsKey("FutureListenerState");
    }

    public final Object get$ar$ds$d696d055_0() {
        try {
            return this.regular.get("FutureListenerState");
        } catch (ClassCastException unused) {
            this.regular.remove("FutureListenerState");
            if (((SavingStateLiveData) this.liveDatas.remove("FutureListenerState")) == null) {
                this.flows.remove("FutureListenerState");
                return null;
            }
            throw null;
        }
    }

    public SavedStateHandle(Map map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new FragmentManager$$ExternalSyntheticLambda0(this, 4);
        linkedHashMap.putAll(map);
    }
}
