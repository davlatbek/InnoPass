package innopolis.innopass.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by davlet on 7/6/17.
 */

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserHolder> {
    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        public UserHolder(View itemView) {
            super(itemView);
        }
    }

}
