package edu.pe.idat.proyectofinal2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.viewmodels.ProductViewModel;

public class MainActivity extends AppCompatActivity {
    //private static final String TAG = "MainActivity";
    //objeto tipo viewmodel

    ProductViewModel productViewModel;
    NavController navController;

    private int cartQuantity=0;
    //ACA VISUALIZAREMOS LA LISTA DE CADA PRODUCTO POR CATEGORIA!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.navig_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);

        //instanciamos el view
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        //CON ESTE METOD VEREMOS NUESTRO CARRITO
        productViewModel.getCart().observe(this, new Observer<List<CarritoItem>>() {
            @Override
            public void onChanged(List<CarritoItem> carritoItems) {
                //LOGD PARA VER CUANTOS ITEM HAY EN EL CARROy cuando esta incrementando en funcion al additem
               // Log.d(TAG, "onChanged: "+ carritoItems.size());
                //hacemos un loop para obtener las cantidades
                int cantidad = 0;
                for(CarritoItem carritoItem : carritoItems){
                    cantidad += carritoItem.getCantidad();
                }
                cartQuantity = cantidad;
                //este metodo se utiliza para decirle que el contenido a cambiado y se debe volver a ibujar algo asi :V
                invalidateOptionsMenu();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //buscamos el menuitem(menu/main_menu)
        MenuItem menuItem =menu.findItem(R.id.carritoFragment);
        //el view lo sacamos delmain_menmu
        View actionView = menuItem.getActionView();

        TextView cartBadgeTextView  = actionView.findViewById(R.id.cart_badge_textview);
        cartBadgeTextView.setText(String.valueOf(cartQuantity));

        //
        cartBadgeTextView.setVisibility(cartQuantity == 0 ? View.GONE : View.VISIBLE);



        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamamos al metodo onOptionsItemSelected para poder navegar
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item);
    }
}