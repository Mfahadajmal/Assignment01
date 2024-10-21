package com.google.android.libraries.consentverifier.initializer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.libs.punchclock.threads.PoolableExecutors;
import com.google.android.gms.phenotype.PhenotypeClient;
import com.google.android.gms.phenotype.internal.IPhenotypeCallbacks;
import com.google.android.gms.phenotype.internal.IPhenotypeService;
import com.google.android.gms.phenotype.internal.PhenotypeClientImpl;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.android.libraries.consentverifier.threading.AppExecutorFactory;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Initializer {
    private static final String[] LOG_SOURCES = {"COLLECTION_BASIS_VERIFIER"};
    public static boolean flagInitialized = false;
    public static final Object initializerLock = new Object();

    public static void commitFlags$ar$class_merging$ar$class_merging(CollectionBasisContext collectionBasisContext, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        final Executor threadPoolExecutor;
        final PhenotypeClient phenotypeClient = new PhenotypeClient(collectionBasisContext.context);
        String valueOf = String.valueOf(collectionBasisContext.context.getPackageName());
        Context context = collectionBasisContext.context;
        if (onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode == null) {
            try {
                onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = Integer.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
                onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = -1;
            }
        }
        final String concat = "com.google.android.libraries.consentverifier#".concat(valueOf);
        final int intValue = ((Integer) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode).intValue();
        final String[] strArr = LOG_SOURCES;
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        builder.TaskApiCall$Builder$ar$execute = new RemoteCall() { // from class: com.google.android.gms.phenotype.PhenotypeClient$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((IPhenotypeService) ((PhenotypeClientImpl) obj).getService()).register$ar$ds(new IPhenotypeCallbacks.Stub((AppLifecycleMonitor) obj2), concat, intValue, strArr);
            }
        };
        Task doRead = phenotypeClient.doRead(builder.build());
        collectionBasisContext.executor.isPresent();
        if (SpannableUtils$NonCopyableTextSpan.isRunningInGmsCore(collectionBasisContext.context)) {
            SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = PoolableExecutors.instance$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory());
            threadPoolExecutor2.allowCoreThreadTimeOut(true);
            threadPoolExecutor = Executors.unconfigurableExecutorService(threadPoolExecutor2);
        } else {
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
            ImageInfo.Builder builder2 = new ImageInfo.Builder(null);
            builder2.setNameFormat$ar$ds("ConsentVerifierLibraryThread-%d");
            threadPoolExecutor = new ThreadPoolExecutor(0, 10, 10L, TimeUnit.SECONDS, linkedBlockingQueue, ImageInfo.Builder.doBuild$ar$class_merging(builder2), AppExecutorFactory.rejectedExecutionHandler);
        }
        try {
            doRead.addOnSuccessListener$ar$ds(threadPoolExecutor, new OnSuccessListener() { // from class: com.google.android.libraries.consentverifier.initializer.Initializer$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    Task forException;
                    boolean z = Initializer.flagInitialized;
                    PhenotypeClient phenotypeClient2 = PhenotypeClient.this;
                    int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.INSTANCE.isGooglePlayServicesAvailable(phenotypeClient2.context, 12451000);
                    String str = concat;
                    if (isGooglePlayServicesAvailable == 0) {
                        TaskApiCall.Builder builder3 = new TaskApiCall.Builder();
                        builder3.TaskApiCall$Builder$ar$execute = new ClearcutLoggerApiImpl$$ExternalSyntheticLambda2(str, 4);
                        forException = phenotypeClient2.doRead(builder3.build());
                    } else {
                        forException = SpannableUtils$NonCopyableTextSpan.forException(new ApiException(new Status(16)));
                    }
                    forException.addOnFailureListener$ar$ds$7efc8a85_0(threadPoolExecutor, new Initializer$$ExternalSyntheticLambda0(str, 0));
                }
            });
            doRead.addOnFailureListener$ar$ds$7efc8a85_0(threadPoolExecutor, new Initializer$$ExternalSyntheticLambda0(concat, 2));
        } catch (RejectedExecutionException e) {
            Log.w("CBVerifier", String.format("Execution failure when updating phenotypeflags for %s. %s", concat, e));
        }
    }
}
