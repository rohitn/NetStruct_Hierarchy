import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Common {
	
	/*
	 * public static void WriteCommAnalysisToFile(List<CommId> comms, CommAnalyzer commAnalyzer, String pathS) throws IOException {
		Path path = Paths.get(pathS);	    	    
		
		int prevLevel=-1;
		for (CommId commId : comms){
			String commLine ="";
			int commSize = commId.GetNodes().size();
			if(commSize>20){
				int commLevel = commId.level;
				if (commLevel>prevLevel){
					Files.write(path, Arrays.asList(" ----------- LEVEL " + commLevel + " ----------- "), StandardCharsets.UTF_8, Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);					
					prevLevel = commLevel;
				}
				Map<String,Integer> mapCodeToCount = commAnalyzer.perCodeCounts(commId);
				commLine = commLine + "Size_" + String.format("%04d", commSize) + "_" + commId.toString() + "\t";			
				
				for(String[] codesArray : commAnalyzer.codes){
					commLine = commLine + "|";
					
					for(String code : codesArray){
						commLine = commLine + code + ":" + mapCodeToCount.get(code) + "\t";					
					}
				}
				
				
			}	
			Files.write(path, Arrays.asList(commLine), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		}
	}*/
	public static String greatestCommonPrefix(String a, String b) {
	    int minLength = Math.min(a.length(), b.length());
	    for (int i = 0; i < minLength; i++) {
	        if (a.charAt(i) != b.charAt(i)) {
	            return a.substring(0, i);
	        }
	    }
	    return a.substring(0, minLength);
	}

	public static String getDate(){
		Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh-mm_dd-MM-y");
        return dateFormatter.format(now);	
	}
	
	public static void writeToLog(String pathToLog, String msg, boolean debug) throws IOException{		
		String msgWithTime  = getDate() + " : " + msg;
	    System.out.println(msgWithTime);
	    if (!debug){   
			Path path = Paths.get(pathToLog);
		    Files.write(path, Arrays.asList(msgWithTime), StandardCharsets.UTF_8,
		        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
	    }
	}
	
	public static void renameFile(String pathToFile){
		String newName = pathToFile + "renamed.txt";
		if(!(new File(pathToFile)).renameTo(new File(newName))){
			renameFile(newName);	
			renameFile(pathToFile);
		}
	}
	
	public static int numOfLinesInFile(String pathToFile) throws IOException {
		File f = new File(pathToFile);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		int lines = 0;
		while (reader.readLine() != null) {
			lines++;			
		}
		reader.close();
		return lines;
	}
	
}
