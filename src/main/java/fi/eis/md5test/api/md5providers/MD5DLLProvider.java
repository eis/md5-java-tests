package fi.eis.md5test.api.md5providers;

import java.io.UnsupportedEncodingException;

import com.twmacinta.util.MD5;

import fi.eis.md5test.api.MD5HexProvider;

/**
 * @see http://www.twmacinta.com/myjava/fast_md5.php
 */
public class MD5DLLProvider implements MD5HexProvider {

    @Override
    public char[] toMD5Hex(byte[] data) {
        if (!MD5.initNativeLibrary()) {
            throw new IllegalStateException("Loading native library failed");
        }
        MD5 md5 = new MD5();
        md5.Update(data, data.length);
        return md5.asHex().toCharArray();
    }

    @Override
    public char[] toMD5Hex(String data) {
        MD5 md5 = new MD5();
        try {
            md5.Update(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        return md5.asHex().toCharArray();
    }

}
