package com.company;
import java.io.*;

public class highest_Score implements Serializable
{
    int score;
    highest_Score() throws IOException
    {
        score=load();
    }
    void edit(int value) throws IOException
    {
        int highest=load();
        //System.out.println(load()+"*");
        if(highest>value)

        {
            ObjectOutputStream out = null;
            try
            {
                out = new ObjectOutputStream(new FileOutputStream("score.txt"));
                out.writeObject(highest);
            }
            finally
            {
                out.close();
            }
      }
        else
        {
            ObjectOutputStream out = null;
            try
            {
                out = new ObjectOutputStream(new FileOutputStream("score.txt"));
                out.writeObject(value);
            }
            finally
            {
                out.close();
            }
        }

    }
    int load() throws IOException
    {
        int retrieve = 0;
        ObjectInputStream in =null;
        try
        {
            in = new ObjectInputStream(new FileInputStream("score.txt"));
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