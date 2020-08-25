/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Controller {

    ArrayList<Category> categoryList;
    ArrayList<Product> productList;
    Scanner sc = new Scanner(System.in);

    private void addCategory() {
        System.out.println("Input Category Name : ");
        String name = sc.nextLine();
        System.out.println("Input Category Tag : ");
        String tag = sc.nextLine();
        if (!checkCategoryTag(tag)) {
            System.out.println("Category tag already exist !!!");
        } else {
            categoryList.add(new Category(name, tag));
            try {
                FileOutputStream fos = new FileOutputStream("Category.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                Category[] category = new Category[categoryList.size()];
                for (int index = 0; index < categoryList.size(); index++) {
                    category[index] = categoryList.get(index);
                }

                oos.writeObject(category);
                oos.close();
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private void getCategoryData() {
        categoryList = new ArrayList<>();
        try {
            File f = new File("Category.bin");
            if (f.exists()) {
                FileInputStream fis = new FileInputStream("Category.bin");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Category[] categories = (Category[]) ois.readObject();
                categoryList.addAll(Arrays.asList(categories));
                ois.close();
                fis.close();
                System.out.println(categoryList);
            } else {
                f.createNewFile();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addProduct() {
        System.out.println("Input Product Name : ");
        String name = sc.nextLine();
        System.out.println("Input Category_Tag : ");
        String tag = sc.nextLine();
        productList.add(new Product(name, tag));
        if (checkCategoryTag(tag)) {
            System.out.println("Category tag doesn't exist !!!");
        } else {
            try {
                FileOutputStream fos = new FileOutputStream("Product.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                Product[] products = new Product[productList.size()];
                for (int index = 0; index < productList.size(); index++) {
                    products[index] = productList.get(index);
                }

                oos.writeObject(products);
                oos.close();
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private void getProductData() {
        productList = new ArrayList<>();
        try {
            File f = new File("Product.bin");
            if (f.exists()) {
                FileInputStream fis = new FileInputStream("Product.bin");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Product[] products = (Product[]) ois.readObject();
                productList.addAll(Arrays.asList(products));
                ois.close();
                fis.close();
                System.out.println(productList);
            } else {
                f.createNewFile();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getData() {
        getCategoryData();
        getProductData();
    }

    private void searchProduct() {
        ArrayList<Product> resultList = new ArrayList<>();
        System.out.println("Enter category_tag : ");
        String tag = sc.nextLine();
        if (checkCategoryTag(tag)) {
            System.out.println("Category tag doesn't exist !!!");
        } else {
            for (Product product : productList) {
                if (product.getCategory_tag().equalsIgnoreCase(tag)) {
                    resultList.add(product);
                }
            }

            Collections.sort(resultList, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return p1.getName().toLowerCase().compareTo(p2.getName().toLowerCase());
                }

            });
            if (resultList.isEmpty()) {
                System.out.println("No products found in this category !!!");
            } else {
                System.out.println("Result : ");
                for (Product product : resultList) {
                    System.out.println(product.getName());
                }
            }
        }
    }

    private boolean checkCategoryTag(String tag) {
        for (int index = 0; index < categoryList.size(); index++) {
            if (categoryList.get(index).getTag().equalsIgnoreCase(tag)) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        boolean loop = true;
        getData();
        while (loop) {
            System.out.println("\n1. Add Category \n"
                    + "2. Add Product \n"
                    + "3. Search by Category tag \n"
                    + "4. Exit \n"
                    + "Your choice :");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    addCategory();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    searchProduct();
                    break;
                case "4":
                    loop = false;
                    break;
                default:
                    System.out.println("You mujst choose one option !!!");
            }
        }
    }
}
