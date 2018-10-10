package dk.welzel.pinkodehusker;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private CardRepository mRepository;
    private LiveData<List<PIN_Card>> mAllCards;

    public CardViewModel(Application app) {
        super(app);
        mRepository = new CardRepository(app);
        mAllCards = mRepository.getAllCards();
    }

    LiveData<List<PIN_Card>> getAllCards() { return mAllCards; }

    public void insert(PIN_Card card) { mRepository.insert(card); }
}
