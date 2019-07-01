/*
 * Copyright (c) 2017 - 2019 Oswald G. Brown, III
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ogbrown.utility.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class DaysOfWeekUtility {

    public DaysOfWeekUtility() {
        // TODO Auto-generated constructor stub
    }
    public static DayOfWeek getDayOfWeek(String dayOfWeek) {
        dayOfWeek = dayOfWeek.toLowerCase().substring(0,2);
        switch (dayOfWeek) {
        case "mo":
            return DayOfWeek.MONDAY;
        case "tu":
            return DayOfWeek.TUESDAY;
        case "we":
            return DayOfWeek.WEDNESDAY;
        case "th":
            return DayOfWeek.THURSDAY;            
        case "fr":
            return DayOfWeek.FRIDAY;            
        case "sa":
            return DayOfWeek.SATURDAY;
        default:
            return DayOfWeek.SUNDAY;
            
        }
    }
    
    public static String getDaysOfWeekCsv(TextStyle textStyle, String daysOfWeek) {
        String output = "";
        String[] daysOfWeeksArray = daysOfWeek.split(",");
        for (String day: daysOfWeeksArray) {
            day = day.toLowerCase();
            switch (day) {
            case "m":
                day = "mo";
                break;
            case "t":
                day = "tu";
                break;
            case "w":
                day = "we";
                break;
            case "f":
                day = "fr";
                break;
            case "s":
                day = "sa";
                break;
            default:
                day = "su";
            }
            if (output.length() > 0 ) output+=", ";
            output += getDayOfWeek(day).getDisplayName(textStyle, Locale.US);
        }
        return output;
    }
    public static DayOfWeek [] getDaysOfWeekArray(String daysOfWeek) {
        ArrayList<DayOfWeek> daysOfWeekArray = new ArrayList<>();
        String[] daysOfWeeksArray = daysOfWeek.split(",");
        for (String day: daysOfWeeksArray) {
            day = day.toLowerCase();
            switch (day) {
            case "m":
                day="mo";
                break;
            case "t":
                day="tu";
                break;
            case "w":
                day="we";
                break;
            case "f":
                day="fr";
                break;
            case "s":
                day="sa";
                break;
            default:
                day = "su";
            }
            daysOfWeekArray.add(getDayOfWeek(day));
        }
        return daysOfWeekArray.toArray(new DayOfWeek[daysOfWeekArray.size()]);
    }
}
