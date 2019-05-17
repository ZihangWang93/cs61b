public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque <Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        if (d.size() == 0 || d.size() == 1){
            return true;
        }else{

            if (d.removeFirst() == d.removeLast()){

                return isPalindrome(dequeToString(d));

            } else{
                return false;
            }
        }
    }

    private String dequeToString(Deque d) {
        String string = "";
        while (d.size() > 0) {
            string += d.removeFirst();
        }
        return string;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        if (d.size() == 0 || d.size() == 1){
            return true;
        }else{

            if (cc.equalChars(d.removeFirst(), d.removeLast())){

                return isPalindrome(dequeToString(d), cc);

            } else{
                return false;
            }
        }
    }

}
