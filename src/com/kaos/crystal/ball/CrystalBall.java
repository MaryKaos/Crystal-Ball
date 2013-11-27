package com.kaos.crystal.ball;

import java.util.Random;

import android.graphics.drawable.AnimationDrawable;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CrystalBall {
	/* ***********************************************
	 * Member variables (properties about the object)
	 *************************************************/
	public String[] mAnswers = {
		"It is certain",
		"It is decidedly so",
		"All signs say YES",
		"The stars are not aligned",
		"My reply is no",
		"It is doubtful",
		"Better not tell you now",
		"Concentrate and ask again",
		"unable to answer now"
	};

	/* *********************************************** 
	 * Methods (abilities: things the object can do)
	 *************************************************/
	public String getAnAnswer() {
		
		String answer = "";
		
		// Randomly select one of the answers from the array
		Random randomGenerator = new Random(); //construct new Random number generator
		int randomNumber = randomGenerator.nextInt(mAnswers.length);
		//answer = Integer.toString(randomNumber); //testing: convert number to a string so it can be displayed in the textlabel
		
		// Convert the random number to a text answer
		answer = mAnswers[randomNumber];
		
		return answer;
	}
	
    public void animateCrystalBall(ImageView mCrystalBallImage) {
    	mCrystalBallImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage.getDrawable();
    	
    	if (ballAnimation.isRunning()) {
    		ballAnimation.stop();
    	}
    	ballAnimation.start();
    }
    
    public void animateAnswer(TextView mAnswerLabel) {
    	AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
    	fadeInAnimation.setDuration(1500);
    	fadeInAnimation.setFillAfter(true);
    	
    	mAnswerLabel.setAnimation(fadeInAnimation);
    }
	
}
