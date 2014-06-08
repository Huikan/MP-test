package multiposting.mapreduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

public class SimTagReducerTest {

	@Test
	public void test() {
		new ReduceDriver<Text, Text, Text, Text> ()
		.withReducer(new SimTagReducer())
		.withInputKey(new Text("keyword"))
		.withInputValue(new Text("word9\t88\tword4\t45\tword3\t22"))
		.withInputValue(new Text("word9\t88\tword6"
				+ "\t6\tword8\t3\tword1\t1"))
		.withOutput(new Text("keyword"), new Text("word9\t176\tword4\t45\tword3\t22\tword6"
				+ "\t6\tword8\t3\tword1\t1"))
		.runTest();
		
		;
		
	}

}
