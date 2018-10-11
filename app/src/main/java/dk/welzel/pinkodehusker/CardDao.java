package dk.welzel.pinkodehusker;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CardDao {

    @Query("SELECT * FROM pin_card_table WHERE id = :id")
    PIN_Card loadCardById(int id);

    @Query("SELECT * FROM pin_card_table ORDER BY id ASC")
    LiveData<List<PIN_Card>> getAllCards();

    @Insert(onConflict = IGNORE)
    void insert(PIN_Card card);

    @Update(onConflict = REPLACE)
    void updateCard(PIN_Card card);

    @Delete
    void deleteCard(PIN_Card card);

    @Query("DELETE FROM pin_card_table")
    void deleteAll();
}
