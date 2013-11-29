package fi.eis.md5test.api;

public interface MD5Provider {
    byte[] getMD5(byte[] data);

    byte[] getMD5(String data);
}
