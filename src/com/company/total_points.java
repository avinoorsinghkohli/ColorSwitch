package com.company;

import java.io.*;

public class total_points implements Serializable
{
    int points;
    total_points() throws IOException
    {
        points=load();
    }
    void edit(int value) throws IOException
    {
        int points=load();
        //System.out.println(load());
        points=points+value;
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream("points.txt"));
            out.writeObject(points);
        }
        finally
        {
            out.close();
        }
    }
    int load() throws IOException
    {
        int retrieve = 0;
        ObjectInputStream in =null;
        try
        {
            in = new ObjectInputStream(new FileInputStream("points.txt"));
            retrieve =(int)in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            in.close();
        }
        return retrieve;
    }
}
