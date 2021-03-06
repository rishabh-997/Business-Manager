package com.example.businessmanager.ProductList.MVP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.Cart.MVP.CartActivity;
import com.example.businessmanager.CheckOut.MVP.CheckOutActivity;
import com.example.businessmanager.ClientDashboard.MVP.ClientDashActivity;
import com.example.businessmanager.History.MVP.HistoryActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.Model_common.UnitList;
import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.PopUpActivity;
import com.example.businessmanager.ProductHistory.Model.ProdHistList;
import com.example.businessmanager.ProductHistory.Model.ProdHistResponse;
import com.example.businessmanager.ProductList.model.Comapany_list;
import com.example.businessmanager.ProductList.model.Comapny_response;
import com.example.businessmanager.ProductList.model.ProductList;
import com.example.businessmanager.ProductList.model.Product_Response;
import com.example.businessmanager.ProductList.model.SpecList;
import com.example.businessmanager.ProductList.model.SpecResponse;
import com.example.businessmanager.ProductList.model.SubCat_list;
import com.example.businessmanager.ProductList.model.SubCat_response;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity implements ProductListContract.view,ProductListAdapter.onNoteClickListener
{
    ProductListContract.presenter presenter;
    @BindView(R.id.prodlist_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.bottomsheetbar)
    ProgressBar progressBar;
    @BindView(R.id.cart_name)
    TextView cart_name;
    @BindView(R.id.cart_id)
    TextView cart_id;
    @BindView(R.id.cart_image)
    ImageView cart_image;
    @BindView(R.id.cart_cross)
    ImageView cart_cross;
    @BindView(R.id.cart_description)
    TextView cart_desciption;
    @BindView(R.id.cart_price)
    EditText cart_price;
    @BindView(R.id.cart_size)
    Spinner cart_size;
    @BindView(R.id.cart_increase_quantity)
    TextView cart_increase;
    @BindView(R.id.cart_decrease_quantity)
    TextView cart_decrease;
    @BindView(R.id.cart_item_quantity)
    EditText cart_quantity;
    @BindView(R.id.cart_nvm)
    EditText nvm;
    @BindView(R.id.cart_product_name)
    EditText product_name;
    @BindView(R.id.cart_submit)
    Button cart_submit;
    @BindView(R.id.lay4)
    LinearLayout linearLayout1;
    @BindView(R.id.lay5)
    LinearLayout linearLayout2;
    @BindView(R.id.cart_history)
    Button seehistory;

    @BindView(R.id.spinner_company)
    Spinner spinner_company;
    @BindView(R.id.spinner_category)
    Spinner spinner_category;

    @BindView(R.id.toolbar_text)
    TextView toolbartext;
    @BindView(R.id.toolbar_cart)
    ImageView toolbarcart;
    @BindView(R.id.bottom_sheet_add_to_cart)
    RelativeLayout bottom_sheet;
    BottomSheetBehavior sheetBehavior;

    List<UnitList> unitList=new ArrayList<>();
    List<Comapany_list> comapany_list=new ArrayList<>();
    List<SubCat_list> subcat_list=new ArrayList<>();
    List<ProductList> list=new ArrayList<>();
    List<SpecList> specifications=new ArrayList<>();

    SharedPref sharedPref;
    ProductListAdapter adapter;
    boolean isSheetClosed = true;
    String cost,size, unit,prod_name="",nvm_name="";
    ClientModel clientModel;
    ProdHistList prodHistList;
    String CompanyNameFull="",CompanyNameShort="",SubCategory="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        ButterKnife.bind(this);

        getSupportActionBar().hide();
        toolbartext.setText("Products");
        presenter=new ProductListPresenter(this);
        sheetBehavior=BottomSheetBehavior.from(bottom_sheet);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");
        presenter.getUnit();

        sharedPref=new SharedPref(this);
        CompanyNameShort=sharedPref.getCompany();
        presenter.getSubCategory(CompanyNameShort);


        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        break;
                    }
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        if (!isSheetClosed)
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    }
                    case BottomSheetBehavior.STATE_SETTLING: {
                        if (!isSheetClosed)
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    }

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        cart_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSheetClosed=true;
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        toolbarcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductListActivity.this, CartActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showtaost(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showdata(Product_Response body) {
        list.clear();
        list=body.getProductListList();
        adapter=new ProductListAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void setList(UnitResponse body) {
        unitList=body.getPUnitList();
    }

    @Override
    public void showCompanies(Comapny_response body) {
        comapany_list=body.getComapany_list();

        String[] companylistfinal=new String[comapany_list.size()];
        for(int i=0;i<comapany_list.size();i++){
            companylistfinal[i]=comapany_list.get(i).getComapanyfull();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductListActivity.this, android.R.layout.simple_list_item_1, companylistfinal);
        spinner_company.setAdapter(adapter);
        spinner_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                CompanyNameFull=spinner_company.getSelectedItem().toString();
                setCategory(spinner_company.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void showSubCategories(SubCat_response body) {
        subcat_list.clear();
        subcat_list=body.getSubcat_list();

        if(subcat_list.size()!=0) {
            String[] categoryListfinla = new String[subcat_list.size()];
            for (int i = 0; i < subcat_list.size(); i++) {
                categoryListfinla[i] = subcat_list.get(i).getSubcategory();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductListActivity.this, android.R.layout.simple_list_item_1, categoryListfinla);
            spinner_category.setAdapter(adapter);
            spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    SubCategory=spinner_category.getSelectedItem().toString();
                    presenter.getList(spinner_category.getSelectedItem().toString(),CompanyNameShort);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else
        {
            String[] cat=new String[1];
            cat[0]="No Categories Found";
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductListActivity.this, android.R.layout.simple_list_item_1, cat);
            spinner_category.setAdapter(adapter);
        }
    }

    @Override
    public void showSpecs(SpecResponse body) {
        specifications=body.getSpecList();
        String nospecs="No Specification details";
        String indent= "                 ";
        String message="";
        for(int i=0;i<specifications.size();i++)
        {
            message=message+specifications.get(i).getHeading()+" - "+specifications.get(i).getValue()+"\n";
        }

        if(specifications.size()==0)
            message=nospecs;

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Specifications");
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void eraseSheet()
    {
        progressBar.setVisibility(View.GONE);
        cart_price.setText("");
        cart_quantity.setText("");
        nvm.setText("");
        product_name.setText("");

    }

    @Override
    public void displayHistory(ProdHistResponse body)
    {
        progressBar.setVisibility(View.GONE);
        if(body.getProductList().size()>0) {
            prodHistList = body.getProductList().get(0);
            Intent intent = new Intent(this, PopUpActivity.class);
            intent.putExtra("history", prodHistList);
            startActivity(intent);
        }
        else
            Toast.makeText(this, clientModel.getName()+" has never ordered this product.", Toast.LENGTH_LONG).show();
    }

    private void setCategory(String Company)
    {
        for(int i=0;i<comapany_list.size();i++)
        {
            if(Company.equals(comapany_list.get(i).getComapanyfull())) {
                CompanyNameShort = comapany_list.get(i).getCompanyshort();
                break;
            }
        }
    }

    @Override
    public void onNoteClick(final int position)
    {
        String[] unitlistfinal=new String[unitList.size()];
        for(int i=0;i<unitList.size();i++)
            unitlistfinal[i]=unitList.get(i).getUnit();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductListActivity.this, android.R.layout.simple_list_item_1, unitlistfinal);
        cart_size.setAdapter(adapter);
        cart_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(sharedPref.getCompany().equals("BCHEM"))
        {
            linearLayout1.setVisibility(View.INVISIBLE);
            linearLayout2.setVisibility(View.GONE);
        }

        final ProductList productList=list.get(position);
        cart_name.setText(productList.getName());
        cart_id.setText(productList.getId());
        cart_desciption.setText(productList.getDescription());
        Picasso.get().load(productList.getImage_url()).into(cart_image);

        isSheetClosed = false;
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        unit =cart_quantity.getText().toString();

        cart_decrease.setVisibility(View.GONE);
        cart_increase.setVisibility(View.GONE);

        cart_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit =""+(Integer.valueOf(unit)+1);
                cart_quantity.setText(unit);
            }
        });
        cart_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit =""+(Integer.valueOf(unit)-1);
                cart_quantity.setText(unit);
            }
        });

        cart_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(cart_price.getText().toString().isEmpty())
                    Toast.makeText(ProductListActivity.this, "Please Enter The Price", Toast.LENGTH_SHORT).show();

                else {
                    onBackPressed();
                    hideKeyboard();
                    addinCart(position);
                }
            }
        });
        cart_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        seehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProdHistory(clientModel.getMobile(),productList.getId());
            }
        });
    }

    private void showProdHistory(String mobile, String pid) {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getProdHist(mobile,pid);
    }

    @Override
    public void onSpecClick(int position)
    {
        presenter.getSpecs(list.get(position).getName());
    }

    private void addinCart(int position)
    {
        progressBar.setVisibility(View.VISIBLE);
        String mobile=clientModel.getMobile();
        String pid=list.get(position).getId();
        cost=cart_price.getText().toString();
        size=cart_size.getSelectedItem().toString();
        unit=cart_quantity.getText().toString();

        if(!sharedPref.getCompany().equals("BCHEM")) {
            prod_name = product_name.getText().toString();
            nvm_name = nvm.getText().toString();
        }
        else
        {
            prod_name = "";
            nvm_name = "";
        }
        presenter.addCart(mobile,pid,size,cost,unit,nvm_name,prod_name);
    }

    @Override
    public void onBackPressed() {
        if(!isSheetClosed)
        {
            isSheetClosed=true;
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        else
            super.onBackPressed();

    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
