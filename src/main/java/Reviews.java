/**
 * Created by StasMaster on 25.09.17.
 */
public class Reviews {

    String author;
    String text;
    String rate;
    String date;
    public Reviews() {

    }

    public Reviews(String author, String text, String rate, String date) {
        this.author = author;
        this.text = text;
        this.rate = rate;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", rate=" + rate +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
