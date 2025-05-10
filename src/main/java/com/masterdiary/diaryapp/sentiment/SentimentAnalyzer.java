package com.masterdiary.diaryapp.sentiment;

public class SentimentAnalyzer {
	 public String analyzeSentiment(String content) {
	        if (content == null || content.isEmpty()) {
	            return "Neutral";
	        }

	        String lowercaseContent = content.toLowerCase();

	        if (lowercaseContent.contains("happy") || lowercaseContent.contains("great") || lowercaseContent.contains("joy") || lowercaseContent.contains("awesome")) {
	            return "Positive";
	        } else if (lowercaseContent.contains("sad") || lowercaseContent.contains("bad") || lowercaseContent.contains("terrible") || lowercaseContent.contains("angry")) {
	            return "Negative";
	        } else {
	            return "Neutral";
	        }
	    }
}
