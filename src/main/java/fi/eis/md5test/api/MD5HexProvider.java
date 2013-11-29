package fi.eis.md5test.api;

public interface MD5HexProvider {
    char[] toMD5Hex(byte[] data);
    char[] toMD5Hex(String data);
}
