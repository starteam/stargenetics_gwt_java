package java.beans;

public class StringTokenizer
{
	//TODO: make better or correct
	private String str;
	private String split;
	private int index;

	public StringTokenizer(String str, String split)
    {
		this.str = str;
		this.split = split;
		this.index = 0;
    }

	public boolean hasMoreTokens()
    {
	    return str.indexOf(split,index) > 0; 
    }

	public String nextToken()
    {
		int next_index = str.indexOf(split,index);
		String ret = str.substring(index, next_index);
		index = next_index;
		return ret;
    }

}
