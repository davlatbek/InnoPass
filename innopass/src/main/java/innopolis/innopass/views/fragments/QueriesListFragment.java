package innopolis.innopass.views.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import innopolis.innopass.R;
import innopolis.innopass.models.entities.PassCard;
import innopolis.innopass.utilities.TempData;
import innopolis.innopass.views.adapters.OnQueryClickListener;
import innopolis.innopass.views.adapters.QueryRecyclerViewAdapter;

/**
 * Created by davlet on 7/6/17.
 */

public class QueriesListFragment extends Fragment implements OnQueryClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    QueryRecyclerViewAdapter queryRecyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        queryRecyclerViewAdapter = new QueryRecyclerViewAdapter(
                getActivity().getApplicationContext(), TempData.queryCardList);
        queryRecyclerViewAdapter.setQueryClickListener(this);
        recyclerView.setAdapter(queryRecyclerViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onQueryClick(View view, final int position) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }

        builder.setTitle("Query")
                .setMessage("This query has next permissions: \n" +
                        queryRecyclerViewAdapter.getQueryByPosition(position).
                                getPermissionList().toString() +
                        "\n\nCreate card for this query?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with adding new card to db
                        TempData.cardList.add(new PassCard(10L, null,
                                queryRecyclerViewAdapter.getQueryByPosition(position).getUser(),
                                true, false,
                                queryRecyclerViewAdapter.getQueryByPosition(position).getPermissionList()));

                        queryRecyclerViewAdapter.getQueryByPosition(position).setStatus(false);
                        queryRecyclerViewAdapter.removeQueryByPosition(position);

                        Toast.makeText(getActivity(), "Created new passcard. Check it out in cards section.",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        Toast.makeText(getActivity(), "CANCELLED", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
