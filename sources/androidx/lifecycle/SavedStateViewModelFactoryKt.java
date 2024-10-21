package androidx.lifecycle;

import _COROUTINE._BOUNDARY;
import android.app.Application;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SavedStateViewModelFactoryKt {
    public static final List ANDROID_VIEWMODEL_SIGNATURE = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.asList(new Class[]{Application.class, SavedStateHandle.class});
    public static final List VIEWMODEL_SIGNATURE = OnDeviceLanguageIdentificationLogEvent.listOf(SavedStateHandle.class);

    public static final Constructor findMatchingConstructor(Class cls, List list) {
        list.getClass();
        Constructor<?>[] constructors = cls.getConstructors();
        constructors.getClass();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            parameterTypes.getClass();
            List list2 = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.toList(parameterTypes);
            if (Intrinsics.areEqual(list, list2)) {
                constructor.getClass();
                return constructor;
            }
            if (list.size() == list2.size() && list2.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final ViewModel newInstance(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (ViewModel) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            Objects.toString(cls);
            throw new RuntimeException("Failed to access ".concat(cls.toString()), e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(cls, "A ", " cannot be instantiated."), e2);
        } catch (InvocationTargetException e3) {
            Objects.toString(cls);
            throw new RuntimeException("An exception happened in constructor of ".concat(cls.toString()), e3.getCause());
        }
    }
}
