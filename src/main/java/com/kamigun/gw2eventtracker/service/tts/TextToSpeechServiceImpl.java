/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.tts;

import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author shinobi
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TextToSpeechServiceImpl implements TextToSpeechService {

    private Synthesizer synthesizer;
    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    public DoSpeakServiceResponse doSpeak(DoSpeakServiceRequest doSpeakServiceRequest) throws Exception {
        DoSpeakServiceResponse doSpeakServiceResponse = new DoSpeakServiceResponse();

        String text = doSpeakServiceRequest.getText();
        if (StringUtils.hasText(text)) {
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        }
        return doSpeakServiceResponse;
    }

    @PostConstruct
    public void init() throws Exception {
        logger.info("Init TTS Service");
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        SynthesizerModeDesc synthesizerModeDesc = new SynthesizerModeDesc(Locale.US);
        Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
        synthesizer = Central.createSynthesizer(synthesizerModeDesc);
        synthesizer.allocate();
        synthesizer.resume();

        SynthesizerModeDesc smd = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();

        Voice[] voices = smd.getVoices();
        Voice selectedVoice = null;
        for (Voice voice : voices) {
            if (voice.getName().equals("kevin16")) {
                selectedVoice = voice;
                break;
            }
        }
        synthesizer.getSynthesizerProperties().setVoice(selectedVoice);
    }

    @PreDestroy
    public void destroy() throws Exception {
        logger.info("Deallocate synthesizer");
        synthesizer.deallocate();
    }
}
