package dk.welzel.pinkodehusker;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pin_card_table")
public class PIN_Card {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "card_name")
    public String cardName;

    @ColumnInfo(name = "img_file")
    public String imgFilePath;

    @ColumnInfo(name = "status")
    public String cardStatus;

    public String getName() { return this.cardName; }

    public String getImgFilePath() { return this.imgFilePath; }

    public String getStatus() { return this.cardStatus; }

    public void setName(String name) { this.cardName = name; }

    public void setImgFilePath(String filePath) { this.imgFilePath = filePath; }

    public void setCardStatus(String status) { this.cardStatus = status; }
}
