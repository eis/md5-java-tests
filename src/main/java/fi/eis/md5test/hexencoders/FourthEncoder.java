package fi.eis.md5test.hexencoders;

import fi.eis.md5test.api.HexEncoder;

//http://stackoverflow.com/a/9855338/365237
//4.89s, eclipse
public class FourthEncoder implements HexEncoder {
    final protected static char[] hexArray = "0123456789abcdef".toCharArray();

    public char[] bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return hexChars;// new String(hexChars);
    }
}