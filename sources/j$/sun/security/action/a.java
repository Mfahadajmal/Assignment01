package j$.sun.security.action;

import java.security.PrivilegedAction;

/* loaded from: classes2.dex */
public final class a implements PrivilegedAction {
    private String a;

    /* JADX WARN: Type inference failed for: r0v1, types: [j$.sun.security.action.a, java.lang.Object, java.security.PrivilegedAction] */
    public static void a() {
        if (System.getSecurityManager() == null) {
            System.getProperty("file.encoding");
            return;
        }
        ?? obj = new Object();
        ((a) obj).a = "file.encoding";
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        String property = System.getProperty(this.a);
        if (property == null) {
            return null;
        }
        return property;
    }
}