package residential;

import java.util.List;

public interface FetchDataListenerr {
    public void onFetchComplete(List<Applicationr> data);
    public void onFetchFailure(String msg);
}
