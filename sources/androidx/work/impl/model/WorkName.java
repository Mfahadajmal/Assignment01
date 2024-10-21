package androidx.work.impl.model;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.viewsintegration.EmojiInputConnection;
import androidx.emoji2.viewsintegration.EmojiKeyListener;
import androidx.emoji2.viewsintegration.EmojiTextWatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStore;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicInt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkName {
    public final Object WorkName$ar$name;
    public final Object WorkName$ar$workSpecId;

    public WorkName(int i, int i2) {
        this.WorkName$ar$name = new int[]{i, i2};
        this.WorkName$ar$workSpecId = new float[]{0.0f, 1.0f};
    }

    private static final boolean belongsToRoot$ar$ds(String str, String str2) {
        String removeTrailingSlash = FileProvider.removeTrailingSlash(str);
        String removeTrailingSlash2 = FileProvider.removeTrailingSlash(str2);
        if (!removeTrailingSlash.equals(removeTrailingSlash2)) {
            if (!removeTrailingSlash.startsWith(removeTrailingSlash2 + '/')) {
                return false;
            }
            return true;
        }
        return true;
    }

    public static final boolean isEmojiCapableKeyListener$ar$ds(KeyListener keyListener) {
        if (!(keyListener instanceof NumberKeyListener)) {
            return true;
        }
        return false;
    }

    public final boolean block$room_runtime_release() {
        synchronized (this) {
            if (((AtomicBoolean) this.WorkName$ar$workSpecId).getValue()) {
                return false;
            }
            ((AtomicInt) this.WorkName$ar$name).incrementAndGet();
            return true;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    public final boolean contains(WorkGenerationalId workGenerationalId) {
        boolean containsKey;
        synchronized (this.WorkName$ar$name) {
            containsKey = this.WorkName$ar$workSpecId.containsKey(workGenerationalId);
        }
        return containsKey;
    }

    public final void dispatchOnFragmentActivityCreated(Fragment fragment, Bundle bundle, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentActivityCreated(fragment, bundle, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentAttached(Fragment fragment, boolean z) {
        fragment.getClass();
        FragmentManager fragmentManager = (FragmentManager) this.WorkName$ar$name;
        Context context = fragmentManager.mHost.context;
        Fragment fragment2 = fragmentManager.mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentAttached(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentCreated(Fragment fragment, Bundle bundle, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentCreated(fragment, bundle, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentDestroyed(Fragment fragment, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentDestroyed(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentDetached(Fragment fragment, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentDetached(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentPaused(Fragment fragment, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentPaused(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentPreAttached(Fragment fragment, boolean z) {
        fragment.getClass();
        FragmentManager fragmentManager = (FragmentManager) this.WorkName$ar$name;
        Context context = fragmentManager.mHost.context;
        Fragment fragment2 = fragmentManager.mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentPreAttached(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentPreCreated(Fragment fragment, Bundle bundle, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentPreCreated(fragment, bundle, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentResumed(Fragment fragment, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentResumed(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentSaveInstanceState(Fragment fragment, Bundle bundle, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentStarted(Fragment fragment, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentStarted(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentStopped(Fragment fragment, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentStopped(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentViewCreated(Fragment fragment, View view, Bundle bundle, boolean z) {
        fragment.getClass();
        view.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentViewCreated(fragment, view, bundle, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void dispatchOnFragmentViewDestroyed(Fragment fragment, boolean z) {
        fragment.getClass();
        Fragment fragment2 = ((FragmentManager) this.WorkName$ar$name).mParent;
        if (fragment2 != null) {
            fragment2.getParentFragmentManager().mLifecycleCallbacksDispatcher$ar$class_merging$ar$class_merging$ar$class_merging.dispatchOnFragmentViewDestroyed(fragment, true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.WorkName$ar$workSpecId).iterator();
        if (it.hasNext()) {
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final File getFileForUri(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        int indexOf = encodedPath.indexOf(47, 1);
        String decode = Uri.decode(encodedPath.substring(1, indexOf));
        String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
        File file = (File) ((HashMap) this.WorkName$ar$name).get(decode);
        if (file != null) {
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (belongsToRoot$ar$ds(canonicalFile.getPath(), file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                file2.toString();
                throw new IllegalArgumentException("Failed to resolve canonical path for ".concat(file2.toString()));
            }
        }
        Objects.toString(uri);
        throw new IllegalArgumentException("Unable to find configured root for ".concat(String.valueOf(uri)));
    }

    public final KeyListener getKeyListener(KeyListener keyListener) {
        if (isEmojiCapableKeyListener$ar$ds(keyListener) && !(keyListener instanceof EmojiKeyListener)) {
            if (keyListener == null) {
                return null;
            }
            if (!(keyListener instanceof NumberKeyListener)) {
                return new EmojiKeyListener(keyListener);
            }
            return keyListener;
        }
        return keyListener;
    }

    public final Uri getUriForFile(File file) {
        String substring;
        try {
            String canonicalPath = file.getCanonicalPath();
            Map.Entry entry = null;
            for (Map.Entry entry2 : ((HashMap) this.WorkName$ar$name).entrySet()) {
                String path = ((File) entry2.getValue()).getPath();
                if (belongsToRoot$ar$ds(canonicalPath, path) && (entry == null || path.length() > ((File) entry.getValue()).getPath().length())) {
                    entry = entry2;
                }
            }
            if (entry != null) {
                String path2 = ((File) entry.getValue()).getPath();
                if (path2.endsWith("/")) {
                    substring = canonicalPath.substring(path2.length());
                } else {
                    substring = canonicalPath.substring(path2.length() + 1);
                }
                return new Uri.Builder().scheme("content").authority((String) this.WorkName$ar$workSpecId).encodedPath(Uri.encode((String) entry.getKey()) + '/' + Uri.encode(substring, "/")).build();
            }
            throw new IllegalArgumentException("Failed to find configured root that contains ".concat(String.valueOf(canonicalPath)));
        } catch (IOException unused) {
            Objects.toString(file);
            throw new IllegalArgumentException("Failed to resolve canonical path for ".concat(file.toString()));
        }
    }

    public final void launchUrl(Context context, Uri uri) {
        ((Intent) this.WorkName$ar$name).setData(uri);
        context.startActivity((Intent) this.WorkName$ar$name, (Bundle) this.WorkName$ar$workSpecId);
    }

    public final void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = ((EditText) this.WorkName$ar$workSpecId).getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        try {
            boolean z = true;
            if (obtainStyledAttributes.hasValue(14)) {
                z = obtainStyledAttributes.getBoolean(14, true);
            }
            obtainStyledAttributes.recycle();
            EmojiTextWatcher emojiTextWatcher = (EmojiTextWatcher) ((NodeBrailler) this.WorkName$ar$name).NodeBrailler$ar$context;
            if (emojiTextWatcher.mEnabled != z) {
                emojiTextWatcher.mEnabled = z;
                if (z) {
                    EmojiCompat.get$ar$ds$55e866f7_0();
                    throw null;
                }
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo) {
        if (inputConnection == null) {
            return null;
        }
        if (!(inputConnection instanceof EmojiInputConnection)) {
            return new EmojiInputConnection((TextView) ((NodeBrailler) this.WorkName$ar$name).NodeBrailler$ar$rules, inputConnection);
        }
        return inputConnection;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.Map, java.lang.Object] */
    public final List remove(String str) {
        List list;
        str.getClass();
        synchronized (this.WorkName$ar$name) {
            ?? r1 = this.WorkName$ar$workSpecId;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : r1.entrySet()) {
                if (Intrinsics.areEqual(((WorkGenerationalId) entry.getKey()).workSpecId, str)) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            Iterator it = linkedHashMap.keySet().iterator();
            while (it.hasNext()) {
                this.WorkName$ar$workSpecId.remove((WorkGenerationalId) it.next());
            }
            list = OnDeviceLanguageIdentificationLogEvent.toList(linkedHashMap.values());
        }
        return list;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    public final ViewModelStore remove$ar$class_merging$ar$class_merging(WorkGenerationalId workGenerationalId) {
        ViewModelStore viewModelStore;
        synchronized (this.WorkName$ar$name) {
            viewModelStore = (ViewModelStore) this.WorkName$ar$workSpecId.remove(workGenerationalId);
        }
        return viewModelStore;
    }

    public final void startWork$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore, AppCompatTextClassifierHelper$Api26Impl appCompatTextClassifierHelper$Api26Impl) {
        ((WorkManagerTaskExecutor) this.WorkName$ar$workSpecId).executeOnTaskThread(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0((Object) this, (Object) viewModelStore, (Object) appCompatTextClassifierHelper$Api26Impl, 7, (byte[]) null));
    }

    public final /* synthetic */ void startWork$ar$class_merging$d9075868_0$ar$class_merging(ViewModelStore viewModelStore) {
        startWork$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(viewModelStore, null);
    }

    public final void stopWork$ar$class_merging$5fc1c17b_0$ar$class_merging(ViewModelStore viewModelStore, int i) {
        ((WorkManagerTaskExecutor) this.WorkName$ar$workSpecId).executeOnTaskThread(new StopWorkRunnable((Processor) this.WorkName$ar$name, viewModelStore, false, i));
    }

    public final /* synthetic */ void stopWork$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore) {
        WorkManagerImpl.Api24Impl.$default$stopWork$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this, viewModelStore);
    }

    public final /* synthetic */ void stopWorkWithReason$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore, int i) {
        WorkManagerImpl.Api24Impl.$default$stopWorkWithReason$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this, viewModelStore, i);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    public final ViewModelStore tokenFor$ar$class_merging$ar$class_merging(WorkGenerationalId workGenerationalId) {
        ViewModelStore viewModelStore;
        synchronized (this.WorkName$ar$name) {
            ?? r1 = this.WorkName$ar$workSpecId;
            Object obj = r1.get(workGenerationalId);
            if (obj == null) {
                obj = new ViewModelStore(workGenerationalId, (byte[]) null);
                r1.put(workGenerationalId, obj);
            }
            viewModelStore = (ViewModelStore) obj;
        }
        return viewModelStore;
    }

    public final void unblock$room_runtime_release() {
        synchronized (this) {
            ((AtomicInt) this.WorkName$ar$name).decrementAndGet();
            if (((AtomicInt) this.WorkName$ar$name).value < 0) {
                throw new IllegalStateException("Unbalanced call to unblock() detected.");
            }
        }
    }

    public WorkName(int i, int i2, int i3) {
        this.WorkName$ar$name = new int[]{i, i2, i3};
        this.WorkName$ar$workSpecId = new float[]{0.0f, 0.5f, 1.0f};
    }

    public WorkName(Intent intent, ArrayList arrayList) {
        this.WorkName$ar$workSpecId = intent;
        this.WorkName$ar$name = arrayList;
    }

    public WorkName(Animation animation) {
        this.WorkName$ar$workSpecId = animation;
        this.WorkName$ar$name = null;
    }

    public WorkName(Object obj, Object obj2) {
        this.WorkName$ar$name = obj;
        this.WorkName$ar$workSpecId = obj2;
    }

    public WorkName(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.WorkName$ar$workSpecId = "";
        this.WorkName$ar$name = "";
    }

    public WorkName(Animator animator) {
        this.WorkName$ar$workSpecId = null;
        AnimatorSet animatorSet = new AnimatorSet();
        this.WorkName$ar$name = animatorSet;
        animatorSet.play(animator);
    }

    public WorkName(FragmentManager fragmentManager) {
        this.WorkName$ar$name = fragmentManager;
        this.WorkName$ar$workSpecId = new CopyOnWriteArrayList();
    }

    public WorkName(String str) {
        this.WorkName$ar$name = new HashMap();
        this.WorkName$ar$workSpecId = str;
    }

    public WorkName(List list, List list2) {
        int size = list.size();
        this.WorkName$ar$name = new int[size];
        this.WorkName$ar$workSpecId = new float[size];
        for (int i = 0; i < size; i++) {
            ((int[]) this.WorkName$ar$name)[i] = ((Integer) list.get(i)).intValue();
            ((float[]) this.WorkName$ar$workSpecId)[i] = ((Float) list2.get(i)).floatValue();
        }
    }

    public WorkName(byte[] bArr, byte[] bArr2) {
        this((byte[]) null, (byte[]) null, (byte[]) null);
    }

    public WorkName(char[] cArr) {
        this.WorkName$ar$name = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
        this.WorkName$ar$workSpecId = OnDeviceSubjectSegmentationCreateLogEvent.atomic(false);
    }

    public WorkName(EditText editText) {
        this.WorkName$ar$workSpecId = editText;
        this.WorkName$ar$name = new NodeBrailler(editText);
    }

    public WorkName(Lifecycle lifecycle) {
        lifecycle.getClass();
        this.WorkName$ar$name = lifecycle;
        this.WorkName$ar$workSpecId = new ArrayList();
    }

    public WorkName(byte[] bArr) {
        this.WorkName$ar$name = new Object();
        this.WorkName$ar$workSpecId = new LinkedHashMap();
    }

    public WorkName(Processor processor, WorkManagerTaskExecutor workManagerTaskExecutor) {
        processor.getClass();
        workManagerTaskExecutor.getClass();
        this.WorkName$ar$name = processor;
        this.WorkName$ar$workSpecId = workManagerTaskExecutor;
    }

    public WorkName(String str, String str2) {
        str2.getClass();
        this.WorkName$ar$workSpecId = str;
        this.WorkName$ar$name = str2;
    }

    public WorkName() {
        throw null;
    }
}
