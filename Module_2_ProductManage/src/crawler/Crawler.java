package crawler;
import product.Product;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
    private static ArrayList<String> productCrawler = new ArrayList<>();

    public static void crawlProduct (String source) {
        try {
            URL url = new URL(source);
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();
            content.replaceAll("\\n+", "");
            content = content.trim().replaceAll(" +", " ");
            String pattern = "data-brand=\"(.*?)\" data-cat=\"(.*?)\" data-code=\"(.*?)\" data-price=\"(.*?)\" id=\"(.*?)\"";
            Pattern price = Pattern.compile(pattern);
            Matcher mPrice = price.matcher(content);

            while (mPrice.find()) {
                // System.out.println(mPrice.group(1) + " " + mPrice.group(2) + " " + mPrice.group(3) +" "+ mPrice.group(4) +" "+ mPrice.group(5));
                productCrawler.add(mPrice.group(1) + ", " + mPrice.group(2) + ", " + mPrice.group(3) +", "+ mPrice.group(4) +", "+ mPrice.group(5) + "\n");

            }
            writeFile(productCrawler);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeFile(ArrayList<String> products){
        try {
            File productCrawl = new File("F:\\lap trinh\\Codegym\\IntelliJProject\\Module_2\\Exam_MidTerm\\src\\storage\\Product.txt");
            FileWriter writer = new FileWriter(productCrawl);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String pr : products){
                bufferedWriter.write(pr);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void crawl()
    {
        final String URL_SAMSUNG = "https://www.thegioididong.com/dtdd-samsung";
        final String URL_IPHONE = "https://www.thegioididong.com/dtdd-apple-iphone";
        crawlProduct(URL_IPHONE);
        crawlProduct(URL_SAMSUNG);
    }
}
