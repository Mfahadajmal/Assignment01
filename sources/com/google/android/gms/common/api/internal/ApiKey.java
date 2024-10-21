package com.google.android.gms.common.api.internal;

import _COROUTINE._BOUNDARY;
import com.google.android.gms.common.api.Api$ApiOptions;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApiKey {
    public final OptionalMethod api$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Api$ApiOptions apiOptions;
    private final String attributionTag;
    private final int hashCode;

    public ApiKey(OptionalMethod optionalMethod, Api$ApiOptions api$ApiOptions, String str) {
        this.api$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        this.apiOptions = api$ApiOptions;
        this.attributionTag = str;
        this.hashCode = Arrays.hashCode(new Object[]{optionalMethod, api$ApiOptions, str});
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiKey)) {
            return false;
        }
        ApiKey apiKey = (ApiKey) obj;
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.api$ar$class_merging$ar$class_merging$ar$class_merging, apiKey.api$ar$class_merging$ar$class_merging$ar$class_merging) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.apiOptions, apiKey.apiOptions) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.attributionTag, apiKey.attributionTag)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.hashCode;
    }
}
