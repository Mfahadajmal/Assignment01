package com.google.mlkit.vision.text;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TextRecognizer extends Closeable, LifecycleObserver, OptionalModuleApi {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void close();

    Task process(InputImage inputImage);
}
