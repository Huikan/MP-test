package multiposting.mapreduce;

import multiposting.common.datastructure.TagLinkedListNode;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

public class SimTagMapperTest {

	@Test
	public void test() {
		Text value = new Text("23452378	ruby-on-rails,postgresql");

		TagLinkedListNode list1 = new TagLinkedListNode();
		list1.insertNode(new TagLinkedListNode("postgresql", 1L));
		TagLinkedListNode list2 = new TagLinkedListNode();
		list2.insertNode(new TagLinkedListNode("ruby-on-rails", 1L));
		new MapDriver<LongWritable, Text, Text, Text>()
				.withMapper(new SimTagMapper()).withInputValue(value)
				.withOutput(new Text("postgresql"), new Text(list2.toString()))
				.withOutput(new Text("ruby-on-rails"), new Text(list1.toString()))				
				.runTest();
	}

}
