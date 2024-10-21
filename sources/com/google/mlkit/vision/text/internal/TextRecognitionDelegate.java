package com.google.mlkit.vision.text.internal;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
interface TextRecognitionDelegate {
    void load();

    void release();

    MultiFlavorDetectorCreator run$ar$class_merging(InputImage inputImage);
}
