package cl.duoc.service;
import cl.duoc.dto.ProductoDTO;
import cl.duoc.entity.Producto;
import cl.duoc.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    public List<ProductoDTO> obtenerProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }
    public ProductoDTO crearProducto(ProductoDTO dto) {
        Producto producto = convertirAEntidad(dto);
        Producto productoGuardado = productoRepository.save(producto);
        return convertirADTO(productoGuardado);
    }
    private ProductoDTO convertirADTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria(),
                producto.getMarca()
        );
    }
    private Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        producto.setMarca(dto.getMarca());
        return producto;
    }

}