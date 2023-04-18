package analyzer.utils;

public class SearchMethods {

    public boolean methodNaive(String pattern, String text) {
        return (text.matches(".*" + pattern + ".*"));
    }

    /**
     * Knuth-Morris-Pratt algorithm
     * @param pattern
     * @param text
     * @return true if pattern found in text
     */
    public boolean methodKMP(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();

        int[] prefix = new int[patternLength];
        int j = 0;

        fillPrefix(pattern, prefix);

        int i = 0;
        while (i < textLength) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == patternLength) {
                return true;
            } else if (i < textLength && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = prefix[j - 1];
                else
                    i++;
            }
        }

        return false;
    }

    /**
     * Rabin-Karp algorithm
     * @param pattern
     * @param text
     * @return true if pattern found in text
     */
    public boolean methodRK(String pattern, String text) {
        boolean found = false;
        int a = 3;
        int m = 112;
        int pl = pattern.length();
        int tl = text.length();

        int patHash = 0;

        for (int i = 0; i < pl; i++) {
            patHash += (pattern.charAt(i)) * Math.pow(a, i);
        }

        patHash %= m;

        for (int i = tl - pl; i >= 0; i--) {
            int textHash = 0;
            for (int k = 0; k < pl; k++) {
                textHash += (text.charAt(i + k)) * Math.pow(a, k);
            }

            textHash  %= m;

            int count = 0;

            if (patHash == textHash) {
                for (int j = 0; j < pl; j++) {
                    count++;
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }
                if (count == pl) {
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    private void fillPrefix(String pattern, int[] prefix) {
        int len = 0;
        int i = 1;
        prefix[0] = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = prefix[len - 1];
                } else {
                    prefix[i] = len;
                    i++;
                }
            }
        }
    }
}
