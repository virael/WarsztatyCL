package warsztatyCL;

public class Main {

	public static void main(String[] args) {
		
//		DataBaseCreator dataBaseCreator = new DataBaseCreator();
//		dataBaseCreator.createDataBase();

		DataBaseConnector dataBaseConnector = new DataBaseConnector();
		dataBaseConnector.connectToDataBase();
		
		User user = new User();
		user.setUsername("Lolek");
		user.setPassword("bolek1");
		user.setEmail("bolekilolek@gmail.com");
		//user.saveToDB();
	}
}
