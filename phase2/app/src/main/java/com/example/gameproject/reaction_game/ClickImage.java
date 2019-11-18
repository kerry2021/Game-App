package com.example.gameproject.reaction_game;

import android.view.View;

import com.example.gameproject.R;


class ClickImage implements View.OnClickListener {
    private boolean movable;
    private reactionGameActivity reaction;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (movable) {
            if (id == R.id.first) {
                if (reaction.next == 1) {
                    reaction.score += 1;
                    reaction.buttons[0].setBackgroundResource(R.drawable.hit);
                } else
                    reaction.buttons[0].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.second) {
                if (reaction.next == 2) {
                    reaction.buttons[1].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[1].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.third) {
                if (reaction.next == 3) {
                    reaction.buttons[2].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[2].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.fourth) {
                if (reaction.next == 4) {
                    reaction.buttons[3].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[3].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.fifth) {
                if (reaction.next == 5) {
                    reaction.buttons[4].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[4].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.sixth) {
                if (reaction.next == 6) {
                    reaction.buttons[5].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[5].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.seventh) {
                if (reaction.next == 7) {
                    reaction.buttons[6].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[6].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.eighth) {
                if (reaction.next == 8) {
                    reaction.buttons[7].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[7].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            } else if (id == R.id.ninth) {
                if (reaction.next == 9) {
                    reaction.buttons[8].setBackgroundResource(R.drawable.hit);
                    reaction.score += 1;
                } else
                    reaction.buttons[8].setBackgroundResource(R.drawable.miss);
                reaction.next = 0;
            }

            String ts = "" + reaction.score;
            reaction.t_score.setText(ts);
        }
    }
    public void setMovable(boolean movable){
        this.movable = movable;
    }
    public void setReaction(reactionGameActivity action){
        this.reaction = action;
    }

}
