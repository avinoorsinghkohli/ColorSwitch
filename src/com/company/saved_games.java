package com.company;

import javafx.stage.Stage;

import java.io.*;

public class saved_games implements Serializable
{
    public void serialize(Game temp,String name) throws IOException
    {
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream("/home/avinoor/IdeaProjects/ball_jump/saved_games/a_"+name+".txt"));
            out.writeObject(temp);
        }
        finally{
            out.close();
        }
    }
    public void deserialize(String name) throws IOException
    {
        Game a=null;
        ObjectInputStream in =null;
        try
        {
            in = new ObjectInputStream(
                    new FileInputStream("/home/avinoor/IdeaProjects/ball_jump/saved_games/a_"+name+".txt")
            );
            a = (Game)in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            in.close();
        }
        assert a != null;

        Main.stage.setScene(a.setup());
    }
}

