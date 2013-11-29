package fi.eis.md5test.api.md5providers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fi.eis.md5test.api.MD5Provider;

public class MessageDigestMD5Provider implements MD5Provider {
    private MessageDigest digest;

    public MessageDigestMD5Provider() {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    public byte[] getMD5(String data) {
        try {
            return getMD5(data.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public byte[] getMD5(byte[] data) {
        digest.update(data);
        return digest.digest();
    }

}
