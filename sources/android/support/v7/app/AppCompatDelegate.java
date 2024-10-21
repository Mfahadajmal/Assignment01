package android.support.v7.app;

import _COROUTINE._BOUNDARY;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import android.support.v4.os.BundleCompat$Api33Impl;
import android.support.v7.widget.ResourceManagerInternal;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.collection.ArraySet;
import androidx.core.os.LocaleListCompat;
import androidx.sqlite.db.framework.FrameworkSQLiteDatabase;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.SharedPreferencesStringList;
import com.google.android.accessibility.braille.interfaces.SelectionRange;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import j$.util.Collection;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AppCompatDelegate {
    static final SerialExecutor sSerialExecutorForLocalesStorage = new SerialExecutor(new ArchTaskExecutor.AnonymousClass2(1));
    public static final int sDefaultNightMode = -100;
    public static LocaleListCompat sRequestedAppLocales = null;
    public static LocaleListCompat sStoredAppLocales = null;
    private static Boolean sIsAutoStoreLocalesOptedIn = null;
    public static boolean sIsFrameworkSyncChecked = false;
    public static final ArraySet sActivityDelegates = new ArraySet();
    public static final Object sActivityDelegatesLock = new Object();
    public static final Object sAppLocalesStorageSyncLock = new Object();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api33Impl {
        public static Map dotKeyStringsToDotCache;

        public static void d(String str, String str2, String str3) {
            LogUtils.d(str, "%s: %s", str2, str3);
        }

        public static void e(String str, String str2, String str3) {
            LogUtils.e(str, null, "%s: %s", str2, str3);
        }

        public static CharSequence filterNonPrintCharacter(CharSequence charSequence) {
            if (TextUtils.isEmpty(charSequence)) {
                return "";
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
            int length = charSequence.length();
            while (true) {
                length--;
                if (length >= 0) {
                    if (Character.isIdentifierIgnorable(spannableStringBuilder.charAt(length))) {
                        spannableStringBuilder.delete(length, length + 1);
                    }
                } else {
                    return spannableStringBuilder;
                }
            }
        }

        public static Drawable getDrawable(Context context, int i) {
            return ResourceManagerInternal.get().getDrawable(context, i);
        }

        private static int getInputTypeClass(int i) {
            int i2 = i & 15;
            if (i2 == 0 && (i & 4080) != 0) {
                return 1;
            }
            return i2;
        }

        public static int getLastWordLengthForDeletion(CharSequence charSequence) {
            int i;
            int i2 = 0;
            if (TextUtils.isEmpty(charSequence)) {
                return 0;
            }
            int length = charSequence.length();
            int lastIndexOfWhitespace = lastIndexOfWhitespace(charSequence);
            if (lastIndexOfWhitespace < 0) {
                return length;
            }
            int i3 = length - 1;
            if (lastIndexOfWhitespace != i3) {
                i = length - lastIndexOfWhitespace;
            } else {
                char charAt = charSequence.charAt(i3);
                if (isNewline(charAt)) {
                    return 1;
                }
                int i4 = 0;
                for (int length2 = charSequence.length() - 1; length2 >= 0 && Character.isWhitespace(charSequence.charAt(length2)); length2--) {
                    i4++;
                }
                if (length > 1 && i4 == 1 && charAt == ' ') {
                    int lastIndexOfWhitespace2 = lastIndexOfWhitespace(charSequence.subSequence(0, i3));
                    if (lastIndexOfWhitespace2 < 0) {
                        return length;
                    }
                    i = length - lastIndexOfWhitespace2;
                } else {
                    int length3 = charSequence.length();
                    while (true) {
                        length3--;
                        if (length3 < 0 || !Character.isWhitespace(charSequence.charAt(length3)) || isNewline(charSequence.charAt(length3))) {
                            break;
                        }
                        i2++;
                    }
                    return i2;
                }
            }
            return i - 1;
        }

        public static SelectionRange getTextSelection(InputConnection inputConnection) {
            int i;
            int i2;
            int i3 = 0;
            ExtractedText extractedText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0);
            if (extractedText != null) {
                if (extractedText.selectionStart > 0) {
                    i2 = extractedText.selectionStart;
                } else {
                    i2 = 0;
                }
                if (extractedText.selectionEnd > 0) {
                    i3 = extractedText.selectionEnd;
                }
                i = i3;
                i3 = i2;
            } else {
                i = 0;
            }
            return new SelectionRange(i3, i);
        }

        public static final FrameworkSQLiteDatabase getWrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, SQLiteDatabase sQLiteDatabase) {
            onDeviceTextDetectionLoadLogEvent.getClass();
            Object obj = onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
            if (obj != null) {
                FrameworkSQLiteDatabase frameworkSQLiteDatabase = (FrameworkSQLiteDatabase) obj;
                if (Intrinsics.areEqual(frameworkSQLiteDatabase.delegate, sQLiteDatabase)) {
                    return frameworkSQLiteDatabase;
                }
            }
            FrameworkSQLiteDatabase frameworkSQLiteDatabase2 = new FrameworkSQLiteDatabase(sQLiteDatabase);
            onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = frameworkSQLiteDatabase2;
            return frameworkSQLiteDatabase2;
        }

        public static boolean isInputMethodDefault(Context context, ComponentName componentName) {
            String string = Settings.Secure.getString(context.getContentResolver(), "default_input_method");
            if (string != null && componentName.equals(ComponentName.unflattenFromString(string))) {
                return true;
            }
            return false;
        }

        public static boolean isInputMethodEnabled(Context context, ComponentName componentName) {
            String string = Settings.Secure.getString(context.getContentResolver(), "enabled_input_methods");
            if (string == null) {
                return false;
            }
            for (String str : string.split(":")) {
                if (componentName.equals(ComponentName.unflattenFromString(str))) {
                    return true;
                }
            }
            return false;
        }

        private static boolean isNewline(char c) {
            if (c != '\r' && c != '\n') {
                return false;
            }
            return true;
        }

        public static boolean isPasswordField(EditorInfo editorInfo) {
            if (editorInfo == null) {
                return false;
            }
            int i = editorInfo.inputType;
            int i2 = i & 4080;
            if (getInputTypeClass(i) == 2 && i2 == 16) {
                return true;
            }
            int i3 = editorInfo.inputType;
            int i4 = i3 & 4080;
            if (getInputTypeClass(i3) != 1) {
                return false;
            }
            if (i4 != 128 && i4 != 144 && i4 != 224) {
                return false;
            }
            return true;
        }

        public static boolean isTextField(EditorInfo editorInfo) {
            int inputTypeClass = getInputTypeClass(editorInfo.inputType);
            if (inputTypeClass != 2 && inputTypeClass != 3 && inputTypeClass != 4) {
                return true;
            }
            return false;
        }

        public static int lastIndexOfWhitespace(CharSequence charSequence) {
            int length = charSequence.length();
            do {
                length--;
                if (length < 0) {
                    break;
                }
            } while (!Character.isWhitespace(charSequence.charAt(length)));
            return length;
        }

        public static LocaleList localeManagerGetApplicationLocales(Object obj) {
            LocaleList applicationLocales;
            applicationLocales = ContextUtils$$ExternalSyntheticApiModelOutline0.m(obj).getApplicationLocales();
            return applicationLocales;
        }

        public static void localeManagerSetApplicationLocales(Object obj, LocaleList localeList) {
            ContextUtils$$ExternalSyntheticApiModelOutline0.m(obj).setApplicationLocales(localeList);
        }

        public static void performKeyAction$ar$ds(InputConnection inputConnection) {
            if (inputConnection.sendKeyEvent(new KeyEvent(0, 67))) {
                inputConnection.sendKeyEvent(new KeyEvent(1, 67));
            }
        }

        public static Enum valueOfSafe(String str, Enum r2) {
            try {
                return Enum.valueOf(r2.getDeclaringClass(), str);
            } catch (IllegalArgumentException unused) {
                return r2;
            }
        }

        public static void e(String str, String str2, String str3, Throwable th) {
            LogUtils.e(str, th, "%s: %s", str2, str3);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock = new Object();
        final Queue mTasks = new ArrayDeque();

        public SerialExecutor(Executor executor) {
            this.mExecutor = executor;
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            synchronized (this.mLock) {
                this.mTasks.add(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(this, runnable, 6, (byte[]) null));
                if (this.mActive == null) {
                    scheduleNext();
                }
            }
        }

        public final void scheduleNext() {
            synchronized (this.mLock) {
                Runnable runnable = (Runnable) this.mTasks.poll();
                this.mActive = runnable;
                if (runnable != null) {
                    this.mExecutor.execute(runnable);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAutoStorageOptedIn(Context context) {
        if (sIsAutoStoreLocalesOptedIn == null) {
            try {
                ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) AppLocalesMetadataHolderService.class), 640);
                if (serviceInfo.metaData != null) {
                    sIsAutoStoreLocalesOptedIn = Boolean.valueOf(serviceInfo.metaData.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                sIsAutoStoreLocalesOptedIn = false;
            }
        }
        return sIsAutoStoreLocalesOptedIn.booleanValue();
    }

    public static void removeDelegateFromActives(AppCompatDelegate appCompatDelegate) {
        synchronized (sActivityDelegatesLock) {
            ArraySet.ElementIterator elementIterator = new ArraySet.ElementIterator();
            while (elementIterator.hasNext()) {
                AppCompatDelegate appCompatDelegate2 = (AppCompatDelegate) ((WeakReference) elementIterator.next()).get();
                if (appCompatDelegate2 == appCompatDelegate || appCompatDelegate2 == null) {
                    elementIterator.remove();
                }
            }
        }
    }

    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract View findViewById(int i);

    public Context getContextForDelegate() {
        throw null;
    }

    public abstract ActionBar getSupportActionBar();

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract void onConfigurationChanged$ar$ds();

    public abstract void onCreate$ar$ds();

    public abstract void onDestroy();

    public abstract void onStop();

    public abstract void requestWindowFeature$ar$ds(int i);

    public abstract void setContentView(int i);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void setTitle(CharSequence charSequence);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        public static void addRememberedDevice(Context context, Pair pair) {
            ImmutableSet immutableSet = (ImmutableSet) Collection.EL.stream(getRememberedDevices(context)).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(pair, 8)).collect(CollectCollectors.TO_IMMUTABLE_SET);
            int i = 0;
            if (!immutableSet.isEmpty()) {
                if (immutableSet.size() > 1) {
                    UnmodifiableIterator listIterator = immutableSet.listIterator();
                    while (listIterator.hasNext()) {
                        Pair pair2 = (Pair) listIterator.next();
                        if (i == 0) {
                            i = 1;
                        } else {
                            deleteRememberedDevice(context, pair2);
                        }
                    }
                    return;
                }
                return;
            }
            String concatenateWithDelimiter$ar$ds = concatenateWithDelimiter$ar$ds((String) pair.first, (String) pair.second);
            SharedPreferences sharedPrefs = getSharedPrefs(context);
            TreeMap readTreeMap$ar$ds = SharedPreferencesStringList.readTreeMap$ar$ds(sharedPrefs);
            TreeMap treeMap = new TreeMap();
            treeMap.put(-1, concatenateWithDelimiter$ar$ds);
            while (i < readTreeMap$ar$ds.keySet().size()) {
                Integer valueOf = Integer.valueOf(i);
                String str = (String) readTreeMap$ar$ds.get(valueOf);
                if (!concatenateWithDelimiter$ar$ds.equals(str)) {
                    treeMap.put(valueOf, str);
                }
                i++;
            }
            SharedPreferencesStringList.writeTreeMap$ar$ds(sharedPrefs, treeMap);
        }

        private static String concatenateWithDelimiter$ar$ds(String str, String str2) {
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str2, str, "$");
        }

        private static void deleteRememberedDevice(Context context, Pair pair) {
            String concatenateWithDelimiter$ar$ds = concatenateWithDelimiter$ar$ds((String) pair.first, (String) pair.second);
            SharedPreferences sharedPrefs = getSharedPrefs(context);
            TreeMap readTreeMap$ar$ds = SharedPreferencesStringList.readTreeMap$ar$ds(sharedPrefs);
            Iterator it = readTreeMap$ar$ds.navigableKeySet().iterator();
            while (it.hasNext()) {
                if (concatenateWithDelimiter$ar$ds.equals(readTreeMap$ar$ds.get(it.next()))) {
                    it.remove();
                }
            }
            SharedPreferencesStringList.writeTreeMap$ar$ds(sharedPrefs, readTreeMap$ar$ds);
        }

        public static final Method getBeginTransactionMethod$ar$ds() {
            return (Method) FrameworkSQLiteDatabase.beginTransactionMethod$delegate.getValue();
        }

        public static final Method getGetThreadSessionMethod$ar$ds() {
            return (Method) FrameworkSQLiteDatabase.getThreadSessionMethod$delegate.getValue();
        }

        public static Object getParcelable(Bundle bundle, String str, Class cls) {
            if (Build.VERSION.SDK_INT >= 34) {
                return BundleCompat$Api33Impl.getParcelable(bundle, str, cls);
            }
            Parcelable parcelable = bundle.getParcelable(str);
            if (cls.isInstance(parcelable)) {
                return parcelable;
            }
            return null;
        }

        public static List getRememberedDevices(Context context) {
            ArrayList<String> arrayList = new ArrayList(SharedPreferencesStringList.readTreeMap$ar$ds(getSharedPrefs(context)).values());
            ArrayList arrayList2 = new ArrayList();
            for (String str : arrayList) {
                int lastIndexOf = str.lastIndexOf("$");
                String substring = str.substring(0, lastIndexOf);
                String substring2 = str.substring(lastIndexOf + 1);
                if (!TextUtils.isEmpty(substring) && !TextUtils.isEmpty(substring2)) {
                    arrayList2.add(new Pair(substring, substring2));
                } else {
                    deleteRememberedDevice(context, substring2);
                }
            }
            return arrayList2;
        }

        public static SharedPreferences getSharedPrefs(Context context) {
            return SpannableUtils$IdentifierSpan.getSharedPreferences(context, "braille_display");
        }

        public static boolean isAutoConnect(Context context) {
            return getSharedPrefs(context).getBoolean("auto_connect", true);
        }

        public static boolean isConnectionEnabled(Context context) {
            return getSharedPrefs(context).getBoolean("connection_enabled_by_user", false);
        }

        public static LocaleList localeListForLanguageTags(String str) {
            LocaleList forLanguageTags;
            forLanguageTags = LocaleList.forLanguageTags(str);
            return forLanguageTags;
        }

        public static void setConnectionEnabled(Context context, boolean z) {
            getSharedPrefs(context).edit().putBoolean("connection_enabled_by_user", z).apply();
        }

        public static void syncRememberedDevice(Context context, Set set) {
            for (Pair pair : getRememberedDevices(context)) {
                ImmutableSet immutableSet = (ImmutableSet) Collection.EL.stream(set).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(pair, 10)).collect(CollectCollectors.TO_IMMUTABLE_SET);
                if (immutableSet.isEmpty()) {
                    deleteRememberedDevice(context, pair);
                } else {
                    ConnectableDevice connectableDevice = (ConnectableDevice) Collection.EL.stream(immutableSet).findFirst().get();
                    if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(connectableDevice.name(), pair.first)) {
                        deleteRememberedDevice(context, pair);
                        addRememberedDevice(context, new Pair(connectableDevice.name(), connectableDevice.address()));
                    }
                }
            }
        }

        public static /* synthetic */ String toStringGeneratedfc30da1462dd4304(int i) {
            switch (i) {
                case 2:
                    return "START_STARTED";
                case 3:
                    return "START_SCREEN_ON";
                case 4:
                    return "START_SETTINGS";
                case 5:
                    return "START_BLUETOOTH_TURNED_ON";
                case 6:
                    return "START_BLUETOOTH_TURNED_OFF";
                case 7:
                    return "START_USER_SELECTED_RESCAN";
                case 8:
                    return "START_USB_ATTACH_DETACH";
                case 9:
                    return "STOP_STOPPED";
                case 10:
                    return "STOP_SCREEN_OFF";
                default:
                    return "STOP_DISCOVERY_FAILED";
            }
        }

        public static void deleteRememberedDevice(Context context, String str) {
            UnmodifiableIterator listIterator = ((ImmutableSet) Collection.EL.stream(getRememberedDevices(context)).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(str, 9)).collect(CollectCollectors.TO_IMMUTABLE_SET)).listIterator();
            while (listIterator.hasNext()) {
                deleteRememberedDevice(context, (Pair) listIterator.next());
            }
        }
    }
}
