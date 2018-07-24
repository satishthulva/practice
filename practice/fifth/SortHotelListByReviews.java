
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * <pre>
 * We have conducted a survey on hotels available in our city to rank hotels in the order goodness.
 * 
 * After looking at the reviews, we came up with a set of words which are assumed to give a good 
 * impression about the hotel i.e. we can give good rank to a hotel if the review contains these words. 
 * 
 * Your work is to rank the hotels in the non-increasing order of their goodness. You can calculate goodness
 * value of a hotel as the number of good words used in the review. If a good word is used twice, count it as 2.
 * 
 * Each hotel is given a unique identifier which is an integer. To break ties if two hotels have 
 * same goodness value, return the hotel with smallest id first.
 * 
 * Input format : three parameter to the method
 * keywords : space separated good words
 * Next two parameters are lists. They are connected in the following manner.
 * hotelUniqueIds list at index i will have unique id of hotel for which a review is at hotelReviewsList list at index i.
 * Same hotel can have multiple reviews.
 * 
 * </pre>
 * 
 * @author rebecca
 */
public class SortHotelListByReviews {
	static List<Integer> rankHotels(String keywords, List<Integer> hotelUniqueIds, List<String> hotelReviewsList) {

		Set<String> keywordsSet = new HashSet<>();
		for (String keyword : keywords.split(" ", -1)) {
			keywordsSet.add(keyword.toLowerCase());
		}

		Map<Integer, List<String>> hotelReviewMap = new HashMap<>();
		for (int i = 0; i < hotelUniqueIds.size(); i += 1) {
			int hotelId = hotelUniqueIds.get(i);
			String review = hotelReviewsList.get(i).toLowerCase();
			review = review.replaceAll("\\.", " ");
			review = review.replaceAll(",", " ");

			List<String> hotelReviews = hotelReviewMap.get(hotelId);
			if (hotelReviews == null) {
				hotelReviews = new ArrayList<>();
				hotelReviewMap.put(hotelId, hotelReviews);
			}
			hotelReviews.add(review);
		}
		// score to hotelID map
		Map<Integer, List<Integer>> hotelScoresInverseMap = new TreeMap<>(Collections.reverseOrder());
		for (Entry<Integer, List<String>> hotelEntry : hotelReviewMap.entrySet()) {
			int hotelId = hotelEntry.getKey();
			int score = 0;
			for (String review : hotelEntry.getValue()) {
				for (String reviewWord : review.split(" ", -1)) {
					if (keywordsSet.contains(reviewWord))
						score += 1;
				}
			}

			List<Integer> hotelsWithScore = hotelScoresInverseMap.get(score);
			if (hotelsWithScore == null) {
				hotelsWithScore = new ArrayList<>();
				hotelScoresInverseMap.put(score, hotelsWithScore);
			}
			hotelsWithScore.add(hotelId);
		}

		List<Integer> rankedHotels = new ArrayList<>();

		for (List<Integer> hotels : hotelScoresInverseMap.values()) {
			Collections.sort(hotels);
			rankedHotels.addAll(hotels);
		}

		return rankedHotels;
	}

}
