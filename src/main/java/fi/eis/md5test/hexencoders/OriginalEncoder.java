package fi.eis.md5test.hexencoders;

import fi.eis.md5test.api.HexEncoder;

//26s with eclipse
public class OriginalEncoder implements HexEncoder {
    public char[] bytesToHex(byte[] data) {
        String result = "";
        for (int i = 0; i < data.length; i++) {
            String str = Integer.toString((data[i] & 0xff) + 0x100, 16);
            result += str.substring(1);
        }
        return result.toCharArray();
    }
}