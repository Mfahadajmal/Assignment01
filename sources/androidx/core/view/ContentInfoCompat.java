package androidx.core.view;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContentInfoCompat {
    public final Compat mCompat;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BuilderCompat {
        ContentInfoCompat build();

        void setExtras(Bundle bundle);

        void setFlags(int i);

        void setLinkUri(Uri uri);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BuilderCompat31Impl implements BuilderCompat {
        private final ContentInfo.Builder mPlatformBuilder;

        public BuilderCompat31Impl(ClipData clipData, int i) {
            this.mPlatformBuilder = new ContentInfo.Builder(clipData, i);
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final ContentInfoCompat build() {
            ContentInfo build;
            build = this.mPlatformBuilder.build();
            return new ContentInfoCompat(new Compat31Impl(build));
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final void setExtras(Bundle bundle) {
            this.mPlatformBuilder.setExtras(bundle);
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final void setFlags(int i) {
            this.mPlatformBuilder.setFlags(i);
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final void setLinkUri(Uri uri) {
            this.mPlatformBuilder.setLinkUri(uri);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BuilderCompatImpl implements BuilderCompat {
        final ClipData mClip;
        Bundle mExtras;
        int mFlags;
        Uri mLinkUri;
        final int mSource;

        public BuilderCompatImpl(ClipData clipData, int i) {
            this.mClip = clipData;
            this.mSource = i;
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final ContentInfoCompat build() {
            return new ContentInfoCompat(new CompatImpl(this));
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final void setExtras(Bundle bundle) {
            this.mExtras = bundle;
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final void setFlags(int i) {
            this.mFlags = i;
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public final void setLinkUri(Uri uri) {
            this.mLinkUri = uri;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Compat {
        ClipData getClip();

        int getFlags();

        int getSource();

        ContentInfo getWrapped();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Compat31Impl implements Compat {
        private final ContentInfo mWrapped;

        public Compat31Impl(ContentInfo contentInfo) {
            contentInfo.getClass();
            this.mWrapped = contentInfo;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final ClipData getClip() {
            ClipData clip;
            clip = this.mWrapped.getClip();
            return clip;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final int getFlags() {
            int flags;
            flags = this.mWrapped.getFlags();
            return flags;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final int getSource() {
            int source;
            source = this.mWrapped.getSource();
            return source;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final ContentInfo getWrapped() {
            return this.mWrapped;
        }

        public final String toString() {
            return "ContentInfoCompat{" + this.mWrapped + "}";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CompatImpl implements Compat {
        private final ClipData mClip;
        private final Bundle mExtras;
        private final int mFlags;
        private final Uri mLinkUri;
        private final int mSource;

        public CompatImpl(BuilderCompatImpl builderCompatImpl) {
            ClipData clipData = builderCompatImpl.mClip;
            clipData.getClass();
            this.mClip = clipData;
            this.mSource = builderCompatImpl.mSource;
            this.mFlags = builderCompatImpl.mFlags;
            this.mLinkUri = builderCompatImpl.mLinkUri;
            this.mExtras = builderCompatImpl.mExtras;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final ClipData getClip() {
            return this.mClip;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final int getFlags() {
            return this.mFlags;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final int getSource() {
            return this.mSource;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public final ContentInfo getWrapped() {
            return null;
        }

        public final String toString() {
            String str;
            String valueOf;
            String str2;
            StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
            sb.append(this.mClip.getDescription());
            sb.append(", source=");
            int i = this.mSource;
            if (i != 1) {
                if (i != 2) {
                    str = "SOURCE_DRAG_AND_DROP";
                } else {
                    str = "SOURCE_INPUT_METHOD";
                }
            } else {
                str = "SOURCE_CLIPBOARD";
            }
            sb.append(str);
            sb.append(", flags=");
            if (this.mFlags != 0) {
                valueOf = "FLAG_CONVERT_TO_PLAIN_TEXT";
            } else {
                valueOf = String.valueOf(0);
            }
            sb.append(valueOf);
            String str3 = "";
            if (this.mLinkUri == null) {
                str2 = "";
            } else {
                str2 = ", hasLinkUri(" + this.mLinkUri.toString().length() + ")";
            }
            sb.append(str2);
            if (this.mExtras != null) {
                str3 = ", hasExtras";
            }
            sb.append(str3);
            sb.append("}");
            return sb.toString();
        }
    }

    public ContentInfoCompat(Compat compat) {
        this.mCompat = compat;
    }

    public static ContentInfoCompat toContentInfoCompat(ContentInfo contentInfo) {
        return new ContentInfoCompat(new Compat31Impl(contentInfo));
    }

    public final ContentInfo toContentInfo() {
        ContentInfo wrapped = this.mCompat.getWrapped();
        wrapped.getClass();
        return wrapped;
    }

    public final String toString() {
        return this.mCompat.toString();
    }
}
