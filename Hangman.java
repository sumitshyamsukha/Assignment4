import acm.io.IOConsole;
import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class Hangman extends ConsoleProgram {

	private HangmanLexicon hl;
	private static int incorrectGuesses = 0;
	private String word;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private IOConsole io = getConsole();
	private boolean guessed = false;
	private char letters[];
	private HangmanCanvas canvas;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		canvas.reset();
		hl = new HangmanLexicon();
		word = hl.getWord(rgen.nextInt(10));
		letters = new char[word.length()];
	}

	public void run() {
		char g = '0';
		for (int i = 0; i < letters.length; i++)
			letters[i] = '-';
		println("Welcome to Hangman!");
		while (!checkWord() & incorrectGuesses < 8) {
			println("The word now looks like this : " + printWord(letters));
			println("You have " + (8 - incorrectGuesses) + " guesses left.");
			String guess = io.readLine("Your guess : ");
			if (guess.length() > 1)
				println("Invalid input!");
			else
				g = Character.toUpperCase(guess.charAt(0));
			if (!word.contains("" + g)) {
				incorrectGuesses++;
				println("There are no " + g + "'s in the word.");
				canvas.noteIncorrectGuess(g);
			} else {
				println("That guess is correct! ");
				for (int i = 0; i < word.length(); i++) {
					if (g == word.charAt(i))
						letters[i] = g;
				}
				canvas.displayWord(printWord(letters));
			}
		}
		if (guessed) {
			println("You guessed the word : " + word);
			println("You win!");
		} else {
			println("You're completely hung!");
			println("The word was : " + word);
			println("You lose!");
		}
	}

	public String printWord(char[] a) {
		String word = "";
		for (int i = 0; i < a.length; i++)
			word += a[i];
		return word;
	}

	public boolean checkWord() {
		String word = "";
		for (int i = 0; i < letters.length; i++) {
			word += letters[i];
		}
		if (this.word.equals(word))
			guessed = true;
		else
			guessed = false;
		return guessed;
	}

	public static int getIncorrectGuesses() {
		return incorrectGuesses;
	}
}