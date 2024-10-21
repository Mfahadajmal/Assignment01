package j$.time.chrono;

import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;

/* renamed from: j$.time.chrono.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public interface InterfaceC0012b extends Temporal, j$.time.temporal.m, Comparable {
    long A();

    InterfaceC0015e B(j$.time.k kVar);

    n a();

    @Override // j$.time.temporal.Temporal
    long b(Temporal temporal, TemporalUnit temporalUnit);

    @Override // j$.time.temporal.Temporal
    InterfaceC0012b e(long j, j$.time.temporal.o oVar);

    @Override // j$.time.temporal.l
    boolean f(j$.time.temporal.o oVar);

    @Override // j$.time.temporal.Temporal
    InterfaceC0012b g(long j, TemporalUnit temporalUnit);

    int hashCode();

    String toString();

    InterfaceC0012b u(long j, TemporalUnit temporalUnit);

    /* renamed from: y */
    int compareTo(InterfaceC0012b interfaceC0012b);
}
