package helpdesk_router_model;

public class KeywordHolder {


    private final int Score;
    private final String Key_Word;

    public KeywordHolder (String Key_Word, int Score) {

        this.Key_Word = Key_Word;
        this.Score = Score;
    }

    public int getScore () {

        return  Score;
    }

    public String getKey_Word() {

        return Key_Word;
    }



}
