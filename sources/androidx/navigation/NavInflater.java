package androidx.navigation;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.view.WindowCompat$Api35Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.navigation.NavArgument;
import androidx.navigation.NavType;
import java.io.Serializable;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavInflater {
    public static final WindowCompat$Api35Impl Companion$ar$class_merging$28bbac58_0 = new WindowCompat$Api35Impl();
    private static final ThreadLocal sTmpValue = new ThreadLocal();
    private final Context context;
    private final NavigatorProvider navigatorProvider;

    public NavInflater(Context context, NavigatorProvider navigatorProvider) {
        navigatorProvider.getClass();
        this.context = context;
        this.navigatorProvider = navigatorProvider;
    }

    private static final NavArgument inflateArgument$ar$ds(TypedArray typedArray, Resources resources, int i) {
        NavType navType;
        Object obj;
        NavType navType2;
        NavArgument.Builder builder = new NavArgument.Builder();
        int[] iArr = androidx.navigation.common.R$styleable.NavAction;
        boolean z = false;
        int i2 = 0;
        builder.isNullable = typedArray.getBoolean(3, false);
        ThreadLocal threadLocal = sTmpValue;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        String string = typedArray.getString(2);
        Object obj2 = null;
        if (string != null) {
            String resourcePackageName = resources.getResourcePackageName(i);
            if (string.startsWith("java")) {
                try {
                    navType = AccessibilityNodeInfoCompat.Api30Impl.fromArgType$ar$ds("j$" + string.substring(4), resourcePackageName);
                } catch (RuntimeException e) {
                    if (!(e.getCause() instanceof ClassNotFoundException)) {
                        throw e;
                    }
                }
            }
            navType = AccessibilityNodeInfoCompat.Api30Impl.fromArgType$ar$ds(string, resourcePackageName);
        } else {
            navType = null;
        }
        if (typedArray.getValue(1, typedValue)) {
            if (navType == NavType.ReferenceType) {
                if (typedValue.resourceId != 0) {
                    i2 = typedValue.resourceId;
                } else if (typedValue.type != 16 || typedValue.data != 0) {
                    throw new XmlPullParserException("unsupported value '" + ((Object) typedValue.string) + "' for " + navType.getName() + ". Must be a reference to a resource.");
                }
                obj2 = Integer.valueOf(i2);
            } else if (typedValue.resourceId != 0) {
                if (navType == null) {
                    navType = NavType.ReferenceType;
                    obj2 = Integer.valueOf(typedValue.resourceId);
                } else {
                    throw new XmlPullParserException("unsupported value '" + ((Object) typedValue.string) + "' for " + navType.getName() + ". You must use a \"" + NavType.ReferenceType.getName() + "\" type to reference other resources.");
                }
            } else if (navType == NavType.StringType) {
                obj2 = typedArray.getString(1);
            } else {
                int i3 = typedValue.type;
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            if (i3 != 18) {
                                if (typedValue.type >= 16 && typedValue.type <= 31) {
                                    NavType navType3 = NavType.FloatType;
                                    if (navType == navType3) {
                                        navType = WindowCompat$Api35Impl.checkNavType$navigation_runtime_release$ar$ds(typedValue, navType, navType3, string, "float");
                                        obj2 = Float.valueOf(typedValue.data);
                                    } else {
                                        navType = WindowCompat$Api35Impl.checkNavType$navigation_runtime_release$ar$ds(typedValue, navType, NavType.IntType, string, "integer");
                                        obj2 = Integer.valueOf(typedValue.data);
                                    }
                                } else {
                                    throw new XmlPullParserException("unsupported argument type " + typedValue.type);
                                }
                            } else {
                                navType = WindowCompat$Api35Impl.checkNavType$navigation_runtime_release$ar$ds(typedValue, navType, NavType.BoolType, string, "boolean");
                                if (typedValue.data != 0) {
                                    z = true;
                                }
                                obj2 = Boolean.valueOf(z);
                            }
                        } else {
                            navType = WindowCompat$Api35Impl.checkNavType$navigation_runtime_release$ar$ds(typedValue, navType, NavType.IntType, string, "dimension");
                            obj2 = Integer.valueOf((int) typedValue.getDimension(resources.getDisplayMetrics()));
                        }
                    } else {
                        navType = WindowCompat$Api35Impl.checkNavType$navigation_runtime_release$ar$ds(typedValue, navType, NavType.FloatType, string, "float");
                        obj2 = Float.valueOf(typedValue.getFloat());
                    }
                } else {
                    String obj3 = typedValue.string.toString();
                    if (navType == null) {
                        obj3.getClass();
                        try {
                            try {
                                try {
                                    try {
                                        NavType.IntType.parseValue(obj3);
                                        navType2 = NavType.IntType;
                                    } catch (IllegalArgumentException unused) {
                                        NavType.LongType.parseValue(obj3);
                                        navType2 = NavType.LongType;
                                    }
                                } catch (IllegalArgumentException unused2) {
                                    NavType.BoolType.parseValue(obj3);
                                    navType2 = NavType.BoolType;
                                }
                            } catch (IllegalArgumentException unused3) {
                                NavType.FloatType.parseValue(obj3);
                                navType2 = NavType.FloatType;
                            }
                        } catch (IllegalArgumentException unused4) {
                            navType2 = NavType.StringType;
                        }
                        navType = navType2;
                    }
                    obj2 = navType.parseValue(obj3);
                }
            }
        }
        if (obj2 != null) {
            builder.defaultValue = obj2;
            builder.defaultValuePresent = true;
        }
        if (navType != null) {
            builder.NavArgument$Builder$ar$type = navType;
        }
        Object obj4 = builder.NavArgument$Builder$ar$type;
        Object obj5 = obj4;
        if (obj4 == null) {
            Object obj6 = builder.defaultValue;
            if (obj6 instanceof Integer) {
                obj5 = NavType.IntType;
            } else if (obj6 instanceof int[]) {
                obj5 = NavType.IntArrayType;
            } else if (obj6 instanceof Long) {
                obj5 = NavType.LongType;
            } else if (obj6 instanceof long[]) {
                obj5 = NavType.LongArrayType;
            } else if (obj6 instanceof Float) {
                obj5 = NavType.FloatType;
            } else if (obj6 instanceof float[]) {
                obj5 = NavType.FloatArrayType;
            } else if (obj6 instanceof Boolean) {
                obj5 = NavType.BoolType;
            } else if (obj6 instanceof boolean[]) {
                obj5 = NavType.BoolArrayType;
            } else if (!(obj6 instanceof String) && obj6 != null) {
                if ((obj6 instanceof Object[]) && (((Object[]) obj6) instanceof String[])) {
                    obj5 = NavType.StringArrayType;
                } else {
                    if (obj6.getClass().isArray()) {
                        Class<?> componentType = obj6.getClass().getComponentType();
                        componentType.getClass();
                        if (Parcelable.class.isAssignableFrom(componentType)) {
                            Class<?> componentType2 = obj6.getClass().getComponentType();
                            componentType2.getClass();
                            obj = new NavType.ParcelableArrayType(componentType2);
                            obj5 = obj;
                        }
                    }
                    if (obj6.getClass().isArray()) {
                        Class<?> componentType3 = obj6.getClass().getComponentType();
                        componentType3.getClass();
                        if (Serializable.class.isAssignableFrom(componentType3)) {
                            Class<?> componentType4 = obj6.getClass().getComponentType();
                            componentType4.getClass();
                            obj = new NavType.SerializableArrayType(componentType4);
                            obj5 = obj;
                        }
                    }
                    if (obj6 instanceof Parcelable) {
                        obj = new NavType.ParcelableType(obj6.getClass());
                    } else if (obj6 instanceof Enum) {
                        obj = new NavType.EnumType(obj6.getClass());
                    } else if (obj6 instanceof Serializable) {
                        obj = new NavType.SerializableType(obj6.getClass());
                    } else {
                        throw new IllegalArgumentException("Object of type " + obj6.getClass().getName() + " is not supported for navigation arguments.");
                    }
                    obj5 = obj;
                }
            } else {
                obj5 = NavType.StringType;
            }
        }
        return new NavArgument((NavType) obj5, builder.isNullable, builder.defaultValue, builder.defaultValuePresent);
    }

    public final NavGraph inflate(int i) {
        int next;
        Resources resources = this.context.getResources();
        XmlResourceParser xml = resources.getXml(i);
        xml.getClass();
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            try {
                try {
                    next = xml.next();
                    if (next == 2) {
                        String name = xml.getName();
                        resources.getClass();
                        asAttributeSet.getClass();
                        NavDestination inflate = inflate(resources, xml, asAttributeSet, i);
                        if (inflate instanceof NavGraph) {
                            return (NavGraph) inflate;
                        }
                        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(name, "Root element <", "> did not inflate into a NavGraph"));
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Exception inflating " + resources.getResourceName(i) + " line " + xml.getLineNumber(), e);
                }
            } finally {
                xml.close();
            }
        } while (next != 1);
        throw new XmlPullParserException("No start tag found");
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0101, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("Every <deepLink> must include at least one of app:uri, app:action, or app:mimeType");
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x023f, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.navigation.NavDestination inflate(android.content.res.Resources r17, android.content.res.XmlResourceParser r18, android.util.AttributeSet r19, int r20) {
        /*
            Method dump skipped, instructions count: 576
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflate(android.content.res.Resources, android.content.res.XmlResourceParser, android.util.AttributeSet, int):androidx.navigation.NavDestination");
    }
}
