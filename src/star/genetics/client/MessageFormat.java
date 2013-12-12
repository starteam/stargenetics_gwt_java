package star.genetics.client;

public class MessageFormat
{
	public static String format(String format,Object... varags) {
		log( "MessageFormat.format: " + format );
		StringBuffer sb  = new StringBuffer(format);
		
		for( Object o : varags )
		{
			sb.append("ARG:" + o + ":,");
		}
		log( sb.toString() );
		return sb.toString();		
	};
	
	private static native void log( String str )
	/*-{
		if( typeof(console) == 'object' && console && console.info )
		{
			console.info( str ) ;
		}
	}-*/;
}
