package com.google.android.libraries.consentverifier.consents;

import android.graphics.Bitmap;
import androidx.activity.OnBackPressedDispatcher;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.consentverifier.initializer.Initializer$$ExternalSyntheticLambda0;
import com.google.mlkit.logging.schema.AccelerationAllowlistLogEvent;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConsentRetrieverImpl {
    public static final ConsentRetrieverImpl instance = new ConsentRetrieverImpl();
    private final CollectionBasisResolverHolders[] collectionBasisToResolversMap;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CollectionBasisResolverHolders {
        volatile Object ConsentRetrieverImpl$CollectionBasisResolverHolders$ar$resolverHolders;

        public CollectionBasisResolverHolders() {
        }

        /* JADX WARN: Type inference failed for: r5v5, types: [com.google.mlkit.vision.text.TextRecognizer, java.lang.Object] */
        public final Object run(Bitmap bitmap, Continuation continuation) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            if (!bitmap.isRecycled()) {
                if (this.ConsentRetrieverImpl$CollectionBasisResolverHolders$ar$resolverHolders == null) {
                    this.ConsentRetrieverImpl$CollectionBasisResolverHolders$ar$resolverHolders = AccelerationAllowlistLogEvent.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
                }
                ?? r5 = this.ConsentRetrieverImpl$CollectionBasisResolverHolders$ar$resolverHolders;
                if (r5 != 0) {
                    Task process = r5.process(InputImage.fromBitmap$ar$ds(bitmap));
                    final OnBackPressedDispatcher.AnonymousClass1 anonymousClass1 = new OnBackPressedDispatcher.AnonymousClass1(cancellableContinuationImpl, 15);
                    process.addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.accessibility.selecttospeak.feedback.ScreenOcrControllerImpl$sam$com_google_android_gms_tasks_OnSuccessListener$0
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final /* synthetic */ void onSuccess(Object obj) {
                            Function1.this.invoke(obj);
                        }
                    }).addOnFailureListener$ar$ds(new Initializer$$ExternalSyntheticLambda0(cancellableContinuationImpl, 1));
                }
                Object result = cancellableContinuationImpl.getResult();
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                return result;
            }
            throw new IllegalStateException("Bitmap already recycled");
        }

        public /* synthetic */ CollectionBasisResolverHolders(byte[] bArr, byte[] bArr2) {
            this.ConsentRetrieverImpl$CollectionBasisResolverHolders$ar$resolverHolders = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x003f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0042. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0045. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004c A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0060 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private ConsentRetrieverImpl() {
        /*
            r8 = this;
            r8.<init>()
            int[] r0 = com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasis.values$ar$edu$b46a8d2b_0()
            int r1 = r0.length
            r2 = 0
            r3 = r2
            r4 = r3
        Lb:
            r5 = 0
            if (r3 >= r1) goto L1c
            r6 = r0[r3]
            int r7 = r6 + (-1)
            if (r6 == 0) goto L1b
            int r4 = java.lang.Math.max(r4, r7)
            int r3 = r3 + 1
            goto Lb
        L1b:
            throw r5
        L1c:
            int r4 = r4 + 1
            com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl$CollectionBasisResolverHolders[] r0 = new com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl.CollectionBasisResolverHolders[r4]
            r8.collectionBasisToResolversMap = r0
            int[] r0 = com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasis.values$ar$edu$b46a8d2b_0()
            int r1 = r0.length
        L27:
            if (r2 >= r1) goto L62
            r3 = r0[r2]
            int r4 = r3 + (-1)
            if (r3 == 0) goto L61
            r6 = 17
            if (r4 == r6) goto L4c
            r6 = 27
            if (r4 == r6) goto L4c
            r6 = 78
            if (r4 == r6) goto L4c
            r6 = 82
            if (r4 == r6) goto L4c
            switch(r4) {
                case 12: goto L4c;
                case 13: goto L4c;
                case 14: goto L4c;
                case 15: goto L4c;
                default: goto L42;
            }
        L42:
            switch(r4) {
                case 19: goto L4c;
                case 20: goto L4c;
                case 21: goto L4c;
                default: goto L45;
            }
        L45:
            switch(r4) {
                case 31: goto L4c;
                case 32: goto L4c;
                case 33: goto L4c;
                case 34: goto L4c;
                case 35: goto L4c;
                case 36: goto L4c;
                case 37: goto L4c;
                case 38: goto L4c;
                case 39: goto L4c;
                case 40: goto L4c;
                case 41: goto L4c;
                case 42: goto L4c;
                case 43: goto L4c;
                case 44: goto L4c;
                case 45: goto L4c;
                case 46: goto L4c;
                case 47: goto L4c;
                case 48: goto L4c;
                case 49: goto L4c;
                default: goto L48;
            }
        L48:
            switch(r4) {
                case 56: goto L4c;
                case 57: goto L4c;
                case 58: goto L4c;
                case 59: goto L4c;
                case 60: goto L4c;
                case 61: goto L4c;
                case 62: goto L4c;
                case 63: goto L4c;
                case 64: goto L4c;
                case 65: goto L4c;
                case 66: goto L4c;
                case 67: goto L4c;
                case 68: goto L4c;
                case 69: goto L4c;
                case 70: goto L4c;
                case 71: goto L4c;
                case 72: goto L4c;
                case 73: goto L4c;
                default: goto L4b;
            }
        L4b:
            goto L5d
        L4c:
            com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl$CollectionBasisResolverHolders[] r6 = r8.collectionBasisToResolversMap
            if (r3 == 0) goto L60
            com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl$CollectionBasisResolverHolders r3 = new com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl$CollectionBasisResolverHolders
            r3.<init>()
            r6[r4] = r3
            int r4 = com.google.common.collect.ImmutableList.ImmutableList$ar$NoOp
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.RegularImmutableList.EMPTY
            r3.ConsentRetrieverImpl$CollectionBasisResolverHolders$ar$resolverHolders = r4
        L5d:
            int r2 = r2 + 1
            goto L27
        L60:
            throw r5
        L61:
            throw r5
        L62:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl.<init>():void");
    }
}
