package com.company.Altynay;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        DBManager manager=new DBManager();
        manager.connect();
        while(true)
        {
            System.out.println( "  PRESS [1] TO ADD ITEMS\n" +
                                "  PRESS [2] TO LIST ITEMS\n" +
                                "  PRESS [3] TO DELETE ITEMS\n" +
                                "  PRESS [0] TO EXIT");
            String choice=in.next();
            if(choice.equals("1")) {
                System.out.println("Insert name"+"\n"+"Insert price");
                String name=in.next();
                double price=in.nextDouble();
                Item i=new Item(null,name,price);
                manager.addItem(i);
            }
            else if(choice.equals("2"))
            {
                ArrayList<Item>items=manager.getALlItems();
                for(Item i:items)
                {
                    System.out.println(i.toString());
                }
            }
            else if(choice.equals("3"))
            {
                System.out.println("Insert id");
                Long id=in.nextLong();
                manager.deleteItem(id);
            }
            else if(choice.equals("0"))
            {
                System.exit(0);
            }


        }
    }
}
