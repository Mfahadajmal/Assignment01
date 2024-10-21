package androidx.core.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.webkit.MimeTypeMap;
import androidx.work.impl.model.WorkName;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FileProvider extends ContentProvider {
    private static final String[] COLUMNS = {"_display_name", "_size"};
    private static final File DEVICE_ROOT = new File("/");
    private static final HashMap sCache = new HashMap();
    private String mAuthority;
    private WorkName mLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Object mLock = new Object();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api21Impl {
        static File[] getExternalMediaDirs(Context context) {
            return context.getExternalMediaDirs();
        }

        public static boolean isFromSource(MotionEvent motionEvent, int i) {
            if ((motionEvent.getSource() & i) == i) {
                return true;
            }
            return false;
        }
    }

    private final WorkName getLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging() {
        WorkName workName;
        synchronized (this.mLock) {
            ContextCompat$Api21Impl.requireNonNull$ar$ds$6106c18d_0(this.mAuthority, "mAuthority is null. Did you override attachInfo and did not call super.attachInfo()?");
            if (this.mLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging == null) {
                this.mLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging = getPathStrategy$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(getContext(), this.mAuthority);
            }
            workName = this.mLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging;
        }
        return workName;
    }

    public static WorkName getPathStrategy$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(Context context, String str) {
        WorkName workName;
        HashMap hashMap = sCache;
        synchronized (hashMap) {
            workName = (WorkName) hashMap.get(str);
            if (workName == null) {
                try {
                    try {
                        workName = new WorkName(str);
                        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                        if (resolveContentProvider != null) {
                            Bundle bundle = resolveContentProvider.metaData;
                            XmlResourceParser loadXmlMetaData = resolveContentProvider.loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
                            if (loadXmlMetaData == null) {
                                throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
                            }
                            while (true) {
                                int next = loadXmlMetaData.next();
                                if (next != 1) {
                                    if (next == 2) {
                                        String name = loadXmlMetaData.getName();
                                        File file = null;
                                        String attributeValue = loadXmlMetaData.getAttributeValue(null, "name");
                                        String attributeValue2 = loadXmlMetaData.getAttributeValue(null, "path");
                                        if ("root-path".equals(name)) {
                                            file = DEVICE_ROOT;
                                        } else if ("files-path".equals(name)) {
                                            file = context.getFilesDir();
                                        } else if ("cache-path".equals(name)) {
                                            file = context.getCacheDir();
                                        } else if ("external-path".equals(name)) {
                                            file = Environment.getExternalStorageDirectory();
                                        } else if ("external-files-path".equals(name)) {
                                            File[] externalFilesDirs = context.getExternalFilesDirs(null);
                                            if (externalFilesDirs.length > 0) {
                                                file = externalFilesDirs[0];
                                            }
                                        } else if ("external-cache-path".equals(name)) {
                                            File[] externalCacheDirs = context.getExternalCacheDirs();
                                            if (externalCacheDirs.length > 0) {
                                                file = externalCacheDirs[0];
                                            }
                                        } else if ("external-media-path".equals(name)) {
                                            File[] externalMediaDirs = Api21Impl.getExternalMediaDirs(context);
                                            if (externalMediaDirs.length > 0) {
                                                file = externalMediaDirs[0];
                                            }
                                        }
                                        if (file != null) {
                                            String[] strArr = {attributeValue2};
                                            for (char c = 0; c <= 0; c = 1) {
                                                String str2 = strArr[0];
                                                if (str2 != null) {
                                                    file = new File(file, str2);
                                                }
                                            }
                                            if (!TextUtils.isEmpty(attributeValue)) {
                                                try {
                                                    ((HashMap) workName.WorkName$ar$name).put(attributeValue, file.getCanonicalFile());
                                                } catch (IOException e) {
                                                    Objects.toString(file);
                                                    throw new IllegalArgumentException("Failed to resolve canonical path for ".concat(file.toString()), e);
                                                }
                                            } else {
                                                throw new IllegalArgumentException("Name must not be empty");
                                            }
                                        } else {
                                            continue;
                                        }
                                    }
                                } else {
                                    sCache.put(str, workName);
                                    break;
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Couldn't find meta-data for provider with authority ".concat(String.valueOf(str)));
                        }
                    } catch (XmlPullParserException e2) {
                        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                    }
                } catch (IOException e3) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e3);
                }
            }
        }
        return workName;
    }

    public static String removeTrailingSlash(String str) {
        if (str.length() > 0 && str.charAt(str.length() - 1) == '/') {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    @Override // android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (!providerInfo.exported) {
            if (providerInfo.grantUriPermissions) {
                String str = providerInfo.authority.split(";")[0];
                synchronized (this.mLock) {
                    this.mAuthority = str;
                }
                HashMap hashMap = sCache;
                synchronized (hashMap) {
                    hashMap.remove(str);
                }
                return;
            }
            throw new SecurityException("Provider must grant uri permissions");
        }
        throw new SecurityException("Provider must not be exported");
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        if (getLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging().getFileForUri(uri).delete()) {
            return 1;
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        File fileForUri = getLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging().getFileForUri(uri);
        int lastIndexOf = fileForUri.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileForUri.getName().substring(lastIndexOf + 1));
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
            return "application/octet-stream";
        }
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public final String getTypeAnonymous(Uri uri) {
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        int i;
        File fileForUri = getLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging().getFileForUri(uri);
        if ("r".equals(str)) {
            i = 268435456;
        } else if (!"w".equals(str) && !"wt".equals(str)) {
            if ("wa".equals(str)) {
                i = 704643072;
            } else if ("rw".equals(str)) {
                i = 939524096;
            } else if ("rwt".equals(str)) {
                i = 1006632960;
            } else {
                throw new IllegalArgumentException("Invalid mode: ".concat(String.valueOf(str)));
            }
        } else {
            i = 738197504;
        }
        return ParcelFileDescriptor.open(fileForUri, i);
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        String str3;
        File fileForUri = getLocalPathStrategy$ar$class_merging$ar$class_merging$ar$class_merging().getFileForUri(uri);
        String queryParameter = uri.getQueryParameter("displayName");
        if (strArr == null) {
            strArr = COLUMNS;
        }
        int length = strArr.length;
        String[] strArr3 = new String[length];
        Object[] objArr = new Object[length];
        int i2 = 0;
        for (String str4 : strArr) {
            if ("_display_name".equals(str4)) {
                i = i2 + 1;
                strArr3[i2] = "_display_name";
                if (queryParameter == null) {
                    str3 = fileForUri.getName();
                } else {
                    str3 = queryParameter;
                }
                objArr[i2] = str3;
            } else if ("_size".equals(str4)) {
                i = i2 + 1;
                strArr3[i2] = "_size";
                objArr[i2] = Long.valueOf(fileForUri.length());
            }
            i2 = i;
        }
        String[] strArr4 = new String[i2];
        System.arraycopy(strArr3, 0, strArr4, 0, i2);
        Object[] objArr2 = new Object[i2];
        System.arraycopy(objArr, 0, objArr2, 0, i2);
        MatrixCursor matrixCursor = new MatrixCursor(strArr4, 1);
        matrixCursor.addRow(objArr2);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
