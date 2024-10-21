package com.google.android.accessibility.braille.brailledisplay.settings;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import com.android.settingslib.widget.BannerMessagePreference;
import com.android.settingslib.widget.MainSwitchBar;
import com.android.settingslib.widget.MainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;
import com.google.android.accessibility.accessibilitymenu.activity.A11yMenuSettingsActivity;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.brailledisplay.controller.CellsContentManager;
import com.google.android.accessibility.braille.brailledisplay.controller.TranslatorManager;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.BrailleDisplayManager;
import com.google.android.accessibility.braille.brailledisplay.platform.ConnectibleDeviceInfo;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda6;
import com.google.android.accessibility.braille.brailledisplay.platform.Displayer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableBluetoothDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableUsbDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.settings.KeyBindingsCommandActivity;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.braille.brltty.BrailleInputEventIA;
import com.google.android.accessibility.braille.brltty.device.BrlttyBluetoothParameterProvider;
import com.google.android.accessibility.braille.brltty.device.BrlttyUsbParameterProvider;
import com.google.android.accessibility.braille.brltty.device.ParameterProvider;
import com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda5;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.settings.BrailleLanguagesActivity;
import com.google.android.accessibility.braille.common.translate.BrailleTranslateUtils;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity;
import com.google.android.accessibility.selecttospeak.activities.SelectToSpeakPreferencesActivity;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.InProductHelp;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpClient;
import com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.surveys.internal.view.InvitationView;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.stream.Collectors;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleDisplaySettingsFragment extends PreferenceFragmentCompat {
    private static final String GOOGLE_HELP_URI = "https://support.google.com/accessibility/android/answer/3535226";
    private static final String HELP_CONTEXT = "android_default";
    private static final BrailleCharacter SHORTCUT_SWITCH_INPUT_CODE = new BrailleCharacter("2478");
    private static final BrailleCharacter SHORTCUT_SWITCH_OUTPUT_CODE = new BrailleCharacter("13578");
    private Connectioneer.AspectConnection aspectConnection;
    private Connectioneer.AspectDisplayProperties aspectDisplayProperties;
    private Connectioneer.AspectEnablement aspectEnablement;
    private SwitchPreference autoConnectPreference;
    private BannerMessagePreference bannerMessagePreference;
    private Preference brailleGradePreference;
    private ProgressPreferenceCategory connectionPreferenceCategory;
    private Connectioneer connectioneer;
    private ComponentName controllingService;
    private ListPreference currentActiveInputCodePreference;
    private ListPreference currentActiveOutputCodePreference;
    private Predicate<String> deviceNameFilter;
    private MainSwitchPreference enablerSwitch;
    private Preference keyBindingsPreference;
    private Preference preferredCodesPreference;
    private Preference scanPreference;
    private TranslatorManager translatorManager;
    private boolean systemPermissionDialogIsShowable = false;
    private boolean scanning = false;
    private final Set<ConnectableDevice> scannedDevicesCache = new HashSet();
    private final ActivityResultLauncher<String[]> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts$RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda21
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            BrailleDisplaySettingsFragment.this.m56xb96614e2((Map) obj);
        }
    });
    private final Connectioneer.AspectEnablement.Callback enablementCallback = new Connectioneer.AspectEnablement.Callback() { // from class: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment.3
        public AnonymousClass3() {
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectEnablement.Callback
        public final void onEnablementChanged() {
            BrailleDisplaySettingsFragment.this.onModelChanged();
        }
    };
    private final Connectioneer.AspectConnection.Callback connectionCallback = new AnonymousClass4(this, 0);
    private final Connectioneer.AspectDisplayProperties.Callback displayPropertyCallback = new KeyBindingsCommandActivity.KeyBindingsCommandFragment.AnonymousClass1(this, 1);
    private final TranslatorManager.InputCodeChangedListener onInputCodeChangedListener = new TranslatorManager.InputCodeChangedListener() { // from class: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment.6
        public AnonymousClass6() {
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.controller.TranslatorManager.InputCodeChangedListener
        public final void onInputCodeChanged() {
            BrailleDisplaySettingsFragment.this.onModelChanged();
        }
    };
    private final TranslatorManager.OutputCodeChangedListener outputCodeChangedListener = new AnonymousClass7(this, 0);
    private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 7);

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements OnMainSwitchChangeListener {
        final /* synthetic */ Object BrailleDisplaySettingsFragment$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public /* synthetic */ AnonymousClass1(MainSwitchBar mainSwitchBar, int i) {
            this.switching_field = i;
            this.BrailleDisplaySettingsFragment$1$ar$this$0 = mainSwitchBar;
        }

        @Override // com.android.settingslib.widget.OnMainSwitchChangeListener
        public final void onSwitchChanged$ar$ds(boolean z) {
            if (this.switching_field != 0) {
                ((MainSwitchBar) this.BrailleDisplaySettingsFragment$1$ar$this$0).setChecked(z);
            } else {
                AppCompatDelegate.Api24Impl.setConnectionEnabled(((Fragment) this.BrailleDisplaySettingsFragment$1$ar$this$0).getContext(), z);
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$1$ar$this$0).onModelChanged();
            }
        }

        public AnonymousClass1(BrailleDisplaySettingsFragment brailleDisplaySettingsFragment, int i) {
            this.switching_field = i;
            this.BrailleDisplaySettingsFragment$1$ar$this$0 = brailleDisplaySettingsFragment;
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$2 */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements Preference.OnPreferenceClickListener {
        final /* synthetic */ PreferenceFragmentCompat BrailleDisplaySettingsFragment$2$ar$this$0;
        private final /* synthetic */ int switching_field;

        public /* synthetic */ AnonymousClass2(PreferenceFragmentCompat preferenceFragmentCompat, int i) {
            this.switching_field = i;
            this.BrailleDisplaySettingsFragment$2$ar$this$0 = preferenceFragmentCompat;
        }

        @Override // androidx.preference.Preference.OnPreferenceClickListener
        public final boolean onPreferenceClick(Preference preference) {
            int i = this.switching_field;
            if (i == 0) {
                Connectioneer.AspectConnection aspectConnection = ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).aspectConnection;
                aspectConnection.connectioneer.connectManagerProxy.startSearch$ar$edu(7);
                aspectConnection.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(0));
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).onModelChanged();
                return true;
            }
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return ((SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).m90xaec384df(preference);
                            }
                            return ((SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).m91x7e8bde85(preference);
                        }
                        return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).m80xd2a166c1(preference);
                    }
                    return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).m79x92768000(preference);
                }
                return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).m76xd1f5cbbd(preference);
            }
            return ((A11yMenuSettingsActivity.A11yMenuPreferenceFragment) this.BrailleDisplaySettingsFragment$2$ar$this$0).m37xb0b13735(preference);
        }

        public AnonymousClass2(BrailleDisplaySettingsFragment brailleDisplaySettingsFragment, int i) {
            this.switching_field = i;
            this.BrailleDisplaySettingsFragment$2$ar$this$0 = brailleDisplaySettingsFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$3 */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements Connectioneer.AspectEnablement.Callback {
        public AnonymousClass3() {
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectEnablement.Callback
        public final void onEnablementChanged() {
            BrailleDisplaySettingsFragment.this.onModelChanged();
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$4 */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 implements Connectioneer.AspectConnection.Callback {
        final /* synthetic */ Object BrailleDisplaySettingsFragment$4$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass4(Object obj, int i) {
            this.switching_field = i;
            this.BrailleDisplaySettingsFragment$4$ar$this$0 = obj;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onConnectFailed$ar$ds(String str) {
            String string;
            int i = this.switching_field;
            if (i == 0) {
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).onModelChanged();
                ((Fragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).getContext();
                if (str == null) {
                    string = ((Fragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).getString(R.string.bd_bt_connection_failed_message);
                } else {
                    string = ((Fragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).getString(R.string.bd_bt_connection_with_device_failed_message, str);
                }
                Toast.makeText(((Fragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).getContext(), string, 1).show();
                return;
            }
            if (i != 1) {
                return;
            }
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplayManager", "onConnectFailed deviceName:".concat(String.valueOf(str)));
            BrailleDisplayAnalytics.getInstance(((BrailleDisplayManager) this.BrailleDisplaySettingsFragment$4$ar$this$0).controller$ar$class_merging.context).logConnectionReset();
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onConnectHidStarted() {
            if (this.switching_field != 1) {
                return;
            }
            BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(((BrailleDisplayManager) this.BrailleDisplaySettingsFragment$4$ar$this$0).controller$ar$class_merging.context);
            brailleDisplayAnalytics.logConnectionReset();
            brailleDisplayAnalytics.connectToHidStartTimeMs = System.currentTimeMillis();
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onConnectRfcommStarted() {
            if (this.switching_field != 1) {
                return;
            }
            BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(((BrailleDisplayManager) this.BrailleDisplaySettingsFragment$4$ar$this$0).controller$ar$class_merging.context);
            brailleDisplayAnalytics.connectToRfcommStartTimeMs = System.currentTimeMillis();
            brailleDisplayAnalytics.connectToBrailleDisplayStartTimeMs = 0L;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onConnectableDeviceDeleted$ar$ds() {
            int i = this.switching_field;
            if (i == 0) {
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).onModelChanged();
            } else {
                if (i != 1) {
                    return;
                }
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplayManager", "onConnectableDeviceDeleted");
            }
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onConnectableDeviceSeenOrUpdated(ConnectableDevice connectableDevice) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplayManager", "onConnectableDeviceSeenOrUpdated");
                return;
            }
            if (!((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).scannedDevicesCache.contains(connectableDevice) && !Collection.EL.stream(AppCompatDelegate.Api24Impl.getRememberedDevices(((Fragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).getContext())).anyMatch(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(connectableDevice, 13))) {
                OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(((Fragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).getString(R.string.bd_new_device_found_announcement), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
            }
            ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).scannedDevicesCache.clear();
            Object obj = this.BrailleDisplaySettingsFragment$4$ar$this$0;
            ((BrailleDisplaySettingsFragment) obj).scannedDevicesCache.addAll(((BrailleDisplaySettingsFragment) obj).aspectConnection.getScannedDevicesCopy());
            ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).onModelChanged();
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onConnectionStatusChanged$ar$edu(int i, ConnectableDevice connectableDevice) {
            String str;
            boolean z;
            ApplicationModule applicationModule;
            ParameterProvider brlttyUsbParameterProvider;
            int i2 = this.switching_field;
            if (i2 == 0) {
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).onModelChanged();
                return;
            }
            if (i2 != 1) {
                if (i != 1) {
                    ((KeyBindingsCommandActivity.KeyBindingsCommandFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).refresh(null);
                    return;
                }
                return;
            }
            String valueOf = String.valueOf(connectableDevice);
            if (i != 1) {
                if (i != 2) {
                    str = "CONNECTING";
                } else {
                    str = "DISCONNECTED";
                }
            } else {
                str = "CONNECTED";
            }
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplayManager", "onConnectionStatusChanged deviceName = " + valueOf + " connected:" + str);
            Object obj = this.BrailleDisplaySettingsFragment$4$ar$this$0;
            if (i == 1) {
                z = true;
            } else {
                z = false;
            }
            ((BrailleDisplayManager) obj).connectedToDisplay = z;
            if (i == 1) {
                Object obj2 = this.BrailleDisplaySettingsFragment$4$ar$this$0;
                AppCompatTextViewAutoSizeHelper.Api23Impl.v("BdController", "onConnected");
                BrailleDisplayAnalytics.getInstance(((BrailleDisplayManager) obj2).controller$ar$class_merging.context).connectToBrailleDisplayStartTimeMs = System.currentTimeMillis();
                Displayer displayer = ((BrailleDisplayManager) this.BrailleDisplaySettingsFragment$4$ar$this$0).displayer;
                displayer.device = connectableDevice;
                if (!displayer.isReady()) {
                    AppCompatTextViewAutoSizeHelper.Api23Impl.v("Displayer", "start a new thread");
                    displayer.bgThread = new HandlerThread("DisplayerBG");
                    displayer.bgThread.start();
                    displayer.bgHandler = new Handler(displayer.bgThread.getLooper(), new Displayer.MainHandlerCallback(displayer, 1));
                }
                Handler handler = displayer.bgHandler;
                int i3 = Displayer.MessageBg.START$ar$edu;
                int i4 = i3 - 1;
                if (i3 != 0) {
                    handler.removeMessages(i4);
                    AppCompatDelegate.Api33Impl api33Impl = displayer.parameterProviderFactory$ar$class_merging$ar$class_merging;
                    boolean z2 = connectableDevice.useHid;
                    ConnectableDevice connectableDevice2 = displayer.device;
                    if (connectableDevice2 instanceof ConnectableBluetoothDevice) {
                        applicationModule = new ApplicationModule(((ConnectableBluetoothDevice) connectableDevice2).bluetoothDevice());
                    } else if (connectableDevice2 instanceof ConnectableUsbDevice) {
                        applicationModule = new ApplicationModule(((ConnectableUsbDevice) connectableDevice2).usbDevice());
                    } else {
                        throw new IllegalArgumentException();
                    }
                    Object obj3 = applicationModule.ApplicationModule$ar$application;
                    if (obj3 instanceof BluetoothDevice) {
                        brlttyUsbParameterProvider = new BrlttyBluetoothParameterProvider((BluetoothDevice) obj3);
                    } else if (obj3 instanceof UsbDevice) {
                        brlttyUsbParameterProvider = new BrlttyUsbParameterProvider((UsbDevice) obj3);
                    } else {
                        throw new IllegalArgumentException("No matching connect type.");
                    }
                    Handler handler2 = displayer.bgHandler;
                    int i5 = Displayer.MessageBg.START$ar$edu;
                    int i6 = i5 - 1;
                    if (i5 != 0) {
                        handler2.obtainMessage(i6, brlttyUsbParameterProvider.getParameters()).sendToTarget();
                        return;
                    }
                    throw null;
                }
                throw null;
            }
            if (i == 2) {
                ((BrailleDisplayManager) this.BrailleDisplaySettingsFragment$4$ar$this$0).controller$ar$class_merging.onDisconnected();
                ((BrailleDisplayManager) this.BrailleDisplaySettingsFragment$4$ar$this$0).displayer.stop();
            }
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onDeviceListCleared() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplayManager", "onDeviceListCleared");
            } else {
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).scannedDevicesCache.clear();
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).onModelChanged();
            }
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectConnection.Callback
        public final void onScanningChanged() {
            int i = this.switching_field;
            if (i == 0) {
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$4$ar$this$0).onModelChanged();
            } else {
                if (i != 1) {
                    return;
                }
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplayManager", "onScanningChanged");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$6 */
    /* loaded from: classes.dex */
    public final class AnonymousClass6 implements TranslatorManager.InputCodeChangedListener {
        public AnonymousClass6() {
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.controller.TranslatorManager.InputCodeChangedListener
        public final void onInputCodeChanged() {
            BrailleDisplaySettingsFragment.this.onModelChanged();
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$7 */
    /* loaded from: classes.dex */
    public final class AnonymousClass7 implements TranslatorManager.OutputCodeChangedListener {
        final /* synthetic */ Object BrailleDisplaySettingsFragment$7$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass7(Object obj, int i) {
            this.switching_field = i;
            this.BrailleDisplaySettingsFragment$7$ar$this$0 = obj;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.controller.TranslatorManager.OutputCodeChangedListener
        public final void onOutputCodeChanged() {
            if (this.switching_field == 0) {
                ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$7$ar$this$0).onModelChanged();
                return;
            }
            ((CellsContentManager) this.BrailleDisplaySettingsFragment$7$ar$this$0).timedMessageDisplayInfoWrapper.retranslate();
            ((CellsContentManager) this.BrailleDisplaySettingsFragment$7$ar$this$0).commonDisplayInfoWrapper.retranslate();
            ((CellsContentManager) this.BrailleDisplaySettingsFragment$7$ar$this$0).refresh();
            ((CellsContentManager) this.BrailleDisplaySettingsFragment$7$ar$this$0).cellOnDisplayContentChanged();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DevicePreference extends Preference {
        private AlertDialog deviceDetailDialog;
        public final int index;
        public ConnectibleDeviceInfo rowDevice;

        public DevicePreference(Context context, int i, ConnectibleDeviceInfo connectibleDeviceInfo) {
            super(context);
            this.index = i;
            setWidgetLayoutResource(R.layout.listitem_bt_device);
            this.rowDevice = connectibleDeviceInfo;
            setKey(connectibleDeviceInfo.deviceAddress);
        }

        public final void dismissConnectionDeviceDetailDialog() {
            AlertDialog alertDialog = this.deviceDetailDialog;
            if (alertDialog != null && alertDialog.isShowing()) {
                this.deviceDetailDialog.dismiss();
            }
        }

        @Override // androidx.preference.Preference
        public final void onAttached() {
            super.onAttached();
            updateViewsInternal();
        }

        @Override // androidx.preference.Preference
        protected final void onClick() {
            AlertDialog.Builder title = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(getContext()).setTitle(this.rowDevice.deviceName);
            Context context = getContext();
            ArrayList arrayList = new ArrayList();
            ConnectibleDeviceInfo connectibleDeviceInfo = this.rowDevice;
            if (!connectibleDeviceInfo.isConnecting && !connectibleDeviceInfo.isConnected) {
                if (connectibleDeviceInfo.hasConnectableDevice()) {
                    arrayList.add(new WindowTrackerFactory(BrailleDisplaySettingsFragment.this.getString(R.string.bd_preference_device_item_button_connect), new ActionBarContextView.AnonymousClass1(this, 14, null)));
                }
            } else {
                arrayList.add(new WindowTrackerFactory(BrailleDisplaySettingsFragment.this.getString(R.string.bd_preference_device_item_button_disconnect), new ActionBarContextView.AnonymousClass1(this, 13, null)));
            }
            if (this.rowDevice.isRemembered) {
                arrayList.add(new WindowTrackerFactory(BrailleDisplaySettingsFragment.this.getString(R.string.bd_preference_device_item_button_forget), new ActionBarContextView.AnonymousClass1(this, 15, null)));
            }
            arrayList.add(new WindowTrackerFactory(BrailleDisplaySettingsFragment.this.getString(android.R.string.cancel), new ActionBarContextView.AnonymousClass1(this, 16, null)));
            AlertDialog create = title.setView(new InvitationView(context, arrayList)).create();
            this.deviceDetailDialog = create;
            create.show();
        }

        @Override // androidx.preference.Preference
        public final void onPrepareForRemoval() {
            super.onPrepareForRemoval();
            dismissConnectionDeviceDetailDialog();
        }

        public final void updateViewsInternal() {
            setTitle(this.rowDevice.deviceName);
            ConnectibleDeviceInfo connectibleDeviceInfo = this.rowDevice;
            boolean z = true;
            if (connectibleDeviceInfo.isConnected) {
                setSummary(R.string.bd_preference_device_item_summary_connected);
            } else if (connectibleDeviceInfo.isConnecting) {
                setSummary(R.string.bd_preference_device_item_summary_connecting);
            } else if (connectibleDeviceInfo.hasConnectableDevice()) {
                if (this.rowDevice.isRemembered) {
                    setSummary(R.string.bd_preference_device_item_summary_saved_and_available);
                } else {
                    setSummary(R.string.bd_preference_device_item_summary_available);
                }
            } else {
                setSummary(R.string.bd_preference_device_item_summary_saved_out_of_range);
                z = this.rowDevice.isRemembered;
            }
            setEnabled(z);
        }
    }

    public BrailleDisplaySettingsFragment(ComponentName componentName, Predicate<String> predicate) {
        this.controllingService = componentName;
        this.deviceNameFilter = predicate;
    }

    private Pair<List<ConnectibleDeviceInfo>, Boolean> buildDevicesPair() {
        ConnectibleDeviceInfo createOutOfRangeRememberedDevice;
        ArrayList arrayList = new ArrayList();
        List rememberedDevices = AppCompatDelegate.Api24Impl.getRememberedDevices(getContext());
        List scannedDevicesCopy = this.aspectConnection.getScannedDevicesCopy();
        Iterator it = rememberedDevices.iterator();
        while (true) {
            int i = 1;
            if (it.hasNext()) {
                Pair pair = (Pair) it.next();
                Optional findFirst = Collection.EL.stream(scannedDevicesCopy).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(pair, 12)).findFirst();
                if (findFirst.isPresent()) {
                    ConnectableDevice connectableDevice = (ConnectableDevice) findFirst.get();
                    arrayList.add(createInRangeDevice(connectableDevice, true));
                    scannedDevicesCopy.remove(connectableDevice);
                } else if (!this.connectioneer.aspectConnection.useUsbConnection() && (createOutOfRangeRememberedDevice = createOutOfRangeRememberedDevice((String) pair.first, (String) pair.second)) != null) {
                    arrayList.add(createOutOfRangeRememberedDevice);
                }
            } else {
                arrayList.addAll((java.util.Collection) Collection.EL.stream(scannedDevicesCopy).map(new BraillePreferenceUtils$$ExternalSyntheticLambda5(this, i)).collect(Collectors.toList()));
                return new Pair<>(arrayList, Boolean.valueOf(((List) Collection.EL.stream(getDevicePreferenceList()).map(new BtConnectManager$$ExternalSyntheticLambda1(6)).collect(Collectors.toList())).equals(Collection.EL.stream(arrayList).collect(Collectors.toList()))));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void constructBannerPreference(com.android.settingslib.widget.BannerMessagePreference r8, boolean r9) {
        /*
            Method dump skipped, instructions count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment.constructBannerPreference(com.android.settingslib.widget.BannerMessagePreference, boolean):void");
    }

    private ConnectibleDeviceInfo createInRangeDevice(ConnectableDevice connectableDevice, boolean z) {
        return new ConnectibleDeviceInfo(connectableDevice.name(), connectableDevice.address(), z, this.aspectConnection.isConnectingTo(connectableDevice.address()), this.aspectConnection.isConnectedTo(connectableDevice.address()), connectableDevice);
    }

    private ConnectibleDeviceInfo createOutOfRangeRememberedDevice(String str, String str2) {
        boolean isConnectingTo = this.aspectConnection.isConnectingTo(str2);
        boolean isConnectedTo = this.aspectConnection.isConnectedTo(str2);
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (bluetoothDevice.getAddress().equals(str2)) {
                OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
                onDeviceTextDetectionLoadLogEvent.setBluetoothDevice$ar$ds(bluetoothDevice);
                return new ConnectibleDeviceInfo(str, str2, true, isConnectingTo, isConnectedTo, onDeviceTextDetectionLoadLogEvent.build());
            }
        }
        return null;
    }

    private List<DevicePreference> getDevicePreferenceList() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.connectionPreferenceCategory.getPreferenceCount(); i++) {
            Preference preference = this.connectionPreferenceCategory.getPreference(i);
            if (preference instanceof DevicePreference) {
                arrayList.add((DevicePreference) preference);
            }
        }
        return arrayList;
    }

    private List<String> getNeededAppLevelPermissions() {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 31) {
            arrayList.add("android.permission.BLUETOOTH_SCAN");
            arrayList.add("android.permission.BLUETOOTH_CONNECT");
        } else {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        return (List) Collection.EL.stream(arrayList).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(this, 11)).collect(Collectors.toList());
    }

    private boolean isGlobalLocationRequiredAndNotEnabled() {
        if (SpannableUtils$IdentifierSpan.isAtLeastQ() && !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && !BrailleInputEventIA.isGlobalLocationSettingEnabled(getContext())) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ String[] lambda$constructBannerPreference$2(int i) {
        return new String[i];
    }

    public static /* synthetic */ void lambda$onModelChanged$7(List list, DevicePreference devicePreference) {
        devicePreference.rowDevice = (ConnectibleDeviceInfo) list.get(devicePreference.index);
        devicePreference.updateViewsInternal();
    }

    public static /* synthetic */ String[] lambda$showPermissionsDialogIfNecessary$15(int i) {
        return new String[i];
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.google.common.base.Supplier, java.lang.Object] */
    private void launchHelpCenter() {
        GoogleHelp googleHelp = new GoogleHelp();
        googleHelp.fallbackSupportUri = Uri.parse(GOOGLE_HELP_URI);
        InProductHelp inProductHelp = new InProductHelp(googleHelp, null, null, 0, null, 0, null);
        inProductHelp.contentUrl = GOOGLE_HELP_URI;
        WindowTrackerFactory windowTrackerFactory = new WindowTrackerFactory((Activity) getActivity());
        if (!TextUtils.isEmpty(inProductHelp.contentUrl)) {
            int isGooglePlayServicesAvailable = windowTrackerFactory.isGooglePlayServicesAvailable();
            if (isGooglePlayServicesAvailable == 0) {
                Object obj = windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider.get();
                GoogleHelpClient googleHelpClient = (GoogleHelpClient) obj;
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(googleHelpClient.callingActivity);
                GoogleApiClient googleApiClient = ((GoogleApi) obj).wrapper;
                Bitmap bitmap = null;
                PendingResultUtil.toVoidTask(googleApiClient.enqueue(new GoogleHelpApiImpl.GoogleHelpImpl(GoogleHelpClient.googleHelpApi$ar$class_merging, googleApiClient, inProductHelp, bitmap, new WeakReference(googleHelpClient.callingActivity)) { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.2
                    final /* synthetic */ GoogleHelpApiImpl this$0;
                    final /* synthetic */ WeakReference val$callingActivityWeakRef;
                    final /* synthetic */ InProductHelp val$inProductHelp;
                    final /* synthetic */ Bitmap val$pipBitmap = null;

                    /* compiled from: PG */
                    /* renamed from: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl$2$1, reason: invalid class name */
                    /* loaded from: classes.dex */
                    final class AnonymousClass1 implements GetSyncHelpPsdRunnable$SyncHelpPsdCallback {
                        final /* synthetic */ GoogleHelpImpl GoogleHelpApiImpl$2$1$ar$this$1;
                        private final /* synthetic */ int switching_field;
                        final /* synthetic */ GoogleHelpImpl val$googleHelpImpl;
                        final /* synthetic */ IGoogleHelpService val$service;

                        public AnonymousClass1(GoogleHelpImpl googleHelpImpl, IGoogleHelpService iGoogleHelpService, GoogleHelpImpl googleHelpImpl2, int i) {
                            this.switching_field = i;
                            this.val$service = iGoogleHelpService;
                            this.val$googleHelpImpl = googleHelpImpl2;
                            this.GoogleHelpApiImpl$2$1$ar$this$1 = googleHelpImpl;
                        }

                        @Override // com.google.android.gms.googlehelp.internal.common.GetSyncHelpPsdRunnable$SyncHelpPsdCallback
                        public final void onSyncHelpPsdCollected(GoogleHelp googleHelp) {
                            if (this.switching_field != 0) {
                                try {
                                    IGoogleHelpService iGoogleHelpService = this.val$service;
                                    GoogleHelpImpl googleHelpImpl = this.GoogleHelpApiImpl$2$1$ar$this$1;
                                    final GoogleHelpApiImpl googleHelpApiImpl = ((AnonymousClass1) googleHelpImpl).this$0;
                                    final GoogleHelpImpl googleHelpImpl2 = this.val$googleHelpImpl;
                                    final WeakReference weakReference = ((AnonymousClass1) googleHelpImpl).val$callingActivityWeakRef;
                                    final Intent intent = ((AnonymousClass1) googleHelpImpl).val$helpIntent;
                                    iGoogleHelpService.processGoogleHelpAndPip(googleHelp, null, new IGoogleHelpCallbacks.Stub() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(null);
                                        }

                                        @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks.Stub
                                        public final void onGoogleHelpProcessed(GoogleHelp googleHelp2) {
                                            intent.putExtra("EXTRA_START_TICK", System.nanoTime());
                                            Activity activity = (Activity) weakReference.get();
                                            if (activity == null) {
                                                googleHelpImpl2.forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                                                return;
                                            }
                                            AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor(googleHelp2);
                                            appLifecycleMonitor.setClientVersion$ar$ds(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                                            if (appLifecycleMonitor.getTogglingData() != null) {
                                                appLifecycleMonitor.getTogglingData().pipTitle = StrictModeUtils$VmPolicyBuilderCompatS.getActionBarTitle(activity);
                                            }
                                            GoogleHelpApiImpl.startHelpActivity$ar$ds(googleHelpImpl2, activity, intent, googleHelp2);
                                        }
                                    });
                                    return;
                                } catch (RemoteException e) {
                                    Log.e("gH_GoogleHelpApiImpl", "Starting help failed!", e);
                                    ((AnonymousClass1) this.GoogleHelpApiImpl$2$1$ar$this$1).forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                                    return;
                                }
                            }
                            GoogleHelpImpl googleHelpImpl3 = this.GoogleHelpApiImpl$2$1$ar$this$1;
                            InProductHelp inProductHelp = ((AnonymousClass2) googleHelpImpl3).val$inProductHelp;
                            inProductHelp.googleHelp = googleHelp;
                            try {
                                IGoogleHelpService iGoogleHelpService2 = this.val$service;
                                final GoogleHelpApiImpl googleHelpApiImpl2 = ((AnonymousClass2) googleHelpImpl3).this$0;
                                final GoogleHelpImpl googleHelpImpl4 = this.val$googleHelpImpl;
                                final WeakReference weakReference2 = ((AnonymousClass2) googleHelpImpl3).val$callingActivityWeakRef;
                                iGoogleHelpService2.processInProductHelpAndPip(inProductHelp, null, new IGoogleHelpCallbacks.Stub() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(null);
                                    }

                                    @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks.Stub
                                    public final void onInProductHelpProcessed(InProductHelp inProductHelp2) {
                                        long nanoTime = System.nanoTime();
                                        GoogleHelp googleHelp2 = inProductHelp2.googleHelp;
                                        Intent putExtra = new Intent("com.google.android.gms.googlehelp.HELP").setPackage("com.google.android.gms").putExtra("EXTRA_START_TICK", nanoTime);
                                        AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor(googleHelp2);
                                        PendingIntent pendingIntent = ((GoogleHelp) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).customFeedbackPendingIntent;
                                        if (pendingIntent != null) {
                                            putExtra.putExtra("EXTRA_CUSTOM_FEEDBACK", pendingIntent);
                                            googleHelp2.customFeedbackPendingIntent = null;
                                        }
                                        StrictModeUtils$VmPolicyBuilderCompatS.serializeToIntentExtra$ar$ds(inProductHelp2, putExtra);
                                        Activity activity = (Activity) weakReference2.get();
                                        if (activity == null) {
                                            googleHelpImpl4.forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                                            return;
                                        }
                                        appLifecycleMonitor.setClientVersion$ar$ds(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                                        if (appLifecycleMonitor.getTogglingData() != null) {
                                            appLifecycleMonitor.getTogglingData().pipTitle = StrictModeUtils$VmPolicyBuilderCompatS.getActionBarTitle(activity);
                                        }
                                        GoogleHelpApiImpl.startHelpActivity$ar$ds(googleHelpImpl4, activity, putExtra, googleHelp2);
                                    }
                                });
                            } catch (RemoteException e2) {
                                Log.e("gH_GoogleHelpApiImpl", "Starting help failed!", e2);
                                ((AnonymousClass2) this.GoogleHelpApiImpl$2$1$ar$this$1).forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                            }
                        }
                    }

                    {
                        this.val$callingActivityWeakRef = r5;
                    }

                    @Override // com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.BaseGoogleHelpApiMethodImpl
                    protected final void doExecute$ar$ds(IGoogleHelpService iGoogleHelpService) {
                        GoogleHelp googleHelp2 = this.val$inProductHelp.googleHelp;
                        GoogleHelpApiImpl.addTrailsData$ar$ds(googleHelp2);
                        new AnonymousClass1(this, iGoogleHelpService, this, 0).onSyncHelpPsdCollected(googleHelp2);
                    }
                }));
                return;
            }
            windowTrackerFactory.handlePlayServicesUnavailable(isGooglePlayServicesAvailable, inProductHelp.googleHelp);
            return;
        }
        throw new IllegalArgumentException("The content URL must be non-empty.");
    }

    public void onModelChanged() {
        boolean z;
        boolean z2;
        boolean z3;
        int i;
        boolean z4 = this.aspectEnablement.connectioneer.controllingServiceEnabled;
        int i2 = 0;
        if (z4 && AppCompatDelegate.Api24Impl.isConnectionEnabled(getContext())) {
            z = true;
        } else {
            z = false;
        }
        constructBannerPreference(this.bannerMessagePreference, z4);
        this.enablerSwitch.setEnabled(z4);
        this.enablerSwitch.setChecked(AppCompatDelegate.Api24Impl.isConnectionEnabled(getContext()));
        if (z) {
            z2 = this.aspectConnection.connectioneer.connectManagerProxy.isScanning();
        } else {
            z2 = false;
        }
        Preference preference = this.scanPreference;
        if (Connectioneer.AspectConnection.isBluetoothOn$ar$ds() && z && !z2) {
            z3 = true;
        } else {
            z3 = false;
        }
        preference.setEnabled(z3);
        this.scanPreference.setVisible(!this.connectioneer.aspectConnection.useUsbConnection());
        Preference preference2 = this.scanPreference;
        if (true != z2) {
            i = R.string.bd_preference_scan_inactivated_title;
        } else {
            i = R.string.bd_preference_scan_activated_title;
        }
        preference2.setTitle(i);
        this.scanPreference.setSummary("");
        ProgressPreferenceCategory progressPreferenceCategory = this.connectionPreferenceCategory;
        progressPreferenceCategory.progressActive = z2;
        progressPreferenceCategory.notifyChanged();
        if (!z2 && this.scanning && this.scannedDevicesCache.isEmpty() && !this.aspectConnection.isConnectingOrConnected()) {
            Toast.makeText(getContext(), getString(R.string.bd_no_devices_found), 1).show();
        }
        this.scanning = z2;
        int i3 = 4;
        if (z) {
            Pair<List<ConnectibleDeviceInfo>, Boolean> buildDevicesPair = buildDevicesPair();
            List list = (List) buildDevicesPair.first;
            if (((Boolean) buildDevicesPair.second).booleanValue()) {
                Collection.EL.stream(getDevicePreferenceList()).forEach(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(list, i3));
            } else {
                removeAllDevicePreferencesFromConnectionCategory();
                ListIterator listIterator = list.listIterator();
                while (listIterator.hasNext()) {
                    this.connectionPreferenceCategory.addPreference$ar$ds(new DevicePreference(getContext(), listIterator.nextIndex(), (ConnectibleDeviceInfo) listIterator.next()));
                }
            }
        } else {
            removeAllDevicePreferencesFromConnectionCategory();
        }
        BrailleDisplayProperties brailleDisplayProperties = this.aspectDisplayProperties.connectioneer.displayProperties;
        Intent intent = this.keyBindingsPreference.getIntent();
        intent.putExtra("property_key", brailleDisplayProperties);
        this.keyBindingsPreference.setIntent(intent);
        AppCompatDelegateImpl.Api21Impl.setupPreferredCodePreference(getContext(), this.preferredCodesPreference, new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 3));
        if (this.currentActiveOutputCodePreference != null) {
            AppCompatDelegateImpl.Api21Impl.setupLanguageListPreference(getContext(), this.currentActiveOutputCodePreference, new BtConnectManager$$ExternalSyntheticLambda1(7), new UsbConnectManager$$ExternalSyntheticLambda0(5), new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, i3));
        }
        if (this.currentActiveInputCodePreference != null) {
            AppCompatDelegateImpl.Api21Impl.setupLanguageListPreference(getContext(), this.currentActiveInputCodePreference, new BtConnectManager$$ExternalSyntheticLambda1(5), new UsbConnectManager$$ExternalSyntheticLambda0(2), new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, i2));
        }
        updateBrailleGradeSummary();
    }

    private void populateFromPersistentStorage() {
        this.enablerSwitch.setChecked(AppCompatDelegate.Api24Impl.isConnectionEnabled(getContext()));
        this.autoConnectPreference.setChecked(AppCompatDelegate.Api24Impl.isAutoConnect(getContext()));
    }

    private void removeAllDevicePreferencesFromConnectionCategory() {
        Iterator<DevicePreference> it = getDevicePreferenceList().iterator();
        while (it.hasNext()) {
            this.connectionPreferenceCategory.removePreference$ar$ds(it.next());
        }
    }

    private void showPermissionsDialogIfNecessary() {
        this.requestPermissionLauncher.launch((String[]) Collection.EL.stream(getNeededAppLevelPermissions()).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(0)));
    }

    private void showSwitchInputCodeGestureTipDialog() {
        AppCompatDelegateImpl.Api21Impl.createTipAlertDialog(getContext(), getString(R.string.bd_switch_input_code_gesture_tip_dialog_title), getString(R.string.bd_switch_input_code_gesture_tip_dialog_message, BrailleTranslateUtils.getDotsText(getResources(), SHORTCUT_SWITCH_INPUT_CODE)), new UsbConnectManager$$ExternalSyntheticLambda0(3)).show();
    }

    private void showSwitchOutputCodeGestureTipDialog() {
        AppCompatDelegateImpl.Api21Impl.createTipAlertDialog(getContext(), getString(R.string.bd_switch_output_code_gesture_tip_dialog_title), getString(R.string.bd_switch_output_code_gesture_tip_dialog_message, BrailleTranslateUtils.getDotsText(getResources(), SHORTCUT_SWITCH_OUTPUT_CODE)), new UsbConnectManager$$ExternalSyntheticLambda0(4)).show();
    }

    public void showTroubleshootingDialog(String str) {
        String string;
        AlertDialog.Builder alertDialogBuilder = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(getContext());
        if (TextUtils.isEmpty(str)) {
            string = getContext().getString(R.string.bd_bt_connect_fail_dialog_title_without_name);
        } else {
            string = getContext().getString(R.string.bd_bt_connect_fail_dialog_title_with_name, str);
        }
        alertDialogBuilder.setTitle(string).setMessage(R.string.bd_bt_connect_fail_dialog_message).setPositiveButton(R.string.bd_bt_connect_fail_positive_button, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 7)).setNegativeButton(R.string.bd_bt_connect_fail_negative_button, (DialogInterface.OnClickListener) null).create().show();
    }

    private static void startRequestEnableBluetoothActivity(Activity activity) {
        activity.startActivity(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"));
    }

    public void updateBrailleGradeSummary() {
        int i;
        Preference preference = this.brailleGradePreference;
        if (true != BrailleUserPreferences.readContractedMode(getContext())) {
            i = R.string.bd_preference_braille_uncontracted;
        } else {
            i = R.string.bd_preference_braille_contracted;
        }
        preference.setSummary(getString(i));
    }

    /* renamed from: lambda$buildDevicesPair$12$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ ConnectibleDeviceInfo m49x12a727fa(ConnectableDevice connectableDevice) {
        return createInRangeDevice(connectableDevice, false);
    }

    /* renamed from: lambda$constructBannerPreference$1$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ void m50x9900da89(View view) {
        Context context = getContext();
        ComponentName componentName = this.controllingService;
        Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
        intent.addFlags(276856832);
        if (componentName != null) {
            SpannableUtils$IdentifierSpan.attachSettingsHighlightBundle(intent, componentName);
        }
        try {
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    /* renamed from: lambda$constructBannerPreference$3$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ void m51xf86f428b(View view) {
        this.requestPermissionLauncher.launch((String[]) Collection.EL.stream(getNeededAppLevelPermissions()).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(2)));
    }

    /* renamed from: lambda$constructBannerPreference$4$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ void m52x2826768c(View view) {
        Context context = getContext();
        String packageName = getActivity().getPackageName();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addFlags(276856832);
        intent.setData(Uri.fromParts("package", packageName, null));
        try {
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    /* renamed from: lambda$constructBannerPreference$5$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ void m53x57ddaa8d(View view) {
        startRequestEnableBluetoothActivity(getActivity());
    }

    /* renamed from: lambda$constructBannerPreference$6$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ void m54x8794de8e(View view) {
        try {
            getContext().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        } catch (Exception unused) {
        }
    }

    /* renamed from: lambda$getNeededAppLevelPermissions$14$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ boolean m55x23f9d86f(String str) {
        if (EditorInfoCompat.checkSelfPermission(getContext(), str) == -1) {
            return true;
        }
        return false;
    }

    /* renamed from: lambda$new$17$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ void m56xb96614e2(Map map) {
        this.systemPermissionDialogIsShowable = false;
        for (Map.Entry entry : map.entrySet()) {
            if (!((Boolean) entry.getValue()).booleanValue() && shouldShowRequestPermissionRationale((String) entry.getKey())) {
                this.systemPermissionDialogIsShowable = true;
                return;
            }
        }
    }

    /* renamed from: lambda$onCreatePreferences$0$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ boolean m57x5ecf6e27(Preference preference, Object obj) {
        Context context = getContext();
        AppCompatDelegate.Api24Impl.getSharedPrefs(context).edit().putBoolean("auto_connect", ((Boolean) obj).booleanValue()).apply();
        onModelChanged();
        return true;
    }

    /* renamed from: lambda$onModelChanged$10$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ boolean m58x6eb07857(Preference preference, Object obj) {
        Context context = getContext();
        if (BrailleUserPreferences.getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_bd_show_switch_input_code_gesture_tip), true)) {
            showSwitchInputCodeGestureTipDialog();
            return false;
        }
        return false;
    }

    /* renamed from: lambda$onModelChanged$8$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ boolean m59xc4f301d6(Preference preference, Object obj) {
        onModelChanged();
        return false;
    }

    /* renamed from: lambda$onModelChanged$9$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ boolean m60xf4aa35d7(Preference preference, Object obj) {
        Context context = getContext();
        if (BrailleUserPreferences.getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_bd_show_switch_output_code_gesture_tip), true)) {
            showSwitchOutputCodeGestureTipDialog();
            return false;
        }
        return false;
    }

    /* renamed from: lambda$showTroubleshootingDialog$16$com-google-android-accessibility-braille-brailledisplay-settings-BrailleDisplaySettingsFragment */
    public /* synthetic */ void m61x5d1e75c(DialogInterface dialogInterface, int i) {
        launchHelpCenter();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        getPreferenceManager().setSharedPreferencesName("braille_keyboard");
        SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.bd_preferences);
        this.bannerMessagePreference = (BannerMessagePreference) findPreference(getString(R.string.pref_key_bd_banner));
        MainSwitchPreference mainSwitchPreference = (MainSwitchPreference) findPreference(getString(R.string.pref_key_bd_enabler));
        this.enablerSwitch = mainSwitchPreference;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this, 0);
        if (!mainSwitchPreference.mSwitchChangeListeners.contains(anonymousClass1)) {
            mainSwitchPreference.mSwitchChangeListeners.add(anonymousClass1);
        }
        MainSwitchBar mainSwitchBar = mainSwitchPreference.mMainSwitchBar;
        if (mainSwitchBar != null) {
            mainSwitchBar.addOnSwitchChangeListener(anonymousClass1);
        }
        Preference findPreference = findPreference(getString(R.string.pref_key_bd_rescan));
        this.scanPreference = findPreference;
        findPreference.setOnPreferenceClickListener(new AnonymousClass2(this, 0));
        this.connectionPreferenceCategory = (ProgressPreferenceCategory) findPreference(getString(R.string.pref_key_bd_connection_category));
        Preference findPreference2 = findPreference(getString(R.string.pref_brailleime_translator_codes_preferred));
        this.preferredCodesPreference = findPreference2;
        findPreference2.setIntent(new Intent(getContext(), (Class<?>) BrailleLanguagesActivity.class));
        this.currentActiveOutputCodePreference = (ListPreference) findPreference(getString(R.string.pref_bd_output_code));
        this.currentActiveInputCodePreference = (ListPreference) findPreference(getString(R.string.pref_brailleime_translator_code));
        Preference findPreference3 = findPreference(getString(R.string.pref_braille_contracted_mode));
        this.brailleGradePreference = findPreference3;
        findPreference3.setIntent(new Intent(getContext(), (Class<?>) BrailleGradeActivity.class));
        Preference findPreference4 = findPreference(getString(R.string.pref_key_bindings_key));
        this.keyBindingsPreference = findPreference4;
        findPreference4.setIntent(new Intent(getContext(), (Class<?>) KeyBindingsActivity.class));
        SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.pref_key_bd_auto_connect));
        this.autoConnectPreference = switchPreference;
        switchPreference.setOnPreferenceChangeListener(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 2));
        findPreference(getString(R.string.pref_key_braille_elements)).setIntent(new Intent(getContext(), (Class<?>) BrailleElementsActivity.class));
        findPreference(getString(R.string.pref_key_bd_auto_scroll)).setIntent(new Intent(getContext(), (Class<?>) AutoScrollActivity.class));
        findPreference(getString(R.string.pref_key_bd_advanced)).setIntent(new Intent(getContext(), (Class<?>) AdvancedSettingsActivity.class));
        this.connectioneer = Connectioneer.getInstance$ar$class_merging$67dd8c5b_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(new WindowTrackerFactory(getContext().getApplicationContext(), this.deviceNameFilter));
        this.translatorManager = new TranslatorManager(getContext());
        showPermissionsDialogIfNecessary();
        populateFromPersistentStorage();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.connectioneer.aspectEnablement.detach$ar$ds(this.enablementCallback);
        this.connectioneer.aspectConnection.detach$ar$ds(this.connectionCallback);
        this.connectioneer.aspectDisplayProperties.detach$ar$ds(this.displayPropertyCallback);
        this.translatorManager.removeOnOutputTablesChangedListener(this.outputCodeChangedListener);
        this.translatorManager.inputCodeChangedListeners.remove(this.onInputCodeChangedListener);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.aspectEnablement = (Connectioneer.AspectEnablement) this.connectioneer.aspectEnablement.attach(this.enablementCallback);
        this.aspectConnection = (Connectioneer.AspectConnection) this.connectioneer.aspectConnection.attach(this.connectionCallback);
        this.aspectDisplayProperties = (Connectioneer.AspectDisplayProperties) this.connectioneer.aspectDisplayProperties.attach(this.displayPropertyCallback);
        this.aspectConnection.onSettingsEntered();
        this.translatorManager.inputCodeChangedListeners.add(this.onInputCodeChangedListener);
        this.translatorManager.addOnOutputTablesChangedListener(this.outputCodeChangedListener);
        onModelChanged();
    }
}
