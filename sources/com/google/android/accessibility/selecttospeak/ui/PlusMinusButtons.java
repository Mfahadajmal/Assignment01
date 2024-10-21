package com.google.android.accessibility.selecttospeak.ui;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.ComponentActivity$fullyDrawnReporter$2$1;
import androidx.room.InvalidationTracker$Observer;
import androidx.room.RoomDatabase;
import androidx.room.TriggerBasedInvalidationTracker;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PlusMinusButtons {
    public final Object PlusMinusButtons$ar$canMinus;
    public final Object PlusMinusButtons$ar$canPlus;
    public final Object PlusMinusButtons$ar$getIndex;
    public Object PlusMinusButtons$ar$indexTextView;
    public final Object PlusMinusButtons$ar$indexTextViewFactory;
    private Object PlusMinusButtons$ar$minusButton;
    public final Object PlusMinusButtons$ar$minusButtonFactory;
    public final Object PlusMinusButtons$ar$onMinus;
    public final Object PlusMinusButtons$ar$onPlus;
    public Object PlusMinusButtons$ar$plusButton;
    private final Object PlusMinusButtons$ar$plusButtonFactory;

    public PlusMinusButtons(Function0 function0, Function0 function02, Function0 function03, Function0 function04, Function0 function05, Function1 function1, Function1 function12, Function1 function13) {
        this.PlusMinusButtons$ar$canPlus = function0;
        this.PlusMinusButtons$ar$canMinus = function02;
        this.PlusMinusButtons$ar$onPlus = function03;
        this.PlusMinusButtons$ar$onMinus = function04;
        this.PlusMinusButtons$ar$getIndex = function05;
        this.PlusMinusButtons$ar$plusButtonFactory = function1;
        this.PlusMinusButtons$ar$indexTextViewFactory = function12;
        this.PlusMinusButtons$ar$minusButtonFactory = function13;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    public final void setup(ViewGroup viewGroup) {
        Button button = (Button) this.PlusMinusButtons$ar$plusButtonFactory.invoke(viewGroup);
        TextView textView = null;
        if (button != null) {
            button.setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 10));
        } else {
            button = null;
        }
        this.PlusMinusButtons$ar$plusButton = button;
        Button button2 = (Button) this.PlusMinusButtons$ar$minusButtonFactory.invoke(viewGroup);
        if (button2 != null) {
            button2.setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 11));
        } else {
            button2 = null;
        }
        this.PlusMinusButtons$ar$minusButton = button2;
        TextView textView2 = (TextView) this.PlusMinusButtons$ar$indexTextViewFactory.invoke(viewGroup);
        if (textView2 != null) {
            textView2.setText(String.valueOf(((Number) this.PlusMinusButtons$ar$getIndex.invoke()).intValue()));
            textView = textView2;
        }
        this.PlusMinusButtons$ar$indexTextView = textView;
        updateUI();
    }

    public final void stopMultiInstanceInvalidation() {
        this.PlusMinusButtons$ar$indexTextView = null;
    }

    public final Object sync$room_runtime_release(Continuation continuation) {
        Object syncTriggers$room_runtime_release;
        if ((!((RoomDatabase) this.PlusMinusButtons$ar$canMinus).inCompatibilityMode$room_runtime_release() || ((RoomDatabase) this.PlusMinusButtons$ar$canMinus).isOpenInternal()) && (syncTriggers$room_runtime_release = ((TriggerBasedInvalidationTracker) this.PlusMinusButtons$ar$canPlus).syncTriggers$room_runtime_release(continuation)) == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return syncTriggers$room_runtime_release;
        }
        return Unit.INSTANCE;
    }

    public final Object unsubscribe(InvalidationTracker$Observer invalidationTracker$Observer, Continuation continuation) {
        Object removeObserver$room_runtime_release = ((TriggerBasedInvalidationTracker) this.PlusMinusButtons$ar$canPlus).removeObserver$room_runtime_release(invalidationTracker$Observer, continuation);
        if (removeObserver$room_runtime_release == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return removeObserver$room_runtime_release;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    public final void updateUI() {
        Object obj = this.PlusMinusButtons$ar$plusButton;
        if (obj != null) {
            ((Button) obj).setEnabled(((Boolean) this.PlusMinusButtons$ar$canPlus.invoke()).booleanValue());
        }
        Object obj2 = this.PlusMinusButtons$ar$minusButton;
        if (obj2 != null) {
            ((Button) obj2).setEnabled(((Boolean) this.PlusMinusButtons$ar$canMinus.invoke()).booleanValue());
        }
        Object obj3 = this.PlusMinusButtons$ar$indexTextView;
        if (obj3 == null) {
            return;
        }
        ((TextView) obj3).setText(String.valueOf(((Number) this.PlusMinusButtons$ar$getIndex.invoke()).intValue()));
    }

    public PlusMinusButtons(RoomDatabase roomDatabase, Map map, Map map2, String... strArr) {
        this.PlusMinusButtons$ar$canMinus = roomDatabase;
        this.PlusMinusButtons$ar$onMinus = map;
        this.PlusMinusButtons$ar$onPlus = map2;
        this.PlusMinusButtons$ar$plusButtonFactory = strArr;
        TriggerBasedInvalidationTracker triggerBasedInvalidationTracker = new TriggerBasedInvalidationTracker(roomDatabase, map, map2, strArr);
        this.PlusMinusButtons$ar$canPlus = triggerBasedInvalidationTracker;
        this.PlusMinusButtons$ar$minusButtonFactory = new ComponentActivity$fullyDrawnReporter$2$1(this, 20);
        this.PlusMinusButtons$ar$indexTextViewFactory = new ComponentActivity$fullyDrawnReporter$2$1(this, 19);
        Collections.newSetFromMap(new IdentityHashMap()).getClass();
        this.PlusMinusButtons$ar$getIndex = new Object();
        triggerBasedInvalidationTracker.onAllowRefresh = new ComponentActivity$fullyDrawnReporter$2$1(this, 18);
    }
}
