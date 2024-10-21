package j$.desugar.sun.nio.fs;

import java.nio.channels.FileLock;

/* loaded from: classes2.dex */
final class b extends FileLock {
    private final FileLock a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(FileLock fileLock, a aVar) {
        super(aVar, fileLock.position(), fileLock.size(), fileLock.isShared());
        this.a = fileLock;
    }

    @Override // java.nio.channels.FileLock
    public final boolean isValid() {
        return this.a.isValid();
    }

    @Override // java.nio.channels.FileLock
    public final void release() {
        this.a.release();
    }
}
