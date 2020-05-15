package crawler;

import manager.ProductManage;
import storage.ProductList;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductCrawler {


    private static volatile ProductCrawler instance;

    private ProductCrawler() {
    }

    public static ProductCrawler getInstance() {
        if (instance == null) {
            instance = new ProductCrawler();
        }
        return instance;
    }

//    private static final String FILE_SOURCE = "F:\\lap trinh\\Codegym\\IntelliJProject\\Case_Study\\Module_2_product_manager\\src\\storage\\Product.txt";
    ;
    private static ArrayList<String> productCrawler = new ArrayList<>();

    public static ArrayList<String> getProductCrawler() {
        return productCrawler;
    }

    public static void setProductCrawler(ArrayList<String> productCrawler) {
        ProductCrawler.productCrawler = productCrawler;
    }

    private static void crawlProduct (String source) {
        try {
            URL url = new URL(source);
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();
            content.replaceAll("\\n+", "");
            content = content.trim().replaceAll(" +", " ");

            String pattern = "data-brand=\"(.*?)\" data-cat=\"(.*?)\" data-code=\"(.*?)\" data-price=\"(.*?)\" id=\"data(.*?)\"";
            Pattern product = Pattern.compile(pattern);
            Matcher mProduct = product.matcher(content);

            String pName = "<h3>(.*?)</h3>";
            Pattern productName = Pattern.compile(pName);
            Matcher mName = productName.matcher(content);

            while (mProduct.find() && mName.find()) {
                // System.out.println(mPrice.group(1) + " " + mPrice.group(2) + " " + mPrice.group(3) +" "+ mPrice.group(4) +" "+ mPrice.group(5));
                productCrawler.add(mProduct.group(2) + ", " + mProduct.group(1) + ", " + mName.group(1) + ", " + mProduct.group(4) +", "+ mProduct.group(5) + "\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readFile(String source) {
        try {
            File inFile = new File( source);
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line ;

            while ((line = bufferedReader.readLine()) != null) {
                productCrawler.add(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void writeFile(String source){
        try {
            File productCrawl = new File( source);
            FileWriter writer = new FileWriter(productCrawl);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String product : productCrawler){
                bufferedWriter.write(product);

            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crawl( String source)
    {
        final String URL_SAMSUNG = "https://www.thegioididong.com/dtdd-samsung";
        final String URL_IPHONE = "https://www.thegioididong.com/dtdd-apple-iphone";
        final String URL_MACBOOK = "https://www.thegioididong.com/laptop-apple-macbook";
        final String URL_ASUS = "https://www.thegioididong.com/laptop-asus";
        final String URL_SMART_WATCH = "https://www.thegioididong.com/dong-ho-thong-minh";
        final String URL_TABLET = "https://www.thegioididong.com/may-tinh-bang-apple-ipad";

        crawlProduct(URL_IPHONE);
        crawlProduct(URL_SAMSUNG);
        crawlProduct(URL_MACBOOK);
        crawlProduct(URL_ASUS);
        crawlProduct(URL_SMART_WATCH);
        crawlProduct(URL_TABLET);

        writeFile(source);
    }
}
