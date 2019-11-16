package com.example.gameproject.puzzle_game;

import android.os.CountDownTimer;

public class CountDownGenerator {

    private CountDownRequester requester;

    //Timer
    private CountDownTimer countDownTimer;
    //Time left during game
    private long timeLeftInMillis;
    //Time left during pause
    private long pauseTimeLeft;

    void startCountDown(CountDownRequester requester, long countDownInMillis) {
        this.requester = requester;
        timeLeftInMillis = countDownInMillis;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                requester.updateCountDown(timeLeftInMillis);
                //showCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                requester.updateCountDown(timeLeftInMillis);
                //showCountDownText();
                //showFinalScore();

            }
        }.start();
    }

    void pause() {
        pauseTimeLeft = timeLeftInMillis;
        countDownTimer.cancel();
    }

    void resume() {
        timeLeftInMillis = pauseTimeLeft;
        startCountDown(requester, timeLeftInMillis);
    }

}
