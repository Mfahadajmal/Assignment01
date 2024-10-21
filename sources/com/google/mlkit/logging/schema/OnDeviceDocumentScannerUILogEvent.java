package com.google.mlkit.logging.schema;

import com.google.protobuf.ByteString;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceDocumentScannerUILogEvent {
    public static String escapeBytes(ByteString byteString) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(byteString);
        StringBuilder sb = new StringBuilder(resolutionResultListener.size());
        for (int i = 0; i < resolutionResultListener.size(); i++) {
            byte byteAt = ((ByteString) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).byteAt(i);
            if (byteAt != 34) {
                if (byteAt != 39) {
                    if (byteAt != 92) {
                        switch (byteAt) {
                            case 7:
                                sb.append("\\a");
                                break;
                            case 8:
                                sb.append("\\b");
                                break;
                            case 9:
                                sb.append("\\t");
                                break;
                            case 10:
                                sb.append("\\n");
                                break;
                            case 11:
                                sb.append("\\v");
                                break;
                            case 12:
                                sb.append("\\f");
                                break;
                            case 13:
                                sb.append("\\r");
                                break;
                            default:
                                if (byteAt >= 32 && byteAt <= 126) {
                                    sb.append((char) byteAt);
                                    break;
                                } else {
                                    sb.append('\\');
                                    sb.append((char) (((byteAt >>> 6) & 3) + 48));
                                    sb.append((char) (((byteAt >>> 3) & 7) + 48));
                                    sb.append((char) ((byteAt & 7) + 48));
                                    break;
                                }
                        }
                    } else {
                        sb.append("\\\\");
                    }
                } else {
                    sb.append("\\'");
                }
            } else {
                sb.append("\\\"");
            }
        }
        return sb.toString();
    }
}
