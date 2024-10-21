package com.google.android.accessibility.talkback.compositor.hint.tv;

import android.content.Context;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.hint.ClickableHint;
import com.google.android.accessibility.talkback.compositor.hint.LongClickableHint;
import com.google.android.accessibility.talkback.compositor.hint.NodeRoleHint;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeRoleHintForTV extends NodeRoleHint {
    public final ClickableHint clickableHint;
    public final LongClickableHint longClickableHint;

    public NodeRoleHintForTV(Context context, GlobalVariables globalVariables) {
        super(context, globalVariables);
        this.clickableHint = new ClickableHintForTV(context, globalVariables);
        this.longClickableHint = new LongClickableHintForTV(context, globalVariables);
    }
}
