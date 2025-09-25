package features.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sistema_ventas.R;


import java.util.List;

import Dto.ProductoDTO;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductoDTO> productList;

    public ProductAdapter(List<ProductoDTO> productList) {
        this.productList = productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName, txtPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProducto);  // Asegúrate de que este ID coincide con el XML
            txtName = itemView.findViewById(R.id.txtProductoNombre);  // Asegúrate de que este ID coincide
            txtPrice = itemView.findViewById(R.id.txtProductoPrecio);  // Asegúrate de que este ID coincide
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_product_card, parent, false);  // Usa el layout de cada producto
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductoDTO product = productList.get(position);
        holder.txtName.setText(product.getNombre());
        holder.txtPrice.setText("S/. " + product.getPrecio());

        // Usar Glide para cargar la imagen del producto
        Glide.with(holder.imgProduct.getContext())
                .load(product.getImagenUrl())  // URL de la imagen (asegúrate de tener la URL en el DTO)
                .into(holder.imgProduct);  // Coloca la imagen en el ImageView
    }

    @Override
    public int getItemCount() {
        return productList.size();  // Número de elementos en la lista de productos
    }

}