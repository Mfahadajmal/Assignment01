package android.support.v4.app;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SpecialEffectsController;
import android.support.v4.os.BundleCompat$Api33Impl;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.activity.Cancellable;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.FragmentNavigator$onAttach$2;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.work.impl.model.WorkName;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import j$.util.DesugarCollections;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentManager {
    static final boolean USE_PREDICTIVE_BACK = true;
    ArrayList mBackStack;
    public ArrayList mBackStackChangeListeners;
    public final AtomicInteger mBackStackIndex;
    public final Map mBackStackStates;
    public FragmentContainer mContainer;
    private ArrayList mCreatedMenus;
    int mCurState;
    private BundleCompat$Api33Impl mDefaultSpecialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean mDestroyed;
    private Runnable mExecCommit;
    private boolean mExecutingActions;
    public final FragmentStore mFragmentStore;
    private boolean mHavePendingDeferredStart;
    public FragmentHostCallback mHost;
    private FragmentFactory mHostFragmentFactory;
    ArrayDeque mLaunchedFragments;
    public final FragmentLayoutInflaterFactory mLayoutInflaterFactory;
    public final WorkName mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl mMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean mNeedMenuInvalidate;
    public FragmentManagerViewModel mNonConfig;
    public final CopyOnWriteArrayList mOnAttachListeners;
    private final OnBackPressedCallback mOnBackPressedCallback;
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final Consumer mOnConfigurationChangedListener;
    private final Consumer mOnMultiWindowModeChangedListener;
    private final Consumer mOnPictureInPictureModeChangedListener;
    private final Consumer mOnTrimMemoryListener;
    public Fragment mParent;
    private final ArrayList mPendingActions;
    Fragment mPrimaryNav;
    public ActivityResultLauncher mRequestPermissions;
    private final Map mResults;
    public ActivityResultLauncher mStartActivityForResult;
    public ActivityResultLauncher mStartIntentSenderForResult;
    public boolean mStateSaved;
    public boolean mStopped;
    private ArrayList mTmpAddedFragments;
    private ArrayList mTmpIsPop;
    private ArrayList mTmpRecords;
    BackStackRecord mTransitioningOp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: android.support.v4.app.FragmentManager$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends OnBackPressedCallback {
        public AnonymousClass1() {
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackCancelled() {
            if (FragmentManager.isLoggingEnabled(3)) {
                Objects.toString(FragmentManager.this);
            }
            FragmentManager fragmentManager = FragmentManager.this;
            BackStackRecord backStackRecord = fragmentManager.mTransitioningOp;
            if (backStackRecord != null) {
                backStackRecord.mCommitted = false;
                DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(fragmentManager, 7);
                if (backStackRecord.mCommitRunnables == null) {
                    backStackRecord.mCommitRunnables = new ArrayList();
                }
                backStackRecord.mCommitRunnables.add(defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4);
                fragmentManager.mTransitioningOp.commit();
                fragmentManager.executePendingTransactions$ar$ds();
            }
            FragmentManager.this.mTransitioningOp = null;
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            if (FragmentManager.isLoggingEnabled(3)) {
                Objects.toString(FragmentManager.this);
            }
            FragmentManager.this.handleOnBackPressed();
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackProgressed(BackEventCompat backEventCompat) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Objects.toString(FragmentManager.this);
            }
            FragmentManager fragmentManager = FragmentManager.this;
            BackStackRecord backStackRecord = fragmentManager.mTransitioningOp;
            if (backStackRecord != null) {
                ArrayList arrayList = new ArrayList(Collections.singletonList(backStackRecord));
                for (SpecialEffectsController specialEffectsController : fragmentManager.collectChangedControllers(arrayList, 0, 1)) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        float f = backEventCompat.progress;
                    }
                    List list = specialEffectsController.runningOperations;
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList2, ((SpecialEffectsController.Operation) it.next()).effects);
                    }
                    List list2 = OnDeviceLanguageIdentificationLogEvent.toList(OnDeviceLanguageIdentificationLogEvent.toSet(arrayList2));
                    int size = list2.size();
                    for (int i = 0; i < size; i++) {
                        SpecialEffectsController.Effect effect = (SpecialEffectsController.Effect) list2.get(i);
                        ViewGroup viewGroup = specialEffectsController.container;
                        effect.onProgress$ar$ds(backEventCompat);
                    }
                }
                ArrayList arrayList3 = FragmentManager.this.mBackStackChangeListeners;
                int size2 = arrayList3.size();
                for (int i2 = 0; i2 < size2; i2++) {
                }
            }
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackStarted$ar$ds() {
            if (FragmentManager.isLoggingEnabled(3)) {
                Objects.toString(FragmentManager.this);
            }
            FragmentManager.this.endAnimatingAwayFragments();
            FragmentManager fragmentManager = FragmentManager.this;
            fragmentManager.enqueueAction(new PrepareBackStackTransitionState(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: android.support.v4.app.FragmentManager$3 */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 extends FragmentFactory {
        public AnonymousClass3() {
        }

        @Override // android.support.v4.app.FragmentFactory
        public final Fragment instantiate$ar$ds(String str) {
            return Fragment.instantiate(FragmentManager.this.mHost.context, str, null);
        }
    }

    /* compiled from: PG */
    /* renamed from: android.support.v4.app.FragmentManager$7 */
    /* loaded from: classes.dex */
    public final class AnonymousClass7 implements FragmentOnAttachListener {
        public AnonymousClass7() {
        }

        @Override // android.support.v4.app.FragmentOnAttachListener
        public final void onAttachFragment$ar$ds(Fragment fragment) {
            Fragment.this.onAttachFragment(fragment);
        }
    }

    /* compiled from: PG */
    /* renamed from: android.support.v4.app.FragmentManager$8 */
    /* loaded from: classes.dex */
    public final class AnonymousClass8 implements ActivityResultCallback {
        private final /* synthetic */ int switching_field;
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass8(FragmentManager fragmentManager, int i) {
            r2 = i;
            this.this$0 = fragmentManager;
        }

        @Override // androidx.activity.result.ActivityResultCallback
        public final /* synthetic */ void onActivityResult(Object obj) {
            int i;
            int i2 = r2;
            if (i2 != 0) {
                if (i2 != 1) {
                    ActivityResult activityResult = (ActivityResult) obj;
                    LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                    if (launchedFragmentInfo == null) {
                        toString();
                        Log.w("FragmentManager", "No IntentSenders were started for ".concat(toString()));
                        return;
                    }
                    FragmentStore fragmentStore = this.this$0.mFragmentStore;
                    String str = launchedFragmentInfo.mWho;
                    Fragment findFragmentByWho = fragmentStore.findFragmentByWho(str);
                    if (findFragmentByWho == null) {
                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment ".concat(String.valueOf(str)));
                        return;
                    } else {
                        findFragmentByWho.onActivityResult(launchedFragmentInfo.mRequestCode, activityResult.resultCode, activityResult.data);
                        return;
                    }
                }
                Map map = (Map) obj;
                String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                ArrayList arrayList = new ArrayList(map.values());
                int[] iArr = new int[arrayList.size()];
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (true != ((Boolean) arrayList.get(i3)).booleanValue()) {
                        i = -1;
                    } else {
                        i = 0;
                    }
                    iArr[i3] = i;
                }
                LaunchedFragmentInfo launchedFragmentInfo2 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                if (launchedFragmentInfo2 == null) {
                    toString();
                    Log.w("FragmentManager", "No permissions were requested for ".concat(toString()));
                    return;
                }
                FragmentStore fragmentStore2 = this.this$0.mFragmentStore;
                String str2 = launchedFragmentInfo2.mWho;
                Fragment findFragmentByWho2 = fragmentStore2.findFragmentByWho(str2);
                if (findFragmentByWho2 == null) {
                    Log.w("FragmentManager", "Permission request result delivered for unknown Fragment ".concat(String.valueOf(str2)));
                    return;
                } else {
                    findFragmentByWho2.onRequestPermissionsResult(launchedFragmentInfo2.mRequestCode, strArr, iArr);
                    return;
                }
            }
            ActivityResult activityResult2 = (ActivityResult) obj;
            LaunchedFragmentInfo launchedFragmentInfo3 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollLast();
            if (launchedFragmentInfo3 == null) {
                toString();
                Log.w("FragmentManager", "No Activities were started for result for ".concat(toString()));
                return;
            }
            FragmentStore fragmentStore3 = this.this$0.mFragmentStore;
            String str3 = launchedFragmentInfo3.mWho;
            Fragment findFragmentByWho3 = fragmentStore3.findFragmentByWho(str3);
            if (findFragmentByWho3 == null) {
                Log.w("FragmentManager", "Activity result delivered for unknown Fragment ".concat(String.valueOf(str3)));
            } else {
                findFragmentByWho3.onActivityResult(launchedFragmentInfo3.mRequestCode, activityResult2.resultCode, activityResult2.data);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FragmentIntentSenderContract extends ActivityResultContract {
        @Override // androidx.activity.result.contract.ActivityResultContract
        public final /* bridge */ /* synthetic */ Intent createIntent$ar$ds(Object obj) {
            Bundle bundleExtra;
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) obj;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent intent2 = intentSenderRequest.fillInIntent;
            if (intent2 != null && (bundleExtra = intent2.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                intent2.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (intent2.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    IntentSenderRequest.Builder builder = new IntentSenderRequest.Builder(intentSenderRequest.intentSender);
                    builder.fillInIntent = null;
                    builder.setFlags$ar$ds(intentSenderRequest.flagsValues, intentSenderRequest.flagsMask);
                    intentSenderRequest = builder.build();
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
            if (FragmentManager.isLoggingEnabled(2)) {
                intent.toString();
            }
            return intent;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        public final /* synthetic */ Object parseResult(int i, Intent intent) {
            return new ActivityResult(i, intent);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new BackStackState.AnonymousClass1(2);
        final int mRequestCode;
        final String mWho;

        public LaunchedFragmentInfo(String str, int i) {
            this.mWho = str;
            this.mRequestCode = i;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mWho);
            parcel.writeInt(this.mRequestCode);
        }

        public LaunchedFragmentInfo(Parcel parcel) {
            this.mWho = parcel.readString();
            this.mRequestCode = parcel.readInt();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OpGenerator {
        boolean generateOps(ArrayList arrayList, ArrayList arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PopBackStackState implements OpGenerator {
        final int mId;
        final String mName;

        public PopBackStackState(String str, int i) {
            this.mName = str;
            this.mId = i;
        }

        @Override // android.support.v4.app.FragmentManager.OpGenerator
        public final boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
            Fragment fragment = FragmentManager.this.mPrimaryNav;
            if (fragment != null && this.mId < 0 && this.mName == null && fragment.getChildFragmentManager().popBackStackImmediate()) {
                return false;
            }
            return FragmentManager.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, 1);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class PrepareBackStackTransitionState implements OpGenerator {
        public PrepareBackStackTransitionState() {
        }

        @Override // android.support.v4.app.FragmentManager.OpGenerator
        public final boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
            int i;
            FragmentManager fragmentManager = FragmentManager.this;
            fragmentManager.mTransitioningOp = (BackStackRecord) fragmentManager.mBackStack.get(r1.size() - 1);
            ArrayList arrayList3 = fragmentManager.mTransitioningOp.mOps;
            int size = arrayList3.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Fragment fragment = ((FragmentTransaction.Op) arrayList3.get(i3)).mFragment;
                if (fragment != null) {
                    fragment.mTransitioning = true;
                }
            }
            boolean popBackStackState = fragmentManager.popBackStackState(arrayList, arrayList2, null, -1, 0);
            if (!FragmentManager.this.mBackStackChangeListeners.isEmpty() && arrayList.size() > 0) {
                boolean booleanValue = ((Boolean) arrayList2.get(arrayList.size() - 1)).booleanValue();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                int size2 = arrayList.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    linkedHashSet.addAll(FragmentManager.fragmentsFromRecord$ar$ds((BackStackRecord) arrayList.get(i4)));
                }
                ArrayList arrayList4 = FragmentManager.this.mBackStackChangeListeners;
                int size3 = arrayList4.size();
                while (i2 < size3) {
                    FragmentNavigator$onAttach$2 fragmentNavigator$onAttach$2 = (FragmentNavigator$onAttach$2) arrayList4.get(i2);
                    Iterator it = linkedHashSet.iterator();
                    while (true) {
                        i = i2 + 1;
                        if (it.hasNext()) {
                            fragmentNavigator$onAttach$2.onBackStackChangeStarted((Fragment) it.next(), booleanValue);
                        }
                    }
                    i2 = i;
                }
            }
            return popBackStackState;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RestoreBackStackState implements OpGenerator {
        private final String mName;

        public RestoreBackStackState(String str) {
            this.mName = str;
        }

        @Override // android.support.v4.app.FragmentManager.OpGenerator
        public final boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
            FragmentManager fragmentManager = FragmentManager.this;
            BackStackState backStackState = (BackStackState) fragmentManager.mBackStackStates.remove(this.mName);
            boolean z = false;
            if (backStackState != null) {
                HashMap hashMap = new HashMap();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    BackStackRecord backStackRecord = (BackStackRecord) arrayList.get(i);
                    if (backStackRecord.mBeingSaved) {
                        ArrayList arrayList3 = backStackRecord.mOps;
                        int size2 = arrayList3.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            Fragment fragment = ((FragmentTransaction.Op) arrayList3.get(i2)).mFragment;
                            if (fragment != null) {
                                hashMap.put(fragment.mWho, fragment);
                            }
                        }
                    }
                }
                HashMap hashMap2 = new HashMap(backStackState.mFragments.size());
                for (String str : backStackState.mFragments) {
                    Fragment fragment2 = (Fragment) hashMap.get(str);
                    if (fragment2 != null) {
                        hashMap2.put(fragment2.mWho, fragment2);
                    } else {
                        Bundle savedState = fragmentManager.mFragmentStore.setSavedState(str, null);
                        if (savedState != null) {
                            ClassLoader classLoader = fragmentManager.mHost.context.getClassLoader();
                            Fragment instantiate = ((FragmentState) savedState.getParcelable("state")).instantiate(fragmentManager.getFragmentFactory(), classLoader);
                            instantiate.mSavedFragmentState = savedState;
                            if (instantiate.mSavedFragmentState.getBundle("savedInstanceState") == null) {
                                instantiate.mSavedFragmentState.putBundle("savedInstanceState", new Bundle());
                            }
                            Bundle bundle = savedState.getBundle("arguments");
                            if (bundle != null) {
                                bundle.setClassLoader(classLoader);
                            }
                            instantiate.setArguments(bundle);
                            hashMap2.put(instantiate.mWho, instantiate);
                        }
                    }
                }
                ArrayList arrayList4 = new ArrayList();
                for (BackStackRecordState backStackRecordState : backStackState.mTransactions) {
                    BackStackRecord backStackRecord2 = new BackStackRecord(fragmentManager);
                    backStackRecordState.fillInBackStackRecord(backStackRecord2);
                    for (int i3 = 0; i3 < backStackRecordState.mFragmentWhos.size(); i3++) {
                        String str2 = (String) backStackRecordState.mFragmentWhos.get(i3);
                        if (str2 != null) {
                            Fragment fragment3 = (Fragment) hashMap2.get(str2);
                            if (fragment3 != null) {
                                ((FragmentTransaction.Op) backStackRecord2.mOps.get(i3)).mFragment = fragment3;
                            } else {
                                throw new IllegalStateException("Restoring FragmentTransaction " + backStackRecordState.mName + " failed due to missing saved state for Fragment (" + str2 + ")");
                            }
                        }
                    }
                    arrayList4.add(backStackRecord2);
                }
                Iterator it = arrayList4.iterator();
                while (it.hasNext()) {
                    ((BackStackRecord) it.next()).generateOps(arrayList, arrayList2);
                    z = true;
                }
            }
            return z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SaveBackStackState implements OpGenerator {
        private final String mName;

        public SaveBackStackState(String str) {
            this.mName = str;
        }

        @Override // android.support.v4.app.FragmentManager.OpGenerator
        public final boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
            String str;
            String concat;
            int i;
            FragmentManager fragmentManager = FragmentManager.this;
            String str2 = this.mName;
            int findBackStackIndex = fragmentManager.findBackStackIndex(str2, -1, true);
            if (findBackStackIndex < 0) {
                return false;
            }
            for (int i2 = findBackStackIndex; i2 < fragmentManager.mBackStack.size(); i2++) {
                BackStackRecord backStackRecord = (BackStackRecord) fragmentManager.mBackStack.get(i2);
                if (!backStackRecord.mReorderingAllowed) {
                    fragmentManager.throwException(new IllegalArgumentException("saveBackStack(\"" + str2 + "\") included FragmentTransactions must use setReorderingAllowed(true) to ensure that the back stack can be restored as an atomic operation. Found " + backStackRecord + " that did not use setReorderingAllowed(true)."));
                }
            }
            HashSet hashSet = new HashSet();
            for (int i3 = findBackStackIndex; i3 < fragmentManager.mBackStack.size(); i3++) {
                BackStackRecord backStackRecord2 = (BackStackRecord) fragmentManager.mBackStack.get(i3);
                HashSet hashSet2 = new HashSet();
                HashSet hashSet3 = new HashSet();
                ArrayList arrayList3 = backStackRecord2.mOps;
                int size = arrayList3.size();
                for (int i4 = 0; i4 < size; i4++) {
                    FragmentTransaction.Op op = (FragmentTransaction.Op) arrayList3.get(i4);
                    Fragment fragment = op.mFragment;
                    if (fragment != null) {
                        if (!op.mFromExpandedOp || (i = op.mCmd) == 1 || i == 2 || i == 8) {
                            hashSet.add(fragment);
                            hashSet2.add(fragment);
                        }
                        int i5 = op.mCmd;
                        if (i5 == 1 || i5 == 2) {
                            hashSet3.add(fragment);
                        }
                    }
                }
                hashSet2.removeAll(hashSet3);
                if (!hashSet2.isEmpty()) {
                    StringBuilder sb = new StringBuilder("saveBackStack(\"");
                    sb.append(str2);
                    sb.append("\") must be self contained and not reference fragments from non-saved FragmentTransactions. Found reference to fragment");
                    if (hashSet2.size() == 1) {
                        Object next = hashSet2.iterator().next();
                        Objects.toString(next);
                        concat = " ".concat(String.valueOf(next));
                    } else {
                        hashSet2.toString();
                        concat = "s ".concat(hashSet2.toString());
                    }
                    sb.append(concat);
                    sb.append(" in ");
                    sb.append(backStackRecord2);
                    sb.append(" that were previously added to the FragmentManager through a separate FragmentTransaction.");
                    fragmentManager.throwException(new IllegalArgumentException(sb.toString()));
                }
            }
            ArrayDeque arrayDeque = new ArrayDeque(hashSet);
            while (!arrayDeque.isEmpty()) {
                Fragment fragment2 = (Fragment) arrayDeque.removeFirst();
                if (fragment2.mRetainInstance) {
                    StringBuilder sb2 = new StringBuilder("saveBackStack(\"");
                    sb2.append(str2);
                    sb2.append("\") must not contain retained fragments. Found ");
                    if (true != hashSet.contains(fragment2)) {
                        str = "retained child ";
                    } else {
                        str = "direct reference to retained ";
                    }
                    sb2.append(str);
                    sb2.append("fragment ");
                    sb2.append(fragment2);
                    fragmentManager.throwException(new IllegalArgumentException(sb2.toString()));
                }
                for (Fragment fragment3 : fragment2.mChildFragmentManager.mFragmentStore.getActiveFragments()) {
                    if (fragment3 != null) {
                        arrayDeque.addLast(fragment3);
                    }
                }
            }
            ArrayList arrayList4 = new ArrayList();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                arrayList4.add(((Fragment) it.next()).mWho);
            }
            ArrayList arrayList5 = new ArrayList(fragmentManager.mBackStack.size() - findBackStackIndex);
            for (int i6 = findBackStackIndex; i6 < fragmentManager.mBackStack.size(); i6++) {
                arrayList5.add(null);
            }
            BackStackState backStackState = new BackStackState(arrayList4, arrayList5);
            int i7 = -1;
            for (int size2 = fragmentManager.mBackStack.size() - 1; size2 >= findBackStackIndex; size2--) {
                BackStackRecord backStackRecord3 = (BackStackRecord) fragmentManager.mBackStack.remove(size2);
                BackStackRecord backStackRecord4 = new BackStackRecord(backStackRecord3);
                int size3 = backStackRecord4.mOps.size();
                while (true) {
                    size3 += i7;
                    if (size3 >= 0) {
                        FragmentTransaction.Op op2 = (FragmentTransaction.Op) backStackRecord4.mOps.get(size3);
                        if (op2.mFromExpandedOp) {
                            int i8 = size3 - 1;
                            if (op2.mCmd == 8) {
                                op2.mFromExpandedOp = false;
                                backStackRecord4.mOps.remove(i8);
                                size3 = i8;
                                i7 = -1;
                            } else {
                                int i9 = op2.mFragment.mContainerId;
                                op2.mCmd = 2;
                                op2.mFromExpandedOp = false;
                                while (i8 >= 0) {
                                    FragmentTransaction.Op op3 = (FragmentTransaction.Op) backStackRecord4.mOps.get(i8);
                                    if (op3.mFromExpandedOp && op3.mFragment.mContainerId == i9) {
                                        backStackRecord4.mOps.remove(i8);
                                        size3--;
                                    }
                                    i8--;
                                }
                            }
                        }
                        i7 = -1;
                    }
                }
                i7 = -1;
                arrayList5.set(size2 - findBackStackIndex, new BackStackRecordState(backStackRecord4));
                backStackRecord3.mBeingSaved = true;
                arrayList.add(backStackRecord3);
                arrayList2.add(true);
            }
            fragmentManager.mBackStackStates.put(str2, backStackState);
            return true;
        }
    }

    public FragmentManager() {
        this.mPendingActions = new ArrayList();
        this.mFragmentStore = new FragmentStore();
        this.mBackStack = new ArrayList();
        this.mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
        this.mTransitioningOp = null;
        this.mOnBackPressedCallback = new OnBackPressedCallback() { // from class: android.support.v4.app.FragmentManager.1
            public AnonymousClass1() {
            }

            @Override // androidx.activity.OnBackPressedCallback
            public final void handleOnBackCancelled() {
                if (FragmentManager.isLoggingEnabled(3)) {
                    Objects.toString(FragmentManager.this);
                }
                FragmentManager fragmentManager = FragmentManager.this;
                BackStackRecord backStackRecord = fragmentManager.mTransitioningOp;
                if (backStackRecord != null) {
                    backStackRecord.mCommitted = false;
                    DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(fragmentManager, 7);
                    if (backStackRecord.mCommitRunnables == null) {
                        backStackRecord.mCommitRunnables = new ArrayList();
                    }
                    backStackRecord.mCommitRunnables.add(defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4);
                    fragmentManager.mTransitioningOp.commit();
                    fragmentManager.executePendingTransactions$ar$ds();
                }
                FragmentManager.this.mTransitioningOp = null;
            }

            @Override // androidx.activity.OnBackPressedCallback
            public final void handleOnBackPressed() {
                if (FragmentManager.isLoggingEnabled(3)) {
                    Objects.toString(FragmentManager.this);
                }
                FragmentManager.this.handleOnBackPressed();
            }

            @Override // androidx.activity.OnBackPressedCallback
            public final void handleOnBackProgressed(BackEventCompat backEventCompat) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(FragmentManager.this);
                }
                FragmentManager fragmentManager = FragmentManager.this;
                BackStackRecord backStackRecord = fragmentManager.mTransitioningOp;
                if (backStackRecord != null) {
                    ArrayList arrayList = new ArrayList(Collections.singletonList(backStackRecord));
                    for (SpecialEffectsController specialEffectsController : fragmentManager.collectChangedControllers(arrayList, 0, 1)) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            float f = backEventCompat.progress;
                        }
                        List list = specialEffectsController.runningOperations;
                        ArrayList arrayList2 = new ArrayList();
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList2, ((SpecialEffectsController.Operation) it.next()).effects);
                        }
                        List list2 = OnDeviceLanguageIdentificationLogEvent.toList(OnDeviceLanguageIdentificationLogEvent.toSet(arrayList2));
                        int size = list2.size();
                        for (int i = 0; i < size; i++) {
                            SpecialEffectsController.Effect effect = (SpecialEffectsController.Effect) list2.get(i);
                            ViewGroup viewGroup = specialEffectsController.container;
                            effect.onProgress$ar$ds(backEventCompat);
                        }
                    }
                    ArrayList arrayList3 = FragmentManager.this.mBackStackChangeListeners;
                    int size2 = arrayList3.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                    }
                }
            }

            @Override // androidx.activity.OnBackPressedCallback
            public final void handleOnBackStarted$ar$ds() {
                if (FragmentManager.isLoggingEnabled(3)) {
                    Objects.toString(FragmentManager.this);
                }
                FragmentManager.this.endAnimatingAwayFragments();
                FragmentManager fragmentManager = FragmentManager.this;
                fragmentManager.enqueueAction(new PrepareBackStackTransitionState(), false);
            }
        };
        this.mBackStackIndex = new AtomicInteger();
        this.mBackStackStates = DesugarCollections.synchronizedMap(new HashMap());
        this.mResults = DesugarCollections.synchronizedMap(new HashMap());
        DesugarCollections.synchronizedMap(new HashMap());
        this.mBackStackChangeListeners = new ArrayList();
        this.mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging = new WorkName(this);
        this.mOnAttachListeners = new CopyOnWriteArrayList();
        this.mOnConfigurationChangedListener = new FragmentActivity$$ExternalSyntheticLambda2(this, 2);
        this.mOnTrimMemoryListener = new FragmentActivity$$ExternalSyntheticLambda2(this, 3);
        this.mOnMultiWindowModeChangedListener = new FragmentActivity$$ExternalSyntheticLambda2(this, 4);
        this.mOnPictureInPictureModeChangedListener = new FragmentActivity$$ExternalSyntheticLambda2(this, 5);
        this.mMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        this.mCurState = -1;
        this.mHostFragmentFactory = new FragmentFactory() { // from class: android.support.v4.app.FragmentManager.3
            public AnonymousClass3() {
            }

            @Override // android.support.v4.app.FragmentFactory
            public final Fragment instantiate$ar$ds(String str) {
                return Fragment.instantiate(FragmentManager.this.mHost.context, str, null);
            }
        };
        this.mDefaultSpecialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging = new BundleCompat$Api33Impl();
        this.mLaunchedFragments = new ArrayDeque();
        this.mExecCommit = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 8, null);
    }

    private final void checkStateLoss() {
        if (!isStateSaved()) {
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private final void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    private final Set collectAllSpecialEffectsController() {
        HashSet hashSet = new HashSet();
        Iterator it = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((FragmentStateManager) it.next()).mFragment.mContainer;
            if (viewGroup != null) {
                hashSet.add(BundleCompat$Api33Impl.getOrCreateController$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(viewGroup, getSpecialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging()));
            }
        }
        return hashSet;
    }

    private final void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    private final void ensureExecReady(boolean z) {
        if (!this.mExecutingActions) {
            if (this.mHost == null) {
                if (this.mDestroyed) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            if (Looper.myLooper() == this.mHost.handler.getLooper()) {
                if (!z) {
                    checkStateLoss();
                }
                if (this.mTmpRecords == null) {
                    this.mTmpRecords = new ArrayList();
                    this.mTmpIsPop = new ArrayList();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:159:0x02fe. Please report as an issue. */
    private final void executeOpsTogether(ArrayList arrayList, ArrayList arrayList2, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        byte[] bArr;
        ArrayList arrayList3 = arrayList;
        ArrayList arrayList4 = arrayList2;
        boolean z = ((BackStackRecord) arrayList3.get(i)).mReorderingAllowed;
        ArrayList arrayList5 = this.mTmpAddedFragments;
        if (arrayList5 == null) {
            this.mTmpAddedFragments = new ArrayList();
        } else {
            arrayList5.clear();
        }
        this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
        Fragment fragment = this.mPrimaryNav;
        int i7 = i;
        boolean z2 = false;
        while (true) {
            int i8 = 1;
            if (i7 < i2) {
                BackStackRecord backStackRecord = (BackStackRecord) arrayList3.get(i7);
                int i9 = 3;
                if (!((Boolean) arrayList4.get(i7)).booleanValue()) {
                    ArrayList arrayList6 = this.mTmpAddedFragments;
                    int i10 = 0;
                    while (i10 < backStackRecord.mOps.size()) {
                        FragmentTransaction.Op op = (FragmentTransaction.Op) backStackRecord.mOps.get(i10);
                        int i11 = op.mCmd;
                        if (i11 != i8) {
                            if (i11 != 2) {
                                if (i11 != i9 && i11 != 6) {
                                    if (i11 != 7) {
                                        if (i11 == 8) {
                                            backStackRecord.mOps.add(i10, new FragmentTransaction.Op(9, fragment, null));
                                            op.mFromExpandedOp = true;
                                            fragment = op.mFragment;
                                            i10++;
                                        }
                                    } else {
                                        i5 = 1;
                                    }
                                } else {
                                    arrayList6.remove(op.mFragment);
                                    Fragment fragment2 = op.mFragment;
                                    if (fragment2 == fragment) {
                                        backStackRecord.mOps.add(i10, new FragmentTransaction.Op(9, fragment2));
                                        i10++;
                                        i5 = 1;
                                        fragment = null;
                                        i10 += i5;
                                        i8 = i5;
                                        i9 = 3;
                                    }
                                }
                                i5 = 1;
                                i10 += i5;
                                i8 = i5;
                                i9 = 3;
                            } else {
                                Fragment fragment3 = op.mFragment;
                                int i12 = fragment3.mContainerId;
                                int size = arrayList6.size() - 1;
                                boolean z3 = false;
                                while (size >= 0) {
                                    Fragment fragment4 = (Fragment) arrayList6.get(size);
                                    if (fragment4.mContainerId == i12) {
                                        if (fragment4 == fragment3) {
                                            i6 = i12;
                                            z3 = true;
                                        } else {
                                            if (fragment4 == fragment) {
                                                i6 = i12;
                                                bArr = null;
                                                backStackRecord.mOps.add(i10, new FragmentTransaction.Op(9, fragment4, null));
                                                i10++;
                                                fragment = null;
                                            } else {
                                                i6 = i12;
                                                bArr = null;
                                            }
                                            FragmentTransaction.Op op2 = new FragmentTransaction.Op(3, fragment4, bArr);
                                            op2.mEnterAnim = op.mEnterAnim;
                                            op2.mPopEnterAnim = op.mPopEnterAnim;
                                            op2.mExitAnim = op.mExitAnim;
                                            op2.mPopExitAnim = op.mPopExitAnim;
                                            backStackRecord.mOps.add(i10, op2);
                                            arrayList6.remove(fragment4);
                                            i10++;
                                        }
                                    } else {
                                        i6 = i12;
                                    }
                                    size--;
                                    i12 = i6;
                                }
                                if (z3) {
                                    backStackRecord.mOps.remove(i10);
                                    i10--;
                                    i5 = 1;
                                    i10 += i5;
                                    i8 = i5;
                                    i9 = 3;
                                } else {
                                    i5 = 1;
                                    op.mCmd = 1;
                                    op.mFromExpandedOp = true;
                                    arrayList6.add(fragment3);
                                    i10 += i5;
                                    i8 = i5;
                                    i9 = 3;
                                }
                            }
                        } else {
                            i5 = i8;
                        }
                        arrayList6.add(op.mFragment);
                        i10 += i5;
                        i8 = i5;
                        i9 = 3;
                    }
                } else {
                    ArrayList arrayList7 = this.mTmpAddedFragments;
                    for (int size2 = backStackRecord.mOps.size() - 1; size2 >= 0; size2--) {
                        FragmentTransaction.Op op3 = (FragmentTransaction.Op) backStackRecord.mOps.get(size2);
                        int i13 = op3.mCmd;
                        if (i13 != 1) {
                            if (i13 != 3) {
                                switch (i13) {
                                    case 8:
                                        fragment = null;
                                        break;
                                    case 9:
                                        fragment = op3.mFragment;
                                        break;
                                    case 10:
                                        op3.mCurrentMaxState = op3.mOldMaxState;
                                        break;
                                }
                            }
                            arrayList7.add(op3.mFragment);
                        }
                        arrayList7.remove(op3.mFragment);
                    }
                }
                if (!z2 && !backStackRecord.mAddToBackStack) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                i7++;
                arrayList3 = arrayList;
                arrayList4 = arrayList2;
            } else {
                this.mTmpAddedFragments.clear();
                if (!z && this.mCurState > 0) {
                    for (int i14 = i; i14 < i2; i14++) {
                        ArrayList arrayList8 = ((BackStackRecord) arrayList.get(i14)).mOps;
                        int size3 = arrayList8.size();
                        for (int i15 = 0; i15 < size3; i15++) {
                            Fragment fragment5 = ((FragmentTransaction.Op) arrayList8.get(i15)).mFragment;
                            if (fragment5 != null && fragment5.mFragmentManager != null) {
                                this.mFragmentStore.makeActive(createOrGetFragmentStateManager(fragment5));
                            }
                        }
                    }
                }
                for (int i16 = i; i16 < i2; i16++) {
                    BackStackRecord backStackRecord2 = (BackStackRecord) arrayList.get(i16);
                    if (((Boolean) arrayList2.get(i16)).booleanValue()) {
                        backStackRecord2.bumpBackStackNesting(-1);
                        for (int size4 = backStackRecord2.mOps.size() - 1; size4 >= 0; size4--) {
                            FragmentTransaction.Op op4 = (FragmentTransaction.Op) backStackRecord2.mOps.get(size4);
                            Fragment fragment6 = op4.mFragment;
                            if (fragment6 != null) {
                                fragment6.mBeingSaved = backStackRecord2.mBeingSaved;
                                fragment6.setPopDirection(true);
                                int i17 = backStackRecord2.mTransition;
                                int i18 = 8194;
                                int i19 = 4097;
                                if (i17 != 4097) {
                                    if (i17 != 8194) {
                                        i18 = 4100;
                                        i19 = 8197;
                                        if (i17 != 8197) {
                                            if (i17 != 4099) {
                                                if (i17 != 4100) {
                                                    i18 = 0;
                                                }
                                            } else {
                                                i18 = 4099;
                                            }
                                        }
                                    }
                                    i18 = i19;
                                }
                                fragment6.setNextTransition(i18);
                                fragment6.setSharedElementNames(backStackRecord2.mSharedElementTargetNames, backStackRecord2.mSharedElementSourceNames);
                            }
                            switch (op4.mCmd) {
                                case 1:
                                    fragment6.setAnimations(op4.mEnterAnim, op4.mExitAnim, op4.mPopEnterAnim, op4.mPopExitAnim);
                                    backStackRecord2.mManager.setExitAnimationOrder(fragment6, true);
                                    backStackRecord2.mManager.removeFragment(fragment6);
                                    break;
                                case 2:
                                default:
                                    throw new IllegalArgumentException("Unknown cmd: " + op4.mCmd);
                                case 3:
                                    fragment6.setAnimations(op4.mEnterAnim, op4.mExitAnim, op4.mPopEnterAnim, op4.mPopExitAnim);
                                    backStackRecord2.mManager.addFragment(fragment6);
                                    break;
                                case 4:
                                    fragment6.setAnimations(op4.mEnterAnim, op4.mExitAnim, op4.mPopEnterAnim, op4.mPopExitAnim);
                                    FragmentManager fragmentManager = backStackRecord2.mManager;
                                    showFragment$ar$ds(fragment6);
                                    break;
                                case 5:
                                    fragment6.setAnimations(op4.mEnterAnim, op4.mExitAnim, op4.mPopEnterAnim, op4.mPopExitAnim);
                                    backStackRecord2.mManager.setExitAnimationOrder(fragment6, true);
                                    backStackRecord2.mManager.hideFragment(fragment6);
                                    break;
                                case 6:
                                    fragment6.setAnimations(op4.mEnterAnim, op4.mExitAnim, op4.mPopEnterAnim, op4.mPopExitAnim);
                                    backStackRecord2.mManager.attachFragment(fragment6);
                                    break;
                                case 7:
                                    fragment6.setAnimations(op4.mEnterAnim, op4.mExitAnim, op4.mPopEnterAnim, op4.mPopExitAnim);
                                    backStackRecord2.mManager.setExitAnimationOrder(fragment6, true);
                                    backStackRecord2.mManager.detachFragment(fragment6);
                                    break;
                                case 8:
                                    backStackRecord2.mManager.setPrimaryNavigationFragment(null);
                                    break;
                                case 9:
                                    backStackRecord2.mManager.setPrimaryNavigationFragment(fragment6);
                                    break;
                                case 10:
                                    backStackRecord2.mManager.setMaxLifecycle(fragment6, op4.mOldMaxState);
                                    break;
                            }
                        }
                    } else {
                        backStackRecord2.bumpBackStackNesting(1);
                        int size5 = backStackRecord2.mOps.size();
                        for (int i20 = 0; i20 < size5; i20++) {
                            FragmentTransaction.Op op5 = (FragmentTransaction.Op) backStackRecord2.mOps.get(i20);
                            Fragment fragment7 = op5.mFragment;
                            if (fragment7 != null) {
                                fragment7.mBeingSaved = backStackRecord2.mBeingSaved;
                                fragment7.setPopDirection(false);
                                fragment7.setNextTransition(backStackRecord2.mTransition);
                                fragment7.setSharedElementNames(backStackRecord2.mSharedElementSourceNames, backStackRecord2.mSharedElementTargetNames);
                            }
                            switch (op5.mCmd) {
                                case 1:
                                    fragment7.setAnimations(op5.mEnterAnim, op5.mExitAnim, op5.mPopEnterAnim, op5.mPopExitAnim);
                                    backStackRecord2.mManager.setExitAnimationOrder(fragment7, false);
                                    backStackRecord2.mManager.addFragment(fragment7);
                                case 2:
                                default:
                                    throw new IllegalArgumentException("Unknown cmd: " + op5.mCmd);
                                case 3:
                                    fragment7.setAnimations(op5.mEnterAnim, op5.mExitAnim, op5.mPopEnterAnim, op5.mPopExitAnim);
                                    backStackRecord2.mManager.removeFragment(fragment7);
                                case 4:
                                    fragment7.setAnimations(op5.mEnterAnim, op5.mExitAnim, op5.mPopEnterAnim, op5.mPopExitAnim);
                                    backStackRecord2.mManager.hideFragment(fragment7);
                                case 5:
                                    fragment7.setAnimations(op5.mEnterAnim, op5.mExitAnim, op5.mPopEnterAnim, op5.mPopExitAnim);
                                    backStackRecord2.mManager.setExitAnimationOrder(fragment7, false);
                                    FragmentManager fragmentManager2 = backStackRecord2.mManager;
                                    showFragment$ar$ds(fragment7);
                                case 6:
                                    fragment7.setAnimations(op5.mEnterAnim, op5.mExitAnim, op5.mPopEnterAnim, op5.mPopExitAnim);
                                    backStackRecord2.mManager.detachFragment(fragment7);
                                case 7:
                                    fragment7.setAnimations(op5.mEnterAnim, op5.mExitAnim, op5.mPopEnterAnim, op5.mPopExitAnim);
                                    backStackRecord2.mManager.setExitAnimationOrder(fragment7, false);
                                    backStackRecord2.mManager.attachFragment(fragment7);
                                case 8:
                                    backStackRecord2.mManager.setPrimaryNavigationFragment(fragment7);
                                case 9:
                                    backStackRecord2.mManager.setPrimaryNavigationFragment(null);
                                case 10:
                                    backStackRecord2.mManager.setMaxLifecycle(fragment7, op5.mCurrentMaxState);
                            }
                        }
                    }
                }
                boolean booleanValue = ((Boolean) arrayList2.get(i2 - 1)).booleanValue();
                if (z2 && !this.mBackStackChangeListeners.isEmpty()) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    int size6 = arrayList.size();
                    for (int i21 = 0; i21 < size6; i21++) {
                        linkedHashSet.addAll(fragmentsFromRecord$ar$ds((BackStackRecord) arrayList.get(i21)));
                    }
                    if (this.mTransitioningOp == null) {
                        ArrayList arrayList9 = this.mBackStackChangeListeners;
                        int size7 = arrayList9.size();
                        int i22 = 0;
                        while (i22 < size7) {
                            FragmentNavigator$onAttach$2 fragmentNavigator$onAttach$2 = (FragmentNavigator$onAttach$2) arrayList9.get(i22);
                            Iterator it = linkedHashSet.iterator();
                            while (true) {
                                i4 = i22 + 1;
                                if (it.hasNext()) {
                                    fragmentNavigator$onAttach$2.onBackStackChangeStarted((Fragment) it.next(), booleanValue);
                                }
                            }
                            i22 = i4;
                        }
                        ArrayList arrayList10 = this.mBackStackChangeListeners;
                        int size8 = arrayList10.size();
                        int i23 = 0;
                        while (i23 < size8) {
                            FragmentNavigator$onAttach$2 fragmentNavigator$onAttach$22 = (FragmentNavigator$onAttach$2) arrayList10.get(i23);
                            Iterator it2 = linkedHashSet.iterator();
                            while (true) {
                                i3 = i23 + 1;
                                if (it2.hasNext()) {
                                    fragmentNavigator$onAttach$22.onBackStackChangeCommitted((Fragment) it2.next(), booleanValue);
                                }
                            }
                            i23 = i3;
                        }
                    }
                }
                for (int i24 = i; i24 < i2; i24++) {
                    BackStackRecord backStackRecord3 = (BackStackRecord) arrayList.get(i24);
                    if (booleanValue) {
                        for (int size9 = backStackRecord3.mOps.size() - 1; size9 >= 0; size9--) {
                            Fragment fragment8 = ((FragmentTransaction.Op) backStackRecord3.mOps.get(size9)).mFragment;
                            if (fragment8 != null) {
                                createOrGetFragmentStateManager(fragment8).moveToExpectedState();
                            }
                        }
                    } else {
                        ArrayList arrayList11 = backStackRecord3.mOps;
                        int size10 = arrayList11.size();
                        for (int i25 = 0; i25 < size10; i25++) {
                            Fragment fragment9 = ((FragmentTransaction.Op) arrayList11.get(i25)).mFragment;
                            if (fragment9 != null) {
                                createOrGetFragmentStateManager(fragment9).moveToExpectedState();
                            }
                        }
                    }
                }
                moveToState(this.mCurState, true);
                int i26 = i;
                for (SpecialEffectsController specialEffectsController : collectChangedControllers(arrayList, i26, i2)) {
                    specialEffectsController.operationDirectionIsPop = booleanValue;
                    specialEffectsController.markPostponedState();
                    specialEffectsController.executePendingOperations();
                }
                while (i26 < i2) {
                    BackStackRecord backStackRecord4 = (BackStackRecord) arrayList.get(i26);
                    if (((Boolean) arrayList2.get(i26)).booleanValue() && backStackRecord4.mIndex >= 0) {
                        backStackRecord4.mIndex = -1;
                    }
                    if (backStackRecord4.mCommitRunnables != null) {
                        for (int i27 = 0; i27 < backStackRecord4.mCommitRunnables.size(); i27++) {
                            ((Runnable) backStackRecord4.mCommitRunnables.get(i27)).run();
                        }
                        backStackRecord4.mCommitRunnables = null;
                    }
                    i26++;
                }
                if (z2) {
                    for (int i28 = 0; i28 < this.mBackStackChangeListeners.size(); i28++) {
                    }
                    return;
                }
                return;
            }
        }
    }

    private final void forcePostponedTransactions() {
        for (SpecialEffectsController specialEffectsController : collectAllSpecialEffectsController()) {
            if (specialEffectsController.isContainerPostponed) {
                specialEffectsController.isContainerPostponed = false;
                specialEffectsController.executePendingOperations();
            }
        }
    }

    static final Set fragmentsFromRecord$ar$ds(BackStackRecord backStackRecord) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < backStackRecord.mOps.size(); i++) {
            Fragment fragment = ((FragmentTransaction.Op) backStackRecord.mOps.get(i)).mFragment;
            if (fragment != null && backStackRecord.mAddToBackStack) {
                hashSet.add(fragment);
            }
        }
        return hashSet;
    }

    private final ViewGroup getFragmentContainer(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.mContainer.onHasView()) {
            View onFindViewById = this.mContainer.onFindViewById(fragment.mContainerId);
            if (onFindViewById instanceof ViewGroup) {
                return (ViewGroup) onFindViewById;
            }
        }
        return null;
    }

    public static Fragment getViewFragment(View view) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    public static boolean isLoggingEnabled(int i) {
        if (Log.isLoggable("FragmentManager", i)) {
            return true;
        }
        return false;
    }

    public static final boolean isMenuAvailable$ar$ds(Fragment fragment) {
        if (!fragment.mHasMenu || !fragment.mMenuVisible) {
            boolean z = false;
            for (Fragment fragment2 : fragment.mChildFragmentManager.mFragmentStore.getActiveFragments()) {
                if (fragment2 != null) {
                    z = isMenuAvailable$ar$ds(fragment2);
                }
                if (z) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static final boolean isParentMenuVisible$ar$ds(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }

    private final void removeRedundantOperationsAndExecute(ArrayList arrayList, ArrayList arrayList2) {
        if (!arrayList.isEmpty()) {
            if (arrayList.size() == arrayList2.size()) {
                int size = arrayList.size();
                int i = 0;
                int i2 = 0;
                while (i < size) {
                    if (!((BackStackRecord) arrayList.get(i)).mReorderingAllowed) {
                        if (i2 != i) {
                            executeOpsTogether(arrayList, arrayList2, i2, i);
                        }
                        i2 = i + 1;
                        if (((Boolean) arrayList2.get(i)).booleanValue()) {
                            while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((BackStackRecord) arrayList.get(i2)).mReorderingAllowed) {
                                i2++;
                            }
                        }
                        executeOpsTogether(arrayList, arrayList2, i, i2);
                        i = i2 - 1;
                    }
                    i++;
                }
                if (i2 != size) {
                    executeOpsTogether(arrayList, arrayList2, i2, size);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error with the back stack records");
        }
    }

    private final void setVisibleRemovingFragment(Fragment fragment) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer != null && fragment.getEnterAnim() + fragment.getExitAnim() + fragment.getPopEnterAnim() + fragment.getPopExitAnim() > 0) {
            if (fragmentContainer.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                fragmentContainer.setTag(R.id.visible_removing_fragment_view_tag, fragment);
            }
            ((Fragment) fragmentContainer.getTag(R.id.visible_removing_fragment_view_tag)).setPopDirection(fragment.getPopDirection());
        }
    }

    static final void showFragment$ar$ds(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Objects.toString(fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    private final void startPendingDeferredFragments() {
        Iterator it = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
        while (it.hasNext()) {
            performPendingDeferredStart((FragmentStateManager) it.next());
        }
    }

    public final FragmentStateManager addFragment(Fragment fragment) {
        String str = fragment.mPreviousWho;
        if (str != null) {
            FragmentStrictMode.onFragmentReuse(fragment, str);
        }
        if (isLoggingEnabled(2)) {
            Objects.toString(fragment);
        }
        FragmentStateManager createOrGetFragmentStateManager = createOrGetFragmentStateManager(fragment);
        fragment.mFragmentManager = this;
        this.mFragmentStore.makeActive(createOrGetFragmentStateManager);
        if (!fragment.mDetached) {
            this.mFragmentStore.addFragment(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (isMenuAvailable$ar$ds(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
        return createOrGetFragmentStateManager;
    }

    public final void addFragmentOnAttachListener(FragmentOnAttachListener fragmentOnAttachListener) {
        this.mOnAttachListeners.add(fragmentOnAttachListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void attachController(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment) {
        String str;
        if (this.mHost == null) {
            this.mHost = fragmentHostCallback;
            this.mContainer = fragmentContainer;
            this.mParent = fragment;
            if (fragment != null) {
                addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: android.support.v4.app.FragmentManager.7
                    public AnonymousClass7() {
                    }

                    @Override // android.support.v4.app.FragmentOnAttachListener
                    public final void onAttachFragment$ar$ds(Fragment fragment2) {
                        Fragment.this.onAttachFragment(fragment2);
                    }
                });
            } else if (fragmentHostCallback instanceof FragmentOnAttachListener) {
                addFragmentOnAttachListener((FragmentOnAttachListener) fragmentHostCallback);
            }
            if (this.mParent != null) {
                updateOnBackPressedCallbackEnabled();
            }
            if (fragmentHostCallback instanceof OnBackPressedDispatcherOwner) {
                OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) fragmentHostCallback;
                OnBackPressedDispatcher onBackPressedDispatcher = onBackPressedDispatcherOwner.getOnBackPressedDispatcher();
                this.mOnBackPressedDispatcher = onBackPressedDispatcher;
                LifecycleOwner lifecycleOwner = onBackPressedDispatcherOwner;
                if (fragment != null) {
                    lifecycleOwner = fragment;
                }
                onBackPressedDispatcher.addCallback(lifecycleOwner, this.mOnBackPressedCallback);
            }
            int i = 0;
            if (fragment != null) {
                FragmentManagerViewModel fragmentManagerViewModel = fragment.mFragmentManager.mNonConfig;
                FragmentManagerViewModel fragmentManagerViewModel2 = (FragmentManagerViewModel) fragmentManagerViewModel.mChildNonConfigs.get(fragment.mWho);
                if (fragmentManagerViewModel2 == null) {
                    FragmentManagerViewModel fragmentManagerViewModel3 = new FragmentManagerViewModel(fragmentManagerViewModel.mStateAutomaticallySaved);
                    fragmentManagerViewModel.mChildNonConfigs.put(fragment.mWho, fragmentManagerViewModel3);
                    fragmentManagerViewModel2 = fragmentManagerViewModel3;
                }
                this.mNonConfig = fragmentManagerViewModel2;
            } else {
                if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                    this.mNonConfig = (FragmentManagerViewModel) new ViewModelProvider(((ViewModelStoreOwner) fragmentHostCallback).getViewModelStore(), FragmentManagerViewModel.FACTORY).get(FragmentManagerViewModel.class);
                } else {
                    this.mNonConfig = new FragmentManagerViewModel(false);
                }
                fragment = null;
            }
            FragmentManagerViewModel fragmentManagerViewModel4 = this.mNonConfig;
            fragmentManagerViewModel4.mIsStateSaved = isStateSaved();
            this.mFragmentStore.mNonConfig = fragmentManagerViewModel4;
            Object obj = this.mHost;
            if ((obj instanceof SavedStateRegistryOwner) && fragment == null) {
                SavedStateRegistry savedStateRegistry = ((SavedStateRegistryOwner) obj).getSavedStateRegistry();
                savedStateRegistry.registerSavedStateProvider("android:support:fragments", new FragmentManager$$ExternalSyntheticLambda0(this, i));
                Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey("android:support:fragments");
                if (consumeRestoredStateForKey != null) {
                    restoreSaveStateInternal(consumeRestoredStateForKey);
                }
            }
            Object obj2 = this.mHost;
            if (obj2 instanceof ActivityResultRegistryOwner) {
                ActivityResultRegistry activityResultRegistry = ((ActivityResultRegistryOwner) obj2).getActivityResultRegistry();
                if (fragment != null) {
                    str = String.valueOf(fragment.mWho).concat(":");
                } else {
                    str = "";
                }
                ActivityResultContract activityResultContract = new ActivityResultContract() { // from class: androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult
                    @Override // androidx.activity.result.contract.ActivityResultContract
                    public final /* synthetic */ Intent createIntent$ar$ds(Object obj3) {
                        Intent intent = (Intent) obj3;
                        intent.getClass();
                        return intent;
                    }

                    @Override // androidx.activity.result.contract.ActivityResultContract
                    public final /* synthetic */ Object parseResult(int i2, Intent intent) {
                        return new ActivityResult(i2, intent);
                    }
                };
                AnonymousClass8 anonymousClass8 = new ActivityResultCallback(this) { // from class: android.support.v4.app.FragmentManager.8
                    private final /* synthetic */ int switching_field;
                    final /* synthetic */ FragmentManager this$0;

                    public AnonymousClass8(FragmentManager this, int i2) {
                        r2 = i2;
                        this.this$0 = this;
                    }

                    @Override // androidx.activity.result.ActivityResultCallback
                    public final /* synthetic */ void onActivityResult(Object obj3) {
                        int i2;
                        int i22 = r2;
                        if (i22 != 0) {
                            if (i22 != 1) {
                                ActivityResult activityResult = (ActivityResult) obj3;
                                LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                                if (launchedFragmentInfo == null) {
                                    toString();
                                    Log.w("FragmentManager", "No IntentSenders were started for ".concat(toString()));
                                    return;
                                }
                                FragmentStore fragmentStore = this.this$0.mFragmentStore;
                                String str2 = launchedFragmentInfo.mWho;
                                Fragment findFragmentByWho = fragmentStore.findFragmentByWho(str2);
                                if (findFragmentByWho == null) {
                                    Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment ".concat(String.valueOf(str2)));
                                    return;
                                } else {
                                    findFragmentByWho.onActivityResult(launchedFragmentInfo.mRequestCode, activityResult.resultCode, activityResult.data);
                                    return;
                                }
                            }
                            Map map = (Map) obj3;
                            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                            ArrayList arrayList = new ArrayList(map.values());
                            int[] iArr = new int[arrayList.size()];
                            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                                if (true != ((Boolean) arrayList.get(i3)).booleanValue()) {
                                    i2 = -1;
                                } else {
                                    i2 = 0;
                                }
                                iArr[i3] = i2;
                            }
                            LaunchedFragmentInfo launchedFragmentInfo2 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                            if (launchedFragmentInfo2 == null) {
                                toString();
                                Log.w("FragmentManager", "No permissions were requested for ".concat(toString()));
                                return;
                            }
                            FragmentStore fragmentStore2 = this.this$0.mFragmentStore;
                            String str22 = launchedFragmentInfo2.mWho;
                            Fragment findFragmentByWho2 = fragmentStore2.findFragmentByWho(str22);
                            if (findFragmentByWho2 == null) {
                                Log.w("FragmentManager", "Permission request result delivered for unknown Fragment ".concat(String.valueOf(str22)));
                                return;
                            } else {
                                findFragmentByWho2.onRequestPermissionsResult(launchedFragmentInfo2.mRequestCode, strArr, iArr);
                                return;
                            }
                        }
                        ActivityResult activityResult2 = (ActivityResult) obj3;
                        LaunchedFragmentInfo launchedFragmentInfo3 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollLast();
                        if (launchedFragmentInfo3 == null) {
                            toString();
                            Log.w("FragmentManager", "No Activities were started for result for ".concat(toString()));
                            return;
                        }
                        FragmentStore fragmentStore3 = this.this$0.mFragmentStore;
                        String str3 = launchedFragmentInfo3.mWho;
                        Fragment findFragmentByWho3 = fragmentStore3.findFragmentByWho(str3);
                        if (findFragmentByWho3 == null) {
                            Log.w("FragmentManager", "Activity result delivered for unknown Fragment ".concat(String.valueOf(str3)));
                        } else {
                            findFragmentByWho3.onActivityResult(launchedFragmentInfo3.mRequestCode, activityResult2.resultCode, activityResult2.data);
                        }
                    }
                };
                String concat = "FragmentManager:".concat(str);
                this.mStartActivityForResult = activityResultRegistry.register(concat.concat("StartActivityForResult"), activityResultContract, anonymousClass8);
                this.mStartIntentSenderForResult = activityResultRegistry.register(concat.concat("StartIntentSenderForResult"), new FragmentIntentSenderContract(), new ActivityResultCallback(this) { // from class: android.support.v4.app.FragmentManager.8
                    private final /* synthetic */ int switching_field;
                    final /* synthetic */ FragmentManager this$0;

                    public AnonymousClass8(FragmentManager this, int i2) {
                        r2 = i2;
                        this.this$0 = this;
                    }

                    @Override // androidx.activity.result.ActivityResultCallback
                    public final /* synthetic */ void onActivityResult(Object obj3) {
                        int i2;
                        int i22 = r2;
                        if (i22 != 0) {
                            if (i22 != 1) {
                                ActivityResult activityResult = (ActivityResult) obj3;
                                LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                                if (launchedFragmentInfo == null) {
                                    toString();
                                    Log.w("FragmentManager", "No IntentSenders were started for ".concat(toString()));
                                    return;
                                }
                                FragmentStore fragmentStore = this.this$0.mFragmentStore;
                                String str2 = launchedFragmentInfo.mWho;
                                Fragment findFragmentByWho = fragmentStore.findFragmentByWho(str2);
                                if (findFragmentByWho == null) {
                                    Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment ".concat(String.valueOf(str2)));
                                    return;
                                } else {
                                    findFragmentByWho.onActivityResult(launchedFragmentInfo.mRequestCode, activityResult.resultCode, activityResult.data);
                                    return;
                                }
                            }
                            Map map = (Map) obj3;
                            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                            ArrayList arrayList = new ArrayList(map.values());
                            int[] iArr = new int[arrayList.size()];
                            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                                if (true != ((Boolean) arrayList.get(i3)).booleanValue()) {
                                    i2 = -1;
                                } else {
                                    i2 = 0;
                                }
                                iArr[i3] = i2;
                            }
                            LaunchedFragmentInfo launchedFragmentInfo2 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                            if (launchedFragmentInfo2 == null) {
                                toString();
                                Log.w("FragmentManager", "No permissions were requested for ".concat(toString()));
                                return;
                            }
                            FragmentStore fragmentStore2 = this.this$0.mFragmentStore;
                            String str22 = launchedFragmentInfo2.mWho;
                            Fragment findFragmentByWho2 = fragmentStore2.findFragmentByWho(str22);
                            if (findFragmentByWho2 == null) {
                                Log.w("FragmentManager", "Permission request result delivered for unknown Fragment ".concat(String.valueOf(str22)));
                                return;
                            } else {
                                findFragmentByWho2.onRequestPermissionsResult(launchedFragmentInfo2.mRequestCode, strArr, iArr);
                                return;
                            }
                        }
                        ActivityResult activityResult2 = (ActivityResult) obj3;
                        LaunchedFragmentInfo launchedFragmentInfo3 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollLast();
                        if (launchedFragmentInfo3 == null) {
                            toString();
                            Log.w("FragmentManager", "No Activities were started for result for ".concat(toString()));
                            return;
                        }
                        FragmentStore fragmentStore3 = this.this$0.mFragmentStore;
                        String str3 = launchedFragmentInfo3.mWho;
                        Fragment findFragmentByWho3 = fragmentStore3.findFragmentByWho(str3);
                        if (findFragmentByWho3 == null) {
                            Log.w("FragmentManager", "Activity result delivered for unknown Fragment ".concat(String.valueOf(str3)));
                        } else {
                            findFragmentByWho3.onActivityResult(launchedFragmentInfo3.mRequestCode, activityResult2.resultCode, activityResult2.data);
                        }
                    }
                });
                this.mRequestPermissions = activityResultRegistry.register(concat.concat("RequestPermissions"), new ActivityResultContracts$RequestMultiplePermissions(), new ActivityResultCallback(this) { // from class: android.support.v4.app.FragmentManager.8
                    private final /* synthetic */ int switching_field;
                    final /* synthetic */ FragmentManager this$0;

                    public AnonymousClass8(FragmentManager this, int i2) {
                        r2 = i2;
                        this.this$0 = this;
                    }

                    @Override // androidx.activity.result.ActivityResultCallback
                    public final /* synthetic */ void onActivityResult(Object obj3) {
                        int i2;
                        int i22 = r2;
                        if (i22 != 0) {
                            if (i22 != 1) {
                                ActivityResult activityResult = (ActivityResult) obj3;
                                LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                                if (launchedFragmentInfo == null) {
                                    toString();
                                    Log.w("FragmentManager", "No IntentSenders were started for ".concat(toString()));
                                    return;
                                }
                                FragmentStore fragmentStore = this.this$0.mFragmentStore;
                                String str2 = launchedFragmentInfo.mWho;
                                Fragment findFragmentByWho = fragmentStore.findFragmentByWho(str2);
                                if (findFragmentByWho == null) {
                                    Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment ".concat(String.valueOf(str2)));
                                    return;
                                } else {
                                    findFragmentByWho.onActivityResult(launchedFragmentInfo.mRequestCode, activityResult.resultCode, activityResult.data);
                                    return;
                                }
                            }
                            Map map = (Map) obj3;
                            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                            ArrayList arrayList = new ArrayList(map.values());
                            int[] iArr = new int[arrayList.size()];
                            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                                if (true != ((Boolean) arrayList.get(i3)).booleanValue()) {
                                    i2 = -1;
                                } else {
                                    i2 = 0;
                                }
                                iArr[i3] = i2;
                            }
                            LaunchedFragmentInfo launchedFragmentInfo2 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollFirst();
                            if (launchedFragmentInfo2 == null) {
                                toString();
                                Log.w("FragmentManager", "No permissions were requested for ".concat(toString()));
                                return;
                            }
                            FragmentStore fragmentStore2 = this.this$0.mFragmentStore;
                            String str22 = launchedFragmentInfo2.mWho;
                            Fragment findFragmentByWho2 = fragmentStore2.findFragmentByWho(str22);
                            if (findFragmentByWho2 == null) {
                                Log.w("FragmentManager", "Permission request result delivered for unknown Fragment ".concat(String.valueOf(str22)));
                                return;
                            } else {
                                findFragmentByWho2.onRequestPermissionsResult(launchedFragmentInfo2.mRequestCode, strArr, iArr);
                                return;
                            }
                        }
                        ActivityResult activityResult2 = (ActivityResult) obj3;
                        LaunchedFragmentInfo launchedFragmentInfo3 = (LaunchedFragmentInfo) this.this$0.mLaunchedFragments.pollLast();
                        if (launchedFragmentInfo3 == null) {
                            toString();
                            Log.w("FragmentManager", "No Activities were started for result for ".concat(toString()));
                            return;
                        }
                        FragmentStore fragmentStore3 = this.this$0.mFragmentStore;
                        String str3 = launchedFragmentInfo3.mWho;
                        Fragment findFragmentByWho3 = fragmentStore3.findFragmentByWho(str3);
                        if (findFragmentByWho3 == null) {
                            Log.w("FragmentManager", "Activity result delivered for unknown Fragment ".concat(String.valueOf(str3)));
                        } else {
                            findFragmentByWho3.onActivityResult(launchedFragmentInfo3.mRequestCode, activityResult2.resultCode, activityResult2.data);
                        }
                    }
                });
            }
            Object obj3 = this.mHost;
            if (obj3 instanceof OnConfigurationChangedProvider) {
                ((OnConfigurationChangedProvider) obj3).addOnConfigurationChangedListener(this.mOnConfigurationChangedListener);
            }
            Object obj4 = this.mHost;
            if (obj4 instanceof OnTrimMemoryProvider) {
                ((OnTrimMemoryProvider) obj4).addOnTrimMemoryListener(this.mOnTrimMemoryListener);
            }
            Object obj5 = this.mHost;
            if (obj5 instanceof OnMultiWindowModeChangedProvider) {
                ((OnMultiWindowModeChangedProvider) obj5).addOnMultiWindowModeChangedListener(this.mOnMultiWindowModeChangedListener);
            }
            Object obj6 = this.mHost;
            if (obj6 instanceof OnPictureInPictureModeChangedProvider) {
                ((OnPictureInPictureModeChangedProvider) obj6).addOnPictureInPictureModeChangedListener(this.mOnPictureInPictureModeChangedListener);
            }
            Object obj7 = this.mHost;
            if ((obj7 instanceof MenuHost) && fragment == null) {
                ((MenuHost) obj7).addMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.mMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                return;
            }
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    final void attachFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Objects.toString(fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                this.mFragmentStore.addFragment(fragment);
                if (isLoggingEnabled(2)) {
                    Objects.toString(fragment);
                }
                if (isMenuAvailable$ar$ds(fragment)) {
                    this.mNeedMenuInvalidate = true;
                }
            }
        }
    }

    final Set collectChangedControllers(ArrayList arrayList, int i, int i2) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i < i2) {
            ArrayList arrayList2 = ((BackStackRecord) arrayList.get(i)).mOps;
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                Fragment fragment = ((FragmentTransaction.Op) arrayList2.get(i3)).mFragment;
                if (fragment != null && (viewGroup = fragment.mContainer) != null) {
                    hashSet.add(SpecialEffectsController.getOrCreateController(viewGroup, this));
                }
            }
            i++;
        }
        return hashSet;
    }

    public final FragmentStateManager createOrGetFragmentStateManager(Fragment fragment) {
        FragmentStateManager fragmentStateManager = this.mFragmentStore.getFragmentStateManager(fragment.mWho);
        if (fragmentStateManager != null) {
            return fragmentStateManager;
        }
        FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging, this.mFragmentStore, fragment);
        fragmentStateManager2.restoreState(this.mHost.context.getClassLoader());
        fragmentStateManager2.mFragmentManagerState = this.mCurState;
        return fragmentStateManager2;
    }

    final void detachFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Objects.toString(fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (isLoggingEnabled(2)) {
                    Objects.toString(fragment);
                }
                this.mFragmentStore.removeFragment(fragment);
                if (isMenuAvailable$ar$ds(fragment)) {
                    this.mNeedMenuInvalidate = true;
                }
                setVisibleRemovingFragment(fragment);
            }
        }
    }

    public final void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.mIsStateSaved = false;
        dispatchStateChange(4);
    }

    public final void dispatchConfigurationChanged(Configuration configuration, boolean z) {
        if (z && (this.mHost instanceof OnConfigurationChangedProvider)) {
            throwException(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
                if (z) {
                    fragment.mChildFragmentManager.dispatchConfigurationChanged(configuration, true);
                }
            }
        }
    }

    public final boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.mCurState <= 0) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.mIsStateSaved = false;
        dispatchStateChange(1);
    }

    public final boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mCurState <= 0) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && isParentMenuVisible$ar$ds(fragment) && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i = 0; i < this.mCreatedMenus.size(); i++) {
                Fragment fragment2 = (Fragment) this.mCreatedMenus.get(i);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    public final void dispatchDestroy() {
        boolean isChangingConfigurations;
        this.mDestroyed = true;
        execPendingActions(true);
        endAnimatingAwayFragments();
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback instanceof ViewModelStoreOwner) {
            isChangingConfigurations = this.mFragmentStore.mNonConfig.mHasBeenCleared;
        } else {
            isChangingConfigurations = true ^ ((Activity) fragmentHostCallback.context).isChangingConfigurations();
        }
        if (isChangingConfigurations) {
            Iterator it = this.mBackStackStates.values().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((BackStackState) it.next()).mFragments.iterator();
                while (it2.hasNext()) {
                    this.mFragmentStore.mNonConfig.clearNonConfigStateInternal((String) it2.next(), false);
                }
            }
        }
        dispatchStateChange(-1);
        Object obj = this.mHost;
        if (obj instanceof OnTrimMemoryProvider) {
            ((OnTrimMemoryProvider) obj).removeOnTrimMemoryListener(this.mOnTrimMemoryListener);
        }
        Object obj2 = this.mHost;
        if (obj2 instanceof OnConfigurationChangedProvider) {
            ((OnConfigurationChangedProvider) obj2).removeOnConfigurationChangedListener(this.mOnConfigurationChangedListener);
        }
        Object obj3 = this.mHost;
        if (obj3 instanceof OnMultiWindowModeChangedProvider) {
            ((OnMultiWindowModeChangedProvider) obj3).removeOnMultiWindowModeChangedListener(this.mOnMultiWindowModeChangedListener);
        }
        Object obj4 = this.mHost;
        if (obj4 instanceof OnPictureInPictureModeChangedProvider) {
            ((OnPictureInPictureModeChangedProvider) obj4).removeOnPictureInPictureModeChangedListener(this.mOnPictureInPictureModeChangedListener);
        }
        Object obj5 = this.mHost;
        if ((obj5 instanceof MenuHost) && this.mParent == null) {
            ((MenuHost) obj5).removeMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.mMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        }
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher != null) {
            Iterator it3 = this.mOnBackPressedCallback.cancellables.iterator();
            while (it3.hasNext()) {
                ((Cancellable) it3.next()).cancel();
            }
            this.mOnBackPressedDispatcher = null;
        }
        ActivityResultLauncher activityResultLauncher = this.mStartActivityForResult;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            this.mStartIntentSenderForResult.unregister();
            this.mRequestPermissions.unregister();
        }
    }

    public final void dispatchLowMemory(boolean z) {
        if (z && (this.mHost instanceof OnTrimMemoryProvider)) {
            throwException(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performLowMemory();
                if (z) {
                    fragment.mChildFragmentManager.dispatchLowMemory(true);
                }
            }
        }
    }

    public final void dispatchMultiWindowModeChanged(boolean z, boolean z2) {
        if (z2 && (this.mHost instanceof OnMultiWindowModeChangedProvider)) {
            throwException(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
                if (z2) {
                    fragment.mChildFragmentManager.dispatchMultiWindowModeChanged(z, true);
                }
            }
        }
    }

    public final void dispatchOnHiddenChanged() {
        for (Fragment fragment : this.mFragmentStore.getActiveFragments()) {
            if (fragment != null) {
                fragment.onHiddenChanged(fragment.isHidden());
                fragment.mChildFragmentManager.dispatchOnHiddenChanged();
            }
        }
    }

    public final boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mCurState <= 0) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mCurState > 0) {
            for (Fragment fragment : this.mFragmentStore.getFragments()) {
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public final void dispatchParentPrimaryNavigationFragmentChanged(Fragment fragment) {
        if (fragment != null && fragment.equals(findActiveFragment(fragment.mWho))) {
            fragment.performPrimaryNavigationFragmentChanged();
        }
    }

    public final void dispatchPause() {
        dispatchStateChange(5);
    }

    public final void dispatchPictureInPictureModeChanged(boolean z, boolean z2) {
        if (z2 && (this.mHost instanceof OnPictureInPictureModeChangedProvider)) {
            throwException(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
                if (z2) {
                    fragment.mChildFragmentManager.dispatchPictureInPictureModeChanged(z, true);
                }
            }
        }
    }

    public final boolean dispatchPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mCurState <= 0) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && isParentMenuVisible$ar$ds(fragment) && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public final void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.mIsStateSaved = false;
        dispatchStateChange(7);
    }

    public final void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.mIsStateSaved = false;
        dispatchStateChange(5);
    }

    public final void dispatchStateChange(int i) {
        try {
            this.mExecutingActions = true;
            for (FragmentStateManager fragmentStateManager : this.mFragmentStore.mActive.values()) {
                if (fragmentStateManager != null) {
                    fragmentStateManager.mFragmentManagerState = i;
                }
            }
            moveToState(i, false);
            Iterator it = collectAllSpecialEffectsController().iterator();
            while (it.hasNext()) {
                ((SpecialEffectsController) it.next()).forceCompleteAllOperations();
            }
            this.mExecutingActions = false;
            execPendingActions(true);
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    public final void dispatchStop() {
        this.mStopped = true;
        this.mNonConfig.mIsStateSaved = true;
        dispatchStateChange(4);
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        FragmentStore fragmentStore = this.mFragmentStore;
        if (!fragmentStore.mActive.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (FragmentStateManager fragmentStateManager : fragmentStore.mActive.values()) {
                printWriter.print(str);
                if (fragmentStateManager != null) {
                    String valueOf = String.valueOf(str);
                    Fragment fragment = fragmentStateManager.mFragment;
                    printWriter.println(fragment);
                    fragment.dump(valueOf.concat("    "), fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size2 = fragmentStore.mAdded.size();
        if (size2 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size2; i++) {
                Fragment fragment2 = (Fragment) fragmentStore.mAdded.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList arrayList = this.mCreatedMenus;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment3 = (Fragment) this.mCreatedMenus.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        int size3 = this.mBackStack.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size3; i3++) {
                String valueOf2 = String.valueOf(str);
                BackStackRecord backStackRecord = (BackStackRecord) this.mBackStack.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(valueOf2.concat("    "), printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.mBackStackIndex.get());
        synchronized (this.mPendingActions) {
            int size4 = this.mPendingActions.size();
            if (size4 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size4; i4++) {
                    Object obj = (OpGenerator) this.mPendingActions.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
    }

    public final void endAnimatingAwayFragments() {
        Iterator it = collectAllSpecialEffectsController().iterator();
        while (it.hasNext()) {
            ((SpecialEffectsController) it.next()).forceCompleteAllOperations();
        }
    }

    public final void enqueueAction(OpGenerator opGenerator, boolean z) {
        if (!z) {
            if (this.mHost == null) {
                if (this.mDestroyed) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            checkStateLoss();
        }
        synchronized (this.mPendingActions) {
            if (this.mHost == null) {
                if (z) {
                    return;
                } else {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            }
            this.mPendingActions.add(opGenerator);
            synchronized (this.mPendingActions) {
                if (this.mPendingActions.size() == 1) {
                    this.mHost.handler.removeCallbacks(this.mExecCommit);
                    this.mHost.handler.post(this.mExecCommit);
                    updateOnBackPressedCallbackEnabled();
                }
            }
        }
    }

    public final boolean execPendingActions(boolean z) {
        ensureExecReady(z);
        boolean z2 = false;
        while (true) {
            ArrayList arrayList = this.mTmpRecords;
            ArrayList arrayList2 = this.mTmpIsPop;
            synchronized (this.mPendingActions) {
                if (this.mPendingActions.isEmpty()) {
                    break;
                }
                try {
                    int size = this.mPendingActions.size();
                    boolean z3 = false;
                    for (int i = 0; i < size; i++) {
                        z3 |= ((OpGenerator) this.mPendingActions.get(i)).generateOps(arrayList, arrayList2);
                    }
                    if (!z3) {
                        break;
                    }
                    z2 = true;
                    this.mExecutingActions = true;
                    try {
                        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                    } finally {
                        cleanupExec();
                    }
                } finally {
                    this.mPendingActions.clear();
                    this.mHost.handler.removeCallbacks(this.mExecCommit);
                }
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return z2;
    }

    public final void execSingleAction(OpGenerator opGenerator, boolean z) {
        if (z && (this.mHost == null || this.mDestroyed)) {
            return;
        }
        ensureExecReady(z);
        opGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop);
        this.mExecutingActions = true;
        try {
            removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            cleanupExec();
            updateOnBackPressedCallbackEnabled();
            doPendingDeferredStart();
            this.mFragmentStore.burpActive();
        } catch (Throwable th) {
            cleanupExec();
            throw th;
        }
    }

    public final void executePendingTransactions$ar$ds() {
        execPendingActions(true);
        forcePostponedTransactions();
    }

    public final Fragment findActiveFragment(String str) {
        return this.mFragmentStore.findActiveFragment(str);
    }

    public final int findBackStackIndex(String str, int i, boolean z) {
        if (this.mBackStack.isEmpty()) {
            return -1;
        }
        if (str == null && i < 0) {
            if (z) {
                return 0;
            }
            return this.mBackStack.size() - 1;
        }
        int size = this.mBackStack.size() - 1;
        while (size >= 0) {
            BackStackRecord backStackRecord = (BackStackRecord) this.mBackStack.get(size);
            if ((str != null && str.equals(backStackRecord.mName)) || (i >= 0 && i == backStackRecord.mIndex)) {
                break;
            }
            size--;
        }
        if (size < 0) {
            return size;
        }
        if (!z) {
            if (size == this.mBackStack.size() - 1) {
                return -1;
            }
            return size + 1;
        }
        while (size > 0) {
            int i2 = size - 1;
            BackStackRecord backStackRecord2 = (BackStackRecord) this.mBackStack.get(i2);
            if ((str != null && str.equals(backStackRecord2.mName)) || (i >= 0 && i == backStackRecord2.mIndex)) {
                size = i2;
            } else {
                return size;
            }
        }
        return size;
    }

    public final Fragment findFragmentById(int i) {
        FragmentStore fragmentStore = this.mFragmentStore;
        int size = fragmentStore.mAdded.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = (Fragment) fragmentStore.mAdded.get(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            } else {
                for (FragmentStateManager fragmentStateManager : fragmentStore.mActive.values()) {
                    if (fragmentStateManager != null) {
                        Fragment fragment2 = fragmentStateManager.mFragment;
                        if (fragment2.mFragmentId == i) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    public final Fragment findFragmentByTag(String str) {
        FragmentStore fragmentStore = this.mFragmentStore;
        int size = fragmentStore.mAdded.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = (Fragment) fragmentStore.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            } else {
                for (FragmentStateManager fragmentStateManager : fragmentStore.mActive.values()) {
                    if (fragmentStateManager != null) {
                        Fragment fragment2 = fragmentStateManager.mFragment;
                        if (str.equals(fragment2.mTag)) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    public final int getBackStackEntryCount() {
        int i;
        int size = this.mBackStack.size();
        if (this.mTransitioningOp != null) {
            i = 1;
        } else {
            i = 0;
        }
        return size + i;
    }

    public final FragmentFactory getFragmentFactory() {
        Fragment fragment = this.mParent;
        if (fragment != null) {
            return fragment.mFragmentManager.getFragmentFactory();
        }
        return this.mHostFragmentFactory;
    }

    public final List getFragments() {
        return this.mFragmentStore.getFragments();
    }

    public final BundleCompat$Api33Impl getSpecialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging() {
        Fragment fragment = this.mParent;
        if (fragment != null) {
            return fragment.mFragmentManager.getSpecialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging();
        }
        return this.mDefaultSpecialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    final void handleOnBackPressed() {
        int i;
        execPendingActions(true);
        if (this.mTransitioningOp != null) {
            if (!this.mBackStackChangeListeners.isEmpty()) {
                LinkedHashSet linkedHashSet = new LinkedHashSet(fragmentsFromRecord$ar$ds(this.mTransitioningOp));
                ArrayList arrayList = this.mBackStackChangeListeners;
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    FragmentNavigator$onAttach$2 fragmentNavigator$onAttach$2 = (FragmentNavigator$onAttach$2) arrayList.get(i2);
                    Iterator it = linkedHashSet.iterator();
                    while (true) {
                        i = i2 + 1;
                        if (it.hasNext()) {
                            fragmentNavigator$onAttach$2.onBackStackChangeCommitted((Fragment) it.next(), true);
                        }
                    }
                    i2 = i;
                }
            }
            ArrayList arrayList2 = this.mTransitioningOp.mOps;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                Fragment fragment = ((FragmentTransaction.Op) arrayList2.get(i3)).mFragment;
                if (fragment != null) {
                    fragment.mTransitioning = false;
                }
            }
            for (SpecialEffectsController specialEffectsController : collectChangedControllers(new ArrayList(Collections.singletonList(this.mTransitioningOp)), 0, 1)) {
                specialEffectsController.processStart(specialEffectsController.runningOperations);
                specialEffectsController.commitEffects$fragment_release(specialEffectsController.runningOperations);
            }
            this.mTransitioningOp = null;
            updateOnBackPressedCallbackEnabled();
            if (isLoggingEnabled(3)) {
                boolean z = this.mOnBackPressedCallback.isEnabled;
                toString();
                return;
            }
            return;
        }
        if (this.mOnBackPressedCallback.isEnabled) {
            popBackStackImmediate();
        } else {
            this.mOnBackPressedDispatcher.onBackPressed();
        }
    }

    final void hideFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Objects.toString(fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
            setVisibleRemovingFragment(fragment);
        }
    }

    public final boolean isParentAdded() {
        Fragment fragment = this.mParent;
        if (fragment == null) {
            return true;
        }
        if (fragment.isAdded() && this.mParent.getParentFragmentManager().isParentAdded()) {
            return true;
        }
        return false;
    }

    public final boolean isPrimaryNavigation(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.mFragmentManager;
        if (fragment.equals(fragmentManager.mPrimaryNav) && isPrimaryNavigation(fragmentManager.mParent)) {
            return true;
        }
        return false;
    }

    public final boolean isStateSaved() {
        if (!this.mStateSaved && !this.mStopped) {
            return false;
        }
        return true;
    }

    final void moveToState(int i, boolean z) {
        FragmentHostCallback fragmentHostCallback;
        if (this.mHost == null && i != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.mCurState) {
            this.mCurState = i;
            FragmentStore fragmentStore = this.mFragmentStore;
            ArrayList arrayList = fragmentStore.mAdded;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                FragmentStateManager fragmentStateManager = (FragmentStateManager) fragmentStore.mActive.get(((Fragment) arrayList.get(i2)).mWho);
                if (fragmentStateManager != null) {
                    fragmentStateManager.moveToExpectedState();
                }
            }
            for (FragmentStateManager fragmentStateManager2 : fragmentStore.mActive.values()) {
                if (fragmentStateManager2 != null) {
                    fragmentStateManager2.moveToExpectedState();
                    Fragment fragment = fragmentStateManager2.mFragment;
                    if (fragment.mRemoving && !fragment.isInBackStack()) {
                        if (fragment.mBeingSaved && !fragmentStore.mSavedState.containsKey(fragment.mWho)) {
                            fragmentStore.setSavedState(fragment.mWho, fragmentStateManager2.saveState());
                        }
                        fragmentStore.makeInactive(fragmentStateManager2);
                    }
                }
            }
            startPendingDeferredFragments();
            if (this.mNeedMenuInvalidate && (fragmentHostCallback = this.mHost) != null && this.mCurState == 7) {
                fragmentHostCallback.onSupportInvalidateOptionsMenu();
                this.mNeedMenuInvalidate = false;
            }
        }
    }

    public final void noteStateNotSaved() {
        if (this.mHost != null) {
            this.mStateSaved = false;
            this.mStopped = false;
            this.mNonConfig.mIsStateSaved = false;
            for (Fragment fragment : this.mFragmentStore.getFragments()) {
                if (fragment != null) {
                    fragment.noteStateNotSaved();
                }
            }
        }
    }

    public final void performPendingDeferredStart(FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.mFragment;
        if (fragment.mDeferStart) {
            if (this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
            } else {
                fragment.mDeferStart = false;
                fragmentStateManager.moveToExpectedState();
            }
        }
    }

    public final void popBackStack$ar$ds(String str) {
        enqueueAction(new PopBackStackState(str, -1), false);
    }

    public final boolean popBackStackImmediate() {
        return popBackStackImmediate$ar$ds(-1, 0);
    }

    public final boolean popBackStackImmediate$ar$ds(int i, int i2) {
        execPendingActions(false);
        ensureExecReady(true);
        Fragment fragment = this.mPrimaryNav;
        if (fragment != null && i < 0 && fragment.getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        boolean popBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, null, i, i2);
        if (popBackStackState) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return popBackStackState;
    }

    final boolean popBackStackState(ArrayList arrayList, ArrayList arrayList2, String str, int i, int i2) {
        boolean z;
        if (1 != i2) {
            z = false;
        } else {
            z = true;
        }
        int findBackStackIndex = findBackStackIndex(str, i, z);
        if (findBackStackIndex < 0) {
            return false;
        }
        int size = this.mBackStack.size();
        while (true) {
            size--;
            if (size < findBackStackIndex) {
                return true;
            }
            arrayList.add((BackStackRecord) this.mBackStack.remove(size));
            arrayList2.add(true);
        }
    }

    final void removeFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Objects.toString(fragment);
            int i = fragment.mBackStackNesting;
        }
        boolean z = !fragment.isInBackStack();
        if (fragment.mDetached && !z) {
            return;
        }
        this.mFragmentStore.removeFragment(fragment);
        if (isMenuAvailable$ar$ds(fragment)) {
            this.mNeedMenuInvalidate = true;
        }
        fragment.mRemoving = true;
        setVisibleRemovingFragment(fragment);
    }

    public final void restoreSaveStateInternal(Parcelable parcelable) {
        FragmentStateManager fragmentStateManager;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3 = (Bundle) parcelable;
        for (String str : bundle3.keySet()) {
            if (str.startsWith("result_") && (bundle2 = bundle3.getBundle(str)) != null) {
                bundle2.setClassLoader(this.mHost.context.getClassLoader());
                this.mResults.put(str.substring(7), bundle2);
            }
        }
        HashMap hashMap = new HashMap();
        for (String str2 : bundle3.keySet()) {
            if (str2.startsWith("fragment_") && (bundle = bundle3.getBundle(str2)) != null) {
                bundle.setClassLoader(this.mHost.context.getClassLoader());
                hashMap.put(str2.substring(9), bundle);
            }
        }
        FragmentStore fragmentStore = this.mFragmentStore;
        fragmentStore.mSavedState.clear();
        fragmentStore.mSavedState.putAll(hashMap);
        FragmentManagerState fragmentManagerState = (FragmentManagerState) bundle3.getParcelable("state");
        if (fragmentManagerState == null) {
            return;
        }
        this.mFragmentStore.mActive.clear();
        ArrayList arrayList = fragmentManagerState.mActive;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Bundle savedState = this.mFragmentStore.setSavedState((String) arrayList.get(i), null);
            if (savedState != null) {
                Fragment fragment = (Fragment) this.mNonConfig.mRetainedFragments.get(((FragmentState) savedState.getParcelable("state")).mWho);
                if (fragment != null) {
                    if (isLoggingEnabled(2)) {
                        Objects.toString(fragment);
                    }
                    fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging, this.mFragmentStore, fragment, savedState);
                } else {
                    fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging, this.mFragmentStore, this.mHost.context.getClassLoader(), getFragmentFactory(), savedState);
                }
                Fragment fragment2 = fragmentStateManager.mFragment;
                fragment2.mSavedFragmentState = savedState;
                fragment2.mFragmentManager = this;
                if (isLoggingEnabled(2)) {
                    String str3 = fragment2.mWho;
                    Objects.toString(fragment2);
                }
                fragmentStateManager.restoreState(this.mHost.context.getClassLoader());
                this.mFragmentStore.makeActive(fragmentStateManager);
                fragmentStateManager.mFragmentManagerState = this.mCurState;
            }
        }
        for (Fragment fragment3 : new ArrayList(this.mNonConfig.mRetainedFragments.values())) {
            if (!this.mFragmentStore.containsActiveFragment(fragment3.mWho)) {
                if (isLoggingEnabled(2)) {
                    Objects.toString(fragment3);
                    Objects.toString(fragmentManagerState.mActive);
                }
                this.mNonConfig.removeRetainedFragment(fragment3);
                fragment3.mFragmentManager = this;
                FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging, this.mFragmentStore, fragment3);
                fragmentStateManager2.mFragmentManagerState = 1;
                fragmentStateManager2.moveToExpectedState();
                fragment3.mRemoving = true;
                fragmentStateManager2.moveToExpectedState();
            }
        }
        FragmentStore fragmentStore2 = this.mFragmentStore;
        ArrayList<String> arrayList2 = fragmentManagerState.mAdded;
        fragmentStore2.mAdded.clear();
        if (arrayList2 != null) {
            for (String str4 : arrayList2) {
                Fragment findActiveFragment = fragmentStore2.findActiveFragment(str4);
                if (findActiveFragment != null) {
                    if (isLoggingEnabled(2)) {
                        Objects.toString(findActiveFragment);
                    }
                    fragmentStore2.addFragment(findActiveFragment);
                } else {
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str4, "No instantiated fragment for (", ")"));
                }
            }
        }
        BackStackRecordState[] backStackRecordStateArr = fragmentManagerState.mBackStack;
        if (backStackRecordStateArr != null) {
            this.mBackStack = new ArrayList(backStackRecordStateArr.length);
            int i2 = 0;
            while (true) {
                BackStackRecordState[] backStackRecordStateArr2 = fragmentManagerState.mBackStack;
                if (i2 >= backStackRecordStateArr2.length) {
                    break;
                }
                BackStackRecordState backStackRecordState = backStackRecordStateArr2[i2];
                BackStackRecord backStackRecord = new BackStackRecord(this);
                backStackRecordState.fillInBackStackRecord(backStackRecord);
                backStackRecord.mIndex = backStackRecordState.mIndex;
                for (int i3 = 0; i3 < backStackRecordState.mFragmentWhos.size(); i3++) {
                    String str5 = (String) backStackRecordState.mFragmentWhos.get(i3);
                    if (str5 != null) {
                        ((FragmentTransaction.Op) backStackRecord.mOps.get(i3)).mFragment = findActiveFragment(str5);
                    }
                }
                backStackRecord.bumpBackStackNesting(1);
                if (isLoggingEnabled(2)) {
                    int i4 = backStackRecord.mIndex;
                    backStackRecord.toString();
                    PrintWriter printWriter = new PrintWriter(new LogWriter());
                    backStackRecord.dump("  ", printWriter, false);
                    printWriter.close();
                }
                this.mBackStack.add(backStackRecord);
                i2++;
            }
        } else {
            this.mBackStack = new ArrayList();
        }
        this.mBackStackIndex.set(fragmentManagerState.mBackStackIndex);
        String str6 = fragmentManagerState.mPrimaryNavActiveWho;
        if (str6 != null) {
            Fragment findActiveFragment2 = findActiveFragment(str6);
            this.mPrimaryNav = findActiveFragment2;
            dispatchParentPrimaryNavigationFragmentChanged(findActiveFragment2);
        }
        ArrayList arrayList3 = fragmentManagerState.mBackStackStateKeys;
        if (arrayList3 != null) {
            for (int i5 = 0; i5 < arrayList3.size(); i5++) {
                this.mBackStackStates.put((String) arrayList3.get(i5), (BackStackState) fragmentManagerState.mBackStackStates.get(i5));
            }
        }
        this.mLaunchedFragments = new ArrayDeque(fragmentManagerState.mLaunchedFragments);
    }

    public final Bundle saveAllStateInternal() {
        BackStackRecordState[] backStackRecordStateArr;
        ArrayList arrayList;
        Bundle bundle = new Bundle();
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions(true);
        this.mStateSaved = true;
        this.mNonConfig.mIsStateSaved = true;
        FragmentStore fragmentStore = this.mFragmentStore;
        ArrayList arrayList2 = new ArrayList(fragmentStore.mActive.size());
        for (FragmentStateManager fragmentStateManager : fragmentStore.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment = fragmentStateManager.mFragment;
                fragmentStore.setSavedState(fragment.mWho, fragmentStateManager.saveState());
                arrayList2.add(fragment.mWho);
                if (isLoggingEnabled(2)) {
                    Objects.toString(fragment);
                    Objects.toString(fragment.mSavedFragmentState);
                }
            }
        }
        HashMap hashMap = this.mFragmentStore.mSavedState;
        if (!hashMap.isEmpty()) {
            FragmentStore fragmentStore2 = this.mFragmentStore;
            synchronized (fragmentStore2.mAdded) {
                backStackRecordStateArr = null;
                if (fragmentStore2.mAdded.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(fragmentStore2.mAdded.size());
                    Iterator it = fragmentStore2.mAdded.iterator();
                    while (it.hasNext()) {
                        Fragment fragment2 = (Fragment) it.next();
                        arrayList.add(fragment2.mWho);
                        if (isLoggingEnabled(2)) {
                            String str = fragment2.mWho;
                            Objects.toString(fragment2);
                        }
                    }
                }
            }
            int size = this.mBackStack.size();
            if (size > 0) {
                backStackRecordStateArr = new BackStackRecordState[size];
                for (int i = 0; i < size; i++) {
                    backStackRecordStateArr[i] = new BackStackRecordState((BackStackRecord) this.mBackStack.get(i));
                    if (isLoggingEnabled(2)) {
                        Objects.toString(this.mBackStack.get(i));
                    }
                }
            }
            FragmentManagerState fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.mActive = arrayList2;
            fragmentManagerState.mAdded = arrayList;
            fragmentManagerState.mBackStack = backStackRecordStateArr;
            fragmentManagerState.mBackStackIndex = this.mBackStackIndex.get();
            Fragment fragment3 = this.mPrimaryNav;
            if (fragment3 != null) {
                fragmentManagerState.mPrimaryNavActiveWho = fragment3.mWho;
            }
            fragmentManagerState.mBackStackStateKeys.addAll(this.mBackStackStates.keySet());
            fragmentManagerState.mBackStackStates.addAll(this.mBackStackStates.values());
            fragmentManagerState.mLaunchedFragments = new ArrayList(this.mLaunchedFragments);
            bundle.putParcelable("state", fragmentManagerState);
            for (String str2 : this.mResults.keySet()) {
                bundle.putBundle("result_".concat(String.valueOf(str2)), (Bundle) this.mResults.get(str2));
            }
            for (String str3 : hashMap.keySet()) {
                bundle.putBundle("fragment_".concat(String.valueOf(str3)), (Bundle) hashMap.get(str3));
            }
        }
        return bundle;
    }

    final void setExitAnimationOrder(Fragment fragment, boolean z) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer != null && (fragmentContainer instanceof FragmentContainerView)) {
            ((FragmentContainerView) fragmentContainer).drawDisappearingViewsFirst = !z;
        }
    }

    final void setMaxLifecycle(Fragment fragment, Lifecycle.State state) {
        if (fragment.equals(findActiveFragment(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, fragment, "Fragment ", " is not an active fragment of FragmentManager "));
    }

    final void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment != null && (!fragment.equals(findActiveFragment(fragment.mWho)) || (fragment.mHost != null && fragment.mFragmentManager != this))) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, fragment, "Fragment ", " is not an active fragment of FragmentManager "));
        }
        Fragment fragment2 = this.mPrimaryNav;
        this.mPrimaryNav = fragment;
        dispatchParentPrimaryNavigationFragmentChanged(fragment2);
        dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
    }

    public final void throwException(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter());
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback != null) {
            try {
                fragmentHostCallback.onDump$ar$ds(printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e) {
                Log.e("FragmentManager", "Failed dumping state", e);
                throw runtimeException;
            }
        }
        try {
            dump("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e2) {
            Log.e("FragmentManager", "Failed dumping state", e2);
            throw runtimeException;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.mParent;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.mParent)));
            sb.append("}");
        } else {
            FragmentHostCallback fragmentHostCallback = this.mHost;
            if (fragmentHostCallback != null) {
                sb.append(fragmentHostCallback.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.mHost)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void updateOnBackPressedCallbackEnabled() {
        synchronized (this.mPendingActions) {
            boolean z = true;
            if (!this.mPendingActions.isEmpty()) {
                this.mOnBackPressedCallback.setEnabled(true);
                if (isLoggingEnabled(3)) {
                    toString();
                }
            } else {
                if (getBackStackEntryCount() <= 0 || !isPrimaryNavigation(this.mParent)) {
                    z = false;
                }
                if (isLoggingEnabled(3)) {
                    toString();
                }
                this.mOnBackPressedCallback.setEnabled(z);
            }
        }
    }

    public FragmentManager(byte[] bArr) {
        this();
    }
}
