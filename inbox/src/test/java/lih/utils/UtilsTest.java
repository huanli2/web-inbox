package lih.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by huanli on 29/04/2017.
 */
public class UtilsTest {

    private Utils utils = new Utils();

    @Test
    public void sha1Password() {

        String pwd = "test123";
        String salt = "E1RZgYbwub0=";

        String shaPwd = "4d5f74bae6a5c16a0ac7fdb349f81b201818885b";

        Assert.assertEquals(utils.sha1Password(pwd, salt), shaPwd);

        //Assert.assertEquals(new ShaPasswordEncoder().encodePassword(pwd, salt), shaPwd);
    }
}
