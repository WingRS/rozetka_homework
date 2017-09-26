import com.csvreader.CsvWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FileCsv {

    public String name;


    public void writeToFile(Product product) throws IOException {
        CsvWriter csvOutput = new CsvWriter(new FileWriter( product.getUrl().split("/")[4] + ".csv"), ',');
        csvOutput.write("Назва: "+product.name);
        csvOutput.write("Ціна: " +Integer.toString(product.price));
        csvOutput.write("Посилання: "+product.url);
        csvOutput.write("Кількість відгуків: "+Integer.toString(product.getReviews_count()));
        csvOutput.endRecord();
        csvOutput.write("#");
        csvOutput.write("Ціна");
        csvOutput.write("rate");
        csvOutput.write("review");
        csvOutput.write("date");
        csvOutput.endRecord();
        if (product.getReviews_count() == 0) { csvOutput.close(); return; }
        int i = 0;
        for (Reviews reviews : product.reviews ) {
            csvOutput.write(Integer.toString(i));
            csvOutput.write(product.price+"UAH");
            csvOutput.write(reviews.rate);
            csvOutput.write(reviews.text);
            csvOutput.write(reviews.date);
            csvOutput.endRecord();
            i++;
            csvOutput.flush();
        }
        csvOutput.close();
    }
}
