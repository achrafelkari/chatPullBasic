package server;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.ORB;

import chatmodule.DialoguePOA;
import chatmodule.Message;

public class DialogueImpl extends DialoguePOA {
	private ORB orb; 
	
	public static List<String> clients = new ArrayList<String>();
	public static List<Message> messages = new ArrayList<Message>();
	
	
	public void setOrb(ORB orb) {
		this.orb = orb;
	}
	
	// je vois si un pseudo est déja connécté ou pas ! 
	boolean isConnected(String pseudo){ // une fonction qui test si un client  est déjà connécté ou pas
		boolean connected =false;
		
		for(String pseu : clients)			
			if(pseu.equals(pseudo)){
				connected=true;
				break;
				}
		return connected;
	} // fin 
	
	
	@Override
	public String[] clients() { // retourne tout les client stocké dans l'ArrayList
		String[] clientsTab  = new String[clients.size()];
	     int i=0;
		for(String s : clients){
			
			clientsTab[i] = clients.get(i);
			i++;
		}
		return clientsTab;
	}

	@Override
	public void clients(String[] newClients) {
		// TODO Auto-generated method stub
		
	}

	
// je test si l'utilisateur est déja connécté d'abord ! 
	@Override
	public void connect(String pseudo) { // il permet d'ajouter un client à un ArrayList des client ! 
		if(!isConnected(pseudo)){
			clients.add(pseudo);
			System.out.println( " connexion réussite de l'utilisateur : "+pseudo);
			}else{
			System.out.println(" vous êtes déja connécté ! vérifier si quelqu'un d'autre utilise ton pseudo ! " );
			}
	}

	// j'enleve le client de la liste pour lui déconnecté ! 
	@Override
	public void disconnect(String pseudo) { /// supprimer le clin de l'arrayList des clients ! 
	
		if(isConnected(pseudo)){
		String client= ""; 
		for(String p : clients){
			
			if(pseudo.equals(p)){
			 client = p;				
			}	
		}
		
		clients.remove(client);
		System.out.println("Déconnexion de : "+pseudo );
		}else
			System.out.println(pseudo + " n'est pas coonécté ! ");
		
	}


// je créee un tableau de clients à partir de mon ArrayList ! 
	@Override
	public String[] getClients() {
		String[] lesClients = new String[clients.size()];
		int i =0;
		for(String client : clients){
		 lesClients[i] =client;
		 i++;
		}
		 return lesClients;
	}

	
	@Override
	public void sendMessage(String from, String to, String message) { // j'jaoute un message dans l'arrayList des messages  !
		messages.add(new Message(from, to, message));
		System.out.println("Message envoyé ::  From :" +from + ", to : "+ to +" , message : "+ message);
		
	}

	@Override
	public String[] getMessages(String pseudo) {
		ArrayList<String> arr = new ArrayList<String>();

		int i=0;
	 for(Message message : messages){
		 
		 if(message.to.equals(pseudo)){
			 arr.add(message.message);
		 }	
	 }
	 
		String[] messagePseudo = new String[arr.size()];
	  for(String mss: arr){
		  messagePseudo[i] = arr.get(i);
		  i++;
	  }
	 
	 	return messagePseudo;
	}

	@Override
	public String[] messages() {
		String[] mesgs = new String[messages.size()];
		int i=0; 
		for(Message mss: messages){
			mesgs[i] = messages.get(i).message;
			  i++;
		  }
		return mesgs;
	}

	@Override
	public void messages(String[] newMessages) {
		// TODO Auto-generated method stub
	}


}
