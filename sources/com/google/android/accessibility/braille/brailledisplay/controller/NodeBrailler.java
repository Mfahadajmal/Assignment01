package com.google.android.accessibility.braille.brailledisplay.controller;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewBoundsCheck$BoundFlags;
import android.support.v7.widget.ViewBoundsCheck$Callback;
import android.support.v7.widget.ViewInfoStore$InfoRecord;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.collection.LongSparseArrayKt;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.os.UserManagerCompat$Api24Impl;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.emoji2.viewsintegration.EmojiEditableFactory;
import androidx.emoji2.viewsintegration.EmojiTextWatcher;
import androidx.lifecycle.ViewModelStore;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import j$.util.Collection;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeBrailler {
    public final Object NodeBrailler$ar$context;
    public final Object NodeBrailler$ar$rules;

    public NodeBrailler(ViewBoundsCheck$Callback viewBoundsCheck$Callback) {
        this.NodeBrailler$ar$rules = viewBoundsCheck$Callback;
        this.NodeBrailler$ar$context = new ViewBoundsCheck$BoundFlags();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Collection, java.lang.Object] */
    private final ViewModelStore find$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return (ViewModelStore) Collection.EL.stream(this.NodeBrailler$ar$rules).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(accessibilityNodeInfoCompat, 1)).findFirst().orElse(null);
    }

    private final List obtainNodeTreePreorder(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(accessibilityNodeInfoCompat);
        for (int i = 0; i < accessibilityNodeInfoCompat.getChildCount(); i++) {
            AccessibilityNodeInfoCompat child = accessibilityNodeInfoCompat.getChild(i);
            if (AccessibilityNodeInfoUtils.FILTER_NON_FOCUSABLE_VISIBLE_NODE.accept(child) || AccessibilityNodeInfoUtils.FILTER_NON_FOCUSABLE_NON_VISIBLE_HAS_TEXT_NODE.accept(child)) {
                arrayList.addAll(obtainNodeTreePreorder(child));
            }
        }
        return arrayList;
    }

    public final void addToDisappearedInLayout(RecyclerView.ViewHolder viewHolder) {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.NodeBrailler$ar$rules).get(viewHolder);
        if (viewInfoStore$InfoRecord == null) {
            Object obj = this.NodeBrailler$ar$rules;
            ViewInfoStore$InfoRecord obtain = ViewInfoStore$InfoRecord.obtain();
            ((SimpleArrayMap) obj).put(viewHolder, obtain);
            viewInfoStore$InfoRecord = obtain;
        }
        viewInfoStore$InfoRecord.flags |= 1;
    }

    public final void addToOldChangeHolders(long j, RecyclerView.ViewHolder viewHolder) {
        ((LongSparseArray) this.NodeBrailler$ar$context).put(j, viewHolder);
    }

    public final void addToPostLayout$ar$class_merging(RecyclerView.ViewHolder viewHolder, NestedScrollingParentHelper nestedScrollingParentHelper) {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.NodeBrailler$ar$rules).get(viewHolder);
        if (viewInfoStore$InfoRecord == null) {
            Object obj = this.NodeBrailler$ar$rules;
            ViewInfoStore$InfoRecord obtain = ViewInfoStore$InfoRecord.obtain();
            ((SimpleArrayMap) obj).put(viewHolder, obtain);
            viewInfoStore$InfoRecord = obtain;
        }
        viewInfoStore$InfoRecord.postInfo$ar$class_merging = nestedScrollingParentHelper;
        viewInfoStore$InfoRecord.flags |= 8;
    }

    public final void addToPreLayout$ar$class_merging(RecyclerView.ViewHolder viewHolder, NestedScrollingParentHelper nestedScrollingParentHelper) {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.NodeBrailler$ar$rules).get(viewHolder);
        if (viewInfoStore$InfoRecord == null) {
            Object obj = this.NodeBrailler$ar$rules;
            ViewInfoStore$InfoRecord obtain = ViewInfoStore$InfoRecord.obtain();
            ((SimpleArrayMap) obj).put(viewHolder, obtain);
            viewInfoStore$InfoRecord = obtain;
        }
        viewInfoStore$InfoRecord.preInfo$ar$class_merging = nestedScrollingParentHelper;
        viewInfoStore$InfoRecord.flags |= 4;
    }

    public final CellsContent brailleNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return new CellsContent(formatSubtree(accessibilityNodeInfoCompat, null));
    }

    public final void clear() {
        ((SimpleArrayMap) this.NodeBrailler$ar$rules).clear();
        ((LongSparseArray) this.NodeBrailler$ar$context).clear();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.support.v7.widget.ViewBoundsCheck$Callback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.support.v7.widget.ViewBoundsCheck$Callback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.support.v7.widget.ViewBoundsCheck$Callback, java.lang.Object] */
    public final View findOneViewWithinBoundFlags(int i, int i2, int i3, int i4) {
        ?? r0 = this.NodeBrailler$ar$rules;
        int parentStart = r0.getParentStart();
        int parentEnd = r0.getParentEnd();
        View view = null;
        int i5 = i;
        while (i5 != i2) {
            ?? r4 = this.NodeBrailler$ar$rules;
            View childAt = r4.getChildAt(i5);
            ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).setBounds(parentStart, parentEnd, r4.getChildStart(childAt), this.NodeBrailler$ar$rules.getChildEnd(childAt));
            ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).resetFlags();
            ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).addFlags(i3);
            if (!((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).boundsMatch()) {
                ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).resetFlags();
                ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).addFlags(i4);
                int i6 = 1;
                if (true == ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).boundsMatch()) {
                    view = childAt;
                }
                if (i2 <= i) {
                    i6 = -1;
                }
                i5 += i6;
            } else {
                return childAt;
            }
        }
        return view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02f3 A[EDGE_INSN: B:101:0x02f3->B:102:0x02f3 BREAK  A[LOOP:2: B:92:0x02e1->B:99:0x02e1], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0318 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.CharSequence formatSubtree(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r17, android.view.accessibility.AccessibilityEvent r18) {
        /*
            Method dump skipped, instructions count: 803
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler.formatSubtree(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, android.view.accessibility.AccessibilityEvent):java.lang.CharSequence");
    }

    public final InputFilter[] getFilters(InputFilter[] inputFilterArr) {
        return ((UserManagerCompat$Api24Impl) ((ViewModelStore) this.NodeBrailler$ar$rules).ViewModelStore$ar$map).getFilters(inputFilterArr);
    }

    public final boolean isDisappearing(RecyclerView.ViewHolder viewHolder) {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.NodeBrailler$ar$rules).get(viewHolder);
        if (viewInfoStore$InfoRecord != null && (viewInfoStore$InfoRecord.flags & 1) != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.support.v7.widget.ViewBoundsCheck$Callback, java.lang.Object] */
    public final boolean isViewWithinBoundFlags$ar$ds(View view) {
        Object obj = this.NodeBrailler$ar$context;
        ?? r1 = this.NodeBrailler$ar$rules;
        ((ViewBoundsCheck$BoundFlags) obj).setBounds(r1.getParentStart(), r1.getParentEnd(), r1.getChildStart(view), r1.getChildEnd(view));
        ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).resetFlags();
        ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).addFlags(24579);
        return ((ViewBoundsCheck$BoundFlags) this.NodeBrailler$ar$context).boundsMatch();
    }

    public final void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = ((TextView) this.NodeBrailler$ar$context).getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        try {
            boolean z = true;
            if (obtainStyledAttributes.hasValue(14)) {
                z = obtainStyledAttributes.getBoolean(14, true);
            }
            obtainStyledAttributes.recycle();
            ((UserManagerCompat$Api24Impl) ((ViewModelStore) this.NodeBrailler$ar$rules).ViewModelStore$ar$map).setEnabled(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void onTypefaceResult$ar$class_merging$ar$class_merging$ar$class_merging(OrderVerifyingClientCall.State state) {
        int i = state.type$ar$edu$88c656f2_0;
        if (i == 0) {
            Object obj = state.OrderVerifyingClientCall$State$ar$cancellationStatus;
            Object obj2 = this.NodeBrailler$ar$context;
            ((Handler) this.NodeBrailler$ar$rules).post(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3((NotificationManagerCompat.Api24Impl) obj2, (Typeface) obj, 16));
            return;
        }
        Object obj3 = this.NodeBrailler$ar$context;
        ((Handler) this.NodeBrailler$ar$rules).post(new CallbackWithHandler$2((NotificationManagerCompat.Api24Impl) obj3, i, 0));
    }

    public final NestedScrollingParentHelper popFromLayoutStep$ar$class_merging(RecyclerView.ViewHolder viewHolder, int i) {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord;
        NestedScrollingParentHelper nestedScrollingParentHelper;
        int indexOfKey = ((SimpleArrayMap) this.NodeBrailler$ar$rules).indexOfKey(viewHolder);
        if (indexOfKey >= 0 && (viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.NodeBrailler$ar$rules).valueAt(indexOfKey)) != null) {
            int i2 = viewInfoStore$InfoRecord.flags;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                viewInfoStore$InfoRecord.flags = i3;
                if (i == 4) {
                    nestedScrollingParentHelper = viewInfoStore$InfoRecord.preInfo$ar$class_merging;
                } else if (i == 8) {
                    nestedScrollingParentHelper = viewInfoStore$InfoRecord.postInfo$ar$class_merging;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    ((SimpleArrayMap) this.NodeBrailler$ar$rules).removeAt(indexOfKey);
                    ViewInfoStore$InfoRecord.recycle(viewInfoStore$InfoRecord);
                }
                return nestedScrollingParentHelper;
            }
        }
        return null;
    }

    public final void postFrameCallback(final Runnable runnable) {
        ((Choreographer) this.NodeBrailler$ar$context).postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.dynamicanimation.animation.AnimationHandler$FrameCallbackScheduler16$$ExternalSyntheticLambda0
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                runnable.run();
            }
        });
    }

    public final void removeFromDisappearedInLayout(RecyclerView.ViewHolder viewHolder) {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.NodeBrailler$ar$rules).get(viewHolder);
        if (viewInfoStore$InfoRecord == null) {
            return;
        }
        viewInfoStore$InfoRecord.flags &= -2;
    }

    public final void removeViewHolder(RecyclerView.ViewHolder viewHolder) {
        int size = ((LongSparseArray) this.NodeBrailler$ar$context).size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (viewHolder == ((LongSparseArray) this.NodeBrailler$ar$context).valueAt(size)) {
                LongSparseArray longSparseArray = (LongSparseArray) this.NodeBrailler$ar$context;
                Object[] objArr = longSparseArray.values;
                Object obj = objArr[size];
                Object obj2 = LongSparseArrayKt.DELETED;
                if (obj != obj2) {
                    objArr[size] = obj2;
                    longSparseArray.garbage = true;
                }
            }
        }
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.NodeBrailler$ar$rules).remove(viewHolder);
        if (viewInfoStore$InfoRecord != null) {
            ViewInfoStore$InfoRecord.recycle(viewInfoStore$InfoRecord);
        }
    }

    public final void setAllCaps(boolean z) {
        ((UserManagerCompat$Api24Impl) ((ViewModelStore) this.NodeBrailler$ar$rules).ViewModelStore$ar$map).setAllCaps(z);
    }

    public NodeBrailler(TextView textView) {
        this.NodeBrailler$ar$context = textView;
        this.NodeBrailler$ar$rules = new ViewModelStore(textView);
    }

    public NodeBrailler(Object obj, Object obj2) {
        this.NodeBrailler$ar$context = obj;
        this.NodeBrailler$ar$rules = obj2;
    }

    public NodeBrailler() {
        this.NodeBrailler$ar$context = Choreographer.getInstance();
        this.NodeBrailler$ar$rules = Looper.myLooper();
    }

    public NodeBrailler(byte[] bArr) {
        this.NodeBrailler$ar$rules = new SimpleArrayMap();
        this.NodeBrailler$ar$context = new LongSparseArray();
    }

    public NodeBrailler(EditText editText) {
        this.NodeBrailler$ar$rules = editText;
        EmojiTextWatcher emojiTextWatcher = new EmojiTextWatcher(editText);
        this.NodeBrailler$ar$context = emojiTextWatcher;
        editText.addTextChangedListener(emojiTextWatcher);
        editText.setEditableFactory(EmojiEditableFactory.getInstance());
    }

    public NodeBrailler(String str, String str2) {
        str.getClass();
        this.NodeBrailler$ar$context = str;
        this.NodeBrailler$ar$rules = str2;
    }

    public NodeBrailler(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        ArrayList arrayList = new ArrayList();
        this.NodeBrailler$ar$rules = arrayList;
        this.NodeBrailler$ar$context = context;
        arrayList.add(new ViewModelStore(shadowDelegateImpl));
    }
}
