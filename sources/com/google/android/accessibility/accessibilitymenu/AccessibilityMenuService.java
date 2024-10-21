package com.google.android.accessibility.accessibilitymenu;

import android.accessibilityservice.AccessibilityService;
import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.accessibilitymenu.analytics.MenuJobService;
import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$A11ymenuSettings;
import com.google.android.accessibility.accessibilitymenu.view.A11yMenuOverlayLayout;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda8;
import com.google.android.accessibility.talkback.actor.search.StringMatcher$MatchResult;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor;
import com.google.android.accessibility.utils.monitor.ScreenMonitor;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AccessibilityMenuService extends AccessibilityService implements View.OnTouchListener, ScreenMonitor.ScreenStateChangeListener, AccessibilityButtonMonitor.AccessibilityButtonMonitorCallback {
    public static AccessibilityMenuService instance;
    public A11yMenuOverlayLayout a11yMenuLayout;
    private AccessibilityButtonMonitor accessibilityButtonMonitor;
    public ProcessStatsCapture analytics$ar$class_merging$ar$class_merging$ar$class_merging;
    public AudioManager audioManager;
    public StringMatcher$MatchResult brightnessController$ar$class_merging;
    public DisplayManager displayManager;
    private SharedPreferences prefs;
    public PrimesController primesController;
    private ScreenMonitor screenMonitor;
    private long lastTimeTouchedOutside = 0;
    private final Handler handler = new Handler();
    private final Runnable onConfigChangedRunnable = new WorkerKt$$ExternalSyntheticLambda0(this, 5, null);
    final DisplayManager.DisplayListener displayListener = new DisplayManager.DisplayListener() { // from class: com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService.2
        int rotation;

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayChanged(int i) {
            Display display = AccessibilityMenuService.this.displayManager.getDisplay(0);
            if (this.rotation != display.getRotation()) {
                this.rotation = display.getRotation();
                A11yMenuOverlayLayout a11yMenuOverlayLayout = AccessibilityMenuService.this.a11yMenuLayout;
                if (a11yMenuOverlayLayout.layout != null && a11yMenuOverlayLayout.layoutParameter != null) {
                    a11yMenuOverlayLayout.updateLayoutPosition();
                    a11yMenuOverlayLayout.windowManager.updateViewLayout(a11yMenuOverlayLayout.layout, a11yMenuOverlayLayout.layoutParameter);
                }
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i) {
        }
    };
    private final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 1, null);

    @Override // com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor.AccessibilityButtonMonitorCallback
    public final void onAccessibilityButtonClicked() {
        int i;
        if (!ScreenMonitor.isDeviceLocked(instance) && SystemClock.uptimeMillis() - this.lastTimeTouchedOutside > 200) {
            ViewGroup viewGroup = this.a11yMenuLayout.layout;
            if (viewGroup.getVisibility() == 0) {
                i = 8;
            } else {
                i = 0;
            }
            viewGroup.setVisibility(i);
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        boolean hasCallbacks;
        hasCallbacks = this.handler.hasCallbacks(this.onConfigChangedRunnable);
        if (hasCallbacks) {
            this.handler.removeCallbacks(this.onConfigChangedRunnable);
        }
        this.handler.postDelayed(this.onConfigChangedRunnable, 100L);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.primesController = new PrimesController();
        Application application = getApplication();
        ClearcutMetricTransmitter.Builder builder = new ClearcutMetricTransmitter.Builder();
        builder.context = application;
        builder.logSource = "A11Y_MENU_ANDROID_PRIMES";
        Primes.initialize$ar$class_merging$fd7e8a43_0$ar$ds(BatteryMetricService.newInstance$ar$class_merging(application, new PrimesController$$ExternalSyntheticLambda8(builder.build(), application, 1)));
        Primes.get().startMemoryMonitor();
        Primes.get().startCrashMonitor();
        setTheme(R.style.ServiceTheme);
        AccessibilityButtonMonitor accessibilityButtonMonitor = new AccessibilityButtonMonitor(this);
        this.accessibilityButtonMonitor = accessibilityButtonMonitor;
        accessibilityButtonMonitor.initAccessibilityButton(this);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        boolean hasCallbacks;
        ProcessStatsCapture processStatsCapture = this.analytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (processStatsCapture != null) {
            processStatsCapture.sendAccessbilityMenuLogs();
        }
        hasCallbacks = this.handler.hasCallbacks(this.onConfigChangedRunnable);
        if (hasCallbacks) {
            this.handler.removeCallbacks(this.onConfigChangedRunnable);
        }
        AccessibilityButtonMonitor accessibilityButtonMonitor = this.accessibilityButtonMonitor;
        if (accessibilityButtonMonitor != null) {
            accessibilityButtonMonitor.shutdown();
        }
        super.onDestroy();
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected final boolean onKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            this.a11yMenuLayout.hideMenu();
            return false;
        }
        return false;
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected final void onServiceConnected() {
        this.a11yMenuLayout = new A11yMenuOverlayLayout(this, this.primesController);
        ScreenMonitor screenMonitor = new ScreenMonitor((PowerManager) getSystemService("power"), this);
        this.screenMonitor = screenMonitor;
        registerReceiver(screenMonitor, ScreenMonitor.SCREEN_CHANGE_FILTER);
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(this);
        this.prefs = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
        this.brightnessController$ar$class_merging = new StringMatcher$MatchResult(this);
        try {
            this.analytics$ar$class_merging$ar$class_merging$ar$class_merging = new ProcessStatsCapture(this, null);
        } catch (IllegalStateException unused) {
            LogUtils.w("A11yMenuService", "Failed to create analytics instance", new Object[0]);
        }
        ProcessStatsCapture processStatsCapture = this.analytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (processStatsCapture != null) {
            int i = A11ymenuSettingsEnums$A11ymenuSettings.A11YMENU_SETTINGS$ar$edu;
            String stringGeneratedc97e382a2b02f4b7 = A11ymenuSettingsEnums$A11ymenuSettings.toStringGeneratedc97e382a2b02f4b7(i);
            if (i != 0) {
                processStatsCapture.increaseEventTimes(stringGeneratedc97e382a2b02f4b7);
                ProcessStatsCapture processStatsCapture2 = this.analytics$ar$class_merging$ar$class_merging$ar$class_merging;
                ProcessStatsCapture processStatsCapture3 = MenuJobService.menuJobAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
                if (processStatsCapture2 != null) {
                    MenuJobService.menuJobAnalytics$ar$class_merging$ar$class_merging$ar$class_merging = processStatsCapture2;
                    ((JobScheduler) getSystemService("jobscheduler")).schedule(new JobInfo.Builder(0, new ComponentName(this, (Class<?>) MenuJobService.class)).setPeriodic(86400000L).build());
                }
            } else {
                throw null;
            }
        }
        DisplayManager displayManager = (DisplayManager) getSystemService("display");
        this.displayManager = displayManager;
        displayManager.registerDisplayListener(this.displayListener, null);
        instance = this;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 4 && this.a11yMenuLayout.hideMenu()) {
            this.lastTimeTouchedOutside = SystemClock.uptimeMillis();
            return false;
        }
        return false;
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        unregisterReceiver(this.screenMonitor);
        this.prefs.unregisterOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
        instance = null;
        return super.onUnbind(intent);
    }

    @Override // com.google.android.accessibility.utils.monitor.ScreenMonitor.ScreenStateChangeListener
    public final void screenTurnedOff() {
        this.a11yMenuLayout.hideMenu();
    }

    public final void startActivityIfIntentIsSafe(Intent intent, int i) {
        if (!getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            intent.setFlags(i);
            startActivity(intent);
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override // com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor.AccessibilityButtonMonitorCallback
    public final void onConfirmSupportability(boolean z) {
    }
}
