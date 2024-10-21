package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    public byte[] mData;
    public int mInt1;
    public int mInt2;
    public Object mObj1;
    public Parcelable mParcelable;
    public String mString1;
    public ColorStateList mTintList;
    PorterDuff.Mode mTintMode;
    public String mTintModeStr;
    public int mType;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api23Impl {
        static Uri getUri(Object obj) {
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.getUri(obj);
            }
            try {
                return (Uri) obj.getClass().getMethod("getUri", null).invoke(obj, null);
            } catch (IllegalAccessException e) {
                Log.e("IconCompat", "Unable to get icon uri", e);
                return null;
            } catch (NoSuchMethodException e2) {
                Log.e("IconCompat", "Unable to get icon uri", e2);
                return null;
            } catch (InvocationTargetException e3) {
                Log.e("IconCompat", "Unable to get icon uri", e3);
                return null;
            }
        }

        static Drawable loadDrawable(Icon icon, Context context) {
            return icon.loadDrawable(context);
        }

        public static Icon toIcon(IconCompat iconCompat, Context context) {
            Icon createWithBitmap;
            int i = iconCompat.mType;
            String str = null;
            r3 = null;
            InputStream openInputStream = null;
            str = null;
            str = null;
            switch (i) {
                case -1:
                    return (Icon) iconCompat.mObj1;
                case 0:
                default:
                    throw new IllegalArgumentException("Unknown type");
                case 1:
                    createWithBitmap = Icon.createWithBitmap((Bitmap) iconCompat.mObj1);
                    break;
                case 2:
                    if (i == -1) {
                        Object obj = iconCompat.mObj1;
                        if (Build.VERSION.SDK_INT >= 28) {
                            str = Api28Impl.getResPackage(obj);
                        } else {
                            try {
                                str = (String) obj.getClass().getMethod("getResPackage", null).invoke(obj, null);
                            } catch (IllegalAccessException e) {
                                Log.e("IconCompat", "Unable to get icon package", e);
                            } catch (NoSuchMethodException e2) {
                                Log.e("IconCompat", "Unable to get icon package", e2);
                            } catch (InvocationTargetException e3) {
                                Log.e("IconCompat", "Unable to get icon package", e3);
                            }
                        }
                    } else if (i == 2) {
                        String str2 = iconCompat.mString1;
                        str = (str2 == null || TextUtils.isEmpty(str2)) ? ((String) iconCompat.mObj1).split(":", -1)[0] : iconCompat.mString1;
                    } else {
                        Objects.toString(iconCompat);
                        throw new IllegalStateException("called getResPackage() on ".concat(iconCompat.toString()));
                    }
                    createWithBitmap = Icon.createWithResource(str, iconCompat.mInt1);
                    break;
                case 3:
                    createWithBitmap = Icon.createWithData((byte[]) iconCompat.mObj1, iconCompat.mInt1, iconCompat.mInt2);
                    break;
                case 4:
                    createWithBitmap = Icon.createWithContentUri((String) iconCompat.mObj1);
                    break;
                case 5:
                    createWithBitmap = Api26Impl.createWithAdaptiveBitmap((Bitmap) iconCompat.mObj1);
                    break;
                case 6:
                    if (Build.VERSION.SDK_INT >= 30) {
                        createWithBitmap = Api30Impl.createWithAdaptiveBitmapContentUri(iconCompat.getUri());
                        break;
                    } else if (context != null) {
                        Uri uri = iconCompat.getUri();
                        String scheme = uri.getScheme();
                        if (!"content".equals(scheme) && !"file".equals(scheme)) {
                            try {
                                openInputStream = new FileInputStream(new File((String) iconCompat.mObj1));
                            } catch (FileNotFoundException e4) {
                                Objects.toString(uri);
                                Log.w("IconCompat", "Unable to load image from path: ".concat(String.valueOf(uri)), e4);
                            }
                        } else {
                            try {
                                openInputStream = context.getContentResolver().openInputStream(uri);
                            } catch (Exception e5) {
                                Objects.toString(uri);
                                Log.w("IconCompat", "Unable to load image from URI: ".concat(String.valueOf(uri)), e5);
                            }
                        }
                        if (openInputStream != null) {
                            createWithBitmap = Api26Impl.createWithAdaptiveBitmap(BitmapFactory.decodeStream(openInputStream));
                            break;
                        } else {
                            Uri uri2 = iconCompat.getUri();
                            Objects.toString(uri2);
                            throw new IllegalStateException("Cannot load adaptive icon from uri: ".concat(String.valueOf(uri2)));
                        }
                    } else {
                        Uri uri3 = iconCompat.getUri();
                        Objects.toString(uri3);
                        throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: ".concat(String.valueOf(uri3)));
                    }
                    break;
            }
            ColorStateList colorStateList = iconCompat.mTintList;
            if (colorStateList != null) {
                createWithBitmap.setTintList(colorStateList);
            }
            PorterDuff.Mode mode = iconCompat.mTintMode;
            if (mode != IconCompat.DEFAULT_TINT_MODE) {
                createWithBitmap.setTintMode(mode);
            }
            return createWithBitmap;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api26Impl {
        static Drawable createAdaptiveIconDrawable(Drawable drawable, Drawable drawable2) {
            return new AdaptiveIconDrawable(drawable, drawable2);
        }

        static Icon createWithAdaptiveBitmap(Bitmap bitmap) {
            return Icon.createWithAdaptiveBitmap(bitmap);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api28Impl {
        static int getResId(Object obj) {
            return ((Icon) obj).getResId();
        }

        static String getResPackage(Object obj) {
            return ((Icon) obj).getResPackage();
        }

        public static int getType(Object obj) {
            return ((Icon) obj).getType();
        }

        static Uri getUri(Object obj) {
            return ((Icon) obj).getUri();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api30Impl {
        static Icon createWithAdaptiveBitmapContentUri(Uri uri) {
            return Icon.createWithAdaptiveBitmapContentUri(uri);
        }
    }

    public IconCompat() {
        this.mType = -1;
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
    }

    public static IconCompat createWithResource(Resources resources, String str, int i) {
        str.getClass();
        if (i != 0) {
            IconCompat iconCompat = new IconCompat(null);
            iconCompat.mInt1 = i;
            if (resources != null) {
                try {
                    iconCompat.mObj1 = resources.getResourceName(i);
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.mObj1 = str;
            }
            iconCompat.mString1 = str;
            return iconCompat;
        }
        throw new IllegalArgumentException("Drawable resource ID must not be 0");
    }

    public final int getResId() {
        int i = this.mType;
        if (i == -1) {
            Object obj = this.mObj1;
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.getResId(obj);
            }
            try {
                return ((Integer) obj.getClass().getMethod("getResId", null).invoke(obj, null)).intValue();
            } catch (IllegalAccessException e) {
                Log.e("IconCompat", "Unable to get icon resource", e);
                return 0;
            } catch (NoSuchMethodException e2) {
                Log.e("IconCompat", "Unable to get icon resource", e2);
                return 0;
            } catch (InvocationTargetException e3) {
                Log.e("IconCompat", "Unable to get icon resource", e3);
                return 0;
            }
        }
        if (i == 2) {
            return this.mInt1;
        }
        toString();
        throw new IllegalStateException("called getResId() on ".concat(toString()));
    }

    public final Uri getUri() {
        int i = this.mType;
        if (i != -1) {
            if (i != 4 && i != 6) {
                toString();
                throw new IllegalStateException("called getUri() on ".concat(toString()));
            }
            return Uri.parse((String) this.mObj1);
        }
        return Api23Impl.getUri(this.mObj1);
    }

    public final String toString() {
        String str;
        if (this.mType == -1) {
            return String.valueOf(this.mObj1);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.mType) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.mType) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.mObj1).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.mObj1).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.mString1);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(getResId())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.mInt1);
                if (this.mInt2 != 0) {
                    sb.append(" off=");
                    sb.append(this.mInt2);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.mObj1);
                break;
        }
        if (this.mTintList != null) {
            sb.append(" tint=");
            sb.append(this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            sb.append(" mode=");
            sb.append(this.mTintMode);
        }
        sb.append(")");
        return sb.toString();
    }

    public IconCompat(byte[] bArr) {
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
        this.mType = 2;
    }
}
