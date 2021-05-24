/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    /**
     *This is where we will initiate our global variables
     */
    int quantity = 0;
    private int basePrice;


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
       EditText nameField = (EditText) findViewById(R.id.user_input_name_view);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name:" + name);

        // Figure out if customer wants whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hashWhippedCream =whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream: " + hashWhippedCream);

        // Figure out if customer wants chocolate
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate =chocolateCheckBox.isChecked();
        Log.v("MainActivity", "Has chocolate: " + hasChocolate);

        int price = calculatePrice(hashWhippedCream, hasChocolate);

        String priceMessage = createOrderSummary(name, price, hashWhippedCream, hasChocolate);
        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /**
     * @param name of the customer
     * @param price
     * @param addWhippedCream is whether or not the user wants Whipped Cream Topping
     * @param addChocolate is whether or not the user wants chocolate
     * @return priceMessage
     */
    private <string> String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nThank you for ordering " + quantity + "\nCoffees!";
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nAmount Due $" + price;
        priceMessage +="\n\nYour order will be right up!"; //I used the escape key \n to put the text on a new line
        displayMessage(priceMessage);
        return priceMessage;
    }
    /**
     * Calculates price of the order
     * @param addWhippedCream
     * @param addChocolate
     * @return total price
    */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
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

    TextView coffeeExtrasTextView = (TextView) findViewById(R.id.coffeeExtras);
    TextView.setOnClickListener(new View.OnClickListener) {
        @Override
                public void onClick(View v) {
            Intent CoffeeExtraIntent = new Intent(MainActivity,this, coffeeExtrasTextView.class)
            startActivity(coffeeExtrasTextView);
        }
    }

}