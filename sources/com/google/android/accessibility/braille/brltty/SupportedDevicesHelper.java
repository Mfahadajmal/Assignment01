package com.google.android.accessibility.braille.brltty;

import com.google.android.accessibility.talkback.trainingcommon.tv.TvPageConfig;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableMap;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SupportedDevicesHelper {
    public static final List supportedDevices;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NameRegexSupportedDevice {
        private final boolean connectSecurely;
        private final String driverCode;
        private final Map friendlyKeyNames;
        private final Pattern[] nameRegexes;

        public NameRegexSupportedDevice(String str, boolean z, Map map, Pattern... patternArr) {
            this.driverCode = str;
            this.connectSecurely = z;
            this.friendlyKeyNames = map;
            this.nameRegexes = patternArr;
        }

        public final DeviceInfo match(String str) {
            String str2;
            Object obj;
            int i = 0;
            while (true) {
                Pattern[] patternArr = this.nameRegexes;
                if (i >= patternArr.length) {
                    return null;
                }
                Pattern pattern = patternArr[i];
                if (pattern.matcher(str).lookingAt()) {
                    TvPageConfig.Builder builder = new TvPageConfig.Builder(null, null);
                    builder.summary = this.driverCode;
                    String pattern2 = pattern.toString();
                    if (pattern2 != null) {
                        builder.title = pattern2;
                        builder.TvPageConfig$Builder$ar$image = ImmutableMap.copyOf(this.friendlyKeyNames);
                        boolean z = this.connectSecurely;
                        builder.enabled = z;
                        builder.set$0 = (byte) 1;
                        String str3 = builder.summary;
                        if (str3 != null && (str2 = builder.title) != null && (obj = builder.TvPageConfig$Builder$ar$image) != null) {
                            return new DeviceInfo(str3, str2, z, (ImmutableMap) obj);
                        }
                        StringBuilder sb = new StringBuilder();
                        if (builder.summary == null) {
                            sb.append(" driverCode");
                        }
                        if (builder.title == null) {
                            sb.append(" modelName");
                        }
                        if (builder.set$0 == 0) {
                            sb.append(" connectSecurely");
                        }
                        if (builder.TvPageConfig$Builder$ar$image == null) {
                            sb.append(" friendlyKeyNames");
                        }
                        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                    }
                    throw new NullPointerException("Null modelName");
                }
                i++;
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.driverCode);
            int i = 0;
            while (true) {
                Pattern[] patternArr = this.nameRegexes;
                if (i < patternArr.length) {
                    Pattern pattern = patternArr[i];
                    sb.append(" ");
                    sb.append(pattern);
                    i++;
                } else {
                    return sb.toString();
                }
            }
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        ApplicationModule applicationModule = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule.dots6$ar$ds();
        applicationModule.add$ar$ds$915d421d_0("Shift", R.string.key_BP_Shift);
        applicationModule.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        applicationModule.add$ar$ds$915d421d_0("Control", R.string.key_BP_Control);
        applicationModule.add$ar$ds$915d421d_0("JoystickLeft", R.string.key_JoystickLeft);
        applicationModule.add$ar$ds$915d421d_0("JoystickRight", R.string.key_JoystickRight);
        applicationModule.add$ar$ds$915d421d_0("JoystickUp", R.string.key_JoystickUp);
        applicationModule.add$ar$ds$915d421d_0("JoystickDown", R.string.key_JoystickDown);
        applicationModule.add$ar$ds$915d421d_0("JoystickEnter", R.string.key_JoystickCenter);
        applicationModule.add$ar$ds$915d421d_0("ScrollLeft", R.string.key_BP_ScrollLeft);
        applicationModule.add$ar$ds$915d421d_0("ScrollRight", R.string.key_BP_ScrollRight);
        arrayList.add(new NameRegexSupportedDevice("vo", true, applicationModule.build(), Pattern.compile("EL12-")));
        ApplicationModule applicationModule2 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule2.dots8$ar$ds();
        applicationModule2.add$ar$ds$915d421d_0("Switch1Left", R.string.key_esys_SwitchLeft);
        applicationModule2.add$ar$ds$915d421d_0("Switch1Right", R.string.key_esys_SwitchRight);
        applicationModule2.dualJoysticks$ar$ds();
        applicationModule2.add$ar$ds$915d421d_0("Backspace", R.string.key_Backspace);
        applicationModule2.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        applicationModule2.add$ar$ds$915d421d_0("RoutingKey1", R.string.key_Routing);
        arrayList.add(new NameRegexSupportedDevice("eu", true, applicationModule2.build(), Pattern.compile("Esys-")));
        ApplicationModule applicationModule3 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule3.dots8$ar$ds();
        applicationModule3.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        applicationModule3.add$ar$ds$915d421d_0("PanLeft", R.string.key_pan_left);
        applicationModule3.add$ar$ds$915d421d_0("PanRight", R.string.key_pan_right);
        applicationModule3.add$ar$ds$915d421d_0("LeftWheelPress", R.string.key_focus_LeftWheelPress);
        applicationModule3.add$ar$ds$915d421d_0("LeftWheelDown", R.string.key_focus_LeftWheelDown);
        applicationModule3.add$ar$ds$915d421d_0("LeftWheelUp", R.string.key_focus_LeftWheelUp);
        applicationModule3.add$ar$ds$915d421d_0("RightWheelPress", R.string.key_focus_RightWheelPress);
        applicationModule3.add$ar$ds$915d421d_0("RightWheelDown", R.string.key_focus_RightWheelDown);
        applicationModule3.add$ar$ds$915d421d_0("RightWheelUp", R.string.key_focus_RightWheelUp);
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule3.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("LeftShift", R.string.key_focus_LeftShift);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("RightShift", R.string.key_focus_RightShift);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("LeftGdf", R.string.key_focus_LeftGdf);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("RightGdf", R.string.key_focus_RightGdf);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("LeftRockerUp", R.string.key_focus_LeftRockerUp);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("LeftRockerDown", R.string.key_focus_LeftRockerDown);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("RightRockerUp", R.string.key_focus_RightRockerUp);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.add$ar$ds$915d421d_0("RightRockerDown", R.string.key_focus_RightRockerDown);
        arrayList.add(new NameRegexSupportedDevice("fs", true, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.build(), Pattern.compile("Focus (40|14|80) BT"), Pattern.compile("FOCUS")));
        ApplicationModule applicationModule4 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule4.dots8$ar$ds();
        applicationModule4.add$ar$ds$915d421d_0("Left", R.string.key_JoystickLeft);
        applicationModule4.add$ar$ds$915d421d_0("Right", R.string.key_JoystickRight);
        applicationModule4.add$ar$ds$915d421d_0("Up", R.string.key_JoystickUp);
        applicationModule4.add$ar$ds$915d421d_0("Down", R.string.key_JoystickDown);
        applicationModule4.add$ar$ds$915d421d_0("Press", R.string.key_JoystickCenter);
        applicationModule4.add$ar$ds$915d421d_0("ThumbLeft", R.string.key_pan_left);
        applicationModule4.add$ar$ds$915d421d_0("ThumbRight", R.string.key_pan_right);
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2 = applicationModule4.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Power", R.string.key_brailliant_Power);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Display1", R.string.key_brailliant_Display1);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Display2", R.string.key_brailliant_Display2);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Display3", R.string.key_brailliant_Display3);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Display4", R.string.key_brailliant_Display4);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Display5", R.string.key_brailliant_Display5);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Display6", R.string.key_brailliant_Display6);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Thumb1", R.string.key_brailliant_Thumb1);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Thumb2", R.string.key_brailliant_Thumb2);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Thumb3", R.string.key_brailliant_Thumb3);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.add$ar$ds$915d421d_0("Thumb4", R.string.key_brailliant_Thumb4);
        arrayList.add(new NameRegexSupportedDevice("hw", false, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging2.build(), Pattern.compile("Brailliant BI"), Pattern.compile("APH Mantis"), Pattern.compile("APH Chameleon"), Pattern.compile("NLS eReader H")));
        ApplicationModule applicationModule5 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule5.dots8$ar$ds();
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3 = applicationModule5.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.add$ar$ds$915d421d_0("F1", R.string.key_F1);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.add$ar$ds$915d421d_0("F2", R.string.key_F2);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.add$ar$ds$915d421d_0("F3", R.string.key_F3);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.add$ar$ds$915d421d_0("F4", R.string.key_F4);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.add$ar$ds$915d421d_0("Backward", R.string.key_Backward);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.add$ar$ds$915d421d_0("Forward", R.string.key_Forward);
        arrayList.add(new NameRegexSupportedDevice("hm", false, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging3.build(), Pattern.compile("Hansone|HansoneXL|SmartBeetle")));
        ApplicationModule applicationModule6 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule6.dots8$ar$ds();
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4 = applicationModule6.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("F1", R.string.key_F1);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("F2", R.string.key_F2);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("F3", R.string.key_F3);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("F4", R.string.key_F4);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("LeftScrollUp", R.string.key_LeftScrollUp);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("LeftScrollDown", R.string.key_LeftScrollDown);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("RightScrollUp", R.string.key_RightScrollUp);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.add$ar$ds$915d421d_0("RightScrollDown", R.string.key_RightScrollDown);
        arrayList.add(new NameRegexSupportedDevice("hm", false, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging4.build(), Pattern.compile("BrailleSense|BrailleEDGE")));
        ApplicationModule applicationModule7 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule7.dots8$ar$ds();
        applicationModule7.add$ar$ds$915d421d_0("Left", R.string.key_JoystickLeft);
        applicationModule7.add$ar$ds$915d421d_0("Right", R.string.key_JoystickRight);
        applicationModule7.add$ar$ds$915d421d_0("Up", R.string.key_JoystickUp);
        applicationModule7.add$ar$ds$915d421d_0("Down", R.string.key_JoystickDown);
        applicationModule7.add$ar$ds$915d421d_0("Press", R.string.key_JoystickCenter);
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging5 = applicationModule7.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging5.add$ar$ds$915d421d_0("Display2", R.string.key_APH_AdvanceLeft);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging5.add$ar$ds$915d421d_0("Display5", R.string.key_APH_AdvanceRight);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging5.add$ar$ds$915d421d_0("B9", R.string.key_Space);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging5.add$ar$ds$915d421d_0("B10", R.string.key_Space);
        arrayList.add(new NameRegexSupportedDevice("bm", false, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging5.build(), Pattern.compile("Refreshabraille")));
        ApplicationModule applicationModule8 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule8.dots8$ar$ds();
        applicationModule8.add$ar$ds$915d421d_0("Left", R.string.key_JoystickLeft);
        applicationModule8.add$ar$ds$915d421d_0("Right", R.string.key_JoystickRight);
        applicationModule8.add$ar$ds$915d421d_0("Up", R.string.key_JoystickUp);
        applicationModule8.add$ar$ds$915d421d_0("Down", R.string.key_JoystickDown);
        applicationModule8.add$ar$ds$915d421d_0("Press", R.string.key_JoystickCenter);
        applicationModule8.add$ar$ds$915d421d_0("Display2", R.string.key_APH_AdvanceLeft);
        applicationModule8.add$ar$ds$915d421d_0("Display5", R.string.key_APH_AdvanceRight);
        applicationModule8.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        arrayList.add(new NameRegexSupportedDevice("bm", false, applicationModule8.build(), Pattern.compile("Orbit")));
        ApplicationModule applicationModule9 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule9.dots8$ar$ds();
        applicationModule9.add$ar$ds$915d421d_0("Left", R.string.key_JoystickLeft);
        applicationModule9.add$ar$ds$915d421d_0("Right", R.string.key_JoystickRight);
        applicationModule9.add$ar$ds$915d421d_0("Up", R.string.key_JoystickUp);
        applicationModule9.add$ar$ds$915d421d_0("Down", R.string.key_JoystickDown);
        applicationModule9.add$ar$ds$915d421d_0("Press", R.string.key_JoystickCenter);
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging6 = applicationModule9.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging6.add$ar$ds$915d421d_0("Display2", R.string.key_APH_AdvanceLeft);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging6.add$ar$ds$915d421d_0("Display5", R.string.key_APH_AdvanceRight);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging6.add$ar$ds$915d421d_0("B9", R.string.key_Space);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging6.add$ar$ds$915d421d_0("B10", R.string.key_Space);
        arrayList.add(new NameRegexSupportedDevice("bm", false, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging6.build(), Pattern.compile("VarioConnect")));
        ApplicationModule applicationModule10 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule10.dots8$ar$ds();
        applicationModule10.add$ar$ds$915d421d_0("Left", R.string.key_JoystickLeft);
        applicationModule10.add$ar$ds$915d421d_0("Right", R.string.key_JoystickRight);
        applicationModule10.add$ar$ds$915d421d_0("Up", R.string.key_JoystickUp);
        applicationModule10.add$ar$ds$915d421d_0("Down", R.string.key_JoystickDown);
        applicationModule10.add$ar$ds$915d421d_0("Press", R.string.key_JoystickCenter);
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging7 = applicationModule10.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging7.add$ar$ds$915d421d_0("Display2", R.string.key_APH_AdvanceLeft);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging7.add$ar$ds$915d421d_0("Display5", R.string.key_APH_AdvanceRight);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging7.add$ar$ds$915d421d_0("B9", R.string.key_Space);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging7.add$ar$ds$915d421d_0("B10", R.string.key_Space);
        arrayList.add(new NameRegexSupportedDevice("bm", false, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging7.build(), Pattern.compile("VarioUltra")));
        ApplicationModule applicationModule11 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule11.add$ar$ds$915d421d_0("Display1", R.string.key_hwg_brailliant_Display1);
        applicationModule11.add$ar$ds$915d421d_0("Display2", R.string.key_hwg_brailliant_Display2);
        applicationModule11.add$ar$ds$915d421d_0("Display3", R.string.key_hwg_brailliant_Display3);
        applicationModule11.add$ar$ds$915d421d_0("Display4", R.string.key_hwg_brailliant_Display4);
        applicationModule11.add$ar$ds$915d421d_0("Display5", R.string.key_hwg_brailliant_Display5);
        applicationModule11.add$ar$ds$915d421d_0("Display6", R.string.key_hwg_brailliant_Display6);
        arrayList.add(new NameRegexSupportedDevice("bm", false, applicationModule11.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().build(), Pattern.compile("HWG Brailliant"), Pattern.compile("NLS eReader Z")));
        ApplicationModule applicationModule12 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule12.dots8$ar$ds();
        applicationModule12.add$ar$ds$915d421d_0("LeftSpace", R.string.key_Space);
        applicationModule12.add$ar$ds$915d421d_0("RightSpace", R.string.key_Space);
        applicationModule12.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        applicationModule12.add$ar$ds$915d421d_0("LeftThumb", R.string.key_braillex_LeftThumb);
        applicationModule12.add$ar$ds$915d421d_0("RightThumb", R.string.key_braillex_RightThumb);
        applicationModule12.add$ar$ds$915d421d_0("RoutingKey1", R.string.key_Routing);
        applicationModule12.add$ar$ds$915d421d_0("BarLeft1", R.string.key_braillex_BarLeft1);
        applicationModule12.add$ar$ds$915d421d_0("BarLeft2", R.string.key_braillex_BarLeft2);
        applicationModule12.add$ar$ds$915d421d_0("BarRight1", R.string.key_braillex_BarRight1);
        applicationModule12.add$ar$ds$915d421d_0("BarRight2", R.string.key_braillex_BarRight2);
        applicationModule12.add$ar$ds$915d421d_0("BarUp1", R.string.key_braillex_BarUp1);
        applicationModule12.add$ar$ds$915d421d_0("BarUp2", R.string.key_braillex_BarUp2);
        applicationModule12.add$ar$ds$915d421d_0("BarDown1", R.string.key_braillex_BarDown1);
        applicationModule12.add$ar$ds$915d421d_0("BarDown2", R.string.key_braillex_BarDown2);
        applicationModule12.add$ar$ds$915d421d_0("LeftKeyRear", R.string.key_braillex_LeftKeyRear);
        applicationModule12.add$ar$ds$915d421d_0("LeftKeyFront", R.string.key_braillex_LeftKeyFront);
        applicationModule12.add$ar$ds$915d421d_0("RightKeyRear", R.string.key_braillex_RightKeyRear);
        applicationModule12.add$ar$ds$915d421d_0("RightKeyFront", R.string.key_braillex_RightKeyFront);
        arrayList.add(new NameRegexSupportedDevice("pm", true, applicationModule12.build(), Pattern.compile("braillex trio")));
        ApplicationModule applicationModule13 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule13.add$ar$ds$915d421d_0("ETouchLeftRear", R.string.key_albc_ETouchLeftRear);
        applicationModule13.add$ar$ds$915d421d_0("ETouchRightRear", R.string.key_albc_ETouchRightRear);
        applicationModule13.add$ar$ds$915d421d_0("ETouchLeftFront", R.string.key_albc_ETouchLeftFront);
        applicationModule13.add$ar$ds$915d421d_0("ETouchRightFront", R.string.key_albc_ETouchRightFront);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadF1", R.string.key_albc_SmartpadF1);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadF2", R.string.key_albc_SmartpadF2);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadF3", R.string.key_albc_SmartpadF3);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadF4", R.string.key_albc_SmartpadF4);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadUp", R.string.key_albc_SmartpadUp);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadDown", R.string.key_albc_SmartpadDown);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadLeft", R.string.key_albc_SmartpadLeft);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadRight", R.string.key_albc_SmartpadRight);
        applicationModule13.add$ar$ds$915d421d_0("SmartpadEnter", R.string.key_albc_SmartpadEnter);
        applicationModule13.add$ar$ds$915d421d_0("ThumbLeft", R.string.key_albc_ThumbLeft);
        applicationModule13.add$ar$ds$915d421d_0("ThumbRight", R.string.key_albc_ThumbRight);
        applicationModule13.add$ar$ds$915d421d_0("ThumbUp", R.string.key_albc_ThumbUp);
        applicationModule13.add$ar$ds$915d421d_0("ThumbDown", R.string.key_albc_ThumbDown);
        applicationModule13.add$ar$ds$915d421d_0("ThumbHome", R.string.key_albc_ThumbHome);
        applicationModule13.add$ar$ds$915d421d_0("RoutingKey1", R.string.key_Routing);
        arrayList.add(new NameRegexSupportedDevice("al", false, applicationModule13.build(), Pattern.compile("Alva BC", 2)));
        ApplicationModule applicationModule14 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule14.add$ar$ds$915d421d_0("B4", R.string.key_Dot1);
        applicationModule14.add$ar$ds$915d421d_0("B3", R.string.key_Dot2);
        applicationModule14.add$ar$ds$915d421d_0("B2", R.string.key_Dot3);
        applicationModule14.add$ar$ds$915d421d_0("B1", R.string.key_Dot7);
        applicationModule14.add$ar$ds$915d421d_0("B5", R.string.key_Dot4);
        applicationModule14.add$ar$ds$915d421d_0("B6", R.string.key_Dot5);
        applicationModule14.add$ar$ds$915d421d_0("B7", R.string.key_Dot6);
        applicationModule14.add$ar$ds$915d421d_0("B8", R.string.key_Dot8);
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8 = applicationModule14.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("LeftRockerTop", R.string.key_handytech_LeftTrippleActionTop);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("LeftRockerBottom", R.string.key_handytech_LeftTrippleActionBottom);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("LeftRockerTop+LeftRockerBottom", R.string.key_handytech_LeftTrippleActionMiddle);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("RightRockerTop", R.string.key_handytech_RightTrippleActionTop);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("RightRockerBottom", R.string.key_handytech_RightTrippleActionBottom);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("RightRockerTop+RightRockerBottom", R.string.key_handytech_RightTrippleActionMiddle);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("SpaceLeft", R.string.key_handytech_LeftSpace);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("SpaceRight", R.string.key_handytech_RightSpace);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("Display1", R.string.key_hwg_brailliant_Display1);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("Display2", R.string.key_hwg_brailliant_Display2);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("Display3", R.string.key_hwg_brailliant_Display3);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("Display4", R.string.key_hwg_brailliant_Display4);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("Display5", R.string.key_hwg_brailliant_Display5);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.add$ar$ds$915d421d_0("Display6", R.string.key_hwg_brailliant_Display6);
        arrayList.add(new NameRegexSupportedDevice("ht", true, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging8.build(), Pattern.compile("(Braille Wave( BRW)?|Braillino( BL2)?|Braille Star 40( BS4)?|Easy Braille( EBR)?|Active Braille( AB4)?|Basic Braille BB[3,4,6]?)\\/[a-zA-Z][0-9]-[0-9]{5}|Actilino"), Pattern.compile("(BRW|BL2|BS4|EBR|AB4|BB(3|4|6)?)\\/[a-zA-Z][0-9]-[0-9]{5}")));
        ApplicationModule applicationModule15 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule15.dots8$ar$ds();
        ApplicationModule routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging9 = applicationModule15.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging9.dualJoysticks$ar$ds();
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging9.add$ar$ds$915d421d_0("Backspace", R.string.key_Backspace);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging9.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging9.add$ar$ds$915d421d_0("LeftButton", R.string.key_skntk_PanLeft);
        routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging9.add$ar$ds$915d421d_0("RightButton", R.string.key_skntk_PanRight);
        arrayList.add(new NameRegexSupportedDevice("sk", false, routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging9.build(), Pattern.compile("TSM|seika")));
        ApplicationModule applicationModule16 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule16.add$ar$ds$915d421d_0("K1", R.string.key_skbdp_PanLeft);
        applicationModule16.add$ar$ds$915d421d_0("K8", R.string.key_skbdp_PanRight);
        applicationModule16.add$ar$ds$915d421d_0("K2", R.string.key_skbdp_LeftRockerLeft);
        applicationModule16.add$ar$ds$915d421d_0("K3", R.string.key_skbdp_LeftRockerRight);
        applicationModule16.add$ar$ds$915d421d_0("K4", R.string.key_skbdp_LeftLongKey);
        applicationModule16.add$ar$ds$915d421d_0("K5", R.string.key_skbdp_RightLongKey);
        applicationModule16.add$ar$ds$915d421d_0("K6", R.string.key_skbdp_RightRockerLeft);
        applicationModule16.add$ar$ds$915d421d_0("K7", R.string.key_skbdp_RightRockerRight);
        applicationModule16.add$ar$ds$915d421d_0("RoutingKey2", R.string.key_Routing);
        arrayList.add(new NameRegexSupportedDevice("sk", true, applicationModule16.routing$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().build(), Pattern.compile("TS5")));
        ApplicationModule applicationModule17 = new ApplicationModule((short[]) null, (byte[]) null);
        applicationModule17.dots8$ar$ds();
        applicationModule17.add$ar$ds$915d421d_0("Space", R.string.key_Space);
        applicationModule17.add$ar$ds$915d421d_0("PanLeft", R.string.key_pan_left);
        applicationModule17.add$ar$ds$915d421d_0("PanRight", R.string.key_pan_right);
        applicationModule17.add$ar$ds$915d421d_0("DPadUp", R.string.key_dpad_up);
        applicationModule17.add$ar$ds$915d421d_0("DPadDown", R.string.key_dpad_down);
        applicationModule17.add$ar$ds$915d421d_0("DPadLeft", R.string.key_dpad_left);
        applicationModule17.add$ar$ds$915d421d_0("DPadRight", R.string.key_dpad_right);
        applicationModule17.add$ar$ds$915d421d_0("DPadCenter", R.string.key_dpad_center);
        applicationModule17.add$ar$ds$915d421d_0("RockerUp", R.string.key_rocker_up);
        applicationModule17.add$ar$ds$915d421d_0("RockerDown", R.string.key_rocker_down);
        arrayList.add(new NameRegexSupportedDevice("hid", true, applicationModule17.build(), Pattern.compile("HID")));
        supportedDevices = DesugarCollections.unmodifiableList(arrayList);
    }

    public static DeviceInfo getDeviceInfo(String str, boolean z) {
        Iterator it = supportedDevices.iterator();
        while (it.hasNext()) {
            DeviceInfo match = ((NameRegexSupportedDevice) it.next()).match(str);
            if (match != null) {
                return match;
            }
        }
        return null;
    }
}
