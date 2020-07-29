package com.example.tasarimj.Activites;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tasarimj.Api.ApiClient2;
import com.example.tasarimj.R;
import com.example.tasarimj.Services.Country;
import com.example.tasarimj.Services.CountryResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements
        View.OnClickListener {

    private Spinner ulkeSpinner;
    private Spinner sehirSpinner;
    private List<String> ulkelerArrayList;
    Context c = this;
    private ArrayAdapter<String> ulkeAdapter;
    private ArrayAdapter<String> sehirAdapter;

    private List<CountryResult> mCountry;
    private List<Object> mCity;
    private ArrayList<String> ulkeler;
    int ulkeId;
    int aralik = 0;
    int sehirId;
    private ArrayList<String> tmpArrayUlke;//sayı olmayan ülkeler listesini tutmak için
    private ArrayList<String> sehirlerArrayList;
    private ArrayList<String> sehirlerList;
    private ArrayList<Integer> sehirlerIdList;
    private ArrayList<String> tmpArray;

    public ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> list2 = new ArrayList<>();
    private EditText editName, editEmail, editPhone, editPassword, editPasswordRtry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }

    private void init() {

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        editPassword = findViewById(R.id.edit_password);
        editPasswordRtry = findViewById(R.id.edit_retrypassword);

        String ulkeText = getResources().getString(R.string.ulke);//Ülke spinnerın default değeri
        final String ilText = getResources().getString(R.string.sehir);//il spinnerin default değeri
        ulkeSpinner = findViewById(R.id.spinnerCountry);
        sehirSpinner = findViewById(R.id.spinnerProvince);
        sehirlerArrayList = new ArrayList<>();
        ulkelerArrayList = new ArrayList<>();
        tmpArrayUlke = new ArrayList<>();
        sehirlerList = new ArrayList<>();
        tmpArray = new ArrayList<>();
        sehirlerIdList = new ArrayList<>();

        try {//spinnerların acılıs boyunu ayarlama
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(ulkeSpinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(1000);
            Field popup2 = Spinner.class.getDeclaredField("mPopup");
            popup2.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow2 = (android.widget.ListPopupWindow) popup2.get(sehirSpinner);

            // Set popupWindow height to 500px
            popupWindow2.setHeight(1000);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        //string.xml icindeki ülkeler dizisini aldık
        tmpArrayUlke.add(ulkeText);//Ülke spinnerın default değerini diziye ekledim.
        getUlkeler();

        //string.xml icindeki sehirler dizisini aldık

        ulkeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {//ülke spineri ilk elemanı secili ise yani ülke yazıyorsa
                    ((TextView) (parent.getChildAt(0))).setTextColor(getResources().getColor(R.color.backgroundBlue));
                    tmpArray.clear();
                    tmpArray.add(ilText);// il spinerina il yazdım
                    sehirAdapter = new ArrayAdapter<>(c, R.layout.spinnertext2, tmpArray);
                    sehirSpinner.setAdapter(sehirAdapter);
                } else {
                    String ulkeNo = ulkelerArrayList.get(position - 1);
                    String tmpNo = ulkeNo;

                    if (tmpNo.substring(0, 2).contains("-")) {//ülke kodu 1-9 arasında ise ör(1-Türkiye)
                        ulkeNo = tmpNo.substring(0, 1); // ulkeno = 1
                        ulkeId = Integer.parseInt(tmpNo.substring(0, 1));
                        aralik = 2;
                    } else if (tmpNo.substring(0, 3).contains("-")) {//ülke kodu 10-99 arasında ise ör(10-Türkiye)
                        ulkeNo = tmpNo.substring(0, 2);// ulkeno = 10
                        ulkeId = Integer.parseInt(tmpNo.substring(0, 2));// ulkeno = 10
                        aralik = 3;
                    } else if (tmpNo.substring(0, 4).contains("-")) {//ülke kodu 100 den büyükse ör(100-Türkiye)
                        ulkeNo = tmpNo.substring(0, 3);// ulkeno = 100
                        ulkeId = Integer.parseInt(tmpNo.substring(0, 3));// ulkeno = 100
                        aralik = 4;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
       /* sehirSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (sehirSpinner.getSelectedItem().equals(ilText)){//eğer sehirler spinerında il yazıyorsa gri yap
                    ((TextView)(parent.getChildAt(0))).setTextColor(getResources().getColor(R.color.gri));
                }
                else{
                    for (int i=0;i<sehirlerList.size();i++){//gecici olarak sınıf tanımladım sonra sehirin id si ve adını ekledim
                        if (sehirlerList.get(i).getSehirIsmi().equals(sehirSpinner.getSelectedItem().toString())){
                            sehirId = sehirlerList.get(i).getSehirId();//eger spinnerdaki sehir adı listemdeki ile aynı ise
                            break;                                      //o sehirin id sini sehir id yap sonra cık
                        }
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        */
    }

    public void getUlkeler() {

        Call<Country> call = ApiClient2.getInstance().getApi().getCountry();
        call.enqueue(new Callback<Country>() {

            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

                mCountry = response.body().getCountryResult();

                if (response.isSuccessful()) {

                    list.add("Ulke");
                    for (int i = 0; i < mCountry.size(); i++) {

                        ulkelerArrayList.add((i + 1) + "-" + mCountry.get(i).getCountryName());

                        for (int j = 0; j < ulkelerArrayList.size(); j++) {//ulkeler dizisini
                            String ulkeNo = ulkelerArrayList.get(j);
                            String tmpNo = ulkeNo;
                            int aralik = 0;
                            if (tmpNo.substring(0, 2).contains("-")) {//ülke kodu 1-9 arasında ise ör(1-Türkiye)
                                ulkeNo = tmpNo.substring(0, 1); // ulkeno = 1
                                aralik = 2;
                            } else if (tmpNo.substring(0, 3).contains("-")) {//ülke kodu 10-99 arasında ise ör(10-Türkiye)
                                ulkeNo = tmpNo.substring(0, 2);// ulkeno = 10
                                aralik = 3;
                            } else if (tmpNo.substring(0, 4).contains("-")) {//ülke kodu 100 den büyükse ör(100-Türkiye)
                                ulkeNo = tmpNo.substring(0, 3);// ulkeno = 100
                                aralik = 4;
                            }
                            if (ulkelerArrayList.get(j).substring(0, aralik).equals(ulkeNo + "-")) {
                                //ülke spinnerdan secilen ülkenin numarası
                                //sehirler dizisindeki karsılıgını tmparraya ekle
                                int ulkeBoy = ulkelerArrayList.get(j).length();//ör 1-Ankara  içindeki '1-' kısmını silmek için
                                tmpArrayUlke.add(ulkelerArrayList.get(j).substring(aralik, ulkeBoy));
                            }
                        }
                        ulkeAdapter = new ArrayAdapter<>(c, R.layout.spinnertext2, tmpArrayUlke);
                        ulkeSpinner.setAdapter(ulkeAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*public void getSehirler() {
        try {
            sehirlerIdList.clear();
            sehirlerArrayList.clear();

            Call<CityResult> call = ApiClient2.getInstance().getApi().getCity(ulkeId);
            call.enqueue(new Callback<CityResult>() {

                @Override
                public void onResponse(Call<CityResult> call, Response<CityResult> response) {

                    mCity = response.body().getCityResult();
                    if (response.isSuccessful()) {

                        list.add("Ulke");
                        for (int i=0 ; i<mCity.size() ; i++ )
                        {
                            sehirlerArrayList.add(mCity.get(i).getString("_CountryID")+"-"+
                                    jo.getString("_CityName"));
                            sehirlerIdList.add(jo.getInt("_PlateNo"));
                            sehirlerList.add(new Sehirler(jo.getInt("_CityID"),jo.getString("_CityName")));
                            list.add(mCountry.get(i).getCountryName());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CityResult> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
                }
            });

            JSONArray ja = s.getJSONArray("CityResult");
                                for (int i = 0; i < ja.length(); i++) {
                                    JSONObject jo = ja.getJSONObject(i);
                                    sehirlerArrayList.add(jo.getString("_CountryID")+"-"+
                                            jo.getString("_CityName"));
                                    sehirlerIdList.add(jo.getInt("_PlateNo"));
                                    sehirlerList.add(new Sehirler(jo.getInt("_CityID"),jo.getString("_CityName")));
                                }
                                tmpArray.clear();//her secimde gecici diziyi temizliyoruz.
                                for (int i=0;i<sehirlerArrayList.size();i++){
                                    if (sehirlerArrayList.get(i).substring(0,aralik).equals(ulkeId+"-")){
                                        //ülke spinnerdan secilen ülkenin numarası
                                        //sehirler dizisindeki karsılıgını tmparraya ekle
                                        int sehirBoy = sehirlerArrayList.get(i).length();//ör 1-Ankara  içindeki '1-' kısmını silmek için
                                        tmpArray.add(sehirlerArrayList.get(i).substring(aralik,sehirBoy));
                                    }
                                }

                                Collections.sort(tmpArray);
                                sehirAdapter = new ArrayAdapter<>(c,R.layout.myspinner,tmpArray);
                                sehirSpinner.setAdapter(sehirAdapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(SignUp.this, getResources().getString(R.string.hatamesajliste), Toast.LENGTH_SHORT).show();
                }

                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-type", "application/json; charset=utf-8");
                    return headers;
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
        }
    }

     */
    public ArrayList<String> getCountry() {
        final String deneme1, den;
        Call<Country> call = ApiClient2.getInstance().getApi().getCountry();
        call.enqueue(new Callback<Country>() {

            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

                mCountry = response.body().getCountryResult();

                if (response.isSuccessful()) {

                    list.add("Ulke");
                    for (int i = 0; i < mCountry.size(); i++) {
                        list.add(mCountry.get(i).getCountryName());
                    }
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
            }
        });

        return list;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void signUp() {
        String name = Objects.requireNonNull(editName.getText()).toString().trim();
        String phone = Objects.requireNonNull(editPhone.getText()).toString().trim();
        String email = Objects.requireNonNull(editEmail.getText().toString().trim());
        String password = Objects.requireNonNull(editPassword.getText().toString().trim());
        String retryPass = Objects.requireNonNull(editPasswordRtry.getText().toString().trim());

        if (name.isEmpty()) {
            editName.setError(getResources().getString(R.string.bosAlanUyarisi));
            editName.requestFocus();
        } else if (email.isEmpty()) {
            editEmail.setError(getResources().getString(R.string.bosAlanUyarisi));
            editEmail.requestFocus();
        } else if (phone.isEmpty()) {
            editPhone.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPhone.requestFocus();
        } else if (phone.length() < 11) {
            editPhone.setError(getResources().getString(R.string.telefonNumarasiEksik));
            editPhone.requestFocus();
        } else if (ulkeSpinner.getSelectedItem().toString().equals("Country")) {
            Toast.makeText(this, getResources().getString(R.string.ulkeSecUyari), Toast.LENGTH_LONG).show();
        } else if (sehirSpinner.getSelectedItem().toString().equals("Province")) {
            Toast.makeText(this, getResources().getString(R.string.sehirSecUyari), Toast.LENGTH_LONG).show();

        } else if (password.isEmpty()) {
            editPassword.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPassword.requestFocus();
        } else if (retryPass.isEmpty()) {
            editPasswordRtry.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPasswordRtry.requestFocus();
        } else {
            if (password.equals(retryPass)) {
                Intent intent = new Intent(getBaseContext(), AddDeviceActivity.class);
                intent.putExtra("name", editName.getText().toString());
                intent.putExtra("email", editEmail.getText().toString());
                intent.putExtra("phone", editPhone.getText().toString());
                intent.putExtra("country", ulkeSpinner.getSelectedItem().toString());
                intent.putExtra("province", sehirSpinner.getSelectedItem().toString());
                intent.putExtra("password", editPassword.getText().toString());
                startActivity(intent);
            } else {
                editPasswordRtry.setError(getResources().getString(R.string.ayniSifreUyari));
                editPasswordRtry.requestFocus();
            }
        }

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                signUp();
                break;
        }
    }

}

        /*View.OnClickListener {

    private Spinner ulkeSpinner;
    private Spinner sehirSpinner;
    private List<String> ulkelerArrayList;
    private List<String> sehirlerArrayList;
    private EditText editName, editEmail, editPhone, editPassword, editPasswordRtry;
    private String sehir, ulke;

    Context c = this;
    private ArrayAdapter<String> ulkeAdapter;
    private ArrayAdapter<String> sehirAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }

    private void init() {

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        editPassword = findViewById(R.id.edit_password);
        editPasswordRtry = findViewById(R.id.edit_retrypassword);

        final ArrayList<String> tmpArrayUlke = new ArrayList<>();//sayı olmayan ülkeler listesini tutmak için
        tmpArrayUlke.add(getResources().getString(R.string.ulke));
        ulkeSpinner = findViewById(R.id.spinnerCountry);
        sehirSpinner = findViewById(R.id.spinnerProvince);
        //string.xml icindeki ülkeler dizisini aldık
        ulkelerArrayList = Arrays.asList(getResources().getStringArray(R.array.ulkeler));//string.xml icindeki ülkeler dizisini aldık
        for (int i = 0; i < ulkelerArrayList.size(); i++) {//ulkeler dizisini
            String ulkeNo = ulkelerArrayList.get(i);
            String tmpNo = ulkeNo;
            int aralik = 0;
            if (tmpNo.substring(0, 2).contains("-")) {//ülke kodu 1-9 arasında ise ör(1-Türkiye)
                ulkeNo = tmpNo.substring(0, 1); // ulkeno = 1
                aralik = 2;
            } else if (tmpNo.substring(0, 3).contains("-")) {//ülke kodu 10-99 arasında ise ör(10-Türkiye)
                ulkeNo = tmpNo.substring(0, 2);// ulkeno = 10
                aralik = 3;
            } else if (tmpNo.substring(0, 4).contains("-")) {//ülke kodu 100 den büyükse ör(100-Türkiye)
                ulkeNo = tmpNo.substring(0, 3);// ulkeno = 100
                aralik = 4;
            }
            if (ulkelerArrayList.get(i).substring(0, aralik).equals(ulkeNo + "-")) {
                //ülke spinnerdan secilen ülkenin numarası
                //sehirler dizisindeki karsılıgını tmparraya ekle
                int ulkeBoy = ulkelerArrayList.get(i).length();//ör 1-Ankara  içindeki '1-' kısmını silmek için
                tmpArrayUlke.add(ulkelerArrayList.get(i).substring(aralik, ulkeBoy));
            }
        }
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spineertext, tmpArrayUlke) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.WHITE);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spineertext);
        ulkeSpinner.setAdapter(spinnerArrayAdapter);


        sehirlerArrayList = Arrays.asList(getResources().getStringArray(R.array.iller));//string.xml icindeki sehirler dizisini aldık
        final ArrayList<String> tmpArray = new ArrayList<>();


        final ArrayAdapter<String> spinnerArrayAdapterProvince = new ArrayAdapter<String>(
                this, R.layout.spineertext, tmpArray) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(Color.WHITE);
                return view;
            }
        };

        ulkeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    String ulkeNo = ulkelerArrayList.get(position - 1);
                    String tmpNo = ulkeNo;
                    int aralik = 0;
                    if (tmpNo.substring(0, 2).contains("-")) {//ülke kodu 1-9 arasında ise ör(1-Türkiye)
                        ulkeNo = tmpNo.substring(0, 1); // ulkeno = 1
                        aralik = 2;
                    } else if (tmpNo.substring(0, 3).contains("-")) {//ülke kodu 10-99 arasında ise ör(10-Türkiye)
                        ulkeNo = tmpNo.substring(0, 2);// ulkeno = 10
                        aralik = 3;
                    } else if (tmpNo.substring(0, 4).contains("-")) {//ülke kodu 100 den büyükse ör(100-Türkiye)
                        ulkeNo = tmpNo.substring(0, 3);// ulkeno = 100
                        aralik = 4;
                    }
                    tmpArray.clear();//her secimde gecici diziyi temizliyoruz.

                    for (int i = 0; i < sehirlerArrayList.size(); i++) {
                        if (sehirlerArrayList.get(i).substring(0, aralik).equals(ulkeNo + "-")) {
                            //ülke spinnerdan secilen ülkenin numarası
                            //sehirler dizisindeki karsılıgını tmparraya ekle
                            int sehirBoy = sehirlerArrayList.get(i).length();//ör 1-Ankara  içindeki '1-' kısmını silmek için
                            tmpArray.add(sehirlerArrayList.get(i).substring(aralik, sehirBoy));
                        }
                        Collections.sort(tmpArray);
                        sehirAdapter = new ArrayAdapter<>(c, R.layout.spineertext, tmpArray);
                        sehirSpinner.setAdapter(sehirAdapter);
                    }
                }
                //                Collections.sort(tmpArray);
                tmpArray.add(getResources().getString(R.string.sehir));
                spinnerArrayAdapterProvince.setDropDownViewResource(R.layout.spineertext);
                sehirSpinner.setAdapter(spinnerArrayAdapterProvince);
                //sehirAdapter = new ArrayAdapter<>(c,R.layout.spineertext,tmpArray);
                //sehirSpinner.setAdapter(sehirAdapter);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void signUp() {
        String name = Objects.requireNonNull(editName.getText()).toString().trim();
        String phone = Objects.requireNonNull(editPhone.getText()).toString().trim();
        String email = Objects.requireNonNull(editEmail.getText().toString().trim());
        String password = Objects.requireNonNull(editPassword.getText().toString().trim());
        String retryPass = Objects.requireNonNull(editPasswordRtry.getText().toString().trim());

        if (name.isEmpty()) {
            editName.setError(getResources().getString(R.string.bosAlanUyarisi));
            editName.requestFocus();
        } else if (phone.isEmpty()) {
            editPhone.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPhone.requestFocus();
        } else if (email.isEmpty()) {
            editEmail.setError(getResources().getString(R.string.bosAlanUyarisi));
            editEmail.requestFocus();
        }
        else if (ulkeSpinner.getSelectedItem().toString().equals("Country")) {
            Toast.makeText(this, getResources().getString(R.string.ulkeSecUyari), Toast.LENGTH_LONG).show();
        } else if (sehirSpinner.getSelectedItem().toString().equals("Province")) {
            Toast.makeText(this, getResources().getString(R.string.sehirSecUyari), Toast.LENGTH_LONG).show();

        }else if (password.isEmpty()) {
            editPassword.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPassword.requestFocus();
        } else if (retryPass.isEmpty()) {
            editPasswordRtry.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPasswordRtry.requestFocus();
        }  else {
            if (password.equals(retryPass)) {
                Intent intent = new Intent(getBaseContext(), AddDeviceActivity.class);
                intent.putExtra("name", editName.getText().toString());
                intent.putExtra("email", editEmail.getText().toString());
                intent.putExtra("phone", editPhone.getText().toString());
                intent.putExtra("country", ulkeSpinner.getSelectedItem().toString());
                intent.putExtra("province", sehirSpinner.getSelectedItem().toString());
                intent.putExtra("password", editPassword.getText().toString());
                startActivity(intent);
            } else {
                editPasswordRtry.setError(getResources().getString(R.string.ayniSifreUyari));
                editPasswordRtry.requestFocus();
            }
        }

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
        /*

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                signUp();
                break;
        }
    }

}

         */