public class OffByN implements CharacterComparator {
    int diff;
    OffByN(int N){
        diff = N;
    }
    public boolean equalChars(char x, char y){
        int diff_x_y = x - y;
        if(diff_x_y == diff || diff_x_y == - diff){
            return true;
        } else{ return false;}
    }

}
