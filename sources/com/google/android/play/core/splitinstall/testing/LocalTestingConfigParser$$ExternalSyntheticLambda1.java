package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.model.SplitInstallErrorCodeHelper;
import com.google.android.play.core.splitinstall.testing.LocalTestingConfigParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class LocalTestingConfigParser$$ExternalSyntheticLambda1 implements LocalTestingConfigParser.ElementParser {
    public final /* synthetic */ LocalTestingConfigParser f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ LocalTestingConfigParser$$ExternalSyntheticLambda1(LocalTestingConfigParser localTestingConfigParser, int i) {
        this.switching_field = i;
        this.f$0 = localTestingConfigParser;
    }

    @Override // com.google.android.play.core.splitinstall.testing.LocalTestingConfigParser.ElementParser
    public final void parse() {
        LocalTestingConfigParser localTestingConfigParser;
        int i = this.switching_field;
        if (i != 0) {
            int i2 = 0;
            int i3 = 1;
            if (i == 1) {
                String str = null;
                String str2 = null;
                int i4 = 0;
                while (true) {
                    localTestingConfigParser = this.f$0;
                    if (i4 >= localTestingConfigParser.xmlParser.getAttributeCount()) {
                        break;
                    }
                    if ("module".equals(localTestingConfigParser.xmlParser.getAttributeName(i4))) {
                        str = localTestingConfigParser.xmlParser.getAttributeValue(i4);
                    }
                    if ("errorCode".equals(localTestingConfigParser.xmlParser.getAttributeName(i4))) {
                        str2 = localTestingConfigParser.xmlParser.getAttributeValue(i4);
                    }
                    i4++;
                }
                if (str != null && str2 != null) {
                    localTestingConfigParser.builder$ar$class_merging$6426c22f_0$ar$class_merging.splitInstallErrorCodeByModule().put(str, Integer.valueOf(SplitInstallErrorCodeHelper.getErrorCode(str2)));
                    do {
                    } while (localTestingConfigParser.xmlParser.next() != 3);
                    return;
                }
                throw new XmlPullParserException(String.format("'%s' element does not contain 'module'/'errorCode' attributes.", "split-install-error"), localTestingConfigParser.xmlParser, null);
            }
            while (true) {
                LocalTestingConfigParser localTestingConfigParser2 = this.f$0;
                if (i2 < localTestingConfigParser2.xmlParser.getAttributeCount()) {
                    if ("defaultErrorCode".equals(localTestingConfigParser2.xmlParser.getAttributeName(i2))) {
                        localTestingConfigParser2.builder$ar$class_merging$6426c22f_0$ar$class_merging.ImageInfo$Builder$ar$imageFormat = Integer.valueOf(SplitInstallErrorCodeHelper.getErrorCode(localTestingConfigParser2.xmlParser.getAttributeValue(i2)));
                    }
                    i2++;
                } else {
                    localTestingConfigParser2.parseElement("split-install-error", new LocalTestingConfigParser$$ExternalSyntheticLambda1(localTestingConfigParser2, i3));
                    return;
                }
            }
        } else {
            LocalTestingConfigParser localTestingConfigParser3 = this.f$0;
            localTestingConfigParser3.parseElement("split-install-errors", new LocalTestingConfigParser$$ExternalSyntheticLambda1(localTestingConfigParser3, 2));
        }
    }
}
