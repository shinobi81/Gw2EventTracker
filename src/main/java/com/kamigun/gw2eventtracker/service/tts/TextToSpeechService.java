/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.tts;

/**
 *
 * @author shinobi
 */
public interface TextToSpeechService {

    DoSpeakServiceResponse doSpeak(DoSpeakServiceRequest paramDoSpeakServiceRequest) throws Exception;
}
