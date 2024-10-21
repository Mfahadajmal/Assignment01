package com.google.frameworks.client.data.android.interceptor;

import _COROUTINE._BOUNDARY;
import android.graphics.PointF;
import android.graphics.Typeface;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.libraries.vision.visionkit.base.L;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OrderVerifyingClientCall extends ForwardingClientCall.SimpleForwardingClientCall {
    private final AtomicReference stateRef;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State {
        public final Object OrderVerifyingClientCall$State$ar$cancellationStatus;
        public final int type$ar$edu$88c656f2_0;

        private State(int i, Object obj) {
            this.type$ar$edu$88c656f2_0 = i;
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = obj;
        }

        public static State create$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i;
            if (accessibilityNodeInfoCompat == null) {
                i = 1;
            } else {
                i = 2;
            }
            return new State(i, accessibilityNodeInfoCompat);
        }

        public static State create$ar$edu$ca49f0b7_0$ar$class_merging$ar$class_merging(int i) {
            if (i != 2) {
                return new State(i, (Object) null);
            }
            throw new IllegalArgumentException("create native type without node");
        }

        static State forCancelType$ar$edu(int i, Status status) {
            if (i != 4) {
                i = 5;
            }
            ContextDataProvider.checkState(true);
            status.getClass();
            return new State(i, status);
        }

        static State forType$ar$edu(int i) {
            ContextDataProvider.checkState(true);
            return new State(i, (Object) null);
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map, java.lang.Object] */
        public final synchronized boolean addFrame(Object obj, long j) {
            if (this.OrderVerifyingClientCall$State$ar$cancellationStatus.size() == this.type$ar$edu$88c656f2_0) {
                L.log.w(this, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "Buffer is full. Drop frame "), new Object[0]);
                return false;
            }
            this.OrderVerifyingClientCall$State$ar$cancellationStatus.put(Long.valueOf(j), obj);
            return true;
        }

        public final int get(int i) {
            return ((int[]) this.OrderVerifyingClientCall$State$ar$cancellationStatus)[i + this.type$ar$edu$88c656f2_0];
        }

        public final PointF getCenter() {
            PointF pointF = (PointF) this.OrderVerifyingClientCall$State$ar$cancellationStatus;
            return new PointF(pointF.x, pointF.y);
        }

        public final AccessibilityNodeInfoCompat getNode() {
            String str;
            if (shouldSkipNavigation()) {
                int i = this.type$ar$edu$88c656f2_0;
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    str = "EXCEPTION";
                                } else {
                                    str = "REACH_EDGE";
                                }
                            } else {
                                str = "EXISTING_FOCUS";
                            }
                        } else {
                            str = "WEB_ELEMENT";
                        }
                    } else {
                        str = "NATIVE_ELEMENT";
                    }
                } else {
                    str = "EMPTY";
                }
                throw new IllegalStateException("getNode for skipped type: ".concat(str));
            }
            return (AccessibilityNodeInfoCompat) this.OrderVerifyingClientCall$State$ar$cancellationStatus;
        }

        public final boolean isEmpty() {
            if (this.type$ar$edu$88c656f2_0 == 1) {
                return true;
            }
            return false;
        }

        public final boolean isFocusAvailable() {
            if (this.type$ar$edu$88c656f2_0 != 2 && !isWebElement() && this.type$ar$edu$88c656f2_0 != 4) {
                return false;
            }
            return true;
        }

        public final boolean isWebElement() {
            if (this.type$ar$edu$88c656f2_0 != 3) {
                return false;
            }
            return true;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final synchronized void removeFrame(long j) {
            this.OrderVerifyingClientCall$State$ar$cancellationStatus.remove(Long.valueOf(j));
        }

        public final void set(int i, int i2) {
            ((int[]) this.OrderVerifyingClientCall$State$ar$cancellationStatus)[i + this.type$ar$edu$88c656f2_0] = i2;
        }

        public final boolean shouldSkipNavigation() {
            int i;
            if (this.type$ar$edu$88c656f2_0 != 4 && !isWebElement() && (i = this.type$ar$edu$88c656f2_0) != 5 && i != 6) {
                return false;
            }
            return true;
        }

        public State(int i, byte[] bArr) {
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = null;
            this.type$ar$edu$88c656f2_0 = i;
        }

        @Deprecated
        public State(int i, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr) {
            this.type$ar$edu$88c656f2_0 = i;
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = fontsContractCompat$FontInfoArr;
        }

        public State(Typeface typeface) {
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = typeface;
            this.type$ar$edu$88c656f2_0 = 0;
        }

        public State(ContextMenuItem contextMenuItem, int i) {
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = contextMenuItem;
            this.type$ar$edu$88c656f2_0 = i;
            int i2 = contextMenuItem.itemId;
        }

        public State(String str) {
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = str;
            this.type$ar$edu$88c656f2_0 = 4;
        }

        public State(String str, int i) {
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = str;
            this.type$ar$edu$88c656f2_0 = i;
        }

        public State(int i, float f, float f2) {
            this.type$ar$edu$88c656f2_0 = i;
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = new PointF(f, f2);
        }

        public State(int i, byte[] bArr, byte[] bArr2) {
            int[] iArr = new int[i];
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = iArr;
            this.type$ar$edu$88c656f2_0 = iArr.length >> 1;
        }

        public State(ConnectionResult connectionResult, int i) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(connectionResult);
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = connectionResult;
            this.type$ar$edu$88c656f2_0 = i;
        }

        public State(int i) {
            this.type$ar$edu$88c656f2_0 = i;
            this.OrderVerifyingClientCall$State$ar$cancellationStatus = new HashMap();
        }
    }

    public OrderVerifyingClientCall(ClientCall clientCall) {
        super(clientCall);
        this.stateRef = new AtomicReference(State.forType$ar$edu(1));
    }

    @Override // io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
    public final void cancel(String str, Throwable th) {
        State state;
        State forCancelType$ar$edu;
        Status status = Status.CANCELLED;
        if (str != null) {
            status = status.withDescription(str);
        }
        if (th != null) {
            status = status.withCause(th);
        }
        do {
            state = (State) this.stateRef.get();
            if (state.type$ar$edu$88c656f2_0 == 4) {
                forCancelType$ar$edu = state;
            } else if (state.type$ar$edu$88c656f2_0 == 1) {
                forCancelType$ar$edu = State.forCancelType$ar$edu(4, status);
            } else {
                forCancelType$ar$edu = State.forCancelType$ar$edu(5, status);
            }
        } while (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(this.stateRef, state, forCancelType$ar$edu));
        this.delegate.cancel(str, th);
    }

    @Override // io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
    public final void halfClose() {
        State state;
        do {
            state = (State) this.stateRef.get();
            if (state.type$ar$edu$88c656f2_0 != 2) {
                throw new IllegalStateException("Call was either not started or already half-closed.");
            }
        } while (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(this.stateRef, state, State.forType$ar$edu(3)));
        this.delegate.halfClose();
    }

    @Override // io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
    public final void request(int i) {
        State state = (State) this.stateRef.get();
        if (state.type$ar$edu$88c656f2_0 != 1 && state.type$ar$edu$88c656f2_0 != 4) {
            ContextDataProvider.checkArgument(true, (Object) "Number requested must be non-negative");
            this.delegate.request(i);
            return;
        }
        throw new IllegalStateException("Not started");
    }

    @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
    public final void sendMessage(Object obj) {
        obj.getClass();
        int i = ((State) this.stateRef.get()).type$ar$edu$88c656f2_0;
        if (i == 2) {
            this.delegate.sendMessage(obj);
        } else if (i == 5) {
        } else {
            throw new IllegalStateException("Call was either not started or already half-closed.");
        }
    }

    @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
    public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
        State state;
        State state2;
        do {
            state = (State) this.stateRef.get();
            if (state.type$ar$edu$88c656f2_0 == 1) {
                state2 = State.forType$ar$edu(2);
            } else {
                state2 = state;
            }
        } while (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(this.stateRef, state, state2));
        int i = state.type$ar$edu$88c656f2_0;
        if (i != 1) {
            if (i == 4) {
                onDeviceFaceMeshCreateLogEvent.onClose((Status) state.OrderVerifyingClientCall$State$ar$cancellationStatus, new Metadata());
                return;
            } else {
                IllegalStateException illegalStateException = new IllegalStateException("Already started");
                this.delegate.cancel("start() called more than once", illegalStateException);
                throw illegalStateException;
            }
        }
        this.delegate.start$ar$class_merging$ar$class_merging(onDeviceFaceMeshCreateLogEvent, metadata);
    }
}
