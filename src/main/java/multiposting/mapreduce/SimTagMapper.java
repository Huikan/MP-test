package multiposting.mapreduce;

import java.io.IOException;
import java.util.Map;

import multiposting.common.TagArrangementsBuilder;
import multiposting.common.datastructure.TagLinkedListNode;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper class
 * 
 * @author huikan
 * 
 */
public class SimTagMapper extends Mapper<LongWritable, Text, Text, Text> {

	/**
	 * map method in which the output key is every tag, output value is its
	 * similar tags with 1-time appearance; output is serialization of object
	 * {@link TagLinkedListNode}
	 * 
	 * @param key
	 * @param Text
	 *            values are lines from input file
	 * @param context
	 */
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] line = value.toString().split("\t");
		String[] tagsArray = line[1].split(",");
		Map<String, TagLinkedListNode> simTags = TagArrangementsBuilder
				.build(tagsArray);

		for (String t : simTags.keySet()) {
			context.write(new Text(t), new Text(simTags.get(t).toString()));
		}

	}
}
