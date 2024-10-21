package org.chromium.base;

import _COROUTINE._BOUNDARY;
import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Matcher;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JniAndroid {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UncaughtExceptionException extends RuntimeException {
        public UncaughtExceptionException(String str, Throwable th) {
            super("Native stack trace:" + System.lineSeparator() + str, th);
        }
    }

    private JniAndroid() {
    }

    private static Throwable handleException(Throwable th, String str) {
        try {
            Log.e("cr_".concat("JniAndroid"), "Handling uncaught Java exception", th);
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), new UncaughtExceptionException(str, th));
            Log.e("cr_".concat("JniAndroid"), "Global uncaught exception handler did not terminate the process.");
            return null;
        } catch (OutOfMemoryError unused) {
            return null;
        } catch (Throwable th2) {
            Log.e("cr_".concat("JniAndroid"), "Exception in uncaught exception handler.", th2);
            return th2;
        }
    }

    private static String sanitizedStacktraceForUnhandledException(Throwable th) {
        try {
            try {
                String stackTraceString = Log.getStackTraceString(th);
                if (TextUtils.isEmpty(stackTraceString)) {
                    return "";
                }
                String[] split = stackTraceString.split("\\n");
                for (int i = 0; i < split.length; i++) {
                    if (!split[i].startsWith("\tat ")) {
                        String str = split[i];
                        if (!PiiElider.NOT_URLS_PATTERN.matcher(str).find()) {
                            StringBuilder sb = new StringBuilder(str);
                            Matcher matcher = PiiElider.WEB_URL.matcher(sb);
                            int i2 = 0;
                            while (matcher.find(i2)) {
                                int start = matcher.start();
                                int end = matcher.end();
                                String substring = sb.substring(start, end);
                                String[] strArr = PiiElider.APP_NAMESPACE;
                                int length = strArr.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 < 3) {
                                        if (substring.startsWith(strArr[i3])) {
                                            break;
                                        }
                                        i3++;
                                    } else {
                                        String[] strArr2 = PiiElider.SYSTEM_NAMESPACE;
                                        int length2 = strArr2.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 < 11) {
                                                if (substring.startsWith(strArr2[i4])) {
                                                    break;
                                                }
                                                i4++;
                                            } else if (!PiiElider.isClassName(substring)) {
                                                int lastIndexOf = substring.lastIndexOf(".");
                                                if (lastIndexOf != -1 && PiiElider.isClassName(substring.substring(0, lastIndexOf))) {
                                                }
                                                sb.replace(start, end, "HTTP://WEBADDRESS.ELIDED");
                                                i2 = start + 24;
                                                matcher = PiiElider.WEB_URL.matcher(sb);
                                            }
                                        }
                                    }
                                }
                                i2 = end;
                            }
                            str = sb.toString();
                        }
                        split[i] = str;
                    }
                }
                return TextUtils.join("\n", split);
            } catch (Throwable th2) {
                return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(Log.getStackTraceString(th2), "Error while getting stack trace: ");
            }
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }
}
