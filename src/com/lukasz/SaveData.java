package com.lukasz;

import java.io.*;

/**
 * Created by Łukasz on 02.06.2017.
 */
public class SaveData {
    public static void save(MatchList matchList) {
        try(ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.dat")))) {
            output.writeObject(matchList.getDate());
            output.writeObject(matchList);
        }catch (IOException e) {
            System.out.println(e);
        }
    }
}
