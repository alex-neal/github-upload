/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class BuildLatex {
    private static String output;
    
    public static String createString(String venue, String date, Set[] sets) {
        output = "";
        output += "\\documentclass[14pt]{extarticle}\n" +
                    "\\renewcommand{\\seriesdefault}{\\bfdefault}\n" +
                    "\\usepackage{caption}\n" +
                    "\\begin{document}\n" +
                    "	\\pagenumbering{gobble}\n" +
                    "\\begin{center}\n" +
                    "\\begin{table}[t]\n" +
                    "	\\begin{center}\n" +
                    "		\\caption*{";
        output += "Armstead at " + venue + ", " + date;
        output += "}\n" +
                    "		\\bgroup\n" +
                    "		\\def\\arraystretch{1.5}\n" +
                    "		\\begin{tabular}{";
        String columnStyle = "";
        for (int i = 0; i < sets.length; i++) {
            columnStyle += "l";
            if (i < sets.length - 1)
                columnStyle += "|";
        }
        output += columnStyle;
        output +=   "}\n";
        for (int i = 0; i < sets.length; i++) {
            output += "\\textbf{Set " + (i + 1) + "}";
            if (i < sets.length - 1) {
                output += " & ";
            } else {
                output += "\\\\\n";
            }
        }
        output +=   "			\\hline\n";
        int maxNumSongs = 0;
        for (int i = 0; i < sets.length; i++) {
            if (sets[i].getNumSongs() > maxNumSongs)
                maxNumSongs = sets[i].getNumSongs();
        }
        for (int i = 0; i < maxNumSongs; i++) {
            for (int j = 0; j < sets.length; j++) {
                output += sets[j].getTitle(i);
                if (j < sets.length - 1)
                    output += " & ";
                else
                    output += "\\\\\n";
            }
        }
        
        output +=   "		\\end{tabular}\n" +
                    "		\\egroup\n" +
                    "	\\end{center}\n" +
                    "\\end{table}\n" +
                    "\\end{center}\n" +
                    "\n" +
                    "\\end{document}";
        return output; 
    }
    

}
