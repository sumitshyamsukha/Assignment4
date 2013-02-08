import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;

public class HangmanCanvas extends GCanvas {

	private GLabel word;
	private GLabel incorrect;
	private String letters = "";
	private int count = 1;

	public void init() {
		GLine scaffold = new GLine(X_OFFSET, Y_OFFSET, X_OFFSET,
				SCAFFOLD_HEIGHT);
		GLine beam = new GLine(X_OFFSET, Y_OFFSET, BEAM_LENGTH, Y_OFFSET);
		GLine rope = new GLine(BEAM_LENGTH, Y_OFFSET, BEAM_LENGTH, Y_OFFSET
				+ ROPE_LENGTH);
		scaffold.setVisible(true);
		beam.setVisible(true);
		rope.setVisible(true);
		add(scaffold);
		add(beam);
		add(rope);
	}

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		init();
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		if (count == 1) {
			this.word = new GLabel(word);
			this.word.setVisible(true);
			this.word.setFont("SansSerif-72");
			this.word.setLocation(100, Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH + 100);
			add(this.word);
			this.word.setLabel(word);
			count++;
		} else {
			remove(this.word);
			this.word = new GLabel(word);
			this.word.setVisible(true);
			this.word.setFont("SansSerif-72");
			this.word.setLocation(100, Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH + 100);
			add(this.word);
			this.word.setLabel(word);
		}
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		nextPart();
		letters += letter;
		incorrect = new GLabel(letters);
		incorrect.setVisible(true);
		incorrect.setFont("SansSerif-48");
		incorrect.setLocation(10, Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS
				+ BODY_LENGTH + LEG_LENGTH + 200);
		add(incorrect);
	}

	private void nextPart() {
		switch (Hangman.getIncorrectGuesses()) {
		case 1:
			GOval head = new GOval(BEAM_LENGTH - (HEAD_RADIUS / 2), Y_OFFSET
					+ ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
			head.setVisible(true);
			add(head);
			break;
		case 2:
			GLine body = new GLine(BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH
					+ HEAD_RADIUS, BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH
					+ HEAD_RADIUS + BODY_LENGTH);
			body.setVisible(true);
			add(body);
			break;
		case 3:
			GLine leftUpperArm = new GLine(BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH
					+ HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, UPPER_ARM_LENGTH,
					Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			leftUpperArm.setVisible(true);
			add(leftUpperArm);
			GLine leftLowerArm = new GLine(UPPER_ARM_LENGTH, Y_OFFSET
					+ ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					UPPER_ARM_LENGTH, Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS
							+ ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			leftLowerArm.setVisible(true);
			add(leftLowerArm);
			break;
		case 4:
			GLine rightUpperArm = new GLine(BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH
					+ HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, BEAM_LENGTH
					+ UPPER_ARM_LENGTH, Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS
					+ ARM_OFFSET_FROM_HEAD);
			rightUpperArm.setVisible(true);
			add(rightUpperArm);
			GLine rightLowerArm = new GLine(
					BEAM_LENGTH + UPPER_ARM_LENGTH,
					Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					BEAM_LENGTH + UPPER_ARM_LENGTH, Y_OFFSET + ROPE_LENGTH
							+ HEAD_RADIUS + ARM_OFFSET_FROM_HEAD
							+ LOWER_ARM_LENGTH);
			rightLowerArm.setVisible(true);
			add(rightLowerArm);
			break;
		case 5:
			GLine leftHip = new GLine(BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH
					+ HEAD_RADIUS + BODY_LENGTH, BEAM_LENGTH - HIP_WIDTH,
					Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
			leftHip.setVisible(true);
			add(leftHip);
			GLine leftLeg = new GLine(BEAM_LENGTH - HIP_WIDTH, Y_OFFSET
					+ ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, BEAM_LENGTH
					- HIP_WIDTH, Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH);
			leftLeg.setVisible(true);
			add(leftLeg);

			break;
		case 6:
			GLine rightHip = new GLine(BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH
					+ HEAD_RADIUS + BODY_LENGTH, BEAM_LENGTH + HIP_WIDTH,
					Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
			rightHip.setVisible(true);
			add(rightHip);
			GLine rightLeg = new GLine(BEAM_LENGTH + HIP_WIDTH, Y_OFFSET
					+ ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, BEAM_LENGTH
					+ HIP_WIDTH, Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH);
			rightLeg.setVisible(true);
			add(rightLeg);
			break;
		case 7:
			GLine leftFoot = new GLine(BEAM_LENGTH - HIP_WIDTH, Y_OFFSET
					+ ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH, Y_OFFSET
							+ ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH
							+ LEG_LENGTH);
			leftFoot.setVisible(true);
			add(leftFoot);
			break;
		case 8:
			GLine rightFoot = new GLine(BEAM_LENGTH + HIP_WIDTH, Y_OFFSET
					+ ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH, Y_OFFSET
							+ ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH
							+ LEG_LENGTH);
			rightFoot.setVisible(true);
			add(rightFoot);
			break;
		}
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int Y_OFFSET = 20;
	private static final int X_OFFSET = 30;
}
