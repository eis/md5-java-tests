package fi.eis.md5test.hexencoders;

import fi.eis.md5test.api.HexEncoder;

//16s with eclipse
public class SecondEncoder implements HexEncoder {
    public char[] bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String str = Integer.toString((data[i] & 0xff) + 0x100, 16);
            sb.append(str, 1, str.length());
        }
        return sb.toString().toCharArray();
    };
}