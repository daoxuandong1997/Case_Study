package storage;

import crawler.Crawler;
import product.Product;
import product.factory.ProductFactory;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    public Storage() {

    }

    public void readFile(ArrayList<Product> productList) {
        try {
            File inFile = new File("F:\\lap trinh\\Codegym\\IntelliJProject\\Module_2\\Exam_MidTerm\\src\\storage\\Product.txt");
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine() ;
            String[] products;
            if (line == null){
                System.out.println("File is empty !");
                Crawler crawler = new Crawler();
                crawler.crawl();
            }
            while (line != null){
                products = line.split(",");
                //System.out.print(splitedLineProducts[]);
                Product product = new Product() {};
                product.setName(products[0]);
                product.setID(products[4]);
                product.setPrice(Integer.parseInt(products[3].trim()));
                product.setCategory(products[1]);
                product.setDescreption(products[2]);
                productList.add(product);
                line = bufferedReader.readLine();
                // chưa hiểu lắm ???
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFile(ArrayList<Product> productList){
        try{
            File outFile = new File("F:\\lap trinh\\Codegym\\IntelliJProject\\Module_2\\Exam_MidTerm\\src\\storage\\Product.txt");
            FileWriter fileWriter = new FileWriter(outFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Product product : productList){
                bufferedWriter.write(String.valueOf(product));
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
