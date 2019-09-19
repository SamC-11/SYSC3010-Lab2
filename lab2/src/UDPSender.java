import java.net.*;

public class UDPSender {
	
	private final static int PACKETSIZE = 100 ;

	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port messages" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
	         int messages = Integer.parseInt(args[2]);
	         
	         socket = new DatagramSocket() ;
     
	         
	         for (int i=0;i<=messages;i++)
	         {
	        		 System.out.println("Sent Message"+String.valueOf(i));
	        		 byte [] data = ("Message"+String.valueOf(i)).getBytes() ;
	        		 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        		 socket.send( packet ) ;
	        		 
	        		 System.out.println( "Waiting for response..");
	        		 DatagramPacket response=new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
	        		 socket.receive( response );
	        		 System.out.println( response.getAddress() + " " + response.getPort() + ": " + new String(response.getData()).trim() ) ;
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}

