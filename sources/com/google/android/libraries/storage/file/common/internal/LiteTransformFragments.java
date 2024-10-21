package com.google.android.libraries.storage.file.common.internal;

import com.google.common.base.Joiner;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LiteTransformFragments {
    public static final Pattern XFORM_NAME_PATTERN = Pattern.compile("(\\w+).*");

    public static String joinTransformSpecs(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return "transform=".concat(String.valueOf(new Joiner("+").join(list)));
    }
}
