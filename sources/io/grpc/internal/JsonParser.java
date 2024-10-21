package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.gson.stream.JsonReader;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonParser {
    public static final Logger logger = Logger.getLogger(JsonParser.class.getName());

    private JsonParser() {
    }

    public static Object parseRecursive(JsonReader jsonReader) {
        boolean z;
        boolean z2;
        String nextQuotedValue;
        String str;
        double parseDouble;
        ContextDataProvider.checkState(jsonReader.hasNext(), "unexpected end of JSON");
        int peek$ar$edu = jsonReader.peek$ar$edu() - 1;
        boolean z3 = true;
        if (peek$ar$edu != 0) {
            char c = '\"';
            if (peek$ar$edu != 2) {
                if (peek$ar$edu != 5) {
                    if (peek$ar$edu != 6) {
                        if (peek$ar$edu != 7) {
                            if (peek$ar$edu == 8) {
                                int i = jsonReader.peeked;
                                if (i == 0) {
                                    i = jsonReader.doPeek();
                                }
                                if (i == 7) {
                                    jsonReader.peeked = 0;
                                    int[] iArr = jsonReader.pathIndices;
                                    int i2 = jsonReader.stackSize - 1;
                                    iArr[i2] = iArr[i2] + 1;
                                    return null;
                                }
                                throw jsonReader.unexpectedTokenError("null");
                            }
                            throw new IllegalStateException("Bad token: ".concat(String.valueOf(jsonReader.getPath())));
                        }
                        int i3 = jsonReader.peeked;
                        if (i3 == 0) {
                            i3 = jsonReader.doPeek();
                        }
                        if (i3 == 5) {
                            jsonReader.peeked = 0;
                            int[] iArr2 = jsonReader.pathIndices;
                            int i4 = jsonReader.stackSize - 1;
                            iArr2[i4] = iArr2[i4] + 1;
                        } else if (i3 == 6) {
                            jsonReader.peeked = 0;
                            int[] iArr3 = jsonReader.pathIndices;
                            int i5 = jsonReader.stackSize - 1;
                            iArr3[i5] = iArr3[i5] + 1;
                            z3 = false;
                        } else {
                            throw jsonReader.unexpectedTokenError("a boolean");
                        }
                        return Boolean.valueOf(z3);
                    }
                    int i6 = jsonReader.peeked;
                    if (i6 == 0) {
                        i6 = jsonReader.doPeek();
                    }
                    if (i6 == 15) {
                        jsonReader.peeked = 0;
                        int[] iArr4 = jsonReader.pathIndices;
                        int i7 = jsonReader.stackSize - 1;
                        iArr4[i7] = iArr4[i7] + 1;
                        parseDouble = jsonReader.peekedLong;
                    } else {
                        if (i6 == 16) {
                            char[] cArr = jsonReader.buffer;
                            int i8 = jsonReader.pos;
                            int i9 = jsonReader.peekedNumberLength;
                            jsonReader.peekedString = new String(cArr, i8, i9);
                            jsonReader.pos = i8 + i9;
                        } else if (i6 != 8 && i6 != 9) {
                            if (i6 == 10) {
                                jsonReader.peekedString = jsonReader.nextUnquotedValue();
                            } else if (i6 != 11) {
                                throw jsonReader.unexpectedTokenError("a double");
                            }
                        } else {
                            if (i6 == 8) {
                                c = '\'';
                            }
                            jsonReader.peekedString = jsonReader.nextQuotedValue(c);
                        }
                        jsonReader.peeked = 11;
                        parseDouble = Double.parseDouble(jsonReader.peekedString);
                        if (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble)) {
                            jsonReader.peekedString = null;
                            jsonReader.peeked = 0;
                            int[] iArr5 = jsonReader.pathIndices;
                            int i10 = jsonReader.stackSize - 1;
                            iArr5[i10] = iArr5[i10] + 1;
                        } else {
                            throw jsonReader.syntaxError("JSON forbids NaN and infinities: " + parseDouble);
                        }
                    }
                    return Double.valueOf(parseDouble);
                }
                int i11 = jsonReader.peeked;
                if (i11 == 0) {
                    i11 = jsonReader.doPeek();
                }
                if (i11 == 10) {
                    str = jsonReader.nextUnquotedValue();
                } else if (i11 == 8) {
                    str = jsonReader.nextQuotedValue('\'');
                } else if (i11 == 9) {
                    str = jsonReader.nextQuotedValue('\"');
                } else if (i11 == 11) {
                    str = jsonReader.peekedString;
                    jsonReader.peekedString = null;
                } else if (i11 == 15) {
                    str = Long.toString(jsonReader.peekedLong);
                } else if (i11 == 16) {
                    String str2 = new String(jsonReader.buffer, jsonReader.pos, jsonReader.peekedNumberLength);
                    jsonReader.pos += jsonReader.peekedNumberLength;
                    str = str2;
                } else {
                    throw jsonReader.unexpectedTokenError("a string");
                }
                jsonReader.peeked = 0;
                int[] iArr6 = jsonReader.pathIndices;
                int i12 = jsonReader.stackSize - 1;
                iArr6[i12] = iArr6[i12] + 1;
                return str;
            }
            int i13 = jsonReader.peeked;
            if (i13 == 0) {
                i13 = jsonReader.doPeek();
            }
            if (i13 == 1) {
                jsonReader.push(3);
                jsonReader.peeked = 0;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                while (jsonReader.hasNext()) {
                    int i14 = jsonReader.peeked;
                    if (i14 == 0) {
                        i14 = jsonReader.doPeek();
                    }
                    if (i14 == 14) {
                        nextQuotedValue = jsonReader.nextUnquotedValue();
                    } else if (i14 == 12) {
                        nextQuotedValue = jsonReader.nextQuotedValue('\'');
                    } else if (i14 == 13) {
                        nextQuotedValue = jsonReader.nextQuotedValue('\"');
                    } else {
                        throw jsonReader.unexpectedTokenError("a name");
                    }
                    jsonReader.peeked = 0;
                    jsonReader.pathNames[jsonReader.stackSize - 1] = nextQuotedValue;
                    linkedHashMap.put(nextQuotedValue, parseRecursive(jsonReader));
                }
                if (jsonReader.peek$ar$edu() == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                ContextDataProvider.checkState(z2, "Bad token: ".concat(String.valueOf(jsonReader.getPath())));
                int i15 = jsonReader.peeked;
                if (i15 == 0) {
                    i15 = jsonReader.doPeek();
                }
                if (i15 == 2) {
                    int i16 = jsonReader.stackSize;
                    int i17 = i16 - 1;
                    jsonReader.stackSize = i17;
                    jsonReader.pathNames[i17] = null;
                    int[] iArr7 = jsonReader.pathIndices;
                    int i18 = i16 - 2;
                    iArr7[i18] = iArr7[i18] + 1;
                    jsonReader.peeked = 0;
                    return DesugarCollections.unmodifiableMap(linkedHashMap);
                }
                throw jsonReader.unexpectedTokenError("END_OBJECT");
            }
            throw jsonReader.unexpectedTokenError("BEGIN_OBJECT");
        }
        int i19 = jsonReader.peeked;
        if (i19 == 0) {
            i19 = jsonReader.doPeek();
        }
        if (i19 == 3) {
            jsonReader.push(1);
            jsonReader.pathIndices[jsonReader.stackSize - 1] = 0;
            jsonReader.peeked = 0;
            ArrayList arrayList = new ArrayList();
            while (jsonReader.hasNext()) {
                arrayList.add(parseRecursive(jsonReader));
            }
            if (jsonReader.peek$ar$edu() == 2) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "Bad token: ".concat(String.valueOf(jsonReader.getPath())));
            int i20 = jsonReader.peeked;
            if (i20 == 0) {
                i20 = jsonReader.doPeek();
            }
            if (i20 == 4) {
                int i21 = jsonReader.stackSize;
                jsonReader.stackSize = i21 - 1;
                int[] iArr8 = jsonReader.pathIndices;
                int i22 = i21 - 2;
                iArr8[i22] = iArr8[i22] + 1;
                jsonReader.peeked = 0;
                return DesugarCollections.unmodifiableList(arrayList);
            }
            throw jsonReader.unexpectedTokenError("END_ARRAY");
        }
        throw jsonReader.unexpectedTokenError("BEGIN_ARRAY");
    }
}
