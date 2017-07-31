package duplicateFinder;

/**
 *
 * @author Antero Oikkonen
 */
public class Words implements Comparable<Words> {

    private String word;
    private int amount;

    public Words(String sana, int amount) {
        this.word = sana;
        this.amount = amount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addOne() {
        amount += 1;
    }

    @Override
    public int compareTo(Words o) {
        if (o.getAmount() > this.amount) {
            return 1;
        } else if (o.getAmount() == this.amount) {
            return word.compareTo(o.getWord());
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Words) {
            return word.equals(((Words) obj).getWord());
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + word + ", " + amount + "]";
    }
}
