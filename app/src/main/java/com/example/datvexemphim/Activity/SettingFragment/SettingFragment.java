package com.example.datvexemphim.Activity.SettingFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.datvexemphim.Activity.ForAll.MainActivity;
import com.example.datvexemphim.Model.Account;
import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.KhachHang;
import com.example.datvexemphim.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvUser, tvHistory, tvDoiMk, tvOut;
    private KhachHang user;
    private Account tk;
    private List<HoaDon> dsHoaDon;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        createUser();
        createAcc();
        createHoaDon();

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUser = view.findViewById(R.id.tvUser);
        tvHistory = view.findViewById(R.id.tvHistory);
        tvOut = view.findViewById(R.id.tvOut);
        tvDoiMk = view.findViewById(R.id.tvDoiMk);

        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), User.class);

                intent.putExtra("user", user);

                startActivity(intent);
            }
        });

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), History.class);

                intent.putExtra("hd", (Serializable) getHoaDon(user.getId_kh()));

                startActivity(intent);
            }
        });

        tvOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialogOut();
            }
        });

        tvDoiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Detail_Account.class);

                intent.putExtra("tk", tk);

                startActivity(intent);
            }
        });
    }

    private List<HoaDon> getHoaDon(int id_kh) {
        List<HoaDon> hdForUser = new ArrayList<>();
        for (HoaDon hd : dsHoaDon) {
            if (hd.getId_kh() == id_kh) {
                hdForUser.add(hd);
            }
        }
        return hdForUser;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void createUser() {
        user = new KhachHang(1, "Nguyen Dinh Phat", "038202007493", "Thanh Hoa", false, 1);
    }

    private void createAcc() {
        tk = new Account(1, "mk", "tk", "email", "KH");
    }

    private void createHoaDon() {
        dsHoaDon = new ArrayList<>();
        dsHoaDon.add(new HoaDon(1, 1));
        dsHoaDon.add(new HoaDon(2, 1));
        dsHoaDon.add(new HoaDon(3, 2));
        dsHoaDon.add(new HoaDon(4, 2));
        dsHoaDon.add(new HoaDon(5, 1));
    }

    private void showConfirmationDialogOut() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage("Bạn có chắc chắn muốn đăng xuất??");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "ĐĂNG XUẤT",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        // Bắt đầu MainActivity
                        startActivity(intent);
                        // Kết thúc toàn bộ ứng dụng
                        getActivity().finishAffinity();
                        dialog.dismiss();
                    }
                });

        builder1.setNegativeButton(
                "HUỶ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}