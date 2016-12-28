package testapp.viewer.model;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Random;

/**
 * Created by Den on 28.12.16.
 */

public class FileModel
{
    private String fileName;
    private boolean isFolder;
    private Date modDate;
    private FileType fileType;
    boolean isOrange;
    boolean isBlue;

//    Create random content for this model
    public FileModel(int i) {
        Random random = new Random();

        this.isFolder = random.nextBoolean();

        if (isFolder()) {
            this.fileName = "FOLDER " + i;
        } else {
            FileType type = FileType.getRandom();
            this.fileName = type + " " + i;
            this.modDate = DateTime.now().withTimeAtStartOfDay().toDate();
            this.fileType = type;
            this.isOrange = random.nextBoolean();
            this.isBlue = random.nextBoolean();
        }

    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public boolean isFolder()
    {
        return isFolder;
    }

    public void setFolder(boolean folder)
    {
        isFolder = folder;
    }

    public Date getModDate()
    {
        return modDate;
    }

    public void setModDate(Date modDate)
    {
        this.modDate = modDate;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public boolean isOrange()
    {
        return isOrange;
    }

    public void setOrange(boolean orange)
    {
        isOrange = orange;
    }

    public boolean isBlue()
    {
        return isBlue;
    }

    public void setBlue(boolean blue)
    {
        isBlue = blue;
    }

    public enum  FileType {
        IMAGE, PDF, MOVIE, MUSIC;

        public static FileType getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
}




