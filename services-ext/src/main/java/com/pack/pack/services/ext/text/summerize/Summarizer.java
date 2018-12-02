/*Karim Oumghar
 * Summarization class for SumIt!
 * Updated Novemeber 2015
 * */
package com.pack.pack.services.ext.text.summerize;

import static com.pack.pack.services.ext.text.summerize.STOP_WORDS.STOP_WORDS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import com.pack.pack.util.LanguageUtil;
import com.pack.pack.util.SystemPropertyUtil;

public class Summarizer {
	
	private ISentenceFinder sentenceFinder;
	
	public Summarizer() {
		this(new DefaultSentenceFinder(SystemPropertyUtil.getOpenNlpConfDir()));
	}
	
	public Summarizer(ISentenceFinder sentenceFinder) {
		this.sentenceFinder = sentenceFinder;
	}

	private Map<String, Integer> getWordCounts(String text) {
		Map<String, Integer> allWords = new HashMap<String, Integer>();

		int count;
		int singleIncrement = 0;
		/*
		 * start with raw frequencies scan entire text and record all words and
		 * word counts so if a word appears multiple times, increment the word
		 * count for that particular word if a word appears only once, add the
		 * new word to the Map
		 */
		text.trim();
		String[] words = text.split("\\s+");// split with white space delimiters

		for (int i = 0; i < words.length; i++) {
			count = 0;

			if (allWords.containsKey(words[i]))// do a check to see if a word
												// already exists in the
												// collection
			{
				allWords.put(words[i], singleIncrement += 1);
			} else {
				allWords.put(words[i], count++);
			}
		}
		return allWords;
	}

	private Map<String, Integer> filterStopWords(Map<String, Integer> d) {
		// filter any stop words here, so remove from the dictionary collection
		// return a dictionary, use the dictionary to store the frequency of a
		// word and the word itself

		for (int i = 0; i < STOP_WORDS.length; i++) {
			if (d.containsKey(STOP_WORDS[i])) {
				d.remove(STOP_WORDS[i]);
			}
		}

		return d;
	}

	private List<String> sortByFreqThenDropFreq(
			Map<String, Integer> wordFrequencies) {
		// sort the dictionary, sort by frequency and drop counts ['code',
		// language']
		// return a List<string>
		List<String> sortedCollection = new ArrayList<String>(
				wordFrequencies.keySet());
		Collections.sort(sortedCollection);
		Collections.reverse(sortedCollection); // largest to smallest
		return sortedCollection;
	}

	private String[] getSentences(String text) {
		return sentenceFinder.findSentences(text);
	}

	private String search(String[] sentences, String word) {
		// search for a particular sentence containing a particular word
		// this function will return the first matching sentence that has a
		// value word
		String first_matching_sentence = null;
		for (int i = 0; i < sentences.length; i++) {
			if (sentences[i].contains(word)) {
				first_matching_sentence = sentences[i];
			}
		}
		return first_matching_sentence;
	}

	public String Summarize(String text, int maxSummarySize) {
		if (text.equals("") || text.equals(" ") || text.equals("\n") || text.trim().isEmpty()) {
			/*String msg = "Nothing to summarize...";
			return msg;*/
			return "";
		}
		// start with raw freqs
		Map<String, Integer> wordFrequencies = getWordCounts(text);

		// filter
		Map<String, Integer> filtered = filterStopWords(wordFrequencies);

		// sort
		List<String> sorted = sortByFreqThenDropFreq(filtered);

		// split the sentences
		String[] sentences = getSentences(text);

		// we should have the first sentence be part of the summary
		String firstSentence = sentences[0];
		String datePatternString = "(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday)\\s\\d{1,2}\\s(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{4}\\s\\d{1,2}\\.\\d{2}(\\sEST|\\sPST)";

		firstSentence = firstSentence.replace("Last modified on", "");
		firstSentence = firstSentence.replaceAll(datePatternString, "");

		// select up to maxSummarySize sentences, so create a List<String>
		Set<String> setSummarySentences = new HashSet<String>();
		for (String word : sorted)// foreach string in the sorted list
		{
			String first_matching_sentence = search(sentences, word);
			setSummarySentences.add(first_matching_sentence);// add to summary
																// list
			if (setSummarySentences.size() == maxSummarySize) {
				break;
			}
		}
		// construct the summary size out of select sentences
		String summary = "";
		/*summary = summary + " " + firstSentence
				+ "." + System.getProperty("line.separator");*/

		for (String sentence : sentences)// foreach string sentence in sentences
											// list
		{
			if (setSummarySentences.contains(sentence)) {
				// produce each sentence with a bullet point and good amounts of
				// spacing
				summary = summary + " " + sentence + ". ";
				/*summary = summary + " " + sentence
						+ "." + System.getProperty("line.separator");*/
			}
		}
		//return summary;
		//System.out.println(StringEscapeUtils.escapeHtml(summary));
		return LanguageUtil.cleanHtmlInvisibleCharacters(StringEscapeUtils.unescapeJava(summary.trim()));
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("line.separator")+"a");
	}
}