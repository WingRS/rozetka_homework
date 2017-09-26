import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StasMaster on 24.09.17.
 */


public class Main {


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://rozetka.com.ua/ua/fitnes-trekery/c4627554/").get();
        Element first_el = new Element("https://rozetka.com.ua/ua/fitnes-trekery/c4627554/");
        List<Product> products = new ArrayList<Product>();
        Elements pages = Parser.getPages(doc);
        pages.add(first_el);
        for (Element page : Parser.getPages(doc) ) {
            Document doc_product = Jsoup.connect(page.attr("href")).get();
            Parser parser = new Parser(products);
            parser.getProducts(doc_product);
        }

    }
}
