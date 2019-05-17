public class OffByN implements CharacterComparator {
    private int diff;
    public OffByN(int N) {
        diff = N;
    }
    public boolean equalChars(char x, char y) {
        int D = x - y;
        return (D == diff || D == -diff);
    }

}
