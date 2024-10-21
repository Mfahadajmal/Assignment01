package com.google.android.accessibility.accessibilitymenu.view;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService;
import com.google.android.accessibility.accessibilitymenu.PrimesController;
import com.google.android.accessibility.accessibilitymenu.activity.A11yMenuSettingsActivity;
import com.google.android.accessibility.accessibilitymenu.model.A11yMenuShortcut;
import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.android.accessibility.accessibilitymenu.utils.BrightnessUtils;
import com.google.android.accessibility.talkback.actor.search.StringMatcher$MatchResult;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.marvin.talkback.R;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yMenuAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final int largeTextSize;
    public final AccessibilityMenuService service;
    private final List shortcutDataList;
    private final OptionalMethod shortcutDrawableUtils$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public A11yMenuAdapter(AccessibilityMenuService accessibilityMenuService, List list) {
        this.service = accessibilityMenuService;
        this.shortcutDataList = list;
        this.inflater = LayoutInflater.from(accessibilityMenuService);
        this.shortcutDrawableUtils$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod(accessibilityMenuService, (byte[]) null);
        this.largeTextSize = accessibilityMenuService.getResources().getDimensionPixelOffset(R.dimen.large_label_text_size);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.shortcutDataList.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.shortcutDataList.get(i);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return ((A11yMenuShortcut) this.shortcutDataList.get(i)).shortcutId;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.grid_item, (ViewGroup) null);
        A11yMenuShortcut a11yMenuShortcut = (A11yMenuShortcut) getItem(i);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.shortcutIconBtn);
        TextView textView = (TextView) inflate.findViewById(R.id.shortcutLabel);
        if (A11yMenuSettingsActivity.A11yMenuPreferenceFragment.isLargeButtonsEnabled(this.service)) {
            ViewGroup.LayoutParams layoutParams = imageButton.getLayoutParams();
            layoutParams.width = (int) (layoutParams.width * 1.5f);
            layoutParams.height = (int) (layoutParams.height * 1.5f);
            textView.setTextSize(0, this.largeTextSize);
        }
        int i2 = a11yMenuShortcut.shortcutId;
        if (i2 == 0) {
            imageButton.setImageResource(android.R.color.transparent);
            imageButton.setBackground(null);
        } else {
            imageButton.setTag(Integer.valueOf(i2));
            imageButton.setContentDescription(this.service.getString(a11yMenuShortcut.imgContentDescription));
            textView.setText(a11yMenuShortcut.labelText);
            imageButton.setImageResource(a11yMenuShortcut.imageSrc);
            OptionalMethod optionalMethod = this.shortcutDrawableUtils$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            int i3 = a11yMenuShortcut.imageColor;
            Bitmap createBitmap = Bitmap.createBitmap(480, 480, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            int color = ((Context) optionalMethod.OptionalMethod$ar$methodName).getColor(i3);
            Paint paint = new Paint();
            paint.setColor(color);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(240.0f, 240.0f, 240.0f, paint);
            imageButton.setBackground(new LayerDrawable(new Drawable[]{new AdaptiveIconDrawable(new BitmapDrawable(((Context) optionalMethod.OptionalMethod$ar$methodName).getResources(), createBitmap), (Drawable) optionalMethod.OptionalMethod$ar$methodParams), new RippleDrawable((ColorStateList) optionalMethod.OptionalMethod$ar$returnType, null, null)}));
        }
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(R.id.shortcutIconBtn);
        imageButton2.post(new DelayedWorkTracker.AnonymousClass1(imageButton2, (TextView) inflate.findViewById(R.id.shortcutLabel), 6, (byte[]) null));
        ((ImageButton) inflate.findViewById(R.id.shortcutIconBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.accessibility.accessibilitymenu.view.A11yMenuAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                double d;
                StringMatcher$MatchResult stringMatcher$MatchResult;
                float log;
                double d2;
                float exp;
                int intValue = ((Integer) view2.getTag()).intValue();
                A11ymenuSettingsEnums$ShortcutId forNumber = A11ymenuSettingsEnums$ShortcutId.forNumber(intValue);
                AccessibilityMenuService accessibilityMenuService = A11yMenuAdapter.this.service;
                ProcessStatsCapture processStatsCapture = accessibilityMenuService.analytics$ar$class_merging$ar$class_merging$ar$class_merging;
                if (processStatsCapture != null) {
                    processStatsCapture.increaseEventTimes(forNumber.name());
                }
                accessibilityMenuService.primesController.startTimer(PrimesController.Timer.CLICK_SHORTCUT);
                try {
                    if (intValue == 6) {
                        accessibilityMenuService.performGlobalAction(5);
                    } else if (intValue == 3) {
                        accessibilityMenuService.performGlobalAction(6);
                    } else if (intValue == 2) {
                        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
                            accessibilityMenuService.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                        }
                        accessibilityMenuService.startActivityIfIntentIsSafe(new Intent("android.settings.ACCESSIBILITY_SETTINGS"), 268468224);
                    } else if (intValue == 7) {
                        accessibilityMenuService.performGlobalAction(4);
                    } else if (intValue == 8) {
                        view2.postDelayed(new WorkerKt$$ExternalSyntheticLambda0(accessibilityMenuService, 4), 100L);
                    } else if (intValue == 5) {
                        accessibilityMenuService.performGlobalAction(8);
                    } else if (intValue == 4) {
                        accessibilityMenuService.performGlobalAction(3);
                    } else if (intValue == 1) {
                        accessibilityMenuService.startActivityIfIntentIsSafe(new Intent("android.intent.action.VOICE_COMMAND"), 335544320);
                    } else {
                        int i4 = -1;
                        if (intValue != 9 && intValue != 10) {
                            if (intValue != 11) {
                                if (intValue == 12) {
                                }
                            } else if (intValue == 11) {
                                i4 = 1;
                            }
                            if (accessibilityMenuService.audioManager == null) {
                                accessibilityMenuService.audioManager = (AudioManager) accessibilityMenuService.getSystemService("audio");
                            }
                            accessibilityMenuService.audioManager.adjustStreamVolume(3, i4, 8);
                            accessibilityMenuService.a11yMenuLayout.showSnackBar(accessibilityMenuService.getString(R.string.music_volume_percentage_label, new Object[]{Integer.valueOf((int) ((100.0d / accessibilityMenuService.audioManager.getStreamMaxVolume(3)) * accessibilityMenuService.audioManager.getStreamVolume(3)))}));
                            accessibilityMenuService.primesController.stopTimer(PrimesController.Timer.CLICK_SHORTCUT, forNumber);
                        }
                        if (intValue == 9) {
                            d = 0.11d;
                        } else {
                            d = -0.11d;
                        }
                        int deviceBrightness = StringMatcher$MatchResult.getDeviceBrightness(accessibilityMenuService);
                        if (Settings.System.canWrite(accessibilityMenuService)) {
                            if (deviceBrightness != -1) {
                                StringMatcher$MatchResult stringMatcher$MatchResult2 = accessibilityMenuService.brightnessController$ar$class_merging;
                                float f = stringMatcher$MatchResult2.start;
                                float f2 = ((deviceBrightness - f) / (stringMatcher$MatchResult2.end - f)) * 12.0f;
                                if (f2 <= 1.0f) {
                                    stringMatcher$MatchResult = stringMatcher$MatchResult2;
                                    log = ((float) Math.sqrt(f2)) * 0.5f;
                                } else {
                                    stringMatcher$MatchResult = stringMatcher$MatchResult2;
                                    log = (((float) Math.log(f2 - 0.28466892f)) * 0.17883277f) + 0.5599107f;
                                }
                                double round = Math.round(((BrightnessUtils.GAMMA_SPACE_MAX + 0.0f) * log) + 0.0f);
                                double d3 = BrightnessUtils.GAMMA_SPACE_MAX;
                                double d4 = 1.0d;
                                if (round > d3) {
                                    d2 = 1.0d;
                                } else if (round < 0.0d) {
                                    d2 = 0.0d;
                                } else {
                                    d2 = (round + 0.0d) / d3;
                                }
                                double d5 = d2 + d;
                                if (d5 <= 1.0d) {
                                    if (d == -0.11d && d5 < 0.1d) {
                                        d4 = 0.0d;
                                    } else {
                                        d4 = d5;
                                    }
                                }
                                int round2 = (int) Math.round(d4 * 100.0d);
                                int i5 = BrightnessUtils.GAMMA_SPACE_MAX;
                                StringMatcher$MatchResult stringMatcher$MatchResult3 = stringMatcher$MatchResult;
                                int i6 = stringMatcher$MatchResult3.start;
                                int i7 = stringMatcher$MatchResult3.end;
                                float f3 = ((((round2 * i5) + 50) / 100) + 0.0f) / (i5 + 0.0f);
                                if (f3 <= 0.5f) {
                                    float f4 = f3 / 0.5f;
                                    exp = f4 * f4;
                                } else {
                                    exp = 0.28466892f + ((float) Math.exp((f3 - 0.5599107f) / 0.17883277f));
                                }
                                float f5 = i6;
                                int round3 = Math.round(f5 + ((i7 - f5) * (exp / 12.0f)));
                                StringMatcher$MatchResult.percentBrightness = round2;
                                Settings.System.putInt(accessibilityMenuService.getContentResolver(), "screen_brightness", round3);
                            }
                        } else {
                            Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                            intent.setData(Uri.parse("package:".concat(String.valueOf(accessibilityMenuService.getPackageName()))));
                            intent.setFlags(268435456);
                            accessibilityMenuService.startActivity(intent);
                        }
                        StringMatcher$MatchResult stringMatcher$MatchResult4 = accessibilityMenuService.brightnessController$ar$class_merging;
                        accessibilityMenuService.a11yMenuLayout.showSnackBar(accessibilityMenuService.getString(R.string.brightness_percentage_label, new Object[]{Integer.valueOf(StringMatcher$MatchResult.percentBrightness)}));
                        accessibilityMenuService.primesController.stopTimer(PrimesController.Timer.CLICK_SHORTCUT, forNumber);
                    }
                    accessibilityMenuService.a11yMenuLayout.hideMenu();
                    accessibilityMenuService.primesController.stopTimer(PrimesController.Timer.CLICK_SHORTCUT, forNumber);
                } catch (Throwable th) {
                    accessibilityMenuService.primesController.stopTimer(PrimesController.Timer.CLICK_SHORTCUT, forNumber);
                    throw th;
                }
            }
        });
        return inflate;
    }
}
