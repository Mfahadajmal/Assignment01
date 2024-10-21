package com.google.android.libraries.performance.primes.metrics.core;

import android.content.Context;
import android.os.Build;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.common.collect.ImmutableSet;
import com.google.common.flogger.context.ContextDataProvider;
import dagger.Lazy;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricDispatcher {
    public final Object MetricDispatcher$ar$metricTransmittersSupplier;

    public MetricDispatcher(Context context) {
        this.MetricDispatcher$ar$metricTransmittersSupplier = context.getPackageName();
    }

    public static boolean isRobolectric() {
        return Objects.equals(Build.FINGERPRINT, "robolectric");
    }

    public final boolean appIsAllowlistedForOutOfOrderLifecycleEvents() {
        if (ImmutableSet.of((Object) "com.android.vending", (Object) "com.google.android.GoogleCamera", (Object) "com.google.android.GoogleCameraEng", (Object) "com.google.android.apps.docs", (Object) "com.google.android.apps.docs.editors.docs", (Object) "com.google.android.apps.docs.editors.sheets", (Object[]) new String[]{"com.google.android.apps.docs.editors.slides", "com.google.android.apps.geo.food.omniapp.nomni", "com.google.android.apps.gmail.testing.unit", "com.google.android.apps.gmm", "com.google.android.apps.gmm.ads.label.testing.app", "com.google.android.apps.gmm.search.map.testing.app", "com.google.android.apps.googlecamera.fishfood", "com.google.android.apps.jamkiosk", "com.google.android.apps.messaging", "com.google.android.apps.streetview.collector", "com.google.android.apps.tasks", "com.google.android.apps.tasks.ui.scuba", "com.google.android.apps.work.clouddpc", "com.google.android.apps.work.clouddpc.arc", "com.google.android.apps.youtube.creator", "com.google.android.apps.youtube.kids", "com.google.android.apps.youtube.mango", "com.google.android.apps.youtube.music", "com.google.android.apps.youtube.unplugged", "com.google.android.apps.youtube.vr", "com.google.android.apps.youtube.vr.oculus", "com.google.android.gms", "com.google.android.googlequicksearchbox", "com.google.android.inputmethod.latin", "com.google.android.inputmethod.latin.canary", "com.google.android.inputmethod.latin.dev", "com.google.android.play.games", "com.google.android.youtube", "com.google.android.youtube.oem", "com.google.android.youtube.test", "com.google.android.youtube.tv", "com.google.android.youtube.tvkids", "com.google.android.youtube.tvunplugged", "com.google.intelligence.sense.ambientmusic.functional.emulator", "com.google.intelligence.sense.ambientmusic.history.functional"}).contains(this.MetricDispatcher$ar$metricTransmittersSupplier)) {
            return true;
        }
        return ImmutableSet.of((Object) "com.google.android.apps.accessibility.reveal", (Object) "com.google.android.apps.diagnosticstool", (Object) "com.google.android.apps.dragonfly", (Object) "com.google.android.apps.dynamite", (Object) "com.google.android.apps.gmm.home.cards.yourexplore", (Object) "com.google.android.apps.internal.admobsdk.mediumtest.stability", (Object[]) new String[]{"com.google.android.apps.nbu.paisa.user.integration.home", "com.google.android.apps.nbu.paisa.user.integration.homescreen", "com.google.android.apps.nbu.paisa.user.integration.microapp", "com.google.android.apps.nbu.paisa.user.integration.qrcode", "com.google.android.apps.searchlite.homescreen", "com.google.android.flutter.testing.integrationtest.skeleton", "com.google.android.libraries.performance.primes.sample.profiling.application", "com.google.android.marvin.talkback", "com.google.android.street"}).contains(this.MetricDispatcher$ar$metricTransmittersSupplier);
    }

    public MetricDispatcher(Lazy lazy) {
        this.MetricDispatcher$ar$metricTransmittersSupplier = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(lazy, 4));
    }
}
