package multiposting.mapreduce;

import java.io.IOException;

import multiposting.common.datastructure.TagLinkedListNode;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reduce class
 * 
 * @author huikan
 * 
 */
public class SimTagReducer extends Reducer<Text, Text, Text, Text> {

	/**
	 * reduce method: on the same key, merge all {@link TagLinkedListNode} and
	 * return its 5 most similar tags as output value
	 * 
	 * @param inKey
	 * @param inValues
	 * @param context
	 */
	@Override
	public void reduce(Text inKey, Iterable<Text> inValues, Context context)
			throws IOException, InterruptedException {

		TagLinkedListNode list = new TagLinkedListNode();
		for (Text value : inValues) {
			list.merge(TagLinkedListNode.deSerialize(value.toString()));
		}

		context.write(inKey, new Text(list.toString()));
	}
}
