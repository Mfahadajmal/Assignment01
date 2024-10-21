package kotlinx.coroutines;

import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
import java.io.Closeable;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.ContinuationInterceptor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Key extends AbstractCoroutineContextKey {
        public Key(byte[] bArr) {
            super(ContinuationInterceptor.Key, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$7f78ac82_0);
        }

        public Key() {
            super(CoroutineDispatcher.Key$ar$class_merging$80195e29_0, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$12d1b183_0);
        }
    }

    static {
        new Key();
    }
}
