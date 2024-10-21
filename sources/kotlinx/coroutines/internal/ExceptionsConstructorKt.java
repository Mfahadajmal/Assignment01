package kotlinx.coroutines.internal;

import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
import com.google.android.accessibility.selecttospeak.popup.S2SPopupParsedIntentKt$parseIntent$text$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.Result;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExceptionsConstructorKt {
    private static final CtorCache ctorCache;
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

    static {
        CtorCache ctorCache2;
        try {
            if (FastServiceLoaderKt.ANDROID_DETECTED) {
                ctorCache2 = WeakMapCtorCache.INSTANCE;
            } else {
                ctorCache2 = ClassValueCtorCache.INSTANCE;
            }
        } catch (Throwable unused) {
            ctorCache2 = WeakMapCtorCache.INSTANCE;
        }
        ctorCache = ctorCache2;
    }

    public static final Function1 createConstructor(Class cls) {
        Object obj;
        int i;
        Pair pair;
        S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 s2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 = S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$a2eeaa1f_0;
        if (throwableFields != fieldsCountOrDefault(cls, 0)) {
            return s2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
        }
        Constructor<?>[] constructors = cls.getConstructors();
        int length = constructors.length;
        ArrayList arrayList = new ArrayList(length);
        int i2 = 0;
        while (true) {
            obj = null;
            if (i2 >= length) {
                break;
            }
            Constructor<?> constructor = constructors[i2];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            int length2 = parameterTypes.length;
            if (length2 != 0) {
                if (length2 != 1) {
                    if (length2 != 2) {
                        pair = new Pair(null, -1);
                    } else if (Intrinsics.areEqual(parameterTypes[0], String.class) && Intrinsics.areEqual(parameterTypes[1], Throwable.class)) {
                        pair = new Pair(new S2SPopupParsedIntentKt$parseIntent$text$1(new S2SPopupParsedIntentKt$parseIntent$text$1(constructor, 14), 18), 3);
                    } else {
                        pair = new Pair(null, -1);
                    }
                } else {
                    Class<?> cls2 = parameterTypes[0];
                    if (Intrinsics.areEqual(cls2, String.class)) {
                        pair = new Pair(new S2SPopupParsedIntentKt$parseIntent$text$1(new S2SPopupParsedIntentKt$parseIntent$text$1(constructor, 15), 18), 2);
                    } else if (Intrinsics.areEqual(cls2, Throwable.class)) {
                        pair = new Pair(new S2SPopupParsedIntentKt$parseIntent$text$1(new S2SPopupParsedIntentKt$parseIntent$text$1(constructor, 16), 18), 1);
                    } else {
                        pair = new Pair(null, -1);
                    }
                }
            } else {
                pair = new Pair(new S2SPopupParsedIntentKt$parseIntent$text$1(new S2SPopupParsedIntentKt$parseIntent$text$1(constructor, 17), 18), 0);
            }
            arrayList.add(pair);
            i2++;
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            obj = it.next();
            if (it.hasNext()) {
                int intValue = ((Number) ((Pair) obj).second).intValue();
                while (true) {
                    Object next = it.next();
                    int intValue2 = ((Number) ((Pair) next).second).intValue();
                    if (intValue < intValue2) {
                        i = intValue2;
                    } else {
                        i = intValue;
                    }
                    if (intValue < intValue2) {
                        obj = next;
                    }
                    if (!it.hasNext()) {
                        break;
                    }
                    intValue = i;
                }
            }
        }
        Pair pair2 = (Pair) obj;
        if (pair2 != null) {
            Function1 function1 = (Function1) pair2.first;
            if (function1 == null) {
                return s2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
            }
            return function1;
        }
        return s2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
    }

    private static final int fieldsCountOrDefault(Class cls, int i) {
        Object createFailure;
        OnDeviceShadowRemovalLogEvent.getKotlinClass(cls);
        int i2 = 0;
        do {
            try {
                int i3 = 0;
                for (Field field : cls.getDeclaredFields()) {
                    if (!Modifier.isStatic(field.getModifiers())) {
                        i3++;
                    }
                }
                i2 += i3;
                cls = cls.getSuperclass();
            } catch (Throwable th) {
                createFailure = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th);
            }
        } while (cls != null);
        createFailure = Integer.valueOf(i2);
        Object valueOf = Integer.valueOf(i);
        if (true == (createFailure instanceof Result.Failure)) {
            createFailure = valueOf;
        }
        return ((Number) createFailure).intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Throwable tryCopyException(Throwable th) {
        Object createFailure;
        if (th instanceof CopyableThrowable) {
            try {
                createFailure = ((CopyableThrowable) th).createCopy();
            } catch (Throwable th2) {
                createFailure = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th2);
            }
            if (true == (createFailure instanceof Result.Failure)) {
                createFailure = null;
            }
            return (Throwable) createFailure;
        }
        return (Throwable) ctorCache.get(th.getClass()).invoke(th);
    }
}
