package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
enum Maps$EntryFunction implements Function {
    KEY,
    VALUE;

    @Override // com.google.common.base.Function
    public final /* synthetic */ Object apply(Object obj) {
        int ordinal = ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return ((Map.Entry) obj).getValue();
            }
            throw null;
        }
        return ((Map.Entry) obj).getKey();
    }
}
