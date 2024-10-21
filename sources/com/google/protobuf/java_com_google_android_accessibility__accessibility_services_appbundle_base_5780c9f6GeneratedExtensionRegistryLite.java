package com.google.protobuf;

import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshot;
import com.google.android.libraries.vision.visionkit.pipeline.VisualAnnotationResults;
import com.google.protobuf.GeneratedMessageLite;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
final class java_com_google_android_accessibility__accessibility_services_appbundle_base_5780c9f6GeneratedExtensionRegistryLite extends ExtensionRegistryLite {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Loader extends GeneratedExtensionRegistryLoader {
        @Override // com.google.protobuf.GeneratedExtensionRegistryLoader
        protected final ExtensionRegistryLite getInstance() {
            return new java_com_google_android_accessibility__accessibility_services_appbundle_base_5780c9f6GeneratedExtensionRegistryLite();
        }
    }

    public java_com_google_android_accessibility__accessibility_services_appbundle_base_5780c9f6GeneratedExtensionRegistryLite() {
        super(null);
    }

    @Override // com.google.protobuf.ExtensionRegistryLite
    public final GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite messageLite, int i) {
        char c;
        String name = messageLite.getClass().getName();
        int hashCode = name.hashCode();
        if (hashCode != -561616335) {
            if (hashCode != 944304276) {
                if (hashCode == 1754514263 && name.equals("logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension")) {
                    c = 0;
                }
                c = 65535;
            } else {
                if (name.equals("com.google.android.libraries.vision.visionkit.pipeline.Results")) {
                    c = 2;
                }
                c = 65535;
            }
        } else {
            if (name.equals("com.google.android.libraries.performance.primes.transmitter.MetricSnapshot")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c == 2 && i == 242663235) {
                    return VisualAnnotationResults.ext;
                }
                return null;
            }
            if (i == 334728578) {
                return ClearcutMetricSnapshot.clearcutMetricSnapshot;
            }
            return null;
        }
        if (i != 28) {
            return null;
        }
        return ExtensionTalkback$TalkbackExtension.metricExtension;
    }
}
