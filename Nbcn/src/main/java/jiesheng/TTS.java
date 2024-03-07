/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.speech.tts.TextToSpeech
 *  android.speech.tts.TextToSpeech$OnInitListener
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.Locale
 */
package jiesheng;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Locale;

public class TTS
        extends 组件 {
    private static HashMap<String, Locale> map = new HashMap();

    static {
        map.put("中文", Locale.CHINESE);
        map.put("英语", Locale.ENGLISH);
        map.put("德语", Locale.GERMAN);
        map.put("法语", Locale.FRENCH);
        map.put("日语", Locale.JAPANESE);
    }

    private Context context;
    private TextToSpeech mSpeech;
    private 初始化完毕 $event_internal_初始化完毕;

    public TTS(Context context) {
        super(context);
        this.context = context;
        this.mSpeech = new TextToSpeech(this.context, (TextToSpeech.OnInitListener) new TTSListener());
        this.mSpeech.setLanguage(Locale.CHINESE);
    }

    public void 语速(float f) {
        this.mSpeech.setSpeechRate(f);
    }

    public void 音调(float f) {
        this.mSpeech.setPitch(f);
    }

    public void 语言(String string) {
        this.mSpeech.setLanguage(map.containsKey((Object) string) ? (Locale) map.get((Object) string) : Locale.CHINESE);
    }

    public boolean 朗读状态() {
        return this.mSpeech.isSpeaking();
    }

    public void 朗读文本(String string) {
        this.mSpeech.speak(string, 0, null);
    }

    public void 文本转wav(String 欲转换文本, String 保存路径) {
        HashMap<String, String> myHashRender = new HashMap<>();
        myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, 欲转换文本);
        mSpeech.synthesizeToFile(欲转换文本, myHashRender, 保存路径);
    }

    public void 关闭() {
        this.mSpeech.shutdown();
    }

    public void 停止() {
        this.mSpeech.stop();
    }

    public void 置初始化完毕(初始化完毕 初始化完毕2) {
        this.$event_internal_初始化完毕 = 初始化完毕2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 初始化完毕 {
        public void 初始化完毕(boolean var1);
    }

    private class TTSListener
            implements TextToSpeech.OnInitListener {
        private TTSListener() {
        }

        public void onInit(int n) {
            if (n == 0) {
                if (TTS.this.$event_internal_初始化完毕 != null) {
                    TTS.this.$event_internal_初始化完毕.初始化完毕(true);
                }
            } else if (TTS.this.$event_internal_初始化完毕 != null) {
                TTS.this.$event_internal_初始化完毕.初始化完毕(false);
            }
        }
    }
}

