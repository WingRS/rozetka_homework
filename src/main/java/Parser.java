
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Product> products = new ArrayList<Product>();
    public Parser(List<Product> products) {
        this.products = products;
    }
    public static void getPages() {
        Document doc = Jsoup.parse("https://rozetka.com.ua/ua/fitnes-trekery/c4627554/");
        String  links = doc.title();
        System.out.println(links);
    }

    public  void getProducts(Document doc) throws IOException {
        Elements el = doc.select(".g-i-tile-i-title a");
        for (Element elem: el) {
            String href = elem.attr("href");
            System.out.println(elem.attr("href"));
            if(!addProducts(href)) { continue; }

        }
    }

    public static Elements getPages(Document doc) {
       return doc.select(".paginator-catalog  ul li a");
    }


    private static  boolean addProducts(String href) throws IOException {
        Document doc_product;
        if(checkUrl(href)) {
            doc_product = Jsoup.connect(href).get();
        }else {
            return false;
        }
        String name = doc_product.select("h1.detail-title[itemprop='name']").text();
        int price = Integer.parseInt(doc_product.select("meta[itemprop='price']").attr("content"));
        boolean isOnSale = true;
        if(price ==0) { isOnSale = false; }else { isOnSale = true; }
        Product product = new Product(href,name,price, isOnSale);
        System.out.println( name +" "+doc_product.select("meta[itemprop='price']").attr("content")+ price);
        goThroughReviews(doc_product,product);
        FileCsv csv = new FileCsv();
        csv.writeToFile(product);
        return products.add(product);

    }



    public static  boolean goThroughReviews(Document doc, Product product ) throws  IOException {
        String href = doc.select("li.m-tabs-i[name='comments'] a").attr("href");
        Document page_reviews = Jsoup.connect(href).get();
        iterateReviewPages(page_reviews, product);
        return  true;
    }

    public static boolean checkUrl(String url) throws IOException {
        try {
               Document doc = Jsoup.connect(url).get();
        } catch (IllegalArgumentException e) {
           return  false;
        }
        return  true;
    }

    public static void  iterateReviewPages(Document doc, Product product) throws IOException {


       Elements reviews_block = doc.select(".pp-review-i");
        for (Element el: reviews_block) {
            String  author  = el.select(".pp-review-author-name[itemprop='author']").text();
            String  text    = el.select(".pp-review-text-i").text();
            String  date    = el.select("time.pp-review-date-text").text();
            String rate_el = el.select("span.g-rating-stars-i").attr("content");

            Reviews review = new Reviews(author, text, rate_el, date);
            System.out.println(author+date);
            if(product.reviews.add(review)) {
                continue;
            }
        }

        Elements links = doc.select(".paginator-catalog-l-i span.paginator-catalog-l-link").parents();
            if(links.isEmpty()) {return;}

         Element next =  findNextPage(links, doc);
            Element el_href = next.nextElementSibling();
            String href =  el_href.select("a").attr("href");
            if(checkUrl(href)) {
                Document next_doc = Jsoup.connect(href).get();
                try {
                    iterateReviewPages(next_doc, product);
                } catch (NullPointerException e) {
                    return;
                }
            }

    }
    public static Element findNextPage(Elements links, Document doc) {
        for (Element link: links) {
            Elements children = link.children();
            for( Element child : children ) {
                if(child.hasClass("paginator-catalog-l-link")) {
                    return link;
                } else {
                    continue;
                }
            }
            continue;
        }
        return links.get(0);
    }

 }
