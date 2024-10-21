package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultRegistry;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompatBuilder$Api28Impl;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.fragment.NavHostFragment;
import androidx.savedstate.SavedStateRegistry;
import com.google.common.android.concurrent.FutureCallbackViewModel;
import com.google.common.android.concurrent.ParcelableFuture;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FragmentManager$$ExternalSyntheticLambda0 implements SavedStateRegistry.SavedStateProvider {
    public final /* synthetic */ Object FragmentManager$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public FragmentManager$$ExternalSyntheticLambda0(AppCompatActivity appCompatActivity, int i) {
        this.switching_field = i;
        this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0 = appCompatActivity;
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public final Bundle saveState() {
        MutableLiveData mutableLiveData;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                Bundle bundle = new Bundle();
                                FutureCallbackViewModel futureCallbackViewModel = (FutureCallbackViewModel) this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0;
                                bundle.putString("appVersion", futureCallbackViewModel.appVersionMarker);
                                int size = futureCallbackViewModel.callbacks.size();
                                int[] iArr = new int[size];
                                for (int i2 = 0; i2 < size; i2++) {
                                    iArr[i2] = futureCallbackViewModel.callbacks.keyAt(i2);
                                }
                                bundle.putIntArray("callback_ids", iArr);
                                Set set = futureCallbackViewModel.pendingFutures;
                                bundle.putParcelableArray("futures", (Parcelable[]) set.toArray(new ParcelableFuture[((ArraySet) set)._size]));
                                return bundle;
                            }
                            int i3 = ((NavHostFragment) this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0).graphId;
                            if (i3 != 0) {
                                return NotificationCompatBuilder$Api28Impl.bundleOf(new Pair("android-support-nav:fragment:graphId", Integer.valueOf(i3)));
                            }
                            Bundle bundle2 = Bundle.EMPTY;
                            bundle2.getClass();
                            return bundle2;
                        }
                        SavedStateHandle savedStateHandle = (SavedStateHandle) this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0;
                        for (Map.Entry entry : OnDeviceLanguageIdentificationLogEvent.toMap(savedStateHandle.savedStateProviders).entrySet()) {
                            String str = (String) entry.getKey();
                            Bundle saveState = ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState();
                            str.getClass();
                            if (saveState != null) {
                                Class[] clsArr = SavedStateHandle.ACCEPTABLE_CLASSES;
                                for (int i4 = 0; i4 < 29; i4++) {
                                    Class cls = clsArr[i4];
                                    cls.getClass();
                                    if (!cls.isInstance(saveState)) {
                                    }
                                }
                                throw new IllegalArgumentException("Can't put value with type " + saveState.getClass() + " into saved state");
                            }
                            Object obj = savedStateHandle.liveDatas.get(str);
                            if (obj instanceof MutableLiveData) {
                                mutableLiveData = (MutableLiveData) obj;
                            } else {
                                mutableLiveData = null;
                            }
                            if (mutableLiveData != null) {
                                mutableLiveData.setValue(saveState);
                            } else {
                                savedStateHandle.regular.put(str, saveState);
                            }
                            StateFlowImpl stateFlowImpl = (StateFlowImpl) savedStateHandle.flows.get(str);
                            if (stateFlowImpl != null) {
                                stateFlowImpl.setValue(saveState);
                            }
                        }
                        Set<String> keySet = savedStateHandle.regular.keySet();
                        ArrayList arrayList = new ArrayList(keySet.size());
                        ArrayList arrayList2 = new ArrayList(arrayList.size());
                        for (String str2 : keySet) {
                            arrayList.add(str2);
                            arrayList2.add(savedStateHandle.regular.get(str2));
                        }
                        return NotificationCompatBuilder$Api28Impl.bundleOf(new Pair("keys", arrayList), new Pair("values", arrayList2));
                    }
                    Bundle bundle3 = new Bundle();
                    ActivityResultRegistry activityResultRegistry = ((ComponentActivity) this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0).activityResultRegistry;
                    bundle3.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(activityResultRegistry.keyToRc.values()));
                    bundle3.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(activityResultRegistry.keyToRc.keySet()));
                    bundle3.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(activityResultRegistry.launchedKeys));
                    bundle3.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", new Bundle(activityResultRegistry.pendingResults));
                    return bundle3;
                }
                Bundle bundle4 = new Bundle();
                ((AppCompatActivity) this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0).getDelegate();
                return bundle4;
            }
            FragmentActivity fragmentActivity = (FragmentActivity) this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0;
            fragmentActivity.markFragmentsCreated();
            fragmentActivity.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
            return new Bundle();
        }
        return ((FragmentManager) this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0).saveAllStateInternal();
    }

    public /* synthetic */ FragmentManager$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.FragmentManager$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }
}
