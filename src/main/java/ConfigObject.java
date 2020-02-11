import java.util.List;
import java.util.function.Supplier;

public class ConfigObject implements Supplier<ConfigObject> {
    private int columnIndexToRead;
    private String fileExtension;
    private String folderLocation;
    private String tableName;
    private String operation;
    private List<String> workSheets;

    public int getColumnIndexToRead() {
        return columnIndexToRead;
    }

    public void setColumnIndexToRead(int columnIndexToRead) {
        this.columnIndexToRead = columnIndexToRead;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFolderLocation() {
        return folderLocation;
    }

    public void setFolderLocation(String folderLocation) {
        this.folderLocation = folderLocation;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<String> getWorkSheets() {
        return workSheets;
    }

    public void setWorkSheets(List<String> workSheets) {
        this.workSheets = workSheets;
    }

    @Override
    public String toString() {
        return "ConfigObject{" +
                "columnIndexToRead=" + columnIndexToRead +
                ", fileExtension='" + fileExtension + '\'' +
                ", folderLocation='" + folderLocation + '\'' +
                ", tableName='" + tableName + '\'' +
                ", operation='" + operation + '\'' +
                ", workSheets=" + workSheets +
                '}';
    }

    @Override
    public ConfigObject get() {
        ConfigObject configObject = new ConfigObject();
        configObject.columnIndexToRead = this.columnIndexToRead;
        configObject.fileExtension = this.fileExtension;
        configObject.folderLocation = this.folderLocation;
        configObject.tableName = this.tableName;
        configObject.operation = this.operation;
        configObject.workSheets.addAll(this.workSheets);
        return configObject;
    }
}
