package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import chatmodule.Dialogue;
import chatmodule.DialogueHelper;

public class Main {

	public static void main(String[] args) {

		try{
	// create and initialize the ORB 
			
			ORB orb = ORB.init(args,null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			
	// create servant and register it with the ORB
			
			DialogueImpl dialogueImpl = new DialogueImpl();
			dialogueImpl.setOrb(orb);
	
	// get object refernece from the Servant 
			
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(dialogueImpl);
			Dialogue href = DialogueHelper.narrow(ref);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			NameComponent path[] = ncRef.to_name("dialogue");
			ncRef.rebind(path, href);
			
			System.out.println("Hello Server ready and waiting ... ");
			
     // wait for invocation from clients
			
			for(;;){
				orb.run();
			}
			
			
		}catch(Exception e ){
			System.err.println("ERROR : "+ e);
			e.printStackTrace(System.out);
		}
		
		
	} // fin main   

}
