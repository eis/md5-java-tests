package fi.eis.md5test;

import java.util.Arrays;

import fi.eis.md5test.api.MD5HexProvider;

class MyMD5Finder {
    private String[] letters;
    private long     checksumsCreated = 0;
    private boolean  countInstances   = true;

    public MyMD5Finder(String[] letters) {
        this.letters = letters;
    }

    private MyMD5Finder() {
        this.letters = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
    }

    private MD5HexProvider md5HexProvider;

    public MyMD5Finder(MD5HexProvider provider) {
        this();
        this.md5HexProvider = provider;
    }

    private void finder(char[] target, int target_len, String generated, int generated_len) throws Exception {
        if (target_len == generated_len) {
            if (this.countInstances) {
                this.checksumsCreated++;
            }
            if (Arrays.equals(target, md5HexProvider.toMD5Hex(generated))) {
                throw new RuntimeException(generated);
            }
            return;
        }
        for (String generated_2 : this.letters) {
            finder(target, target_len, generated + generated_2, generated_len + 1);
        }
    }

    public String crackmd5(String target, int len) throws Exception {
        char[] targetCharArr = target.toCharArray();
        try {
            for (String generated : this.letters) {
                finder(targetCharArr, len, generated, 1);
            }
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return null;
    }

    public static String tryWith(MD5HexProvider md5HexProvider, String md5, int length) throws Exception {
        MyMD5Finder finder = new MyMD5Finder(md5HexProvider);
        return finder.crackmd5WithPrintout(md5, length);
    }

    private String crackmd5WithPrintout(String md5, int length) throws Exception {
        System.out.printf("Using md5HashClass=%s%n", this.md5HexProvider.toString());
        long starttime = System.currentTimeMillis();
        String result = this.crackmd5(md5, length);
        long endtime = System.currentTimeMillis();
        long time = endtime - starttime;
        double timeSeconds = ((double) time) / 1000;
        if (result == null) {
            System.out.printf("Did nothing in %f seconds%n", timeSeconds);
        } else {
            if (countInstances) {
                System.out.printf("Created %d instances in %.2f seconds, speed: %d instances/s%n", checksumsCreated,
                        timeSeconds, Math.round(checksumsCreated / timeSeconds));
            }
            if (!countInstances) {
                System.out.printf("Took %f seconds%n", timeSeconds);
            }
        }
        return result;
    }

    public void countInstances(boolean b) {
        this.countInstances = b;
    }

    public long checksumsCreated() {
        return this.checksumsCreated;
    }
}
