package com.google.android.libraries.storage.file.backends;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.libraries.storage.file.common.MalformedUriException;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FileUriAdapter {
    public static final FileUriAdapter INSTANCE = new FileUriAdapter();

    private FileUriAdapter() {
    }

    public static final File toFile$ar$ds(Uri uri) {
        if (uri.getScheme().equals("file")) {
            if (TextUtils.isEmpty(uri.getQuery())) {
                if (TextUtils.isEmpty(uri.getAuthority())) {
                    return new File(uri.getPath());
                }
                throw new MalformedUriException("Did not expect uri to have authority");
            }
            throw new MalformedUriException("Did not expect uri to have query");
        }
        throw new MalformedUriException("Scheme must be 'file'");
    }
}
