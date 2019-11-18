package com.example.gameproject.reaction_game;

import android.view.View;

import com.example.gameproject.R;

import static com.example.gameproject.reaction_game.reactionGameActivity.buttons;
import static com.example.gameproject.reaction_game.reactionGameActivity.next;
import static com.example.gameproject.reaction_game.reactionGameActivity.t_score;
import static com.example.gameproject.reaction_game.reactionGameActivity.score;

class ClickImage implements View.OnClickListener {
    private boolean movable;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (movable) {
            if (id == R.id.first) {
                if (next == 1) {
                    score += 1;
                    buttons[0].setBackgroundResource(R.drawable.hit);
                } else
                    buttons[0].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.second) {
                if (next == 2) {
                    buttons[1].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[1].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.third) {
                if (next == 3) {
                    buttons[2].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[2].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.fourth) {
                if (next == 4) {
                    buttons[3].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[3].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.fifth) {
                if (next == 5) {
                    buttons[4].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[4].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.sixth) {
                if (next == 6) {
                    buttons[5].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[5].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.seventh) {
                if (next == 7) {
                    buttons[6].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[6].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.eighth) {
                if (next == 8) {
                    buttons[7].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[7].setBackgroundResource(R.drawable.miss);
                next = 0;
            } else if (id == R.id.ninth) {
                if (next == 9) {
                    buttons[8].setBackgroundResource(R.drawable.hit);
                    score += 1;
                } else
                    buttons[8].setBackgroundResource(R.drawable.miss);
                next = 0;
            }

            String ts = "" + score;
            t_score.setText(ts);
        }
    }
    public void setMovable(boolean movable){
        this.movable = movable;
    }
}
