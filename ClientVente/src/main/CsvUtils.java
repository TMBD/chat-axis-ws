package main;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader; 

public class CsvUtils {

	CSVReader reader = null; 
	int nbCol = 0;
	
	public CsvUtils(String filePath, int nbCol) throws FileNotFoundException {
		this.reader = new CSVReader(new FileReader(filePath)); 
		this.nbCol = nbCol;
	}
	
	//public ArrayList<String[]> getRecords(){
	public ArrayList<String[]> getRecords(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		try{   
			String [] nextLine;  
			while ((nextLine = reader.readNext()) != null){ 
				String [] lineContentArray = new String[nbCol];
				//int i = 0;
				for(String token : nextLine){  
					//System.out.print(token);  
					lineContentArray = token.split(";");
//					System.out.println(token);
//					i++;
				}  
				//System.out.println(lineContentArray[4]);  
				result.add(lineContentArray);
			}
		}  
		catch (Exception e){  
			e.printStackTrace();  
		}  
		  
		return result;
	}
}
