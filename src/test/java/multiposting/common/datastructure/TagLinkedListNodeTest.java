package multiposting.common.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

public class TagLinkedListNodeTest {

	@Test
	public void testMerge() {
		TagLinkedListNode list1 = new TagLinkedListNode();
		list1.insertNode(new TagLinkedListNode("word1", 1L));
		list1.insertNode(new TagLinkedListNode("word3", 22L));
		list1.insertNode(new TagLinkedListNode("word9", 33L));
		list1.insertNode(new TagLinkedListNode("word6", 1L));
		list1.insertNode(new TagLinkedListNode("word15", 5L));
		TagLinkedListNode list2 = new TagLinkedListNode();
		list2.insertNode(new TagLinkedListNode("word4", 45L));
		list2.insertNode(new TagLinkedListNode("word8", 3L));
		list2.insertNode(new TagLinkedListNode("word9", 55L));
		list2.insertNode(new TagLinkedListNode("word6", 5L));
		list2.insertNode(new TagLinkedListNode("word15", 8L));
		list1.merge(list2); 
		assertEquals("word9\t88\tword4\t45\tword3\t22\tword15\t13\tword6"
				+ "\t6\tword8\t3\tword1\t1" + "", list1.toString());
	}

	@Test
	public void testInsertion() {
		TagLinkedListNode header = new TagLinkedListNode();
		TagLinkedListNode newNode = new TagLinkedListNode("test", 123L);
		header.insertNode(newNode);
		assertEquals("test\t123", header.toString());
		newNode = new TagLinkedListNode("after-test", 121L);
		header.insertNode(newNode);
		assertEquals("test\t123\tafter-test\t121", header.toString());
		newNode = new TagLinkedListNode("before-test", 124L);
		header.insertNode(newNode);
		assertEquals("before-test\t124\ttest\t123\tafter-test\t121",
				header.toString());
		newNode = new TagLinkedListNode("after-test", 124L);
		header.insertNode(newNode);
		assertEquals("after-test\t245\tbefore-test\t124\ttest\t123",
				header.toString());

	}

}
