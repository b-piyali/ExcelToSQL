import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class ReadExcelFile {
    private void readFilesFromPathAndCompute() throws IOException {
        final ConfigObject configObject = new ConfigReader().parseJSON();
        generateFile(configObject);
    }

    private void generateFile(final ConfigObject configObject) {
        final String path = configObject.getFolderLocation();
        final File folder = new File(path);
        Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .filter(File::isFile)
                .map(File::getName)
                .forEach(fileNameWithExtension -> {
                    String inputFileName = path + "\\" + fileNameWithExtension;
                    String outputFileName = path + "\\" +
                            fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf('.')) +
                            configObject.getFileExtension();
                    try {
                        readExcelFile(configObject, inputFileName, outputFileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void readExcelFile(final ConfigObject configObject, final String inputFileName,
                               final String outputFileName) throws IOException {
        try (XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(inputFileName))) {
            configObject.getWorkSheets()
                    .stream()
                    .map(myExcelBook::getSheet)
                    .forEach(myExcelSheet -> readColumnValues(configObject, outputFileName, myExcelSheet));
        }
    }

    private void readColumnValues(final ConfigObject configObject, final String outputFile, final XSSFSheet myExcelSheet) {
        myExcelSheet.forEach(row -> {
            try {
                writeToFile(outputFile, row.getCell(configObject.getColumnIndexToRead()));
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
    }

    private void writeToFile(final String outputFile, final Cell cell) throws IOException {
        if (Objects.nonNull(cell) && StringUtils.isNotEmpty(outputFile)) {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFile, true), StandardCharsets.UTF_8))) {
                writer.write(cell.getStringCellValue() + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ReadExcelFile readExcelFile = new ReadExcelFile();
        /*String filePath = "C:\\EnergyComponents\\CA-Package\\JIRA\\ECAP-11841\\OneDrive_1_1-16-2020";
        List<String> workSheets = new ArrayList<>(Arrays.asList
                ("1) Sql Asset Data Load", "2) Sql STREAM WELL ANALYSIS", "3) Sql transactional data"));*/
        readExcelFile.readFilesFromPathAndCompute();
    }
}
