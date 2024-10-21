package com.google.android.apps.common.inject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.talkback.TalkBackService$TalkbackServiceStateNotifier$TalkBackServiceStateChangeListener;
import com.google.android.accessibility.talkback.UserInterface$UserInputEventListener;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.AccessibilityWindow;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.accessibility.utils.output.FeedbackController;
import com.google.android.marvin.talkback.R;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationModule {
    private static ApplicationModule serviceStateChangeNotifier$ar$class_merging;
    public final Object ApplicationModule$ar$application;

    public ApplicationModule(Object obj) {
        this.ApplicationModule$ar$application = obj;
    }

    public static ApplicationModule getInstance$ar$class_merging$416cc131_0() {
        if (serviceStateChangeNotifier$ar$class_merging == null) {
            serviceStateChangeNotifier$ar$class_merging = new ApplicationModule((short[]) null);
        }
        return serviceStateChangeNotifier$ar$class_merging;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    public final void add$ar$ds$915d421d_0(String str, int i) {
        this.ApplicationModule$ar$application.put(str, Integer.valueOf(i));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    public final Map build() {
        return DesugarCollections.unmodifiableMap(this.ApplicationModule$ar$application);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
    public final synchronized void cacheCurrentWindow(AccessibilityWindow accessibilityWindow, Filter filter) {
        AccessibilityNode root;
        clearCachedNodes();
        if (accessibilityWindow != null && (root = accessibilityWindow.getRoot()) != null) {
            ?? r0 = this.ApplicationModule$ar$application;
            List matchingDescendantsOrRoot = AccessibilityNodeInfoUtils.getMatchingDescendantsOrRoot(root.getCompat(), filter);
            ArrayList arrayList = new ArrayList(matchingDescendantsOrRoot.size());
            Iterator it = matchingDescendantsOrRoot.iterator();
            while (it.hasNext()) {
                arrayList.add(AccessibilityNode.takeOwnership((AccessibilityNodeInfoCompat) it.next()));
            }
            r0.addAll(arrayList);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
    public final void clear() {
        this.ApplicationModule$ar$application.clear();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    public final synchronized void clearCachedNodes() {
        this.ApplicationModule$ar$application.clear();
    }

    public final A11yAlertDialogWrapper create() {
        return new A11yAlertDialogWrapper(((AlertDialog.Builder) this.ApplicationModule$ar$application).create());
    }

    public final void dots6$ar$ds() {
        add$ar$ds$915d421d_0("Dot1", R.string.key_Dot1);
        add$ar$ds$915d421d_0("Dot2", R.string.key_Dot2);
        add$ar$ds$915d421d_0("Dot3", R.string.key_Dot3);
        add$ar$ds$915d421d_0("Dot4", R.string.key_Dot4);
        add$ar$ds$915d421d_0("Dot5", R.string.key_Dot5);
        add$ar$ds$915d421d_0("Dot6", R.string.key_Dot6);
    }

    public final void dots8$ar$ds() {
        dots6$ar$ds();
        add$ar$ds$915d421d_0("Dot7", R.string.key_Dot7);
        add$ar$ds$915d421d_0("Dot8", R.string.key_Dot8);
    }

    public final void dualJoysticks$ar$ds() {
        add$ar$ds$915d421d_0("LeftJoystickLeft", R.string.key_LeftJoystickLeft);
        add$ar$ds$915d421d_0("LeftJoystickRight", R.string.key_LeftJoystickRight);
        add$ar$ds$915d421d_0("LeftJoystickUp", R.string.key_LeftJoystickUp);
        add$ar$ds$915d421d_0("LeftJoystickDown", R.string.key_LeftJoystickDown);
        add$ar$ds$915d421d_0("LeftJoystickPress", R.string.key_LeftJoystickCenter);
        add$ar$ds$915d421d_0("RightJoystickLeft", R.string.key_RightJoystickLeft);
        add$ar$ds$915d421d_0("RightJoystickRight", R.string.key_RightJoystickRight);
        add$ar$ds$915d421d_0("RightJoystickUp", R.string.key_RightJoystickUp);
        add$ar$ds$915d421d_0("RightJoystickDown", R.string.key_RightJoystickDown);
        add$ar$ds$915d421d_0("RightJoystickPress", R.string.key_RightJoystickCenter);
    }

    public final void emitFeedback(FeedbackManager$Type feedbackManager$Type) {
        FeedbackManager$Type feedbackManager$Type2 = FeedbackManager$Type.NAVIGATE_OUT_OF_BOUNDS;
        int i = feedbackManager$Type.resId;
        Logger logger = Performance.DEFAULT_LOGGER;
        ((FeedbackController) this.ApplicationModule$ar$application).playAuditory(i, null);
    }

    public final void emitOnFailure$ar$ds(boolean z, FeedbackManager$Type feedbackManager$Type) {
        if (!z) {
            emitFeedback(feedbackManager$Type);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Collection, java.lang.Object] */
    public final synchronized List getCachedNodes() {
        List arrayList;
        if (this.ApplicationModule$ar$application.isEmpty()) {
            arrayList = Collections.emptyList();
        } else {
            arrayList = new ArrayList((Collection) this.ApplicationModule$ar$application);
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Set, java.lang.Object] */
    public final void notifyTalkBackServiceStateChanged(boolean z) {
        Iterator it = this.ApplicationModule$ar$application.iterator();
        while (it.hasNext()) {
            ((TalkBackService$TalkbackServiceStateNotifier$TalkBackServiceStateChangeListener) it.next()).onServiceStateChange(z);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
    public final void registerListener(UserInterface$UserInputEventListener userInterface$UserInputEventListener) {
        this.ApplicationModule$ar$application.add(userInterface$UserInputEventListener);
    }

    public final ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        add$ar$ds$915d421d_0("RoutingKey", R.string.key_Routing);
        return this;
    }

    public final void setCancelable$ar$class_merging$ar$ds() {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setCancelable(true);
    }

    public final void setMessage$ar$class_merging$51f49cd0_0$ar$ds(CharSequence charSequence) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setMessage(charSequence);
    }

    public final void setMessage$ar$class_merging$ar$ds(int i) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setMessage(i);
    }

    public final void setNegativeButton$ar$class_merging$ar$ds(int i, DialogInterface.OnClickListener onClickListener) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setNegativeButton(i, onClickListener);
    }

    public final void setOnCancelListener$ar$class_merging$ar$ds() {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setOnCancelListener(null);
    }

    public final void setOnDismissListener$ar$class_merging$ar$ds(DialogInterface.OnDismissListener onDismissListener) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setOnDismissListener$ar$ds(onDismissListener);
    }

    public final void setPositiveButton$ar$class_merging$ar$ds(int i, DialogInterface.OnClickListener onClickListener) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setPositiveButton(i, onClickListener);
    }

    public final void setTitle$ar$class_merging$51f49cd0_0$ar$ds(CharSequence charSequence) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setTitle(charSequence);
    }

    public final void setTitle$ar$class_merging$ar$ds(int i) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setTitle(i);
    }

    public final void setView$ar$class_merging$ar$ds(View view) {
        ((AlertDialog.Builder) this.ApplicationModule$ar$application).setView(view);
    }

    public ApplicationModule(short[] sArr, byte[] bArr) {
        this.ApplicationModule$ar$application = new HashMap();
    }

    public ApplicationModule(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.ApplicationModule$ar$application = new ArrayList();
    }

    public ApplicationModule(char[] cArr, byte[] bArr) {
        this.ApplicationModule$ar$application = new HashMap();
    }

    public ApplicationModule(byte[] bArr, byte[] bArr2) {
        this.ApplicationModule$ar$application = new HashMap();
    }

    private ApplicationModule(short[] sArr) {
        this.ApplicationModule$ar$application = ConcurrentHashMap.newKeySet();
    }

    public ApplicationModule(char[] cArr) {
        this.ApplicationModule$ar$application = new ArrayList();
    }

    public ApplicationModule(byte[] bArr) {
        this.ApplicationModule$ar$application = new ArrayList();
    }

    public ApplicationModule() {
        this.ApplicationModule$ar$application = new ArrayList();
    }
}
