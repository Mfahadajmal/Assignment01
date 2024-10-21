package androidx.core.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class KeyEventDispatcher {
    private static boolean sActionBarFieldsFetched = false;
    private static Method sActionBarOnMenuKeyMethod = null;
    private static boolean sDialogFieldsFetched = false;
    private static Field sDialogKeyListenerField;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Component {
        boolean superDispatchKeyEvent(KeyEvent keyEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0062, code lost:
    
        if (((java.lang.Boolean) r8).booleanValue() != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean dispatchKeyEvent(androidx.core.view.KeyEventDispatcher.Component r7, android.view.View r8, android.view.Window.Callback r9, android.view.KeyEvent r10) {
        /*
            r0 = 0
            if (r7 != 0) goto L5
            goto Lc9
        L5:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 28
            if (r1 < r2) goto L10
            boolean r7 = r7.superDispatchKeyEvent(r10)
            return r7
        L10:
            boolean r1 = r9 instanceof android.app.Activity
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L83
            android.app.Activity r9 = (android.app.Activity) r9
            r9.onUserInteraction()
            android.view.Window r7 = r9.getWindow()
            r8 = 8
            boolean r8 = r7.hasFeature(r8)
            if (r8 == 0) goto L65
            android.app.ActionBar r8 = r9.getActionBar()
            int r1 = r10.getKeyCode()
            r4 = 82
            if (r1 != r4) goto L65
            if (r8 == 0) goto L65
            boolean r1 = androidx.core.view.KeyEventDispatcher.sActionBarFieldsFetched
            if (r1 != 0) goto L4d
            java.lang.Class r1 = r8.getClass()     // Catch: java.lang.NoSuchMethodException -> L4b
            java.lang.String r4 = "onMenuKeyEvent"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch: java.lang.NoSuchMethodException -> L4b
            java.lang.Class<android.view.KeyEvent> r6 = android.view.KeyEvent.class
            r5[r0] = r6     // Catch: java.lang.NoSuchMethodException -> L4b
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L4b
            androidx.core.view.KeyEventDispatcher.sActionBarOnMenuKeyMethod = r1     // Catch: java.lang.NoSuchMethodException -> L4b
        L4b:
            androidx.core.view.KeyEventDispatcher.sActionBarFieldsFetched = r3
        L4d:
            java.lang.reflect.Method r1 = androidx.core.view.KeyEventDispatcher.sActionBarOnMenuKeyMethod
            if (r1 == 0) goto L65
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L65
            r4[r0] = r10     // Catch: java.lang.Throwable -> L65
            java.lang.Object r8 = r1.invoke(r8, r4)     // Catch: java.lang.Throwable -> L65
            if (r8 != 0) goto L5c
            goto L65
        L5c:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L65
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L65
            if (r8 == 0) goto L65
            goto L6b
        L65:
            boolean r8 = r7.superDispatchKeyEvent(r10)
            if (r8 == 0) goto L6d
        L6b:
            r0 = r3
            goto Lc9
        L6d:
            android.view.View r7 = r7.getDecorView()
            boolean r8 = androidx.core.view.ViewCompat.dispatchUnhandledKeyEventBeforeCallback(r7, r10)
            if (r8 == 0) goto L78
            goto L6b
        L78:
            if (r7 == 0) goto L7e
            android.view.KeyEvent$DispatcherState r2 = r7.getKeyDispatcherState()
        L7e:
            boolean r0 = r10.dispatch(r9, r2, r9)
            goto Lc9
        L83:
            boolean r1 = r9 instanceof android.app.Dialog
            if (r1 == 0) goto Lbb
            android.app.Dialog r9 = (android.app.Dialog) r9
            android.content.DialogInterface$OnKeyListener r7 = getDialogKeyListenerPre28(r9)
            if (r7 == 0) goto L9a
            int r8 = r10.getKeyCode()
            boolean r7 = r7.onKey(r9, r8, r10)
            if (r7 == 0) goto L9a
            goto L6b
        L9a:
            android.view.Window r7 = r9.getWindow()
            boolean r8 = r7.superDispatchKeyEvent(r10)
            if (r8 == 0) goto La5
            goto L6b
        La5:
            android.view.View r7 = r7.getDecorView()
            boolean r8 = androidx.core.view.ViewCompat.dispatchUnhandledKeyEventBeforeCallback(r7, r10)
            if (r8 == 0) goto Lb0
            goto L6b
        Lb0:
            if (r7 == 0) goto Lb6
            android.view.KeyEvent$DispatcherState r2 = r7.getKeyDispatcherState()
        Lb6:
            boolean r0 = r10.dispatch(r9, r2, r9)
            goto Lc9
        Lbb:
            if (r8 == 0) goto Lc3
            boolean r8 = androidx.core.view.ViewCompat.dispatchUnhandledKeyEventBeforeCallback(r8, r10)
            if (r8 != 0) goto L6b
        Lc3:
            boolean r7 = r7.superDispatchKeyEvent(r10)
            if (r7 != 0) goto Lca
        Lc9:
            return r0
        Lca:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.KeyEventDispatcher.dispatchKeyEvent(androidx.core.view.KeyEventDispatcher$Component, android.view.View, android.view.Window$Callback, android.view.KeyEvent):boolean");
    }

    private static DialogInterface.OnKeyListener getDialogKeyListenerPre28(Dialog dialog) {
        if (!sDialogFieldsFetched) {
            try {
                Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                sDialogKeyListenerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            sDialogFieldsFetched = true;
        }
        Field field = sDialogKeyListenerField;
        if (field != null) {
            try {
                return (DialogInterface.OnKeyListener) field.get(dialog);
            } catch (IllegalAccessException unused2) {
                return null;
            }
        }
        return null;
    }
}
