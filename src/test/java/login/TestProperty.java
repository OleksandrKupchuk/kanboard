package login;

import org.testng.annotations.Test;

public class TestProperty {
    @Test
    public void tesProp(){
        String msg = System.getProperty("my_message");
        System.out.println(msg);
    }
}
