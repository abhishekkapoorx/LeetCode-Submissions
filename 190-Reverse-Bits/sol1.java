// 190. Reverse Bits
class Solution {
    public int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
            int j = 31 - i;

            int bitI = (n >> i) & 1;
            int bitJ = (n >> j) & 1;

            if (bitI != bitJ) {
                n ^= (1 << i);
                n ^= (1 << j);
            }
        }
        return n;
    }
}