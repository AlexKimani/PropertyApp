package commercial;

import java.util.List;

public interface FetchDataListener {
    public void onFetchComplete(List<Applicationc> data);
    public void onFetchFailure(String msg);
}
