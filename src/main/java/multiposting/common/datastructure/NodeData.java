package multiposting.common.datastructure;

/**
 * data zone : tag and multiplicity
 * 
 * @author huikan
 * 
 */
public class NodeData implements Comparable<NodeData> {

	private String tag;
	private Long multiplicity;

	public NodeData(String tag, Long multiplicity) {
		this.tag = tag;
		this.multiplicity = multiplicity;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(Long multiplicty) {
		this.multiplicity = multiplicty;
	}

	@Override
	public String toString() {
		return tag + "\t" + multiplicity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((multiplicity == null) ? 0 : multiplicity.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeData other = (NodeData) obj;
		if (multiplicity == null) {
			if (other.multiplicity != null)
				return false;
		} else if (!multiplicity.equals(other.multiplicity))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}

	/**
	 * rule for comparison two nodes
	 * 
	 * @return if the same tag return 0; if not, order by multiplicity or tag's
	 *         alphabetic order
	 */
	public int compareTo(NodeData other) {

		if (this == other)
			return 0;
		if (other == null)
			return -1;
		if (getClass() != other.getClass())
			return -1;
		if (tag.compareTo(other.tag) == 0) {
			return 0;
		} else {
			if (multiplicity == null) {
				return -1;
			} else if (multiplicity > other.multiplicity) {
				return 1;
			} else if (multiplicity < other.multiplicity) {
				return -1;
			} else if (multiplicity == other.multiplicity) {
				return tag.compareTo(other.tag);
			}
		}

		return -1;
	}
}
