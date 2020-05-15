package storage;

import product.PhoneProduct;
import product.Product;

import java.io.*;
import java.util.ArrayList;

public class Storage {


    public Storage() {

    }

    public boolean readFile(ArrayList<Product> productList, String source) {
        try {
            File inFile = new File(source);
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine() ;
            String[] products;
            if (line == null)
                return false;
            while (line != null) {
                products = line.split(",");
                //System.out.print(splitedLineProducts[]);
                Product product = new PhoneProduct() {
                };
                product.setName(products[2]);
                product.setID(products[4]);
                product.setPrice(Integer.parseInt(products[3].trim()));
                product.setCategory(products[0]);
                product.setBrand(products[1]);
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
        return true;
    }

    public boolean updateFile(ArrayList<Product> productList, String source){
        try{
            File outFile = new File(source);
            FileWriter fileWriter = new FileWriter(outFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < productList.size(); i++){
                Product updateProduct = productList.get(i);
                String content = updateProduct.getCategory() + ", " + updateProduct.getBrand() + ", " +updateProduct.getName() + ", " + updateProduct.getPrice() + ", " + updateProduct.getID();
                bufferedWriter.write(content);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
