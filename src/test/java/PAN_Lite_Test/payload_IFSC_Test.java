package PAN_Lite_Test;

public class payload_IFSC_Test {


	public static String Addbook(String a , String b , String c) {
		String payload = "{\r\n"
				+ "    \"mode\": \"sync\",\r\n"
				+ "    \"data\": {\r\n"
				+ "        \"ifsc\": \""+a+"\",\r\n"
				+ "        \"consent\": \""+b+"\",\r\n"
				+ "         \"consent_text\": \""+c+"\"\r\n"
				+ "    }\r\n"
				+ "}";
		//	+ "}\r\n";
		return payload ; 
	}

}
