package com.lukasz;

import java.io.*;
import java.time.YearMonth;
import java.util.Calendar;

/**
 * Created by Łukasz on 02.06.2017.
 */
public class LoadData {
    public static MatchList load() {
        MatchList matchList;
        String date = getDate(0);


        try(ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data.dat")))) {
            String loadedDate = (String) input.readObject();
            if (date.equals(loadedDate)) {
                System.out.println("Plik na dysku aktualny. Laduje mecze z pliku.");
                System.out.println();
                matchList = (MatchList) input.readObject();
                return matchList;
            }
        }catch(FileNotFoundException e) {
        }catch (IOException e) {
            System.out.println("Blad pliku");
        }catch (ClassNotFoundException e) {
            System.out.println("Blad pliku");
        }

        System.out.println("Sciagam mecze z Internetu.");
        System.out.println();
        matchList = SiteParser.parse(date);
        SaveData.save(matchList);
        return matchList;
    }


    private static String getDate(int daysFromNow) {
        String yearMonth = YearMonth.now().toString() + "-";
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + daysFromNow;
        if (day < 10) yearMonth += "0";
        return yearMonth + day;
    }
}
