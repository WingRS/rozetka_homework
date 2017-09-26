import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StasMaster on 25.09.17.
 */
public class Product {
    String url;
    String name;
    boolean isOnSale;
    int price;
    int reviews_count;
   public List<Reviews> reviews = new ArrayList<Reviews>();

    public Product(String url, String name, int price, boolean isOnSale) {
        this.url = url;
        this.name = name;
        this.price = price;
        this.isOnSale = isOnSale;
    }

    public Product() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReviews_count() {
        return reviews.size();
    }


    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
}
