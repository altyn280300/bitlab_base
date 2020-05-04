package com.company.Altynay;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private Connection connection;
    public void connect()//инициализируем подключение с базой данных
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
           connection =DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/bitlab_db?useUnicode=true&serverTimezone=UTC","root","");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addItem(Item item)
    {
        try{
            // Это интерфейс, включающий команду SQL отправленный в базу данных для анализа
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO items(id,name,price)"+"VALUES(NULL,?,?)");
            //методы prepared Statement для каждого тип данного
            statement.setString(1,item.getName());
            statement.setDouble(2,item.getPrice());

            int rows = statement.executeUpdate();
            statement.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<Item> getALlItems()
    {
        ArrayList<Item>itemList=new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement("Select *FROM items");
            ResultSet resultSet=statement.executeQuery();

                while(resultSet.next()){
                    Long id=resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    itemList.add(new Item(id, name, price));
                }
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        return itemList;

    }
    public void deleteItem(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM items WHERE id = ?"
            );
            statement.setLong(1, id);
            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
