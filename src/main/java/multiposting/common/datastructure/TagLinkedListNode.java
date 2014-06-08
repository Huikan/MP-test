package multiposting.common.datastructure;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * an implementation of {@link Writable} interface, a single-direction linked list
 * head pointer, modification by order of {@link NodeData}
 * 
 * @author huikan
 * 
 */
public class TagLinkedListNode implements Writable {

	private NodeData data;
	private TagLinkedListNode next;

	public TagLinkedListNode() {

	}

	public TagLinkedListNode(String tag, Long multiplicity) {
		this.setData(new NodeData(tag, multiplicity));
		next = null;
	}

	public TagLinkedListNode(NodeData data) {
		this.setData(data);
		next = null;
	}

	public NodeData getData() {
		return data;
	}

	public void setData(NodeData data) {
		this.data = data;
	}

	public TagLinkedListNode getNext() {
		return next;
	}

	public void setNext(TagLinkedListNode next) {
		this.next = next;
	}

	/**
	 * iterate the first 5 tags
	 * 
	 * @return tag1\ttag1_multiplicity\tetc..
	 */
	public String getTop5SimilarTags() {
		String result = "";
		TagLinkedListNode pointer = this.next;
		int counter = 0;
		while (pointer != null && counter <= 5) {
			if (counter > 0) {
				result += "\t";
			}
			result += pointer.getData().toString();
			pointer = pointer.next;
			counter++;
		}
		return result;
	}

	/**
	 * merge this with another list ordered by multiplicity
	 * 
	 * @param otherList
	 */
	public void merge(TagLinkedListNode otherList) {
		TagLinkedListNode pointer = otherList.next;
		while (pointer != null) {
			TagLinkedListNode insert = new TagLinkedListNode(pointer.data);
			this.insertNode(insert);
			pointer = pointer.next;
		}
	}

	/**
	 * insert a new node which has not existed in the list
	 * 
	 * @param newNode
	 */
	private void insertNonExistedNode(TagLinkedListNode newNode) {
		if (this.next == null) {
			// first node after header pointer
			this.next = newNode;
		} else {
			TagLinkedListNode before = this;
			TagLinkedListNode after = this.next;
			boolean alreadyInserted = false;
			while (after != null) {
				if (newNode.getData().compareTo(after.getData()) > 0) {
					// insert in middle of list
					before.next = newNode;
					newNode.next = after;
					alreadyInserted = true;
					break;
				}
				before = after;
				after = after.next;
			}

			if (!alreadyInserted) {
				// append to the tail
				before.next = newNode;
			}
		}
	}

	/**
	 * insert a node
	 * 
	 * @param newNode
	 */
	public void insertNode(TagLinkedListNode newNode) {
		TagLinkedListNode cumulatedNode = checkExistedTag(newNode);
		insertNonExistedNode(cumulatedNode);

	}

	/**
	 * verify if a node has existed in the list in terms of tag
	 * 
	 * @param newNode
	 * @return
	 */
	private TagLinkedListNode checkExistedTag(TagLinkedListNode newNode) {
		TagLinkedListNode cumulatedNode = new TagLinkedListNode(
				newNode.getData());

		TagLinkedListNode prePointer = this;
		TagLinkedListNode pointer = this.next;
		while (pointer != null) {
			if (pointer.getData().getTag()
					.equals(cumulatedNode.getData().getTag())) {
				// refresh the cumulated node
				Long times = cumulatedNode.getData().getMultiplicity()
						+ pointer.getData().getMultiplicity();
				cumulatedNode.getData().setMultiplicity(times);
				// delete the existed node
				prePointer.next = pointer.next;
				break;
			}
			prePointer = pointer;
			pointer = pointer.next;
		}
		return cumulatedNode;
	}

	@Override
	public String toString() {
		// ordered output
		String output = "";
		TagLinkedListNode nomade = this.next;
		while (nomade != null) {
			if (output.length() > 0) {
				output += "\t";
			}
			output += nomade.data.toString();
			nomade = nomade.next;
		}
		return output;
	}

	/**
	 * add a new node to the tail
	 * 
	 * @param data
	 */
	public void addToTail(NodeData data) {
		TagLinkedListNode prePointer = this;
		TagLinkedListNode pointer = this.next;

		while (pointer != null) {
			prePointer = pointer;
			pointer = pointer.next;
		}
		prePointer.next = new TagLinkedListNode(data);

	}

	/**
	 * deserialize a list from a string
	 * 
	 * @param input
	 * @return
	 */
	public static TagLinkedListNode deSerialize(String input) {
		TagLinkedListNode result = new TagLinkedListNode();

		String[] simTagMultiplicity = input.split("\t");

		if (simTagMultiplicity.length >= 2) {
			for (int i = 0; i < simTagMultiplicity.length; i = i + 2) {
				result.addToTail(new NodeData(simTagMultiplicity[i], Long
						.parseLong(simTagMultiplicity[i + 1])));
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.io.Writable#readFields(java.io.DataInput)
	 */
	public void readFields(DataInput input) throws IOException {

		String field = input.readUTF();

		this.next = TagLinkedListNode.deSerialize(field).getNext();

	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.io.Writable#write(java.io.DataOutput)
	 */
	public void write(DataOutput output) throws IOException {
		output.writeUTF(this.toString());
	}

}
