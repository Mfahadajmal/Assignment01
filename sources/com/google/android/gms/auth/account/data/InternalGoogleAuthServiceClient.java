package com.google.android.gms.auth.account.data;

import android.content.Context;
import android.os.Looper;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalGoogleAuthServiceClient extends GoogleApi {
    private static final OptionalMethod API$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan clientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public static final WindowTrackerFactory logger$ar$class_merging$adff595e_0$ar$class_merging;

    static {
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = new SpannableUtils$IdentifierSpan((byte[]) null, (char[]) null);
        CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan;
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan2 = new SpannableUtils$IdentifierSpan() { // from class: com.google.android.gms.auth.account.data.InternalGoogleAuthServiceClient.1
            @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
            public final /* synthetic */ Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new GoogleAuthServiceClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        clientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan2;
        API$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod("GoogleAuthService.API", spannableUtils$IdentifierSpan2, spannableUtils$IdentifierSpan, (char[]) null);
        logger$ar$class_merging$adff595e_0$ar$class_merging = new WindowTrackerFactory("GoogleAuthServiceClient");
    }

    public InternalGoogleAuthServiceClient(Context context) {
        super(context, API$ar$class_merging$ar$class_merging$ar$class_merging, Api$ApiOptions.NoOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
