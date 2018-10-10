package dk.welzel.pinkodehusker;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.File;

@Entity(tableName = "pin_card_table")
public class PIN_Card {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "card_name")
    public String cardName;

    @ColumnInfo(name = "img_file")
    public String imgFilePath;

    public String getName() { return this.cardName; }

    public String getImgFilePath() { return this.imgFilePath; }

    public void setName(String name) { this.cardName = name; }

    public void setImgFilePath(String filePath) { this.imgFilePath = filePath; }
}
