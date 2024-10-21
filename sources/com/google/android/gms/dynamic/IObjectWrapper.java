package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.lang.reflect.Field;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IObjectWrapper extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IObjectWrapper {
        private final Object wrappedObject;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Proxy extends BaseProxy implements IObjectWrapper {
            public Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamic.IObjectWrapper");
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static Object unwrap(IObjectWrapper iObjectWrapper) {
            if (iObjectWrapper instanceof Stub) {
                return ((Stub) iObjectWrapper).wrappedObject;
            }
            IBinder asBinder = iObjectWrapper.asBinder();
            Field[] declaredFields = asBinder.getClass().getDeclaredFields();
            Field field = null;
            int i = 0;
            for (Field field2 : declaredFields) {
                if (!field2.isSynthetic()) {
                    i++;
                    field = field2;
                }
            }
            if (i == 1) {
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(field);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                    try {
                        return field.get(asBinder);
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException("Could not access the field in remoteBinder.", e);
                    } catch (NullPointerException e2) {
                        throw new IllegalArgumentException("Binder object is null.", e2);
                    }
                }
                throw new IllegalArgumentException("IObjectWrapper declared field not private!");
            }
            throw new IllegalArgumentException("Unexpected number of IObjectWrapper declared fields: " + declaredFields.length);
        }

        public Stub(Object obj) {
            this();
            this.wrappedObject = obj;
        }
    }
}
