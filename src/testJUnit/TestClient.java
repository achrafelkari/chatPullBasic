package testJUnit;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import chatmodule.Dialogue;
import chatmodule.DialogueHelper;
import org.junit.runners.MethodSorters;




@FixMethodOrder(MethodSorters.NAME_ASCENDING) // je force un peu l'ordre des execution ! 
public class TestClient {
	
	Dialogue obj ;
	
	@Test // je test si on peux prendre d'un anuaire namespace des objet ! 
	public void t1est() {
		try{
			String[] arg = new String[0];
			// Create the ORB (or connect to it in our case)
						ORB orb = ORB.init(arg,null);
						
						org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
						// load the corba Naming Servce 
						
						NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
						
						// get the Hello Object from the Naming service 
						obj = (Dialogue) DialogueHelper.narrow(ncRef.resolve_str("dialogue"));
						assertNotNull(obj);
					
						
			}catch(Exception e){
				System.out.println("Erreur !");
				e.getStackTrace();
			}
	}
 
	@Test
	public void t2estConnection(){
		
		try {
			String[] arg = new String[0];
			ORB orb = ORB.init(arg,null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			obj = (Dialogue) DialogueHelper.narrow(ncRef.resolve_str("dialogue"));

	 int nbrClient1 = 	obj.clients().length;
	 obj.connect("titi");
	 int nbrClient2 = obj.clients().length;
	 assertTrue(nbrClient2>nbrClient1); // on test si le nombre est augmenté ! 
	 
	}catch(Exception e){
		e.getStackTrace();
		} 
	}
	
	@Test
	public void t3estDeconexion(){
		
		try {
			String[] arg = new String[0];
			ORB orb = ORB.init(arg,null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			obj = (Dialogue) DialogueHelper.narrow(ncRef.resolve_str("dialogue"));

	 int nbrClient1 = 	obj.clients().length;
	 obj.disconnect("titi");
	 int nbrClient2 = obj.clients().length;
	 assertTrue(nbrClient2<nbrClient1); // maintenant on test s le nombre est diminué 
	 
			}catch(Exception e){
					e.getStackTrace();
				} 
			}
	
	@Test
	public void t4estMessageSend(){
		// on suppose que TITI s'est connécté puis il s'est déconnécté ! 
		try {
			String[] arg = new String[0];
			ORB orb = ORB.init(arg,null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			obj = (Dialogue) DialogueHelper.narrow(ncRef.resolve_str("dialogue"));

			obj.connect("TITI");
			obj.connect("TOTO");
			
			obj.sendMessage("TITI", "TOTO", "ICI c'est mon message ");
		
			String[] messages = obj.getMessages("TOTO"); 
			
			assertTrue(messages.length>0); // on test à la fois l'envoi du message et le getMessages ! 
	 
			}catch(Exception e){
					e.getStackTrace();
				} 
			}
	
	
}

