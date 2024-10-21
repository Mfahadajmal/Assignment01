package com.google.android.libraries.consentverifier;

import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifier {
    public static final OnDeviceTextDetectionLoadLogEvent appInfoHelper$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
    private static final CollectionBasisManager collectionBasisManager = CollectionBasisManager.instance;
    private static final CollectionBasisVerifierDecider collectionBasisVerifierDecider = new FastCollectionBasisVerifierDecider();
}
