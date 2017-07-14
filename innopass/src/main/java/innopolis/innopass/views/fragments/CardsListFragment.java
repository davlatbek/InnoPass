package innopolis.innopass.views.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import innopolis.innopass.R;
import innopolis.innopass.utilities.TempData;
import innopolis.innopass.views.adapters.CardRecyclerViewAdapter;
import innopolis.innopass.views.adapters.OnCardClickListener;

//import android.support.v4.app.Fragment;

/**
 * Created by davlet on 7/6/17.
 */

public class CardsListFragment extends Fragment implements OnCardClickListener{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CardRecyclerViewAdapter cardRecyclerViewAdapter;
    EditText editTextFilter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editTextFilter = (EditText) getActivity().findViewById(R.id.editTextFilter);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardRecyclerViewAdapter.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        cardRecyclerViewAdapter = new CardRecyclerViewAdapter(getActivity().getApplicationContext(),
                TempData.cardList);
        cardRecyclerViewAdapter.setOnCardClickListener(this);
        recyclerView.setAdapter(cardRecyclerViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onCardClick(View viev, int position) {
        AlertDialog.Builder cardDetails;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardDetails= new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            cardDetails = new AlertDialog.Builder(getActivity());
        }
        cardDetails.setTitle("Permissions")
                .setMessage("This user has following permissions:\n" +
                cardRecyclerViewAdapter.getCardByPosition(position).getPermissionList())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
