package testapp.viewer.database.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Den on 28.12.16.
 */

public class RFileModel extends RealmObject{

    @PrimaryKey
    private String fileName;
    private boolean isFolder;
    private Date modDate;
    private String fileType;
    boolean isOrange;
    boolean isBlue;

    public RFileModel() {
    }

    public RFileModel(String fileName, boolean isFolder, Date modDate, String fileType, boolean isOrange, boolean isBlue) {
        this.fileName = fileName;
        this.isFolder = isFolder;
        this.modDate = modDate;
        this.fileType = fileType;
        this.isOrange = isOrange;
        this.isBlue = isBlue;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isOrange() {
        return isOrange;
    }

    public void setOrange(boolean orange) {
        isOrange = orange;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

}
