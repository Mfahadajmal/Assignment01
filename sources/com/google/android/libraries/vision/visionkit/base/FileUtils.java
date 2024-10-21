package com.google.android.libraries.vision.visionkit.base;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat$Api23Impl;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FileUtils {
    public FileUtils() {
    }

    public static int compositeARGBWithAlpha(int i, int i2) {
        return ColorUtils.setAlphaComponent(i, (Color.alpha(i) * i2) / PrivateKeyType.INVALID);
    }

    public static String copyAssetSubFolderSafe$ar$edu(Context context, String str, int i) {
        AssetManager assets = context.getAssets();
        String[] list = assets.list(str);
        if (list != null) {
            if (list.length == 0) {
                L.log.w("Attempted to copy files from empty asset subfolder: %s.", str, new Object[0]);
            }
            for (String str2 : list) {
                String str3 = str + "/" + str2;
                if (assets.list(str3).length > 0) {
                    copyAssetSubFolderSafe$ar$edu(context, str3, 1);
                } else {
                    AssetManager assets2 = context.getAssets();
                    if (str3.startsWith("file:///android_asset/")) {
                        str3 = str3.substring(22);
                    }
                    L.log.v(FileUtils.class, "Asset to copy: %s", str3);
                    String str4 = context.getFilesDir().getAbsolutePath() + "/" + str3;
                    InputStream open = assets2.open(str3);
                    try {
                        File file = new File(str4);
                        file.exists();
                        new File(file.getParent()).mkdirs();
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(str4);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = open.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        if (open != null) {
                            open.close();
                        }
                        L.log.v(FileUtils.class, "Copied asset to %s", str4);
                    } catch (Throwable th) {
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                }
            }
            return context.getFilesDir().getAbsolutePath() + "/" + str;
        }
        throw new FileNotFoundException("No assets exist in this folder: ".concat(String.valueOf(str)));
    }

    public static int getColor(Context context, int i, int i2) {
        Integer colorOrNull = getColorOrNull(context, i);
        return colorOrNull != null ? colorOrNull.intValue() : i2;
    }

    public static Integer getColorOrNull(Context context, int i) {
        TypedValue resolve = DrawableUtils$OutlineCompatR.resolve(context, i);
        if (resolve != null) {
            return Integer.valueOf(resolveColor(context, resolve));
        }
        return null;
    }

    public static ColorStateList getColorStateListOrNull(Context context, int i) {
        TypedValue resolve = DrawableUtils$OutlineCompatR.resolve(context, i);
        if (resolve != null) {
            if (resolve.resourceId != 0) {
                return EditorInfoCompat.getColorStateList(context, resolve.resourceId);
            }
            if (resolve.data != 0) {
                return ColorStateList.valueOf(resolve.data);
            }
            return null;
        }
        return null;
    }

    public static boolean isColorLight(int i) {
        double pow;
        double pow2;
        double pow3;
        if (i != 0) {
            double[] dArr = (double[]) ColorUtils.TEMP_ARRAY.get();
            if (dArr == null) {
                dArr = new double[3];
                ColorUtils.TEMP_ARRAY.set(dArr);
            }
            int red = Color.red(i);
            int green = Color.green(i);
            int blue = Color.blue(i);
            if (dArr.length == 3) {
                double d = red / 255.0d;
                if (d < 0.04045d) {
                    pow = d / 12.92d;
                } else {
                    pow = Math.pow((d + 0.055d) / 1.055d, 2.4d);
                }
                double[] dArr2 = dArr;
                double d2 = green / 255.0d;
                if (d2 < 0.04045d) {
                    pow2 = d2 / 12.92d;
                } else {
                    pow2 = Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
                }
                double d3 = blue / 255.0d;
                if (d3 < 0.04045d) {
                    pow3 = d3 / 12.92d;
                } else {
                    pow3 = Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
                }
                dArr2[0] = ((0.4124d * pow) + (0.3576d * pow2) + (0.1805d * pow3)) * 100.0d;
                double d4 = ((0.2126d * pow) + (0.7152d * pow2) + (0.0722d * pow3)) * 100.0d;
                dArr2[1] = d4;
                dArr2[2] = ((pow * 0.0193d) + (pow2 * 0.1192d) + (pow3 * 0.9505d)) * 100.0d;
                if (d4 / 100.0d > 0.5d) {
                    return true;
                }
                return false;
            }
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        return false;
    }

    public static int layer(int i, int i2, float f) {
        return ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i2, Math.round(Color.alpha(i2) * f)), i);
    }

    public static void playTogether(AnimatorSet animatorSet, List list) {
        int size = list.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            Animator animator = (Animator) list.get(i);
            j = Math.max(j, animator.getStartDelay() + animator.getDuration());
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 0);
        ofInt.setDuration(j);
        list.add(0, ofInt);
        animatorSet.playTogether(list);
    }

    private static int resolveColor(Context context, TypedValue typedValue) {
        if (typedValue.resourceId != 0) {
            return ContextCompat$Api23Impl.getColor(context, typedValue.resourceId);
        }
        return typedValue.data;
    }

    public static void setBadgeDrawableBounds$ar$ds(BadgeDrawable badgeDrawable, View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        badgeDrawable.setBounds(rect);
        badgeDrawable.updateBadgeCoordinates(view, null);
    }

    public FileUtils(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this(null, null);
    }

    public FileUtils(char[] cArr, byte[] bArr) {
        this();
    }

    public static int getColor(Context context, int i, String str) {
        return resolveColor(context, DrawableUtils$OutlineCompatR.resolveTypedValueOrThrow(context, i, str));
    }

    public static int getColor(View view, int i) {
        return resolveColor(view.getContext(), DrawableUtils$OutlineCompatR.resolveTypedValueOrThrow(view.getContext(), i, view.getClass().getCanonicalName()));
    }
}
