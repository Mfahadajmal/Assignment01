package io.grpc.okhttp;

import io.grpc.internal.GrpcUtil;
import io.grpc.okhttp.internal.framed.Header;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Headers {
    public static final Header HTTPS_SCHEME_HEADER = new Header(Header.TARGET_SCHEME, "https");
    public static final Header HTTP_SCHEME_HEADER = new Header(Header.TARGET_SCHEME, "http");
    public static final Header METHOD_HEADER = new Header(Header.TARGET_METHOD, "POST");
    public static final Header METHOD_GET_HEADER = new Header(Header.TARGET_METHOD, "GET");
    public static final Header CONTENT_TYPE_HEADER = new Header(GrpcUtil.CONTENT_TYPE_KEY.name, "application/grpc");
    public static final Header TE_HEADER = new Header("te", "trailers");
}
