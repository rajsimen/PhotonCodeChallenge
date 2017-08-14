package android.mobile.photoncodechallenge.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.mobile.photoncodechallenge.LowestPathView;
import android.mobile.photoncodechallenge.R;
import android.mobile.photoncodechallenge.model.Matrix;
import android.mobile.photoncodechallenge.model.Output;
import android.mobile.photoncodechallenge.presenter.LowestCostPathPresenter;
import android.mobile.photoncodechallenge.utils.LeastCostPath;
import android.mobile.photoncodechallenge.utils.RecyclerViewAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raj
 * on 8/9/2017.
 */

public class LowestPathActivity extends Activity implements LowestPathView{

    private TextView matrixGrid,titleColumn;
    private Spinner testcaseList;
    private Map<String,int[][]> lowestPath;
    private String rows,matrixX,matrixY;
    private EditText matrixEditTextX,matrixEditTextY;
    private Button openTable,output;
    private RecyclerView recyclerViewMatrixGrid;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LowestCostPathPresenter lowestCostPathPresenter;
    private static String selectedItemFromDefaultTestCase="";
    private LeastCostPath leastCostPath;
    private int [][] userSelectedMatrixGrid;
    private boolean isDefault=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowestpath);
        //Initialize the Presenter
        lowestCostPathPresenter=new LowestCostPathPresenter(this);
        leastCostPath=new LeastCostPath();

        // get the static test cases from Resources
        String [] testCases= getResources().getStringArray(R.array.test_cases);
        lowestPath=lowestCostPathPresenter.initializeData(testCases);

        // Initialize the views
        initializeWidgets();
    }

    private void initializeWidgets(){
        matrixGrid =(TextView)findViewById(R.id.matrix_Grid);
        titleColumn=(TextView)findViewById(R.id.titleColumn);
        testcaseList =(Spinner)findViewById(R.id.testcase_List);
        matrixEditTextX=(EditText)findViewById(R.id.matrixX);
        matrixEditTextY=(EditText)findViewById(R.id.matrixY);
        openTable=(Button)findViewById(R.id.openTable);
        output=(Button)findViewById(R.id.output);
        recyclerViewMatrixGrid=(RecyclerView)findViewById(R.id.recyclerViewMatrixGrid);
        testcaseList.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        matrixEditTextX.setText("0");
        matrixEditTextY.setText("0");





        openTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matrixX=matrixEditTextX.getText().toString().trim();
                matrixY=matrixEditTextY.getText().toString().trim();
                userSelectedMatrixGrid= new int[Integer.parseInt(matrixX)][Integer.parseInt(matrixY)];
               lowestCostPathPresenter.validateUserMatrixXY(matrixX,matrixY);
                titleColumn.setText("Matrix Column : 1");


            }
        });

        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Output output=null;
                AlertDialog alertDialog = new AlertDialog.Builder(
                        LowestPathActivity.this).create();
                // Setting Dialog Title
                alertDialog.setTitle("Output");
                if (isDefault) {
                    if (!selectedItemFromDefaultTestCase.isEmpty() && selectedItemFromDefaultTestCase != null) {
                        output = leastCostPath.findLeastCostPath(lowestPath.get(selectedItemFromDefaultTestCase));
                    } else {
                        Snackbar.make(openTable, "Selected The Testcase", Snackbar.LENGTH_LONG).show();
                    }
                }else{

                    output = leastCostPath.findLeastCostPath(userSelectedMatrixGrid);

                }
                // Setting Dialog Message
                alertDialog.setMessage(String.valueOf(output.isCompletePath()) + "\n"
                        + String.valueOf(output.getLowestPathCost()) + "\n" + String.valueOf(Arrays.toString(output.getPath())));
                // Showing Alert Message
                alertDialog.show();
            }

        });

    }


    private void getMatrixGridDisplayed(String selectedTestCase){
        // visible the default Matrix Grid Displayed
        //matrixGrid.setVisibility(View.VISIBLE);
        selectedItemFromDefaultTestCase=selectedTestCase;
        rows="";
        int [][] selectedItemFromArray=lowestPath.get(selectedTestCase);

        Log.v("value",Arrays.deepToString(selectedItemFromArray));

        for (int [] array:selectedItemFromArray ) {
           // Arrays.toString(array);
            Log.v("value",Arrays.toString(array));
            rows+=Arrays.toString(array)+"\n";
        }

       matrixGrid.setText(rows);

    }




    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            // Make the Recycler View inVisible
            recyclerViewMatrixGrid.setVisibility(View.GONE);
           /* Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();*/
           // selectedItemFromDefaultTestCase=parent.getItemAtPosition(pos).toString();
            getMatrixGridDisplayed(parent.getItemAtPosition(pos).toString());
            titleColumn.setText("Matrix Column");
            isDefault=true;

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }

    }



    @Override
    public void errorMessageForEnterbelow10() {
        Snackbar.make(openTable,"Enter Below 10",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void errorMessageForEnterInputMatrix() {
        Snackbar.make(openTable,"Enter Valid Numeric",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void loadTheStaticKeyboadToRecyclerView(List<Matrix> matrixList,String inputX,String inputY) {
        // Invisible the default Matrix Grid Displayed
       // matrixGrid.setVisibility(View.GONE);
        matrixGrid.setText("");
        // Make recyclerViewMatrixGrid visible
        recyclerViewMatrixGrid.setVisibility(View.VISIBLE);
        // Adapter for the Custom Matrix Table User to Enter
        recyclerViewAdapter=new RecyclerViewAdapter(this,getApplicationContext(),matrixList,inputX,inputY);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMatrixGrid.setLayoutManager(mLayoutManager);
        recyclerViewMatrixGrid.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMatrixGrid.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void displayToSelectOutput() {
        Snackbar.make(openTable,"Click Output",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void printTheColumnTex(String columnName) {
       // titleColumn.setText("Matrix Column : "+ columnName);
    }

    @Override
    public void printTheRowsAndColumn(int [][] selectedMatrix) {
        isDefault=false;
        userSelectedMatrixGrid=selectedMatrix;
        matrixGrid.setText("Matrix Grid"+String.valueOf(Arrays.deepToString(userSelectedMatrixGrid)));
    }



}
