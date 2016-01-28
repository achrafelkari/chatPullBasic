package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import chatmodule.Dialogue;
import chatmodule.DialogueHelper;

public class Main {

	public static void main(String[] args) {
		try{
			// Create the ORB (or connect to it in our case)
			ORB orb = ORB.init(args,null);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			// load the corba Naming Servce 
			
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			// get the Hello Object from the Naming service 
			Dialogue obj = (Dialogue) DialogueHelper.narrow(ncRef.resolve_str("dialogue"));
			
			// dump the message to console
			

			obj.connect("Achraf"); // Je me connecte avec l'identifiant Achraf 
			
			
			System.out.println("Consulter mon message :  "+ obj.getMessages("Achraf")[0]);
			
			obj.sendMessage("Achraf", "Moussa", "C'est Achraf qui t'envoi le message, cava Moussa? ");
            
		//	obj.getMessages("Achraf");
			
		}catch(Exception e){
			
		}

	}
}
