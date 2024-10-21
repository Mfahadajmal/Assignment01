package com.google.android.accessibility.braille.brailledisplay.platform;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.hardware.usb.UsbManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.support.v7.widget.DropDownListView;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManagerProxy;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbAttachedReceiver;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.ScreenOnOffReceiver;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.talkback.compositor.EventFeedback$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.function.Consumer$CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Connectioneer {
    private static Connectioneer instance;
    private final RetryingNameResolver.ResolutionResultListener connectManagerCallback$ar$class_merging$aa22a820_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ConnectManagerProxy connectManagerProxy;
    public final Context context;
    public boolean controllingServiceEnabled;
    public final RetryingNameResolver.ResolutionResultListener d2dConnectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Predicate deviceNameFilter;
    public BrailleDisplayProperties displayProperties;
    private final SharedPreferences.OnSharedPreferenceChangeListener preferencesListener;
    private final ScreenOnOffReceiver screenOnOffReceiver;
    private final RetryingNameResolver.ResolutionResultListener screenOnOffReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final WindowTrackerFactory setupWizardFinishReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final RetryingNameResolver.ResolutionResultListener setupWizardFinishReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final UsbAttachedReceiver usbAttachedReceiver;
    private final RetryingNameResolver.ResolutionResultListener usbAttachedReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean usbConnected;
    public final AspectEnablement aspectEnablement = new AspectEnablement(this);
    public final AspectConnection aspectConnection = new AspectConnection(this);
    public final AspectTraffic aspectTraffic = new AspectTraffic(this);
    public final AspectDisplayProperties aspectDisplayProperties = new AspectDisplayProperties(this);
    public final Set userDisconnectedDevices = new HashSet();
    public final Set userDeniedDevices = new HashSet();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Aspect {
        public final Connectioneer connectioneer;
        protected final List listeners = new ArrayList();

        public Aspect(Connectioneer connectioneer) {
            this.connectioneer = connectioneer;
        }

        public Aspect attach(Object obj) {
            this.listeners.add(obj);
            return this;
        }

        public void detach$ar$ds(Object obj) {
            this.listeners.remove(obj);
        }

        public final void notifyListeners(Consumer consumer) {
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                consumer.accept(it.next());
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AspectConnection extends Aspect {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface Callback {
            void onConnectFailed$ar$ds(String str);

            void onConnectHidStarted();

            void onConnectRfcommStarted();

            void onConnectableDeviceDeleted$ar$ds();

            void onConnectableDeviceSeenOrUpdated(ConnectableDevice connectableDevice);

            void onConnectionStatusChanged$ar$edu(int i, ConnectableDevice connectableDevice);

            void onDeviceListCleared();

            void onScanningChanged();
        }

        public AspectConnection(Connectioneer connectioneer) {
            super(connectioneer);
        }

        public static final boolean isBluetoothOn$ar$ds() {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                return true;
            }
            return false;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ Aspect attach(Object obj) {
            super.attach(obj);
            return this;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ void detach$ar$ds(Object obj) {
            super.detach$ar$ds(obj);
        }

        public final void disconnectFromDevice(String str) {
            if (!getCurrentlyConnectingDevice().filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(str, 5)).isPresent() && !getCurrentlyConnectedDevice().filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(str, 6)).isPresent()) {
                return;
            }
            this.connectioneer.connectManagerProxy.disconnect();
        }

        public final Optional getCurrentlyConnectedDevice() {
            return this.connectioneer.connectManagerProxy.getCurrentlyConnectedDevice();
        }

        public final Optional getCurrentlyConnectingDevice() {
            return this.connectioneer.connectManagerProxy.getCurrentlyConnectingDevice();
        }

        public final List getScannedDevicesCopy() {
            return (List) Collection.EL.stream(this.connectioneer.connectManagerProxy.getConnectableDevices()).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(this, 7)).collect(Collectors.toCollection(new EventFeedback$$ExternalSyntheticLambda0(1)));
        }

        public final boolean isConnectedTo(String str) {
            return getCurrentlyConnectedDevice().filter(new Connectioneer$AspectConnection$$ExternalSyntheticLambda7(this, str, 0)).isPresent();
        }

        public final boolean isConnectingOrConnected() {
            return this.connectioneer.isConnectingOrConnected();
        }

        public final boolean isConnectingTo(String str) {
            return getCurrentlyConnectingDevice().filter(new Connectioneer$AspectConnection$$ExternalSyntheticLambda7(this, str, 1)).isPresent();
        }

        /* renamed from: lambda$getScannedDevicesCopy$4$com-google-android-accessibility-braille-brailledisplay-platform-Connectioneer$AspectConnection */
        public final /* synthetic */ boolean m38x6f326594(ConnectableDevice connectableDevice) {
            return this.connectioneer.allowDevice(connectableDevice.name());
        }

        /* renamed from: lambda$isConnectedTo$6$com-google-android-accessibility-braille-brailledisplay-platform-Connectioneer$AspectConnection */
        public final /* synthetic */ boolean m39x95e7f418(String str, ConnectableDevice connectableDevice) {
            if (this.connectioneer.allowDevice(connectableDevice.name()) && connectableDevice.address().equals(str)) {
                return true;
            }
            return false;
        }

        /* renamed from: lambda$isConnectingTo$5$com-google-android-accessibility-braille-brailledisplay-platform-Connectioneer$AspectConnection */
        public final /* synthetic */ boolean m40xe5dfe334(String str, ConnectableDevice connectableDevice) {
            if (this.connectioneer.allowDevice(connectableDevice.name()) && connectableDevice.address().equals(str)) {
                return true;
            }
            return false;
        }

        public final void notifyConnectionStatusChanged$ar$edu(final int i, final ConnectableDevice connectableDevice) {
            notifyListeners(new Consumer() { // from class: com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Connectioneer.AspectConnection.Callback) obj).onConnectionStatusChanged$ar$edu(i, connectableDevice);
                }

                public final /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        }

        public final void notifyDeviceListCleared() {
            notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(3));
        }

        public final void notifyScanningChanged() {
            notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(0));
        }

        public final void onSettingsEntered() {
            if (this.connectioneer.isBrailleDisplayEnabled()) {
                if (isBluetoothOn$ar$ds()) {
                    Connectioneer connectioneer = this.connectioneer;
                    AppCompatDelegate.Api24Impl.syncRememberedDevice(connectioneer.context, connectioneer.connectManagerProxy.getBondedDevices());
                }
                this.connectioneer.connectManagerProxy.startSearch$ar$edu(4);
            }
        }

        public final void onUserChoseConnectDevice(ConnectableDevice connectableDevice) {
            this.connectioneer.userDisconnectedDevices.remove(connectableDevice.address());
            this.connectioneer.userDeniedDevices.remove(connectableDevice.address());
            this.connectioneer.submitConnectionRequest$ar$edu(connectableDevice, 1);
        }

        public final boolean useUsbConnection() {
            if (this.connectioneer.connectManagerProxy.getType$ar$edu$c2cf13b1_0() == 2) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AspectDisplayProperties extends Aspect {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface Callback {
            void onDisplayPropertiesArrived(BrailleDisplayProperties brailleDisplayProperties);
        }

        public AspectDisplayProperties(Connectioneer connectioneer) {
            super(connectioneer);
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ Aspect attach(Object obj) {
            super.attach(obj);
            return this;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ void detach$ar$ds(Object obj) {
            super.detach$ar$ds(obj);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AspectEnablement extends Aspect {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface Callback {
            void onEnablementChanged();
        }

        public AspectEnablement(Connectioneer connectioneer) {
            super(connectioneer);
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ Aspect attach(Object obj) {
            super.attach(obj);
            return this;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ void detach$ar$ds(Object obj) {
            super.detach$ar$ds(obj);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AspectTraffic extends Aspect {
        public AspectTraffic(Connectioneer connectioneer) {
            super(connectioneer);
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ Aspect attach(Object obj) {
            super.attach(obj);
            return this;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.Aspect
        public final /* bridge */ /* synthetic */ void detach$ar$ds(Object obj) {
            super.detach$ar$ds(obj);
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.function.Predicate, java.lang.Object] */
    private Connectioneer(WindowTrackerFactory windowTrackerFactory) {
        OverlayDisplay.AnonymousClass1 anonymousClass1 = new OverlayDisplay.AnonymousClass1(this, 4);
        this.preferencesListener = anonymousClass1;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(this);
        this.screenOnOffReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener2 = new RetryingNameResolver.ResolutionResultListener(this);
        this.usbAttachedReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener2;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener3 = new RetryingNameResolver.ResolutionResultListener(this);
        this.setupWizardFinishReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener3;
        this.d2dConnectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
        Context context = (Context) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider;
        this.context = context;
        this.deviceNameFilter = windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
        this.screenOnOffReceiver = new ScreenOnOffReceiver(context, resolutionResultListener);
        this.usbAttachedReceiver = new UsbAttachedReceiver(context, resolutionResultListener2);
        this.setupWizardFinishReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory(context, resolutionResultListener3);
        RetryingNameResolver.ResolutionResultListener resolutionResultListener4 = new RetryingNameResolver.ResolutionResultListener(this);
        this.connectManagerCallback$ar$class_merging$aa22a820_0$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener4;
        this.connectManagerProxy = new ConnectManagerProxy(context, resolutionResultListener4);
        AppCompatDelegate.Api24Impl.getSharedPrefs(context).registerOnSharedPreferenceChangeListener(anonymousClass1);
    }

    public static Connectioneer getInstance$ar$class_merging$67dd8c5b_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(WindowTrackerFactory windowTrackerFactory) {
        if (instance == null) {
            instance = new Connectioneer(windowTrackerFactory);
        }
        return instance;
    }

    public final boolean allowDevice(String str) {
        boolean test;
        if (str == null) {
            return false;
        }
        test = this.deviceNameFilter.test(str);
        return test;
    }

    public final void autoConnectIfPossible$ar$edu(java.util.Collection collection, int i) {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "autoConnectIfPossible; reason: " + DropDownListView.Api21Impl.toStringGenerated23ac2ca7ba239ff5(i) + "; examining " + collection.size() + " devices");
        if (isConnectingOrConnected()) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "isConnectingOrConnected(): " + isConnectingOrConnected());
            return;
        }
        if (AppCompatDelegate.Api24Impl.isAutoConnect(this.context)) {
            Optional findFirst = Collection.EL.stream(collection).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(this, 4)).findFirst();
            if (findFirst.isPresent()) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "autoConnectIfPossible; found bonded remembered device ".concat(findFirst.get().toString()));
                submitConnectionRequest$ar$edu((ConnectableDevice) findFirst.get(), i);
            }
        }
    }

    public final void autoConnectIfPossibleToBondedDevice$ar$edu(int i) {
        autoConnectIfPossible$ar$edu(this.connectManagerProxy.getBondedDevices(), i);
    }

    public final void figureEnablement() {
        int i;
        if (shouldUseUsbConnection()) {
            i = 2;
        } else {
            i = 1;
        }
        this.connectManagerProxy.switchTo$ar$edu(i);
        if (isBrailleDisplayEnabled()) {
            if (AspectConnection.isBluetoothOn$ar$ds()) {
                AppCompatDelegate.Api24Impl.syncRememberedDevice(this.context, this.connectManagerProxy.getBondedDevices());
            }
            autoConnectIfPossibleToBondedDevice$ar$edu(4);
            this.screenOnOffReceiver.registerSelf$ar$ds();
            this.usbAttachedReceiver.registerSelf$ar$ds();
            WindowTrackerFactory windowTrackerFactory = this.setupWizardFinishReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            ((Context) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).getContentResolver().registerContentObserver(Settings.Secure.getUriFor("user_setup_complete"), false, (ContentObserver) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider);
            this.connectManagerProxy.onStart();
        } else {
            this.screenOnOffReceiver.unregisterSelf();
            this.usbAttachedReceiver.unregisterSelf();
            WindowTrackerFactory windowTrackerFactory2 = this.setupWizardFinishReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            ((Context) windowTrackerFactory2.WindowTrackerFactory$ar$executorProvider).getContentResolver().unregisterContentObserver((ContentObserver) windowTrackerFactory2.WindowTrackerFactory$ar$handlerProvider);
            this.connectManagerProxy.onStop();
            this.connectManagerProxy.switchTo$ar$edu(1);
            this.userDisconnectedDevices.clear();
            this.userDeniedDevices.clear();
        }
        this.aspectEnablement.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(4));
    }

    public final boolean isBrailleDisplayEnabled() {
        boolean isConnectionEnabled = AppCompatDelegate.Api24Impl.isConnectionEnabled(this.context);
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "serviceEnabled: " + this.controllingServiceEnabled + ", userSettingEnabled: " + isConnectionEnabled);
        if (this.controllingServiceEnabled && isConnectionEnabled) {
            return true;
        }
        return false;
    }

    public final boolean isConnectingOrConnected() {
        if (!this.connectManagerProxy.isConnecting() && !this.connectManagerProxy.isConnected()) {
            return false;
        }
        return true;
    }

    public final void onServiceEnabledChanged(boolean z) {
        this.controllingServiceEnabled = z;
        figureEnablement();
    }

    public final boolean shouldUseUsbConnection() {
        return Collection.EL.stream(((UsbManager) this.context.getSystemService("usb")).getDeviceList().values()).anyMatch(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(this, 2));
    }

    public final void submitConnectionRequest$ar$edu(ConnectableDevice connectableDevice, int i) {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "submitConnectionRequest to " + String.valueOf(connectableDevice) + ", reason:" + DropDownListView.Api21Impl.toStringGenerated23ac2ca7ba239ff5(i));
        if (!this.aspectConnection.isConnectedTo(connectableDevice.address()) && !this.aspectConnection.isConnectingTo(connectableDevice.address())) {
            this.connectManagerProxy.disconnect();
            this.aspectConnection.notifyConnectionStatusChanged$ar$edu(3, connectableDevice);
            ConnectManagerProxy connectManagerProxy = this.connectManagerProxy;
            boolean z = true;
            if (i != 1) {
                z = false;
            }
            connectManagerProxy.connect(connectableDevice, z);
            return;
        }
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "submitConnectionRequest ignored because already connecting or connected to " + connectableDevice.name() + ": " + connectableDevice.address());
    }
}
