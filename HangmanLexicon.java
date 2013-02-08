import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.ErrorException;

public class HangmanLexicon {
	private ArrayList<String> words;

	public HangmanLexicon() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(
					"HangmanLexicon.txt"));
			words = new ArrayList<String>();
			
			while(true) {
				String line = rd.readLine();
				words.add(line);
				if(line==null)
					break;
			}
			rd.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		if(words.get(index) !=null)
			return words.get(index);
		else
			throw new ErrorException("getWord: Illegal index");
		}
	}

