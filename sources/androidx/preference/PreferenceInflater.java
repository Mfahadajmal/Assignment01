package androidx.preference;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class PreferenceInflater {
    public final Context mContext;
    private PreferenceManager mPreferenceManager;
    private static final Class[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private static final HashMap CONSTRUCTOR_MAP = new HashMap();
    private final Object[] mConstructorArgs = new Object[2];
    private String[] mDefaultPackages = {String.valueOf(Preference.class.getPackage().getName()).concat("."), String.valueOf(SwitchPreference.class.getPackage().getName()).concat(".")};

    public PreferenceInflater(Context context, PreferenceManager preferenceManager) {
        this.mContext = context;
        this.mPreferenceManager = preferenceManager;
    }

    private final Preference createItem(String str, String[] strArr, AttributeSet attributeSet) {
        Class<?> cls;
        Constructor<?> constructor = (Constructor) CONSTRUCTOR_MAP.get(str);
        if (constructor == null) {
            try {
                try {
                    ClassLoader classLoader = this.mContext.getClassLoader();
                    if (strArr != null) {
                        cls = null;
                        ClassNotFoundException e = null;
                        for (int i = 0; i < 2; i++) {
                            try {
                                cls = Class.forName(strArr[i] + str, false, classLoader);
                                break;
                            } catch (ClassNotFoundException e2) {
                                e = e2;
                            }
                        }
                        if (cls == null) {
                            if (e == null) {
                                throw new InflateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(str, attributeSet, ": Error inflating class "));
                            }
                            throw e;
                        }
                    } else {
                        cls = Class.forName(str, false, classLoader);
                    }
                    constructor = cls.getConstructor(CONSTRUCTOR_SIGNATURE);
                    constructor.setAccessible(true);
                    CONSTRUCTOR_MAP.put(str, constructor);
                } catch (ClassNotFoundException e3) {
                    throw e3;
                }
            } catch (Exception e4) {
                InflateException inflateException = new InflateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(str, attributeSet, ": Error inflating class "));
                inflateException.initCause(e4);
                throw inflateException;
            }
        }
        Object[] objArr = this.mConstructorArgs;
        objArr[1] = attributeSet;
        return (Preference) constructor.newInstance(objArr);
    }

    private final Preference createItemFromTag(String str, AttributeSet attributeSet) {
        try {
            if (str.indexOf(46) == -1) {
                return createItem(str, this.mDefaultPackages, attributeSet);
            }
            return createItem(str, null, attributeSet);
        } catch (InflateException e) {
            throw e;
        } catch (ClassNotFoundException e2) {
            InflateException inflateException = new InflateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(str, attributeSet, ": Error inflating class (not found)"));
            inflateException.initCause(e2);
            throw inflateException;
        } catch (Exception e3) {
            InflateException inflateException2 = new InflateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(str, attributeSet, ": Error inflating class "));
            inflateException2.initCause(e3);
            throw inflateException2;
        }
    }

    private final void rInflate(XmlPullParser xmlPullParser, Preference preference, AttributeSet attributeSet) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3) {
                if (xmlPullParser.getDepth() > depth) {
                    next = 3;
                } else {
                    return;
                }
            }
            if (next != 1) {
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    if ("intent".equals(name)) {
                        try {
                            preference.setIntent(Intent.parseIntent(this.mContext.getResources(), xmlPullParser, attributeSet));
                        } catch (IOException e) {
                            XmlPullParserException xmlPullParserException = new XmlPullParserException("Error parsing preference");
                            xmlPullParserException.initCause(e);
                            throw xmlPullParserException;
                        }
                    } else if ("extra".equals(name)) {
                        this.mContext.getResources().parseBundleExtra("extra", attributeSet, preference.getExtras());
                        try {
                            int depth2 = xmlPullParser.getDepth();
                            while (true) {
                                int next2 = xmlPullParser.next();
                                if (next2 != 1 && (next2 != 3 || xmlPullParser.getDepth() > depth2)) {
                                }
                            }
                        } catch (IOException e2) {
                            XmlPullParserException xmlPullParserException2 = new XmlPullParserException("Error parsing preference");
                            xmlPullParserException2.initCause(e2);
                            throw xmlPullParserException2;
                        }
                    } else {
                        Preference createItemFromTag = createItemFromTag(name, attributeSet);
                        ((PreferenceGroup) preference).addPreference$ar$ds(createItemFromTag);
                        rInflate(xmlPullParser, createItemFromTag, attributeSet);
                    }
                }
            } else {
                return;
            }
        }
    }

    public final Preference inflate(XmlPullParser xmlPullParser, PreferenceGroup preferenceGroup) {
        int next;
        synchronized (this.mConstructorArgs) {
            AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
            this.mConstructorArgs[0] = this.mContext;
            do {
                try {
                    try {
                        try {
                            next = xmlPullParser.next();
                            if (next == 2) {
                                PreferenceGroup preferenceGroup2 = (PreferenceGroup) createItemFromTag(xmlPullParser.getName(), asAttributeSet);
                                if (preferenceGroup == null) {
                                    preferenceGroup2.onAttachedToHierarchy(this.mPreferenceManager);
                                    preferenceGroup = preferenceGroup2;
                                }
                                rInflate(xmlPullParser, preferenceGroup, asAttributeSet);
                            }
                        } catch (IOException e) {
                            InflateException inflateException = new InflateException(xmlPullParser.getPositionDescription() + ": " + e.getMessage());
                            inflateException.initCause(e);
                            throw inflateException;
                        }
                    } catch (XmlPullParserException e2) {
                        InflateException inflateException2 = new InflateException(e2.getMessage());
                        inflateException2.initCause(e2);
                        throw inflateException2;
                    }
                } catch (InflateException e3) {
                    throw e3;
                }
            } while (next != 1);
            throw new InflateException(xmlPullParser.getPositionDescription() + ": No start tag found!");
        }
        return preferenceGroup;
    }
}
