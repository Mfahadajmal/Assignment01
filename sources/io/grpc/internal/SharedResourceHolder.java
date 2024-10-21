package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.collection.ArrayMap;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import androidx.core.widget.ImageViewCompat$Api21Impl;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ExecutionList;
import com.google.mlkit.logging.schema.OnDeviceImageQualityAnalysisLoadLogEvent;
import j$.util.DesugarCollections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedResourceHolder {
    private static final SharedResourceHolder holder = new SharedResourceHolder(new OnDeviceImageQualityAnalysisLoadLogEvent());
    public ScheduledExecutorService destroyer;
    private final OnDeviceImageQualityAnalysisLoadLogEvent destroyerFactory$ar$class_merging$ar$class_merging$ar$class_merging;
    public final IdentityHashMap instances = new IdentityHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Instance {
        public Object SharedResourceHolder$Instance$ar$destroyTask;
        final Object payload;
        public int refcount;

        public Instance(ImageView imageView) {
            this.refcount = 0;
            this.payload = imageView;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Map, java.lang.Object] */
        public final void addCallback$ar$ds$e7521d11_1(LifecycleCallback lifecycleCallback) {
            if (!this.payload.containsKey("ConnectionlessLifecycleHelper")) {
                this.payload.put("ConnectionlessLifecycleHelper", lifecycleCallback);
                if (this.refcount > 0) {
                    new TracingHandler(Looper.getMainLooper()).post(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(this, lifecycleCallback, 14));
                    return;
                }
                return;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4("ConnectionlessLifecycleHelper", "LifecycleCallback with tag ", " already added to this fragment."));
        }

        public final void applyImageLevel() {
            if (((ImageView) this.payload).getDrawable() != null) {
                ((ImageView) this.payload).getDrawable().setLevel(this.refcount);
            }
        }

        public final void applySupportImageTint() {
            Drawable drawable = ((ImageView) this.payload).getDrawable();
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final void dump$ar$ds$b2012eff_0() {
            for (LifecycleCallback lifecycleCallback : this.payload.values()) {
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final LifecycleCallback getCallbackOrNull$ar$ds$eb92a4e_0(Class cls) {
            return (LifecycleCallback) cls.cast(this.payload.get("ConnectionlessLifecycleHelper"));
        }

        public final boolean hasOverlappingRendering() {
            if (((ImageView) this.payload).getBackground() instanceof RippleDrawable) {
                return false;
            }
            return true;
        }

        public final void loadFromAttributes(AttributeSet attributeSet, int i) {
            int resourceId;
            ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(((ImageView) this.payload).getContext(), attributeSet, R$styleable.AppCompatImageView, i, 0);
            Object obj = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable;
            Object obj2 = this.payload;
            ViewCompat.saveAttributeDataForStyleable((View) obj2, ((ImageView) obj2).getContext(), R$styleable.AppCompatImageView, attributeSet, (TypedArray) obj, i, 0);
            try {
                Drawable drawable = ((ImageView) this.payload).getDrawable();
                if (drawable == null && (resourceId = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(1, -1)) != -1 && (drawable = AppCompatDelegate.Api33Impl.getDrawable(((ImageView) this.payload).getContext(), resourceId)) != null) {
                    ((ImageView) this.payload).setImageDrawable(drawable);
                }
                if (drawable != null) {
                    DrawableUtils.fixDrawable(drawable);
                }
                if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(2)) {
                    ImageViewCompat$Api21Impl.setImageTintList((ImageView) this.payload, obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getColorStateList(2));
                }
                if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(3)) {
                    ImageViewCompat$Api21Impl.setImageTintMode((ImageView) this.payload, _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(3, -1), null));
                }
            } finally {
                obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
            }
        }

        public final void obtainLevelFromDrawable(Drawable drawable) {
            this.refcount = drawable.getLevel();
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final void onActivityResult(int i, int i2, Intent intent) {
            Iterator it = this.payload.values().iterator();
            while (it.hasNext()) {
                ((LifecycleCallback) it.next()).onActivityResult(i, i2, intent);
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
        public final void onCreate(Bundle bundle) {
            Bundle bundle2;
            this.refcount = 1;
            this.SharedResourceHolder$Instance$ar$destroyTask = bundle;
            for (Map.Entry entry : this.payload.entrySet()) {
                LifecycleCallback lifecycleCallback = (LifecycleCallback) entry.getValue();
                if (bundle != null) {
                    bundle2 = bundle.getBundle((String) entry.getKey());
                } else {
                    bundle2 = null;
                }
                lifecycleCallback.onCreate(bundle2);
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
        public final void onDestroy() {
            this.refcount = 5;
            for (LifecycleCallback lifecycleCallback : this.payload.values()) {
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
        public final void onResume() {
            this.refcount = 3;
            Iterator it = this.payload.values().iterator();
            while (it.hasNext()) {
                ((LifecycleCallback) it.next()).onResume();
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final void onSaveInstanceState(Bundle bundle) {
            if (bundle != null) {
                for (Map.Entry entry : this.payload.entrySet()) {
                    Bundle bundle2 = new Bundle();
                    ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
                    bundle.putBundle((String) entry.getKey(), bundle2);
                }
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
        public final void onStart() {
            this.refcount = 2;
            Iterator it = this.payload.values().iterator();
            while (it.hasNext()) {
                ((LifecycleCallback) it.next()).onStart();
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
        public final void onStop() {
            this.refcount = 4;
            Iterator it = this.payload.values().iterator();
            while (it.hasNext()) {
                ((LifecycleCallback) it.next()).onStop();
            }
        }

        public final void setImageResource(int i) {
            if (i != 0) {
                Drawable drawable = AppCompatDelegate.Api33Impl.getDrawable(((ImageView) this.payload).getContext(), i);
                if (drawable != null) {
                    DrawableUtils.fixDrawable(drawable);
                }
                ((ImageView) this.payload).setImageDrawable(drawable);
            } else {
                ((ImageView) this.payload).setImageDrawable(null);
            }
            applySupportImageTint();
        }

        public Instance(Object obj) {
            this.payload = obj;
        }

        public Instance() {
            this.payload = DesugarCollections.synchronizedMap(new ArrayMap());
            this.refcount = 0;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Resource {
        void close(Object obj);

        Object create();
    }

    public SharedResourceHolder(OnDeviceImageQualityAnalysisLoadLogEvent onDeviceImageQualityAnalysisLoadLogEvent) {
        this.destroyerFactory$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceImageQualityAnalysisLoadLogEvent;
    }

    public static Object get(Resource resource) {
        return holder.getInternal(resource);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.concurrent.ScheduledFuture, java.lang.Object] */
    final synchronized Object getInternal(Resource resource) {
        Instance instance;
        instance = (Instance) this.instances.get(resource);
        if (instance == null) {
            instance = new Instance(resource.create());
            this.instances.put(resource, instance);
        }
        ?? r3 = instance.SharedResourceHolder$Instance$ar$destroyTask;
        if (r3 != 0) {
            r3.cancel(false);
            instance.SharedResourceHolder$Instance$ar$destroyTask = null;
        }
        instance.refcount++;
        return instance.payload;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void releaseInternal$ar$ds(Resource resource, Object obj) {
        boolean z;
        boolean z2;
        Instance instance = (Instance) this.instances.get(resource);
        if (instance != null) {
            boolean z3 = true;
            if (obj == instance.payload) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z, (Object) "Releasing the wrong instance");
            if (instance.refcount > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            ContextDataProvider.checkState(z2, "Refcount has already reached zero");
            int i = instance.refcount - 1;
            instance.refcount = i;
            if (i == 0) {
                if (instance.SharedResourceHolder$Instance$ar$destroyTask != null) {
                    z3 = false;
                }
                ContextDataProvider.checkState(z3, "Destroy task already scheduled");
                if (this.destroyer == null) {
                    this.destroyer = Executors.newSingleThreadScheduledExecutor(GrpcUtil.getThreadFactory$ar$ds("grpc-shared-destroyer-%d"));
                }
                instance.SharedResourceHolder$Instance$ar$destroyTask = this.destroyer.schedule(new LogExceptionRunnable(new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(this, instance, resource, obj, 8)), 1L, TimeUnit.SECONDS);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("No cached instance found for ".concat(String.valueOf(String.valueOf(resource))));
    }
}
