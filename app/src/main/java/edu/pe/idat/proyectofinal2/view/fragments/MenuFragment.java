package edu.pe.idat.proyectofinal2.view.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.adapters.CategoriaAdapter;
import edu.pe.idat.proyectofinal2.adapters.PopularProductAdapter;
import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.databinding.FragmentMenuBinding;
import edu.pe.idat.proyectofinal2.models.Categoria;
import edu.pe.idat.proyectofinal2.models.Product;
import edu.pe.idat.proyectofinal2.view.MainActivity;
import edu.pe.idat.proyectofinal2.viewmodels.CategoriaViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.PopularProductViewModel;


public class MenuFragment extends Fragment implements CategoriaAdapter.CategoriaInterface {

    private static final String TAG ="MenuFragment" ;
    CategoriaAdapter categoriaAdapter;
    PopularProductAdapter popularProductAdapter;

    private CategoriaViewModel categoriaViewModel;
    private PopularProductViewModel popularProductViewModel;

    FragmentMenuBinding fragmentMenuBinding;
    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMenuBinding =FragmentMenuBinding.inflate(inflater,container,false);
        //Banner presentacion
        ImageSlider imageSlider = fragmentMenuBinding.imageSlider;
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);




        return fragmentMenuBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Categoria
        categoriaAdapter = new CategoriaAdapter(this);
        fragmentMenuBinding.recCategory.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        fragmentMenuBinding.recCategory.setAdapter(categoriaAdapter);

        categoriaViewModel =new ViewModelProvider(this).get(CategoriaViewModel.class);
        categoriaViewModel.getCategorias(getActivity()).observe(getViewLifecycleOwner(), new Observer<List<Categoria>>() {
            @Override
            public void onChanged(List<Categoria> categorias) {
                categoriaAdapter.submitList(categorias);
            }
        });

        //Productos Populares
        popularProductAdapter = new PopularProductAdapter();
        fragmentMenuBinding.newProductRec.setLayoutManager(new GridLayoutManager(getContext(),2));
        fragmentMenuBinding.newProductRec.setAdapter(popularProductAdapter);

        popularProductViewModel = new ViewModelProvider(this).get(PopularProductViewModel.class);
        popularProductViewModel.getPopularProducto(getActivity()).observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                popularProductAdapter.submitList(products);
            }
        });



    }

    @Override
    public void onItemClick(Categoria categoria) {
        Log.d(TAG, "onItemClick" + categoria.toString());
        //enviamos los datos capturados
        categoriaViewModel.setCategoria(categoria);

        SharedPreferencesManager.setSomeIntValue(new Constantes().PREF_ID_CATEGORIA,categoria.getIdcategoria());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

    }
}