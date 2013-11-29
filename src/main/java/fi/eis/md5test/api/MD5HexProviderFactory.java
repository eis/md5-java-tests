package fi.eis.md5test.api;

import java.io.UnsupportedEncodingException;

public class MD5HexProviderFactory {
    public static MD5HexProvider provider(final MD5Provider md5Provider, final HexEncoder hexEncoder) {
        return new MD5HexProvider() {
            @Override
            public char[] toMD5Hex(byte[] data) {
                byte[] bytes = md5Provider.getMD5(data);
                return hexEncoder.bytesToHex(bytes);
            }

            @Override
            public char[] toMD5Hex(String data) {
                try {
                    return toMD5Hex(data.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalStateException(e);
                }
            };

            @Override
            public String toString() {
                return String.format("%s [md5Provider=%s,hexCreator=%s]", this.getClass().getName(), md5Provider
                        .getClass().getName(), hexEncoder.getClass().getName());
            }
        };
    }
}
