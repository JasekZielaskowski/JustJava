/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    /**
     *This is where we will initiate our global variables
     */
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createWeatherMessage(77, "San Francisco");
    }

    private String createWeatherMessage(int temperature, String citName) {
        return "Welcome to San Francisco where the temperature is" + temperature + "F";
    }



    /**
     * This method is called when the increment button is clicked
     */
    public void increment(View view) {
        //quantity = quantity + 1;  ***this is one way to do it***
        quantity++;  //This is a simpler way to do it
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked
     */
    public void decrement(View view) {
        //quantity = quantity - 1;  ***this is one way to do it***
        if(quantity>0) {
            quantity--;  //This is a simpler way to do it
            displayQuantity(quantity);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hashWhippedCream =whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream: " + hashWhippedCream);
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hashWhippedCream);
        displayMessage(priceMessage);





    }

    /**
     * @param price
     * @param addWhippedCream is whether or not the user wants Whipped Cream Topping
     * @return priceMessage
     */

    private <string> String createOrderSummary(int price, boolean addWhippedCream) {
        String priceMessage = "Thank you for ordering." + "\nPrice $" + (quantity * 5);
        priceMessage += "\nAdd Whipped Cream?" + addWhippedCream;
        priceMessage +="\n\nYour order will be right up!"; //I used the escape key \n to put the text on a new line
        displayMessage(priceMessage);
        return priceMessage;

    }


    /**
     * Calculates price of the order
     * @return total price
    */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }


}