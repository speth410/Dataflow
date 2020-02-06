import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvParse {

  private String inFile;
  private List fileRows = new ArrayList();

  public CsvParse(String inFile) throws IOException {
    this.inFile = inFile;

    if (checkFile(inFile)) {
      readCsv(inFile);
      printCsv();
    }
  }

  private boolean checkFile(String inFile) {

    return Files.exists(Paths.get(inFile));
  }

  private void readCsv(String inFile) throws IOException {
    Reader reader = null;

    try {
      reader = Files.newBufferedReader(Paths.get(inFile));
      CSVReader csvReader = new CSVReader(reader);

      String[] nextRow;
      while ((nextRow = csvReader.readNext()) != null) {
        fileRows.add(nextRow);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    reader.close();
  }

  private void printCsv() {
    for (Object row : fileRows) {
      for (String fields : (String[]) row) {
        System.out.print(fields + ", ");
      }
      System.out.println("\n-----------------------------------------------------");
    }
  }
}
