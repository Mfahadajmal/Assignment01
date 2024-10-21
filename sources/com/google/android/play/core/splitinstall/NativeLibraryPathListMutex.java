package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.google.android.play.core.splitcompat.BackgroundExecutor$1;
import com.google.android.play.core.splitcompat.reflectutils.SplitCompatReflectionException;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.mlkit.logging.schema.SystemInfo;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.DesugarCollections;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NativeLibraryPathListMutex {
    private static SystemInfo component$ar$class_merging$ar$class_merging;
    private static ThreadPoolExecutor instance;

    public NativeLibraryPathListMutex() {
    }

    private static Field findField(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new SplitCompatReflectionException(String.format("Failed to find a field named %s on an object of instance %s", str, obj.getClass().getName()));
    }

    public static Method findMethod(Object obj, String str, Class... clsArr) {
        return findMethodInClass(obj.getClass(), str, clsArr);
    }

    public static Method findMethodInClass(Class cls, String str, Class... clsArr) {
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            try {
                Method declaredMethod = cls2.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new SplitCompatReflectionException(String.format("Could not find a method named %s with parameters %s in type %s", str, Arrays.asList(clsArr), cls));
    }

    public static Executor get() {
        if (instance == null) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new BackgroundExecutor$1(0));
            instance = threadPoolExecutor;
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        return instance;
    }

    public static Context getApplicationContext(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            return applicationContext;
        }
        return context;
    }

    public static OptionalMethod getArrayField$ar$class_merging$ar$class_merging$ar$class_merging(Object obj, String str, Class cls) {
        return new OptionalMethod(obj, findField(obj, str), cls, (byte[]) null);
    }

    public static synchronized SystemInfo getComponent$ar$class_merging$ar$class_merging(Context context) {
        SystemInfo systemInfo;
        synchronized (NativeLibraryPathListMutex.class) {
            if (component$ar$class_merging$ar$class_merging == null) {
                OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
                onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = new SplitInstallModule(getApplicationContext(context));
                OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode, SplitInstallModule.class);
                component$ar$class_merging$ar$class_merging = new SystemInfo((SplitInstallModule) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode);
            }
            systemInfo = component$ar$class_merging$ar$class_merging;
        }
        return systemInfo;
    }

    public static OptionalMethod getField$ar$class_merging$b104d96d_0$ar$class_merging(Object obj, String str, Class cls) {
        return new OptionalMethod(obj, findField(obj, str), cls);
    }

    public static String getSplitIdFromFile(File file) {
        if (file.getName().endsWith(".apk")) {
            String replaceFirst = file.getName().replaceFirst("(_\\d+)?\\.apk", "");
            if (replaceFirst.equals("base-master") || replaceFirst.equals("base-main")) {
                return "";
            }
            if (replaceFirst.startsWith("base-")) {
                return replaceFirst.replace("base-", "config.");
            }
            return replaceFirst.replace("-", ".config.").replace(".config.master", "").replace(".config.main", "");
        }
        throw new IllegalArgumentException("Non-apk found in splits directory.");
    }

    public static Object invokeMethod(Object obj, String str, Class cls, Class cls2, Object obj2) {
        try {
            return cls.cast(findMethod(obj, str, cls2).invoke(obj, obj2));
        } catch (Exception e) {
            throw new SplitCompatReflectionException(String.format("Failed to invoke method %s on an object of type %s", str, obj.getClass()), e);
        }
    }

    public static final Object[] makeDexElements$ar$ds(Object obj, ArrayList arrayList, File file, ArrayList arrayList2) {
        try {
            return (Object[]) Object[].class.cast(findMethod(obj, "makePathElements", List.class, File.class, List.class).invoke(obj, arrayList, file, arrayList2));
        } catch (Exception e) {
            throw new SplitCompatReflectionException(String.format("Failed to invoke method %s on an object of type %s", "makePathElements", obj.getClass()), e);
        }
    }

    public static final Object[] makePathElements$ar$ds(Object obj, List list) {
        return (Object[]) invokeMethod(obj, "makePathElements", Object[].class, List.class, list);
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.util.Map, java.lang.Object] */
    public static final SplitInstallModule parse$ar$objectUnboxing$ar$class_merging$ar$class_merging(XmlPullParser xmlPullParser, SplitInstallModule splitInstallModule) {
        while (xmlPullParser.next() != 1) {
            try {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("splits")) {
                        while (xmlPullParser.next() != 3) {
                            if (xmlPullParser.getEventType() == 2) {
                                if (xmlPullParser.getName().equals("module")) {
                                    String readAttribute$ar$objectUnboxing$ar$ds = readAttribute$ar$objectUnboxing$ar$ds("name", xmlPullParser);
                                    if (readAttribute$ar$objectUnboxing$ar$ds != null) {
                                        while (xmlPullParser.next() != 3) {
                                            if (xmlPullParser.getEventType() == 2) {
                                                if (xmlPullParser.getName().equals("language")) {
                                                    while (xmlPullParser.next() != 3) {
                                                        if (xmlPullParser.getEventType() == 2) {
                                                            if (xmlPullParser.getName().equals("entry")) {
                                                                String readAttribute$ar$objectUnboxing$ar$ds2 = readAttribute$ar$objectUnboxing$ar$ds("key", xmlPullParser);
                                                                String readAttribute$ar$objectUnboxing$ar$ds3 = readAttribute$ar$objectUnboxing$ar$ds("split", xmlPullParser);
                                                                skipTag$ar$objectUnboxing$ar$ds(xmlPullParser);
                                                                if (readAttribute$ar$objectUnboxing$ar$ds2 != null && readAttribute$ar$objectUnboxing$ar$ds3 != null) {
                                                                    if (!splitInstallModule.SplitInstallModule$ar$context.containsKey(readAttribute$ar$objectUnboxing$ar$ds2)) {
                                                                        splitInstallModule.SplitInstallModule$ar$context.put(readAttribute$ar$objectUnboxing$ar$ds2, new HashMap());
                                                                    }
                                                                    ((Map) splitInstallModule.SplitInstallModule$ar$context.get(readAttribute$ar$objectUnboxing$ar$ds2)).put(readAttribute$ar$objectUnboxing$ar$ds, readAttribute$ar$objectUnboxing$ar$ds3);
                                                                }
                                                            } else {
                                                                skipTag$ar$objectUnboxing$ar$ds(xmlPullParser);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    skipTag$ar$objectUnboxing$ar$ds(xmlPullParser);
                                                }
                                            }
                                        }
                                    } else {
                                        skipTag$ar$objectUnboxing$ar$ds(xmlPullParser);
                                    }
                                } else {
                                    skipTag$ar$objectUnboxing$ar$ds(xmlPullParser);
                                }
                            }
                        }
                    } else {
                        skipTag$ar$objectUnboxing$ar$ds(xmlPullParser);
                    }
                }
            } catch (IOException | IllegalStateException | XmlPullParserException e) {
                Log.e("SplitInstall", "Error while parsing splits.xml", e);
                return null;
            }
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : splitInstallModule.SplitInstallModule$ar$context.entrySet()) {
            hashMap.put((String) entry.getKey(), DesugarCollections.unmodifiableMap(new HashMap((Map) entry.getValue())));
        }
        return new SplitInstallModule(DesugarCollections.unmodifiableMap(hashMap));
    }

    private static final String readAttribute$ar$objectUnboxing$ar$ds(String str, XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals(str)) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    public static String sha256(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return Base64.encodeToString(messageDigest.digest(), 11);
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    private static final void skipTag$ar$objectUnboxing$ar$ds(XmlPullParser xmlPullParser) {
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next != 2) {
                if (next == 3) {
                    i--;
                }
            } else {
                i++;
            }
        }
    }

    public NativeLibraryPathListMutex(byte[] bArr, char[] cArr) {
        this();
    }
}
