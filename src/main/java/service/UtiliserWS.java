package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import controleur.Controleur_PageAccueil;

/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
*/



public class UtiliserWS {
	//private static final String ENCODAGE_PARAMETRE_URL = "UTF-8";

	public static String utiliserService(String u) throws Exception{
		//System.out.println(u);
		String t = "";
		URL url = new URL(u);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String tmp = "";
		while((tmp=in.readLine()) != null){	t += tmp; }
		con.disconnect();
		return t;
	}

	public static String service_Who() throws Exception {
		String url = Controleur_PageAccueil.ADRESSE_IP_SERVEUR + "Who";
		/*url += "wal.php?inscription&identite=" + URLEncoder.encode(identite, ENCODAGE_PARAMETRE_URL) + "&mail=" + 
				URLEncoder.encode(eMail, ENCODAGE_PARAMETRE_URL) + "";*/
		return utiliserService(url);
	}
	
	public static String service_Nouveau(String pseudo) throws Exception {
		String url = Controleur_PageAccueil.ADRESSE_IP_SERVEUR + "Nouveau?pseudo=" + pseudo;
		return utiliserService(url);
	}
	
	public static String getStatusService_Nouveau(String jsonNouveau) throws Exception {
		String status = "";
		JSONObject jo = null;
		try {
			jo = new JSONObject(jsonNouveau);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		try {
			status = jo.getString("status");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return status;
	}
	
	public static String service_getNombreJoueursConnectes() throws Exception {
		String nbJoueursConnectes = "";
		String who = UtiliserWS.service_Who();
		return nbJoueursConnectes;
	}

	/*public static int getReponse(String jsonString) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(jsonString);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		int reponse = 0;
		try {
			reponse = jo.getJSONObject("etat").getInt("reponse");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		/*String message = "";
		try {
			message = jo.getJSONObject("etat").getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
		//return reponse;
	//}

	/*public static String getIdApresActivation(String jsonString) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(jsonString);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		String id = "";
		try {
			id = jo.getString("identifiant");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return id;
	}

	public static String serviceObtenirParametres(String idUser) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?identifiant=" + URLEncoder.encode(idUser, ENCODAGE_PARAMETRE_URL) + "";
		return utiliserService(url);
	}

	public static String getIdentiteApresParametre(String jsonString) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(jsonString);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		String reponse = "";
		try {
			reponse = jo.getString("identite");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return reponse;
	}

	public static String serviceListerRelation(String idUser) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?identifiant=" + URLEncoder.encode(idUser, ENCODAGE_PARAMETRE_URL) + "&relations";
		return utiliserService(url);
	}

	public static ArrayList<Ami> lireListeAmisApresListage(ArrayList<Ami> listeAmis, String jsonString) {
		JSONObject jo = null;
		JSONArray tab = null;
		try {
			jo = new JSONObject(jsonString);
			tab = jo.getJSONArray("relations");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String identiteAmi = "";
		int identifiantRelationAmi = 0;
		int identifiantAmi = 0;
		for(int i = 0 ; i < tab.length() ; i++) {
			try {
				identifiantRelationAmi = tab.getJSONObject(i).getInt("relation");
				identifiantAmi = tab.getJSONObject(i).getInt("identifiant");
				identiteAmi = tab.getJSONObject(i).getString("identite");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			listeAmis.add(new Ami(identiteAmi, identifiantRelationAmi, identifiantAmi));
		}
		Collections.sort(listeAmis);
		return listeAmis;
	}

	public static String serviceAjoutAmi(String idUser, String emailAmi) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?identifiant=" + URLEncoder.encode(idUser, ENCODAGE_PARAMETRE_URL) + "&lier&mail=" 
				+ URLEncoder.encode(emailAmi, ENCODAGE_PARAMETRE_URL) +"";
		return utiliserService(url);
	}

	public static String serviceLireMessages(int idRelationCorrespondant) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?identifiant=" + URLEncoder.encode(GestionDiscussion.getIdentifiantUtilisateur(), 
				ENCODAGE_PARAMETRE_URL) + "&lire&relation=" 
				+ URLEncoder.encode("" + idRelationCorrespondant, ENCODAGE_PARAMETRE_URL) +"";
		return utiliserService(url);
	}

	public static String serviceEnvoyerMessage(int idParametreCorrespondant, String message) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?identifiant=" + URLEncoder.encode(GestionDiscussion.getIdentifiantUtilisateur(), 
				ENCODAGE_PARAMETRE_URL) + "&ecrire&destinataire="
				+ URLEncoder.encode("" + idParametreCorrespondant, ENCODAGE_PARAMETRE_URL) 
				+"&message=" + URLEncoder.encode(message, ENCODAGE_PARAMETRE_URL);
		return utiliserService(url);
	}*/
}

























