package fi.eis.md5test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fi.eis.md5test.api.HexEncoder;
import fi.eis.md5test.api.MD5HexProvider;
import fi.eis.md5test.api.MD5HexProviderFactory;
import fi.eis.md5test.api.MD5Provider;
import fi.eis.md5test.api.md5providers.CommonMD5Provider;
import fi.eis.md5test.api.md5providers.MD5DLLProvider;
import fi.eis.md5test.api.md5providers.MessageDigestMD5Provider;
import fi.eis.md5test.hexencoders.FourthEncoder;
import fi.eis.md5test.hexencoders.OriginalEncoder;
import fi.eis.md5test.hexencoders.SecondEncoder;
import fi.eis.md5test.hexencoders.ThirdEncoder;

public class MD5FinderTest {
    static final String md5            = "49fc455b20e50f22a736048ceba0ad39";
    static final int    length         = 5;
    static final String expectedResult = "vcckp";

    @BeforeClass
    public static void beforeClass() {
        System.out.printf("Using md5 '%s' with len '%d'%n", md5, length);
    }

    @Test
    public void testOriginalEncoder() {
        tryWith(new MessageDigestMD5Provider(), new OriginalEncoder());
    }

    @Test
    public void testSecondEncoder() {
        tryWith(new MessageDigestMD5Provider(), new SecondEncoder());
    }

    @Test
    public void testThirdEncoder() {
        tryWith(new MessageDigestMD5Provider(), new ThirdEncoder());
    }

    @Test
    public void testFourthEncoder() {
        tryWith(new MessageDigestMD5Provider(), new FourthEncoder());
    }

    @Test
    public void testCommonEncoder() {
        tryWith(new CommonMD5Provider());
    }

    @Test
    public void testDllApproach() {
        tryWith(new MD5DLLProvider());
    }

    public static void tryWith(MD5Provider md5Provider, HexEncoder hexEncoder) {
        tryWith(MD5HexProviderFactory.provider(md5Provider, hexEncoder));
    }

    public static void tryWith(MD5HexProvider md5HexProvider) {
        try {
            Assert.assertEquals(expectedResult, MyMD5Finder.tryWith(md5HexProvider, md5, length));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
