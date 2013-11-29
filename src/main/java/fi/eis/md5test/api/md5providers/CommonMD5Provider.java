package fi.eis.md5test.api.md5providers;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import fi.eis.md5test.api.MD5HexProvider;

public class CommonMD5Provider implements MD5HexProvider {

    @Override
    public char[] toMD5Hex(byte[] data) {
        return Hex.encodeHex(DigestUtils.md5(data));
    }

    @Override
    public char[] toMD5Hex(String data) {
        return DigestUtils.md5Hex(data).toCharArray();
    }

}
