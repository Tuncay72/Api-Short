package zudemy;

public class Library {
    public static String Addbook(String isbn,String aisle) {
        
        String payload = "{\n" +
                "        \"name\":\"Learn Appium Automation with Java\",\n" +
                "        \"isbn\":\""+isbn+"\",\n" +
                "         \"aisle\":\""+aisle+"\",\n" +
                "          \"author\":\"John foe\"\n" +
                "} ";
        return payload;
    }
}
