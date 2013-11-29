package fi.eis.md5test.hexencoders;

import fi.eis.md5test.api.HexEncoder;

//http://stackoverflow.com/a/9655224/365237
//5.7s with eclipse
public class ThirdEncoder implements HexEncoder {
    static final String HEXES = "0123456789ABCDEF".toLowerCase();

    public char[] bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * data.length);
        for (final byte b : data) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
                    HEXES.charAt((b & 0x0F)));
        }
        return hex.toString().toCharArray();
    }
}