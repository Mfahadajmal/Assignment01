package com.google.mlkit.logging.schema;

import androidx.preference.Preference;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ConflatedBufferedChannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ShadowRemovalOptionalModuleLogEvent {
    public static /* synthetic */ Channel Channel$default$ar$ds$ar$edu(int i, int i2, Function1 function1, int i3) {
        ConflatedBufferedChannel conflatedBufferedChannel;
        if ((i3 & 2) != 0) {
            i2 = BufferOverflow.SUSPEND$ar$edu;
        }
        if (1 == (i3 & 1)) {
            i = 0;
        }
        if (i != -2) {
            if (i != -1) {
                if (i != 0) {
                    if (i != Integer.MAX_VALUE) {
                        if (i2 == BufferOverflow.SUSPEND$ar$edu) {
                            return new BufferedChannel(i, null);
                        }
                        return new ConflatedBufferedChannel(i, i2, null);
                    }
                    return new BufferedChannel(Preference.DEFAULT_ORDER, null);
                }
                if (i2 == BufferOverflow.SUSPEND$ar$edu) {
                    return new BufferedChannel(0, null);
                }
                conflatedBufferedChannel = new ConflatedBufferedChannel(1, i2, null);
            } else {
                if (i2 == BufferOverflow.SUSPEND$ar$edu) {
                    return new ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST$ar$edu, null);
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
            }
        } else {
            if (i2 == BufferOverflow.SUSPEND$ar$edu) {
                return new BufferedChannel(Channel.Factory.CHANNEL_DEFAULT_CAPACITY, null);
            }
            conflatedBufferedChannel = new ConflatedBufferedChannel(1, i2, null);
        }
        return conflatedBufferedChannel;
    }
}
