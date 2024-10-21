package com.google.android.accessibility.brailleime.input;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.input.Swipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayDeque;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoPerformer extends Handler {
    private final FloatingActionButton.ShadowDelegateImpl callback$ar$class_merging$dd4840dc_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public ArrayDeque characters;
    public final Context context;
    public int interCharacterDelay;
    public int interWordDelay;

    public AutoPerformer(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.context = context;
        this.callback$ar$class_merging$dd4840dc_0$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        BrailleInputPlaneResult createTapAndRelease;
        int i;
        if (!this.characters.isEmpty()) {
            BrailleCharacter brailleCharacter = (BrailleCharacter) this.characters.removeFirst();
            if (brailleCharacter.isEmpty()) {
                createTapAndRelease = ((BrailleInputView) this.callback$ar$class_merging$dd4840dc_0$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).inputPlane.createSwipe(new Swipe(Swipe.Direction.LEFT, 1));
                i = this.interWordDelay;
            } else {
                createTapAndRelease = BrailleInputPlaneResult.createTapAndRelease(brailleCharacter);
                i = this.interCharacterDelay;
            }
            this.callback$ar$class_merging$dd4840dc_0$ar$class_merging$ar$class_merging$ar$class_merging.onPerform(createTapAndRelease);
            sendEmptyMessageDelayed(0, i);
            return;
        }
        ((BrailleInputView) this.callback$ar$class_merging$dd4840dc_0$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).autoPerformer = null;
    }
}
