package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.mlkit.logging.schema.ImageInfo;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LocalTestingConfigParser {
    private static final SplitInstallModule logger$ar$class_merging$ceb098d3_0$ar$class_merging = new SplitInstallModule("LocalTestingConfigParser");
    public final ImageInfo.Builder builder$ar$class_merging$6426c22f_0$ar$class_merging = LocalTestingConfig.builder$ar$class_merging$7885372b_0$ar$class_merging();
    public final XmlPullParser xmlParser;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ElementParser {
        void parse();
    }

    public LocalTestingConfigParser(XmlPullParser xmlPullParser) {
        this.xmlParser = xmlPullParser;
    }

    public static LocalTestingConfig getLocalTestingConfig(File file) {
        File file2 = new File(file, "local_testing_config.xml");
        if (!file2.exists()) {
            return LocalTestingConfig.DEFAULT;
        }
        try {
            FileReader fileReader = new FileReader(file2);
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(fileReader);
                LocalTestingConfigParser localTestingConfigParser = new LocalTestingConfigParser(newPullParser);
                localTestingConfigParser.parseElement("local-testing-config", new LocalTestingConfigParser$$ExternalSyntheticLambda1(localTestingConfigParser, 0));
                LocalTestingConfig m222build = localTestingConfigParser.builder$ar$class_merging$6426c22f_0$ar$class_merging.m222build();
                fileReader.close();
                return m222build;
            } catch (Throwable th) {
                try {
                    fileReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | RuntimeException | XmlPullParserException e) {
            logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("%s can not be parsed, using default. Error: %s", "local_testing_config.xml", e.getMessage());
            return LocalTestingConfig.DEFAULT;
        }
    }

    public final void parseElement(String str, ElementParser elementParser) {
        while (true) {
            int next = this.xmlParser.next();
            if (next != 3 && next != 1) {
                if (this.xmlParser.getEventType() == 2) {
                    if (this.xmlParser.getName().equals(str)) {
                        elementParser.parse();
                    } else {
                        throw new XmlPullParserException(String.format("Expected '%s' tag but found '%s'.", str, this.xmlParser.getName()), this.xmlParser, null);
                    }
                }
            } else {
                return;
            }
        }
    }
}
