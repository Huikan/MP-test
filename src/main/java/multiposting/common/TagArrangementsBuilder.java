package multiposting.common;

import java.util.HashMap;
import java.util.Map;

import multiposting.common.datastructure.TagLinkedListNode;

/**
 * input tags' combination, output duo-arrangement
 * 
 * @author huikan
 * 
 */
public class TagArrangementsBuilder {

	public static Map<String, TagLinkedListNode> build(String[] tagSet) {
		Map<String, TagLinkedListNode> result = new HashMap<String, TagLinkedListNode>();
		for (String tag : tagSet) {
			TagLinkedListNode list = new TagLinkedListNode();
			for (String st : tagSet) {
				if (!st.equals(tag)){
					list.insertNode(new TagLinkedListNode(
							st, 1L));
				}
			}

			result.put(tag, list);
		}

		return result;
	}
}
