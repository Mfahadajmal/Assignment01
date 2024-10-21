package com.google.mlkit.logging.schema;

import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import io.grpc.Configurator;
import io.grpc.InternalChannelz;
import io.grpc.ManagedChannelBuilder;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.logging.Level;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceImageCaptioningCreateLogEvent {
    public OnDeviceImageCaptioningCreateLogEvent(SSLSession sSLSession) {
        sSLSession.getCipherSuite();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            Certificate certificate = localCertificates[0];
        }
        try {
            Certificate[] peerCertificates = sSLSession.getPeerCertificates();
            if (peerCertificates != null) {
                Certificate certificate2 = peerCertificates[0];
            }
        } catch (SSLPeerUnverifiedException e) {
            InternalChannelz.log.logp(Level.FINE, "io.grpc.InternalChannelz$Tls", "<init>", String.format("Peer cert not available for peerHost=%s", sSLSession.getPeerHost()), (Throwable) e);
        }
    }

    public static void configureChannelBuilder(ManagedChannelBuilder managedChannelBuilder) {
        Iterator it = MultiFlavorDetectorCreator.getDefaultRegistry$ar$class_merging().getConfigurators().iterator();
        while (it.hasNext()) {
            ((Configurator) it.next()).configureChannelBuilder$ar$ds();
        }
    }
}
