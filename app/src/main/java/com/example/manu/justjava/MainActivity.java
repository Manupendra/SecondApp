package com.example.manu.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;int price=0;
    int pricePerCup = 5;
    String name = "";
    boolean hasWhippedCream;
    boolean hasChocolate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {


        display(++quantity);

    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {


        display(--quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox =(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckbox.isChecked();
        Log.v("MainActivity","has Wipped Cream: "+ hasWhippedCream);

        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox) ;
        hasChocolate = ChocolateCheckBox.isChecked();

        EditText text=(EditText) findViewById(R.id.Name_view);
        String Name=text.getText().toString();





        String message=createOrderSummary(calculatePrice(),hasWhippedCream,hasChocolate,Name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+ Name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * This method returns the summary of the order
     * @param price as input
     */
    private String createOrderSummary(int price, boolean addWhippedCream,boolean addChocolate,String name) {
        return ( "Name: "+name+"\nAdd Whipped Cream?"+addWhippedCream+"\nAdd Chocolate?"+addChocolate+"\nQuantity: "+quantity+"\nTotal: £"+price+"\nThank you!");
    }

    /**
     * This method displays the given calculate price per coffee cup
     */
    private int calculatePrice() {
        pricePerCup = 5;
        if(hasWhippedCream==true)
            pricePerCup +=1;
        if (hasChocolate==true)
            pricePerCup+=2;
        return quantity * pricePerCup;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}